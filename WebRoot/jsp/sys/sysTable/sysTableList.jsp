<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>

  </head> 
<body  class="gray-bg">
<!-- 主信息区域begin -->
	<div class="row  wrapper wrapper-content ">
            <div class="col-sm-4">
                 <div class="ibox float-e-margins">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title" >
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-fw fa-yelp"></i>&nbsp&nbsp表对象列表</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools"> 
                        <span  class="btn btn-round"  style="border-radius:3px;padding: 0px;">
                         <select class="form-control" name="search_sysCode" id="search_sysCode"  style="width: 140px;height: 25px;padding: 0px;">
	                      	                      	 
	                      </select> 
	                      </span> <button type="button" onclick="refTable();" class="btn btn-round btn-primary btn-sm fa fa-search"></button>
		             		<button type="button" onclick="addorEdittable(0);" class="btn btn-round btn-primary btn-sm fa fa-plus"></button>
							<button type="button" onclick="addorEdittable(1);" class="btn btn-round btn-primary btn-sm fa fa-pencil-square-o"></button>
							<button type="button" onclick="deletetable();" class="btn btn-round btn-danger btn-sm fa fa-minus"></button>
                        </div> 
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		        
		             <div class="ibox-content"> 
		             	<div class="row">
		             	
							
		             	</div>
							<div class="scrollbarlist2">
									<div class="panel-body">
										<ul id="tableTree" class="ztree" style="overflow:auto;"></ul>
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
                        <h5><i class="fa fa-life-ring"></i>子段对象列表</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">                         
                          <button type="button" onclick="addOrEditField();" class="btn btn-round btn-info btn-sm fa fa-save">添加</button>
                          
                        </div>
                        <!-- 标题栏工具区域end -->  
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		        
		             <div class="ibox-content">
		               <table id="datagrid1" class="mmg" ></table>
					   <div id="pg" style="text-align: right;"></div>
                  	 </div>
               	     <!-- 操作区域 end-->	
               	 </div> 
		     </div>
	</div>
<!--内容部分end-->
 <input type="hidden" id="sroleId" value="">
 <input type="hidden" id="isParent" value="">
 <input type="hidden" id="smenuId" value="">
</body>
</html>
	<script src="<c:url value='/javaScript/sys/systable/sysTableList.js?v=12'/>"></script>