<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加图文素材</title>
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="../wx/css/style.css" rel="stylesheet" type="text/css" />
<script src="../wx/js/jquery-1.7.2.js" type="text/javascript"></script>
<link href="../wx/css/style_1.css" rel="stylesheet" type="text/css" />
<link href="../wx/css/sub_style.css" rel="stylesheet" type="text/css" />
<link href="../wx/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="../wx/js/jquery-ui.js" type="text/javascript"></script>
<script src="../wx/js/editor_config.js" type="text/javascript"></script>
<script src="../wx/js/editor_all_min.js" type="text/javascript"></script>	
<script src="../wx/js/O2String.js"	type="text/javascript"></script>
<script src="../wx/js/JScriptImg.js" type="text/javascript"></script>		 
<script src="../wx/js/ajaxfileupload.js" type="text/javascript"></script>
	<script type="text/javascript">
				function onSubmitAction() {
					var sub_msg_item = $("#sub-msg-item").children();
					var sum = sub_msg_item.length;
					var titles = document.getElementsByName("title");
					var imgs = document.getElementsByName("news_images");
					for(var i=0;i<=sum;i++){
						if(null==titles[i].value||""==titles[i].value){
							alert("第"+(i+1)+"条标题为空！");
							return false;
						}
						if(null==imgs[i].value||""==imgs[i].value){
							alert("第"+(i+1)+"条封面图片为空！");
							return false;
						}	
					}
					return true;
				}				
				//添加预览效果的图文消息，和编辑部分的图文消息
				var _n = ${list?size};
				function addNews(){
					var sub_msg_item = $("#sub-msg-item").children();
					if(sub_msg_item.length<9){						
						_n++;
						var _MsgItem = "";
						_MsgItem += "<div id=\"sub-msg-item-"+_n+"\" class='rel sub-msg-item appmsgItem y" + _n + "' onmouseover=\"showBlock("+_n+")\" onmouseout=\"showNone("+_n+")\">";
						_MsgItem += "   <span id=\"img_s_"+_n+"\" class='thumb'>";
						_MsgItem += "       <span id=\"img_font_"+_n+"\" class='default-tip' style='display: block;'>缩略图</span>";
						_MsgItem += "       <img id=\"img_"+_n+"\" src='' class='i-img' style='display: none' onload='ImgMiddle(this,70,70)' />";
						_MsgItem += "   </span>";
						_MsgItem += "   <h4 id=\"title_"+_n+"\" class='msg-t'>标题</h4>";
						_MsgItem += "   <div id=\"op_"+_n+"\" class='op'>";
						_MsgItem += "       <a class='th opr-icon edit-icon co5'  onclick='EditItem(\"" + _n + "\")' >编辑</a>";
						_MsgItem += "       <a class='th opr-icon del-icon mL20 co4' onclick='DelItem(\"" + _n + "\")' >删除</a>";
						_MsgItem += "   </div>";
						_MsgItem += "</div>";
						$("#sub-msg-item").append(_MsgItem);
						var _valItem = "";
						_valItem += "<div id=\"val_item_sub_"+_n+"\" name=\"val_item_sub\" style=\"display:none\"><div class=\"control-group\">";
						_valItem += "<label class=\"control-label\"> 标题</label><span class=\"maroon\">*</span><span class=\"help-inline\">(必填,不能超过64个字)</span>";
						_valItem += "<div class=\"controls\">";
						_valItem += "<input value=\"标题\" id=\"news_title_"+_n+"\" class=\"input400\" name=\"title\" maxlength=\"64\" type=\"text\" onkeyup=\"previewTitle("+_n+",this);\"/>";
						_valItem += "</div></div>";
						_valItem += "<div class=\"clear\"></div><div class=\"control-group\">";
						_valItem += "<label class=\"control-label\"> 封面</label><span class=\"maroon\">*</span><span class=\"help-inline\">(必须上传一张图片)</span>";
						_valItem += "<div class=\"controls\"><div class=\"cover-area\">";
						_valItem += "<p id=\"upload-tip\" class=\"upload-tip right\">大图片建议尺寸：660像素 * 270像素</p><div class=\"cover-hd left\">";
						_valItem += "<input type=\"file\" name=\"images\" id=\"images_"+_n+"\" onchange=\"ajaxFileUpload(this,"+_n+");\"/>";
						_valItem += "<input name=\"news_images\" id=\"news_images_"+_n+"\" type=\"hidden\"/></div><div class=\"clear\"></div><div class=\"controls yl\">";
						_valItem += "<img class=\"i-img\" style=\"display: none; max-width: 400px; max-height: 100px;\" />";
						_valItem += "</div><div class=\"controls  CoverPic\"></div>";
						_valItem += "<div class=\"clear\"></div></div></div></div>";
						_valItem += "<div class=\"clear\"></div><div class=\"control-group\" id=\"info-block\">";
						_valItem += "<label class=\"control-label\"> 正文</label><span ";
						_valItem += "class=\"help-inline\">(不能超过10000个字)</span><div style=\"width:500px;\">";
						_valItem += "<textarea name=\"description\" rows=\"10\" style=\"width:491px;\"></textarea></div></div>";
						_valItem += "<div id=\"url-block\" class=\"control-group\"><label class=\"control-label\">信息来源</label> <span class=\"help-inline\">(请输入正确的URL链接格式)</span>";
						_valItem += "<div class=\"controls\"><input id=\"_news_url\" class=\"input400\" style=\"width: 482px;\" name=\"news_url\" type=\"text\" />";
						_valItem += "</div></div></div>";
						$("#val_item").append(_valItem);
					}else{
						alert("图文消息最多10条！");
					}					
				}
				//编辑子图文消息
				function EditItem(n){
					var valItemSubs = document.getElementById("val_item").children;
					for(var i=0;i<valItemSubs.length;i++){
						valItemSubs[i].style.display="none";
					}
					var valItemSub = document.getElementById("val_item_sub_"+n).style.display="block";
				}
				//删除图文消息
				function DelItem(n){
					var id = "sub-msg-item-"+n;
					var div_item = document.getElementById(id);
					var main_item = document.getElementById("sub-msg-item");
					main_item.removeChild(div_item);
					var valId = "val_item_sub_"+n;
					var val_sub_item = document.getElementById(valId);
					var val_main_item = document.getElementById("val_item");
					val_main_item.removeChild(val_sub_item);
					var val_ids = document.getElementById("val_id");
					var val_sub_id = document.getElementById("id_"+n);
					val_ids.removeChild(val_sub_id);
				}
				//显示编辑按钮
				function showBlock(n){
					var id = "op_"+n;
					$("#"+id).show();
				}
				//隐藏编辑按钮
				function showNone(n){
					var id = "op_"+n;
					$("#"+id).hide();
				}
		//异步上传文件	      	
		function ajaxFileUpload(_file,n) {
			debugger;
		 	if( !_file.value.match( /.jpg|.gif|.png/i ) ){   
		        alert('图片格式无效！');   
		        return false;   
		    }
		    var imgId = "images_"+n
            $.ajaxFileUpload
            (
                {
                	url: '${request.contextPath}/event/ajaxUploadFile', //用于文件上传的服务器端请求地址
                    secureuri: false, //一般设置为false
                    fileElementId: imgId, //文件上传空间的id属性  <input type="file" id="file" name="file" />
                    dataType: 'text', //返回值类型 一般设置为json
                    type:'post',
                    success: function (datas, status)  //服务器成功响应处理函数
                    {   
                  	  var startPosition = datas.indexOf('{');
                  	  var endPosition = datas.indexOf('}')+1;
                  	  datas = datas.substring(startPosition,endPosition);
                  	  var data = jQuery.parseJSON(datas);  
                   
  					var path = "${request.contextPath}";
                        var newsImagesId = "news_images_"+n;
                        var imgFontId = "img_font_"+n;
                        var imgId = "img_"+n;
                        $("#"+newsImagesId).val(data.filePath);
                        $("#"+imgFontId).hide(); 
                        $("#"+imgId).attr("src", path+data.filePath);
                        $("#"+imgId).show();
                    },
                    error: function (datas, status, e)//服务器响应失败处理函数
                    { 
                  	  datas = datas.responseText;
                  	  var startPosition = datas.indexOf('{');
                  	  var endPosition = datas.indexOf('}')+1;
                  	  datas = datas.substring(startPosition,endPosition);
                  	  var data = jQuery.parseJSON(datas); 
                  	  var path = "${request.contextPath}";
                        var newsImagesId = "news_images_"+n;
                        var imgFontId = "img_font_"+n;
                        var imgId = "img_"+n;
                        $("#"+newsImagesId).val(data.filePath);
                        $("#"+imgFontId).hide(); 
                        $("#"+imgId).attr("src", path+data.filePath);
                        $("#"+imgId).show();
                    }
                }
            )
            return false;
        }
        //预览标题
        function previewTitle(n,obj){
        	var id = "title_"+n;
        	$("#"+id).html(obj.value);
        }
			</script>
			<style type="text/css">
