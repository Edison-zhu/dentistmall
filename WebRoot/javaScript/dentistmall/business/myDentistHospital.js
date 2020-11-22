var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="rbsId";
var _all_saveAction = "MdDentistHospitalService.saveOrUpdateObject";
var _all_questAction = "MdDentistHospitalService.findFormObject";
var _saveForm={
		renew:true,
		lineNum:10,
		columnNum:1,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"rbsId",id:"rbsId",value:"",type:"hidden"},
						{name:"rbaId",id:"rbaId",value:"",type:"hidden"},
						{name:"flowState",id:"flowState" ,type:"hidden"}, 
						{name:"state",id:"state" ,type:"hidden"}, 
						{id:"createDate" ,name:'createDate',type:"hidden"},
			            {id:"createRen" ,name:'createRen',type:"hidden"},
			            {id:"editDate" ,name:'editDate',type:"hidden"},
			            {id:"editRen",name:'editRen',type:"hidden"}
		             ],
		items:[ 
		       	{title:'医院编号', name:'rbsCode', placeholder:"医院编号","readOnly":"true"},  
		    	{title:'所属集团公司名称', name:'rbaName',placeholder:"所属集团公司名称","readOnly":"true"},
		       	{title:'医院名称', name:'rbsName', placeholder:"医院名称","readOnly":"true"},
		    	{title:'医院地址', name:'address', placeholder:"医院地址"},  
		    	{title:'公司所在省', name:'province', placeholder:"公司所在省",type:"select"},   
		    	{title:'公司所在市', name:'city', placeholder:"公司所在市",type:"select"},  
		    	{title:'公司所在地区', name:'area', placeholder:"公司所在地区",type:"select"}, 
		    	{title:'负责人', name:'legalPerson', placeholder:"负责人"}, 
		    	{title:'手机号', name:'legalPhone', placeholder:"手机号","checkType":"isMobile"},
		    	{title:'备注', name:'remark', placeholder:"备注",type:"textarea"}
		]	
	};
 
var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		        {title:"提交",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:save} 
		       	//,{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}


function initForm(){	
	cleanFormWin();	
	var att_data=_all_table_Id+"="+_rbbId;
	if(_rbbId!=null&&_rbbId!=""){
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data,"BackE":function(data){
			initShi(data.obj.city);  
			initArea(data.obj.area); 
		}});
	}
}



function cleanFormWin(){ 
	_rbbId = $("#oldId").val(); 	
	_all_div_body.xform('createForm',_saveForm); 
	
	_rbaId=top.window._win_main_orgGxId?top.window._win_main_orgGxId:"";	
	_rbaName=top.window._win_main_orgName?top.window._win_main_orgName:"";
	$("#rbaId").val(_rbaId);
	$("#rbaName").val(_rbaName);
	
	
	$('#province').change(function(){
		initShi() ;
	});	
	$('#city').change(function(){
		initArea() ;
	}); 
	initShen(null);
	initShi(null);
	initArea(null); 
}




function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){
	initFormHidden();
	initForm();
	initToolBar(); 
	/**
	 * 改变提交按钮样式
	 */
	$("#win_but_save").css("width","95px");
	$("#win_but_save").css("vertical-align","middle");
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
						msg : "操作成功！"
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




function initShen(selProvince){
	 var str = "<option value=\"\">所在省</option>";
	 for(var  i = 0 ;i < shqJson.length ;i++){
	 	str += "<option value=\""+shqJson[i].name+"\">"+shqJson[i].name+"</option>";
	 }
	 $("#province").html(str);
	 if(selProvince != null && selProvince != "")
		 $("#province").val(selProvince);
}

function initShi(selCity){
	var selSheng=$("#province").val();
	var str = "<option value=\"\">所在市</option>";
	 for(var  i = 0 ;i < shqJson.length ;i++){
	 	if(shqJson[i].name==selSheng){
	 		for(var j = 0 ; j < shqJson[i].city.length;j++){
	 			str += "<option value=\""+shqJson[i].city[j].name+"\">"+shqJson[i].city[j].name+"</option>";
	 		}
	 		break;
	 	}
	 	
	 }
	 $("#city").html(str);
	 if(selCity != null && selCity != "")
	 	$("#city").val(selCity);
}

function initArea(selArea){
	var selSheng=$("#province").val();
	var selShi=$("#city").val();
	var str = "<option value=\"\">所在区、县</option>";
	 for(var  i = 0 ;i < shqJson.length ;i++){
	 	if(shqJson[i].name==selSheng){
	 		for(var j = 0 ; j < shqJson[i].city.length;j++){
	 			if(shqJson[i].city[j].name==selShi){
	 				for(var k = 0 ; k < shqJson[i].city[j].area.length;k++){
	 					str += "<option value=\""+shqJson[i].city[j].area[k]+"\">"+shqJson[i].city[j].area[k]+"</option>";
	 				}
	 				break;
	 			}
	 			
	 		}
	 		break;
	 	}
	 }
	 $("#area").html(str);
	 if(selArea != null && selArea != "")
	 	$("#area").val(selArea);
}


