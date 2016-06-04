package com.origin.seok.knuplan;

/**
 * Created by Seok on 2016-06-03.
 */
public class PasswordCoder {
    public String encodePassword(String pw){
        if(pw.length() == 0)
            return "";
        return "dud" + pw + "tjr8";
    }
    public String decodePassword(String pw){
        if(pw.length() == 0)
            return "";
        return pw.substring(3, pw.length()-4);
    }
}
