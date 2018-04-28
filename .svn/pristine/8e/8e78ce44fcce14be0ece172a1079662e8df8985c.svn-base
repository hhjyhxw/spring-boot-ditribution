/**
 * @author
 * @version
 * 2018年2月27日 下午5:57:07
 */
package com.icloud.service.interfaces;

import java.util.List;
import java.util.Map;

import com.icloud.vo.BeanCouponVo;

/**
 * 类名称: CouponInterfaceService
 * 类描述: 优惠券接口
 * 创建人: zhangdehai
 * 创建时间:2018年2月27日 下午5:57:07
 */
public interface CouponInterfaceService {
	
	/**
	* Description:查询优惠券列表
	* @author zhangdehai
	* @param openid
	* @param type 0:可用抵价券，1：已消耗，2：全部
	* @date 2018年2月27日下午5:58:22
	 */
	public List<BeanCouponVo> queryBeanCouponList(String openid,String type);
	
	/**
	* Description:查询优惠券列表
	* @author zhangdehai
	* @param openid
	* @param type 0:可用抵价券，1：已消耗，2：全部
	* @date 2018年2月27日下午5:58:22
	 */
	public String queryBeanCouponJsonString(String openid,String type);
	
	/**
	* Description:消费优惠券
	* @author zhangdehai
	* @param ticketId 卡券唯一标识
	* @param openid 用户openid
	* @return  
	* @date 2018年2月27日下午6:00:30
	 */
	public Map<String,String> consumeCoupon(String ticketId,String openid);

}
