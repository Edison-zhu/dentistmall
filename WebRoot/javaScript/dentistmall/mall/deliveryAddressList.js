var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdDeliveryAddressService.getPagerModelObject";
var _all_deleteAction = "MdDeliveryAddressService.deleteObject";
var _all_deleteAllAction = "MdDeliveryAddressService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/mall/updateDeliveryAddress.jsp";
var _all_table_Id = "mdaId";
var _all_edit_icon = "gears";
var _all_edit_title = "收货地址维护";
var _all_edit_width = 800;
var _all_edit_height =400;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	main_edit(data.mdaId);
}

var _searchForm={ 
		lineNum:1,
		columnNum:2,
		control:[],
		groupTag:[], 
		items:[ 
			   {title:"收件人",name:"addressee",type:"text", placeholder:"收件人"}
		]	
	}
	 
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search}, 
		       	{title:"添加",id:"win_but_add",icon:"plus", colourStyle:"primary",rounded:true,clickE:main_add},
		       	{title:"批量删除",id:"win_but_alldel",icon:"close", colourStyle:"success",rounded:true,clickE:main_allDelete} 
		       ] 
	}


var _DataGrid = {
	cols: [
		{title: '收件人', sortable: true, name: 'addressee', width: 30, align: 'center'},
		{title: '省', sortable: true, name: 'province', width: 30, align: 'center'},
		{title: '市', sortable: true, name: 'city', width: 30, align: 'center'},
		{title: '区县', sortable: true, name: 'area', width: 30, align: 'center'},
		{title: '详细地址', sortable: true, name: 'deliveryAddress', width: 120, align: 'center'},
		{title: '收件人电话', sortable: true, name: 'addresseeTelephone', width: 80, align: 'center'},
		{title: '是否默认', sortable: true, name: 'ifDefault', width: 30, align: 'center', impCode: 'PAR171027083430113'},
		{title: '操作', name: 'control', width: 80, align: 'center', renderer: control}

	]
	, remoteSort: false
	, name: 'deliveryAddressListGrid'
	, height: _all_datagrid_height
	, url: getMMGridUrl()
	, dblClickFunc: _dblClick
	, mmPaginatorOpt: _all_win_datagrid_pg
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
  
  main_search();
  
  /**
	 * 改变查询,添加按钮样式
	 */
	$("#win_but_search").css("width","95px");
	$("#win_but_search").css("vertical-align","middle");
	
	$("#win_but_add").css("width","95px");
	$("#win_but_add").css("vertical-align","middle");
  
});  

function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"main_edit('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>修改</a>&nbsp;&nbsp;";
	str += "<a onclick=\"main_delete('"+  idvalue+"')\"  class='btn btn-danger  btn-xs'>删除</a> ";  
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
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	 var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
	//	$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
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


 