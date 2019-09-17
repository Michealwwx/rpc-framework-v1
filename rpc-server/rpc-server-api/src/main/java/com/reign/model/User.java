package com.reign.model;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description user
 * @Author wuwenxiang
 * @Date 2019-09-17 21:11
 * @Version 1.0
 **/
public class User{

    private String name;

    private int age;


    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

