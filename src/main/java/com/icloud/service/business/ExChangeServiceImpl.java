/**
 * @author
 * @version
 * 2018年2月28日 上午11:12:44
 */
package com.icloud.service.business;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icloud.common.DateTools;
import com.icloud.exceptions.BeanException;
import com.icloud.model.business.BeanAddress;
import com.icloud.model.business.BeanCouponConsumeRecord;
import com.icloud.model.business.BeanFans;
import com.icloud.model.business.BeanGoods;
import com.icloud.model.business.BeanOrder;
import com.icloud.model.business.BeanOrderItem;
import com.icloud.service.interfaces.CouponInterfaceService;
import com.icloud.service.interfaces.SmokeBeanInterfaceService;
import com.icloud.service.interfaces.impl.SmokeBeanInterfaceServiceImpl;
import com.icloud.service.redis.RedisService;
import com.icloud.service.redislock.DistributedLock;
import com.icloud.service.redislock.DistributedLockUtil;
import com.icloud.web.util.OrderUtil;
import com.icloud.web.util.RedisBeanUtil;

/**
 * 类名称: ExChangeServiceImpl
 * 类描述: 
 * 创建人: zhangdehai
 * 创建时间:2018年2月28日 上午11:12:44
 */
@Service
public class ExChangeServiceImpl implements ExChangeService{
	
	private final static Logger log = LoggerFactory.getLogger(ExChangeServiceImpl.class);
	@Autowired
	private BeanGoodsService beanGoodsService;
	@Autowired
	private CouponInterfaceService couponInterfaceServiceImpl;
	@Autowired 
	private BeanOrderService beanOrderService;
	@Autowired
	private BeanOrderItemService beanOrderItemService;
	@Autowired
	@Qualifier("smokeBeanInterfaceServiceImpl")
	private SmokeBeanInterfaceService smokeBeanInterfaceServiceImpl;
	@Autowired
	private RedisService redisService;
	@Autowired
	private BeanCouponConsumeRecordService beanCouponConsumeRecordService;
	@Autowired
	private DistributedLockUtil distributedLockUtil;
	@Autowired
	private RedisBeanUtil<BeanGoods> redisBeanUtil;
	@Autowired
	private BeanAddressService beanAddressService;
	/**
	* Description:兑换商品(需要折扣券)
	* @author zhangdehai
	* @param ticket_id 优惠券Id
	* @param ticketValue 优惠券值
	* @param goodsId 商品id
	* @param exChangeNum 兑换数量
	* @param beanFans	用户对象
	* @return  
	* @date 2018年2月28日下午2:49:22
	 */
	@Transactional
	@Override
	public void exChangeGoods(String ticket_id, Integer ticketValue,Integer goodsId,
			Integer exChangeNum,BeanFans beanFans,Long addressId) {
		
			//1、保存订单
			BeanGoods beanGoods = beanGoodsService.findByKey(goodsId);
			if(beanGoods==null){
				throw new BeanException("兑换时,获取商品对象为空");
			}
			BeanOrder beanOrder = createBeanOrder(ticket_id, ticketValue, exChangeNum, beanGoods,beanFans,addressId);
			
			beanOrderService.save(beanOrder);
			BeanOrderItem beanOrderItem = createBeanOrderItem(beanOrder, exChangeNum, beanGoods);
			//2、保存订单明细
			beanOrderItemService.save(beanOrderItem);
			//3、保存优惠券消费流水
			BeanCouponConsumeRecord consumeRecord = createBeanCouponConsumeRecord(ticket_id, exChangeNum, "0", beanFans.getId());
			beanCouponConsumeRecordService.save(consumeRecord);
			//4、更新库存
			DistributedLock lock = distributedLockUtil.getDistributedLock(goodsId.toString());
			try {
			    if (lock.acquire()) {
			        //获取锁成功业务代码
			    	int result = beanGoodsService.updateGoodsStore(beanGoods,exChangeNum);
					if(result==0){
						log.error("兑换时,更新商品库存失败");
						throw new BeanException("更新商品库存失败");
					}
			    } else { // 获取锁失败
			        //获取锁失败业务代码
			    	throw new BeanException("系统繁忙,请稍后再试");
			    }
			} finally {
			    if (lock != null) {
			        lock.release();
			    }
			}
			
			Map<String, String> beanMap = null;
			//积分消费>0则调用乐豆接口
			if(beanOrder.getScore()>0){
				//5、扣减乐豆接口
				 beanMap = smokeBeanInterfaceServiceImpl.consumeFansBeans(beanFans.getOpenid(), beanOrder.getOrderNo(), beanOrder.getScore().toString(),"乐豆商城兑换", "乐豆商城兑换");
				 if(beanMap==null || beanMap.get("status")==null ){
					 	log.error("扣减乐豆接口返回值");
					 	//回退库存,与销量
					 	redisBeanUtil.rollBackFreezeStore(beanGoods.getId(),-exChangeNum);
						throw new BeanException("扣减乐豆失败");
				 }
				 if(!"0".equals(beanMap.get("status").toString())){
					//回退库存,与销量
					 redisBeanUtil.rollBackFreezeStore(beanGoods.getId(),-exChangeNum);
					 throw new BeanException(SmokeBeanInterfaceServiceImpl.getBeanCodeMap().get(beanMap.get("status").toString()));
				 }
			}
			
			 //6、消费优惠券接口
			 Map<String,String> couponMap = couponInterfaceServiceImpl.consumeCoupon(ticket_id, beanFans.getOpenid());
			 if(couponMap==null || couponMap.get("code")==null){
				 //回退乐豆
				 if(beanOrder.getScore()>0){
					 beanMap = smokeBeanInterfaceServiceImpl.rechargeFansBeans(beanFans.getOpenid(), beanOrder.getOrderNo()+OrderUtil.bulidBeanOrderNo(), beanOrder.getOrderNo()+OrderUtil.bulidBeanOrderNo(), beanOrder.getScore().toString(), "15", "乐豆商城退款", "乐豆商城退款");
				 }
				//回退库存,与销量
				 redisBeanUtil.rollBackFreezeStore(beanGoods.getId(),-exChangeNum);
				 log.error("调用扣减优惠券接口返回空值");
				 throw new BeanException("使用优惠券失败");
			 }
			 if(!"0".equals(couponMap.get("code"))){
				 //回退乐豆
				 if(beanOrder.getScore()>0){
					 beanMap = smokeBeanInterfaceServiceImpl.rechargeFansBeans(beanFans.getOpenid(), beanOrder.getOrderNo()+OrderUtil.bulidBeanOrderNo(), beanOrder.getOrderNo()+OrderUtil.bulidBeanOrderNo(), beanOrder.getScore().toString(), "15", "乐豆商城退款", "乐豆商城退款");
				 }
				 //回退库存,与销量
				 redisBeanUtil.rollBackFreezeStore(beanGoods.getId(),-exChangeNum);
				 throw new BeanException(couponMap.get("msg"));
			 }
	}

