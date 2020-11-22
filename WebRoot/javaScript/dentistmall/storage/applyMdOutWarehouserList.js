var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_wait_main = $('#win_datagrid_wait_main');
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_win_datagrid_wait_pg = $("#_all_win_datagrid_wait_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdOutWarehouseService.getPagerModelObject";
// var _wait_queryAction = "MdOutOrderService.getPagerMdOutOrder";
var _wait_queryAction = "MdOutOrderService.getPagerMdOutInfo";

var _all_deleteAction = "MdOutWarehouseService.deleteObject";
var _all_deleteAllAction = "MdOutWarehouseService.deleteAllObject";

var _all_win_datagrid;
var _all_win_datagrid_wait;
var _all_win_url_edit = "/jsp/dentistmall/storage/editApplyMdOutWarehouser.jsp";
var _all_win_url_view = "/jsp/dentistmall/storage/viewApplyMdOutWarehouser.jsp";
// var _all_table_Id = "wowId";
var _all_table_Id = "mooId";
var _all_table_wait_id = 'mooCode';
var _all_edit_icon = "calendar";
var _all_edit_title = "新增出库";
var _all_edit_width = 1000;
var _all_edit_height = 600;
var _all_datagrid_height ;
var _dblClick = function (data, row, col) {
	main_edit(data.wowId)
}
var _outdblClick = function (data, row, col) {
	selOutOrder(row);
}
var _NameService = 'MdOutWarehouseService.getPagerModelObjectDistinct';
var _NameSelectAction = {serviceName: _NameService};
var _customerNameSelectAction = {serviceName: _NameService};
var _waitNameService = 'MdOutOrderService.getPagerMdOutOrderDistinct';
var _waitNameSelectAction = {serviceName: _waitNameService};
var _waitUserNameSelectAction = {serviceName: _waitNameService};
var _all_cahnge_type = '1';
// 单号关键字、申领部门（需要从系统中读取部门信息）、申领人（关键字或拼音首字母）、申请日期（开始及结束）、出库日期（开始及结束），状态（所有、申领中、已完成、撤销）默认搜索状态给出所有。
var _searchForm={ 
	lineNum:2,
	columnNum:3,
	control:[],
	groupTag:[],
	hiddenitems:[
		{title:"出库单类型",name:"companyType",type:"hidden",value:'1',placeholder:"出库单类型"}
	],
	items:[
		// {title:"出库单号",name:"wowCode",type:"text",placeholder:"出库单号"},
		{title:'申领单号', id: 'mooCodeSearchInput', name:'mooCode', width:80, placeholder:"输入申领单号,回车查询"},
		// {title:"申领单号", id: 'relatedBill1SearchInput', name:"relatedBill1", placeholder:"申领单号"},
		{title:"申领部门", id:'customer', name:"customer",type:"text",placeholder:"输入申领部门,回车查询"},
		{title:"申领人", id: 'customerName', name:"customerName",type:"text",placeholder:"输入申领人,回车查询"},
		{title:"申领日期范围", name:"orderTime_str", type:'userdefined', readOnly:true, renderer: orderTimeInput},
		{title:"出库日期范围", name:"outTime_str", type:'userdefined', readOnly:true, renderer: outTimeInput},
		{title:'申领状态', name:'flowState', width:80, type: 'select',impCode:"PAR171113111313225", placeholder:"订单状态"},
	]
}

function waitCodeInput(){
	var tt = '<input type="text" id="mooCodeSearchInput"/>';
	return tt;
}
function readyCodeInput(){
	var tt = '<input type="text" id="relatedBill1SearchInput" />';
	return tt;
}
function orderTimeInput(){
	var startTime = '<input type="text" placeholder="选择开始日期" id="orderTimeStart" name="orderTime_start" readonly class="form-control" style="width: 48%;display: inline"/> - ';
	var endTime = '<input type="text" placeholder="选择结束日期" id="orderTimeEnd" name="orderTime_end" readonly class="form-control" style="width: 48%;display: inline"/>';
	return startTime + endTime;
}
function outTimeInput() {
	var startTime = '<input type="text" placeholder="选择开始日期" id="outTimeStart" name="outTimeStart" readonly class="form-control" style="width: 48%;display: inline"/> - ';
	var endTime = '<input type="text" placeholder="选择结束日期" id="outTimeEnd" name="outTimeEnd" readonly class="form-control" style="width: 48%;display: inline"/>';
	return startTime + endTime;
}
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"&nbsp查询&nbsp",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:searchByType},
		        {title:"批量导出",id:"win_but_exportC",icon:"download", colourStyle:"success",rounded:true,clickE:export_allC},
		       // {title:"导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all},
		       // {title:"添加",id:"win_but_add",icon:"plus", colourStyle:"primary",rounded:true,clickE:main_add},
		        {title:"批量导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all}
		       ] 
	}
