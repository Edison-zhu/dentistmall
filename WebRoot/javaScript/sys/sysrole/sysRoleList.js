$(function(){
	initRoleTree();
	initOrg();
	initMenuTree();
	initGrid();
});
var rolSetting;
var mmg;
function editRole(){
	var sroleId = $("#sroleId").val();
	if(sroleId == null || sroleId == ''){
		$.supper("alert",{ title:"操作提示", msg:"请选择岗位或岗位组！"});
   		return;
	}
	var data="sroleId="+sroleId;
	var att_url= $.supper("getServicePath", {"service":"SysRoleService.findSysRole","data":data,url:"sys/sysrole/editSysRole"});
	var tt_win=$.supper("showWin",{url:att_url,width:800,height:400,icon:"fa-yelp",title:"岗位信息",BackE:loadUpdateTree}); 
}

function addRole(){
	var sroleId = $("#sroleId").val();
	if(sroleId == null || sroleId == ''){
		$.supper("alert",{ title:"操作提示", msg:"请选择岗位组！"});
   		return;
	}
	var isParent = $("#isParent").val();
	if(isParent != '1'){
		$.supper("alert",{ title:"操作提示", msg:"岗位下不能添加子岗位！"});
   		return;
	}
	var data="sysSroleId="+sroleId;
	var att_url= $.supper("getServicePath", {"service":"SysRoleService.findSysRole","data":data,url:"sys/sysrole/editSysRole"});
	var tt_win=$.supper("showWin",{url:att_url,width:800,height:400,icon:"fa-yelp",title:"岗位信息",BackE:loadAddTree}); 
}
function addRoleGroup(){
	var att_url= $.supper("getServicePath", {"service":"SysRoleService.findSysRole",url:"sys/sysrole/editSysRole"});
	var tt_win=$.supper("showWin",{url:att_url,width:800,height:400,icon:"fa-yelp",title:"岗位组信息",BackE:initOrg}); 
}


