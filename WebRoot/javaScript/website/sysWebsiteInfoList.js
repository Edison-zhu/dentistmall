var queryAction = "SysWebsiteInfoService.GetPMSysWebsiteInfo";
var mmg ;
var _dblClick = function(data, row, col){
	toUpdate(data.swiId);
}
$(function () {
	var cols = [
		{title: '网站编码', name: 'websitecode', width: 100, align: 'center'},
		{title: '网站名称', name: 'websitName', width: 100, align: 'center'},
		{title: '根目录地址', name: 'rootPath', width: 100, align: 'center'},
		{title: '首页地址', name: 'indexPath', width: 140, align: 'center'},
		{title: '模板目录地址', name: 'templateDirectory', width: 100, align: 'center'},
		{title: '图标', name: 'websitIconFilecode', width: 20, align: 'center', renderer: formatIcon},
		{title: '状态', name: 'state', width: 20, align: 'center', renderer: formatState},
		{title: '操作', name: 'mwiId', width: 70, align: 'center', renderer: formateComplete}
	];
	//更改传值方式 查询用form表单查询,若websit_name有值,则进行查询,模糊查询，若没值，全部查询
	var att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
	mmg = $('#datagrid1').mmGrid({
		height: "auto"
		, nowrap: true
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
});


function search(){  
	var url= rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = url;
    mmg.load(); 
   
}

function add(){ 
	var att_url= $.supper("getServicePath", {url:"/jsp/website/sysWebsiteInfo"});
	var tt_win=$.supper("showWin",{url:att_url,title:"网站信息",icon:"fa-ra",width:800,height:450,BackE:search}); 
}
function formatIcon(val,item,rowIndex){
	var websitIconFilecode  = item["websitIconFilecode"];
	var resultText = "";
	
	if(websitIconFilecode== null || websitIconFilecode==""){
		resultText = "无";
	}
	else{
		resultText = "有";
	}
   return resultText;
}

function formatState(val,item,rowIndex){
	var state  = item["state"];
	var resultText = "";
	if(state == null || state == ""){
		resultText = "启用";
	}
	else if(state == 0){
		resultText = "禁用";
	}else if(state == 1){
		resultText = "启用";
	}else{
		resultText = "启用";
	}
   return resultText;
}

function formateComplete(val,item,rowIndex){
	 return "<a onclick=\"toUpdate('"+item.swiId+"')\" class='btn btn-info btn-sm'>修改</a> &nbsp;" +
	 		"<a onclick=\"toDelete('"+item.swiId+"')\" class='btn btn-danger btn-sm'>删除</a>";
}

function toUpdate(swiId){ 
	var data = "swiId="+swiId; 
	var att_url= $.supper("getServicePath", {"service":"SysWebsiteInfoService.GetOneSysWebsiteInfo", "data":data,url:"/jsp/website/sysWebsiteInfo"});
	var tt_win=$.supper("showWin",{url:att_url,title:"网站信息",icon:"fa-ra",width:800,height:450,BackE:search}); 
}

function toDelete(swiId) {
	var data = "swiId="+swiId; 
	$.supper("confirm",{ title:"删除站点", msg:"确认删除站点？", BackE:function(){
		$.supper("doservice", {"service":"SysWebsiteInfoService.deleteSysWebsiteInfo","data":data, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
	
}
		 