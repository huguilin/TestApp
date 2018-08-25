package com.example.navinfo.testapp.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.httpmodule.HttpUtils;
import com.example.navinfo.testapp.R;
import com.example.navinfo.testapp.bean.InstroduceRespose;
import com.example.navinfo.testapp.bean.LoginBean;
import com.example.navinfo.testapp.bean.LoginRespose;
import com.example.navinfo.testapp.presenter.LoginPresenter;
import com.example.testmoudle.HttpClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 手机号
     */
    private EditText mName;
    /**
     * 密码
     */
    private EditText mPassword;
    /**
     * 登录
     */
    private Button mLogin;
    private TextView mTv;

    private LoginPresenter presenter;
    private EditText mText;

    private HttpClient httpClient;
    private HttpUtils httpUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mName = (EditText) findViewById(R.id.name);
        mPassword = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(this);
        mTv = (TextView) findViewById(R.id.tv);
        presenter = new LoginPresenter(this);
        httpClient = new HttpClient();
        httpClient.http();
        mText = (EditText) findViewById(R.id.text);
        mTv.setTextSize(SizeUtils.dp2px(30));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
            Log.e("hgl","------111111");
        } else {
//            DeviceUtil.getIMEI(this);
            String str = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
            Log.e("hgl",str+"-----22222");
        }

        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = mName.getText().toString();
//                if (str.matches("[a-z]+[A-Z]+[0-9]+\\p{Punct}+")){
//                    Toast.makeText(MainActivity.this,"四种类型",Toast.LENGTH_SHORT).show();
//                }
//
//                if (str.matches("^[0-9]+$")){
//                    Toast.makeText(MainActivity.this,"全是数字",Toast.LENGTH_SHORT).show();
//                }
                int i =0;
                if (str.matches(".*[0-9].*")){
                    i++;
                }

                if (str.matches(".*[a-z].*")){
                    i++;
                }
                if (str.matches(".*[A-Z].*")){
                    i++;
                }
                if (str.matches(".*\\p{Punct}.*")){
                    i++;
                }

                if (i == 0){
                    Toast.makeText(MainActivity.this,"请输入正确字符",Toast.LENGTH_SHORT).show();
                }else if (i == 1){
                    Toast.makeText(MainActivity.this,"一种数据类型",Toast.LENGTH_SHORT).show();
                }else if (i == 2){
                    Toast.makeText(MainActivity.this,"两种数据类型",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this,"三种以上数据类型",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void setText(LoginRespose loginRespose) {
        mTv.setText(loginRespose.getMsg());
    }

    public void setText(InstroduceRespose loginRespose) {
        mTv.setText(loginRespose.getMsg());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login:
                LoginBean loginBean = new LoginBean();
                loginBean.setName(mName.getText().toString());
                loginBean.setPassword(mPassword.getText().toString());
                presenter.login(loginBean);
                presenter.instroduce(mText.getText().toString());
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {
//            DeviceUtil.getIMEI(this);
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                String str = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                Log.e("hgl",str+"---333333");

            }else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                    Log.e("hgl","提示用户");
                    //解释原因，并且引导用户至设置页手动授权
                    new AlertDialog.Builder(this)
                            .setMessage("【用户选择了不在提示按钮，或者系统默认不在提示（如MIUI）。" +
                                    "引导用户到应用设置页去手动授权,注意提示用户具体需要哪些权限】\r\n" +
                                    "获取相关权限失败:xxxxxx,将导致部分功能无法正常使用，需要到设置页面手动授权")
                            .setPositiveButton("去授权", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //引导用户至设置页手动授权
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package", getApplicationContext().getPackageName(), null);
                                    intent.setData(uri);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //引导用户手动授权，权限请求失败
                                }
                            }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            //引导用户手动授权，权限请求失败
                        }
                    }).show();

                } else {
                    //权限请求失败，但未选中“不再提示”选项
                    Log.e("hgl","请求失败");
                }
                Log.e("hgl","----444444444");
            }
        }else {
            Log.e("hgl","---5555555");
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
