package com.origin.seok.knuplan;

import android.util.Log;

/**
 * Created by Seok on 2016-06-03.
 */
public class PasswordCoder {
    public String encodePassword(String pw){
        if(pw.length() == 0)
            return "";
        return "dud" + passwordCheck(pw) + "tjr8";
    }
    public String decodePassword(String pw){
        if(pw.length() == 0)
            return "";
        return pw.substring(3, pw.length()-4);
    }
    public String passwordCheck(String pw){
        // +랑 =등 기호 핸들링
        String temp = "";
        for(int i=0;i<pw.length() ; i++){
            char c = pw.charAt(i);
            if(c == '+'||c == '='||c == '-'||c == '/'||c == '*' || c == '￦'){
                temp += "%"+String.valueOf(c);
            }
            temp += String.valueOf(c);
        }
        return temp;
    }
}
