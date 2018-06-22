package com.qcp.dfv.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qcp.dfv.R;
import com.qcp.dfv.adapter.RecommendedAdapter;
import com.qcp.dfv.base.BaseFragment;
import com.qcp.dfv.bean.RecommendedList;
import com.qcp.dfv.pub.L;
import com.qcp.dfv.ui.activity.AdWebActivity;
import com.qcp.dfv.ui.activity.CarFansTopActivity;
import com.qcp.dfv.ui.activity.WeizhangCheckActivity;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


@SuppressLint("ResourceAsColor")
public class FragmentRecommended extends BaseFragment {

    View view;
    @Bind(R.id.ry_view)
    RecyclerView ryView;
    @Bind(R.id.swipely)
    SwipeRefreshLayout swipely;

    ArrayList<RecommendedList> recommendedLists;
    RecommendedAdapter adapter;
    MZBannerView mzBanner;
    ImageView banderImageView;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommended, container, false);
        ButterKnife.bind(this, view);

        initListData();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("http://36.111.199.9:8888/resources/dl/picture/375200839010019619.png");
        stringList.add("http://36.111.199.9:8888/resources/dl/picture/375200839010019619.png");
        stringList.add("http://36.111.199.9:8888/resources/dl/picture/375200839010019619.png");
        stringList.add("http://36.111.199.9:8888/resources/dl/picture/375200839010019619.png");

        setBanner(stringList);
        return view;
    }

    private void initListData() {
        recommendedLists = new ArrayList<>();
        adapter = new RecommendedAdapter(getActivity(), recommendedLists);
        //设置布局管理器，默认纵向滑动
        ryView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置adapter
        ryView.setAdapter(adapter);
        setHeader(ryView);
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

    //添加的head布局
    private void setHeader(RecyclerView ryView) {
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.recommended_header, ryView, false);
        mzBanner = (MZBannerView) header.findViewById(R.id.banner);
        RelativeLayout carRly = (RelativeLayout) header.findViewById(R.id.car_rly);
        RelativeLayout topRly = (RelativeLayout) header.findViewById(R.id.top_rly);
        carRly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WeizhangCheckActivity.class);
                startActivity(intent);
            }
        });

        topRly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CarFansTopActivity.class);
                startActivity(intent);
            }
        });
        adapter.setHeaderView(header);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void setBanner(final ArrayList<String> adList) {
        mzBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int i) {

                Intent intent = new Intent(getActivity(), AdWebActivity.class);
                intent.putExtra("banner_url","http://www.iov-dfv.net");
                startActivity(intent);
            }
        });
        // 设置数据
        mzBanner.setPages(adList, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        mzBanner.start();
    }

    public class BannerViewHolder implements MZViewHolder<String> {
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            //            roundImageView = (RoundImageView) view.findViewById(R.id.banner_ruondimage);
            banderImageView = (ImageView) view.findViewById(R.id.banner_image);
            //            roundImageView.setBorderRadius(5);
            //            roundImageView.setType(RoundImageView.TYPE_ROUND);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String imageBanner) {
            Glide.with(context)
                    .load(imageBanner)
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(banderImageView);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        L.e("onHiddenChanged-recommend" + hidden);
        if (hidden) {
            mzBanner.pause();//暂停轮播
        } else {
            mzBanner.start();//开始轮播
        }
    }
}





