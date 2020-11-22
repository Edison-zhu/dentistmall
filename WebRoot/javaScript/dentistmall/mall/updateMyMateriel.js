var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");

/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id="wmsMiId";
var _all_saveAction = "MdMaterielInfoService.saveOrUpdateObject";
var _all_questAction = "MdMaterielInfoService.findFormObject";
var _dblClick = function (data, row, col) {
	editFormat(data.mmfId);
}

var _saveForm={ 
		lineNum:6,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"wmsMiId",id:"wmsMiId",value:"",type:"hidden"},  
						{name:"mdWmsMiId",id:"mdWmsMiId",value:"",type:"hidden"},  
						{name:"purchaseType",id:"purchaseType",value:"",type:"hidden"}, 
						{name:"wzId",id:"wzId",value:"",type:"hidden"}, 
						{name:"number1",id:"number1",value:"",type:"hidden"},  
						{name:"matType1",id:"matType1",value:"",type:"hidden"},  
						{name:"matType2",id:"matType2",value:"",type:"hidden"},  
						{name:"lessenFilecode",id:"lessenFilecode",value:"",type:"hidden"},  
						{name:"listFilecode",id:"listFilecode",value:"",type:"hidden"},  
						{id:'createDate' ,name:'createDate',type:"hidden"},
			            {id:'createRen',name:'createRen',type:"hidden"},
			            {id:'editDate',name:'editDate',type:"hidden"},
			            {id:'editRen',name:'editRen',type:"hidden"}
		             ],
		items:[  
			{title:'物料编码', name:'matCode', placeholder:"物料编码",readOnly:false,prefixCode:"MAT"},
			{title:'物料名称', name:'matName', placeholder:"物料名称",ariaRequired:true},  
			{title:'供应商', name:'applicantName', placeholder:"供应商"},  
			{title:'生产厂家', name:'productName', placeholder:"生产厂家"},
			{title:'品牌', name:'brand', placeholder:"品牌"},  
			{title:'注册证号', name:'backPrinting', placeholder:"注册证号"},  
			{title:'注册证有效期', name:'basicUnitAccuracy', placeholder:"注册证有效期"},  
			{title:'基本单位', name:'basicUnit', placeholder:"基本单位"},
			{title:'规格', name:'norm', placeholder:"规格"},
			{title:'别名', name:'aliasName',placeholder:"选填，每个别名以“，”符号分开",width:100,height:30,  align:'center'}
		]	
	};

var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		        {title:"提交",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:save} 
		       //	{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}
 

function initForm(){
	_rbbId = $.supper("getParam", _all_table_Id); 
	var att_data=_all_table_Id+"="+_rbbId;
	_all_div_body.xform('createForm',_saveForm); 
	if(_rbbId!=null&&_rbbId!=""){
		$("#_title").html("编辑物料信息");
		$("#addFor").show();
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data});
		loadGrid();
	}else{
		$("#_title").html("添加物料信息");
		$("#addFor").hide();
	}
	initOneUploadImg("lessenFilecode","lessenFileDiv");
	initListUploadImg("listFilecode","imglist");
}

function hideItems(){
	if(!_showItem)
		$("#div_items").hide();
}
var _showItem;
function blurItem(){
	_showItem=true;
}
function noBlurItem(){
	_showItem=false;
}
function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}


var mmg;
var initDataGrid = function () {
	var cols = [
		{title: '型号编码', sortable: true, name: 'mmfCode', width: 150, align: 'center'},
		{title: '型号名称', sortable: true, name: 'mmfName', width: 100, align: 'center'},
		{title: '状态', sortable: true, name: 'state', width: 80, align: 'center', impCode: 'PAR170926033732594'},
		//2020 4月19 去掉价格显示
		// {title: '价格', sortable: true, name: 'price', width: 80, align: 'center'},
		{title: '操作', name: 'control', width: 150, align: 'center', renderer: control}
	];
	mmg = $('#datagrid1').mmGrid({
		height: 'auto'
		, cols: cols
		, method: 'get'
		, remoteSort: false
		, sortName: 'serialNumber'
		, sortStatus: 'asc'
		, multiSelect: true
		, checkCol: false
		, showBackboard: false
		, fullWidthRows: true
		, autoLoad: false
		, dblClickFunc: _dblClick
	});
	mmg.load([]);
}
function loadGrid(){
	var att_url= $.supper("getServicePath", {service:"MdMaterielFormatService.getMdMaterielFormatListByWmsMiId",data:"wmsMiId="+_rbbId});
	mmg.opts.url = att_url;
    mmg.load();
}
function control(val,item,rowIndex){
	var str = "";
	str += "<a onclick=\"editFormat('"+  item.mmfId+"')\"  class='btn btn-warning  btn-xs'>修改</a>&nbsp;&nbsp;";
	str += "<a onclick=\"delFormat('"+  item.mmfId+"')\"  class='btn btn-danger  btn-xs'>删除</a> ";  
   return str;
}
$(function(){
	initFormHidden();
	initDataGrid();
	initForm();
	initToolBar(); 
	//修改提交按钮
	$("#win_but_save").css("width","95px");

	// //初始化查询时判断物料编码是否可以进行编辑
	getMatCodeReadOnly();
	
}); 

