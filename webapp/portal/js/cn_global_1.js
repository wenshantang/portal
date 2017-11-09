
(function () {
    var d = document;
var l = window.location.href;
var f = l.substring(l.lastIndexOf('/') + 1);
var timer = false;

$(window).load(function(){    
    menuPopup.ini();
    menuHighlight.exec();
});

var menuHighlight = {};
menuHighlight.exec = function(){
    if (l.indexOf('solutions') > -1) {
        $('#menu .solutions a').addClass('active');
        return $('#menu').data("normal", "solutions");
    }
    if (l.indexOf('products') > -1) {
        $('#menu .products a').addClass('active');
        return $('#menu').data("normal", "products");
    }
    if (l.indexOf('services') > -1) {
        $('#menu .services a').addClass('active');
        return $('#menu').data("normal", "services");
    }
    if (l.indexOf('about-huawei') > -1) {
        $('#menu .about-huawei a').addClass('active');
        return $('#menu').data("normal", "about-huawei");
    }
};

//----------------------------------------------
var mouse_events = {};

$(document).ready(function(){    
    if(!iPx()){
        mouse_events.mouse_over();
    }
});

var timeout;
mouse_events.mouse_over = function(){
    $("#menu li").mouseover(function(){
        dateIn = new Date();
        timeIn = dateIn.getTime();
        timeOut = 0;
        flag = true;    

        var p = this.className;
        timeout = setTimeout(function(){mouse_events.aShow(p)}, 500);
    });
};

mouse_events.aShow = function(classname){

    $('#menu .'+classname+' a').addClass('hover');  
};

var dateOut;
var timeOut;
var dateIn
var timeIn;
var flag = false;
var divShow_flag = false;
var $target;
var target;
var css;
var time_temp;

function showDiv() {
    if (flag = true && timeIn > 0) {
        $target = menuPopup.obj.css(css).find('.' + target).show();
    }
    flag = false;
}

// //add by bob 2011-04-07 (end)
// //-----------------------------------------------

var menuPopup = {};
menuPopup.timer = false;
menuPopup.ini = function(){
    menuPopup.obj = $('#menu-popup');
    
    $('#menu li a').hover(function(){
    
        menuPopup.stop();
        $('#menu li a').removeClass('active');
        menuPopup.obj.find('.popup').hide();
        target = this.parentNode.className;
        var position = $(this).offset();
        var shadowOffset = 2;
        if ($.browser.msie && parseInt($.browser.version) == 6) 
            shadowOffset = 0;
        css = {
            top: position.top + 49,
            left: $('#menu li a').eq(0).offset().left - shadowOffset
        };

        if(iPx()){
        
            var $target = menuPopup.obj.css(css).find('.' + target).show();
                            
            if ($target.length > 0) 
            $(this).addClass('hover');
            
        }else{
            if (flag = true) {
                time_temp = setTimeout(showDiv, 500);       
            }
        }
        if ($(this).parent().attr('class') != $('#menu').data("normal")) {
            menuHighlight.exec();
        }
        
    }, menuPopup.hide);
    
    menuPopup.obj.hover(menuPopup.stop, menuPopup.hide);
    
};
menuPopup.stop = function(){

    divShow_flag = true;

    if (menuPopup.timer) 
        clearTimeout(menuPopup.timer);
}
menuPopup.hide = function(){
    menuPopup.stop();
    menuPopup.timer = setTimeout(function(){
        menuPopup.obj.css('top', '-1000px');
        menuHighlight.exec();
    }, 10);
}


function iPx(){
    if ((navigator.userAgent.match(/iPhone/i)) || (navigator.userAgent.match(/iPod/i)) || (navigator.userAgent.match(/iPad/i))) 
        return true;
    return false;
}

}());
