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
var _all_edit_title = "设置预警";
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
			    {title:"物料名称",name:"matName",type:"text", placeholder:"物料名称"},
			   {title:"规格",name:"mmfName",type:"text",placeholder:"规格"}
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
		{title: '物料名称', sortable: true, name: 'matName', width: 100, align: 'center'},
		{title: '规格', sortable: true, name: 'mmfName', width: 100, align: 'center'},
		{
			title: '库存数量',
			sortable: true,
			name: 'baseNumber',
			width: 80,
			align: 'center',
			sortable: true,
			renderer: quantityControl
		},
		{title: '预警数量', sortable: true, name: 'warningShu', width: 80, align: 'center', renderer: warningControl},
		{title: '最高数量', sortable: true, name: 'maxShu', width: 70, align: 'center', renderer: warningControl},
		{title: '到货日期', sortable: true, name: 'minDay', width: 80, align: 'center', renderer: warningControl},
		{title: '基本单位', sortable: true, name: 'basicUnit', width: 80, align: 'center'}
	]
	, remoteSort: false
	, name: 'inventoryListGrid'
	, height: _all_datagrid_height
	, gridtype: '2'
	, nowrap: true
	, url: getMMGridUrl()
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
});

function quantityControl(val,item,rowIndex){
   if(item.warningShu != null && item.warningShu > 0 && val < item.warningShu){
	   var str = "<lable style=\"color:red\">"+val+"</lable>";
	   return str;
   }else
	   return val;
   
}
function warningControl(val,item,rowIndex){
	if(val ==null || val==0)
		return "-";
	return val;
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

