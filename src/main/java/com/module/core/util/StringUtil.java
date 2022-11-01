package com.module.core.util;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

    public static String getSHA256( String source ){
        String SHA256 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(source.getBytes());
            byte byteData[] = md.digest();
            SHA256 = Hex.encodeHexString(byteData);
            System.out.println("원문 : " + source + " / SHA-256 : " + SHA256);
        }
        catch( NoSuchAlgorithmException ex ){
            ex.printStackTrace();
            SHA256 = null;
        }
        return SHA256;
    }

    public void setSha(){
        System.out.println("Adetstset");
    }
}
