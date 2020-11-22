var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdInventoryService.getPagerViewObject";
var _all_deleteAction = "MdInventoryService.deleteObject";
var _all_deleteAllAction = "MdInventoryService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/storage/editInventoryWanning.jsp";
var _all_table_Id = "wiId";
var _all_edit_icon = "bolt";
var _all_edit_title = "设置库存信息";
var _all_edit_width = 800;
var _all_edit_height = 500;
var _all_datagrid_height ;

var _searchForm={ 
		lineNum:2,
		columnNum:2,
		control:[],
		groupTag:[], 
		hiddenitems:[
		],
		items:[ 
			   {title:"物料名称",name:"matName",type:"text", placeholder:"输入物料名称,回车查询"},
			   {title:"规格",name:"mmfName",type:"text",placeholder:"输入规格,回车查询"},
			   {title:"是否预警",name:"safeIsNot",type:"select",data:[{id:1, name: '是'}, {id: 2, name: '否'}]},
			   {title:"库存数量筛选", name:"InventoryNumber", type:'userdefined', renderer: InventoryNumberInput},
		]
	};
function InventoryNumberInput(){
	var InventoryNumberStart = '<input type="text" id="InventoryStart" name="InventoryStart"  class="form-control" style="width: 48%;display: inline"/> - ';
	var InventoryNumberEnd = '<input type="text" id="InventoryEnd" name="InventoryEnd" class="form-control" style="width: 48%;display: inline"/>';
	return InventoryNumberStart + InventoryNumberEnd;
}
var _Toolbar={
		toolBarId:"tools_but",
		items:[
				//{title:"",id:"safetyEwBtn",colourStyle:"default"},
				{title:"",id:"safetyEwBtn",icon:"qqq", colourStyle:"default",rounded:true,clickE:chickSafety},
				// {title:"&nbsp查询&nbsp",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search},
		        {title:"全部导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all},
		        {title:"&nbsp导入&nbsp",id:"win_but_export1",icon:"cloud-upload", colourStyle:"info",rounded:true,clickE:imp_all}
		       ] 
	};

var _DataGrid={
			cols: [  
			       	{title:'物料名称', sortable: true, name:'matName', width:100, align: 'center'},
			       	{title:'规格', sortable: true, name:'mmfName', width:60, align: 'center'},
			    	{title:'库存数量', sortable: true, name:'baseNumber', width:50, align: 'center',sortable:true,renderer:quantityControl},
			    	{title:'预警', sortable: true, name:'warningShu', width:40, align: 'center',renderer:warningControl},
			    	{title:'最高', sortable: true, name:'maxShu', width:60, align: 'center',renderer:warningControl},
			    	{title:'到货', sortable: true, name:'minDay', width:40, align: 'center',renderer:warningControl},
			    	{title:'单位', sortable: true, name:'basicUnit', width:40, align: 'center'},
			       	{title:'操作' ,name:'control',width:150,  align:'center',renderer:control }
	           ]
	, remoteSort: false
			,name:'inventoryListGrid'
			,height:_all_datagrid_height
			,gridtype:'2'
			,nowrap:true 
			, url:getMMGridUrl() 
	        ,mmPaginatorOpt:_all_win_datagrid_pg
	}


/**
 * 页面初始化函数
 */
$(function(){
  var warning =	$.supper("getProductArray", "selOutOrderInfo");
  var needWarn = false;
  if (warning !== undefined && warning !== null && warning !== '') {
  	if (warning.warning === undefined || warning.warning === null || warning.warning === ''){
  		needWarn = false;
	}else {
		$.supper("setProductArray", {"name": "selOutOrderInfo", "value": {warning: null}});
		needWarn = true;
	}
  }
  _all_win_searchForm.xform('createForm',_searchForm); 
  
  _all_div_hidden.xform('createhidden',_searchForm.hiddenitems);

	if (needWarn === true){
		$('#safeIsNot').val(1);
		needWarn = false;
	} else {
		$('#safeIsNot').val('');
	}
  
  _all_win_tools_but.xtoolbar('create',_Toolbar); 
  
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-95; 
  
  _DataGrid.height=_all_datagrid_height;
  
  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid); 
  
  main_search();
  
//设置按钮宽度与图标随文字居中
  $("#win_but_search").css("width","95px");
  $("#win_but_search").css("vertical-align","middle");
  $("#win_but_export").css("width","95px");
  $("#win_but_export").css("vertical-align","middle");
  $("#win_but_export1").css("width","95px");
  $("#win_but_export1").css("vertical-align","middle");

	$("#safetyEwBtn").html($("#safetyEw").html())
	$("#safetyEwBtn").css("width","120px");
	$("#safetyEwBtn").css("vertical-align","middle");


  //设置搜索框字体大小
  if($("#matName").val() !=null){
	  $("#matName").css("font-size","13px");
  }
//设置搜索框字体大小
  if($("#mmfName").val() !=null){
	  $("#mmfName").css("font-size","13px");
  }
  //查询连接回车
  $("#matName").on('keydown', function(){
	  if (event.keyCode==13) {
		  $("#win_but_search").trigger("click");
	  }
});
  $("#mmfName").on('keydown', function(){
	  if (event.keyCode==13) {
		  $("#win_but_search").trigger("click");
	  }
});
 // $("#safetyEw1").html($("#safetyEw"));
	// $("#safetyEw").blur(function(){
	// 	$("#safetyhide").hide();
	// });
});  

