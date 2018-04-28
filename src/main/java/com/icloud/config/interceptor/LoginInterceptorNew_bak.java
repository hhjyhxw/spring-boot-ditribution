package com.icloud.config.interceptor;


import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.common.ConfigUtil;
import com.icloud.common.MD5Utils;
import com.icloud.common.SpringContextHolder;
import com.icloud.common.util.StringUtil;
import com.icloud.model.business.BeanFans;
import com.icloud.service.business.BeanFansService;


/**
 * 手机端全局拦截器
 * zdh
 */
public class LoginInterceptorNew_bak implements HandlerInterceptor{
	
	public final static Logger log = LoggerFactory.getLogger(LoginInterceptorNew_bak.class);
	private BeanFansService beanFansService = SpringContextHolder.getBean(BeanFansService.class);
	
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
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {

//			StringBuffer visiturl = new StringBuffer();
//			visiturl.append(request.getScheme() + "://"+request.getHeader("host")+request.getRequestURI());
//			if(visiturl.toString().indexOf("/frontpage/user/toSubscribe")>=0){
//				return true;
//			}
//			//测试使用
//			if(visiturl.toString().indexOf("/frontpage/beanGoods/exChangeGoods")>=0){
//				return true;
//			}
//			//请求域名
//			if (request.getQueryString() != null) // 判断请求参数是否为空
//				visiturl.append("?" + request.getQueryString()); // 参数
//				log.info("visiturl====="+visiturl);
//			
//			BeanFans beanFans = null;
//			/*1=========================从redis session获取=========================*/
//			Object obj = request.getSession().getAttribute("beanFans");
//			if(obj!=null){
//				beanFans=(BeanFans) obj;
//				log.info("queryredis,用户==="+beanFans.getNickname()+"===已登录==="+beanFans.getOpenid());
//				return true;
//			}else{
//				/*2=========================调用真龙接口获取=============================*/
//				//获取接口参数数据,并校验
//				String nickName = request.getParameter("nickName");
//				String headPhoto = request.getParameter("headPhoto");
//				String unionid = request.getParameter("unionid");
//				String openid = request.getParameter("openid");
//				String sign = request.getParameter("sign");
//				if(StringUtil.checkStr(openid) && StringUtil.checkStr(nickName)){
//					String newsign = "";
//					if(StringUtil.checkStr(unionid)){
//						newsign = MD5Utils.encode2hex("openid="+openid+"&unionid="+unionid+"&key="+ConfigUtil.get("infokey"));
//					}else{
//						newsign = MD5Utils.encode2hex("openid="+openid+"&key="+ConfigUtil.get("infokey"));
//					}
//					log.error("sign====="+sign+"&newsign===="+newsign);
//					if(!newsign.equals(sign)){
//						log.error("签名错误,非法请求");
//						return false;
//					}
//					String subscribe = request.getParameter("subscribe");
//					//用户未关注
//					if(!StringUtil.checkStr(subscribe) || "0".equals(subscribe)){
//						//跳转关注页面,需要做
//						 response.sendRedirect(request.getContextPath()+ConfigUtil.get("subscibeUrl"));
//						 return false;
//					}
//					
//					BeanFans beanFansParam = new BeanFans();
//					beanFansParam.setOpenid(openid);
//					List<BeanFans> beanFansList = beanFansService.findList(beanFansParam);
//					if(beanFansList!=null && beanFansList.size()>0){
//						beanFans = beanFansList.get(0);
//						//更新
//						beanFans.setNickname(URLDecoder.decode(nickName, "utf-8"));
//						beanFans.setHeadimgurl(headPhoto);
//						beanFansService.update(beanFans);
//						//更新缓存
//						request.getSession().setAttribute("beanFans",beanFans);
//						//图片访问路径
//						request.getSession().setAttribute("pictureVisitUrl",ConfigUtil.get("pictureVisitUrl"));
//						log.info("querydb,用户==="+beanFans.getNickname()+"===已登录==="+beanFans.getOpenid());
//						return true;
//					}
//					//数据库查询不到，则保存
//					beanFans = new BeanFans();
//					beanFans.setOpenid(openid);
//					beanFans.setNickname(URLDecoder.decode(nickName, "utf-8"));
//					beanFans.setHeadimgurl(headPhoto);
//					beanFans.setUnionid(unionid);
//					beanFans.setCreateTime(new Date());
//					beanFansService.save(beanFans);
//					request.getSession().setAttribute("beanFans",beanFans);
//					request.getSession().setAttribute("pictureVisitUrl",ConfigUtil.get("pictureVisitUrl"));
//					log.info("queryByInterface,用户==="+beanFans.getNickname()+"===已登录==="+beanFans.getOpenid());
//					return true;
//				}else{
//					/*3、===============从redis 和数据库 都获取不到 ，那么从接口获取=====================*/
//					//调用接口获取用户数据
//					 String state = Base64.getEncoder()
//							 .encodeToString(visiturl.toString().getBytes(StandardCharsets.UTF_8))
//							 .replace("=", "-").replace("/", "_");
//					 String redirectUrl = ConfigUtil.get("getUserInfo")
//							 .replace("APPID", ConfigUtil.get("appid"))
//							 .replace("STATE", state);	
//					 response.sendRedirect(redirectUrl);
//					 return false;
//				}
//			}
		
		BeanFans beanFans = beanFansService.findByKey(1L);
		//更新缓存
		request.getSession().setAttribute("beanFans",beanFans);
//		//图片访问路径
		request.getSession().setAttribute("pictureVisitUrl","http://localhost:8080/");
		return true;
	}
	
	public Object getBean(String beanName,HttpServletRequest request){
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
	    return factory.getBean(beanName); 
	}
	
}
