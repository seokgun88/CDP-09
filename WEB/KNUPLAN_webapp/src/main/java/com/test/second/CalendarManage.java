package com.test.second;

import java.util.*;

import com.test.second.object.*;
import com.test.second.parser.*;

public class CalendarManage {
	public ArrayList<CalendarObj> getHolidayEvent(String start, String end){
		int styear,stmonth,edyear,edmonth;		
		HolidayPlan obj;
		ArrayList<CalendarObj> calList;

		if(start.equals("") == false){
			styear = Integer.parseInt(start.substring(0,4));
			stmonth = Integer.parseInt(start.substring(5,7));
			edyear = Integer.parseInt(start.substring(0,4));
			edmonth = Integer.parseInt(end.substring(5,7));

			obj = new HolidayPlan(styear,stmonth);
			calList = obj.getCalList();

			if(stmonth != edmonth){
				ArrayList<CalendarObj> tempcalList;
				for(int n=0;n<2;n++){
					if(stmonth == edmonth-n){
						continue;
					}
					obj = new HolidayPlan(edyear,edmonth-n);
					tempcalList = obj.getCalList();
					for(CalendarObj e : tempcalList){
						calList.add(e);
					}
				}
			}
		}
		else{
			obj = new HolidayPlan(2016,5);
			calList = obj.getCalList();
		}
		return calList;
	}
	
	public ArrayList<CalendarObj> getCollegeEvent(String start, String end){
		int styear,stmonth,edyear,edmonth;
		CollegePlan collobj;
		ArrayList<CalendarObj> calList;

		if(start.equals("") == false){
			styear = Integer.parseInt(start.substring(0,4));
			stmonth = Integer.parseInt(start.substring(5,7));
			edyear = Integer.parseInt(start.substring(0,4));
			edmonth = Integer.parseInt(end.substring(5,7));

			collobj = new CollegePlan(styear,stmonth);
			calList = collobj.getCalList();
			HolidayPlan obj = new HolidayPlan(styear,stmonth);
			ArrayList<CalendarObj> tempHoliList = obj.getCalList();
			// 공휴일 중복 삭제
			for(int i=0;i<tempHoliList.size();i++){
				for(int j=0;j<calList.size();j++){
					if(tempHoliList.get(i).getTitle().contains(calList.get(j).getTitle())){
						calList.remove(j);
						break;
					}
				}				
			}

			if(stmonth != edmonth){
				ArrayList<CalendarObj> tempcalList;
				for(int n=0;n<2;n++){
					if(stmonth == edmonth-n){
						continue;
					}

					collobj = new CollegePlan(edyear,edmonth-n);
					tempcalList = collobj.getCalList();
					obj = new HolidayPlan(edyear,edmonth-n);
					tempHoliList = obj.getCalList();

					// 공휴일 중복 삭제
					for(int i=0;i<tempHoliList.size();i++){
						for(int j=0;j<tempcalList.size();j++){
							if(tempHoliList.get(i).getTitle().contains(tempcalList.get(j).getTitle())){
								tempcalList.remove(j);
								break;
							}
						}				
					}
					for(CalendarObj e : tempcalList){
						calList.add(e);
					}
				}
			}			
		}
		else{
			collobj = new CollegePlan(2016,5);
			calList = collobj.getCalList();
		}
		return calList;
	}
	
	public ArrayList<CalendarObj> getTimetableEvent(String start, String end, ArrayList<CalendarObj> calobj){
		TimetableManage con = new TimetableManage();
		ArrayList<CalendarObj> resultcalobj = new ArrayList<CalendarObj>();

		String x = Integer.toString(con.getDateDay(start, "yyyy-MM-dd"));
		System.out.println(x);
		String startyear = start.substring(0,4);
		int startmonth = Integer.parseInt(start.substring(5,7));
		int startday = Integer.parseInt(start.substring(8,10));
		int endmonth = Integer.parseInt(end.substring(5,7));
		int endday = Integer.parseInt(end.substring(8,10));

		// start(2016-05-01) to end(2016-05-31)
		// ex) second/calendarcollege?start=2016-05-29&end=2016-07-10&_=1462640344776
		ArrayList<CalendarObj>[] days = new ArrayList[7];
		for(int i=0; i<7;i++){
			days[i] = new ArrayList<CalendarObj>();
		}

		CalendarObj objtemp;
		for(int i=0;i<calobj.size();i++){
			String date = calobj.get(i).getStart();
			String datearr[] = date.split("/");

			objtemp = new CalendarObj();

			objtemp.setStart(datearr[1]);
			objtemp.setTitle(calobj.get(i).getTitle());
			objtemp.setEnd(calobj.get(i).getEnd());
			days[Integer.parseInt(datearr[0])].add(objtemp);
		}

		int month = startmonth;
		if(month>= 3 && month <= 6 ){
			int y = con.getDateDay(startyear+"-"+month+"-"+startday, "yyyy-MM-dd");
			for(int j = startday ; j<=31 ; j++){
				if(month == 3 && j < 2){
					y+=1;
					continue;
				}
				String ymd = String.format("%s-%02d-%02dT", startyear,month,j);

				for(CalendarObj e :days[y%7]){
					CalendarObj tempobj = new CalendarObj();
					tempobj.setStart(ymd+e.getStart());
					tempobj.setTitle(e.getTitle());
					tempobj.setEnd(ymd+e.getEnd());
					resultcalobj.add(tempobj);
				}
				y+=1;				
				if(month == 6 && j >= 20){
					break;
				}
			}
		}

		if(startmonth != endmonth-1){
			month = endmonth-1;
			if(month>= 3 && month <=6){
				int y = con.getDateDay(startyear+"-"+month+"-"+"01", "yyyy-MM-dd");
				for(int j = 01 ; j<=31 ; j++){
					if(month == 3 && j < 2){
						y+=1;
						continue;
					}
					String ymd = String.format("%s-%02d-%02dT", startyear,month,j);

					for(CalendarObj e :days[y%7]){
						CalendarObj tempobj = new CalendarObj();
						tempobj.setStart(ymd+e.getStart());
						tempobj.setTitle(e.getTitle());
						tempobj.setEnd(ymd+e.getEnd());
						resultcalobj.add(tempobj);
					}
					y+=1;					
					if(month == 6 && j >= 20){
						break;
					}
				}	
			}
		}

		month = endmonth;
		if(month>= 3 && month <7){
			int y = con.getDateDay(startyear+"-"+month+"-"+"01", "yyyy-MM-dd");
			for(int j = 01 ; j<=endday ; j++){
				if(month == 3 && j < 2){
					y+=1;
					continue;
				}
				String ymd = String.format("%s-%02d-%02dT", startyear,month,j);

				for(CalendarObj e :days[y%7]){
					CalendarObj tempobj = new CalendarObj();
					tempobj.setStart(ymd+e.getStart());
					tempobj.setTitle(e.getTitle());
					tempobj.setEnd(ymd+e.getEnd());
					resultcalobj.add(tempobj);
				}				
				y+=1;
			}
		}
//		System.out.println("***********************************");
//		for(CalendarObj e : resultcalobj){
//			System.out.println(resultcalobj.toString());
//		}
		return resultcalobj;
	}
}
