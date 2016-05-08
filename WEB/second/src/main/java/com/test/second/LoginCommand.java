package com.test.second;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.test.second.parser.UserSchedulePlan;
import com.test.second.object.ScheduleAttr;
import com.test.second.Constant;

public class LoginCommand implements Command {

	@Override
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
				Constant.user_id = id;
				Constant.scheduleList = attrList;
				/*for(int i = 0 ;i<attrList.size();i++){			
					System.out.println(attrList.get(i).toString());
				}*/
			}
			Constant con = new Constant();
			con.getCalList();
			
			return true;
	}

}
