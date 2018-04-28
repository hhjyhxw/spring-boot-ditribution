<!DOCTYPE html>
<html class="flex-layout">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="viewport" content="width=device-width,height=device-height,inital-scale=1.0,maximum-scale=1.0,user-scalable=no;">
		<title>我的的地址</title>
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
				<div class="oderHead">我的地址列表</div>
				<#if orderAddressList?? && (orderAddressList?size > 0)>
					<#list orderAddressList as orderAddress>
						<div class="addressUnit <#if orderAddress.defaultAddr=='1'>onDefault</#if>" style="min-height:165px;">
							<div class="nameTel">
								<p>${(orderAddress.name)!}</p>
								<p>${(orderAddress.phone)!}</p>
							</div>
							<p class="fullAddress">
								${(orderAddress.provinceName)!}${(orderAddress.cityName)!}${(orderAddress.countyName)!}
								${(orderAddress.townsName)!}${(orderAddress.detailAddress)!}
							</p>
							<input type="hidden" id="addressId" value="${orderAddress.id}"/>
						</div>
					</#list>
				</#if>
				<div class="saveAddressBtn"><a href="${request.contextPath}/frontpage/orderAddress/toOrderAddressAdd">添加地址</a></div>
			</div>
		</div>
	</body>
	<#include "/front/public.ftl"/> 
	<script>
			$.fn.ready( function(){
				//切换地址
				$(".body").on('click','.addressUnit',function(){
					var id = $(this).find("#addressId").val();
					window.location.href="${request.contextPath}/frontpage/orderAddress/orderAddressChange?id="+id;
				});
			});
		</script>
</html>