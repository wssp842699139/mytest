package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/13 19:46
 * @package com
 */
@RestController
public class TestJenkinsController {

    @Autowired
    TestJenkinsService testJenkinsService;

    private static final int defaultTime = 60;
    private static final String key = "testKey";

    @Autowired
    JedisPool jedisPool;

    @RequestMapping("/test")
    public String test1() {
        return "success";
    }


    @RequestMapping("/testRedisLocal")
    public void redisSuoTest() {
        Jedis redis = jedisPool.getResource();
        Long setnx = redis.setnx(key, "1");
        System.out.println("加锁返回的字段为 " + setnx);
        if (setnx ==1){
            //加锁成功了
            redis.expire(key,defaultTime);
            System.out.println("设置了锁超时时间");
            try {
                User user = new User();
                user.setPassword("123456");
                user.setUsername("张三");
                List<User> users = testJenkinsService.selectUser(user);
                if (!CollectionUtils.isEmpty(users)) {
                    System.out.println("查询到了这个角色了");
                    User updateUser = new User();
                    updateUser.setPassword("1111111");
                    updateUser.setUsername("张三");
                    updateUser.setVersion(users.get(0).getVersion());
                    int i = testJenkinsService.updateUser(updateUser);
                    if (i == 1) {
                        System.out.println("对角色进行修改了");
                    } else {
                        System.out.println("进来了但是没有修改成功");
                    }
                } else {
                    System.out.println("没有查询到这个角色");
                }
            } finally {
                System.out.println("删除锁之前看到的锁的value为 : " + redis.get(key));
                redis.del(key);
            }
        }else {
            System.out.println("不能重复修改");
        }
    }

    @RequestMapping("/insertLocalTest")
    public void insertLocalTest() {
        System.out.println("到这里了1");
        Jedis redis = jedisPool.getResource();
        System.out.println("到这里了2");
        Long setnx = redis.setnx(key, String.valueOf(System.currentTimeMillis()+defaultTime));
        System.out.println("加锁返回的字段为 " + setnx);
        boolean lock = false;
        if (setnx == 1) {
            lock = true;
        }else {
            //设置超时时间
            long oldTime = Long.parseLong(redis.get(key));
            System.out.println("旧的超时时间为 : " + oldTime);
            System.out.println("当前系统时间为 : " + System.currentTimeMillis());
            if (oldTime < System.currentTimeMillis()){
                //超时
                String getSet = redis.getSet(key, String.valueOf(System.currentTimeMillis() + defaultTime));
                System.out.println("getSet方法返回的值为 :" + getSet);
                if (Long.parseLong(getSet) == oldTime){
                    lock = true;
                    System.out.println("获取到了锁,并且现在value为 :" + redis.get(key));
                }
            }
        }
        if (lock){
            //加锁成功了
            try {
                redis.expire(key, defaultTime);
                System.out.println("设置了锁超时时间");
                User user = new User();
                user.setUsername("张三");
                List<User> users = testJenkinsService.selectUserByUsername(user);
                if (CollectionUtils.isEmpty(users)) {
                    System.out.println("查询不到这个角色 , 准备新增数据");
                    User insertUser = new User();
                    insertUser.setPassword("1111111");
                    insertUser.setUsername("张三");
                    int i = testJenkinsService.insertUser(insertUser);
                    if (i == 1) {
                        System.out.println("新增加了角色");
                    } else {
                        System.out.println("进来了但是没有新增成功");
                    }
                } else {
                    System.out.println("查询到了这个角色 , 不能新增");
                }
            } finally {
                System.out.println("删除锁之前看到的锁的value为 : " + redis.get(key));

                redis.del(key);
            }
        }else {
            System.out.println("不能重复创建角色");
        }
    }
}
