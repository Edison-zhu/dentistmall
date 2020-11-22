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
var _all_table_Id="wiId";
var _all_saveAction = "MdInventoryService.saveWanning";
var _all_questAction = "MdInventoryService.findFormObject";
var _saveForm={ 
		lineNum:5,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						{name:"wiId",id:"wiId",value:"",type:"hidden"}
		             ],
		items:[ 
	               {title:'商品名称', name:'matName', placeholder:"商品名称" ,width:80,  align:'center' },
	               {title:'型号', name:'mmfName', placeholder:"商品名称" ,width:80,  align:'center'},
	               {title:'单位', name:'basicUnit', placeholder:"单位" ,width:80,  align:'center' },
	               {title:'告警数量', name:'warningShu', placeholder:"告警数量" ,width:80,  align:'center'},
	               {title:'最大数量', name:'maxShu', placeholder:"最大数量" ,width:80,  align:'center'},
	               {title:'到货时间', name:'minDay', placeholder:"到货时间" ,width:80,  align:'center'},
	               {title:'拆分系数', name:'ratio', placeholder:"拆分系数" ,width:80,  align:'center'}
		]	
	};

 

var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		        {title:"提交",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:save}, 
		       	{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin} 
		       ] 
	} ;

var _Toolbar2={
		toolBarId:"win_toolbar",
		items:[
		       {title:"合并库存",id:"win_but_addjc",icon:"plus", colourStyle:"success",clickE:addItem}
		      ] 
}
function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}
 

function initForm(){
	_rbbId = $.supper("getParam", _all_table_Id);
	var att_data=_all_table_Id+"="+_rbbId;
	_all_div_body.xform('createForm',_saveForm); 
	if(_rbbId != null)
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data});
}


function initToolBar(){
	_all_win_tools_but.xtoolbar('create',_Toolbar);
	_all_win_tools_but2.xtoolbar('create',_Toolbar2);
}

$(function(){
	initFormHidden();
	initForm();
	initToolBar(); 
	_all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid);  
	main_search();
	/*
	 *优化按钮
	 */
	$("#win_but_save").css("width","95px");
	$("#win_but_add").css("width","95px");
}); 


function  closeWin(){
	$.supper("closeWin"); 
} 
 
