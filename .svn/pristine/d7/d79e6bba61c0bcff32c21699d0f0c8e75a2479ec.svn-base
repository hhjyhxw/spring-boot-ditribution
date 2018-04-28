var base ='/hyzyactivities';
/**
 * 订单确认js
 */

  $(".submit").click(function(){
	  var flag = true;
	  var clientToken = $("#clientToken").val();//表单验证令牌 
	  var memo = $("#memo").val();//支付方式
	  var teamId = $("#teamId").val().replace(',', '');//团id
	  var payWay = $("#payWay").val();//支付方式
	  var payfrom = $("#payfrom").val();//从购物车进入还是立即购买进入
	  var receiverId = $("#defautReceiverId").val();//用户收货地址
	  var deliverType = getDeliveryType($(this));
	  var zitiAddress = $("#zitiAddress").text();//自提地址
	  if(deliverType=='1'){
		  if(receiverId=="" || receiverId==undefined){
				openAlert('请填写地址');
				return ;
		  }
	  }
	  receiverId = receiverId.replace(',', '');
	  var cartIdArray = $("input[name='cartId']");//购物车iD集合
	 // var goodIdArray = $("input[name='goodId']");//商品iD集合
	  //var quantityArray = $("input[name='quantity']");//商品数量集合
	  var cartIds = ''
	  var goodIds = ''
	  var quantitys = ''
		cartIdArray.each(function(){
			cartIds +=$(this).val().replace(',', '')+"@@@";
			goodIds+=$(this).siblings("#goodId").val().replace(',', '')+"@@@";
			quantitys+=$(this).siblings("#quantity").val().replace(',', '')+"@@@";
		})
		if(cartIds=='' || goodIds=='' || quantitys==''){
			openAlert('没选中商品');
			return ;
		}
	  if(receiverId=="" || receiverId=='undefined'){
			openAlert('请填写地址');
			return ;
	  }
	  //页面防表单提交
	  if(!flag){
		  openAlert('正在生成订单，请稍后');
		  return;
	  }
	  flag = false; 
	   //1、*检查token
		 $.ajax({
				url:base+"/phone/checkToken",
				data:{"clientToken":clientToken},
				method:'POST',
				dataType:'json',
				success:function(data){
					if(data!=null && data.status=="success"){
						//检查库存
						var b = checkCart(goodIds,quantitys);
						if(b){
							///生成订单
							var orderNo = createOrder(cartIds,receiverId,payWay,goodIds,quantitys,memo,payfrom,deliverType,zitiAddress,teamId);
							if(orderNo!=null && orderNo!=''){
								//验证用户，并获取预支付单
								var topayurl = checkUser(orderNo);
								if(topayurl!=''){
									window.location.href = topayurl;
									return ;
								}
							}
						}
					}else{
						  openAlert('正在生成订单，请稍后');
					}
				},
				error:function(){
					  openAlert('正在生成订单，请稍后');
				}
		 });		
  });
  //2、检查库存
  function checkCart(goodIds,quantitys){
	  b = false;
	  $.ajax({
		  	async: false, 
			url:base+"/phone/checkCart",
			data:{"goodIds":goodIds,"quantitys":quantitys},
			method:'POST',
			dataType:'json',
			success:function(data){
				if(data!=null && data.status=="success"){
					 b = true;
				}else if(data!=null && data.status=="error"){
					openAlert(data.message);
				}else{
					openAlert("检查库存失败!");
				}
			},
			error:function(){
				openAlert("检查库存失败!");
			}
	 });
	  return b;
  }

