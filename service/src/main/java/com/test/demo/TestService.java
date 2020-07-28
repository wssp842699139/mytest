package com.test.demo;

import com.test.Student;
import com.test.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/23 10:44
 * @package com.test.demo
 */
@Service
public class TestService {

    @Autowired
    private TestDao testDao;

    @Async
    void testasync() {
        for (int i = 0; i < 800000000; i++) {
            int a =10;
            int b = 5;
            a = a + i;
            b = a * b * a / a + 1;
        }
        System.out.println("执行了");
    }

    public Student selectStudent(String username) {
      return testDao.selectStudent(username);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insertStudent(Student student) {
        try {
            testDao.insertStudent(student);
            System.out.println("添加数据库成功了");
            int a =1/0;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStudent(Student student) {
        testDao.updateStudeng(student);
        int a = 1/0;
    }
}
