package com.example.navinfo.testapp.bean;

/**
 * <p>Title      : []_[]</p>
 * <p>Description: [登录bean类]</p>
 * <p>Copyright  : Copyright (c) 2018</p>
 * <p>Company    : 北京四维图新科技股份有限公司</p>
 * <p>Department : Telematics业务部</p>
 *
 * @author : huguilin
 * @version : 2.0
 */
public class LoginBean {

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
