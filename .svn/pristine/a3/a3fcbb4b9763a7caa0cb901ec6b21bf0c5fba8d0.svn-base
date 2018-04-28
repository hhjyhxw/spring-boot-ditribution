<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图文素材管理</title>
<meta http-equiv="Cache-Control" content="no-store">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<link  href="../wx/css/style.css" rel="stylesheet" type="text/css">
<link href="../wx/css/style_1.css" rel="stylesheet" type="text/css" />
<link href="../wx/css/sub_style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	//删除前提示
	 function delByid(id){
	 	if(confirm("是否要删除数据？\r\n删除后，数据不可恢复！")){
	 		jump("${request.contextPath}/event/delete?id="+id);
	 	}
	 }
	 //编辑
	 function edit(id){
	 	jump("${request.contextPath}/event/beforeEditWeixinMaterial?id="+id);
	 }
	
	 function addNews(){
	 	jump("${request.contextPath}/event/beforeAddWeixinMaterial");
	 }
	 
	 function next(){
	 	jump("${request.contextPath}/event/weixin_material_list?pageNo=${p.nextPage}");
	 }
	 function pre(){
	 	jump("${request.contextPath}/event/weixin_material_list?pageNo=${p.prePage}");
	 }
	 
	function jump(url){
		var e = document.createElement("a");
		e.href = url;
		document.body.appendChild(e);
		e.click();
	}
	
	</script>
</head>

<body>

    <div id="box_main" class="hig">
        <!--main_top注释-->
        <div class="main_top co7">
            
        </div>
    </div>
    <div class="Mix_W600">
        
    <div style="height: 477px; width: 1036px;" id="submain">
        <h1 class="title co1">
            素材管理</h1>
        <div class="tab top">
            <table class="table_1" border="0" cellspacing="0">
                <thead>
                    <tr class="bgco1 co1">
                        <th>标题</th>
                        <th style="width: 120px;">操作</th>
                    </tr>
                </thead>
                <tbody class="co1">
                <#list p.list as m>
                <tr>
                    <td>${m.title}</td>
                    <td>
                        [<a href="javascript:edit('${m.id}')">编辑</a>]
                        [<a title="删除" class="Del" href="javascript:delByid('${m.id}')">删除</a>]
                    </td>
                </tr>
                </#list>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="4" class="r">
                            <input name="ctl00$cph$btnAdd" value="新增" onclick='addNews();' id="cph_btnAdd" class="greenbtn100 mR20" type="button"/>
                        </td>
                    </tr>
                </tfoot>
            </table>
            <div class="page top mainbottom20 c">
                <a href="javaScript:pre()">上一页</a><a class="selected hover" id="currPage">${p.pageNum}</a><a href="javaScript:next()">下一页</a>
            </div>
        </div>
    </div>
    
	</div>
</body>
</html>