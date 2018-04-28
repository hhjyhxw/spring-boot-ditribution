//package com.icloud;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.icloud.model.groupBuy.GroupBuy;
//import com.icloud.model.order.ActivityOrder;
//import com.icloud.service.groupBuy.GroupBuyService;
//import com.icloud.service.order.ActivityOrderService;
//import com.icloud.service.order.message.SendGroupBuyMessageService;
//
////import com.icloud.dao.CloudEventMapper;
////import com.icloud.service.MerchantsMpService;
//
//
//@RunWith(SpringRunner.class)
////@SpringApplicationConfiguration(classes = WebManagerStarter.class)
//@SpringBootTest(classes=AppInterfaceApplication.class)
//public class GroupTest {
// 
////
////	@Value("${local.server.port}")
////	private int port;
//// 
//
//	@Autowired
//	private GroupBuyService groupBuyService;
//	@Autowired
//	private SendGroupBuyMessageService sendGroupBuyMessageService;
//	@Autowired
//	private ActivityOrderService activityOrderService;
//	@Test
//	@Transactional  
//	@Rollback(false)// 事务自动回滚，默认是true。可以不写  
//	public void mpDisable() {
//			GroupBuy groupBuyParam = new GroupBuy();
//			groupBuyParam.setStatus("2");//状态为已成团
//			groupBuyParam.setSended("0");//未发送成团通知的
//			List<GroupBuy> groupBuyList = groupBuyService.getList(groupBuyParam);
//			System.err.println("已成团、未发送成团通知的数=========="+(groupBuyList!=null?groupBuyList.size():0));
//			ActivityOrder orderParam = new ActivityOrder();
//			if(groupBuyList!=null && groupBuyList.size()>0){
//				for(GroupBuy groupBuy : groupBuyList){
//					orderParam.setPayStatu("2");//已支付的
//					orderParam.setOrderType("2");//拼团订单
//					orderParam.setRefundStatu("0");//未退款的
//					orderParam.setTeamId(groupBuy.getId());
//					List<ActivityOrder> list = activityOrderService.findForList(orderParam);
//					System.err.println("已成团、未发送成团通知的数订单条数list.size()======"+(list!=null?list.size():0));
//					int count = 0;
//					if(list!=null && list.size()>0){
//						for (ActivityOrder order:list) {
//							count++;
//							try{
//								//发送拼团失败模板消息
//								boolean b = sendGroupBuyMessageService.sendSuccessResultMessage(order, groupBuy);
//								if(b){
//									count++;
//								}
//							}catch(Exception e){
//								e.printStackTrace();
//							}
//						}
//					}
//					groupBuy.setSended("1");
//					groupBuyService.updateStatus(groupBuy);
//					System.err.println("处理条数list.size()======"+(list!=null?list.size():0));
//				}
//			}
//	}
//	
//	
//}
//	
//	
//	
