package com.qcp.dfv.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qcp.dfv.R;
import com.qcp.dfv.adapter.FansAdapter;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.bean.FansList;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/6/1.
 * 我的粉丝
 */

public class MyFansActivity extends BaseActivity {


    @Bind(R.id.ry_view)
    RecyclerView ryView;
    @Bind(R.id.swipely)
    SwipeRefreshLayout swipely;

    ArrayList<FansList> fansLists;
    FansAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfans);
        ButterKnife.bind(this);
        super.initAppBar();
        setTitle("我的粉丝");
        showLeftView();

        initListData();
    }

    private void initListData() {
        fansLists = new ArrayList<>();
        adapter = new FansAdapter(this, fansLists);
        //设置布局管理器，默认纵向滑动
        ryView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        ryView.setAdapter(adapter);
        //上拉刷新
//        refreshLayout.setOnRefreshListener(refreshListener);
//        //下拉加载
//        refreshLayout.setOnLoadmoreListener(loadmoreListener);
//        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
//        adapter.setOnItemClickLitener(litener);

        //下拉刷新控件的用法
        swipely.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark));//设置刷新控件颜色
        swipely.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //执行刷新加载数据操作
                swipely.setRefreshing(false);//停止刷新
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
