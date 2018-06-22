package com.qcp.dfv.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qcp.dfv.DfvApplication;
import com.qcp.dfv.R;
import com.qcp.dfv.ui.widget.WaitDialog;


/**
 * Created by w.gs on 2016/10/24.
 */

public class BaseActivity extends AppCompatActivity {

    protected TextView tv_title;

    protected ImageButton menu_left;

    protected TextView menu_right;

    protected ImageButton menu_back;

    protected ImageButton menu_msg;

    protected LinearLayout left;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DfvApplication.getInstance().addActivity(this);
    }

    private long exitTime = 0;

    protected void initAppBar() {
        tv_title = (TextView) findViewById(R.id.title);
    }

    protected void showLeftView() {
        left = (LinearLayout) findViewById(R.id.left);
        left.setVisibility(View.VISIBLE);
        if (left != null) {
            left.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    protected void hideBackView() {
        if (menu_back != null) {
            menu_back.setVisibility(View.GONE);
        }
    }

    protected void hideLeftView() {
        if (menu_left != null) {
            menu_left.setVisibility(View.GONE);
        }
    }

    protected void hideMsgView() {
        if (menu_msg != null) {
            menu_msg.setVisibility(View.GONE);
        }
    }


    public void setTitle(String title) {
        if (tv_title != null) {
            tv_title.setText(title);
        }
    }

    protected WaitDialog dialog;

    protected void showWait(boolean isShow) {
        if (isShow) {
            if (null == dialog) {
                dialog = new WaitDialog(this);
                dialog.setCanceledOnTouchOutside(false);
            }
            dialog.show();
        } else {
            if (null != dialog) {
                dialog.dismiss();
                dialog.setCanceledOnTouchOutside(true);
            }
        }
    }

}
