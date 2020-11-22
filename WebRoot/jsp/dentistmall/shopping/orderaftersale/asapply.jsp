<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2019/12/27
  Time: 8:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
</head>
<script>
    var _isFix = 1;
    var _isCar = 2;
</script>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<script src="<c:url value='/js/plugins/layui/layui.all.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?v=2' />">
<link rel="stylesheet" href="<c:url value='/jsp/util/orderlistutil.css?v=3' />">
<link rel="stylesheet" href="<c:url value='/css/shopping/css/confirmOrder.css?v=2' />">
<script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
<style>
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

    .radiolabel {
        border-radius: 4px;
        height: auto;
    }

    .aftersale {
        padding: 10px;
    }

    .astype {
        margin-left: 10px;
        margin-bottom: 20px;
    }

    .aftersale table {
        margin-left: 5px;
    }

    .aftersale table tr td {
        padding: 5px;
    }

    .imgul li .add,
    .imgul li:hover .imgdel {
        background: url(../img/sprite-new.png) 999px 999px no-repeat;
    }

    .imgul {

    }

    .imgul li {
        width: 90px;
        height: 90px;
        float: left;
        margin-right: 10px;
        margin-bottom: 10px;
        position: relative;
        list-style: none;
        text-align: center;
    }

    .imgul .one {
        width: 200px;
        height: 90px;
        float: left;
        margin-right: 10px;
        margin-bottom: 10px;
        position: relative;
        list-style: none;
        text-align: center;
    }

    .imgul .one img {
        width: 200px;
        height: 90px;
    }

    .imgul .one .butyfile {
        filter: alpha(opacity=0);
        -moz-opacity: 0;
        -khtml-opacity: 0;
        opacity: 0;
        width: 89px;
        height: 89px;
        position: absolute;
        top: 0;
        left: 60px;
        cursor: pointer;
    }

    .imgul li img {
        width: 90px;
        height: 90px;
    }

    .imgul li .add {
        width: 35px;
        height: 35px;
        margin-top: 27px;
        margin-left: 29px;
        background-position: 0px -424px;
    }

    .imgul li .butyfile {
        filter: alpha(opacity=0);
        -moz-opacity: 0;
        -khtml-opacity: 0;
        opacity: 0;
        width: 89px;
        height: 89px;
        position: absolute;
        top: 0;
        left: 0;

        cursor: pointer;
    }

    .addLi {
        width: 89px;
        height: 89px;
        border: 1px dashed #c6c6c6;
        cursor: pointer;
        background-color: #f8f8f8;
        margin-left: 60px;
    }

    .imgul li:hover .imgdel {
        position: absolute;
        margin: 0;
        top: 0;
        right: 0;
        width: 20px;
        height: 20px;
        background-position: 0px -500px;
    }

    .float-e-margins .btn {
        margin-bottom: 5px;
    }

    .shopping-cart-content .hd .hd-right label {
        display: inline-block;
        color: white;
        font-weight: bold;
        width: auto;
        margin-left: 0px;
    }

    .shopping-cart-content .hd {
        width: 1080px;
    }

    .hd ul li {
        display: inline-block;
        float: left;
    }

    .order-info ul li {
        width: 310px;
        word-wrap: break-word;
    }

    .asState {
        text-align: center;
    }

    .asState .ul {
        line-height: 50px;
        text-align: center;
        margin-bottom: 20px;
    }

    /*正在处理*/
    .ased {
        background-color: rgba(9, 85, 181, 0.83);
    }

    .arrowed {
        border: 25px solid transparent;
        border-left: 35px solid rgba(9, 85, 181, 0.83);
        width: 0;
        height: 0px;
    }

    /*未处理*/
    .aftering {
        background-color: rgba(50, 142, 193, 0.89);
    }

    .arrowing {
        border: 25px solid transparent;
        border-left: 35px solid rgba(50, 142, 193, 0.89);
        width: 0;
        height: 0px;
    }

    .asState .ul .li {
        color: white;
        flex: 1;
        text-align: center;
    }

    .asState .li {
        color: white;
    }

    .asState .state-block-left {
        vertical-align: top;
        width: 80%;
        display: inline-block;
    }

    .asState .state-block-right {
        display: inline;
        /*width: 20%;*/
    }

    .asState-li-margin {
        /*margin-right: 30px;*/
    }

</style>
<body>
<header class="header">
    <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<p class="bread-crumb w1080">
    <i class="icon-homepage"></i>
    <label>
        <a href="index.htm">
            首页
        </a>
    </label>
    <label>
        申请售后
    </label>
