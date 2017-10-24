// JavaScript Document
$(function(){
	baiye(".drop-menu-effect");
});
function baiye(_this){
	$(_this).each(function(){
		var $this = $(this);
		var theMenu = $this.find(".submenu");
		var tarHeight = theMenu.height();
		theMenu.css({height:0});
		$this.hover(
			function(){
				$(this).addClass("mj_hover_menu");
				theMenu.stop().show().animate({height:tarHeight},400);
			},
			function(){
				$(this).removeClass("mj_hover_menu");
				theMenu.stop().animate({height:0},400,function(){
					$(this).css({display:"none"});
				});
			}
		);
	});
}
var mySwiper = new Swiper('.swiper-container',{
	autoplay: 5000,
pagination : '.swiper-pagination',
paginationClickable :true,
})

jQuery(document).ready(function($) {
	//$('.theme-login').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	//})
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})

})

jQuery(document).ready(function($) {
	$('.yuyue-login').click(function(){
		$('.yuyue-popover-mask').fadeIn(100);
		$('.yuyue-popover').slideDown(200);
	})
	$('.yuyue-poptit .close').click(function(){
		$('.yuyue-popover-mask').fadeOut(100);
		$('.yuyue-popover').slideUp(200);
	})

})
jQuery(document).ready(function($) {
	$('.baoming-login').click(function(){
		$('.baoming-popover-mask').fadeIn(100);
		$('.baoming-popover').slideDown(200);
	})
	$('.baoming-poptit .close').click(function(){
		$('.baoming-popover-mask').fadeOut(100);
		$('.baoming-popover').slideUp(200);
	})

})
$(function(){
	
	$('.protab .content dl').width(640*$('.protab .content dt').length+'px');
	$(".protab .protu a").mouseover(function(){
		$(this).addClass('on').siblings().removeClass('on');
		var index = $(this).index();
		number = index;
		var distance = -640*index;
		$('.protab .content dl').stop().animate({
			left:distance
		});
	});
	
	var auto = 1;  //等于1则自动切换，其他任意数字则不自动切换
	if(auto ==1){
		var number = 0;
		var maxNumber = $('.protab .protu a').length;
		function autotab(){
			number++;
			number == maxNumber? number = 0 : number;
			$('.protab .protu a:eq('+number+')').addClass('on').siblings().removeClass('on');
			var distance = -640*number;
			$('.protab .content ul').stop().animate({
				left:distance
			});
		}
		var tabChange = setInterval(autotab,3000);
		//鼠标悬停暂停切换
		$('.protab').mouseover(function(){
			clearInterval(tabChange);
		});
		$('.protab').mouseout(function(){
			tabChange = setInterval(autotab,3000);
		});
	  }  
});







$(function() {
			
			var indicator = $('#indicator'),
					indicatorHalfWidth = indicator.width()/2,
					lis = $('#tabs').children('li');

			$("#tabs").tabs("#content section", {
				effect: 'fade',
				fadeOutSpeed: 0,
				fadeInSpeed: 400,
				onBeforeClick: function(event, index) {
					var li = lis.eq(index),
					    newPos = li.position().left + (li.width()/2) - indicatorHalfWidth;
					indicator.stop(true).animate({ left: newPos }, 600, 'easeInOutExpo');
				}
			});	

		});
		