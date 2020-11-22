<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
   <title>优牙库</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 <%@ include file="/jsp/link.jsp"%>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<!--隐藏区域end -->
		
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group" styel="">
			   		<label class="control-label col-sm-2">旧密码</label>
                    <div class="controls col-sm-3">
                      <input type="password" class="form-control" name= "oldPwd" id="oldPass" value="" placeholder="旧密码">
                    </div>
                </div>
               <div class="form-group" styel="">
			   		<label class="control-label col-sm-2">新密码</label>
                    <div class="controls col-sm-3">
                      <input type="password" class="form-control" name= "newPwd" id="newPass" onkeyup = "checkPass()" value="" placeholder="新密码">
                      <span style="color:red;display:none" id="passCheck" >*两次密码输入不一致</span>
                    </div>
                </div>
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2">密码确认</label>
                    <div class="controls col-sm-3">
                      <input type="password" class="form-control"  id="newPass2" onkeyup = "checkPass()" value="" placeholder="密码确认">
                    </div>
                </div>
			</div> 
		 <!-- 正文部分end -->
		 <!-- 工具栏部分begin -->
				<div class="panel-footer">
		           <div class="form-group">
			           <div class="col-sm-offset-5 col-sm-10"> 
                                <button type="button"  onclick="save()" class="btn btn-primary">保存</button>
			           </div>
			      </div>
       		</div>
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
 <!-- 表单部分end-->
</body>
   
</html>
<script>
$(function(){
 	
});
function  closeWin(){
  	 $.supper("closeWin");
}
function checkPass(){
		var pass1 = $("#newPass").val();
		var pass2 = $("#newPass2").val();
		
		if(pass1 != pass2)
			$("#passCheck").show();
		else
			$("#passCheck").hide();
		
			
}
   function save(){
   		
   		if($("#oldPass").val() == null || $("#oldPass").val()== ""){
   			$.supper("alert",{ title:"操作提示", msg:"旧密码不能为空！"});
   			return;
   		}
   		if($("#newPass").val() == null || $("#newPass").val()== ""){
   			$.supper("alert",{ title:"操作提示", msg:"新密码不能为空！"});
   			return;
   		}
   		if($("#newPass").val().length < 6){
   			$.supper("alert",{ title:"操作提示", msg:"新密码长度不能小于6位！"});
   			return;
   		}
   		if($("#newPass2").val() == null || $("#newPass2").val()== ""){
   			$.supper("alert",{ title:"操作提示", msg:"密码确认不能为空！"});
   			return;
   		}
   		var pass1 = $("#newPass").val();
		var pass2 = $("#newPass2").val();
		if(pass1 != pass2){
			$.supper("alert",{ title:"操作提示", msg:"两次密码输入 不一致！"});
   			return;
		}
   		
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"SysUserService.updateLoginUserPass","data":data, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:function(){
						window.parent.loginOut();
					}});
				}else
					$.supper("alert",{ title:"操作提示", msg:jsondata.meg});
 		}});
   		
   }
</script>