var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdFavoritesService.getMdFavoritesMapPagerModel";
var _all_deleteAction = "MdFavoritesService.deleteObject";
//var _all_deleteAllAction = "MdInventoryService.deleteAllObject";

var _all_win_datagrid;

var _all_table_Id = "mfId";
var _all_edit_icon = "gears";
var _all_edit_title = "收藏夹维护";
var _all_edit_width = 800;
var _all_edit_height = 700;
var _all_datagrid_height ;

var _searchForm={ 
		lineNum:1,
		columnNum:2,
		control:[],
		groupTag:[], 
		items:[ 
			   {title:"商品名称",name:"matName",type:"text",placeholder:"商品名称"}, 
			   {title:"供应商名称",name:"applicantName",type:"text", placeholder:"供应商名称"}
		]	
	};
	 
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search} 
		       ] 
	};

var _DataGrid={
			cols: [  
			       	{title:'商品名称', sortable: true, name:'mat_name', width:100, align: 'center',renderer:controlInfo} ,
			       	{title:'供应商', sortable: true, name:'applicant_name', width:80, align: 'center'},
			       	{title:'品牌', sortable: true, name:'brand', width:80, align: 'center'},
			    	{title:'单价', sortable: true, name:'money1', width:50, align: 'center'},
			    	{title:'单位', sortable: true, name:'basic_unit', width:50, align: 'center'},
			    	{title:'商品状态', sortable: true, name:'state' ,width:50,  align:'center',impCode:'PAR170926033732594'},
			    	{title:'操作' ,name:'mf_id',width:100,  align:'center',renderer:control }
	           ]
	,remoteSort: false
			,height:_all_datagrid_height
			, url:getMMGridUrl() 
	        ,mmPaginatorOpt:_all_win_datagrid_pg
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
	 * 改变查询按钮样式
	 */
	$("#win_but_search").css("width","95px");
	$("#win_but_search").css("vertical-align","middle");
	
});  
function controlInfo(val,item,rowIndex){
	var str = "";
	str += "<a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+item.wms_mi_id+"\" target=\"_blank\">"+item.mat_name+"</a> ";  
   return str;
}
function control(val,item,rowIndex){
	var str = "";
	str += "<a onclick=\"main_delete('"+  val+"')\"  class='btn btn-danger  btn-xs'>删除</a> ";  
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


 