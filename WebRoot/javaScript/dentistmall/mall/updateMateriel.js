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
						{name:"applicantName",id:"applicantName",value:"",type:"hidden"}, 
						{name:"wzId",id:"wzId",value:"",type:"hidden"}, 
						{name:"money1",id:"money1",value:"",type:"hidden"},  
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
			{title:'商品编码', name:'matCode', placeholder:"商品编码",ariaRequired:true,prefixCode:"MAT"},
			{title:'商品名称', name:'matName', placeholder:"商品名称",type:'userdefined',width:80,  align:'center',renderer:initMatName},  
			{title:'商品分类 ', name:'mdWmsMiIdDiv', placeholder:"商品分类" ,type:'userdefined',width:80,  align:'center',renderer:initSel },
			{title:'包装方式', name:'productName', placeholder:"包装方式",ariaRequired:true},
			{title:'品牌', name:'brand', placeholder:"品牌",ariaRequired:true},  
			{title:'基本单位', name:'basicUnit', placeholder:"基本单位",ariaRequired:true},  
			{title:'注册证号', name:'backPrinting', placeholder:"注册证号",ariaRequired:false},  
			{title:'注册证有效期', name:'basicUnitAccuracy', placeholder:"注册证有效期",ariaRequired:false},  
			{title:'推荐顺序', name:'serialNumber', placeholder:"推荐顺序"},
			{title:'状态', name:'state' ,type:"select",width:80,  align:'center',impCode:"PAR171023031139920",ariaRequired:false},
			{title:'生产厂家', name:'productFactory',type:'userdefined',width:80,align:'center',renderer:initProductFactory},
			{title:'别名', name:'aliasName',placeholder:"选填，每个别名以“，”符号分开",width:100,height:30,  align:'center'}
		]	
	};
function initProductFactory(){
var str='<input type="text" name="productFactory" class="form-control" id="productFactory" placeholder="选填，12个字" maxlength="12" />';
	str += '<div id="div_items" ></div> ';
	return str;
}
function initSel(){
	var str="<input type=\"text\" id=\"mdWmsMiName\" class=\"form-control2\" readonly name=\"mdWmsMiName\" aria-required=\"true\" aria-invalid=\"true\" placeholder=\"商品分类\"/>&nbsp;&nbsp;&nbsp;&nbsp;";
	str += "<a class=\"btn btn-danger btn-xs\" id=\"selTypeBut\" onclick=\"selType()\">选择</a>";
	return str;
}

