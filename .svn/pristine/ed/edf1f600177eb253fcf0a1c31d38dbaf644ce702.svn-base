<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<title>人员列表</title>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<link rel="stylesheet" href="../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/table.css" />
	</head>

	<body>
	
			<blockquote class="layui-elem-quote">
				 昵称：<input type="text" name="nick" value="${(nick)!}" id="nick">
				 是否允许发布事件：
				 <select id="isAllowPublish">
				    <option value="">全部</option>
				    <option value="1">允许</option>
				    <option value="0">不允许</option>
				 </select>
		       <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> 查找
				</a>
			</blockquote>
				
			<fieldset class="layui-elem-field">
				<legend>用户列表</legend>
				<div class="layui-field-box">
					<table class="site-table table-hover">
						<thead>
							<tr>
							    <th>微信头像</th>
								<th>昵称</th>								
								<th>发布权限</th>	
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
						<#list page.list as t>
						    <tr>
						        <td><img src="${t.headImg!""}" height="50" width="50"/></td>
								<td>${t.nick!""}</td>
								<td>
								   <#if t.isAllowPublish=='1'>
                                                                                                 可发布
                                   </#if>
                                    <#if t.isAllowPublish=='0'>
                                                                                              不可发布
                                   </#if>
								</td>
								<td>
                                    <#if t.isAllowPublish=='1'>
                                      <a href="javascript:change('${t.id}','0');"  class="layui-btn"><i class="layui-icon">&#xe642;</i>不允许发布</a>
                                    </#if>
                                    <#if t.isAllowPublish=='0'>
                                     <a href="javascript:change('${t.id}','1');"  class="layui-btn"><i class="layui-icon">&#xe642;</i>允许发布</a>
                                   </#if>
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
        <script type="text/javascript" src="../plugins/layui/layui.js"></script>
		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script>
			layui.config({
				base: '../plugins/layui/modules/'
			});
			layui.use(['icheck', 'laypage','layer'], function() {
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});

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
				           url:'/admin/user_getList',
				           type:'post',
				           data:{"pageNo":obj.curr,"nick":$('#nick').val(),"isAllowPublish":$('#isAllowPublish')},
				           success:function(data) { 
				            str = ''; 
                            $.each(data.list,function(i,val){
                            
                                str += '<tr>'
                                str += '<td><img src="'+val.headImg+'" height="50" width="50" /></td>'
                                str += '<td>'+val.nick+'</td>'
                                str += '<td>'+(val.isAllowPublish=='1'?'可发布':'不可发布')+'</td>'
                                if(val.isAllowPublish=='1'){
                                str += '<a href="javascript:change(\''+val.id+'\',\'0\');"  class="layui-btn layui-btn-mini">不允许发布</a>'
                                }else{
                                   str += '<a href="javascript:change(\''+val.id+'\',\'1\');"  class="layui-btn layui-btn-mini">允许发布</a>'
                                }
                                str += '</tr>'
                            
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

				$('#search').on('click', function() {
						 $.ajax({
				           url:'/admin/user_getList',
				           type:'post',
				           data:{"nick":$('#nick').val(),"isAllowPublish":$('#isAllowPublish').val()},
				           success:function(data) { 
				            str = ''; 
                            $.each(data.list,function(i,val){
                                str += '<tr>'
                                str += '<td><img src="'+val.headImg+'" height="50" width="50" /></td>'
                                str += '<td>'+val.nick+'</td>'
                                str += '<td>'+(val.isAllowPublish=='1'?'可发布':'不可发布')+'</td>'
                                if(val.isAllowPublish=='1'){
                                    str += '<td><a href="javascript:change(\''+val.id+'\',\'0\');"  class="layui-btn layui-btn-mini">不允许发布</a></td>'
                                }else{
                                    str += '<td><a href="javascript:change(\''+val.id+'\',\'1\');"  class="layui-btn layui-btn-mini">允许发布</a></td>'
                                }
                            })
                            $('.layui-elem-field').find('tbody').html(str)
                         },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
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
		     function change(id,type){
			    	 $.ajax({
				           url:'/admin/user_change',
				           type:'post',
				           data:{"id":id,"type":type},
				           success:function(data) { 
				             if(data.errCode=='0000'){
				                   layer.msg("修改成功成功",{time:2000});
				              setTimeout(function(){window.location.href="/admin/user_list";},1000);
				               
				              }else{
				                layer.msg(data.resultMsg,{time:2000});
				              }
                          },    
                        error : function() {    
                           layer.msg("异常！");    
                        } 
				 })
			
			}	
			
		</script>
	</body>

</html>