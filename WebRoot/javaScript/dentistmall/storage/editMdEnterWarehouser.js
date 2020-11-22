var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="wewId";
var _all_saveAction = "MdEnterWarehouseService.saveMdEnterWarehouse";
var _all_questAction = "MdEnterWarehouseService.findFormObject";
var _saveForm={ 
		lineNum:3,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"wewId",id:"wewId",value:"",type:"hidden"},
						{name:"rbaId",id:"rbaId",value:"",type:"hidden"},
						{name:"rbsId",id:"rbsId",value:"",type:"hidden"},
						{name:"rbbId",id:"rbbId",value:"",type:"hidden"},
						{title:'创建时间' ,name:'createDate',type:"hidden"},
			            {title:'创建人' ,name:'createRen',type:"hidden"},
			            {title:'修改时间' ,name:'editDate',type:"hidden"},
			            {title:'修改人' ,name:'editRen',type:"hidden"}
		             ],
		items:[ 
		       	   {title:'入库单号', name:'billcode', placeholder:"入库单号", width: 120, align: 'center',readOnly:true,prefixCode:"RK"},  
	               {title:'订单编号', name:'relationBillCodeDiv', placeholder:"订单编号" ,type:'userdefined',width:80,  align:'center',renderer:initSel },
	               {title:'订单时间', name:'orderDatetime' ,width:80, placeholder:"订单时间",align:'center',readOnly:true},
	               {title:'供应商', name:'supplierName' ,width:80,  placeholder:"供应商",align:'center',readOnly:true},
	               {title:'采购人', name:'consignee' ,width:80,  placeholder:"采购人",align:'center',readOnly:true}
		]	
	};

function initSel(){
	var str="<input type=\"text\" id=\"relationBillCode\" class=\"form-control2\" readonly name=\"relationBillCode\" aria-required=\"true\" aria-invalid=\"false\" placeholder=\"订单编号\"/>&nbsp;&nbsp;&nbsp;&nbsp;"
	str += "<a class=\"btn btn-danger btn-xs\" id=\"selCodeBut\" onclick=\"selCode()\">选择</a>";
	return str;
}

var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		        {title:"入库",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:save},
		       	{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}
var mmg;
var initDataGrid = function(){
	var cols = [   
	            {title:'商品名称', name:'matName' ,width:100,  align:'center',renderer:controlInfo  },
	            {title:'规格', name:'norm' ,width:30,  align:'center',renderer:controlMmfName} ,
	            {title:'单位', name:'basicUnit' ,width:30,  align:'center'} ,
	            {title:'订单数量', name:'matNumber', width: 80, align: 'center'},
	            {title:'发货数量', name:'number2', width: 80, align: 'center'},
	            {title:'已入库数量', name:'enterNumber', width: 80, align: 'center'},
	            {title:'入库数量', name:'number2', width: 80, align: 'center',renderer:formateInp}
	        ];  
	          mmg = $('#datagrid1').mmGrid({
	            height:'auto'
	            , cols: cols
	            , method: 'get'
	            , remoteSort:false
	            , sortName: 'serialNumber'
	            , sortStatus: 'asc'
	            , multiSelect: true
	            , checkCol: false
	            ,showBackboard:false
	            , fullWidthRows: true
	            , autoLoad: false
	            , nowrap:true
	        });  
	        mmg.load([]); 
}
function controlInfo(val,item,rowIndex){
	var str = "";
	str += "<a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+item.wmsMiId+"\" id=\""+item.mmfId+"MatName\" target=\"_blank\">"+item.matName+"</a> ";  
   return str;
}
function controlMmfName(val,item,rowIndex){
	var str = "";
	str += "<lable id=\""+item.mmfId+"MmfName\">"+item.norm+"</lable> ";  
   return str;
}
function delRow(mmfId){
	var rows = mmg.rows();
	for(var i =0;i < rows.length;i++){
		if(rows[i] != null && rows[i].mmfId==mmfId){
			mmg.removeRow(i);
		}
	}
}

function formateInp(val,item,rowIndex){
	var enterNumber = (item.enterNumber!=null?item.enterNumber:0);
	var number2 = (item.number2!=null?item.number2:0);
	var cha=parseInt(number2)-parseInt(enterNumber);
	if(enterNumber < number2)
		var tt = "<input type=\"text\" id=\""+item.mmfId+"Inp\" value=\""+cha+"\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" style=\"width:80px\"/>";
	else
		tt="-";
	return tt;
}

