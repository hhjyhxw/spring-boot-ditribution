package com.icloud.web.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.icloud.exceptions.BeanException;
import com.icloud.model.business.BeanGoods;
import com.icloud.service.redis.RedisService;
import com.icloud.service.redislock.DistributedLock;
import com.icloud.service.redislock.DistributedLockUtil;


/**
 * @filename      : ExChangeGoodsUtil.java
 * @description   : 兑换商品 操作库存util;其他泛型操作
 * @author        : zdh
 * @create        : 2017年9月17日 下午9:44:38   
 * @copyright     : zhumeng.com@hyzy-activities
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Component	
public class RedisBeanUtil<T> {

	@Autowired
	private RedisService redisService;
	
	@Autowired
	private DistributedLockUtil distributedLockUtil;
	
	/**
	* Description:获取商品
	* @author zhangdehai
	* @param goodsId
	* @return  
	* @date 2018年3月1日下午6:52:09
	 */
	public BeanGoods getBeanGoods(Integer goodsId){
		return (BeanGoods) redisService.get(goodsId.toString());
	}
	
	/**
	* Description:获取商品
	* @author zhangdehai
	* @param goodsId
	* @return  
	* @date 2018年3月1日下午6:52:09
	 */
	public boolean setBeanGoods(BeanGoods beanGoods){
		return redisService.set(beanGoods.getId().toString(),beanGoods);
	}
	
	/**
	 * 对redis中  商品的库存的更新 需要锁
	* Description:
	* @author zhangdehai
	* @param goodsId
	* @param num  
	 */
	public void setBeanGoods(Integer goodsId,Integer num){
		DistributedLock lock = distributedLockUtil.getDistributedLock("set_"+goodsId.toString());
		try {
		    //获取锁成功业务代码
		    if (lock.acquire()) {
		    	BeanGoods obj = getBeanGoods(goodsId);
			 	obj.setFreezeStore(obj.getFreezeStore()+num);
			 	obj.setVirtlSales(obj.getVirtlSales()+num);
			 	obj.setRealSales(obj.getRealSales()+num);
			 	setBeanGoods(obj);
		    } else { // 获取锁失败
		        //获取锁失败业务代码
		    	throw new BeanException("系统繁忙,请稍后再试");
		    }
		} finally {
		    if (lock != null) {
		        lock.release();
		    }
		}
	}
	
	/**
	* Description:回退库存
	* @author zhangdehai
	* @param beanGoods
	* @param num  
	* @date 2018年3月1日下午6:58:53
	 */
	public void rollBackFreezeStore(Integer goodsId ,Integer num){
//		BeanGoods obj = getBeanGoods(goodsId);
//	 	obj.setFreezeStore(obj.getFreezeStore()-num);
//	 	obj.setVirtlSales(obj.getVirtlSales()-num);
//	 	obj.setRealSales(obj.getRealSales()-num);
//	 	setBeanGoods(obj);
		setBeanGoods(goodsId,num);
	}
	
	@SuppressWarnings("unchecked")
	public T getBeans(String Key){
		return (T) redisService.get(Key);
	}
	
	public boolean setBeans(T t,String Key){
		return redisService.set(Key,t);
	}
}
