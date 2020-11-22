var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdOrderInfoService.getAllPagerModelObject";
var _all_deleteAction = "MdOrderInfoService.deleteObject";
var _all_deleteAllAction = "MdOrderInfoService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit ="/jsp/dentistmall/transaction/updateOrderInfo.jsp";
	//"/jsp/dentistmall/transaction/orderInfo.jsp";
var _all_table_Id = "moiId";
var _all_edit_icon = "gears";
var _all_edit_title = "订单信息维护";
var _all_edit_width = 800;
var _all_edit_height = 700;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	viewOrder(data.moiId);
};
var _applicantNameService = 'MdOrderInfoService.getAllPagerModelObjectDistinct';
var _purchaseUnitService = 'MdOrderInfoService.getAllPagerModelObjectDistinct';
var _applicantNameSelectAction = {serviceName: _applicantNameService};
var _purchaseUnitSelectAction = {serviceName: _purchaseUnitService};

var _searchForm={ 
		lineNum:2,
		columnNum:3,
		control:[],
		groupTag:[], 
		items:[ 
			   {title:"订单编号",name:"orderCode",type:"text",placeholder:"输入订单编号,回车查询"}, 
			   {title:"供应商名称",name:"applicantName",type:"inputSelect", placeholder:"输入供应商名称,回车查询"},
			   {title:"采购商名称",name:"purchaseUnit",type:"inputSelect", placeholder:"输入采购商名称,回车查询"},
			   {title:"订单状态",name:"processStatus",type:"select",impCode:"PAR171023031218563", placeholder:"订单状态"},
			   {title:"下单开始时间",name:"placeOrderTime_start",type:"text", placeholder:"下单开始时间",readOnly:true},
			   {title:"下单结束时间",name:"placeOrderTime_end",type:"text", placeholder:"下单结束时间",readOnly:true}
			   
		]	
	}
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search},
		        {title:"全部导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all}
		       ] 
	} 
 

//供应商grid
var _DataGrid = {
	cols: [
		{title: '订单编号', sortable: true, name: 'orderCode', width: 80, align: 'center'},
		{title: '下单时间', sortable: true, name: 'placeOrderTime', width: 80, align: 'center'},
		{title: '供应商名称', sortable: true, name: 'applicantName', width: 100, align: 'center'},
		{title: '采购商名称', sortable: true, name: 'purchaseUnit', width: 150, align: 'center'},
		{title: '收件人', sortable: true, name: 'addressee', width: 50, align: 'center'},
		{title: '联系电话', sortable: true, name: 'addresseeTelephone', width: 80, align: 'center'},
		{title: '下单数量', sortable: true, name: 'commodityNumber', width: 50, align: 'center'},
		{title: '下单金额', sortable: true, name: 'placeOrderMoney', width: 50, align: 'center', renderer: formatMoney},
		{title: '确认数量', sortable: true, name: 'number1', width: 50, align: 'center'},
		{title: '确认金额', sortable: true, name: 'actualMoney', width: 50, align: 'center', renderer: formatMoney},
		{
			title: '流程状态',
			sortable: true,
			name: 'processStatus',
			width: 50,
			align: 'center',
			impCode: "PAR171023031218563"
		},
		{title: '操作', name: 'control', width: 100, align: 'center', renderer: control}
	]
	, remoteSort: false
	, name: 'orderInfoListGrid'
	, height: _all_datagrid_height
	, url: getMMGridUrl()
	, mmPaginatorOpt: _all_win_datagrid_pg
	, dblClickFunc: _dblClick
}

function formatMoney(val,item,rowIndex){
	if(val != null && val!="")
		return toDecimal2(val);
	else
		return "";
}

//获取当前时间 用于工作板筛选
// var time1="";
// var time2=""
// var time3=""
// function writeCurrentDate() {
// 	var now = new Date();
// 	var year = now.getFullYear(); //得到年份
// 	var month = now.getMonth();//得到月份
// 	var date = now.getDate();//得到日期
// 	var day = now.getDay();//得到周几
// 	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
// 	month = month + 1;
// 	week = arr_week[day];
// 	var time = "";
// 	time = year + "年" + month + "月" + date + "日" + " " + week;
// 	$("#currentDate").html(time);
// 	time1=year+"-"+0+month+"-"+0+date;
// 	time2=year+"-"+0+month+"-"+0+(date-1);
// 	time3=year+"-"+0+(month-1)+"-"+0+date;
// 	//设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。
// 	var timer = setTimeout("writeCurrentDate()", 1000);
// }

//获取当前时间
var time1="";
var time2="";
var time3="";
var time4="";
function writeCurrentDate() {
	var now = new Date();
	var year = now.getFullYear(); //得到年份
	var month = now.getMonth();//得到月份
	var day = now.getDay();//得到周几
	var date ;
	if (now.getDate()>9){
		date = now.getDate();//得到日期
	}else{
		date ="0"+now.getDate();//得到日期
	}
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	var month1 =month;
	month = month + 1;
	if (month>9){
		month = month;
	}else {
		month ="0"+month;
		month1 ="0"+month1;
	}
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + week;
	$("#currentDate").html(time);
	time1=year+"-"+month+"-"+date;
	time2=year+"-"+month+"-"+(date-1);
	time3=year+"-"+month1+"-"+date;
	//设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。
	var timer = setTimeout("writeCurrentDate()", 1000);
}
/**
 * 页面初始化函数
 */