function intiItems(){
	var labelInfo =$("#labelInfo").val();
	if(labelInfo != null && labelInfo!=""){
		$.supper("doservice", {"service":"MdMaterielInfoService.getMdMaterielInfoByLabelInfo","data":"labelInfo="+labelInfo, "BackE":function (jsondata) {
			var dataList = jsondata;
			if(dataList!=null && dataList.length > 0){
				$("#div_items").show();
				var str="";
				for(var i=0;i < dataList.length ; i++){
					str += "<div class=\"div_item\">"+dataList[i].labelInfo+"</div>";
				}
				$("#div_items").html(str);
				$("#div_items").show();
				$(".div_item").hover(function () {  
			        $(this).css('background-color', '#1C86EE').css('color', 'white');  
			    }, function () {  
			        $(this).css('background-color', 'white').css('color', 'black');  
			    }); 
				$(".div_item").click(function () {  
			        $("#labelInfo").val($(this).text());  
			        $("#div_items").hide();
			    }); 
			}else{
				$("#div_items").hide();
				$("#div_items").html("");
			}
	 	}});
	}else{
		$("#div_items").hide();
		$("#div_items").html("");
	}
}

function save() {
	if (_all_div_body.xform('checkForm')) { 
		var state=$("#state").val();
		if(state==null || state==""){
			$.supper("alert",{ title:"操作提示", msg:"请选择状态！"});
			return;
		}
		var data = _all_accountForm.serialize();
		$.supper("doservice", {
			"service" : _all_saveAction,
			"ifloading" : 1,
			"options":{"type":"post","data":data},
			"BackE" : function(jsondata) {
				if (jsondata.code == "1") {
					_rbbId = jsondata.obj.wmsMiId;
					
					$("#wmsMiId").val(jsondata.obj.wmsMiId);
					$("#wzId").val(jsondata.obj.wzId);
					$("#applicantName").val(jsondata.obj.applicantName);
					$("#purchaseType").val(jsondata.obj.purchaseType);
					$("#createRen").val(jsondata.obj.createRen);
					$("#createDate").val(jsondata.obj.createDate);
					$("#editDate").val(jsondata.obj.editDate);
					$("#editRen").val(jsondata.obj.editRen);
					$("#addFor").show();
					$.supper("alert", {title : "操作提示",msg : "操作成功！"});
				} else
					$.supper("alert", {
						title : "操作提示",
						msg : "操作失败！"
					});
			}
		});
	} 
}

function addFormat(){
	var vdata ="wmsMiId="+_rbbId;
	var att_url= $.supper("getServicePath", {"data":vdata,url:"/jsp/dentistmall/materiel/editMdMaterielFormat.jsp"});
	var tt_win=$.supper("showWin",{url:att_url,title:"型号信息",icon:"fa-bars",width:"800",height:"500", BackE:loadGrid});
}

function editFormat(mmfId){
	var vdata ="mmfId="+mmfId;
	var att_url= $.supper("getServicePath", {"data":vdata,url:"/jsp/dentistmall/materiel/editMdMaterielFormat.jsp"});
	var tt_win=$.supper("showWin",{url:att_url,title:"型号信息",icon:"fa-bars",width:"800",height:"500", BackE:loadGrid});
}

function delFormat(mmfId){
	var vdata ="mmfId="+mmfId;
	$.supper("confirm",{ title:"删除操作", msg:"确认删除记录操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdMaterielFormatService.deleteObject","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:loadGrid});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}
//增加查询出入库记录中是否有型号编码  如果有 就不可以编辑 如果没有 就为初始化状态 可以编辑
var MatCodeReadOnly;
// function getMatCodeReadOnly() {
// 	if (_rbbId!=null&&_rbbId!=undefined) {
// 		var vdata="wmsMiId="+_rbbId;
//
// 		$.supper("doservice", {"service":"MdOutOrderService.getMatCodeReadOnly","data":vdata, isAjax:"1", "BackE":function (jsondata) {
// 				if (jsondata.code == "1") {
// 					MatCodeReadOnly=jsondata.obj.MatCodeReadOnly;
// 					if (MatCodeReadOnly==0){
// 						$("#matCode").removeAttr("readonly");
// 					}else {
// 						$("#matCode").attr("readonly",true);
// 					}
// 					newTab.location.href=jsondata.obj.path;
// 				}else
// 					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
// 			}});
// 	}
// }
//
//
