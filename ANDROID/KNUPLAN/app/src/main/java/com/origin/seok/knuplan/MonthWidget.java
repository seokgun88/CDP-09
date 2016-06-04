package com.origin.seok.knuplan;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Implementation of App Widget functionality.
 */
public class MonthWidget extends AppWidgetProvider {
    private static SharedPreferences pref; //to save login data
    private static Calendar mCal;
    private static PasswordCoder pwdCoder;
    private static int curMonth;
    private static int curDay;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    //update widget func
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        pref = context.getSharedPreferences("pref", 0);
        pwdCoder = new PasswordCoder();

        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);

        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);
        curMonth = Integer.valueOf(curMonthFormat.format(date));
        curDay = Integer.valueOf(curDayFormat.format(date));

        /*---get all event from JSON---*/
        JsonThread jsonThread = new JsonThread();
        jsonThread.start();
        try {
            jsonThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<DayEventData> eventArray = jsonThread.getEventArray();
        /*-----------------------------*/

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.month_widget);

        //initialize widget view and add month head
        views.removeAllViews(R.id.month_layout);

        //add month head
        views.setTextViewText(R.id.tv_date_month, curYearFormat.format(date) + "년 "
                + curMonthFormat.format(date) + "월");

        //add week head
        RemoteViews weekViews = new RemoteViews(context.getPackageName(), R.layout.weekhead_month);
        views.addView(R.id.month_layout, weekViews);

        //add all day in month
        weekViews = new RemoteViews(context.getPackageName(), R.layout.weekitem_month);
        Calendar mCal = Calendar.getInstance();

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)),
                Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        int spaceNum = mCal.get(Calendar.DAY_OF_WEEK) - 1;

        //1일 - 요일 매칭 시키기 위해 공백 add 후 첫주 완성
        int day = 0;
        int row;
        boolean addSpaceComplete = false;
        /*---int to id---*/
        int dayLayoutArray [] ={R.id.sat_layout, R.id.sun_layout, R.id.mon_layout,
                R.id.tue_layout, R.id.wed_layout, R.id.thu_layout, R.id.fri_layout};
        int dayArray [] = {R.id.tv_sat, R.id.tv_sun, R.id.tv_mon, R.id.tv_tue,
                R.id.tv_wed, R.id.tv_thu, R.id.tv_fri};
        int dayEventArray [] = {R.id.sat_event_tv, R.id.sun_event_tv, R.id.mon_event_tv,
                R.id.tue_event_tv, R.id.wed_event_tv, R.id.thu_event_tv, R.id.fri_event_tv};
        /*----------------*/
        //insert every day and event in curMonth
        while(day < mCal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            String data;
            day++;
            if(day <= spaceNum && !addSpaceComplete) {
                data = "";
                row = day % 7;
            }
            else{
                if(!addSpaceComplete) {
                    addSpaceComplete = true;
                    day = 1;
                }
                data = day + "";
                row = (spaceNum + day) % 7;
            }
            if(day == curDay)
                weekViews.setInt(dayLayoutArray[row], "setBackgroundColor", Color.parseColor("#66b0b0b0"));
            weekViews.setTextViewText(dayArray[row], data);
            weekViews.setTextViewText(dayEventArray[row], getDayEvent(eventArray, day));
            if (row % 7 == 0 || day == mCal.getActualMaximum(Calendar.DAY_OF_MONTH)){
                views.addView(R.id.month_layout, weekViews);
                weekViews = new RemoteViews(context.getPackageName(), R.layout.weekitem_month);
            }
        }

        mCal.set(Calendar.MONTH, mCal.get(Calendar.MONTH) + 1);

        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pe = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.monthWidget, pe);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
    //get Json data in url
    public static ArrayList<DayEventData> getJSON(){
        StringBuilder result = new StringBuilder();
        String id = pref.getString("id", "");
        String pwd = pwdCoder.decodePassword(pref.getString("pwd", ""));
        ArrayList<DayEventData> eventArray = new ArrayList<>();

        //login information false
        if(id.equals("") || pwd.equals(""))
            return new ArrayList<>();

        try {
            //login using id and password
            URL url = new URL("http://knuplan.kert.or.kr/login");
            URLConnection con = url.openConnection();
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            DataOutputStream dos = new DataOutputStream(con.getOutputStream());
            dos.writeBytes("id=" + id + "&pwd=" + pwd);
            dos.flush();

            con.connect();
            Log.d("myLog", "Connected URL: " + con.getURL());
            InputStream is = con.getInputStream();
            Log.d("myLog", "Redirected URL: " + con.getURL());
            //get jseesion Id
            String jsessionid = con.getURL().toString().split("=")[1];
            is.close();

            //get allevent Json data
            con = new URL("http://knuplan.kert.or.kr/getallevent"
                    + ";jsessionid=" + jsessionid).openConnection();
            con.connect();
            Log.d("myLog", "Connected URL: " + con.getURL());

            //String to JSON
            is = new BufferedInputStream(con.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while((line = reader.readLine()) != null){
                result.append(line + "\n");
            }
            JSONTokener tokener = new JSONTokener(result.toString());
            JSONArray finalResult = new JSONArray(tokener);
            for(int i=0; i<finalResult.length(); i++){
                JSONObject event = finalResult.getJSONObject(i);
                String title = event.getString("title");
                String start = event.getString("start");
                String end = event.getString("end");
                int startDay = Integer.valueOf(start.substring(8, 10));
                int startMonth = Integer.valueOf(start.substring(5, 7));
                int endDay = 0;
                int endMonth = 0;
                if(!end.equals("")) {
                    endDay = Integer.valueOf(end.substring(8, 10));
                    if(end.length() == 10)
                        endDay -= 1;
                    endMonth = Integer.valueOf(end.substring(5, 7));
                }
                if(startMonth == curMonth && (endDay == 0 || endMonth == curMonth)) {
                    if (endDay == 0
                            || (start.length() == end.length() && startDay == endDay)) {
                        boolean alreadyDay = false;
                        for (DayEventData ed : eventArray) {
                            if (ed.getDay() == startDay) {
                                ed.addEvnet(title);
                                alreadyDay = true;
                                break;
                            }
                        }
                        if (!alreadyDay) {
                            DayEventData eventData = new DayEventData();
                            eventData.setDay(startDay);
                            eventData.setEvent(title);
                            eventArray.add(eventData);
                        }
                    } else {
                        for(int day = startDay; day <= endDay; day++){
                            boolean alreadyDay = false;
                            for (DayEventData ed : eventArray) {
                                if (ed.getDay() == day) {
                                    ed.addEvnet(title);
                                    alreadyDay = true;
                                    break;
                                }
                            }
                            if (!alreadyDay) {
                                DayEventData eventData = new DayEventData();
                                eventData.setDay(day);
                                eventData.setEvent(title);
                                eventArray.add(eventData);
                            }
                        }
                    }
                }
            }
            Log.d("myLog", "Redirected URL: " + con.getURL());
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return eventArray;
    }
    //get specific day event
    public static String getDayEvent(ArrayList<DayEventData> eventArray, int day){
        for (DayEventData ed : eventArray) {
            //Log.d("myLog2", ed.getEvent() + ed.getDay());
            if (ed.getDay() == day) {
                return ed.getEvent();
            }
        }
        return "";
    }
    //get Json from url in thread
    public static class JsonThread extends Thread{
        ArrayList<DayEventData> eventArray;
        public void run(){
            eventArray = getJSON();
        }
        public ArrayList<DayEventData> getEventArray(){
            return eventArray;
        }
    }
}