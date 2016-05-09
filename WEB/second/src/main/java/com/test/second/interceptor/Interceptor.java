package com.test.second.interceptor;

import javax.servlet.http.*;

import org.springframework.web.servlet.*;
import org.springframework.web.servlet.handler.*;

public class Interceptor extends HandlerInterceptorAdapter{
    
   @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       try {
           //admin이라는 세션key를 가진 정보가 널일경우 로그인페이지로 이동
           if(request.getSession().getAttribute("login") == null ){
                   response.sendRedirect("home"); 
                   return false;
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       //admin 세션key 존재시 main 페이지 이동
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