function selCode(){
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/transaction/selOrderInfoList","data":"selType=1"});
	var tt_win=$.supper("showWin",{url:att_url,title:"选择订单信息",icon:"gears",width:800,height:500,BackE:function () {
		var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
		if(selCodeInfo != null && selCodeInfo.moiId != null){
			$("#relationBillCode").val(selCodeInfo.orderCode);
			$("#orderDatetime").val(selCodeInfo.placeOrderTime);
			$("#supplierName").val(selCodeInfo.applicantName);
			$("#consignee").val(selCodeInfo.purchaseAccount);
			loadGrid(selCodeInfo.moiId);
			$.supper("setProductArray", {"name":"selCodeInfo", "value":null});
		}
 	}}); 
}

function loadGrid(moiId){
	var att_url= $.supper("getServicePath", {service:"MdOrderMxService.getMdOrderMxListByMoiIdForEnter",data:"moiId="+moiId});
	mmg.opts.url = att_url;
    mmg.load();
}

function initForm(){
	_rbbId = $.supper("getParam", _all_table_Id);
	var att_data=_all_table_Id+"="+_rbbId;
	_all_div_body.xform('createForm',_saveForm); 
	if(_rbbId != null){
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data});
	}
	initDataGrid();
}

function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){
	var moiId = $.supper("getParam", "moiId");
	if(moiId != null){
	}else{
		selCode();
	}
	initFormHidden();
	initForm();
	initToolBar(); 
	if(moiId != null){
		initOrderInfo(moiId);
	}
	$("#win_but_save").css("width","95px");
	$("#win_but_add").css("width","95px");
}); 

function initOrderInfo(moiId){
	var vdata = "moiId="+moiId;
	$.supper("doservice", {"service":"MdOrderInfoService.findFormObject","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$("#relationBillCode").val(jsondata.obj.orderCode);
			$("#orderDatetime").val(jsondata.obj.placeOrderTime);
			$("#supplierName").val(jsondata.obj.applicantName);
			$("#consignee").val(jsondata.obj.purchaseAccount);
		}
 	}});
	
	loadGrid(moiId);
}


function  closeWin(){
	$.supper("closeWin"); 
} 
 
function save() {
	if (_all_div_body.xform('checkForm')) { 
		var data = _all_accountForm.serialize();
		var rows=mmg.rows();
		var momIds="";
		var shus="";
		var mmfIds="";
		var number1s="";
		var prices="";
		var masIds = '';
		if($("#relationBillCode").val()==null || $("#relationBillCode").val()==""){
			$.supper("alert", {title : "操作提示",msg : "请选择订单号！"});
			return;
		}
		if(rows != null && rows.length > 0 && rows[0] != null){
			var flag=false;
			for(var i=0;i < rows.length;i ++){
				var mmfId=rows[i].mmfId;
				var tt = rows[i].number2-rows[i].enterNumber;
				var shu = $("#"+mmfId+"Inp").val();
				if(shu != null && shu != "" && shu >tt){
					$.supper("alert", {title : "操作提示",msg : "入库数量不能大于发货数量！"});
					return;
				}else if(shu != null && shu != "" && shu >0){
					flag=true;
					momIds += rows[i].momId+",";
					shus += shu+",";
					mmfIds += rows[i].mmfId+",";
					number1s += rows[i].matNumber+",";
					prices +=  rows[i].unitMoney+",";
					if(masIds.indexOf(rows[i].masId) < 0){
						masIds += rows[i].masId + ',';
					}
				}
			}
			var message = '';
			/*if(!flag && masIds != ''){
				message = '存在未确认的数量以及售后商品，是否确认入库？';
			}else*/
			if(!flag){
				// message = '存在未确认的数量，是否确认入库？';
				$.supper("alert", {title:"操作提示",msg : "请输入确认数量！"});
				return;
			} else if (masIds != ''){
				message = '存在售后的商品，是否确认入库';
			}
			// if(!flag){
			// 	$.supper("alert", {title : "操作提示",msg : "请输入库数量！"});
			// 	return;
			// }
			momIds=momIds.substring(0,momIds.length-1);
			shus=shus.substring(0,shus.length-1);
			mmfIds=mmfIds.substring(0,mmfIds.length-1);
			number1s=number1s.substring(0,number1s.length-1);
			prices = prices.substring(0,prices.length-1);
			if(masIds != '') {
				masIds = masIds.substring(0, masIds.length - 1);
				data += '&masIds=' + masIds;
			}
		}else{
			$.supper("alert", {title : "操作提示",msg : "没有入库明细，不允许入库！"});
			return;
		}
		data += "&shus="+shus+"&momIds="+momIds+"&mmfIds="+mmfIds+"&billType=2&number1s="+number1s+"&prices="+prices;
		$.supper("doservice", {
			"service" : _all_saveAction,
			"ifloading" : 1,
			"options":{"type":"post","data":data},
			"BackE" : function(jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert", {
						title : "操作提示",
						msg : "操作成功！",
						BackE : closeWin
					});
				} else
					$.supper("alert", {
						title : "操作提示",
						msg : "操作失败！"
					});
			}
		});
	} 
}

 
