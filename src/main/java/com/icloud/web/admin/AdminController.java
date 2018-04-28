package com.icloud.web.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.DateTools;
import com.icloud.common.MD5Utils;
import com.icloud.common.ResponseUtils;
import com.icloud.model.bms.BmsAdmin;
import com.icloud.model.bms.BmsAdminRole;
import com.icloud.model.bms.BmsRole;
import com.icloud.service.bms.AdminRoleService;
import com.icloud.service.bms.AdminService;
import com.icloud.service.bms.RoleService;


@Controller
@RequestMapping(value = "${backPath}/admin")
public class AdminController extends AdminBaseController<BmsAdmin> {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private AdminRoleService adminRoleService;
	/**
	 * 跳转列表页
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/admin_list")
	public String list(HttpServletRequest request,BmsAdmin admin ) throws Exception{	
		PageInfo<BmsAdmin> page = adminService.findByPage(1, 10, admin);
		request.setAttribute("page", page);;
		return "bms/admin_list";
	}

	/**
	 * 获取列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/getlist")
	public String getList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String account = request.getParameter("account");
		String pageNo = request.getParameter("pageNo");		
		JSONObject json = new JSONObject();
		BmsAdmin admin = new BmsAdmin();
		if(StringUtils.isNotBlank(account)){
			json.put("account", account);
			admin.setAccount(account);
		}
		PageInfo<BmsAdmin> page = adminService.findByPage(StringUtils.isNotBlank(pageNo)?Integer.parseInt(pageNo):1, 10, admin);
		List<BmsAdmin> list = page.getList();
		json.put("pages", admin.getPages());
		JSONArray array = new JSONArray();
		for(BmsAdmin subadmin:list){
			JSONObject sub = new JSONObject();
			sub.put("id",subadmin.getId());
			sub.put("account", subadmin.getAccount());
			sub.put("isLock", subadmin.getIsLock());
            sub.put("createDate", DateTools.convertDateToString(subadmin.getCreateDate(),"yyyy-MM-dd HH:mm:ss"));
			array.add(sub);
		}
		json.put("list", array);
		ResponseUtils.renderJson(response, json.toJSONString());
		return null;

	}
	
	/**
	 * 管理员输入
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception TbmsRole
	 */
	@RequestMapping("/admin_to_input")
	public String toinput(Integer id,HttpServletRequest request) throws Exception{
		
		List<BmsRole> allRole = roleService.selectAll();
		request.setAttribute("rList", allRole);
		if(id!=null){
			BmsAdmin admin= adminService.findByKey(id);
		  List<BmsRole> aList =  roleService.selectByAdmin(admin.getId());
		   
		   for(BmsRole role:aList){
			   for(BmsRole r:allRole){
				   if(r.getId().equals(role.getId())){
					   r.setIsHas(true);
					   continue;
				   }
			   }
		   }
		   
		   request.setAttribute("admin", admin);
		}else{
			request.setAttribute("admin", null);
		}
		return "bms/admin_input";
	
	}
	/**
	 * 管理员添加
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/admin_input")
	public String input(HttpServletRequest request,HttpServletResponse response,Integer id) throws Exception{
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String isLock = request.getParameter("isLock");
		String passwordPrompt = request.getParameter("passwordPrompt");
		String[] roleIds =  request.getParameterValues("roleId");
		if(id==null){
			BmsAdmin adminParam =new BmsAdmin();
			adminParam.setAccount(account);
			int count = adminService.selectCountByAccount(adminParam);
			if(count>0){
				//账号已被使用
				ResponseUtils.renderHtml(response, "0001");
				return null;
			}
			
			BmsAdmin admin = new BmsAdmin();
			admin.setAccount(account);
			admin.setIsLock(StringUtils.isNotBlank(isLock)&&isLock.equals("on")?"0":"1");
			admin.setPassWord(MD5Utils.encode2hex(password));
			admin.setPasswordPrompt(passwordPrompt);
			admin.setCreateDate(new Date());
			adminService.save(admin);
			if(null!=roleIds){
				BmsAdminRole ar = null;
				List<BmsAdminRole> list = new ArrayList<BmsAdminRole>();
				for(int i=0;i<roleIds.length;i++){
					ar = new BmsAdminRole();
					ar.setAdminId(admin.getId());
					ar.setRoleId(!StringUtils.isBlank(roleIds[i])?Integer.parseInt(roleIds[i]):0);
					list.add(ar);
				}
				adminRoleService.batchInsert(list);
			}
			ResponseUtils.renderHtml(response, "0000");
			return null;
		}else{
			BmsAdmin admin =adminService.findByKey(id);
			if(!admin.getPassWord().equals(password)){
				admin.setPassWord(MD5Utils.encode2hex(password));
			}
			admin.setPasswordPrompt(passwordPrompt);
			if(StringUtils.isNotBlank(isLock)&&isLock.equals("on")){
				 admin.setIsLock("0");
			}
			adminService.update(admin);
			if(null!=roleIds){
				adminRoleService.delele(admin.getId());
				BmsAdminRole ar = null;
				List<BmsAdminRole> list = new ArrayList<BmsAdminRole>();
				for(int i=0;i<roleIds.length;i++){
					ar = new BmsAdminRole();
					ar.setAdminId(admin.getId());
					ar.setRoleId(!StringUtils.isBlank(roleIds[i])?Integer.parseInt(roleIds[i]):0);
					list.add(ar);
				}
				adminRoleService.batchInsert(list);
			}
			ResponseUtils.renderHtml(response, "0002");
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
	@RequestMapping("/admin_del")
	public String del(@RequestParam  Integer id,HttpServletResponse response) throws Exception{
		adminService.delete(id);
		adminRoleService.delele(id);
		ResponseUtils.renderHtml(response, "0000");
		return null;
		
	}
	
	@RequestMapping("/admin_pass")
	public String passEdit(Integer id,HttpServletRequest request) throws Exception{
		BmsAdmin admin = adminService.findByKey(id);
		 request.setAttribute("admin", admin);
		 return "/bms/admin_pass";
	}

	@Override
	public String input(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
