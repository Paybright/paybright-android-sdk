package com.paybright.sdk;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */


import java.util.HashMap;
import java.util.Map;


public class PBCustomer {

    private String customerEmail;

    private String customerFirstName;

    private String customerLastName;

    private String customerPhone;


    public PBCustomer(String customerEmail,
                      String customerFirstName,
                      String customerLastName,
                      String customerPhone) {

        this.customerEmail = customerEmail;

        this.customerFirstName = customerFirstName;

        this.customerLastName = customerLastName;


        if (customerPhone != null) {

            this.customerPhone = customerPhone;
        }
    }


    Map<String, Object> customerMap() {

        Map<String, Object> rawMap = new HashMap<>();


        rawMap.put("x_customer_email", customerEmail);

        rawMap.put("x_customer_first_name", customerFirstName);

        rawMap.put("x_customer_last_name", customerLastName);


        if (customerPhone != null) {

            rawMap.put("x_customer_phone", customerLastName);
        }


        return rawMap;
    }
}

