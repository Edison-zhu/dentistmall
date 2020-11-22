
(function ($) {
	jQuery.fn.extend({xtree:function (options, val) {
		options = options || {};
		if (typeof options == "string") {
			switch (options) {
			  case "refresh":
				return expandrefresh(this, val);
			  case "setexpand":
				return setexpand(this, val);
			  case "create":
				return sys_createTree(this, val);
			}
		}
	}});
	function setexpand(opt, val) {
		var tree_node = new Array();
		opt.tree("options").tree_node = tree_node;
		opt.tree("options").Xvalues = val;
		opt.tree("options").onLoadSuccess = xonLoadSuccess;
	}
	/**
	加载系统定义的onLoadSuccess时间，用于设置默认选中节点
	*/
	function xonLoadSuccess(node, data) {
		var opt = $(this);
		var user_onLoadSuccess = opt.data("user_onLoadSuccess");
		var NodeArray = opt.data("NodeArray");
		if (NodeArray) {
				var ro_node = opt.tree("find", NodeArray);
				if (ro_node) {
					opt.tree("select", ro_node.target);
					opt.tree("expand", ro_node.target);
				}
		  }else {
		  		var ro = opt.tree("getRoot");
				if (ro) { 
						 //默认选中根节点
					opt.tree("select", ro.target);
				}
		  }
			
		if (!node) {
			//第一次加载的时候 
				//触发默认用户提供的onLoadSuccess事件
			if (user_onLoadSuccess) {
				user_onLoadSuccess();
			}
		}  
	}
	function sys_createTree(opt, val) {
		if (val.onLoadSuccess) {
			opt.data("user_onLoadSuccess", val.onLoadSuccess);
		}
		val.onLoadSuccess = xonLoadSuccess;
		opt.tree(val);
	}
	function xExpand(opt) {
		var val = opt.tree("options").Xvalues;
		var tt = opt.tree("options").tree_node;
		if (tt) {
			var j = tt.length;
			if (j <= 0) {
				return;
			}
			var rid = tt.pop();
			var tree_find = opt.tree("find", rid);
			if (tree_find) {
				if (j > 1) {
					opt.tree("expand", tree_find.target);
				}
				if (j == 1) {
					opt.tree("select", tree_find.target);
					tt = [];
					if (val.porperties) {
						val.porperties(tree_find);
					}
					return;
				}
			}
		}
	}
	function expandrefresh(opt, val) {
		var sel = opt.tree("getSelected");
		var Parent = opt.tree("getParent", sel.target);
		opt.data("NodeArray", sel.id);
		 
		 if(Parent){
			opt.tree("reload", Parent.target);
		}else {
			opt.tree("reload");
		} 
		/*
		var i = 0;
		var tt = opt.tree("options").tree_node;
		tt[i] = sel.id;
		do {
			var parent = opt.tree("getParent", sel.target);
			if (parent){
				tt[++i] = parent.id;
				sel = parent;
			} else {
				break;
			}
		} while (true);
		opt.tree("reload");
		*/
	}
})(jQuery);

