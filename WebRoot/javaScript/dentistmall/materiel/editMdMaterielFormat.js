var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _wmsMiId;
var _all_table_Id="mmfId";
var _all_saveAction = "MdMaterielFormatService.saveOrUpdateObject";
var _all_questAction = "MdMaterielFormatService.findFormObject";
var _all_checkAvtion = 'MdMaterielFormatService.checkFormMmfCode';
var needCheckMmfCode = true; //是否需要检查重复
var isUpdateData = false;
var autoMmfCode = false; //是否自动生成
var layerAlert = null;
var _mmfCode = ''; //保存修改功能拿到的mmfcode，不检查修改时同一个mmfcode
var _saveForm={
		lineNum:3,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"mmfId",id:"mmfId",value:"",type:"hidden"},
						{name:"wmsMiId",id:"wmsMiId",value:"",type:"hidden"},
						{name:"oldPrice",id:"oldPrice",value:"",type:"hidden"},
						{title:'创建时间' ,name:'createDate',type:"hidden"},
			            {title:'创建人' ,name:'createRen',type:"hidden"},
			            {title:'修改时间' ,name:'editDate',type:"hidden"},
			            {title:'修改人' ,name:'editRen',type:"hidden"}
		             ],
	items:[
		       	   {title:'型号编号', name:'mmfCode', placeholder:"型号编号", width: 120, align: 'center',ariaRequired:true},  //,prefixCode:"MR"
	               {title:'型号名称', name:'mmfName', placeholder:"型号名称" ,width:80,  align:'center',ariaRequired:true},
					//2020 4月19 去掉价格显示
					//{title:'价格', name:'price', placeholder:"价格" ,width:80,  align:'center',ariaRequired:true },
	               {title:'状态', name:'state' ,type:"select",width:80,  align:'center',impCode:"PAR170926033732594",ariaRequired:true},

		]
	};
