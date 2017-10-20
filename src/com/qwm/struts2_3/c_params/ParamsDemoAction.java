package com.qwm.struts2_3.c_params;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: wiming
 * @date: 2017-09-20 16:01:15  星期三
 * @decription:
 * 接收参数原理
 */
public class ParamsDemoAction extends ActionSupport {
    private User user;
    @Override
    public String execute() throws Exception {
        System.out.println(user.getName());
        return SUCCESS;
    }

    public User getUser() {
        System.out.println("---getUser---"+user);
        return user;
    }

    public void setUser(User user) {
        System.out.println("---setUser------"+user);
        this.user = user;
    }
}