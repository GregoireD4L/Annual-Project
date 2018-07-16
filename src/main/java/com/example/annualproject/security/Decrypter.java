package com.example.annualproject.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/***
 *  Created by Nicolas Sirac
 ****/
public class Decrypter {

    /**Sha-256
     *
     * @param plainText
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String encrypt(String plainText) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(plainText.getBytes(StandardCharsets.UTF_8));
        return new String(hash);
    }
}
