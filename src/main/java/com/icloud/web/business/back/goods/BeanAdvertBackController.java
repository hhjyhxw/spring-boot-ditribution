package com.icloud.web.business.back.goods;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.icloud.common.ConfigUtil;
import com.icloud.common.Contants;
import com.icloud.common.ResponseUtils;
import com.icloud.model.business.BeanAdvert;
import com.icloud.service.business.BeanAdvertService;
import com.icloud.web.BaseController;

/**
 * 后台商品管理
 * @author zdh
 *
 */
@Controller
@RequestMapping(value = "${backPath}/beanAdvert")
public class BeanAdvertBackController extends BaseController<BeanAdvert>{

	@Autowired
	private BeanAdvertService beanAdvertService;
	
	/**
	 * 获取列表
	 */
	@RequestMapping(value = "/list")
	@Override
	public String list(HttpServletRequest request, BeanAdvert t)
			throws Exception {
		PageInfo<BeanAdvert> page = beanAdvertService.findByPage(1, 10, t);
		request.setAttribute("page", page);
		request.setAttribute("record", t);
		return "business/advert/bean_advert_list";
	}

	/**
	 * 异步翻页
	 */
	@RequestMapping(value = "/getList")
	@Override
	public String getList(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String status = request.getParameter("status");	
		String pageNo = request.getParameter("pageNo");	
		String pageSize = request.getParameter("pageSize");	
		BeanAdvert t = new BeanAdvert();
		if(StringUtils.isNotBlank(status)){
			t.setStatus(status);
		}
		
		PageInfo<BeanAdvert> page = beanAdvertService
				.findByPage(StringUtils.isNotBlank(pageNo)?Integer.parseInt(pageNo):1, StringUtils.isNotBlank(pageSize)?Integer.parseInt(pageSize):10, t);
		List<BeanAdvert> list = page.getList();
		JSONObject json = new JSONObject();
		json.put("pages",page.getPages());
		JSONArray array = new JSONArray();
		for(BeanAdvert s:list){
			JSONObject sub = new JSONObject();
			sub.put("id",s.getId());
			sub.put("name", s.getName());
			sub.put("adImage", s.getAdImage());
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
	public String toinput(Long id, HttpServletRequest request)throws Exception {
		BeanAdvert record = null;
		if(id!=null){
			record = beanAdvertService.findByKey(id);
		}
		request.setAttribute("record", record);
		return "business/advert/bean_advert_input";
	}

	/**
	 * 新增或者修改
	 */
	@RequestMapping(value = "/input")
	public String input(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("pfile") MultipartFile pfile,BeanAdvert t)throws Exception {
		//添加
		if(t.getId()==null){
			String fileName = pfile.getOriginalFilename();
			fileName = UUID.randomUUID().toString().replace("-", "")+"_"+fileName;
			//文件存储的相对路径
            String basePath = Contants.IMG_BASE_PATH_;
            //获取项目根路径的绝对路径
            String realPath = request.getSession().getServletContext().getRealPath(basePath); 
            log.error(realPath);
    		File dirFile = new File(realPath);
    		if (!dirFile.exists()) {
    			dirFile.mkdirs();
    		}
    		//上传新图片
    		pfile.transferTo(new File(dirFile+"/"+fileName));  
    		beanAdvertService.save(t);
			return "redirect:"+ConfigUtil.getBackPath()+"/beanAdvert/list";
		}else{//修改
			//判断是否更新了商品主图,没有 则直接更新，有就上传再更新
			String fileName = pfile.getOriginalFilename();
			if("".equals(fileName) || fileName == null){
				beanAdvertService.update(t);
				return "redirect:"+ConfigUtil.getBackPath()+"/beanAdvert/list";
			}else{
				fileName = UUID.randomUUID().toString().replace("-", "")+"_"+fileName;
				//文件存储的相对路径
	            String basePath = Contants.IMG_BASE_PATH_;
	            //获取项目根路径的绝对路径
	            String realPath = request.getSession().getServletContext().getRealPath(basePath); 
	            log.error(realPath);
	    		File dirFile = new File(realPath);
	    		if (!dirFile.exists()) {
	    			dirFile.mkdirs();
	    		}
	    		//上传新图片
	    		pfile.transferTo(new File(dirFile+"/"+fileName));  
				//先删除原图片
				String originalFileName =t.getAdImage();
				File originalFile = new File(originalFileName);
	        	originalFile.delete();
	        	//替换新的图片并保存记录
				t.setAdImage(basePath+fileName);
				beanAdvertService.update(t);
				return "redirect:"+ConfigUtil.getBackPath()+"/beanAdvert/list";
			}
		}
	}

	/**
	 * 删除
	* Description:
	* @return  
	* @date 2018年2月27日下午3:13:38
	 */
	public String del(Long id, HttpServletResponse response){
		 try {
			 beanAdvertService.delete(id);
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

	@Override
	public String toinput(Integer id, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String del(Integer id, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
