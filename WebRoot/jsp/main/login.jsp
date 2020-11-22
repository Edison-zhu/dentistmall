<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
<!DOCTYPE HTML> 
<html>
	<head>

		<title>优牙库</title>
		<meta charset="utf-8">
		<%@ include file="/jsp/lib.jsp"%>
		<%@ include file="/jsp/link.jsp"%>
	</head>
	<style>
		.beian:link{
			text-decoration: underline ;
		}
		.beian:hover{
			color: white;
			text-underline: underline ;
		}
	</style>
<body style="background:url(<c:url value='/img/main_login_bg.jpg' />)">
	<div style="position: absolute;left: 36%;top: 10%;">
		<div style="width:380px;height:568px;margin:0 auto;background:url(<c:url value='/img/main_login_div.png' />);">
			<form id="loginForm">
				<div>
					<input type="text" class="ipt" style="margin-top:268px;margin-left:65px;height:41px;width:240px;border:0px;background:#e5e5e5;padding:10px" placeholder="用户名" name="userName" id="userName" onkeydown="if(event.keyCode==13){loginS();}"/>
				</div>
				<div>
					<input type="password" class="ipt" style="margin-top:15px;margin-left:65px;height:41px;width:240px;border:0px;background:#e5e5e5;padding:10px" placeholder="密码" name="password" id="password" onkeydown="if(event.keyCode==13){loginS();}">
				</div>
				<div>
					<a style="margin-top:53px;margin-left:65px;width:240px;color:#fff" href="javascript:loginS();"class="btn btn-block btn-lg">登录</a>
				</div>
				<div id="errorMsg" style="margin-left:65px;color:red">
				</div>
			</form>
			
		</div>
		<div style="width:250px;margin:0 auto;color:#00b0d7;font-weight:bold">
			<a class="beian" href="http://www.beian.miit.gov.cn" target="_blank">版权所有：优牙库网络科技(苏州)有限公司    备案号：苏ICP备18035594号</a>
<%--			优牙库网络科技（苏州）有限公司<br/>--%>
<%--				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--			<a class="beian" href="http://www.beian.miit.gov.cn" target="_blank">©苏ICP备18035594号-1</a>--%>
		</div>
	</div>
</body>

</html>
<script> 
function  loginS(){ 
		var userName=$("#userName").val();
		var password = $("#password").val();
		if(userName ==null || userName == ""){
			$("#errorMsg").text("请输入用户名！");
			//$("#errorMsg").css("display","block");
			return;
		}
		if(password==null || password==""){
			$("#errorMsg").text("请输入密码！");
			//$("#errorMsg").css("display","block");
			return;
		}
		var data =$('#loginForm').serialize();
		$.supper("doservice", {"service":"SysLoginService.sysLogin", "options":{"data":data,"type":"post"},"dotry":"true", "BackE":function (jsondata) {
					if(jsondata.code == "1") {
						window.location.href = $.supper("getServicePath", {url:"main/main"});
					}else{
						$("#errorMsg").text(jsondata.meg);
					}
	 		}});
     	
}

</script>
