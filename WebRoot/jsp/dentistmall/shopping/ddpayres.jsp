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

    .pay {
        padding: 10px 10px;
        margin-top: 20px;
        margin-right: 20px;
        margin-left: 20px;
    }

    .payinfo {
        border: black solid 1px;
        padding: 10px 10px;
        margin-top: 20px;
        margin-right: 20px;
        margin-left: 20px;
    }

    .payinfo .left {
        display: inline-block;
        width: 50px;
        vertical-align: top;
    }

    .payinfo .middle {
        vertical-align: bottom;
        display: inline-block;
        width: 60%;
    }

    .payinfo .right {
        vertical-align: bottom;
        display: inline-block;
    }

    .refreshFont {
        font-size: 18px;
    }

    .money {
        font-size: 30px;
        /*position: relative;*/
        /*bottom: 12px;*/
    }

    .btn-style button {
        float: left;
        width: 150px;
        height: 30px;
        border: 2px solid #0bc176;
        background-color: #0bc176;
        border-radius: 5px;
        margin: 15px 10px;
        font-size: 18px;
        background-color: #0bc176;
        color: #ffffff;
        font-family: null;
        line-height: 25px;
    }
</style>
<script>
    var _isFix = 1;
    var _isCar = 3;
</script>
</head>
<body>
<header class="header">
    <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/confirmOrder.css?v=57' />">
<%--<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?9' />">--%>
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
            购物车
        </a>
    </label>
    <label>
        订单确认
    </label>
</p>
<div class="content">
    <div class="shopping-cart-body w1080">
        <div class="shopping-cart-header">
            <div class="shopping-flow">
                <div class="shopping-cart-icon">
                    <img src="<c:url value='/css/shopping/images/shopping-list.png'/>">
                    <span class="cn">请支付金额：</span>
                    <span class="un">Please pay the amount</span>
                </div>
                <div class="flow">
                    <div class="ready">
                        <i class="icon-circle green"></i>
                        <ul>
                            <li class="green">我的购物车</li>
                            <li class="green">STEP 1</li>
                        </ul>
                    </div>
                    <div class="done bGreen"></div>
                    <div class="ready l30">
                        <i class="icon-circle green"></i>
                        <ul class="l10">
                            <li class="green">确认订单信息</li>
                            <li class="green">STEP 2</li>
                        </ul>
                    </div>
                    <div class="done bGreen"></div>
                    <div class="ready l30">
                        <i class="icon-circle green"></i>
                        <ul class="l3">
                            <li class="green">支付信息</li>
                            <li class="green">STEP 3</li>
                        </ul>
                    </div>
                    <div class="done"></div>
                    <div class="ready l30">
                        <i class="icon-circle"></i>
                        <ul class="l3">
                            <li>确认收货</li>
                            <li>STEP 3</li>
                        </ul>
                    </div>
                    <div class="done"></div>
                    <div class="ready l30">
                        <i class="icon-circle"></i>
                        <ul class="l3">
                            <li>完成</li>
                            <li>STEP 4</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="shopping-cart-content">
            <%--            <div style="text-align: right;margin-right: 20px;">--%>
            <%--                <a class="btn btn-primary" onclick="refreshMoney(${moiId})">刷新价格</a>--%>
            <%--            </div>--%>
                <div style="padding: 10px 10px;margin-top: 20px;margin-right: 20px;margin-left: 20px;">
                    <span style="font-size: 20px">订单总价：<span id="allOrderMoney" style="font-size: 20px" class="red"></span></span>
                </div>
                <div id="payinfo" class="payinfo">
<%--                <div style="display: inline-block;width: 60%">--%>
<%--                    <div class="left">--%>
<%--                        <img width="50px" height="50px" src="../../../dentistmall/css/shopping/images/orderSucess.png">--%>
<%--                    </div>--%>
<%--                    <div class="middle">--%>
<%--                        <h1><span class="refreshFont">订单号：<strong class="refreshFont" id="orderCode"></strong></span>--%>
<%--                        </h1>--%>
<%--                        <h3 style="font-size: 10px; color: grey;margin-top: 5px"><span>您的订单已成功支付，商家会尽快为您发货</span></h3>--%>
<%--                    </div>--%>
<%--                    <div class="btn-style">--%>
<%--                        <button class="saveOrder font-weight-trigger" onclick="viewDd()">立即查看订单</button>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="" style="display: inline-block;vertical-align: text-top;line-height: 48px;">--%>
<%--                    <div>--%>
<%--                        支付方式：--%>
<%--                        <span id="payTypeName"></span>--%>
<%--                    </div>--%>
<%--                    <span><strong id="orderMoney" class="red money"><c:if--%>
<%--                            test="${placeOrderMoney!=null}">¥<fmt:formatNumber--%>
<%--                            type="number"--%>
<%--                            value="${placeOrderMoney}"--%>
<%--                            pattern="0.00"--%>
<%--                            maxFractionDigits="2"/></c:if><c:if--%>
<%--                            test="${order.money3==null}">¥0.00</c:if></strong></span>--%>
<%--                </div>--%>
<%--                <div style="clear: both"></div>--%>
            </div>
            <div class="ft">

        </div>
    </div>
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<input type="hidden" id="moiId" value="${moiId}">
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/ddpayres.js?v=2' />"></script>