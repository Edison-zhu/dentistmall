$(function(){
	
	
	var userType=$("#userType").val();
	var orgGxId = $("#orgGxId").val();
	var organizaType = $("#organizaType").val();
	var oldId = $("#oldId").val();
	//var data = "userType="+userType+"&orgGxId="+orgGxId+"&organizaType="+organizaType+"&oldId="+oldId; 
	//$("#data_tt").html(data);
});

function closeWin(){
  $.supper("closeWin");
}
var checkA=0;
function checkAccount(){
		var account = $("#account").val();
		if(account != null && account != ""){
			var data ="account="+account;
			var suiId = $("#suiId").val();
			if(suiId != null && suiId!=""){
				data += "&suiId="+suiId;
			}
			$.supper("doservice", {"service":"SysUserService.checkSysUser", "data":data, "BackE":function (jsondata) {
			if (jsondata.code == "2") {
				$("#accountCheck").show();
				checkA=1;
			}else{
					$("#accountCheck").hide();
					checkA=0;
				}
			}});
		}else{
			checkA=0;
			$("#accountCheck").hide();
			
		}
}
   function save(){
	   var account = $("#account").val();
  		if(account == null || account == ""){
  			$.supper("alert",{ title:"操作提示", msg:"用户账号不能为空！"});
  			return;
  		}
  		if(checkA ==1){
   			$.supper("alert",{ title:"操作提示", msg:"用户名称已经存在！"});
   			return;
   		}
   		var userName = $("#userName").val();
   		if(userName == null || userName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"用户名称不能为空！"});
   			return;
   		}
   		var userRoles=""; 
   		$('input[name="userRoles"]:checked').each(function(){ 
   			userRoles+=$(this).val()+","; 
   		});
   		if(userRoles==null || userRoles==""){
   			$.supper("alert",{ title:"操作提示", msg:"请选择用户角色！"});
   			return;
   		}
   		userRoles = userRoles.substring(0,userRoles.length -1);
   		$("#userRole").val(userRoles);
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"SysUserService.saveSysUser", "ifloading":1,"options":{"type":"post","data":data}, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 		}});
   		
   }