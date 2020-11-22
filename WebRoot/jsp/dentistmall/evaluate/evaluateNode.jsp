<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>牙医商城平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>  
 	 
  </head> 
	<body style="overflow:hidden; ">
	<!-- 表单部分begin -->
		<form id="accountForm" class="form-horizontal form-bordered"
			role="form">
			<div class="panel panel-default" >
				<!-- 隐藏域begin -->
				<div id="win_form_edithidden"></div>
				<!--隐藏区域end -->
				<!-- 正文部分begin -->
				<div class="panel-body scrollbarinfo"  id="win_form_body"> 
					<div   style="padding-left: 15px;padding-right: 15px;">
                          <b>点评内容:</b>
                         
                    </div>
                    <div   style="padding-left: 15px;padding-right: 15px;">
                          
                          <textarea id="reviewContent"  name="reviewContent" style="width:460px;height:180px;" > </textarea>
                    </div>
				</div>
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

 <script src="<c:url value='/javaScript/dentistmall/evaluate/EvaluateNode.js?v=19'/>"></script>