.reply {
	width: 1150px;
	position: static;
}

.msg-item .cover {
	
}

.span5 {
	width: 470px;
	float: left;
	margin-left: 0px;
}

.span7 {
	width: 670px;
	float: left;
	margin-left: 10px;
}

.msg-item-wrapper {
	margin-bottom: 26px;
	border: 1px solid rgb(184, 184, 184);
	background-color: rgb(244, 244, 244);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.1);
	border-radius: 5px 5px 5px 5px;
}

.msg-item-wrapper {
	background-color: rgb(255, 255, 255);
}

.msg-meta {
	margin: 0px 14px 6px;
	font-size: 13px;
}

.msg-item {
	padding: 2px 0px;
	background-color: rgb(255, 255, 255);
	border-bottom: 1px solid rgb(204, 204, 204);
	box-shadow: 0px 2px 4px rgba(0, 0, 0, 0.2);
	border-radius: 5px 5px 0px 0px;
	width: auto;
	border: 0;
	min-height: 225px;
}

.msg-preview .msg-item {
	border-radius: 5px 5px 5px 5px;
	box-shadow: none;
}

#appmsgItem1 {
	border-bottom: 0px none;
}

.appmsgItem {
	
}

#sub-msg-item {
	cursor: pointer;
}

.sub-msg-item {
	padding: 12px 14px;
	overflow: hidden;
	border-top: 1px solid rgb(198, 198, 198);
}

