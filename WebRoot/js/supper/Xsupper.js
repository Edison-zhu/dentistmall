function saveDataGridShowList(paramNames,controlId){
	var data = "controlId="+controlId+"&paramNames="+paramNames+"&paramSource=3";
	$.supper("doservice", {"service":"SysControlParamService.saveSysControlParamList","data":data, "BackE":function (jsondata) {
	}});
}

function getControlDataGridCols(cols,controlId){
	var data = "controlId="+controlId+"&paramSource=3";
	$.supper("doservice", {"service":"SysControlParamService.getMyUserSysControlParamList","data":data,options:{"async":false}, "BackE":function (jsondata) {
		if(jsondata.code ==1 && jsondata.obj != null && jsondata.obj.length >0){
			for(var i = 0;i < cols.length;i++){
				var hidden=true;
				for(var j =0 ;j < jsondata.obj.length;j++){
					if(cols[i].name==jsondata.obj[j].paramName){
						hidden=false;
						break;
					}
				}
				cols[i].hidden=hidden;
			}
		}
	}});
	return cols;
}
function GoTOLoad(att_url,att_service) {
	var url = $.supper("getServicePath", {url:att_url,"service":att_service});
	$("#frame_active_region").attr("src", url);
}
function GoTOrecepLoad(att_url,data) {
	var AjaxUrl = $.supper("getServicePath", {url:att_url,data:data});
	window.location.href = AjaxUrl;
}
function ReceptionCancellation() {
	$.supper("doservice", {"service":"SysLoginService.ReceptionCancellation", "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			window.location.href = "admin.htm";
		}
	}});
}
/**
 * 获取系统web主目录
 */
function GetWebPath(url) {
var requestURL = window.location.href;
var regExp = /([^\/]+\/{2}[^\/]+\/[^\/]+)/;
regExp.exec(requestURL);
var result = RegExp.$1;
var rootk = result.split("/");
return "/" + rootk[rootk.length - 1] + url;
}


// js文件路径数组
/**
 * 
 * "js/jq_easyui/jquery.easyui.min.js",
 * 
 * "js/jq_easyui/locale/easyui-lang-zh_CN.js", "js/supper/comm/xtree.js",
 * 
 * "js/supper/comm/xtab.js", "js/supper/comm/xaccordion.js",
 * 
 * "js/supper/comm/xcombox.js", "js/supper/comm/xdatagrid.js",
 * 
 * "js/supper/comm/xmenubutton.js", "js/supper/comm/xtoolbar.js",
 * 
 * "js/supper/comm/xdialog.js"
 * 
 */
