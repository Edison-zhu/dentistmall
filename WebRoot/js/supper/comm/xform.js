(function($) {

	/*初始化xform对象*/
	function createForm(target) {
		//var state = $.data(target, 'xform');
		//var opts = state.options; 
	}
	/**加載Form對象的初始化*/
	function LoadForm(opt, val) {
		var objInfoFormid = $(opt).attr("id");
		if (!objInfoFormid) {
			return;
		}

		$("#" + objInfoFormid + " textarea").each(
				function(i, v) { 
						var att_name = $(v).attr("name");
						eval("var vals=val." + att_name + ";");
						if (vals) {
							$(v).text(vals == undefined ? '' : vals);
							$(v).val(vals == undefined ? '' : vals);
						} 
		}); 
		
		$("#" + objInfoFormid + " input").each(
				function(i, v) { 
					var att_type = $(v).attr("type"); 
					var att_Rowtype = $(v).attr("rowtype"); 
					if (val) {
						var att_name = $(v).attr("name");
						eval("var vals=val." + att_name + ";")
						if (vals) { 
							if (ifcheach(att_type) == 2||ifcheach(att_type) ==4) { 
								var att_che = $("input[name='" + att_name
										+ "']");
								for ( var i = 0; i < att_che.length; i++) {
									var row = $(att_che[i]);
									if (row.val() == vals) {
										row.attr('checked', 'true');
									} else {
										row.removeAttr("checked");
									}
								}
							} else if (ifcheach(att_type) == 1) {
								 $(v).val(vals == undefined ? '' : vals);
								 if( att_Rowtype&&"file"==att_Rowtype) {
								 
									 $("div[butname='"+att_name+"']").remove();
									 var bt_id="upButton_"+att_name;
									 var  id= att_name;
									 $.supper('rewFile',{id:bt_id,codeId:id,className:id,value:vals});
								  
								 }
								  
							}
						}
					}
	  }); 
		
		$("#" + objInfoFormid + " select").each(
				function(i, v) { 
					var att_type = $(v).attr("type"); 
					
					if (val) {
						var att_name = $(v).attr("name");
						eval("var vals=val." + att_name + ";")
						if (vals) {  
							if(att_type=="xselect"){
								$(v).selectpicker('val', vals);
							}else {
								$(v).val(vals == undefined ? '' : vals);
							} 
						}
					}
	  }); 
		
	}
	
	
	function LoadAjaxForm(opt,val){ 
		$.supper("doservice", {"service":val.ActionUrl,
			"options" : {
			"type" : "post",
			"data" : val.data,
			"async" : false
		}, "ifloading":1,  "BackE":function (jsondata) {
			if (jsondata.code == "1"&&jsondata.obj) { 
				   LoadForm(opt, jsondata.obj);
				   if(val.BackE){
					   val.BackE(jsondata);
				   }
			}
		}});
		
		
	}
	
	/**
	 * 在form控件容器中画数据显示表格
	 * 
	 */
	function sys_drawForm(obj, val) {

	}
	/**
	 * 获取数据
	 */
	function getRowData(fromData,line,column){
		var att_coln= fromData.columnNum;
		var  att_nums=line*att_coln+column;
		return fromData.items[att_nums]; 
	}
	/**
	 * 获取行数
	 */
	function getFormlabelNum(rowdata,columnNum) { 
		if(rowdata.labelNum){
			return  rowdata.labelNum;
		}else {
			  if(columnNum==1){
						return 3;
				}
				if(columnNum==2){
						return 2;
				}
				if(columnNum==3){
						return 1;
				}
				if(columnNum==4){
						return 1;
				}
		}
	}
	/**
	 * 获取列数
	 */
	function getForminputNum(rowdata,columnNum) {
			if(rowdata.inputNum){
				return  rowdata.inputNum;
			}else {
				if(columnNum==1){
						return 8;
				}
				if(columnNum==2){
						return 4;
				}
				if(columnNum==3){
						return 3;
				}
				if(columnNum==4){
						return 2;
				} 
			}
	}
	function  getCode(pix){
		var lb_str="";
		$.supper("doservice", {"service":"SysParameterService.getNewCode",
			"options" : {
			"type" : "post",
			"data" : "prefix="+pix,
			"async" : false
		}, "ifloading":1,  "BackE":function (jsondata) {
			if (jsondata.code == "1"&&jsondata.obj) { 
				lb_str= jsondata.obj;
			}
		}});
		return lb_str;
	}
	
	/**
	 * 获取对象
	 */
	function getControlObj(rowdata) {
	    var conObj=null;
		rowdata.type = rowdata.type ? rowdata.type : "text";
		var att_id=rowdata.id?rowdata.id:rowdata.name;
		var att_readOnly=rowdata.readOnly?true:false;
		
		if ("userdefined" == rowdata.type) {
			if(rowdata.renderer){
				var str=rowdata.renderer(rowdata);
				 conObj=$(str);
			}else {
				rowdata.type="text";
			}
		} 
		
		if ("text" == rowdata.type) {
			var ar=rowdata.ariaRequired?'true':'false';
			var ai=rowdata.ariaInvalid?'true':'false';
			var str='<input id="'+att_id+'" name="'+rowdata.name+'" class="form-control"   placeholder="'+rowdata.placeholder+'"	type="text" '+	' aria-required="'+ar+ '" aria-invalid="'+ai+ '">';
			 conObj=$(str);
			 if(rowdata.prefixCode){
				 var st=getCode(rowdata.prefixCode);
				 conObj.val(st);
			 }
		}
		
		if ("hidden" == rowdata.type) { 
			var str='<input type="hidden" name="'+rowdata.name+'" id="'+att_id+'"  value="'+(rowdata.value?rowdata.value:"")+'"  />';
			conObj=$(str);
			if(rowdata.prefixCode){
				var st=getCode(rowdata.prefixCode);
				conObj.val(st);
			}
			
		} 
		if ("date" == rowdata.type) {  
			var str='<input type="text" placeholder="开始时间"    id="'+att_id+'" name="'+rowdata.name+'" readonly class="form-control laydate-icon">';
			conObj=$(str);
		}
		if ("select" == rowdata.type) {  
			var str=' <select   type="select"  class="form-control" name="'+rowdata.name+'" id="'+att_id+'" ></select>';
			conObj=$(str);
			var defaultData=rowdata.defaultData?rowdata.defaultData:("选择"+rowdata.title);
			var str = "<option value=\"\">"+defaultData+"</option>";

			if(rowdata.impCode !== undefined && rowdata.impCode !== null && rowdata.impCode !== '') {
				var shqJson = $.supper("getsysParam", rowdata.impCode);
				for (var i = 0; i < shqJson.length; i++) {
					str += "<option value=\"" + shqJson[i].id + "\">" + shqJson[i].name + "</option>";
				}
			} else if (rowdata.data !== undefined && rowdata.data !== null && rowdata.data !== '') {
				rowdata.data.forEach(function (value) {
					str += "<option value=\"" + value.id + "\">" + value.name + "</option>";
				})
			}
			 conObj.html(str);
			 rowdata.val=(rowdata.val)?(rowdata.val):(rowdata.value);
			 if(rowdata.val != null && rowdata.val != "")
				 conObj.val(rowdata.val); 
		}
		if('multipleselect' == rowdata.type){ //可多选，获取的值是以逗号隔开
			var str=' <select';
			if (rowdata.data) {
				str += ' xm-select-max="' + rowdata.data + '"';
			}
			str += ' xm-select="'+rowdata.name+'"  class="form-control" placeholer="选择'+rowdata.title+'" name="'+rowdata.name+'" id="'+att_id+'" xm-select-skin="primary"></select>';
			conObj=$(str);
			// var defaultData=rowdata.defaultData?rowdata.defaultData:("选择"+rowdata.title);
			// var shqJson=$.supper("getsysParam",rowdata.impCode);
			//
			// var str = "<option value=\"\">"+defaultData+"</option>";
			// for(var  i = 0 ;i < shqJson.length ;i++){
			// 	str += "<option value=\""+shqJson[i].id+"\">"+shqJson[i].name+"</option>";
			// }
			// conObj.html(str);
			// rowdata.val=(rowdata.val)?(rowdata.val):(rowdata.value);
			// if(rowdata.val != null && rowdata.val != "")
			// 	conObj.val(rowdata.val);
		}

		if("inputSelect" == rowdata.type){ //可输入下拉框
			var str =' <select   type="select"  class="form-control" name="'+rowdata.name+'" id="'+att_id+'" placeholder="' + rowdata.placeholder + '"></select>';
			conObj=$(str);
			var defaultData=rowdata.defaultData?rowdata.defaultData:("选择"+rowdata.title);
			var shqJson=$.supper("doselectService",rowdata.param);

			var str = "";
			for(var  i = 0 ;i < shqJson.length ;i++){
				str += "<option value=\""+shqJson[i].id+"\">"+shqJson[i].name+"</option>";
			}
			conObj.html(str);
			rowdata.val=(rowdata.val)?(rowdata.val):(rowdata.value);
			if(rowdata.val != null && rowdata.val != "")
				conObj.val(rowdata.val);
		}

				
		if ("xselect" == rowdata.type) {
			var str = '<div id="' + att_id + '" name="' + rowdata.name
					+ '" class="form-control"  ></div>';
			conObj = $(str);
	
		} 
		
		if("timeInterval"== rowdata.type) { 
			var str='<div class="row"><div class="col-sm-5"><input type="text" placeholder="开始时间" id="'+rowdata.startTime+'" name="'+rowdata.startTime+'" readonly   class="form-control laydate-icon"></div>'
			+'<div class="col-sm-1">-</div>'
			+'<div class="col-sm-5"><input type="text" placeholder="结束时间" id="'+rowdata.endTime+'"  name="'+rowdata.endTime+'" readonly    class="form-control laydate-icon"></div></div>';	
			conObj=$(str);
		} 
		if('file'==rowdata.type) { 
			var str='<span><div id="upButton_'+att_id+'" value="" ></div><input type="hidden" rowtype="file" id="'+att_id+'" name="'+att_id+'" value="'+(rowdata.value?rowdata.value:"")+'"     /></span>'
			conObj=$(str); 
		} 
		
		if('textarea'==rowdata.type) { 
		    var att_height="80px";
			
			if(rowdata.height){
				 var 	lb_back=CheckUtil.isDigit(rowdata.height);
				if(lb_back){
					 att_height=rowdata.height?(rowdata.height+"px"):"80px";
				}else {
					att_height=rowdata.height;
				}
			}
			var att_width= "600px";
			if(rowdata.width){
				 var 	lb_back=CheckUtil.isDigit(rowdata.width);
				 if(lb_back){
			    att_width=rowdata.width?(rowdata.width+"px"):"600px";
				 }else { 
					 att_width=rowdata.width;
				 }
			}
			
			var defaultData= rowdata.defaultData?rowdata.defaultData:("输入"+rowdata.title);
			var str='<textarea class=\"form-control\" id="'+att_id+'" name="'+rowdata.name+'"  style="height:'+att_height+';width:'+att_width+'"   placeholder="'+rowdata.placeholder+'"></textarea>';	
			conObj=$(str);
		}
		
		
		 if(att_readOnly){
			 conObj.attr("readonly", "readonly");
			// conObj.attr("disabled", "disabled");
		 }
		return conObj;

	}
	
	function syscreatehidden(opt,item){
		opt.html("");
		if(item){
			for ( var i = 0; i < item.length; i++) {  
				opt.append(getControlObj(item[i]));
			} 
		}
	}
	
	function syscreateForm(opt,_searchForm){
		
		var _searchForm = $.extend({}, $.fn.xform.defaults, _searchForm);
		
	  	if(_searchForm.renew&&_searchForm.renew==true){
	  		opt.html("");
	  	}
		_searchForm.id=opt.attr("id");
		_all_win_searchForm=opt;
		_searchForm.lineNum=_searchForm.lineNum?(_searchForm.lineNum<1?1:_searchForm.lineNum):1;
		_searchForm.columnNum=_searchForm.columnNum?(_searchForm.columnNum<=4&&_searchForm.columnNum>=1?_searchForm.columnNum:1):1;
		var _index = 0;
		T1:
		for ( var i = 0; i < _searchForm.lineNum; i++) {
			var formGroupObj = $('<div class="form-group" style="margin-bottom:0px;"></div>');
			_all_win_searchForm.append(formGroupObj);
			var nums=0;
			T2:
			for ( var j = 0; j < _searchForm.columnNum; j++) {
				var rowdata = getRowData(_searchForm,i,j);
				if (rowdata != null&&nums< 12) {
					var labelNum=getFormlabelNum(rowdata,_searchForm.columnNum);
					var inputNum=getForminputNum(rowdata,_searchForm.columnNum);
					nums+=labelNum+inputNum; 
					var ar=rowdata.ariaRequired?'true':'false';	
					var reqStr=""; 
					
					if(ar=='true'){ 
							reqStr="<span class='text-danger'>(*)</span>"
					}
					var labelObj="";
					var controlDivObj="";
					var controlObj="";
					if ("space" == rowdata.type) {  
						  labelObj = $('<label></label>');
						  controlDivObj = $('<div class="col-sm-'+inputNum+'"></div>');
						  controlObj = "";
					}else {
						  labelObj = $('<label class="col-sm-'+labelNum+' control-label">'
								+ rowdata.title +reqStr+ '：</label>');
						  controlDivObj = $('<div class="col-sm-'+inputNum+'"></div>');
						  controlObj = getControlObj(rowdata);
					}
					
					formGroupObj.append(labelObj);
					formGroupObj.append(controlDivObj);
					controlDivObj.append(controlObj);
					if ("date" == rowdata.type) {  
						laydate({elem: '#'+rowdata.name,format: 'YYYY-MM-DD', istime: true,istoday: false}); 
					}
					if("timeInterval"== rowdata.type) { 
						laydate({elem: '#'+rowdata.startTime,format: 'YYYY-MM-DD', istime: true,istoday: false}); 
						laydate({elem: '#'+rowdata.endTime,format: 'YYYY-MM-DD', istime: true,istoday: false});  
					}
					
					if('file'==rowdata.type) {
						var att_id=rowdata.id?rowdata.id:rowdata.name;
						var bt_id="upButton_"+att_id;
						var  id= att_id;
						$.supper('initUpFile',{id:bt_id,codeId:id,className:id});
					}
					
					if("xselect"==rowdata.type){ 
						var att_data = rowdata.data ? rowdata.data : ""; 
						if (rowdata.service) {
							$.supper("doservice", {
								"service" : rowdata.service,
								"data" : att_data,
								options : {
									async : false
								},
								"BackE" : function(jsondata) {
									if (jsondata.code == "1") {  
										var defaultsopt = {
											"id" : att_id,
											"selectData" : jsondata.obj,
											"liveSearch" : true,
											"doneButton" :true,
											maxOptions :1, 
											title : "请选择",
											classStr : "show-tick,form-control",
											liveSearchPlaceholder : "输入条件提示信息",
											actionsBox : false,
											hideDisabled : true
										}; 
										var opts = $.extend({}, defaultsopt, rowdata); 
										var att_opt=initXSelect(opts);
										 controlDivObj.append(att_opt);
									}
								}
							});
						}
						
					}
					
					_searchForm.control.push({
						"index" : _index,
						"label" : labelObj,
						"controlDiv" : controlDivObj,
						"control" : controlObj
					});
					_index++;
				}

			}  
			if(i!= _searchForm.lineNum-1){
			   var lineDashedObj=$('<div class="hr-line-dashed" style="margin:5px 0px;"></div>');
			   _all_win_searchForm.append(lineDashedObj);
			}
			_searchForm.groupTag.push(formGroupObj);
		} 
		_all_win_searchForm.data("option", _searchForm);
		
	}
	
	function sys_checkForm(opt){
		var rowdatas=$(opt).data("option");
		var lb_back=true;
	    $.each(rowdatas.items, function(idex_i, rowdata){ 
	    	var ar=rowdata.ariaRequired?'true':'false';
			var ai=rowdata.ariaInvalid?'true':'false'; 
			var inputValue = $("#"+(rowdata.id != undefined ? rowdata.id : rowdata.name)).val();
			if('true'==ar){ 
		   		if(inputValue == null || inputValue == ""){
		   			$.supper("alert",{ title:"操作提示", msg: (rowdata.error?rowdata.error:rowdata.title+"不能为空！")});
		   			lb_back=false;
		   			return false;
		   		} 
			}
			if('true'==ai){
				var checkType=rowdata.checkType?rowdata.checkType:null;
				if(checkType!=null){
					if("isDigit"==checkType){
						lb_back=CheckUtil.isDigit(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是数字！")}); 
							return false;
						}
					}else if("isPlusFloat"==checkType){
						lb_back=CheckUtil.isPlusFloat(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是正浮点数！")}); 
							return false;
						}
					}else if("isInteger"==checkType){
						lb_back=CheckUtil.isInteger(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是整数！")}); 
							return false;
						}						
					}else if("isPlusInteger"==checkType){
						lb_back=CheckUtil.isPlusInteger(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是正整数！")}); 
							return false;
						}						
					}else if("isMobile"==checkType){
						lb_back=CheckUtil.isMobile(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是手机号！")}); 
							return false;
						}						
					}else if("isUrl"==checkType){
						lb_back=CheckUtil.isUrl(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是网址！")}); 
							return false;
						}						
					}else if("isEmail"==checkType){
						lb_back=CheckUtil.isEmail(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是邮件地址！")}); 
							return false;
						}						
					}else if("isZipCode"==checkType){
						lb_back=CheckUtil.isZipCode(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是邮编！")}); 
							return false;
						}						
					}else if("isIP"==checkType){
						lb_back=CheckUtil.isIP(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是ip地址！")}); 
							return false;
						}						
					}else if("isIDCard"==checkType){
						lb_back=CheckUtil.isIDCard(inputValue);
						if(lb_back==false){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"必须是身份证号！")}); 
							return false;
						}						
					}else if("length"==checkType){
						let minMax = rowdata.aiData;
						if (inputValue.length > minMax.max && inputValue.length < minMax.min){
							$.supper("alert",{ title:"操作提示", msg: ( rowdata.title+"长度在" + minMax.min + "与" + minMax.max + "之间！")});
							return false;
						}
					}
					
					
				}
			}
		});
	
	  return lb_back;
	}
	
	

