<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>青浦GIS系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/sys/sysuser/selMenu.js'/>"></script>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
				<div class="form-group" styel="">
					<div class="controls col-sm-4">
						<ul id="tree" class="ztree" style="overflow:auto;"></ul>
					</div>
					<div class="controls col-sm-8">
						<table id="datagrid1" class="mmg" ></table>
					   	<div id="pg" style="text-align: right;"></div>
					</div>
				</div>
			</div> 
		 <!-- 正文部分end -->
		 <!-- 工具栏部分begin -->
				<div class="panel-footer">
		           <div class="form-group">
			           <div class="col-sm-offset-2 col-sm-4">  
                           <button type="button"  onclick="saveMenu()" class="btn btn-primary">保存菜单</button>
                           <button type="button"  onclick="saveControl()" class="btn btn-success">保存控件</button>
			               <button type="button"  onclick="closeWin()" class="btn btn-default">关闭</button> 
			           </div>
			      </div>
       		</div>
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
  <input type="hidden" id="smenuId" value="">
 <!-- 表单部分end-->
</body>
</html>