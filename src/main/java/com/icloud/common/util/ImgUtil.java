package com.icloud.common.util;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sun.misc.BASE64Decoder;

public class ImgUtil {

	/**
	 * 保存图片
	 * 对字节数组字符串进行Base64解码并生成图片到指定路径
	 * @param imgStr
	 * @param imgFilePath
	 * @return
	 * @throws Exception 
	 */
	public static void generateImage(String imgStr, String imgFilePath) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static byte[] generateImage(String imgStr) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			return bytes;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static List<String> splitUrlStrToList(String imgUrl, String symbol){
		List<String> img = new ArrayList<String>();
		if(null != imgUrl){
			String[] tmp = imgUrl.split(symbol);
			if(null != tmp){
				Collections.addAll(img, tmp);
			}
		}
		return img;
	}
	
	public static String formatImgToPage(List<String> imgsUrl, String symbol){
		StringBuffer sb = new StringBuffer();
		if(null != imgsUrl){
			for(String tmp : imgsUrl){
				sb.append("<p><img src=\"");
				sb.append(tmp);
				sb.append("\"></p>");
			}
		}
		return sb.toString();
	}
}