package com.icloud.common.util.wx;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.icloud.common.CacheUtils;
import com.icloud.common.util.AccessTokenAndJsapiTicketUtil;
import com.icloud.common.util.ConfigUtil;

/**
 * 获取基础accessToken
 * @author z
 *
 */
public class AccessTokenUtil {
	
	private static Logger logger = LoggerFactory.getLogger(AccessTokenUtil.class);
//	private static final String ACCESS_TOKEN_CACHE = "accessTokenCache";
	private static Map<String,AccessToken> accessTokenCache= new HashMap<String,AccessToken>();
	public static synchronized  AccessToken getAccessToken(){
//		JedisCacheManager jedisCacheManager = new JedisCacheManager();
//		AccessToken accessToken = (AccessToken)jedisCacheManager.getCache(ACCESS_TOKEN_CACHE).get(wxapp.getAppid());
		/*1、先从缓存取*/
		AccessToken accessToken = accessTokenCache.get(ConfigUtil.get("hostnumber"));
		if(accessToken!=null){
			if(accessToken.isValid(System.currentTimeMillis())){
				return accessToken;
			}else{
				// 如果请求成功
				String token = AccessTokenAndJsapiTicketUtil.getAccessToken();
				if (null != token) {
					try {
						accessToken = new AccessToken();
						accessToken.setToken(token);
						accessToken.setExpiresIn(6000);//10分钟
						accessToken.setCreateTime(System.currentTimeMillis());
						//存入缓存
						accessTokenCache.put(ConfigUtil.get("hostnumber"),accessToken);
//						jedisCacheManager.getCache(ACCESS_TOKEN_CACHE).put(wxapp.getAppid(), accessToken);
						return accessToken;
					} catch (Exception e) {
						accessToken = null;
						//刷新token失败
						logger.error("刷新token失败");
					}
				}
				return null;
			}
		}else{
			/*2、重新请求*/
			// 如果请求成功
			String token = AccessTokenAndJsapiTicketUtil.getAccessToken();
			if (null != token) {
				try {
					accessToken = new AccessToken();
					accessToken.setToken(token);
					accessToken.setExpiresIn(6000);//10分钟
					accessToken.setCreateTime(System.currentTimeMillis());
					//存入缓存
					accessTokenCache.put(ConfigUtil.get("hostnumber"),accessToken);
//					jedisCacheManager.getCache(ACCESS_TOKEN_CACHE).put(wxapp.getAppid(), accessToken);
					return accessToken;
				} catch (Exception e) {
					accessToken = null;
					//刷新token失败
					logger.error("刷新token失败");
				}
			}
			return null;
		}
	}
}