function quantityControl(val,item,rowIndex){
	var str ="<a onclick=\"viewExtend('"+item.wiId+"')\">";
   if(item.warningShu != null && item.warningShu > 0 && val < item.warningShu){
	   str+= "<lable style=\"color:red\">"+val+"</lable>";
   } else if(item.maxShu != null && item.maxShu > 0 && val > item.maxShu){
		str+= "<lable style=\"color:red\">"+val+"</lable>";
	}
   else
	   str+= val;
   str += "</a>";
   return str;
}
function warningControl(val,item,rowIndex){
	if(val ==null || val==0)
		return "-";
	return val;
}
function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"main_edit('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>设置</a>&nbsp;&nbsp;";
	str += "<a onclick=\"viewMdEnterMxList('"+  item.wiId+"')\"  class='btn btn-primary  btn-xs'>入库列表</a>&nbsp;&nbsp;";
	str += "<a onclick=\"viewMdOutMxList('"+  item.wiId+"')\"  class='btn btn-info  btn-xs'>出库列表</a>&nbsp;&nbsp;";
	if(item.quantity=='0')
		str += "<a onclick=\"main_delete('"+  idvalue+"')\"  class='btn btn-danger  btn-xs'>删除</a> "; 
	else
		str += "<a class='btn btn-default  btn-xs' disabled=true>删除</a> "; 
   return str;
}
function controlInfo(val,item,rowIndex){
	var str = "";
	if(item.matPurchase=='1')
		str += "<a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+item.wmsMiId2+"\" target=\"_blank\">"+item.matName+"</a> ";  
	else
		str = item.matName;
	return str;
}
/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl(){
	var data = _all_win_searchForm.serialize();
	var InventoryStart=$("#InventoryStart").val();
	var InventoryEnd=$("#InventoryEnd").val();
	var safeIsNot=$("#safeIsNot").val();
	data+="&"+"InventoryStart="+InventoryStart+"&"+"InventoryEnd="+InventoryEnd+"&"+"safeIsNot="+safeIsNot;
	var att_url= $.supper("getServicePath", {"service":_all_queryAction, "data":data}); 
	return att_url;
}
/**
 * 对主表进行查询（刷新）操作
 */
function main_search(){
	_all_win_datagrid.opts.url = getMMGridUrl();
	_all_win_datagrid.load();
}
/**
 * 添加记录操作事件
 */
function main_add(){ 
		var att_url= $.supper("getServicePath", {url:_all_win_url_edit});
	//	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search}); 
		$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
} 
/**
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function main_edit(id){
	eval("var data= '"+_all_table_Id+"="+id+"'");
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:700, BackE:main_search});
}  

function viewExtend(id){
	eval("var data= '"+_all_table_Id+"="+id+"'");
	var att_url= $.supper("getServicePath", {"data":data,url:"/jsp/dentistmall/storage/viewInventoryExtendList.jsp"});
	var tt_win=$.supper("showWin",{url:att_url,title:"查看库存明细",icon:"fa-"+_all_edit_icon,width:1200,height:600});
}
/**
 * 删除选中行记录
 * @param id 选中行记录主键值
 */
