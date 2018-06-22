package com.qcp.dfv.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qcp.dfv.R;
import com.qcp.dfv.adapter.MsgAdapter;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.bean.MsgList;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/5/29.
 * 消息
 */

public class MsgActivity extends BaseActivity {


    @Bind(R.id.ry_view)
    RecyclerView ryView;
    @Bind(R.id.swipely)
    SwipeRefreshLayout swipely;

    ArrayList<MsgList> msgLists;
    MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg);
        ButterKnife.bind(this);
        super.initAppBar();
        setTitle("消息");
        showLeftView();


        initListData();
    }

    private void initListData() {
        msgLists = new ArrayList<>();
        adapter = new MsgAdapter(this, msgLists);
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
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
