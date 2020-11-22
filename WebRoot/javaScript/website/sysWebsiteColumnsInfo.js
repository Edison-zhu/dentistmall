$(function(){  
$.supper('initUpFile',{id:"uploadButton",codeId:"columnIconFileCode"}); 

$.supper('initUpFile',{id:"lm_uploadButton01",codeId:"columnPictureFilecode01"}); 
$.supper('initUpFile',{id:"lm_uploadButton02",codeId:"columnPictureFilecode02"}); 
$.supper('initUpFile',{id:"lm_uploadButton03",codeId:"columnPictureFilecode03"}); 

});
function  closeWin(){
   $.supper("closeWin");
} 

function validate(){

	var columnName = $("#columnName").val(); 
	var columnscode = $("#columnscode").val(); 
	var state = $("#state").val();
	var serialNumber = $("#serialNumber").val();
	
	if(columnName == null || columnName ==""){
		$.supper("alert",{ title:"操作提示", msg:"栏目名称不能为空！"});
		return false;
	}
	
	 //正整数
	if(serialNumber ==null || serialNumber==""){
		$.supper("alert",{ title:"操作提示", msg:"请输入栏目顺序！"});
		return false;
	 }
	 if(serialNumber){
		 if(!CheckUtil.isPlusInteger(serialNumber)){
			 $.supper("alert",{ title:"操作提示", msg:"显示顺序必须为正整数！"});
			 return false;
		 }
		 
	 }
	 if(state == null || state ==""){
			$.supper("alert",{ title:"操作提示", msg:"请选择状态！"});
			return false;
		}
	 
	return true;
}

function  save(){  
	if(!validate()){
		return ;
	}
	var data =$('#accountForm').serialize();
	$.supper("doservice", {"service":"SysWebsiteColumnsService.saveSysWebsiteColumns", "ifloading":1,"options":{"type":"post","data":data}, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:closeWin});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	}});
}