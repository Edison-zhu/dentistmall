<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2019/12/28
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>牙医商城平台</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
</head>
<style>
    .span-font {
        font-weight: bold;
        font-size: 15px;
    }

    span {
        padding: 0px;
    }

    .span-line-height {
        line-height: 70px;
    }

    .bottom-border {
        border-bottom: #c8c8c8 solid 1px;
    }

    .content-left {
        vertical-align: top;
        display: inline-block;
        width: 70%;
        border-right: #c8c8c8 solid 1px;
    }

    .content-right {
        width: 30%;
        float: right;
    }

    .as-info {
        min-height: 100px;
        padding: 10px;
    }

    .as-ctl {
        min-height: 100px;
        padding: 10px;
        line-height: 80px;
    }

    .as-type {
        padding: 10px;
        height: auto;
    }

    .as-type table {
        margin-top: 10px;
        line-height: 22px;
    }

    .as-list {
        height: auto;
        padding: 10px;
    }

    .supplier-content {
        height: 200px;
    }

    .as-supplier {
        display: inline-block;
        float: left;
        line-height: 30px;
        padding: 10px;
    }

    .supplier-address li {
        float: left;
        display: block;
        padding: 10px;
    }

    .as-supplier-img {
        display: inline-block;
        float: left;
        margin-left: 10px;
        margin-top: 10px;
    }

    .as-mx {
        padding: 10px;
        line-height: 22px;
    }

    .as-mx img {
        margin-top: 10px;
    }

    .order-body table td {
        vertical-align: top;
    }

    .mx-div {
        padding: 5px;
        line-height: 25px;
        border-bottom: lightgrey 1px solid;
        border-top: lightgrey 1px solid;
    }

    .order-info {
        height: auto !important;
        font-size: 12px;
        /*width: 150px!important;*/
    }

    .order-info ul li {
        width: 150px;
        word-wrap: break-word;
    }

    .money {
        font-size: 12px !important;
    }

    .td-text-align .order-info span {

    }

    .w1080 {
        margin: 0 auto;
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

    .span-as-color {
        color: red;
        font-size: 19px;
        font-weight: bold;
    }
</style>
<link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?13' />">
<script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/modules/layer/default/layer.css?v=1'/>">
<script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
<script src="<c:url value="/js/plugins/layui/lay/modules/layer.js" />"></script>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?v=2' />">
<link rel="stylesheet" href="<c:url value='/jsp/util/orderlistutil.css?v=3' />">
<script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox-title">
        <h5>
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>售后明细信息</span>
        </h5>
    </div>
    <div class="ibox-content">
        <div class="shopping-cart-body w1080" style="width: 1080px; margin: 0 auto;">
            <div class="asState">
                <div class="ul" style="display: flex">
                    <%--                <div class="li asState-li-margin">--%>
                    <div class="li state-block-left ased"><span>买家申请售后</span></div>
                    <div class="arrowed state-block-right"></div>
                    <%--                </div>--%>
                    <%--                <div class="li asState-li-margin">--%>
                    <div class="li state-block-left ased"><span>商家处理售后申请操作</span></div>
                    <div class="arrowed state-block-right"></div>
                    <%--                </div>--%>
                    <%--                <div class="li">--%>
                    <c:if test="${obj.asState != 3 && obj.asState != 4 && obj.asState != 6}">
                        <div class="li state-block-left aftering"><span>售后完成</span></div>
                    </c:if>
                    <c:if test="${obj.asState == 3 || obj.asState == 4 || obj.asState == 6}">
                        <div class="li state-block-left ased"><span>售后完成</span></div>
                    </c:if>
                    <%--                </div>--%>
                </div>
            </div>
            <div class="shopping-cart-content">
                <div class="content-left">
                    <div class="as-info bottom-border">
                        <span class="span-font">售后单号:${obj.masCode}</span><br>
                        <c:if test="${obj.asState == null}"> <!-- 申请后还未处理 -->
                            <span class="span-line-height span-as-color">买家已经取消售后申请。</span>
                        </c:if>
                        <c:if test="${obj.asState == 5}"> <!-- 申请后还未处理 -->
                            <span class="span-line-height span-as-color">买家已经发起售后申请，请处理。</span>
                        </c:if>
                        <c:if test="${obj.asState == 1}"> <!-- 退货中 -->
                            <span class="span-line-height span-as-color">您已经同意退货申请，请耐心等待收货。</span>
                        </c:if>
                        <c:if test="${obj.asState == 2}"> <!-- 退款中 -->
                            <span class="span-line-height span-as-color">已收货，请处理后续。</span>
                        </c:if>
                        <c:if test="${obj.asState == 3}"> <!-- 售后完成 -->
                            <span class="span-line-height span-as-color">此次售后申请已结束。</span>
                        </c:if>
                        <c:if test="${obj.asState == 4}"> <!-- 售后拒绝 -->
                            <span class="span-line-height span-as-color">您已拒绝售后服务，请联系买家并提供好服务，以防被投诉。</span>
                            <br>
                            <c:if test="${obj.refuse != null}">
                                <span class="span-line-height span-as-color">
                                    拒绝理由：${obj.refuse}
                                </span>
                            </c:if>
                        </c:if>
                    </div>
                    <div class="as-ctl bottom-border"> <!-- 这里需要判断状态，显示不同的信息 -->
                        <c:if test="${obj.afterSale == '1'}"> <!-- 退货 -->
                            <c:if test="${obj.asState == 5}">
                                <div style="border-bottom: lightgrey solid 1px;">
                                    <a class="btn btn-primary" href="javascript:;" onclick="agreeReturnBack()">同意退货</a>
                                    <a class="btn btn-white" href="javascript:;" onclick="refuseAs()">拒绝申请</a>
                                </div>
                            </c:if>
                        </c:if>
                        <c:if test="${obj.afterSale == '2'}"> <!-- 退款 -->
                            <c:if test="${obj.asState == 5}">
                                <div style="border-bottom: lightgrey solid 1px;">
                                    <a class="btn btn-primary" href="javascript:;" onclick="agreeRefund()">同意退款</a>
                                    <a class="btn btn-white" href="javascript:;" onclick="refuseAs()">拒绝申请</a>
                                </div>
                            </c:if>
                        </c:if>

                        <c:if test="${obj.asState == 5}"> <!-- 申请后还未处理 -->
                            卖家已发起售后申请，请处理。
                            <a class="layui-btn layui-btn-primary" href="javascript:;"
                               onclick="viewOrder(${obj.moiId})">查看订单</a>
                        </c:if>
                        <c:if test="${obj.asState == 1}"> <!-- 退货中 -->
                            <div style="line-height: 40px">
                                <div style="display: inline-block;width: 200px">
                                    <span class="span-font">退货地址：</span><br/>
                                </div>
                                <div style="display:inline-block;text-align: right">
                                    <button type="button" class="btn btn-primary" id="editAddressBtn"
                                            onclick="editASAddress()">修改地址
                                    </button>
                                </div>
                                <ul class="supplier-address">
                                    <li>
                                        收货人： ${obj.supplierName}
                                    </li>
                                    <li>
                                        电话： ${obj.supplierPhone}
                                    </li>
                                    <li>
                                        地址： ${obj.supplierAddress}
                                    </li>
                                </ul>
                            </div>
                            <div style="border-bottom: lightgrey solid 1px;clear: both">
                            </div>
                            <div id="expressForm" class="form-inline" style="clear: both">
                                <c:if test="${obj.expressName != null && obj.expressName != ''}">
                                    <label>物流公司：</label>
                                    <span id="expressNameSpan">${obj.expressName}</span>
                                    <label>物流单号：</label>
                                    <span id="expressCodeSpan">${obj.expressCode}</span>
                                </c:if>
                            </div>
                            <c:if test="${obj.expressName != null && obj.expressCode != null}">
                                <a id="applyAsAddress" class="btn btn-primary" style="margin-top: 20px"
                                   href="javascript:;"
                                   onclick="ensureGet()">确认收货</a>
                            </c:if>
                        </c:if>
                        <c:if test="${obj.asState == 2}"> <!-- 退款中 -->
                            <span>商家退款中……</span><br/>
                            <a id="applyAsAddress" class="btn btn-primary" href="javascript:;" onclick="ensureRefund()">确认已退款</a>
                        </c:if>
                        <c:if test="${obj.asState == 3}"> <!-- 售后完成 -->
                            <table>
                                <tr>
                                    <td>退款金额：</td>
                                    <td>
                                        <span style="color: red">￥<fmt:formatNumber type="number"
                                                                                    value="${obj.afterSaleMoney}"
                                                                                    pattern="0.00"
                                                                                    maxFractionDigits="2"/></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>物流公司：</td>
                                    <td>${obj.expressName}</td>
                                </tr>
                                <tr>
                                    <td>物流单号：</td>
                                    <td>${obj.expressCode}</td>
                                </tr>
                            </table>
                        </c:if>
                        <c:if test="${obj.asState == 4}"> <!-- 售后拒绝 -->
                            <span>您还可以：</span><a class="btn btn-primary" href="javascript:;"
                                                 onclick="viewOrder(${obj.moiId})">查看订单</a>
                        </c:if>
                    </div>
                    <div class="as-type bottom-border">
                        <span class="span-font">售后类型及协议:</span>
                        <table style="font-size: 14px">
                            <tr>
                                <td>售后服务：</td>
                                <td>${obj.afterSaleName}</td>
                            </tr>
                            <tr>
                                <td>申请售后时间：</td>
                                <td>${fn:substring(obj.createDate, 0, 19)}</td>
                            </tr>
                            <tr>
                                <td>售后状态：</td>
                                <td>
                                    <c:if test="${obj.asState == 5}"> <!-- 申请还未处理 -->
                                        <span>退款协议等待卖家确认</span>
                                    </c:if>
                                    <c:if test="${obj.asState != 5}">
                                        ${obj.asStateName}
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td>买家是否已收到货：</td>
                                <td><span id="isRecieve">--</span></td>
                            </tr>
                            <tr>
                                <td>售后金额：</td>
                                <td>
                                    <span style="color: red">￥<fmt:formatNumber type="number"
                                                                                value="${obj.afterSaleMoney}"
                                                                                pattern="0.00"
                                                                                maxFractionDigits="2"/></span>
                                </td>
                            </tr>
                            <tr>
                                <td>售后说明：</td>
                                <td>${obj.remarks}</td>
                            </tr>
                        </table>
                    </div>
                    <div class="as-list">
                        <div>
                            <span class="span-font">售后商品列表<a class="btn btn-success" href="javascript:;"
                                                             onclick="exportLierAsmx()" style="float: right;">导出售后商品</a></span>
                        </div>
                        <div class="order1">
                            <div class="order-header">
                                <table style="width: 100%; line-height: 37px;font-size: 12px">
                                    <tr>
                                        <th style="width: 33%">
                                            <div style="display: inline;width: 60%;">
                                                <div class="hd-left">
                                                    <label class="l32">货品</label>
                                                </div>
                                            </div>
                                        </th>
                                        <th class="td-text-align" style="width: 7%">
                                            <div>
                                            <span class="hd-middle">
                                                <label>单价（元）</label>
                                            </span>
                                            </div>
                                        </th>
                                        <th class="td-text-align" style="width: 7%">
                                            <div>
                                             <span class="hd-middle">
                                                 <label>数量</label>
                                             </span>
                                            </div>
                                        </th>
                                        <th class="td-text-align" style="width: 6%;text-align: center">
                                            <div style="display: inline;width: 60%;">
                                                <div class="hd-right" style="display: inline;">
                                                    <label>单位</label>
                                                </div>
                                            </div>
                                        </th>
                                        <th class="td-text-align" style="width: 10%;text-align: center">
                                            <div style="display: inline;width: 60%;">
                                                <div class="hd-right" style="display: inline;">
                                                    <label>金额</label>
                                                </div>
                                            </div>
                                        </th>
                                        <th class="td-text-align" style="width: 9%;text-align: center">
                                            <div style="display: inline;width: 60%;">
                                                <div class="hd-right" style="display: inline;">
                                                    <label>已发货/已收货</label>
                                                </div>
                                            </div>
                                        </th>
                                        <th class="td-text-align" style="width: 8%;text-align: center">
                                            <div style="display: inline;width: 60%;">
                                                <div class="hd-right" style="display: inline;">
                                                    <label>状态</label>
                                                </div>
                                            </div>
                                        </th>
                                    </tr>
                                </table>
                            </div>
                            <%@include file="/jsp/util/aftersale/aslistutil.jsp" %>
                        </div>
                        <div style="text-align: right">统计金额：<span id="totalMoney" style="color: red">￥<fmt:formatNumber
                                type="number" value="${obj.afterSaleMoney}" pattern="0.00"
                                maxFractionDigits="2"/></span></div>
                        <div style="text-align: right;margin-top: 10px">
                            <div class="pagination w1080 m-pagenum pagination" id="Pagination"></div>
                        </div>

                    </div>
                </div>
                <div class="content-right">
                    <div class="supplier supplier-content bottom-border">
                        <div class="as-supplier">
                            您还可以联系买家：<br>

                            买家名称：<a href="javascript:;">${obj.createNickname}</a><br>

                            联系人姓名：${obj.createName}<br>

                            联系人方式：${obj.createPhone}<br>

                            订单号：${obj.orderCode}
                        </div>
                    </div>
                    <div class="as-mx">
                        <div style="border-bottom: solid grey 2px">
                            <span class="span-font">售后明细</span>
                            <br/>
                            <strong>申请时间：</strong>${fn:substring(obj.createDate, 0, 19)}
                            <br/>
                            <strong>${obj.createName}：</strong>于<strong
                                style="color: red">${fn:substring(obj.createDate, 0, 19)}</strong>创建了${obj.afterSaleName}申请
                            <br/>
                            <strong>售后类型: </strong>${obj.afterSaleName}
                            <br/>
                            <strong>退款金额: </strong><span style="color: red;">￥<fmt:formatNumber type="number"
                                                                                                value="${obj.afterSaleMoney}"
                                                                                                pattern="0.00"
                                                                                                maxFractionDigits="2"/></span>
                            <br/>
                            <strong>要求：</strong>${obj.afterSaleName}
                            <br/>
                            <strong>申请说明：</strong>${obj.remarks}
                            <br/>
                            <c:if test="${obj.asImg1Path != null}">
                                <img height="100px" width="100px" src="${obj.asImg1Path}">
                            </c:if>
                            <c:if test="${obj.asImg2Path != null}">
                                <img height="100px" width="100px" src="${obj.asImg2Path}">
                            </c:if>
                            <c:if test="${obj.asImg3Path != null}">
                                <img height="100px" width="100px" src="${obj.asImg3Path}">
                            </c:if>
                        </div>
                        <div style="margin-top: 20px"><span class="span-font">操作明细</span></div>
                        <div id="asList">

                        </div>
                        <div style="text-align: right">
                            <div class="pagination m-pagenum pagination" id="AsPagination"></div>
                        </div>
                    </div>
                </div>
                <div style="clear: both"></div>
            </div>
        </div>
    </div>
</div>
<input type="hidden" id="moiId" value="${obj.moiId}">
<input type="hidden" id="masId" value="${obj.masId}">
<input type="hidden" id="masCode" value="${obj.masCode}">
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/transaction/orderaftersale/supplierasmx.js?v=21' />"></script>

