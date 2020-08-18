package com.test.dao;

import com.test.bean.Student;

import org.apache.ibatis.annotations.Mapper;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/25 10:55
 * @package com.test.dao
 */
@Mapper
public interface TestDao {




    Student selectStudent(String username);

    void insertStudent(Student student);

     void updateStudent(Student student);
}
