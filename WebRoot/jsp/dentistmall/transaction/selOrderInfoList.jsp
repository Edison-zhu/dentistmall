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
  			left:440px;
  			width:95px;
  			height:21px;
  			vertical-align:middle;
  			align-items:center;
  		}
  #button2{
  			position:relative;
  			left:540px;
  			width:95px;
  			height:21px;
  			vertical-align:middle;
  			align-items:center;
  		}
  		#button3{
  			width:95px;
  		}
  </style>
<body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="queryForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
				<!--条件输入区域begin-->
				<input type="hidden" name="state" value="1">
				<div class="form-inline form-group">
					<div class="controls col-sm-3" style="font-size:15px">
						订单编号:
						<input type="text" class="form-control" placeholder="订单编号" name="orderCode" value="" style="width:200px">
					</div>
					<div class="controls col-sm-4" style="font-size:15px">
						供应商名称:
						<input type="text" class="form-control" placeholder="供应商名称" name="applicantName" value="" style="width:200px">
					</div>
					<div class="controls col-sm-3" style="font-size:15px;margin-left: -70px;">
						运单号:
						<input type="text" class="form-control" placeholder="运单号" name="expressCode" value="" style="width:200px">
					</div>
					<div class="controls col-sm-2 text-right"style="margin-left: -135px;margin-top: 4px">
						<a class="btn btn-success btn-xs" onclick="vsearch();" id="button3">查询</a>
					</div>
				</div>
				<table id="datagrid1" class="mmg" ></table>
				<div id="pg" style="text-align: right;"></div> 
			</div> 
		 <!-- 正文部分end -->
		 <!-- 工具栏部分begin -->
				<div class="panel-footer">
		           <div class="form-group">
			           <div class="col-sm-offset-2 col-sm-4">  
                           <button type="button"  onclick="save()" class="btn btn-primary btn-xs" id="button1">确定</button>
			               <button type="button"  onclick="closeWin()" class="btn btn-default btn-xs" id="button2">关闭</button> 
			           </div>
			      </div>
       		</div>
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
 <!-- 表单部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/transaction/selOrderInfoList.js?v=36'/>"></script>