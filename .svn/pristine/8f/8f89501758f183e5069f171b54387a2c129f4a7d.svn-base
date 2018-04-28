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
//import com.icloud.common.util.ConfigUtil;
//import com.icloud.dao.order.ActivityOrderItemMapper;
//import com.icloud.model.order.ActivityOrder;
//import com.icloud.model.order.ActivityOrderItem;
//import com.icloud.model.order.ActivityRefund;
//import com.icloud.service.order.ActivityOrderItemService;
//import com.icloud.service.order.ActivityOrderService;
//import com.icloud.service.order.ActivityRefundService;
//import com.icloud.service.order.CreateRefundService;
//
////import com.icloud.dao.CloudEventMapper;
////import com.icloud.service.MerchantsMpService;
//
//
//@RunWith(SpringRunner.class)
////@SpringApplicationConfiguration(classes = WebManagerStarter.class)
//@SpringBootTest(classes=AppInterfaceApplication.class)
//public class OrderTest {
// 
////
////	@Value("${local.server.port}")
////	private int port;
//// 
////	@Autowired
////	private RestTemplate restTemplate = new TestRestTemplate();
//	@Autowired
//	private ActivityOrderItemService activityOrderItemService;
//	@Autowired
//	private ActivityOrderService activityOrderService;
//	@Autowired
//	private ActivityOrderItemMapper activityOrderItemMapper;
//	@Autowired
//	private ActivityRefundService activityRefundService;
//	@Autowired
//	private CreateRefundService createRefundService;
//    
//	@Test
//	@Transactional  
//	@Rollback(false)// 事务自动回滚，默认是true。可以不写  
//	public void mpDisable() {
//		
//		try {
//			ActivityOrder order = new ActivityOrder();
//			order.setPayStatu("3");//已关闭的订单
//			order.setLongRefundStatu("0");//龙币未退款
//			order.setOrderType("1");//涉及龙币支付的订单
//			order.setLongPayStatu("2");//龙币支付成功的
//			order.setTime(Long.valueOf(ConfigUtil.get("time"))*60*60*1000);//下单 超时时间  单位毫秒
//			List<ActivityOrder> list = activityOrderService.getUppayOrderList(order);
//			for (ActivityOrder t:list) {
//				System.err.println(t.getReceiverName());
//				// 查询是否有退款单
//				ActivityRefund refund = new ActivityRefund();
//				refund.setOrderId(t.getId());
//				List<ActivityRefund> activityRefundList = activityRefundService.findList(refund);
//				if(activityRefundList!=null && activityRefundList.size()>0){
//					refund = activityRefundList.get(0);
//				}else{
//					refund = createRefundService.createRefund(t);
//					refund.setRefundStatu("0");
//				}
//				System.err.println("refund.getLongRefundStatu()======"+refund.getLongRefundStatu());
//				System.err.println("t.getLongRefundStatu()======"+t.getLongRefundStatu());
//				System.err.println("orderId======"+t.getId());
//				System.err.println("refundId======"+refund.getId());
//			}
//		
//			System.err.println("处理条数list.size()======"+(list!=null?list.size():0));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
// 
//	
//	
//}
//	
//	
//	
