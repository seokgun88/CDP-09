package com.origin.seok.knuplan;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    private WebView webview;
    private SharedPreferences pref; //to save login data
    private PasswordCoder pwdCoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = getSharedPreferences("pref" ,MODE_PRIVATE);
        pwdCoder = new PasswordCoder();
        String id = pref.getString("id", "");
        String pwd = pwdCoder.decodePassword(pref.getString("pwd", ""));

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
                    editor.putString("pwd", pwdCoder.encodePassword(loginPwd.getText().toString()));
                    editor.commit();
                    setContentView(R.layout.activity_main);
                    login(pref.getString("id", ""), pwdCoder.decodePassword(pref.getString("pwd", "")));
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
                            updateWidgets(getApplicationContext());
                            finish();
                        }
                    }
                    else{
                        updateWidgets(getApplicationContext());
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
        String postData = null;
        try {
            postData = "id=" + id + "&pwd=" + URLEncoder.encode(pwd, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
    public static void updateWidgets(Context context) {
        Intent intent = new Intent(context.getApplicationContext(), MonthWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        // Use an array and EXTRA_APPWIDGET_IDS instead of AppWidgetManager.EXTRA_APPWIDGET_ID,
        // since it seems the onUpdate() is only fired on that:
        AppWidgetManager widgetManager = AppWidgetManager.getInstance(context);
        int[] ids = widgetManager.getAppWidgetIds(new ComponentName(context, MonthWidget.class));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            widgetManager.notifyAppWidgetViewDataChanged(ids, android.R.id.list);

        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        context.sendBroadcast(intent);
    }
}
