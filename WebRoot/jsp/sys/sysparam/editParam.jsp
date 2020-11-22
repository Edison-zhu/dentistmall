<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>青浦GIS系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/sys/sysparam/editParam.js' />"></script>
  </head> 
<body style="overflow:hidden;">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" name="sparId" id="sparId" value="" />
		<input type="hidden" name="sysSparId" id="sysSparId" value="" />
		<input type="hidden" name="createRen" id="createRen" value="" />
		<input type="hidden" name="createDate" value="" />
		<!--隐藏区域end -->
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo" id="formBody">
				<div class="form-group" styel="">
				   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>编码编号</label>
	                    <div class="controls col-sm-3">
	                      <input type="text" class="form-control" name= "paramCode" id="paramCode" value=""  >
	                    </div>
	                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>编码名称</label>
	                    <div class="controls col-sm-3">
	                      <input type="text" class="form-control" name= "paramName" id="paramName" value=""  placeholder="编码名称">
	                    </div>
	                </div>
	                <div class="form-group" styel="">
				   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>编码值</label>
	                    <div class="controls col-sm-3">
	                      <input type="text" class="form-control" name= "paramValue" id="paramValue" value=""  placeholder="编码值">
	                    </div>
	                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>编码序号</label>
	                    <div class="controls col-sm-3">
	                      <input type="text" class="form-control" name="paramOrderNumber" id="paramOrderNumber" value=""  placeholder="编码序号">
	                    </div>
	                </div>
	                
			</div> 
		 <!-- 正文部分end -->
		 <!-- 工具栏部分begin -->
				<div class="panel-footer">
		           <div class="form-group">
			           <div class="col-sm-offset-2 col-sm-4"> 
                            <button type="button"  onclick="save()" class="btn btn-primary">保存</button>
			           		<button type="button"  onclick="closeWin()" class="btn btn-default">关闭</button> 
			           </div>
			      </div>
       		</div>
       		
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
 <!-- 表单部分end-->
</body>
</html>