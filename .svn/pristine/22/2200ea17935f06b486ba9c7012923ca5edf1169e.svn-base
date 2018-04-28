<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<title><#if (record.id)??>编辑<#else>添加</#if></title>
		<link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../../css/global.css" media="all">
		<link rel="stylesheet" href="../../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../../css/table.css" />
		<link rel="stylesheet" href="../../layui/dist/css/layui.css"  media="all">
	</head>

	<body>
	<form class="layui-form" action="" name="actionForm" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="id" value="${(record.id)!}">
		<input type="hidden" name="goodImage" value="${pictureVisitUrl}${(record.goodImage)!}">
		<fieldset class="layui-elem-field" style="width: 60%; text-align: center; margin-left: 20%;">
			<legend><#if (record.id)??>编辑<#else>添加</#if></legend>
			<div class="layui-form-item">
		  	</div>
  			<div class="layui-form-item">
				<label class="layui-form-label">分类名称</label>
			    <div class="layui-input-inline" style="width:70%">
			        <input type="text" name="name" lay-verify="" id="name" value="${(record.name)!}" placeholder="" autocomplete="off" class="layui-input">
		  		</div>
		  		<div class="layui-form-mid layui-word-aux"><span style="color:red;">*</span></div>
		  	</div>
		  	 <div class="layui-form-item">
			  	<label class="layui-form-label">分类排序</label>
			    <div class="layui-input-inline" style="width:70%">
			        <input type="number" name="sortNum" lay-verify="" value="${(record.sortNum)!'99999'}"  id="sortNum" placeholder="0" autocomplete="off" class="layui-input">
		  		</div>
		  		<div class="layui-form-mid layui-word-aux"><span style="color:red;">*</span></div>
		  	</div>
		  	<div class="layui-form-item">
			    <label class="layui-form-label">设置状态</label>
			    <div class="layui-input-inline" style="width:70%">
			      	<select name="status" lay-verify="" id="status">
			      		<option value="">--请选择--</option>
			      		<option value="1" <#if (((record.status)!'') == '1')>selected="selected"</#if>>使用</option>
				  		<option value="0" <#if (((record.status)!'') == '0')>selected="selected"</#if>>停用</option>
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
	
 	<script type="text/javascript" src="../../plugins/layui/layui.js?v=2"></script>
	<script type="text/javascript" src="../../js/jquery.min.js"></script>
	<script>
		layui.use('form', function(){
		  var form = layui.form();
		  //监听提交
		  form.on('submit(formDemo)', function(data){
		  	
		  	var regex = /^([\d]+(\.\d{1,2})?$)|^([1-9]+)$/;
		  	if($("#name").val()==''){
		  		alert("请输入分类名称");
				return false;
		  	}
		  	document.actionForm.action="${request.contextPath}/backpage/beanGoodsClass/input";
	    	document.actionForm.submit();
		  })
		});
		
		function check(){
			var status = $("#status").val();
			if(status == ""){
				alert("请选择是否停用");
				return false;
			}else{return true;}
		}
	</script>
	</body>
</html>