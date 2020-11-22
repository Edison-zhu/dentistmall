<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head> 
   <title>采购中心系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
   <%@ include file="/jsp/link.jsp"%> 
  </head>
<body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="queryForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		 <!-- 正文部分begin -->
			<div class="panel-body">
				<div class="controls col-sm-3">
						<div class="scrollbarinfo3">
							<ul id="tree" class="ztree" style="overflow:auto;"></ul>
						</div>
				</div>
				<!--条件输入区域begin-->
				<div class="controls col-sm-9">
					<input type="hidden" name="matType" id="matType" value="">
					<input type="hidden" name="wiId" id="wiId" value="">
					<div class="form-inline form-group">
						<div class="controls col-sm-4" style="font-size:15px">
							单号:
							<input type="text" class="form-control" placeholder="单号" name="relatedCode" value="" style="width:120px">
						</div>
						<div class="controls col-sm-4" style="font-size:15px">
							开始时间:
							<input type="text" class="form-control" placeholder="入库时间" id="startDate" name="startDate" readOnly value="" style="width:120px">
						</div>
						<div class="controls col-sm-4" style="font-size:15px">
							结束时间:
							<input type="text" class="form-control" placeholder="入库时间" id="endDate" name="endDate" readOnly value="" style="width:120px">
						</div>
					</div>
					<div class="form-inline form-group">
						<div class="controls col-sm-4" style="font-size:15px">
							品牌:
							<input type="text" class="form-control" placeholder="品牌" id="brand" name="brand" value="" style="width:120px">
						</div>
						<div class="controls col-sm-4" style="font-size:15px">
							物料类型:
							<input type="text" class="form-control" placeholder="物料类型" id="typeName" name="typeName" value="" style="width:120px">
						</div>
						
						<div class="controls col-sm-1 text-right">
							<a class="btn btn-success btn-xs" onclick="search2();">查询</a>
						</div>
					</div>
					<table id="datagrid1" class="mmg" ></table>
					<div id="pg" style="text-align: right;"></div> 
				</div>
			</div> 
		 <!-- 正文部分end -->
		 <!-- 工具栏部分begin -->
				<div class="panel-footer">
		           <div class="form-group">
			           <div class="col-sm-offset-2 col-sm-4">  
			               <button type="button"  onclick="closeWin()" class="btn btn-default">关闭</button> 
			           </div>
			      </div>
       		</div>
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
 <!-- 表单部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/viewInventoryExtendList.js?v=25'/>"></script>