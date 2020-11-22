var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdEnterWarehouseService.getPagerModelObject";
var _all_deleteAction = "MdEnterWarehouseService.deleteObject";
var _all_deleteAllAction = "MdEnterWarehouseService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/storage/editMdEnterWarehouser.jsp";
var _all_win_url_view = "/jsp/dentistmall/storage/viewMdEnterWarehouser.jsp";
var _all_table_Id = "wewId";
var _all_edit_icon = "calendar";
var _all_edit_title = "订单入库信息";
var _all_edit_width = 800;
var _all_edit_height = 600;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	main_edit(data.wewId);
}

var _searchForm={ 
		lineNum:2,
		columnNum:2,
		control:[],
		groupTag:[], 
		hiddenitems:[ 
					   {title:"入库单类型",name:"billType",type:"hidden",value:'2',placeholder:"入库单类型"}
		],
		items:[ 
			   {title:"入库单号",name:"billcode",type:"text",placeholder:"输入入库单号,回车查询"}, 
			   {title:"订单编号",name:"relationBillCode",type:"text", placeholder:"输入订单编号,回车查询"}, 
			   {title:"开始时间",name:"receiptDatetime_start",type:"text", placeholder:"入库开始时间",readOnly:true},
			   {title:"结束时间",name:"receiptDatetime_end",type:"text", placeholder:"入库结束时间",readOnly:true}
		]	
	}
	 
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"&nbsp查询&nbsp",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search}, 
		       	{title:"&nbsp添加&nbsp",id:"win_but_add",icon:"plus", colourStyle:"primary",rounded:true,clickE:main_add},
		        {title:"全部导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all}
		       ] 
	} 
 


var _DataGrid={
	cols: [
		{title: '入库单号', sortable: true, name: 'billcode', width: 80, align: 'center'},
		{title: '订单编号', sortable: true, name: 'relationBillCode', width: 80, align: 'center'},
		{title: '供应商', sortable: true, name: 'supplierName', width: 80, align: 'center'},
		{title: '入库数量', sortable: true, name: 'expectNumber', width: 60, align: 'center'},
		{title: '入库人', sortable: true, name: 'consignor', width: 60, align: 'center'},
		{title: '入库时间', sortable: true, name: 'receiptDatetime', width: 80, align: 'center'},
		{title: '操作', name: 'control', width: 100, align: 'center', renderer: control}
	]
	, remoteSort: false
	, name: 'mdEnterWarehouserListGrid'
	, height: _all_datagrid_height
	, url: getMMGridUrl()
	, mmPaginatorOpt: _all_win_datagrid_pg
	, nowrap: true
	, dblClickFunc: _dblClick
}

/**
 * 页面初始化函数
 */
$(function(){ 
	
  _all_win_searchForm.xform('createForm',_searchForm); 
  
  _all_div_hidden.xform('createhidden',_searchForm.hiddenitems);
  
  _all_win_tools_but.xtoolbar('create',_Toolbar); 
  
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-95; 
  
  _DataGrid.height=_all_datagrid_height;
  
  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid); 
  laydate({
		elem: '#receiptDatetime_start',
		format: 'YYYY-MM-DD' //日期格式
	});
  laydate({
		elem: '#receiptDatetime_end',
		format: 'YYYY-MM-DD' //日期格式
	});
  main_search();
  
  //设置按钮宽度与图标随文字居中
  $("#win_but_search").css("width","95px");
  $("#win_but_search").css("vertical-align","middle");
  $("#win_but_add").css("width","95px");
  $("#win_but_add").css("vertical-align","middle");
  
  //设置搜索框字体大小
  if($("#billcode").val() !=null){
	  $("#billcode").css("font-size","13px");
  }
//设置搜索框字体大小
  if($("#relationBillCode").val() !=null){
	  $("#relationBillCode").css("font-size","13px");
  }
  if($("#receiptDatetime_end").val() !=null){
	  $("#receiptDatetime_end").css("font-size","13px");
  }
  if($("#receiptDatetime_start").val() !=null){
	  $("#receiptDatetime_start").css("font-size","13px");
  }
  //新增回车查询 2019-12-3 yanglei
  $("#billcode").on('keydown', function(){
	  if (event.keyCode==13) {
		  $("#win_but_search").trigger("click");
	  }
}); 
 
  $("#relationBillCode").on('keydown', function(){
	  if (event.keyCode==13) {
		  $("#win_but_search").trigger("click");
	  }
});
});  

function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"main_edit('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>查看</a>&nbsp;&nbsp;";
	str += "<a onclick=\"main_export('"+  idvalue+"')\"  class='btn btn-success  btn-xs'>导出</a>&nbsp;&nbsp;";
   return str;
}
/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl(){	
	var data = _all_win_searchForm.serialize();
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
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:1000,height:_all_edit_height, BackE:main_search}); 
	//	$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
} 
/**
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function main_edit(id){
	eval("var data= '"+_all_table_Id+"="+id+"'");
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_view});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:1000,height:_all_edit_height, BackE:main_search});
	//$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
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

function main_export(id){
	var vdata="wewId="+id;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdEnterWarehouseService.exportInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

function export_all(){
	var vdata = _all_win_searchForm.serialize();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdEnterWarehouseService.exportList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

 