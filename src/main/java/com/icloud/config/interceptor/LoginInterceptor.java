package com.icloud.config.interceptor;


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
import com.icloud.service.redis.RedisService;


/**
 * 手机端全局拦截器
 * zdh
 */
public class LoginInterceptor implements HandlerInterceptor{
	
	public final static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
	private BeanFansService beanFansService = SpringContextHolder.getBean(BeanFansService.class);
	private RedisService redisService = SpringContextHolder.getBean(RedisService.class);
	
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

		StringBuffer visiturl = new StringBuffer();
		visiturl.append(request.getScheme() + "://"+request.getHeader("host")+request.getRequestURI());
		//请求域名
		if (request.getQueryString() != null) // 判断请求参数是否为空
			visiturl.append("?" + request.getQueryString()); // 参数
		log.info("visiturl====="+visiturl);
		//openid作为sessionkey, 前端方法请求都需要改参数
		String openid = request.getParameter("openid");
		BeanFans beanFans = null;
		log.info("openid==="+openid);
		if(StringUtil.checkStr(openid)){
			//1从redis获取
			Object obj = redisService.get(openid);
			if(StringUtil.checkObj(obj)){
				beanFans = (BeanFans) obj;
				log.info("queryredis,用户==="+beanFans.getNickname()+"已登录");
			}else{
				//2从数据库查询
				BeanFans beanFansParam = new BeanFans();
				beanFansParam.setOpenid(openid);
				List<BeanFans> beanFansList = beanFansService.findList(beanFansParam);
				if(beanFansList!=null && beanFansList.size()>0){
					beanFans = beanFansList.get(0);
					//更新缓存
					redisService.set(openid, beanFans);
					log.info("querydb,用户==="+beanFans.getNickname()+"已登录");
				}
			}
			//3、从redis 和数据库 都获取不到 ，那么从接口获取
			if(beanFans==null){
				
				String nickName = request.getParameter("nickName");
				String headPhoto = request.getParameter("headPhoto");
				String unionid = request.getParameter("unionid");
				String sign = request.getParameter("sign");
				String newsign = "";
				if(StringUtil.checkStr(unionid)){
					newsign = MD5Utils.encode2hex("openid="+openid+"&unionid="+unionid+"&key="+ConfigUtil.get("infokey"));
				}else{
					newsign = MD5Utils.encode2hex("openid="+openid+"&key="+ConfigUtil.get("infokey"));
				}
				log.error("sign====="+sign+"&newsign===="+newsign);
				if(!newsign.equals(sign)){
					log.error("签名错误,非法请求");
					return false;
				}
				
				String subscribe = request.getParameter("subscribe");
				//用户未关注
				if(!StringUtil.checkStr(subscribe) || "0".equals(subscribe)){
					//跳转关注页面,需要做
					 response.sendRedirect(request.getContextPath()+ConfigUtil.get("subscibeUrl"));
					 return false;
				}
				
				beanFans = new BeanFans();
				beanFans.setOpenid(openid);
				beanFans.setNickname(nickName);
				beanFans.setHeadimgurl(headPhoto);
				beanFans.setUnionid(unionid);
				beanFans.setCreateTime(new Date());
				beanFansService.save(beanFans);
				redisService.set(openid, beanFans);
				log.info("queryByInterface,用户==="+beanFans.getNickname()+"已登录");
			}
		}else{
			//调用接口获取用户数据
			 String state = Base64.getEncoder()
					 .encodeToString(visiturl.toString().getBytes(StandardCharsets.UTF_8))
					 .replace("=", "-").replace("/", "_");
			 String redirectUrl = ConfigUtil.get("getUserInfo")
					 .replace("APPID", ConfigUtil.get("appid"))
					 .replace("STATE", state);	
			 response.sendRedirect(redirectUrl);
			 return false;
		}
        return true;
	}
	
	public Object getBean(String beanName,HttpServletRequest request){
		BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()); 
	    return factory.getBean(beanName); 
	}
	
}