//3、创建订单
  function createOrder(cartIds,receiverId,payWay,goodIds,quantitys,memo,payfrom,deliverType,zitiAddress,teamId){
	  orderNo = '';
	  $.ajax({
		  	async: false, 
			url:base+"/phone/creatOrder",
			data:{"cartIds":cartIds,"receiverId":receiverId,"payWay":payWay,"goodIds":goodIds,
				"quantitys":quantitys,"memo":memo,"payfrom":payfrom,
				"deliverType":deliverType,"zitiAddress":zitiAddress,
				"teamId":teamId},
			method:'POST',
			dataType:'json',
			success:function(data){
				if(data!=null && data.status=="success"){
					orderNo = data.orderNo;
				}else{
					openAlert("创建订单失败!");
				}
			},
			error:function(){
				//openAlert("创建订单失败!");
			}
	 });	
	  return orderNo;
  }
  
  //验证用户并获取 生成预支付id的连接
  function checkUser(orderNo){
	  topayUrl = '';
	  $.ajax({
		  	async: false, 
			url:base+"/phone/checkUser",
			data:{"orderNo":orderNo},
			method:'POST',
			dataType:'json',
			success:function(data){
				if(data!=null && data.status=="success"){
					topayUrl = data.url;
				}else{
					openAlert("验证用户失败!");
				}
			},
			error:function(){
				openAlert("验证用户失败!");
			}
	 });	
	  return topayUrl;
  }
  
  
  //打开上门自提地址
  $('.manual').on('click',function(){
    $(this).parents('table').find('.row-dizhi').css('display','table-row');
    $(this).attr("checked","true"); 
    $('.auto').attr("checked","false"); 
  })
  //关闭上门自提地址
  $('.auto').on('click',function(){
    $(this).parents('table').find('.row-dizhi').css('display','none');
    $(this).attr("checked","true"); 
    $('.manual').attr("checked","false"); 
  })
  
  	/**封装送货方式*/
	function getDeliveryType(dom){ 
		var type = $('input[type=radio]:checked').val();
		if(type === 'shsm')
			return '1';//送货上门
		else
			return '0';//自提
	}
	
 
  
  //商品数量减一
  $('.minus').on('click', function() {
	  var quantity = $("#quantity").val();
	  quantity>1 ? quantity-- : quantity=1;
	//刷新商品总数
    $("#quantity").val(quantity);
	//刷新总金额
	calcMoney()
  });
 
//防止用户点击过快，后台一个商品产生两个购物篮
var addFlag = true;
//商品数量加1
$('.plus').on('click', function() {
	debugger;
	  if(!addFlag){
		  return;
	  }
	  addFlag = false;
	  var quantity = $("#quantity").val();
      quantity++;
	  var goodId = $('#goodId').val().replace(',', '');
   $.ajax({
			url : base+'/phone/beforePayCheckStore',
			data : {
				"goodId":goodId,
				"quantities":1
			},
			method : 'post',
			dataType : 'json',
			success : function(data) {
				if ('success' == data.status) {
					addFlag = true;
				 	//刷新商品总数
					$("#quantity").val(quantity);
					//刷新总金额
					calcMoney();
				} else {
					openAlert(data.message);
					$("#quantity").val(quantity--);
				}
			},
			error : function() {
				openAlert('系统异常~一会再试一下噢');
				$("#quantity").val(quantity--);
			}
		});
    
});

//修改文本框
$('.goodsNum').blur(function() {
	  goodsNum = $(this).val();
	  var regexp1 ="^[0-9]*$";
	   if(goodsNum.trim().match(regexp1) == null){
	       openAlert('输入数值哦~');
         $('.goodsNum').val(1);
         return false;
     }
	   if(goodsNum==0){
		   $(this).val(1);
		   return;
	   }
	  var goodId = $('#goodId').val().replace(',', '');
   $.ajax({
			url : base+'/phone/beforePayCheckStore',
			data : {
				"goodId":goodId,
				"quantities":goodsNum
			},
			method : 'post',
			dataType : 'json',
			success : function(data) {
				if ('success' == data.status) {
				 	//刷新商品总数
					 $("#quantity").val(goodsNum);
					//刷新总金额
					calcMoney();
				} else {
					openAlert(data.message);
					$("#quantity").val(1);
				}
			},
			error : function() {
				openAlert('系统异常~一会再试一下噢');
				$("#quantity").val(1);
			}
		});
    
});

//直接购买 或者拼团， 在支付确认前修改数量时修改总金额
function calcMoney(){
	var payWay = $("#payWay").val();
	var salePrice = $("#salePrice").val();
	var combinPrice = $("#combinPrice").val();
	var quantity = $("#quantity").val();
	var total = 0;
	if("weixin"==payWay){
		total = salePrice*quantity;
	}else if("pingTuan"==payWay){
		total = combinPrice*quantity;
	}
	$(".total").html("￥"+total.toFixed(2));
}
