package com.test.second.controller;

import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.second.*;
import com.test.second.dao.Dao;
import com.test.second.object.CalendarObj;
import com.test.second.object.ScheduleAttr;
import com.test.second.parser.CollegePlan;
import com.test.second.parser.HolidayPlan;

@Controller
public class CalendarController {
	@Autowired
	private SqlSession sqlSession;
	
	private CalendarManage calendarCommand = new CalendarManage();
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(HttpServletRequest request, @RequestParam("new_title") String title, @RequestParam("start") String start, @RequestParam("end") String end) {
		HashMap<String, Object> map = (HashMap<String, Object>) request.getSession().getAttribute("login");
		title = title.replace("\"", "");
		start = start.replace("\"", "");
		end = end.replace("\"", "");
		//System.out.println((String)map.get("user_id") + ' ' + title + ' ' + start + ' ' + end);
		Dao dao = sqlSession.getMapper(Dao.class);
		dao.insert((String)map.get("user_id"), title, start, end);
		return "redirect:fullcalendar";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(HttpServletRequest request, @RequestParam("title") String title, @RequestParam("start") String start, @RequestParam("end") String end) {
		HashMap<String, Object> map = (HashMap<String, Object>) request.getSession().getAttribute("login");
		title = title.replace("\"", "");
		start = start.replace("\"", "");
		end = end.replace("\"", "");
		//System.out.println((String)map.get("user_id") + ' ' + title + ' ' + start + ' ' + end);
		Dao dao = sqlSession.getMapper(Dao.class);
		dao.delete((String)map.get("user_id"),  title, start, end);
		return "redirect:fullcalendar";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request, @RequestParam("title") String title, @RequestParam("cstart") String cstart, @RequestParam("cend") String cend, 
			@RequestParam("start") String start, @RequestParam("end") String end) {
		HashMap<String, Object> map = (HashMap<String, Object>) request.getSession().getAttribute("login");
		title = title.replace("\"", "");
		cstart = cstart.replace("\"", "");
		cend = cend.replace("\"", "");
		start = start.replace("\"", "");
		end = end.replace("\"", "");
		//System.out.println("update " + (String)map.get("user_id") + ' ' + title + ' ' + cstart + ' ' + cend + ' ' + start + ' ' + end);
		Dao dao = sqlSession.getMapper(Dao.class);
		dao.update((String)map.get("user_id"), title, cstart, cend, start, end);
		return "redirect:fullcalendar";
	}

	@RequestMapping(value = "/calendarholiday", method = RequestMethod.GET)
	@ResponseBody
	public List<CalendarObj> calendarholiday(@RequestParam(value="start",required=false,defaultValue="") String start,
			@RequestParam(value="end",required=false,defaultValue="") String end) {
		return calendarCommand.getHolidayEvent(start, end);
	}

	@RequestMapping(value = "/calendarcollege", method = RequestMethod.GET)
	@ResponseBody
	public List<CalendarObj> calendarcollege(@RequestParam(value="start",required=false,defaultValue="") String start,
			@RequestParam(value="end",required=false,defaultValue="") String end) {
		return calendarCommand.getCollegeEvent(start, end);
	}

	@RequestMapping(value = "/calendartimetable", method = RequestMethod.GET)
	@ResponseBody
	public List<CalendarObj> calendartimetable(HttpServletRequest request, @RequestParam(value="start",required=false,defaultValue="2016-05-01") String start,
			@RequestParam(value="end",required=false,defaultValue="2016-06-04") String end) {
		HashMap<String, Object> map = (HashMap<String, Object>) request.getSession().getAttribute("login");
		TimetableManage con = new TimetableManage();
		ArrayList<CalendarObj> calobj = con.getCalList((ArrayList<ScheduleAttr>) map.get("scheduleList"));
		return calendarCommand.getTimetableEvent(start, end, calobj);
	}	

	@RequestMapping(value = "/calendarusertime", method = RequestMethod.GET)
	@ResponseBody
	public List<CalendarObj> calendarusertime(HttpServletRequest request) {
		HashMap<String, Object> map = (HashMap<String, Object>) request.getSession().getAttribute("login");
		ArrayList<CalendarObj> calList;

		Dao dao = sqlSession.getMapper(Dao.class);
		calList = dao.select((String)map.get("user_id"));

		/*for(CalendarObj list : calList){
			System.out.println(list);
		}*/
		return calList;
	}
	
	@RequestMapping("/getallevent")
	@ResponseBody
	public List<CalendarObj> getallevent(HttpServletRequest request) {
		/*---calculate current month's first and last day---*/
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date lastDayOfMonth = cal.getTime();

        String start = new SimpleDateFormat("yyyy-MM").format(lastDayOfMonth) + "-01";
        String end = new SimpleDateFormat("yyyy-MM-dd").format(lastDayOfMonth);
        
		ArrayList<CalendarObj> calList = new ArrayList<CalendarObj>();
		calList.addAll(calendarCommand.getHolidayEvent(start, end));
		calList.addAll(calendarCommand.getCollegeEvent(start, end));
		HashMap<String, Object> map = (HashMap<String, Object>) request.getSession().getAttribute("login");
		Dao dao = sqlSession.getMapper(Dao.class);
		calList.addAll(dao.select((String)map.get("user_id")));
		return calList;		
	}
}
