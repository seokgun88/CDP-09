package com.test.second;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.test.second.object.ClassroomScheduleObj;
import com.test.second.object.ScheduleAttr;

//강의실 시간표 관련 클래스
public class ClassTimetableManage {
	//db에서 받아온 해당 강의실 데이터를 이용하여 29행 7열로 출력
	private ArrayList<ScheduleAttr> ScheduleList;
	
	public ArrayList<ScheduleAttr> printTimetable(ArrayList<ClassroomScheduleObj> classScheduleList){
		
		ScheduleList = new ArrayList<ScheduleAttr>();
		
		String[][] timetable = new String [29][7];
		
		for(ClassroomScheduleObj classObj : classScheduleList){
			int i=classObj.getStart();
			while(i<=classObj.getEnd()){
				timetable[getTime(i)][classObj.getDay()] = classObj.getSchedule();
				if(i%100 ==0)
					i += 30;
				else
					i += 70;
			}
		}
		int cnt = 0;
		String time = "";
		
		for (String[] row: timetable) {
			if( cnt%2 == 0){
				time = cnt/2+"A";
			}else{
				time = cnt/2+"B";
			}
			
//		    System.out.println((Arrays.toString(row)));
//		    ScheduleAttr schobj = new ScheduleAttr(time, row[0], row[1], row[2], row[3], row[4], row[5], row[6]);
		    ScheduleAttr schobj = new ScheduleAttr(time, row[1], row[2],
		    		row[3], row[4], row[5], row[6], row[0]);
		    ScheduleList.add(schobj);
		    cnt++;
		}
		return ScheduleList;
	}
	//30분 단위로 새로운 행 생성을 위한 변환 함수
	public int getTime(int time){
		if(time%100 == 0){
			return ((time/100) - 8) * 2;
		}
		else{
			return ((time/100) - 8) * 2 + 1;
		}
	}
	//db에 넣는 형식인 ClassroomScheduleObj의 List를 생성
	public ArrayList<ClassroomScheduleObj> getClassScheduleObj(String place, String time, String subject_name, String professor, String url){
		ArrayList<ClassroomScheduleObj> classScheduleList = new ArrayList<ClassroomScheduleObj>();
		
		final String day_regex = "[ㄱ-ㅎㅏ-ㅣ가-힣]";
		final String time_regex = "(\\d{1,2})([AB])";
		Pattern p = Pattern.compile(time_regex);
		String[] splited_time = time.split(" ");
		String curDay = "";
		int curEnd = 0;
		String start = "";
		String end = "";
		String schedule_title = subject_name + " - " + professor;
		
		String[] splited_place = place.split("-");
		if(splited_place.length < 2)
			return null;
		if(splited_place.length > 2 && splited_place[1].split(" ").length > 1){
			if(splited_place.length-1 != splited_time.length){
				return null;
			}
			System.out.println(place);
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
				if(i == 0){
					classScheduleList.add(new ClassroomScheduleObj(
							splited_place[0], splited_place[1].split(" ")[0], getIntofDay(day), 
							Integer.parseInt(start), Integer.parseInt(end), schedule_title));
				}
				else{
					classScheduleList.add(new ClassroomScheduleObj(
							splited_place[i].split(" ")[1], splited_place[i+1].split(" ")[0], getIntofDay(day), 
							Integer.parseInt(start), Integer.parseInt(end), schedule_title));
				}
				
			}
		}
		else{
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
				if(curDay.equals(day) && 
						(Integer.parseInt(start) == curEnd+30) || 
						(Integer.parseInt(start) == curEnd+70)){
					classScheduleList.get(classScheduleList.size()-1).setEnd(Integer.parseInt(end));
					curEnd = Integer.parseInt(end);
				}
				else{
					classScheduleList.add(new ClassroomScheduleObj(splited_place[0], splited_place[1], getIntofDay(day), 
							Integer.parseInt(start), Integer.parseInt(end), schedule_title));
					curDay = day;
					curEnd = Integer.parseInt(end);
				}
			}
		}
		return classScheduleList;
	}
	//요일을 숫자로 변환
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
