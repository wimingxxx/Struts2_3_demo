package com.qwm.struts2_3.d_test;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: wiming
 * @date: 2017-09-21 15:03:10  星期四
 * @decription:
 * 测试使用一下
 */
public class TestAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        System.out.println("--test--");
        return SUCCESS;
    }
}
