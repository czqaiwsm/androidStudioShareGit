package com.share.learn.fragment.center;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.alipay.sdk.pay.demo.H5PayDemoActivity;
import com.share.learn.R;
import com.share.learn.fragment.BaseFragment;
import com.share.learn.utils.BaseApplication;

public class ServiceProtocolFragment extends BaseFragment {

    private WebView mWebView;
    private String url;
    private String title = "我要学服务协议";

    int flag = 0; //11 banner

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Intent intent = mActivity.getIntent();
        if(intent != null){
            url = intent.getStringExtra("url");
            title = intent.hasExtra("title")?intent.getStringExtra("title"):"";

            flag = intent.getFlags();
            switch (flag){
                case 11:
                    BaseApplication application = BaseApplication.getInstance();
                    title = "我享学";
                    url = url+"&"+"appVersion="+application.appVersion+"&clientType=3&accessToken="+BaseApplication.getMt_token()+"&deviceId="+BaseApplication.diviceId;
                    break;
                case 12:
                    title = "关于我们";
                    break;
            }


        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.fragment_web_logic, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    @SuppressWarnings("deprecation")
    private void initView(View view) {
        // TODO Auto-generated method stub

        setTitleText(title);
        setLeftHeadIcon(R.drawable.back, new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                getActivity().finish();
            }
        });
        mWebView = (WebView) view.findViewById(R.id.rule_webview);
        mWebView.setWebViewClient(getClient());
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(false);
        int screenDensity = getResources().getDisplayMetrics().densityDpi;
        WebSettings.ZoomDensity zoomDensity = WebSettings.ZoomDensity.MEDIUM;
        switch (screenDensity) {
            case DisplayMetrics.DENSITY_LOW:
                zoomDensity = WebSettings.ZoomDensity.CLOSE;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                zoomDensity = WebSettings.ZoomDensity.MEDIUM;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                zoomDensity = WebSettings.ZoomDensity.FAR;
                break;
        }
        WebSettings settings = mWebView.getSettings();
        settings.setDefaultZoom(zoomDensity);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);// 可能的话不要超过屏幕宽度

//        if(url.contains("scheme=alipays")){
            mWebView.setWebViewClient(new WebViewClient(){

                public boolean shouldOverrideUrlLoading(final WebView view, String url) {
                    // 获取上下文, H5PayDemoActivity为当前页面
                    final Activity context = getActivity();
                    System.out.println("url======"+url);

                    // ------  对alipays:相关的scheme处理 -------
                    if(url.startsWith("alipays:") || url.startsWith("alipay")) {
                        try {
                            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                        } catch (Exception e) {
                            new AlertDialog.Builder(context)
                                    .setMessage("未检测到支付宝客户端，请安装后重试。")
                                    .setPositiveButton("立即安装", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Uri alipayUrl = Uri.parse("https://d.alipay.com");
                                            context.startActivity(new Intent("android.intent.action.VIEW", alipayUrl));
                                        }
                                    }).setNegativeButton("取消", null).show();
                        }
                        return true;
                    }
                    // ------- 处理结束 -------

                    if (!(url.startsWith("http") || url.startsWith("https"))) {
                        return true;
                    }

                    view.loadUrl(url);
                    return true;
                }

            });
//        }


        if(TextUtils.isEmpty(url)){
            url = "http://www.sf-express.com/cn/sc/";
        }
        mWebView.loadUrl(url);

    }

    private WebViewClient getClient() {
        // TODO Auto-generated method stub
        WebViewClient mClient = new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                super.onPageStarted(view, url, favicon);
                // startLoading(mFragment);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
            }

        };
        return mClient;
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        if (mWebView != null) {
            mWebView.getSettings().setLoadWithOverviewMode(false);
            mWebView.getSettings().setUseWideViewPort(false);
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.getSettings().setSupportZoom(false);
            mWebView.getSettings().setBuiltInZoomControls(false);
            mWebView.destroy();
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        ViewGroup view = (ViewGroup) getActivity().getWindow().getDecorView();
        view.removeAllViews();
    }



}
