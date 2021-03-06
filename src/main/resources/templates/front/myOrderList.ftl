<!DOCTYPE html>
<html class="flex-layout">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
		<title>我的订单</title>
		<link rel="stylesheet" href="${request.contextPath}/ldsc/css/reset.css">
		<link rel="stylesheet" href="${request.contextPath}/ldsc/css/myStyle.css">
		<style type="text/css">
			@font-face {
		        font-family: 'PingFang';
			}
		</style>
		
		
	</head>
	
	<body style="background: #e4ecff; overflow: hidden; width: 100%;">
		<div class="flex">
			<div class="body">
				<div class="oderHead">我的订单</div>
				<div class="orderTab">
					<h2 class="orderStateTab">
						<span class="tabItem onOrderState" id=""><i></i>全部订单</span>
						<span class="tabItem" id="0">未发货</span>
						<span class="tabItem" id="1">已发货</span>
					</h2>
					<!--切换栏    -->
					<div class="oderUnit" style="">
						<!--<div class="oderBox">
							<dl>
								<dt><img src="img/detailImg.png" alt="" /></dt>
								<dd>
									<p>品吸大礼包</p>
									<p>时间：2018-2-3 20:55:15</p>
								</dd>
							</dl>
							<ul class="state">
								<li>乐豆：1880</li>
								<li>未发货</li>
							</ul>
						</div> -->
					</div>
					<!--切换栏    END-->
				</div>
			</div>
		</div>
	</body>
	
	<#include "/front/public.ftl"/> 
	<script src="${request.contextPath}/ldsc/js/myOrderList.js"></script>
</html>