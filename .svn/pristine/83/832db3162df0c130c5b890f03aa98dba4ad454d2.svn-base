
$.fn.ready( function(){
		//1、异步获取乐豆
		getMyBeansCount();
});

//异步获取乐豆
function getMyBeansCount(){
	 $.ajax({
			url : getBasePath()+'/frontpage/user/queryBeans',
			method : 'post',
			dataType : 'json',
			timeout:5000,
			success : function(data) {
				if ('success' == data.status) {
					$('#smokeBeansCount').text("乐豆 "+data.smokeBeansCount);
				} 
			},
			error : function() {
				
			}
		});
}
 