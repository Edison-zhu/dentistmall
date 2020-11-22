<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>基础平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/sys/sysinfo/editSysInfo.js?v=10'/>"></script>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" name="ssiId" id="ssiId" value="${obj.ssiId}" />
		<input type="hidden" name="swsId" id="swsId" value="${obj.swsId}" />
		<input type="hidden" name="sysSsiId" id="sysSsiId" value="${obj.sysSsiId}" />
		<input type="hidden" name="createRen" id="createRen" value="${obj.createRen}" />
		<input type="hidden" name="createTime" value="<fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
		<!--隐藏区域end -->
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>系统编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysCode" id="sysCode" value="${obj.sysCode}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>系统名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysName" id="sysName" value="${obj.sysName}"  placeholder="系统名称">
                    </div>
                </div>
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>IP地址</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "systemIp" id="systemIp" value="${obj.systemIp}" placeholder="IP地址" onblur="initUrl()">
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>端口号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "systemPort" id="systemPort" value="${obj.systemPort}"  placeholder="端口号" onblur="initUrl()">
                    </div>
                </div>
                   <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>字符集</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "charset" id="charset" value="${obj.charset}"  placeholder="字符集" >
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>服务名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "systemService" id="systemService" value="${obj.systemService}" placeholder="服务名称">
                    </div>
                   
                </div>
                <div class="form-group" styel="">
			   		  <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>项目名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "projectName" id="projectName" value="${obj.projectName}"  placeholder="项目名称">
                    </div> 
                </div>
                
                     <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>数据库名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "dbName" id="dbName" value="${obj.dbName}"  placeholder="数据库名" >
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>数据库用户名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "dbuserName" id="dbuserName" value="${obj.dbuserName}" placeholder="数据库用户名">
                    </div>
                   
                </div>
             
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2">项目根路径</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "projectRootPath" id="projectRootPath" value="${obj.projectRootPath}"  placeholder="项目根路径"  readonly=true>
                    </div>
                     <label class="control-label col-sm-2">项目存储路径</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "savePath" id="savePath" value="${obj.savePath}"  placeholder="项目存储路径"  readonly=true>
                    </div>
                </div>
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>访问地址</label>
                    <div class="controls col-sm-8">
                      <input type="text" class="form-control" name= "systemUrl" id="systemUrl" value="${obj.systemUrl}" placeholder="访问地址" readonly=true>
                    </div>
                </div>
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>系统类型</label>
                    <div class="controls col-sm-3">
                      <select class="form-control" name="systemtype" id="systemtype">
                      	<option value="">请选择</option>
                      	<option value="1" <c:if test="${obj.systemtype =='1'}"> selected</c:if> >web服务器</option>
                      	<option value="2" <c:if test="${obj.systemtype =='2'}"> selected </c:if> >主后台服务</option>
                      	<option value="3" <c:if test="${obj.systemtype =='3'}"> selected </c:if> >业务服务器</option>
                      	<option value="4" <c:if test="${obj.systemtype =='4'}"> selected </c:if> >文件服务器</option>
                      	<option value="5" <c:if test="${obj.systemtype =='5'}"> selected </c:if> >业务样板系统</option>
                      </select>
                    </div>
                    <label class="control-label col-sm-2">接口类型</label>
                    <div class="controls col-sm-3">
                      <select class="form-control" name="sysIotype" id="sysIotype">
                      	<option value="">请选择</option>
                      	<option value="1,0" <c:if test="${obj.sysIotype =='1,0'}"> selected</c:if> >http</option>
                      	<option value="0,1" <c:if test="${obj.sysIotype =='0,1'}"> selected</c:if> >webservice</option>
                      	<option value="1,1" <c:if test="${obj.sysIotype =='1,1'}"> selected</c:if> >http和webservice</option>
                      </select>
                    </div>
                </div>
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>数据库类型</label>                     
                    <div class="controls col-sm-10">
                      <select class="form-control" name="dbType" id="dbType">
                      	<option value="">请选择</option>
                      	<option value="mysql" <c:if test="${obj.dbType =='mysql'}"> selected</c:if> >mysql</option>
                      	<option value="oracle" <c:if test="${obj.dbType =='oracle'}"> selected</c:if> >oracle</option>
                      	<option value="sqlservice" <c:if test="${obj.dbType =='sqlservice'}"> selected</c:if> >sqlservice</option>
                      </select>
                    </div>
                </div>
                
                
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2">系统简介</label>
                    <div class="controls col-sm-10">
                      <textarea name= "systemComment"  class="form-control" style="height:100px;" id="systemComment" placeholder="系统简介（200字以内）">${obj.systemComment}</textarea>
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