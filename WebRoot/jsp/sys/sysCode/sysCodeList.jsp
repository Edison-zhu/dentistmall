<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>代码管理列表</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
	<style type="text/css">
	
	.ui-jqgrid tr.jqgrow td {
white-space: normal !important;
height:auto;
vertical-align:text-top;
padding-top:2px;
} 
	</style>
  </head> 
 
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<i class="fa fa-group"></i>系统类
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
					                    	系统类编号:
					                      <input type="text" class="form-control" placeholder="系统类编号" name="SClassCode" value="">
					                    </div>
					                    <div class="controls col-sm-3" style="font-size:15px">
					                                         系统类名称:
					                       <input type="text" class="form-control" placeholder="系统类名称" name="SClassName" value="">
					                    </div>
					                    <div class="controls col-sm-3" style="font-size:15px">
					                     	系统编码:
					                     	<select class="form-control" name="sysCode" id="sysCode"  style="width: 140px;height: 25px;padding: 0px;">
	                      	                      	 
	                      						</select> 
					                    </div>
					                    </form>
					                    <div class="col-sm-3 text-right">
					                    		<button type="button" class="btn btn-info btn-xs" onclick="search();">
													<i class="fa fa-search"></i>查询
												</button>  
												<button type="button" class="btn btn-info btn-xs" onclick="listDataZip();">
													<i class="fa fa-share"></i>批量导出
												</button>  
												
												<button type="button" class="btn btn-info dropdown-toggle btn-xs" 
														data-toggle="dropdown">
													创建 <span class="caret"></span>
												</button>
												<ul class="dropdown-menu" role="menu"  style="width: 95px;min-width:0px; ">
													<li ><a href="javascript:editSys('','pojo');" >pojo</a></li>
													<li  class="divider" ></li>
													<li  ><a href="javascript:editSys('','dao');">dao</a></li>
													<li class="divider" ></li>
													<li  ><a href="javascript:editSys('','service');" >service</a></li> 
												</ul>
												
												
												
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

<script src="<c:url value='/javaScript/sys/syscode/sysCodeList.js?v=12'/>"></script>
 
 