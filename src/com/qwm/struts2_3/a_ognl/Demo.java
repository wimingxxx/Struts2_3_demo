package com.qwm.struts2_3.a_ognl;

import com.qwm.struts2_3.bean.Employ;
import com.qwm.struts2_3.bean.TestBean;
import com.qwm.struts2_3.bean.User;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: wiming
 * @date: 2017-09-19 16:37:03  星期二
 * @decription:
 * OGNL测试
 */
public class Demo {

    /**
     * 基本语法
     * @throws OgnlException
     * 取出root中的属性值
     */
    @Test
    public void test1() throws OgnlException {
        //创建root
        User rootUser = new User("qiwenming",20);
//        User rootUser = new User();
        //准备context
        Map<String,User> context = new HashMap<>();
        context.put("user1",new User("wiming",10));
        context.put("user1",new User("xiaoming",12));
        OgnlContext oc = new OgnlContext();
        //将rootUser作为root部分
        oc.setRoot(rootUser);
        //将context这个Map作为Context部分
        oc.setValues(context);

        String name = (String) Ognl.getValue("name",oc,oc.getRoot());
        Integer age = (Integer) Ognl.getValue("age",oc,oc.getRoot());
        System.out.println(name);
        System.out.println(age);
    }

    /**
     * 基本语法
     * 取出context中的属性值
     */
    @Test
    public void test2() throws OgnlException {
        //创建root
        User rootUser = new User("qiwenming",20);
        //准备context
        Map<String,User> context = new HashMap<>();
        context.put("user1",new User("wiming",10));
        context.put("user2",new User("xiaoming",12));
        OgnlContext oc = new OgnlContext();
        oc.setRoot(rootUser);
        oc.setValues(context);

        //取出context中键为user1和user2对象的name属性
        String name = (String)Ognl.getValue("#user1.name",oc,oc.getRoot());
        String name2 = (String)Ognl.getValue("#user2.name",oc,oc.getRoot());
        System.out.println(name);
        System.out.println(name2);
    }


    /**
     * 基本语法
     * 修改context中的属性值
     * #对象名
     */
    @Test
    public void test3() throws OgnlException {
        //创建root
        User rootUser = new User("qiwenming",20);
        //准备context
        Map<String,User> context = new HashMap<>();
        context.put("user1",new User("wiming",10));
        context.put("user2",new User("xiaoming",12));
        OgnlContext oc = new OgnlContext();
        oc.setRoot(rootUser);
        oc.setValues(context);

        //对root中的user对象的name属性赋值
        String name = (String) Ognl.getValue("name='文明'",oc,oc.getRoot());
        //多个语句可以一起，如果是语句都是输出的话，那么输出最后一个
        String name2 = (String)Ognl.getValue("#user1.name='明明白白',#user1.name",oc,oc.getRoot());
        System.out.println(name);
        System.out.println(name2);
    }

    /**
     * 基本语法
     * 调用方法
     */
    @Test
    public void test4() throws OgnlException {
        //创建root
        User rootUser = new User("qiwenming",20);
        //准备context
        Map<String,User> context = new HashMap<>();
        context.put("user1",new User("wiming",10));
        context.put("user2",new User("xiaoming",12));
        OgnlContext oc = new OgnlContext();
        oc.setRoot(rootUser);
        oc.setValues(context);

        //调用root中user对象的setName方法赋值，然后再用 getName()取值
        String name = (String) Ognl.getValue("setName('geggegege') , getName()",oc,oc.getRoot());
        //user1的名称修改
        Ognl.getValue("#user1.setName('user1--test')",oc,oc.getRoot());
        String name2 = (String)Ognl.getValue("#user1.getName()",oc,oc.getRoot());
        System.out.println(name);
        System.out.println(name2);
    }

    /**
     * 基本语法
     * 调用静态方法
     * @全类名@方法名
     */
    @Test
    public void test5() throws OgnlException {
        //创建root
        User rootUser = new User("qiwenming",20);
        //准备context
        Map<String,User> context = new HashMap<>();
        context.put("user1",new User("wiming",10));
        context.put("user2",new User("xiaoming",12));
        OgnlContext oc = new OgnlContext();
        oc.setRoot(rootUser);
        oc.setValues(context);

        //@全类名@方法名
        Double sqrtTest1 = (Double) Ognl.getValue("@java.lang.Math@sqrt(100)",oc,oc.getRoot());
        Double sqrtTest2 = (Double) Ognl.getValue("@@sqrt(100)",oc,oc.getRoot());
        //@全类名@静态变量名
        Double PI1 = (Double) Ognl.getValue("@java.lang.Math@PI",oc,oc.getRoot());
        Double PI2 = (Double) Ognl.getValue("@@PI",oc,oc.getRoot());

        Integer int1 = (Integer)Ognl.getValue("@com.qwm.struts2_3.utils.TestUtils@add(10,12)",oc,oc.getRoot());
        Integer age = (Integer)Ognl.getValue("@com.qwm.struts2_3.utils.TestUtils@MY_AGE",oc,oc.getRoot());

        System.out.println("sqrtTest1:"+sqrtTest1);
        System.out.println("sqrtTest2:"+sqrtTest2);
        System.out.println("PI1:"+PI1);
        System.out.println("PI2:"+PI2);
        System.out.println("int1:"+int1);
        System.out.println("age:"+age);
    }

    /**
     * 基本语法
     * 调用静态方法
     * ognl创建对象-list|map
     */
    @Test
    public void test6() throws OgnlException {
        //创建root
        User rootUser = new User("qiwenming",20);
        //准备context
        Map<String,User> context = new HashMap<>();
        context.put("user1",new User("wiming",10));
        context.put("user2",new User("xiaoming",12));
        OgnlContext oc = new OgnlContext();
        oc.setRoot(rootUser);
        oc.setValues(context);

        //创建list 集合 使用{}
        List<String> list = (List<String>) Ognl.getValue("{'mingge','mingming','wiming','wenming'}",oc,oc.getRoot());
        //长度
        Integer size = (Integer) Ognl.getValue("{'mingge','mingming','wiming','wenming'}.size()",oc,oc.getRoot());
        //第0个值
        String oneStr = (String) Ognl.getValue("{'mingge','mingming','wiming','wenming'}[0]",oc,oc.getRoot());

        //Map集合 使用#{}
        Map<String,String> map = (Map<String, String>) Ognl.getValue("#{'name':'wiming','gender':'男'}",oc,oc.getRoot());
        String name = (String) Ognl.getValue("#{'name':'wiming','gender':'男'}['name']",oc,oc.getRoot());
        Character gender = (Character) Ognl.getValue("#{'name':'wiming','gender':'男'}.get('gender')",oc,oc.getRoot());

        System.out.println("--------");
        System.out.println(list);
        System.out.println(size);
        System.out.println(oneStr);
        System.out.println("--------");
        System.out.println(map);
        System.out.println(name);
        System.out.println(gender);
    }
}
