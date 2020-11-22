<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
  <head> 
   <title>优牙库</title> 
   <%@ include file="/jsp/lib.jsp"%> 
   <%@ include file="/jsp/link.jsp"%>
  
  </head> 
<body  class="gray-bg">
<!-- 主信息区域begin -->
	<div class="row  wrapper wrapper-content ">
            <div class="col-sm-3"   id="orgTreebody"  style="padding-right:5px;" >
                 <div class="ibox float-e-margins">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title">
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp组织结构树</h5> 
                        <!-- 标题文字区域end --> 
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		        
		             <div class="ibox-content" style="height:492.26px;"> 
						 <ul id="tree" class="ztree" style="overflow:auto;"></ul> 
                  	 </div>
               	     <!-- 操作区域 end-->	
               	 </div> 
		     </div>

		<div class="col-sm-9" id="staffListbody" style="padding-left:5px;"> 
				<!-- 标题区域begin -->
				<div class="ibox-title">
					<!-- 标题文字区域begin -->
					<h5>
						<i class="fa fa-flask"></i>&nbsp;&nbsp;<span>员工信息列表</span>
					</h5>
					<!-- 标题文字区域end -->
					<div class="ibox-tools" id="win_tools_but">
						<button id="win_but_search" type="button"  onclick="search()" style="margin-left:5px;"
							class="btn btn-rounded btn-default btn-sm fa fa-search ">查询</button>
						<button id="win_but_add" type="button" style="margin-left:5px;"
							class="btn btn-rounded btn-primary btn-sm fa fa-plus "  onclick="editUser()" >添加</button> 
					</div>
				</div>
				<!-- 标题区域end -->
				<!-- 查询区域begin -->
			<div class="ibox-content" style="padding: 5px 10px 10px;">
				<form class="form-horizontal" id="queryForm"> 
					        			<input type="hidden" name="userType" id="userType"  />
					                    <input type="hidden" name="orgGxId" id="orgGxId"  />
					                    <input type="hidden" name="organizaType" id="organizaType"  />
					                    <input type="hidden" name="oldId" id="oldId" />
					                    <input type="hidden" name="state_str" id="state_str"  value="1,2" />
					                    
						
					<div class="form-group" style="margin-bottom:0px;">
						<label class="col-sm-2 control-label">账号名称：</label>
						<div class="col-sm-4">
							<input id="userName" name="userName" class="form-control"
								placeholder="账号名称" type="text" aria-required="false"
								aria-invalid="false">
						</div>
						<label class="col-sm-2 control-label">登陆名称：</label>
						<div class="col-sm-4">
							<input id="account" name="account" class="form-control"
								placeholder="登陆名称" type="text" aria-required="false"
								aria-invalid="false">
						</div>
					</div>
				</form>
			</div>
			<!-- 查询区域end -->
 				<!-- 表格区域begin -->
						<div class="ibox-content" style="padding: 5px 10px 10px;">
							<table id="datagrid1" class="mmg"></table>
							<div id="pg" style="text-align: right;"></div> 
						</div>
				<!-- 表格区域end -->
		</div>
	</div>
</body>
</html>

 <script src="<c:url value='/javaScript/sys/sysuser/staffList.js?v=37'/>"></script>