var allClaomant;
var organizaType;
var userRole;
$(function(){

	_all_win_searchForm.xform('createForm',_searchForm); 
	_all_div_hidden.xform('createhidden',_searchForm.hiddenitems);
  _all_win_tools_but.xtoolbar('create',_Toolbar); 
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-95; 
  _DataGrid.height=_all_datagrid_height;
  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid);

  writeCurrentDate()
  laydate({
		elem: '#placeOrderTime_start',
		format: 'YYYY-MM-DD' //日期格式
	});
laydate({
		elem: '#placeOrderTime_end',
		format: 'YYYY-MM-DD' //日期格式
	});
  //main_search();


	//设置搜索框字体大小
	  if($("#orderCode").val() !=null){
		  $("#orderCode").css("font-size","13px");
	  }

	  if($("#purchaseUnit").val() !=null){
		  $("#purchaseUnit").css("font-size","13px");
	  }
	  //设置下拉框字体大小
	  if($("#processStatus").val() !=null){
		  $("#processStatus").css("font-size","13px");
	  }
	  if($("#placeOrderTime_start").val() !=null){
		  $("#placeOrderTime_start").css("font-size","13px");
	  }
	  if($("#placeOrderTime_end").val() !=null){
		  $("#placeOrderTime_end").css("font-size","13px");
	  }
	  
	  //设置按钮宽度与图标随文字居中
	  $("#win_but_search").css("width","95px");
	  $("#win_but_search").css("vertical-align","middle");
	  $("#win_but_add").css("width","95px");
	  $("#win_but_add").css("vertical-align","middle");
	  
	  //新增回车查询 2019-12-3 yanglei
	 $("#orderCode").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
	//
	 var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
	 if(selOutOrderType != null && selOutOrderType.stateTime != null){
	 	allClaomant=selOutOrderType.stateTime;
		$.supper("setProductArray", {"name":"selOutOrderType", "value":null});
	}
	 if (allClaomant==1) {
		 $("#placeOrderTime_start").val(time1);
		 $("#placeOrderTime_end").val(time1);
		// $("#win_but_search").trigger("click");
	 }else if (allClaomant==2) {
		 $("#placeOrderTime_start").val(time2);
		 $("#placeOrderTime_end").val(time2);
	 }else if(allClaomant==3){
		 $("#placeOrderTime_start").val(time3);
		 $("#placeOrderTime_end").val(time1);
	 }else {
		 $("#placeOrderTime_start").val();
		 $("#placeOrderTime_end").val();
	 }

	main_search();
	initInputSelect();
	if (_user_type == 2 || _user_org_type == 100) {
		$("#applicantName").val(_win_main_orgName);
		$("#applicantName").attr("readonly","readonly");
		// main_search();
	}

	// $("#applicantName").val("23");
	// alert($("#applicantName").val());


	//设置搜索框字体大小
	if($("#applicantName").val() !=null){
		$("#applicantName").css("font-size","13px");
	}
});
function initInputSelect(){
	$('#applicantName').editableSelect({
		keyEnter: function () {
			$("#win_but_search").trigger("click");
		}
	});
	$('#purchaseUnit').editableSelect({
		keyEnter: function () {
			$("#win_but_search").trigger("click");
		}
	});
	var data = _all_win_searchForm.serialize();
	_applicantNameSelectAction.data = data + '&distinctName=applicantName';
	_purchaseUnitSelectAction.data = data + '&distinctName=purchaseUnit';
	$('#applicantName').on('focus', function () {
		$('#applicantName').editableSelect('clear');
		var shqJson=$.supper("doselectService", _applicantNameSelectAction);
		var items = shqJson.items;
		for(var  i = 0 ;i < items.length ;i++){
			$('#applicantName').editableSelect('add', items[i])
		}
		$('#applicantName').editableSelect('show');
	})
	$('#purchaseUnit').on('focus', function () {
		$('#purchaseUnit').editableSelect('clear');
		var shqJson=$.supper("doselectService", _purchaseUnitSelectAction);
		var items = shqJson.items;
		for(var  i = 0 ;i < items.length ;i++){
			$('#purchaseUnit').editableSelect('add', items[i])
		}
		$('#purchaseUnit').editableSelect('show');
	})
}
function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"viewOrder('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>查看</a>&nbsp;&nbsp;";
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

// function export_all(){
// 	var vdata = _all_win_searchForm.serialize();
// 	var newTab=window.open('about:blank');
// 	$.supper("doservice", {"service":"MdOrderInfoService.exportCgOrderList","data":vdata, "BackE":function (jsondata) {
// 		if (jsondata.code == "1") {
// 			newTab.location.href=jsondata.obj.path;
// 		}else
// 			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
//  	}});
// }
// function main_export() {
// 	var vdata="&salesmanIds=''";
// 	var newTab=window.open('about:blank');//ExportExcelService   //SysSalesmanService
// 	$.supper("doservice", {"service":"ExportExcelService.exportSalesManAll","data":vdata, "BackE":function (jsondata) {
// 			if (jsondata.code == "1") {
// 				newTab.location.href=jsondata.obj.path;
// 			}else
// 				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
// 		}});
// }
function export_all(){
	var vdata = _all_win_searchForm.serialize();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"ExportExcelService.exportCgOrderList","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				newTab.location.href=jsondata.obj.path;
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}});
}


function viewOrder(moiId){
	var vdata="moiId="+moiId;
	var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/viewCgyOrderInfo"});
	$.supper("showTtemWin",{ "url":att_url,"title":"查看订单"});
}
