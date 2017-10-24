/**
 * 
 */
var Popbox = Popbox || {};
Popbox.tip = function(msg){
	msg = msg|| '错误';
	layer.open({
		type:4,
		shadeClose: true,
	    content: msg,
	    time: 1
	});
};
	
Popbox.loading = function(msg){
	msg = msg||"";
	return layer.open({
        type: 2,
        content: msg
    });
};
	
	
	
Popbox.success = function (msg){
	msg = msg||"恭喜，操作成功！";
	layer.open({
		typ: 1,
	    content: '<i class="icon-rr-right"></i><p class="lh30">'+msg+'</p>',
	    style: 'background-color:#FFF; color:#109363; border:none;',
	    time: 1
	});
}
	

/*Popbox.error = function(msg){
	msg = msg||"呜，出错啦 ";
	layer.open({
		typ: 1,
	    content: '<i class="icon-rr-wrong"></i><p class="lh30">'+msg+'</p>',
	    style: 'background-color:#FFF; color:#c00; border:none;',
	    time: 5
	});
};*/

Popbox.error = function(msg){
msg = msg||"呜，出错啦 ";
layer.open({
	shadeClose: true,
	content: '<i class="icon-rr-wrong"></i><p class="lh30">'+msg+'</p>',
    style: 'background-color:#FFF; color:#c00; border:none;',
    btn: ['确定'],
    time:5
});
};

Popbox.sureWithBtn = function(msg){
	msg = msg||"确定要这样吗 ";
	layer.open({
		shadeClose: true,
	    content: msg,
	    btn: ['确定']
	});
};

Popbox.sureWithClose = function(msg){
	msg = msg||"确定要这样吗 ";
	layer.open({
		shadeClose: true,
		content: '<i class="icon-rr-wrong"></i><p class="lh30">'+msg+'</p>',
	    style: 'background-color:#FFF; color:#c00; border:none;',
	    btn: ['确定'],
	    time:5
	});
};

	
Popbox.confirm = function(msg){
	msg = msg||"确定要执行该操作吗？ ";
	layer.open({
	    content: msg,
	    btn: ['确认', '取消'],
	    yes: function(){
	    	Popbox.success('点击了确认');
	    }, no: function(){
	    	Popbox.success('点击了取消');
	    }
	});
};