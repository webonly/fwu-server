package com.techsun.famouswine.util;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AllInterceptor implements HandlerInterceptor  {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
		
		
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
        String username =  (String)request.getSession().getAttribute("user");
        
        String token = (String)request.getSession().getAttribute("token");
        request.getHeaderNames();
        
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        System.out.print("preHandle");
        
        return true;
        
/*        if(token == null){   
            request.getRequestDispatcher("/WebContent/login.jsp").forward(request, response);            
            return true;  
        }else  
            return true; */
	}
	
}	
