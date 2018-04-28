package com.icloud.web.business.front.address;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icloud.model.business.BeanAddress;
import com.icloud.service.business.BeanAddressService;
import com.icloud.service.business.BeanAreaService;
import com.icloud.web.AppBaseController;

/**
 * 类名称: OrderAddressController
 * 类描述: 在兑换商品 的时候 用户切换地址 或者添加地址成功后 返回兑换页面
 * 创建人: zhangdehai
 * 创建时间:2018年3月6日 上午11:44:13
 */
@Controller
@RequestMapping(value = "${frontPath}/orderAddress")
public class OrderAddressController extends AppBaseController{

	@Autowired
	private BeanAddressService beanAddressService;
	
	@Autowired
	private BeanAreaService beanAreaService;
	
	/**
	* Description:跳转地址列表
	* @author zhangdehai
	* @param request
	* @param response
	* @param t
	* @return
	* @throws Exception  
	* @date 2018年3月6日上午11:12:31
	 */
	@RequestMapping("/orderAddressList")
	public String myAddressList(HttpServletRequest request,HttpServletResponse response,
			BeanAddress t,String url) throws Exception {
		//保存重定向url，在兑换页面中切换地址或者新添加地址成功后 重定向到兑换页面
		request.getSession().setAttribute("orderAddress_redirectUrl", url);
		t.setUserId(getMemberId());
		List<BeanAddress> orderAddressList = beanAddressService.findList(t);
		request.setAttribute("orderAddressList", orderAddressList);
		return "front/orderAddressList";
	}
	
	/**
	 * 进入收货地址添加页面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/toOrderAddressAdd")
	public String toAddressEdit(HttpServletRequest request,HttpServletResponse response,
			BeanAddress address) throws Exception {
		return "front/orderAddressAdd";
	}
	
	/**
	* Description:在兑换时 添加地址
	* @author zhangdehai
	* @param request
	* @param response
	* @param t
	* @return
	* @throws Exception  
	* @date 2018年3月6日上午11:11:33
	 */
	@RequestMapping("/orderAddressSave")
	public String orderAddressSave(HttpServletRequest request,HttpServletResponse response,
			BeanAddress t) throws Exception {
		String redirctUrl = (String) request.getSession().getAttribute("orderAddress_redirectUrl");
		
		t.setUserId(getMemberId());
		t.setDefaultAddr("1");
		beanAddressService.saveAndUpdate(t);
	
		return "redirect:"+redirctUrl;
	}
	
	
	/**
	* Description:在兑换时 切换地址
	* @author zhangdehai
	* @param request
	* @param response
	* @param t
	* @return
	* @throws Exception  
	* @date 2018年3月6日上午11:11:33
	 */
	@RequestMapping("/orderAddressChange")
	public String orderAddressChange(HttpServletRequest request,HttpServletResponse response,
			Long id,BeanAddress t) throws Exception {
		String redirctUrl = (String) request.getSession().getAttribute("orderAddress_redirectUrl");
		t.setUserId(getMemberId());
		t.setDefaultAddr("1");
		beanAddressService.saveAndUpdate(t);
		return "redirect:"+redirctUrl;
	}
}
