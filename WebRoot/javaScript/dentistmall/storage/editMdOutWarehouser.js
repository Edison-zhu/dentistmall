var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="wewId";
var _all_saveAction = "MdOutWarehouseService.saveEditMdOutWarehouse";
var _all_questAction = "MdOutWarehouseService.findFormObject";
var _saveForm={ 
		lineNum:3,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"wowId",id:"wowId",value:"",type:"hidden"},
						{name:"rbaId",id:"rbaId",value:"",type:"hidden"},
						{name:"rbsId",id:"rbsId",value:"",type:"hidden"},
						{name:"rbbId",id:"rbbId",value:"",type:"hidden"},
						{name:'orderTime',id:"orderTime",value:"",type:"hidden"},
						{title:'创建时间' ,name:'createDate',type:"hidden"},
			            {title:'创建人' ,name:'createRen',type:"hidden"},
			            {title:'修改时间' ,name:'editDate',type:"hidden"},
			            {title:'修改人' ,name:'editRen',type:"hidden"}
		             ],
		items:[ 
		       	   {title:'出库单号', name:'wowCode', placeholder:"出库单号", width: 120, align: 'center',readOnly:true,prefixCode:"CK"},  
	               {title:'订单编号', name:'relatedBill1Div', placeholder:"订单编号" ,type:'userdefined',width:80,  align:'center',renderer:initSel },
	               {title:'供应商', name:'supplierName' ,width:80,  placeholder:"供应商",align:'center',readOnly:true},
	               {title:'采购人', name:'consignee' ,width:80,  placeholder:"采购人",align:'center',readOnly:true},
	               {title:'退货原因', name:'description', placeholder:"退货原因",type:"textarea"}
	             
		]	
	};

function initSel(){
	var str="<input type=\"text\" id=\"relatedBill1\" class=\"form-control2\" readonly name=\"relatedBill1\" aria-required=\"true\" aria-invalid=\"false\" placeholder=\"订单编号\"/>&nbsp;&nbsp;&nbsp;&nbsp;"
	str += "<a class=\"btn btn-danger btn-xs\" id=\"selCodeBut\" onclick=\"selCode()\">选择</a>";
	return str;
}

var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		        {title:"提交",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:save}, 
		       	{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}
var mmg;
var initDataGrid = function(){
	var cols = [   
	            {title:'商品名称', name:'matName' ,width:100,  align:'center'  ,renderer:controlInfo},
	            {title:'规格', name:'norm', width:80, align: 'center'},  
	            {title:'单位', name:'basicUnit' ,width:30,  align:'center'} ,
	            {title:'订单数量', name:'enterNumber' ,width:80,  align:'center',renderer:formatMatNum},
	            {title:'库存数量', name:'quantity' ,width:80,  align:'center'},
	            {title:'退货数量', name:'quantity', width: 100, align: 'center',renderer:formateInp}
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
	            , fullWidthRows: true
	            ,showBackboard:false
	            , autoLoad: false
	            , nowrap:true
	        });  
	        mmg.load([]); 
}
function formatMatNum(val,item,rowIndex){
	var tt = item.number2-(item.number3!=null?item.number3:0);
	return tt;
}

function controlInfo(val,item,rowIndex){
	var str = "";
	str += "<a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+item.wmsMiId+"\" target=\"_blank\">"+item.matName+"</a> ";  
   return str;
}

function control(val,item,rowIndex){
	var str = "";
	str += "<a onclick=\"delRow('"+item.mieId+"')\"  class='btn btn-danger  btn-xs'>删除</a> ";  
   return str;
}

function delRow(mieId){
	var rows = mmg.rows();
	for(var i =0;i < rows.length;i++){
		if(rows[i] != null && rows[i].mieId==mieId){
			mmg.removeRow(i);
		}
	}
}

function formateInp(val,item,rowIndex){
	var tt = "<input type=\"text\" id=\""+item.mieId+"Inp\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" style=\"width:80px\"/>";
	return tt;
}

function selCode(){
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/transaction/selOrderInfoList","data":"selType=2"});
	var tt_win=$.supper("showWin",{url:att_url,title:"选择订单信息",icon:"gears",width:800,height:500,BackE:function () {
		var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
		if(selCodeInfo != null && selCodeInfo.moiId != null){
			$("#relatedBill1").val(selCodeInfo.orderCode);
			$("#supplierName").val(selCodeInfo.applicantName);
			$("#consignee").val(selCodeInfo.purchaseAccount);
			$("#orderTime").val(selCodeInfo.placeOrderTime);
			loadGrid(selCodeInfo.moiId);
			$.supper("setProductArray", {"name":"selCodeInfo", "value":null});
		}
 	}}); 
}


function loadGrid(moiId){
	var att_url= $.supper("getServicePath", {service:"MdOrderMxService.getMdOrderMxListByMoiId",data:"moiId="+moiId});
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
	var companyType= $("#companyType").val();
	initDataGrid();
}

function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){
	selCode();
	initFormHidden();
	initForm();
	initToolBar(); 
	
	/*
	 *优化按钮
	 */
	$("#win_but_save").css("width","95px");
	$("#win_but_add").css("width","95px");
}); 


function  closeWin(){
	$.supper("closeWin"); 
} 
 
function save() {
	if (_all_div_body.xform('checkForm')) { 
		var data = _all_accountForm.serialize();
		var rows=mmg.rows();
		var mieIds="";
		var shus="";
		var momIds="";
		var number1s="";
		if($("#relatedBill1").val()==null || $("#relatedBill1").val()==""){
			$.supper("alert", {title : "操作提示",msg : "请选择订单号！"});
			return;
		}
		if(rows != null && rows.length > 0 && rows[0] != null){
			var flag=false;
			for(var i=0;i < rows.length;i ++){
				var mieId=rows[i].mieId;
				var shu = $("#"+mieId+"Inp").val();
				if(shu != null && shu != "" && shu >rows[i].quantity){
					$.supper("alert", {title : "操作提示",msg : "出库数量不能大于库存数量！"});
					return;
				}else if(shu != null && shu != "" && shu >rows[i].enterNumber){
					$.supper("alert", {title : "操作提示",msg : "出库数量不能大于订单数量！"});
					return;
				}else if(shu != null && shu != ""){
					flag=true;
					mieIds += mieId+",";
					shus += shu+",";
					momIds += rows[i].momId+",";
					number1s += rows[i].enterNumber+",";
				}
				
				
			}
			if(!flag){
				$.supper("alert", {title : "操作提示",msg : "请输出库数量！"});
				return;
			}
			mieIds=mieIds.substring(0,mieIds.length-1);
			shus=shus.substring(0,shus.length-1);
			momIds=momIds.substring(0,momIds.length-1);
			number1s=number1s.substring(0,number1s.length-1);
		}else{
			$.supper("alert", {title : "操作提示",msg : "没有出库明细，不允许出库！"});
			return;
		}
		data += "&shus="+shus+"&mieIds="+mieIds+"&momIds="+momIds+"&companyType=2&number1s="+number1s;
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

 
