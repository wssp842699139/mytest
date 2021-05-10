package com.test.设计模式等;

import com.test.bean.Student;
import com.test.dao.TestDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/23 10:44
 * @package com.test.excel
 */
@Service
@Slf4j
public class TestService {

    @Autowired
    private TestDao testDao;


    public List<Student> testSelect(String username) {
        Student student = new Student();
        student.setUsername(username);
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
        testDao.updateStudent(student);
        int a = 1/0;
    }

    public void testBatchInsert() {
        List<Student> a = new ArrayList<>();
        // 初始化10000个对象
        for (int i = 0; i < 500; i++) {
            Student student = new Student();
            student.setUsername("a");
            student.setAge(i);
            a.add(student);
        }

        // 批量执行
        long start = System.currentTimeMillis();
        for (Student student : a) {
            testDao.insertStudent(student);
        }
        long end = System.currentTimeMillis();

        // 输出执行耗时
        System.out.println("耗时:" + (end - start) + " ms!");
    }


}
