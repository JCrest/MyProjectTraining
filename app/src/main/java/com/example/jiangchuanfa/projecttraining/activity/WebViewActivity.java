package com.example.jiangchuanfa.projecttraining.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.jiangchuanfa.projecttraining.R;
import com.example.jiangchuanfa.projecttraining.base.BaseActivity;

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
    private String topic_url;
    private String topic_name;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void initView() {

        topic_url = getIntent().getStringExtra("topic_url");
        topic_name = getIntent().getStringExtra("topic_name");
        tvTitle.setText(topic_name);
        //webview 加载网页地址

        wbWebview.getSettings().setJavaScriptEnabled(true);
        wbWebview.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wbWebview.getSettings().setPluginsEnabled(true);//可以使用插件
        wbWebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wbWebview.getSettings().setAllowFileAccess(true);
        wbWebview.getSettings().setDefaultTextEncodingName("UTF-8");
        wbWebview.getSettings().setLoadWithOverviewMode(true);
        wbWebview.getSettings().setUseWideViewPort(true);
        wbWebview.setVisibility(View.VISIBLE);






//        wbWebview.getSettings().setJavaScriptEnabled(true);
//        wbWebview.getSettings().setOffscreenPreRaster(true);
//        wbWebview.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wbWebview.setVisibility(View.VISIBLE);
//        wbWebview.getSettings().setUseWideViewPort(true);

        wbWebview.loadUrl(topic_url);
        Log.e(TAG, "initView: " + topic_url);
        hideProgressBar();
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
}
