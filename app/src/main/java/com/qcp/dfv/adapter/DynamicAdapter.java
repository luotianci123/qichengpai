package com.qcp.dfv.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qcp.dfv.R;
import com.qcp.dfv.bean.DynamicList;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by panjunquan on 2018/6/5.
 */

public class DynamicAdapter extends RecyclerView.Adapter<DynamicAdapter.ViewHolder> {
    @Bind(R.id.user_ly)
    LinearLayout userLy;
    private Context context;
    private ArrayList<DynamicList> list;
    private Handler handler;

    public static final int TYPE_HEADER = 0;//设置header的状态
    public static final int TYPE_NORMAL = 1;
    private View mHeaderView;

    public View getHeaderView() {
        return mHeaderView;
    }

    public void setHeaderView(View mHeaderView) {
        this.mHeaderView = mHeaderView;
        notifyItemInserted(0);
    }

    public DynamicAdapter(Context context, ArrayList<DynamicList> list) {
        this.context = context;
        this.list = list;
    }

    public DynamicAdapter(Context context, ArrayList<DynamicList> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new ViewHolder(mHeaderView);
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.dynamic_item, parent, false));
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

        if (getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder);

        holder.userLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 100;
                msg.arg1 = pos;
                handler.sendMessage(msg);
            }
        });
        holder.commentTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message msg = new Message();
                msg.what = 100;
                msg.arg1 = pos;
                handler.sendMessage(msg);
            }
        });

        /*
         *这里处理item数据的显示
         */
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
        return mHeaderView == null ? 20 : 20 + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null)
            return TYPE_NORMAL;
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout userLy;
        TextView commentTv;


        public ViewHolder(View itemView) {
            super(itemView);
            if (itemView == mHeaderView) return;
            userLy = (LinearLayout) itemView.findViewById(R.id.user_ly);
            commentTv = (TextView) itemView.findViewById(R.id.comment_tv);

        }
    }
}
