package com.qwm.struts2_3.c_params;

/**
 * @author: wiming
 * @date: 2017-09-20 19:14:36  星期三
 * @decription:
 */
public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
