
package com.test.second.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.text.DateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.*;
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
	@Autowired
	private SqlSession sqlSession;
	
	boolean isClassRoomParse = true;

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
		LoginCommand loginComand = new LoginCommand();
		if (loginComand.excute(model))
			return "redirect:fullcalendar";
		else
			return "redirect:home?success=false";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		System.out.println("logout()");
		
		request.getSession().invalidate();
		
		return "redirect:home";
	}

	

	@RequestMapping("/knumap")
//	public String knumap(HttpServletRequest request, Model model) {
	public String knumap(Model model) {
//        System.out.println("user_id " + request.getSession().getAttribute("user_id"));
//        System.out.println("**************************");
//		PlaceManage PM = new PlaceManage();
		
		return "knumap";
	}

	@RequestMapping("/calendar")
	public String calendar(Model model){
		CollegePlan colplan = new CollegePlan(2016,5);
		model.addAttribute("college_plan_list", colplan.getCollegeStringList());
		return "calendar";
	}

	@RequestMapping("/fullcalendar")
	public String fullcalendar(Model model) {
		return "fullcalendar";
	}

	@RequestMapping("/help")
	public String help(Model model) {
		return "help";
	}

	@RequestMapping("/knulibrary")
	public String knulibrary(Model model) {
		return "knulibrary";
	}
	
}
