//package com.icloud.config.quartz.job;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.icloud.common.util.ConfigUtil;
//import com.icloud.model.groupBuy.GroupBuy;
//import com.icloud.model.order.ActivityOrder;
//import com.icloud.model.order.ActivityOrderItem;
//import com.icloud.service.groupBuy.GroupBuyService;
//import com.icloud.service.order.ActivityGoodsService;
//import com.icloud.service.order.ActivityOrderService;
//import com.icloud.service.order.RefundService;
//import com.icloud.service.order.message.SendGroupBuyMessageService;
//
///**
// * 
// * 类名称: OrderStatusUpade
// * 类描述: 订单状态更新
// * 创建人: zdh
// * 创建时间:2017年9月25日 上午11:31:36
// */
//@Component
//@EnableScheduling
//public class OrderStatusUpade{
//	
//	public final static Logger log = LoggerFactory.getLogger(OrderStatusUpade.class);
//
//	@Autowired
//	private ActivityOrderService activityOrderService;
//	@Autowired
//	private ActivityGoodsService activityGoodsService;
//	@Autowired
//	private RefundService refundService;
//	@Autowired
//	private GroupBuyService groupBuyService;
//	@Autowired
//	private SendGroupBuyMessageService sendGroupBuyMessageService;
//	
//	/**
//	 * 1\关闭超时订单
//	 * @author   : zdh
//	 * @version  : 1.0  
//	 * @throws Exception   :
//	 * 处理超过8小时未支付的订单，关闭订单，减少冻结库存
//	 */
////	@Scheduled(cron = "0/30 * * * * ?")
//	@Scheduled(cron = "0 0/10 * * * ?")
//	public void orderStatusJoRun() throws Exception{
//		log.error("开始处理超过n小时未支付的订单======");
//		ActivityOrder order = new ActivityOrder();
//		order.setPayStatu("0");//未支付
//		// 查询出 下单未支付超过8小时的订单 配置
//		double d = (new Double(ConfigUtil.get("time"))*60*60*1000);
//		order.setTime((long)d);//下单 超时时间  单位毫秒
//		List<ActivityOrder> list = activityOrderService.getUppayOrderList(order);
//		log.error("处理条数list.size()======"+(list!=null?list.size():0));
//		int count = 0;
//		if(list!=null && list.size()>0){
//			for (ActivityOrder obj:list) {
//				try{
//					obj.setPayStatu("3");//3超时并关闭
//					activityOrderService.update(obj);
//					
//					List<ActivityOrderItem> itemList = obj.getOrderItemList();
//					for (ActivityOrderItem item:itemList) {
//						Map<String,Long> paramMap = new HashMap<String,Long>();
//						paramMap.put("goodId", item.getGoodId());
//						paramMap.put("quantity", item.getGoodQuantity());
//						activityGoodsService.cutActivityGoodsStore(paramMap);
//					}
//					count++;
//					
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		}
//		log.error("成功处理条数count======"+count);
//	}
//
//	/**
//	 * 2\退龙币
//	 * @author   : zdh
//	 * @version  : 1.0  
//	 * @throws Exception   :
//	 * 处理超过${}小时微信未支付的订单，龙币支付成功的，且已关闭的，退龙币
//	 */
////	@Scheduled(cron = "0/30 * * * * ?")
//	@Scheduled(cron = "0 0/30 * * * ?")
//	public void refundStatusJoRun() throws Exception{
//		log.error("开始处理已关闭的订单,退还龙币==========");
//		ActivityOrder order = new ActivityOrder();
//		order.setPayStatu("3");//已关闭的订单
//		order.setLongRefundStatu("0");//龙币未退款
//		order.setOrderType("1");//涉及龙币支付的订单
//		order.setLongPayStatu("2");//龙币支付成功的
//		//order.setTime(Long.valueOf(new BigDecimal(ConfigUtil.get("time")).toString())*60*60*1000);//下单 超时时间  单位毫秒
//		double d = (new Double(ConfigUtil.get("time"))*60*60*1000);
//		order.setTime((long)d);//下单 超时时间  单位毫秒
//		List<ActivityOrder> list = activityOrderService.getUppayOrderList(order);
//		log.error("处理条数list.size()======"+(list!=null?list.size():0));
//		int count = 0;
//		if(list!=null && list.size()>0){
//			for (ActivityOrder obj:list) {
//				try{
//					//创建退款单 并退龙币
//					Map<String,String> resultMap = refundService.quartRefundLongBi(obj, obj.getMemberId());
//					if("success".equals(resultMap.get("status"))){
//						log.error("定时器龙币退款成功");
//						obj.setLongRefundStatu("2");
//						activityOrderService.update(obj);
//						count++;
//					}
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//				
//			}
//		}
//		log.error("定时器成功退款龙币数条数count======"+count);
//	}
//	
//	/**
//	 * 
//	 * 3\未开团记录处理
//	 * @author   : zdh
//	 * @version  : 1.0  
//	 * @throws Exception   :
//	 *获取未开团 ，团的创建时间超过 指定时间的团记录
//	 */
////	@Scheduled(cron = "0/30 * * * * ?")
//	@Scheduled(cron = "0 0/10 * * * ?")
//	public void  groupBuyStatusRun() throws Exception{
//		GroupBuy groupBuyParam = new GroupBuy();
//		groupBuyParam.setStatus("0");//状态为未开团
//		//groupBuyParam.setTime(Long.valueOf(new BigDecimal(ConfigUtil.get("time")).toString())*60*60*1000);//下单 超时时间  单位毫秒
//		double d = (new Double(ConfigUtil.get("time"))*60*60*1000);
//		groupBuyParam.setTime((long)d);//下单 超时时间  单位毫秒
//		List<GroupBuy> groupBuyList = groupBuyService.getUppayGroupList(groupBuyParam);
//		log.error("处理条数groupBuyList.size()======"+(groupBuyList!=null?groupBuyList.size():0));
//		int count = 0;
//		if(groupBuyList!=null && groupBuyList.size()>0){
//			for (GroupBuy groupBuy:groupBuyList) {
//				try{
//					groupBuy.setStatus("3");;//3超时并关闭
//					groupBuyService.updateStatus(groupBuy);
//					count++;
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		}
//		log.error("成功处理条数groupBuyList count======"+count);
//	}
//	
//	/**
//	 * 4\已开团  未成团
//	 * @author   : zdh
//	 * @version  : 1.0  
//	 * @throws Exception   :
//	 *每半小时处理一次（单应用应该每天晚上处理比较合理） 
//	 *获取已开团、开团时间结束、状态  进为行中、未成团 的团记录 ，进行退款
//	 */
////	@Scheduled(cron = "0/30 * * * * ?")
//	@Scheduled(cron = "0 0/10 * * * ?")
//	public void refundGroupBuy() throws Exception{
//		GroupBuy groupBuyParam = new GroupBuy();
//		groupBuyParam.setStatus("1");//状态为进行中的
////		groupBuyParam.setOrStatus("4");//状态为拼团失败的(处理因为时间间隙：如服务暂停等问题，导致部分拼团订单无法退款款问题)
//		groupBuyParam.setEndTime(new Date());//活动时间结束时间  比 当前时间小的
//		List<GroupBuy> groupBuyList = groupBuyService.getList(groupBuyParam);
//		log.error("已开团、未成团的团数以及拼团失败=========="+(groupBuyList!=null?groupBuyList.size():0));
//		ActivityOrder orderParam = new ActivityOrder();
//		if(groupBuyList!=null && groupBuyList.size()>0){
//			for(GroupBuy groupBuy : groupBuyList){
//				if("0".equals(groupBuy.getMakeSuccess())){//参团有效时间结束 是否成团 ，0 拼团失败；1拼团成功
//					orderParam.setPayStatu("2");//已支付的
//					orderParam.setOrderType("2");//拼团订单
//					orderParam.setRefundStatu("0");//未退款的
//					orderParam.setTeamId(groupBuy.getId());
//					List<ActivityOrder> list = activityOrderService.findForList(orderParam);
//					log.error("已开团、未成团数订单条数list.size()======"+(list!=null?list.size():0));
//					int count = 0;
////					list = null;
////					//先在此测试，看看查询是否正确
//					if(list!=null && list.size()>0){
//						for (ActivityOrder order:list) {
//							try{
//								//创建退款单
//								Map<String,String> resultMap = refundService.toRefund(order);
//								if("success".equals(resultMap.get("status"))){
//									log.error("定时器处理开团后人数不足退款成功");
//									order.setRefundStatu("2");//退款成功
//									activityOrderService.update(order);
//									count++;
//									try{
//										//发送拼团失败模板消息
//										sendGroupBuyMessageService.sendFailResultMessage(order, groupBuy);
//									}catch(Exception e){
//										e.printStackTrace();
//									}
//								}
//							}catch(Exception e){
//								e.printStackTrace();
//							}
//						}
//					}
//					groupBuy.setStatus("4");//已开始 未成团 ，时间到改为拼团失败
//					groupBuyService.updateStatus(groupBuy);
//					log.error("成功处理 已开团、未成团数订单条数count======"+count);
//				}else{
//					groupBuy.setStatus("2");//已开始 ，时间到 改为拼团成功
//					groupBuyService.updateStatus(groupBuy);
//				}
//			}
//		}
//	}
//	
//	
//	/**
//	 * 4.2\拼团失败  这个定时器将会消耗比较多资源
//	 * @author   : zdh
//	 * @version  : 1.0  
//	 * @throws Exception   :
//	 *每10分钟处理一次（单应用应该每天晚上处理比较合理） 
//	 *获取已开团、开团时间结束、状态未由  进行中改为 已 开始、未成团 的团记录
//	 */
////	@Scheduled(cron = "0/30 * * * * ?")
//	@Scheduled(cron = "0 0/10 * * * ?")
//	public void refundGroupBuyGroupFail() throws Exception{
//		GroupBuy groupBuyParam = new GroupBuy();
//		groupBuyParam.setStatus("4");//状态为拼团失败的(处理因为时间间隙：如服务暂停等问题，导致部分拼团订单无法退款款问题)
//		groupBuyParam.setEndTime(new Date());//活动时间结束时间  比 当前时间小的
//		List<GroupBuy> groupBuyList = groupBuyService.getList(groupBuyParam);
//		log.error("拼团失败=========="+(groupBuyList!=null?groupBuyList.size():0));
//		ActivityOrder orderParam = new ActivityOrder();
//		if(groupBuyList!=null && groupBuyList.size()>0){
//			for(GroupBuy groupBuy : groupBuyList){
//				orderParam.setPayStatu("2");//已支付的
//				orderParam.setOrderType("2");//拼团订单
//				orderParam.setRefundStatu("0");//未退款的
//				orderParam.setTeamId(groupBuy.getId());
//				List<ActivityOrder> list = activityOrderService.findForList(orderParam);
//				log.error("已开团、未成团数订单条数list.size()======"+(list!=null?list.size():0));
//				int count = 0;
////				list = null;
////				//先在此测试，看看查询是否正确
//				if(list!=null && list.size()>0){
//					for (ActivityOrder order:list) {
//						try{
//							//创建退款单
//							Map<String,String> resultMap = refundService.toRefund(order);
//							if("success".equals(resultMap.get("status"))){
//								log.error("定时器处理开团后人数不足退款成功");
//								order.setRefundStatu("2");//退款成功
//								activityOrderService.update(order);
//								count++;
//								try{
//									//发送拼团失败模板消息
//									sendGroupBuyMessageService.sendFailResultMessage(order, groupBuy);
//								}catch(Exception e){
//									e.printStackTrace();
//								}
//							}
//						}catch(Exception e){
//							e.printStackTrace();
//						}
//					}
//				}
//				groupBuy.setStatus("4");//已开始 未成团
//				groupBuyService.updateStatus(groupBuy);
//				log.error("成功处理 已开团、未成团数订单条数count======"+count);
//			}
//		}
//	}
//	
//	/**
//	 * 5\成团
//	 * 
//	 * @author   : zdh
//	 * @version  : 1.0  
//	 * @throws Exception   :
//	 *每半小时处理一次（单应用应该每天晚上处理比较合理） 
//	 *获取已开团、开团时间结束、状态未由  进行中改为 已 开始、未成团 的团记录
//	 */
////	@Scheduled(cron = "0/30 * * * * ?")
//	@Scheduled(cron = "0 0/3 * * * ?")
//	public void sendGroupBuySuccessMessage() throws Exception{
//		GroupBuy groupBuyParam = new GroupBuy();
//		groupBuyParam.setStatus("2");//状态为已成团
//		groupBuyParam.setSended("0");//未发送成团通知的
//		List<GroupBuy> groupBuyList = groupBuyService.getList(groupBuyParam);
//		log.error("已成团、未发送成团通知的数=========="+(groupBuyList!=null?groupBuyList.size():0));
//		ActivityOrder orderParam = new ActivityOrder();
//		if(groupBuyList!=null && groupBuyList.size()>0){
//			for(GroupBuy groupBuy : groupBuyList){
//				orderParam.setPayStatu("2");//已支付的
//				orderParam.setOrderType("2");//拼团订单
//				orderParam.setRefundStatu("0");//未退款的
//				orderParam.setTeamId(groupBuy.getId());
//				List<ActivityOrder> list = activityOrderService.findForList(orderParam);
//				log.error("已成团、未发送成团通知的数订单条数list.size()======"+(list!=null?list.size():0));
//				int count = 0;
//				if(list!=null && list.size()>0){
//					for (ActivityOrder order:list) {
//						count++;
//						try{
//							//发送拼团失败模板消息
//							boolean b = sendGroupBuyMessageService.sendSuccessResultMessage(order, groupBuy);
//							if(b){
//								count++;
//							}
//						}catch(Exception e){
//							e.printStackTrace();
//						}
//					}
//				}
//				groupBuy.setSended("1");
//				groupBuyService.updateStatus(groupBuy);
//				log.error("成功处理 已成团、未发送成团通知订单条数count======"+count);
//			}
//		}
//	}
//	
//	/**
//	 * 6\团主免单退款
//	 * @author   : zdh
//	 * @version  : 1.0  
//	 * @throws Exception   :
//	 *每半小时处理一次（单应用应该每天晚上处理比较合理） 
//	 *获取已开团、开团时间结束、状态未由  进行中改为 已 开始、未成团 的团记录
//	 */
////	@Scheduled(cron = "0/30 * * * * ?")
//	@Scheduled(cron = "0 0/30 * * * ?")
//	public void sendGroupBuyMainFreeMessage() throws Exception{
//		GroupBuy groupBuyParam = new GroupBuy();
//		groupBuyParam.setStatus("2");//状态为已成团
//		groupBuyParam.setGroupType("0");//团主免单
//		List<GroupBuy> groupBuyList = groupBuyService.getList(groupBuyParam);
//		log.error("团主免单退款 团数=========="+(groupBuyList!=null?groupBuyList.size():0));
//		ActivityOrder orderParam = new ActivityOrder();
//		if(groupBuyList!=null && groupBuyList.size()>0){
//			for(GroupBuy groupBuy : groupBuyList){
//				orderParam.setPayStatu("2");//已支付的
//				orderParam.setOrderType("2");//拼团订单
//				orderParam.setRefundStatu("0");//未退款的
//				orderParam.setShippStatu("1");//已发货
//				orderParam.setMemberId(groupBuy.getMemberId());//团主id
//				orderParam.setTeamId(groupBuy.getId());//团id
//				List<ActivityOrder> list = activityOrderService.findForList(orderParam);
//				log.error("团主免单退款 获取订单条数list.size()======"+(list!=null?list.size():0));
//				int count = 0;
//				if(list!=null && list.size()>0){
//					for (ActivityOrder order:list) {
//						count++;
//						try{
//							//创建退款单
//							Map<String,String> resultMap = refundService.toRefund(order);
//							if("success".equals(resultMap.get("status"))){
//								log.error("团主免单退款成功");
//								order.setRefundStatu("2");//退款成功
//								activityOrderService.update(order);
//								count++;
//								try{
//									//发送模板消息
//									sendGroupBuyMessageService.sendMainFreeMessage(order, groupBuy);
//								}catch(Exception e){
//									e.printStackTrace();
//								}
//							}
//						}catch(Exception e){
//							e.printStackTrace();
//						}
//					}
//				}
//				groupBuy.setSended("1");
//				groupBuyService.updateStatus(groupBuy);
//				log.error("团主免单退款 处理订单条数count======"+count);
//			}
//		}
//	}
//}
