package com.qcp.dfv.ui.activity;

import android.os.Bundle;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/6/1.
 * 个人信息
 */

public class UserInfoActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        super.initAppBar();
        setTitle("个人信息");
        showLeftView();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
