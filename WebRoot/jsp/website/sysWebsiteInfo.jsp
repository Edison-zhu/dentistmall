<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>采购中心系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/website/sysWebsiteInfo.js?02'/>"></script>
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
		<input type="hidden" value="${obj.swiId}"  name="swiId" id="swiId" />
    	<input type="hidden" value="${obj.websitecode}"  name="websitecode" id="websitecode" />
    	<input type="hidden" id="backgroundPictureFileCode03" name="backgroundPictureFileCode03" value="${obj.backgroundPictureFileCode03}"/>
	    <input type="hidden" id="backgroundPictureFileCode02" name="backgroundPictureFileCode02" value="${obj.backgroundPictureFileCode02}"/>
	    <input type="hidden" id="backgroundPictureFileCode01" name="backgroundPictureFileCode01" value="${obj.backgroundPictureFileCode01}"/>
	    <input type="hidden" id="websitIconFilecode" name="websitIconFilecode" value="${obj.websitIconFilecode}"/>
		<!--隐藏区域end -->
		
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group">
	                    <label class="control-label col-sm-2">网站名称</label>
	                    <div class="controls col-sm-3">
	                        <input type="text" class="form-control" placeholder="必填项" value="${obj.websitName}"  name="websitName"   id="websitName" maxlength="64"  />
	                    </div>
	                         
	                         <label class="control-label col-sm-2">根目录地址</label>
	                    <div class="controls col-sm-4">
	                        <input type="text" class="form-control" placeholder="必填项" value="${obj.rootPath}"  name="rootPath" id="rootPath" maxlength="64"/>
	                    </div>
	                    
	                </div>
	                
	                 <div class="form-group">
	                     <label class="control-label col-sm-2">模板目录地址</label>
	                    <div class="controls col-sm-3">
	                        <input type="text" class="form-control" placeholder="模板目录地址" value="${obj.templateDirectory}"  name="templateDirectory" id="templateDirectory" maxlength="256" />
	                    </div>
	                        <label class="control-label col-sm-2">首页地址</label>
	                    <div class="controls col-sm-4">
	                        <input type="text" class="form-control" placeholder="必填项" value="${obj.indexPath}"  name="indexPath" id="indexPath"  maxlength="256" />
	                    </div>
	                    
	                </div>
	                
	                 <div class="form-group">
	                    <label class="control-label col-sm-2">网站图标</label>
	                    <div class="controls col-sm-3">
	                         <div id="uploadButton" value="${obj.websitIconFilecode}" ></div>
	                        
	                        
	                    </div>
	                    
	                      <label class="control-label col-sm-2">状态</label>
	                    <div class="controls col-sm-4">
	                           <input type="radio" name="state" value="1" class="icheck square" checked="checked"> &nbsp;启用&nbsp;&nbsp;&nbsp;
	                           <input type="radio" name="state" value="0" class="icheck square" <c:if test="${obj.state==0}">checked="checked"</c:if>>&nbsp;禁用
	                    </div>
	                </div>
	                
	                 <div class="form-group">
	                    <label class="control-label col-sm-2">背景图片</label>
	                    <div class="controls col-sm-9">
	                    
	                         <div id="bg_uploadButton01" value="${obj.backgroundPictureFileCode01}" ></div>
	                        <div id="bg_uploadButton02" value="${obj.backgroundPictureFileCode02}" ></div>
	                      
	                         <div id="bg_uploadButton03" value="${obj.backgroundPictureFileCode03}" ></div>
	                      
	                    </div>
	                </div>
	                
	                  <div class="form-group">
	                    <label class="control-label col-sm-2">版本信息</label>
	                    <div class="controls col-sm-9">
	                        <input type="text" class="form-control" placeholder="必填项" value="${obj.versionInformation}"  name="versionInformation"   id="versionInformation" maxlength="256" />
	                    </div>
	                </div>
	                
	                   <div class="form-group">
	                    <label class="control-label col-sm-2">网站说明</label>
	                    <div class="controls col-sm-9">
	                    	<textarea class="form-control" rows="4" placeholder="网站说明"  value="${obj.websitComment}"  name="websitComment" id="websitComment" >${obj.websitComment}</textarea>
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