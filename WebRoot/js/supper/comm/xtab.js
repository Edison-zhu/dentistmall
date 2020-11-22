(function($) {
	jQuery.fn.extend( {
		xtab : function(options, val) {
			options = options || {};
			if (typeof options == "string") {
				switch (options) {
				case "setTabClose":
					return setTabClose(this);
				case "addTab":
					return addTab(this, val);
				case "refreshtab":
					return refreshtab(this, val);
				}
			}
		}
	});
	/**
	 * 添加tab页面
	 */
	function addTab(opt, val) {
		var tt = opt;
		var title = val.title;
		var href = val.href ? val.href : val.url;
		if (tt.tabs("exists", title)) {
			tt.tabs("select", title);
		} else {
			if (href) {
				var date = new Date();
				var date_str = "tab" + date.format("yyyyMMddhhmmss");
				var content = $.supper("getIframe", {
					id : date_str,
					href : href
				});
			} else {
				var content = val.content ? val.content : "\u672a\u5b9e\u73b0";
			}
			var v_closable = (val.closable == undefined) ? true : val.closable;
			var options = $.extend(val, {
				title : title,
				closable : v_closable,
				content : content
			}, {})
			tt.tabs("add", options);
			opt.xtab("setTabClose");
		}
	}
	/**
	 * 添加tab关闭菜单
	 */
	function setTabClose(opt) {
		var tw = $("#Hsktabclosemm2010");
		var tabmenu_div = "<div id='Hsktabclosemm2010' class='easyui-menu' style='width: 150px;'><div id='mm-tabclose'>\u5173\u95ed</div><div id='mm-tabcloseall'>\u5168\u90e8\u5173\u95ed</div><div id='mm-tabcloseother'>\u9664\u6b64\u4e4b\u5916\u5168\u90e8\u5173\u95ed</div><div class='menu-sep'></div> </div>";
		if (tw.length == 0) {
			$("body").append(tabmenu_div);
			tabCloseEven(opt);
			$("#Hsktabclosemm2010").menu( {});
		}
		var _thisid = opt.attr("id");
		var runid = $("#" + _thisid + ">div>div>ul>li>a");
		/* 双击关闭TAB选项卡 */
		runid.dblclick(function() {
			var subtitle = $(this).children("span").text();
			if (subtitle != "\u9996\u9875") {
				$("#" + _thisid).tabs("close", subtitle);
			}
		});
		runid.bind("contextmenu", function(e) {
			$("#Hsktabclosemm2010").menu("show", {
				left : e.pageX,
				top : e.pageY
			});
			var subtitle = $(this).children("span").text();
			$("#Hsktabclosemm2010").data("currtab", subtitle);
			return false;
		});
	}
	function refreshtab(opt, val) {
		var title = val.title;
		if (opt.tabs("exists", title)) {
			opt.tabs("close", title);
		}
		opt.tabs("add", val);
	}

	// 绑定右键菜单事件
	function tabCloseEven(runtabid) {
		// 关闭当前
		$("#mm-tabclose").click(function() {
			var currtab_title = $("#Hsktabclosemm2010").data("currtab");
			if (currtab_title == "\u9996\u9875") {
				return;
			}
			runtabid.tabs("close", currtab_title);
		});
		// 全部关闭
		$("#mm-tabcloseall").click(function() {
			$(".tabs-inner span").each(function(i, n) {
				var t = $(n).text();
				if (t != "\u9996\u9875") {
					runtabid.tabs("close", t);
				}
			});
		});
		// 关闭除当前之外的TAB
		$("#mm-tabcloseother").click(function() {
			var currtab_title = $("#Hsktabclosemm2010").data("currtab");
			$(".tabs-inner span").each(function(i, n) {
				var t = $(n).text();
				if ((t != currtab_title) && (t != "\u9996\u9875")) {
					runtabid.tabs("close", t);
				}
			});
		});
		// 关闭当前右侧的TAB
		$("#mm-tabcloseright").click(function() {
			var nextall = $(".tabs-selected").nextAll();
			if (nextall.length == 0) {
				// msgShow('系统提示','后边没有啦~~','error');
				// alert('后边没有啦~~');
				return false;
			}
			nextall.each(function(i, n) {
				var t = $("a:eq(0) span", $(n)).text();
				if (t != "\u9996\u9875") {
					runtabid.tabs("close", t);
				}
			});
			return false;
		});
		// 关闭当前左侧的TAB
		$("#mm-tabcloseleft")
				.click(
						function() {
							var prevall = $(".tabs-selected").prevAll();
							if (prevall.length == 0) {
								alert("\u5230\u5934\u4e86\uff0c\u524d\u8fb9\u6ca1\u6709\u5566~~");
								return false;
							}
							prevall.each(function(i, n) {
								var t = $("a:eq(0) span", $(n)).text();
								if (t != "\u9996\u9875") {
									runtabid.tabs("close", t);
								}
							});
							return false;
						});
	}
})(jQuery);
