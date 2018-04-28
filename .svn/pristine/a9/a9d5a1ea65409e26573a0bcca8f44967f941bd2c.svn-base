package com.icloud.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.icloud.common.ConfigUtil;
import com.icloud.common.MD5Utils;
import com.icloud.common.ResponseUtils;
import com.icloud.model.bms.BmsAdmin;
import com.icloud.model.bms.BmsMenu;
import com.icloud.service.bms.AdminService;
import com.icloud.service.bms.MenuService;


@Controller
public class AdminLoginController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping("/tologin")
	public String tologin(HttpSession session){
		if(null!=session.getAttribute("admin_user")&&null!=session.getAttribute("admin_menu")){
			return "index";
		}
		return "login";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam String account,@RequestParam String password,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception{
		BmsAdmin admin = new BmsAdmin();
		admin.setAccount(account);
		admin.setPassWord(MD5Utils.encode2hex(password));
		admin = adminService.login(admin);
		if(null==admin){
			ResponseUtils.renderText(response, "0001");
		}else if(admin.getIsLock().equals("1")){
			ResponseUtils.renderText(response, "0002");
		}else{
			session.setAttribute("admin_user", admin);
			session.setAttribute("pictureVisitUrl",ConfigUtil.get("pictureVisitUrl"));
			List<BmsMenu> list = menuService.selectMenuByUser(admin);
			session.setAttribute("admin_menu", list);
			//return "redirect:/admin";
			ResponseUtils.renderText(response, "0000");
		}
		return null;
		//return "redirect:/tologin";
		
		
		
	}
	
	@RequestMapping("/admin")
	public String index(HttpServletRequest request,HttpSession session){
		if(null!=session.getAttribute("admin_user")){		
			//取出用戶權限
			BmsAdmin admin = (BmsAdmin) session.getAttribute("admin_user");
			List<BmsMenu> list = menuService.selectMenuByUser(admin);
			session.setAttribute("admin_menu", list);
			JSONArray navObj = new JSONArray();
			for(BmsMenu menu:list){
				if(menu.getParentId().longValue()==0){
					JSONObject obj = new JSONObject();
					obj.put("title", menu.getMenuName());
					obj.put("icon", "&#xe621;");
					obj.put("spread", true);
					JSONArray array = new JSONArray();
					for(BmsMenu submenu:list){
						if(submenu.getParentId().intValue()==menu.getId().intValue()){
							JSONObject subobj = new JSONObject();
							subobj.put("title", submenu.getMenuName());
							subobj.put("icon", "&#xe621;");
							subobj.put("href", submenu.getMenuUrl());
							array.add(subobj);
						}
						
					}
					obj.put("children", array);
					navObj.add(obj);
				}
					
			}
			request.setAttribute("nav_obj", navObj.toJSONString());
			return "index";
		}
		
		return "tologin";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute("admin_user");
		session.removeAttribute("admin_menu");
		return "tologin";
	}
	
	
}
