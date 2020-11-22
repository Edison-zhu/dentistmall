<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
  <head> 
   <title>青浦GIS系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
   <%@ include file="/jsp/link.jsp"%>
   <script src="<c:url value='/javaScript/sys/sysorg/organizationList.js'/>"></script>
  </head> 
  <style>
  		#sea{
  			width:90px;
  			vertical-align:middle;
  			align-items:center;
  		}
  </style>
<body  class="gray-bg">
<!-- 主信息区域begin -->
	<div class="row  wrapper wrapper-content ">
            <div class="col-sm-4">
                 <div class="ibox float-e-margins">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title">
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp组织类型</h5> 
                        <!-- 标题文字区域end -->   
                        
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		        
		             <div class="ibox-content">
								<div class="panel-body">
									<ul id="tree" class="ztree" style="overflow:auto;"></ul>
								</div>
                  	 </div>
               	     <!-- 操作区域 end-->	
               	 </div> 
		     </div>
		     
		     <div class="col-sm-8">
                 <div class="ibox float-e-margins">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title">
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp组织列表信息</h5> 
                        <!-- 标题文字区域end -->   
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		
                    <div class="ibox-content">        
		             <div crole="grid" class="dataTables_wrapper form-inline"
							id="DataTables_Table_0_wrapper">
                       <!--条件输入区域begin-->
		                <div class="row" styel="">
		                	<form class="form-horizontal" id="queryForm">
				            <div class="controls col-sm-9" style="font-size:15px">
				            	名称：
				            	<input type="text" class="form-control" placeholder="名称" name="name" value="">
				            </div>
				            <div class="col-sm-3 text-right">
					                    		<button type="button" class="btn btn-info btn-sm" onclick="search();" id="sea">
													<i class="fa fa-search" ></i>查询
												</button> 
												<button type="button" class="btn btn-success btn-sm"  onclick="editCompany();">
													<i class="fa fa-plus"></i>创建组织
												</button>
												
							</div>
							<input type="hidden" name="orgGxId" id="orgGxId" >
					    	</form>
		                </div>
							<table id="datagrid1" class="mmg" ></table>
							<div id="pg" style="text-align: right;"></div>
					    </div>
                  	 </div>
               	     <!-- 操作区域 end-->	
               	 </div> 
		     </div>
	</div>
</body>
</html>
 