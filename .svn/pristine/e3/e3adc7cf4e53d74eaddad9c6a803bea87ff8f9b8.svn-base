package com.icloud.web.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.ResponseUtils;
import com.icloud.model.bms.BmsMenu;
import com.icloud.model.bms.BmsRoleMenu;
import com.icloud.service.bms.MenuService;
import com.icloud.service.bms.RoleMenuService;

@Controller
@RequestMapping(value = "${backPath}/admin")
public class MenuController extends AdminBaseController<BmsMenu> {
	
	@Autowired
	private MenuService menuService;
	@Autowired
	private RoleMenuService roleMenuService;
	
	
	
	@RequestMapping("/menu_list")
	public String list(HttpServletRequest request,BmsMenu menu) throws Exception{
	  PageInfo<BmsMenu> page = menuService.findByPage(1, 10, menu);
	  request.setAttribute("page", page);
	  return "bms/menu_list";
	}
	
	@RequestMapping("/menu_getlist")
	public String getList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String menuName = request.getParameter("menuName");
		String pageNo = request.getParameter("pageNo");		
		JSONObject json = new JSONObject();
		BmsMenu menu = new BmsMenu();
		if(StringUtils.isNotBlank(menuName)){
			menu.setMenuName(menuName);
		}
		PageInfo<BmsMenu> page = menuService.findByPage(StringUtils.isNotBlank(pageNo)?Integer.parseInt(pageNo):1, 10, menu);
		List<BmsMenu> list = page.getList();
		json.put("pages", menu.getPages());
		JSONArray array = new JSONArray();
		for(BmsMenu m:list){
			JSONObject sub = new JSONObject();
			sub.put("id",m.getId());
			sub.put("menuName", m.getMenuName());
			sub.put("menuUrl", m.getMenuUrl());
			
			if(m.getParentId().intValue()==0){
				sub.put("parentName", "一级菜单");
			}else{
				sub.put("parentName",m.getParent().getMenuName());
			}
			
			array.add(sub);
		}
		json.put("list", array);
		ResponseUtils.renderJson(response, json.toJSONString());
		return null;
		
		
	}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/menu_to_input")
	public String toinput(Integer id, HttpServletRequest request) throws Exception{
		if(id!=null){
			BmsMenu menu = menuService.findByKey(id);
			request.setAttribute("menu", menu);
		}else{
			request.setAttribute("menu", null);
		}
		
		List<BmsMenu> list = menuService.selectParentMenu();
		request.setAttribute("list", list);
		
		return "bms/menu_input";
		
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/menu_input")
	public String input(HttpServletRequest request,HttpServletResponse response,
			Integer id,Integer parentId,Integer sortNum) throws Exception{
		
		String menuName = request.getParameter("menuName");
		String menuUrl = request.getParameter("menuUrl");
		
		
		if(id!=null){
			BmsMenu menu = menuService.findByKey(id);
			menu.setMenuUrl(menuUrl);
			menu.setParentId(parentId);
			menu.setSortNum(sortNum);
			menuService.update(menu);
			ResponseUtils.renderText(response, "0002");
			return null;
			
		}else{
			int count = menuService.selectCountByName(menuName);
			if(count>0){
				ResponseUtils.renderText(response, "0001");
				return null;
			}
			
			BmsMenu menu = new BmsMenu();
			menu.setMenuName(menuName);
			menu.setMenuUrl(menuUrl);
			menu.setParentId(parentId);
			menu.setSortNum(sortNum);
			menuService.save(menu);
		}
		
		ResponseUtils.renderText(response, "0000");
		return null;
	}
	
	@RequestMapping("/menu_del")
	public String del(Integer id, HttpServletResponse response) throws Exception {
	
		BmsMenu menu = menuService.findByKey(id);
		if(null!=menu){
			if(menu.getParentId().longValue()==0){
				int count = menuService.countByParent(id);
				if(count>0){
					ResponseUtils.renderText(response, "0001");
					return null;
				}else{
					menuService.delete(id);
					BmsRoleMenu rm = new BmsRoleMenu();
					rm.setMenuId(id);
				    roleMenuService.delete(rm);
				}
			}else{
				 menuService.delete(id);
				 BmsRoleMenu rm = new BmsRoleMenu();
			     rm.setMenuId(id);
				 roleMenuService.delete(rm);
			}
		}
		ResponseUtils.renderText(response, "0001");
		return null;
	}

	@Override
	public String input(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