var _DataGrid={
    cols: [
        {title:'出库时间', sortable: true, name:'finshDate',width:80, align: 'center'},
        {title:'出库单号', sortable: true, name:'wowCode', width:80, align: 'center'},
        {title:'申领单号', sortable: true, name:'relatedBill1', width:80, align: 'center'},
        {title:'申领时间', sortable: true, name:'orderTime', width:80, align: 'center'},
        {title:'申领状态', sortable: true, name:'flowState', width:80, align: 'center',impCode:"PAR171113111313225"},
        {title:'申领人', sortable: true, name:'customerName',width:80, align: 'center'},
        {title:'申领部门', sortable: true, name:'customer',width:80, align: 'center'},
        {title:'申领总数', sortable: true, name:'allNumber', width:80, align: 'center'},
        {title:'实际出库数量', sortable: true, name:'alreadyNumber', width:80, align: 'center'},
		{title:'缺少数量', sortable: true, name:'missingNumber', width:40, align: 'center', renderer: formateOutWareMissNumberInp},
        {title:'操作人', sortable: true, name:'userName',width:80, align: 'center'},
        {title:'操作' ,name:'control',width:100,  align:'center',renderer:control }
    ]
	, remoteSort: false
    ,name:'mdOutWarehouserListGrid'
    ,height:_all_datagrid_height
    , url:getMMGridUrl()
    , nowrap:true
    ,mmPaginatorOpt:_all_win_datagrid_pg
	, dblClickFunc: _dblClick
}
//2020 05-17 修改
var _Wait_DataGrid = {
	cols: [
		//{title:'申领单号', sortable: true, name:'mooCode', width:80, align: 'center'},
		{title:'申请出库时间', sortable: true, name:'orderTime', width:100, align: 'center'},
		{title:'申请单号', sortable: true, name:'mooCode',width:80, align: 'center'},
		{title:'申请出库部门', sortable: true, name:'groupName',width:100, align: 'center'},
		{title:'申请出库人', sortable: true, name:'userName', width:80, align: 'center'},
		{title:'申请总数', sortable: true, name:'number1', width:30, align: 'center', renderer: renderNumber1},
		{title:'实际出库数', sortable: true, name:'number2', width:30, align: 'center',renderer:actual},
		{title:'缺少数', sortable: true, name:'alreadyNumber', width:30, align: 'center',renderer: formateOrderOutMissNumberInp},
		{title:'出库类型', sortable: true, name:'wowType', width:40, align: 'center', renderer: renderWowType},
		{title:'出库编号', sortable: true, name:'wowCode', width:40, align: 'center'},
		{title:'出库时间', sortable: true, name:'finishDate', width:40, align: 'center'},
		{title:'状态', sortable: true, name:'flowState', width:30, align: 'center',impCode:"PAR171113111313225"},
		{title:'接收人', sortable: true, name:'receivingObject', width:30, align: 'center'},
		{title:'出库备注', sortable: true, name:'wowRemarks', width:40, align: 'center'},
		{title:'制作人', sortable: true, name:'userName', width:45, align: 'center'},
		{title:'操作信息', name:'controlXinxi', width:150, align: 'center', renderer: selectOutOrder}//$("#controlXinxi").css("width","500px");
	]
	, width: '100%'
	, remoteSort: false
	, name: 'mdOutWarehouserListGrid'
	, height: _all_datagrid_height
	, url: getMMGridWaitUrl()
	, nowrap: true
	, dblClickFunc: _outdblClick
	, mmPaginatorOpt: _all_win_datagrid_wait_pg
}

function renderNumber1(val, item, rowIndex) {
	// console.log(JSON.stringify(item))
	// if (item.wowType == undefined || item.wowType == 1) {
	// 	if (item.flowState == 4 && item.number1 != undefined && item.number1 > item.number2) {
	// 		return item.number2;
	// 	}
	// 	if ((item.number1 == undefined || item.number1 == '') && (item.number6 != undefined || item.number6 != ''))
	// 		return item.number6;
	// } else {
	// 	if (item.flowState == 4 && item.number1 != undefined && item.number1 > item.number2) {
	// 		return item.number2;
	// 	}
	// 	if (item.number1 == undefined || item.number1 == '')
	// 		return item.number2;
	// }
	var tt = '-';
	tt = '<span>' + item.number1 + '</span>';//+'/'+'<span>' + (item.number6 == undefined ? 0 : item.number6) + '</span>';
	return tt;
}
function actual(val, item, rowInde) {
	if (item.number7 == undefined){
		item.number7=0;
	}
	var tt = '-';
	tt = '<span>' + (item.number2 == undefined ? 0 : item.number2);// + '</span>'+'/'+'<span>' + item.number7 + '</span>';
	return tt;
}
function formateOrderOutMissNumberInp(val, item, rowInde){
	// if (item.flowState == 5)
	// 	return 0;
	// if (item.flowState == 4 && item.number1 != undefined && item.number1 > item.number2) {
	// 	return 0;
	// }
	// var tt = '-';
	// var leftNumber = (item.number1 == undefined ? 0 : item.number1) - (item.number2 == undefined ? 0 : item.number2);
	// let leftSplit = (item.number6 == undefined ? 0 : item.number6) - (item.number7 == undefined ? 0 : item.number7);
	// if (item.mooCode != undefined && item.mooId == undefined) {
	// 	leftNumber = item.missNumber;
	// }
	// if(leftNumber > 0){
	// 	tt = '<span style="color: red;">' + leftNumber + '</span>';
	// } else if (leftSplit > 0) {
	// 	tt = '<span style="color: red;">' + leftSplit + '</span>';
	// }
	// return tt;
	var tt = '-';
	var leftNumber = item.number1 - item.number2;
	if (item.number7 == undefined){
		item.number7=0;
	}
	var leftNumbers = (item.number6 == undefined ? 0 : item.number6) - item.number7;
	if(leftNumber > 0){
		tt = '<span>' + leftNumber + '</span>';//+'/'+'<span>' + leftNumbers + '</span>';
	}
	return tt;
}

