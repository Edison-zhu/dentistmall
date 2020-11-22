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
		lineNum:2,
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
		       	   {title:'入库单号', name:'billcode', placeholder:"入库单号", width: 120, align: 'center',readOnly:true,prefixCode:"RKS"},
		       	   {title:'供应商', name:'supplierName' ,width:80,  placeholder:"供应商",align:'center'},
	               {title:'采购人', name:'consignee' ,width:80,  placeholder:"采购人",align:'center'}
		]	
	};

function initSel(){
	var str="<input type=\"text\" id=\"relationBillcode\" class=\"form-control2\" readonly name=\"relationBillcode\" aria-required=\"true\" aria-invalid=\"false\" placeholder=\"订单编号\"/>&nbsp;&nbsp;&nbsp;&nbsp;"
	str += "<a class=\"btn btn-danger btn-xs\" id=\"selCodeBut\" disabled=true onclick=\"selCode()\">选择</a>";
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
	            {title:'商品名称', name:'matName' ,width:100,  align:'center'  },
	            {title:'规格', name:'mmfName' ,width:70,  align:'center'} ,
	            {title:'单价', name:'price' ,width:70,  align:'center',renderer:formatePrice} ,
	            {title:'单位', name:'basicUnit' ,width:30,  align:'center'} ,
		{title:'库存', name:'allInventory' ,width:30,  align:'center'} ,
	            {title:'入库数量', name:'matNumber', width: 100, align: 'center',renderer:formateInp},
				{title:'包装方式', name:'packageWay' ,width:70,  align:'center', renderer: formatPackInp} ,
				{title:'生产日期', name:'productTime', width:70,placeholder:"生产日期", renderer: formatePTime} ,
				{title:'有效期', name:'productValitime', width:70,placeholder:"有效期", renderer: formatePVTime} ,
				{title:'生产厂家', name:'productFactory' ,width:70,  align:'center', renderer: formateFactory} ,
				{title:'批次证号', name:'batchCertNo' ,width:70,  align:'center', renderer: formateCertNo} ,
	            {title:'操作' ,name:'control',width:50,  align:'center',renderer:control },
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
                  ,indexCol: true
	            ,showBackboard:false
	            , fullWidthRows: true
	            , autoLoad: false
	            , nowrap:true
				  ,width: 'auto'
	        });  
	        mmg.load([]); 
}