function ifcheach(att_type) {
		var lb = 1;
		if ("radio" == att_type)
			return 2;
		if ("checkbox" == att_type)
			return 3;
		if("select"== att_type)
			return 4;
		return lb;

	} 

function assignValue(opt,data) {
		var objInfoFormid = data.id;
		var Arrary_state = data.colstates;
		var val = data.obj;
		var setReadOnly = false;
		if (!objInfoFormid) {
			return;
		}
		$("#" + objInfoFormid + " input").each(
				function(i, v) {
					var att_state = $(v).attr("colstates");
					var att_type = $(v).attr("type");
					if (att_type && (att_type == "hidden")) {
						setReadOnly = false;
					} else {
						setReadOnly = data.setReadOnly ? data.setReadOnly
								: false;
					}
					if (setReadOnly) {
						if (att_state && Arrary_state
								&& colstates(att_state, Arrary_state)) {
							$(v).removeAttr("readonly");
							$(v).removeAttr("disabled");
						} else {
							$(v).attr("readonly", "readonly");
							$(v).attr("disabled", "disabled");
						}
					}
					if (val) {
						var att_name = $(v).attr("name");
						eval("var vals=val." + att_name + ";")
						if (vals) {
							if (ifcheach(att_type) == 2) { 
								var att_che = $("input[name='" + att_name
										+ "']");
								for ( var i = 0; i < att_che.length; i++) {
									var row = $(att_che[i]);
									if (row.val() == vals) {
										row.attr('checked', 'true');
									} else {
										row.removeAttr("checked");
									}
								}
							} else if (ifcheach(att_type) == 1) {
								$(v).val(vals);
							}
						}
					}
				});

		$("#" + objInfoFormid + " textarea").each(
				function(i, v) {
					setReadOnly = data.setReadOnly ? data.setReadOnly : false;
					if (setReadOnly) {
						var att_state = $(v).attr("colstates");
						if (att_state && Arrary_state
								&& colstates(att_state, Arrary_state)) {
							$(v).removeAttr("readonly");
							$(v).removeAttr("disabled");
						} else {
							$(v).attr("readonly", "readonly");
							$(v).attr("disabled", "disabled");
						}
					}
					if (val) {
						var att_name = $(v).attr("name");
						eval("var vals=val." + att_name + ";");
						if (vals) {
							$(v).text(vals);
							$(v).val(vals);
						}
					}
				});
	} 
	 
	