function renderWowType(val, item, rowInde) {
	let str = '';
	if (item.wowType == 2 || item.wowType == 4)
		str = '退货出库';
	else if (item.wowType == 3)
		str = '报损出库';
	else
		str = '领料出库';
	return str;
}
//缺少数量
function formateOutWareMissNumberInp(val, item, rowInde){
	var tt = '-';
	var leftNumber = item.allNumber - item.alreadyNumber;
	if(leftNumber > 0){
		tt = '<span style="color: red;">' + leftNumber + '</span>';
	}
	return tt;
}
function formateOutWarenumber2(val, item, rowInde){
	var tt = '-';
	var leftNumber = item.allNumber - item.alreadyNumber;
	if(leftNumber > 0){
		tt = '<span style="color: red;">' + leftNumber + '</span>';
	}
	return tt;
}

var allClaomant;
/**
 * 页面初始化函数
 */
$(function(){

	_all_win_searchForm.xform('createForm',_searchForm);

	_all_div_hidden.xform('createhidden',_searchForm.hiddenitems);

	_all_win_tools_but.xtoolbar('create',_Toolbar);

	_all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-140;

	_DataGrid.height=_all_datagrid_height;

	_Wait_DataGrid.height=_all_datagrid_height;
	_all_win_datagrid = _all_win_datagrid_main.xdatagrid('create',_DataGrid);
	_all_win_datagrid_wait = _all_win_datagrid_wait_main.xdatagrid('create',_Wait_DataGrid);
	intiWaitDataGrid();
	layui.use('laydate', function () {
		var laydate = layui.laydate;
		//执行一个laydate实例
		laydate.render({
			elem: '#outTimeEnd'
			, type: 'datetime'
		});
		laydate.render({
			elem: '#outTimeStart'
			, type: 'datetime'
		});
		laydate.render({
			elem: '#orderTimeEnd'
			, type: 'datetime'
		});
		laydate.render({
			elem: '#orderTimeStart'
			, type: 'datetime'
		});
	});
	showOrHideReady(false);
	showOrHideWait(true);

	_all_win_datagrid_wait.on('loadSuccess', view_wait);
	_all_win_datagrid.on('loadSuccess', view_ready);
	//2020年07月03日14:03:47朱燕冰（设置页面加载全部类型高亮显示）
	$("#allColor1").addClass("a-click");
	$("#allColors1").addClass("a-click");

	 //设置按钮宽度与图标随文字居中
	  $("#win_but_search").css("width","95px");
	  $("#win_but_search").css("vertical-align","middle");
	  
	  //设置搜索框字体大小
	  if($("#mooCodeSearchInput").val() !=null){
		  $("#mooCodeSearchInput").css("font-size","13px");
	  }
	//设置搜索框字体大小
	  if($("#customer").val() !=null){
		  $("#customer").css("font-size","13px");
	  }
	  if($("#customerName").val() !=null){
		  $("#customerName").css("font-size","13px");
	  }
	  if($("#orderTime_str").val() !=null){
		  $("#orderTime_str").css("font-size","13px");
	  }
	  if($("#outTime_str").val() !=null){
		  $("#outTime_str").css("font-size","13px");
	  }
	  if($("#flowState").val() !=null){
		  $("#flowState").css("font-size","13px");
	  }
	 //查询连接回车
	  $("#mooCodeSearchInput").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
	initFormInputSelect()
	// 首页跳转查询
	var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
	if (selOutOrderType != null && JSON.stringify(selOutOrderType) !== '{}') {
		if (selOutOrderType.warningMoo != undefined) {
			let mooIds = selOutOrderType.mooIds;
			mooId_str = mooIds;
			main_wait_search();
		} else {
			allClaomant = selOutOrderType.flowState;
			if (selOutOrderType.flowState1 != null) {
				allClaomant = selOutOrderType.flowState + "," + selOutOrderType.flowState1;
			}
			let wowType = selOutOrderType.wowType;
			let flowState = selOutOrderType.flowState;
			let warning = selOutOrderType.warning;
			if (flowState != undefined) {
				aSearchFlowStateFilter($('#waitOut'), 2, true);
				changes(6);
			} else if (wowType != undefined) {
				aSearchWowTypeFilter($('#afterOrder'), 2, true);
				changes(2);
			} else if (warning != undefined)
				main_wait_search(warning);
		}
		$.supper("setProductArray", {"name": "selOutOrderInfo", "value": null});
	} else {
		// $("#flowState").val(allClaomant);
		//main_search();
		main_wait_search();
	}
});
var  wowType2;
var flowState2;
function initCount(data,i) {
	let vdata = '';
	if (data != undefined)
		vdata = data;
	if (mooId_str != undefined && mooId_str != '')
		vdata += '&mooId_str=' + mooId_str;
	$.supper("doservice", {
		"service": 'MdOutWarehouseService.getOutWareHouseCount', "data": vdata, "BackE": function (jsondata) {
			if (jsondata.code == "1") {
				let val = jsondata.obj;
				if (i==0){
					wowType2=val.wowType;
					flowState2=val.flowState;
					$('#span1 span').each(function (i, v) {
						var att_name = $(v).attr("id");
						eval("var vals=val." + att_name + ";");
						if (vals != undefined) {
							$(v).text(vals);
						}
					})
					$('#span2 span').each(function (i, v) {
						var att_name = $(v).attr("id");
						eval("var vals=val." + att_name + ";");
						if (vals != undefined) {
							$(v).text(vals);
						}
					})
				}
				$("#wowType0").text(wowType2);
				$("#flowState0").text(flowState2);
				if (i==1){
					$('#span2 span').each(function (i, v) {
						var att_name = $(v).attr("id");
						eval("var vals=val." + att_name + ";");
						if (vals != undefined) {
							$(v).text(vals);
						}
					})
				}
				if (i==2) {
					$('#span1 span').each(function (i, v) {
						var att_name = $(v).attr("id");
						eval("var vals=val." + att_name + ";");
						if (vals != undefined) {
							$(v).text(vals);
						}
					})
				}

			}
		}
	});
}

