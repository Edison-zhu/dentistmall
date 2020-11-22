<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>基础平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>

  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" name="stableId" id="stableId" value="${obj.stableId}" />
		<input type="hidden" name="smiId" id="smiId" value="${obj.smiId}" /> 
		<input type="hidden" name="createRen" id="createRen" value="${obj.createRen}" /> 
		<input type="hidden" name="tableState" id="tableState" value="${obj.tableState}" /> 
		<input type="hidden" name="linkAddree" id="linkAddree" value="${obj.linkAddree}" /> 
				<input type="hidden" name="linkAddree" id="linkAddree" value="${obj.linkAddree}" /> 
						<input type="hidden" name="tableNameTemp" id="tableNameTemp" value="${obj.tableName}" /> 
		<!--隐藏区域end -->
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group" styel="">
			   		<label class="control-label col-sm-2">系统编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysCode" id="sysCode" value="${obj.sysCode}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2">表对象编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= tableCode id="tableCode" value="${obj.tableCode}" readonly=true>
                    </div>
                </div>
                
                 <div class="form-group" styel="">
			   		<label class="control-label col-sm-2">数据库用户名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "dbuserName" id="dbuserName" value="${obj.dbuserName}"  readonly=true>
                    </div>
                    <label class="control-label col-sm-2">数据库名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "dbName" id="dbName" value="${obj.dbName}"  readonly=true>
                    </div>
                </div>
                
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2">创建人</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "createRen" id="createRen" value="${obj.createRen}"  readonly=true>
                    </div>
                    <label class="control-label col-sm-2">创建时间</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "createDatetime" id="createDatetime" value='<fmt:formatDate value="${obj.createDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>' readonly=true>
                    </div>
                </div>
                
                  <div class="form-group" styel="">
			   		<label class="control-label col-sm-2">映射对象名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "mappingclassName" id="mappingclassName" value="${obj.mappingclassName}"  readonly=true>
                    </div>
                    <label class="control-label col-sm-2">创建时间</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "dbType" id="dbType" value='${obj.dbType}' readonly=true>
                    </div>
                </div>
                
                
                 <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>表名称</label>
                    <div class="controls col-sm-10"> 
                      <select class="form-control" name="tableName" id="tableName"  value="${obj.tableName}"  onchange="show_table_info(this);">               	 
                      </select>
                    </div>
                    
                </div>
                <div class="form-group" styel="">
			   		 
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>表说明</label>
                    <div class="controls col-sm-10">
                      <input type="text" class="form-control" name= "tableNode" id="tableNode" value="${obj.tableNode}"  placeholder="表说明">
                    </div>
                </div>
                
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>主键字段名称</label>
                    <div class="controls col-sm-10">
                      <input type="text" class="form-control" name= "pkName" id="pkName" value="${obj.pkName}"  placeholder="主键字段名称">
                    </div>
                     
                </div>
                
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>主键字段序列</label>
                    <div class="controls col-sm-10">
                      <input type="text" class="form-control" name= "seqstr" id="seqstr" value="${obj.seqstr}"  placeholder="主键字段序列说明">
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
 	<script src="<c:url value='/javaScript/sys/systable/editTable.js?v=14'/>"></script>