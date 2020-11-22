$(function(){   
	$("#tree").css("height",$(window).height()-165);
	loadItemZtree();
	initDataGrid();
  
});

var mmg ;
var lastExpandNode;  
var queryAction = "SysWebsiteColumnsService.GetPMSysWebsiteColumns";
var _dblClick = function (data, row, col) {
	toUpdate(data.swcId);
}
var initDataGrid =  function(){
	var cols = [
		{title: '栏目编码', name: 'columnscode', width: 100, align: 'center'},
		{title: '栏目名称', name: 'columnName', width: 100, align: 'center'},
		{title: '栏目类型', name: 'mszType', width: 50, align: 'center', renderer: formatType},
		{title: '显示顺序', name: 'serialNumber', width: 50, align: 'center'},
		{title: '状态', name: 'state', width: 20, align: 'center', renderer: formatState},
		{title: '操作', name: 'mszId', width: 100, align: 'center', renderer: formateComplete}
	];

	var att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
	mmg = $('#datagrid1').mmGrid({
		height: 'auto'
		, cols: cols
		, method: 'get'
		, remoteSort: true
		, url: att_mmgurl
		, sortName: 'SECUCODE'
		, sortStatus: 'asc'
		, multiSelect: true
		, checkCol: false
		, fullWidthRows: true
		, showBackboard: false
		, autoLoad: false
		, dblClickFunc: _dblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
	mmg.load();
}
var loadItemZtree =  function (){
	//设置树形数据开始
	    	 var setting = {
	    	 	data: {
		            simpleData: {
		                enable:true,
		                idKey: "id",
		                pIdKey: "pId",
		                tags: "tags",
		                isParent: "isParent"
		            }
	        	},
	        	async: {  
	                enable: true,  
	                url:$.supper("getServicePath", {"service":"SysWebsiteColumnsService.getSysWebsiteColumnsTree","data":"type=0"}),  //获取异步数据的地址
	                autoParam:["id"],  
	                dataFilter: filter //设置数据的展现形式  
	            },
	            callback: {//增加点击事件
		            beforeClick: function(treeId, treeNode) {
		            	lastExpandNode = treeNode;//记录当前点击的节点
						$("#sysSwcId").val(treeNode.id);
						search();
		            }
		        }
	         }  
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

//当增加树的数据后刷新当前节点
		function loadAddTree(){
			 var zTree = $.fn.zTree.getZTreeObj("tree"); 
			 if(lastExpandNode != null)//刷新当前节点
	            	zTree.reAsyncChildNodes(lastExpandNode, "refresh");  
			 search(); 
		}
		//当修改树的数据后刷新当前节点的父节点
		function loadUpdateTree(){
			 var zTree = $.fn.zTree.getZTreeObj("tree"); 
			 if(lastExpandNode != null)  
		      	{  
		            var zTree = $.fn.zTree.getZTreeObj("tree");  
		            if(lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
		           		zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");  
		        } 
		         search(); 
		}

//--------------------------------tree---------------------------

function search(){
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
    
}

function add(){ 
	var sysSwcId = $("#sysSwcId").val();
	if(sysSwcId == '0')
		sysSwcId = "";
	var data = "sysSwcId="+sysSwcId; 
	var att_url= $.supper("getServicePath", {"service":"SysWebsiteColumnsService.addSysWebsiteColumns", "data":data,url:"/jsp/website/sysWebsiteColumns"});
	var tt_win=$.supper("showWin",{url:att_url,title:"栏目信息",icon:"fa-tint",width:800,height:450,BackE:loadAddTree}); 
}

function toUpdate(swcId){ 
	var data = "swcId="+swcId; 
	var att_url= $.supper("getServicePath", {"service":"SysWebsiteColumnsService.GetOneSysWebsiteColumns", "data":data,url:"/jsp/website/sysWebsiteColumns"});
	var tt_win=$.supper("showWin",{url:att_url,title:"栏目信息",icon:"fa-tint",width:800,height:450,BackE:loadAddTree}); 
}

function toDelete(swcId) {
	var data = "swcId="+swcId; 
	$.supper("confirm",{ title:"删除栏目", msg:"确认删除栏目？", BackE:function(){
		$.supper("doservice", {"service":"SysWebsiteColumnsService.deleteSysWebsiteColumns","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:loadAddTree});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

function formatType(val,item,rowIndex){
	if(val=='1')
		return "普通栏目";
	else if(val=='2')
		return "商品栏目";
	else if(val=='3')
		return "供应商栏目";
	else if(val=='4')
		return "商品类别栏目";
	else if(val=='5')
		return "图片栏目";
	else if(val=='6')
		return "文字栏目";
	return "";
}

function formatState(val,item,rowIndex){
	if(val=='1')
		return "启用";
	else if(val=='2')
		return "禁用";
	return "";
}

function formateComplete(val,item,rowIndex){
	 return "<a onclick=\"toUpdate('"+item.swcId+"')\" class='btn btn-info btn-sm'>修改</a> &nbsp;" +
	 		"<a onclick=\"toDelete('"+item.swcId+"')\" class='btn btn-danger btn-sm'>删除</a>";
}
		 