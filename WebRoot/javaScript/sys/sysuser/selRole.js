var suiId;
$(function () {
	suiId = $.supper("getParam", "suiId");
	initRoleTree();
});
function initRoleTree(){
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
			chkboxType: { "Y": "", "N": "" },
			enable: true
		},
		callback: {
			beforeCheck: zTreeBeforeCheck
		}
	};
	//设置树的初始数据
	var zNodes =[];
	$.supper("doservice", {"service":"SysRoleService.getAllRoleTree","BackE":function (jsondata) {
		if (jsondata) {
			zNodes = jsondata;
			$.fn.zTree.init($("#tree"), setting, zNodes);  
    		//设置树形数据结束
    		selRole();
		}
	}});
    
}
function zTreeBeforeCheck(treeId, treeNode) {
    return !treeNode.isParent;//当是父节点 返回false 不让选取
};
function selRole(){
	var zTree = $.fn.zTree.getZTreeObj("tree"); 
	$.supper("doservice", {"service":"SysUserService.getUserRole", "data":"suiId="+suiId,"BackE":function (jsondata) {
		if (jsondata.code == "1") {
			
			if(jsondata.obj != null && jsondata.obj.length > 0){
				
				for(var i = 0; i < jsondata.obj.length;i++){
					var node = zTree.getNodeByParam("id",jsondata.obj[i].sroleId);
					zTree.checkNode(node,true,false,false);
				}
			}
			
		}
	}});
}

function  closeWin(){
	$.supper("closeWin");
}

function saveRole(){
	var zTree = $.fn.zTree.getZTreeObj("tree"); 
	var nodes = zTree.getCheckedNodes(true);
	var data = "suiId="+suiId;
	if(nodes != null && nodes.length > 0){
		var str = "";
		for(var i = 0; i < nodes.length ; i++)
			str += nodes[i].id + ",";
		str = str.substring(0,str.length - 1);
		data += "&sroleIds="+str;
	}
	$.supper("doservice", {"service":"SysUserService.saveUserRole", "data":data,"BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！",BackE:closeWin});
		}
	}});
	
}