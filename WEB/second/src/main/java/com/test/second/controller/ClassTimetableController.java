package com.test.second.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.second.ClassTimetable;
import com.test.second.dao.Dao;
import com.test.second.object.CalendarObj;
import com.test.second.object.ClassroomScheduleObj;
import com.test.second.parser.LecturePlan;

@Controller
public class ClassTimetableController {
	@Autowired
	private SqlSession sqlSession;
	
	boolean isClassRoomParse = true;

	//강의실별 시간표 파싱(특정 사용자만 접속 가능)
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
		ClassTimetable classTimetable = new ClassTimetable();
		classTimetable.printTimetable(dao.class_select("공대9호관", "418"));
		
		return "redirect:fullcalendar";
	}
	
	//현재 시간에 해당 건물의 강의실들 사용 여부 알아오기
	@RequestMapping("/getRoomUsalbe")
	public String getRoomUsalbe(Model model, HttpServletRequest request){
		Dao dao = sqlSession.getMapper(Dao.class);
		ClassTimetable classTimetable = new ClassTimetable();
		Calendar calendar = Calendar.getInstance();
        java.util.Date date = calendar.getTime();
        String time = (new SimpleDateFormat("HHmm").format(date));
        String day = (new SimpleDateFormat("E").format(date));
        System.out.println(Integer.parseInt(time) +"-"+ classTimetable.getIntofDay(day));
		for(ClassroomScheduleObj classObj: dao.building_select("공대9호관", 1, 130)){
			System.out.println(classObj.getRoom());
		}
		
		return "redirect:fullcalendar";
	}
	
	//해당 건물의 강의실들 전부 가져오기
	@RequestMapping("/getRoom")
	public String getRoom(Model model, HttpServletRequest request){
		Dao dao = sqlSession.getMapper(Dao.class);
		ClassTimetable classTimetable = new ClassTimetable();
		for(ClassroomScheduleObj classObj: dao.room_select("공대9호관")){
			System.out.println(classObj.getRoom());
		}
		
		return "redirect:fullcalendar";
	}
}