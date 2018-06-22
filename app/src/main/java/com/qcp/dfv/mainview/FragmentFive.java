package com.qcp.dfv.mainview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseFragment;
import com.qcp.dfv.pub.L;
import com.qcp.dfv.ui.activity.AboutActivity;
import com.qcp.dfv.ui.activity.HelpActivity;
import com.qcp.dfv.ui.activity.MsgActivity;
import com.qcp.dfv.ui.activity.MyDynamicActivity;
import com.qcp.dfv.ui.activity.MyFansActivity;
import com.qcp.dfv.ui.activity.MyFollowingActivity;
import com.qcp.dfv.ui.activity.MyInfoActivity;
import com.qcp.dfv.ui.activity.SettingActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


@SuppressLint("ResourceAsColor")
public class FragmentFive extends BaseFragment {

    View fourViewt;
    @Bind(R.id.title)
    TextView title;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fourViewt = inflater.inflate(R.layout.fragment_five, container, false);
        ButterKnife.bind(this, fourViewt);
        title.setText("我的");

        return fourViewt;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        L.e("onHiddenChanged-five");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.user_ly, R.id.dynamic_ly, R.id.fans_ly, R.id.following_ly, R.id.msg_ly, R.id.setting_ly, R.id.help_ly, R.id.about_ly})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_ly:
                Intent user_intent = new Intent(getActivity(), MyInfoActivity.class);
                startActivity(user_intent);
                break;
            case R.id.dynamic_ly:
                Intent dynamic_intent = new Intent(getActivity(), MyDynamicActivity.class);
                startActivity(dynamic_intent);
                break;
            case R.id.fans_ly:
                Intent fans_intent = new Intent(getActivity(), MyFansActivity.class);
                startActivity(fans_intent);
                break;
            case R.id.following_ly:
                Intent follwoing_intent = new Intent(getActivity(), MyFollowingActivity.class);
                startActivity(follwoing_intent);
                break;
            case R.id.msg_ly:
                Intent msg_intent = new Intent(getActivity(), MsgActivity.class);
                startActivity(msg_intent);
                break;
            case R.id.setting_ly:
                Intent setting_intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(setting_intent);
                break;
            case R.id.help_ly:
                Intent help_intent = new Intent(getActivity(), HelpActivity.class);
                startActivity(help_intent);
                break;
            case R.id.about_ly:
                Intent about_intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(about_intent);
                break;
        }
    }
}





