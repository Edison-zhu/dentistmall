var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdOutOrderService.getPagerModelObject";
var _all_deleteAction = "MdOutOrderService.deleteObject";
var _all_closeAction = "MdOutOrderService.updateObjectFlowState";
var _all_deleteAllAction = "MdOutOrderService.deleteAllObject";

var _name_service = 'MdOutOrderService.getPagerMdOutOrderDistinct';
var _name_select_action = {serviceName: _name_service};

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/storage/editMdOutOrder.jsp";
var _all_win_url_view = "/jsp/dentistmall/storage/viewMdOutOrder.jsp";
var _all_table_Id = "mooId";
var _all_table_moo_code = "mooCode";
var _all_edit_icon = "th";
var _all_edit_title = "申领信息";
var _all_edit_width = 800;
var _all_edit_height = 500;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	// if(data.flowState == '2' || data.flowState == '3') { //申领中，部分发货
		main_view(data.mooId, data.mooCode);
	// }
}

var _searchForm={ 
		lineNum:2,
		columnNum:3,
		control:[],
		groupTag:[], 
		hiddenitems:[ 
		],
		items:[ 
			   {title:"申领单号",name:"mooCode",type:"text",placeholder:"输入申领单号,回车查询"}, 
			   {title:"申领人",name:"userName",type:"text", placeholder:"输入申领人,回车查询"},
			   {title:"申领状态",name:"flowState",type:"select",impCode:"PAR171113111313225", placeholder:"申领状态"},
			   {title:"开始时间",name:"orderTime_start",type:"text", placeholder:"申请开始时间",readOnly:true},
			   {title:"结束时间",name:"orderTime_end",type:"text", placeholder:"申请结束时间",readOnly:true}
		]	
	}
	 
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"&nbsp查询&nbsp",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search}, 
		       	// {title:"添加",id:"win_but_add",icon:"plus", colourStyle:"primary",rounded:true,clickE:main_add},
		        {title:"批量导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all}
		       ] 
	}


var _DataGrid = {
	cols: [
		{title: '申领单号', sortable: true, name: 'mooCode', width: 80, align: 'center'},
		{title: '申领人', sortable: true, name: 'userName', width: 40, align: 'center'},
		{title: '申领部门', sortable: true, name: 'groupName', width: 80, align: 'center'},
		{title: '申领数量', sortable: true, name: 'number1', width: 40, align: 'center'},
		{title: '出库数量', sortable: true, name: 'number2', width: 40, align: 'center'},
		{
			title: '缺少数量',
			sortable: true,
			name: 'missingNumber',
			width: 30,
			align: 'center',
			renderer: missingNumberInput
		},
		{title: '申领时间', sortable: true, name: 'orderTime', width: 80, align: 'center'},
		{title: '流程状态', sortable: true, name: 'flowState', width: 40, align: 'center', impCode: "PAR171113111313225"},
		{title: '操作', name: 'control', width: 170, align: 'left', renderer: control}
	]
	, remoteSort: false
	, name: 'mdOutWarehouserListGrid'
	, height: _all_datagrid_height
	, url: getMMGridUrl()
	, dblClickFunc: _dblClick
	, mmPaginatorOpt: _all_win_datagrid_pg
}

//20191124 yangfeng 缺少数量显示
function missingNumberInput(val, item, index){
	var missingNumber = item.number1 - item.number2;
	var tt = '';
	if(item.flowState == '1') {
		tt = '<span>-</span>';
	} else if(missingNumber < 0){
		tt = '<span style="color: red;">' + missingNumber + '</span>';
	} else {
		tt = '<span>' + missingNumber + '</span>';
	}
	return tt;
}
var allClaomant;

/**
 * 页面初始化函数
 */
$(function () {
	_all_win_searchForm.xform('createForm', _searchForm);

	_all_div_hidden.xform('createhidden', _searchForm.hiddenitems);

	_all_win_tools_but.xtoolbar('create', _Toolbar);

	_all_datagrid_height = $(window).height() - _all_win_searchForm.height() - 64 - 95;

	_DataGrid.height = _all_datagrid_height;

	_all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);
	laydate({
		elem: '#orderTime_start',
		format: 'YYYY-MM-DD' //日期格式
	});
	laydate({
		elem: '#orderTime_end',
		format: 'YYYY-MM-DD' //日期格式
	});
	// main_search();

//设置按钮宽度与图标随文字居中
	$("#win_but_search").css("width", "95px");
	$("#win_but_search").css("vertical-align", "middle");
	/*  $("#win_but_export").css("width","95px");
      $("#win_but_export").css("vertical-align","middle");*/

	//设置搜索框字体大小
	if ($("#mooCode").val() != null) {
		$("#mooCode").css("font-size", "13px");
	}
//设置搜索框字体大小
	if ($("#userName").val() != null) {
		$("#userName").css("font-size", "13px");
	}
	if ($("#flowState").val() != null) {
		$("#flowState").css("font-size", "13px");
	}
	if ($("#orderTime_start").val() != null) {
		$("#orderTime_start").css("font-size", "13px");
	}
	if ($("#orderTime_end").val() != null) {
		$("#orderTime_end").css("font-size", "13px");
	}
	//查询连接回车
	$("#mooCode").on('keydown', function () {
		if (event.keyCode == 13) {
			$("#win_but_search").trigger("click");
		}
	});
	// $("#userName").on('keydown', function () {
	// 	if (event.keyCode == 13) {
	// 		$("#win_but_search").trigger("click");
	// 	}
	// 	e.preventDefault();
	// });

	var data = _all_win_searchForm.serialize();
	data = data.replace('flowState=1&', 'flowState=&'); //flowState=1时不需要传值
	_name_select_action.data = data + '&distinctName=userName';
	$('#userName').editableSelect({
		keyEnter: function () {
			$("#win_but_search").trigger("click");
		}
	});
	$('#userName').on('focus', function () {
		$('#userName').editableSelect('clear');
		var shqJson = [];
		shqJson = $.supper("doselectService", _name_select_action);

		var items = shqJson.items;
		for (var i = 0; i < items.length; i++) {
			$('#userName').editableSelect('add', items[i]);
		}
		$('#userName').editableSelect('show');
	})
	var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
	if(selOutOrderType != null && selOutOrderType.flowState != null){
		allClaomant=selOutOrderType.flowState;
		// if (selOutOrderType.flowState1!=null) {
		// 	allClaomant=selOutOrderType.flowState+","+selOutOrderType.flowState1;
		// }
		$.supper("setProductArray", {"name":"selOutOrderType", "value":null});
	}
	$("#flowState").val(allClaomant);
	// if (allClaomant!=null&&allClaomant!=""&&allClaomant!=undefined) {
		main_search();
	// }
});

