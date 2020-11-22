var _mszType;
var _swcId;
$(function(){ 
	$("#tree").css("height",$(window).height()-165);
	loadItemZtree();
	initDataGrid();
	search();
});

var mmg ;
var _querydblClick = function (data, row, col) {
	toQueryUpdate(data.swmId, data.swcId);
}
var _formatdblClick = function (data, row, col) {
	toUpdate(data.mscId);
}
//普通栏目
var queryAction = "SysWebsiteCommentService.GetPMSysWebsiteComment";
var initDataGrid = function(){
	var cols = [
		{title: '编码', name: 'newscode', width: 120, align: 'center'},
		{title: '标题', name: 'newsTitle', width: 150, align: 'center'},
		{title: '序号', name: 'serialNumber', width: 30, align: 'center'},
		{title: '状态', name: 'state', width: 30, align: 'center', renderer: formatCommState},
		{title: '操作', name: 'mwmId', width: 100, align: 'center', renderer: formateQueryComplete}
	];
	mmg = $('#datagrid1').mmGrid({
		height: 'auto'
		, cols: cols
		, method: 'get'
		, remoteSort: false
		, multiSelect: true
		, checkCol: false
		, fullWidthRows: true
		, autoLoad: false
		, showBackboard: false
		, nowrap: true
		, dblClickFunc: _querydblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
}
//商品栏目
var matAction = "MdSiteCommentService.getMdCommentMaterielViewPager";
var initMatDataGrid = function(){
	var cols = [
		{title: '商品编码', name: 'matCode', width: 100, align: 'center'},
		{title: '商品名称', name: 'matName', width: 150, align: 'center'},
		{title: '序号', name: 'serialComm', width: 80, align: 'center'},
		{title: '商品状态', name: 'state', width: 80, align: 'center', renderer: formatState},
		{title: '发布状态', name: 'commState', width: 80, align: 'center', renderer: formatCommState},
		{title: '操作', name: 'mscId', width: 100, align: 'center', renderer: formateComplete}
	];
	mmg = $('#datagrid1').mmGrid({
		height: 'auto'
		, cols: cols
		, method: 'get'
		, remoteSort: false
		, multiSelect: true
		, checkCol: false
		, showBackboard: false
		, fullWidthRows: true
		, autoLoad: false
		, nowrap: true
		, dblClickFunc: _formatdblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
}
//供应商栏目
var supplierAction = "MdSiteCommentService.getMdCommentSupplierViewPager";
var initSupplierGrid = function(){
	var cols = [
		{title: '供应商名称', name: 'applicantName', width: 150, align: 'center'},
		{title: '序号', name: 'serialComm', width: 80, align: 'center'},
		{title: '供应商状态', name: 'state', width: 80, align: 'center', renderer: formatState},
		{title: '发布状态', name: 'commState', width: 80, align: 'center', renderer: formatCommState},
		{title: '操作', name: 'mscId', width: 100, align: 'center', renderer: formateComplete}
	];
	mmg = $('#datagrid1').mmGrid({
		height: 'auto'
		, cols: cols
		, method: 'get'
		, remoteSort: false
		, multiSelect: true
		, showBackboard: false
		, checkCol: false
		, fullWidthRows: true
		, autoLoad: false
		, nowrap: true
		, dblClickFunc: _formatdblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
}
//商品分类栏目
var typeAction = "MdSiteCommentService.getMdCommentTypeViewPager";
var initTypeGrid = function () {
	var cols = [
		{title: '类别编码', name: 'mmtCode', width: 100, align: 'center'},
		{title: '类别名称', name: 'mmtName', width: 150, align: 'center'},
		{title: '序号', name: 'serialComm', width: 30, align: 'center'},
		{title: '类别状态', name: 'state', width: 80, align: 'center', renderer: formatState},
		{title: '发布状态', name: 'commState', width: 80, align: 'center', renderer: formatCommState},
		{title: '操作', name: 'mscId', width: 100, align: 'center', renderer: formateComplete}
	];
	mmg = $('#datagrid1').mmGrid({
		height: 'auto'
		, cols: cols
		, method: 'get'
		, remoteSort: false
		, multiSelect: true
		, checkCol: false
		, fullWidthRows: true
		, autoLoad: false
		, showBackboard: false
		, nowrap: true
		, dblClickFunc: _formatdblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
}

//--------------------------------tree---------------------------

function loadItemZtree(){

//设置树形数据开始
	    	 var setting = {
	    	 	data: {
		            simpleData: {
		                enable:true,
		                idKey: "id",
		                pIdKey: "pId",
		                isParent: "isParent"
		            }
	        	},
	        	async: {  
	                enable: true,  
	                url:$.supper("getServicePath", {"service":"SysWebsiteColumnsService.getSiteColumnsTree","data":"type=0"}),  //获取异步数据的地址
	                autoParam:["id"],  
	                dataFilter: filter //设置数据的展现形式  
	            },
	            callback: {//增加点击事件
		            beforeClick: function(treeId, treeNode) {
		            	if(treeNode.id !=0){
		            		_mszType = treeNode.mszType;
			            	_swcId = treeNode.id;
		            	}else{
		            		_swcId=null;
		            		_mszType=null;
		            	}
		            	initSearch();
		            	initGrid();
		            }
		        }
	         };
	         //设置树的初始数据
	          var zNodes =[ 
	         				{id:0, pId:"", name:"栏目列表", isParent:true}
				        ];  
              $.fn.zTree.init($("#tree"), setting, zNodes);  
              //设置树形数据结束
}

//设置数据的展现形式 
function filter(treeId, parentNode, childNodes) {  
	if (!childNodes) 
		return null;  
	for (var i=0, l=childNodes.length; i<l; i++) {  
		childNodes[i].name = childNodes[i].name.replace('','');  
	}  
	return childNodes;  
}  

//--------------------------------tree---------------------------
function initSearch(){
	$("#newsTitle").val("");
	$("#queryState").val("");
	$("#matName").val("");
	$("#matState").val("");
	$("#matCommState").val("");
	$("#applicantName").val("");
	$("#supplierState").val("");
	$("#supplierCommState").val("");
	$("#mmtName").val("");
	$("#typeState").val("");
	$("#typeCommState").val("");
	if(_mszType == "1" || _mszType == "5" || _mszType == "6"){
		$("#matDiv").hide();
		$("#supplierDiv").hide();
		$("#typeDiv").hide();
		$("#queryDiv").show();
		
	}else if(_mszType == "2"){
		$("#queryDiv").hide();
		$("#supplierDiv").hide();
		$("#typeDiv").hide();
		$("#matDiv").show();
	}else if(_mszType == "3"){
		$("#queryDiv").hide();
		$("#matDiv").hide();
		$("#typeDiv").hide();
		$("#supplierDiv").show();
	}else if(_mszType == "4"){
		$("#queryDiv").hide();
		$("#matDiv").hide();
		$("#supplierDiv").hide();
		$("#typeDiv").show();
	}else{
		$("#matDiv").hide();
		$("#supplierDiv").hide();
		$("#typeDiv").hide();
		$("#queryDiv").show();
	}
}
function initGrid(){
	$("#gridDiv").html("<table id=\"datagrid1\" class=\"mmg\" ></table><div id=\"pg\" style=\"text-align: right;\"></div>");
	if(_mszType == "1" || _mszType == "5" || _mszType == "6")//普通
		initDataGrid();
	else if(_mszType == "2")//商品
		initMatDataGrid();
	else if(_mszType == "3")//供应商
		initSupplierGrid();
	else if(_mszType == "4")//供应商
		initTypeGrid();
	else
		initDataGrid();
	search();
}
function search(){  
	var att_mmgurl = "";
	if(_mszType == "1" || _mszType == "5" || _mszType == "6"){
		var data=$('#queryForm').serialize()+"&swcId="+_swcId;
		att_mmgurl= $.supper("getServicePath", {service:queryAction,"data":data});
	}//普通
	else if(_mszType == "2"){
		var data=$('#matForm').serialize()+"&swcId="+_swcId;
		att_mmgurl= $.supper("getServicePath", {service:matAction,"data":data});
	}//商品
	else if(_mszType == "3"){
		var data=$('#supplierForm').serialize()+"&swcId="+_swcId;
		att_mmgurl= $.supper("getServicePath", {service:supplierAction,"data":data});
	}//供应商
	else if(_mszType == "4"){
		var data=$('#typeForm').serialize()+"&swcId="+_swcId;
		att_mmgurl= $.supper("getServicePath", {service:typeAction,"data":data});
	}//供应商
	else{
		var data=$('#queryForm').serialize();
		att_mmgurl= $.supper("getServicePath", {service:queryAction,"data":data});
	}
	mmg.opts.url = att_mmgurl;
    mmg.load();
}

function add(){ 
	var itemId = _swcId;
	if(!itemId){
		$.supper("alert",{ title:"操作提示", msg:"请选择栏目信息！"});
		return false;
	}
	var data = "swcId="+itemId;
	if(_mszType=="1"){
		var commUrl="";
		if(itemId=='9')
			commUrl="/jsp/website/lbWebsiteComment";
		else if(itemId=='16')
			commUrl="/jsp/website/searchWebsiteComment";
		else if(itemId=='18')
			commUrl="/jsp/website/companyWebsiteComment";
		else
			commUrl="/jsp/website/sysWebsiteComment";
		var att_url= $.supper("getServicePath", {"service":"SysWebsiteCommentService.addSysWebsitComment","data":data,url:commUrl});
		if(itemId=='16')
			var tt_win=$.supper("showWin",{url:att_url,title:"内容信息",icon:"fa-file-text-o",width:800,height:500,BackE:search}); 
		else
			$.supper("showTtemWin",{ "url":att_url,"title":"内容信息"});
	}else if(_mszType=='5'){
		var commUrl="/jsp/website/lbWebsiteComment";
		var att_url= $.supper("getServicePath", {"service":"SysWebsiteCommentService.addSysWebsitComment","data":data,url:commUrl});
		$.supper("showTtemWin",{ "url":att_url,"title":"内容信息"});
	}else if(_mszType=='6'){
		var commUrl="/jsp/website/companyWebsiteComment";
		var att_url= $.supper("getServicePath", {"service":"SysWebsiteCommentService.addSysWebsitComment","data":data,url:commUrl});
		$.supper("showTtemWin",{ "url":att_url,"title":"内容信息"});
	}else{
		data += "&mszType="+_mszType;
		var att_url= $.supper("getServicePath", {"data":data,url:"/jsp/website/mdSiteComment"});
		var tt_win=$.supper("showWin",{url:att_url,title:"内容信息",icon:"fa-file-text-o",width:800,height:500,BackE:search}); 
	}
	
}
function toQueryUpdate(swmId,swcId){ 
	var data = "swmId="+swmId;
	var commUrl="";
	if(_mszType=='1'){
		if(swcId=='9')
			commUrl="/jsp/website/lbWebsiteComment";
		else if(swcId=='16')
			commUrl="/jsp/website/searchWebsiteComment";
		else if(swcId=='18')
			commUrl="/jsp/website/companyWebsiteComment";
		else
			commUrl="/jsp/website/sysWebsiteComment";
		var att_url= $.supper("getServicePath", {"service":"SysWebsiteCommentService.GetOneSysWebsitComment","data":data,url:commUrl});
		if(swcId=='16')
			var tt_win=$.supper("showWin",{url:att_url,title:"内容信息",icon:"fa-file-text-o",width:800,height:500,BackE:search}); 
		else
			$.supper("showTtemWin",{ "url":att_url,"title":"内容信息"});
	}
	else if(_mszType=='5'){
		var commUrl="/jsp/website/lbWebsiteComment";
		var att_url= $.supper("getServicePath", {"service":"SysWebsiteCommentService.GetOneSysWebsitComment","data":data,url:commUrl});
		$.supper("showTtemWin",{ "url":att_url,"title":"内容信息"});
	}else if(_mszType=='6'){
		var commUrl="/jsp/website/companyWebsiteComment";
		var att_url= $.supper("getServicePath", {"service":"SysWebsiteCommentService.GetOneSysWebsitComment","data":data,url:commUrl});
		$.supper("showTtemWin",{ "url":att_url,"title":"内容信息"});
	}
}

function toQueryDelete(swmId) {
	var data = "swmId="+swmId; 
	$.supper("confirm",{ title:"删除内容", msg:"确认删除内容？", yesE:function(){
		$.supper("doservice", {"service":"SysWebsiteCommentService.deleteSysWebsiteComment","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
	
}
function formatState(val,item,rowIndex){
	if(val=="1")
		return "启用";
	else if(val=="2")
		return "禁用";
   return "";
}

function formatCommState(val,item,rowIndex){
	if(val=="1")
		return "发布";
	else if(val=="2")
		return "草稿";
   return "";
}

function formateQueryComplete(val,item,rowIndex){
	 return "<a onclick=\"toQueryUpdate('"+item.swmId+"','"+item.swcId+"')\" class='btn btn-info btn-xs'>修改</a>&nbsp;" +
	 		"<a onclick=\"toQueryDelete('"+item.swmId+"')\" class='btn btn-danger btn-xs'>删除</a>&nbsp;";
}

function formateComplete(val,item,rowIndex){
	 return "<a onclick=\"toUpdate('"+item.mscId+"')\" class='btn btn-info btn-xs'>修改</a>&nbsp;" +
	 		"<a onclick=\"toDelete('"+item.mscId+"')\" class='btn btn-danger btn-xs'>删除</a>&nbsp;";
}

function toUpdate(mscId){ 
	var data = "mscId="+mscId;
	data += "&mszType="+_mszType;
	data += "&swcId="+_swcId;
	var att_url= $.supper("getServicePath", {"data":data,url:"/jsp/website/mdSiteComment"});
	var tt_win=$.supper("showWin",{url:att_url,title:"内容信息",icon:"fa-file-text-o",width:800,height:500,BackE:search}); 
}

function toDelete(mscId) {
	var data = "mscId="+mscId; 
	$.supper("confirm",{ title:"删除内容", msg:"确认删除内容？", yesE:function(){
		$.supper("doservice", {"service":"MdSiteCommentService.deleteObject","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
	
}



		 