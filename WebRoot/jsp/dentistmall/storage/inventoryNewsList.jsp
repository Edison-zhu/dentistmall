<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
  <head> 
   <title>采购中心系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
   <%@ include file="/jsp/link.jsp"%> 
   
   
  </head>
<body class="gray-bg">
	<!-- 主信息区域begin -->
	<div class="row  wrapper wrapper-content ">
		<!-- 左边区域begin -->
		<!-- 左边区域end -->
		<!-- 右边区域begin  -->
		<div class="col-sm-12">
			 
				<div class="row">
					<div class="col-sm-12">
						<!-- 标题栏区域 begin-->
						<div class="ibox-title">
							<!-- 标题文字区域begin -->
							<h5>
								<i class="fa fa-bell-o"></i>&nbsp&nbsp<span>缺货提醒</span> 
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
				</div>
			 
		</div>
		<!-- 右边区域end -->
	</div>
	<!-- 主信息区域end -->
</body>
</html>
<script   src="<c:url value='/js/cookie.js' />"></script>
<script src="<c:url value='/javaScript/dentistmall/storage/inventoryNewsList.js?v=47'/>"></script>
 
 