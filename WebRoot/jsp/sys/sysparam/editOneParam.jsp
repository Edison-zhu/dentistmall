<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>青浦GIS系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/sys/sysparam/editOneParam.js' />"></script>
  </head> 
<body style="overflow:hidden;">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		<!-- 隐藏域begin -->
		<input type="hidden" name="sparId" id="sparId" value="${obj.sparId}" />
		<input type="hidden" name="relevantId" id="relevantId" value="${obj.relevantId}" />
		<input type="hidden" name="sysSparId" id="sysSparId" value="${obj.sysSparId}" />
		<input type="hidden" name="createRen" id="createRen" value="${obj.createRen}" />
		<input type="hidden" name="paramOrderNumber" id="paramOrderNumber" value="${obj.paramOrderNumber}" />
		<input type="hidden" name="createDate" value="<fmt:formatDate value="${obj.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
		<!--隐藏区域end -->
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo">
				<div class="ibox float-e-margins" style="height:240px;border:1px solid #e7eaec">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title" style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:38px;margin-bottom:10px">
                    	<!-- 标题文字区域begin -->
                        <h5>&nbsp&nbsp基本信息</h5> 
                        <!-- 标题文字区域end -->   
                    </div>
				   	<div class="form-group" styel="">
				   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>大类编号</label>
	                    <div class="controls col-sm-3">
	                      <input type="text" class="form-control" name= "paramCode" id="paramCode" value="${obj.paramCode}"  >
	                    </div>
	                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>大类名称</label>
	                    <div class="controls col-sm-3">
	                      <input type="text" class="form-control" name= "paramName" id="paramName" value="${obj.paramName}"  placeholder="大类名称">
	                    </div>
	                </div>
	                <div class="form-group" styel="">
				   		<label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>大类图标</label>
	                    <div class="controls col-sm-3">
	                      <div class="position-rel">
                        	<div class="position-rel">
                            <input type="text" name= "paramIcon" id="paramIcon" value="${obj.paramIcon}"   class="form-control" placeholder="大类图标"/>
                            <div class="bank_xljt"><img class="jt_xz" width="20" src="<c:url value='/img/jtx.png' />" alt=""/></div>
                            <div id="chose_bank" class="bank_xljt1"></div>
                           </div>
	                        <div class="bank_xl" style="display: none;height:200px;overflow:auto">
	                            <ul id="iconUl">
	                            </ul>
	                        </div>
                    		</div>
	                    </div>
	                    <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>是否启动加载</label>
	                    <div class="controls col-sm-3">
	                    			 <select id="ifLoad" class="form-control" name="ifLoad"> 
	                    			         <option value ="" 	>选择是否启动加载</option>
	                    			 		<option value ="1" 	<c:if test="${obj.ifLoad==1}"> selected = "selected" </c:if>>启动</option>
	                    			 		<option value ="2"  <c:if test="${obj.ifLoad==2}"> selected = "selected" </c:if>  >不启动</option>
	                    			 </select>
	                    </div>
	                    
	                </div>
	                <div class="form-group" styel="">
				   		<label class="control-label col-sm-2">大类说明</label>
	                    <div class="controls col-sm-8">
	                      <textarea name= "paramNode" style="height:80px;width:600px" id="paramNode" placeholder="大类说明（200字以内）">${obj.paramNode}</textarea>
	                    </div>
	                </div>
	            </div>
	            <div class="ibox float-e-margins" style="height:100px;border:1px solid #e7eaec">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title" style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:38px;margin-bottom:10px">
                    	<!-- 标题文字区域begin -->
                        <h5>&nbsp&nbsp基础字段</h5> 
                        <!-- 标题文字区域end -->   
                    </div>
				   	<div class="form-group">
	                    <div class="controls col-sm-2"  style="margin-left:40px;">
	                      <input type="text" class="form-control" value="编码编号" disabled="disabled">
	                    </div>
	                    <div class="controls col-sm-2">
	                      <input type="text" class="form-control" value="编码名称" disabled="disabled">
	                    </div>
	                    <div class="controls col-sm-2">
	                      <input type="text" class="form-control" value="编码值" disabled="disabled">
	                    </div>
	                    <div class="controls col-sm-2">
	                      <input type="text" class="form-control" value="创建时间" disabled="disabled">
	                    </div>
	                </div>
	            </div>
	            <!-- 选项字段设置开始 -->
	            <div class="ibox float-e-margins" style="border:1px solid #e7eaec">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title" style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:38px;">
                    	<!-- 标题文字区域begin -->
                        <h5>&nbsp&nbsp选项字段设置</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">                         
						  <a class="collapse-link" id="showSel" onclick="controlSelShow('1')">
                                <i class="fa fa-chevron-down"></i>
                          </a>
                          <a class="collapse-link" id="hideSel" style="display:none"  onclick="controlSelShow('0')">
                                <i class="fa fa-chevron-up"></i>
                          </a>
                        </div>
                        <!-- 标题栏工具区域end -->  
                    </div>
				   	<div class="form-group selShow" style="display:none;margin-top:10px;padding-left:40px">
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr01Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项内容</label>
			                    <div class="controls col-sm-8">
			                      <textarea style="height:80px;width:180px" id="paramStr01Content"></textarea>
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr01Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="selSet" value="paramStr01">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr02Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项内容</label>
			                    <div class="controls col-sm-8">
			                      <textarea style="height:80px;width:180px" id="paramStr02Content"></textarea>
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr02Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="selSet" value="paramStr02">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr03Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项内容</label>
			                    <div class="controls col-sm-8">
			                      <textarea style="height:80px;width:180px" id="paramStr03Content"></textarea>
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr03Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="selSet" value="paramStr03">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr04Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项内容</label>
			                    <div class="controls col-sm-8">
			                      <textarea style="height:80px;width:180px" id="paramStr04Content"></textarea>
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr04Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="selSet" value="paramStr04">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr05Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项内容</label>
			                    <div class="controls col-sm-8">
			                      <textarea style="height:80px;width:180px" id="paramStr05Content"></textarea>
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr05Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="selSet" value="paramStr05">
	                    </div>
	                </div>
	            </div>
	            <!-- 选项字段设置结束 -->
	            <!-- 文本字段设置开始 -->
	            <div class="ibox float-e-margins" style="border:1px solid #e7eaec">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title" style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:38px;">
                    	<!-- 标题文字区域begin -->
                        <h5>&nbsp&nbsp文本字段设置</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">                         
						  <a class="collapse-link" id="showText" onclick="controlTextShow('1')">
                                <i class="fa fa-chevron-down"></i>
                          </a>
                          <a class="collapse-link" id="hideText" style="display:none"  onclick="controlTextShow('0')">
                                <i class="fa fa-chevron-up"></i>
                          </a>
                        </div>
                        <!-- 标题栏工具区域end -->  
                    </div>
				   	<div class="form-group textShow" style="display:none;margin-top:10px;padding-left:40px">
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr06Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr06Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="textSet" value="paramStr06">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr07Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr07Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="textSet" value="paramStr07">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr08Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr08Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="textSet" value="paramStr08">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr09Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr09Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="textSet" value="paramStr09">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr10Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramStr10Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="textSet" value="paramStr10">
	                    </div>
	                </div>
	            </div>
	            <!-- 文本字段设置结束 -->
	            <!-- 时间字段设置开始 -->
	            <div class="ibox float-e-margins" style="border:1px solid #e7eaec">
                  <!-- 标题栏区域 begin-->
                    <div class="ibox-title" style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:38px;">
                    	<!-- 标题文字区域begin -->
                        <h5>&nbsp&nbsp时间字段设置</h5> 
                        <!-- 标题文字区域end -->   
                        <!-- 标题栏工具区域begin -->                                          
                        <div class="ibox-tools">                         
						  <a class="collapse-link" id="showTime" onclick="controlTimeShow('1')">
                                <i class="fa fa-chevron-down"></i>
                          </a>
                          <a class="collapse-link" id="hideTime" style="display:none"  onclick="controlTimeShow('0')">
                                <i class="fa fa-chevron-up"></i>
                          </a>
                        </div>
                        <!-- 标题栏工具区域end -->  
                    </div>
				   	<div class="form-group timeShow" style="display:none;margin-top:10px;padding-left:40px">
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt01Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">时间格式</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt01Content">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt01Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="timeSet" value="paramInt01">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt02Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">时间格式</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt02Content">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt02Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="timeSet" value="paramInt02">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt03Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">时间格式</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt03Content">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt03Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="timeSet" value="paramInt03">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt04Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">时间格式</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt04Content">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt04Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="timeSet" value="paramInt04">
	                    </div>
	                    <div class="controls col-sm-5"  style="padding-top:10px;margin:10px;border:1px solid #e7eaec">
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">选项名称</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt05Name">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">时间格式</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt05Content">
			                    </div>
	                    	</div>
	                    	<div class="form-group">
	                    		<label class="control-label col-sm-4">序号</label>
			                    <div class="controls col-sm-8">
			                      <input type="text" class="form-control" id="paramInt05Order">
			                    </div>
	                    	</div>
	                    	<input type="hidden" class="timeSet" value="paramInt05">
	                    </div>
	                </div>
	            </div>
	            <!-- 时间字段设置结束 -->
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