function control(val,item,rowIndex){
	var str = "";
	str += "<a onclick=\"delRow('"+item.mmfId+"')\"  class='btn btn-danger  btn-xs'>删除</a> ";  
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
function formatMatNum(val,item,rowIndex){
	var tt = item.matNumber-item.enterNumber - item.backNumber;
	return tt;
}

function formatePrice(val,item,rowIndex){//value=\""+val+"\"//" + item.money1 + "
	//var val=0;
	var tt = "<input type=\"text\" id=\""+item.mmfId+"price\" onkeyup=\"this.value=this.value.replace(/[^0-9.]/g,'')\"  value='' style=\"width:80px\"/>";
	return tt;
}

function formateInp(val,item,rowIndex){
	var btton_pre = '<input type="button" value="-" id="'+item.mmfId+'Min" onclick="minWareNum('+item.mmfId+')" />';
	var tt = "<input type=\"text\" id=\""+item.mmfId+"Inp\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" style=\"width:50px\;text-align: center\" value='"+item.matNumber+"'/>";
	var button_next = '<input type="button" value="+" id="'+item.mmfId+'Add" onclick="addWareNum('+item.mmfId+')" />';
	tt = btton_pre + tt + button_next;
	return tt;
}
function minWareNum(mmfId){
	var base_num = $("#" + mmfId + "Inp").val();
	base_num--;
	if(base_num <= 1) {
		base_num = 1;
	}
	$("#" + mmfId + "Inp").val(base_num);
}
function addWareNum(mmfId) {
	var base_num = $("#" + mmfId + "Inp").val()
	base_num++;
	$("#" + mmfId + "Inp").val(base_num);
}

function formatPackInp(val,item,rowIndex){
	var tt = "<input type=\"text\" id=\""+item.mmfId+"Pack\" style=\"width:80px\"/>";
	return tt;
}

function formatePTime(val,item,rowIndex){
	var tt = "<input type=\"text\" placeholder='生产日期' readonly id=\""+item.mmfId+"PTime\" style=\"width:80px\"/>";
	return tt;
}

function formatePVTime(val,item,rowIndex){
	var tt = "<input type=\"text\" placeholder='有效期' readonly id=\""+item.mmfId+"PVTime\" style=\"width:80px\"/>";
	return tt;
}

function formateFactory(val,item,rowIndex){
	var tt = "<input type=\"text\" id=\""+item.mmfId+"Factory\" style=\"width:80px\"/>";
	return tt;
}

function formateCertNo(val,item,rowIndex){
	var tt = "<input type=\"text\" id=\""+item.mmfId+"CertNo\" style=\"width:80px\"/>";
	return tt;
}

function selInv(){
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/materiel/selMdMaterielInfoList"});
	var tt_win=$.supper("showWin",{url:att_url,title:"选择物料信息",icon:"fa-flask",width:900,height:500,BackE:function () {
		var selMaterielInfoArray = $.supper("getProductArray", "selMaterielInfoArray");
		var rows = mmg.rows();
		if(selMaterielInfoArray != null && selMaterielInfoArray.length >0){
			for(var i =0;i < selMaterielInfoArray.length ;i++){
				var selMaterielInfo = selMaterielInfoArray[i];
				var flag=false;
				if(rows != null && rows.length > 0 && rows[0] != null){
					for(var j =0;j<rows.length;j++){
						if(selMaterielInfo.mmfId==rows[j].mmfId){
							flag=true;
							break;
						}
					}
				}
				if(!flag) {
					mmg.addRow(selMaterielInfo);
					laydate({
						elem: "#" +selMaterielInfo.mmfId + "PTime", //生产日期
						format: 'YYYY-MM-DD'
					})
					laydate({
						elem: "#" +selMaterielInfo.mmfId + "PVTime", //有效期
						format: 'YYYY-MM-DD'
					})
				}
			}
			
			$.supper("setProductArray", {"name":"selMaterielInfoArray", "value":null});
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
	initDataGrid();
}

function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){
	selInv();
	initFormHidden();
	initForm();
	initToolBar(); 
	
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
		var shus="";
		var mmfIds="";
		var prices="";
		var productPTimes = "";
		var productValiTimes = "";
		var packasgs = "";
		var factories = "";
		var certnos = "";
		if(rows != null && rows.length > 0 && rows[0] != null){
			var flag=false;
			for(var i=0;i < rows.length;i ++){
				var mmfId=rows[i].mmfId;
				var tt = rows[i].matNumber-rows[i].enterNumber - rows[i].backNumber;
				var shu = $("#"+mmfId+"Inp").val();
				 var price=$("#"+mmfId+"price").val();
				if(shu != null && shu != "" && shu >tt){
					$.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，入库数量不能大于订单数量！"});
					return;
				}if(price == null ||price==""){
					$.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，价格不能为空！"});
					return;
				}else if(shu != null && shu != "" && shu >0){
					flag=true;
					shus += shu+",";
					mmfIds += mmfId+",";
					prices += price+",";
				} else if (shu == null || shu == "" || shu <= 0){
					flag = false;
				}

				// 20191118 yangfeng 增加五个新字段
				var pack = $("#"+mmfId+"Pack").val();
				if(pack.length > 10){
					$.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，包装方式字数不能大于10个！"});
					return;
				}
				packasgs += pack + ",";

				var ptime = $("#"+mmfId+"PTime").val();
				var pvtime = $("#"+mmfId+"PVTime").val()
				if((ptime !="" && pvtime != "")){
					var start_time = new Date(ptime.replace("-", "/").replace("-", "/"));
					var end_time = new Date(pvtime.replace("-", "/").replace("-", "/"));
					if(start_time > end_time) {
						$.supper("alert", {title: "操作提示", msg: "第" + (i + 1) + "行，生产日期不能超过有效期！"});
						return;
					}
				}
				productPTimes += ptime + ",";
				productValiTimes += pvtime + ",";

				var fac = $("#"+mmfId+"Factory").val();
				if(fac.length > 4){
					$.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，生产厂家字数不能大于4个！"});
					return;
				}
				factories += fac + ",";

				var certno = $("#"+mmfId+"CertNo").val();
				if(certno.length > 16){
					$.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，批次证号字数不能大于16个！"});
					return;
				}
				certnos += certno + ",";
			}
			if(!flag){
				$.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，请输入库数量！"});
				return;
			}
			shus=shus.substring(0,shus.length-1);
			mmfIds=mmfIds.substring(0,mmfIds.length-1);
			prices = prices.substring(0,prices.length-1);
			packasgs=packasgs.substring(0,packasgs.length-1);
			productPTimes=productPTimes.substring(0,productPTimes.length-1);
			productValiTimes = productValiTimes.substring(0,productValiTimes.length-1);
			factories=factories.substring(0,factories.length-1);
			certnos=certnos.substring(0,certnos.length-1);
		}else{
			$.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，没有入库明细，不允许入库！"});
			return;
		}
		data += "&shus="+shus+"&mmfIds="+mmfIds+"&billType=1"+"&prices="+prices+"&packasgs="+packasgs+"&productPTimes="+productPTimes+"&productValiTimes="+productValiTimes+"&factories="+factories+"&certnos="+certnos;
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

 