var _saveForm1={
	lineNum:3,
	columnNum:2,
	control:[],
	groupTag:[],
	hiddenitems:[
		{name:"mmfId",id:"mmfId",value:"",type:"hidden"},
		{name:"wmsMiId",id:"wmsMiId",value:"",type:"hidden"},
		{name:"oldPrice",id:"oldPrice",value:"",type:"hidden"},
		{title:'创建时间' ,name:'createDate',type:"hidden"},
		{title:'创建人' ,name:'createRen',type:"hidden"},
		{title:'修改时间' ,name:'editDate',type:"hidden"},
		{title:'修改人' ,name:'editRen',type:"hidden"}
	],
	items:[
		{title:'型号编号', name:'mmfCode', placeholder:"型号编号", width: 120, align: 'center',ariaRequired:true},  //,prefixCode:"MR"
		{title:'型号名称', name:'mmfName', placeholder:"型号名称" ,width:80,  align:'center',ariaRequired:true},
		//2020 4月19 去掉价格显示
		{title:'价格', name:'price', placeholder:"价格" ,width:80,  align:'center',ariaRequired:true },
		{title:'状态', name:'state' ,type:"select",width:80,  align:'center',impCode:"PAR170926033732594",ariaRequired:true},

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
	if (_user_type==2&&_user_role==3) {
		_all_div_hidden.xform('createhidden', _saveForm.hiddenitems);
	}else{
		_all_div_hidden.xform('createhidden', _saveForm1.hiddenitems);
	}
}
 

function initForm(){
	_rbbId = $.supper("getParam", _all_table_Id);
	var wmsMiId = $.supper("getParam", "wmsMiId");

	if(wmsMiId != null && wmsMiId > 0) {
		$("#wmsMiId").val(wmsMiId);
	}
	var att_data=_all_table_Id+"="+_rbbId;
	if (_user_type==2&&_user_role==3) {
		_all_div_body.xform('createForm',_saveForm);
	}else{
		_all_div_body.xform('createForm',_saveForm1);
	}

	if(_rbbId != null) {
		isUpdateData = true;
		_all_accountForm.xform('loadAjaxForm', {'ActionUrl': _all_questAction, "data": att_data});
	}
		
}


function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

$(function(){
	initFormHidden();
	initForm();
	initToolBar(); 
	//修改按钮样式
	$("#win_but_save").css("width","95px");
	$("#win_but_add").css("width","95px");

	var code = $('#mmfCode').val();
	_wmsMiId = $('#wmsMiId').val();
	_mmfCode = code;

	// 如果是修改，则在输入框失去焦点后开始检查是否重复
	$('#mmfCode').on('blur', function () {
		var mmfCode = $(this).val();
		if(isUpdateData === true && mmfCode == _mmfCode){
			return;
		}
		if(checkMmfCodeEmpty(mmfCode) === false){
			return false;
		}
		if(checkMmfCodeLength(mmfCode) === false){
			return false;
		}
		if(checkMmfCodeIsNumber(mmfCode) === false){
			return false;
		}
		checkMffCode($(this).val());
	})
	$('#mmfCode').on('focus', function () {
		//隐藏mmfcode的警告内容
		if(layerAlert !== null){
			layer.close(layerAlert);
		}
	})
	// if (_user_type==2&&_user_role==3){
	// 	$("#price").style.display("none");
	// }
});
//20191212 yangfeng 检查mmfcode
function checkMmfCodeEmpty(mmfCode) {
	if(mmfCode.toString().trim() === ''){
		//是否为空警告信息
		return false;
	}
	return true;
}
function checkMmfCodeLength(mmfCode) {
	if(mmfCode.length < 5 || mmfCode.length > 7){
		//字数警告信息
		layerAlert = layer.tips('长度在5-7位', $('#mmfCode'));
		return false;
	}
	return true;
}
function checkMmfCodeIsNumber(mmfCode) {
	if(/^\d+$/.test(mmfCode) === false){
		layerAlert = layer.tips('必须是数字', $('#mmfCode'));
		return false;
	}
	return true;
}
function checkMffCode(mmfCode, save, vdata) {
	var checkSaveAndRun = function() {
		if(save !== undefined && save !== null && typeof save === 'function'){
			save(vdata);
		}
	}
	var data = 'mmfCode=' + mmfCode;
	if (_wmsMiId != undefined) {
		data += '&wmsMiId=' + _wmsMiId;
	}
	$.supper("doservice", {
		"service" : _all_checkAvtion,
		"options":{"type":"post","data":data},
		"BackE" : function(jsondata) {
			if (jsondata.code == "1") {// 存在
				if(isUpdateData === true){
					let obj = jsondata.obj;
					if (obj.length == 1 && obj[0].mmfId == _rbbId) {
						checkSaveAndRun();
					} else {
						//显示重复警告信息
						layerAlert = layer.tips('已存在规格编号，请重新填写', $('#mmfCode'));
					}
				} else {
					//显示重复警告信息
					layerAlert = layer.tips('已存在规格编号，请重新填写', $('#mmfCode'));
				}
			} else if (jsondata.code == '2'){//不存在
				checkSaveAndRun();
			}
		}
	});
}


function  closeWin(){
	$.supper("closeWin"); 
} 
 
function save() {
	if (_all_div_body.xform('checkForm')) { 
		var price = $("#price").val();
		if(price != null && !price== ''){
			var lb_back=CheckUtil.isPlusFloat(price);
			var lb_back2=CheckUtil.isPlusInteger(price);
			if(lb_back==false && lb_back2==false){
				$.supper("alert",{ title:"操作提示", msg: ("价格必须为数字！")}); 
				return false;
			}
		}
		var data = _all_accountForm.serialize();
		var mmfCode = $('#mmfCode').val();
		if(checkMmfCodeEmpty(mmfCode) === false){
			return;
		}
		if(checkMmfCodeLength(mmfCode) === false){
			return;
		}
		if(checkMmfCodeIsNumber(mmfCode) === false){
			return;
		}
		checkMffCode(mmfCode, saveAction, data);
	} 
}
function saveAction(data) {
	$.supper("doservice", {
		"service": _all_saveAction,
		"ifloading": 1,
		"options": {"type": "post", "data": data},
		"BackE": function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert", {
					title: "操作提示",
					msg: "操作成功！",
					BackE: closeWin
				});
			} else if (jsondata.code == '2') {
				layerAlert = layer.tips('已存在规格名称，请重新填写', $('#mmfName'));
			} else if (jsondata.code == '3') {
				layerAlert = layer.tips('已存在规格编码，请重新填写', $('#mmfName'));
			} else {
				$.supper("alert", {
					title: "操作提示",
					msg: "操作失败！"
				});
			}
		}
	});
}

 