var base_jsfile = ["js/supper/comm/xSelect.js?v=5", "js/supper/comm/xform.js?v=41", "js/supper/comm/xtoolbar.js?v=6", "js/supper/comm/xdatagrid.js?v=17"];
var base_default_dataType = "json";
var base_default_async = true;
var base_default_type = "post";
(function ($) {
/** 系统登录事件* */
function system_login(obj, val) {
	$.supper("doservice", {"service":"UserService.checkout", "data":val.data, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("setProductArray", {"name":"admin_vercode", "value":jsondata.obj.admin_vercode});
			$.supper("setProductArray", {"name":"user_vercode", "value":jsondata.obj.user_vercode});
			window.location.href = $.supper("getServicePath", {url:jsondata.obj.url, data:"admin_vercode=" + jsondata.obj.admin_vercode + "&user_vercode=" + jsondata.obj.user_vercode});
		}
	}});
}
/**
 * 从窗口中获取一个iframe对象
 */
 function sys_getIframe(opt, val) {
	var att_iframe = $.supper("getProductArray", "iframe_default");
	var att_href = val.href ? val.href : val.url;
	var att_id = val.id ? ("iframe_" + val.id) : "iframe_" + att_iframe.length;
	if (jQuery.isEmptyObject(att_iframe)) {
		att_iframe = new Array();
	}
	att_iframe.push(att_id);
	$.supper("setProductArray", {"name":"iframe_default", "value":att_iframe});
		 // scrolling="no"
	return "<iframe id=\"" + att_id + "\" frameborder=\"0\"    src=\"" + att_href + "\" style=\"width:100%;height:100%;padding:0px;\"></iframe>";
}
/** 把一个对象转换成json字符串* */
function Obj2str(o) {
	if (o == undefined) {
		return "";
	}
	var r = [];
	if (typeof o == "string") {
		return "\"" + o.replace(/([\"\\])/g, "\\$1").replace(/(\n)/g, "\\n").replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
	}
	if (typeof o == "object") {
		if (!o.sort) {
			for (var i in o) {
				r.push("\"" + i + "\":" + Obj2str(o[i]));
			}
			if (!!document.all && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test(o.toString)) {
				r.push("toString:" + o.toString.toString());
			}
			r = "{" + r.join() + "}";
		} else {
			for (var i = 0; i < o.length; i++) {
				r.push(Obj2str(o[i]));
			}
			r = "[" + r.join() + "]";
		}
		return r;
	}
	return o.toString().replace(/\"\:/g, "\":\"\"");
}
/**
 * 显示窗口
 * 
 * @param opt
 * @param val
 * @return
 */
function sys_ShowWin(val) {
	var url = val.url;
	var wth = val.width? val.width:800;
	var hth = val.height? val.height:400;
	var area;
	if (val.area != undefined)
		area = val.area;
	else
		area=  [(val.width? val.width:800) + 'px', (val.height? val.height:400) + 'px']
	var enterKeyClose = val.close ? val.close : true; //回车键是否关闭窗口，默认不关闭，若需要关闭，传入false
	var att_title=val.title?val.title:"提示"
	var att_icon=val.icon?val.icon:"fa-exclamation-circle";
	var att_maxmin=val.maxmin?val.maxmin:false;
	var closeBtn = val.closeBtn!=undefined?val.closeBtn:1;
	// return showDialogOpen(url, wth, hth, val.BackE);
	  
	var index =top.window.layer.open({
	    type: 2,
	    shift:7,
	     title:"<div ><i class='fa "+att_icon+"' style='margin-right: 15px;' ></i>"+att_title+"</div>",		 
		// area: ["'"+wth+"px'","'"+hth+"px'"],
	     area: area,
	     maxmin: att_maxmin, 
	    content: url,
		closeBtn:closeBtn,
	    end:val.BackE
	} );  
	 
	var all_win_index_array = $.supper("getProductArray", "all_win_index_array");	
	if($.isEmptyObject(all_win_index_array)){
		all_win_index_array=[];	
	}
	all_win_index_array.push(index);
	 $.supper("setProductArray", {"name":"all_win_index_array", "value":all_win_index_array}); 
	 $.supper("setProductArray", {"name":"all_win_index_ifEnter", "value":enterKeyClose});
	return index;
	
}


/** 删除div对象* */
function sys_deletediv(idname) {
	if (idname) {
		var isdiv = $(top.window).children("#" + idname);
		if (isdiv) {
			isdiv.remove();
		}
	}
}
/**
 * 获取随机数
 */
function generateMixed(n) {
	var chars = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"];
	var res = "";
	for (var i = 0; i < n; i++) {
		var id = Math.ceil(Math.random() * 35);
		res += chars[id];
	}
	return res;
}
/** 加载文件* */
function loadFile(file) {
	if ($.supper.defaults.basepath == null) {
		$.supper.defaults.basepath = GetWebPath("/");
	}
	var files = typeof file == "string" ? [file] : file;
	for (var i = 0; i < files.length; i++) {
		var name = files[i];
		var att = name.split(".");
		var ext = att[att.length - 1].toLowerCase();
		var isCSS = ext == "css";
		var tag = isCSS ? "link" : "script";
		var attr = isCSS ? " type='text/css' rel='stylesheet' " : " language='javascript' type='text/javascript' ";
		var link = (isCSS ? "href" : "src") + "='" + $.supper.defaults.basepath + name + "'";
		if ($(tag + "[" + link + "]").length == 0) {
			document.write("<" + tag + attr + link + "></" + tag + ">");
		}
	}
}
/** 获取AjaxUrl跳转地址* */
function getServicePath(opt, val) {
	var strBuffer = new StringBuffer();
	// var AjaxUrl = $.supper("getbasepath") + "doNewService.htm?1=1";
	var AjaxUrl = $.supper("getbasepath") + "doService.htm?1=1";
	strBuffer.append(AjaxUrl);
	if (typeof val == "string") {
		strBuffer.append("&url=" + getSimplifyUrl(val));
	}
	if (val.service) {
			// 1.处理service字符串
		var attr_service = val.service.split(".");
		if (attr_service.length == 1) {
			val.service = attr_service[0];
		}
		if (attr_service.length > 1) {
			val.service = attr_service[attr_service.length - 2];
			val.func = attr_service[attr_service.length - 1];
		}
		val.service = (val.service).replace(/\s[A-Z]/g, function ($1) {
			return $1.toLowerCase();
		}).replace(/^[A-Z]/, function ($1) {
			return $1.toLowerCase();
		});
		strBuffer.append("&service=" + val.service);
	}
	if (val.func) {
		strBuffer.append("&func=" + val.func);
	}
	if (val.url) {
			// 2.处理url地址
		strBuffer.append("&url=" + getSimplifyUrl(val.url));
	}
	
	if (val.data) {
		strBuffer.append("&" + val.data);
	}
	if(val.commandindex){
		strBuffer.append("&commandindex="+val.commandindex);			
		if(val.param){
			strBuffer.append("&paramvalue="+Obj2str(val.param));
		}
	} 
	return strBuffer.toString();
}

/**
 * 过滤jsp字符串
 */
function getSimplifyUrl(url) {
	if (!url) {
		return null;
	}
	if (url.length < 6) {
		return url;
	}
	
	// 去掉前缀/jsp/
	var prefix = url.substring(0, 5);
	if ("/jsp/" == prefix) {
		url = url.substring(5);
	}
	var prefix = url.substring(0, 4);
	if ("jsp/" == prefix) {
		url = url.substring(4);
	}
	var suffix = url.substring(url.length - 4, 4);
	if (".htm" == suffix) {
	// 去掉后缀.htm
		url = url.substring(0, url.length - 4);
	}
	// 例如/jsp/sys/main/cstree.htm 会处理成sys/main/cstree
	return url;
}
/**
 * 执行ajax调用service中的函数
 */
function doService(opt, val) {
	var AjaxUrl = $.supper("getServicePath", val);
	if (val.native) {
		AjaxUrl = val.url;
	}
	if(val.options&&val.options.type){
		base_default_type=val.options.type;
	}
	var attr_options = {type:(val.type?val.type:base_default_type), url:AjaxUrl, data: val.jsonData!=undefined?val.jsonData:null, dataType:(val.dataType?val.dataType:base_default_dataType), async:(val.async?val.async:base_default_async), success:function (JsonData) {

			// var attr_options = {type:(val.type?val.type:base_default_type), url:AjaxUrl, dataType:(val.dataType?val.dataType:base_default_dataType), async:(val.async?val.async:base_default_async), success:function (JsonData) {
		 if (val.dotry) {
			if (val.BackE) {
				val.BackE(JsonData);
			 
				if(val.ifloading){
					closeLoading();
				}
				return;
			}
		} else {
			dotry(val, JsonData);
			if(val.ifloading){
					closeLoading();
				}
		}
	}, error:function (XMLHttpRequest, textStatus, e) {
		if(val.ifloading){
					closeLoading();
				}
		var serv = val.service ? (val.service + "." + val.func) : val.url;
		sys_alert(serv + "\u65b9\u6cd5\u8c03\u7528\u51fa\u9519", textStatus, "error");
	}};
	
	if(val.paramContent){
		var  paramStr_sys={"paramContent":Obj2str(val.paramContent)};   
	}
	
	if (val.options) {
		if(val.options.data){
			$.extend(val.options.data,paramStr_sys);
		}else {
			val.options.data=paramStr_sys;
		} 
	}else {
		val.options={data:paramStr_sys}
	}
	
	$.extend(attr_options, val.options);
	if(val.ifloading){
		showLoading();
	}
	$.ajax(attr_options);
	
}


/** ajax异常调用* */
function dotry(attr_param, json) {
	try {
		if (json.code == undefined) {
			if (attr_param.BackE) {
				attr_param.BackE(json);
				return;
			}
		} else {
			if (json.code == "0") {
				sys_alert("\u64cd\u4f5c\u4fe1\u606f", json.meg, "error");
				return;
			} else {
				if (attr_param.BackE) {
					attr_param.BackE(json);
					return;
				}
			}
		}
	}
	catch (e) {
		if ($.browser.mozilla) {
			var msg = " \u9519\u8bef\u540d\u79f0:" + e.name + "\n \u51fa\u9519\u4fe1\u606f:  " + e.message + " \n \u51fa\u9519\u884c\u53f7:  " + e.lineNumber + " \n \u51fa\u9519\u6587\u4ef6:  " + e.fileName;
			sys_alert("jsp\u9875\u9762\u51fa\u9519", msg, "error");
			return;
		} else {
			if ($.browser.msie) {
				var msg = "\u9519\u8bef\u540d\u79f0:  " + e.name + " \n \u9519\u8bef\u53f7:  " + (e.number & 65535) + " \n \u9519\u8bef\u4fe1\u606f:  " + e.message;
				sys_alert("jsp\u9875\u9762\u51fa\u9519", msg, "error");
				return;
			}
		}
	}
}
/**
 * 获取全局变量
 */
function getProductArray(val) {
	var prod = null;
	if (top.window.ProductArray) {
		var ifs;
		eval("  ifs=top.window.ProductArray." + val + "?true:false;");
		if (ifs) {
			eval(" prod=top.window.ProductArray." + val + ";");
		} else {
			eval(" top.window.ProductArray." + val + "={};");
			prod = {};
		}
	} else {
		top.window.ProductArray = {};
		eval(" top.window.ProductArray." + val + "={};");
		prod = {};
	}
	return prod;
}
/**
 * 设置全局变量
 */
function setProductArray(val) {
	var value = val.value;
	var name = val.name;
	var prod = getProductArray(name);
	eval("top.window.ProductArray." + name + "=value ;");
}
/**
 * 获取全局变量缓存池里面的全部变量名
 */
function getProductNames() {
	var name = new Array;
	if (top.window.ProductArray) {
		$.each(top.window.ProductArray, function (i, n) {
			name.push(i);
		});
	} else {
		top.window.ProductArray = {};
	}
	return name;
}
/**
 * 获取全部变量缓存池的长度
 */
function getProductSize() {
	var num = 0;
	if (top.window.ProductArray) {
		$.each(top.window.ProductArray, function (i, n) {
			num = num + 1;
		});
	} else {
		top.window.ProductArray = {};
	}
	return num;
}

/**
 * 初始化文件上传控件 参数有 url==上传文件处理类 默认 FileService.upfile data==上传文件处理类需要带入的参数
 * id==上传控件id fieldtype==文件类型 默认“imgFile” codeId==fileCode存放的控件id 默认"fileCode"
 * BackE== 成功后的回调函数 ErrorE==失败的回调函数
 */	
function system_initFile(val){  
var att_url=val.url?val.url: "FileService.addfile";
var att_data=val.data?val.data:"";
var att_id=val.id?val.id:null;
var att_fieldtype=val.fieldtype?val.fieldtype:"imgFile";
var att_filecodeId=val.codeId?val.codeId:null;
if(!att_id){
 	return ;
}  
var att_title=val.title?val.title:"选择本地文件";
var att_val=val.value?val.value:($("#"+att_id).attr("value")); 
var auE=function(data){
		closeLoading();
	   if (data.code == 1) {  
	   			var button_id=att_id;
	   			if(!data.obj){
	   				return ;
	   			}
	   			var fileName=data.obj.fileName; 
	   			 	var codeId="";
	   			if(att_filecodeId){
	   				$("#"+att_filecodeId).val(data.obj.fileCode);
	   			}else{ 
		 	 	   codeId='<input type="hidden"  name="fileCode" id="fileCode" value="'+data.obj.fileCode+'" />';
		 	 	}
		 	 	var  urldiv= '<div id="upMsg_'+button_id+'"><a href="'+data.obj.rootPath+'">'+fileName+'</a><a href="javascript:$.supper(\'deleteUpFile\',{fileCode:\''+data.obj.fileCode+'\',id:\''+button_id+'\',codeId:\''+att_filecodeId+'\'})">&nbsp;&nbsp;<i class="fa fa-bank"></i></a>'+codeId+'</div>';
				$("#"+button_id).after(urldiv);
				$("#up_"+button_id).hide(); 
				$("#kup_"+button_id).hide();
			} else { 
				$.supper("alert",{"title":"操作信息","msg":data.meg});
			}
} 

var auBE=function (data){
	closeLoading();
	val.BackE(data);
	
}
var att_backE= val.BackE?auBE:auE;
var  aeE=function (Str){ 
		$.supper("alert",{"title":"操作信息","msg":Str});
}
var att_aeE=val.ErrorE?val.ErrorE:aeE;

var att_upfile_url = $.supper('getServicePath', {
	"service" : att_url,
	data:att_data
});

KindEditor.ready(function(K) {
	var uploadbutton = K.uploadbutton( {
		button:K('#'+att_id)[0],
		button_title:att_title,
		button_id:att_id,
		fieldName :att_fieldtype,
		url:att_upfile_url,
		afterUpload:att_backE,
		afterError:att_aeE,
		button_type:"singleType"
});
 
uploadbutton.fileBox.change(function(e) { 
		showLoading();
		uploadbutton.submit();
	});
});
if(att_val){ 
   	$.supper("doservice", {"service":"FileService.GetOneFileInfo", "data":"fileCode=" + att_val, "BackE":auE });
	  return ;
}
}	


/**
 * 图片管理，上传图片只保存到服务器，不保存到数据库
 */
function system_initFileWithOutSaveDB(val){ 
var att_url=val.url?val.url: "FileService.upfileWithOutSaveDB";
var att_data=val.data?val.data:"";
var att_id=val.id?val.id:null;
var att_fieldtype=val.fieldtype?val.fieldtype:"imgFile";
var att_filecodeId=val.codeId?val.codeId:null;
if(!att_id){
 	return ;
}  
var att_title=val.title?val.title:"选择本地文件";
var att_val=val.value?val.value:($("#"+att_id).attr("value")); 
var auE=function(data){
	   if (data.code == 1) {  
	   			var button_id=att_id;
	   			if(!data.obj){
	   				return ;
	   			}
	   			var fileName=data.obj.fileName; 
	   			 	var codeId="";
	   			if(att_filecodeId){
	   				$("#"+att_filecodeId).val(data.obj.fileCode);
	   			}else{ 
		 	 	   codeId='<input type="hidden"  name="fileCode" id="fileCode" value="'+data.obj.fileCode+'" />';
		 	 	}
		 	 	var  urldiv= '<div id="upMsg_'+button_id+'"><span>'+fileName+'</span><a href="javascript:$.supper(\'deleteUpFile\',{fileCode:\''+data.obj.fileCode+'\',id:\''+button_id+'\',codeId:\''+att_filecodeId+'\'})">&nbsp;&nbsp;<i class="fa fa-bank"></i></a>'+codeId+'</div>';
				 $("#"+button_id).after(urldiv);
				 $("#up_"+button_id).css("display","none" ); 
			} else { 
				$.supper("alert",{"title":"操作信息","msg":data.meg});
			}
} 

var att_backE=val.BackE?val.BackE:auE;
var  aeE=function (Str){ 
		$.supper("alert",{"title":"操作信息","msg":str});
}
var att_aeE=val.ErrorE?val.ErrorE:aeE;

var att_upfile_url = $.supper('getServicePath', {
	"service" : att_url,
	data:att_data
});

KindEditor.ready(function(K) {
	var uploadbutton = K.uploadbutton( {
		button:K('#'+att_id)[0],
		button_title:att_title,
		button_id:att_id,
		fieldName :att_fieldtype,
		url:att_upfile_url,
		afterUpload:att_backE,
		afterError:att_aeE,
		button_type:"singleType"
});
 
uploadbutton.fileBox.change(function(e) { 
		uploadbutton.submit();
	});
});
if(att_val){ 
   	$.supper("doservice", {"service":"FileService.GetOneFileInfo", "data":"fileCode=" + att_val, "BackE":auE });
	  return ;
}
}


function system_rewFile(val){  
	var att_filecodeId=val.codeId?val.codeId:null;
	var att_id=val.id?val.id:null;
	var auE=function(data){
		closeLoading();
	   if (data.code == 1) {  
	   			var button_id=att_id;
	   			if(!data.obj){
	   				return ;
	   			}
	   			var fileName=data.obj.fileName; 
	   			 	var codeId="";
	   			if(att_filecodeId){
	   				$("#"+att_filecodeId).val(data.obj.fileCode);
	   			}else{ 
		 	 	   codeId='<input type="hidden"  name="fileCode" id="fileCode" value="'+data.obj.fileCode+'" />';
		 	 	}
		 	 	var  urldiv= '<div id="upMsg_'+button_id+'"><a href="'+data.obj.rootPath+'">'+fileName+'</a><a href="javascript:$.supper(\'deleteUpFile\',{fileCode:\''+data.obj.fileCode+'\',id:\''+button_id+'\',codeId:\''+att_filecodeId+'\'})">&nbsp;&nbsp;<i class="fa fa-bank"></i></a>'+codeId+'</div>';
				$("#"+button_id).after(urldiv);
				$("#up_"+button_id).hide(); 
				$("#kup_"+button_id).hide();
			} else { 
				$.supper("alert",{"title":"操作信息","msg":data.meg});
			}
   } 
	
	
	var att_val=val.value?val.value:($("#"+att_id).attr("value")); 
	if(att_val){ 
	   	$.supper("doservice", {"service":"FileService.GetOneFileInfo", "data":"fileCode=" + att_val, "BackE":auE });
		  return ;
	}
}

/**
 * 删除文件
 */
function system_deleteFile(val) {
if(val.fileCode==null){
 		$("#up_"+val.id).show();
 		$("#kup_"+val.id).show();
    	 $("#upMsg_"+val.id).remove();
    	 if (val.codeId){
    	 	$("#"+val.codeId).val("");
    	 }
    	 return ;
}
$.supper("doservice", {"service":"FileService.deletefile", "data":"fileCode=" + val.fileCode, "BackE":function (jsondata) {
	if(val.BackE){
		val.BackE();
	}else{  
	if (jsondata.code == "1") {
		$("#up_"+val.id).show();
    	 $("#kup_"+val.id).show();
    	 $("#upMsg_"+val.id).remove();
    	 if (val.codeId){
    	 	$("#"+val.codeId).val("");
    	 }
	}
	}
}});
}
/**
 * 分页
 */
var paginationCurrentPage = {};
var paginationLimit = {};
var paginationBackE = {};
var paginationService = {};
var commService;
var initPagination = function(val) {
	var totalCount=val.totalCount?val.totalCount: 0;
	var currentPage=(val.currentPage && val.currentPage > 0)? val.currentPage-1: 0;
	var att_id=val.id?val.id:null;
	var att_url=val.url?val.url: "";
	paginationService[att_id]=val.service?val.service: "";
	commService=val.commandindex?val.commandindex:"";
	var att_data=val.data?val.data: "";
	if(paginationCurrentPage[att_id] == undefined || paginationCurrentPage[att_id] == null){
		paginationCurrentPage[att_id] = {};
	}
	if(paginationLimit[att_id] == undefined || paginationLimit[att_id] == null){
		paginationLimit[att_id] = {};
	}
	paginationCurrentPage[att_id].page = 1;
	paginationLimit[att_id].limit=val.limit?val.limit: 10;
	paginationLimit[att_id].limits = val.limits == undefined ? [10, 20, 30, 40] : val.limits;
	var allPage = totalCount != 0 ? totalCount/paginationLimit[att_id].limit:1;
	paginationBackE[att_id] = val.BackE ? val.BackE :null;
	if(!att_id){
		 return ;
	}
	//console.log('aaa', att_id);
	if(val.isAjax != null && val.isAjax == "1"){
		var list =[];
		$.supper("doservice", {"service":paginationService[att_id], "data":att_data+"&page=1&limit="+paginationLimit[att_id].limit, "BackE":function (jsondata) {
			totalCount = jsondata.totalCount?jsondata.totalCount: 0;
			allPage = totalCount != 0 ? totalCount/paginationLimit[att_id].limit:1;
				paginationLimit[att_id].limit = paginationLimit[att_id].limit;
				paginationCurrentPage[att_id].page = 1;
				paginationBackE[att_id].call(this,jsondata.items, totalCount);
	 		$("#"+att_id).pagination(totalCount, {
				num_edge_entries: 2, // 边缘页数
				num_display_entries: 4, // 主体页数
				callback: function(page_index, per_page, jq){
					page_index ++;
					paginationLimit[att_id].limit = (per_page == undefined || per_page == 0) ? paginationLimit[att_id].limit : per_page;
					paginationCurrentPage[att_id].page = page_index;
					$.supper("doservice", {"service":paginationService[att_id], "data":att_data + "&limit="+paginationLimit[att_id].limit+"&page="+page_index, "BackE":function (jsondata) {
						paginationBackE[att_id].call(this,jsondata.items, totalCount);
					}});
					
				},
				items_per_pages: paginationLimit[att_id].limits, // 可供选择的每页显示数目
				per_page: paginationLimit[att_id].limit,
				items_per_page: 1, // 每页显示1项
				prev_text: "<",
				next_text: ">"
			});
	 	}});
	}else{
		
		// 创建分页
		$("#"+att_id).pagination(allPage, {
			num_edge_entries: 2, // 边缘页数
			num_display_entries: 4, // 主体页数
			callback: function(page_index, jq){
				if(page_index == currentPage)
					return false;
				page_index ++;
				var AjaxUrl = "";
				if(att_data != "")
					att_data += "&page="+page_index+"&limit="+paginationLimit[att_id].limit;
				else
					att_data += "page="+page_index+"&limit="+paginationLimit[att_id].limit;
				if(paginationService[att_id] != ""){
					AjaxUrl = $.supper("getServicePath", {"service":paginationService[att_id],"data":att_data ,url:att_url});
				}else{
					AjaxUrl = $.supper("getServicePath", {"data":att_data ,url:att_url});
				}
				window.location.href = AjaxUrl;
			},
			items_per_pages: paginationLimit[att_id].limits, // 可供选择的每页显示数目
			items_per_page: 1, // 每页显示1项
			current_page:currentPage,
			prev_text: "上一页",
			next_text: "下一页"
		});
	}
	
	
};

function paginationReload(val) {
var att_data=val.data?val.data: "";
	var att_id=val.id?val.id:null;
$.supper("doservice", {"service":paginationService[att_id], "data":att_data + "&limit="+paginationLimit[att_id].limit+"&page="+paginationCurrentPage[att_id].page, "BackE":function (jsondata) {
		paginationBackE[att_id].call(this,jsondata.items);
}});
};
 

function _all_system_initSysParam() {
	  var paramList=[]; 
	  $.supper("setProductArray", {"name":"_system_param_ProductArray", "value":paramList});  
		$.supper("doservice", {"service":"SysParameterService.getTreeNodeListByParentCode",data:'parentCode=system', "BackE":function (jsondata) {
			if (jsondata.code == "1"&&jsondata.obj) { 
				 var  list_back=jsondata.obj ;
				  $.each(list_back, function (index_i, index_n) { 
						  var paramList01=[]; 
						  if(index_n.children){
							  $.each(index_n.children, function (index_i01, index_n01) {
								  var row01={"datatype":index_n01.datatype,"code":index_n01.type,"id":index_n01.id,"name":index_n01.name,"value":index_n01.id,"text":index_n01.name,"attributes":index_n01.attributes };
								  paramList01.push(row01);
							  });
						  }
						  var row={"children":paramList01,"datatype":index_n.datatype,"code":index_n.type,"id":index_n.id,"name":index_n.name,"value":index_n.id,"text":index_n.name,"attributes":index_n.attributes };
						  paramList.push(row);
					}); 
				  $.supper("setProductArray", {"name":"_system_param_ProductArray", "value":paramList});  
			}
		}}); 
		 
}
/**
 * 获取下拉框内容
 * @param param
 * @returns {[]}
 * @private
 */
function _getSelectService(param) {
    var lb_obj = {};
    if(param === undefined || param === null){
        return lb_obj;
    }
    if (param.serviceName !== undefined && param.serviceName !== null && param.serviceName !== "") {
        $.supper("doservice", {
            "service": param.serviceName,
            data: param.data,
            "options": {async: false},
            "BackE": function (jsondata) {
                lb_obj = jsondata;
            }
        });
    }
    return lb_obj;
}
/**
 * 获取系统编码
 * @param att_code  编码编号
 */
function _system_getsysParam(att_code) {
    var lb_obj = [];
    if (att_code) {
        var ifnull = 1;
        var att_array = $.supper("getProductArray", "_system_param_ProductArray");
        $.each(att_array,
        function(index_i, index_n) {
            if (index_n.code == att_code) {
                lb_obj = index_n.children;
                ifnull = 2;
                return false;
            }
        });
        if (ifnull == 1) {
            $.supper("doservice", {
                "service": "SysParameterService.getTreeNodeListByParentCode",
                data: "parentCode=" + att_code,
                "options":{async:false},
                "BackE": function(jsondata) {
                    if (jsondata.code == "1" && jsondata.obj) {
                        var list_back = jsondata.obj;
                        $.each(list_back,
                        function(index_i01, index_n01) {
                            var row01 = {
                                "datatype": index_n01.datatype,
                                "code": index_n01.type,
                                "id": index_n01.id,
                                "name": index_n01.name,
                                "value": index_n01.id,
                                "text": index_n01.name,
                                "attributes": index_n01.attributes
                            };
                            lb_obj.push(row01);
                        }); 
                    }
                }
            	});
            }
     
      } 
    return lb_obj;
}

/*******************************************************************************
 * 自定义插件xun对象
 * 
 * @param options
 *            参数对象一般为String类型的值,如果为json对象表示传递进来的配置对象
 * @param param
 *            参数对象‘json’字符串，或者数字和字符
 */
$.supper = function (options, param) {
	options = options || {};
	if (typeof options == "string") {
		switch (options) {
		  case "path":
			return $.supper.defaults.basepath + param;
		  case "setjspath":
			return $.supper.defaults.jspath = param;
		  case "getjspath":
			return $.supper.defaults.jspath;
		  case "setbasepath":
			/**
			 * supper默认配置对象
			 */
			$.supper.defaults = {xurl:base_jsfile, basepath:null};
			if (param) {
				$.supper.defaults.jspath = param + "js/";
				$.supper.defaults.basepath = param;
			} else {
				$.supper.defaults.jspath = GetWebPath("/") + "js/";
				$.supper.defaults.basepath = GetWebPath("/");
			}
			loadFile(base_jsfile);
			// 设置ajax
			$.ajaxSetup({beforeSend:function (XHR) {
					// 对Ajax返回的原始数据进行预处理
				this.url = this.url + "&jsonSysDateId=" + generateMixed(10);
			}});
			return;
		  case "getbasepath":
			return $.supper.defaults.basepath;
		  case "generate":
			return generateMixed(param);
		  case "loadFile":
			return loadFile(param, null);
		  case "getServicePath":
			return getServicePath(this, param);
		  case "doservice":
			return doService(this, param);
		  case "alert":
			return sys_alert(param.title, param.msg, param.icon, param.BackE);
		  case "confirm":
			  return sys_confirm(param);
		  case "initProductArray":
		      return  _all_system_initSysParam(param);
		  case "getProductArray":
			return getProductArray(param);
		  case "setProductArray":
			return setProductArray(param);
		  case "getProductNames":
			return getProductNames();
		  case "getProductSize":
			return getProductSize();
		  case "containsKey":
			return sys_containsKey(param);
		  case "getdiv":
			return GetSysDiv(param);
		  case "deletediv":
			return sys_deletediv(param);
		  case "showWin":
			return sys_ShowWin(param);
		  case "closeWin":
			return closeDialog();
		  case "getStrForJson":
			return Obj2str(param);
		  case "getIframe":
			return sys_getIframe(this, param);
		  case "login":
			return system_login(this, param);
		  case "getParam":
			return system_getRequest(param);
		  case "getsysParam":
				return _system_getsysParam(param);
            case "doselectService":
                return _getSelectService(param);
		  case "deleteUpFile":
			return system_deleteFile(param);
		  case "rewFile":
		   return  system_rewFile(param);
		  case "initUpFile":
			return system_initFile(param);
		  case "initUpFileWithOutSaveDB":
			return system_initFileWithOutSaveDB(param);
	      case "initPagination":
			return initPagination(param);
		 case "paginationReload":
			return paginationReload(param);
		 case "showReceptionWin":
			return showReceptionWin(param);
		 case "initForm":
				return assignValue(param);
		 case "showTtemWin":
				return showTtemWin(param);
			case "closeTtemWin":
				return closeTtemWin(param);
		 case "initSelect":
				return initSelect(param);
		 case "setDownKey":
				return sysDownKey(param);
		}
	}


	function sysDownKey(){

		if(window.focusE){
	    		focusE();
	    }

	    $(document).on('keydown', function(e){
			if (e.keyCode == 13) {
				var all_win_index_ifEnter = $.supper("getProductArray", "all_win_index_ifEnter");
				//不知为何，找不到OnEnterDown方法，永远也无法进入自定义
				// if (window.OnEnterDown && all_win_index_ifEnter == true) {
				// 	all_win_index_ifEnter = OnEnterDown();
				// 	$.supper("setProductArray", {"name": "all_win_index_ifEnter", "value": all_win_index_ifEnter});
				// 	return false;
				// }
				if (all_win_index_ifEnter == true) {
					return false;
				}
				top.onkeydowns(e);
				return false;
			} else if (e.keyCode == 27) {
				top.onkeydowns(e);
				return false;
			}
		});
	}




	function initSelect(param){
		if(param.vid != null && param.vid !=""&& param.code !=null&& param.code !=""){
			var str = "<option value=\"\"></option>";
			$.supper("doservice", {"service":"SysParameterService.getParameterListByParentCode","data":"parentCode="+param.code,"BackE":function (jsondata) {
				for(var i =0;i < jsondata.length ; i++){
					str += "<option value='"+jsondata[i].paramValue+"'>"+jsondata[i].paramName+"</optin>";
				}
				$("#"+param.vid).html(str);
				if(param.val != null && param.val != "")
					$("#"+param.vid).val(param.val);
		 	}});
		}
	}
	function showTtemWin(param){
		if(param.url != null && param.url !=""){
			var title = param.title?param.title:"";
			parent.window.addMenuItem(param.url,title);
		}

	}
	function closeTtemWin(param) {
		if(param.url != null && param.url !=""){
			var title = param.title?param.title:"";
			parent.window.closeMenuItem(param.url);
		}
	}
	/**
	 * 返回对象（相对于初始化）
	 */
	return this.each(function () {
		var state = $.data(this, "supper");
		if (!state) {
			// 如果不存在配置对象，就加载配置对象
			var opts = $.extend({}, $.supper.defaults, options);
			$.data(this, "supper", {options:opts});
		} else {
			// 如果已经存在配置对象
			$.extend($.data(this, "supper").options, options || {});
		}
	});
};
})(jQuery);


function sys_confirm(val){
if(!val)return ;
var att_content=val.msg?val.msg:"";
var att_title=val.title?val.title:"提示";

top.window.layer.confirm(att_content, {icon: 3, title:att_title}, function(index){
	   if(val.yesE ){
		   val.yesE();
	   }
	   if(val.BackE){
		   val.BackE();
	   }
	   top.window.layer.close(index);
	},function(index){
		if(val.noE){
			val.noE();
		}
		 if(val.BackE){
			   val.BackE();
		   }
	});

}

function ifcheach(att_type){
var lb=1;
if("radio"==att_type) return 2;
if("checkbox"==att_type) return 3;
return lb;

}


function assignValue(data){
var objInfoFormid= data.id;
var Arrary_state=data.colstates;
var val=data.obj;
var setReadOnly=false;
if(!objInfoFormid){
	return ;
}
$("#"+objInfoFormid+" input").each(function(i,v){
	var att_state=$(v).attr("colstates");
	var att_type=$(v).attr("type");
    if( att_type&&(att_type=="hidden")){
    	setReadOnly=false;
    }else {
  		 setReadOnly  =data.setReadOnly?data.setReadOnly:false;
    }
	if(setReadOnly ){
		if(att_state&&Arrary_state&&colstates(att_state,Arrary_state)){
			$(v).removeAttr("readonly");
			$(v).removeAttr("disabled");
		}else {
			$(v).attr("readonly","readonly");
			$(v).attr("disabled","disabled");
		}
	}
	if(val){
	  var att_name=  $(v).attr("name") ;
	  eval("var vals=val."+att_name+";")
	  if(vals){
		  if(ifcheach(att_type)==2){
			  // $("input[@type=radio][name="+att_name+"][@value="+vals
				// +"]").attr("checked",true);
			  var att_che=$("input[name='"+att_name+"']");
			  for(var i=0;i<att_che.length;i++){
				  var row=$(att_che[i]);
				  if(row.val()==vals ){
					  row.attr('checked','true');
				  }else {
					  row.removeAttr("checked");
				  }
			  }
		  }else if(ifcheach(att_type)==1){
			  $(v).val(vals);
		  }
	  }
	}
 });


$("#"+objInfoFormid+" textarea").each(function(i,v){
    setReadOnly  =data.setReadOnly?data.setReadOnly:false;
	if(setReadOnly ){
		var att_state=$(v).attr("colstates");
		if(att_state&&Arrary_state&&colstates(att_state,Arrary_state)){
			$(v).removeAttr("readonly");
			$(v).removeAttr("disabled");
		}else {
			$(v).attr("readonly","readonly");
			$(v).attr("disabled","disabled");
		}
	}
	if(val){
 		  var att_name=  $(v).attr("name") ;
 		  eval("var vals=val."+att_name+";") ;
 		  if(vals){
 			  $(v).text(vals);
 			  $(v).val(vals);
 		  }
	}
 });
}


function colstates(val,stats){
var arrayT=	stats.split(",");
	for(var i=0;i<arrayT.length;i++){
		if(arrayT[i]==val){
			return true;
		}
	}
return false;
}


function system_getRequest(strParame) {
var args = new Object();
var query = location.search.substring(1);
var pairs = query.split("&"); // Break at ampersand
for (var i = 0; i < pairs.length; i++) {
	var pos = pairs[i].indexOf("=");
	if (pos == -1) {
		continue;
	}
	var argname = pairs[i].substring(0, pos);
	var value = pairs[i].substring(pos + 1);
	value = decodeURIComponent(value);
	args[argname] = value;
}
return args[strParame];
}

function StringBuffer() {
this.string = new Array; // 创建Array对象存储字符串
}
StringBuffer.prototype.append = function (str) { // 把参数str附加到字符串数组
if (this.string) {
	this.string.push(str);
}
};
StringBuffer.prototype.toString = function () { // 用join方法返回真正的字符串
if (this.string) {
	return this.string.join("");
} else {
	return "";
}
};
/**
 * 获取系统div对象,用于显示弹出窗口和面板
 *
 * @param idname
 *            页面中div的id号
 * @return 返回div的dom对象
 */
function GetSysDiv(val) {
var idname;
var xiframeId;
var iframe;
if (typeof val == "string") {
	idname = val;
} else {
	idname = val.id;
	iframe = val.iframe;
	xiframeId = val.iframeId ? val.iframeId : "iframe_" + val.objectId;
}
if (top == self) {
	// 是顶级窗口
	if ($("#" + idname)[0]) {
		// 存在");
		return $("#" + idname);
	} else {
		// 如果不存在");
		if (xiframeId) {
			// 需要从iframe中查找
			return $("#" + xiframeId).contents().find("#" + idname);
		}
		// 如果不知道iframeId从iframe队列中找
		if (iframe && !xiframeId) {
			var att_iframe = $.supper("getProductArray", "iframe_default");
			$.each(att_iframe, function (i, n) {
				if ($("#" + n)[0]) {
					if ($("#" + n).contents().find("#" + idname)) {
						return $("#" + n).contents().find("#" + idname);
					}
				}
			});
		}
		var mm1tr = $("<div id='" + idname + "'></div>");
		$(this.document.body).append(mm1tr);
		return mm1tr;
	}
} else {
	// 不是顶级窗口
	return top.GetSysDiv(val);
}
}
function showDialogOpen(url, wth, hth, onClosed) {
if (top == self) {
					// 是顶级窗口
	// var id = "#inline1";
	// $("#win_frame").attr("src", url);
	// //$("#iftr1").attr("width", wth);
	// $("#iftr1").attr("height", hth);
	fbox = $.fancybox({hideOnOverlayClick:false,
	 modal:true, hideOnOverlayClick:true, centerOnScroll:true,
	 width:wth, height:hth, autoDimensions:true, type:"iframe", href:url, onClosed:onClosed});
} else {
						// 不是顶级窗口
	return top.showDialogOpen(url, wth, hth, onClosed);
}
return fbox;
}


function closeDialog() {
//var index = top.window.layer.getFrameIndex(window.name); // 获取窗口索引
 // window.parent.closeDialog();
//top.window.parent.layer.close(index);


/*
 * if (top == self) { // 是顶级窗口 $.fancybox.close(); } else { // 不是顶级窗口 return
 * top.closeDialog(); }
 */
	var all_win_index_array = $.supper("getProductArray", "all_win_index_array");
	if($.isEmptyObject(all_win_index_array)){
		var index = top.window.layer.getFrameIndex(window.name); //获取窗口索引
		top.window.parent.layer.close(index);
		return ;
	}
	var index = all_win_index_array.pop();
	top.window.parent.layer.close(index);
	 $.supper("setProductArray", {"name":"all_win_index_array", "value":all_win_index_array});
return;
}
/*
 * function sys_alert(title, msg, icon, BackE) { if (top == self) { // 是顶级窗口 var
 * att_title = title ? title : "\u63d0\u793a\u6846"; var att_msg = msg ? msg :
 * ""; var att_icon = icon ? icon : "fa fa-info"; $.alert({title:att_title,
 * content:att_msg, confirmButton:"\u786e\u5b9a", confirmButtonClass:"btn
 * btn-success", icon:att_icon, animation:"zoom", confirm:BackE}); } else { //
 * 不是顶级窗口 return top.sys_alert(title, msg, icon, BackE); } }
 */

function sys_alert(title, msg, icon, BackE) {
var att_title = title ? title : "\u63d0\u793a\u6846";
var att_msg = msg ? msg : "";
var att_icon = icon ? "fa "+icon : "fa  fa-info";
var index =top.window.layer.open({
    type: 1,
    shift:7,
    top:300,
    title:"<div  style='font-size: 15px;'><i class='fa fa-comments'  style='margin-right:15px;' ></i>"+att_title+"</div>",
    area: ['380px', '200px'],
    maxmin: false,
    scrollbar: false,
    content: '<div id="systemAlert" class="ibox float-e-margins"> <div class="ibox-content" id="AlertSBI"> <h2 style="font-size: 22px"><i class="'+att_icon+'" style="margin-top: 15px;margin-left: 15px;margin-right: 15px;color:#003399;"></i><div style="font-size: 18px;display: inline;margin-top: 60px;">'+ att_msg+'</div></h2></div></div>',
   	icon: 1,
    btn: ['关闭'],end:function(index, layero){
    	$.supper("setProductArray", {"name":"all_win_index_ifEnter", "value":true});
    	if(BackE) 		BackE();
    	if(top)   {
    		var all_win_index_array = $.supper("getProductArray", "all_win_index_array");
    		if($.isEmptyObject(all_win_index_array)){
    			var index = top.window.layer.getFrameIndex(window.name); //获取窗口索引
    			top.window.parent.layer.close(index);
    			return ;
    		}
    		var indexss = all_win_index_array.pop();
    		top.window.parent.layer.close(indexss);
    		 $.supper("setProductArray", {"name":"all_win_index_array", "value":all_win_index_array});
    	}
    }
} );

GetSysDiv("systemAlert").parent('.layui-layer-content').css("overflow","hidden") ;
var all_win_index_array = $.supper("getProductArray", "all_win_index_array");
if($.isEmptyObject(all_win_index_array)){
	all_win_index_array=[];
}
all_win_index_array.push(index);
 $.supper("setProductArray", {"name":"all_win_index_array", "value":all_win_index_array});
return index;




}

function showLoading() {

var inntHtml = '';
inntHtml += '<div id="loading-mask"  class="u-shade" style="z-index:9098910159999;display:block ">';
inntHtml += '<img src="'+GetWebPath("/img/loading.gif")+'" alt=""></div>';

if (top == self) {
	$(this.document.body).append(inntHtml);
	$('#loading-mask').fadeIn('fast');
	// 是顶级窗口
} else {
			// 不是顶级窗口
	return top.showLoading();
}
/*
 * var $body = $(window.parent.document.body); if (top == self) {
 * $body=$(window.document.body); $($body).after(inntHtml); }
 */

}
function closeLoading(){
 if (top == self) {
	 	$('#loading-mask').fadeOut('fast');
		  $('#loading-mask').remove();
		// 是顶级窗口
	} else {
				// 不是顶级窗口
		return top.closeLoading();
	}

}
var $swwrap;
var $swwrapClose;
function showReceptionWin(val){
$swwrap = $('.g-swipewrap');
var attr_url = val.url? val.url:"";
var winWidth = val.width? val.width:620;
$swwrapClose = val.BackE? val.BackE :null;
if(attr_url == "")
	return ;
$("#open_iframe_url").attr('src',attr_url);
$swwrap.animate({'width': winWidth + 'px'}, 300);
}

function swwrapClose(){
if($swwrap == null)
	return;
else{
	$swwrap.animate({'width': 0 + 'px'}, 300);
	if($swwrapClose != null && $swwrapClose != "")
		$swwrapClose.call(this);
    return false;
}
}
function closeReceptionWin(){
window.parent.swwrapClose();
}

function toDecimal2(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
      return "--";
    }
    var f = Math.round(x*100)/100;
    var s = f.toString();
    var rs = s.indexOf('.');
    if (rs < 0) {
      rs = s.length;
      s += '.';
    }
    while (s.length <= rs + 2) {
      s += '0';
    }
    return s;
  }


