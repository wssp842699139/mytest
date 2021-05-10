package com.test.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/7/3 11:34
 * @package com.test
 */
public class Student implements Serializable{
   private String username;
   private String address;
   private Integer age;
   private byte[] aa;

    public byte[] getAa() {
        return aa;
    }

    public void setAa(byte[] aa) {
        this.aa = aa;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(username, student.username) &&
                Objects.equals(address, student.address) &&
                Objects.equals(age, student.age) &&
                Arrays.equals(aa, student.aa);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(username, address, age);
        result = 31 * result + Arrays.hashCode(aa);
        return result;
    }
}
