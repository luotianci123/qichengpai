package com.qcp.dfv.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qcp.dfv.R;
import com.qcp.dfv.adapter.FansAdapter;
import com.qcp.dfv.base.BaseFragment;
import com.qcp.dfv.bean.FansList;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


@SuppressLint("ResourceAsColor")
public class FansFragmennt extends BaseFragment {

    View view;
    @Bind(R.id.ry_view)
    RecyclerView ryView;
    @Bind(R.id.swipely)
    SwipeRefreshLayout swipely;

    ArrayList<FansList> fansLists;
    FansAdapter adapter;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fans, container, false);
        ButterKnife.bind(this, view);

        initListData();
        return view;
    }

    private void initListData() {
        fansLists = new ArrayList<>();
        adapter = new FansAdapter(getActivity(), fansLists);
        //设置布局管理器，默认纵向滑动
        ryView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}





