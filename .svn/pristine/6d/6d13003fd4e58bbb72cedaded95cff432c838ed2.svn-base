//获取cookie
function getCookieValue(cookieName){
	var cookieValue = document.cookie;
	var cookieStartAt = cookieValue.indexOf(""+cookieName+"=");
	if(cookieStartAt==-1){
		cookieStartAt = cookieValue.indexOf(cookieName+"=");
	}
	if(cookieStartAt==-1){
		cookieValue = null;
	}else{
		cookieStartAt = cookieValue.indexOf("=",cookieStartAt)+1;
		cookieEndAt = cookieValue.indexOf(";",cookieStartAt);
		if(cookieEndAt==-1){
			cookieEndAt = cookieValue.length;
		}
		cookieValue = unescape(cookieValue.substring(cookieStartAt,cookieEndAt));//解码latin-1
	}
	return cookieValue;
}

function refleshCartCount(className){
	var cartCount = getCookieValue("cartCountCookie");
	if(undefined == cartCount ||cartCount==null || cartCount==''){
		cartCount = 0;
	}
	$('.'+className).text(cartCount);
}

function openAlert(tex){
	$('.modal-alert').find('p').text(tex);
    $('.modal-alert').fadeIn();
}

function closeAlert(){
	  $('.modal-alert').hide();
} 