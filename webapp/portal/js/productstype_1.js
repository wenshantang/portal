$(document).ready(function(){
		//$(".nav ul li").children("ul").hide();
		$(".nav").find("li").not(":has(ul)").children("a").css({textDecoration:"none"})
		.click(function(){
		$(this).get(0).location.href="'"+$(this).attr("href")+"'";
		});
		$(".nav").find("li:has(ul)").children("a").css({})
		.click(function(){
		if($(this).next("ul").is(":hidden")){
		$(this).next("ul").slideDown("slow");
		if($(this).parent("li").siblings("li").children("ul").is(":visible")){
		$(this).parent("li").siblings("li").find("ul").slideUp("1000");
		$(this).parent("li").siblings("li:has(ul)").children("a").css({})
		.end().find("li:has(ul)").children("a").css({});}
		$(this).css({});
		return false;
		}else{
		$(this).next("ul").slideUp("normal");
		//不用toggle()的原因是为了在收缩菜单的时候同时也将该菜单的下级菜单以后的所有元素都隐藏
		$(this).css({});
		$(this).next("ul").children("li").find("ul").fadeOut("normal");
		$(this).next("ul").find("li:has(ul)").children("a").css({});
		return false;
		}
		});
		
		
		var mbxie = $("div.FrontPublic_breadCrumb01-d1_c1>div>a:eq(1)").text();
		
		if(mbxie != ""){
		$("div.FrontProductsCategory_show01-d12_c1>ul.nav>li").each(function(){
			var flname = $(this).children("a").text();
			if(flname.indexOf(mbxie) != -1){
				$(this).children("a").addClass("current");
				
				$(this).children("ul").css('display','block');
			}else{
				$(this).children("a").removeClass("current");
				$(this).children("ul").css('display','none');
			}
		});
		}
	
	var mbxie = $("div.FrontPublic_breadCrumb01-d1_c1>div>a:eq(2)").text();
	if(mbxie != ""){
		$("div.FrontProductsCategory_show01-d12_c1>ul.nav>li>ul>li").each(function(){
			var flname = $(this).children("a").text();
			//alert(flname);
			if(flname.indexOf(mbxie) != -1){
				$(this).children("a").addClass("current2");	
				
			}else{
				$(this).children("a").removeClass("current2");
				
			}
		});
		}
	
	 
		jQuery("#${compId}<c:out value='${param.pmcId}'/>_menu").parents("ul").css("display","block");
	});

//<![CDATA[

//]]>