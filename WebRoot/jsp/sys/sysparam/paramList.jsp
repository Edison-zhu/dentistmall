<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>青浦GIS系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
	<script src="<c:url value='/javaScript/sys/sysparam/paramList.js?05' />"></script>
  </head> 
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins" id="oneCode">
					<div class="ibox-title">
						<h5>
							<i class="fa fa-codepen"></i>编码管理 
						</h5>
						<div class="ibox-tools">
							<button type="button" class="btn btn-success btn-xs adminQX"  onclick="addOneParam();">
								<i class="fa fa-plus"></i>添加编码
							</button>
							<button type="button" class="btn btn-warning btn-xs adminQX"  onclick="editOneParam();">
								<i class="fa fa-edit"></i>修改编码
							</button>
							<button type="button" class="btn btn-danger btn-xs adminQX"  onclick="delOneParam();">
								<i class="fa fa-minus"></i>删除编码
							</button>
						</div>
					</div>
					<div class="ibox-content">
						<div class="scrollbarlist">
		 					<div  id="pobjT">
		 					</div>
	 					</div>
					</div>
				</div>
				
				<div class="ibox float-e-margins" id="childCode" style="display:none">
					<div class="ibox-title">
						<h5>
							<i class="fa fa-codepen"></i>(<lable id="oneName"></lable>)子编码管理
						</h5>
						<div class="ibox-tools">
							<button type="button" class="btn btn-success btn-xs adminQX"  onclick="editParam();">
								<i class="fa fa-plus"></i>添加编码
							</button>
							<button type="button" class="btn btn-default btn-xs adminQX"  onclick="backOne();">
								<i class="fa fa-reply"></i>返回
							</button>
							
						</div>
					</div>
					<div class="ibox-content">
						<div role="grid" class="dataTables_wrapper form-inline"
							id="DataTables_Table_0_wrapper"> 
							<!--表格区域begin--> 
							<table id="datagrid1" class="mmg" ></table>
							<div id="pg" style="text-align: right;"></div> 
							<!--表格区域end-->
						</div>
					</div>
				</div>
			</div>

		</div>
</body>
</html>
 