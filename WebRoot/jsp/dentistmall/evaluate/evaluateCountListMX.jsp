<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
  <head> 
   <title>牙医商城平台</title> 
   <%@ include file="/jsp/lib.jsp"%> 
   <%@ include file="/jsp/link.jsp"%> 
   
   
  </head>
<body class="gray-bg">
	<!-- 主信息区域begin -->
	<div class="wrapper wrapper-content animated fadeInRight ">
		    <!-- 标题栏区域 begin-->
						<div class="ibox-title">
							<!-- 标题文字区域begin -->
							<h5>
								<i class="fa fa-flask"></i>&nbsp&nbsp<span>评价统计信息</span> 
							</h5>
							<!-- 标题文字区域end --> 
							<div class="ibox-tools" id="win_tools_but"   > 
							</div>
						</div>
						<!-- 标题栏区域 end-->
						
						<!-- 查询区域begin -->
						<div class="ibox-content" style="padding: 5px 10px 10px;">
							<form class="form-horizontal" id="win_form_search"> 
							<div  id="win_form_hidden" ></div>
							</form>
						</div>
						<!-- 查询区域end -->
 
						<!-- 表格区域begin -->
						<div class="ibox-content" style="padding: 5px 10px 10px;">
							<table id="win_datagrid_main" class="mmg"></table>
							<div id="_all_win_datagrid_pg" style="text-align: right;"></div> 
						</div>
						<!-- 表格区域end -->
		 
	</div>
	<!-- 主信息区域end -->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/evaluate/evaluateCountList.js?v=18'/>"></script>
 
 