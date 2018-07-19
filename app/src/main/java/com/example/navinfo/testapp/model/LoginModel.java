package com.example.navinfo.testapp.model;

import com.example.navinfo.testapp.bean.InstroduceRespose;
import com.example.navinfo.testapp.bean.LoginBean;
import com.example.navinfo.testapp.bean.LoginRespose;
import com.example.navinfo.testapp.listener.IntroduceListener;
import com.example.navinfo.testapp.listener.LoginListener;
import com.example.navinfo.testapp.presenter.LoginPresenter;

public class LoginModel {
    private LoginListener loginListener;
    private LoginRespose loginRespose;
    private InstroduceRespose instroduceRespose;
    private IntroduceListener introduceListener;



    public LoginModel(LoginPresenter loginListener) {
        this.loginListener = (LoginListener) loginListener;
        this.introduceListener = (IntroduceListener) loginListener;
        loginRespose = new LoginRespose();
        instroduceRespose = new InstroduceRespose();
    }

    public void startInstroduce(String text) {

        if (text.equals("你好")) {
            instroduceRespose.setMsg("你好");
            introduceListener.getIntroduce(instroduceRespose);
        }else {
            instroduceRespose.setMsg("真好");
            introduceListener.getIntroduce(instroduceRespose);
        }
    }

    public void startLogin(LoginBean loginBean) {
        if (loginBean.getName().equals("1") && loginBean.getPassword().equals("1")) {
            loginRespose.setCode("2000");
            loginRespose.setMsg("成功");
            loginRespose.setUid("1");
            loginListener.getLogin(loginRespose);
        } else {
            loginRespose.setCode("2001");
            loginRespose.setMsg("密码错误");
            loginRespose.setUid("0");
            loginListener.getLogin(loginRespose);
        }
    }
}
