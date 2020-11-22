<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>采购中心系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/website/sysWebsiteColumnsInfo.js?04'/>"></script>
  </head> 
 <style>
  #button1{
  			position:absolute;
  			left:440px;
  			width:95px;
  			vertical-align:middle;
  			align-items:center;
  		}
  #button2{
  			position:relative;
  			left:540px;
  			width:95px;
  			vertical-align:middle;
  			align-items:center;
  		}
  </style>
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden"  id="swcId" name="swcId"  value="${obj.swcId}"  />
		<input type="hidden"  id="columnscode" name="columnscode"  value="${obj.columnscode}"  />
		<input type="hidden"  id="swiId" name="swiId"  value="${obj.swiId }">
		<input type="hidden"  id="sysSwcId" name="sysSwcId"  value="${obj.sysSwcId}"  />
		<input type="hidden" id="linkUrl" name="linkUrl" value="${obj.linkUrl}">
		<input type="hidden" id="columnIconFileCode" name="columnIconFileCode" value="${obj.columnIconFileCode}">
		<input type="hidden" id="columnPictureFilecode01" name="columnPictureFilecode01" value="${obj.columnPictureFilecode01}">
	    <input type="hidden" id="columnPictureFilecode02" name="columnPictureFilecode02" value="${obj.columnPictureFilecode02}">
	    <input type="hidden" id="columnPictureFilecode03" name="columnPictureFilecode03" value="${obj.columnPictureFilecode03}">
		<!--隐藏区域end -->
		
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group">
	                    <label class="control-label col-sm-3">栏目名称</label>
	                    <div class="controls col-sm-7">
	                        <input type="text" class="form-control" placeholder="必填项" value="${obj.columnName}" id="columnName"  name="columnName"  maxlength="32" />
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-sm-3">栏目简介</label>
	                    <div class="controls col-sm-7">
	                        <input type="text" class="form-control" placeholder="栏目简介" value="${obj.templateName}" id="templateName" name="templateName" maxlength="32"/>
	                    </div>
	                </div>
	                
	                <div class="form-group">
	                	<label class="control-label col-sm-3">栏目类别</label>
	                    <div class="controls col-sm-7" style="padding-top:8px">
	                    	<input type="radio" name="mszType" value="1" checked=true/> 普通栏目&nbsp;&nbsp;
	                      	<input type="radio" name="mszType" value="2" <c:if test="${obj.mszType=='2'}"> checked=true </c:if>/> 商品栏目&nbsp;&nbsp;
	                      	<input type="radio" name="mszType" value="3" <c:if test="${obj.mszType=='3'}"> checked=true </c:if>/> 供应商栏目&nbsp;&nbsp;
	                      	<input type="radio" name="mszType" value="4" <c:if test="${obj.mszType=='4'}"> checked=true </c:if>/> 商品类别栏目&nbsp;&nbsp;
	                      	<input type="radio" name="mszType" value="5" <c:if test="${obj.mszType=='5'}"> checked=true </c:if>/> 图片栏目&nbsp;&nbsp;
	                      	<input type="radio" name="mszType" value="6" <c:if test="${obj.mszType=='6'}"> checked=true </c:if>/> 文字栏目&nbsp;&nbsp;
                      
	                    </div>
	                </div>
	                
	                <div class="form-group">
	                    <label class="control-label col-sm-3">更多地址</label>
	                    <div class="controls col-sm-7">
	                        <input type="text" class="form-control" placeholder="更多地址" value="${obj.linkUrl}" id="linkUrl" name="linkUrl" />
	                    </div>
	                </div>
	                <div class="form-group">
	                  <label class="control-label col-sm-3">显示数量</label>
	                    <div class="controls col-sm-3">
	                        <input type="text" class="form-control" placeholder="请输入数字" value="${obj.showNumber}" id="showNumber" name="showNumber" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" />
	                    </div>
	                   <label class="control-label col-sm-3">是否更多</label>
	                    <div class="controls col-sm-3">
	                         <select id="isMore" name="isMore" class="form-control">
	                         	<option></option>
	                         	<option value="1" <c:if test="${obj.isMore=='1'}"> selected </c:if>>是</option>
	                         	<option value="2" <c:if test="${obj.isMore=='2'}"> selected </c:if>>否</option>
	                         </select>
	                    </div>
	                </div>
	                <div class="form-group">
	                  <label class="control-label col-sm-3">显示顺序</label>
	                    <div class="controls col-sm-3">
	                        <input type="text" class="form-control" placeholder="请输入数字" value="${obj.serialNumber}" id="serialNumber" name="serialNumber" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/>
	                    </div>
	                   <label class="control-label col-sm-3">状态</label>
	                    <div class="controls col-sm-3">
	                         <select id="state" name="state" class="form-control">
	                         	<option></option>
	                         	<option value="1" <c:if test="${obj.state=='1'}"> selected </c:if>>启用</option>
	                         	<option value="2" <c:if test="${obj.state=='2'}"> selected </c:if>>禁用</option>
	                         </select>
	                    </div>
	                </div>
	                <div class="form-group">
	                  
	                   <label class="control-label col-sm-3">栏目图标</label>
	                    <div class="controls col-sm-3">
	                         <div id="uploadButton" value="${obj.columnIconFileCode}" ></div>
	                    </div>
	                </div>
	                
	                <div class="form-group">
	                    <label class="control-label col-sm-3">栏目图片</label>
	                    <div class="controls col-sm-7">
	                    
	                         <div id="lm_uploadButton01" value="${obj.columnPictureFilecode01}" ></div>
	                         
	                         <div id="lm_uploadButton02" value="${obj.columnPictureFilecode02}" ></div>
	                         
	                         <div id="lm_uploadButton03" value="${obj.columnPictureFilecode03}" ></div>
	                         
	                    </div>
	                    
	                </div>
			</div> 
		 <!-- 正文部分end -->
		 <!-- 工具栏部分begin -->
				<div class="panel-footer">
		           <div class="form-group">
			           <div class="col-sm-offset-2 col-sm-4">  
                                <button type="button"  onclick="save()" class="btn btn-primary" id="button1">保存</button>
			                    <button type="button"  onclick="closeWin()" class="btn btn-default" id="button2">关闭</button> 
			           </div>
			      </div>
       		</div>
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
 <!-- 表单部分end-->
</body>
</html>