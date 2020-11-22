
/**
options= {padding:5,width:100,background:"none",onClick:function(){},id:"div_id",iconCls:"icon_save",text:"按钮"} 
*/
(function ($) { 
	
	function setOnClick(opt, val) {
		val.cover = val.cover ? val.cover : false;
		if (val.id) {
			var bot_opt = $("#" + val.id);
			if (val.cover) {
				bot_opt.unbind("click");
			}
			bot_opt.bind("click", function () {
				if (val.onClick) {
					val.onClick();
				}
			});
		} else {
			$.each(opt.children(".l-btn"), function (i, n) {
				if (val.cover && val.onClick) {
					$(n).unbind("click");
				}
				$(n).bind("click", function () {
					if (val.onClick) {
						val.onClick();
					}
				});
			});
		}
	}
	function isArray(opt, val) {
		var lb = false;
		var name = null;
		if (typeof val == "string") {
			name = val;
		} else {
			name = val.id ? val.id : name;
		}
		if (name == null) {
			return lb;
		}
		$.each(getNames(opt), function (i, n) {
			if (name == n) {
				lb = true;
			}
		});
		return lb;
	}
	function getNames(opt) {
		var lb = new Array(opt.children(".l-btn").length);
		$.each(opt.children(".l-btn"), function (i, n) {
			lb[i] = n.id;
		});
		return lb;
	}
	function CreateToolBar(opt, val) {
		Clear(opt);
		if (val.padding) {
			opt.css("padding", val.padding + "px");
		}
		if (val.width) {
			opt.css("width", val.width + "px");
		}
		if (val.background) {
			opt.css("background", val.background);
		}
		if (val.style) {
			opt.css(val.style);
		}
		if (val.service) {
			var ajax_options = {"options":{"async":false}, "BackE":function (R_data) {
				if (R_data) {
					$.each(R_data, function (i, n) {
						addButton(opt, n);
					});
				}
			}};
			ajax_options = $.extend(ajax_options, val);
			$.supper("doservice", ajax_options);
			return ;
		}
		if (val.tools) {
			$.each(val.tools, function (i, n) {
				addButton(opt, n);
			});
		}
	}
	function addButton(opt, val) {
	   if(isArray(opt, val)) {
	   		deletebutton(opt,val);
	   }
		var stt = "<a href=\"#\" id=\"" + val.id + "\"   iconCls=\"" + val.iconCls + "\">" + val.text + "</a>";
		
		stt = $(stt).bind("click", function () {
		val.onClick=val.onClick?val.onClick:val.handler;
			if (val.onClick) {
			  if($.isFunction( val.onClick)){
			 		 val.onClick(val);
			  }else {
			    	eval("if(window."+val.onClick+"){ "+val.onClick+"(val);  }"); 
			  } 
			}
		});
		if(val.classname){
		    stt.addClass(val.classname);
		}
		if(val.prepend){
		 	opt.prepend($(stt).linkbutton({plain:val.plain?val.plain:false}));
		}else {
			opt.append($(stt).linkbutton({plain:val.plain?val.plain:false}));
		}
	}
	function deletebutton(opt, val) {
		if (typeof val == "string") {
			$("#" + val).remove();
		}
		if (val.id) {
			$("#" + val.id).remove();
		}
	}
	function Clear(opt) {
		$.each(opt.children(".l-btn"), function (i, n) {
			deletebutton(opt, n.id);
		});
	}
	
	//////////////////////
	
	
	
	/*
	jQuery.fn.extend({xtoolbar:function (options, val) {
		options = options || {};
		if (typeof options == "string") {
			switch (options) {
			  case "create":
				return CreateToolBar(this, val);
			  case "addButton":
				return addButton(this, val);
			  case "deletebutton":
				return deletebutton(this, val);
			  case "clear":
				return Clear(this);
			  case "getNames":
				return getNames(this);
			  case "isArray":
				return isArray(this, val);
			  case "setOnclick":
				return setOnClick(this, val);
			}
		}
	}});
	*/
	
	
	function creatextoolbar(target) {
		var state = $.data(target, 'xtoolbar');
		var opts = state.options;
		$(target).form($.extend(state, opts, {}));
	}
	
	
	function sysxtoolbarCrate(opt,val){
		if(val.reCreate==false){
			sysxtoolbarclear(opt,val);
		}
		val.toolBarId=opt.attr('id'); 
		for(var i=0;i<val.items.length;i++){
			var rowData=val.items[i];
			var colourStyle=rowData.colourStyle?rowData.colourStyle:"info";
			var icon=rowData.icon?("fa fa-"+rowData.icon):"";
			var rounded=rowData.rounded?"btn-rounded":"";
			var  butobj=$('<button id="'+rowData.id+'"  type="button" style="margin-left:5px;"  class="btn '+rounded+' btn-'+colourStyle+' btn-sm '+icon+' ">'+rowData.title+'</button>');
			opt.append(butobj);
			butobj.data("clickE",rowData.clickE);
			
			butobj.bind("click", function(){
				var isFunc= false;
				var clickE=$(this).data("clickE");
				 if (typeof clickE == "string") {
					// var isFunc = jQuery.isFunction(objs[i]);
					 eval(" isFunc= jQuery.isFunction("+clickE+") ") ;
					 if(isFunc){
						 eval(clickE +"(); ") ;
						 
					 } 
				 }else if(jQuery.isFunction( clickE)){
					  clickE();				 
				 } 
				  
			 }); 
		}
	}
	
	function sysxtoolbarclear(opt,val){
		opt.html("");
	}
	
	$.fn.xtoolbar = function(options, val) {
		options = options || {};
		if (typeof options == "string") {
			switch (options) {
			case "create":
				return sysxtoolbarCrate(this, val); 
			case "clear":
				return sysxtoolbarclear(this, val); 	
				
			}
		}
		return this.each(function() {
			var state = $.data(this, "xtoolbar");
			if (state) {
				$.extend(state.options, options);
			} else {
				var t = $(this);
				var opts = $.extend({}, $.fn.xtoolbar.defaults, options);
				$.data(this, "xtoolbar", {
					options : opts
				});
			}
			creatextoolbar(this);
		});
	};
	
	
	$.fn.xform.xtoolbar = {
			toolBarId:"win_tools_but",
			control:[],
			groupTag:[],
			items:[]	
		};
	 
	
})(jQuery);