function sys_cleanForm(opt,val){
	
	var objInfoFormid = $(opt).attr("id");
	if (!objInfoFormid) {
		return;
	}
	$("#" + objInfoFormid + " textarea").val("");
	$("#" + objInfoFormid + " textarea").text(""); 
	
	$("#" + objInfoFormid + " input").val(""); 
	
	$("#" + objInfoFormid + " select").val(""); 
	
	 	
}
	
	
	
	
	$.fn.xform = function(options, val) {
		options = options || {};
		if (typeof options == "string") {
			switch (options) {
			case "loadForm":
				return LoadForm(this, val);
			case "loadAjaxForm":
				return LoadAjaxForm(this, val);	
				
			case "drawForm":
				return sys_drawForm(this, val);
			case "cleanForm":
				return sys_cleanForm(this, val); 
			case "checkForm":
				return sys_checkForm(this, val);	
				
			case "createForm":
				return syscreateForm(this, val);
			case "createhidden":
				return syscreatehidden(this, val);	
			}
		}
		return this.each(function() {
			var state = $.data(this, "xform");
			if (state) {
				$.extend(state.options, options);
			} else {
				var t = $(this);
				var opts = $.extend({}, $.fn.xform.defaults, options);
				$.data(this, "xform", {
					options : opts
				});
			}
			createForm(this);
		});
	};
	
	
	$.fn.xform.defaults = {
			id:"win_form_search",
			control:[],
			groupTag:[],
			items:[]	
		};
	
})(jQuery);
