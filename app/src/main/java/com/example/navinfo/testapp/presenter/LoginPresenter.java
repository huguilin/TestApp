package com.example.navinfo.testapp.presenter;

import android.widget.Toast;

import com.example.navinfo.testapp.bean.InstroduceRespose;
import com.example.navinfo.testapp.bean.LoginBean;
import com.example.navinfo.testapp.bean.LoginRespose;
import com.example.navinfo.testapp.listener.IntroduceListener;
import com.example.navinfo.testapp.listener.LoginListener;
import com.example.navinfo.testapp.model.LoginModel;
import com.example.navinfo.testapp.view.MainActivity;

public class LoginPresenter implements LoginListener,IntroduceListener{

    private LoginModel loginModel;
    private MainActivity mainActivity;

    public LoginPresenter(MainActivity mainActivity){
        this.mainActivity = mainActivity;
        loginModel = new LoginModel(this);
    }

    public void login(LoginBean loginBean){
        loginModel.startLogin(loginBean);
    }

    public void instroduce(String text){
        loginModel.startInstroduce(text);
    }

    @Override
    public void getIntroduce(InstroduceRespose introduceBean) {
        mainActivity.setText(introduceBean);
    }

    @Override
    public void getLogin(LoginRespose loginRespose) {
        if (loginRespose.getCode().equals("2000")){
            Toast.makeText(mainActivity,loginRespose.getMsg()+"----"+loginRespose.getUid(),Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mainActivity,loginRespose.getMsg()+"+++++"+loginRespose.getUid(),Toast.LENGTH_SHORT).show();
        }

//        mainActivity.setText(loginRespose);
    }
}
