package com.paybright.sdk;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */


import java.util.HashMap;


public class PBProduct {

    private String amount;

    private String currency;

    private String description;       //Optional

    private String invoice;           //Optional

    private String planID;            //Optional

    private String platform;

    private String reference;

    private String shopCountry;

    private String shopName;

    private String urlCallback;

    private String urlCancel;

    private String urlComplete;


    public PBProduct(Double amount,
                     String currency,
                     String description,
                     String invoice,
                     String planID,
                     String platform,
                     String reference,
                     String shopCountry,
                     String shopName,
                     String urlCallback,
                     String urlCancel,
                     String urlComplete) {

        this.amount = String.valueOf(amount);

        this.currency = currency;


        if (description != null) {

            this.description = description;
        }


        if (invoice != null) {

            this.invoice = invoice;
        }


        if (planID != null) {

            this.planID = planID;
        }


        this.platform = platform;

        this.reference = reference;

        this.shopCountry = shopCountry;

        this.shopName = shopName;

        this.urlCallback = urlCallback;

        this.urlCancel = urlCancel;

        this.urlComplete = urlComplete;
    }


    HashMap<String, Object> productMap() {

        HashMap<String, Object> rawMap = new HashMap<>();


        rawMap.put("x_amount", amount);

        rawMap.put("x_currency", currency);

        rawMap.put("x_platform", platform);

        rawMap.put("x_reference", reference);

        rawMap.put("x_shop_country", shopCountry);

        rawMap.put("x_shop_name", shopName);

        rawMap.put("x_url_callback", urlCallback);

        rawMap.put("x_url_cancel", urlCancel);

        rawMap.put("x_url_complete", urlComplete);


        if (description != null) {

            rawMap.put("x_description", description);
        }


        if (invoice != null) {

            rawMap.put("x_invoice", invoice);
        }


        if (planID != null) {

            rawMap.put("x_plan_id", planID);
        }


        return rawMap;
    }
}

