package com.qwm.struts2_3.b_stack;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: wiming
 * @date: 2017-09-20 15:15:28  星期三
 * @decription:
 * 查看值栈中的值
 */
public class StackDemoAction extends ActionSupport{
    @Override
    public String execute() throws Exception {
        System.out.println("--查看值栈--");
        return SUCCESS;
    }
}
