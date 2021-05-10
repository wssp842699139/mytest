package com.test.service;

import com.test.Utils.MD5Utils;
import com.test.bean.User;
import com.test.dao.LoginDao;
import com.test.exception.RemotionServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/17 19:45
 * @package com.test.service
 */
@Service
public class LoginService {
    @Autowired
    private LoginDao loginDao;


    //用户注册的方法
    public void register(String username, String savePassword) throws RemotionServiceException {
        /*RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.21.41.181:2181,10.21.42.47:2181,10.21.49.252:2181", retryPolicy);

        client.start();

        //创建分布式锁, 锁空间的根节点路径为/curator/lock

        InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");

        mutex.acquire();*/
        User user = loginDao.selectUsername(username);
        if (null == user) {
            User user1 = new User();
            user1.setUsername(username);
            user1.setPassword(savePassword);
            if (loginDao.save(user1) != 1) {
                throw new RemotionServiceException(500,"注册失败");
            }
        }else {
            throw new RemotionServiceException(500,"改账号已经被注册过了");
        }
       /* mutex.release();

        //关闭客户端

        client.close();*/
    }

    //用户登录的方法
    public void login(String username, String password) throws RemotionServiceException {
        String md5Password = MD5Utils.md5Password(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(md5Password);
        User user2 = loginDao.selectUsername(username);
        if (user2 == null){
            throw new RemotionServiceException(500,"找不到改账号");
        }else {
            User user1 = loginDao.selectByUsernameAndPassword(user);
            //保存登录状态到cookie中
            if (user1 == null) {
                throw new RemotionServiceException(500, "密码错误");
            }
        }
    }
}
