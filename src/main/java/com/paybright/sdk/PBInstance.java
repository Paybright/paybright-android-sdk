package com.paybright.sdk;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */



public class PBInstance {

    PBCustomer customerObj;

    PBCustomerBilling customerBillingObj;

    PBCustomerShipping customerShippingObj;

    PBProduct productObj;


    public PBInstance(PBCustomer customerObj,
                      PBCustomerBilling customerBillingObj,
                      PBCustomerShipping customerShippingObj,
                      PBProduct productObj) {

        this.customerObj            = customerObj;

        this.customerBillingObj     = customerBillingObj;

        this.customerShippingObj    = customerShippingObj;

        this.productObj             = productObj;
    }
}

