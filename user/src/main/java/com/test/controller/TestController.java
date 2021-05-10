package com.test.controller;


import com.test.bean.Student;
import com.test.设计模式等.TestService;
import com.test.设计模式等.设计模式.observe.Observeable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/6/4 15:01
 * @package com.test.excel
 */
@RestController
@RequestMapping("/test")
public class TestController {

    // public static Logger logger = org.slf4j.LoggerFactory.getLogger(TestController.class);


    @Autowired
    TestService testService;

    @Autowired
    JedisPool jedisPool;

    @Autowired
    Observeable observeable;


    private static Integer nowCount = 10000;
    private int c = 0;


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test() throws IOException {

    }

    @GetMapping("/testBatchInsert")
    public void testBatchInsert(
    ) {


        System.out.println("进来了");
        testService.testBatchInsert();
    }

    @GetMapping("/testSelect")
    public void testSelect(
            @RequestParam String username
    ) {
        List<Student> students = testService.testSelect(username);
        System.out.println(students.toString());
    }



    @RequestMapping("/testInsert")
    public void testInsert(
            @RequestBody Student student
    ) {
        System.out.println(student.toString());
        testService.insertStudent(student);
    }

    @RequestMapping("/testUpdate")
    public void testUpdate(
            @RequestBody Student student
    ) {
        testService.updateStudent(student);
    }

}
