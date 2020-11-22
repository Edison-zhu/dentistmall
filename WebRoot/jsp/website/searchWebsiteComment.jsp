<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>采购中心系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/website/searchWebsiteComment.js?22'/>"></script>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" value="${obj.swmId}"  name="swmId" id="swmId" />
		<input type="hidden" value="${obj.swcId}"  name="swcId" id="swcId" />
		<input type="hidden" value="${obj.newscode}"  name="newscode" id="newscode" />
		<input type="hidden" name="createDatetime" id="createDatetime" value="<fmt:formatDate value="${obj.createDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
		<!--隐藏区域end -->
		
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
			   <div class="form-group">
			             <label class="control-label col-sm-2">标题</label>
			             <div class="controls col-sm-9">
							  <input type="text" class="form-control" placeholder="必填项" value="${obj.newsTitle}"  name="newsTitle" id="newsTitle"  maxlength="64" />
			             </div>
		        </div>
	            <div class="form-group">
	                     <label class="control-label col-sm-2">序号</label>
	                     <div class="controls col-sm-4">
	                        	<input type="text" class="form-control" placeholder="序号" name="serialNumber" value="${obj.serialNumber}" id="serialNumber"/>
	                     </div>
	                     <label class="control-label col-sm-2">状态</label>
	                    <div class="controls col-sm-4">
	                        <input type="radio" name="state" value="2" checked="checked" class="icheck square">&nbsp;草稿
		                    <input type="radio" name="state" value="1" <c:if test="${obj.state==1}">checked="checked"</c:if> class="icheck square" > &nbsp;发布
		                            
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