var lastExpandNode;
var setting; 
var mmg;
var _dblClick = function (data, row, col) {
	editControlInfo(data.sconId);
}
$(function(){   
	$.supper('initUpFile',{id:"fileCodeBtn",codeId:"fileCode"}); 
		//设置树形数据开始
	setting = {
		
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
            url:$.supper("getServicePath", {"service":"SysMenuService.getSysMenuList"}),  //获取异步数据的地址
            autoParam:["id"],  
            dataFilter: filter //设置数据的展现形式  
        },
        callback: {//增加点击事件
        	beforeClick: function(treeId, treeNode) {
            	lastExpandNode = treeNode;//记录当前点击的节点
            	if(treeNode.id !=null){
            		$("#selId").val(treeNode.id);
                	$("#smenuId2").val(treeNode.id);
                	initForm(treeNode.id);
                	search();
            	}else{
            		$("#selId").val("");
                	$("#smenuId2").val("");
                	clearForm();
                	search();
            	}
            	
            }
        }
     };  
	initOrg();
	initGrid();
});
	
function initOrg(){
	//设置树的初始数据
    var zNodes =[ 
   				{ pId:"", name:"平台系统", isParent:true}
		        ];  
    $.fn.zTree.init($("#tree"), setting, zNodes); 
    $("#tree").css("height",$(window).height()-120);
	
}
//设置数据的展现形式 
function filter(treeId, parentNode, childNodes) {  
    if (!childNodes) return null;  
    for (var i=0, l=childNodes.length; i<l; i++) {  
        childNodes[i].name = childNodes[i].name.replace('','');  
    }  
    return childNodes;  
}  

//当增加树的数据后刷新当前节点
function loadAddTree(){
	 var zTree = $.fn.zTree.getZTreeObj("tree"); 
	 if(lastExpandNode != null)  
      	{  
            var zTree = $.fn.zTree.getZTreeObj("tree");  
            if(lastExpandNode != null)//刷新当前节点
            	zTree.reAsyncChildNodes(lastExpandNode, "refresh");  
        }  
}
//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree(){
	 var zTree = $.fn.zTree.getZTreeObj("tree"); 
	 if(lastExpandNode != null)  
      	{  
            var zTree = $.fn.zTree.getZTreeObj("tree");  
            if(lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
           		zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");  
            else
            	initOrg();
        }  
}

function clearForm(){
	$("#menuName").val("");
	$("#menuOrderCode").val("");
	$("#menuIcon").val("");
	$("#menuAddree").val("");
	$("#smenuId").val("");
	$("#sysSmenuId").val("");
	$("#menuCode").val("");
	$("#menuState").val("1");
	$("#menuType").val("1");
	
	$("#up_fileCodeBtn").show();
	$("#kup_fileCodeBtn").show();
	$("#upMsg_fileCodeBtn").remove();
	$("#fileCode").val("");
	$("#fileCodeBtn").attr("value","");
}

function initForm(vid){
	clearForm();
	var vdata = "smenuId="+vid;
	$.supper("doservice", {"service":"SysMenuService.findSysMenuInfo", "data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			var parent = jsondata.obj.pinfo;
			if(parent != null){
				$("#parentName").html(parent.menuName);
				$("#parentCode").html(parent.menuCode);
				$("#sysSmenuId").val(parent.smenuId);
			}
			var info = jsondata.obj.info;
			if(info != null){
				$("#menuName").val(info.menuName);
				$("#menuCodeDiv").html(info.menuCode);
				$("#menuOrderCode").val(info.menuOrderCode);
				$("#menuIcon").val(info.menuIcon);
				$("#menuAddree").val(info.menuAddree);
				$("#smenuId").val(info.smenuId);
				$("#menuCode").val(info.menuCode);
				$("#menuState").val(info.menuState);
				$("#menuType").val(info.menuType);
				$("#fileCode").val(info.fileCode);
				$("#fileCodeBtn").attr("value",info.fileCode);
				$.supper('initUpFile',{id:"fileCodeBtn",codeId:"fileCode"}); 
				
			}
		}
		}});
}

function toAddChileMenu(){
	var pid = $("#selId").val();
	var vdata = "1=1";
	if(pid != null && pid != "")
		vdata = "sysSmenuId="+pid;
	clearForm();
	$.supper("doservice", {"service":"SysMenuService.findSysMenuInfo", "data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			var parent = jsondata.obj.pinfo;
			if(parent != null){
				$("#sysSmenuId").val(parent.smenuId);
			}
			var info = jsondata.obj.info;
			if(info != null){
				$("#menuName").val(info.menuName);
				$("#menuOrderCode").val(info.menuOrderCode);
				$("#menuIcon").val(info.menuIcon);
				$("#menuAddree").val(info.menuAddree);
				$("#smenuId").val(info.smenuId);
				$("#menuCode").val(info.menuCode);
			}
		}
		}});
	
}

