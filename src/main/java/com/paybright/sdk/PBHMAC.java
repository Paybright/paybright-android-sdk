package com.paybright.sdk;


/*
 * Created by Manpreet Singh on 15/09/2018.
 */


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;


class PBHMAC {

    String generateHashWithHMAC256(String message, String key) {

        try {

            final String hashingAlgorithm = "HmacSHA256"; //or "HmacSHA1", "HmacSHA512"


            byte[] bytes = hmac(hashingAlgorithm, key.getBytes(), message.getBytes());


            final String messageDigest = bytesToHex(bytes);


            return messageDigest;
        }

        catch (Exception e) {

            e.printStackTrace();
        }


        return "";
    }


    private byte[] hmac(String algorithm, byte[] key, byte[] message)
            throws NoSuchAlgorithmException, InvalidKeyException {


        Mac mac = Mac.getInstance(algorithm);

        mac.init(new SecretKeySpec(key, algorithm));


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
