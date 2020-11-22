<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>基础平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%> 
  </head> 
  <style>
  		#win_edit_toolbar{
  		float: right;
  		}
	</style>
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
	<form id="accountForm" class="form-horizontal form-bordered"
		role="form">
		<div class="panel panel-default" >
			<!-- 隐藏域begin -->
			<div id="win_form_edithidden"></div>
			<!--隐藏区域end -->
			<!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo" id="win_form_body"></div>
			<!-- 正文部分end -->
			<!-- 工具栏部分begin -->
			<div class="panel-footer">
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-4"  id="win_edit_toolbar">
					</div>
				</div>
			</div>
			<!-- 工具栏部分end -->
		</div>
	</form>
	<!-- 表单部分end-->
</body>
   
</html>
	<script src="<c:url value='/javaScript/dentistmall/materiel/editMdMaterielFormat.js?v=13'/>"></script>

<script>
	// 获取当前登录角色的角色
	var _user_type = "${sessionScope.sessionUser.userType}";
	var _user_role = "${sessionScope.sessionUser.userRole}";
</script>