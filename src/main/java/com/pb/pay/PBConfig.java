package com.pb.pay;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */



public class PBConfig {


    public enum PBEnvironment {

        Sandbox, Production
    }


    private static PBConfig pbConfig;


    public static PBConfig getInstance() {

        if (pbConfig == null)

            pbConfig = new PBConfig();


        return pbConfig;
    }


    public PBEnvironment environment = PBEnvironment.Sandbox;

    public String accountID = "";

    public String apiToken = "";


    public PBInstance instanceObj;


    public void initialize(PBEnvironment environment, String accountID, String apiToken) {

        this.environment = environment;

        this.accountID = accountID;

        this.apiToken = apiToken;
    }
}