$.fn.serializeObject = function()
{
var o = {};
var a = this.serializeArray();
$.each(a, function() {
   if (o[this.name]) {
       if (!o[this.name].push) {
           o[this.name] = [o[this.name]];
       }
       o[this.name].push(this.value || '');
   } else {
       o[this.name] = this.value || '';
   }
});
return o;
};

(function(jQuery){

	if(jQuery.browser) return;

	jQuery.browser = {};
	jQuery.browser.mozilla = false;
	jQuery.browser.webkit = false;
	jQuery.browser.opera = false;
	jQuery.browser.msie = false;

	var nAgt = navigator.userAgent;
	jQuery.browser.name = navigator.appName;
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion);
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);
	var nameOffset,verOffset,ix;

	// In Opera, the true version is after "Opera" or after "Version"
	if ((verOffset=nAgt.indexOf("Opera"))!=-1) {
	jQuery.browser.opera = true;
	jQuery.browser.name = "Opera";
	jQuery.browser.fullVersion = nAgt.substring(verOffset+6);
	if ((verOffset=nAgt.indexOf("Version"))!=-1)
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
	}
	// In MSIE, the true version is after "MSIE" in userAgent
	else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) {
	jQuery.browser.msie = true;
	jQuery.browser.name = "Microsoft Internet Explorer";
	jQuery.browser.fullVersion = nAgt.substring(verOffset+5);
	}
	// In Chrome, the true version is after "Chrome"
	else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) {
	jQuery.browser.webkit = true;
	jQuery.browser.name = "Chrome";
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7);
	}
	// In Safari, the true version is after "Safari" or after "Version"
	else if ((verOffset=nAgt.indexOf("Safari"))!=-1) {
	jQuery.browser.webkit = true;
	jQuery.browser.name = "Safari";
	jQuery.browser.fullVersion = nAgt.substring(verOffset+7);
	if ((verOffset=nAgt.indexOf("Version"))!=-1)
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
	}
	// In Firefox, the true version is after "Firefox"
	else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) {
	jQuery.browser.mozilla = true;
	jQuery.browser.name = "Firefox";
	jQuery.browser.fullVersion = nAgt.substring(verOffset+8);
	}
	// In most other browsers, "name/version" is at the end of userAgent
	else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) <
	(verOffset=nAgt.lastIndexOf('/')) )
	{
	jQuery.browser.name = nAgt.substring(nameOffset,verOffset);
	jQuery.browser.fullVersion = nAgt.substring(verOffset+1);
	if (jQuery.browser.name.toLowerCase()==jQuery.browser.name.toUpperCase()) {
	jQuery.browser.name = navigator.appName;
	}
	}
	// trim the fullVersion string at semicolon/space if present
	if ((ix=jQuery.browser.fullVersion.indexOf(";"))!=-1)
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);
	if ((ix=jQuery.browser.fullVersion.indexOf(" "))!=-1)
	jQuery.browser.fullVersion=jQuery.browser.fullVersion.substring(0,ix);

	jQuery.browser.majorVersion = parseInt(''+jQuery.browser.fullVersion,10);
	if (isNaN(jQuery.browser.majorVersion)) {
	jQuery.browser.fullVersion = ''+parseFloat(navigator.appVersion);
	jQuery.browser.majorVersion = parseInt(navigator.appVersion,10);
	}
	jQuery.browser.version = jQuery.browser.majorVersion;
	})(jQuery);

