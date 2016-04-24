package com.test.second;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class LoginCommand implements Command {

	@Override
	public void excute(Model model) {
		// TODO Auto-generated method stub
			Map<String, Object> map = model.asMap();
			HttpServletRequest request = (HttpServletRequest) map.get("request");
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			System.out.println(id + " and " + pwd);
	}

}
