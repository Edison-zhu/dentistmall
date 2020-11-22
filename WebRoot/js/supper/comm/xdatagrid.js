
(function ($) { 
	$.fn.xdatagrid = function(options, val) {
		options = options || {};
		if (typeof options == "string") {
			switch (options) {
			case "create":
				return  _createDateGrid( $(this), val); 
			}
		}
		
		return this.each(function() {
			var state = $.data(this, "xdatagrid");
			if (state) {
				$.extend(state.options, options);
			} else {
				var t = $(this);
				var opts = $.extend({}, $.fn.xdatagrid.defaults, options);
				$.data(this, "xdatagrid", {
					options : opts
				});
			}
			createObj(this);
		});
	}

	/*初始化xdatagrid对象*/
	function createObj(target) {
		var state = $.data(target, 'xdatagrid');
		var opts = state.options;
		$(target).form($.extend(state, opts, {}));
	}
	
	$.fn.xdatagrid.defaults = {
			id:"win_form_search",
			control:[],
			groupTag:[],
			items:[]	
	};
	
	 _createDateGrid =function (opt,val){ 
			  var att_mp=val.mmPaginatorOpt;
			  var data01=	{ 
					  		  method: 'get'
					         , remoteSort:true  
					         , multiSelect: true
					         ,nowrap:true 
					         , fullWidthRows: true
					         , autoLoad: false
					         ,showBackboard:false
					         ,checkCol:true
					          
			          };
				
			  var data02=	{ 
		              	   height:'auto'
			              ,isSaveSel:true 
			              , method: 'get'
			              , remoteSort:true
			              , url:val.url
			              , multiSelect: false
			              , checkCol: false
			              , fullWidthRows: true
			              ,nowrap:true 
			              ,showBackboard:false
			              , autoLoad: false
				          , dblClickFunc: val.dblClickFunc
			          };
			  
			  if(att_mp){
				  var tt={
						  plugins: [
					                  att_mp.mmPaginator({})
					              ]  
				  }
				  data01 = $.extend(data01, tt);
				  data02 = $.extend(data02, tt);
			  }
			   
			  var opts ;
				if(val.gridtype&&val.gridtype==2){ 
					  opts = $.extend(data02, $.fn.xdatagrid.defaults, val);
				}else {
					 opts = $.extend(data01, $.fn.xdatagrid.defaults, val); 
				}
			   var mmg= opt.mmGrid(opts);
		       return  mmg
	        }   
	 
	
	
})(jQuery);
	 

