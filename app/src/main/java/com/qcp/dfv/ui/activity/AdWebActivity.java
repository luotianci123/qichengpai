package com.qcp.dfv.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qcp.dfv.R;
import com.qcp.dfv.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by panjunquan on 2018/1/5.
 */

public class AdWebActivity extends BaseActivity {

    @Bind(R.id.left)
    LinearLayout left;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.web)
    WebView webView;
    String banner_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adweb);
        ButterKnife.bind(this);
        super.initAppBar();

        setTitle("广告页面");
        showLeftView();
        banner_url = getIntent().getStringExtra("banner_url");
        show(banner_url);


    }

    public void show(String url) {
        showWait(true);
        webView = (WebView) findViewById(R.id.web);
        WebSettings webSettings = webView.getSettings();

        if (Build.VERSION.SDK_INT >= 19) {
            webView.getSettings().setLoadsImagesAutomatically(true);
        } else {
            webView.getSettings().setLoadsImagesAutomatically(false);
        }
        webView.getSettings().setUserAgentString("android");

        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);//设置缓冲大小，我设的是8M
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        String dbPath = this.getApplicationContext().getDir("database", this.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(dbPath);
        webSettings.setAppCacheEnabled(true);
        String appCaceDir = this.getApplicationContext().getDir("cache", this.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);

        webView.requestFocus();// 触摸焦点起作用
        webView.setHorizontalScrollBarEnabled(false);// 水平不显示
        webView.setVerticalScrollBarEnabled(false); // 垂直不显示
        webView.loadUrl(url);

        webView.setWebChromeClient(new MyWebViewClient());
        showWait(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (!webView.getSettings().getLoadsImagesAutomatically()) {
                    webView.getSettings().setLoadsImagesAutomatically(true);
                }
                showWait(false);
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                showWait(false);
                // 加载网页失败时处理  如：
                view.loadDataWithBaseURL(null,
                        "<div align=\"center\"><br><span style=\"color:#242424;display:block;padding-top:50px\">数据加载失败</span></div>",
                        "text/html",
                        "utf-8",
                        null);
            }
        });
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                showWait(false);
            }
        }
    }
}