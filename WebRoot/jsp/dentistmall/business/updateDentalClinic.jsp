<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>牙医商城平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%> 
 	<script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
  </head> 
   <style>
  		#win_edit_toolbar{
  		float: right;
  		}
	</style> 
<body   class="gray-bg">
		<form method="get" id="accountForm" class="form-horizontal">
		 <div class="wrapper wrapper-content animated fadeInRight"> 
		        <div class="row">
		            <div class="col-sm-12">
		                <div class="ibox float-e-margins">
		                    <!-- 标题部分begin -->
		                    <div class="ibox-title">
		                        <h5 id="_title">门诊信息注册</h5> 
		                    </div>
		                    <!-- 标题部分end -->
		                    <!-- 内容部分begin -->
		                    <div class="ibox-content">    
		                        	<!-- 隐藏域begin -->
									<div id="win_form_edithidden"></div>
									<!--隐藏区域end -->
		                             <!-- 正文部分begin -->
									<div class="panel-body" >
										<div id="win_form_body"></div>						  
									</div>
									<!-- 正文部分end --> 
		                    </div>
		                     <!-- 内容部分end -->
		                    <!-- 工具栏部分begin -->
							<div class="panel-footer">
								<div class="form-group">
				                      <div class="col-sm-4 col-sm-offset-2" id="win_edit_toolbar"></div>
				                </div>
							</div>
							<!-- 工具栏部分end --> 
		                </div>
		            </div>
		        </div>
		 </div>
		</form>
	</body>  
</html>
<script>
	var _user_org_type = "${sessionScope.sessionUser.organizaType}";
	var _user_type = "${sessionScope.sessionUser.userType}";
</script>
<script src="<c:url value='/javaScript/dentistmall/business/updateDentalClinic.js?v=34'/>"></script>