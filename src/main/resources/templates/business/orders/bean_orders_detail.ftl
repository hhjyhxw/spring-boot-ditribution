<!doctype html>
<html>

	<head>
		<meta charset="utf-8">
		<title>订单明细</title>
		<link rel="stylesheet" href="../../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../../css/global.css" media="all">
		<link rel="stylesheet" href="../../plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="../../css/table.css" />
	</head>

	<body>
	    
	    
	    <blockquote class="layui-elem-quote">
				<label>订单号：${beanOrder.orderNo}</label>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label>订单状态：
				   <#if beanOrder.orderStatus=='1'><span style="color:green;font-size:30px;">完成</span></#if>
				   <#if beanOrder.orderStatus=='0'><span style="color:red;font-size:30px;">未完成</span></#if>
				</label>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label>订单类型：
				   <#if beanOrder.orderStatus=='1'>A型</#if>
				   <#if beanOrder.orderStatus=='0'>B型</#if>
				</label>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label>发货状态：
				   <#if beanOrder.orderStatus=='0'>未发货</#if>
				   <#if beanOrder.orderStatus=='1'>已发货</#if>
				</label>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label>用户id：
				   ${beanOrder.userId}
				</label>
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label>openid：
				   ${beanOrder.openid}
				</label>
				
				<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<label>
				   <a href="#" onClick="javascript :history.back(-1);">返回订单列表</a>
				</label>
				
		</blockquote>
		
		<table class="site-table">
						<thead>
							<tr>
							    <th>图片</th>
								<th>商品名称</th>
							    <th>商品数量</th>
							    <th>兑换积分</th>
							    <th>优惠券值</th>
							</tr>
						</thead>
						<tbody>
						<#if beanOrderItems?? && (beanOrderItems?size > 0)> 
						<#list beanOrderItems as items>
						    <tr>
						    	
						        <td>
						            <img src="${items.goodImage}" height="50" width="50"/>
						        </td>
						        <td>
						        	${items.goodName!""}
						        </td>
						        <td>
						        	${items.goodQuantities!""}
						        </td>
						        <td>
						        	${items.score}
						        </td>
						        <td>
						        	${items.score}
						        </td>
							</tr>
						</#list>
						</#if>
						</tbody>
					</table>
		
	    <script type="text/javascript" src="../../plugins/layui/layui.js"></script>
		<script type="text/javascript" src="../../js/jquery.min.js"></script>
		<script>
			layui.config({
				base: '../../plugins/layui/modules/'
			});

			
		</script>
	
	  
	</body>
    
</html>