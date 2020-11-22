<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>青浦GIS系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/sys/sysrole/editSysRole.js'/>"></script>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" name="sroleId" id="sroleId" value="${obj.sroleId}" />
		<input type="hidden" name="sysSroleId" id="sysSroleId" value="${obj.sysSroleId}" />
		<input type="hidden" name="createRen" id="createRen" value="${obj.createRen}" />
		<input type="hidden" name="createDate" value="<fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
		<!--隐藏区域end -->
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>岗位编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "menuCode" id="menuCode" value="${obj.menuCode}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>岗位名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "menuName" id="menuName" value="${obj.menuName}"  placeholder="岗位名称">
                    </div>
                </div>
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2">岗位说明</label>
                    <div class="controls col-sm-3">
                      <textarea name= "menuComment"  style="height:100px;width:400px" id="menuComment" placeholder="岗位说明（200字以内）">${obj.menuComment}</textarea>
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