<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>基础平台系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
	<script src="<c:url value='/javaScript/sys/sysinfo/sysInfoList.js?v=10'/>"></script>
  </head> 
 
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<i class="fa fa-group"></i>系统信息
						</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">

						<div role="grid" class="dataTables_wrapper form-inline"
							id="DataTables_Table_0_wrapper"> 

									<!--条件输入区域begin-->
					               <div class="row">
					                    <form id="queryForm"> 					                    
					                    <div class="controls col-sm-3" style="font-size:15px">
					                    	系统编号:
					                      <input type="text" class="form-control" placeholder="系统编号" name="sysCode" value="">
					                    </div>
					                    <div class="controls col-sm-3" style="font-size:15px">
					                                         系统名称:
					                       <input type="text" class="form-control" placeholder="系统名称" name="sysName" value="">
					                    </div>
					                    <div class="controls col-sm-3" style="font-size:15px">
					                     	 
					                    </div>
					                    </form>
					                    <div class="col-sm-3 text-right">
					                    		<button type="button" class="btn btn-info btn-sm" onclick="search();">
													<i class="fa fa-search"></i>查询
												</button> 
												<button type="button" class="btn btn-success btn-sm"  onclick="editSys();">
													<i class="fa fa-plus"></i>创建系统
												</button>
												
										</div>
					                </div>
					               
									<!--条件输入区域end-->
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
 
 