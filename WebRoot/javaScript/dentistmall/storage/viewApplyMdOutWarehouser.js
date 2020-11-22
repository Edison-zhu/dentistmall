var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
// var _all_table_Id="wowId";
var _all_table_Id="mooId";
var _all_questAction = "MdOutWarehouseService.findFormObject";
var _all_questOrderAction = 'mdOutOrderService.findFormObject';
var _saveForm={
	lineNum: 3,
	columnNum: 4,
	control: [],
	groupTag: [],
	hiddenitems: [
		{name: "wowId", id: "wewId", value: "", type: "hidden"},
		{name: "rbaId", id: "rbaId", value: "", type: "hidden"},
		{name: "rbsId", id: "rbsId", value: "", type: "hidden"},
		{name: "rbbId", id: "rbbId", value: "", type: "hidden"},
		{title: '创建时间', name: 'createDate', type: "hidden"},
		{title: '创建人', name: 'createRen', type: "hidden"},
		{title: '修改时间', name: 'editDate', type: "hidden"},
		{title: '修改人', name: 'editRen', type: "hidden"},
		{title: '修改人', name: 'flowState', type: "hidden"}
	],
	items: [
		{
			title: '出库单号',
			name: 'wowCode',
			placeholder: "出库单号",
			width: 120,
			align: 'center',
			readOnly: true,
			// prefixCode: "CK"
		},
		{title: '申领单号', name: 'mooCode', width: 80, placeholder: "申领单号", align: 'center', readOnly: true},
		{title: '申领时间', name: 'createDate', width: 80, placeholder: "申领时间", align: 'center', readOnly: true},
		{title: '申领人', name: 'userName', width: 80, placeholder: "申领人", align: 'center', readOnly: true},
		{title: '申领部门', name: 'groupName', width: 80, placeholder: "申领部门", align: 'center', readOnly: true},
		{title: '申领总数量', name: 'number1', readOnly: true, width: 80, align: 'center'},
		{title: '已出库数量', name: 'number2', readOnly: true, placeholder: '0', width: 80, align: 'center'},
		{title: '缺少数量', name: 'missingNumber', readOnly: true, placeholder: '0', type: "text"},
		{title: '申领状态', name: 'flowStateName', placeholder: "-", readOnly: true},
		{title: '备注', name: 'remarks', placeholder: "备注", type: "textarea", readOnly: true}

	]
};

var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		       	{title:"导出",id:"win_but_add",icon:"bolt", colourStyle:"success",clickE:main_export},
		       	{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}
var mmg;
var initDataGrid = function(){
	var cols = [   
	            {title:'商品名称', name:'matName' ,width:100,  align:'center'},
	            {title:'规格', name:'norm', width:80, align: 'center'},  
	            {title:'单位', name:'basicUnit' ,width:30,  align:'center'} ,
	            {title:'申请数量', name:'number1' ,width:30,  align:'center'} ,
	            {title:'出库数量', name:'baseNumber', width: 100, align: 'center'}
	        ];  
	          mmg = $('#datagrid1').mmGrid({
	            height:'auto'
	            , cols: cols
	            , method: 'get'
	            , remoteSort:false
	            , sortName: 'serialNumber'
	            , sortStatus: 'asc'
	            , multiSelect: true
	            ,showBackboard:false
	            , checkCol: false
	            , fullWidthRows: true
	            , autoLoad: false
	        });  
	        mmg.load([]); 
}
function controlInfo(val,item,rowIndex){
	var str = "";
	if(item.inputMode=='1')
		str += "<a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+item.wmsMiId+"\" target=\"_blank\">"+item.matName+"</a> ";  
	else
		str = item.matName;
	return str;
}
function loadGrid(){
	var att_url= $.supper("getServicePath", {service:"MdOutWarehouseMxService.getMdOutWarehouseMxListByMoiId",data:"mooId="+_rbbId});
	mmg.opts.url = att_url;
    mmg.load();
}

function initForm(){
	_rbbId = $.supper("getParam", _all_table_Id);
	console.log(_rbbId)

	_all_div_body.xform('createForm',_saveForm);
	initDataGrid();
	if(_rbbId != undefined && _rbbId != null){
		_rbbId = Number(_rbbId);
		var att_data=_all_table_Id+"="+Number(_rbbId);
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questOrderAction,"data":att_data});
		initFormNumber();
		loadGrid();
	}
}
function initFormNumber(){
	var selCodeInfo = $.supper("getProductArray", "viewApplyMdOutWaregouserInfo");
	if(selCodeInfo != null){
		// var allNumber = selCodeInfo.allNumber;
		// var alreadyNumber = selCodeInfo.alreadyNumber;
		// $('#allNumber').val(allNumber);
		// $('#alreadyNumber').val(alreadyNumber);
		// var leftNumber = allNumber - alreadyNumber;
		// $('#missingNumber').val(leftNumber);
		if($('#missingNumber').val() > 0){
			$('#missingNumber').css('color', 'red');
			$('#missingNumber').css('font-weight', 'bold');
		}
		// var shqJson=$.supper("getsysParam", "PAR171113111313225");
		// var flowState = selCodeInfo.flowState;
		// shqJson.forEach(function (item) {
		// 	if(flowState == item.id && flowState != '1'){
		// 		$('#flowState').val(item.name);
		// 		return;
		// 	}
		// })
		if(Number($('#flowState')) < 7){
			$('#flowStateName').css('color', 'red');
			$('#flowStateName').css('font-weight', 'bold');
		}
		$.supper("setProductArray", {"name":"selCodeInfo", "value":null});
	}
}
function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){
	initFormHidden();
	initForm();
	initToolBar(); 
}); 


function  closeWin(){
	$.supper("closeWin"); 
} 

function main_export(){
	var vdata="wowId="+_rbbId;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOutWarehouseService.exportInfoApply","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

 