function initFormInputSelect(){
	$('#customer').editableSelect({
		keyEnter: function () {
			$("#win_but_search").trigger("click");
		}
	});

	$('#customerName').editableSelect({
		keyEnter: function () {
			$("#win_but_search").trigger("click");
		}
	});

	var data = _all_win_searchForm.serialize();
	_waitNameSelectAction.data = data + '&distinctName=groupName';
	_NameSelectAction.data = data + '&distinctName=customer';
	$('#customer').on('focus', function () {
		$('#customer').editableSelect('clear');
		var shqJson = [];
		if(_all_cahnge_type == '1'){
			shqJson=$.supper("doselectService", _waitNameSelectAction);
		}else {
			shqJson=$.supper("doselectService", _NameSelectAction);
		}
		var items = shqJson.items;
		for(var  i = 0 ;i < items.length ;i++){
			$('#customer').editableSelect('add', items[i]);
		}
		$('#customer').editableSelect('show');
	})
	_waitUserNameSelectAction.data = data + '&distinctName=userName';
	_customerNameSelectAction.data = data + '&distinctName=customerName';
	$('#customerName').on('focus', function () {
		$('#customerName').editableSelect('clear');
		var shqJson = [];
		if(_all_cahnge_type == '1'){
			shqJson=$.supper("doselectService", _waitUserNameSelectAction);
		}else {
			shqJson=$.supper("doselectService", _customerNameSelectAction);
		}
		var items = shqJson.items;
		for(var  i = 0 ;i < items.length ;i++){
			$('#customerName').editableSelect('add', items[i]);
		}
		$('#customerName').editableSelect('show');
	})
}
function view_wait(e, data) {
    // if(colIndex == 7){
	//
    // }else {
    //     selOutOrder(rowIndex);
    // }
	if(data === null || data === undefined){
		return;
	}
	$('#collectCount').text(data.total);
}

function view_ready(e, data){
	// if(colIndex == 11){
	//
	// }else {
	// 	main_edit(item.wowId);
	// }
	if(data === null || data === undefined){
		return;
	}
	$('#collectCount').text(data.total);
}

function showOrHideReady(showOrHide) {
    if(showOrHide){
    	$('#win_but_export').hide();
    	$('#win_but_exportC').show();
        // $('#readyFormSearch').show();
        $('#readyDiv').show();
        $('#mooCodeSearchInput').attr('name', 'relatedBill1');
		$('#customer').attr('name', 'customer');
        $('#customerName').attr('name', 'customerName');
        _all_win_datagrid.show();
    }else {
    	$('#win_but_export').show();
    	$('#win_but_exportC').hide();
       // $('#win_but_export').hide();
        // $('#readyFormSearch').hide();
        $('#readyDiv').hide();
		// $('#relatedBill1SearchInput').hide();
        if(_all_win_datagrid !== null && _all_win_datagrid !== undefined){
            _all_win_datagrid.hide();
        }
    }
}

function showOrHideWait(showOrHide){
    if(showOrHide){
        // $('#waitFormSearch').show();
        $('#waitDiv').show();
		$('#mooCodeSearchInput').attr('name', 'mooCode');
		$('#customer').attr('name', 'groupName');
		$('#customerName').attr('name', 'userName');
		_all_win_datagrid_wait.show();
    }else {
        // $('#waitFormSearch').hide();
        $('#waitDiv').hide();
		// $('#mooCodeSearchInput').hide();
        if(_all_win_datagrid_wait !== null && _all_win_datagrid_wait !== undefined){
            _all_win_datagrid_wait.hide();
        }
    }
}

//20191125 yangfeng 初始化表格数据
function initDataGrid(){
	// _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create',_DataGrid);
	showOrHideReady(true);
	showOrHideWait(false);
}

function intiWaitDataGrid(){
	// _all_win_datagrid_wait = _all_win_datagrid_wait_main.xdatagrid('create',_Wait_DataGrid);
	showOrHideReady(false);
	showOrHideWait(true);
}
var viewArray = {};
function control(val,item,rowIndex){
	var str = "";
	// eval( 'var idvalue=  item.'+_all_table_Id);
	//
	// viewArray[idvalue] = item;
	// str += "<a onclick=\"main_edit('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>查看</a>&nbsp;&nbsp;";
	// str += "<a onclick=\"main_export('"+  idvalue+"')\"  class='btn btn-success  btn-xs'>导出</a>&nbsp;&nbsp;";
   return str;
}

