<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>采购中心系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
	<script src="<c:url value='/javaScript/website/sysWebsiteCommentList.js?36'/>"></script>
	<style>
		.col-sm-1-5 {
			float:left;
		    width: 10.66666667%;
		    position: relative;
		    min-height: 1px;
		    padding-right: 15px;
		    padding-left: 15px;
		    
		}
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
         #searchForm1 {
            width: 50%;
            display: inline;
            margin-left: 68px;
        } 
	</style>
  </head> 
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
                        <h5><i class="fa fa-file-text-o"></i>&nbsp&nbsp内容信息</h5> 
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
		             	<div class="row" id="queryDiv" >
						    <form id="queryForm">
							   <!--  <label class="control-label col-sm-1">标题</label>
							    <div class="controls col-sm-2">
							    	<input type="text" class="form-control" placeholder="标题" name="newsTitle" id="newsTitle" value="">
							    </div> -->
							    <!-- 更改label样式 -->
							    <div id="searchForm" class="form-inline" style="display: inline">
					                 	<label>标题</label>
					                 	<input type="text" class="form-control" placeholder="标题" name="newsTitle" id="newsTitle" value="">
					              </div>
					               <div id="searchForm1" class="form-inline" style="display: inline">
					                 	<label>发布状态</label>
					                 	<select name="state" class="form-control" id="queryState">
			                         	<option value=""></option>
			                         	<option value="1">发布</option>
			                         	<option value="2">草稿</option>
			                         </select>
					             	</div> 
								<!--  <label class="control-label col-sm-1-5">发布状态</label>
							    <div class="controls col-sm-2">
							    	<select name="state" class="form-control" id="queryState">
			                         	<option value=""></option>
			                         	<option value="1">发布</option>
			                         	<option value="2">草稿</option>
			                         </select>
							    </div>  -->
						    </form>
					    </div>
					    <div class="row" id="matDiv" style="display:none">
						    <form id="matForm">
							    <label class="control-label col-sm-1-5">商品名称</label>
							    <div class="controls col-sm-2">
							    	<input type="text" class="form-control" placeholder="商品名称" name="matName" id="matName" value="">
							    </div>
							    <label class="control-label col-sm-1-5">商品状态</label>
							    <div class="controls col-sm-2">
							    	<select name="state" class="form-control" id="matState">
			                         	<option></option>
			                         	<option value="1">启用</option>
			                         	<option value="2">禁用</option>
			                         </select>
							    </div>
							    <label class="control-label col-sm-1-5">发布状态</label>
							    <div class="controls col-sm-2">
							    	<select name="commState" class="form-control" id="matCommState">
			                         	<option value=""></option>
			                         	<option value="1">发布</option>
			                         	<option value="2">草稿</option>
			                         </select>
							    </div>
						    </form>
					    </div>
					    <div class="row" id="supplierDiv" style="display:none">
						    <form id="supplierForm">
							    <label class="control-label col-sm-1-5">供应商名称</label>
							    <div class="controls col-sm-2">
							    	<input type="text" class="form-control" placeholder="供应商名称" id="applicantName" name="applicantName" value="">
							    </div>
							    <label class="control-label col-sm-1-5">供应商状态</label>
							    <div class="controls col-sm-2">
							    	<select name="state" class="form-control" id="supplierState">
			                         	<option value=""></option>
			                         	<option value="1">启用</option>
			                         	<option value="2">禁用</option>
			                         </select>
							    </div>
							    <label class="control-label col-sm-1-5">发布状态</label>
							    <div class="controls col-sm-2">
							    	<select name="commState" class="form-control" id="supplierCommState">
			                         	<option value=""></option>
			                         	<option value="1">发布</option>
			                         	<option value="2">草稿</option>
			                         </select>
							    </div>
						    </form>
					    </div>
					    <div class="row" id="typeDiv" style="display:none">
						    <form id="typeForm">
							    <label class="control-label col-sm-1-5">类别名称</label>
							    <div class="controls col-sm-2">
							    	<input type="text" class="form-control" placeholder="类别名称" name="mmtName" id="mmtName" value="">
							    </div>
							    <label class="control-label col-sm-1-5">类别状态</label>
							    <div class="controls col-sm-2">
							    	<select name="state" class="form-control" id="typeState">
			                         	<option value=""></option>
			                         	<option value="1">启用</option>
			                         	<option value="2">禁用</option>
			                         </select>
							    </div>
							    <label class="control-label col-sm-1-5">发布状态</label>
							    <div class="controls col-sm-2">
							    	<select name="commState" class="form-control" id="typeCommState">
			                         	<option value=""></option>
			                         	<option value="1">发布</option>
			                         	<option value="2">草稿</option>
			                         </select>
							    </div>
						    </form>
					    </div>
					    <div id="gridDiv" style="margin-top:5px">
			               <table id="datagrid1" class="mmg" ></table>
						   <div id="pg" style="text-align: right;"></div>
					   	</div>
                  	 </div>
               	     <!-- 操作区域 end-->	
               	 </div> 
		     </div>
	</div>
<!--内容部分end-->
</body>
</html>