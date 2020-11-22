<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
  <head> 
   <title>青浦GIS系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
   <%@ include file="/jsp/link.jsp"%>
   <script src="<c:url value='/javaScript/sys/sysmenu/sysMenuList.js?16'/>"></script>
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
  </style>
<body class="gray-bg">
	<!-- 主信息区域begin -->
	<div class="row  wrapper wrapper-content ">
            <div class="col-sm-4">
                 <div class="ibox float-e-margins">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title">
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-flag"></i>&nbsp&nbsp菜单</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">                         
							<button type="button" onclick="toAddChileMenu();" class="btn btn-round btn-info btn-sm fa fa-plus" id="button1">&nbsp&nbsp增加&nbsp&nbsp</button>
							<button type="button" onclick="delMenu();" class="btn btn-round btn-danger btn-sm fa fa-minus" id="button2">&nbsp&nbsp删除&nbsp&nbsp</button>
                        </div>
                        <!-- 标题栏工具区域end -->  
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
		     
		     <div class="col-sm-8 ">
                 <div class="ibox float-e-margins" style="height:auto">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title">
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-flag"></i>&nbsp&nbsp菜单信息</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">                         
                          <button type="button" onclick="save()" class="btn btn-round btn-info btn-sm fa fa-plus" id="button3">&nbsp&nbsp提交&nbsp&nbsp</button>
                        </div>
                        <!-- 标题栏工具区域end -->  
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->		        
		             <div class="ibox-content">
		               <form class="form-horizontal" id="menuForm">
		              <!-- 影藏区域 -->
						<input type="hidden" name= "smenuId" id="smenuId" value="">
						<input type="hidden" name= "sysSmenuId" id="sysSmenuId" value="">
                       <div class="form-group">
						   <label class="control-label col-sm-2">菜单名称</label>
						   <div class="controls col-sm-3">
						   		<input type="text" class="form-control" name= "menuName" id="menuName" value="">
						   </div>
						   <label class="control-label col-sm-2">菜单编码</label>
						   <div class="controls col-sm-3">
						   		<input type="text" class="form-control" name= "menuCode" id="menuCode" value="">
						   </div>
					   </div>
					   <div class="form-group">
						   <label class="control-label col-sm-2">菜单地址</label>
						   <div class="controls col-sm-8">
						   		<input type="text" class="form-control" name= "menuAddree" value="" id="menuAddree" placeholder="菜单地址">
						   </div>
					   </div>
					   <div class="form-group">
						   <label class="control-label col-sm-2">菜单图标</label>
						   <div class="controls col-sm-3">
						   		<input type="text" class="form-control" name= "menuIcon" value="" id="menuIcon" placeholder="菜单图标">
						   </div>
						   <div class="form-group">
						   <label class="control-label col-sm-2">菜单序号</label>
						   <div class="controls col-sm-3">
						   		<input type="text" class="form-control" name= "menuOrderCode" value="" id="menuOrderCode" placeholder="菜单序号">
						   </div>
					   </div>
					   </div>
					   <div class="form-group">
						   <label class="control-label col-sm-2">菜单状态</label>
						   <div class="controls col-sm-3">
							   <select class="form-control" name="menuState" id="menuState">
							       <option value="1" selected>启用</option>
							       <option value="0">禁用</option>
						       </select>
						   </div>
						   <label class="control-label col-sm-2">菜单类型</label>
						   <div class="controls col-sm-3">
							   <select class="form-control" name="menuType" id="menuType">
						          <option value="1" selected>主菜单</option>
						          <option value="2">子菜单</option>
						          <option value="3">视屏</option>
						          <option value="4">文件</option>
						       </select>
						   </div>
					   </div>
					   <div class="form-group">
						   <label class="control-label col-sm-2">文件上传</label>
						   <div class="controls col-sm-3" id="fileDiv">
							 <div id="fileCodeBtn" value="" ></div>
	                         <input type="hidden" id="fileCode" name="fileCode" value="">
						   </div>
					   </div>
					    </form>
                  	 </div>
               	     <!-- 操作区域 end-->	
               	 </div>
               	 
               	 <div class="ibox float-e-margins">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title">
                    	<!-- 标题文字区域begin -->
                        <h5><i class="fa fa-life-ring"></i>&nbsp&nbsp控件列表信息</h5> 
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
				            	控件名称：
				            	<input type="text" class="form-control" placeholder="控件名称" name="sconName" value="">
				            	
				            </div>
				            <div class="col-sm-3 text-right">
					                    		<button type="button" class="btn btn-info btn-sm" onclick="search();" id="button4">
													<i class="fa fa-search"></i>查询
												</button> 
												<button type="button" class="btn btn-success btn-sm"  onclick="editControlInfo();" id="button5">
													<i class="fa fa-plus"></i>创建控件
												</button>
												
							</div>
							<input type="hidden" name= "smenuId" id="smenuId2" value="">
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
	<input type="hidden"  id="selId" value="">
</body>
</html>

 