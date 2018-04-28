<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<title>新增商品图片</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
		<link rel="stylesheet" href="../layui/dist/css/layui.css"  media="all">
	</head>

	<body>
	<form class="layui-form" action="" name="actionForm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="id" value="">
		<fieldset class="layui-elem-field" style="width: 60%; text-align: center; margin-left: 20%;">
			<legend>新增商品分类</legend>
			<div class="layui-form-item">
		  	</div>
  			
  			<div class="layui-form-item">
				<label class="layui-form-label">分类名称</label>
			    <div class="layui-input-inline" style="width:70%">
			        <input type="text" name="name" lay-verify=""  id="name" value="" placeholder="" autocomplete="off" class="layui-input">
		  		</div>
		  		<div class="layui-form-mid layui-word-aux"><span style="color:red;">*</span></div>
		  	</div>
		  	<div class="layui-form-item">
			  	<label class="layui-form-label">排序</label>
			    <div class="layui-input-inline" style="width:70%">
			        <input type="text" name="sortNum" lay-verify="" value="1" id="sortNum" placeholder="0" autocomplete="off" class="layui-input">
		  		</div>
		  		<div class="layui-form-mid layui-word-aux"><span style="color:red;"></span></div>
		  	</div>
		  	<div class="layui-form-item">
			    <label class="layui-form-label">设置状态</label>
			    <div class="layui-input-inline" style="width:70%">
			      	<select name="status" lay-verify="" id="status">
			      		<option value="">--请选择--</option>
			      		<option value="0">不显示</option>
				  		<option value="1">显示</option>
			      	</select>
		  		</div>
		  		<div class="layui-form-mid layui-word-aux"><span style="color:red;">*</span></div>
		  	</div>
		  	<div class="layui-form-item">
			    <div class="layui-input-block">
					<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
				    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
			    </div>
		  	</div>
		</fieldset>
	</form>
 	<script type="text/javascript" src="../plugins/layui/layui.js?v=2"></script>
	<script type="text/javascript" src="../js/jquery.min.js"></script>
	<script>
	//Demo
	layui.use('form', function(){
	  var form = layui.form();
	  
	  //监听提交
	  form.on('submit(formDemo)', function(data){
	  	if(!check()){return false;}
	  	
	  	if($("#name").val()==''){
	  		alert("请输入分类名称");
			return false;
	  	}
	  	var storeRegex = /^[0-9]*$/;
	  	if (!storeRegex.test($("#sortNum").val())){
	  		alert("请输正确的排序");
	  		return false;
	  	}
	  	
	  	var categoriesId = $("#categoriesId").val();
		if(categoriesId == ""){
			alert("请选择商品分类");
			return false;
		}
	  	document.actionForm.action="${request.contextPath}/goodclass/saveAdd";
    	document.actionForm.submit();
	  });
	});
	
	function check(){
		var status = $("#status").val();
		if(status == ""){
			alert("请选择状态");
			return false;
		}else{return true;}
	}
	</script>
	</body>
<script>
	layui.use('layedit', function(){
	 	 var layedit = layui.layedit,$ = layui.jquery;
	  
	 	 //设置图片
		  layedit.set({
			  uploadImage: {
			    url: '/hyzyactivities/ueditor', //接口url
			    type: 'POST' //默认post
			  }
		  });  
	 
	
		  //自定义工具栏
		  layedit.build('goodDecrib', {
			   tool: [
			  'strong' //加粗
			  ,'italic' //斜体
			  ,'underline' //下划线
			  ,'del' //删除线
			  ,'|' //分割线
			  ,'left' //左对齐
			  ,'center' //居中对齐
			  ,'right' //右对齐
			  ,'link' //超链接
			  ,'unlink' //清除链接
			  ,'face' //表情
			  ,'image' //插入图片 ,'help' //帮助
			] ,height: 400
		  });
	});
	</script>
</html>