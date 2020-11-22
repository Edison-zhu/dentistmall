 $(function(){   
	initDataGrid();
  
});

var mmg ;
var queryAction = "SystemInfoService.getSystemInfoPager";
 var _dblClick = function (data, row, col) {
	editSys(data.ssiId);
 }
var initDataGrid = function () {
	var cols = [
		{title: '系统名称', name: 'sysName', width: 100, align: 'center'},
		{title: '系统编号', name: 'sysCode', width: 100, align: 'center'},
		{title: '系统类型', name: 'systemtype', width: 100, align: 'center', renderer: controltype},
		{title: '创建时间', name: 'createTime', width: 80, align: 'center'},
		{title: '创建人', name: 'createRen', width: 50, align: 'center'},
		{title: '操作', name: 'sysCode', width: 100, align: 'center', renderer: control}
	];

	var att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
	mmg = $('#datagrid1').mmGrid({
		height: $(window).height() - 190
		, cols: cols
		, method: 'get'
		, remoteSort: true
		, url: att_mmgurl
		, sortName: 'SECUCODE'
		, sortStatus: 'asc'
		, multiSelect: true
		, checkCol: false
		, fullWidthRows: true
		, autoLoad: false
		, dblClickFunc: _dblClick
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
	mmg.load();
}

function search(){
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
}
 function controltype(val,item,rowIndex){
	 if(val ==1){
		return "web服务器";
	 }
	 if(val ==2){
		 return "主后台服务";
		 }
	 if(val ==3){
		 return "业务服务器";
	 }
	 if(val ==4){
		 return "文件服务器";
	 }
	 if(val ==5){
		 return "业务样板系统";
	 }
	 
 }
function control(val,item,rowIndex){
	var str = "";
	str += "<a onclick=\"editSys('"+item.ssiId+"')\" class='btn btn-warning'>修改</a>&nbsp;&nbsp;";
	if(val !="SYS000000000000001"){
		str += " <a onclick=\"delSys('"+item.ssiId+"')\" class='btn btn-danger'>删除</a> ";
	}
   return str;
}

 

function editSys(ssiId){
	if(ssiId != null && ssiId != ''){
		var data = "ssiId="+ssiId; 
		var att_url= $.supper("getServicePath", {"service":"SystemInfoService.findSystemInfo", "data":data,url:"/jsp/sysinfo/editSysInfo"});
		var tt_win=$.supper("showWin",{url:att_url,icon:"fa fa-flag",title:"系统信息",width:800,height:600,BackE:search}); 
	}else{
		var att_url= $.supper("getServicePath", {"service":"SystemInfoService.findSystemInfo",url:"/jsp/sysinfo/editSysInfo"});
		var tt_win=$.supper("showWin",{url:att_url,icon:"fa fa-flag",title:"系统信息",width:800,height:600,BackE:search}); 
	}
}

function delSys(ssiId){
	var vdata = "ssiId="+ssiId;
	$.supper("confirm",{ title:"删除系统", msg:"确认删除系统？" ,yesE:function(){
		 
		$.supper("doservice", {"service":"SystemInfoService.delSystemInfo","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}
