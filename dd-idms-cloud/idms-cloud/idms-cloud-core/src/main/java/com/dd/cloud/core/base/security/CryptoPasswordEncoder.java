package com.dd.cloud.core.base.security;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

public class CryptoPasswordEncoder {

    public static synchronized String encryptSha256(String inputStr) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte digest[] = md.digest(inputStr.getBytes("UTF-8"));
            return new String(Base64.encodeBase64(digest));
        } catch (Exception e) {
            return null;
        }
    }

    public static String hexToBase64(String input) throws DecoderException {
        String out = null;
        try {
            byte[] bytes = Hex.decodeHex(input.toCharArray());
            out = new String(Base64.encodeBase64(bytes));
        } catch (Exception ex) {
            //ex.printStackTrace();
        }
        return out;
    }

    public static void main(String[] args) throws DecoderException {
        PwdEncoder pwdEncoder = new PwdEncoder();
        System.out.println(pwdEncoder.encode("123456"));
    }
}
