$(function(){
	 
});
function  closeWin(){
	$.supper("closeWin");
}

function initForm(){
 
} 

function save(){
   		var parkName = $("#parkName").val();
   		if(parkName == null || parkName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"园区名称不能为空！"});
   			return;
   		}
   		var accountCode = $("#accountCode").val();
   		if(accountCode == null || accountCode == ""){
   			$.supper("alert",{ title:"操作提示", msg:"管理员账号不能为空！"});
   			return;
   		}
   		var passWord = $("#passWord").val();
   		if(passWord == null || passWord == ""){
   			$.supper("alert",{ title:"操作提示", msg:"管理员密码不能为空！"});
   			return;
   		}
   		 
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"OpenParkInfoService.saveParkInfo", "ifloading":1,"data":data, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 		}});
   		
   }