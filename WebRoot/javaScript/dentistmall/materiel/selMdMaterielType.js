$(function () {
	initTree();
});
function initTree(){
	//设置树形数据开始
	setting = {
			view: {  
		        dblClickExpand: false  
		    }, 
			data: {
				simpleData: {
					enable:true,
					idKey: "id",
					pIdKey: "pId",
					isParent: "isParent"
				}
			},
			check:{
				chkStyle: "radio",
				chkboxType: { "Y": "ps", "N": "ps" },
				radioType :"all",
				enable: true
			},
			async: {  
	            enable: true, 
	            url:$.supper("getServicePath", {"service":"MdMaterielTypeService.getTreeListByMdMmtId"}),  //获取异步数据的地址
	            autoParam:["id"],  
	            dataFilter: filter //设置数据的展现形式  
	      },
		   callback: {
				beforeCheck: zTreeBeforeCheck,
				onClick: onNodeClick 
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

function onNodeClick(e,treeId, treeNode) {  
    var zTree = $.fn.zTree.getZTreeObj("tree");  
    if(treeNode.isParent)
    	zTree.expandNode(treeNode);  
    else
    	zTree.checkNode(treeNode,true);
}  

function zTreeBeforeCheck(treeId, treeNode) {
    return !treeNode.isParent;//当是父节点 返回false 不让选取
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
		var checkNode=nodes[0];
		var twoNode=nodes[0].getParentNode();
		var oneNode=twoNode.getParentNode();
		var selMdMaterielType={
			oneId:oneNode.id,
			towId:twoNode.id,
			id:checkNode.id,
			name:checkNode.name
		}
		$.supper("setProductArray", {"name":"selMdMaterielType", "value":selMdMaterielType});
		closeWin();
	}
}