package com.icloud.config.interceptor;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 生成token拦截器
 */
public class AddTokenInterceptor implements HandlerInterceptor{
	
	public final static Logger log = LoggerFactory.getLogger(RemoveTokenInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest reqeust, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest reqeust, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {
 
	}

	@Override
	public boolean preHandle(HttpServletRequest reqeust, HttpServletResponse response, Object arg2) throws Exception {
		  String uuid = UUID.randomUUID().toString();
		  reqeust.getSession().setAttribute( "payToken" , uuid);
          log.debug("进入表单页面，payToken值为："+uuid);
          return true;
	}
	

}
