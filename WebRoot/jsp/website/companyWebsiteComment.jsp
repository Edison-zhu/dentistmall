<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>采购中心系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/website/companyWebsiteComment.js?20'/>"></script>
  </head> 
  <style>
  #button1{
  			position:absolute;
  			left:480px;
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
	                    <label class="control-label col-sm-2">链接地址</label>
	                    <div class="controls col-sm-9">
	                        	<input type="text" class="form-control" placeholder="链接地址" value="${obj.linkUrl}"  name="linkUrl" id="linkUrl" maxlength="256" />
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
	            <div class="form-group" >
	            <label class="control-label col-sm-2">内容</label>
	              <div class="controls col-sm-9">
	                  <textarea class="form-control KindEditor"  placeholder="新闻内容" name="newsComment" id="newsComment"  style="height:400px">${obj.newsComment}</textarea>
	                   		<%-- 您当前输入了 <span id="word_count1">0</span> 个文字。（只能输入1000个字符以内的内容） --%>
	               </div>
	            </div>
			</div> 
		 <!-- 正文部分end -->
		 <!-- 工具栏部分begin -->
				<div class="panel-footer">
		           <div class="form-group">
			           <div class="col-sm-offset-2 col-sm-4">  
                                <button type="button"  onclick="save()" class="btn btn-primary" id="button1">保存</button>
			           </div>
			      </div>
       		</div>
       	<!-- 工具栏部分end -->	 
    </div> 
 </form>
 <!-- 表单部分end-->
</body>
</html>