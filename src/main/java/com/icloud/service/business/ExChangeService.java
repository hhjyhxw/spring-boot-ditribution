/**
 * @author
 * @version
 * 2018年2月28日 上午11:08:45
 */
package com.icloud.service.business;

import com.icloud.model.business.BeanFans;

/**
 * 类名称: ExChangeService
 * 类描述: 商品兑换
 * 创建人: zhangdehai
 * 创建时间:2018年2月28日 上午11:08:45
 */
public interface ExChangeService {
	
	/**
	* Description:商品兑换（需要折扣券兑换）
	* @author zhangdehai
	* @param ticket_id 优惠券id
	* @param ticketValue 优惠券面值
	* @param goodsId	商品id
	* @param exChangeNum  商品数量
	* @date 2018年2月28日上午11:43:37
	 */
	public void exChangeGoods(String ticket_id,Integer ticketValue,Integer goodsId,
			Integer exChangeNum,BeanFans beanFans,Long addressId)throws Exception;

	/**
	* Description:商品兑换（存乐豆兑换）
	* @author zhangdehai
	* @param goodsId 商品id
	* @param exChangeNum 商品数量
	* @param beanFans  
	* @date 2018年3月6日上午10:34:57
	*/
	public void beanExChangeGoods(Integer goodsId, Integer exChangeNum,
			BeanFans beanFans,Long addressId);

}
