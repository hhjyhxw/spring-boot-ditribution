$.fn.ready( function(){
	//异步获取我的订单
	getMyOrderList('');
	$(".tabItem").click(function(){
		var shippingStatus = $(this).attr("id");
		$(this).siblings(".tabItem").removeClass("onOrderState").find("i").remove();;
		$(this).addClass("onOrderState").prepend("<i></i>") ;
		getMyOrderList(shippingStatus);
	});
});

//异步获取我的订单
function getMyOrderList(shippingStatus){
	var resultString = '';
	 $.ajax({
			url : getBasePath()+'/frontpage/beanOrder/orderListJson',
			data : {
				"shippingStatus":shippingStatus
			},
			method : 'post',
			dataType : 'json',
			timeout:5000,
			success : function(data) {
				if (data!=null) {
					$.each(data, function(i, n){
						var shippingStatus = n.shippingStatus;
						var shippText = '';
						if(shippingStatus=='0'){
							shippText='未发货';
						}else if(shippingStatus=='1'){
							shippText='已发货';
						}
						resultString+='<div class="oderBox">';
							resultString+='<dl>';	
								resultString+='<dt><img src="'+n.goodImage+'" alt="" /></dt>';
								resultString+='<dd>';
									resultString+='<p>'+n.name+'</p>';
									resultString+='<p>时间：'+n.createTime+'</p>';
								resultString+='</dd>';
							resultString+='</dl>';	
							resultString+='<ul class="state">';	
								resultString+='<li>乐豆：'+n.score+'   &nbsp;抵扣券值'+n.coupon+'</li>';	
								resultString+='<li>'+shippText+'</li>';	
							resultString+='</ul>';	
						resultString+='</div>';	
					});
					$(".oderUnit").html(resultString);
				}else{
					$(".oderUnit").html("");
				}
			},
			error : function(data) {
				$(".oderUnit").html("");
			}
		});
}
 