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
		<input type="hidden" name="sysClassInfo.sciId" id="sciId" value="${obj.sciId}" />	
		<input type="hidden"   id="tableNameTemp" value="${obj.tableName}" />	
		<input type="hidden"   id="dbType" name="sysClassInfo.dbType" value="${obj.dbType}" />	 
		<input type="hidden"   id="seqstr" name="sysClassInfo.seqstr" value="${obj.seqstr}" />	 
		<input type="hidden"   id="pkName" name="sysClassInfo.pkName" value="${obj.pkName}" />	  
		<input type="hidden"   id="ftlpath" name="sysClassInfo.ftlpath" value="${obj.ftlpath}" />	 
		 
		<!--隐藏区域end -->
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>系统编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.sysCode" id="sysCode" value="${obj.sysCode}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>类编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassCode" id="SClassCode" value="${obj.SClassCode}" readonly=true>
                    </div>
                </div>
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>类名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassName" id="SClassName" value="${obj.SClassName}" placeholder="类名称" >
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>类说明</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassComment" id="SClassComment" value="${obj.SClassComment}"  placeholder="类说明" >
                    </div>
                </div>
                   <div class="form-group" styel="">
                    <label class="control-label col-sm-2"> 作者</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.authortext" id="authortext" value="${obj.authortext}"  placeholder="作者" >
                    </div>
                    <label class="control-label col-sm-2"> 版本信息</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.versiontext" id="versiontext" value="${obj.versiontext}" placeholder="版本信息">
                    </div> 
                </div>
                <div class="form-group" styel="">
			   		  <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>类包名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.packagename" id="packagename" value="${obj.packagename}"  placeholder="类包名">
                    </div> 
                     <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>ftl文件</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.ftlname" id="ftlname" value="${obj.ftlname}"  placeholder="ftl文件">
                    </div> 
                </div>
                
                  <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>项目路径</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.projectpath" id="projectpath" value="${obj.projectpath}"  placeholder="项目路径" >
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>存储路径</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.savepath" id="savepath" value="${obj.savepath}" placeholder="存储路径">
                    </div>
                   
                </div> 
  
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>系统类型</label>
                    <div class="controls col-sm-3">
                      <select class="form-control" name="sysClassInfo.SClassType" id="SClassType" readonly=true> 
                      	<option value="pojo" <c:if test="${obj.SClassType =='pojo'}"> selected</c:if> >pojo</option>
                      	<option value="dao" <c:if test="${obj.SClassType =='dao'}"> selected </c:if> >dao</option>
                      	<option value="service" <c:if test="${obj.SClassType =='service'}"> selected </c:if> >service</option> 
                      </select>
                    </div>
                    <label class="control-label col-sm-2">表名称</label>
                    <div class="controls col-sm-3">
                      <select class="form-control" name="sysClassInfo.tableName" id="tableName" onchange="show_table_info(this);"> 
                      </select>
                    </div>
                </div>                
                
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>表编号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.tableCode" id="tableCode" value="${obj.tableCode}"   readonly=true> 
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>表说明</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.tableNode" id="tableNode" value="${obj.tableNode}" readonly=true> 
                    </div>                   
                </div>      
                <div class="hr-line-dashed" style="margin-bottom: 2px;    margin-top: 2px;"></div>
                 <!--
                  <div class="form-group" style="margin-bottom: 5px; margin-top: 5px;">
                      <div class="col-sm-offset-9 col-sm-3">
                        <button type="button"  onclick="initCodeList()" class="btn btn-primary btn-xs">初始化</button> 	           
                        <button type="button"  onclick="addOrUpdateClassColumns()" class="btn btn-primary btn-xs">添加</button> 
                         <button type="button"  onclick="deleteClassColumnsList()" class="btn btn-primary btn-xs">删除</button> 	  
			           </div>                                    
                </div>
                 -->
                <div class="form-group" styel="">
                    
                    <div class="controls col-sm-12">
                      <!--表格区域begin--> 
					                     <table id="datagrid1" class="mmg" ></table>
								         <div id="pg" style="text-align: right;"></div> 
						 <!--表格区域end--> 
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
<script src="<c:url value='/javaScript/sys/syscode/editCodePojoInfo.js?v=12'/>"></script>