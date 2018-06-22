package com.qcp.dfv.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.qcp.dfv.R;
import com.qcp.dfv.bean.ListCustomerBanner;
import com.qcp.dfv.ui.activity.PictureShareActivity;

import java.util.List;

/**
 * Created by pan on 2016/7/25 0025.
 * >#
 * >#
 */
public class PicAdapter extends BaseAdapter {
    List<ListCustomerBanner> list;
    private Context context;
    Handler handler;
    int status;

    public PicAdapter(Context context, Handler handler, int status) {
        list = PictureShareActivity.pics;
        this.context = context;
        this.handler = handler;
        this.status = status;
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
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.ricpic_item, null);
        ImageView pic = (ImageView) convertView.findViewById(R.id.pic);
        ImageView remove = (ImageView) convertView.findViewById(R.id.remove);

        if (status == 2 || status == 1) {
            remove.setVisibility(View.GONE);
        } else {
            remove.setVisibility(View.VISIBLE);
        }

        if (list.get(i).isadd() && (list.size() < 4)) {
            remove.setVisibility(View.GONE);
            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message msg = new Message();
                    msg.arg1 = i;
                    msg.what = 205;
                    handler.sendMessage(msg);
                }
            });
            remove.setVisibility(View.GONE);
        } else if (list.get(i).ismodify()) {
            remove.setVisibility(View.VISIBLE);
            pic.setImageBitmap(list.get(i).getBitmap());
        } else if (i == 3) { //够6张不显示加号
            remove.setVisibility(View.GONE);
            pic.setVisibility(View.GONE);
        }

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = new Message();
                msg.arg1 = i;
                msg.what = 206;
                handler.sendMessage(msg);
            }
        });

        return convertView;
    }
}
