var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="wzId";
var _all_saveAction = "MdSupplierService.saveOrUpdateObject";
var _all_questAction = "MdSupplierService.findFormObject";
var _saveForm={ 
		lineNum:7,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"wzId",id:"wzId",type:"hidden"}, 
						{name:"orgGxId",id:"orgGxId",type:"hidden"},  
						{name:"logoCode",id:"logoCode",type:"hidden"},  
						{name:'state',id:"state",type:"hidden"},
						{title:'创建时间' ,name:'createDate',type:"hidden"},
			            {title:'创建人' ,name:'createRen',type:"hidden"},
			            {title:'修改时间' ,name:'editDate',type:"hidden"},
			            {title:'修改人' ,name:'editRen',type:"hidden"}
		             ],
		items:[ 
		       	{title:'企业编号', name:'legalCertificateNo', placeholder:"企业编号",readOnly:true,prefixCode:"MS"},  
		       	{title:'企业名称', name:'applicantName', placeholder:"企业名称",readOnly:true}, 
		    	{title:'企业类型', name:'enterpriseType', placeholder:"企业类型"},  
		    	{title:'企业住所地', name:'corporateDomicile', placeholder:"企业住所地",ariaRequired:true},  
		    	{title:'公司所在省', name:'selprovince', placeholder:"公司所在省",type:"select"},  
		    	{title:'公司所在市', name:'selcity', placeholder:"公司所在市",type:"select"},  
		    	{title:'公司所在地区', name:'selarea', placeholder:"公司所在地区",type:"select"},  
		    	{title:'免快递费限额', name:'noExpressMoney', placeholder:"免快递费限额",ariaRequired:false},  
				{title:'快递费', name:'expressMoney', placeholder:"快递费",ariaRequired:false},  
		    	{title:'联系人姓名', name:'legalPerson', placeholder:"联系人姓名",ariaRequired:true}, 
		    	{title:'电话', name:'phoneNumber', placeholder:"电话",ariaRequired:true},
		    	{title:'邮箱', name:'mailbox', placeholder:"邮箱"},
		    	{title:'客服QQ<br>(多个以 , 分开)', name:'customs', placeholder:"多个以 , 分开"}
		    	 
		]	
	};
var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		        {title:"提交",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:save} 
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}

function initForm(){
	_rbbId = $("#oldId").val(); 
	var att_data=_all_table_Id+"="+_rbbId;
	_all_div_body.xform('createForm',_saveForm); 
	$('#selprovince').change(function(){
		initShi() ;
	});	
	$('#selcity').change(function(){
		initArea() ;
	}); 
	initShen(null);
	_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data,"BackE":function(data){
		initShi(data.obj.selcity);  
		initArea(data.obj.selarea); 
	}});
	initOneUploadImg("logoCode","logoFileDiv");
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
 
function save() {
	if (_all_div_body.xform('checkForm')) { 
		var logoCode=$("#logoCode").val();
		var noExpressMoney = $("#noExpressMoney").val();
		var expressMoney = $("#expressMoney").val();
		if(logoCode == null || logoCode==""){
			$.supper("alert", {title : "操作提示",msg : "请上传企业图标！"});
			return;
		}
		if(noExpressMoney != null && !noExpressMoney== ''){
			var lb_back=CheckUtil.isPlusFloat(noExpressMoney);
			var lb_back2=CheckUtil.isPlusInteger(noExpressMoney);
			if(lb_back==false && lb_back2==false){
				$.supper("alert",{ title:"操作提示", msg: ("免快递费限额必须为数字！")}); 
				return false;
			}
		}
		if(expressMoney != null && !expressMoney== ''){
			var lb_back=CheckUtil.isPlusFloat(expressMoney);
			var lb_back2=CheckUtil.isPlusInteger(expressMoney);
			if(lb_back==false && lb_back2==false){
				$.supper("alert",{ title:"操作提示", msg: ("快递费必须为数字！")}); 
				return false;
			}
		}
		var data = _all_accountForm.serialize();
		$.supper("doservice", {
			"service" : _all_saveAction,
			"ifloading" : 1,
			"options":{"type":"post","data":data},
			"BackE" : function(jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert", {
						title : "操作提示",
						msg : "操作成功！"});
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
	 $("#selprovince").html(str);
	 if(selProvince != null && selProvince != "")
		 $("#selprovince").val(selProvince);
}

function initShi(selCity){
	var selSheng=$("#selprovince").val();
	var str = "<option value=\"\">所在市</option>";
	 for(var  i = 0 ;i < shqJson.length ;i++){
	 	if(shqJson[i].name==selSheng){
	 		for(var j = 0 ; j < shqJson[i].city.length;j++){
	 			str += "<option value=\""+shqJson[i].city[j].name+"\">"+shqJson[i].city[j].name+"</option>";
	 		}
	 		break;
	 	}
	 	
	 }
	 $("#selcity").html(str);
	 if(selCity != null && selCity != "")
	 	$("#selcity").val(selCity);
}

function initArea(selArea){
	var selSheng=$("#selprovince").val();
	var selShi=$("#selcity").val();
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
	 $("#selarea").html(str);
	 if(selArea != null && selArea != "")
	 	$("#selarea").val(selArea);
}

 
