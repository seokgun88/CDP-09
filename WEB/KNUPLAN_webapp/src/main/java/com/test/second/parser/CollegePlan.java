package com.test.second.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.second.object.CalendarObj;
import com.test.second.object.CollegeObj;

public class CollegePlan {
	ArrayList<CollegeObj> CollegeList;
	ArrayList<String> CollegeStringList;
	ArrayList<CalendarObj> calList;
	
	public ArrayList<CollegeObj> getCollegeList() {
		return CollegeList;
	}
	
	public ArrayList<CalendarObj> getCalList() {		
		
		for(CollegeObj e: CollegeList){
			CalendarObj tempvalue2 = new CalendarObj();
			String tempvalue1 = "";
			tempvalue1 = e.getDate();
			tempvalue2.setStart(tempvalue1);
			tempvalue1 = e.getName();
			tempvalue2.setTitle(tempvalue1);
			calList.add(tempvalue2);			
		}
		return calList;
	}
	
	public ArrayList<String> getCollegeStringList(){
		return CollegeStringList;
	}
	
	public CollegePlan(int year, int month){
		CollegeList = new ArrayList<CollegeObj>();
		CollegeStringList = new ArrayList<String>();
		calList = new ArrayList<CalendarObj>();
		
		ParseStart(year, month);
	}
	
	public void ParseStart(int year, int month){
		String strmonth = String.format("%02d", month);
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
			if(cmonth.equals(strmonth) == false) continue;
			String cday = e.text().substring(3, 5);
			String cname = e.text().substring(8);
			
			CollegeObj colobj = new CollegeObj();
			
			colobj.setDate(Integer.toString(year)+"-"+cmonth+"-"+cday);
			colobj.setName(cname);
			
			CollegeList.add(colobj);			
			CollegeStringList.add(colobj.toString());
			
			//System.out.println(colobj.toString());
		}
		
	}

}