function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);

	str += "<a onclick=\"main_view('" + idvalue + "', '"+ item.mooCode +"')\"  class='btn btn-primary  btn-xs'>查看</a>&nbsp;&nbsp;";
	if(item.flowState == '2') { //申领中
		str += "<a onclick=\"main_copy('" + idvalue + "', '"+ item.mooCode +"')\"  class='btn btn-primary  btn-xs'>修改</a>&nbsp;&nbsp;";
	}
	// if(item.flowState == '4') { //已完成
		str += "<a onclick=\"main_copy_again('" + idvalue + "')\"  class='btn btn-warning  btn-xs'>再次申请</a>&nbsp;&nbsp;";
	// }
	// str += "<a onclick=\"main_export('"+  idvalue+"')\"  class='btn btn-success  btn-xs'>导出</a>&nbsp;&nbsp;";
	//单个导出方法写在了多个中
	 //str += "<a onclick=\"main_export('"+  idvalue+"')\"  class='btn btn-success  btn-xs'>导出</a>&nbsp;&nbsp;";
	if(item.flowState == '2' || item.flowState == '3'){
		str += "<a onclick=\"main_close('"+  idvalue+"')\"  class='btn btn-danger  btn-xs'>撤销申请</a> ";
	}
	// else if(item.flowState=='2')
	// 	str += "<a onclick=\"main_close('"+  idvalue+"')\"  class='btn btn-info  btn-xs'>关闭</a> ";
	
   return str;
}
/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl(){	
	var data = _all_win_searchForm.serialize();
	data = data.replace('flowState=1&', 'flowState=&'); //flowState=1时不需要传值
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
	$.supper("setProductArray", {"name":"menuItemUrl", "value":att_url});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
	//	$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
} 
function main_copy(id, mooCode){
	eval("var data= '"+_all_table_Id+"="+id+"'");
	if(mooCode !== undefined && mooCode !== null){
	    eval('var data = "' + _all_table_Id + '=' + id + '&' + _all_table_moo_code + '=' + mooCode +'"');
    }
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	$.supper("setProductArray", {"name":"menuItemUrl", "value":att_url});
	var tt_win=$.supper("showTtemWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
	// $.supper("showTtemWin",{ "url":"/dentistmall/jsp/dentistmall/storage/editMdOutOrder.jsp","title":_all_edit_title});
}
function main_copy_again(id){
	eval("var data= '"+_all_table_Id+"="+id+"&reapply=1'");
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	$.supper("setProductArray", {"name":"menuItemUrl", "value":att_url});
	var tt_win=$.supper("showTtemWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
	// $.supper("showTtemWin",{ "url":"/dentistmall/jsp/dentistmall/storage/editMdOutOrder.jsp","title":_all_edit_title});
}
function main_view(id, mooCode){
	eval("var data= '"+_all_table_Id+"=" + id + "&mainview=1'");
	if(mooCode !== undefined && mooCode !== null){
		eval('var data = "' + _all_table_Id + '=' + id + '&' + _all_table_moo_code + '=' + mooCode +'&mainview=1"');
	}
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	$.supper("setProductArray", {"name":"menuItemUrl", "value":att_url});
	var tt_win=$.supper("showTtemWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
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

function main_close(id){ 
	eval("var vdata= '"+_all_table_Id+"="+id+"'"); 
	$.supper("confirm",{ title:"关闭操作", msg:"确认撤销申领单操作？" ,yesE:function(){
		$.supper("doservice", {"service":_all_closeAction,"data":vdata, "BackE":function (jsondata) {
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

//单个导出方法写在了多个导出中
/*function main_export(mooId){
	var vdata="mooId="+mooId;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOutOrderService.exportInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}*/

function export_all(){
	//var vdata=_all_win_datagrid.serialize();
	var rows=_all_win_datagrid.selectedRows();
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}
	var mooIds="";
	for ( var int = 0; int < rows.length; int++) {
		
		mooIds += rows[int].mooId+",";
	}
	mooIds=mooIds.substring(0,mooIds.length-1);
	vdata="&mooIds="+mooIds;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOutOrderService.exportList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

 