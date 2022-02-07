package by.ishangulyev.application.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPassGenerator {
    public static String generate(String pass){
        StringBuilder sb = new StringBuilder();
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
        }
        byte[] bytes = md5.digest(pass.getBytes());
        for (byte i: bytes) {
            sb.append(String.format("%02X",i));
        }
        return sb.toString();
    }
}
