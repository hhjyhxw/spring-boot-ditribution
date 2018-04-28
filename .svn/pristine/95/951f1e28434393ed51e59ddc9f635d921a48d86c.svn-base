<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<title>广告列表</title>
		<link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../../css/global.css" media="all">
		<link rel="stylesheet" href="../../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../../css/table.css" />
	</head>

	<body>
	
			<blockquote class="layui-elem-quote">
				<label class="layui-form-label">广告名称</label>
			    <div class="layui-input-inline" style="width:10%">
			        <input type="text" name="name" id="name" lay-verify="" value="${(record.name)!''}" placeholder="" autocomplete="off" class="layui-input">
		  		</div>
				    上下架状态：<select style="width:12%;" name="status" lay-verify="" id="status" value="${(record.status)!''}">
			    	<option value="">全部</option>
			    	<option value="0" <#if (((record.status)!'') == '0')>selected="selected"</#if>>停用</option>
				  	<option value="1" <#if (((record.status)!'') == '1')>selected="selected"</#if>>在用</option>
				</select>
				
		       <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 查找
				</a>
				<button class="layui-btn layui-btn-small" id="toAdd">
				  <i class="layui-icon">&#xe608;</i> 添加
				</button>
			</blockquote>
				
			<fieldset class="layui-elem-field">
				<legend>商品列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th>图片</th>
							    <th>广告名称</th>
							    <th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<#list page.list as t>
						    <tr>
						    	<td><img src="${pictureVisitUrl}${t.adImage!""}" height="70" width="70"/></td>
						        <td>${t.name!""}</td>
						        <td>
						        	<#if t.status=='0'>停用</#if>
						        	<#if t.status=='1'>使用</#if>
						        </td>
								<th>
								<div class="layui-btn-group">
								 	<button class="layui-btn layui-btn-small" onclick="toEdit(${t.id})">
								    	<i class="layui-icon">&#xe642;</i>&nbsp;编辑
								  	</button>
									<#--
								  	<button class="layui-btn layui-btn-small" onclick="delById(${t.id})">
								    	<i class="layui-icon">&#xe640;</i>&nbsp;删除
								  	</button>
								  	-->
								</div>
								</th>
							</tr>
						</#list>
						</tbody>
					</table>

				</div>
			</fieldset>
			<div class="admin-table-page">
				<div id="page" class="page">
				</div>
			</div>
		</div>
        <script type="text/javascript" src="../../plugins/layui/layui.js"></script>
		<script type="text/javascript" src="../../js/jquery.min.js"></script>
		<script>
			layui.config({
				base: '../../plugins/layui/modules/'
			});

			layui.use(['icheck', 'laypage','layer'], function() {
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});
				
				var pageSize = ${page.pageSize};
				//page
				laypage({
					cont: 'page',
					pages: ${page.pages} //总页数
						,
					groups: 10 //连续显示分页数
						,
					first:true,
					last:true,
					jump: function(obj, first) {
						//得到了当前页，用于向服务端请求对应数据
						var curr = obj.curr;
						if(!first) {
						 $.ajax({
				           url:'${request.contextPath}/backpage/beanAdvert/getList',
				           type:'post',
				           data:{"pageNo":obj.curr,"pageSize":pageSize,"status":$('#status').val(),
				           "name":$('#name').val()
				         
				           },
				           success:function(data) { 
				            str = ''; 
                            $.each(data.list,function(i,val){
                            	
                            	str += '<tr>';
                            	str += '<th><img src="'+val.adImage+'"  height="70" width="70"/></th>';
                            	str += '<th>'+val.name+'</th>';
                            	if(val.status == '0'){
                            		str += '<th>停用</th>';
                            	}else if(val.status == '1'){
                            		str += '<th>使用</th>';
                            	}else{
                            		str += '<th></th>';
                            	}
                            	str += '<th>';
                            	str += '<div class="layui-btn-group">';
                            	str += '<button class="layui-btn layui-btn-small" onclick="toEdit('+val.id+')">';
                            	str += '<i class="layui-icon">&#xe642;</i>&nbsp;编辑';
								str += '</button>';
                            	str += '</div>';
								str += '</th>';
						    	str += '</tr>';       
                            })
                            $('.layui-elem-field').find('tbody').html(str)
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
				 	}
					}
				});
				//查询
				$('#search').on('click', function() {
					var status = $('#status').val();
					var name = $('#name').val();
					window.location.href = "${request.contextPath}/backpage/beanAdvert/list?status="+status+"&name="+name;
					
				});
				//跳转新增
				$('#toAdd').on('click', function() {
					window.location.href = "${request.contextPath}/backpage/beanAdvert/toinput"
				});
				$('.site-table tbody tr').on('click', function(event) {
					var $this = $(this);
					var $input = $this.children('td').eq(0).find('input');
					$input.on('ifChecked', function(e) {
						$this.css('background-color', '#EEEEEE');
					});
					$input.on('ifUnchecked', function(e) {
						$this.removeAttr('style');
					});
					$input.iCheck('toggle');
				}).find('input').each(function() {
					var $this = $(this);
					$this.on('ifChecked', function(e) {
						$this.parents('tr').css('background-color', '#EEEEEE');
					});
					$this.on('ifUnchecked', function(e) {
						$this.parents('tr').removeAttr('style');
					});
				});
						
			})
			//跳转修改
			function toEdit(id){
				window.location.href = "${request.contextPath}/backpage/beanAdvert/toinput?id="+id;
			}
			//删除
			function delById(id){
				if(confirm("确定要删除？")){
					var url = '${request.contextPath}/backpage/beanAdvert/delete';
					$.post(url, {
		                'id':id
		            }, function(ret) {
					    if(ret == "0000"){
					    	layer.msg("删除成功");
					    	window.location.href = "${request.contextPath}/backpage/beanAdvert/list";
					    }else{
					    	layer.msg("删除失败");
					    }
		            });
				}
			}
			
		</script>
	</body>

</html>