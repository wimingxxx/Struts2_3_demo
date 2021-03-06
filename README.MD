# Struts2 学习之OGNL、拦截器、标签

----
这个例子是学习Struts中OGNL、拦截器、标签相关的例子

[SSH与SSM学习之Struts207——OGNL基本语法](blog/07.md)

[SSH与SSM学习之Struts208——OGNL表达式与Struts框架结合之原理](blog/08.md)

[SSH与SSM学习之Struts209——OGNL表达式与Struts框架结合参数接收](blog/09.md)

[SSH与SSM学习之Struts210——Struts2基本流程(源码)](blog/10.md)

[SSH与SSM学习之Struts211——拦截器](blog/11.md)

[SSH与SSM学习之Struts212——标签](blog/12.md)

**其他相关**

[SSH与SSM学习之Struts201——介绍与环境搭建和第一个例子](blog/01.md)

[SSH与SSM学习之Struts202——Struts2访问流程和架构图](blog/02.md)

[SSH与SSM学习之Struts203——主配置文件配置_常量配置_动态方法配置_action书写方式](blog/03.md)

[SSH与SSM学习之Struts204——结果跳转方式](blog/04.md)

[SSH与SSM学习之Struts205——访问servletAPI方式](blog/05.md)

[SSH与SSM学习之Struts206——参数获取](blog/06.md)


---------

或者查看blog

[SSH与SSM学习](http://blog.csdn.net/qiwenmingshiwo/article/category/7175517)


----

相关的项目


**Struts2 学习之配置**
[https://github.com/wimingxxx/Struts2_1_setting](https://github.com/wimingxxx/Struts2_1_setting)

**Struts2 学习之结果与参数获取**
[https://github.com/wimingxxx/Struts2_2_demo](https://github.com/wimingxxx/Struts2_2_demo)

**Struts2 学习之OGNL、拦截器、标签**
[https://github.com/wimingxxx/Struts2_3_demo](https://github.com/wimingxxx/Struts2_3_demo)

----

[TOC]

# SSH与SSM学习之Struts208——OGNL基本语法

## 一、OGNL概述

### 1.1 什么是OGNL


OGNL的全称是对象图导航语言（Object-Graph Navigation Language>，它是一种功能强大的开
源表达式语言，使用这种表达式语言，可以通过某种表达式语法，存取Java对象的任意属性，调用
Java对象的方法，同时能够自动实现必要的类型转换。如果把表达式看作是一个带有语义的字符串,
那么OGNL无疑成为了这个语义字符串与Java对象之间沟通的桥梁。

### 1.2 0GNL 的作用

Struts2默认的表达式语言就足OGNL，它具有以下特点：

- 支持对象方法调用。例如：objName.melhodNameO。

- 支持类静态方法调用和值访问，表达式的格式为@[类全名(包括包路径)]@[方法名|值名]。
例如：@java.lang.String@format('foo %s','bar')。

- 支持赋值操作和表达式串联。
例如：price=100, discount=0.8，calculatePrice()，在方法中进行乘法计算会返回80。

- 访问 OGNL 上下文（OGNL context)和 ActionContext。

- 操作集合对象。
 

### 1.3 0GNL 的要素

了解了什么是OGNL及其特点后，接下来，分析一下OGNL的结构。OGNL的操作实际上就是
围绕符OGNL结构的三个要素而进行的，分别足表达式（Expression)、根对象（RootObject)、上
下文环境（Context)，下面分别讲解这三个要素，具体如下。

#### 1.3.1 表达式

表达式是整个OGNL的核心，OGNL会根据表达式去对象中取值。所有OGNL操作都是针对表
达式解祈后进行的。它表明了此次OGNL操作要“做什么”。表达式就是一个带有语法含义的字符
串，这个字符串规定了操作的类型和操作的内容。OGNL支持大量的表达式语法，不仅支持这种“链
式”对象访问路径，还支持在表达式中进行简单的计算。

#### 1.3.2 根对象（Root)

Root对象可以理解为OGNL的操作对象，表达式规定了“做什么”，而Root对象则规定了“对
谁操作”。OGNL称为对象图导航语言，所谓对象图，即以任意一个对象为根，通过OGNL可以访
问与这个对象关联的其它对象。


### 1.3.3 Context对象
实际上OGNL的取值还需要一个上下文环境。设置了Root对象，OGNL可以对Root对象进行
取值或写值等操作，Root对象所在环境就是OGNL的上下文环境（Context)。上下文环境规定了
OGNL的操作“在哪里进行”。上下文环境Context是一个Map类型的对象，在表达式中访问Context
中的对象，需要使用“#”号加上对象名称，即“ #对象名称”的形式。


---

## 二、取出root中的属性值

我们使用 OGNL 得准备 root 和context。


### 2.1 代码示例

```java
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
```

### 2.2 结果

```
qiwenming
20
```

----

## 三、取出context中的属性值


### 3.1 代码示例

```java
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
```

### 3.2 结果

```
wiming
xiaoming
```

----

## 四、修改context中的属性值


### 4.1 代码示例

```java
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
```

### 4.2 结果

```
文明
明明白白
```

----


## 五、调用方法


### 5.1 代码示例

```java
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
```

### 5.2 结果

```
geggegege
user1--test
```

----


## 六、调用静态方法


### 6.1 代码示例

```java
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

```

### 6.2 结果

```
sqrtTest1:10.0
sqrtTest2:10.0
PI1:3.141592653589793
PI2:3.141592653589793
int1:22
age:10
```

----


## 七、ognl创建对象-list|map


### 7.1 代码示例

```java
/**
 * 基本语法
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
```

### 7.2 结果

```
[mingge, mingming, wiming, wenming]
4
mingge
--------
{name=wiming, gender=男}
wiming
男
```

