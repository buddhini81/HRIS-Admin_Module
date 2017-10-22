/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ha.presentation.util;

import com.ha.exceptions.BusinessException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import com.ha.exceptions.PasswordEncryptionSytemUnavailableException;
import sun.misc.BASE64Encoder;
import sun.misc.CharacterEncoder;

/**
 *
 * @author Buddhini
 */
public final class PasswordService {

    private static PasswordService instance;

    private PasswordService() {
    }

    public synchronized String encrypt(String plaintext) throws BusinessException {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA"); //step 2
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new BusinessException();
        }
        try {
            md.update(plaintext.getBytes("UTF-8")); //step 3
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new BusinessException();
        }
        byte raw[] = md.digest(); //step 4
        String hash = (new BASE64Encoder()).encode(raw); //step 5
        return hash; //step 6
    }

    public static synchronized PasswordService getInstance() //step 1
    {
        if (instance == null) {
            return new PasswordService();
        } else {
            return instance;
        }
    }
}
