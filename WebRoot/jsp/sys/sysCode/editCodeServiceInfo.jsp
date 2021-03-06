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
                      <input type="text" class="form-control" name= "sysClassInfo.SClassName" id="SClassName" value="${obj.SClassName}"    readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>类说明</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassComment" id="SClassComment" value="${obj.SClassComment}"  placeholder="类说明" >
                    </div>
                </div>
                
                
                  <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>业务名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.servicename" id="servicename"  value="${obj.servicename}" placeholder="业务名称" >
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>表名称</label>
                    <div class="controls col-sm-3">
                      <select class="form-control" name="sysClassInfo.tableName" id="tableName" onchange="show_table_info(this);"> 
                      </select>
                    </div>
                </div>  
                <div class="form-group" styel="">
			   		  <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>类包名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.packagename" id="packagename" value="${obj.packagename}"  readonly=true>
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
                      <input type="text" class="form-control" name= "sysClassInfo.savepath" id="savepath" value="${obj.savepath}" readonly=true>
                    </div>                   
                </div> 
                
                <div class="hr-line-dashed" style="margin-bottom: 2px;    margin-top: 2px;"></div>
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>接口类名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassName2" id="SClassName2" value="${obj.SClassName2}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>接口类说明</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassComment2" id="SClassComment2" value="${obj.SClassComment2}"  placeholder="接口类说明" >
                    </div>
                </div>
                
                <div class="form-group" styel="">
			   		  <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>接口类包名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.packagename2" id="packagename2" value="${obj.packagename2}" readonly=true>
                    </div> 
                     <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>接口类ftl文件</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.ftlname2" id="ftlname2" value="${obj.ftlname2}"  placeholder="接口类ftl文件">
                    </div> 
                </div>
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2">接口类编号</label>
                    <div class="controls col-sm-3">
                        <input type="text" class="form-control" name= "sysClassInfo.SClassCode2" id="SClassCode2" value="${obj.SClassCode2}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>接口类存储路径</label>
                    <div class="controls col-sm-3">
                       <input type="text" class="form-control" name= "sysClassInfo.savepath2" id="savepath2" value="${obj.savepath2}" readonly=true>
                    </div>                   
                </div> 
                <div class="hr-line-dashed" style="margin-bottom: 2px;    margin-top: 2px;"></div>
                
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>持久化对象类名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassName3" id="SClassName3" value="${obj.SClassName3}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>持久化对象类说明</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassComment3" id="SClassComment3" value="${obj.SClassComment3}"  placeholder="持久化对象类说明" >
                    </div>
                </div>
                
                  <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>持久化对象类包名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.packagename3" id="packagename3" value="${obj.packagename3}" readonly=true>
                    </div>
                   
                </div>           
                
                 <div class="hr-line-dashed" style="margin-bottom: 2px;    margin-top: 2px;"></div>                
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>Dao类接口名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassName4" id="SClassName4" value="${obj.SClassName4}" readonly=true>
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>Dao类接口类说明</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.SClassComment4" id="SClassComment4" value="${obj.SClassComment4}"  placeholder="Dao类接口类说明" >
                    </div>
                </div>
                
                  <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>Dao类接口类包名</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sysClassInfo.packagename4" id="packagename4" value="${obj.packagename4}" readonly=true>
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
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>系统类型</label>
                    <div class="controls col-sm-3">
                      <select class="form-control" name="sysClassInfo.SClassType" id="SClassType" readonly=true> 
                      	<option value="pojo" <c:if test="${obj.SClassType =='pojo'}"> selected</c:if> >pojo</option>
                      	<option value="dao" <c:if test="${obj.SClassType =='dao'}"> selected </c:if> >dao</option>
                      	<option value="service" <c:if test="${obj.SClassType =='service'}"> selected </c:if> >service</option> 
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
<script src="<c:url value='/javaScript/sys/syscode/editCodeServiceInfo.js?v=11'/>"></script>