package com.qcp.dfv.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.qcp.dfv.R;
import com.qcp.dfv.adapter.HotAdapter;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.bean.HotList;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/1/9.
 * 热门
 */

public class HotActivity extends BaseActivity {

    @Bind(R.id.left)
    LinearLayout left;
    @Bind(R.id.ry_view)
    RecyclerView ryView;
    @Bind(R.id.swipely)
    SwipeRefreshLayout swipely;

    ArrayList<HotList> hotLists;
    HotAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot);
        ButterKnife.bind(this);
        super.initAppBar();
        setTitle("最热");
        showLeftView();

        initListData();
    }

    private void initListData() {
        hotLists = new ArrayList<>();
        adapter = new HotAdapter(this, hotLists);
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
