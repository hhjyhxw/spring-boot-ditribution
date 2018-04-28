//package com.icloud;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.icloud.exceptions.BeanException;
//import com.icloud.service.interfaces.CouponInterfaceService;
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes=BeanApplication.class)
//public class CounponTest {
// 
//	private final static Logger log = LoggerFactory.getLogger(CounponTest.class);
//	
//	@Autowired
//	private CouponInterfaceService couponInterfaceServiceImpl;
//	
//	public static String openid = "ocoMKt2a_9XrLt2NBG5CupS6THE4";
//	@Test
//	@Transactional  
//	@Rollback(false)// 事务自动回滚，默认是true。可以不写  
//	public void testCounpons() {
//		
//		Map<String,String> map = new HashMap<String,String>();
//		try{
//			String result = couponInterfaceServiceImpl.queryBeanCouponJsonString(openid, "2");
//			log.error(result);
//			log.error("查询成功");
//		}catch(BeanException e){
//			e.printStackTrace();
//			log.error(e.getMessage());
//		}catch(Exception e){
//			e.printStackTrace();
//			log.error(e.getMessage());
//		}
//	}
//	
//	
//}
//	
//	
//	
