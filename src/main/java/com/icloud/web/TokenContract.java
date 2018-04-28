package com.icloud.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.icloud.exceptions.FormRepeatException;
import com.icloud.web.util.Token;

/**
 * @filename      : TokenContract.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年9月17日 上午10:23:52   
 * @copyright     : zhumeng.com@hyzy-activities
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Aspect
@Component
public class TokenContract {
 
	public final static Logger log = LoggerFactory.getLogger(TokenContract.class);
 
    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(token)")
    public void testToken(final JoinPoint joinPoint, Token token){
        try {
            if (token != null) {
                //获取 joinPoint 的全部参数
                Object[] args = joinPoint.getArgs();
                HttpServletRequest request = null;
                HttpServletResponse response = null;
                for (int i = 0; i < args.length; i++) {
                    //获得参数中的 request && response
                    if (args[i] instanceof HttpServletRequest) {
                        request = (HttpServletRequest) args[i];
                    }
                    if (args[i] instanceof HttpServletResponse) {
                        response = (HttpServletResponse) args[i];
                    }
                }
                //进入表单前 生成tokenS
                boolean needSaveSession = token.save();
                if (needSaveSession){
                    String uuid = UUID.randomUUID().toString();
                    request.getSession().setAttribute( "payToken" , uuid);
                    log.debug("进入表单页面，payToken值为："+uuid);
                }
                //提交表单前，删除token
                boolean needRemoveSession = token.remove();
                if (needRemoveSession) {
                    if (isRepeatSubmit(request)) {
                        log.error("表单重复提交");
                        throw new FormRepeatException("表单重复提交");
                    }
                    request.getSession(false).removeAttribute( "payToken" );
                }
            }
 
        } catch (FormRepeatException e){
            throw e;
        } catch (Exception e){
            log.error("payToken 发生异常 : "+e);
        }
    }
 
    private boolean isRepeatSubmit(HttpServletRequest request) throws FormRepeatException {
        String serverToken = (String) request.getSession( false ).getAttribute( "payToken" );
        if (serverToken == null ) {
            //throw new FormRepeatException("session 为空");
            return true;
        }
        String clinetToken = request.getParameter( "clientToken" );
        if (clinetToken == null || clinetToken.equals("")) {
            //throw new FormRepeatException("请从正常页面进入！");
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            //throw new FormRepeatException("重复表单提交！");
            return true ;
        }
        log.debug("校验是否重复提交：表单页面payToken值为："+clinetToken + ",Session中的payToken值为:"+serverToken);
        return false ;
    }
}
