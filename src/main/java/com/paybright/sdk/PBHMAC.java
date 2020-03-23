package com.paybright.sdk;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */


import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


class PBHMAC {

    String generateHashWithHMAC256(String message, String key) {

        try {

            byte[] bytes = hmac(key.getBytes(), message.getBytes());


            return bytesToHex(bytes);
        }

        catch (Exception e) {

            e.printStackTrace();
        }


        return "";
    }


    private byte[] hmac(byte[] key, byte[] message)
            throws NoSuchAlgorithmException, InvalidKeyException {


        Mac mac = Mac.getInstance("HmacSHA256");

        mac.init(new SecretKeySpec(key, "HmacSHA256"));


        return mac.doFinal(message);
    }


    private String bytesToHex(byte[] bytes) {

        final char[] hexArray = "0123456789abcdef".toCharArray();


        char[] hexChars = new char[bytes.length * 2];


        for (int j = 0, v; j < bytes.length; j++) {

            v = bytes[j] & 0xFF;


            hexChars[j * 2] = hexArray[v >>> 4];

            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }


        return new String(hexChars);
    }
}

