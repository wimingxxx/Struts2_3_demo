package com.qwm.struts2_3.c_params;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: wiming
 * @date: 2017-09-21 09:52:42  星期四
 * @decription:
 * 配置文件中配置传递参数的验证
 */
public class ParamsDemo5Action extends ActionSupport{
    private String name;
    private Integer age;
    @Override
    public String execute() throws Exception {
        System.out.println("name:"+name);
        System.out.println("age:"+age);
        return SUCCESS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
