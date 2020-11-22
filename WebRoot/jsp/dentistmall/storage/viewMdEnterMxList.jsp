<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head> 
   <title>采购中心系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
   <%@ include file="/jsp/link.jsp"%> 
  </head>
  <style>
  		#button1{
  			position:absolute;
  			left:875px;
  			width:95px;
  			height:21px;
  			vertical-align:middle;
  			align-items:center;
  		}
  		#button2{
  		width:95px;
  		}
  </style>
<body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="queryForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		 <!-- 正文部分begin -->
			<div class="panel-body">
				<!--条件输入区域begin-->
				<div class="controls col-sm-12">
					<input type="hidden" name="wiId" id="wiId" value="">
					<div class="form-inline form-group">
						<div class="controls col-sm-5" style="font-size:15px">
							采购人:
							<input type="text" class="form-control" placeholder="采购人" name="consignee" value="" style="width:120px">
						</div>
						<div class="controls col-sm-5" style="font-size:15px">
							入库人:
							<input type="text" class="form-control" placeholder="入库人" name="consignor" value="" style="width:120px">
						</div>
						<div class="controls col-sm-2 text-right">
							<a class="btn btn-success btn-xs" onclick="search2();" id="button2">查询</a>
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
			               <button type="button"  onclick="closeWin()" class="btn btn-default btn-xs" id="button1">关闭</button> 
			           </div>
			      </div>
       		</div>
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
 <!-- 表单部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/viewMdEnterMxList.js?v=12'/>"></script>