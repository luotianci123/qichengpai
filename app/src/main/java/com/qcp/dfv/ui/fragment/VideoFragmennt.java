package com.qcp.dfv.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qcp.dfv.R;
import com.qcp.dfv.adapter.VideoAdapter;
import com.qcp.dfv.base.BaseFragment;
import com.qcp.dfv.bean.VideoList;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


@SuppressLint("ResourceAsColor")
public class VideoFragmennt extends BaseFragment {

    View view;
    @Bind(R.id.ry_view)
    RecyclerView ryView;
    @Bind(R.id.swipely)
    SwipeRefreshLayout swipely;

    ArrayList<VideoList> lists;
    VideoAdapter adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this, view);

//        new ClientNIOSocketImpl(getContext(),"watch").start();

        initListData();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initListData() {
        lists = new ArrayList<>();
        adapter = new VideoAdapter(getActivity(), lists);
        //设置布局管理器
        ryView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
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

}





