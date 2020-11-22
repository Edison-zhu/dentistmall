<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>AI平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/dentistmall/transaction/sendOrderInfo.js?v=37'/>"></script>
  </head> 
  <style>
  		#button1{
  			width:95px;
  			height:21px;
  		}
  		#sendBtn{
  			width:95px;
  			height:21px;
  		}
  		#button3{
  			width:95px;
  			height:21px;
  		}
  </style>
  <body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox-title">
			<!-- 标题文字区域begin -->
			<h5>
				<i class="fa fa-flask"></i>&nbsp&nbsp<span>订单发货</span> 
			</h5>
			<!-- 标题文字区域end --> 
			<div class="ibox-tools" id="win_tools_but"   > 
				<a onclick="main_export()"  class='btn btn-success  btn-xs' style="color:#fff" id="button3">导出详情</a>
			</div>
		</div>
		<div class="ibox-content">
        <div class="row">
            <div class="col-sm-12">
                
                     <!-- 订单信息区域begin -->
                    <div class="row">
                       <!-- 收货信息begin -->
                        <div class="col-sm-6"> 
                              <address>
                                <h3>收货地址</h3>
                                <p><strong>收货人:</strong><span style="padding-left:20px;" >${obj.province}${obj.city}${obj.area}(${obj.addressee} 收)</span></p>
                                <p><strong>收货地址:</strong><span style="padding-left:20px;" >${obj.deliveryAddress}</span></p>
                                 <p><strong>收货人电话：</strong><span style="padding-left:20px;" >${obj.addresseeTelephone}</span></p>
                              </address>
	                          <address>
		                          <h3>物流信息 </h3>
		                          <p><strong>物流公司:</strong><span style="padding-left:20px;" ><input type="text" id="expressName"></span></p>
		                          <p><strong>运&nbsp;&nbsp;单&nbsp;&nbsp;号:</strong><span style="padding-left:18px;" ><input type="text" id="expressCode"></span></p>
	                          </address>
                        </div>
 					    <!-- 收货信息end -->
                        <div class="col-sm-6 text-right">
                           <address>
                           <h3>订单信息</h3> 
                             <p><strong>订单编号:</strong><span style="padding-left:20px;" class="text-navy" >${obj.orderCode}</span></p>
                             <p><strong>订单状态：</strong><span style="padding-left:20px;">${obj.processStatusName}</span></p> 
                             <p><strong>采购商:</strong><span style="padding-left:20px;" >${obj.purchaseUnit}</span></p> 
                             <c:if test="${obj.needBill!='1'}">
                             	<p><strong>发票信息:</strong><span style="padding-left:20px;" >无</span></p>  
                             </c:if>
                             <c:if test="${obj.needBill=='1'}">
                             	<p><strong>发票抬头:</strong><span style="padding-left:20px;" >${obj.billHeard}(<c:if test="${obj.billType=='1'}">个人</c:if><c:if test="${obj.billType=='2'}">商家</c:if>)</span></p>  
                             	<c:if test="${obj.billType=='2'}">
                    				<p><strong>纳税人识别号：</strong><span style="padding-left:20px;" >${obj.billCode}</span></p> 
                    			</c:if>
                             </c:if>
                             <p><strong>下单日期：</strong><span style="padding-left:20px;"><fmt:formatDate value="${obj.placeOrderTime}" type="both" /></span></p> 
                             
                           </address> 
                        </div>
                    </div>
                    <c:if test="${obj.scopeBusiness!=null && obj.scopeBusiness!=''}">
	                    <div class="row">
	                       <!-- 收货信息begin -->
	                        <div class="col-sm-12"> 
	                              <address>
	                                <h3>买家留言</h3>
	                                <p><strong>留言信息：</strong>${obj.scopeBusiness}</p>
	                              </address>
	                        </div>
	                    </div>
                    </c:if>
  					<!-- 订单信息区域end -->                  
                    <!-- 订单明细信息区域begin -->
                    <div class="table-responsive m-t">
                        <table class="table invoice-table">
                            <thead>
                                <tr>
                                    <th style="text-align:center">商品名称</th>
                                    <th style="text-align:center">型号</th>
                                    <th style="text-align:center">单价</th>
                                    <th style="text-align:center">单位</th>
                                    <th style="text-align:center">订单数量</th>
                                    <th style="text-align:center">已发货数量</th>
                                    <th style="text-align:center">发货数量</th>
                                </tr>
                            </thead>
                            <tbody id="mxList">
                                
                            </tbody>
                        </table>
                    </div>
                    <!-- 订单明细信息区域end -->
  					<!-- 订单明细合计信息区域begin -->
                    <table class="table invoice-total">
                        <tbody>
                            <tr>
                            	<td style="text-align:center"><strong>订单总数：</strong><span style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;"> ${obj.commodityNumber}</span> </td>
                                <td style="text-align:center;border-bottom:0px;width:50%"><strong>已发货总数：</strong> <span style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;" id="fhcount">
                                <c:if test="${obj.number2!=null}"><fmt:formatNumber value="${obj.number2}" pattern="#0" maxFractionDigits="0"/></c:if>
                       			<c:if test="${obj.number2==null}">0</c:if>
                                </span> </td>
                            </tr> 
                        </tbody>
                    </table>
	              <!--   <div class="text-right">
	                <a onclick="main_exportC()"  class='btn btn-success  btn-xs' style="color:#fff">导出配送单</a>
	                   <button class="btn btn-info  btn-sm" onclick="send()" id="sendBtn">发货</button>
	                </div> -->
                    <!-- 订单明细合计信息区域end -->
                    <!-- 工具栏区域begin -->
					<!-- 工具栏区域end -->
					<!-- 提示区域begin 
                    <div class="well m-t"><strong>注意：</strong> 请在30日内完成付款，否则订单会自动取消。 </div>-->
                    <!-- 提示区域end -->
                </div>
                 <div>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                	<b>摘要</b>:
               <input type="text" id="test" value="${obj.enterpriseType}" placeholder="选填，100个字以内" style="width:600px;height:40px;" name="abstract"/>
           	 </div>
           	 <!-- 更改配送单位置  优化按钮 -->
           	 <div class="text-right">
           	 		<a onclick="main_exportC()"  class='btn btn-success  btn-xs' style="color:#fff" id="button1">导出配送单</a>
	            	<button class="btn btn-info  btn-xs" onclick="send()" id="sendBtn">发货</button>
           	 </div>
        </div>
        </div>
    </div>
</body>
</html>
 