.thumb {
	float: right;
	font-size: 0px;
	width: 70px;
	height: 70px;
	overflow: hidden;
}

.default-tip {
	display: block;
	text-align: center;
	background-color: rgb(236, 236, 236);
	text-shadow: 0px 1px 0px rgb(255, 255, 255);
	font-size: 22px;
	color: rgb(170, 170, 170);
	line-height: 160px;
}

.thumb .default-tip {
	font-size: 16px;
	color: rgb(192, 192, 192);
	width: 70px;
	line-height: 70px;
	border: 1px solid rgb(178, 184, 189);
}

.sub-msg-item .msg-t {
	margin-left: 0px;
	margin-right: 85px;
	margin-top: 0px;
	padding-left: 4px;
	padding-top: 12px;
	line-height: 24px;
	max-height: 48px;
	font-size: 14px;
	overflow: hidden;
}

.sub-msg-item .msg-t {
	line-height: 24px;
	font-size: 14px;
	word-break: break-all;
	width: 350px;
}

.sub-add {
	padding: 12px 14px;
	border-top: 1px solid rgb(198, 198, 198);
}

.dib {
	display: inline-block;
}

.vm {
	vertical-align: middle;
}

.sub-add-btn {
	line-height: 70px;
	color: rgb(34, 34, 34);
	border: 2px dotted rgb(184, 184, 184);
	border-radius: 5px 5px 5px 5px;
	display: block;
}

.sub-add-icon {
	width: 18px;
	height: 18px;
	margin-right: 5px;
	background: url("../../images/icon18.png") no-repeat scroll 0px -83px
		transparent;
}

.msg-editer-wrapper {
	
}

.msg-editer {
	background-color: rgb(248, 248, 248);
	border: 1px solid rgb(184, 184, 184);
	border-radius: 5px 5px 5px 5px;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.1);
	padding: 14px 0px 20px 20px;
}

.control-group {
	margin-bottom: 10px;
}

.maroon {
	color: rgb(210, 0, 0);
	padding-left: 2px;
	font-family: "宋体";
}

.help-inline {
	display: inline-block;
	vertical-align: middle;
	padding-left: 5px;
	color: #999;
}

.msg-input,.cover-area,.msg-txta {
	background-color: rgb(255, 255, 255);
	border: 1px solid rgb(211, 211, 211);
	color: rgb(102, 102, 102);
	max-width: 480px;
	padding: 2px 8px;
	width: 480px;
	border-radius: 4px 4px 4px 4px;
}

.cover-area {
	max-width: 482px;
	padding: 0px;
	width: 482px;
	padding: 10px;
}

