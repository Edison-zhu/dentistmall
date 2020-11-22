<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>牙医商城平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%> 
 	<script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
  </head>  
    <style>
  		#win_edit_toolbar{
  		float: right;
  		}
  		}
	</style>
	<body   class="gray-bg">
		<input type="hidden" id="oldId" value="${sessionScope.sessionUser.oldId}">
		<form method="get" id="accountForm" class="form-horizontal">
		 <div class="wrapper wrapper-content animated fadeInRight"> 
		        <div class="row">
		            <div class="col-sm-12">
		                <div class="ibox float-e-margins">
		                    <!-- 标题部分begin -->
		                    <div class="ibox-title">
		                        <h5 id="_title">供应商信息</h5> 
		                    </div>
		                    <!-- 标题部分end -->
		                    <!-- 内容部分begin -->
		                    <div class="ibox-content">    
		                        	<!-- 隐藏域begin -->
									<div id="win_form_edithidden"></div>
									<!--隐藏区域end -->
		                             <!-- 正文部分begin -->
									<div class="panel-body" >
										<div id="win_form_body"></div>						  
									</div>
									<div class="hr-line-dashed" style="margin:5px 0px;"></div>
										<div class="form-group" style="margin-bottom:0px;">
											<label class="col-sm-2 control-label">企业简介：</label>
											<div class="col-sm-10">
												<textarea name= "scopeBusiness" id="scopeBusiness" style="height:150px" class="form-control"  id="menuComment" placeholder="企业简介（500字以内）"></textarea>
											</div>
										</div>
										<div class="hr-line-dashed" style="margin:5px 0px;"></div>
										<div class="form-group" style="margin-bottom:0px;">
											<label class="col-sm-2 control-label">企业图标<span class="text-danger">(*)</span>：</label>
											<div class="col-sm-3">
												<div class="awards-imgs">
													<ul class="imgul f-cb" >
													<li class="one">
														<div id="logoFileDiv">
														</div>
													</li>
													</ul>
												</div>
											</div>
										</div>
									<!-- 正文部分end --> 
		                    </div>
		                     <!-- 内容部分end -->
		                    <!-- 工具栏部分begin -->
							<div class="panel-footer">
								<div class="form-group">
				                      <div class="col-sm-4 col-sm-offset-2" id="win_edit_toolbar"></div>
				                </div>
							</div>
							<!-- 工具栏部分end --> 
		                </div>
		            </div>
		        </div>
		 </div>
		</form>
	</body> 
</html>

<script src="<c:url value='/javaScript/dentistmall/transaction/mySupplier.js?v=120'/>"></script>