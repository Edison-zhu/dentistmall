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
		<div class="col-sm-3">
			<div class="ibox float-e-margins">
				<!-- 标题栏区域 begin-->
				<div class="ibox-title">
					<!-- 标题文字区域begin -->
					<h5>
						<i class="fa fa-th"></i>&nbsp&nbsp商品分类
					</h5>
					<!-- 标题文字区域end -->
				</div>
				<!-- 标题栏区域 end-->
				<!-- 操作区域 begin-->
				<div class="ibox-content">
					<div class="scrollbarlist">
						<div class="panel-body">
							<ul id="tree" class="ztree" style="overflow:auto;"></ul>
						</div>
					</div>
				</div>
				<!-- 操作区域 end-->
			</div>
		</div>
		<!-- 左边区域end -->
		<!-- 右边区域begin  -->
		<div class="col-sm-9">
			 
				<div class="row">
					<div class="col-sm-12">
						<!-- 标题栏区域 begin-->
						<div class="ibox-title">
							<!-- 标题文字区域begin -->
							<h5>
								<i class="fa fa-th-list"></i>&nbsp&nbsp商品分类列表
							</h5>
							<div class="ibox-tools" id="win_tools_but"   >
							</div>
						</div>
						<!-- 标题栏区域 end-->
						
						<!-- 查询区域begin -->
						<div class="ibox-content" style="padding: 5px 10px 10px;">
							<form class="form-horizontal" id="win_form_search"> 
							<div  id="win_form_hidden"></div>
							</form>
						</div>
						<!-- 查询区域end -->

						<div class="hr-line-dashed" style="margin:2px 0px;"></div>
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
<script src="<c:url value='/javaScript/dentistmall/materiel/mdMaterielTypeList.js?v=1007'/>"></script>
 
 