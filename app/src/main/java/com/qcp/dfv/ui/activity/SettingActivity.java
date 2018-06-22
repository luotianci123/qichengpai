package com.qcp.dfv.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.listener.DownloadListener;
import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;
import com.qcp.dfv.bean.VersionInfo;
import com.qcp.dfv.net.JsonCallback;
import com.qcp.dfv.net.ReportError;
import com.qcp.dfv.net.UrlConstant;
import com.qcp.dfv.pub.Preference;
import com.qcp.dfv.utils.AppUtils;
import com.qcp.dfv.utils.CacheUtils;
import com.qcp.dfv.utils.DialogHelp;
import com.qcp.dfv.utils.ToastHelper;
import com.qcp.dfv.utils.Utils;
import com.socks.library.KLog;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by panjunquan on 2018/5/29.
 * 设置
 */

public class SettingActivity extends BaseActivity {

    @Bind(R.id.left)
    LinearLayout left;
    @Bind(R.id.cache_tv)
    TextView cacheTv;
    private int mCurrentVersionCode;//app当前版本

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        super.initAppBar();
        showLeftView();

        mCurrentVersionCode = AppUtils.getVersionCode();
        try {
            cacheTv.setText(CacheUtils.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.update_ly, R.id.delete_ly, R.id.log_out_ly})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.update_ly:
                dataRequest();
                break;
            case R.id.delete_ly:
                DialogHelp.getConfirmDialog(this, "确定清除所有缓存？", onOkClickListener).show();
                break;
            case R.id.log_out_ly:
                DialogHelp.getConfirmDialog(this, "确定退出登录？", logoutClickListener).show();
                break;
        }
    }

    DialogInterface.OnClickListener onOkClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            CacheUtils.clearAllCache(SettingActivity.this);
            cacheTv.setText("0K");
            AppUtils.showShortToast("缓存已清理");
        }
    };

    DialogInterface.OnClickListener logoutClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    };

    /**
     * 版本更新请求
     */
    public void dataRequest() {
        showWait(true);
        OkGo.get(UrlConstant.GET_MY_GETVERSION)
                .execute(new JsonCallback<VersionInfo>() {//ProductPageVoBean
                    @Override
                    public void onSuccess(VersionInfo versionInfo, Call call, Response response) {
                        showWait(false);
                        if (versionInfo != null) {
                            if (mCurrentVersionCode < versionInfo.getVersionCode()) {

                                updateDialog(versionInfo);
                            } else {
                                AppUtils.showShortToast("已经是最新版本");
                            }

                        }
                    }

                    @Override
                    public void onFailure(String code, String message) {
                        showWait(false);
                        if (!TextUtils.isEmpty(message)) {
                            AppUtils.showToast(message);
                        }
                    }
                });
    }

    private void updateDialog(final VersionInfo versionInfo) {
        //先new出一个监听器，设置好监听
        DialogInterface.OnClickListener dialogOnclicListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        downloadAndInstallApk(versionInfo);
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        dialog.cancel();
                        break;
                }
            }
        };
        //dialog参数设置
        AlertDialog.Builder builder = new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle("版本更新"); //设置标题
        builder.setMessage(versionInfo.getUpdateInfo()); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定更新", dialogOnclicListener);
        builder.setNegativeButton("残忍拒绝", dialogOnclicListener);
        builder.setCancelable(false);
        builder.create().show();
    }

    /**
     * 下载最新版本
     */
    private void downloadAndInstallApk(final VersionInfo versionInfo) {

        final String downloadUrl = versionInfo.getDownloadUrl();

        if (TextUtils.isEmpty(downloadUrl)) {
            ReportError.reportError("DownloadUrl is Empty");
            return;
        }

        String folder = Environment.getExternalStorageDirectory() + File.separator + "Download" + File.separator;

        if (!new File(folder).exists()) new File(folder).mkdirs();

        String fileName = "bss-" + versionInfo.getVersionCode() + ".apk";
        File file = new File(folder + fileName);
        int last_version = Preference.getInt("package_version");

        if (last_version == versionInfo.getVersionCode()) {
            if (file.exists()) {
                KLog.v("exist");
                installApp(file.getAbsolutePath());
                return;
            }
        } else {
            if (file.exists()) {
                file.delete();
            }
        }

        DownloadManager downloadManager = DownloadManager.getInstance();

        downloadManager.setTargetFolder(folder);

        downloadManager.removeTask("download_apk");

        downloadManager.addTask(fileName, "download_apk", OkGo.get(downloadUrl), new DownloadListener() {

            @Override
            public void onAdd(DownloadInfo downloadInfo) {
                KLog.v("开始下载");
            }

            @Override
            public void onProgress(DownloadInfo downloadInfo) {
                KLog.v("下载进度--->>" + (int) (downloadInfo.getProgress() * 100));
                ToastHelper.showToast(SettingActivity.this, "正在下载中" + (int) (downloadInfo.getProgress() * 100) + "%", 1);
            }

            @Override
            public void onFinish(DownloadInfo downloadInfo) {

                Preference.putInt("package_version", versionInfo.getVersionCode());

                KLog.v("--->>" + downloadInfo.getTargetPath());
                installApp(downloadInfo.getTargetPath());
                ToastHelper.cancelToast();
            }

            @Override
            public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {

                ReportError.reportError("DOWNLOAD: " + errorMsg);
            }
        });
    }

    private void installApp(String apkPath) {
        Utils.installApk(SettingActivity.this, apkPath);
    }

}
