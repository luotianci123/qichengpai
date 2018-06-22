package com.qcp.dfv.mainview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseFragment;
import com.qcp.dfv.pub.ClientNIOSocketImpl;
import com.qcp.dfv.ui.fragment.PictureFragmennt;
import com.qcp.dfv.ui.fragment.VideoFragmennt;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


@SuppressLint("ResourceAsColor")
public class FragmentFour extends BaseFragment {

    View fourViewt;
    @Bind(R.id.one_tv)
    TextView oneTv;
    @Bind(R.id.line_a)
    View lineA;
    @Bind(R.id.one_ly)
    RelativeLayout oneLy;
    @Bind(R.id.two_tv)
    TextView twoTv;
    @Bind(R.id.line_b)
    View lineB;
    @Bind(R.id.two_ly)
    RelativeLayout twoLy;
    @Bind(R.id.head_ly)
    LinearLayout headLy;

    private PictureFragmennt pictureFragmennt;
    private VideoFragmennt videoFragmennt;
    private FragmentManager fragmentManager;
    private int current = 0;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fourViewt = inflater.inflate(R.layout.fragment_four, container, false);
        ButterKnife.bind(this, fourViewt);


        fragmentManager = this.getChildFragmentManager();
        if (savedInstanceState != null) {
            pictureFragmennt = (PictureFragmennt) fragmentManager.findFragmentByTag("pictureFragmennt");
            videoFragmennt = (VideoFragmennt) fragmentManager.findFragmentByTag("videoFragmennt");
        }
        setTabSelection(current);
//        //手机开始通过socket请求服务器获取图片
        new ClientNIOSocketImpl(getContext(),"preview").start();

        return fourViewt;
    }


    private void setTabSelection(int index) {
        clearState();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                lineA.setVisibility(View.VISIBLE);
                oneTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                if (pictureFragmennt == null) {
                    pictureFragmennt = new PictureFragmennt();
                    transaction.add(R.id.photo_content, pictureFragmennt, "pictureFragmennt");
                } else {
                    transaction.show(pictureFragmennt);
                }
                break;
            case 1:
                lineB.setVisibility(View.VISIBLE);
                twoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
                if (videoFragmennt == null) {
                    videoFragmennt = new VideoFragmennt();
                    transaction.add(R.id.photo_content, videoFragmennt, "videoFragmennt");
                } else {
                    transaction.show(videoFragmennt);
                }
                break;
        }
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void clearState() {
        oneTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        twoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        lineA.setVisibility(View.INVISIBLE);
        lineB.setVisibility(View.INVISIBLE);
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (pictureFragmennt != null) {
            transaction.hide(pictureFragmennt);
        }
        if (videoFragmennt != null) {
            transaction.hide(videoFragmennt);
        }
    }

    @OnClick({R.id.one_ly, R.id.two_ly})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one_ly:
                current = 0;
                setTabSelection(current);
                break;
            case R.id.two_ly:
                current = 1;
                setTabSelection(current);
                break;
        }
    }
}





