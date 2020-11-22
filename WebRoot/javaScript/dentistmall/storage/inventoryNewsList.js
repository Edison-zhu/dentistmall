var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdNewsInfoService.getInventoryNewPagerModel";
var _all_deleteAction = "MdInventoryService.deleteObject";
var _all_deleteAllAction = "MdInventoryService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit = "";
var _all_table_Id = "mniId";
var _all_edit_icon = "bolt";
var _all_edit_title = "设置预警";
var _all_edit_width = 800;
var _all_edit_height = 500;
var _all_datagrid_height ;

var _searchForm={ 
		lineNum:1,
		columnNum:3,
		control:[],
		groupTag:[], 
		hiddenitems:[
		],
		items:[ 
			   {title:"商品名称",name:"matName",type:"text", placeholder:"输入之后回车查询"},
			   {title:"规格",name:"mmfName",type:"text",placeholder:"输入之后回车查询"}, 
			   {title:"供应商",name:"applicantName",type:"text",placeholder:"输入之后回车查询"}
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
			       	{title:'商品名称', sortable: true, name:'mat_name', width:100, align: 'center',renderer:controlInfo},
			       	{title:'规格', sortable: true, name:'mmf_name', width:100, align: 'center'},
			       	{title:'供应商', sortable: true, name:'applicant_Name', width:80, align: 'center'},
			    	{title:'单价', sortable: true, name:'price', width:80, align: 'center'},
			    	{title:'基本单位', sortable: true, name:'Basic_unit', width:80, align: 'center'},
			    	{title:'库存数量', sortable: true, name:'base_number', width:80, align: 'center',renderer:quantityControl},
			    	{title:'预警数量', sortable: true, name:'warning_shu', width:80, align: 'center',renderer:warningControl},
			    	{title:'到货日期', sortable: true, name:'min_day', width:80, align: 'center',renderer:warningControl},
			    	{title:'提醒时间', sortable: true, name:'new_date', width:80, align: 'center'},
			    	{title:'状态', sortable: true, name:'is_view', width:80, align: 'center',renderer:controlView},
			       	{title:'操作' ,name:'is_view',width:100,  align:'center',renderer:control}
	           ]
	, remoteSort: false
			,height:_all_datagrid_height
			,gridtype:'2'
			,nowrap:true 
			, url:getMMGridUrl() 
	        ,mmPaginatorOpt:_all_win_datagrid_pg
	}

function quantityControl(val,item,rowIndex){
	   if(item.warning_shu != null && item.warning_shu > 0 && val < item.warning_shu){
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
//设置按钮宽度与图标随文字居中
  $("#win_but_search").css("width","95px");
  $("#win_but_search").css("vertical-align","middle");
/*  $("#win_but_export").css("width","95px");
  $("#win_but_export").css("vertical-align","middle");*/
  
  //设置搜索框字体大小
  if($("#matName").val() !=null){
	  $("#matName").css("font-size","13px");
  }
//设置搜索框字体大小
  if($("#mmfName").val() !=null){
	  $("#mmfName").css("font-size","13px");
  }
  if($("#applicantName").val() !=null){
	  $("#applicantName").css("font-size","13px");
  }
  
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
  $("#applicantName").on('keydown', function(){
	  if (event.keyCode==13) {
		  $("#win_but_search").trigger("click");
	  }
});
});  

function control(val,item,rowIndex){
	var str = "";
	if(val=='1')
		str += "<a onclick=\"main_shure('"+  item.mni_id+"')\"  class='btn btn-info  btn-xs'>确认</a>";
	else
		str += "<a onclick=\"main_delete('"+  item.mni_id+"')\"  class='btn btn-danger  btn-xs'>删除</a> "; 
   return str;
}
function controlInfo(val,item,rowIndex){
	var str = "";
	str += "<a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+item.wms_mi_id+"\" target=\"_blank\">"+item.mat_name+"</a> "; 
	return str;
}

function controlView(val,item,rowIndex){
	var str = "";
	if(val=='1')
		return "未确认";
	else
		return "已确认";
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

function main_shure(mniId){
	var data="mniId="+mniId+"&isView=2";
	$.supper("confirm",{ title:"确认操作", msg:"确认提醒操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdNewsInfoService.saveMdNewsInfoView","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
	
}
function main_delete(mniId){
	var data="mniId="+mniId+"&isView=0";
	$.supper("confirm",{ title:"删除操作", msg:"确认删除操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdNewsInfoService.saveMdNewsInfoView","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
	
}
