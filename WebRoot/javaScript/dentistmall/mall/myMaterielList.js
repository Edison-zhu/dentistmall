var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdMaterielInfoService.getPagerModelObject";
var _all_deleteAction = "MdMaterielInfoService.deleteObject";
var _all_deleteAllAction = "MdMaterielInfoService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/mall/updateMyMateriel.jsp";
var _all_table_Id = "wmsMiId";
var _all_edit_icon = "gears";
var _all_edit_title = "物料信息维护";
var _all_edit_width = 800;
var _all_edit_height =400;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	main_edit(data.wmsMiId);
}

var _searchForm={ 
		lineNum:1,
		columnNum:2,
		control:[],
		groupTag:[], 
		items:[ 
		       //新增回车查询 2019-12-3 yanglei
			   {title:"物料名称",name:"matName",type:"text",placeholder:"可拼音查询,输入之后回车查询"}, 
			   {title:"物料类型",name:"labelInfo",type:"text", placeholder:"可拼音查询,输入之后回车查询"},
		]	
	}
/*function EnterTextBox() {
    if (event.keyCode==13) {
            event.keyCode = 9;
            event.returnValue = false;
            document.all["win_but_search"].clickE();
    }
}*/
/*function EnterTextBox() {
	if (event.keyCode==13) {
		event.keyCode = 9;
		 event.returnValue = false;
		 alert('123');
	}
}*/
/*$(document).keydown(function(event) {              
    if (event.keyCode == 13) {
        login();
    }
    
});*/
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"&nbsp查询&nbsp",id:"win_but_search",icon:"search",colourStyle:"default",rounded:true,clickE:main_search},
			// if(sessionScope==1){}
		       	{title:"&nbsp添加&nbsp",id:"win_but_add",icon:"plus",align:'center', colourStyle:"primary",rounded:true,clickE:main_add},
		        {title:"全部导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all}
		       ] 
	} 

var _DataGrid={
	cols: [
		{title: '物料编码', sortable: true, name: 'matCode', width: 80, align: 'center'},
		{title: '物料名称', sortable: true, name: 'matName', width: 80, align: 'center'},
		{title: '供应商', sortable: true, name: 'applicantName', width: 80, align: 'center'},
		{title: '包装方式', sortable: true, name: 'productName', width: 80, align: 'center'},
		{title: '规格', sortable: true, name: 'norm', width: 80, align: 'center'},
		{title: '物料类型', sortable: true, name: 'labelInfo', width: 20, align: 'center'},
		{title: '品牌', sortable: true, name: 'brand', width: 20, align: 'center'},
		{title: '单价', sortable: true, name: 'money1', width: 20, align: 'center'},
		{title: '单位', sortable: true, name: 'basicUnit', width: 10, align: 'center'},
		{title: '状态', sortable: true, name: 'state', width: 10, align: 'center', impCode: 'PAR170926033732594'},
		{title: '操作', name: 'control', width: 130, align: 'center', renderer: control}
	]
	, remoteSort: false
	, name: 'materielListGrid'
	, remoteSort: false
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
 /* $("#win_but_search")function(){
	  vertical-align:middle;
  }*/
  
  //设置按钮宽度与图标随文字居中
  $("#win_but_search").css("width","95px");
  $("#win_but_search").css("vertical-align","middle");
  $("#win_but_add").css("width","95px");
  $("#win_but_add").css("vertical-align","middle");
  
  //设置搜索框字体大小
  if($("#matName").val() !=null){
	  $("#matName").css("font-size","13px");
  }
//设置搜索框字体大小
  if($("#labelInfo").val() !=null){
	  $("#labelInfo").css("font-size","13px");
  }
  //新增回车查询 2019-12-3 yanglei
  $("#matName").on('keydown', function(){
	  if (event.keyCode==13) {
		  $("#win_but_search").trigger("click");
	  }
}); 
  $("#labelInfo").on('keydown', function(){
	  if (event.keyCode==13) {
		  $("#win_but_search").trigger("click");
	  }
});
	// var  _win_main_orgGxId=${sessionScope.sessionUser.orgGxId};
	// if (==1){
  // 		$("#win_but_add").hide();
  // }
	if (_win_main_orgGxId==1){
		$("#win_but_add").hide();
	}
})

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
	// var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
		$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
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

function export_all(){
	var vdata = _all_win_searchForm.serialize();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdMaterielInfoService.exportList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}


 