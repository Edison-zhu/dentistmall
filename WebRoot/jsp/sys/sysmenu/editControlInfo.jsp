<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>青浦GIS系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/sys/sysmenu/editControlInfo.js'/>"></script>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" name="sconId" id="sconId" value="${obj.sconId}" />
		<input type="hidden" name="smenuId" id="smenuId" value="${obj.smenuId}" />
		<input type="hidden" name="createRen" id="createRen" value="${obj.createRen}" />
		<input type="hidden" name="createDate" value="<fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
		<!--隐藏区域end -->
		
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sconCode" id="sconCode" value="${obj.sconCode}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sconName" id="sconName" value="${obj.sconName}" placeholder="名称">
                    </div>
                </div>
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>类型</label>
                    <div class="controls col-sm-3">
                      <select class="form-control" name="sconType" id="sconType">
						<option value="">请选择类型</option>
						<option value="button" <c:if test="${obj.sconType=='button'}"> selected</c:if> >按钮</option>
						<option value="div" <c:if test="${obj.sconType=='div'}"> selected</c:if>>显示层</option>
						<option value="img" <c:if test="${obj.sconType=='img'}"> selected</c:if>>图片</option>
						<option value="input" <c:if test="${obj.sconType=='input'}"> selected</c:if>>输入框</option>
						<option value="raddio" <c:if test="${obj.sconType=='raddio'}"> selected</c:if>>单选框</option>
						<option value="checkbox" <c:if test="${obj.sconType=='checkbox'}"> selected</c:if>>多选框</option>
						<option value="select" <c:if test="${obj.sconType=='select'}"> selected</c:if>>下拉选择框</option>
						<option value="other" <c:if test="${obj.sconType=='other'}"> selected</c:if>>其他</option>
					  </select>
                    </div>
                </div>
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2">说明</label>
                    <div class="controls col-sm-8">
                    	<textarea name= "sconNode"  class="form-control" style="height:100px;" id="sconNode" placeholder="说明（200字以内）">${obj.sconNode}</textarea>
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