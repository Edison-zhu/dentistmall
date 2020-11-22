$(function(){
	
});
function  closeWin(){
	$.supper("closeWin"); 
}

  

   function save(){
   		var fieldName = $("#fieldName").val();
   		if(fieldName == null || fieldName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"字段名称不能为空！"});
   			return;
   		}
   		
   		var fieldNode = $("#fieldNode").val();
   		if(fieldNode == null || fieldName == ""){
   			$.supper("alert",{ title:"操作提示", msg:"字段说明不能为空！"});
   			return;
   		}
   		
   		var dataType = $("#dataType").val();
   		if(dataType == null || dataType == ""){
   			$.supper("alert",{ title:"操作提示", msg:"数据类型不能为空！"});
   			return;
   		}
   		
   		var ifNull = $("#ifNull").val();
   		if(ifNull == null || ifNull == ""){
   			$.supper("alert",{ title:"操作提示", msg:"是否为空不能为空！"});
   			return;
   		}
   		
 		var ifPk = $("#ifPk").val();
   		if(ifPk == null || ifPk == ""){
   			$.supper("alert",{ title:"操作提示", msg:"是否主键不能为空！"});
   			return;
   		}
   		var fieldLength=$("#fieldLength").val();
   		if(dataType="string"&&(fieldLength == null || fieldLength == "")){
   			$.supper("alert",{ title:"操作提示", msg:"整数位不能为空！"});
   			return;
   		}
   		 
   		
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"CodeAutoService.saveOrUpdateClassColumns", "ifloading":1,"data":data, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
				 
					$.supper("setProductArray", {"name":"win_pojo_ClassColumns", "value":jsondata.obj});
					$.supper("alert",{ title:"操作提示", msg:"操作成功！"});
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 		}});
   		
   }