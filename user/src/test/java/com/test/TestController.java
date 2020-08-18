package com.test;

import com.test.bean.Student;
import com.test.demo.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/25 17:57
 * @package PACKAGE_NAME
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestController {
    @Autowired
    private TestService testService;
    @Test
    public void testTest(){
        Student student = new Student();
        student.setUsername("ggg1");
        student.setAddress("ggg1");
        testService.insertStudent(student);
    }

}
