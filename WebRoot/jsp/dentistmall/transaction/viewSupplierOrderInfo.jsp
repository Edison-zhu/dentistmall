<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>AI平台系统</title>
    <meta charset="UTF-8">
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <script src="<c:url value='/javaScript/dentistmall/transaction/viewSupplierorderInfo.js?v=26'/>"></script>
        <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?13' />">
        <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/css/shopping/css/confirmOrder.css?v=3' />">
    <link rel="stylesheet" href="<c:url value='/jsp/util/ordermxutil/ordermxutil.css?v=3' />">
</head>
<style>
    #button1 {
        width: 95px;
        height: 21px;
        margin-right: 11px;
    }
 #searchDdBtn {
        width: 95px;
        height: 22px;
        margin-right: 11px;
    }
     #export1{
        width: 95px;
        height: 22px;
        margin-right: 11px;
    }
     #resetting {
        width: 95px;
        height: 22px;
        margin-right: 11px;
    }
    #button2 {
        width: 95px;
        height: 22px;
    }

    .ddBatchManage {
        font-size: 16px;
        text-align: right;
        margin-right: 2%;
        margin-top: 10px;
        margin-bottom: 10px;
        /*float: right;*/
        /*position: absolute;*/
        /*top: 15px;*/
        /*right: 2%;*/
    }

    .ddBatchManage input {
        padding-top: 4px;
    }

    .xqtabl {
        float: right;
        margin-right: 20px;
    }

    .xqtabl tr td {
        width: 100px;
    }

    .right {
        text-align: right;
    }

    .left {
        text-align: left;
    }

    .name {
        width: 300px;
        overflow: hidden;
        /*text-overflow: ellipsis;*/
        /*white-space: nowrap;*/
    }

    .type {
        width: 300px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
</style>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5>
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>订单信息</span>
        </h5>
        <!-- 标题文字区域end -->
<%--        <div class="ibox-tools" id="win_tools_but">--%>
<%--            <a onclick="main_export()" class='btn btn-success  btn-xs' style="color:#fff" id="button2">导出详情</a>--%>
<%--        </div>--%>
    </div>
    <div class="ibox-content">
        <div style="width: 1080px; margin: 0 auto;">
            <%@ include file="/jsp/util/ordermxutil/ordermxutil.jsp" %>
        </div>
        <%--        <div class="row">--%>
        <%--            <div class="col-sm-12">--%>

        <%--                <!-- 订单信息区域begin -->--%>
        <%--                <div class="row">--%>
        <%--                    <!-- 收货信息begin -->--%>
        <%--                    <div class="col-sm-6">--%>
        <%--                        <address>--%>
        <%--                            <h3>收货地址</h3>--%>
        <%--                            <p><strong>收货人:</strong><span--%>
        <%--                                    style="padding-left:20px;">${obj.province}${obj.city}${obj.area}(${obj.addressee} 收)</span>--%>
        <%--                            </p>--%>
        <%--                            <p><strong>收货地址:</strong><span style="padding-left:20px;">${obj.deliveryAddress}</span></p>--%>
        <%--                            <p><strong>收货人电话：</strong><span style="padding-left:20px;">${obj.addresseeTelephone}</span>--%>
        <%--                            </p>--%>
        <%--                        </address>--%>
        <%--                        <c:if test="${obj.processStatus=='2' || obj.processStatus=='3' || obj.processStatus=='4' || obj.processStatus=='5'}">--%>
        <%--                            <address>--%>
        <%--                                <h3>物流信息 </h3>--%>
        <%--                                <p><strong>物流公司:</strong><span style="padding-left:20px;"><input type="text"--%>
        <%--                                                                                                 name="expressName"--%>
        <%--                                                                                                 value="${obj.expressName}"--%>
        <%--                                                                                                 readonly="readonly"/></span>--%>
        <%--                                </p>--%>
        <%--                                <p><strong>运单号:&nbsp&nbsp&nbsp</strong><span style="padding-left:20px;"><input--%>
        <%--                                        type="text" name="expressCode" value="${obj.expressCode}" readonly="readonly"/></span>--%>
        <%--                                </p>--%>
        <%--                                <!--  -->--%>
        <%--                                <!--  -->--%>
        <%--                            </address>--%>
        <%--                        </c:if>--%>
        <%--                    </div>--%>
        <%--                    <!-- 收货信息end -->--%>
        <%--                    <div class="col-sm-6 text-right">--%>
        <%--                        <address>--%>
        <%--                            <h3>订单信息</h3>--%>
        <%--                            <p><strong>订单编号:</strong><span style="padding-left:20px;"--%>
        <%--                                                           class="text-navy">${obj.orderCode}</span></p>--%>
        <%--                            <p><strong>订单状态：</strong><span style="padding-left:20px;">${obj.processStatusName}</span>--%>
        <%--                            </p>--%>
        <%--                            <p><strong>采购商:</strong><span style="padding-left:20px;">${obj.purchaseUnit}</span></p>--%>
        <%--                            <c:if test="${obj.needBill!='1'}">--%>
        <%--                                <p><strong>发票信息:</strong><span style="padding-left:20px;">无</span></p>--%>
        <%--                            </c:if>--%>
        <%--                            <c:if test="${obj.needBill=='1'}">--%>
        <%--                                <p><strong>发票抬头:</strong><span style="padding-left:20px;">${obj.billHeard}(<c:if--%>
        <%--                                        test="${obj.billType=='1'}">个人</c:if><c:if test="${obj.billType=='2'}">商家</c:if>)</span>--%>
        <%--                                </p>--%>
        <%--                                <c:if test="${obj.billType=='2'}">--%>
        <%--                                    <p><strong>纳税人识别号：</strong><span style="padding-left:20px;">${obj.billCode}</span>--%>
        <%--                                    </p>--%>
        <%--                                </c:if>--%>
        <%--                            </c:if>--%>
        <%--                            <p><strong>下单日期：</strong><span style="padding-left:20px;"><fmt:formatDate--%>
        <%--                                    value="${obj.placeOrderTime}" type="both"/></span></p>--%>
        <%--                            <c:if test="${obj.processStatus=='5' || obj.processStatus=='6'}">--%>
        <%--                                <p><strong>完结日期：</strong><span style="padding-left:20px;"><fmt:formatDate--%>
        <%--                                        value="${obj.endTime}" type="both"/></span></p>--%>
        <%--                            </c:if>--%>
        <%--                        </address>--%>
        <%--                    </div>--%>

        <%--                </div>--%>
        <%--                <c:if test="${obj.scopeBusiness!=null && obj.scopeBusiness!=''}">--%>
        <%--                    <div class="row">--%>
        <%--                        <!-- 收货信息begin -->--%>
        <%--                        <div class="col-sm-12">--%>
        <%--                            <address>--%>
        <%--                                <h3>买家留言</h3>--%>
        <%--                                <p><strong>留言信息：</strong>${obj.scopeBusiness}</p>--%>
        <%--                            </address>--%>
        <%--                        </div>--%>
        <%--                    </div>--%>
        <%--                </c:if>--%>
        <%--                <!-- 订单信息区域end -->--%>
        <%--                <!-- 订单明细信息区域begin -->--%>
        <%--                <div class="table-responsive m-t">--%>
        <%--                    <table class="table invoice-table">--%>
        <%--                        <thead>--%>
        <%--                        <tr>--%>
        <%--                            <!-- 增加型号编号 -->--%>
        <%--                            <th style="text-align:center">型号编号</th>--%>
        <%--                            <th style="text-align:center">商品名称</th>--%>
        <%--                            <th style="text-align:center">型号</th>--%>
        <%--                            <th style="text-align:center">单价</th>--%>
        <%--                            <th style="text-align:center">单位</th>--%>
        <%--                            <th style="text-align:center">订单数量</th>--%>
        <%--                            <th style="text-align:center">发货数量</th>--%>
        <%--                            <th style="text-align:center">确认数量</th>--%>
        <%--                            <th style="text-align:center">订单总价</th>--%>
        <%--                            <th style="text-align:center">确认总价</th>--%>
        <%--                        </tr>--%>
        <%--                        </thead>--%>
        <%--                        <tbody id="mxList">--%>

        <%--                        </tbody>--%>
        <%--                    </table>--%>
        <%--                </div>--%>
        <%--                <!-- 订单明细信息区域end -->--%>
        <%--                <!-- 订单明细合计信息区域begin -->--%>
        <%--                <table class="table invoice-total">--%>
        <%--                    <tbody>--%>
        <%--                    <tr>--%>
        <%--                        <td style="text-align:center"><strong>订单总数：</strong><span--%>
        <%--                                style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;"> ${obj.commodityNumber}</span>--%>
        <%--                        </td>--%>
        <%--                        <td style="text-align:center"><strong>发货总数：</strong> <span--%>
        <%--                                style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">--%>
        <%--                                	<c:if test="${obj.number2!=null}"><fmt:formatNumber value="${obj.number2}"--%>
        <%--                                                                                        pattern="#0"--%>
        <%--                                                                                        maxFractionDigits="0"/></c:if>--%>
        <%--                       				<c:if test="${obj.number2==null}">0</c:if>--%>
        <%--                       				</span>--%>
        <%--                        </td>--%>
        <%--                        <td style="text-align:center"><strong>确认总数：</strong> <span--%>
        <%--                                style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">--%>
        <%--                                <c:if test="${obj.number1!=null}"><fmt:formatNumber value="${obj.number1}" pattern="#0"--%>
        <%--                                                                                    maxFractionDigits="0"/></c:if>--%>
        <%--                       			<c:if test="${obj.number1==null}">0</c:if>--%>
        <%--                                </span></td>--%>
        <%--                        <td style="text-align:center"><strong>运费：</strong> <span--%>
        <%--                                style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">¥--%>
        <%--                                <c:if test="${obj.money2!=null}"><fmt:formatNumber value="${obj.money2}" pattern="#0"--%>
        <%--                                                                                   maxFractionDigits="0"/></c:if>--%>
        <%--                       			<c:if test="${obj.money2==null}">0.00</c:if>--%>
        <%--                                </span></td>--%>
        <%--                        <c:if test="${obj.money3!=null}">--%>
        <%--                            <td style="text-align:center"><strong>优惠金额：</strong>--%>
        <%--                                <span style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">¥--%>
        <%--	                                	<fmt:formatNumber value="${obj.money3}" pattern="#0" maxFractionDigits="0"/>--%>
        <%--	                                	</span>--%>
        <%--                            </td>--%>
        <%--                        </c:if>--%>
        <%--                        <td style="text-align:center"><strong>订单总价：</strong> <span--%>
        <%--                                style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">--%>
        <%--                     			¥<fmt:formatNumber type="number" value="${obj.placeOrderMoney}" pattern="0.00"--%>
        <%--                                                   maxFractionDigits="2"/>--%>
        <%--                     			</span></td>--%>
        <%--                        <td style="text-align:center;border-bottom:0px"><strong>确认总价：</strong> <span--%>
        <%--                                style="border-bottom: 1px solid #DDDDDD;padding-left:20px; padding-right: 20px;">¥--%>
        <%--                     			<c:if test="${obj.actualMoney!=null}"><fmt:formatNumber type="number"--%>
        <%--                                                                                        value="${obj.actualMoney}"--%>
        <%--                                                                                        pattern="0.00"--%>
        <%--                                                                                        maxFractionDigits="2"/></c:if>--%>
        <%--                       			<c:if test="${obj.actualMoney==null}">0.00</c:if>--%>
        <%--                     			</span></td>--%>
        <%--                    </tr>--%>
        <%--                    </tbody>--%>
        <%--                </table>--%>
        <%--                <!-- 订单明细合计信息区域end -->--%>
        <%--                <!-- 工具栏区域begin -->--%>
        <%--                <!-- 工具栏区域end -->--%>
        <%--                <!-- 提示区域begin --%>
        <%--                <div class="well m-t"><strong>注意：</strong> 请在30日内完成付款，否则订单会自动取消。 </div>-->--%>
        <%--                <!-- 提示区域end -->--%>
        <%--            </div>--%>

        <%--            <div class="ibox-tools" id="win_tools_but">--%>
        <%--            </div>--%>
        <%--            <div>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--%>
        <%--                <b>摘要</b>:--%>
        <%--                <input type="text" id="test" value="${obj.enterpriseType}" readonly="readonly"--%>
        <%--                       style="width:600px;height:40px;" name="abstract"/>--%>

        <%--                <!-- <div class="ibox-tools" id="win_tools_but"   > --%>
        <%--				<a onclick="main_exportC()"  class='btn btn-success  btn-xs' style="color:#fff">导出对账单</a>--%>
        <%--			</div> -->--%>
        <%--                <div class="text-right">--%>
        <%--                    <a onclick="main_exportC()" class='btn btn-success  btn-xs' style="color:#fff"--%>
        <%--                       id="button1">导出配送单</a>--%>
        <%--                </div>--%>
        <%--            </div>--%>
        <%--        </div>--%>
    </div>
</div>
</body>
</html>
 