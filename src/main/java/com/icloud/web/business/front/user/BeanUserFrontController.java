package com.icloud.web.business.front.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.icloud.model.business.BeanAddress;
import com.icloud.service.business.BeanAddressService;
import com.icloud.service.business.BeanFansService;
import com.icloud.service.interfaces.SmokeBeanInterfaceService;
import com.icloud.service.redis.RedisService;
import com.icloud.web.AppBaseController;

/**
 * 类名称: BeanUserFrontController
 * 类描述: 个人中心，获取我的默认地址，查询乐豆
 * 创建人: zhangdehai
 * 创建时间:2018年3月6日 上午11:46:25
 */
@Controller
@RequestMapping(value = "${frontPath}/user")
public class BeanUserFrontController extends AppBaseController{
	
	
	@Autowired
	private BeanFansService beanFansService;
	@Autowired
	private SmokeBeanInterfaceService smokeBeanInterfaceService;
	@Autowired
	private RedisService redisService;
	@Autowired
	private BeanAddressService beanAddressService;
	
	/**
	* Description:个人中心
	* @author zhangdehai
	* @throws Exception  
	* @date 2018年2月27日下午5:01:37
	 */
	@RequestMapping(value = "/myCenter")
	public String myCenter(){
		
		return "front/myCenter";
	}
	
	/**
	* Description:异步查询我的乐豆
	* @author zhangdehai
	* @throws Exception  
	* @date 2018年2月27日下午5:13:18
	 */
	@RequestMapping(value = "/queryBeans")
	@ResponseBody
	public String queryBeans() throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put(STATUS, SUCCESS);
		Map<String,String> map = smokeBeanInterfaceService.queryFansBeans(getOpenId());
		 if(map!=null){
			 if("0".equals(map.get("status"))){
				 resultMap.put("smokeBeansCount",  map.get("smokeBeansCount"));
				 return pakageJsonp(resultMap);
			 }
		 }
		 resultMap.put("smokeBeansCount","0");
		 return pakageJsonp(resultMap);
	}
	 
	
	/**
	* Description:异步查询我的乐豆
	* @author zhangdehai
	* @throws Exception  
	* @date 2018年2月27日下午5:13:18
	 */
	@Deprecated
	@RequestMapping(value = "/queryBean")
	@ResponseBody
	public String queryBean() throws Exception{
		JSONObject obj = new JSONObject();
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put(STATUS, SUCCESS);
		//1、缓存中获取
		Object smokeBeansCount = redisService.get("smokeBeansCount");
		if(smokeBeansCount!=null){
			resultMap.put("smokeBeansCount", smokeBeansCount+"");
			return pakageJsonp(obj);
		}else{
		//2接口查询
			 Map<String,String> map = smokeBeanInterfaceService.queryFansBeans(getOpenId());
			 if(map!=null){
				 if("0".equals(map.get("status"))){
					 obj.put("smokeBeansCount", map.get("smokeBeansCount"));
					 resultMap.put("smokeBeansCount", smokeBeansCount+"");
					 //缓存乐豆有效时间 3分钟
					 redisService.set("smokeBeansCount", map.get("smokeBeansCount"),1800L);
					 return pakageJsonp(obj);
				 }
			 }
		}
		obj.put("smokeBeansCount",0);
		return pakageJsonp(obj);
	}
	 
	/**
	* Description:获取用户默认地址
	* @author zhangdehai
	* @return  
	 * @throws Exception 
	* @date 2018年3月5日下午4:44:56
	 */
	@RequestMapping(value = "/getDefaultAddr")
	@ResponseBody
	public String getDefaultAddr() throws Exception{
		BeanAddress beanAddress = new BeanAddress();
		beanAddress.setDefaultAddr("1");
		beanAddress.setUserId(getMemberId());
		List<BeanAddress> list = beanAddressService.findList(beanAddress);
		if(list!=null && list.size()>0){
			return pakageJsonp(list.get(0));
		}
		return null;
		
	}
	
}
