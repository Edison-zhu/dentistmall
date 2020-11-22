var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");
var _all_win_tools_but2 = $("#win_toolbar");
var _all_win_datagrid;
/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="";
var _all_saveAction = "";
var _all_questAction = "";
var _saveForm={ 
		lineNum:1,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
		             ],
		items:[ 
	               {title:'导出月份', name:'month', placeholder:"选择月份" ,width:80,  align:'center' ,readOnly:true},
	              
		]	
	};
var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		        {title:"导出",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:save}, 
		       	{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

function initForm(){
	_all_div_body.xform('createForm',_saveForm);
}


function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){
	initForm();
	laydate({
		elem: '#month',
		format: 'YYYY-MM' //日期格式
	});
	
	initToolBar(); 
}); 


function  closeWin(){
	$.supper("closeWin"); 
} 
 
function save() {
	var month=$("#month").val();
   if(month==""){
	   $.supper("alert",{ title:"操作提示", msg:"导出日期不可为空！"});
	   return ;
   }
	var newTab=window.open('about:blank');
	var vdata="month="+month;
	$.supper("doservice", {"service":"MdExportService.exportSfhz","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
			$.supper("alert",{ title:"操作提示", msg:"操作成功！",BackE:closeWin});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}


 