function main_delete(id){ 
	eval("var vdata= '"+_all_table_Id+"="+id+"'"); 
	$.supper("confirm",{ title:"删除操作", msg:"确认删除记录操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":_all_deleteAction,"data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}  

/**
 * 删除多条选中行记录
 */
function main_allDelete(){ 
	var rows=_all_win_datagrid.selectedRows();
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}
	var rbsIds="";
	for(var i =0 ;i < rows.length;i++){
		eval('rbsIds += rows[i].'+_all_table_Id+'+","; ' ); 
	}
	rbsIds = rbsIds.substring(0,rbsIds.length-1);	
	$.supper("confirm",{ title:"批量删除操作", msg:"确认删除记录操作？", yesE:function(){		 
		eval("var vdata= '"+_all_table_Id+"s="+rbsIds+"'"); 		
		$.supper("doservice", {"service":_all_deleteAllAction,"data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

function viewMdEnterMxList(wiId){
	var data = "wiId="+wiId;
	var att_url= $.supper("getServicePath", {"data":data,url:"/jsp/dentistmall/storage/viewMdEnterMxList.jsp"});
	var tt_win=$.supper("showWin",{url:att_url,title:"入库明细",icon:"fa-calendar",width:1200,height:_all_edit_height});
}

function viewMdOutMxList(wiId){
	var data = "wiId="+wiId;
	var att_url= $.supper("getServicePath", {"data":data,url:"/jsp/dentistmall/storage/viewMdOutMxList.jsp"});
	var tt_win=$.supper("showWin",{url:att_url,title:"出库明细",icon:"fa-calendar",width:1200,height:_all_edit_height});
}

function export_all(){
	var vdata = _all_win_searchForm.serialize();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdInventoryService.exportList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

function imp_all(){
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/storage/impInventory.jsp"});
	var tt_win=$.supper("showWin",{url:att_url,title:"库存导入",icon:"cloud-upload",width:500,height:500, BackE:main_search});
}
var count =  1;
function chickSafety() {
	if ($("#safetyhide").is(":hidden")) {
		$("#safetyhide").show();
	}else{
		$("#safetyhide").hide();
	}
}
// //输入模糊查询  获取文本框的值
// var wmsIdStrs="";
// function inputValueLike(input1,input2){
// 	var wmsIdStr = "";
// 	var data="matName="+input1+"&"+"mmfName="+input2;
// 	$.supper("doservice", {"service":"MdInventoryService.getPagerViewObject","data":data, "BackE":function (jsondata) {
// 			var mxList = jsondata.rows;
// 			if(mxList != null && mxList.length > 0){
// 				for(var i =0;i < mxList.length;i++){
// 					wmsIdStr +=mxList[i].wiId+",";
// 				}
// 				wmsIdStrs=wmsIdStr;
// 			}
//
// 		}});
// }
//获取单选框的值 ****start******
var checkValue="";
var checkValueCount =  1;
function checkboxMatName(checkboxNameValue) {
	if(checkValueCount%2!=0){
		checkValue+=checkboxNameValue;
		checkValueCount++;
	}else if (checkValueCount%2==0){
		checkValue="";
		checkValueCount++;
	}
}
var checkValue2="";
var checkValueNormCount =  1;
function checkboxNorm(checkboxNormValue) {
	if(checkValueNormCount%2!=0){
		checkValue2+=checkboxNormValue;
		checkValueNormCount++;
	}else if (checkValueNormCount%2==0){
		checkValue2="";
		checkValueNormCount++;
	}
}

// //获取单选框的值 ****end******
// function onclickSafety() {
// 	var select1=$("#select1").val();
// 	var select2=$("#select2").val();
// 	var input1="";
// 	var input2="";
// 	if (checkValue=="0"&&select1=="1"&&checkValue2=="1"&&select2=="1") {
// 		input1=$("#input1").val();
// 		input2=$("#input2").val();
// 		inputValueLike(input1,input2);
// 	}
// 	else if (((checkValue!="0"||select1!="1")&&checkValue2=="1"&&select2=="1")||(checkValue=="0"&&checkValue2=="1"&&select2=="1")||(select1=="1"&&checkValue2=="1"&&select2=="1")) {
// 		input1="";
// 		input2=$("#input2").val();
// 		inputValueLike(input1,input2);
// 	}else if (((checkValue2!="1"||select2!="1")&&checkValue=="0"&&select1=="1")||(checkValue2=="1"&&checkValue=="0"&&select1=="1")||(select2=="1"&&checkValue=="0"&&select1=="1")) {
// 		input1=$("#input1").val();
// 		input2="";
// 		inputValueLike(input1,input2);
// 	}else {
// 		input1="@###$$%^";
// 		input2="@###$$%^";
// 		inputValueLike(input1,input2);
// 	}
// 	//获取文本框的值
// 	var inputMin=$("#inputMin").val();
// 	var inputMax=$("#inputMax").val();
// 	if (inputMin==""||inputMin==null) {
// 		inputMin=0;
// 	}
// 	if (inputMax==""||inputMax==null) {
// 		inputMax=0;
// 	}
// 	var pattern = /^[0-9]+(.[0-9]{0,3})?$/;
// 	if (!pattern.test(inputMin)||!pattern.test(inputMax)) {
// 		$("#yz").show();
// 		return;
// 	}
// 	if (inputMin>inputMax) {
// 		$("#yz1").show();
// 		return;
// 	}
// 	var vdata="wmsIdStrs="+wmsIdStrs+"&"+"inputMin="+inputMin+"&"+"inputMax="+inputMax;
// 	$.supper("doservice", {"service":"MdInventoryService.saveSafety","data":vdata, "BackE":function (jsondata) {
// 			if (jsondata.code == "1") {
// 				if (jsondata==""&&jsondata==null&&!jsondata.equals("")){
// 				}
// 				$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
// 				wmsIdStrs="";
// 				main_search();
// 			}else
// 				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
// 		}});
// }
//获取单选框的值 ****end******
function onclickSafety() {
	var select1=$("#select1").val();
	var select2=$("#select2").val();
	var input1=$("#input1").val();
	var input2=$("#input2").val();
	// if (checkValue=="0"&&select1=="1"&&checkValue2=="1"&&select2=="1") {
	// 	input1=$("#input1").val();
	// 	input2=$("#input2").val();
	// 	inputValueLike(input1,input2);
	// }
	// else if (((checkValue!="0"||select1!="1")&&checkValue2=="1"&&select2=="1")||(checkValue=="0"&&checkValue2=="1"&&select2=="1")||(select1=="1"&&checkValue2=="1"&&select2=="1")) {
	// 	input1="";
	// 	input2=$("#input2").val();
	// 	inputValueLike(input1,input2);
	// }else if (((checkValue2!="1"||select2!="1")&&checkValue=="0"&&select1=="1")||(checkValue2=="1"&&checkValue=="0"&&select1=="1")||(select2=="1"&&checkValue=="0"&&select1=="1")) {
	// 	input1=$("#input1").val();
	// 	input2="";
	// 	inputValueLike(input1,input2);
	// }else {
	// 	input1="@###$$%^";
	// 	input2="@###$$%^";
	// 	inputValueLike(input1,input2);
	// }
	//获取文本框的值
	var inputMin=$("#inputMin").val();
	var inputMax=$("#inputMax").val();
	if (inputMin==""||inputMin==null) {
		inputMin=0;
	}
	if (inputMax==""||inputMax==null) {
		inputMax=0;
	}
	var pattern = /^[0-9]+(.[0-9]{0,3})?$/;
	if (!pattern.test(inputMin)||!pattern.test(inputMax)) {
		$("#yz").show();
		return;
	}
	if (inputMin>inputMax) {
		$("#yz1").show();
		return;
	}
	var vdata="input1="+input1+"&input2="+input2+"&"+"inputMin="+inputMin+"&"+"inputMax="+inputMax+"&"+"checkValue="+checkValue+"&"+"checkValue2="+checkValue2+"&"+"select1="+select1+"&"+"select2="+select2;
	$.supper("doservice", {"service":"MdInventoryService.saveSafetyNew","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				if (jsondata==""&&jsondata==null&&!jsondata.equals("")){
				}
				$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
				wmsIdStrs="";
				main_search();
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}});
}


//此方法用于安全预警时正则验证不正确之后点击文本框提示文字消失
function yzInput(){
	$("#yz").hide();
	$("#yz1").hide();
}
$(document).bind("click", function(e) {
	var target = $(e.target);
	if (target.closest("#safetyhide").length == 0&&target.closest("#safetyEwBtn").length == 0) {
		$("#safetyhide").hide();
	}
})
 