</p>

<div class="content">
    <div class="shopping-cart-body w1080">
        <div class="asState">
            <div class="ul" style="display: flex">
                <%--                <div class="li asState-li-margin">--%>
                <div class="li state-block-left ased"><span>买家申请售后</span></div>
                <div class="arrowed state-block-right"></div>
                <%--                </div>--%>
                <%--                <div class="li asState-li-margin">--%>
                <div class="li state-block-left aftering"><span>商家处理售后申请操作</span></div>
                <div class="arrowing state-block-right"></div>
                <%--                </div>--%>
                <%--                <div class="li">--%>
                <div class="li state-block-left aftering"><span>售后完成</span></div>
                <%--                </div>--%>
            </div>
        </div>
        <div class="shopping-cart-content">
            <div class="hd">
                <ul style="width: 100%; line-height: 27px">
                    <%--                    <tr>--%>
                    <li style="width: 45%">
                        <div style="display: inline;width: 60%;">
                            <div class="hd-left">
                                <label class="l32">货品</label>
                            </div>
                        </div>
                    </li>
                    <li class="td-text-align" style="width: 10%">
                        <div class="hd-left">
                            <%--                                <span class="hd-middle">--%>
                            <label>单价（元）</label>
                            <%--                                </span>--%>
                        </div>
                    </li>
                    <li class="td-text-align" style="">
                        <div class="hd-left">
                            <%--                                 <span class="hd-middle">--%>
                            <label>数量</label>
                            <%--                                 </span>--%>
                        </div>
                    </li>
                    <li class="td-text-align" style="width: 10%;padding-left: 5%;">
                        <div style="display: inline;width: 60%;">
                            <div class="hd-right" style="display: inline;">
                                <%--                    <label>单位</label>--%>
                                <label>单位</label>
                            </div>
                        </div>
                    </li>
                    <li class="td-text-align" style="width: 10%;padding-left: 1%;text-align: center">
                        <div style="display: inline;width: 60%;">
                            <div class="hd-right" style="display: inline;">
                                <%--                    <label>单位</label>--%>
                                <label>金额</label>
                            </div>
                        </div>
                    </li>
                    <li class="td-text-align" style="margin-left: 40px;%">
                        <div style="display: inline;width: 60%;">
                            <div class="hd-right" style="display: inline;">
                                <label>已发货/已收货</label>
                            </div>
                        </div>
                    </li>
                    <li class="td-text-align" style="margin-left: 34px;">
                        <div style="display: inline;width: 60%;">
                            <div class="hd-right" style="display: inline;">
                                <label>交易状态</label>
                            </div>
                        </div>
                    </li>
                    <%--                    </tr>--%>
                </ul>
            </div>
            <div>
                <div id="order" class="order1">
                    <div id="allDiv" style="line-height: 40px;padding-left: 20px;display: none">
                        <input type="checkbox" id="all" onclick="selectAll(this)" value="全选"><span
                            style="margin-left: 4px">全选</span>
                    </div>
                    <div id="order-header" class="order-header"></div>
                    <%@include file="/jsp/util/aftersale/aslistutil.jsp" %>
                    <div style="margin-top: 20px; margin-left: 20px">
                        <strong>合计：您共计申请
                            <span id="asCout">
                                ${asCount != null ? asCount : 0}
                        </span>
                            件商品，总金额为
                            <span id="asMoney" style="color: red">￥
                                <fmt:formatNumber type="number" value="${allMoney != null ? allMoney : '0.00'}" pattern="0.00"
                                                  maxFractionDigits="2"/>
                                </span>
                            元
                        </strong>
                    </div>
                    <div class="pagination w1080 m-pagenum pagination" id="Pagination"></div>
                    <div id="aftersale" class="aftersale">
                        <div id="astype" class="astype">
                            <span>选择售后类型<strong
                                    style="color: red;margin-left: 5px; font-size: 20px; vertical-align: text-top;">*</strong>
                            </span>
                            <div class="form-inline" style="display: inline-block; margin-top: 10px">
                                <label id="refundLabel"
                                       class="radiolabel radio-info radio-inline form-control" for="refund"
                                       style="height: auto;"><input type="radio"
                                                                    id="refund"
                                                                    name="payRadio"
                                                                    value="2"
                                                                    style="margin-top: 40px;width: 0px;"/><img
                                        height="80px" src="/dentistmall/img/payback.png"/>我要退款<br/>未收到货，需要退款</label>
                                <label id="returnsLabel"
                                       class="radiolabel radio-info radio-inline form-control" style="height: auto;"
                                       for="returns"><input type="radio"
                                                            id="returns"
                                                            name="payRadio"
                                                            value="1"
                                                            style="margin-top: 40px;width: 0px;"/><img
                                        height="80px"
                                        src="/dentistmall/img/payreturn.png"/>我要退货退款<br/>已收到货，需要退换已收到的货物</label>
                            </div>

                        </div>
                        <form id="form">
                            <table>
                                <tr id="moneyTr" style="">
                                    <td style="vertical-align: baseline;">退款金额：<strong
                                            style="color: red;margin-left: 5px; font-size: 20px; vertical-align: text-top;">*</strong>
                                    </td>
                                    <td>
                                        <div class="input-group" style="width: 100%">
                                            <span class="input-group-addon">￥</span>

                                            <input id="refundMoneInp" class="form-control" style="color: red"
                                                   name="after_sale_money_show"
                                                   value="<fmt:formatNumber type='number' value="${obj.afterSaleMoney != null ? obj.afterSaleMoney : '0.00'}" pattern='0.00'
                                                                                                      maxFractionDigits='2'/>"/>
                                        </div>
                                        <input id="refundMoneInp1" type="hidden" class="form-control" style="color: red"
                                               name="after_sale_money"
                                               value=""/>
                                        <br/>
                                        <span style="color: lightgrey">最多￥<span
                                                id="maxMoney">0.00</span>，含发货邮费<span
                                                id="expressMoney">￥0.00</span></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="vertical-align: baseline;">申请说明：</td>
                                    <td width="90%"><textarea id="remarksInp" class="form-control" style="width: 100%;"
                                                              name="remarks"
                                                              placeholder="选填，100字以内，请简单描述下原因"
                                                              maxlength="100"
                                                              value=""><c:if
                                            test="${obj != null}">${obj.remarks}</c:if></textarea></td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <div style="background-color: yellow;color: grey;text-align: center;padding: 10px">
                                            请仔细确认您的退款金额，一旦卖家同意该退款协议，<span id="reMoney" style="color: red">￥0.00</span>元退款金额将转入您的付款账户（月结用户不在计算订单费用）
                                        </div>
                                    </td>
                                </tr>
                            </table>
                            <div id="asImg" class="asImg">
                                <div class="ibox float-e-margins" style="overflow:hidden;margin-top:5px">
                                    <div class="ibox-title" style="border:0px">
                                        <h5>&nbsp&nbsp上传图片<span style="color: lightgrey;margin-left: 5px">格式：png,jpg,gif;大小:不超过3M;最多上传三张图片</span><strong
                                                style="color: red;margin-left: 5px; font-size: 20px; vertical-align: text-top;"></strong>
                                        </h5>
                                    </div>
                                    <div class="ibox-content">
                                        <div class="form-group" styel="">
                                            <div class="awards-imgs">
                                                <ul class="imgul f-cb" id="imglist">
                                                </ul>
                                            </div>
                                        </div>
                                    </div>

                                    <input type="hidden" id="lessenFilecode" name="lessenFilecode"/>
                                    <input type="hidden" id="listFilecode" name="listFilecode"
                                           value="<c:if test="${obj.asImg1Path != null}">${obj.asImg1}</c:if><c:if test="${obj.asImg2 != null}">,${obj.asImg2}</c:if><c:if test="${obj.asImg3 != null}">,${obj.asImg3}</c:if>"/>
                                </div>
                            </div>
                        </form>
                        <div style="text-align: right">
                            <a class="btn btn-white" style="background-color: grey; color: white;width: 82px;height: 34px"  href="javascript:;"onclick="applyAs(1)">取消</a>
                            <a class="btn btn-primary" id="applyAs" href="javascript:;" onclick="applyAs(2)">提交售后</a>
                        </div>
                    </div>
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
                <a href="javascript:checkSecurityCodeFunc()" class="fl btn ui-btn-theme" style="color:#fff;" >确认</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<input type="hidden" id="moiId" value="${moiId}">
<input type="hidden" id="masCode" value="${masCode}">
<input type="hidden" id="masId" value="${masId}">
<input type="hidden" id="afterSale" value="${obj != null ? obj.afterSale : ''}">
<input type="hidden" id="newAs" value="${newAs}">
<input type="hidden" id="momIds" value="${momIds}">

</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/orderaftersale/asapply.js?v=17' />"></script>
