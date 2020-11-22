<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>AI平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 	<script src="<c:url value='/javaScript/dentistmall/transaction/orderInfo.js?v=7'/>"></script>
  </head> 
 	
  <body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
		<div class="ibox-title">
			<!-- 标题文字区域begin -->
			<h5>
				<i class="fa fa-flask"></i>&nbsp&nbsp<span>订单信息</span> 
			</h5>
			<!-- 标题文字区域end --> 
			<div class="ibox-tools" id="win_tools_but"   > 
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
                                <p><strong>收货人:</strong><span style="padding-left:20px;" id="addressee"></span></p>
                                <p><strong>收货地址:</strong><span style="padding-left:20px;" id="deliveryAddress"></span></p>
                                 <p><strong>收货人电话：</strong><span style="padding-left:20px;" id="addresseeTelephone"></span></p>
                              </address>
                        </div>
 					    <!-- 收货信息end -->
                        <div class="col-sm-6 text-right">
                           <address>
                           <h3>订单信息</h3> 
                             <p><strong>单据编号:</strong><span style="padding-left:20px;" class="text-navy" id="orderCode"></span></p>
                             <p><strong>供应商:</strong><span style="padding-left:20px;" id="applicantName"></span></p> 
                             <p><strong>公司地址:</strong><span style="padding-left:20px;" id="corporateDomicile"></span></p>  
                             <p><strong>公司电话：</strong><span style="padding-left:20px;" id="phoneNumber"></span></p> 
                             <p><strong>下单日期：</strong><span style="padding-left:20px;" id="placeOrderTime"></span></p> 
                             
                           </address> 
                        </div>
                   
                    </div>
  					<!-- 订单信息区域end -->                  
                    <!-- 订单明细信息区域begin -->
                    <div class="table-responsive m-t">
                        <table class="table invoice-table">
                            <thead>
                                <tr>
                                    <th>清单</th>
                                    <th>数量</th>
                                    <th>确认数量</th>
                                    <th>退货数量</th>
                                    <th>单价</th>
                                    <th>单位</th>
                                    <th>总价</th>
                                    <th>操作</th>
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
                            	<td><strong>总数：</strong><span style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;" id="commodityNumber"></span> </td>
                                <td><strong>确认总数：</strong> <span style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;" id="shureNumber"></span> </td>
                                <td><strong>未确认数量：</strong> <span style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;" id="noShureNumber"></span> </td>
                                <td><strong>退货数量：</strong> <span style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;"  id="backNumber"></span></td>
                     			<td><strong>总价：</strong> </td>
                                <td id="actualMoney"></td>
                            </tr> 
                        </tbody>
                    </table>
                    <!-- 订单明细合计信息区域end -->
                    <!-- 工具栏区域begin -->
                    <div class="text-right">
                        <button class="btn   btn-sm"><i class="fa fa-dollar"></i> 整单确认</button>
                    </div>
					<!-- 工具栏区域end -->
					<!-- 提示区域begin -->
                    <div class="well m-t"><strong>注意：</strong> 请在30日内完成付款，否则订单会自动取消。 </div>
                    <!-- 提示区域end -->
                </div>
            
        </div>
        </div>
    </div>
</body>
</html>
 