<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>牙医商城平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%> 
 	<style>
 	#div_items {  
            position: relative;  
            width: 100%;  
            height: 200px;  
            border: 1px solid #e5e6e7;  
            border-top: 0px;  
            overflow: auto;  
            display: none;  
        }
  
        .div_item {  
            width: 100%;  
            height: 20px;  
            margin-top: 1px;  
            font-size: 14px;  
            line-height: 25px;  
            padding-left:12px;
            cursor:pointer;
        } 
	 
  		#win_edit_toolbar{
  		float: right;
  		}
 	</style>
  </head> 
  
<body   class="gray-bg">
<form method="get" id="accountForm" class="form-horizontal">
 <div class="wrapper wrapper-content animated fadeInRight"> 
        <div class="row">
            <div class="col-sm-12">
             
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5 id="_title">物料信息 </h5> 
                    </div>
                   
                    <div class="ibox-content">
                        	 
                        	<!-- 隐藏域begin -->
                        	
							<div id="win_form_edithidden">
							</div>
							<!--隐藏区域end -->
                             <!-- 正文部分begin -->
							<div class="panel-body" >
								<div id="win_form_body">
								</div>
								<div class="form-group" style="margin-bottom:0px;">
								<label class="col-sm-2 control-label">物料类别：</label>
								<div class="col-sm-4">
									<input id="labelInfo" name="labelInfo" class="form-control" placeholder="物料类别" type="text" onfocus="intiItems()" onkeyup="intiItems()" onblur="hideItems()" aria-required="false" aria-invalid="false">
									<div id="div_items" onmouseover="blurItem()" onmouseout="noBlurItem()">  
					                </div>  
								</div>
								<label class="col-sm-2 control-label">状态<span class="text-danger">(*)</span>：</label>
								<div class="col-sm-4">
									<select type="select" class="form-control" name="state" id="state">
										<option value="">选择状态</option>
										<option value="1">启用</option>
										<option value="2">禁用</option>
									</select>
								</div>
							</div>
								<div class="hr-line-dashed" style="margin:5px 0px;"></div>
								<div class="form-group" style="margin-bottom:0px;">
									<label class="col-sm-2 control-label">物料图标：</label>
									<div class="col-sm-3">
										<div class="awards-imgs">
											<ul class="imgul f-cb" >
												<li class="one">
													<div id="lessenFileDiv">
										            </div>
									            </li>
								            </ul>
								          </div>
									</div>
								</div>
								
								<div class="hr-line-dashed" style="margin:5px 0px;"></div>
								<div class="ibox float-e-margins" style="overflow:hidden;margin-top:5px">
				                  <!-- 标题栏区域 begin-->
				                    <div class="ibox-title" style="border:0px">
				                    	<!-- 标题文字区域begin -->
				                        <h5>&nbsp&nbsp物料图片</h5> 
				                        <!-- 标题文字区域end -->   
				                    </div>
				                    <div class="ibox-content">
									   	<div class="form-group" styel="">
									   		<div class="awards-imgs">
									            <ul class="imgul f-cb" id="imglist">
									            </ul>
									         </div>
									   	</div>
								   	</div>
					            </div>
								<div class="ibox float-e-margins" style="border:1px solid #e7eaec;margin-top:5px">
					                  <!--标题栏区域 begin-->
					                     <div class="ibox-title" style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:45px;margin-bottom:10px">
					                    	<!-- 标题文字区域begin -->
					                        <h5>&nbsp&nbsp物料型号列表</h5> 
					                        <!-- 标题文字区域end -->
					                        <div class="ibox-tools">
					                        	<button type="button" class="btn btn-success btn-xs" id="addFor" style="display:none"  onclick="addFormat();"><i class="fa fa-edit"></i>增加</button>
											</div> 
					                    </div>
					                   <div id="gridDiv">
					                    	<table id="datagrid1" class="mmg" ></table>
					                    </div>
				                    </div>
							</div>
								
							<!-- 正文部分end -->
                        
                    </div>
                    <!-- 工具栏部分begin -->
                    
			<div class="panel-footer">
				<div class="form-group">
                                <div class="col-sm-4 col-sm-offset-2" id="win_edit_toolbar"> 
                                  
                                </div>
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

<script src="<c:url value='/javaScript/dentistmall/mall/updateMyMateriel.js?v=14'/>"></script>