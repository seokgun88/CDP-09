package com.test.second.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.second.object.CollegeObj;

public class CollegePlan {
	ArrayList<CollegeObj> CollegeList;
	ArrayList<String> CollegeStringList;
	
	public ArrayList<CollegeObj> getCollegeList() {
		return CollegeList;
	}
	
	public ArrayList<String> getCollegeStringList(){
		return CollegeStringList;
	}
	
	public CollegePlan(int year){
		CollegeList = new ArrayList<CollegeObj>();
		CollegeStringList = new ArrayList<String>();
		ParseStart(year);
	}
	
	public void ParseStart(int year){
		
		Document doc = null;
		try {
			doc = Jsoup.connect("http://knu.ac.kr/wbbs/wbbs/user/yearSchedule"
					+ "/index.action?menu_idx=43&schedule.search_year="+year).get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//Elements titles = doc.select("div#body_content div#calendar dl dd ul li span.day");
		Elements titles = doc.select("div#body_content div#calendar dl dd ul li");
		
		for(Element e: titles){
			String cmonth = e.text().substring(0, 2);
			String cDay = e.text().substring(3, 5);
			String cname = e.text().substring(8);
			CollegeObj colobj = new CollegeObj();
			
			colobj.setMonth(cmonth);
			colobj.setDay(cDay);
			colobj.setName(cname);
			
			CollegeList.add(colobj);
			CollegeStringList.add(colobj.toString());
			
			System.out.println(colobj.toString());
		}
		
	}

}
