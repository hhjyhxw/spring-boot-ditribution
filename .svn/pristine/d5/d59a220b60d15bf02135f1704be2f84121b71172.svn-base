var base ='/hyzyactivities';
$.fn.ready( function() {
	$("#payTrade").click(function(){
		$.ajax({
			url:base+'/phone/checkUser',
  			data:{'orderNo': $("#orderNo").val()},
  			method:'POST',
  			dataType:'json',
  			success:function(data){
				if(null != data.url){
					top.window.location=data.url;
				}   
  			}
		});
	});
	
	
	$("#cancelTrade").click(function(){
		 if(confirm('是否取消订单？')){
			$.ajax({
				url:'${request.contextPath}/phone/cancelOrder',
	  			data: {'orderNo' : $("#orderNo").val()},
	  			method:'POST',
	  			dataType:'json',
	  			success:function(data){
  					top.window.location=data.url;
	  			}
			});
		 }
	})
	
	$("#deleteTrade").click(function(){
		 if(confirm('是否删除订单？')){
			$.ajax({
				url:mall.base+"/phone/ajaxDeleteOrder",
	  			data: {'tid' : $("#tid").val()},
	  			method:'POST',
	  			dataType:'json',
	  			success:function(data){
	  				top.window.location=data.url;
	  			}
			});
		 }
	})
	
	
})



