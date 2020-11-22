var mmg ;
var setting;
var _dblClick = function(data, row, col){
	editCompany(data.wzId);
}
$(function(){   
 	initTree();
 	initGrid();
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
	    async: {  
	            enable: true,  
	                url:$.supper("getServicePath", {"service":"SysOrgService.getOrgTree"}),  //获取异步数据的地址
	                autoParam:["id"],  
	                dataFilter: filter //设置数据的展现形式  
	      },
	    callback: {//增加点击事件
	            	beforeClick: function(treeId, treeNode) {
		                $("#orgGxId").val(treeNode.id);
		                search();
		            }
		        }
	         };  
	    	//设置树的初始数据
	         $.supper("doservice", {"service":"SysOrgService.getOrgTree","BackE":function (jsondata) {
		              $.fn.zTree.init($("#tree"), setting, jsondata);  
		              //设置树形数据结束
		              $("#tree").css("height",$(window).height()-120);
		              var zTree = $.fn.zTree.getZTreeObj("tree"); 
		              var node = zTree.getNodesByParam("id","1");
					  zTree.expandNode(node[0],  true, false, false); 
					 
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
        
function initGrid() {
	var cols = [
		{title: '编号', name: 'education', width: 50, align: 'center'},
		{title: '名称', name: 'applicantName', width: 40, align: 'center'},
		{title: '类别', name: 'orgName', width: 80, align: 'center'},
		{title: '状态', name: 'state', width: 80, align: 'center', renderer: formateState},
		{title: '操作', name: 'control', width: 100, align: 'center', renderer: formateControl}

	];
	mmg = $('#datagrid1').mmGrid({
		height: $(window).height() - 200
		, cols: cols
		, method: 'get'
		, remoteSort: true
		, url: $.supper("getServicePath", {"service": "SysOrgService.getSysCompanyPager"})
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
	var queryAction = "SysOrgService.getSysCompanyPager";
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
	mmg.load(); 
}
function formateState(val,item,rowIndex){
	if(val=='2')
		return "禁用";
	else if(val=='1')
		return "启用";
}
function formateControl(val,item,rowIndex){
	var str = "";
	if(item.state == '1')
		str += "<a onclick=\"updateState('"+item.wzId+"','2')\" class='btn btn-success btn-xs'>禁用</a>&nbsp;&nbsp;";
	else
		str += "<a onclick=\"updateState('"+item.wzId+"','1')\" class='btn btn-info btn-xs'>启用</a>&nbsp;&nbsp;";
		str += "<a onclick=\"editCompany('"+item.wzId+"')\" class='btn btn-warning btn-xs'>修改</a>&nbsp;&nbsp;";
	return str;
}
		
function editCompany(wzId){
	if(wzId != null && wzId != ''){
		var data = "wzId="+wzId; 
		var att_url= $.supper("getServicePath", {"service":"SysOrgService.findSysCompany", "data":data,url:"sys/sysorg/editSysCompany"});
		var tt_win=$.supper("showWin",{url:att_url,title:"组织信息",icon:"fa-sitemap",width:800,height:500,BackE:search}); 
	}else{
		var orgGxId = $("#orgGxId").val();
		if(orgGxId ==null || orgGxId==""){
			$.supper("alert",{ title:"操作提示", msg:"请选择组织类型!"});
			return;
		}
		var data="orgGxId="+orgGxId;
		var att_url= $.supper("getServicePath", {"service":"SysOrgService.findSysCompany","data":data,url:"sys/sysorg/editSysCompany"});
		var tt_win=$.supper("showWin",{url:att_url,title:"组织信息",icon:"fa-sitemap",width:800,height:500,BackE:search}); 
	}
}
function updateState(wzId,state){
	var vdata = "wzId="+wzId+"&state="+state;
	$.supper("doservice", {"service":"SysOrgService.updateSysCompanyState","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}