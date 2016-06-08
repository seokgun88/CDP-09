package com.test.second.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.second.object.ScheduleAttr;

public class UserSchedulePlan {
	String StrCookie = "";
	
	private ArrayList<ScheduleAttr> ScheduleList;
	
	public ArrayList<ScheduleAttr> getScheduleList() {
		return ScheduleList;
	}

	public UserSchedulePlan() {
		ScheduleList = new ArrayList<ScheduleAttr>();
		
	}
	
	public boolean StartRequest(String id, String pw){
		
		
		// +랑 =등 기호 핸들링
		String temp = "";
		for(int i=0;i<pw.length() ; i++){
			char c = pw.charAt(i);
			if(c == '+'||c == '='||c == '-'||c == '/'||c == '*'||c == '\''){
				temp += "%"+String.valueOf(c);				
			}
			temp += String.valueOf(c);
		}
		
		pw = temp;
		
		if(YesLoginPOSTRequest(id, pw) == false){
			System.out.println("로그인 실패!!");
			return false;
		}
		else{
			System.out.println("로그인 성공!!");
			YesScheduleGETRequest();
			return true;
		}		
	}
	
	public void ParseStart(String StrHtml){
		
		Document doc = Jsoup.parse(StrHtml);		
		ScheduleAttr attrobj;
		
		Elements titles = doc.select("table.bbslist tbody tr td.schedule");
		
		String []tempstrlist = new String[8];
//		String []daylist = { "시간", "월", "화","수","목","금","토","일" };
		int cnt = 0;
		for(int i=0;i< titles.size() ; i++){
			Element e = titles.get(i);			
			
			tempstrlist[cnt] = e.text();
			
			if(tempstrlist[cnt]==""){
				tempstrlist[cnt] = "NULL";
			}
			
			cnt += 1;			
			
			if(cnt%8 == 0){
				
//				for(int j = 0; j < 8;j++){
//					System.out.println(daylist[j] + tempstrlist[j]);					
//				}
				attrobj = new ScheduleAttr(
						tempstrlist[0], tempstrlist[1], tempstrlist[2], tempstrlist[3],
						tempstrlist[4], tempstrlist[5], tempstrlist[6], tempstrlist[7]);
				
				ScheduleList.add(attrobj);
				

				
				cnt = 0;
			}
		}	
		
//		System.out.println(ScheduleList.size());		
		
	}

	@SuppressWarnings("finally")
	public boolean YesLoginPOSTRequest(String user_id, String user_pw){

		URL url = null;
		String urlstr = "http://lms.knu.ac.kr/ilos/lo/login.acl";
		boolean IsEnd = false;
		
		try {
			url = new URL(urlstr);
			URLConnection con = url.openConnection();

			// POST request임을 설정
			con.setDoOutput(true);

			// 헤더 정보 설정
			con.setRequestProperty("Accept-Language",  "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
			con.setRequestProperty("Cookie", "_language_=ko; WMONID=NFhakRD7cNz; "
					+ "PHPSESSID=b1b67aa7164e5db765f63df01faa587e; "
					+ "ncook_20160418173542=done; "
					+ "SESSION_NEWLMS=VvLxXbnNdYRl3M7bxDnnqJwYh5J1031xg9B2Gpq5JTBzs6y5M1x2!-1103523962");


			//usr_id=123&usr_pwd=123&encoding=utf-8
			String parameter = URLEncoder.encode("usr_id", "UTF-8") + "=" + URLEncoder.encode(user_id, "UTF-8");
			parameter += "&" + URLEncoder.encode("usr_pwd", "UTF-8") + "=" + URLEncoder.encode(user_pw, "UTF-8");
			parameter += "&" + URLEncoder.encode("encoding", "UTF-8") + "=" + URLEncoder.encode("utf-8", "UTF-8");
						
			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(parameter);
			wr.flush();  // 꼭 flush를 호출해야 한다.			

			// 응답 처리
			BufferedReader rd = null;

			rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
//			String line = null;
//			while ((line = rd.readLine()) != null) {
//				// 로직 처리
//				System.out.println(line);
//			}
			//Cookie: _language_=ko; WMONID=DfEd6GBGwgI; SESSION_NEWLMS=g1lGXcjXCgqsL9Tj87f0HX2QvClRw2tY8T5yyQH62KhGfl3znWKS!-1103523962
			StrCookie = con.getHeaderField("Set-Cookie");
//			System.out.println("Cookie : " + StrCookie );
			wr.close();
			rd.close();
			
			if(StrCookie == null){
				IsEnd = false;
			}
			else{
				IsEnd = true;
			}
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block			
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally{
			if(IsEnd == true){				
				return true;
			}
			else{				
				return false;
			}
		}
	}



	public void YesScheduleGETRequest(){		

		URL url = null;
		String urlstr = "http://lms.knu.ac.kr/ilos/st/main/pop_academic_timetable_form.acl";
		try {

			url = new URL(urlstr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// GET request임을 설정
			con.setRequestMethod("GET");

			// 헤더 정보 설정
			con.setRequestProperty("Accept-Language",  "ko-KR,ko;q=0.8,en-US;q=0.6,en;q=0.4");
			con.setRequestProperty("Cookie", "_language_=ko; WMONID=NFhakRD7cNz; "
					+ "PHPSESSID=b1b67aa7164e5db765f63df01faa587e; "
					+ "ncook_20160418173542=done; "
					+ StrCookie);

//			int responseCode = con.getResponseCode();
//			System.out.println("\nSending 'GET' request to URL : " + url);
//			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream(),"UTF-8") );
			String inputLine;
			
			String StrHtml = "";
			while ((inputLine = in.readLine()) != null) {
//				System.out.println(inputLine);				
				StrHtml += inputLine;
			}
			ParseStart(StrHtml);
			in.close();

			//print result
			//System.out.println(response.toString());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		


	}


}
