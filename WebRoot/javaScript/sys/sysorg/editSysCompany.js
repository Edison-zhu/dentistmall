function closeWin(){
  $.supper("closeWin");
}

function save(){
	var applicantName = $("#applicantName").val();
  	if(applicantName == null || applicantName == ""){
  		$.supper("alert",{ title:"操作提示", msg:"名称不能为空！"});
  		return;
  	}
   	var data =$('#accountForm').serialize();
   	$.supper("doservice", {"service":"SysOrgService.saveSysCompany", "ifloading":1,"options":{"type":"post","data":data},"BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}