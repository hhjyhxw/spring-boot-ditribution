package com.icloud.service.interfaces;

import java.util.Map;

/**
 * 
* ClassName: SmokeBeanInterfaceService
* Description:
* @author z
* @date 2018年2月8日上午9:31:43
 */
public interface SmokeBeanInterfaceService {
	
	/**
	 * 用户乐豆查询接口
	 * @param openid 用户openid
	 * @param app_id 渠道（这里是乐豆商城：ldsc）
	 * @param sign 签名 MD5Util.encode2hex(app_id + openid + appkey;);
	 * @return
	 */
	public Map<String,String> queryFansBeans(String app_id,String openid,String sign);
	
	public Map<String,String> queryFansBeans(String openid);
	
	/**
	* Description:真龙用户乐豆充值接口
	* @author zhangdehai 
	* @param app_id 渠道（这里是乐豆商城：ldsc）
	* @param openid 用户openid
	* @param qrcodeNo 二维码编号 可为空
	* @param orderno	订单号
	* @param smokeBeans 乐豆数量
	* @param smokeBrand 品牌
	* @param type	获取烟豆途径，比如扫的哪款香烟品牌，哪个游戏或者哪次活动等 (这里是: 没确定)
	* @param desc	操作说明
	* @param sign = MD5Util.encode2hex(app_id + qrcodeNo + orderno + openid + smokeBeans
				+ smokeBrand + desc + appKey);
	* @return  
	* @date 2018年2月8日上午9:45:30
	 */
	public Map<String,String> rechargeFansBeans(String app_id,String openid,
			String qrcodeNo,String orderno,
			String smokeBeans,String smokeBrand,String type,String desc,String sign);

	
	public Map<String,String> rechargeFansBeans(String openid,
			String qrcodeNo,String orderno,
			String smokeBeans,String smokeBrand,String type,String desc);
	

	/**
	 * 
	* Description:真龙乐豆消费接口
	* @author zhangdehai
	* @param app_id  渠道（这里是乐豆商城：ldsc）
	* @param openid 用户openid
	* @param orderno	ldsc_ 订单号
	* @param smokeBeans	乐豆数量
	* @param consumeType 消费类型，(这里是：乐豆商城兑换)
	* @param desc 描述  乐豆商城兑换
	* @param sign MD5Util.encode2hex(app_id + orderno + openid + smokeBeans + consumeType
				+ desc + appKey);
	* @return  
	* @date 2018年2月8日上午9:45:38
	 */
	public Map<String,String> consumeFansBeans(String openid,String orderno,
			String smokeBeans,String consumeType,String desc);

	public Map<String,String> consumeFansBeans(String app_id,String openid,String orderno,
			String smokeBeans,String consumeType,String desc,String sign);
}
