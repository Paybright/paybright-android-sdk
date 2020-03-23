package com.paybright.sdk;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.TreeMap;


public class PBViewController extends AppCompatActivity {

    static Activity context;

    protected static PBWebViewDelegate delegate;

    public PBViewController(Activity context, PBWebViewDelegate delegate) {

        PBViewController.context = context;

        PBViewController.delegate = delegate;

        setup();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // setContentView(R.layout.activity_view_controller);
    }

    private void setup() {

        String requestUrl = (PBConfig.getInstance().environment == PBConfig.PBEnvironment.Sandbox ? PBConstants.TEST_URL :
                PBConstants.LIVE_URL) + "checkout/applicationform.aspx";

        String postString = queryString();

        context.startActivity(new Intent(context, PBWebView.class)
                .putExtra("reqUrl", requestUrl)
                .putExtra("postString", postString));
    }

    private String queryString() {

        Map<String, Object> rawMap = new TreeMap<>();

        rawMap.put("x_account_id", PBConfig.getInstance().accountID);

        rawMap.put("x_test", PBConfig.getInstance().environment == PBConfig.PBEnvironment.Sandbox ? "true" : "false");


        rawMap.putAll(PBConfig.getInstance().instanceObj.customerObj.customerMap());

        rawMap.putAll(PBConfig.getInstance().instanceObj.customerBillingObj.customerBillingMap());

        rawMap.putAll(PBConfig.getInstance().instanceObj.customerShippingObj.customerShipping());

        rawMap.putAll(PBConfig.getInstance().instanceObj.productObj.productMap());

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Object> entry : rawMap.entrySet()) {

            sb.append(entry.getKey());

            sb.append(entry.getValue());
        }

        rawMap.put("x_signature", new PBHMAC().generateHashWithHMAC256(sb.toString(),
                PBConfig.getInstance().apiToken));

        return queryParameters(rawMap);
    }

    private String queryParameters(Map<String, Object> rawMap) {

        StringBuilder strBuilder = new StringBuilder();

        int size = 0;

        for (Map.Entry<String, Object> entry : rawMap.entrySet()) {

            try {

                strBuilder.append(URLEncoder.encode(entry.getKey(), "UTF-8"));

                strBuilder.append("=");

                strBuilder.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));

                if (size != rawMap.size() - 1) {

                    strBuilder.append("&");

                    size++;
                }
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            }
        }

        return strBuilder.toString();
    }
}
