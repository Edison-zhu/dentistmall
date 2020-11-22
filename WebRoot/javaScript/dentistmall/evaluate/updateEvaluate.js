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
						{name:"wmsMiId",id:"wmsMiId",value:"",type:"hidden"},
						{name:"mmfId",id:"mmfId",value:"",type:"hidden"},
						{name:"state",id:"state",value:"1",type:"hidden"},				
						{name:"priceReview",id:"priceReview",value:"1",type:"hidden"},
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
		        {title:"提交",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:save}, 
		       	{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems); 
}
 

function initForm(){
	//初始化 型号 
	_rbbId = $.supper("getParam", "wmsMiId");
	var data ="wmsMiId="+_rbbId;
	$("#wmsMiId").val(_rbbId);
	$.supper("doservice", {
		"service" : "MdEvaluateService.getFormatList",
		"ifloading" : 1,
		"options":{"type":"post","data":data},
		"BackE" : function(jsondata) {
			if (jsondata.code == "1"&&jsondata.obj) {
				var str="";
				var dataList=jsondata.obj;
				if(dataList != null && dataList.length > 0){ 
					 
					for(var i =0;i < dataList.length;i++){
						var data = dataList[i]; 
						var att_selected='class="selected mmfId" ';
						if(i==0){ 
							$("#mmfId").val(data.mmfId);
                            $("#mmfName").html(data.matName);
                            $("#mmPrice").html("￥"+toDecimal2(data.price));
						} else {
							att_selected='class="mmfId"';
						}
						str +='<a id="li_'+data.mmfId+'"   href="javascript:selFormatet('+data.mmfId+','+data.price+');" '+att_selected+'><em>'+data.mmfName+'</em></a>';
					}
				}
				 $("#gg_list").html(str)
			}  
		}
	}); 
	_all_div_body.xform('createForm',_saveForm);  
}

function selFormatet(mmfId,price){
	  $(".mmfId").removeClass("selected");
	 $("#li_"+mmfId).addClass("selected"); 
	
	$("#mmfId").val(mmfId); 
    $("#mmPrice").html("￥"+toDecimal2(price));
	
}

function setReview(rstate){
	$("#priceReview").val(rstate);
	$("#review1").css("color","#808C8A");
	$("#review2").css("color","#808C8A");
	$("#review3").css("color","#808C8A");
	$("#review"+rstate).css("color","#01B76D");
	if(rstate=='1'){
		$("#priceComparison").html("<option value=\"1\" selected=\"selected\">比较其他渠道较低</option>");
	}else if(rstate=='2'){
		$("#priceComparison").html("<option value=\"1\" selected=\"selected\">比较其他渠道较低</option><option  value=\"2\">比较其他渠道较高</option>");
	}else if(rstate=='3'){
		$("#priceComparison").html("<option value=\"2\" selected=\"selected\">比较其他渠道较高</option>");
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
