package com.qcp.dfv.mainview;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseFragment;
import com.qcp.dfv.ui.fragment.FragmentDynamic;
import com.qcp.dfv.ui.fragment.FragmentRecommended;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/4/24.
 */

public class FragmentOne extends BaseFragment {

    View oneview;
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
    @Bind(R.id.content_fly)
    FrameLayout contentFly;

    private FragmentRecommended recommendedFgm;
    private FragmentDynamic dynamicFgm;
    private FragmentManager fragmentManager;
    private int current = 0;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        oneview = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, oneview);

        fragmentManager = this.getChildFragmentManager();
        if (savedInstanceState != null) {
            recommendedFgm = (FragmentRecommended) fragmentManager.findFragmentByTag("recommendedFgm");
            dynamicFgm = (FragmentDynamic) fragmentManager.findFragmentByTag("dynamicFgm");
        }
        setTabSelection(current);
        return oneview;
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

    private void setTabSelection(int index) {
        clearState();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 0:
                lineA.setVisibility(View.VISIBLE);
                oneTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                if (recommendedFgm == null) {
                    recommendedFgm = new FragmentRecommended();
                    transaction.add(R.id.content_fly, recommendedFgm, "recommendedFgm");
                } else {
                    transaction.show(recommendedFgm);
                }
                break;
            case 1:
                lineB.setVisibility(View.VISIBLE);
                twoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                if (dynamicFgm == null) {
                    dynamicFgm = new FragmentDynamic();
                    transaction.add(R.id.content_fly, dynamicFgm, "dynamicFgm");
                } else {
                    transaction.show(dynamicFgm);
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
        oneTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        twoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        lineA.setVisibility(View.INVISIBLE);
        lineB.setVisibility(View.INVISIBLE);
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (recommendedFgm != null) {
            transaction.hide(recommendedFgm);
        }
        if (dynamicFgm != null) {
            transaction.hide(dynamicFgm);
        }
    }


}
