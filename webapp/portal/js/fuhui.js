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
	autoplay: 3000,
pagination : '.swiper-pagination',
paginationClickable :true,
});

jQuery(document).ready(function($) {
	if(getCookie("showthemenr")=='show'){
		$('.theme-popover-mask').fadeOut();
		$('.theme-popover').slideUp();
	}else{
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	}
	$('.theme-poptit .close').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	});

});

$(function(){
	$('.protab .content dl').width(1040*$('.protab .content dt').length+'px');
	$(".protab .protu a").mouseover(function(){
		$(this).addClass('on').siblings().removeClass('on');
		var index = $(this).index();
		number = index;
		var distance = -1040*index;
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
			var distance = -1040*number;
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

$.fn.extend({
	hoverTips : function (){
		var self = $(this);
		var sw = self.get(0).switch;
		if( !sw ) {
			sw = true;
			var content = self.attr("tooltips");
			var htmlDom = $("<div class='tooltips'>")
					.html("<p class='content'></p>");
			htmlDom.find("p.content").html( content );
		}
		self.on("mouseover",function(){
			$("body").append( htmlDom );
			var left = self.offset().left - htmlDom.outerWidth()/2 + self.outerWidth()/2;
			var top = self.offset().top - htmlDom.outerHeight() - parseInt(htmlDom.find(".triangle-front").css("border-width"));
			htmlDom.css({"left":left,"top":top,"display":"block"});
			htmlDom.stop().animate({ "top" : top ,"opacity" : 1},300);
		});
		self.on("mouseout",function(){
			var top = parseInt(htmlDom.css("top"));
			htmlDom.stop().animate({ "top" : top,"opacity" : 0},300,function(){
				htmlDom.remove();
				sw = false;
			});
		});
	},
});
$("#ahover").hoverTips();