(function($) {
	jQuery.fn.extend( {
		xmenubutton : function(options, val) {
			options = options || {};
			if (typeof options == "string") {
				switch (options) {
				case "create":
					return LoadMenuButton(this, val);
				case "addOnClick":
					return setclickmenu(this, val);
				}
			}
		}
	});

	function sysLoadMenuButton(opt, data) {
		// 1.设置读取菜单json对象
		var ajax_options = {
			"options" : {
				"async" : false
			}
		};
		// 2.读取成功后处理事件
		ajax_options.BackE = function(R_data) {
			// 返回的是tree格式的json字符串
			if (R_data) {
				opt.data("attributes", R_data)
				// 添加菜单
				addMenuButton(opt, data);
			}
		}
		// 5.执行doServie
		ajax_options = $.extend(ajax_options, data);
		$.supper("doservice", ajax_options);
	}
 
	/**
	 * 设置菜单
	 */
	function LoadMenuButton(opt, data) {
		// 读取菜单
		var ajax_options = {
			"options" : {
				"async" : false
			},
			"BackE" : function(R_data) {
				// 读取menu的json对象
				if (R_data != undefined) {
					var select_text = null;
					$.each(R_data, function(i, n) {
						var iconCls = n.iconCls ? "iconCls='" + n.iconCls + "'"
								: "";
						var stra = new StringBuffer();
						stra.append("<a href=\"javascript:void(0)\" id=\"meu_"
								+ n.id + "\"");
						if (n.url) {
							stra.append("  url=\"" + n.url + "\" ");
						}
						if (n.text) {
							stra.append("  title=\"" + n.text + "\" ");
						}
						stra.append(" class=\"MenuButtonclass\" " + iconCls
								+ " >" + n.text + "</a>");
						opt.append(stra.toString());
						var meudiv = $("#meu_" + n.id);
						meudiv.menubutton( {
							"plain" : true
						});
						meudiv.data("menujson", n);
						var con_text = "";
						if (n.children != undefined) {
							var subid = "sub_" + n.id;
							n.text = "";
							var submenu = LoadSubMenuButton(subid, n);
							$("body").append(submenu);
							meudiv.menubutton( {
								"plain" : true,
								menu : "#" + subid
							});
						}
					});
					// 给菜单附单击事件
					//setAllclickmenu_sys(opt,data);
					setclickmenu(opt, data);
				}
			}
		};
		ajax_options = $.extend(ajax_options, data);
		$.supper("doservice", ajax_options);
	}
	function LoadSubMenuButton(divid, data) {
		var iconCls = data.iconCls ? "iconCls='" + data.iconCls + "'" : "";
		var title = $.trim(data.text);
		var att_width = data.width ? data.widthdata.width : 150;
		var strdiv = new StringBuffer();
		if(title=="menu-sep"){
			return $("<div class=\"menu-sep\"></div>");			 
		}
		strdiv.append(" <div id='" + divid
				+ "' style='width:"+att_width+"px;' class='MenuButtonclass' ");
		if (data.url) {
			strdiv.append("  url=\"" + data.url + "\" ");
		}
		strdiv.append(data.iconCls ? "iconCls='" + data.iconCls + "'" : "");
		strdiv.append(" title='" + title + "' >"+title+"</div>");
		var ls_return = $(strdiv.toString());
		if (data.children != undefined) {
			ls_return.append($("<span>" + title + "</span> "));
			var chind;
			if (data.text != "" && data.text != undefined) {	
				 chind = $("<div style='width:" + att_width + "px;' ></div>");	
			}else {
				chind=ls_return;
			}
			$.each(data.children, function(i, n) {
				var vid = "sub_" + n.id;
				var att_chindiv = LoadSubMenuButton(vid, n);
				chind.append(att_chindiv);
			});
			if (data.text != "" && data.text != undefined) {
				ls_return.append(chind);
			}
		}
		ls_return.data("menujson", data);
		return ls_return;

	}
 

	function setclickmenu(opt, data) {
		var idname = opt.attr("id");
		var bond = $(".MenuButtonclass");
		mclick(bond, data);
	}
	function mclick(array, data) {
		array.each(function(i) {
			$(this).bind("click", function() {
				var title = $.trim($(this).attr("title"));
				var url = $(this).attr("url");
				var idtext = $(this).attr("id");
				var attributes = $(this).data("menujson");
				idtext = idtext.substr(4, idtext.length - 4);
				if (data.ClickE && title) {
					//data.ClickE(title, url, idtext);
					data.ClickE(attributes);
				}
			});
		});
	}
})(jQuery);
