function loginOut(){
	$.supper("doservice", {"service":"LoginService.ReceptionCancellation", "BackE":function (jsondata) {
		var att_url= $.supper("getServicePath", {url:"/jsp/main/login"});
		window.location.href = att_url;
	}});
}

function updatePass(){
	var att_url = "";
	var att_url= $.supper("getServicePath", {url:"/jsp/account/updatePass"});
	var tt_win=$.supper("showWin",{url:att_url,width:600,height:400,icon:"fa fa-user",title:"更改密码"}); 
}

function editUser(suiId){
	var data = "suiId="+suiId; 
	var att_url= $.supper("getServicePath", {"service":"SysUserService.findSysUser", "data":data,url:"/jsp/sysuser/editSysUser"});
	var tt_win=$.supper("showWin",{url:att_url,title:"用户信息",icon:"fa-group",width:800,height:500}); 
}