.upload-tip {
	color: rgb(102, 102, 102);
	font-size: 12px;
	line-height: 28px;
	text-align: right;
	top: 3px;
	right: 8px;
}

.del-icon,.edit-icon {
	cursor: pointer;
}

.sub-msg-item .i-img {
	width: 70px;
}

.appmsgItem .msg-con,.appmsgItem .msg-url,.appmsgItem .msg-CoverPic,.appmsgItem .msg-type,.op
	{
	display: none;
}

.op_select {
	background-color: #ECECEC;
}

.CoverPic label,.CoverPic input {
	cursor: pointer;
}
</style>
</head>
<body>
		<div id="box_main" class="hig">
			<!--main_top注释-->
			<div class="main_top co7">
				<span>
				</span>
			</div>
			<div class="clear"></div>
		</div>
		<div class="Mix_W600">
			<div style="height: 577px; width: 1036px;" id="submain">
				<div class="hig border">
					<h1 class="tit2 co1 left">多图文编辑</h1>
				</div>
				<div class="clear"></div>
				<div class="reply top size co1">
					<div class="span5 msg-preview">
						<div class="msg-item-wrapper">
							<div style="background:#ffefaf"
								class="appmsgItem msg-item  y1 op_select" data-no="1" id="sub-main-item">
								<p class="msg-meta">
									<span id="_date" class="msg-date"><#-- ${wm.createTime} --></span>
								</p>
								<div class="cover">
									<#if (wm.picUrl=='')>
										<p id="img_font_0" class="default-tip">封面图片</p>
										<img id="img_0" class="i-img" style="display: none" src=""
										onload="ImgMiddle(this,440,160)" />
									</#if>									
									<h4 id="title_0" class="msg-t">${(wm.title)!''}</h4>
									<#if wm.picUrl!='' >
										<img id="img_0" class="i-img" src="${request.contextPath}${(wm.picUrl)!''}" onload="ImgMiddle(this,440,160)" />
									</#if>
								</div>
								<div class="mL20 msg-con"></div>
								<div class="mL20 msg-url"></div>
								<div class="mL20 msg-CoverPic">
									<input checked="checked" class="ShowCoverPic" type="checkbox" />
								</div>
								<div class="mL20 msg-type">0</div>
								<div style="display: block;" class="op">
									<a class="th opr-icon edit-icon co5 mL20" onclick="EditItem('0')">编辑</a>
								</div>
							</div>
							<div class="ui-sortable" id="sub-msg-item">
							<#list list as wm1>
							<div id="sub-msg-item-${wm1_index+1}" class='rel sub-msg-item appmsgItem y${wm1_index+1}' onmouseover="showBlock(${wm1_index+1})" onmouseout="showNone(${wm1_index+1})">
							<span id="img_s_${wm1_index+1}" class='thumb'>
							<span id="img_font_${wm1_index+1}" class='default-tip' style='display: none;'>缩略图</span>
							<img id="img_${wm1_index+1}" src='${request.contextPath}${(wm1.picUrl)!''}' class='i-img' style='display: block' onload='ImgMiddle(this,70,70)' />
							</span>
							<h4 id="title_${wm1_index+1}" class='msg-t'>${(wm1.title)!''}</h4>
							<div id="op_${wm1_index+1}" class='op'>
						 	<a class='th opr-icon edit-icon co5'  onclick='EditItem(${wm1_index+1})' >编辑</a>
							<a class='th opr-icon del-icon mL20 co4' onclick='DelItem(${wm1_index+1})' >删除</a>
							</div>
							</div>
							</#list>
							</div>
							<div class="sub-add">
								<a href="javascript:addNews();" class="block tc sub-add-btn c"><span
									class="vm dib sub-add-icon"> </span>增加一条</a>
							</div>
						</div>
					</div>				
						<div class="span7">
							<div class="msg-editer-wrapper">
								<div class="msg-editer">
								<form method="post" action="${request.contextPath}/event/saveWeixinMaterial" enctype="multipart/form-data" onsubmit="return onSubmitAction();">
								<input type="hidden" name="id" value="${(wm.id)!}"/>
							
								<div id="val_id">
								<#list list as wm2>
									<input type="hidden" id="id_${wm2_index+1}" name="id" value="${wm2.id}"/>
								</#list>
								</div>
								<div id="val_item">
									<div id="val_item_sub_0" name="val_item_sub">
									<div class="control-group">
										<label class="control-label"> 标题</label><span class="maroon">*</span><span
											class="help-inline">(必填,不能超过64个字)</span>
										<div class="controls">
											<input value="${(wm.title)!''}" id="news_title_0" class="input400" name="title" maxlength="64" type="text" onkeyup="previewTitle(0,this);"/>
										</div>
									</div>
									<div class="clear"></div>
									<div class="control-group">
										<label class="control-label"> 封面</label><span class="maroon">*</span><span
											class="help-inline">(必须上传一张图片)</span>
										<div class="controls">
											<div class="cover-area">
												<p id="upload-tip" class="upload-tip right">
													大图片建议尺寸：660像素 * 270像素</p>
												<div class="cover-hd left">
													<input type="file" name="images" id="images_0" onchange="ajaxFileUpload(this,0);"/>
													<input name="news_images" id="news_images_0" type="hidden" value="${(wm.picUrl)!""}"/>
												</div>
												<div class="clear"></div>
												<div class="controls yl">
													<img class="i-img"
														style="display: none; max-width: 400px; max-height: 100px;" />
												</div>
												<div class="controls  CoverPic">													
												</div>
												<div class="clear"></div>
											</div>
										</div>
									</div>
									<div class="clear"></div>
									<div class="control-group">
										<label class="control-label"> 正文</label><span
											class="help-inline">(不能超过10000个字)</span>
										<textarea id="_description" name="description" rows="10" style="width:491px;">${(wm.description)!""}</textarea>
									</div>
									<div id="url-block" class="control-group">
										<label class="control-label">信息来源</label> <span
											class="help-inline">(请输入正确的URL链接格式)</span>
										<div class="controls">
											<input id="_news_url" class="input400" style="width: 482px;" name="news_url" type="text" value="${(wm.vistUrl)!""}"/>
										</div>
									</div>
									</div>
									<#list list as wm3>
									
									<div id="val_item_sub_${wm3_index+1}" name="val_item_sub" style="display:none"><div class="control-group">
									<label class="control-label"> 标题</label><span class="maroon">*</span><span class="help-inline">(必填,不能超过64个字)</span>
									<div class="controls">
									<input value="${(wm3.title)!''}" id="news_title_${wm3_index+1}" class="input400" name="title" maxlength="64" type="text" onkeyup="previewTitle(${wm3_index+1},this);"/>
									</div></div>
									<div class="clear"></div><div class="control-group">
									<label class="control-label"> 封面</label><span class="maroon">*</span><span class="help-inline">(必须上传一张图片)</span>
									<div class="controls"><div class="cover-area">
									<p id="upload-tip" class="upload-tip right">大图片建议尺寸：660像素 * 270像素</p><div class="cover-hd left">
									<input type="file" name="images" id="images_${wm3_index+1}" onchange="ajaxFileUpload(this,${wm3_index+1});"/>
									<input name="news_images" id="news_images_${wm3_index+1}" type="hidden" value="${(wm3.picUrl)!''}"/></div><div class="clear"></div><div class="controls yl">
									<img class="i-img" style="display: none; max-width: 400px; max-height: 100px;" />
									</div><div class="controls  CoverPic"></div>
									<div class="clear"></div></div></div></div>
									<div class="clear"></div><div class="control-group" id="info-block">
									<label class="control-label"> 正文</label><span class="help-inline">(不能超过10000个字)</span><div style="width:500px;">
									<textarea name="description" rows="10" style="width:491px;">${(wm3.description)!''}</textarea></div></div>
									<div id="url-block" class="control-group"><label class="control-label">信息来源</label> <span class="help-inline">(请输入正确的URL链接格式)</span>
									<div class="controls"><input id="_news_url" class="input400" style="width: 482px;" name="news_url" type="text" value="${(wm3.vistUrl)!''}"/>";
									</div></div></div>
									</#list>
									</div>								
									<div class="control-group">
										<div class="controls">
											<input id="save-btn" value="保存" class="greenbtn100" type="submit" />&nbsp;&nbsp;
											<input id="save-btn" value="返回" class="greenbtn100" type="button" onclick="window.location.href='${request.contextPath}/event/weixin_material_list'"/>
										</div>
									</div>
									</form>
								</div>
								<span class="abs msg-arrow a-out" style="margin-top: 0px;"></span><span
									class="abs msg-arrow a-in" style="margin-top: 0px;"></span>
							</div>
						</div>
					<div class="clear"></div>
				</div>
			</div>
	</div>			
</body>
</html>