//选择未申领订单操作
function selectOutOrder(val, item, rowIndex){
	var tt ='';
	if (item.mooId == undefined)
		tt += '<a onclick="main_edit(' + rowIndex + ', ' + item.wowType + ')" class="btn btn-default btn-xs">详情</a>&nbsp;&nbsp;';
	else
		tt += '<a onclick="main_edit2(' + rowIndex + ')" class="btn btn-default btn-xs">详情</a>&nbsp;&nbsp;';
	if ((item.wowType == 1 || item.wowType == undefined)) {
		// tt += '<a onclick="main_edit2(' + rowIndex + ')" class="btn btn-default btn-xs">详情</a>&nbsp;&nbsp;';
		if (item.flowState == 2 || item.flowState == 3)
			tt += '<a onclick="selOutOrder(' + rowIndex + ')" class="btn btn-primary btn-xs">立即出库</a>&nbsp;&nbsp;';
		tt += '<a onclick="main_export(' + item.mooId + ',' + item.wowId + ')" class="btn btn-success btn-xs">导出</a>&nbsp;&nbsp;';
	} else {
		eval('var idvalue=  item.' + _all_table_Id);
		viewArray[idvalue] = item;

		if (item.moiId != undefined || item.wiId != undefined) // 老数据无法进行编辑
			tt += '<a onclick="selOutOrder1(' + rowIndex + ', ' + item.wowType + ')" class="btn btn-primary btn-xs">编辑</a>&nbsp;&nbsp;';
		tt += '<a onclick="main_export(' + item.mooId + ',' + item.wowId + ')" class="btn btn-success btn-xs">导出</a>&nbsp;&nbsp;';
	}
	return tt;
}

function selOutOrder(rowIndex){
	var item = _all_win_datagrid_wait.row(rowIndex);
	if(item==null){
		$.supper("alert",{ title:"操作提示", msg:"请选择数据！"});
		return;
	}
	$.supper("setProductArray", {"name":"selCodeInfo", "value":item});
	eval("var data= '"+_all_table_Id+"= " + item.mooId+ "'");
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	let mainSearch = main_wait_search;
	let d = {url: att_url, orderDel: 1, func: mainSearch}
	// if (newOrOne == true)
	// 	d.newOrOne = newOrOne;
	$.supper("setProductArray", {"name":"addNewOut", "value": d});
	var tt_win=$.supper("showTtemWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,area: ['98%', '90%'],width:1300,height:_all_edit_height, BackE:simulateClick});
	// main_add();
}
function selOutOrder1(rowIndex, wowType){
	var item = _all_win_datagrid_wait.row(rowIndex);
	if(item==null){
		$.supper("alert",{ title:"操作提示", msg:"请选择数据！"});
		return;
	}
	$.supper("setProductArray", {"name":"selCodeInfo", "value":item});
	eval("var data= 'wowId= " + item.wowId+ "'");
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	let d = {url: att_url, wowId: item.wowId, wowType: wowType, wiId: item.wiId, wewId: item.wewId, moiId: item.moiId}
	// if (newOrOne == true)
	// 	d.newOrOne = newOrOne;
	$.supper("setProductArray", {"name":"addNewOut", "value": d});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,area: ['98%', '90%'],width:1300,height:_all_edit_height, BackE:simulateClick});
	// main_add();
}
/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl(){	
	var data = _all_win_searchForm.serialize();
	var length = data.length;
	var index = data.lastIndexOf('flowState=');
	if(length - index == 11) {
		data = data.replace('flowState=1', 'flowState='); //flowState=1时不需要传值
	}
	data = data.replace('flowState=1&', 'flowState=&'); //flowState=1时不需要传值
	var att_url= $.supper("getServicePath", {"service":_all_queryAction, "data":data});
	return att_url;
}

var mooId_str = '';
function getMMGridWaitUrl(warning){
	var data = _all_win_searchForm.serialize();
	var length = data.length;
	var index = data.lastIndexOf('flowState=');
	if(length - index == 11) {
		data = data.replace('flowState=1', 'flowState='); //flowState=1时不需要传值
	}
	if (warning != undefined)
		data += '&warning=1';
	if (mooId_str != undefined && mooId_str != '')
		data += '&mooId_str=' + mooId_str;
	data = data.replace('flowState=1&', 'flowState=&'); //flowState=1时不需要传值
	var att_url = $.supper("getServicePath", {"service": _wait_queryAction, "data":data});
	return att_url;
}
/**
 * 对主表进行查询（刷新）操作
 */
// function main_search14() {
// 	_all_win_datagrid.opts.url = getMMGridUrl();
// 	_all_win_datagrid.load();
// }
function main_search(){
	_all_win_datagrid.opts.url = getMMGridUrl();
	_all_win_datagrid.load();
}


var isSave = false;
function simulateClick(){

	if(isSave){
		simulateReadyClick()
	} else{
		main_wait_search();
	}
}

function simulateReadyClick() {
	$('#readyOutWarehouser').click();
}
function simulateWaitClick() {
    $('#waitOutWarehouser').click();
}

function main_wait_search(warning){
	_all_win_datagrid_wait.opts.url = getMMGridWaitUrl(warning);
	_all_win_datagrid_wait.load();
	var data = _all_win_searchForm.serialize();
	var length = data.length;
	var index = data.lastIndexOf('flowState=');
	if(length - index == 11) {
		data = data.replace('flowState=1', 'flowState='); //flowState=1时不需要传值
	}
	if (warning != undefined)
		data += '&warning=1';
	data = data.replace('flowState=1&', 'flowState=&'); //flowState=1时不需要传值
	initCount(data,0);
}

function searchByType(){
	// if(_all_cahnge_type == '1'){
	main_wait_search();
	// } else if(_all_cahnge_type =='2'){
	// 	main_search();
	// }
}


/**
 * 添加记录操作事件
 */
