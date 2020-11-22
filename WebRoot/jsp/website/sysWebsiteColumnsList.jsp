<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>采购中心系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
	<script src="<c:url value='/javaScript/website/sysWebsiteColumnsList.js?19'/>"></script>
  </head> 
  <style>
  		.ibox-tools button{
  			width:95px;
  			vertical-align:middle;
  			align-items:center;
  		}
  		#searchForm {
            width: 50%;
            display: inline;
            margin-left: 18px;
        }
  </style>
<body  class="gray-bg">
<!-- 主信息区域begin -->
	<div class="row  wrapper wrapper-content ">
            <div class="col-sm-4">
                 <div class="ibox float-e-margins">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title" >
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp网站栏目</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->  
                        <div class="ibox-tools">     
                        </div>
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		        
		             <div class="ibox-content">
							<div class="scrollbarlist">
									<div class="panel-body">
										<ul id="tree" class="ztree" style="overflow:auto;"></ul>
									</div>
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
                        <h5><i class="fa fa-tint"></i>&nbsp&nbsp栏目信息</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">                         
                          <button type="button" class="btn btn-round btn-default btn-sm fa fa-search" onclick="search()">&nbsp;查询</button>
						  <button type="button" class="btn btn-round btn-info btn-sm fa fa-edit" onclick="add()">&nbsp;添加</button>
                        </div>
                        <!-- 标题栏工具区域end -->  
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		        
		             <div class="ibox-content">
		             	<div class="row">
						    <form id="queryForm">
						    <input type="hidden"  id="swiId" name="swiId">
                			<input type="hidden"  id="sysSwcId" name="sysSwcId">
							   <!--  <label class="control-label col-sm-2">栏目名称</label>
							    <div class="controls col-sm-2">
							    	<input type="text" class="form-control" placeholder="栏目名称" name="columnName" value="">
							    </div> -->
							     <div id="searchForm" class="form-inline" style="display: inline">
					                 	<label>栏目名称</label>
					                 	<input type="text" class="form-control" placeholder="栏目名称" name="columnName" value="">
					                  </div>
						    </form>
					    </div>
		               <table id="datagrid1" class="mmg" ></table>
					   <div id="pg" style="text-align: right;"></div>
                  	 </div>
               	     <!-- 操作区域 end-->	
               	 </div> 
		     </div>
	</div>
<!--内容部分end-->
</body>
</html>