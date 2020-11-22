var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="mdaId";
var _all_saveAction = "MdDeliveryAddressService.saveOrUpdateObject";
var _all_questAction = "MdDeliveryAddressService.findFormObject";

 

var _saveForm={ 
		lineNum:4,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"mdaId",id:"mdaId",value:"",type:"hidden"},  
						{title:'创建时间' ,name:'createDate',type:"hidden"},
			            {title:'创建人' ,name:'createRen',type:"hidden"},
			            {title:'修改时间' ,name:'editDate',type:"hidden"},
			            {title:'修改人' ,name:'editRen',type:"hidden"}
		             ],
		items:[  
		       	{title:'收件人', name:'addressee', placeholder:"收件人",ariaRequired:true}, 
		       	{title:'收件人电话', name:'addresseeTelephone', placeholder:"收件人电话",ariaRequired:true},
		       	{title:'所在省', name:'province', placeholder:"所在省",type:"select",ariaRequired:true},  
		    	{title:'所在市', name:'city', placeholder:"所在市",type:"select",ariaRequired:true},  
		    	{title:'所在地区', name:'area', placeholder:"所在地区",type:"select",ariaRequired:true},
		    	{title:'配送地址', name:'deliveryAddress', placeholder:"配送地址",ariaRequired:true},
		    	{title:'是否默认', name:'ifDefault', placeholder:"是否默认",type:"select",impCode:"PAR171027083430113",ariaRequired:true}
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
	_all_div_body.xform('createForm',_saveForm); 
	$('#province').change(function(){
		initShi();
	});
	
	$('#city').change(function(){
		initArea();
	}); 
	initShen(null);
	
	_rbbId = $.supper("getParam", _all_table_Id); 
	var att_data=_all_table_Id+"="+_rbbId;
	
	if(_rbbId!=null&&_rbbId!=""){
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data,"BackE":function(data){
			initShi(data.obj.city);  
			initArea(data.obj.area); 
		}});
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
 
function save() {
	if (_all_div_body.xform('checkForm')) { 
		var data = _all_accountForm.serialize();
		$.supper("doservice", {
			"service" : _all_saveAction,
			"ifloading" : 1,
			"data" : data,
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

 
