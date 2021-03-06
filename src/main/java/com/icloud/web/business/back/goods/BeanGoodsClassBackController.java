package com.icloud.web.business.back.goods;

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
import com.icloud.common.ConfigUtil;
import com.icloud.common.ResponseUtils;
import com.icloud.model.business.BeanGoodsClass;
import com.icloud.service.business.BeanGoodsClassService;
import com.icloud.web.BaseController;

/**
 * 后台商品分类
 * @author zdh
 *
 */
@Controller
@RequestMapping(value = "${backPath}/beanGoodsClass")
public class BeanGoodsClassBackController extends BaseController<BeanGoodsClass>{

	@Autowired
	private BeanGoodsClassService beanGoodsClassService;
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/list")
	@Override
	public String list(HttpServletRequest request, BeanGoodsClass t)
			throws Exception {
		PageInfo<BeanGoodsClass> page = beanGoodsClassService.findByPage(1, 10, t);
		request.setAttribute("page", page);
		request.setAttribute("record", t);
		return "business/goodsclass/bean_goods_class_list";
	}

	/**
	 * 异步翻页
	 */
	@RequestMapping(value = "/getList")
	@Override
	public String getList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String name = request.getParameter("name");	
		String status = request.getParameter("status");	
		String pageNo = request.getParameter("pageNo");	
		String pageSize = request.getParameter("pageSize");	
		BeanGoodsClass t = new BeanGoodsClass();
		if(StringUtils.isNotBlank(name)){
			t.setName(name);
		}
		if(StringUtils.isNotBlank(status)){
			t.setStatus(status);
		}
		if(StringUtils.isNotBlank(name)){
			t.setName(name);
		}
		PageInfo<BeanGoodsClass> page = beanGoodsClassService
				.findByPage(StringUtils.isNotBlank(pageNo)?Integer.parseInt(pageNo):1, StringUtils.isNotBlank(pageSize)?Integer.parseInt(pageSize):10, t);
		List<BeanGoodsClass> list = page.getList();
		JSONObject json = new JSONObject();
		json.put("pages",page.getPages());
		JSONArray array = new JSONArray();
		for(BeanGoodsClass s:list){
			JSONObject sub = new JSONObject();
			sub.put("id",s.getId());
			sub.put("name", s.getName());
			sub.put("sortNum", s.getSortNum());
			sub.put("status", s.getStatus());
			array.add(sub);
		}
		json.put("list", array);
		ResponseUtils.renderJson(response, json.toJSONString());
		return null;
	}

	/**
	 * 跳转添加编辑页
	 */
	@RequestMapping(value = "/toinput")
	@Override
	public String toinput(Integer id, HttpServletRequest request)throws Exception {
		BeanGoodsClass record = null;
		if(id!=null){
			record = beanGoodsClassService.findByKey(id.longValue());
		}
		request.setAttribute("record", record);
		return "business/goodsclass/bean_goods_class_input";
	}

	/**
	 * 新增或者修改
	 */
	@RequestMapping(value = "/input")
	public String input(HttpServletRequest request, HttpServletResponse response,
		BeanGoodsClass record)throws Exception {
		if(record.getId()!=null){
			beanGoodsClassService.update(record);
		}else{
			beanGoodsClassService.save(record);
		}
		return "redirect:"+ConfigUtil.getBackPath()+"/beanGoodsClass/list";
	}

	@Override
	public String del(Integer id, HttpServletResponse response){
		 try {
			 beanGoodsClassService.delete(id.longValue());
			ResponseUtils.renderHtml(response, "0000");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResponseUtils.renderHtml(response, "1000");
		return null;
	}

	@Override
	public String input(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
