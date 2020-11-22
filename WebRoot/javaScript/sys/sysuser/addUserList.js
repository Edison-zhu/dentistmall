$(function(){   
	var userType = $.supper("getParam", "userType");
	var orgGxId = $.supper("getParam", "orgGxId");
	var organizaType = $.supper("getParam", "organizaType");
	var oldId=$.supper("getParam", "oldId"); 
	
	var applicantName=$.supper("getParam", "applicantName");
	
	$("#userType").val("1");
	$("#orgGxId").val(orgGxId?orgGxId:"");
	$("#organizaType").val(organizaType?organizaType:"");
	$("#oldId").val(oldId?oldId:"");
	
	var data = "userType="+userType+"&orgGxId="+orgGxId+"&organizaType="+organizaType+"&oldId="+oldId; 
	 
	$("#_title").html(applicantName);
	//$("#_title").html(applicantName+"======"+data);
	
	initDataGrid();
});

var mmg ;
var queryAction = "SysUserService.getSysUserPager";
var _dblClick = function (data, row, col) {
	editUser(data.suiId);
}

var initDataGrid = function () {
	var cols = [
		{title: '用户编号', name: 'userCode', width: 110, align: 'center'},
		{title: '用户名称', name: 'userName', width: 80, align: 'center'},
		{title: '用户账号', name: 'account', width: 80, align: 'center'},
		{title: '手机号', name: 'phoneNumber', width: 80, align: 'center'},
		{title: '用户角色', name: 'userRole', width: 100, align: 'center', renderer: fmateRole},
		{title: '状态', name: 'state', width: 50, align: 'center', renderer: fmateState},
		{title: '操作', name: 'control', width: 180, align: 'center', renderer: control}
	];
	var att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
	mmg = $('#datagrid1').mmGrid({
		cols: cols
		, height: 'auto'
		, method: 'get'
		, remoteSort: true
		, multiSelect: true
		, nowrap: true
		, fullWidthRows: true
		, autoLoad: false
		, showBackboard: false
		, checkCol: false
		, url: att_mmgurl
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

function fmateRole(val,item,rowIndex){
	var reStr = "";
	if(val != null){
		if(item.userType=='2'){//供应商
			if(val.indexOf("1") >= 0){
				reStr+="供应商管理员"+"、";
			}
			if(val.indexOf("2") >= 0)
				reStr+="供应商员工"+"、";
		}else{
			if(item.userType=='3'){
				if(val.indexOf("1") >= 0)
					reStr+="集团管理员"+"、";
			}else if(item.userType=='4'){
				if(val.indexOf("1") >= 0)
					reStr+="医院管理员"+"、";
			}else if(item.userType=='4'){
				if(val.indexOf("1") >= 0)
					reStr+="门诊管理员"+"、";
			}
			
			if(val.indexOf("2") >= 0)
				reStr+="采购员"+"、";
			if(val.indexOf("3") >= 0)
				reStr+="仓库管理员"+"、";
			if(val.indexOf("4") >= 0)
				reStr+="领料员"+"、";
		}
		if(reStr != null && reStr.length > 0)
			reStr = reStr.substring(0, reStr.length-1);
	}
	
	return reStr;
}

function fmateState(val,item,rowIndex){
	if(val=='2')
		return "禁用";
	else if(val=='1')
		return "启用";
}

function control(val,item,rowIndex){
	var str = "";
	if(item.state == '1')
		str += "<a onclick=\"updateState('"+item.suiId+"','2')\" class='btn btn-success btn-xs'>禁用</a>&nbsp;&nbsp;";
	else
		str += "<a onclick=\"updateState('"+item.suiId+"','1')\" class='btn btn-info btn-xs'>启用</a>&nbsp;&nbsp;";
	str += "<a onclick=\"editUser('"+item.suiId+"')\" class='btn btn-warning btn-xs'>修改</a>&nbsp;&nbsp;";
	str += "<a onclick=\"delUser('"+item.suiId+"')\" class='btn btn-danger btn-xs'>删除</a>&nbsp;&nbsp;";
	str += "<a onclick=\"editUserPass('"+item.suiId+"')\" class='btn btn-default btn-xs'>初始密码</a>";
   return str;
}

function updateState(suiId,state){
	var vdata = "suiId="+suiId+"&state="+state;
	$.supper("doservice", {"service":"SysUserService.updateSysUserState","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

function editUserPass(suiId){
	$.supper("confirm",{ title:"初始化密码", msg:"确认初始化密码？", yesE:function(){
		var vdata ="suiId="+suiId;
		$.supper("doservice", {"service":"SysUserService.updateSysUserPass","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

function editUser(suiId){
	var userType=$("#userType").val();
	var orgGxId = $("#orgGxId").val();
	var organizaType = $("#organizaType").val();
	var oldId = $("#oldId").val();
	var data = "userType="+userType+"&orgGxId="+orgGxId+"&organizaType="+organizaType+"&oldId="+oldId; 
	if(suiId != null && suiId != ''){
		data += "&suiId="+suiId; 
		var att_url= $.supper("getServicePath", {"service":"SysUserService.findSysUser", "data":data,url:"/jsp/sys/sysuser/editUser"});
		var tt_win=$.supper("showWin",{url:att_url,title:"用户信息",icon:"fa-group",width:800,height:500,BackE:search}); 
	}else{
		var att_url= $.supper("getServicePath", {"service":"SysUserService.findSysUser", "data":data,url:"/jsp/sys/sysuser/editUser"});
		var tt_win=$.supper("showWin",{url:att_url,title:"用户信息",icon:"fa-group",width:800,height:500,BackE:search}); 
	}
}

function delUser(suiId){
	var vdata = "suiId="+suiId;
	$.supper("confirm",{ title:"删除用户", msg:"确认删除用户？", yesE:function(){
		$.supper("doservice", {"service":"SysUserService.delSysUser","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