	/**
	* Description:创建订单实体
	* @author zhangdehai
	* @param ticket_id 优惠券Id
	* @param ticketValue 优惠券值
	* @param goodsId 商品id
	* @param exChangeNum 兑换数量
	* @param beanGoods	商品
	* @param beanFans	用户对象
	* @return  
	* @date 2018年2月28日下午2:49:22
	 */
	private BeanOrder createBeanOrder(String ticket_id, Integer ticketValue,
			Integer exChangeNum,BeanGoods beanGoods,BeanFans beanFans,Long addressId){
		BeanAddress beanAddress = beanAddressService.findByKey(addressId);
		if(beanAddress==null){
			throw new BeanException("兑换时,获取不到用户地址");
		}
		ticketValue=ticketValue!=null?ticketValue:0;
		BeanOrder beanOrder = new BeanOrder();
		beanOrder.setCoupon(ticketValue);//优惠券值
		beanOrder.setCouponId(ticket_id);//优惠券id
		beanOrder.setCreateTime(new Date());
		beanOrder.setOrderStatus("0");//新建 未完成
		beanOrder.setShippingStatus("0");
		beanOrder.setPhone(beanAddress.getPhone());//收货人手机
		beanOrder.setName(beanAddress.getName());//收货人姓名
		beanOrder.setCountyName(beanAddress.getCountyName());//区县
		beanOrder.setTownsName(beanAddress.getTownsName());//乡镇
		beanOrder.setProvinceName(beanAddress.getProvinceName());//省
		beanOrder.setCityName(beanAddress.getCityName());//市
		beanOrder.setDetailAddress(beanAddress.getDetailAddress());//详细地址
		String lastString = null;
		DistributedLock lock = distributedLockUtil.getDistributedLock("orderNo_"+beanGoods.getId().toString());
		try {
		    if (lock.acquire()) {
		        //获取锁成功业务代码
		    	lastString = beanOrderService.getLastOrderNo();
		    } else { // 获取锁失败
		        //获取锁失败业务代码
		    	throw new BeanException("系统繁忙,请稍后再试");
		    }
		} finally {
		    if (lock != null) {
		        lock.release();
		    }
		}
		beanOrder.setOrderNo(OrderUtil.bulidOrderNo(lastString));
		beanOrder.setOrderType("0");//0 默认兑换订单
		beanOrder.setGoodName(beanGoods.getName());
		if(beanGoods.getScore()*exChangeNum<=ticketValue){
			beanOrder.setScore(0);
		}else{
			beanOrder.setScore(beanGoods.getScore()*exChangeNum-ticketValue);
		}
		beanOrder.setShippingStatus("0");//未发货
		beanOrder.setDeleteStatus("0");//未删除
		beanOrder.setUserId(beanFans.getId());
		beanOrder.setOpenid(beanFans.getOpenid());
		return beanOrder;
	}
	
