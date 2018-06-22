package com.qcp.dfv.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qcp.dfv.R;

import java.util.List;


public class VersionAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;

    int currentposition;

    public void setCurrentPosition(int currentposition) {
        this.currentposition = currentposition;
    }

    public VersionAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.gridview_item, null);
        TextView selectTv = (TextView) convertView.findViewById(R.id.select_tv);
        selectTv.setText(list.get(position));

        if (position == currentposition) {
            //这里写点击之后item的变色效果处理逻辑代码
            selectTv.setBackground(ContextCompat.getDrawable(context, R.drawable.gridview_bg_blue));
            selectTv.setTextColor(ContextCompat.getColor(context,R.color.white));
        } else {
            selectTv.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_gray));
            selectTv.setTextColor(ContextCompat.getColor(context,R.color.drop_down_unselected));
        }
        return convertView;
    }

}
