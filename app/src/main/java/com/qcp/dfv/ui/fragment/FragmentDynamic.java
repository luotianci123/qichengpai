package com.qcp.dfv.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qcp.dfv.R;
import com.qcp.dfv.adapter.DynamicAdapter;
import com.qcp.dfv.adapter.RecommendFansAdapter;
import com.qcp.dfv.adapter.RecommendFansList;
import com.qcp.dfv.base.BaseFragment;
import com.qcp.dfv.bean.DynamicList;
import com.qcp.dfv.ui.activity.ContentInfoActivity;
import com.socks.library.KLog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


@SuppressLint("ResourceAsColor")
public class FragmentDynamic extends BaseFragment {

    View view;
    @Bind(R.id.ry_view)
    RecyclerView ryView;
    @Bind(R.id.swipely)
    SwipeRefreshLayout swipely;

    ArrayList<DynamicList> dynamicLists;
    DynamicAdapter adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        ButterKnife.bind(this, view);


        initListData();
        return view;
    }

    private void initListData() {
        dynamicLists = new ArrayList<>();
        adapter = new DynamicAdapter(getActivity(), dynamicLists, handler);
        //设置布局管理器，默认纵向滑动
        ryView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setHeader(ryView);
        //设置adapter
        ryView.setAdapter(adapter);
        //上拉刷新
//        refreshLayout.setOnRefreshListener(refreshListener);
//        //下拉加载
//        refreshLayout.setOnLoadmoreListener(loadmoreListener);
//        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
        adapter.setOnItemClickLitener(litener);

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

    DynamicAdapter.OnItemClickLitener litener = new DynamicAdapter.OnItemClickLitener() {
        @Override
        public void onItemClick(View view, int position) {

        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    };

    //添加的head布局
    private void setHeader(RecyclerView ryView) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.dynamic_header, ryView, false);
        RecyclerView rlView = (RecyclerView) header.findViewById(R.id.recommend_view);
        ArrayList<RecommendFansList> recommendFansLists = new ArrayList<>();
        RecommendFansAdapter recommendFansAdapter = new RecommendFansAdapter(getActivity(), recommendFansLists);
        //设置横向滑动的布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rlView.setLayoutManager(linearLayoutManager);
        rlView.setAdapter(recommendFansAdapter);
        adapter.setHeaderView(header);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    int pos = msg.arg1;
                    KLog.e("动态：" + pos);
                    Intent intent = new Intent(getActivity(), ContentInfoActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };

}





