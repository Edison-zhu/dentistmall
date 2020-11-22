$(function(){
	
});
function  closeWin(){
	$.supper("closeWin"); 
}
   function save(){
   		var menuName = $("#menuName").val();
   		if(menuName == null || menuName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"岗位名称不能为空！"});
   			return;
   		}
   		var menuComment = $("#menuComment").val();
   		if(menuComment != null && menuComment.length > 200){
   			$.supper("alert",{ title:"操作提示", msg:"岗位说明不能超过200字！"});
   			return;
   		}
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"SysRoleService.saveSysRole", "ifloading":1,"options":{"type":"post","data":data}, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 		}});
   		
   }