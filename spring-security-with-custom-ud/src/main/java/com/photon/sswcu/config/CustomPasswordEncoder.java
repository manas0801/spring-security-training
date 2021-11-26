package com.photon.sswcu.config;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return hashwithSha512(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        String inputPwd= hashwithSha512(rawPassword.toString());

        return inputPwd.equals(encodedPassword);
    }


    private String hashwithSha512(String input)  {
         StringBuilder sb= new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digestedBytes=md.digest(input.getBytes());
            for(int i=0;i< digestedBytes.length;i++){
                sb.append(Integer.toHexString( 0xFF & digestedBytes[i]));

            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