function initMatName(){
	var str="<input type=\"text\" id=\"matName\" class=\"form-control\" name=\"matName\" aria-required=\"true\" onfocus=\"intiItems()\" onkeyup=\"intiItems()\" onblur=\"hideItems()\" aria-invalid=\"true\" placeholder=\"商品名称\"/>";
	str += "<div id=\"div_items\" onmouseover=\"blurItem()\" onmouseout=\"noBlurItem()\"></div> ";
	return str;
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
function intiItems(){
	var matName =$("#matName").val();
	if(matName != null && matName!=""){
		$.supper("doservice", {"service":"MdMaterielInfoService.getMatNameList","data":"matName="+matName, "BackE":function (jsondata) {
			var dataList = jsondata;
			if(dataList!=null && dataList.length > 0){
				$("#div_items").show();
				var str="";
				for(var i=0;i < dataList.length ; i++){
					str += "<div class=\"div_item\" >"+dataList[i].mat_name+"</div>";
				}
				$("#div_items").html(str);
				$("#div_items").show();
				$(".div_item").hover(function () {  
			        $(this).css('background-color', '#1C86EE').css('color', 'white');  
			    }, function () {  
			        $(this).css('background-color', 'white').css('color', 'black');  
			    }); 
				$(".div_item").click(function () {  
			        $("#matName").val($(this).text());  
			        $("#div_items").hide();
			        initMat();
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
function initMat(){
	var matName =$("#matName").val();
	if(matName != null && matName!=""){
		$.supper("doservice", {"service":"MdMaterielInfoService.getMdMaterielInfoByMatName","data":"matName="+matName, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				var info = jsondata.obj;
				$("#mdWmsMiName").val(info.mdWmsMiName);
				$("#productFactory").val(info.productFactory);
				$("#brand").val(info.brand);
				$("#basicUnit").val(info.basicUnit);
				$("#mdWmsMiId").val(info.mdWmsMiId);
				$("#matType1").val(info.matType1);
				$("#matType2").val(info.matType2);
			}
	 	}});
	}
}

function selType(){
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/materiel/selMdMaterielType"});
	var tt_win=$.supper("showWin",{url:att_url,title:"选择商品分类",icon:"fa-th-list",width:800,height:500,BackE:function () {
		var selMdMaterielType = $.supper("getProductArray", "selMdMaterielType");
		if(selMdMaterielType != null && selMdMaterielType.id != null){
			$("#mdWmsMiId").val(selMdMaterielType.id);
			$("#mdWmsMiName").val(selMdMaterielType.name);
			$("#matType1").val(selMdMaterielType.oneId);
			$("#matType2").val(selMdMaterielType.towId);
			$("#mdWmsMiName").val(selMdMaterielType.name);
			$.supper("setProductArray", {"name":"selMdMaterielType", "value":null});
		}
 	}}); 
}
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
		$("#_title").html("编辑商品信息");
		$("#addFor").show();
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data});
		loadGrid();
	}else{
		$("#_title").html("添加商品信息");
		$("#addFor").hide();
	}
	initOneUploadImg("lessenFilecode","lessenFileDiv");
	initListUploadImg("listFilecode","imglist");
}

function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
}

var mmg;
var initDataGrid = function () {
	var cols = [
		{title: '型号编码', sortable: true, name: 'mmfCode', width: 150, align: 'center'},
		{title: '型号名称', sortable: true, name: 'mmfName', width: 100, align: 'center'},
		{title: '价格', sortable: true, name: 'price', width: 100, align: 'center'},
		{title: '状态', sortable: true, name: 'state', width: 80, align: 'center', impCode: 'PAR170926033732594'},
		{title: '操作', name: 'control', width: 150, align: 'center', renderer: control},
	];
	mmg = $('#datagrid1').mmGrid({
		height: 'auto'
		, cols: cols
		, method: 'get'
		, remoteSort: false
		, sortName: 'serialNumber'
		, sortStatus: 'asc'
		, multiSelect: true
		, showBackboard: false
		, checkCol: false
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
	if(item.state=='1')
		str += "<a onclick=\"update_state('"+  item.mmfId+"','2')\"  class='btn btn-success  btn-xs'>禁用</a>&nbsp;&nbsp;";
	else if(item.state=='2')
		str += "<a onclick=\"update_state('"+  item.mmfId+"','1')\"  class='btn btn-info  btn-xs'>启用</a>&nbsp;&nbsp;";
	str += "<a onclick=\"editFormat('"+  item.mmfId+"')\"  class='btn btn-warning  btn-xs'>修改</a>&nbsp;&nbsp;";
	str += "<a onclick=\"delFormat('"+  item.mmfId+"')\"  class='btn btn-danger  btn-xs'>删除</a> ";  
   return str;
}

$(function(){
	initFormHidden();
	initDataGrid();
	initForm();
	initToolBar(); 
	KindEditor_init(); 
	/**
	 * 改变提交按钮样式
	 */
	$("#win_but_save").css("width","95px");
	$("#win_but_save").css("vertical-align","middle");
}); 
var editorObj = new Array(); 
function KindEditor_init(keval){
	var att_keval_afterChange =keval?keval.afterChange:null;
  $.each($(".KindEditor"), function(i, n){
              var k_name=n.id;
               if(k_name){
  				 	var editor = KindEditor.create('textarea[id="'+k_name+'"]',{
  				 		cssData:'body {font-family: "微软雅黑"; font: 14px/1.5}',
  				 		cssPath : '/dentistmall/js/plugins/kindeditor/plugins/code/prettify.css',
				      	uploadJson : '/dentistmall/jsp/upload_json.htm',
						fileManagerJson : '/dentistmall/jsp/file_manager_json.htm',   
					    allowFileManager: true,
					 	autoHeightMode : false ,
					 	afterChange : att_keval_afterChange,
					 	afterCreate : function() { 
					          this.sync(); 
					         }, 
				         afterBlur:function(){ 
				             this.sync();
				         }   
					});
  				 	editorObj[i]=editor; 	
			  } 
	 });
  
}	
function  all_sync(){
    $.each(editorObj, function(i, n_editor){ 
   		 n_editor.sync();  
	 });
}

function getKindEditor(att_id){
        $.each($(".X_kindereditor"), function(i, n){
            var k_name=n.id;
             if(k_name==att_id){ 
				  return  editorObj[i] ; 	
			  } 
	 }); 
}	

function save() {
	if (_all_div_body.xform('checkForm')) { 
		var matName =$("#matName").val();
		var serialNumber=$("#serialNumber").val();
		var mdWmsMiId = $("#mdWmsMiId").val();
		var lessenFilecode = $("#lessenFilecode").val();
		var listFilecode = $("#listFilecode").val();
		if(matName ==null || matName==""){
			$.supper("alert",{ title:"操作提示", msg: ("请输入商品名称！")}); 
			return false;
			
		}
		if(mdWmsMiId ==null || mdWmsMiId==""){
			$.supper("alert",{ title:"操作提示", msg: ("请选择商品类型！")}); 
			return false;
			
		}
		if(serialNumber != null && serialNumber!= ''){
			var lb_back=CheckUtil.isPlusInteger(serialNumber);
			if(lb_back==false){
				$.supper("alert",{ title:"操作提示", msg: ("推荐顺序必须是正整数！")}); 
				return false;
			}
		}
		if(lessenFilecode ==null || lessenFilecode==""){
			$.supper("alert",{ title:"操作提示", msg: ("请上传商品图标！")}); 
			return false;
			
		}
		if(listFilecode ==null || listFilecode==""){
			$.supper("alert",{ title:"操作提示", msg: ("请上传商品图片！")}); 
			return false;
			
		}
		
		var data = _all_accountForm.serialize();
		$.supper("doservice", {
			"service" : _all_saveAction,
			"ifloading" : 1,
			"options":{"type":"post","data":data},
			"BackE" : function(jsondata) {
				if (jsondata.code == "1") {
					if(_rbbId==null || _rbbId==""){
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
					}
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

function update_state(mmfId,state){
	var vdata ="mmfId="+mmfId;
	vdata +="&state="+state;
	$.supper("confirm",{ title:"修改操作", msg:"确认修改状态操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdMaterielFormatService.updateObjectState","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:loadGrid});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

 
