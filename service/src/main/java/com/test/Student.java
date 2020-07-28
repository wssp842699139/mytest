package com.test;

import java.io.Serializable;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/3 11:34
 * @package com.test
 */
public class Student implements Serializable{
   private String username;
   private String address;

    public Student() {
    }

    public Student(String username, String address) {
        this.username = username;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
