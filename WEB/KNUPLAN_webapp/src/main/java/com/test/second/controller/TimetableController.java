package com.test.second.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.second.object.ClassroomScheduleObj;
import com.test.second.object.LectureObj;
import com.test.second.parser.LecturePlan;

@Controller
public class TimetableController {
	
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
			//			System.out.println("[List] " + e.toString());
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
	public String timetable(HttpServletRequest request, Model model) {
		//		for (int i = 0; i < Constant.scheduleList.size(); i++) {
		//			System.out.println(Constant.scheduleList.get(i).toString());
		//		}
		HashMap<String, Object> map = (HashMap<String, Object>) request.getSession().getAttribute("login");
		model.addAttribute("list", map.get("scheduleList"));
		
		return "timetable";
	}
	
	
}
