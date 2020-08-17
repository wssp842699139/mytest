package com.test;

import java.io.Serializable;

/**
 * @version V1.0
 * @Author ship
 * @Date 2020/8/1 23:00
 * @package com.test
 */
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String address;
    private int version;
    public User() {
    }

    public User(Integer id, String username, String password, String address, int version) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", version=" + version +
                '}';
    }
}
