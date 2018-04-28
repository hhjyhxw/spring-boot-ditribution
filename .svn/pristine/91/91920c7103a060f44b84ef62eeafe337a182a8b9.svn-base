
/* 引用fastclick库,消除click事件300ms延迟 */
window.addEventListener('load', function() {
  FastClick.attach(document.body);
}, false);

$(function(){

  //底部tab导航点击事件
  var $menu = $('.footer').find('.menu');
  $menu.on('click',function(){
    if(!$(this).hasClass("active")){
      $menu.removeClass("active");
      $(this).addClass("active");
    }
  })


  refreshIcon();
})

function refreshIcon(){
  var icon = document.querySelector('.gwc .count'),
      txt = icon.innerHTML,
      len = txt.length;

  icon.style.borderRadius = len > 1 ?  '50%/100%' : '50%';
  icon.style.display = len < 1? 'none' : 'block';
}
