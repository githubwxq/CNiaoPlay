package com.chhd.cniaoplay.bean;

/**
 * Created by CWQ on 2017/5/29.
 */

public class LoginBean {


    /**
     * token : ca157c84-7ca2-4c0d-963f-16c25c143af2
     * user : {"email":"conghuahuadan@163.com","id":270609,"mobi":"15875006020","username":"葱花滑蛋"}
     */

    private String token;

    private User user;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