function save(){
	var menuName=$("#menuName").val();
	if(menuName == null || $.trim(menuName)==''){
		$.supper("alert",{ title:"操作提示", msg:"请输入菜单名称！"});
		return ;
	}
	var menuAddree=$("#menuAddree").val();
	if(menuAddree == null || $.trim(menuAddree)==''){
		$.supper("alert",{ title:"操作提示", msg:"请输入菜单地址！"});
		return ;
	}
	
	var data =$('#menuForm').serialize();
	$.supper("doservice", {"service":"SysMenuService.saveSysMenu", "ifloading":1,"options":{"type":"post","data":data}, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:function (){
				clearForm();
				loadUpdateTree();
			}});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	}});
}

function delMenu(){
	var pid = $("#selId").val();
	if(pid != null && pid !=""){
		var vdata = "smenuId="+pid;
		$.supper("doservice", {"service":"SysMenuService.delSysMenu", "data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				clearForm();
				$("#selId").val("");
				$.supper("alert",{ title:"操作提示", msg:"操作成功！",BackE:function(){
					$("#selId").val("");
					clearForm();
					loadUpdateTree();
				}});
			}else{
				$.supper("alert",{ title:"操作提示", msg:jsondata.meg});
			}
		}});
	}else{
		$.supper("alert",{ title:"操作提示", msg:"请选择删除的点！"});
		return ;
	}
}
function initGrid() {
	var cols = [
		{title: '编号', name: 'sconCode', width: 50, align: 'center'},
		{title: '名称', name: 'sconName', width: 40, align: 'center'},
		{title: '类别', name: 'sconType', width: 80, align: 'center', renderer: formateType},
		{title: '操作', name: 'control', width: 100, align: 'center', renderer: formateControl}
	];
	mmg = $('#datagrid1').mmGrid({
		height: $(window).height() - 200
		, cols: cols
		, method: 'get'
		, remoteSort: true
		, url: $.supper("getServicePath", {"service": "SysMenuService.getSysControlInfoPager"})
		, multiSelect: true
		, checkCol: false
		, fullWidthRows: true
		, autoLoad: false
		, showBackboard: false
		, dblClickFunc: _dblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
	mmg.load();
}
function search(){
	var queryAction = "SysMenuService.getSysControlInfoPager";
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
	mmg.load(); 
}
function formateType(val,item,rowIndex){
	if(val=='div')
		return "显示层";
	else if(val=='button')
		return "按钮";
	else if(val=='img')
		return "图片";
	else if(val=='input')
		return "输入框";
	else if(val=='raddio')
		return "单选框";
	else if(val=='checkbox')
		return "多选框";
	else if(val=='select')
		return "下拉选择框";
	else
		return "其他";
}
function formateControl(val,item,rowIndex){
	var str = "";
	str += "<a onclick=\"editControlInfo('"+item.sconId+"')\" class='btn btn-warning btn-xs'>修改</a>&nbsp;&nbsp;";
	str += "<a onclick=\"delControlInfo('"+item.sconId+"')\" class='btn btn-danger btn-xs'>删除</a>";
	return str;
}
function editControlInfo(sconId){
	if(sconId != null && sconId != ''){
		var data = "sconId="+sconId; 
		var att_url= $.supper("getServicePath", {"service":"SysMenuService.findSysControlInfo", "data":data,url:"sys/sysmenu/editControlInfo"});
		var tt_win=$.supper("showWin",{url:att_url,title:"控件信息",icon:"fa-life-ring",width:800,height:500,BackE:search}); 
	}else{
		var smenuId = $("#smenuId2").val();
		if(smenuId ==null || smenuId==""){
			$.supper("alert",{ title:"操作提示", msg:"请选择菜单!"});
			return;
		}
		var data="smenuId="+smenuId;
		var att_url= $.supper("getServicePath", {"service":"SysMenuService.findSysControlInfo","data":data,url:"sys/sysmenu/editControlInfo"});
		var tt_win=$.supper("showWin",{url:att_url,title:"控件信息",icon:"fa-life-ring",width:800,height:500,BackE:search}); 
	}
}

function delControlInfo(sconId){
	var vdata = "sconId="+sconId;
	$.supper("confirm",{ title:"删除控件", msg:"确认删除控件？", yesE:function(){
		$.supper("doservice", {"service":"SysMenuService.delSysControlInfo","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}