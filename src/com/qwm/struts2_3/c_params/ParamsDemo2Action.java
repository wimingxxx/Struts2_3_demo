package com.qwm.struts2_3.c_params;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * @author: wiming
 * @date: 2017-09-20 16:01:15  星期三
 * @decription:
 * 接收参数原理
 */
public class ParamsDemo2Action extends ActionSupport implements Preparable {
    //必须实例化
    private User user = new User();
    @Override
    public String execute() throws Exception {
        System.out.println(user.getName());
        return SUCCESS;
    }

    @Override
    public void prepare() throws Exception {
        ValueStack valueStack = ActionContext.getContext().getValueStack();
        valueStack.push(user);
    }
}