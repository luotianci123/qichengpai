package com.qcp.dfv.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qcp.dfv.R;
import com.qcp.dfv.utils.AppUtils;

import java.util.ArrayList;

/**
 * Created by panjunquan on 2018/6/5.
 */

public class RecommendFansAdapter extends RecyclerView.Adapter<RecommendFansAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RecommendFansList> list;

    public RecommendFansAdapter(Context context, ArrayList<RecommendFansList> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recommendfans_item, null));
    }

    //点击接口
    public interface OnItemClickLitener {
        /**
         * 点击事件处理
         *
         * @author pan
         * @time 2016/10/31 0031 上午 11:18
         */
        void onItemClick(View view, int position);

        /**
         * 长按点击事件处理
         *
         * @author pan
         * @time 2016/10/31 0031 上午 11:18
         */
        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        /*
         *这里处理item数据的显示
         */

        int width = AppUtils.getSceenWidth(context);

        ViewGroup.LayoutParams params = holder.userIV.getLayoutParams();
        int gapPx = AppUtils.dip2px(context, 106);
        params.height = (width - gapPx) / 5;//设置当前控件布局的高度
        params.width = (width - gapPx) / 5;//设置当前控件布局的高度
        holder.userIV.setLayoutParams(params);//将设置好的布局参数应用到控件中

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView userIV;
        TextView nameTv;

        public ViewHolder(View itemView) {
            super(itemView);

            userIV = (ImageView) itemView.findViewById(R.id.user_iv);
            nameTv = (TextView) itemView.findViewById(R.id.name_tv);


        }
    }
}
