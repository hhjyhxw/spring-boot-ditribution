package com.icloud.web.upload;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.icloud.common.Contants;
import com.icloud.common.ftp.FtpFileService;
import com.icloud.common.util.ConfigUtil;
import com.icloud.web.AppBaseController;


/**
 * @filename      : aa.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年9月21日 下午5:47:57   
 * @copyright     : zhumeng.com@hyzy-activities
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */

@Controller
public class UpLoadController extends AppBaseController{
	
	@Autowired
	private FtpFileService ftpFileService;
	
	@RequestMapping(value = "/ueditor",method=RequestMethod.POST)
	@ResponseBody
	public String ueditor(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("file") MultipartFile pfile) throws Exception {
		log.error("进入文本编辑器图片上传后台");
		
	    	String fileName = pfile.getOriginalFilename();
			fileName = UUID.randomUUID().toString().replace("-", "")+"_"+fileName;
			String basePath = Contants.IMG_BASE_PATH_;
			boolean result = false;
			String parentDer = ConfigUtil.get("filepath")+basePath;
			System.out.println("parentDer==="+parentDer);
			result = ftpFileService.upload(pfile.getBytes(), ConfigUtil.get("filepath")+basePath, fileName);
			JSONObject obj = new JSONObject();
			if(result){
				log.error("上传成功");
				obj.put("code", 0);
				obj.put("msg", "上传成功");
			}else{
				obj.put("code", 1);
				obj.put("msg", "图片上传失败");
			}
			String paths = basePath+fileName;
			JSONObject data = new JSONObject();
			data.put("src", Contants.IMG_SERVER+paths);
			data.put("title", "图片");
			obj.put("data", data);
			return pakageJsonp(obj);
	
	}

}

