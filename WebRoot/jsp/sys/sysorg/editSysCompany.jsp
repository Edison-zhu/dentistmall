<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>青浦GIS系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/sys/sysorg/editSysCompany.js'/>"></script>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" name="wzId" id="wzId" value="${obj.wzId}" />
		<input type="hidden" name="orgGxId" id="orgGxId" value="${obj.orgGxId}" />
		<input type="hidden" name="state" id="state" value="${obj.state}" />
		<input type="hidden" name="createRen" id="createRen" value="${obj.createRen}" />
		<input type="hidden" name="createDate" value="${obj.createDate}" />
		<input type="hidden" name="barCode" id="barCode" value="${obj.barCode}" />
		<input type="hidden" name="barCodeFileCode" id="barCodeFileCode" value="${obj.barCodeFileCode}" />
		<input type="hidden" name="printNum" id="printNum" value="${obj.printNum}" />
		<!--隐藏区域end -->
		
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "education" id="education" value="${obj.education}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "applicantName" id="applicantName" value="${obj.applicantName}" placeholder="名称">
                    </div>
                </div>
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2">省份</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control"  name= "selProvince" id="selProvince" value="${obj.selProvince}" placeholder="省份">
                    </div>
                    <label class="control-label col-sm-2">城市</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "selCity" id="selCity" value="${obj.selCity}" placeholder="城市">
                    </div>
                </div>
                 <div class="form-group" styel="">
                    <label class="control-label col-sm-2">联系人</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control"  name= "fullName" id="fullName" value="${obj.fullName}" placeholder="联系人">
                    </div>
                    <label class="control-label col-sm-2">联系电话</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "phoneNumber" id="phoneNumber" value="${obj.phoneNumber}" placeholder="联系电话">
                    </div>
                </div>
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2">邮箱</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "mailBox" id="mailBox" value="${obj.mailBox}"  placeholder="邮箱">
                    </div>
                </div>
                
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2">联系地址</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "corporateDomicile" id="corporateDomicile" value="${obj.corporateDomicile}"  placeholder="联系地址">
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