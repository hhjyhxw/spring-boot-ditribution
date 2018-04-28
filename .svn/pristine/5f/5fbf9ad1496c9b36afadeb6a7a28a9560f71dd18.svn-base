package com.icloud.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.icloud.common.ConfigUtil;
import com.icloud.common.Contants;
import com.icloud.common.ftp.FtpUtils;
import com.icloud.common.ftp.FtpUtils.UploadStatus;
import com.icloud.common.util.wx.HttpRequestUtil;
import com.icloud.common.util.wx.WxConst;

public class AppQRCodeUtil {
	
	public final static Logger log = LoggerFactory.getLogger(AppQRCodeUtil.class);
	
//	public static void main(String[] args) throws Exception{
//		System.out.println(createQRCode("233", null));
//	}
	
	/**
	 * @param accessToken
	 * @return url
	 * @throws Exception 
	 */
	public static String createQRCode(String id, String accessToken) throws Exception{
		String fileName = null;
		try{
			String requestUrl = WxConst.getwxacodeunlimit.replace("ACCESS_TOKEN", accessToken);
			int width = Integer.parseInt(ConfigUtil.get("app_qrcode_width"));
			boolean autoColor = Boolean.parseBoolean(ConfigUtil.get("app_qrcode_auto_color"));
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("scene", id);
			params.put("width", width);
			params.put("auto_color", autoColor);
			if(!autoColor){		//autoColor为false，读取配置文件的line_color并添加参数
				String line_color = ConfigUtil.get("app_qrcode_line_color");
				JSONObject jsonObj = JSONObject.parseObject(line_color);
				params.put("line_color", jsonObj);
			}
			String paramJson = JSONObject.toJSONString(params);
			byte[] imgByte = HttpRequestUtil.httpRequestByteArr(requestUrl, "POST", paramJson);
			if(null == imgByte){
				throw new NullPointerException("saveQRCodeFile imgByte is null.");
			}
			if(imgByte.length < 5000){	//根据返回的字节数组长度判断wx返回图片字节或者错误信息
				String error = new String(imgByte);
				throw new Exception("saveQRCodeFile wx return error: " + error);
			}
			InputStream is = new ByteArrayInputStream(imgByte);
			fileName = UUID.randomUUID().toString().replace("-", "") + ".jpg";
			String ftpPath = ConfigUtil.get("filepath") + Contants.IMG_BASE_PATH_;
			UploadStatus status = FtpUtils.uploadFile(ftpPath, fileName, is, FtpUtils.connectServer());
			if(UploadStatus.Upload_New_File_Success != status)
				throw new Exception("create qrCode fail.");
		}catch(Exception ex){
			log.error("saveQRCodeFile error.", ex);
			throw ex;
		}
		return Contants._DO_MAIN_ + Contants.IMG_BASE_PATH_ + fileName;
	}
	
}
