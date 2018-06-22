package com.qcp.dfv.ui.activity;

import android.os.Bundle;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/6/6.
 * 违章举报
 */

public class ViolationReportActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violation_report);
        ButterKnife.bind(this);
        super.initAppBar();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
