package com.qcp.dfv.ui.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.gson.JsonObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.mainview.MainActivity;
import com.qcp.dfv.net.JsonCallback;
import com.qcp.dfv.utils.BitmapUtil;
import com.qcp.dfv.utils.ImageUrlDir;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by panjunquan on 2018/1/4.
 */

public class SplashActivity extends BaseActivity {
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final View view = View.inflate(this, R.layout.activity_splash, null);
        setContentView(view);
//        setContentView(R.layout.activity_splash);
//        startHandler();

        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(800);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                startMain();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
        videoView = (VideoView) findViewById(R.id.videoView);
//        videoView = (FullScreenVideoView) this.findViewById(R.id.videoView);
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
//        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
//        videoView.setVideoURI(Uri.parse("http://180.101.255.3:8888/ship2.mp4"));
        //设置视频路径
        //开始播放视频
//        videoView.start();
//        postData2("037946339");
    }

    private void postData2(String LoginAccount_) {//获取视频
        Log.e("NN2","postData:");
//        OkGo.get("http://192.168.100.162:8082/qcpai-console/onTheRoad/downloadVideo2/downloadVideo")
//        OkGo.get("http://180.101.255.3:8888/shiping.mp4")
        OkGo.get("http://180.101.255.3:8888/ship2.mp4")
//                .params("fileId",LoginAccount_)//037946339
                .execute(new FileCallback() {
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        Log.e("NN2","file:" + file);
                        Uri.parse(String.valueOf(new File(file.toString())));

                        videoView.setVideoURI(Uri.parse(String.valueOf(new File(file.toString()))));
                        //设置视频路径
                        //开始播放视频
                        videoView.start();
                    }
                });
    }
    private void startMain() {
//        if (SharedPreferencesUtils.getIsLogin(this)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
//        } else {
//            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//            startActivity(intent);
//        }
        finish();
    }

    private void startHandler() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
