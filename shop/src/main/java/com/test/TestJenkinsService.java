package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/1 22:55
 * @package com.test
 */
@Service
public class TestJenkinsService {

    @Autowired
    TestJenkinsDao testJenkinsDao;

    public int updateUser(User user) {
        int i = testJenkinsDao.updateUser(user);
        return i;
    }

    public List<User> selectUser(User user) {
        List<User> users = testJenkinsDao.selectUser(user);
        return users;
    }

    public int insertUser(User insertUser) {
        int i =testJenkinsDao.insertUser(insertUser);
        return i;
    }

    public List<User> selectUserByUsername(User user) {
        List<User> users = testJenkinsDao.selectUserByUsername(user);
        return users;
    }
}
