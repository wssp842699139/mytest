package com.test;

import java.io.Serializable;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/3 11:34
 * @package com.test
 */
public class Student implements Serializable{
    private String name;
    private String sex ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
