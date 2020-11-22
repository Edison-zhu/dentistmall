<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>采购中心系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
	
  </head> 
  <style>
  		#win_but_search{
  			width:95px;
  		}
  		#win_but_add{
  			width:95px;
  		}
  </style>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							<i class="fa fa-group"></i><lable id="_title"></lable>用户管理 
						</h5>
						<div class="ibox-tools">
							<button id="win_but_search"  onclick="search();" type="button" style="margin-left:5px;" class="btn btn-rounded btn-default btn-sm fa fa-search ">查询</button>
							<button id="win_but_add"     onclick="editUser();" type="button" style="margin-left:5px;" class="btn btn-rounded btn-primary btn-sm fa fa-plus ">添加</button>
						</div>
					</div>
					<div class="ibox-content">
						<div role="grid" class="dataTables_wrapper form-inline"
							id="DataTables_Table_0_wrapper"> 

									<!--条件输入区域begin-->
					               <div class="row">
					                    <form id="queryForm">
					                    <input type="hidden" name="userType" id="userType"  />
					                    <input type="hidden" name="orgGxId" id="orgGxId"  />
					                    <input type="hidden" name="organizaType" id="organizaType"  />
					                    <input type="hidden" name="oldId" id="oldId" />
					                    <input type="hidden" name="state_str" id="state_str"  value="1,2" />
					                    
					                    
					                    <div class="controls col-sm-3" style="font-size:15px">
					                    	用户名称:
					                      <input type="text" class="form-control" placeholder="用户名称" name="userName" value="" style="width:100px">
					                    </div>
					                    <div class="controls col-sm-3" style="font-size:15px">
					                                          用户账号:
					                      <input type="text" class="form-control" placeholder="用户账号" name="account" value="" style="width:100px">
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
												
										</div>
					                </div>
					               <br/>
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
<script src="<c:url value='/javaScript/sys/sysuser/addUserList.js?v=21'/>"></script>