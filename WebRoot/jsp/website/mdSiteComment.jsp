<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>采购中心系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/website/mdSiteComment.js?16' />"></script>
  </head> 
<body style="overflow:hidden;">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" name="mscId" id="mscId" value="" />
		<input type="hidden" name="swcId" id="swcId" value="" />
		<input type="hidden" name="commId" id="commId" value="" />
		<input type="hidden" name="mscType" id="mscType" value="" />
		<!--隐藏区域end -->
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo" id="formBody">
				<div class="form-group" styel="">
				   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span><lable id="lname"></lable></label>
	                    <div class="controls col-sm-8">
	                      <input type="text" id="commName" class="form-control" readonly name="commName"/>
	                    </div>
	                    <div class="controls col-sm-1">
	                      <a class="btn btn-danger btn-xs"  onclick="selComm()">选择</a>
	                    </div>
	                    
	                </div>
	                <div class="form-group" styel="">
	                	<label class="control-label col-sm-2">序号</label>
	                    <div class="controls col-sm-3">
	                      <input type="text" class="form-control" name= "serialComm" id="serialComm" value=""  placeholder="序号">
	                    </div>
				   		<label class="control-label col-sm-2">状态</label>
	                    <div class="controls col-sm-3">
	                        <input type="radio" name="commState" value="2" checked="checked" class="icheck square">&nbsp;草稿
		                    <input type="radio" name="commState" value="1" class="icheck square" > &nbsp;发布
		                            
	                    </div>
	                </div>
	                <div class="form-group" styel="">
				   		<label class="control-label col-sm-2">描述</label>
	                    <div class="controls col-sm-8">
	                       <textarea name= "content" style="height:150px" class="form-control"  id="content" placeholder="描述（200字以内）"></textarea>
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