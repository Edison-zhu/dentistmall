var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="mmtId";
var _all_saveAction = "MdMaterielTypeService.saveOrUpdateObject";
var _all_questAction = "MdMaterielTypeService.findFormObject";
var _saveForm={ 
		lineNum:3,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"mmtId",id:"mmtId",value:"",type:"hidden"},
						{name:"mdMmtId",id:"mdMmtId",value:"",type:"hidden"},
						{title:'创建时间' ,name:'createDate',type:"hidden"},
			            {title:'创建人' ,name:'createRen',type:"hidden"},
			            {title:'修改时间' ,name:'editDate',type:"hidden"},
			            {title:'修改人' ,name:'editRen',type:"hidden"}
		             ],
		items:[ 
		       	   {title:'类型编号', name:'mmtCode', placeholder:"类型编号", width: 120, align: 'center',readOnly:true,prefixCode:"MT"},  
	               {title:'类型名称', name:'mmtName', placeholder:"类型名称" ,width:80,  align:'center',ariaRequired:true },
	               {title:'状态', name:'state' ,type:"select",width:80,  align:'center',impCode:"PAR170926033732594",ariaRequired:true}
		]	
	};

 

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
 

function initForm(){
	_rbbId = $.supper("getParam", _all_table_Id);
	var mdMmtId = $.supper("getParam", "mdMmtId"); 
	if(mdMmtId != null && mdMmtId > 0)
		$("#mdMmtId").val(mdMmtId);
	var att_data=_all_table_Id+"="+_rbbId;
	_all_div_body.xform('createForm',_saveForm); 
	if(_rbbId != null)
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data});
}


function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){
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

 
