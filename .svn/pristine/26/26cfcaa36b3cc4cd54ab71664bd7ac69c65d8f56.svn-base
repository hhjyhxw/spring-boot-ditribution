package com.icloud.web.business.back.orders;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageInfo;
import com.icloud.common.DateTools;
import com.icloud.common.FreeMarkerConfig;
import com.icloud.model.business.BeanOrder;
import com.icloud.service.business.BeanOrderItemService;
import com.icloud.service.business.BeanOrderService;
import com.icloud.web.BaseController;
import com.icloud.web.util.ClassUtil;

@Controller
@RequestMapping("${backPath}/beanOrderReport")
public class BeanOrderReportController{

	private final static Logger log = LoggerFactory.getLogger(BaseController.class);
	@Autowired 
	private BeanOrderService beanOrderService;
	@Autowired
	private BeanOrderItemService beanOrderItemService;
	@Autowired
	FreeMarkerConfig freeMarkerConfig;
	
	/**
	 * 获取 订单列表
	 * @param request
	 * @param beanOrder
	 * @return
	 */
	@RequestMapping("/downLoadOrderlist")
	public void downLoadOrderlist(HttpServletRequest request,HttpServletResponse resp, BeanOrder t)
			throws Exception {
		String message = "";
		String templateName = "orderListExcel.ftl";
	    String start = request.getParameter("startTime");
	    String end = request.getParameter("endTime");
//		String pageNumStr = request.getParameter("pageNum");
//		int pageNum = 1;
//		if(pageNumStr != null && !"".equals(pageNumStr)) {
//			pageNum = Integer.parseInt(pageNumStr);
//		}
		
//		log.warn("当前页数：" + pageNum);
		Map<String, Object> map = new HashMap<>();
		map = ClassUtil.setConditionMap(t);
		map.put("start", start);
		map.put("end", end);
		log.warn(map.get("start")!=null?map.get("start").toString():null);
		PageInfo<BeanOrder> pageInfo = beanOrderService.findByPage(1, 1000, map);
		log.warn("查询结果：" + pageInfo.toString());
		//pageInfo.setList(new ArrayList<>());
//		request.setAttribute("page", pageInfo);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		List<BeanOrder> orderList = pageInfo.getList();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("start",start);
		dataMap.put("end",end);
		dataMap.put("orderList",orderList);
		dataMap.put("sheetSize", orderList!=null?orderList.size():0);
//
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		String fileType = "excel";
		String fileName = DateTools.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss")
				+ "_orderListExcel";
		try {
			// 调用工具类WordGenerator的createDoc方法生成Word文档
			file = freeMarkerConfig.createDoc(dataMap, fileName,templateName, fileType);
			fin = new FileInputStream(file);
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/msexcel");
			// 设置浏览器以下载的方式处理该文件默认名为resume.doc
			resp.addHeader("Content-Disposition", "attachment;filename="+ fileName + ".xls");

			out = resp.getOutputStream();
			byte[] buffer = new byte[512]; // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "下载失败!";
			out.write(message.getBytes());
		} finally {
			if (fin != null)
				try {
					fin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (file != null)
				file.delete();
		}
	}

	
}
