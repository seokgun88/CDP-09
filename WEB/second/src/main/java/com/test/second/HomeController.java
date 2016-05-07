package com.test.second;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.second.*;
import com.test.second.dao.*;
import com.test.second.object.*;
import com.test.second.parser.*;;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	Command command;
	
	@Autowired
	private AbstractDAO dao;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		return "home";
	}

	@RequestMapping("/home")
	public String home(Model model) {
		return "home";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		System.out.println("login()");

		model.addAttribute("request", request);
		command = new LoginCommand();
		if (command.excute(model))
			return "redirect:timetable";
		else
			return "redirect:home";
	}

	@RequestMapping("/gettimetable")
	public String gettimetable(Model model) {
		BufferedWriter write = null;

		try {
			write = new BufferedWriter(new FileWriter("output.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		LecturePlan lp = new LecturePlan();
		lp.TotalParse();
		ArrayList<LectureObj> LectureList = lp.getLectureList();

		for (LectureObj e : LectureList) {
			System.out.println("[List] " + e.toString());
			try {
				write.write("[List] " + e.toString() + "\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		try {
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:home";
	}

	@RequestMapping("/timetable")
	public String timetable(Model model) {
		for (int i = 0; i < Constant.scheduleList.size(); i++) {
			System.out.println(Constant.scheduleList.get(i).toString());
		}
		model.addAttribute("list", Constant.scheduleList);
		return "timetable";
	}

	@RequestMapping("/knumap")
	public String knumap(Model model) {

		return "knumap";
	}

	@RequestMapping("/calendar")
	public String calendar(Model model){
		CollegePlan colplan = new CollegePlan(2016,"05");
		model.addAttribute("college_plan_list", colplan.getCollegeStringList());
		return "calendar";
	}

	@RequestMapping("/fullcalendar")
	public String fullcalendar(Model model) {
		return "fullcalendar";
	}

	@RequestMapping(value = "/schedule", method = RequestMethod.POST)
	public String schedule(@RequestParam("title") String title, @RequestParam("start") String start, @RequestParam("end") String end) {
		System.out.println(title + ' ' + start + ' ' + end);
		try {
			dao.calendar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fullcalendar";
	}
	
	@RequestMapping(value = "/calendarholiday", method = RequestMethod.GET)
	@ResponseBody
	public List<CalendarObj> calendarholiday(@RequestParam(value="month",
		required=false, defaultValue="05") String month){		
		HolidayPlan obj = new HolidayPlan(2016,Integer.parseInt(month));
		
		return obj.getCalList();
	}
	
	@RequestMapping(value = "/calendarcollege", method = RequestMethod.GET)
	@ResponseBody
	public List<CalendarObj> calendarcollege(@RequestParam(value="month",
		required=false, defaultValue="05") String month){
				
		CollegePlan collobj = new CollegePlan(2016 , month);
				
		return collobj.getCalList();
	}
	
//	@RequestMapping("/calendarusertime")
//	public String calendarusertime(@RequestParam(value="month", required=false, defaultValue="1") String month
//			)
//	{
//		
//	}
	
	


}
