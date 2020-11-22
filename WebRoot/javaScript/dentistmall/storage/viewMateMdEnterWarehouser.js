var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="wewId";
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
		       	   {title:'入库单号', name:'billcode', placeholder:"入库单号", width: 120, align: 'center',readOnly:true,prefixCode:"RK"},  
	               {title:'供应商', name:'supplierName' ,width:80,  placeholder:"供应商",align:'center',readOnly:true},
	               {title:'采购人', name:'consignee' ,width:80,  placeholder:"采购人",align:'center',readOnly:true}
		]	
	};


var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		   		{title:"导出",id:"win_but_add",icon:"bolt", colourStyle:"success",clickE:main_export},
		       	{title:"关闭",id:"win_but_add1",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}
var mmg;
var initDataGrid = function(){
	var cols = [   
	            {title:'商品名称', name:'matName' ,width:100,  align:'center'},
	            {title:'规格', name:'norm' ,width:80,  align:'center'} ,
	            {title:'单价', name:'price' ,width:30,  align:'center'} ,
	            {title:'单位', name:'basicUnit' ,width:30,  align:'center'} ,
	            {title:'入库数量', name:'matNumber', width: 100, align: 'center'},
				{title:'包装方式', name:'packageWay' ,width:70,  align:'center'} ,
				{title:'生产日期', name:'productTime', width:70, align: 'center', renderer: formatProductTime} ,
				{title:'有效期', name:'productValitime', width:70, align: 'center', renderer: formatProductValiTime} ,
				{title:'生产厂家', name:'productFactory' ,width:70, align: 'center'} ,
				{title:'批次证号', name:'batchCertNo' ,width:70, align: 'center'},
				{title:'注册证号', name:'backPrinting' ,width:70, align: 'center'}
	        ];  
	          mmg = $('#datagrid1').mmGrid({
	            height:'auto'
	            , cols: cols
	            , method: 'get'
	            , remoteSort:false
	            ,showBackboard:false
	            , sortName: 'serialNumber'
	            , sortStatus: 'asc'
	            , multiSelect: true
	            , checkCol: false
	            , fullWidthRows: true
	            , autoLoad: false
	        });  
	        mmg.load([]); 
}
// 格式化时间
function formatProductTime(val, item, rowIndex){
	var date = formatViewMEWTime(item.productTime);
	return '<span calss>' + date +'</span>';
}
function formatProductValiTime(val, item, rowIndex){
	var date = formatViewMEWTime(item.productValitime);
	return '<span calss>' + date +'</span>';
}
function formatViewMEWTime(time){
	if(time == null || time == undefined){
		return '';
	}
	var date = new Date(time);
	return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate();
}

function loadGrid(){
	var att_url= $.supper("getServicePath", {service:"MdEnterWarehouseMxService.getMdEnterWarehouseMxListByMoiId",data:"wewId="+_rbbId});
	mmg.opts.url = att_url;
    mmg.load();
}

function initForm(){
	_rbbId = $.supper("getParam", _all_table_Id);
	var att_data=_all_table_Id+"="+_rbbId;
	_all_div_body.xform('createForm',_saveForm); 
	initDataGrid();
	if(_rbbId != null){
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data});
		loadGrid();
	}
}

function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){
	initFormHidden();
	initForm();
	initToolBar(); 
	
	$("#win_but_add1").css("width","95px");
	$("#win_but_add").css("width","95px");
}); 


function  closeWin(){
	$.supper("closeWin"); 
} 
function main_export(){
	var vdata="wewId="+_rbbId;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdEnterWarehouseService.exportInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
 
