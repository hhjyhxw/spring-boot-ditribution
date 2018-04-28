/**
 * @author
 * @version
 * 2018年2月27日 上午9:17:45
 */
package com.icloud.web.business.front.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 类名称: SubscribeController
 * 类描述: 用户关注跳转
 * 创建人: zhangdehai
 * 创建时间:2018年2月27日 上午9:17:45
 */
@Controller
@RequestMapping(value = "${frontPath}/user")
public class SubscribeController {
	
	@RequestMapping(value="/toSubscribe")
	public String toSubscribe(){
		return "front/user/userSubscribe";
	}

}
