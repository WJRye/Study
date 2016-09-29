package com.wj.study.domain;

/**
 * Author：王江 on 2016/9/28 19:46
 * Description:
 */

public class User {
    private int id;
    private int age;
    private String name;
    private String number;
    private String bloodType;


    public User(int id, int age, String name, String bloodType) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.bloodType = bloodType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
