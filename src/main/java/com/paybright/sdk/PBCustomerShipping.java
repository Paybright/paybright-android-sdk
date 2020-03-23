package com.paybright.sdk;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */


import java.util.HashMap;


public class PBCustomerShipping {

    private String customerShippingAddress1;

    private String customerShippingAddress2;  //Optional

    private String customerShippingCity;

    private String customerShippingCompany;   //Optional

    private String customerShippingCountry;

    private String customerShippingFirstName;

    private String customerShippingLastName;

    private String customerShippingPhone;

    private String customerShippingState;

    private String customerShippingZip;


    public PBCustomerShipping(String customerShippingAddress1,
                              String customerShippingAddress2,
                              String customerShippingCity,
                              String customerShippingCompany,
                              String customerShippingCountry,
                              String customerShippingFirstName,
                              String customerShippingLastName,
                              String customerShippingPhone,
                              String customerShippingState,
                              String customerShippingZip) {

        this.customerShippingAddress1 = customerShippingAddress1;


        if (customerShippingAddress2 != null) {

            this.customerShippingAddress2 = customerShippingAddress2;
        }


        this.customerShippingCity = customerShippingCity;


        if (customerShippingCompany != null) {

            this.customerShippingCompany = customerShippingCompany;
        }


        this.customerShippingCountry = customerShippingCountry;

        this.customerShippingFirstName = customerShippingFirstName;

        this.customerShippingLastName = customerShippingLastName;

        this.customerShippingPhone = customerShippingPhone;

        this.customerShippingState = customerShippingState;

        this.customerShippingZip = customerShippingZip;
    }


    HashMap<String, Object> customerShipping() {

        HashMap<String, Object> rawMap = new HashMap<>();


        rawMap.put("x_customer_shipping_address1", customerShippingAddress1);

        rawMap.put("x_customer_shipping_city", customerShippingCity);

        rawMap.put("x_customer_shipping_country", customerShippingCountry);

        rawMap.put("x_customer_shipping_first_name", customerShippingFirstName);

        rawMap.put("x_customer_shipping_last_name", customerShippingLastName);

        rawMap.put("x_customer_shipping_phone", customerShippingPhone);

        rawMap.put("x_customer_shipping_state", customerShippingState);

        rawMap.put("x_customer_shipping_zip", customerShippingZip);


        if (customerShippingAddress2 != null) {

            rawMap.put("x_customer_shipping_address2", customerShippingAddress2);
        }


        if (customerShippingCompany != null) {

            rawMap.put("x_customer_shipping_company", customerShippingCompany);
        }


        return rawMap;
    }
}

