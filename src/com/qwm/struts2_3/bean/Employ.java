package com.qwm.struts2_3.bean;

public class Employ {
    private String name;
    private int age;

    public Employ() {
    }

    public Employ(String name, int age) {
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
