var base ='/hyzyactivities';

$.fn.ready( function(){
	 calcMoney();
	var dataNum = 1;
	
	   //radio点击切换样式
	    $('input[type=radio]').on('click',function(){
	      if($(this).attr('checked')){
	        $(this).removeAttr('checked')
	      }else{
	        $(this).attr('checked','checked')
	      }  
	      calcMoney();
	    });
	    

	    //radio选择全部
	    $('.selectAll').on('click',function(){
	      var $allRadio = $('.caret-list').find('input[type=radio]');
	      if($(this).prop('checked')){
	        $allRadio.prop('checked',true);
	      }else{
	        $allRadio.removeAttr('checked');
	      }
	      calcMoney();
	    });
	    
		//商品批量删除
		$(".del-caret").click(function(){
			$('.modal-comfirm').find('p').html("<center><font size='3'>真的狠心删除吗？</font></center>");
			$('#delwhate').val(1);
			opencomfirm();  
		})
	
	     //商品删除
		  $(".del-list").click(function(){
			
			 var goodId =  $(this).attr("id");
			  $('.modal-comfirm').find('p').html("<center><font size='3'>真的狠心删除吗？</font></center>");
			  $('#delwhate').val(0);
			  $('#delOne').val(goodId);
			  opencomfirm(); 
			   
		  })
		  
		    //商品数量减一
		    $('.minus').on('click', function() {
		      var input = $(this).siblings('.goodsNum'),
		          count = input.val();
		      count>0 ? count-- : count=0;
		      var goodId = $(this).siblings('#goodId').val().replace(',', '');
		      $.ajax({
					url : base+'/phone/ajaxCartMinus',
					data : {
						"goodId":goodId,
						"quantities":1
					},
					method : 'post',
					dataType : 'json',
					success : function(data) {
						if ('success' == data.status) {
						 	//刷新购物车商品总数
							input.val(count);
							//刷新总金额
							calcMoney();
							if(count==0){
						    	  window.location.href = base+'/phone/getCartList';
						    }
						} else {
							openAlert(data.message);
						}
					},
					error : function() {
						openAlert('系统异常~一会再试一下噢');
					}
				});
		      
		    });
		   
		//防止用户点击过快，后台一个商品产生两个购物篮
		var addFlag = true;
		//商品数量加1
	    $('.plus').on('click', function() {
	    	  if(!addFlag){
	    		  return;
	    	  }
	    	  addFlag = false;
	    	  var input = $(this).siblings('.goodsNum');
	          count = input.val();
	    	  count++;
	    	  var goodId = $(this).siblings('#goodId').val().replace(',', '');
		     $.ajax({
					url : base+'/phone/ajaxAddCart',
					data : {
						"goodId":goodId,
						"quantities":1
					},
					method : 'post',
					dataType : 'json',
					success : function(data) {
						if ('success' == data.status) {
							addFlag = true;
						 	//刷新购物车商品总数
							input.val(count);
							//刷新总金额
							calcMoney();
						} else {
							openAlert(data.message);
						}
					},
					error : function() {
						openAlert('系统异常~一会再试一下噢');
					}
				});
		      
		 });

	    //修改文本框
	    $('.goodsNum').blur(function() {
	    	  goodsNum = $(this).val();
	    	  var regexp1 ="^[0-9]*$";
			   if(goodsNum.trim().match(regexp1) == null){
			       openAlert('输入数值哦~');
		           $('#goodsNum').val(1);
		           return false;
		       }
			   if(goodsNum==0){
			  	 openAlert('亲~要买多一点哦');
			   	 return false;
			   }
	    	  var goodId = $(this).siblings('#goodId').val().replace(',', '');
		     $.ajax({
					url : base+'/phone/updateCart',
					data : {
						"goodId":goodId,
						"quantities":goodsNum
					},
					method : 'post',
					dataType : 'json',
					success : function(data) {
						if ('success' == data.status) {
						 	//刷新购物车商品总数
							 $(this).val(count)
							//刷新总金额
							calcMoney();
						} else {
							openAlert(data.message);
						}
					},
					error : function() {
						openAlert('系统异常~一会再试一下噢');
					}
				});
		      
		 });

	
		 
})

 	  //开始删除商品
	  function deleteCardProduct(){
		  var delwhate= $('#delwhate').val();	//1 批量删除   0删除单条
		  var aa = $(".del-caret");
		  if(delwhate==1){
			  var checkedProduct = $(".selectOne:checked");
				var goodIds = "";
				checkedProduct.each(function(){
					goodIds +=$(this).val().replace(',', '')+"@@@";
					$(".del-caret").removeAttr('checked');
				})
				if(goodIds==""){
					openAlert('请选择要删除的商品');
					return ;
				}
				  $.ajax({
						url : base+'/phone/deleteCartList',
						data : {
							"goodIds":goodIds
						},
						method : 'post',
						dataType : 'json',
						success : function(data) {
							if ('success' == data.status) {
								
								//删除行节点
								checkedProduct.each(function(){
									$(this).closest('tr').remove();
								})
								if($('.caret-list').length==1){//只剩下全选节点
									window.location.href = base+'/phone/getCartList';
								} 
								//刷新总金额
								flag = calcMoney();
							} else {
								openAlert(data.message);
							}
						},
						error : function() {
							openAlert('系统异常~一会再试一下噢');
						}
					});
		  }
		  if(delwhate==0){
			    var goodId = $("#delOne").val().replace(',', '');
			    //父表格行节点
			    parentDom = $("#"+goodId).closest('tr'),
			    $.ajax({
					url : base+'/phone/deleteCart',
					data : {
						"goodId":goodId
					},
					method : 'post',
					dataType : 'json',
					success : function(data) {
						if ('success' == data.status) {
							//刷新总金额
							flag = calcMoney();
							if(!flag){
								window.location.href = base+'/phone/getCartList';
							}else{
								//删除行节点
								parentDom.remove();
								calcMoney();
							}
							
						} else {
							openAlert(data.message);
						}
					},
					error : function() {
						openAlert('系统异常~一会再试一下噢');
					}
				});
		  }
	  }
	  


