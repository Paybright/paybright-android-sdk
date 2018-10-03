package com.pb.pay;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */


import java.io.Serializable;

public interface PBWebViewDelegate extends Serializable {

    void userDidCancel();

    void transactionComplete(boolean success, String params);
}
