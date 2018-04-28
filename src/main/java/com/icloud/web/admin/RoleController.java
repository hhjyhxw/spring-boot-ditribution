package com.icloud.web.admin;

import java.util.ArrayList;
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
import com.icloud.model.bms.BmsRole;
import com.icloud.model.bms.BmsRoleMenu;
import com.icloud.service.bms.MenuService;
import com.icloud.service.bms.RoleMenuService;
import com.icloud.service.bms.RoleService;

@Controller
@RequestMapping(value = "${backPath}/admin")
public class RoleController extends AdminBaseController<BmsRole> {
	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleMenuService roleMenuService;
	@Autowired
	private MenuService menuService;

	@RequestMapping("/role_list")
	public String list(HttpServletRequest request,BmsRole role) throws Exception {
		PageInfo<BmsRole> page = roleService.findByPage(1, 10, role);
		request.setAttribute("page", page);
		return "bms/role_list";

	}
	@RequestMapping("/role_getlist")
	public String getList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String roleName = request.getParameter("roleName");
		String pageNo = request.getParameter("pageNo");
		JSONObject json = new JSONObject();
		BmsRole role = new BmsRole();
		if (StringUtils.isNotBlank(roleName)) {
			json.put("roleName", roleName);
			role.setRoleName(roleName);
		}
		
		PageInfo<BmsRole> page = roleService.findByPage(StringUtils.isNotBlank(pageNo)?Integer.parseInt(pageNo):1, 10, role);
		List<BmsRole> list = page.getList();

		json.put("pages", role.getPages());
		JSONArray array = new JSONArray();
		for (BmsRole subrole : list) {
			JSONObject sub = new JSONObject();
			sub.put("id", subrole.getId());
			sub.put("roleName", subrole.getRoleName());

			array.add(sub);
		}
		json.put("list", array);
		ResponseUtils.renderJson(response, json.toJSONString());
		return null;
	}
	
	@RequestMapping("/role_to_input")
	public String toinput(Integer id,HttpServletRequest request) throws Exception{
		List<BmsMenu> mList = menuService.selectAllList();
		request.setAttribute("mList", mList);
		if(id!=null){
			BmsRole role = roleService.findByKey(id);
			List<BmsMenu> roleMenus = menuService.selectByRole(id);
			
			for(BmsMenu m:roleMenus){
				for(BmsMenu menu:mList){
					if(m.getId().longValue()==menu.getId()){
						menu.setIsHas(true);
						continue;
					}
				}
			}
			request.setAttribute("role", role);
		}else{
			request.setAttribute("role", null);
		}
		
		
		return "bms/role_input";
	}
	
	@RequestMapping("/role_input")
     public String input(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception{
	   String roleName = request.getParameter("roleName");
	   String[] menuIds = request.getParameterValues("menuId");
	   if(id!=null){
		   BmsRoleMenu rm = new BmsRoleMenu();
		   rm.setRoleId(id);
		   roleMenuService.delete(rm);
		   if(null!=menuIds){
			   List<BmsRoleMenu> rmList = new ArrayList<BmsRoleMenu>();
			   for(int i=0;i<menuIds.length;i++){
				   rm = new BmsRoleMenu();
				   rm.setMenuId(Integer.parseInt(menuIds[i]));
				   rm.setRoleId(id);
				   rmList.add(rm);
			   }
			   roleMenuService.batchInsert(rmList);
		   }
		   ResponseUtils.renderText(response, "0001");
		   return null;
		   
	   }else{
		   int count = roleService.selectCountByName(roleName);
		   if(count>0){
			   ResponseUtils.renderText(response, "0002");
			   return null;
		   }
		   
		   
		   BmsRole role = new BmsRole();
		   role.setRoleName(roleName);
		   roleService.save(role);
		   if(menuIds!=null){
			   List<BmsRoleMenu> rmList = new ArrayList<BmsRoleMenu>();
			   BmsRoleMenu rm = null;
			   for(int i=0;i<menuIds.length;i++){
				   rm = new BmsRoleMenu();
				   rm.setMenuId(Integer.parseInt(menuIds[i]));
				   rm.setRoleId(role.getId());
				   rmList.add(rm);
			   }
			   roleMenuService.batchInsert(rmList);
		   }
		   ResponseUtils.renderText(response, "0000");
		   return null;
	   }
	   
    }
	
	
	/**
	 * 删除
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/role_del")
	public String del(Integer id,HttpServletResponse response) throws Exception{
		roleService.delete(id);
		BmsRoleMenu rm = new BmsRoleMenu();
		rm.setRoleId(id);
		roleMenuService.delete(rm);
		ResponseUtils.renderText(response, "0000");
		return null;
		
	}
	@Override
	public String input(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
