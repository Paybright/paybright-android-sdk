package com.paybright.sdk;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */


import java.util.HashMap;


public class PBCustomerBilling {

    private String customerBillingAddress1;

    private String customerBillingAddress2; //Optional

    private String customerBillingCity;

    private String customerBillingCompany;  //Optional

    private String customerBillingCountry;

    private String customerBillingPhone;

    private String customerBillingState;

    private String customerBillingZip;


    public PBCustomerBilling(String customerBillingAddress1,
                             String customerBillingAddress2,
                             String customerBillingCity,
                             String customerBillingCompany,
                             String customerBillingCountry,
                             String customerBillingPhone,
                             String customerBillingState,
                             String customerBillingZip) {

        this.customerBillingAddress1 = customerBillingAddress1;


        if (customerBillingAddress2 != null) {

            this.customerBillingAddress2 = customerBillingAddress2;
        }


        this.customerBillingCity = customerBillingCity;


        if (customerBillingCompany != null) {

            this.customerBillingCompany = customerBillingCompany;
        }


        this.customerBillingCountry = customerBillingCountry;

        this.customerBillingPhone = customerBillingPhone;

        this.customerBillingState = customerBillingState;

        this.customerBillingZip = customerBillingZip;
    }

     HashMap<String, Object> customerBillingMap() {

        HashMap<String, Object> rawMap = new HashMap<>();


        rawMap.put("x_customer_billing_address1", customerBillingAddress1);

        rawMap.put("x_customer_billing_city", customerBillingCity);

        rawMap.put("x_customer_billing_country", customerBillingCountry);

        rawMap.put("x_customer_billing_phone", customerBillingPhone);

        rawMap.put("x_customer_billing_state", customerBillingState);

        rawMap.put("x_customer_billing_zip", customerBillingZip);


        if (customerBillingAddress2 != null) {

            rawMap.put("x_customer_billing_address2", customerBillingAddress2);
        }


        if (customerBillingCompany != null) {

            rawMap.put("x_customer_billing_company", customerBillingCompany);
        }

        return rawMap;
    }
}
