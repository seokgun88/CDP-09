package com.test.second.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.second.dao.Dao;
import com.test.second.object.CalendarObj;
import com.test.second.object.MenuListObj;
import com.test.second.object.MenuObj;
import com.test.second.parser.Menu_Parser;

@Controller
public class MenuController {
			
	@RequestMapping("/tempknumenu")
	public String timetable(HttpServletRequest request, Model model) {
				
		return "knumenu";
	}

	@RequestMapping("/knumenu")	
	public String MenuList(HttpServletRequest request, Model model) {
//		ArrayList<MenuListObj> MenuList = new ArrayList<MenuListObj>();
		System.out.println("what the fuck!!!");
		Menu_Parser m = new Menu_Parser(); // 객체 생성
		int []numList = {
				46,40,37,56,35
		};
		// 빌딩 행
		ArrayList<String> building_list = new ArrayList<String>();
		// 조식 행
		ArrayList<String> breakfast_list = new ArrayList<String>();
		// 중식 행
		ArrayList<String> lunch_list = new ArrayList<String>();
		// 석식 행
		ArrayList<String> dinner_list = new ArrayList<String>();
		
		breakfast_list.add("조식");
		lunch_list.add("중식");
		dinner_list.add("석식");
		
		for(int cnt = 0; cnt < numList.length; cnt++)
		{
			m.ParseStart(numList[cnt]);
//			System.out.println(m.getTempMenuList()+"\n");
			MenuListObj temp = m.getTempMenuList();
//			MenuList.add(temp);
			
			building_list.add(temp.getBuildname());
			
			if(temp.getBreakfast() == true){
				String tempstr = "";
				for(MenuObj element : temp.getBreakfast_MenuList()){
					tempstr += element.toString() +"<br/>";
				}
				breakfast_list.add(tempstr);
//				breakfast_list.add(temp.getBreakfast_MenuList().toString());
			}
			else{
				breakfast_list.add("");
			}
			
			if(temp.getLunch() == true){
				String tempstr = "";
				for(MenuObj element : temp.getLunch_MenuList()){
					tempstr += element.toString() +"<br/>";
				}
				lunch_list.add(tempstr);
//				lunch_list.add(temp.getLunch_MenuList().toString());
			}
			else{
				lunch_list.add("");
			}
			
			if(temp.getDinner() == true){
				String tempstr = "";
				for(MenuObj element : temp.getDinner_MenuList()){
					tempstr += element.toString() +"<br/>";										
				}
				dinner_list.add(tempstr);
			}
			else{
				dinner_list.add("");
			}
		}
		System.out.println(building_list);
		System.out.println(breakfast_list);
		System.out.println(lunch_list);
		System.out.println(dinner_list);
		
		model.addAttribute("buildinglist", building_list);
		model.addAttribute("breakfastlist", breakfast_list);
		model.addAttribute("lunchlist", lunch_list);
		model.addAttribute("dinnerlist", dinner_list);

		return "knumenu";
	}
	
}
