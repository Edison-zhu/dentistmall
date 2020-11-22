<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>表对象维护</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
	
  </head> 
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<i class="fa fa-group"></i>表对象维护
						</h5>
						<div class="ibox-tools">
									<button type="button" class="btn btn-success btn-xs adminQX"  onclick="addParam(1);">
													<i class="fa fa-plus"></i>添加表对象
									 </button>
						</div>
					</div>
					<div class="ibox-content">
	 				 表对象列表
					</div>
				</div>
			</div>

		</div>
</body>
</html>
<script src="<c:url value='/javaScript/sys/sysCodeAutomation/tableSet/tableSetList.js'/>"></script>
 