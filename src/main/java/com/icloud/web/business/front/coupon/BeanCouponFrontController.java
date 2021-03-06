package com.icloud.web.business.front.coupon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.icloud.common.ResponseUtils;
import com.icloud.service.interfaces.CouponInterfaceService;
import com.icloud.vo.BeanCouponVo;
import com.icloud.web.AppBaseController;

/**
 * 类名称: BeanCouponFrontController
 * 类描述: 紫光优惠券控制层
 * 创建人: zhangdehai
 * 创建时间:2018年3月6日 上午11:44:49
 */
@Controller
@RequestMapping(value = "${frontPath}/beanCoupon")
public class BeanCouponFrontController extends AppBaseController{
	
	@Autowired
	private CouponInterfaceService couponInterfaceServiceImpl;

//	/**
//	 * 测试使用
//	* Description:紫光优惠券查询
//	* @author zhangdehai
//	* @param type 0:可用抵价券，1：已消耗，2：全部
//	* @throws Exception  
//	* @date 2018年2月27日下午4:54:03
//	 */
//	@RequestMapping(value = "/queryCouponsList")
//	@ResponseBody
//	public String queryCouponsList(String type) throws Exception{
//		int y=0;
//		List<BeanCouponVo> list = new ArrayList<BeanCouponVo>();
//		for (int i = 0; i < 10; i++) {
//			BeanCouponVo vo = new BeanCouponVo();
//			vo.setName("折扣券");
//			vo.setStat(y+"");
//			y++;
//			if(y>2){
//				y=0;
//			}
//			vo.setTicket_id("20180307");
//			vo.setVali(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
//			 int s= (int) (Math.floor(Math.random()*10+1)*10);
//			vo.setValue(s+"");
//			list.add(vo);
//		}
//		return pakageJsonp(list);
//	}
//	
//	/**
//	 * 测试使用
//	* Description:
//	* @author zhangdehai
//	* @param type
//	* @return
//	* @throws Exception  
//	* @date 2018年3月7日上午10:43:36
//	 */
//	@RequestMapping(value = "/queryCouponsCount")
//	@ResponseBody
//	public String queryCouponsCount(String type) throws Exception{
//		Map<String,String> map = new HashMap<String,String>();
//		map.put(STATUS, SUCCESS);
//		map.put("cardCount", "10");
//		return pakageJsonp(map);
//	}
	
	/**
	* Description:紫光优惠券查询
	* @author zhangdehai
	* @param type 0:可用抵价券，1：已消耗，2：全部
	* @throws Exception  
	* @date 2018年2月27日下午4:54:03
	 */
	@RequestMapping(value = "/queryCouponsList")
	@ResponseBody
	public String queryCouponsList(String type) throws Exception{
		String result = couponInterfaceServiceImpl.queryBeanCouponJsonString(getOpenId(), type);
		if(result!=null){
			ResponseUtils.renderJson(respone,result);
		}
		return null;
	}
	
	
	/**
	* Description:获取我的可用卡券数量
	* @author zhangdehai
	* @param type
	* @return
	* @throws Exception  
	* @date 2018年3月5日上午9:29:03
	 */
	@RequestMapping(value = "/queryCouponsCount")
	@ResponseBody
	public String queryCouponsCount(String type) throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put(STATUS, SUCCESS);
		try{
			String result = couponInterfaceServiceImpl.queryBeanCouponJsonString(getOpenId(), type);
			if(result!=null){
				List<BeanCouponVo> list = JSON.parseArray(result, BeanCouponVo.class);
				if(list!=null){
					map.put("cardCount", list.size()+"");
					return pakageJsonp(map);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		map.put("cardCount", "0");
		return pakageJsonp(map);
	}
	
	
	/**
	* Description:我的卡券列页面
	* @author zhangdehai
	* @param type
	* @return
	* @throws Exception  
	* @date 2018年3月5日下午5:09:24
	 */
	@RequestMapping(value = "/myCardList")
	public String myCardList() throws Exception{
		return "front/myCardList";
	}
}
