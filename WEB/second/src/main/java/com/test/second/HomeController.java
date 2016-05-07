package com.test.second;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.test.second.*;
import com.test.second.object.*;
import com.test.second.parser.*;;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	Command command;

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
	public String calendar(Model model) {
		CollegePlan colplan = new CollegePlan(2016);
		model.addAttribute("college_plan_list", colplan.getCollegeStringList());
		return "calendar";
	}

	@RequestMapping("/fullcalendar")
	public String fullcalendar(Model model) {
		return "fullcalendar";
	}

	
	@RequestMapping(value = "/schedule", method = RequestMethod.POST)
	@ResponseBody
	public String schedule(@RequestParam String title, @RequestParam String start, @RequestParam String end) {
		System.out.println(title + ' ' + start + ' ' + end);
		System.out.println("dd");
		return "fullcalendar";
	}
}
