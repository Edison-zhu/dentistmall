var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdCompanyGroupService.getPagerModelObject";
var _all_deleteAction = "MdCompanyGroupService.deleteObject";
var _all_deleteAllAction = "MdCompanyGroupService.deleteAllObject";
var _verify_pass = 'MdCompanyGroupService.updateVerify';

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/business/updateCompanyGroup.jsp";
var _all_table_Id = "rbaId";
var _all_edit_icon = "gears";
var _all_edit_title = "公司集团维护";
var _all_edit_width = 800;
var _all_edit_height = 700;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	main_edit(data.rbaId);
}

var _searchForm={
		lineNum:1,
		columnNum:2,
		control:[],
		groupTag:[],
		items:[
			   {title:"集团公司编号",name:"rbaCode",type:"text",placeholder:"集团公司编号"},
			   {title:"集团公司名称",name:"rbaName",type:"text", placeholder:"集团公司名称"}
		]
	}

var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search},
		       	{title:"添加",id:"win_but_add",icon:"plus", colourStyle:"primary",rounded:true,clickE:main_add}
		       ]
	}


var _DataGrid = {
	cols: [
		{title: '集团公司编号', sortable: true, name: 'rbaCode', width: 80, align: 'center'},
		{title: '集团公司名称', sortable: true, name: 'rbaName', width: 80, align: 'center'},
		{title: '业务员(姓名/电话)', sortable: true, name: 'salesName', width: 80, align: 'center', renderer: initSalesMan},
		{title: '公司所在地址', sortable: true, name: 'address', width: 80, align: 'center'},
		{title: '公司所在省', sortable: true, name: 'province', width: 80, align: 'center'},
		{title: '公司所在市', sortable: true, name: 'city', width: 80, align: 'center'},
		{title: '负责人', sortable: true, name: 'personName', width: 80, align: 'center'},
		{title: '电话', sortable: true, name: 'phoneNumber', width: 80, align: 'center'},
		{title: '状态', sortable: true, name: 'state', width: 80, align: 'center', impCode: 'PAR170926033732594'},
		{title: '操作', name: 'control', width: 160, align: 'center', renderer: control}
	]
	, remoteSort: false
	, name: 'companyGroupListGrid'
	, height: _all_datagrid_height
	, url: getMMGridUrl()
	, dblClickFunc: _dblClick
	, mmPaginatorOpt: _all_win_datagrid_pg
}

function initSalesMan(val, item, rowIndex){
	var str = '<span>' + (item.salesName == undefined ? '' : item.salesName) + '</span>/<span>' + (item.salesPhone == undefined ? '' : item.salesPhone) + '</span>';
	return str;
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
	eval('var idvalue=  item.' + _all_table_Id);
	str += "<a onclick=\"main_edit('" + idvalue + "')\"  class='btn btn-warning  btn-xs'>修改</a>&nbsp;&nbsp;";
	str += "<a onclick=\"main_addUser('" + idvalue + "' )\"  class='btn btn-info  btn-xs'>维护管理员</a>&nbsp;&nbsp; ";

	if (item.verifyState != undefined && item.verifyState != null && item.verifyState != '') {
		if (item.verifyState == "1") {
			if (_user_type != undefined && _user_type != null && _user_type == '6') {
				str += "<span>审核中</span>";
			} else {
				str += "<a class='btn btn-warning  btn-xs' onclick='verifyObj(\"" + idvalue + "\", \"" + item.rbaCode + "\", \"" + item.rbaName + "\")'>审核</a>&nbsp;&nbsp;";
			}
		} else if (item.verifyState == "2") {
			if (_user_type != undefined && _user_type != null && _user_type == '6') {
				str += "<span style='color: red' onmouseover='showSpanMsg(this, \"" + item.verifyRemark + "\")' onmouseout='hideSpanMsg()'>驳回</span>";
				str += '<a style="padding-left: 10px" onclick="verifyAgain(\'' + idvalue + '\')">再次提交</a>';
			}
		}
	}
	return str;
}

function verifyAgain(id) {
	let vdata = 'id=' + id;
	$.supper("confirm", {
		title: "再次提交", msg: "确认再次提交？", yesE: function () {
			vdata += '&verifyState=1';
			$.supper("doservice", {
				"service": _verify_pass, "data": vdata, "BackE": function (jsondata) {
					if (jsondata.code == "1") {
						$.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
					}
					// layer.close(index);
				}
			});
		}
	});
}

let tips = null;
function showSpanMsg(e, message) {
	if (message === undefined || message === 'undefined' || message === null || message === '') {
		message = '无信息';
		return;
	}
	tips = layer.tips(message, $(e), {tips: 3, time: 0});
}

function hideSpanMsg() {
	if (tips === null) {
		return;
	}
	layer.close(tips);
	tips = null;
}

function verifyObj(id, code, name) {
	let html = '<div style="line-height: 98px;text-align: center">是否通过业务员或代理商创建的机构：<span>' + code + '</span></div>';
	let vdata = 'id=' + id;

	//示范一个公告层
	layer.open({
		type: 1
		, title: "审核操作" //不显示标题栏
		, closeBtn: 1
		, area: ['400px', '200px']
		, shade: 0.8
		, id: 'LAY_layuipro' //设定一个id，防止重复弹出
		, resize: false
		, btn: ['通过', '驳回']
		, btnAlign: 'c'
		, moveType: 1 //拖拽模式，0或者1
		, content: html
		, btn1: function (index, layero) {
			vdata += '&verifyState=3';
			$.supper("doservice", {
				"service": _verify_pass, "data": vdata, "BackE": function (jsondata) {
					if (jsondata.code == "1") {
						$.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
					}
					layer.close(index);
				}
			});
		}
		, btn2: function (index, layero) {
			layer.prompt({title: '输入驳回理由', formType: 2}, function(text, index1){

				vdata += '&verifyState=2&verifyRemark=' + text;
				$.supper("doservice", {
					"service": _verify_pass, "data": vdata, "BackE": function (jsondata) {
						if (jsondata.code == "1") {
							$.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
						}
						layer.close(index1);
					}
				});
			});
			layer.close(index);
		}
	});
}

function main_addUser(id){
	var att_urlstr="/jsp/sys/sysuser/addUserList.jsp";
	var att_title="管理员维护"
	var att_userType="1"
	var data= "organizaType=20001&oldId="+id+"&userType="+att_userType+"&applicantName=" +att_title;
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


 