	/**
	* Description:创建订单明细
	* @author zhangdehai
	* @param order
	* @param exChangeNum
	* @param beanGoods
	* @return  
	* @date 2018年2月28日下午2:47:00
	 */
	private BeanOrderItem createBeanOrderItem(BeanOrder order,Integer exChangeNum,BeanGoods beanGoods){
		BeanOrderItem item = new BeanOrderItem();
		item.setCoupon(order.getCoupon());
		item.setGoodId(beanGoods.getId());
		item.setGoodImage(beanGoods.getGoodImage());
		item.setGoodName(beanGoods.getName());
		item.setGoodQuantities(exChangeNum);
		item.setOrderId(order.getId());
		item.setScore(beanGoods.getScore());
		return item;
		
	}
	
	/**
	* Description:创建优惠券
	* @author zhangdehai
	* @param ticket_id优惠券id
	* @param ticketValue  优惠券面值
	* @param type 来源  0紫光游戏， 。。。。。
	* @param userId 用户id
	* @return  
	* @date 2018年2月28日下午2:46:43
	 */
	private BeanCouponConsumeRecord createBeanCouponConsumeRecord(String ticket_id, Integer ticketValue,String type,Long userId){
		BeanCouponConsumeRecord consumeRecod = new BeanCouponConsumeRecord();
		consumeRecod.setAmount(ticketValue.longValue());
		consumeRecod.setCouponId(ticket_id);
		consumeRecod.setCreateTime(new Date());
		consumeRecod.setType(type);
		consumeRecod.setUserId(userId);
		return consumeRecod;
	}

	/**
	 * 无需折扣券
	 */
	@Transactional
	@Override
	public void beanExChangeGoods(Integer goodsId, Integer exChangeNum,
			BeanFans beanFans,Long addressId) {
		//1、保存订单
		BeanGoods beanGoods = beanGoodsService.findByKey(goodsId);
		if(beanGoods==null){
			throw new BeanException("兑换时,获取商品对象为空");
		}
		BeanOrder beanOrder = createBeanOrder(null, null, exChangeNum, beanGoods,beanFans,addressId);
		
		beanOrderService.save(beanOrder);
		BeanOrderItem beanOrderItem = createBeanOrderItem(beanOrder, exChangeNum, beanGoods);
		//2、保存订单明细
		beanOrderItemService.save(beanOrderItem);
		//3、更新库存
		DistributedLock lock = distributedLockUtil.getDistributedLock(goodsId.toString());
		try {
		    if (lock.acquire()) {
		        //获取锁成功业务代码
		    	int result = beanGoodsService.updateGoodsStore(beanGoods,exChangeNum);
				if(result==0){
					log.error("兑换时,更新商品库存失败");
					throw new BeanException("更新商品库存失败");
				}
		    } else { // 获取锁失败
		        //获取锁失败业务代码
		    	throw new BeanException("系统繁忙,请稍后再试");
		    }
		} finally {
		    if (lock != null) {
		        lock.release();
		    }
		}
		//5、扣减乐豆接口
		 Map<String, String> beanMap = smokeBeanInterfaceServiceImpl.consumeFansBeans(beanFans.getOpenid(), beanOrder.getOrderNo(), beanOrder.getScore().toString(),"乐豆商城兑换", "乐豆商城兑换");
		 if(beanMap==null || beanMap.get("status")==null ){
			 	log.error("扣减乐豆接口返回值");
			 	//回退库存,与销量
			 	redisBeanUtil.rollBackFreezeStore(beanGoods.getId(),-exChangeNum);
				throw new BeanException("扣减乐豆失败");
		 }
		 if(!"0".equals(beanMap.get("status").toString())){
			//回退库存,与销量
			 redisBeanUtil.rollBackFreezeStore(beanGoods.getId(),-exChangeNum);
			 throw new BeanException( SmokeBeanInterfaceServiceImpl.getBeanCodeMap().get(beanMap.get("status").toString()));
		 }
		
	}
}
