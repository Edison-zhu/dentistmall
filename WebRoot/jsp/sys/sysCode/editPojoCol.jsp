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
		<input type="hidden" name="sccId" id="sccId" value="${obj.sccId}" />
		<input type="hidden" name="sccId" id="sciId" value="${obj.sciId}" /> 
		
		<!--隐藏区域end -->
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			    
			    <div class="form-group" styel="">
			   		<label class="control-label col-sm-2">字段编码</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "fieldCode" id="fieldCode" value="${obj.fieldCode}" readonly=true>
                    </div> 
                    	<label class="control-label col-sm-2">类属性编码</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "sccCode" id="sccCode" value="${obj.sccCode}" readonly=true>
                    </div> 
                    
                </div>
                
                
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>入参描述</label>
                    <div class="controls col-sm-10">                    
                       <input type="text" class="form-control" name= "inParamComment" id="inParamComment" value="${obj.inParamComment}"  placeholder="入参描述" >                    
                    </div> 
                                    
                </div>
                  <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>入参字符串</label>
                    <div class="controls col-sm-10">                    
                       <input type="text" class="form-control" name= "inParamStr" id="inParamStr" value="${obj.inParamStr}"  placeholder="入参字符串" >                    
                    </div> 
                                    
                </div>
                
                
                
                <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>出参描述</label>
                    <div class="controls col-sm-10">                    
                       <input type="text" class="form-control" name= "outParamComment" id="outParamComment" value="${obj.outParamComment}"  placeholder="出参描述" >                    
                    </div> 
                                    
                </div>
                  <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>出参字符串</label>
                    <div class="controls col-sm-10">                    
                       <input type="text" class="form-control" name= "outParamStr" id="outParamStr" value="${obj.outParamStr}"  placeholder="出参字符串" >                    
                    </div> 
                                    
                </div>
			    
			    
			          <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>属性名称</label>
                    <div class="controls col-sm-10">                    
                       <input type="text" class="form-control" name= "sccName" id="sccName" value="${obj.sccName}"  placeholder="属性名称" >                    
                    </div> 
                                    
                </div>
			    
                <div class="form-group" styel="">
			   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>字段名称</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "fieldName" id="fieldName" value="${obj.fieldName}" placeholder="字段名称" >
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>端口号</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "fieldNode" id="fieldNode" value="${obj.fieldNode}"  placeholder="字段说明">
                    </div>
                </div>
                
                
                 <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>整数位</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "fieldLength" id="fieldLength" value="${obj.fieldLength}"  placeholder="整数位" >
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>小数位</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "systemService" id="fieldPrecision" value="${obj.fieldPrecision}" placeholder="小数位">
                    </div>                   
                </div> 
                
                 <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>默认值</label>
                    <div class="controls col-sm-3">
                      <input type="text" class="form-control" name= "defaultValue" id="defaultValue" value="${obj.defaultValue}"  placeholder="默认值" >
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>数据类型</label>
                    <div class="controls col-sm-3"> 
                      <select class="form-control" name="dataType" id="dataType">
                      	<option value="">请选择</option>
                      	<option value="int" <c:if test="${obj.dataType =='int'}"> selected</c:if> >数值类型</option>
                      	<option value="string" <c:if test="${obj.dataType =='string'}"> selected </c:if> >字符串类型</option>
                      	<option value="datetime" <c:if test="${obj.dataType =='datetime'}"> selected </c:if> >日期类型</option> 
                      </select>                 
                    </div>                   
                </div>
                
                 <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>是否为空</label>
                    <div class="controls col-sm-3">                    
                      <select class="form-control" name="ifNull" id="ifNull">
                      	<option value="">请选择</option>
                      	<option value="1" <c:if test="${obj.ifNull =='1'}"> selected</c:if> >是</option>
                      	<option value="2" <c:if test="${obj.ifNull =='2'}"> selected </c:if> >否</option>                      	 
                      </select>                      
                    </div>
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>是否主键</label>
                    <div class="controls col-sm-3"> 
                       <select class="form-control" name="ifPk" id="ifPk">
                      	<option value="">请选择</option>
                      	<option value="1" <c:if test="${obj.ifPk =='1'}"> selected</c:if> >是</option>
                      	<option value="2" <c:if test="${obj.ifPk =='2'}"> selected </c:if> >否</option>                      	 
                      </select>                   
                    </div>   
                    
                    
                       
                 <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>是否为无关字段</label>
                    <div class="controls col-sm-3">                    
                      <select class="form-control" name="ifTransient" id="ifTransient">
                      	<option value="">请选择</option>
                      	<option value="1" <c:if test="${obj.ifTransient =='1'}"> selected</c:if> >是</option>
                      	<option value="2" <c:if test="${obj.ifTransient =='2'}"> selected </c:if> >否</option>                      	 
                      </select>                      
                    </div> 
                                    
                </div>
                
                
                
                 <div class="form-group" styel="">
                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>sql字段</label>
                    <div class="controls col-sm-10">                    
                       <input type="text" class="form-control" name= "formula" id="formula" value="${obj.formula}"  placeholder="sql字段" >                    
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

 	<script src="<c:url value='/javaScript/sys/syscode/editPojoCol.js?v=10'/>"></script>