function main_add(newOrOne){
		var att_url= $.supper("getServicePath", {url:_all_win_url_edit});

	let d = {url: att_url}
		if (newOrOne == true)
			d.newOrOne = newOrOne;
	$.supper("setProductArray", {"name":"addNewOut", "value": d});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:1300,height:_all_edit_height, BackE:simulateClick});
	//	$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
}
//新增出库
function main_add1() {
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/storage/newApplyMdOutWarehouser.jsp"});
	
	// if (newOrOne == true)
	// 	d.newOrOne = newOrOne;
	$.supper("setProductArray", {"name":"addNewOut", "value": {url: att_url}});
	// var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,area: ['80%', '90%'],width:1300,height:_all_edit_height, BackE:simulateClick});
	var tt_win=$.supper("showTtemWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,area: ['80%', '90%'],width:1300,height:_all_edit_height, BackE:simulateClick});
}
/**
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function main_edit(id, wowType){
	let item = _all_win_datagrid_wait.row(id);
	$.supper("setProductArray", {"name":"selCodeInfo", "value":item});
	eval("var data= 'wowId= " + item.wowId+ "'");
	// var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_view});
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	let d = {url: att_url, wowId: item.wowId, wowType: wowType, wiId: item.wiId, moiId: item.moiId, wewId: item.wewId, view: 1}
	// if (newOrOne == true)
	// 	d.newOrOne = newOrOne;
	$.supper("setProductArray", {"name":"addNewOut", "value": d});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,area: ['80%', '90%'],width:1300,height:_all_edit_height, BackE:main_search});
	//$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
}

function main_edit2(rowIndex) {
	var item = _all_win_datagrid_wait.row(rowIndex);
	$.supper("setProductArray", {"name":"selCodeInfo", "value":item});
	$.supper("setProductArray", {"name":"addNewOut", "value": {view1: 1, oth: 1}});
	// $.supper("setProductArray", {"name":"selCodeInfo", "value":item});
	eval("var data= '"+_all_table_Id+"= " + item.mooId+ "'");
	// var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_view});
	var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,area: ['80%', '90%'],width:1300,height:_all_edit_height, BackE:main_search});
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
				$.supper("alert",{ title:"提示", msg:"操作失败！"});
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

//20191125 yangfeng 改变tab页查询显示
function changeOutWarehouserType(changType) {
	if(changType == '2'){ //待申领
		_all_cahnge_type = '2';
        intiWaitDataGrid();
        main_wait_search();
	} else if(changType == '1'){ //已申领
		_all_cahnge_type = '1';
        initDataGrid();
        main_search();
	}
}

function main_export(id,wowId){
	 // eval("var vdata= '"+_all_table_Id+"="+id+"'");
	// var vdata="&mooId="+id+"&wowId="+wowId;
	var vdata='';
	if (id!=null){
		vdata="&mooId="+id;
	}else if (wowId!=null) {
		vdata="&wowId="+wowId;
	}
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"enterWarehouseExportService.exportOutWarehousing","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
function export_all(){
	var mooCode= $("#mooCodeSearchInput").val();
	var customer= $("#customer").val();
	var customerName= $("#customerName").val();
	var orderTimeStart= $("#orderTimeStart").val();
	var orderTimeEnd= $("#orderTimeEnd").val();
	var outTimeStart= $("#outTimeStart").val();
	var orderTimeStart= $("#orderTimeStart").val();
	var outTimeEnd= $("#outTimeEnd").val();
	var flowState= $("#flowState").val();
	var rows=_all_win_datagrid_wait.selectedRows();
	if(rows ==null || rows.length ==0){
		// if(mooCode!=null&&mooCode!=undefined){
		// 	console.log(mooCode);
		// }
		$.supper("confirm",{ title:"操作提示", msg:"您未选择要导出的数据，系统将所有数据导出是否确定导出？" ,yesE:function(){
				var mooIds="";
				var wowIds='';
				for ( var int = 0; int < rows.length; int++) {
					// if (rows[int].wowId!=undefined){
					// 	wowIds += rows[int].wowId+",";
					// }
					if (rows[int].mooId!=undefined) {
						mooIds +=rows[int].mooId+",";
					}
				}
				mooIds=mooIds.substring(0,mooIds.length-1);
				var mooId1='';
				var count1=$("#collectCount").text();
				vdata="&count="+count1+"&stateMooId="+1+"&mooIds="+1+"&wowIds="+0+"&mooCode="+mooCode+"&customer="+customer+"&customerName="+customerName+"&orderTimeStart="+orderTimeStart+"&orderTimeEnd="+orderTimeEnd+"&outTimeStart="+outTimeStart+"&outTimeEnd="+outTimeEnd+"&flowState="+flowState;
				var newTab=window.open('about:blank');
				$.supper("doservice", {"service":"EnterWarehouseExportService.plExportOutWarehousing","data":vdata, "BackE":function (jsondata) {
						if (jsondata.code == "1") {
							newTab.location.href=jsondata.obj.path;
						}else
							$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
					}});
			}});
		return;
	}else {
		var mooIds="";
		var wowIds='';
		var flowStates='';
		var count=0;
		for ( var int = 0; int < rows.length; int++) {
			var flowState=rows[int].flowState;
			// if (flowState==2) {
			//
			// }
			// alert(flowState==2);
			// if (rows[int].wowId!=undefined){
			// 	wowIds += rows[int].wowId+",";
			// }
			// if (rows[int].wowId==undefined) {
			// 	mooIds +=rows[int].mooId+",";
			// }
			if (flowState==2||flowState==3) {
				mooIds +=rows[int].mooId+",";
			}else {
				wowIds += rows[int].wowId+",";
			}
			// alert(rows.length);
			count+=1;
		}
		// alert(count);
		// return;

		mooIds=mooIds.substring(0,mooIds.length-1);
		wowIds=wowIds.substring(0,wowIds.length-1);
		vdata="&count="+count+"&stateMooId="+0+"&mooIds="+mooIds+"&wowIds="+wowIds+"&mooCode="+mooCode+"&customer="+customer+"&customerName="+customerName+"&orderTimeStart="+orderTimeStart+"&orderTimeEnd="+orderTimeEnd+"&outTimeStart="+outTimeStart+"&outTimeEnd="+outTimeEnd+"&flowState="+flowState;
		var newTab=window.open('about:blank');
		$.supper("doservice", {"service":"EnterWarehouseExportService.plExportOutWarehousing","data":vdata, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					newTab.location.href=jsondata.obj.path;
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
			}});
	}
}
function export_allC(){
//	var rows=_all_win_datagrid_wait.selectedRows();
	var rows=_all_win_datagrid.selectedRows();
	// if(rows ==null || rows.length ==0){
	// 	$.supper("alert",{ title:"操作提示", msg:"您暂时未选择要导出的数据，系统将所有数据导出是否确定导出？"});
	// 	return;
	// 	//rows=_all_win_datagrid.selectall();
	// }
	var wowIds="";
	for ( var int = 0; int < rows.length; int++) {
		wowIds += rows[int].wowId+",";
	}
	wowIds=wowIds.substring(0,wowIds.length-1);
	vdata="&wowIds="+wowIds;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"EnterWarehouseExportService.exportOutWarehousing","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
// var wowType1;
// function aSearchWowTypeFilter(selector,wowType) {
// 	$('#uiSpan a').each(function () {
// 		$(this).removeClass('a-click');
// 	})
// 	$(selector).addClass('a-click');
// 	var data = _all_win_searchForm.serialize();
// 	var length = data.length;
// 	var index = data.lastIndexOf('flowState=');
// 	if(length - index == 11) {
// 		data = data.replace('flowState=1', 'flowState='); //flowState=1时不需要传值
// 	}
//
// 	data = data.replace('flowState=1&', 'flowState=&'); //flowState=1时不需要传值
// 	let gridUrl = getMMGridWaitUrl();
// 	if (wowType != undefined) {
// 		wowType1=wowType;
// 		gridUrl += '&wowType=' + wowType;
// 		data += '&wowType=' + wowType;
// 	}
// 	_all_win_datagrid_wait.opts.url = gridUrl;
// 	_all_win_datagrid_wait.load();
// 	initCount(data);
// }
//
// //2020年07月03日14:12:23朱燕冰修改
//
// function aSearchFlowStateFilter(selector, flowState) {
// 	 console.log("=====>"+flowState)
// 	// $('#uiSpan a').each(function () {
// 	// 	$(this).removeClass('a-click');
// 	// })
// 	// $(selector).addClass('a-click');
// 	var data = _all_win_searchForm.serialize();
// 	var length = data.length;
// 	var index = data.lastIndexOf('flowState=');
// 	let gridUrl = getMMGridWaitUrl(flowState);
// 	if (flowState != undefined) {
// 		// gridUrl += '&flowState=' + flowState;
// 		if(length - index == 11) {
// 			data = data.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState); //flowState=1时不需要传值
// 		}
// 		data = data.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState + '&'); //flowState=1时不需要传值
//
// 		if(length - index == 11) {
// 			gridUrl = gridUrl.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState); //flowState=1时不需要传值
// 		}
// 		gridUrl = gridUrl.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState + '&'); //flowState=1时不需要传值
// 		// data += '&flowState=' + flowState;
// 		if (wowType1 != undefined) {
// 			gridUrl += '&wowType=' + wowType1;
// 			data += 'wowType=' + wowType1;
// 		}
//
// 	} else {
// 		if(length - index == 11) {
// 			data = data.replace('flowState=1', ''); //flowState=1时不需要传值
// 		}
// 		data = data.replace('flowState=1&', ''); //flowState=1时不需要传值
//
// 		if(length - index == 11) {
// 			gridUrl = gridUrl.replace('flowState=1', ''); //flowState=1时不需要传值
// 		}
// 		gridUrl = gridUrl.replace('flowState=1&', ''); //flowState=1时不需要传值
// 		if (wowType1 != undefined) {
// 			gridUrl += '&wowType=' + wowType1;
// 			data += '&wowType=' + wowType1;
// 		}
// 	}
// 	_all_win_datagrid_wait.opts.url = gridUrl;
// 	_all_win_datagrid_wait.load();
// 	initCount(data);
// }
var wowType1;
var flowState1;
function aSearchWowTypeFilter(selector,wowType, needCount) {
	$('#span1 a').each(function () {
		$(this).removeClass('a-click');
	})
	$(selector).addClass('a-click');
	var data = _all_win_searchForm.serialize();
	if (needCount != undefined)
		initCount(data,0);
	var length = data.length;
	var index = data.lastIndexOf('flowState=');
	// if(length - index == 11) {
	// 	data = data.replace('flowState=1', 'flowState='); //flowState=1时不需要传值
	// }
	// data = data.replace('flowState=1&', 'flowState=&'); //flowState=1时不需要传值
	let gridUrl = getMMGridWaitUrl();
	// if (flowState1 != undefined) {
	// 	// 	if(length - index == 11) {
	// 	// 		data = data.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState1); //flowState=1时不需要传值
	// 	// 	}
	// 	// 	data = data.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState1 + '&'); //flowState=1时不需要传值
	// 	//
	// 	// 	if(length - index == 11) {
	// 	// 		gridUrl = gridUrl.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState1); //flowState=1时不需要传值
	// 	// 	}
	// 	// 	gridUrl = gridUrl.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState1 + '&'); //flowState=1时不需要传值
	// 	// }else {
	// 	// 	if(length - index == 11) {
	// 	// 		data = data.replace('flowState=1', ''); //flowState=1时不需要传值
	// 	// 	}
	// 	// 	data = data.replace('flowState=1&', ''); //flowState=1时不需要传值
	// 	//
	// 	// 	if(length - index == 11) {
	// 	// 		gridUrl = gridUrl.replace('flowState=1', ''); //flowState=1时不需要传值
	// 	// 	}
	// 	// 	gridUrl = gridUrl.replace('flowState=1&', ''); //flowState=1时不需要传值
	// 	// }
	if (flowState1!=undefined&&flowState1!=1){
		gridUrl = gridUrl.replace(new RegExp('flowState=[0-9]?'), '');
		// data = data.replace(new RegExp('flowState=[0-9]?'), '');
		gridUrl += '&flowState=' + flowState1;
		// data += '&flowState=' + flowState1;
	}
	wowType1=wowType;
	if (wowType != undefined) {
		gridUrl += '&wowType=' + wowType;
		data += '&wowType=' + wowType;
	}
	_all_win_datagrid_wait.opts.url = gridUrl;
	_all_win_datagrid_wait.load();
	initCount(data,1);
}

//2020年07月03日14:12:23朱燕冰修改

function aSearchFlowStateFilter(selector, flowState, needCount) {
	$('#span2 a').each(function () {
		$(this).removeClass('a-click');
	})
	$(selector).addClass('a-click');
	var data = _all_win_searchForm.serialize();
	if (needCount != undefined)
		initCount(data,0);
	var length = data.length;
	var index = data.lastIndexOf('flowState=');
	let gridUrl = getMMGridWaitUrl(flowState);
	if (flowState != undefined) {
		flowState1=flowState;
		// gridUrl += '&flowState=' + flowState;
		if(length - index == 11) {
			data = data.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState); //flowState=1时不需要传值
		}
		data = data.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState + '&'); //flowState=1时不需要传值

		if(length - index == 11) {
			gridUrl = gridUrl.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState); //flowState=1时不需要传值
		}
		gridUrl = gridUrl.replace(new RegExp('flowState=[0-9]?'), 'flowState=' + flowState + '&'); //flowState=1时不需要传值
		// data += '&flowState=' + flowState;
		if (wowType1 != undefined) {
			gridUrl += '&wowType=' + wowType1;
			data += 'wowType=' + wowType1;
		}
	} else {
		flowState1=1;
		if(length - index == 11) {
			data = data.replace('flowState=1', ''); //flowState=1时不需要传值
		}
		data = data.replace('flowState=1&', ''); //flowState=1时不需要传值

		if(length - index == 11) {
			gridUrl = gridUrl.replace('flowState=1', ''); //flowState=1时不需要传值
		}
		gridUrl = gridUrl.replace('flowState=1&', ''); //flowState=1时不需要传值
		if (wowType1 != undefined) {
			gridUrl += '&wowType=' + wowType1;
			data += '&wowType=' + wowType1;
		}
	}
	_all_win_datagrid_wait.opts.url = gridUrl;
	_all_win_datagrid_wait.load();

}
var clickI;
function changes(i){
	console.log("i="+i)
	clickI = i;
	if (i == "1"){
		$("#allColor1").addClass("a-click");
		$("#allColor2").removeClass("a-click")
		$("#allColor3").removeClass("a-click")
		$("#allColor4").removeClass("a-click")
	}
	if (i == "2"){
		$("#allColor1").removeClass("a-click");
		$("#allColor2").addClass("a-click")
		$("#allColor3").removeClass("a-click")
		$("#allColor4").removeClass("a-click")
	}
	if (i == "3"){
		$("#allColor1").removeClass("a-click");
		$("#allColor2").removeClass("a-click")
		$("#allColor3").addClass("a-click")
		$("#allColor4").removeClass("a-click")
	}
	if (i == "4"){
		$("#allColor1").removeClass("a-click");
		$("#allColor2").removeClass("a-click")
		$("#allColor3").removeClass("a-click")
		$("#allColor4").addClass("a-click")
	}
	if (i == "5"){
		$("#allColors1").addClass("a-click");
		$("#allColors2").removeClass("a-click")
		$("#allColors3").removeClass("a-click")
		$("#allColors4").removeClass("a-click")
		$("#allColors5").removeClass("a-click")
	}
	if (i == "6"){
		$("#allColors1").removeClass("a-click");
		$("#allColors2").addClass("a-click")
		$("#allColors3").removeClass("a-click")
		$("#allColors4").removeClass("a-click")
		$("#allColors5").removeClass("a-click")
	}
	if (i == "7"){
		$("#allColors1").removeClass("a-click");
		$("#allColors2").removeClass("a-click")
		$("#allColors3").addClass("a-click")
		$("#allColors4").removeClass("a-click")
		$("#allColors5").removeClass("a-click")
	}
	if (i == "8"){
		$("#allColors1").removeClass("a-click");
		$("#allColors2").removeClass("a-click")
		$("#allColors3").removeClass("a-click")
		$("#allColors4").addClass("a-click")
		$("#allColors5").removeClass("a-click")
	}
	if (i == "9"){
		$("#allColors1").removeClass("a-click");
		$("#allColors2").removeClass("a-click")
		$("#allColors3").removeClass("a-click")
		$("#allColors4").removeClass("a-click")
		$("#allColors5").addClass("a-click")
	}
}