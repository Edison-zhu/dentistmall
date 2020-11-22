var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdOutWarehouseService.getPagerModelObject";
var _all_deleteAction = "MdOutWarehouseService.deleteObject";
var _all_deleteAllAction = "MdOutWarehouseService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/storage/editMdOutWarehouser.jsp";
var _all_win_url_view = "/jsp/dentistmall/storage/viewMdOutWarehouser.jsp";
var _all_table_Id = "wowId";
var _all_edit_icon = "truck";
var _all_edit_title = "退货出库信息";
var _all_edit_width = 800;
var _all_edit_height = 500;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	main_edit(data.wowId);
}

var _searchForm={ 
		lineNum:2,
		columnNum:2,
		control:[],
		groupTag:[], 
		hiddenitems:[ 
					   {title:"出库单类型",name:"companyType",type:"hidden",value:'2',placeholder:"出库单类型"}
		],
		items:[ 
			   {title:"出库单号",name:"wowCode",type:"text",placeholder:"输入出库单号,回车查询"}, 
			   {title:"订单编号",name:"relatedBill1",type:"text", placeholder:"输入订单编号,回车查询"},
			   {title:"开始时间",name:"finshDate_start",type:"text", placeholder:"退货开始时间",readOnly:true},
			   {title:"结束时间",name:"finshDate_end",type:"text", placeholder:"退货结束时间",readOnly:true}
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


var _DataGrid = {
	cols: [
		{title: '出库单号', sortable: true, name: 'wowCode', width: 80, align: 'center'},
		{title: '订单编号', sortable: true, name: 'relatedBill1', width: 80, align: 'center'},
		{title: '供应商', sortable: true, name: 'supplierName', width: 80, align: 'center'},
		{title: '采购人', sortable: true, name: 'consignee', width: 50, align: 'center'},
		{title: '退货数量', sortable: true, name: 'baseNumber', width: 50, align: 'center'},
		{title: '操作人', sortable: true, name: 'userName', width: 50, align: 'center'},
		{title: '操作时间', sortable: true, name: 'finshDate', width: 80, align: 'center'},
		{title: '操作', name: 'control', width: 100, align: 'center', renderer: control}
	]
	, remoteSort: false
	, name: 'mdOutWarehouserListGrid'
	, height: _all_datagrid_height
	, url: getMMGridUrl()
	, mmPaginatorOpt: _all_win_datagrid_pg
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
		elem: '#finshDate_start',
		format: 'YYYY-MM-DD' //日期格式
	});
  laydate({
		elem: '#finshDate_end',
		format: 'YYYY-MM-DD' //日期格式
	});
  main_search();
  //设置按钮宽度与图标随文字居中
  $("#win_but_search").css("width","95px");
  $("#win_but_search").css("vertical-align","middle");
  $("#win_but_add").css("width","95px");
  $("#win_but_add").css("vertical-align","middle");
  
  //设置搜索框字体大小
  if($("#wowCode").val() !=null){
	  $("#wowCode").css("font-size","13px");
  }
//设置搜索框字体大小
  if($("#relatedBill1").val() !=null){
	  $("#relatedBill1").css("font-size","13px");
  }
  if($("#finshDate_start").val() !=null){
	  $("#finshDate_start").css("font-size","13px");
  }
  if($("#finshDate_end").val() !=null){
	  $("#finshDate_end").css("font-size","13px");
  }
  //查询连接回车
  $("#wowCode").on('keydown', function(){
	  if (event.keyCode==13) {
		  $("#win_but_search").trigger("click");
	  }
}); 
  $("#relatedBill1").on('keydown', function(){
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
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search}); 
	//	$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
} 
/**
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function main_edit(id){
	eval("var data= '"+_all_table_Id+"="+id+"'");
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_view});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
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
	eval("var vdata= '"+_all_table_Id+"="+id+"'"); 	
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOutWarehouseService.exportInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

function export_all(){
	var vdata = _all_win_searchForm.serialize();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOutWarehouseService.exportBackList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
 