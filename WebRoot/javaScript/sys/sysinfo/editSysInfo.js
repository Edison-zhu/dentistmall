$(function(){
	
});
function  closeWin(){
	$.supper("closeWin"); 
}

function initUrl(){
	var str = "";
	var systemIp = $("#systemIp").val();
	var systemPort = $("#systemPort").val();
	var projectName = $("#projectName").val();
	if(systemIp != null && systemIp!= "" && systemPort != null && systemPort!= "" && projectName != null && projectName!= ""){
		str += "http://"+systemIp+":"+systemPort+"/"+projectName;
	}
	$("#systemUrl").val(str);
}

   function save(){
   		var sysName = $("#sysName").val();
   		if(sysName == null || sysName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"系统名称不能为空！"});
   			return;
   		}
   		var systemIp = $("#systemIp").val();
   		if(systemIp == null || systemIp == ""){
   			$.supper("alert",{ title:"操作提示", msg:"IP地址不能为空！"});
   			return;
   		}
   		var systemPort = $("#systemPort").val();
   		if(systemPort == null || systemPort == ""){
   			$.supper("alert",{ title:"操作提示", msg:"端口号不能为空！"});
   			return;
   		}
   		var projectName = $("#projectName").val();
   		if(projectName == null || projectName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"系统名称不能为空！"});
   			return;
   		}
   		var systemService = $("#systemService").val();
   		if(systemService == null || systemService == ""){
   			$.supper("alert",{ title:"操作提示", msg:"服务名称不能为空！"});
   			return;
   		}
   		var charset = $("#charset").val();
   		if(charset == null || charset == ""){
   			$.supper("alert",{ title:"操作提示", msg:"字符集不能为空！"});
   			return;
   		}
   		var systemtype = $("#systemtype").val();
   		if(systemtype == null || systemtype == ""){
   			$.supper("alert",{ title:"操作提示", msg:"系统类型不能为空！"});
   			return;
   		}
   		
   		
   		var dbName = $("#dbName").val();
   		if(dbName == null || dbName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"数据库名不能为空！"});
   			return;
   		}
   		
   		
   		var dbuserName = $("#dbuserName").val();
   		if(dbuserName == null || dbuserName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"数据库用户名不能为空！"});
   			return;
   		}
   		
   		
   		var systemtype = $("#systemtype").val();
   		if(systemtype == null || systemtype == ""){
   			$.supper("alert",{ title:"操作提示", msg:"系统类型不能为空！"});
   			return;
   		}
   		
   		var dbType = $("#dbType").val();
   		if(dbType == null || dbType == ""){
   			$.supper("alert",{ title:"操作提示", msg:"数据库类型不能为空！"});
   			return;
   		}
    
   		
   		var systemComment = $("#systemComment").val();
   		if(systemComment != null && systemComment.length > 200){
   			$.supper("alert",{ title:"操作提示", msg:"系统简介不能超过200字！"});
   			return;
   		}
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"SystemInfoService.saveSystemInfo", "ifloading":1,"data":data, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 		}});
   		
   }