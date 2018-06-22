package com.qcp.dfv.ui.activity;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/1/9.
 * 违章查询
 */

public class WeizhangCheckActivity extends BaseActivity {

    @Bind(R.id.left)
    LinearLayout left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weizhang_check);
        ButterKnife.bind(this);
        super.initAppBar();
        setTitle("填写车辆信息");
        showLeftView();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
