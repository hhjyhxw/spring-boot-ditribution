<!doctype html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="apple-mobile-web-app-capable" content="yes" />
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<meta name="format-detection" content="telephone=no" />
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
		<title>管理收获地址</title>
		<link rel="stylesheet" href="../../ldsc/css/reset.css">
		<link rel="stylesheet" href="../../ldsc/css/myStyle.css">
		
		<link rel="stylesheet" href="../../ldsc/weui/lib/weui.min.css">
        <link rel="stylesheet" href="../../ldsc/weui/css/jquery-weui.css">
		
		<script src="../../ldsc/js/jquery-2.1.4.js"></script>
		<script src="../../ldsc/js/global.js"></script>
    <style type="text/css">
			@font-face {
		        font-family: 'PingFang';
		        src: url('/images/PingFang Regular.ttf'),
			}

	</style>
	
	
	    
	    
	    
	
	</head>

	<body ontouchstart style="background: #e4ecff; overflow: hidden; width: 100%;">
		<div class="flex">
			<div class="body">
				<div class="oderHead"><a href="javascript :history.back(-1)"><span></span></a>管理收货地址</div>
				<#list addrs as address>
				<#if address.defaultAddr == '1'>
				  <div class="addressUnit onDefault">
				<#else>
				  <div class="addressUnit">
				</#if>
				    <input type="hidden"  value='${address.id}'>
					<div class="nameTel">
					    
						<p>${address.name}</p>
						<p>${address.phone}</p>
					</div>
					<p class="fullAddress">${address.provinceName}${address.cityName}${address.countyName}${address.detailAddress}</p>
					<div class="defaultBtn">
						<p><span class="defaultAddress"></span>默认地址</p>
						<p><a href="${request.contextPath}/frontpage/address/toAddrEdit?id=${address.id}">编辑</a>
						<a class="deleteAddr"  onclick="deleteAddress(this);return false;" href="${request.contextPath}/frontpage/address/deleteAddr?id=${address.id}" >删除</a></p>
					</div>
				</div>
				</#list>
				<div class="saveAddressBtn"><a href="${request.contextPath}/frontpage/address/toAddrEdit">添加新地址</a></div>
			</div>
		</div>
		<script src="../../ldsc/weui/js/jquery-weui.js"></script>
		<script type = "text/javascript">
	
	  	
	  	
	  	$(function(){
	  	  $(".defaultAddress").click(function() {
		        obj = $(this).parent().parent().parent();
		        if(obj.attr('class') == 'addressUnit'){
		          var addressId = obj.children('input').val();
		          window.location.href = "${request.contextPath}/frontpage/address/setDefaultAddr?id="+addressId;
		        }
		    });
	  	})
	  	
	  	function deleteAddress(obj){
	  	  
	  	   $.confirm("确认删除地址吗?", function() {
	  	   window.location.href = obj.href
        }, function() {
          
        });
	  	  }
	  	
	 	
	</script>
		
	</body>

</html>