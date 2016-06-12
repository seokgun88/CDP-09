package com.test.second.interceptor;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.*;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       try {
           //세션key의 id가 관리자가 아닐경우 로그아웃
    	   HashMap<String, Object> login_data = (HashMap<String, Object>) request.getSession().getAttribute("login");
           if(login_data == null || !login_data.get("user_id").equals("sukgun88")){
                   response.sendRedirect("logout"); 
                   return false;
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return true;
   }

   @Override
   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       super.postHandle(request, response, handler, modelAndView);
   }

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       super.afterCompletion(request, response, handler, ex);
   }
}
