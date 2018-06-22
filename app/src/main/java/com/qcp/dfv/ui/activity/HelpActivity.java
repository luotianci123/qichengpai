package com.qcp.dfv.ui.activity;

import android.os.Bundle;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/5/28.
 * 问题帮助
 */

public class HelpActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        super.initAppBar();
        setTitle("问题帮助");
        showLeftView();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
