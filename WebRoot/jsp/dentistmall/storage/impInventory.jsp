<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
  <head> 
   <title>采购中心系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
   <%@ include file="/jsp/link.jsp"%> 
 	<script src="<c:url value='/javaScript/dentistmall/storage/impInventory.js?v=08'/>"></script>
  </head> 
  
  <body style="overflow:hidden; ">
<!-- 表单部分begin -->
  <form id="accountForm" class="form-horizontal form-bordered" role="form">
		<div class="panel panel-default">
		 <!-- 正文部分begin -->
			<div class="panel-body scrollbarinfo" >
			   <div class="form-group" id="drmb">
			   		<label class="control-label col-sm-2">导入模板</label>
                    <div class="controls col-sm-6">
                      <a href="<c:url value='/fileInfo/updown/优牙库-仓库-库存导入.xlsx'/>" target="_blank">库存导入模板.xls</a>
                    </div>
                </div>
                <div class="form-group" id="drwj">
                	<label class="control-label col-sm-2">导入文件</label>
                    <div class="controls col-sm-6">
                      <div id="upButton" value="" ></div>
                       <input type="hidden" id="fileCode" name="fileCode" value=""/>
                    </div>
                    <div class="controls col-sm-6" style="padding-top: 20px;color: red">
                        <span id="showMessage" style="display: none">

                        </span>
                    </div>
                </div>
                <div class="form-group" id="allImp" style="display:none">
                    <div class="controls col-sm-12">
                    	数据导入成功！耗时<lable id="timeCount1"></lable>秒。
                    </div>
                </div>
                <div class="form-group" id="sectionImp" style="display:none">
                    <div class="controls col-sm-12">
                    	部分数据导入成功！其中成功<lable id="rightCount"></lable>条，失败<lable id="errorCount"></lable>条。耗时<lable id="timeCount2"></lable>秒。
                    </div>
                </div>
			</div> 
		 <!-- 正文部分end -->
		 <!-- 工具栏部分begin -->
				<div class="panel-footer">
		           <div class="form-group">
			           <div class="col-sm-offset-2 col-sm-4">  
                                <button type="button"  onclick="save()" class="btn btn-primary" id="impBu">导入</button>
                                <button type="button"  onclick="cxImp()" class="btn btn-primary" id="cxBu" style="display:none">重新导入</button>
                                <button type="button"  onclick="expError()" class="btn btn-success" id="xzError" style="display:none">下载错误文件</button>
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