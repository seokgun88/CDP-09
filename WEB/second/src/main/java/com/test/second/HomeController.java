package com.test.second;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.second.*;
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
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}

	@RequestMapping("/home")
	public String home(Model model){		
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model){
		System.out.println("login()");
		
		model.addAttribute("request", request);
		command = new LoginCommand();
		command.excute(model);
		
		return "redirect:timetable";
	}
	
	@RequestMapping("/timetable")
	public String timetable(Model model){		
		return "timetable";
	}
}
