package com.pb.pay;


/*
 * Created by Manpreet Singh on 19/09/2018.
 */



import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class PBWebExtraWindow extends Fragment {

    private RelativeLayout layProgress;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_extra_window, container, false);

        WebView wvView = view.findViewById(R.id.wvView);

        layProgress = view.findViewById(R.id.layProgress);

        ProgressBar mBar = view.findViewById(R.id.pBar);

        mBar.getIndeterminateDrawable().setColorFilter(Color.WHITE,
                android.graphics.PorterDuff.Mode.MULTIPLY);

        final WebSettings webSettings = wvView.getSettings();

        wvView.setWebViewClient(new WebClient());

        webSettings.setJavaScriptEnabled(true);

        String url = getArguments().getString("reqUrl");

        assert url != null;
        if (!url.isEmpty())

            wvView.loadUrl(url);

        return view;
    }

    private class WebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);

            layProgress.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            layProgress.setVisibility(View.GONE);
        }
    }
}
