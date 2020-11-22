<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>牙医商城平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>  
 	<link rel="stylesheet" href="<c:url value='/css/shopping/css/font/font.css?02' />">
 	<link rel="stylesheet" href="<c:url value='/css/shopping/css/productInfo.css?16' />">
  </head> 
	<body style="overflow:hidden; ">
	<!-- 表单部分begin -->
		<form id="accountForm" class="form-horizontal form-bordered"
			role="form">
			<div class="panel panel-default" >
				<!-- 隐藏域begin -->
				<div id="win_form_edithidden"></div>
				<!--隐藏区域end -->
				<!-- 正文部分begin -->
				<div class="panel-body scrollbarinfo"  id="win_form_body">
					<div   style="padding-left: 15px;padding-right: 15px;">
                                        <label id="mmfName"></label>
                                        <b id="mmPrice" style="color:#e64419"></b>
                    </div>
                    
                    <div style="padding-left: 15px;padding-right: 15px;" class="product-info-format" id="gg_list">
                         
                    </div>
					<div class="hr-line-dashed"></div>
					 <div   style="padding-left: 15px;padding-right: 15px;">
                                       <b style="margin-right:20px;">价格点评:</b>
                                       <a style="color:#01B76D;margin-right:5px;" id="review1" href="javascript:setReview('1')"> <img src="img/xiao1.png"/>&nbsp;价格有优势</a>
                                       <a style="color:#808C8A;margin-right:5px;" id="review2" href="javascript:setReview('2')"> <img src="img/xiao2.png"/>&nbsp;价格一般</a>
                                       <a style="color:#808C8A" id="review3" href="javascript:setReview('3')"> <img src="img/xiao3.png"/>&nbsp;价格无优势</a>
                                       
                    </div> 
                    <div   style="padding-left: 15px;padding-right: 15px;">
                                        <b>价格比较:</b>
                                        <div class="radio radio-info radio-inline">
		                                     	<select name="priceComparison" id="priceComparison" class="form-control" >
			                                     	<option value="1" selected="selected">比较其他渠道较低</option>
		                                     	</select>
		                                </div>
		                                 <div class="radio radio-info radio-inline">
		                                     <input type="text"  name="difference" class="form-control" placeholder="0元" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')" style="width:100px;" />
		                                </div>
		                                 <div class="radio radio-info radio-inline">
		                                     <input type="text"  name="preOrderNumber"  class="form-control" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" placeholder="预购数量" style="width:100px;" />
		                                </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div   style="padding-left: 15px;padding-right: 15px;valign:middle">
                         <b style="height:130px;display:block;vetical-align:top;float:left;margin-right:20px">点评内容:&nbsp;</b>
                         <textarea id="reviewContent" class="form-control"  name="reviewContent" style="width:440px;height:130px;" > </textarea>
                    </div>
				</div>
				<!-- 正文部分end -->
				<!-- 工具栏部分begin -->
				<div class="panel-footer">
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-4"  id="win_edit_toolbar"> 
						</div>
					</div>
				</div>
				<!-- 工具栏部分end -->
			</div>
		</form>
		<!-- 表单部分end-->
	</body> 
</html>

<script src="<c:url value='/javaScript/dentistmall/evaluate/updateEvaluate.js?v=26'/>"></script>