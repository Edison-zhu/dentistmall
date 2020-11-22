<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>青浦GIS系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
	<script src="<c:url value='/javaScript/sys/sysrole/sysRoleList.js?01'/>"></script>
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
  		#button3{
  			width:95px;
  			vertical-align:middle;
  			align-items:center;
  			}
  		#button4{
  			width:95px;
  			vertical-align:middle;
  			align-items:center;
  			}
  		#button5{
  			width:95px;
  			vertical-align:middle;
  			align-items:center;
  			}	
  		#button6{
  			width:95px;
  			vertical-align:middle;
  			align-items:center;
  			}
  		#button7{
  			width:95px;
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
                    <div class="ibox-title" >
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-fw fa-yelp"></i>&nbsp&nbsp岗位</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">     
                        	</div>
                        
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		        
		             <div class="ibox-content">
		             	
		             	<div class="row">
		             		<button type="button" onclick="addRoleGroup();" class="btn btn-round btn-primary btn-sm fa fa-plus" id="button1">&nbsp增加组&nbsp</button>
							<button type="button" onclick="addRole();" class="btn btn-round btn-info btn-sm fa fa-plus" id="button2">&nbsp增加岗位&nbsp</button>
							<button type="button" onclick="editRole();" class="btn btn-round btn-default btn-sm fa fa-pencil-square-o" id="button3">&nbsp修改&nbsp</button>
							<button type="button" onclick="delRole();" class="btn btn-round btn-danger btn-sm fa fa-minus" id="button4">&nbsp删除&nbsp</button>
		             	</div>
							<div class="scrollbarlist2">
									<div class="panel-body">
										<ul id="roleTree" class="ztree" style="overflow:auto;"></ul>
									</div>
							</div>
                  	 </div>
               	     <!-- 操作区域 end-->	
               	 </div> 
		     </div>
		     
		     <div class="col-sm-3">
                 <div class="ibox float-e-margins">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title">
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-flag"></i>&nbsp&nbsp菜单</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">                         
                          <button type="button" onclick="saveRoleMenu();" class="btn btn-round btn-info btn-sm fa fa-save" id="button5">保存</button>
                        </div>
                        <!-- 标题栏工具区域end -->  
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		        
		             <div class="ibox-content">
		               <div class="scrollbarlist">
							<div class="panel-body">
								<ul id="menutree" class="ztree" style="overflow:auto;"></ul>
							</div>
						</div>
                  	 </div>
               	     <!-- 操作区域 end-->	
               	 </div> 
		     </div>
		     
		     <div class="col-sm-5">
                 <div class="ibox float-e-margins">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title">
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-life-ring"></i>&nbsp&nbsp控件列表信息</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">                         
                          <button type="button" onclick="saveRoleControl();" class="btn btn-round btn-info btn-sm fa fa-save" id="button6">保存</button>
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