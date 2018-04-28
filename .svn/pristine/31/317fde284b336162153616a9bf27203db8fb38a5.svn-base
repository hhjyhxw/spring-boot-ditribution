package com.icloud.web.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @filename      : Token.java
 * @description   : 
 * @author        : zdh
 * @create        : 2017年9月17日 上午10:18:57   
 * @copyright     : zhumeng.com@hyzy-activities
 *
 * Modification History:
 * Date             Author       Version
 * --------------------------------------
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Token {
    //生成 Token 标志
    boolean save() default false ;
    //移除 Token 值
    boolean remove() default false ;
}