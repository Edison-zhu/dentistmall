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
	.pay{
		padding: 10px 10px;
		margin-top: 20px;
		margin-right: 20px;
		margin-left: 20px;
	}
	.payinfo{
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
	.payinfo .middle{
        vertical-align: bottom;
		display: inline-block;
		width: 60%;
	}
	.payinfo .right{
        vertical-align: bottom;
		display: inline-block;
	}
	.refreshFont{
		font-size: 18px;
	}
	.money{
		font-size: 30px;
		/*position: relative;*/
		/*bottom: 12px;*/
	}

    .xqtabl{
        float: right;
        /*margin-right: 20px;*/
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
            <div style="text-align: right;margin-right: 20px;">
                <a class="btn btn-primary" onclick="refreshMoney()">刷新价格</a>
            </div>
            <div id="payinfo" class="payinfo">
<%--				<div class="left">--%>
<%--					<img width="50px" height="50px" src="../../../dentistmall/css/shopping/images/orderSucess.png">--%>
<%--				</div>--%>
<%--                <div class="middle">--%>
<%--					<h1><span class="refreshFont">订单号：<strong class="refreshFont" id="orderCode"></strong></span></h1>--%>
<%--					<h3 style="font-size: 10px; color: grey;margin-top: 5px"><span>您的订单已成功提交，请尽快完成支付</span></h3>--%>
<%--                </div>--%>
<%--                <div class="right">--%>
<%--                    <span><strong id="orderMoney" class="red money"><c:if test="${placeOrderMoney!=null}">¥<fmt:formatNumber type="number"--%>
<%--																													value="${placeOrderMoney}"--%>
<%--																													pattern="0.00"--%>
<%--																													maxFractionDigits="2"/></c:if><c:if--%>
<%--							test="${order.money3==null}">¥0.00</c:if></strong></span>--%>
<%--                </div>--%>

<%--                <div style="height: auto; line-height: 20px;margin-right: 50px;float: right">--%>
<%--                    <div style="width: 100%;display: block">--%>
<%--                        <table class="xqtabl">--%>
<%--                            <tr>--%>
<%--                                <td class="right">商品总价：</td>--%>
<%--                                <td class="left">--%>
<%--                                    <strong class="red">¥<span id="totalmoney"></span></strong>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td class="right">店铺优惠：</td>--%>
<%--                                <td class="left">--%>
<%--                                    <strong class="red">-¥<span id="money3"><c:if test="${obj.money3!=null}"><fmt:formatNumber type="number"--%>
<%--                                                                                                             value="${obj.money3}"--%>
<%--                                                                                                             pattern="0.00"--%>
<%--                                                                                                             maxFractionDigits="2"/></c:if><c:if--%>
<%--                                            test="${obj.money3==null}">0.00</c:if></span>--%>
<%--                                    </strong>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td class="right">运费(快递)：</td>--%>
<%--                                <td class="left">--%>
<%--                                    <strong class="red">¥<span id="money2">--%>
<%--                                        <c:if test="${obj.money2!=null}"><fmt:formatNumber type="number"--%>
<%--                                                                                            value="${obj.money2}"--%>
<%--                                                                                            pattern="0.00"--%>
<%--                                                                                            maxFractionDigits="2"/></c:if>--%>
<%--                                        <c:if test="${obj.money2==null}">0.00</c:if></span>--%>
<%--                                    </strong>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td class="right">订单总价：</td>--%>
<%--                                <td class="left">--%>
<%--                                    <strong class="red">¥<span id="placeOrderMoney"><c:if test="${obj.placeOrderMoney!=null}"> <fmt:formatNumber--%>
<%--                                            type="number"--%>
<%--                                            value="${obj.placeOrderMoney}"--%>
<%--                                            pattern="0.00"--%>
<%--                                            maxFractionDigits="2"/>--%>
<%--                                    </c:if></span></strong>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </table>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div style="clear: both"></div>--%>
            </div>
            <div class="control-group pay" style="clear: both">
                <div class="hd">
                    支付方式：
                </div>
                <div class="bd">
                    <div class="form-inline" style="display: inline-block; margin-top: 10px">
                        <label id="olinePayLabel" style="border-radius: 4px;height: auto;"
                               class="radio-info radio-inline form-control" for="monthPay"><input type="radio"
                                                                                                   id="monthPay"
                                                                                                   name="payRadio"
                                                                                                   value="3" checked style="margin-top: 40px"/><img height="80px" src="../../../dentistmall/css/shopping/images/monthPay.png" /><strong>月结支付</strong></label>
<%--                        <label id="olinePayLabel" style="border-radius: 4px;height: auto;"--%>
<%--                               class="radio-info radio-inline form-control" for="wechatPay"><input type="radio"--%>
<%--                                                                                                   id="wechatPay"--%>
<%--                                                                                                   name="payRadio"--%>
<%--                                                                                                   value="2" checked style="margin-top: 40px"/><img height="80px" src="../../../dentistmall/css/shopping/images/wechatPay.png" /></label>--%>
                        <%--                        <label id="monthPayLabel" style="border-radius: 4px;"--%>
                        <%--                               class="radio-info radio-inline form-control" for="monthPay"><input type="radio"--%>
                        <%--                                                                                                  id="monthPay"--%>
                        <%--                                                                                                  name="payRadio"--%>
                        <%--                                                                                                  value="1"/>支付宝支付</label>--%>
                    </div>
                </div>
            </div>
            <div class="ft">
                <div class="ft-left"></div>
                <div class="ft-right">
                    <button class="saveOrder font-weight-trigger" onclick="payNow()">立即支付</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="u-mask"  id="addressId"></div>
<div class="g-login" style="width:500px" id="addressInfo">
    <a class="open-close" id="addressClose">关闭</a>
    <div class="wrap" style="border: 0px solid #FF5F0F">
        <form id="accountForm" onkeydown="if(event.keyCode==13){return false;}">
            <input type="hidden" id="mdaId" name="mdaId" />
            <input type="hidden" id="suiId" name="suiId"/>
            <input type="hidden" id="ifDefault" name="ifDefault" value="2" />
            <div class="control-group">
                <div class="hd">
                    安全码：
                </div>
                <div class="bd">
                    <input type="password" class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" id="securityCode" name="securityCode">
                </div>
            </div>
            <div class="control-bottom clearfix" style="text-align:center">
                <a href="javascript:checkSecurityCodeFunc()"  class="fl btn ui-btn-theme" style="color:#fff;" id="submit">确认</a>
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<input type="hidden" id="moiId" value="${moiId}">
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/ddpay.js?v=22' />"></script>