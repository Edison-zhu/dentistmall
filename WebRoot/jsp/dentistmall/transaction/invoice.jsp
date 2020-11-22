<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE HTML>
<html>
  <head> 
   <title>AI平台系统</title> 
 	<%@ include file="/jsp/lib.jsp"%>
 	<%@ include file="/jsp/link.jsp"%>
 		
  </head> 
 
  <body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">

        <div class="row">
            <div class="col-sm-12">
                <div class="ibox-content p-xl">
                     <!-- 订单信息区域begin -->
                    <div class="row well" id="tableSH">
                       <!-- 收货信息begin -->
                        <div class="col-sm-6"> 
                              <address>
                                <h3>收货地址</h3>
                                <p><strong>收货人:</strong><span style="padding-left:20px;" id="addressee" >北京百度在线网络技术有限公司</span></p>
                                <p><strong>收货地址:</strong><span style="padding-left:20px;" id="delivery_address" >北京市海淀区上地十街10号</span></p>
                                 <p><strong>收货人电话：</strong><span style="padding-left:20px;" id="addressee_Mobile" >(+86 10) 5992 8888</span></p>
                                 <p><button onclick="edit_address()" class="btn  btn-primary btn-sm"><i class="fa fa-bus"></i>&nbsp;&nbsp;修改地址</button></p>
                              </address>
                        </div>
 					    <!-- 收货信息end -->
                        <div class="col-sm-6 text-right">
                           <address>
                             <h3>订单信息</h3> 
                             <p><strong>单据编号:</strong><span  id="orderCode"      style="padding-left:20px;" class="text-navy">H+-000567F7-00</span></p>
                             <p><strong>供应商:</strong> <span   id="purchaseUnit"       style="padding-left:20px;">阿里巴巴集团</span></p> 
                             <p><strong>公司地址:</strong><span  id="corporateDomicile"       style="padding-left:20px;">中国杭州市华星路99号东部软件园创业大厦6层(310099)</span></p>  
                             <p><strong>公司电话：</strong><span  id="phoneNumber"       style="padding-left:20px;">(86) 571-8502-2088</span></p> 
                             <p><strong>下单日期：</strong><span  id="placeOrderTime"       style="padding-left:20px;"> 2014-11-11</span></p>   
                           </address> 
                        </div>
                   
                    </div>
  					<!-- 订单信息区域end -->  
  					<!-- 订单明细合计信息区域begin -->
                    <table class="table invoice-total"  id="tableSH_hj">
                        <tbody>
                            <tr>
                            	<td>
                            	 <strong>商品总数：</strong>   <span  id=commodityNumber   style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">46</span>  
                                 <strong>确认总数：</strong>   <span  id="allNumber"   style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">32</span>  
                                 <strong>未确认数量：</strong>  <span  id="notNumber"   style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">12</span>  
                                 <strong>退货数量：</strong>   <span  id="backNumber"   style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">2</span> 
                     			 <strong>总价：</strong>      <span  id="placeOrderMoney"   style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">&yen;1026.00</span> 
                               </td>
                            </tr> 
                        </tbody>
                    </table>
                    <!-- 订单明细合计信息区域end -->
                     <!-- 工具栏区域begin
                      <button class="btn   btn-sm"><i class="fa fa-dollar"></i> 整单确认</button>
                      -->
                    <div class="text-right" id="win_tools_but">
                       
                    </div>
					<!-- 工具栏区域end -->
                    
                    <!-- 订单明细信息区域begin -->
                    
                    <div class="table-responsive m-t">
                        <table id="win_datagrid_main" class="mmg"></table> 
                    </div>
                   
                    <!-- 订单明细信息区域end -->
  					
                   
					<!-- 提示区域begin -->
					<!-- 
                    <div class="well m-t"><strong>注意：</strong> 请在30日内完成付款，否则订单会自动取消。 </div>
                     -->
                    <!-- 提示区域end -->
                </div>
            </div>
        </div>
    </div> 
</body> 
</html>

<script src="<c:url value='/javaScript/dentistmall/transaction/invoice.js?v=44'/>"></script>
