package com.qcp.dfv.mainview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseFragment;

import butterknife.ButterKnife;

@SuppressLint("ResourceAsColor")
public class FragmentThree extends BaseFragment {


    View threeViewt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        threeViewt = inflater.inflate(R.layout.fragment_three, container, false);
        ButterKnife.bind(this, threeViewt);

        return threeViewt;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}





