<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp" %>
</head>
<style>
    .name {
        width: 300px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .type {
        width: 300px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
</style>
<script>
    var _isFix = 1;
    var _isCar = 4;
</script>
</head>
<body>
<header class="header">
    <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<style>
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

    .xqtabl{
        float: right;
        margin-right: 20px;
    }
    .xqtabl tr td {
        width: 100px;
    }
    .right{
        text-align: right;
    }
    .left{
        text-align: left;
    }
</style>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/confirmOrder.css?58' />">
<script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
<p class="bread-crumb w1080">
    <i class="icon-homepage"></i>
    <label>
        <a href="index.htm">
            首页
        </a>
    </label>
    <label>
        <a href="gwc.htm">
            订单列表
        </a>
    </label>
    <label>
        订单信息
    </label>
</p>
<%@ include file="/jsp/util/ordermxutil/ordermxutil.jsp" %>
<%--<div class="content">--%>
<%--    <div class="shopping-cart-body w1080">--%>
<%--        <div class="shopping-cart-header">--%>
<%--            <div class="shopping-flow">--%>
<%--                <div class="shopping-cart-icon">--%>
<%--                    <img src="<c:url value='/css/shopping/images/shopping-list.png'/>">--%>
<%--                    <span class="cn">订单信息：</span>--%>
<%--                    <span class="un">View order info</span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="shopping-cart-content">--%>
<%--            <div>--%>
<%--                <div style="margin-left: 3%;float: left; margin-top: 0px;font-size: 20px">--%>
<%--                    <b>订单状态：${order.processStatusName}</b></div>--%>
<%--                <div style="float: right;margin-right: 4%;text-align: center">--%>
<%--                    <c:if test="${order.processStatus == 1}"> <!-- 待发货 -->--%>
<%--                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"--%>
<%--                           style="text-decoration: underline;color: blue">取消订单</a><br/>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${order.processStatus == 2}"> <!-- 待付款 -->--%>
<%--                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"--%>
<%--                           style="text-decoration: underline;color: blue">取消订单</a><br/>--%>
<%--                        <a class="btn btn-primary" style="margin-top: 10px;"--%>
<%--                           href="ddpay.htm?moiId=${order.moiId}">立即支付</a>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${order.processStatus == 3}"> <!-- 待发货 -->--%>
<%--                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"--%>
<%--                           style="text-decoration: underline;color: blue">取消订单</a><br/>--%>
<%--                        <a class="btn btn-primary" style="margin-top: 10px;"--%>
<%--                           href="ddsh.htm?moiId=${order.moiId}">确认收货</a>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${order.processStatus == 4}"> <!-- 已发货 -->--%>
<%--                        &lt;%&ndash;                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                           style="text-decoration: underline;color: blue">取消订单</a><br/>&ndash;%&gt;--%>
<%--                        <a class="btn btn-primary" style="margin-top: 10px;"--%>
<%--                           href="">申请售后</a><!-- ddsh.htm?moiId=${order.moiId} -->--%>
<%--                        <a class="btn btn-primary" style="margin-top: 10px;"--%>
<%--                           href="ddsh.htm?moiId=${order.moiId}">待确认收货</a>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${order.processStatus == 5}"> <!-- 交易成功 -->--%>
<%--                        &lt;%&ndash;                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                           style="text-decoration: underline;color: blue">取消订单</a><br/>&ndash;%&gt;--%>
<%--                        <a class="btn btn-primary" style="margin-top: 10px;"--%>
<%--                           href="javascript=buyAgain(${order.moiId})">再次购买</a>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${order.processStatus == 6}"> <!-- 交易失败 -->--%>
<%--                        &lt;%&ndash;                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                           style="text-decoration: underline;color: blue">取消订单</a><br/>&ndash;%&gt;--%>
<%--                        <a class="btn btn-primary" style="margin-top: 10px;"--%>
<%--                           href="javascript=buyAgain(${order.moiId})">再次购买</a>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${order.processStatus == 7}"> <!-- 售后 -->--%>
<%--                        &lt;%&ndash;                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                           style="text-decoration: underline;color: blue">取消订单</a><br/>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        <a class="btn btn-primary" style="margin-top: 10px;"&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                           href="ddsh.htm?moiId=${order.moiId}">确认收货</a>&ndash;%&gt;--%>
<%--                    </c:if>--%>

<%--                </div>--%>
<%--            </div>--%>

<%--            <div class="address-list">--%>
<%--                <div class="address-items-info">--%>
<%--                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">--%>
<%--                        订单号：${order.orderCode}</div>--%>
<%--                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">--%>
<%--                        供应商：${order.applicantName}</div>--%>
<%--                    <div class="ellips_line" style="float:left;width:330px;padding-left:40px">--%>
<%--                        订单状态：${order.processStatusName}</div>--%>
<%--                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">下单时间：<fmt:formatDate--%>
<%--                            value="${order.placeOrderTime}" type="both"/></div>--%>
<%--                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">--%>
<%--                        下单数量：${order.commodityNumber}</div>--%>
<%--                    <div class="ellips_line" style="float:left;width:330px;padding-left:40px">订单金额：--%>
<%--                        ¥<fmt:formatNumber type="number" value="${order.placeOrderMoney}" pattern="0.00"--%>
<%--                                           maxFractionDigits="2"/></div>--%>
<%--                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">完结时间：<fmt:formatDate--%>
<%--                            value="${order.endTime}" type="both"/></div>--%>
<%--                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">确认数量：--%>
<%--                        <c:if test="${order.number1!=null}"><fmt:formatNumber value="${order.number1}" pattern="#0"--%>
<%--                                                                              maxFractionDigits="0"/></c:if>--%>
<%--                        <c:if test="${order.number1==null}">-</c:if>--%>
<%--                    </div>--%>
<%--                    <div class="ellips_line" style="float:left;width:330px;padding-left:40px">确认金额：--%>
<%--                        <c:if test="${order.actualMoney!=null}">¥<fmt:formatNumber type="number"--%>
<%--                                                                                   value="${order.actualMoney}"--%>
<%--                                                                                   pattern="0.00"--%>
<%--                                                                                   maxFractionDigits="2"/></c:if>--%>
<%--                        <c:if test="${order.actualMoney==null}">-</c:if>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="address-items">--%>
<%--                    <p><i class="icon-people"></i>${order.addressee}</p>--%>
<%--                    <p><i class="icon-address"></i>${order.province}${order.city}${order.area}${order.deliveryAddress}--%>
<%--                    </p>--%>
<%--                    <p><i class="icon-phone"></i>${order.addresseeTelephone}</p>--%>
<%--                    <span class="font-weight-trigger">收货地址</span>--%>
<%--                </div>--%>
<%--                <div class="address-items" style="margin-left:14px">--%>
<%--                    <c:if test="${order.needBill=='1'}">--%>
<%--                        <p>抬头:${order.billHeard}(<c:if test="${order.billType=='1'}">个人</c:if><c:if--%>
<%--                                test="${order.billType=='2'}">商家</c:if>)</p>--%>
<%--                        <c:if test="${order.billType=='2'}">--%>
<%--                            <p>纳税人识别号:${order.billCode}</p>--%>
<%--                        </c:if>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${order.needBill!='1'}">--%>
<%--                        <p>无</p>--%>
<%--                    </c:if>--%>
<%--                    <span class="font-weight-trigger">发票信息</span>--%>
<%--                </div>--%>
<%--                <div class="address-items" style="margin-left:15px">--%>
<%--                    <p>运送方式:快递</p>--%>
<%--                    <p>物流公司:--%>
<%--                        <c:if test="${order.expressName!=null}">${order.expressName}</c:if>--%>
<%--                        <c:if test="${order.expressName==null}">-</c:if>--%>
<%--                    </p>--%>
<%--                    <p>运单号:--%>
<%--                        <c:if test="${order.expressCode!=null}">${order.expressCode}</c:if>--%>
<%--                        <c:if test="${order.expressCode==null}">-</c:if>--%>
<%--                    </p>--%>
<%--                    <span class="font-weight-trigger">物流信息 </span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div style="clear: both">--%>
<%--                <div class="ddBatchManage form-inline">--%>
<%--                    <div style="display: inline">--%>
<%--                        <input type="text" id="searchDdName" name="searchDdName" class="form-control"--%>
<%--                               placeholder="请输入商品名称"/>--%>
<%--                        &lt;%&ndash;                        <select id="searchDDState" class="form-control">&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <option value="">选择订单状态</option>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <option value="1">待发货</option>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <option value="2">待付款</option>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <option value="3">待收货</option>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <option value="4">已发货</option>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <option value="5">交易成功</option>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <option value="6">交易失败</option>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                            <option value="7">售后</option>&ndash;%&gt;--%>
<%--                        &lt;%&ndash;                        </select>&ndash;%&gt;--%>
<%--                        <button id="searchDdBtn" class="btn btn-primary" onclick="searchDd()">搜索</button>--%>
<%--                        <button id="" class="btn btn-white" onclick="resetSearch()">重置</button>--%>

<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="order">--%>
<%--                <div class="order-header">--%>
<%--                    <div class="order-header-left">--%>
<%--                        <label>供应商：<a class="supplier font-weight-trigger" href="pcjxiangxi.htm?wzId=${order.wzId}"--%>
<%--                                      target="_blank">${order.applicantName}</a></label>--%>
<%--                    </div>--%>
<%--                    <div class="order-header-right">--%>
<%--                        <span class="order-header-item">单价</span>--%>
<%--                        <span class="order-header-item">订单数量</span>--%>
<%--                        <span class="order-header-item">发货数量</span>--%>
<%--                        <span class="order-header-item">收货数量</span>--%>
<%--                        <span class="order-header-item">订单总额</span>--%>
<%--                        <span class="order-header-item">实际总额</span>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="order-body" id="matList">--%>
<%--                </div>--%>
<%--                <div class="pagination w1080 m-pagenum pagination" id="Pagination"></div>--%>
<%--            </div>--%>
<%--            <div class="note">--%>
<%--                <label class="note-label">买家留言：</label>--%>
<%--                <textarea class="note-textarea" placeholder="在此输入给商家的留言" readonly=true>${order.scopeBusiness}</textarea>--%>
<%--            </div>--%>
<%--            <div class="ft" style="height: auto; line-height: 20px;">--%>
<%--                <div style="width: 100%;display: block">--%>
<%--                    <table class="xqtabl">--%>
<%--                        <tr>--%>
<%--                            <td class="right">商品总价：</td>--%>
<%--                            <td>--%>
<%--                                <strong class="red">¥<span id="totalmoney"></span></strong>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td class="right">店铺优惠：</td>--%>
<%--                            <td class="left">--%>
<%--                                <strong class="red">-<c:if test="${order.money3!=null}">¥<fmt:formatNumber type="number"--%>
<%--                                                                                                           value="${order.money3}"--%>
<%--                                                                                                           pattern="0.00"--%>
<%--                                                                                                           maxFractionDigits="2"/></c:if><c:if--%>
<%--                                        test="${order.money3==null}">¥0.00</c:if>--%>
<%--                                </strong>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td class="right">运费(快递)：</td>--%>
<%--                            <td class="left">--%>
<%--                                <strong class="red">--%>
<%--                                    <c:if test="${order.money2!=null}">¥<fmt:formatNumber type="number"--%>
<%--                                                                                         value="${order.money2}"--%>
<%--                                                                                         pattern="0.00"--%>
<%--                                                                                         maxFractionDigits="2"/></c:if>--%>
<%--                                    <c:if test="${order.money2==null}">0.00</c:if>--%>
<%--                                </strong>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td class="right">订单总价：</td>--%>
<%--                            <td class="left">--%>
<%--                                <strong class="red"><c:if test="${order.placeOrderMoney!=null}"> ¥<fmt:formatNumber--%>
<%--                                        type="number"--%>
<%--                                        value="${order.placeOrderMoney}"--%>
<%--                                        pattern="0.00"--%>
<%--                                        maxFractionDigits="2"/>--%>
<%--                                </c:if></strong>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                        <c:if test="${order.placeOrderMoney==null}">-</c:if>--%>
<%--                        <c:if test="${order.processStatus == 1}"> <!-- 待发货 -->--%>

<%--                        </c:if>--%>
<%--                        <c:if test="${order.processStatus == 2}"> <!-- 待付款 -->--%>

<%--                        </c:if>--%>
<%--                        <c:if test="${order.processStatus == 3}"> <!-- 待发货 -->--%>

<%--                        </c:if>--%>
<%--                        <c:if test="${order.processStatus == 4}"> <!-- 已发货 -->--%>

<%--                        </c:if>--%>
<%--                        <c:if test="${order.processStatus == 5}"> <!-- 交易成功 -->--%>

<%--                        </c:if>--%>
<%--                        <c:if test="${order.processStatus == 6}"> <!-- 交易失败 -->--%>
<%--                            <tr>--%>
<%--                                <td class="right">退款金额：</td>--%>
<%--                                <td class="left">--%>
<%--                                    <strong class="red"><c:if test="${order.placeOrderMoney!=null}"> ¥<fmt:formatNumber--%>
<%--                                            type="number"--%>
<%--                                            value="${order.placeOrderMoney}"--%>
<%--                                            pattern="0.00"--%>
<%--                                            maxFractionDigits="2"/>--%>
<%--                                    </c:if></strong>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td class="right">实付金额：</td>--%>
<%--                                <td class="left">--%>
<%--                                    <strong class="red"><c:if test="${order.placeOrderMoney!=null}"> ¥<fmt:formatNumber--%>
<%--                                            type="number"--%>
<%--                                            value="${order.actualMoney}"--%>
<%--                                            pattern="0.00"--%>
<%--                                            maxFractionDigits="2"/>--%>
<%--                                    </c:if></strong>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </c:if>--%>
<%--                        <c:if test="${order.processStatus == 7}"> <!-- 售后 -->--%>

<%--                        </c:if>--%>
<%--                        <tr style="color: grey">--%>
<%--                            <td class="right">--%>
<%--                                支付方式：--%>
<%--                            </td>--%>
<%--                            <td class="left">${order.payTypeName}</td>--%>
<%--                        </tr>--%>
<%--                        <tr>--%>
<%--                            <td></td>--%>
<%--                            <td class="left" align="left"><button class="saveOrder font-weight-trigger" style="float: left; width: auto; height: auto; margin: 0px; margin-bottom: 10px" onclick="main_export()">导出</button></td>--%>
<%--                        </tr>--%>
<%--                    </table>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    &lt;%&ndash;                    商品总价，运费，订单总价，实付金额 付款方式，退款金额，支付方式&ndash;%&gt;--%>
<%--                    &lt;%&ndash;                    <button class="saveOrder font-weight-trigger" onclick="main_export()">导出</button>&ndash;%&gt;--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<input type="hidden" id="moiId" value="${obj.moiId}">
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/ddxq.js?31' />"></script>