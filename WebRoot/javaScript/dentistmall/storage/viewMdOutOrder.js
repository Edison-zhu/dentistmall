var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="mooId";
var _all_questAction = "MdOutOrderService.findFormObject";
var _saveForm={ 
		lineNum:2,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"mooId",id:"mooId",value:"",type:"hidden"},
						{name:"rbaId",id:"rbaId",value:"",type:"hidden"},
						{name:"rbsId",id:"rbsId",value:"",type:"hidden"},
						{name:"rbbId",id:"rbbId",value:"",type:"hidden"},
						{title:'创建时间' ,name:'createDate',type:"hidden"},
			            {title:'创建人' ,name:'createRen',type:"hidden"},
			            {title:'修改时间' ,name:'editDate',type:"hidden"},
			            {title:'修改人' ,name:'editRen',type:"hidden"}
		             ],
		items:[ 
		       	   {title:'申领单号', name:'mooCode', placeholder:"申领单号", width: 120, align: 'center',readOnly:true,prefixCode:"CK"}, 
		       	   {title:'申领部门', name:'userName' ,width:80,  placeholder:"申领部门",align:'center',readOnly:true},
	               {title:'申领人', name:'groupName' ,width:80,  placeholder:"申领人",align:'center',readOnly:true}
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
	            {title:'申领数量', name:'baseNumber', width: 80, align: 'center'},
	            {title:'出库数量', name:'number1', width: 80, align: 'center'}
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
	var att_url= $.supper("getServicePath", {service:"MdOutOrderMxService.getMdOutOrderMxListByMooId",data:"mooId="+_rbbId});
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
}); 


function  closeWin(){
	$.supper("closeWin"); 
} 

function main_export(){
	var vdata="mooId="+_rbbId;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOutOrderService.exportInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

 
