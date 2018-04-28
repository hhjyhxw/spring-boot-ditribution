package com.icloud.web.business.front.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icloud.common.ConfigUtil;
import com.icloud.exceptions.BeanException;
import com.icloud.model.business.BeanFans;
import com.icloud.model.business.BeanOrder;
import com.icloud.model.business.BeanOrderItem;
import com.icloud.service.business.BeanFansService;
import com.icloud.service.business.BeanOrderService;
import com.icloud.service.business.ExChangeService;
import com.icloud.vo.OrderVo;
import com.icloud.web.AppBaseController;

/**
 * 类名称: BeanOrderFrontController
 * 类描述: 订单兑换控制
 * 创建人: zhangdehai
 * 创建时间:2018年3月6日 上午11:45:55
 */
@Controller
@RequestMapping(value = "${frontPath}/beanOrder")
public class BeanOrderFrontController extends AppBaseController{
	
	@Autowired
	private BeanOrderService beanOrderService;
	@Autowired
	private ExChangeService exChangeService;
	@Autowired
	private BeanFansService beanFansService;
	/**
	* Description:我的订单列表
	* @author zhangdehai
	* @throws Exception  
	* @date 2018年2月27日下午5:10:43
	 */
	@RequestMapping(value = "/myOrderList")
	public String orderList() throws Exception{
		return "front/myOrderList";
	}

	/**
	* Description:切换订单状态查询
	* @author zhangdehai
	* @return
	* @throws Exception  
	* @date 2018年3月6日上午9:33:00
	 */
	@RequestMapping(value = "/orderListJson")
	@ResponseBody
	public String orderListJson(String shippingStatus) throws Exception{
		BeanOrder t = new BeanOrder();
		t.setUserId(getMemberId());
		if(shippingStatus!=null && !"".equals(shippingStatus)){
			t.setShippingStatus(shippingStatus);
		}
		List<BeanOrder> beanOrderList = beanOrderService.findList(t);
		if(beanOrderList!=null && beanOrderList.size()>0){
			List<OrderVo> newLsit = new ArrayList<OrderVo>();
			for(BeanOrder beanOrder : beanOrderList){
				OrderVo vo = new OrderVo();
				vo.setId(beanOrder.getId());
				vo.setCoupon(beanOrder.getCoupon());
				vo.setCreateTime(DateUtils.format(beanOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				vo.setShippingStatus(beanOrder.getShippingStatus());
				vo.setScore(beanOrder.getScore());
				
				vo.setTotalScore(vo.getCoupon()+vo.getScore());
				List<BeanOrderItem> itemList = beanOrder.getOrderItemList();
				if(itemList!=null && itemList.size()>0){
					vo.setName(itemList.get(0).getGoodName());
					vo.setGoodImage(ConfigUtil.get("pictureVisitUrl")+itemList.get(0).getGoodImage());
				}
				newLsit.add(vo);
			}
			return pakageJsonp(newLsit);
		}
		return null;
	}
	
	/**
	* Description:兑换商品(需要折扣券)
	* 1、在页面判断 是否存在卡券
	* 2、在页面判断乐豆是否足够
	* @author zhangdehai
	* @param ticket_id 代金券id
	* @param goodsId	商品Id
	* @param exChangeNum 兑换数量
	* @param addressId 地址id
	* @return
	* @date 2018年2月28日上午10:10:26
	 */
	@RequestMapping(value = "/exChangeGoods")
	@ResponseBody
	public String exChangeGoods(String ticket_id,Integer ticketValue,
			Integer goodsId,Integer exChangeNum,Long addressId){
		Map<String,String> map = new HashMap<String,String>();
		BeanFans beanFans = null;
		try{
			Object obj = request.getSession().getAttribute("beanFans");
			if(obj!=null){
				beanFans = (BeanFans)obj;
			}
			//测试使用
			if(beanFans==null){
				beanFans = beanFansService.findByKey(1L);
			}
			exChangeService.exChangeGoods(ticket_id, ticketValue, goodsId, exChangeNum,beanFans,addressId);
			map.put(STATUS, SUCCESS);
			map.put(MESSAGE, "兑换成功");
		}catch(BeanException e){
			e.printStackTrace();
			map.put(STATUS, ERROR);
			map.put(MESSAGE,e.getMessage());
			log.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			map.put(STATUS, ERROR);
			map.put(MESSAGE,"兑换失败，请稍后再试");
			log.error(e.getMessage());
		}
		return pakageJsonp(map);
	}
	
	
	/**
	* Description:纯商品兑换,无需折扣券
	* @author zhangdehai
	* @param goodsId
	* @param exChangeNum
	* @param addressId
	* @return  
	* @date 2018年3月6日上午10:33:22
	 */
	@RequestMapping(value = "/beanExChangeGoods")
	@ResponseBody
	public String beanExChangeGoods(Integer goodsId,Integer exChangeNum,Long addressId){
		Map<String,String> map = new HashMap<String,String>();
		BeanFans beanFans = null;
		try{
			Object obj = request.getSession().getAttribute("beanFans");
			if(obj!=null){
				beanFans = (BeanFans)obj;
			}
			//测试使用
			if(beanFans==null){
				beanFans = beanFansService.findByKey(1L);
			}
			exChangeService.beanExChangeGoods(goodsId, exChangeNum,beanFans,addressId);
			map.put(STATUS, SUCCESS);
			map.put(MESSAGE, "兑换成功");
		}catch(BeanException e){
			e.printStackTrace();
			map.put(STATUS, ERROR);
			map.put(MESSAGE,e.getMessage());
			log.error(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			map.put(STATUS, ERROR);
			map.put(MESSAGE,"兑换失败，请稍后再试");
			log.error(e.getMessage());
		}
		return pakageJsonp(map);
	}
	
}
