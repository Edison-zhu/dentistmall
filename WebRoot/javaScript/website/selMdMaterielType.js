$(function () {
	initTree();
});
function initTree(){
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
			check:{
				chkStyle : "radio",
				chkboxType:{ "Y": "", "N": "" },
				radioType: "all",
				enable: true
			},
			async: {  
	            enable: true, 
	            url:$.supper("getServicePath", {"service":"MdMaterielTypeService.getTreeListByMdMmtId"}),  //获取异步数据的地址
	            autoParam:["id"],  
	            dataFilter: filter //设置数据的展现形式  
	      },
		   callback: {
				beforeCheck: zTreeBeforeCheck
			}
         };
	var zNodes =[{id:0, pId:"", name:"商品类别列表", isParent:true}];  
	$.fn.zTree.init($("#tree"), setting, zNodes);  
	//自动展现第一层树
	var zTree = $.fn.zTree.getZTreeObj("tree"); 
	var node = zTree.getNodesByParam("id","0");
	lastExpandNode=node;
	zTree.expandNode(node[0],  true, false, false); 
	
}

function zTreeBeforeCheck(treeId, treeNode) {
	if(treeNode.id=="0")
		return false;
};
function allotObjTreeOnCheck(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("tree"); 
	zTree.checkNode(treeNode, true, false);
};
//设置数据的展现形式 
function filter(treeId, parentNode, childNodes) {  
	if (!childNodes) return null;  
	for (var i=0, l=childNodes.length; i<l; i++) {  
		childNodes[i].name = childNodes[i].name.replace('','');  
	}  
	return childNodes;  
} 

function  closeWin(){
	$.supper("closeWin");
}

function save(){
	var zTree = $.fn.zTree.getZTreeObj("tree"); 
	var nodes = zTree.getCheckedNodes(true);
	if(nodes ==null || nodes.length <=0 ){
		$.supper("alert",{ title:"操作提示", msg:"请选择数据！"});
		return;
	}else{
		$.supper("setProductArray", {"name":"selMdMaterielType", "value":nodes[0]});
		closeWin();
	}
}