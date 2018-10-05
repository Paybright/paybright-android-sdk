package com.paybright.sdk;


/*
 * Created by Manpreet Singh on 20/09/2018.
 */



import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PBWebView extends AppCompatActivity {

    private boolean fTime, isWindowAdded;

    private PBWebViewDelegate delegate;

    private PBWebExtraWindow wbExtraWindow;

    private RelativeLayout layProgress;

    private WebView wvPayment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web_view);

        wbExtraWindow = new PBWebExtraWindow();

        wvPayment = findViewById(R.id.wvPayment);

        layProgress = findViewById(R.id.layProgress);

        ProgressBar mBar = findViewById(R.id.pBar);

        mBar.getIndeterminateDrawable().setColorFilter(Color.WHITE,
                android.graphics.PorterDuff.Mode.MULTIPLY);

        delegate = PBViewController.delegate;

        launchUIWebViewWithRequest();
    }

    private void clearUIWebViewCache() {

        wvPayment.loadUrl("");

        wvPayment.clearCache(true);

        wvPayment.clearHistory();

        wvPayment.removeAllViews();

        finish();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void launchUIWebViewWithRequest() {

        final WebSettings webSettings = wvPayment.getSettings();

        wvPayment.setWebViewClient(new WebClient());

        webSettings.setJavaScriptEnabled(true);

        webSettings.setSupportMultipleWindows(true);

        wvPayment.setWebChromeClient(new WebChromeClient() {

            @Override
            public boolean onCreateWindow(WebView view, boolean dialog, boolean userGesture, Message resultMsg) {

                Message href = view.getHandler().obtainMessage();

                view.requestFocusNodeHref(href);

                String url = href.getData().getString("url");

                isWindowAdded = true;

                Bundle bData = new Bundle();

                bData.putString("reqUrl", url);

                wbExtraWindow.setArguments(bData);

                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.layContainer, wbExtraWindow)
                        .commit();

                return super.onCreateWindow(view, dialog, userGesture, resultMsg);
            }
        });

        wvPayment.postUrl(getIntent().getStringExtra("reqUrl"),
                getIntent().getStringExtra("postString").getBytes());
    }

    private class WebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);

            if (!fTime)

                layProgress.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            try {

                if (url.equals(PBConfig.getInstance()
                        .instanceObj.productObj
                        .productMap()
                        .get("x_url_cancel").toString())) {

                    clearUIWebViewCache();

                    delegate.userDidCancel();

                } else if (url.contains((CharSequence) PBConfig.getInstance().instanceObj
                        .productObj
                        .productMap()
                        .get("x_url_complete"))) {

                    clearUIWebViewCache();

                    Map<String, String> rawMap = new TreeMap<>();

                    rawMap.putAll(getQueryString(url));

                    if (rawMap.get("x_result").equals("Completed")) {

                        String generatedSignature = rawMap.get("x_signature");

                        rawMap.remove("x_signature");

                        StringBuilder sb = new StringBuilder();

                        for (Map.Entry<String, String> entry : rawMap.entrySet()) {

                            sb.append(entry.getKey());

                            sb.append(entry.getValue());
                        }

                        if (generatedSignature.equals(new PBHMAC().generateHashWithHMAC256(sb.toString(),
                                PBConfig.getInstance().apiToken))) {

                            delegate.transactionComplete(true, rawMap.toString());

                        } else {

                            delegate.transactionComplete(false, "");
                        }

                    } else {

                        delegate.transactionComplete(false, "");
                    }
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            if (!fTime)

                layProgress.setVisibility(View.GONE);

            fTime = true;
        }
    }

    private HashMap<String, String> getQueryString(String url) {

        Uri uri = Uri.parse(url);

        HashMap<String, String> map = new HashMap<>();

        for (String paramName : uri.getQueryParameterNames()) {

            if (paramName != null) {

                String paramValue = uri.getQueryParameter(paramName);

                if (paramValue != null) {

                    map.put(paramName, paramValue);
                }
            }
        }

        return map;
    }

    private void removeWindow() {

        isWindowAdded = false;

        getSupportFragmentManager()
                .beginTransaction()
                .remove(wbExtraWindow)
                .commit();
    }

    @Override
    public void onBackPressed() {

        if (isWindowAdded) {

            removeWindow();

        } else if (wvPayment.canGoBack()) {

            wvPayment.goBack();

        } else {

            finish();
        }
    }
}
