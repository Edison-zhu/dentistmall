<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>优牙库</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
	
  </head> 
  <style>
  		#button1{
  			width:95px;
  			vertical-align:middle;
  			align-items:center;
  		}
  		#button2{
  			width:95px;
  			vertical-align:middle;
  			align-items:center;
  		}
  		
  </style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<i class="fa fa-group"></i>系统用户管理 
						</h5>
						<div class="ibox-tools"></div>
					</div>
					<div class="ibox-content">

						<div role="grid" class="dataTables_wrapper form-inline"
							id="DataTables_Table_0_wrapper"> 
										<!-- <div id="searchForm" class="form-inline" style="display: inline">
					                 	<label>栏目名称</label>
					                 	<input type="text" class="form-control" placeholder="栏目名称" name="columnName" value="">
					                  </div>  -->
									<!--条件输入区域begin-->
					               <div class="row">
					                    <form id="queryForm">
					                    <div class="controls col-sm-3" style="font-size:15px">
					                    	用户名称:
					                      <input type="text" class="form-control" placeholder="用户名称" name="userName" value="" style="width:200px">
					                    </div>
					                    <div class="controls col-sm-3" style="font-size:15px">
					                                          用户账号:
					                      <input type="text" class="form-control" placeholder="用户账号" name="account" value="" style="width:200px">
					                    </div>
					                    <div class="controls col-sm-3" style="font-size:15px">
					                     	状态:
					                      <select class="form-control" name="state">
					                      	<option value="">全部</option>
					                      	<option value="1">启用</option>
					                      	<option value="2">禁用</option>
					                      </select>
					                    </div>
					                    </form>
					                    <div class="col-sm-3 text-right">
					                    		<button type="button" class="btn btn-info btn-sm" onclick="search();" id="button1">
													<i class="fa fa-search"></i>查询
												</button> 
												<button type="button" class="btn btn-success btn-sm"  onclick="editUser();" id="button2">
													<i class="fa fa-plus"></i>创建用户
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
<script src="<c:url value='/javaScript/sys/sysuser/sysUserList.js?v=12'/>"></script>
 