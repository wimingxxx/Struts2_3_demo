package com.qwm.struts2_3.c_params;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author: wiming
 * @date: 2017-09-21 09:52:42  星期四
 * @decription:
 * 配置文件中配置传递参数的验证
 */
public class ParamsDemo4Action extends ActionSupport{
    @Override
    public String execute() throws Exception {
        ActionContext.getContext().put("name","qiwenming");
        return SUCCESS;
    }
}
