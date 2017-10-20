package com.qwm.struts2_3.bean;

public class TestBean {
    private String test;
    private String xxx;

    public TestBean() {
    }

    public TestBean(String test, String xxx) {
        this.test = test;
        this.xxx = xxx;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }
}
