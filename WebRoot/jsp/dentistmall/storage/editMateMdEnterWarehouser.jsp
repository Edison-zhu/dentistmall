<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>基础平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%> 
  </head> 
  <!-- 
  	修改按钮样式
   -->
   <style>
  		#win_edit_toolbar{
  		float: right;
  		}
  		#button1{
  			width:95px;
  			height:25px;
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
			<div class="panel-body scrollbarinfo" >
				<div id="win_form_body">
				</div>
				<div class="ibox float-e-margins" style="border:1px solid #e7eaec;margin-top:5px">
	                  <!--标题栏区域 begin-->
	                     <div class="ibox-title" style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:45px;margin-bottom:10px">
	                    	<!-- 标题文字区域begin -->
	                        <h5>&nbsp&nbsp物料明细列表</h5> 
	                        <!-- 标题文字区域end -->
	                        <div class="ibox-tools">
	                        	<button type="button" class="btn btn-success btn-xs"  onclick="selInv();" id="button1"><i class="fa fa-edit"></i>选择</button>
							</div> 
	                    </div>
	                   <div id="gridDiv">
	                    	<table id="datagrid1" class="mmg" ></table>
	                    </div>
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
	<script src="<c:url value='/javaScript/dentistmall/storage/editMateMdEnterWarehouser.js?v=30'/>"></script>