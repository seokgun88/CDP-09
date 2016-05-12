package com.test.second.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.second.dao.Dao;
import com.test.second.object.CalendarObj;
import com.test.second.object.ClassroomScheduleObj;
import com.test.second.parser.LecturePlan;

@Controller
public class ClassTimetableController {
	@Autowired
	private SqlSession sqlSession;
	
	boolean isClassRoomParse = true;

	//강의실별 시간표 파싱
	@RequestMapping("/classRoomParse")
	public String classRoomParse(Model model) {
		if(isClassRoomParse){
			isClassRoomParse = false;
			System.out.println("**************************");
			LecturePlan lecturePlan = new LecturePlan();
			int cnt = 0;
			ArrayList<ClassroomScheduleObj> classScheduleList = lecturePlan.TotalParse();
			int scheduleLen = classScheduleList.size();
			Dao dao = sqlSession.getMapper(Dao.class);
			for(int i=0; i<scheduleLen; i++){
				dao.class_insert(classScheduleList.get(i).getBuilding(), classScheduleList.get(i).getRoom(), classScheduleList.get(i).getDay(), 
						classScheduleList.get(i).getStart(), classScheduleList.get(i).getEnd(), classScheduleList.get(i).getSchedule());	
				cnt++;
				System.out.println(cnt + " / " + scheduleLen);
			}
		}
		isClassRoomParse = true;
		return "redirect:fullcalendar";
	}
	
	//특정 강의실 시간표 DB에서 가져오기
	@RequestMapping("/getClassTimetable")
	public String getClassTimetable(Model model, HttpServletRequest request){
		Dao dao = sqlSession.getMapper(Dao.class);
		String[][] timetable = new String [28][7];
		ArrayList<ClassroomScheduleObj> classScheduleList = dao.class_select("공대9호관", "417");
		for(ClassroomScheduleObj classObj : classScheduleList){
			//System.out.println(classObj.toString());
			int i=classObj.getStart();
			while(i<=classObj.getEnd()){
				timetable[getTime(i)][classObj.getDay()] = classObj.getSchedule();
				if(i%100 ==0)
					i += 30;
				else
					i += 70;
			}
		}
		for (String[] row: timetable) {
		    System.out.println((Arrays.toString(row)));
		}
		return "redirect:fullcalendar";
	}
	
	public int getTime(int time){
		if(time%100 == 0){
			return time/100 * 2;
		}
		else{
			return time/100 * 2 + 1;			
		}
	}
}
