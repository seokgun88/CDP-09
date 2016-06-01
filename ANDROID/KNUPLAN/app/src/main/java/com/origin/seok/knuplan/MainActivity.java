package com.origin.seok.knuplan;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webview;
    private SharedPreferences pref; //to save login data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getSharedPreferences("pref" ,MODE_PRIVATE);
        String id = pref.getString("id", "");
        String pwd = decodePassword(pref.getString("pwd", ""));

        //if don't have a login preference
        if(id.equals("") || pwd.equals("")) {
            setContentView(R.layout.activity_login);

            Button loginBtn = (Button) findViewById(R.id.loginBtn);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText loginId = (EditText) findViewById(R.id.loginId);
                    EditText loginPwd = (EditText) findViewById(R.id.loginPwd);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("id", loginId.getText().toString());
                    editor.putString("pwd", encodePassword(loginPwd.getText().toString()));
                    editor.commit();
                    setContentView(R.layout.activity_main);
                    login(pref.getString("id", ""), decodePassword(pref.getString("pwd", "")));
                }
            });
        }
        //if remember login preference
        else {
            setContentView(R.layout.activity_main);
            login(id, pwd);
        }
    }

    //back button setting
    @Override
     public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if(webview != null) {
                        if (webview.canGoBack()) {
                            webview.goBack();
                        } else {
                            finish();
                        }
                    }
                    else{
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void login(String id, String pwd){
        webview = (WebView) findViewById(R.id.webview); //웹뷰 생성
        webview.getSettings().setJavaScriptEnabled(true); //자바 스크립트 enable
        webview.setWebViewClient(new WebViewClient() { //웹뷰 클라이언트(주소창 없애기 위해)

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        String url = "http://knuplan.kert.or.kr/login";
        String postData = "id=" + id + "&pwd=" + pwd;
        webview.postUrl(url, postData.getBytes()); //주소입력

        //check url when page is loaded
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //login fail or logout or another page
                if (url.equals("http://knuplan.kert.or.kr/home?success=false")) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.remove("id");
                    editor.remove("pwd");
                    editor.commit();
                    recreate();
                    Toast.makeText(getApplicationContext(), "아이디, 비밀번호를 틀렸네요", Toast.LENGTH_SHORT).show();
                    return true;
                } else if(url.equals("http://knuplan.kert.or.kr/home")){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.remove("id");
                    editor.remove("pwd");
                    editor.commit();
                    recreate();
                    Toast.makeText(getApplicationContext(), "로그아웃 했어요", Toast.LENGTH_SHORT).show();
                    return true;
                } else{
                    view.loadUrl(url);
                    return true;
                }
            }
        });
    }

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
