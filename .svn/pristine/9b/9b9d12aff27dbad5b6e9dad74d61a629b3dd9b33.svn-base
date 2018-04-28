//过滤脚本
function filterOutScript(val) {
	return val.replace(/<script.*?>.*?<\/script>/ig, '');  
}
//判断非空
function isNullValue(val) {
	if (val == '') {return true;}
	var regu = "^[ ]+$|^[ ]+$";
	var re = new RegExp(regu);
	if (re.test(val) == true) {return true;}
	return false;
}
function addAdress(){

	$("#provinceName").val($("#provinceId").find("option:selected").text());
	$("#cityName").val($("#cityId").find("option:selected").text());
	$("#areaName").val($("#areaId").find("option:selected").text());	
	
	var name=filterOutScript($("#name").val());
	var mobile=$("#mobile").val();
	var provinceName=$("#provinceName").val();
	var cityName=$("#cityName").val();
	var address=filterOutScript($("#address").val());
	var village=$("#village").val();
	var outString1=$("#outString1").val();
	var outString2=$("#outString2").val();
	
	var longitude=$("#lng").val();
	var latitude=$("#lat").val();
	var areaId = $("#areaId").val();
	var areaName = $("#areaName").val();
	var cityId = $("#cityId").val();
	var provinceId = $("#provinceId").val();
	var zipCode = $("#zipCode").val();
	var id = $("#id").val();
	var fromW = $("#fromW").val();//判断是从哪个页面提交 1、首页 2、我的地址列表
	var villageCompared = $("#villageCompared").val();//判断用户所选的 village是否非法
	
	
	if(isNullValue(cityName)){
		$('.popover-success').text('请选择市区！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(villageCompared!=village){
		$('.popover-success').text('请从系统提供的地址栏中，选择所在街道！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(isNullValue(village)){
		$('.popover-success').text('请填写所在街道！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(isNullValue(address)){
		$('.popover-success').text('请填写详细地址！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	
	if(isNullValue(name)){
		$('.popover-success').text('请填写收货人！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(isNullValue(mobile)){
		$('.popover-success').text('请填写手机号码！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	
	if(!isNullValue(mobile)){
		var regu = /^1[3|4|5|8][0-9]\d{8}$/;
		if (!regu.test(mobile)) {
			$('.popover-success').text('请填写有效的11位手机号码！').addClass('show');
			setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
			return false;
		}
	}
	if(mobile.length!=11){
		$('.popover-success').text('请填写11位手机号码！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(isNullValue(longitude)){
		$('.popover-success').text('所在地区不存在，请重新选择！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		$('#controlMap').css("display","block");
		$('#closeBaidu').css("display","block");
		return false;
	}
	if(isNullValue(latitude)){
		$('.popover-success').text('所在地区不存在，请重新选择！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		$('#controlMap').css("display","block");
		$('#closeBaidu').css("display","block");
		return false;
	}
	
	if(!isNullValue(outString1)){
		if(village.indexOf(outString1)>=0){
			village = village.substr(outString1.length);
			$("#village").val(village);
		}
	}
	if(!isNullValue(outString2)){
		
		if(village.indexOf(outString2)>=0){
			$("#village").val(village.substr(outString2.length));
		}
	}
	$("#provinceName").val($("#provinceId").find("option:selected").text());
	$("#cityName").val($("#cityId").find("option:selected").text());
	$("#areaName").val($("#areaId").find("option:selected").text());
	$(".form").submit();
}

//订单确认页面，切换地址中新增地址
function addAdress2(){
	$("#cityName").val($("#cityId").find("option:selected").text());
	$("#areaName").val($("#areaId").find("option:selected").text());
	var name=filterOutScript($("#name").val());
	var mobile=$("#mobile").val();
	var provinceName=$("#provinceName").val();
	var cityName=$("#cityName").val();
	var address=filterOutScript($("#address").val());
	var village=$("#village").val();
	var outString1=$("#outString1").val();
	var outString2=$("#outString2").val();
	
	var longitude=$("#lng").val();
	var latitude=$("#lat").val();
	var areaId = $("#areaId").val();
	var areaName = $("#areaName").val();
	var cityId = $("#cityId").val();
	var provinceId = $("#provinceId").val();
	var zipCode = $("#zipCode").val();
	var id = $("#id").val();
	var fromW = $("#fromW").val();//判断是从哪个页面提交 1、首页 2、我的地址列表
	var villageCompared = $("#villageCompared").val();//判断用户所选的 village是否非法
	
		
	
	if(isNullValue(cityName)){
		$('.popover-success').text('请选择市区！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(villageCompared!=village){
		$('.popover-success').text('请从系统提供的地址栏中，选择所在街道！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(isNullValue(village)){
		$('.popover-success').text('请填写所在街道！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(isNullValue(address)){
		$('.popover-success').text('请填写详细地址！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	
	/*if(isNullValue(provinceName)){
		alert('请选择省份！');
		return false;
	}*/
	
	
/*	var isNull = true;
	for(var i=0;i<address.length;i++){
			if(!isNullValue(address.eq(i).val())){
				isNull = false; 
			}
	}
	
	if(isNull){
		alert('请填写详细地址！');
		return false;
	}*/
	if(isNullValue(name)){
		$('.popover-success').text('请填写收货人！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(isNullValue(mobile)){
		$('.popover-success').text('请填写手机号码！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	
	if(!isNullValue(mobile)){
		var regu = /^1[3|4|5|7|8][0-9]\d{8}$/;
		if (!regu.test(mobile)) {
			$('.popover-success').text('请填写有效的11位手机号码！').addClass('show');
			setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
			return false;
		}
	}
	if(mobile.length!=11){
		$('.popover-success').text('请填写11位手机号码！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		return false;
	}
	if(isNullValue(longitude)){
		$('.popover-success').text('所在地区不存在，请重新选择！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		$('#controlMap').css("display","block");
		$('#closeBaidu').css("display","block");
		return false;
	}
	if(isNullValue(latitude)){
		$('.popover-success').text('所在地区不存在，请重新选择！').addClass('show');
		setTimeout(function() { $('.popover-success').removeClass('show');}, 2000);
		$('#controlMap').css("display","block");
		$('#closeBaidu').css("display","block");
		return false;
	}
	
	
    var sids = $("#sids").val();
    var isBettwen = false;
    $.ajax({
        url:'supplier!checkAdrressDistanceByCoordinate.action',
        async: false, 
        data:{"latitude":latitude,"longitude":longitude,"sids":sids},
        method:'POST',
        dataType: "json",
        success: function(data){
          if(data.status=='error'){
             if('0'===data.errcode){
                return;
              }
              if('1'===data.errcode){
                return;
              }
              if('2'===data.errcode){
                alert(confirm('地址信息不完整，是否前去完善？'));
                return;
             }
             if('3'===data.errcode){
   	          str="<strong>您的收货地址不在["+data.supplierName+"]</strong>的配送范围内，不能配送哦";
   	             $('.content').find('p').html(str);
   	             openAlert();
   	             return;
              }
          
          }
        if(data.status=="success"){
        	if(!isNullValue(outString1)){
        		if(village.indexOf(outString1)>=0){
        			village = village.substr(outString1.length);
        			$("#village").val(village);
        			$('#villageCompared').val($('#village').val());
        		}
        	}
        	if(!isNullValue(outString2)){
        		
        		if(village.indexOf(outString2)>=0){
        			$("#village").val(village.substr(outString2.length));
        			$('#villageCompared').val($('#village').val());
        		}
        	}
        	$("#cityName").val($("#cityId").find("option:selected").text());
        	$("#areaName").val($("#areaId").find("option:selected").text());
        		
            areaId = $("#areaId").val();
        	areaName = $("#areaName").val();
            cityName=$("#cityName").val();
            village=$("#village").val();//截取省市县
        	isBettwen = true;
        }
        
        }});
	if(isBettwen)
		return true;
	else
		return false;
	//;
}




$.fn.ready( function() {
	$('#provinceId').change(function(){
		var provinceVal=$(this).val();
		var provinceName=$(this).find('option:selected').html();
		$.ajax({
			url:'address!ajaxProvinceChange.action',
  			data:{"provinceId":provinceVal},
  			method:'POST',
  			dataType:'json',
  			success:function(data){
  				if(data.status == 'success'){
  					$("#cityId option").each(function() {                            
                        $(this).remove();   //移除原有项                        
                    }); 
                    $("<option value=''>请选择</option>").appendTo("#cityId");
                    $(data.message).appendTo("#cityId");        //将返回来的项添加到下拉菜单中
                    
                    
                    $("#areaId option").each(function() {                            
                        $(this).remove();   //移除原有项                        
                    }); 
                    $("<option value=''>请选择</option>").appendTo("#areaId");
  				}
  			}
		});
		
		$('#provinceName').val(provinceName);
		$('#cityName').val("");
		$('#areaName').val("");
		$("#address").val("");
	});

	//市的改变事件
	$('#cityId').change(function(){
		var cityVal=$(this).val();
		var cityName=$(this).find('option:selected').html();
		$.ajax({
			url:'address!ajaxCityChanges.action',
  			data:{"cityId":cityVal},
  			method:'POST',
  			dataType:'json',
  			success:function(data){
  				if(data.status == 'success'){
  					$("#areaId option").each(function() {                            
                        $(this).remove();   //移除原有项                        
                    }); 
                    $("<option value=''>--请选择--</option>").appendTo("#areaId");
                    $(data.message).appendTo("#areaId");        //将返回来的项添加到下拉菜单中
  				}
  			}
		});
		
		$('#cityName').val(cityName);
		$('#areaName').val("");
		$('#village').val("");
		$("#address").val("");
		
	});

	
	//区的改变
	$('#areaId').change(function(){
		var areaName=$(this).find('option:selected').html();
		$('#areaName').val(areaName);
		$('#village').val("");
		$("#address").val("");
	});
	
	
	//设置默认地址
	$(".people").click(function(){
		    var dom = $(this);
			var id = dom.find("#id").val();
		    var defaultid = $(".selected").find("#id").val();
			$.ajax({
			url:'address!ajaxIsDefault.action',
  			data:{"id":id,"defaultId":defaultid },
  			method:'POST',
  			dataType:'json',
  			success:function(data){
  				if(data.status == 'success'){
  					$(".selected").removeClass("selected")
  					dom.addClass("selected"); 
  					window.location.href=data.url;
  				}
  			}
		});
	})
		 
		$(".backBtn.add").click(function(){
			
			//新增地址 
			window.location.href="address!list.action?pid="+$("#pid").val();
		}); 
	
		
		$(".backBtn.list").click(function(){
			//页面左上角返回按钮点击返回事件
		//	window.location.href="carItem2!preOrder.action?pid="+$("#pid").val();
			//window.location.href="${Session.address_redirectUrl}";
			javascript:history.back(-1);
		})
		
		
//		$(".xiaoqu").click(function(){ 
//			//选择详细地址
//			$("#address").val($(this).html());
//		})

});