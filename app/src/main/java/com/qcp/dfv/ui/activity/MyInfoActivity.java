package com.qcp.dfv.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.ui.fragment.CollectionFragmennt;
import com.qcp.dfv.ui.fragment.DynamicFragmennt;
import com.qcp.dfv.ui.fragment.FansFragmennt;
import com.qcp.dfv.ui.fragment.FollowFragmennt;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by panjunquan on 2018/5/29.
 * 我的详细信息
 */

public class MyInfoActivity extends BaseActivity {

    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.signature_tv)
    TextView signatureTv;
    @Bind(R.id.gender_tv)
    TextView genderTv;
    @Bind(R.id.address_tv)
    TextView addressTv;
    @Bind(R.id.fans_tv)
    TextView fansTv;
    @Bind(R.id.line_a)
    View lineA;
    @Bind(R.id.fans_ly)
    LinearLayout fansLy;
    @Bind(R.id.following_tv)
    TextView followingTv;
    @Bind(R.id.line_b)
    View lineB;
    @Bind(R.id.following_ly)
    LinearLayout followingLy;
    @Bind(R.id.dynamic_tv)
    TextView dynamicTv;
    @Bind(R.id.line_c)
    View lineC;
    @Bind(R.id.dynamic_ly)
    LinearLayout dynamicLy;
    @Bind(R.id.collection_tv)
    TextView collectionTv;
    @Bind(R.id.line_d)
    View lineD;
    @Bind(R.id.collection_ly)
    LinearLayout collectionLy;
    @Bind(R.id.my_content_fly)
    FrameLayout myContentFly;

    private FansFragmennt fansFragmennt;
    private FollowFragmennt followFragmennt;
    private DynamicFragmennt dynamicFragmennt;
    private CollectionFragmennt collectionFragmennt;
    private FragmentManager fragmentManager;
    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        super.initAppBar();
        showLeftView();

        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {

            fansFragmennt = (FansFragmennt) fragmentManager.findFragmentByTag("fansFragmennt");
            followFragmennt = (FollowFragmennt) fragmentManager.findFragmentByTag("followFragmennt");
            dynamicFragmennt = (DynamicFragmennt) fragmentManager.findFragmentByTag("dynamicFragmennt");
            collectionFragmennt = (CollectionFragmennt) fragmentManager.findFragmentByTag("collectionFragmennt");

        }
        setTabSelection(current);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.edit_ibt, R.id.fans_ly, R.id.following_ly, R.id.dynamic_ly, R.id.collection_ly})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.edit_ibt:
                Intent edit_intent = new Intent(MyInfoActivity.this, UserInfoActivity.class);
                startActivity(edit_intent);
                break;
            case R.id.fans_ly:
                current = 0;
                setTabSelection(current);
                break;
            case R.id.following_ly:
                current = 1;
                setTabSelection(current);
                break;
            case R.id.dynamic_ly:
                current = 2;
                setTabSelection(current);
                break;
            case R.id.collection_ly:
                current = 3;
                setTabSelection(current);
                break;
        }
    }

    private void setTabSelection(int index) {
        clearState();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);//设置fragment切换动画效果

        hideFragments(transaction);
        switch (index) {
            case 0:
                fansTv.setTextColor(ContextCompat.getColor(this, R.color.black));
                lineA.setVisibility(View.VISIBLE);
                if (fansFragmennt == null) {
                    fansFragmennt = new FansFragmennt();
                    transaction.add(R.id.my_content_fly, fansFragmennt, "fansFragmennt");
                } else {
                    transaction.show(fansFragmennt);
                }
                break;
            case 1:
                followingTv.setTextColor(ContextCompat.getColor(this, R.color.black));
                lineB.setVisibility(View.VISIBLE);
                if (followFragmennt == null) {
                    followFragmennt = new FollowFragmennt();
                    transaction.add(R.id.my_content_fly, followFragmennt, "followFragmennt");
                } else {
                    transaction.show(followFragmennt);
                }
                break;

            case 2:
                dynamicTv.setTextColor(ContextCompat.getColor(this, R.color.black));
                lineC.setVisibility(View.VISIBLE);
                if (dynamicFragmennt == null) {
                    dynamicFragmennt = new DynamicFragmennt();
                    transaction.add(R.id.my_content_fly, dynamicFragmennt, "dynamicFragmennt");
                } else {
                    transaction.show(dynamicFragmennt);
                }
                break;
            case 3:
                collectionTv.setTextColor(ContextCompat.getColor(this, R.color.black));
                lineD.setVisibility(View.VISIBLE);
                if (collectionFragmennt == null) {
                    collectionFragmennt = new CollectionFragmennt();
                    transaction.add(R.id.my_content_fly, collectionFragmennt, "collectionFragmennt");
                } else {
                    transaction.show(collectionFragmennt);
                }
                break;
        }
        transaction.commit();
    }

    private void clearState() {
        fansTv.setTextColor(Color.parseColor("#82858b"));
        followingTv.setTextColor(Color.parseColor("#82858b"));
        dynamicTv.setTextColor(Color.parseColor("#82858b"));
        collectionTv.setTextColor(Color.parseColor("#82858b"));
        lineA.setVisibility(View.INVISIBLE);
        lineB.setVisibility(View.INVISIBLE);
        lineC.setVisibility(View.INVISIBLE);
        lineD.setVisibility(View.INVISIBLE);
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {

        if (fansFragmennt != null) {
            transaction.hide(fansFragmennt);
        }
        if (followFragmennt != null) {
            transaction.hide(followFragmennt);
        }
        if (dynamicFragmennt != null) {
            transaction.hide(dynamicFragmennt);
        }
        if (collectionFragmennt != null) {
            transaction.hide(collectionFragmennt);
        }

    }

}
