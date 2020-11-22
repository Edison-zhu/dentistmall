var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdEvaluateService.getPagerModelObject";
var _all_deleteAction = "MdEvaluateService.deleteObject";
var _all_deleteAllAction = "MdEvaluateService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/business/mevaText.jsp";
var _all_table_Id = "mevaId";
var _all_edit_icon = "gears";
var _all_edit_title = "评价信息";
var _all_edit_width = 800;
var _all_edit_height = 700;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	main_view(data.mevaId);
}

var _searchForm={ 
		lineNum:2,
		columnNum:2,
		control:[],
		groupTag:[], 
		hiddenitems:[{name:"mmfId",type:"hidden"},{name:"priceReview",type:"hidden"}],
		items:[ 
			   {title:"商品名称",name:"matName",type:"text",placeholder:"输入商品名称,回车查询"}, 
			   {title:"型号",name:"mmfName",type:"text", placeholder:"输入型号,回车查询"},
			   {title:"点评人账号",name:"evaluateZh",type:"text", placeholder:"输入点评人账号,回车查询"},
			   {title:"点评人姓名",name:"evaluateRen",type:"text", placeholder:"输入点评人姓名,回车查询"}
		]	
	}
	 
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search} 
		       ] 
	}


var _DataGrid = {
	cols: [
		{title: '点评id', sortable: true, name: 'mevaId', width: 80, align: 'center'},
		{title: '点评人姓名', sortable: true, name: 'evaluateRen', width: 80, align: 'center'},
		{title: '点评人账号', sortable: true, name: 'evaluateZh', width: 50, align: 'center'},
		{title: '所属公司', sortable: true, name: 'orgName', width: 50, align: 'center'},
		{title: '点评人手机号', sortable: true, name: 'evaluatePhoneNumber', width: 50, align: 'center'},
		{title: '点评商品编号', sortable: true, name: 'matCode', width: 50, align: 'center'},
		{title: '点评商品名称', sortable: true, name: 'matName', width: 50, align: 'center'},
		{title: '型号', sortable: true, name: 'mmfName', width: 50, align: 'center'},
		{title: '售价', sortable: true, name: 'price', width: 50, align: 'center'},
		{title: '点评时间', sortable: true, name: 'evaluateTime', width: 50, align: 'center'},
		{title: '点评类型', sortable: true, name: 'priceReview', width: 50, renderer: control_priceReview, align: 'center'},
		{
			title: '价格比较',
			sortable: true,
			name: 'priceComparison',
			width: 50,
			renderer: control_priceComparison,
			align: 'center'
		},
		{title: '差价', sortable: true, name: 'difference', width: 50, align: 'center'},
		{title: '预购数量', sortable: true, name: 'preOrderNumber', width: 50, align: 'center'},
		{title: '点评内容', sortable: true, name: 'cz', width: 50, renderer: control01, align: 'center'},
		{title: '操作', name: 'control', width: 50, align: 'center', renderer: control}
	]
	, remoteSort: false
	, name: 'dentistevaluateListGrid'
	, height: _all_datagrid_height
	, url: getMMGridUrl()
	, mmPaginatorOpt: _all_win_datagrid_pg
	, dblClickFunc: _dblClick
}

function control_priceReview(val,item,rowIndex){
	var str = "";
  if(val=="1"){
	  str = "价格有优势";
  }
  if(val=="2"){
	  str = "价格一般";
  }
  if(val=="3"){
	  str = "价格无优势";
  }
   return str;
}

function control_priceComparison(val,item,rowIndex){
	var str = "";
	  if(val=="1"){
		  str = "比较其他渠道较低";
	  }
	  if(val=="2"){
		  str = "价格有优势";
	  } 
	   return str;
}


function control01(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"main_view('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>查看</a>&nbsp;&nbsp;"; 
   return str;
}

function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"main_delete('"+  idvalue+"')\"  class='btn btn-danger  btn-xs'>删除</a>&nbsp;&nbsp;"; 
   return str;
}

function main_view(id){ 
	var att_urlstr="/jsp/dentistmall/evaluate/evaluateNode.jsp";
	var _all_edit_title="评价信息"
 
		var _all_table_Id = "rbbId";
	var _all_edit_icon = "gears";
	 
	var _all_edit_width =500;
	var _all_edit_height =350; 
	var data="mevaId="+id;
	var att_url= $.supper("getServicePath", {"data":data,url:att_urlstr});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height}); 
}


/**
 * 页面初始化函数
 */
$(function(){ 
	var _mmfId= $.supper("getParam", "mmfId");

  _all_win_searchForm.xform('createForm',_searchForm); 
  
  _all_div_hidden.xform('createhidden',_searchForm.hiddenitems);
	$("#mmfId").val(_mmfId);
	var _priceReview= $.supper("getParam", "priceReview");
	$("#priceReview").val(_priceReview);
	
	
  _all_win_tools_but.xtoolbar('create',_Toolbar); 
  
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-95; 
  
  _DataGrid.height=_all_datagrid_height;
  
  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid); 
  
  main_search();
  //设置查询按钮大小
  $("#win_but_search").css("width","95px");
  $("#win_but_search").css("vertical-align","middle");
  //设置搜索框字体大小
  if($("#matName").val() !=null){
	  $("#matName").css("font-size","13px");
  }
  if($("#mmfName").val() !=null){
	  $("#mmfName").css("font-size","13px");
  }
  if($("#evaluateZh").val() !=null){
	  $("#evaluateZh").css("font-size","13px");
  }
  if($("#evaluateRen").val() !=null){
	  $("#evaluateRen").css("font-size","13px");
  }
  
  //输入回车查询
//新增回车查询 2019-12-4 yanglei
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
	 $("#evaluateZh").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
	 $("#evaluateRen").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
	 
});  

function main_addUser(id){
	var att_urlstr="/jsp/sys/sysuser/addUserList.jsp";
	var att_title="管理员维护"
	var att_userType="1"
	var data= "organizaType=20002&oldId="+id+"&userType="+att_userType+"&applicantName=" +att_title;
	var att_url= $.supper("getServicePath", {"data":data,url:att_urlstr});
	$.supper("showTtemWin",{ "url":att_url,"title":att_title});
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
	//var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
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


 