function delRole(){
	var sroleId = $("#sroleId").val();
	if(sroleId == null || sroleId == ''){
		$.supper("alert",{ title:"操作提示", msg:"请选择岗位或岗位组！"});
   		return;
	}
	var vdata = "sroleId="+sroleId;
	$.supper("confirm",{ title:"删除岗位", msg:"确认删除岗位？", yesE:function(){
		$.supper("doservice", {"service":"SysRoleService.delSysRole","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:loadUpdateTree});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

function initRoleTree(){
	//设置树形数据开始
	rolSetting = {
		
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
            url:$.supper("getServicePath", {"service":"SysRoleService.getSysRoleList"}),  //获取异步数据的地址
            autoParam:["id"],  
            dataFilter: filter //设置数据的展现形式  
        },
        callback: {//增加点击事件
        	beforeClick: function(treeId, treeNode) {
            	lastExpandNode = treeNode;//记录当前点击的节点
            	$("#sroleId").val(treeNode.id);
            	if(treeNode.isParent)
            		$("#isParent").val("1");
            	else
            		$("#isParent").val("0");
            	selMenu();
            	getSel();
            }
        }
     };  
    
}

function initOrg(){
	//设置树的初始数据
	$.supper("doservice", {"service":"SysRoleService.getSysRoleList","BackE":function (jsondata) {
		$.fn.zTree.init($("#roleTree"), rolSetting, jsondata);  
		//设置树形数据结束
		$("#roleTree").css("height",$(window).height()-120);
	}});
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
	 var zTree = $.fn.zTree.getZTreeObj("roleTree"); 
	 if(lastExpandNode != null)  
      	{  
            var zTree = $.fn.zTree.getZTreeObj("roleTree");  
            if(lastExpandNode != null)//刷新当前节点
            	zTree.reAsyncChildNodes(lastExpandNode, "refresh");  
        }  
}
//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree(){
	 var zTree = $.fn.zTree.getZTreeObj("roleTree"); 
	 if(lastExpandNode != null)  
      	{  
            var zTree = $.fn.zTree.getZTreeObj("roleTree");  
            if(lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
           		zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");  
            else
            	initOrg();
        }  
}

function initMenuTree(){
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
		check:{
			checkstyle: "checkbox",
			enable: true
		},
        callback: {//增加点击事件
        	beforeClick: function(treeId, treeNode) {
            	$("#smenuId").val(treeNode.id);
            	search();
            }
        }
	};
	//设置树的初始数据
	var zNodes =[];
	$.supper("doservice", {"service":"SysMenuService.getAllSysMenuList", "BackE":function (jsondata) {
		if (jsondata) {
			zNodes = jsondata;
			$.fn.zTree.init($("#menutree"), setting, zNodes);  
    		//设置树形数据结束
		}
	}});
    
}

function selMenu(){
	var sroleId= $("#sroleId").val();
	var zTree = $.fn.zTree.getZTreeObj("menutree"); 
	zTree.checkAllNodes(false);
	$.supper("doservice", {"service":"SysRoleService.getRoleMenuListByRoleId", "data":"sroleId="+sroleId,"BackE":function (jsondata) {
		if (jsondata.code == "1") {
			if(jsondata.obj != null && jsondata.obj.length > 0){
				var zTree = $.fn.zTree.getZTreeObj("menutree"); 
				for(var i = 0; i < jsondata.obj.length;i++){
					var node = zTree.getNodeByParam("id",jsondata.obj[i].smenuId);
					if(node != null)
						zTree.checkNode(node,true,false,false);
				}
			}
			
		}
	}});
}

function saveRoleMenu(){
	var sroleId= $("#sroleId").val();
	if(sroleId == null || sroleId == ""){
		$.supper("alert",{ title:"操作提示", msg:"请选择岗位！"});
		return;
	}
	var zTree = $.fn.zTree.getZTreeObj("menutree"); 
	var nodes = zTree.getCheckedNodes(true);
	var data = "sroleId="+sroleId;
	if(nodes != null && nodes.length > 0){
		var str = "";
		for(var i = 0; i < nodes.length ; i++)
			str += nodes[i].id + ",";
		str = str.substring(0,str.length - 1);
		data += "&smenuIds="+str;
	}
	$.supper("doservice", {"service":"SysRoleService.saveSysRoleMenus", "data":data,"BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
		}
	}});
	
}

function initGrid(){
	var cols = [
	           { title:'编号', name:'sconCode' ,width:80,  align:'center' },
	           { title:'名称' ,name:'sconName',width:30,  align:'center' },
	           { title:'类别', name:'sconType' ,width:30,  align:'center',renderer:formateType},
	           {title:'操作', name:'control', width: 80, align: 'center',renderer:formateControl}
	           ];  
	mmg = $('#datagrid1').mmGrid({
	       		height:$(window).height()-200
	            , cols: cols
	            , method: 'get'
	            , remoteSort:true
	            , multiSelect: true
	            , checkCol: false
	            , fullWidthRows: true
	            ,showBackboard:false
	            , autoLoad: false
	            , plugins: [
	                $('#pg').mmPaginator({})
	            ]
	 });  
	 mmg.on("loadSuccess",getSel);
	 mmg.load([]); 
}
function search(){
	var smenuId=$("#smenuId").val();
	var att_mmgurl = $.supper("getServicePath", {"service":"SysMenuService.getSysControlInfoPager","data":"smenuId="+smenuId});
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
	var str = "<select id=\""+item.sconId+"\">"
			+"<option value='0' selected >隐藏 </option>"
			+"<option value='1'>显示</option>"
			+"<option value='2'>只读</option>"
			+"</select>";
	return str;
}

function getSel(){
	var sroleId= $("#sroleId").val();
	var smenuId = $("#smenuId").val();
	if(sroleId != null && sroleId != "" && smenuId != null && smenuId != ""){
		var data = "sroleId="+sroleId+"&smenuId="+smenuId;
		
		$.supper("doservice", {"service":"SysRoleService.getRoleControlListByRoleId", "data":data,"BackE":function (jsondata) {
			if (jsondata.code == "1") {
				var rows = mmg.rows();
				
				if(rows != null && rows.length > 0 && jsondata.obj != null && jsondata.obj.length >0){
					for(var i = 0; i < rows.length;i++){
						var flag =false;
						for(var j =0;j < jsondata.obj.length;j ++){
							if(rows[i].sconId==jsondata.obj[j].SCON_ID){
								$("#"+rows[i].sconId).val(jsondata.obj[j].IF_OPERATE);
								flag= true;
								break;
							}
						}
						if(!flag)
							$("#"+rows[i].sconId).val("0");
					}
				}else{
					for(var i = 0; i < rows.length;i++)
						$("#"+rows[i].sconId).val("0");
				}
			}
		}});
	}
}

function saveRoleControl(){
	var sroleId= $("#sroleId").val();
	if(sroleId == null || sroleId == ""){
		$.supper("alert",{ title:"操作提示", msg:"请选择岗位！"});
		return;
	}
	var rows = mmg.rows();
	var sconIds = "";
	var oprates="";
	if(rows != null && rows.length > 0){
		for(var i = 0; i < rows.length;i++){
			sconIds += rows[i].sconId+",";
			oprates += $("#"+ rows[i].sconId).val()+",";
		}
		sconIds = sconIds.substring(0, sconIds.length-1);
		oprates = oprates.substring(0, oprates.length-1);
	}
	if(sconIds == null || sconIds == ""){
		$.supper("alert",{ title:"操作提示", msg:"请选择控件信息！"});
		return;
	}
	var data = "sroleId="+sroleId+"&sconIds="+sconIds+"&oprates="+oprates;
	$.supper("doservice", {"service":"SysRoleService.saveSysRoleControls", "data":data,"BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	}});
	
}