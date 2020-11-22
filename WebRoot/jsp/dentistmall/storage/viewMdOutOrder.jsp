<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>基础平台系统</title> 
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
			<div class="panel-body scrollbarinfo" >
				<div id="win_form_body">
				</div>
				<div class="ibox float-e-margins" style="border:1px solid #e7eaec;margin-top:5px">
	                  <!--标题栏区域 begin-->
	                     <div class="ibox-title" style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:38px;margin-bottom:10px">
	                    	<!-- 标题文字区域begin -->
	                        <h5>&nbsp&nbsp申领明细列表</h5> 
	                        <!-- 标题文字区域end -->
	                        <div class="ibox-tools">
							</div> 
	                    </div>
	                    <!-- !--表格区域begin -->
					    <table id="datagrid1" class="mmg" ></table>
						<!--!--表格区域end-->
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
	<script src="<c:url value='/javaScript/dentistmall/storage/viewMdOutOrder.js?v=18'/>"></script>