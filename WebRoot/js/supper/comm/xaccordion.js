
(function ($) {
	jQuery.fn.extend({xaccordion:function (options, val) {
		options = options || {};
		if (typeof options == "string") {
			switch (options) {
			  case "createMenu":
				return createMenu(this, val);
			  case "addMenu":
				return addMenu(this, val);
			  case "addMenuOnClick":
				return clickMenu(this, val);
			  case "addSubMenu":
				return addMenu_Sub(this, val);
			  case "clearMenu":
				return menuclean(this);
			}
		}
	}});
	/**
设置菜单
**/
	function createMenu(opt, val) {
		opt.accordion({});
		loadmenu(opt, val);
	}
	function menuclean(opt) {
		/** 清除菜单* */
		var tt = "#" + opt.attr("id");
		var panels = $(tt).accordion("panels");
		var arr_title = new Array();
		for (var i = 0; i < panels.length; i++) {
			var n = panels[i];
			var title = n.panel("options").title;
			arr_title[i] = title;
		}
		for (var i = 0; i < arr_title.length; i++) {
			$(tt).accordion("remove", arr_title[i]);
		}
	}
	/**
添加一级菜单
**/
	function addMenu(opt, data) {
		/** 添加一级菜单 * */
		if (data.title == undefined) {
			return;
		}
		if (data.ico == undefined) {
			data.ico = "";
		}
		if (data.state == undefined || data.state == "" || data.state != "open") {
			data.state = false;
		} else {
			data.state = true;
		}
		opt.accordion("add", {title:$.trim(data.title), iconCls:data.ico, content:data.content});
	}
	function addMenu_Sub(opt, data) {
		/** 添加二级菜单* */
		var tt = "#" + opt.attr("id");
		if (data.title == undefined) {
			return;
		} // 父节点菜单
		if (data.parenttitle == undefined) {
			return;
		}// 需要添加节点菜单
		if (data.ico == undefined) {
			data.ico = $.supper.defaults.basepath + "img/kdmconfig.png";
		} else {
			data.ico = $.supper.defaults.basepath + "img/" + data.ico;
		}// 节点菜单图标
		if (data.url == undefined) {
			data.url = "";
		}// 节点触发的事件
		if (data.id == undefined) {
			data.id = "";
		}// 节点id
		//var palt =  (tt).accordion("getPanel", data.title);
		var str_content = "<div class=\"nav-item\"   style='text-align:center' id=\"" + data.id + "\">" + "<a href=\"#\"  parenttitle=\"" + data.parenttitle + "\" title=\"" + data.title + "\" idtext=\"" + data.id + "\"  url=\"" + data.url + "\"><img src='" + data.ico + "'></img> <br /><span>" + data.title + "</span></a></div>";
		//palt.append(str_content);
		return str_content;
	}
	//添加二级菜单onclick事件
	function clickMenu(opt, data) {
		if (data.id) {
			$("#" + data.id).unbind("click");
			$("#" + data.id).bind("click", function () {
				var title = $.trim($(this).text());
				var url = $(this).attr("url");
				var idtext = $(this).attr("idtext");
				if (data.ClickE) {
					data.ClickE(title, url, idtext);
				}
			});
			return;
		}
		var optid = "#" + opt.attr("id");
		$(optid + "  a").each(function (i) {
			$(this).bind("click", function () {
				var title = $.trim($(this).text());
				var url = $(this).attr("url");
				var idtext = $(this).attr("idtext");
				if (data.ClickE) {
					data.ClickE(title, url, idtext);
				}
			});
		});
	}
	function loadmenu(opt, data) {
		menuclean(opt);
		var checktitle = null;
		var ajax_options = {"options":{"async":false}, "BackE":function (R_data) {
			// 读取menu的json对象
			if (R_data != undefined) {
				var select_text = null;
				$.each(R_data, function (i, n) {
					var title = n.text;
					var gpanel = opt.accordion("getPanel", title);
					if (n.state && n.state == "open") {
						checktitle = n.text;
					}
					if (gpanel) {
					} else {
						var con_text = "";
						if (n.children != undefined) {
							$.each(n.children, function (index, E_data) {
								var subtitle = E_data.text;
								var ico = E_data.iconCls;
								con_text = con_text + addMenu_Sub(opt, {"title":subtitle, "ico":ico, "subtitle":title, "id":E_data.id, "url":E_data.url});
							});
						}
						addMenu(opt, {"title":n.text, "ico":n.iconCls, "state":n.state, content:con_text});
					}
				});
				 //设置选中菜单
				if (checktitle) {
					opt.accordion("select", checktitle);
				}
				clickMenu(opt, data);
			}
		}};
		ajax_options = $.extend(ajax_options, data);
		$.supper("doservice", ajax_options);
	}
})(jQuery);

