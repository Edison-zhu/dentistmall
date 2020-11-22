
/** 
关闭xdialog事件BackE();
CenterPanel添加onLoad事件;
onNext按钮事件
onBack按钮事件
onSubmit按钮事件
onClose按钮事件
*/
(function ($) {
	/*
添加xDialog对象属性
*/
	function buildDialog(target) {
		var opt = $(target);
		var val = $.data(target, "xdialog").options;
		var bodyLayout = $.data(target, "xdialog").bodyLayout;
		//添加内容  
		opt.append(bodyLayout);
		val.winTitle = val.title;
		val.collapsible = false;
		val.minimizable = false;
		val.maximizable = false;
		val.resizable = false;
		if (val.href) {
			val.pagehref = val.href;
			val.href = undefined;
		} else {
			val.pagehref = val.url;
		}
		val.winSize = (val.winOptions) ? ((val.winOptions).length) : 0;
		if (val.winOptions) {
			val.winSize = (val.winOptions).length;
			val.pagehref = val.winOptions[0].href ? val.winOptions[0].href : (val.winOptions[0].url ? val.winOptions[0].url : val.pagehref);
			val.onLoad = val.winOptions[0].onLoad ? val.winOptions[0].onLoad : val.onLoad;
		} else {
			val.winSize = 0;
		}
		opt.attr("winSize", val.winSize);
		opt.attr("winPage", 0);
		opt.window($.extend({}, val, {onClose:function () { 
			if (val.BackE) {
				val.BackE();
			}
			opt.window("destroy");
		}}));
		$.parser.parse(opt);  
	//给CenterPanel添加onLoad事件
		var center_obj = opt.xdialog("getCenterPanel");
		var center_options = center_obj.panel("options");
		opt.xdialog("refreshCenter", val);
		$.data(this, "xdialog", {options:val, bodyLayout:bodyLayout});
	}
	function set_xdialog(opt, val) {
		var winPage = parseInt(opt.attr("winPage"));
		var winSize = parseInt(opt.attr("winSize"));
		if (winSize > 0) {
			//四个基本按钮
			//先从winOptions中查找事件，如果没有就从弹出的窗口中去找;
			val.onClose = val.winOptions[winPage].onClose ? val.winOptions[winPage].onClose : (val.iframe ? $.supper("getdiv", "iframe_" + val.id)[0].contentWindow.onClose : (window.onClose?window.onClose:undefined));
			val.onSubmit = val.winOptions[winPage].onSubmit ? val.winOptions[winPage].onSubmit : (val.iframe ? $.supper("getdiv", "iframe_" + val.id)[0].contentWindow.onSubmit : (window.onSubmit?window.onSubmit:undefined));
			val.onBack = val.winOptions[winPage].onBack ? val.winOptions[winPage].onBack : (val.iframe ? $.supper("getdiv", "iframe_" + val.id)[0].contentWindow.onBack :(window.onBack?window.onBack:undefined) );
			val.onNext = val.winOptions[winPage].onNext ? val.winOptions[winPage].onNext : (val.iframe ? $.supper("getdiv", "iframe_" + val.id)[0].contentWindow.onNext : (window.onNext?window.onNext:undefined));
		} else {
			val.onClose = val.onClose ? val.onClose : (val.iframe ? $.supper("getdiv", "iframe_" + val.id)[0].contentWindow.onClose : (window.onClose?window.onClose:undefined));
			val.onSubmit = val.onSubmit ? val.onSubmit : (val.iframe ? $.supper("getdiv", "iframe_" + val.id)[0].contentWindow.onSubmit : (window.onSubmit?window.onSubmit:undefined));
			val.onBack = val.onBack ? val.onBack : (val.iframe ? $.supper("getdiv", "iframe_" + val.id)[0].contentWindow.onBack : (window.onBack?window.onBack:undefined) );
			val.onNext = val.onNext ? val.onNext : (val.iframe ? $.supper("getdiv", "iframe_" + val.id)[0].contentWindow.onNext : (window.onNext?window.onNext:undefined));
		}
		if (winSize > 0) {
			//多页中从第二页开始
			val.message = val.winOptions[winPage].message;
			val.toolbars = val.winOptions[winPage].toolbars;
			val.width = val.winOptions[winPage].width ? val.winOptions[winPage].width : "auto";
			val.height = val.winOptions[winPage].height ? val.winOptions[winPage].height : "auto";
		//
			val.title = val.winOptions[winPage].title;
			val.iconCls = val.winOptions[winPage].iconCls;
			//设置标题
			if (val.title) {
				opt.window("setTitle", val.winTitle + "  (" + val.title + ")");
			}
			if (val.iconCls) {
				opt.xdialog("seticonCls", val.iconCls);
			}
		}
		var page_tools = opt.xdialog("getToolBar");
		var page_message = opt.xdialog("getMessage");
		var center_obj = opt.xdialog("getCenterPanel");
		var center_options = center_obj.panel("options");
		 //设置resize
		opt.window("resize", {width:val.width, height:val.height});
		/**
	设置窗口Message部分
	 **/
		if (val.message) {
			page_message.val(val.message);
			opt.xdialog("getTopPanel").panel("open");
		} else {
			opt.xdialog("getTopPanel").panel("close");
			center_obj.panel("resize", {top:0, left:0, width:center_options.width, height:center_options.height + 60});
		}
		//添加固定toolbar 
		var att_tools = [{id:"win_close_bt", iconCls:"icon-no", text:"\u5173\u95ed", onClick:function () {
			if (val.onClose) {
				val.onClose(opt, val);
			}
			opt.xdialog("close");
		}}];
	      //添加一个提交按钮 ,一个返回按钮
		page_tools.xtoolbar("create", {padding:5, tools:att_tools, background:"none"});
		if (val.onSubmit) {
			page_tools.xtoolbar("addButton", {prepend:true, id:"win_submit_bt", iconCls:"icon-save", text:"\u63d0\u4ea4", onClick:function () {
				 var isok=true;				 
				if (val.onSubmit) {
					isok=  val.onSubmit(opt, val);
				}
				if(isok) {
					opt.xdialog("close");
				}
			}});
		} 
		
	
		//多页面窗口
		if (val.winSize > 0) { 
			   //添加下一步按钮 
			if (val.onNext || winSize == 1) {
				page_tools.xtoolbar("addButton", {prepend:true, id:"win_next_bt", iconCls:"icon-forword", text:"\u4e0b\u4e00\u6b65", onClick:function () {			 
					if (val.onNext) {
					var ss=val.onNext(opt, val); 					
						 if(ss==undefined||ss==true){
						 		val.winSize = (val.winOptions) ? ((val.winOptions).length) : 0;
								opt.attr("winSize", val.winSize);
						 		window_next_bt(opt, val);
						 }else {
						 		return ;
						 }					
						
					}else {
						window_next_bt(opt, val);
					}
				}});
			} 
			 //添加上一步按钮
			if (winPage > 0) {
				page_tools.xtoolbar("addButton", {prepend:true, id:"win_back_bt", iconCls:"icon-back", text:"\u4e0a\u4e00\u6b65", onClick:function () {
					if (val.onBack) {
						val.onBack(opt, val);
					}
					window_back_bt(opt, val);
				}});
			}
		} 
		 //添加toolbars 设置窗口中自定义按钮部分 
		if (val.toolbars) {
			for (var tbi = val.toolbars.length - 1; tbi >= 0; tbi--) {
				var n = val.toolbars[tbi];
				n.prepend = true;
				n.classname = "xdialogClass";
				page_tools.xtoolbar("addButton", n);
			}
		}
	}
	/**
	下一步按钮事件
	**/
	function window_next_bt(opt, val) {
		var old_page = parseInt(opt.attr("winPage"));
		var winPage = old_page + 1;
		var winSize = parseInt(opt.attr("winSize"));
		opt.attr("winPage", winPage);
		if (winPage < winSize) {
			var row_option = val.winOptions[winPage];
			row_option.href = row_option.href ? row_option.href : row_option.url;
			val.pagehref = row_option.href;
			opt.xdialog("refreshCenter", val);
		}
	}
	/**
	上一步按钮事件
	*/
	function window_back_bt(opt, val) {
		var old_page = parseInt(opt.attr("winPage"));
		var winPage = old_page - 1;
		var winSize = parseInt(opt.attr("winSize"));
		opt.attr("winPage", winPage);
		if (winPage >= 0) {
			var row_option = val.winOptions[winPage];
			row_option.href = row_option.href ? row_option.href : row_option.url;
			val.pagehref = row_option.href;
			opt.xdialog("refreshCenter", val);
		}
	}
	/**
	 * 初始化内容部分.
	 */
	function wrapDialog(target) {
		var str = "<div id=\"bodyLayout\" class=\"easyui-layout\" fit=true border=false>" + "<div region=\"north\" border=false  \t\tstyle=\"overflow:hidden;height:60px;border-bottom:1px solid #8DB2E3;background:#fafafa;\">" + "<textarea id=\"top_text\"     style=\"width:100%; height:100%;background:#fafafa;\" ></textarea></div>" + "<div region=\"south\" border=false   style=\"overflow:hidden;height:35px;border-top:1px solid #8DB2E3;background:#fafafa;\"><div id=\"xDialog_toolbar\" style=\"padding:5px;background:#fafafa;float:right;\"></div></div>" + "<div region=\"center\" border=false   style=\"overflow-x:hidden;\"></div></div>";
		return $(str);
	}
	/**
	信息内容部分进行刷新*/
	function xdialog_refreshCenter(opt, param) {
		var pbody = ($.data(opt[0], "xdialog").bodyLayout).layout("panel", "center");
		param.pagehref = param.pagehref ? param.pagehref : (param.href ? param.href : param.url);
		param.iframe = param.iframe ? param.iframe : false;
		if (param.iframe) {  
			var content = $.supper("getIframe", {id:param.id, href:param.pagehref});
			opt.xdialog("getTopPanel").panel("close"); 
		   //  opt.html($('<div class="panel-loading"></div>').html("load....")); 
			   pbody.html( $(content).load( function(){ 
					 	set_xdialog(opt,param);
					})); 
		} else {
			obj.panel("refresh", param.pagehref);
			set_xdialog(opt,param);
			
		}  
		if (param.onLoad) {
			param.onLoad(this, param);
		}
	}
	function xdialog_seticonCls(opt, iconCls) {
		$.data(opt[0], "xdialog").options.iconCls = iconCls;
		var icon_obj = opt.panel("header").find("div.panel-icon");
		icon_obj.removeClass();
		icon_obj.addClass("panel-icon");
		icon_obj.addClass(iconCls);
	}
	function xdialog_addWin(opt, val) {
		var att_Options = $.data(opt[0], "xdialog").options;
		if(att_Options.winOptions){
		var isadd=false;
		var row_num=att_Options.winOptions.length;
		 $.each( att_Options.winOptions, function(i, row_options){ 
		     if ((row_options.id)&&(row_options.id==val.id)){
		    		 isadd=true;
		    		 row_num=i;
		     }
		});
		if(isadd){ 
				att_Options.winOptions.splice(row_num,1,val);
				var att_size= att_Options.winOptions.length; 
				opt.attr("winSize", att_size);
				att_Options.winSize = att_size;
		}else {
				var att_size= att_Options.winOptions.push(val); 
				opt.attr("winSize", att_size);
				att_Options.winSize = att_size;
		}
		 
		}
	}
	function xdialong_cleanwin(opt, val) {
	  if($.data(opt[0], "xdialog")){
		var att_Options = $.data(opt[0], "xdialog").options;
		att_Options.winOptions = undefined;
		opt.attr("winSize", 0);
		att_Options.winSize = 0;
		}
		opt.html("");
	}
	function xdialog_deleteWin(opt, val) {
		var att_Options = $.data(opt[0], "xdialog").options;
		att_Options.winOptions.splice(val, 1);
		var att_size = att_Options.winOptions.length;
		opt.attr("winSize", att_size);
		att_Options.winSize = att_size;
	}
	
	/**
	显示窗口
	*/
	function xdialong_showWin(obj,val){
	  
	    var winType=val.winType?val.winType:"datagrid_modle";
	    if(winType=="datagrid_modle"){
	    var att_id=obj.attr("id")?"&xdialog_id="+obj.attr("id"):"";
	    var att_jspath=val.jspath?"&jspath="+val.jspath:"";
	    var att_data=  ( val.data? val.data:"")+att_jspath+att_id;
	       val.href=$.supper("getServicePath",{url:"/jsp/supper/datagrid_modle.jsps",data:att_data}); 
	    }  
	    obj.xdialog(val);
	}
	
	/*
 创建xDialog对象
 */
	$.fn.xdialog = function (options, param) {
		if (typeof options == "string") {
			switch (options) {
			  case "options":
				return $.data(this[0], "xdialog").options;
			  case "getbodyLayout":
				return $.data(this[0], "xdialog").bodyLayout;
			  case "getTopPanel":
				var obj = $.data(this[0], "xdialog").bodyLayout;
				return obj.layout("panel", "north");
			  case "getBottomPanel":
				var obj = $.data(this[0], "xdialog").bodyLayout;
				return obj.layout("panel", "south");
			  case "getCenterPanel":
				var obj = $.data(this[0], "xdialog").bodyLayout;
				return obj.layout("panel", "center");
			  case "setMessageStr":
				var textAreaObj = (this.xdialog("getTopPanel")).find(">textarea#top_text");
				return textAreaObj.val(param);
			  case "getMessage":
				return (this.xdialog("getTopPanel")).find(">textarea#top_text");
			  case "getToolBar":
				return (this.xdialog("getBottomPanel")).find(">div#xDialog_toolbar");
			  case "close":
				return this.window("close");
			  case "setWinOptions":
				var ops = $.data(this[0], "xdialog").options;
				ops.winOptions = param;
				return;
			  case "refreshCenter":
				return xdialog_refreshCenter(this, param);
			  case "seticonCls":
				return xdialog_seticonCls(this, param);
			  case "addWin":
				return xdialog_addWin(this, param);
			  case "cleanWin":
				return xdialong_cleanwin(this, param);
			  case "deleteWin":
				return xdialog_deleteWin(this, parma);
			  case "getObject":
				var ops = $.data(this[0], "xdialog").options;
				return $.supper("getdiv", {id:param, objectId:ops.id});
			  case "showWin":
				return xdialong_showWin(this, param);
			}
		}
		options = $.extend({}, options);
		return this.each(function () {
			options.id = (this.id) ? this.id : "winDialogDefaultId";
			var state = $.data(this, "xdialog");
			if (state) {
				$.extend(state.options, options);
			} else {
				var t = $(this);
				var opts = $.extend({}, $.fn.xdialog.defaults, {title:(t.attr("title") ? t.attr("title") : undefined), href:t.attr("href"), collapsible:false, minimizable:false, maximizable:false, resizable:false}, options);
				$.data(this, "xdialog", {options:opts, bodyLayout:wrapDialog(obj)});
			}
			buildDialog(this);
		});
	};
	$.fn.xdialog.defaults = {title:"New Dialog", href:null, collapsible:false, minimizable:false, maximizable:false, resizable:false, modal:true};
})(jQuery);

