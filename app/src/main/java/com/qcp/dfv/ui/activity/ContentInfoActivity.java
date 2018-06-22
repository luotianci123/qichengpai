package com.qcp.dfv.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qcp.dfv.R;
import com.qcp.dfv.adapter.CommentAdapter;
import com.qcp.dfv.adapter.LikeAdapter;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.bean.CommentList;
import com.qcp.dfv.bean.LikeList;
import com.qcp.dfv.utils.AppUtils;
import com.socks.library.KLog;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by panjunquan on 2018/1/9.
 * 详细信息
 */

public class ContentInfoActivity extends BaseActivity {

    @Bind(R.id.ry_view)
    RecyclerView ryView;
    @Bind(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;

    ArrayList<CommentList> commentLists;
    ArrayList<LikeList> likeLists;
    CommentAdapter adapter;
    LikeAdapter likeAdapter;
    TextView editTv;
    private String VIDEO_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentinfo);
        ButterKnife.bind(this);
        super.initAppBar();
        setTitle("详细信息");
        showLeftView();

        VIDEO_URL = "http://b.icloud-u.com/Uploads/Video/2016-08-11/57ac2812b5275.mp4";

        editTv = (TextView) findViewById(R.id.edit_tv);

        initListData();

        videoplayer.setUp(VIDEO_URL, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        Glide.with(this).load("http://36.111.199.9:8888/resources/dl/picture/375200839010019619.png").into(videoplayer.thumbImageView);
//        videoplayer.thumbImageView.setImage(VIDEO_URL);
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        editTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    /**
     * 弹出评论框
     */
    private void showDialog() {
        final Dialog dlg = new Dialog(this, R.style.ActionSheet);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.comment_edit_dialog, null);
        final EditText commentEt = (EditText) layout.findViewById(R.id.comment_et);
        Button sendBt = (Button) layout.findViewById(R.id.send_bt);
        final TextView numberTv = (TextView) layout.findViewById(R.id.number_tv);
        commentEt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_BACK)) {
                    KLog.e("键盘收起：" + commentEt.getText());
                    dlg.dismiss();
                    return true;
                }
                return false;
            }
        });
        commentEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int num = editable.length();
                numberTv.setText(String.valueOf(num) + "/200");

            }
        });
        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(commentEt.getText().toString())) {

                } else {

                }
                dlg.dismiss();

            }
        });

        Window w = dlg.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        w.getDecorView().setPadding(0, 0, 0, 0); //消除边距
        w.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        w.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;   //设置宽度充满屏幕
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;//设置dialog在屏幕中显示的位置
        dlg.setContentView(layout);
        dlg.show();

    }

    private void initListData() {
        commentLists = new ArrayList<>();
        likeLists = new ArrayList<>();
        adapter = new CommentAdapter(this, commentLists);
        likeAdapter = new LikeAdapter(this, likeLists);
        //设置布局管理器，默认纵向滑动
        ryView.setLayoutManager(new LinearLayoutManager(this));
        //设置adapter
        ryView.setAdapter(adapter);
        setHeader(ryView);
        //上拉刷新
//        refreshLayout.setOnRefreshListener(refreshListener);
//        //下拉加载
//        refreshLayout.setOnLoadmoreListener(loadmoreListener);
//        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
//        adapter.setOnItemClickLitener(litener);
    }

    //添加的head布局
    private void setHeader(RecyclerView ryView) {
        View header = LayoutInflater.from(this).inflate(R.layout.content_header, ryView, false);
        RecyclerView likeRyView = (RecyclerView) header.findViewById(R.id.like_ry_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        likeRyView.setLayoutManager(linearLayoutManager);
        likeRyView.setAdapter(likeAdapter);

        adapter.setHeaderView(header);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
        //Change these two variables back
        JZVideoPlayer.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        JZVideoPlayer.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.like_ibt, R.id.collect_ibt, R.id.share_ibt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.like_ibt:
                AppUtils.showShortToast("点赞");
                break;
            case R.id.collect_ibt:
                AppUtils.showShortToast("收藏");
                break;
            case R.id.share_ibt:
                AppUtils.showShortToast("分享");
                break;
        }
    }
}
