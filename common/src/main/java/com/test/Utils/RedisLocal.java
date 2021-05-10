package com.test.Utils;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.exceptions.JedisNoScriptException;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @version V1.0
 * @Author ship
 * @Date 2021/4/11 22:40
 * @package com.test.Utils
 */
public class RedisLocal {
    /*
     * qsh
     * jedis单机模式
     * */
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;


    /**
     * qsh
     * jedis单机模式
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间 单位毫秒
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {
        System.out.println(Thread.currentThread()+"开始获取锁");
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(expireTime);
        String result = jedis.set(lockKey, requestId, setParams);
        if ("ok".equals(result)) {
            System.out.println(Thread.currentThread()+"获取锁成功");
            return true;
        }
        System.out.println(Thread.currentThread()+"获取锁失败");
        return false;
    }


    /**
     * qsh
     * jedis单机模式
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {
        System.out.println(Thread.currentThread()+"开始释放锁");
        //启动lua脚本
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }


    /*
     * qsh
     * jedis集群模式
     * */
    private static final String DISTRIBUTE_LOCK_SCRIPT_UNLOCK_VAL = "if" +
            " redis.call('get', KEYS[1]) == ARGV[1]" +
            " then" +
            " return redis.call('del', KEYS[1])" +
            " else" +
            " return 0" +
            " end";
    private static volatile String unlockSha1 = "";
    private static final Long UNLOCK_SUCCESS_CODE = 1L;
    private static final String LOCK_SUCCESS_CODE = "ok";

    /**
     * qsh
     * jedis集群模式
     * 根据loopTryTime循环重试
     * @param lockKey 锁key
     * @param lockVal 锁值，用于解锁校验
     * @param expiryTime 锁过期时间
     * @param loopTryTime 获取失败时，循环重试获取锁的时长
     * @return 是否获得锁
     */
    public static boolean tryLock(String lockKey, String lockVal, long expiryTime, long loopTryTime,JedisCluster jedisCluster){
        Long endTime = System.currentTimeMillis() + loopTryTime;
        while (System.currentTimeMillis() < endTime){
            if (tryLock(lockKey, lockVal, expiryTime,jedisCluster)){
                return true;
            }
        }
        return false;
    }

    /**
     * qsh
     * jedis集群模式
     * 根据loopTryTime循环重试
     * @param lockKey 锁key
     * @param lockVal 锁值，用于解锁校验
     * @param expiryTime 锁过期时间
     * @param retryTimes 重试次数
     * @param setpTime 每次重试间隔 mills
     * @return 是否获得锁
     */
    public static boolean tryLock(String lockKey, String lockVal, long expiryTime, int retryTimes, long setpTime,JedisCluster jedisCluster){
        while (retryTimes > 0){
            if (tryLock(lockKey, lockVal, expiryTime,jedisCluster)){
                return true;
            }
            retryTimes--;
            try {
                Thread.sleep(setpTime);
            } catch (InterruptedException e) {
                System.out.println("get distribute lock error" +e.getLocalizedMessage());
            }
        }
        return false;
    }

    /**
     * qsh
     * jedis集群模式
     * 一次尝试，快速失败。不支持重入
     * @param lockKey 锁key
     * @param lockVal 锁值，用于解锁校验
     * @param expiryTime 锁过期时间 MILLS
     * @return 是否获得锁
     */
    public static boolean tryLock(String lockKey, String lockVal, long expiryTime,JedisCluster jedisCluster){
        //相比一般的分布式锁，这里把setNx和setExpiry操作合并到一起，jedis保证原子性，避免连个命令之间出现宕机等问题
        //这里也可以我们使用lua脚本实现
        SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex((int) expiryTime);
        String result = jedisCluster.set(lockKey, lockVal,setParams);
        return LOCK_SUCCESS_CODE.equalsIgnoreCase(result);
    }

    /**
     * qsh
     * jedis集群模式
     * 释放分布式锁，释放失败最可能是业务执行时间长于lockKey过期时间，应当结合业务场景调整过期时间
     * @param lockKey 锁key
     * @param lockVal 锁值
     * @return 是否释放成功
     */
    public static boolean tryUnLock(String lockKey, String lockVal,JedisCluster jedisCluster){
        List<String> keys = new ArrayList<>();
        keys.add(lockKey);
        List<String> argv = new ArrayList<>();
        argv.add(lockVal);
        try {
            Object result = jedisCluster.evalsha(unlockSha1, keys, argv);
            return UNLOCK_SUCCESS_CODE.equals(result);
        }catch (JedisNoScriptException e){
            //没有脚本缓存时，重新发送缓存
            System.out.println("尝试重新发送缓存");
            storeScript(jedisCluster,lockKey);
            Object result = jedisCluster.evalsha(unlockSha1, keys, argv);
            return UNLOCK_SUCCESS_CODE.equals(result);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * qsh
     * jedis集群模式
     * 由于使用redis集群，因此每个节点都需要各自缓存一份脚本数据
     * @param slotKey 用来定位对应的slot的slotKey
     */
    public static void storeScript(JedisCluster jedisCluster, String slotKey){
        if (StringUtils.isEmpty(unlockSha1) || !jedisCluster.scriptExists(unlockSha1, slotKey)){
            //redis支持脚本缓存，返回哈希码，后续可以继续用来调用脚本
            unlockSha1 = jedisCluster.scriptLoad(DISTRIBUTE_LOCK_SCRIPT_UNLOCK_VAL, slotKey);
        }
    }
}
