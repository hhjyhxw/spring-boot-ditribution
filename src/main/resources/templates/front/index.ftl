<!DOCTYPE html>
<html class="flex-layout">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
		<title>首页</title>
		<link rel="stylesheet" href="${request.contextPath}/ldsc/css/reset.css">
		<link rel="stylesheet" href="${request.contextPath}/ldsc/css/myStyle.css">
		<style type="text/css">
			@font-face {
		        font-family: 'PingFang';
			}
			
			.classActive_a{
				background:white;
    			color: blue;
			}
		</style>
	</head>
	
	<body style=" width: 100%; height:100%;">
		<div class="flex menu" >
			<div class="body"  style="background: #80c8fc; width: 100%; height:100%;overflow:hidden;">
				<ul class="headMenu">
					<li>
						<a href="#">
							<div class="p_b10"><img src="../../ldsc/img/icon_ldNum.png" alt="" /></div>
							<p>乐豆数</p>
							<span id="smokeBeansCount">0</span>
						</a>
					</li>
					<li>
						<a href="#">
							<div class="p_b10"><img src="../../ldsc/img/icon_cj.png" alt="" /></div>
							<p>长卷</p>
							<span  id="myCardCount">0张</span>
						</a>
					</li>
					<li>
						<a href="${request.contextPath}/frontpage/user/myCenter">
							<div class="p_b10"><img src="../../ldsc/img/icon_center.png" alt="" /></div>
							<p>个人中心</p>
						</a>
					</li>
				</ul>
				<div class="overflowH" style="padding-top: 13px;">
					<div class="asideMenu">
						<span></span>
						<div class="bgBlue">
							<ul>
							 <#if goodClassList??>
	     						 <#list goodClassList as goodClass>
	     						 	<#if goodClass_index==0>
										<li class="goodsClass classActive" id="${goodClass.id}"><a href="javascript:void(0)" style="background:white;color:#2080d1;font-size:26px">${goodClass.name}</a></li>
							 		<#else>
							 			<li class="goodsClass" id="${goodClass.id}"><a href="javascript:void(0)">${goodClass.name}</a></li>
							 		</#if>
							 	 </#list>
	    					 </#if>
							</ul>
						</div>
					</div>
					<ul class="rightProducts overflowH">
					</ul>
				</div>
				<div class="active"><img src="../../ldsc/img/actPic.jpg"/></div>
			</div>
		</div>
		
	</body>
	<#include "/front/public.ftl"/>  
	<script src="${request.contextPath}/ldsc/js/index.js?v=5"></script>
</html>