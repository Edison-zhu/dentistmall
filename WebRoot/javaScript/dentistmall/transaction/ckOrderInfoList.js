var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdOrderInfoService.getPagerModelObjectForCk";
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
	viewCkOrder(data.moiId);
}
var _applicantNameService = 'MdOrderInfoService.getPagerModelObjectForCkDistinct';
var _applicantNameSelectAction = {serviceName: _applicantNameService};

//供应商查询条件
var _searchForm={ 
		lineNum:2,
		columnNum:2,
		control:[],
		groupTag:[], 
		items:[ 
			   {title:"订单编号",name:"orderCode",type:"text",placeholder:"输入订单编号,点击查询"}, 
			   {title:"运单号",name:"expressCode",type:"text", placeholder:"输入运单号,点击查询"},
			   {title:"供应商名称",name:"applicantName",type:"text", placeholder:"输入供应商名称,点击查询"},
			   {title:"订单状态",name:"processStatus",type:"select",impCode:"PAR171023031218563", placeholder:"订单状态"}
		]	
	}
	 
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search}
		       ] 
	} 
 

//供应商grid
var _DataGrid = {
	cols: [
		{title: '订单编号', sortable: true, name: 'orderCode', width: 80, align: 'center'},
		{title: '下单时间', sortable: true, name: 'placeOrderTime', width: 80, align: 'center'},
		{title: '供应商名称', sortable: true, name: 'applicantName', width: 150, align: 'center'},
		{title: '采购人', sortable: true, name: 'purchaseAccount', width: 50, align: 'center'},
		{title: '运单号', sortable: true, name: 'expressCode', width: 80, align: 'center'},
		{title: '下单数量', sortable: true, name: 'commodityNumber', width: 50, align: 'center'},
		{title: '发货数量', sortable: true, name: 'number2', width: 50, align: 'center'},
		{title: '入库数量', sortable: true, name: 'number3', width: 50, align: 'center'},
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
	, gridtype: '2'
	, nowrap: true
	, url: getMMGridUrl()
	, mmPaginatorOpt: _all_win_datagrid_pg
	, dblClickFunc: _dblClick
}

/**
 * 页面初始化函数
 */
var organizaType;
var userRole;
var allClaomant;
$(function(){ 
	_all_win_searchForm.xform('createForm',_searchForm); 
	_all_div_hidden.xform('createhidden',_searchForm.hiddenitems);
	_all_win_tools_but.xtoolbar('create',_Toolbar); 
  
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-95; 
  _DataGrid.height=_all_datagrid_height;
  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid); 
 // main_search();
//设置查询按钮大小
  $("#win_but_search").css("width","95px");
  $("#win_but_search").css("vertical-align","middle");
  //设置搜索框字体大小
  if($("#orderCode").val() !=null){
	  $("#orderCode").css("font-size","13px");
  }
  if($("#expressCode").val() !=null){
	  $("#expressCode").css("font-size","13px");
  }
  if($("#applicantName").val() !=null){
  	  $("#applicantName").css("font-size","13px");
    }
  if($("#processStatus").val() !=null){
  	  $("#processStatus").css("font-size","13px");
    }
  //新增回车查询 2019-12-4 yanglei
	 $("#orderCode").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
	//  $("#applicantName").on('keydown', function(){
	// 	  if (event.keyCode==13) {
	// 		  $("#win_but_search").trigger("click");
	// 	  }
	// });
	 $("#expressCode").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_b" +
				  "ut_search").trigger("click");
		  }
	});
	applicantNameInputSelect();
	var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
	if(selOutOrderType != null && selOutOrderType.flowState != null){
		allClaomant=selOutOrderType.flowState;
		if (selOutOrderType.flowState1!=null) {
			allClaomant=selOutOrderType.flowState+","+selOutOrderType.flowState1;
		}
		$.supper("setProductArray", {"name":"selOutOrderType", "value":null});
	}
	$("#processStatus").val(allClaomant);
	main_search();
});
function applicantNameInputSelect() {
	$('#applicantName').editableSelect({
		keyEnter: function () {
			$("#win_but_search").trigger("click");
		}
	});
	var data = _all_win_searchForm.serialize();
	_applicantNameSelectAction.data = data + '&distinctName=applicantName';
	$('#applicantName').on('focus', function () {
		$('#applicantName').editableSelect('clear');
		var shqJson=$.supper("doselectService", _applicantNameSelectAction);
		var items = shqJson.items;
		for(var  i = 0 ;i < items.length ;i++){
			$('#applicantName').editableSelect('add', items[i])
		}
		$('#applicantName').editableSelect('show');
	})
}
function formatMoney(val,item,rowIndex){
	if(val != null && val!="")
		return toDecimal2(val);
	else
		return "";
}
function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"viewCkOrder('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>查看</a>&nbsp;&nbsp;";
	var number2 = item.number2?item.number2:0;
	var number3 = item.number3?item.number3:0;
	if(item.processStatus!='6' && item.processStatus!='5' && number3 < number2)
		str += "<a style='display: none' onclick=\"createEnter('"+  idvalue+"')\"  class='btn btn-info  btn-xs'>入库</a>&nbsp;&nbsp;";
	else
		str += "<a style='display: none' class='btn btn-default  btn-xs' disabled=true>入库</a>&nbsp;&nbsp;";
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
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function viewCkOrder(moiId){
	var vdata="moiId="+moiId;
	var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/viewCkOrderInfo"});
	$.supper("showTtemWin",{ "url":att_url,"title":"查看订单"});
}

function createEnter(moiId){
	var vdata="moiId="+moiId;
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/storage/editMdEnterWarehouser.jsp","data":vdata});
	var tt_win=$.supper("showWin",{url:att_url,title:"订单入库信息",icon:"fa-calendar",width:800,height:600, BackE:main_search}); 

}


 