package com.test.second.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;

import org.apache.ibatis.session.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.*;

import com.test.second.dao.*;
import com.test.second.object.*;

public class LecturePlan {
	
	private ArrayList<LectureObj> LectureList;
	private ArrayList<ClassroomScheduleObj> classScheduleList;

	public ArrayList<LectureObj> getLectureList() {
		return LectureList;
	}

	public LecturePlan(){
		LectureList = new ArrayList<LectureObj>();
	}
	
	public ArrayList<ClassroomScheduleObj> TotalParse(){
	    System.out.println("total pare start");
	    classScheduleList = new ArrayList<ClassroomScheduleObj>();
		SectionParse(1);
		SectionParse(2);
		for(int i=4;i<=57;i++){
			SectionParse(i);
		}
		return classScheduleList;
	}
	
	// input : [1,57]
	// output : true 완료 , false 실패
	public boolean SectionParse(int n){
		
		if(1<=n&&n<=3){
			if(ParseEtc(n) == false){
				return false;
			}
		}
		else if(4<=n&&n<=57){
			if(ParseStart(n) == false){
				return false;
			}
		}
		
		return true;
	}

	// input : 스마트러닝 1, 원어 2, 계절 3,
	// outpu : tru 성공 , false 실패
	public boolean ParseEtc(int n){
		if( 1 > n && n > 3 ){			
			return false;
		}
		int offset = 1;

		// [*] td strong a[href]					
		Document doc = null;
		try {
			String url = "http://knu.ac.kr/wbbs/wbbs/contents/"
					+ "index.action?menu_url=curriculum2/index&noDeco=true";
			Connection con = Jsoup.connect(url);
			con.timeout(1000 * 60);
			doc = con.get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Elements refs = doc.select("tbody tbody tbody td strong a[href]");
		
		// 원래 [0,2] 구간 순환문 가능
		int start = n - offset;
		int end = n - offset;
		
		// 인덱스 3 이상의 나머지 4개  ; 검색, 강의계획서, 건물 및 교과구분코드, 수업시간
		for(int i=start;i<=end;i++){
			Element e = refs.get(i);

			String tempurl = e.attr("abs:href");
//			String tempname = e.text();
//			System.out.println(tempurl +"\n" + tempname + "\n");

			Document subcolldoc = null;
			try {						
				Connection con3 = Jsoup.connect(tempurl);
				con3.timeout(1000 * 60);
				subcolldoc = con3.get();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// 교양 강의 계획서 파싱
			Elements tables = subcolldoc.select("table.courTable tbody tr td");
			String []tempvalue = new String[13];
			int subcnt = 0;
			
			Elements lechrefs = subcolldoc.select("table.courTable tbody tr td a[href]");
			String temphref = "NULL";
			int hrefcnt = 0;

			//교양 인덱스 : 2 , 3 , 7 , 8 , 9			
			for(Element ele: tables){			
				tempvalue[subcnt] = ele.text();
								
				subcnt+=1;

				//System.out.println( cnt +"td: " + tempvalue[cnt-1]);

				if( subcnt%13 == 0){
					//System.out.println("----------------");
					LectureObj Aobj = new LectureObj(i + offset,"NULL",tempvalue[2],tempvalue[3],
							tempvalue[7],tempvalue[8],tempvalue[9]);
					
					Element lechref = lechrefs.get(hrefcnt);
					temphref = lechref.attr("abs:href");					
					Aobj.setLectureExplainUrl(temphref);
					
					LectureList.add(Aobj);

					//System.out.println(Aobj.toString());
					ArrayList<ClassroomScheduleObj> classObjList = 
							getClassScheduleObj(Aobj.getPlace(), Aobj.getTime(), Aobj.getSubject_name(), Aobj.getProfessor(), Aobj.getLectureExplainUrl());
					if(classObjList != null)
						for(ClassroomScheduleObj classObj : classObjList){
							classScheduleList.add(classObj);
						}
					
					subcnt = 0;
					hrefcnt+=1;
				}
			}
		}

		return true;		
	}

	// input : 4부터 57까지 가능
	// output : false : 실패,  true : 성공
	public boolean ParseStart(int n){
		if( 4 > n && n > 57 ){
			return false;
		}	
		int offset = 4;

		Document doc = null;
		try {
			String url = "http://knu.ac.kr/wbbs/wbbs/contents/"
					+ "index.action?menu_url=curriculum2/index&noDeco=true";
			Connection con = Jsoup.connect(url);
			con.timeout(1000 * 60);
			doc = con.get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// 대학과 대학원
		//		Elements titles = doc.select("tbody tbody tbody span.style2");
		Elements refs = doc.select("tbody tbody tbody span.style2 a[href]");

		int start = n - offset;
		int end = n - offset;
		//원래 [0, refs.size()) 구간 까지 순환문 가능하다.
		for(int i=start;i<=end;i++){
			Element e = refs.get(i);
			String ref = e.attr("abs:href");
//			System.out.println("refs: " + e.attr("abs:href"));
//			System.out.println("name: " + e.text());
			
			if(i<13){
				// 교양 페이지의 넘버링				
				Document subdoc = null;
				try {					
					Connection con1 = Jsoup.connect(ref);
					con1.timeout(1000 * 60);
					subdoc = con1.get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// 교양 강의 계획서 파싱
				Elements tds = subdoc.select("table.courTable tbody tr td");
				String []tempvalue = new String[14];
				
				Elements lechrefs = subdoc.select("table.courTable tbody tr td a[href]");
				String temphref = "NULL";
				int hrefcnt = 0;
								
				int cnt = 0;
				//교양 인덱스 : 2 , 3 , 4 , 8 , 9 , 10			
				for(Element ele: tds){			
					tempvalue[cnt] = ele.text();
					
					cnt+=1;					

					//System.out.println( cnt +"td: " + tempvalue[cnt-1]);

					if( cnt%14 == 0){
						//System.out.println("----------------");
						LectureObj Aobj = new LectureObj(i + offset,tempvalue[2],tempvalue[3],tempvalue[4],
								tempvalue[8],tempvalue[9],tempvalue[10]);
						
						Element lechref = lechrefs.get(hrefcnt);
						temphref = lechref.attr("abs:href");						
						Aobj.setLectureExplainUrl(temphref);
						
						LectureList.add(Aobj);

						//System.out.println(Aobj.toString());
						ArrayList<ClassroomScheduleObj> classObjList = 
								getClassScheduleObj(Aobj.getPlace(), Aobj.getTime(), Aobj.getSubject_name(), Aobj.getProfessor(), Aobj.getLectureExplainUrl());
						if(classObjList != null)
							for(ClassroomScheduleObj classObj : classObjList){
								classScheduleList.add(classObj);
							}
						
						cnt = 0;
					}
				}
			}
			else{
				Document subdoc = null;
				try {					
					Connection con2 = Jsoup.connect(ref);
					con2.timeout(1000 * 60);
					subdoc = con2.get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				// college
				Elements collrefs = subdoc.select("a[href]");

				for(Element collref : collrefs){
					String collsuburl = collref.attr("abs:href");
					String collname = collref.text();
//					System.out.println("collref : " + collsuburl);
//					System.out.println("collname : " + collname);
					
					// 교양이외 대학 페이지의 넘버링				
					Document subcolldoc = null;
					try {						
						Connection con3 = Jsoup.connect(collsuburl);
						con3.timeout(1000 * 60);
						subcolldoc = con3.get();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// 교양 강의 계획서 파싱
					Elements tables = subcolldoc.select("table.courTable tbody tr td");
					String []tempvalue = new String[13];
					int subcnt = 0;
					
					Elements lechrefs = subcolldoc.select("table.courTable tbody tr td a[href]");
					String temphref = "NULL";
					int hrefcnt = 0;

					//교양 인덱스 : 2 , 3 , 7 , 8 , 9			
					for(Element ele: tables){
						
						tempvalue[subcnt] = ele.text();
						subcnt+=1;

						//System.out.println( cnt +"td: " + tempvalue[cnt-1]);

						if( subcnt%13 == 0){
							//System.out.println("----------------");
							LectureObj Aobj = new LectureObj( i + offset,collname,tempvalue[2],tempvalue[3],
									tempvalue[7],tempvalue[8],tempvalue[9]);
							Element lechref = lechrefs.get(hrefcnt);
							temphref = lechref.attr("abs:href");							
							Aobj.setLectureExplainUrl(temphref);
							LectureList.add(Aobj);

							//System.out.println(Aobj.toString());
							ArrayList<ClassroomScheduleObj> classObjList = 
									getClassScheduleObj(Aobj.getPlace(), Aobj.getTime(), Aobj.getSubject_name(), Aobj.getProfessor(), Aobj.getLectureExplainUrl());
							if(classObjList != null)
								for(ClassroomScheduleObj classObj : classObjList){
									classScheduleList.add(classObj);
								}
							
							subcnt = 0;
						}
					}


				}				

			}

		}
		
		return true;

	}
	
	public ArrayList<ClassroomScheduleObj> getClassScheduleObj(String place, String time, String subject_name, String professor, String url){
		ArrayList<ClassroomScheduleObj> classScheduleList = new ArrayList<ClassroomScheduleObj>();
		String[] splited_place = place.split("-");
		if(splited_place.length < 2)
			return null;
		final String day_regex = "[ㄱ-ㅎㅏ-ㅣ가-힣]";
		final String time_regex = "(\\d{1,2})([AB])";
		Pattern p = Pattern.compile(time_regex);
		String[] splited_time = time.split(" ");
		String curDay = "";
		String start = "";
		String end = "";
		String schedule_title = subject_name + " - " + professor;
		for(int i=0; i<splited_time.length; i++){
			String day = splited_time[i].substring(0, 1);
			String[] splited2_time = splited_time[i].substring(1, splited_time[i].length()).split(",");
			int lastTime = splited2_time.length - 1;
			
			if(splited2_time[0].equals("00"))
				start = "000";
			else{
				Matcher m = p.matcher(splited2_time[0]);
				m.matches();
				if(m.group(2).equals("A"))
					start = m.group(1) + "00";
				else if(m.group(2).equals("B"))
					start = m.group(1) + "30";
				else
					start = m.group(1) + "00";
			}
			if(splited2_time[lastTime].equals("00"))
				end = "000";
			else{
				Matcher m = p.matcher(splited2_time[lastTime]);
				m.matches();
				if(m.group(2).equals("A"))
					end = m.group(1) + "00";
				else if(m.group(2).equals("B"))
					end = m.group(1) + "30";
				else
					end = m.group(1) + "00";
			}
			if(curDay.equals(day)){
				classScheduleList.get(classScheduleList.size()-1).setEnd(Integer.parseInt(end));
			}
			else{
				classScheduleList.add(new ClassroomScheduleObj(splited_place[0], splited_place[1], getIntofDay(day), 
						Integer.parseInt(start), Integer.parseInt(end), schedule_title));
				curDay = day;
			}
		}
		return classScheduleList;
	}
	
	public int getIntofDay(String day){
		if(day.equals("월")){
			return 1;
		}
		else if(day.equals("화")){
			return 2;
		}
		else if(day.equals("수")){
			return 3;
		}
		else if(day.equals("목")){
			return 4;
		}
		else if(day.equals("금")){
			return 5;
		}
		else if(day.equals("토")){
			return 6;
		}
		else if(day.equals("일")){
			return 7;
		}
		return 0;		
	}
}
