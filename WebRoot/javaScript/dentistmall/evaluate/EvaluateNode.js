var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="mevaId";
var _all_saveAction = "MdEvaluateService.saveOrUpdateObject";
var _all_questAction = "MdEvaluateService.findFormObject";
var _saveForm={ 
		lineNum:0,
		columnNum:1,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"mevaId",id:"mevaId",value:"",type:"hidden"},
						{name:"mmfId",id:"mmfId",value:"",type:"hidden"},
						{name:"state",id:"state",value:"1",type:"hidden"},						
						{ name:'createDate',type:"hidden"},
			            { name:'createRen',type:"hidden"},
			            { name:'editDate',type:"hidden"},
			            {name:'editRen',type:"hidden"}
		             ],
		items:[]	
	};

 

var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		         
		       	{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems); 
}
 

function initForm(){
	//初始化 型号 
	_rbbId = $.supper("getParam", "mevaId");
	var att_data ="mevaId="+_rbbId;
 
	if(_rbbId!=null&&_rbbId!=""){
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data,"BackE":function(data){
			 
		}});
	} 
}

function selFormatet(mmfId,price){
	  $(".mmfId").removeClass("selected");
	 $("#li_"+mmfId).addClass("selected"); 
	
	$("#mmfId").val(mmfId); 
    $("#mmPrice").html("￥"+toDecimal2(price));
	
}

function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){ 
	initForm();
	initToolBar(); 
}); 


function  closeWin(){
	$.supper("closeWin"); 
} 
 
function save() {
	if (_all_div_body.xform('checkForm')) { 
		var data = _all_accountForm.serialize();
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
				} 
			}
		});
	} 
}
