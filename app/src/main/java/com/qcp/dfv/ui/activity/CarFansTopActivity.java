package com.qcp.dfv.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.qcp.dfv.R;
import com.qcp.dfv.adapter.CarFansTopAdapter;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.bean.CarFansTopList;
import com.qcp.dfv.ui.widget.RecycleViewDivider;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/1/9.
 * 车友排行榜
 */

public class CarFansTopActivity extends BaseActivity {

    @Bind(R.id.left)
    LinearLayout left;
    @Bind(R.id.ry_view)
    RecyclerView ryView;
    @Bind(R.id.swipely)
    SwipeRefreshLayout swipely;


    ArrayList<CarFansTopList> topLists;
    CarFansTopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carfanstop);
        ButterKnife.bind(this);
        super.initAppBar();
        setTitle("排行榜");
        showLeftView();


        initListData();

    }

    private void initListData() {
        topLists = new ArrayList<>();
        adapter = new CarFansTopAdapter(this, topLists);
        //设置布局管理器，默认纵向滑动
        ryView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        ryView.setAdapter(adapter);
        setHeader(ryView);
        //上拉刷新
//        refreshLayout.setOnRefreshListener(refreshListener);
//        //下拉加载
//        refreshLayout.setOnLoadmoreListener(loadmoreListener);
//        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
//        adapter.setOnItemClickLitener(litener);
        //添加分割线
        ryView.addItemDecoration(new RecycleViewDivider(this,1));

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

    //添加的head布局
    private void setHeader(RecyclerView ryView) {
        View header = LayoutInflater.from(this).inflate(R.layout.fanstop_header, ryView, false);
        adapter.setHeaderView(header);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
