package com.qcp.dfv.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.mainview.MainActivity;
import com.qcp.dfv.net.JsonCallback;
import com.qcp.dfv.net.UrlConstant;
import com.qcp.dfv.pub.L;
import com.qcp.dfv.utils.AppUtils;
import com.qcp.dfv.utils.SharedPreferencesUtils;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

import static com.qcp.dfv.utils.AppUtils.showToast;

/**
 * Created by panjunquan on 2018/5/31.
 * 找回密码
 */

public class RetrievePwdActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retrieve_pwd);
        ButterKnife.bind(this);
        super.initAppBar();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 登录请求
     */
    public void dataRequest() {
        showWait(true);
        OkGo.post(UrlConstant.POST_USER_LOGIN)
//                .params("username", phone)//17756927125
//                .params("password", password)//316003
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

//                                SharedPreferencesUtils.setSharedPreferencesy(RegisterActivity.this, "phone", phone);
//                                SharedPreferencesUtils.setSharedPreferencesy(RegisterActivity.this, "pwd", password)log;
                                SharedPreferencesUtils.setSharedPreferencesy(RetrievePwdActivity.this, "is_login", true);
                                Intent intent = new Intent(RetrievePwdActivity.this, MainActivity.class);
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
