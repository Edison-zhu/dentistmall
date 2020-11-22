$(function(){ 
});

function validate(){

	var newsTitle = $("#newsTitle").val();
	var serialNumber = $("#serialNumber").val();
	 
	 if(CheckUtil.isNull(newsTitle)){
		 Messager.info("标题不能为空!");
		 return false;
	 }
/*	 if(CheckUtil.isNull(newscode)){
		 Messager.info("新闻编码不能为空!");
		 return false;
	 }
	 if(CheckUtil.isNull(createRen)){
		 Messager.info("创建人不能为空!");
		 return false;
	 }
	 
	 if(CheckUtil.isNull(newsComment)){
		 Messager.info("新闻内容不能为空!");
		 return false;
	 }
	 //正整数
	 
		 
	 }*/
	 if(serialNumber){
		 
		 if(!CheckUtil.isPlusInteger(serialNumber)){
			 Messager.info("序号必须为正整数!");
			 return false;
		 }
	 }
	return true;
}

function  save(){  
	if(!validate()){
		return ;
	}
	 var att_data=$("#accountForm").serialize();
	 
	 $.supper("doservice", {"service":"SysWebsiteCommentService.saveSysWebsiteComment", "ifloading":1,"options":{"type":"post","data":att_data}, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert", {
					title : "操作提示",
					msg : "操作成功！",
					BackE:closeWin});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}});
}

function  closeWin(){
	$.supper("closeWin"); 
}
 