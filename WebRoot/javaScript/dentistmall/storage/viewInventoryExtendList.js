$(function(){   
	laydate({
		elem: '#startDate',
		format: 'YYYY-MM-DD' //日期格式
	});
  laydate({
		elem: '#endDate',
		format: 'YYYY-MM-DD' //日期格式
	});
	var wiId= $.supper("getParam", "wiId");
	$("#wiId").val(wiId);
	initDataGrid();
	search2();
	initTree();
});
var mmg ;
var queryAction = "MdInventoryService.getExtendViewPagerModel";
var initDataGrid =  function(){
	    var cols = [
	                {title:'单号', name:'relatedCode', width:80, align: 'center'},  
			       	{title:'物料名称', name:'matName', width:80, align: 'center',renderer:controlInfo},  
			       	{title:'规格', name:'mmfName', width:80, align: 'center'},  
			       	{title:'供应商', name:'applicantName', width:80, align: 'center'},
			       	{title:'物料类型', name:'typeName', width:120, align: 'center'},
			       	{title:'品牌', name:'brand', width:80, align: 'center'},
			       	{title:'库存数量', name:'baseNumber', width:40, align: 'center'},
			       	{title:'基本单位', name:'basicUnit', width:40, align: 'center'},
			       	{title:'采购人', name:'purchaseUser', width:40, align: 'center'},
			       	{title:'入库时间', name:'createDate', width:80, align: 'center'},
			       	{title:'操作', name:'mieId', width:140, align: 'center',renderer:control}
			       	
			    	
	            ];  
	    var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	          mmg = $('#datagrid1').mmGrid({
	              height:'330px'
	        	  ,cols: cols
	              , method: 'post'
	              , remoteSort:true
	              ,url : att_mmgurl
	              , sortName: 'SECUCODE'
	              , sortStatus: 'asc'
	              , multiSelect: true
	              ,showBackboard:true
	              , checkCol: false
	              , fullWidthRows: true
	              , autoLoad: false
	              , nowrap:true
	              , plugins: [
	    	                  $('#pg').mmPaginator({})
	    	              ]
	          });  
	          mmg.load(); 
}

function search2(){
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
}

function  closeWin(){
	$.supper("closeWin");
}
function control(val,item,rowIndex){
	var str = "";
	if(item.baseNumber<=0)
		str += "<a onclick=\"main_delete('"+item.mieId+"')\"  class='btn btn-danger  btn-xs'>删除</a> "; 
	else
		str += "<a class='btn btn-default  btn-xs' disabled=true>删除</a> "; 
	if(item.matType !=null && item.matType !="")
		str += "<a onclick=\"saveInventoryNew('"+  item.mmfId+"')\"  class='btn btn-success  btn-xs'>缺货</a>&nbsp;&nbsp;";
	else
		str += "<a  class='btn btn-default  btn-xs' disabled=true>缺货</a>&nbsp;&nbsp;";
   return str;
}
function controlInfo(val,item,rowIndex){
	var str = "";
	if(item.matType !=null && item.matType !="")
		str += "<a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+item.wmsMiId+"\" target=\"_blank\">"+item.matName+"</a> ";  
	else
		str = item.matName;
	return str;
}
var setting;
var lastExpandNode;
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
	            url:$.supper("getServicePath", {"service":"MdMaterielTypeService.getTreeListByMdMmtId"}),  //获取异步数据的地址
	            autoParam:["id"],  
	            dataFilter: filter //设置数据的展现形式  
	      },
		  callback: {//增加点击事件
            	beforeClick: function(treeId, treeNode) {
            		if(treeNode.id > 0){
            			$("#matType").val("/"+treeNode.id+"/");
            			search2();
            		}else{
            			$("#matType").val("");
            			search2();
            		}
            		
	            }
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
//设置数据的展现形式 
function filter(treeId, parentNode, childNodes) {  
	if (!childNodes) return null;  
	for (var i=0, l=childNodes.length; i<l; i++) {  
		childNodes[i].name = childNodes[i].name.replace('','');  
	}  
	return childNodes;  
}

function main_delete(id){ 
	var vdata="mieId="+id;
	$.supper("confirm",{ title:"删除操作", msg:"确认删除记录操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdInventoryService.deleteExtendObject","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search2});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}  

function saveInventoryNew(id){ 
	var data="mmfId="+id;
	$.supper("confirm",{ title:"提醒操作", msg:"确认提醒操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdNewsInfoService.saveInventoryNew","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}  