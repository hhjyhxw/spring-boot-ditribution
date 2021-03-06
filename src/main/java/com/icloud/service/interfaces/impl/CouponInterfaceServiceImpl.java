/**
 * @author
 * @version
 * 2018年2月27日 下午5:57:07
 */
package com.icloud.service.interfaces.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.ConfigUtil;
import com.icloud.common.HttpUtils;
import com.icloud.common.JsonToMapUtil;
import com.icloud.common.MD5Utils;
import com.icloud.service.interfaces.CouponInterfaceService;
import com.icloud.vo.BeanCouponVo;

/**
 * 类名称: CouponInterfaceServiceImpl
 * 类描述: 紫光优惠券接口实现
 * 创建人: zhangdehai
 * 创建时间:2018年2月27日 下午5:57:07
 */
@Service
public class CouponInterfaceServiceImpl implements CouponInterfaceService{
	
	private final static Logger log = LoggerFactory.getLogger(CouponInterfaceServiceImpl.class);
	/**
	* Description:查询优惠券列表
	* @author zhangdehai
	* @param openid
	* @param type 0:可用抵价券，1：已消耗，2：全部
	* @date 2018年2月27日下午5:58:22
	 */
	public List<BeanCouponVo> queryBeanCouponList(String openid,String type){
		NameValuePair openidPair = new BasicNameValuePair("openid",openid);
		NameValuePair typePair = new BasicNameValuePair("type",type);
		List<NameValuePair> pair = new ArrayList<NameValuePair>();
		pair.add(openidPair);
		pair.add(typePair);
		String result  = "";
		try{
			result = HttpUtils.postMethod(ConfigUtil.get("yccqQueryCouponsListUrl"), pair);
			if(result!=null){
				JSONObject jsonObject = JSON.parseObject(result);
				//code状态码，0：成功，1：失败
				if("0".equals(jsonObject.get("code"))){
					JSONArray data = jsonObject.getJSONArray("data");
					List<BeanCouponVo> couponList = data.toJavaList(BeanCouponVo.class);
					return couponList;
				}else{
					log.error(jsonObject.get("msg")!=null?jsonObject.get("msg").toString():"获取优惠券列表失败");
					return null;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* Description:查询优惠券列表
	* @author zhangdehai
	* @param openid
	* @param type 0:可用抵价券，1：已消耗，2：全部
	* @date 2018年2月27日下午5:58:22
	 */
	public String queryBeanCouponJsonString(String openid,String type){
		NameValuePair openidPair = new BasicNameValuePair("openid",openid);
		NameValuePair typePair = new BasicNameValuePair("type",type);
		List<NameValuePair> pair = new ArrayList<NameValuePair>();
		
		log.warn("代金券查询接口参数："
				+" \r\n openid="+openid
				+" \r\n type="+type	);
			
		pair.add(openidPair);
		pair.add(typePair);
		String result  = "";
		try{
			result = HttpUtils.postMethod(ConfigUtil.get("yccqQueryCouponsListUrl"), pair);
			if(result!=null){
				JSONObject jsonObject = JSON.parseObject(result);
				//code状态码，0：成功，1：失败
				if("0".equals(jsonObject.get("code"))){
					JSONArray data = jsonObject.getJSONArray("data");
					if(data!=null){
						return JSON.toJSONString(data);
					}
					log.error("优惠券列表为空");
				}else{
					log.error(jsonObject.get("msg")!=null?jsonObject.get("msg").toString():"获取优惠券列表失败");
					return null;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	* Description:消费优惠券
	* @author zhangdehai
	* @param ticketId 卡券唯一标识
	* @param openid 用户openid
	* @return  
	* @date 2018年2月27日下午6:00:30
	 */
	public Map<String,String> consumeCoupon(String ticket_id,String openid) {
		NameValuePair openidPair = new BasicNameValuePair("openid",openid);
		NameValuePair ticket_idPair = new BasicNameValuePair("ticket_id",ticket_id);
		String signString = ConfigUtil.get("yccq_key")+ticket_id+openid;
		String sign = MD5Utils.encode2hex(signString);
		NameValuePair signPair = new BasicNameValuePair("sign",sign);
		List<NameValuePair> pair = new ArrayList<NameValuePair>();
		
		log.warn("优惠券消费接口参数："
				+" \r\n openid="+openid
				+" \r\n yccq_key="+ConfigUtil.get("yccq_key")
				+" \r\n sgin="+sign);
		
		pair.add(openidPair);
		pair.add(ticket_idPair);
		pair.add(signPair);
		String result  = "";
		try{
			result = HttpUtils.postMethod(ConfigUtil.get("yccqConsumerCouponsUrl"), pair);
			if(result!=null){
				return JsonToMapUtil.getMapFromJson(result);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
