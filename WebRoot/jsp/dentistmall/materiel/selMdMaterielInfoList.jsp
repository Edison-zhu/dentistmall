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
  		.divControls{
  			float: left;
  			margin-left:30px
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
					<div class="divControls" style="font-size:15px"><!-- controls col-sm-4 -->
						物料信息:
						<input type="text" class="form-control" placeholder="请输入物料信息,回车查询" name="matName" value="" style="width:210px" id="matName">
					</div>
					<div class="divControls" style="font-size:15px" >
						规格名称:
						<input type="text" class="form-control" placeholder="请输入规格名称,回车查询" name="mmfName" value="" style="width:210px" id="mmfName">
					</div>
					<div class="controls col-sm-2 text-right">
						<a class="btn btn-success btn-xs" onclick="search2();" id="button3">查询</a>
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
			               <button type="button"  onclick="beforeClose()" class="btn btn-default btn-xs" id="button2">关闭</button> 
			           </div>
			      </div>
       		</div>
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
 <!-- 表单部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/materiel/selMdMaterielInfoList.js?v=11'/>"></script>