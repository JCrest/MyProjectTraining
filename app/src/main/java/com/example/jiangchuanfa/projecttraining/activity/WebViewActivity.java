package com.example.jiangchuanfa.projecttraining.activity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;
import com.example.jiangchuanfa.projecttraining.utils.TagUtils;

import butterknife.BindView;

public class WebViewActivity extends BaseActivity {


    private static final String TAG = WebViewActivity.class.getSimpleName();
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ib_topic_favour)
    ImageButton ibTopicFavour;
    @BindView(R.id.ib_share_liangcang)
    ImageButton ibShareLiangcang;
    @BindView(R.id.wb_webview)
    WebView wbWebview;
    @BindView(R.id.head_progressBar)
    ProgressBar headProgressBar;
    @BindView(R.id.videoContainer)
    FrameLayout videoContainer;
    private String topic_url;
    private String topic_name;


    private WebChromeClient.CustomViewCallback mCallBack;

    @Override
    public void initView() {

        topic_url = getIntent().getStringExtra("topic_url");
        topic_name = getIntent().getStringExtra("topic_name");
        tvTitle.setText(topic_name);
        //webview 加载网页地址

        initWebView();

//        wbWebview.getSettings().setJavaScriptEnabled(true);
//        wbWebview.getSettings().setPluginState(WebSettings.PluginState.ON);
////        wbWebview.getSettings().setPluginsEnabled(true);//可以使用插件
//        wbWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        wbWebview.getSettings().setAllowFileAccess(true);
//        wbWebview.getSettings().setDefaultTextEncodingName("UTF-8");
//        wbWebview.getSettings().setLoadWithOverviewMode(true);
//        wbWebview.getSettings().setUseWideViewPort(true);
//        wbWebview.setVisibility(View.VISIBLE);


//        wbWebview.getSettings().setJavaScriptEnabled(true);
//        wbWebview.getSettings().setOffscreenPreRaster(true);
//        wbWebview.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wbWebview.setVisibility(View.VISIBLE);
//        wbWebview.getSettings().setUseWideViewPort(true);

        wbWebview.loadUrl(topic_url);

        wbWebview.addJavascriptInterface(new JsObject(), "onClick");
        Log.e(TAG, "initView: " + topic_url);
        hideProgressBar();
    }

    private void initWebView() {

        wbWebview.getSettings().setJavaScriptEnabled(true);

        wbWebview.setWebChromeClient(new CustomWebViewChromeClient());
        wbWebview.setWebViewClient(new CustomWebClient());

        wbWebview.addJavascriptInterface(new JsObject(), "onClick");

    }

    private void hideProgressBar() {
        wbWebview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                headProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void initListener() {
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ibTopicFavour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("收藏");
            }
        });
        ibShareLiangcang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToast("分享");
            }
        });

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private class JsObject {
        @JavascriptInterface
        public void fullscreen() {
            //监听到用户点击全屏按钮
            fullScreen();
        }
    }

    private void fullScreen() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////
    private class CustomWebViewChromeClient extends WebChromeClient {
        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            fullScreen();
            wbWebview.setVisibility(View.GONE);
            videoContainer.setVisibility(View.VISIBLE);
            videoContainer.addView(view);
            mCallBack = callback;
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onHideCustomView() {
            fullScreen();
            if (mCallBack != null) {
                mCallBack.onCustomViewHidden();
            }
            wbWebview.setVisibility(View.VISIBLE);
            videoContainer.removeAllViews();
            videoContainer.setVisibility(View.GONE);
            super.onHideCustomView();
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private class CustomWebClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            String js = TagUtils.getJs(url);
            view.loadUrl(js);
        }
    }


    @Override
    public void onBackPressed() {
        if (wbWebview.canGoBack()) {
            wbWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
