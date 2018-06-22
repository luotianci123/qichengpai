package com.qcp.dfv.ui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.mainview.MainActivity;
import com.qcp.dfv.net.JsonCallback;
import com.qcp.dfv.net.UrlConstant;
import com.qcp.dfv.pub.L;
import com.qcp.dfv.ui.widget.LoginButton;
import com.qcp.dfv.utils.AppUtils;
import com.qcp.dfv.utils.SharedPreferencesUtils;
import com.qcp.dfv.utils.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.qcp.dfv.utils.AppUtils.showToast;

/**
 * Created by panjunquan on 2018/1/4.
 * 登录界面
 */

public class LoginActivity extends BaseActivity {

    @Bind(R.id.phone)
    EditText phoneEt;
    @Bind(R.id.password)
    EditText passwordEt;
    @Bind(R.id.box_pwd)
    CheckBox boxPwd;
    @Bind(R.id.login_bt)
    Button loginBt;

    LoginButton loginButton;
    String phone;
    String password;
    @Bind(R.id.forgot_pwd_tv)
    TextView forgotPwdTv;
    @Bind(R.id.register_tv)
    TextView registerTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        super.initAppBar();

        forgotPwdTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        registerTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        loginButton = (LoginButton) findViewById(R.id.login);
//        addListenerButton(loginButton, true);

        phoneEt.setText(SharedPreferencesUtils.getPhone(this));
        phoneEt.setSelection(phoneEt.getText().toString().length());
        passwordEt.setSelection(passwordEt.getText().toString().length());
        if (SharedPreferencesUtils.getIsSave(this)) {
            boxPwd.setChecked(true);
            passwordEt.setText(SharedPreferencesUtils.getPwd(this));
        } else {
            passwordEt.setText("");
        }
    }

    private void addListenerButton(final LoginButton atLoginButton, final boolean loaginStatus) {
        atLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                atLoginButton.buttonLoginAction();
                atLoginButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        atLoginButton.buttonLoaginResultAciton(loaginStatus);
                        String notice = loaginStatus ? "登陆成功,重置button状态" : "登录失败,显示失败状态";

                        login();
//                        if (loaginStatus) {
//                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(getApplicationContext(), notice, Toast.LENGTH_SHORT).show();
//                        }
                    }
                }, 2000);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.box_pwd, R.id.forgot_pwd_tv, R.id.register_tv, R.id.login_bt, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.box_pwd:
                SharedPreferencesUtils.setSharedPreferencesy(LoginActivity.this, "is_save", boxPwd.isChecked());
                break;
            case R.id.forgot_pwd_tv:
                Intent pwd_intent = new Intent(LoginActivity.this, RetrievePwdActivity.class);
                startActivity(pwd_intent);
                break;
            case R.id.register_tv:
                Intent reg_intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(reg_intent);
                break;
            case R.id.login_bt:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                finish();
                login();
                break;

            case R.id.login:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
//                finish();
                login();
                break;
        }
    }

    public void login() {
        phone = phoneEt.getText().toString();
        password = passwordEt.getText().toString();
        if (Utils.isEmpty(phone)) {
            showToast("请输入账号");
        } else if (Utils.isEmpty(password)) {
            showToast("请输入密码");
        } else {
            dataRequest();
        }
    }

    /**
     * 登录请求
     */
    public void dataRequest() {
        showWait(true);
        OkGo.post(UrlConstant.POST_USER_LOGIN)
                .params("username", phone)//17756927125
                .params("password", password)//316003
                .execute(new JsonCallback<JsonObject>() {//ProductPageVoBean
                    @Override
                    public void onSuccess(JsonObject voBean, Call call, Response response) {
                        showWait(false);
                        L.e("AAAD1", "response:" + response + "---" + voBean.get("staCode").getAsInt());
                        if (voBean != null) {

                            int staCode = voBean.get("staCode").getAsInt();
                            String message = voBean.get("retDesc").getAsString();
                            if (staCode == 1000) {
//                                addListenerButton(loginButton, true);

                                SharedPreferencesUtils.setSharedPreferencesy(LoginActivity.this, "phone", phone);
                                SharedPreferencesUtils.setSharedPreferencesy(LoginActivity.this, "pwd", password);
                                SharedPreferencesUtils.setSharedPreferencesy(LoginActivity.this, "is_login", true);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
//                                addListenerButton(loginButton, false);
                                AppUtils.showShortToast(message);
                            }

                        }
                    }

                    @Override
                    public void onFailure(String code, String message) {
                        showWait(false);
                        if (!TextUtils.isEmpty(message)) {
                            showToast(message);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        Log.e("LLL9", "onError:" + response + "---" + e);
                    }
                });
    }

}