//重新计算金额
function calcMoney(){
	var $allGoods = $('.caret-list').find('tbody tr'),
	memberAmoutDOM = $('.memberAmout'),
	unmemberAmoutDOM = $('.unmemberAmout'),
    totalSalePrice = 0;
	totalCombinPrice = 0;
	totalCombinScore = 0;
	flag = false;
	debugger;
	$allGoods.each(function(index,value){
		var isSelect = $(this).find('input[type=radio]').prop('checked');
			if(isSelect){
				if(index!=0)
					flag = true;
				var count = $(this).find('.goodsNum').val(),
				salePrice = $(this).find('#salePrice').val()*1;
				combinPrice = $(this).find('#combinPrice').val()*1;
				combinScore = $(this).find('#combinScore').val()*1;
				totalSalePrice += salePrice * count;
				totalCombinPrice += combinPrice * count;
				totalCombinScore += combinScore * count;
			}
  
	});
	memberAmoutDOM.html('真龙会员：'+totalCombinPrice.toFixed(2)+'元+'+totalCombinScore+'龙币,');//totalMoney.toFixed(2)
	unmemberAmoutDOM.html('非会员：'+totalSalePrice.toFixed(2)+'元');
	$('#totalCombinScore').val(totalCombinScore);//用于跳转真龙用户支付页面时，校验用户龙币是否充足
	return flag;
}

//去支付
function topay(payWay){
	calcMoney();
	debugger;
	var flag = false;
	$('.modal-comfirm2').hide();
	var totalCombinScore = $('#totalCombinScore').val();
	//商品校验
	var checkedProduct = $(".selectOne:checked");
	var goodIds = "";
	checkedProduct.each(function(){
		goodIds +=$(this).val().replace(',', '')+"@@@";
	})
	if(goodIds==""){
		openAlert('要选择商品才可以支付哦');
		return ;
	}
	//真龙会员校验
	if(payWay=='weixinAndCoin'){//微信加龙币支付,验证是否是真龙会员以及龙币是否够支付
		$.ajax({
			url : base+'/phone/checkLongbiCoin',
			data : {
				"tatolScore":totalCombinScore
			},
			method : 'post',
			timeout : 3000, //超时时间设置，单位毫秒
			dataType : 'json',
			success : function(data) {
				if ('success' == data.status) {
					window.location.href=base+"/phone/preOrder?goodIds="+goodIds+"&payWay="+payWay;
				} else {;
					//openAlert(data.message);
					openAlert('您的龙币不足!');
				}
			},
			error : function() {
				openAlert('系统异常~一会再试一下噢');
			}
		});
	}
	//跳转预支付页面
	if(payWay=='weixin'){
		window.location.href=base+"/phone/preOrder?goodIds="+goodIds+"&payWay="+payWay;
	}
	
}
 