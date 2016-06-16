package com.test.second;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.test.second.parser.UserSchedulePlan;
import com.test.second.object.ScheduleAttr;
import com.test.second.TimetableManage;

public class LoginCommand{

	public boolean excute(Model model) {
		// TODO Auto-generated method stub
			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
						
			UserSchedulePlan UserTime = new UserSchedulePlan();

			if(UserTime.StartRequest(id, pwd) == false){
				System.out.println("Request 실패 !!");
				return false;
			}
			else{
				System.out.println("Request 성공 !!");
				ArrayList<ScheduleAttr> attrList = UserTime.getScheduleList();
		        Map<String, Object> login_map = new HashMap<String,Object>();
		        login_map.put("user_id", id);
		        login_map.put("scheduleList", attrList);
		        request.getSession().setAttribute("login", login_map);				
			}
			
			return true;
	}

}
