
$(function(){
	
	//适配
	$('html').screenAdaptation();

})

//扩展jq.fn
$.extend($.fn, {
	
	//屏幕适配 ***
	screenAdaptation : function(){
		var that = $(this);
		var iWidth = document.documentElement.getBoundingClientRect().width;
		iWidth=iWidth>640?640:iWidth;
		iWidth=iWidth<320?320:iWidth;
		that[0].style.fontSize=iWidth/6.4+"px";
	},
	
	//选项卡 ***
	tab : function(opts){
		var def = {
			tabBd : "",
			  cls :"cur",
		   _event :"click"
		};
		var setting = $.extend(def,opts);	
		$(this).bind(setting._event ,fn).eq(0).trigger("click");
		function fn(){
			var index = $(this).index();
			$(this).addClass(setting.cls).siblings().removeClass(setting.cls);
			$(setting.tabBd).eq(index).show().siblings().hide();			
		};
	},
	
	//切换 ***
	tabcur : function(opts){
		var def = {
			  cls :"cur",
		   _event :"click"
		};
		var setting = $.extend(def,opts);	
		$(this).bind(setting._event ,fn).eq(0).trigger(setting._event);
		function fn(){
			$(this).addClass(setting.cls).siblings().removeClass(setting.cls);		
		};
	},
	
	//订单中心切换
	toggleCur : function(){
		var that = $(this);
		that.find("dt").click(function(){
			var _next = $(this).next("dd");
			if(_next.is(":hidden")){
				$(this).addClass('cur');
				_next.slideDown(0);
			}else{
				_next.slideUp(0);	
				$(this).removeClass('cur');
			}
		}).eq(0).trigger("click");		
	}
});

//商品订单数量增减
function addNum(name,value){
	$(name).click(function(){
		var val = $(value).val();
		$(value).val(parseInt(val) + 1);	
		$(value).keydown(function(e){
			if(e.keyCode!=8&&(e.keyCode<48||e.keyCode>57)&&(e.keyCode<96||e.keyCode>105)){
				return false;
			}
		});
	});
}
	
//商品订单数量增减
function lessNum(name,value){
	$(name).click(function(){
		var val = $(value).val();
		val >= 2 ? $(value).val(parseInt(val) - 1) : $(value).val(1);
		$(value).keydown(function(e){
			if(e.keyCode!=8&&(e.keyCode<48||e.keyCode>57)&&(e.keyCode<96||e.keyCode>105)){
				return false;
			}
		});
	});
}

