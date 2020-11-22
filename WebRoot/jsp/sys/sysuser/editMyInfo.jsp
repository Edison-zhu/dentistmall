<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>青浦GIS系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/sys/sysuser/editMyInfo.js?03'/>"></script>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" name="suiId" id="suiId" value="${obj.suiId}" />
		<input type="hidden" name="createRen" id="createRen" value="${obj.createRen}" />
		<input type="hidden" name="password" id="password" value="${obj.password}" />
		<input type="hidden" name="createDate" value="${obj.createDate}" />
		<input type="hidden" name="state" id="state" value="${obj.state}" />
		<input type="hidden" name="barCode" id="barCode" value="${obj.barCode}" />
		<input type="hidden" name="barCodeFileCode" id="barCodeFileCode" value="${obj.barCodeFileCode}" />
		<input type="hidden" name="userType" id="userType" value="${obj.userType}" />
		<input type="hidden" name="orgGxId" id="orgGxId" value="${obj.orgGxId}" />
		<input type="hidden" name="organizaType" id="organizaType" value="${obj.organizaType}" />
		<input type="hidden" name="oldId" id="oldId" value="${obj.oldId}" /> 
		<input type="hidden" name="userRole" id="userRole" value="${obj.userRole}" />
		<!--隐藏区域end -->
		
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>用户编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "userCode" id="userCode" value="${obj.userCode}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>用户账号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" onkeyup = "checkAccount()" name= "account" id="account" value="${obj.account}"  <c:if test="${obj.account != null}"> readonly=true</c:if> placeholder="用户账号">
                      <span style="color:red;display:none" id="accountCheck" >*该账号已存在</span>
                    </div>
                </div>
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>用户名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control"  name= "userName" id="userName" value="${obj.userName}" placeholder="用户名称">
                    </div>
                    <label class="control-label col-sm-2">手机号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "phoneNumber" id="phoneNumber" value="${obj.phoneNumber}">
                    </div>
                </div>
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2">邮箱</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "email" id="email" value="${obj.email}"  placeholder="邮箱" >
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