function save() {
	var matName = $("#matName").val();
	var mmfName = $("#mmfName").val();
	var warningShu = $("#warningShu").val();
	var maxShu = $("#maxShu").val();
	var minDay = $("#minDay").val();
	var ratio = $("#ratio").val();
	var basicUnit=$("#basicUnit").val();
	if(matName == null || matName== ''){
		$.supper("alert",{ title:"操作提示", msg: ("请输入商品名称！")}); 
		return false;
	}
	if(mmfName == null || mmfName== ''){
		$.supper("alert",{ title:"操作提示", msg: ("请输入规格！")}); 
		return false;
	}
	if(basicUnit == null || basicUnit== ''){
		$.supper("alert",{ title:"操作提示", msg: ("请输入单位！")}); 
		return false;
	}
	if(basicUnit == null || basicUnit== ''){
		$.supper("alert",{ title:"操作提示", msg: ("请输入单位！")}); 
		return false;
	}
	if(warningShu != null && !warningShu== ''){
		var lb_back=CheckUtil.isPlusFloat(warningShu);
		var lb_back2=CheckUtil.isPlusInteger(warningShu);
		if(lb_back==false && lb_back2==false){
			$.supper("alert",{ title:"操作提示", msg: ("告警数量必须为数字！")}); 
			return false;
		}
	}
	if(maxShu != null && !maxShu== ''){
		var lb_back=CheckUtil.isPlusFloat(maxShu);
		var lb_back2=CheckUtil.isPlusInteger(maxShu);
		if(lb_back==false && lb_back2==false){
			$.supper("alert",{ title:"操作提示", msg: ("最高数量必须为数字！")}); 
			return false;
		}
	}
	if(minDay != null && !minDay== ''){
		var lb_back2=CheckUtil.isPlusInteger(minDay);
		if(lb_back==false && lb_back2==false){
			$.supper("alert",{ title:"操作提示", msg: ("到货日期必须为正整数！")}); 
			return false;
		}
	}
	if(ratio != null && !ratio== ''){
		var lb_back2=CheckUtil.isPlusInteger(ratio);
		if(lb_back==false && lb_back2==false){
			$.supper("alert",{ title:"操作提示", msg: ("拆分系数必须为正整数！")}); 
			return false;
		}
	}else{
		$.supper("alert",{ title:"操作提示", msg: ("拆分系数不能为空！")}); 
		return false;
	}
	var data = "wiId="+_rbbId+"&wanningShu="+$("#warningShu").val()+"&maxShu="+$("#maxShu").val()
			+"&minDay="+$("#minDay").val()+"&ratio="+ratio+"&baseUnit="+basicUnit+"&matName="+matName+"&mmfName="+mmfName;
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

var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_queryAction = "MdInventoryService.getItemKeyMxList";
var _all_datagrid_height ;
var _DataGrid={
		cols: [
               {title:'商品名称', sortable: true, name:'matName', width:120,align:'center'},
               {title:'型号', sortable: true, name:'mmfName',width:80, align:'center'} ,
               {title:'供应商', sortable: true, name:'applicantName', width:100, align:'center'},
               {title:'类型', sortable: true, name:'typeName', width:80, align:'center'},
               {title:'品牌', sortable: true, name:'brand', width:80, align:'center'},
               {title:'单位', sortable: true, name:'basicUnit',width:50, align:'center'} ,
               {title:'操作' ,name:'mimId',width:80,  align:'center',renderer:control}  
           ]
,remoteSOrt: false
		,name:'itemKeyMxList'
		,height:300
		, url:""
}


/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl(){ 
	var data=_all_table_Id+"="+_rbbId; 
	var att_action=_all_queryAction;	
	var att_url= $.supper("getServicePath", {"service":att_action, "data":data}); 
	return att_url;
}
/**
 * 对主表进行查询（刷新）操作
 */
function main_search(){
	_all_win_datagrid.opts.url = getMMGridUrl();
	_all_win_datagrid.load();	
}

function control(val,item,rowIndex){
	var str = "";
	str += "<a onclick=\"main_delete('"+  item.mimId+"')\"  class='btn btn-danger  btn-xs'>移出</a> ";  
   return str;
}
/**
 * 删除选中行记录
 * @param id 选中行记录主键值
 */
function main_delete(id){ 
	var rows = _all_win_datagrid.rows();
	if(rows.length > 1){
		var vdata= _all_table_Id+"="+_rbbId+"&mimId="+id; 
		$.supper("confirm",{ title:"移出操作", msg:"确认移出库存操作？" ,yesE:function(){ 
			$.supper("doservice", {"service":"MdInventoryService.delItemKeyMx","data":vdata, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		 	}});
		}});
	}else{
		$.supper("alert",{ title:"操作提示", msg:"不允许删除唯一的商品明细！"});
		return;
	}
    
}

function addItem(){
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/storage/selAllInventoryList"});
	var tt_win=$.supper("showWin",{url:att_url,title:"选择库存信息",icon:"fa-flask",width:1200,height:540,BackE:function () {
		var selInvInfoArray = $.supper("getProductArray", "selInvInfoArray");
		if(selInvInfoArray != null && selInvInfoArray.length >0){
			var addWiIds="";
			for(var i =0;i < selInvInfoArray.length ;i++){
				var selInvInfo = selInvInfoArray[i];
				if(selInvInfo.wiId != _rbbId)
					addWiIds += selInvInfo.wiId+",";
			}
			if(addWiIds != null && addWiIds !=""){
				$.supper("confirm",{ title:"合并操作", msg:"确认合并库存操作？" ,yesE:function(){
					addWiIds=addWiIds.substring(0,addWiIds.length-1);
					var vdata="wiId="+_rbbId+"&addWiIds="+addWiIds;
					 $.supper("doservice", {"service":"MdInventoryService.addItemKeyMx","data":vdata, "BackE":function (jsondata) {
							if (jsondata.code == "1") {
								$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
							}else
								$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
					 }});
				}});
				
			}
			$.supper("setProductArray", {"name":"selInvInfoArray", "value":null});
		}
 	}}); 
}
 
