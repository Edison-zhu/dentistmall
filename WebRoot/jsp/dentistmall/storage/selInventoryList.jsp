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
	  .tr-style td{
		  padding-left: 20px;
	  }
	  .td-width{
		  width: 350px;
	  }
		 .input-text{
			 height: 22px;
		 }
		 .input-button{
			 height: 22px;
			 padding: 4px 9px;
			 line-height: 1px;
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
					<input type="hidden" name="state" value="1">
					<input type="hidden" name="matType" id="matType" value="">
					<div class="form-inline form-group">
						<table>
							<tr class="tr-style">
								<td>物料名称:</td>
								<td class="td-width">
									<div><input type="text" class="form-control" placeholder="输入物料名称,回车查询" name="matName" id="mat1"
												value=""
												style="width: 80%">
									</div>
								</td>
								<td>规格:</td>
								<td class="td-width">
									<div><input type="text" class="form-control" placeholder="输入规格,回车查询" name="mmfName" id="mmf1"
												value=""
												style="width: 80%">
									</div>
								</td>
								<td></td>
								<td style="padding-left: 150px">
									<div class="controls col-sm-2 text-right">
										<a class="btn btn-success btn-xs" onclick="search2();" id="button3">查询</a>
									</div>
							</td>
						</tr>
						</table>
					</div>
					<div class="tabs-container">
				        <ul class="nav nav-tabs">
					        <li class="active"  id="sz"><a data-toggle="tab" onclick="javascript:changeType('2')" aria-expanded="true">全部库存</a>
					        </li>
					         <li id="sw"><a data-toggle="tab" onclick="javascript:changeType('1')" aria-expanded="false">收藏库存</a>
					        </li>
				        </ul>
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
<script src="<c:url value='/javaScript/dentistmall/storage/selInventoryList.js?v=29'/>"></script>