package com.test.second.interceptor;

import javax.servlet.http.*;

import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.*;

public class LoginInterceptor extends HandlerInterceptorAdapter{
    
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       try {
           if(request.getSession().getAttribute("login") == null ){
                   response.sendRedirect("home"); 
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