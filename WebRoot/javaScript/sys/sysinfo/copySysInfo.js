$(function(){
	
});
function  closeWin(){
	$.supper("closeWin"); 
}
 
function save(){
   		var sysName = $("#sysName").val();
   		if(sysName == null || sysName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"系统名称不能为空！"});
   			return;
   		}
   		 
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"SystemInfoService.copySystemInfo", "ifloading":1,"data":data, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 		}});
   		
 }