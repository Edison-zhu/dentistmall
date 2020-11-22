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
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp" %>

</head>
<script>
    var _isFix = 1;
    var _isCar = 2;
</script>
<style>
    .span-font {
        font-weight: bold;
        font-size: 15px;
    }

    span {
        padding: 10px;
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
        display: block;
        float: left;
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

    .mx-div {
        padding: 5px;
        line-height: 25px;
        border-bottom: lightgrey 1px solid;
        border-top: lightgrey 1px solid;
    }

    .order-body table td {
        vertical-align: top;
    }

    .order-info {
        height: auto !important;
        font-size: 12px !important;
        /*width: 150px!important;*/
    }

    .order-info ul li {
        width: 200px;
        word-wrap: break-word;
    }

    .money {
        font-size: 12px !important;
    }

    .td-text-align .order-info span {

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
<link rel="stylesheet" href="<c:url value='/css/shopping/css/confirmOrder.css?v=57' />">
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<script src="<c:url value='/js/plugins/layui/layui.all.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?v=2' />">
<link rel="stylesheet" href="<c:url value='/jsp/util/orderlistutil.css?v=3' />">
<script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
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
        售后明细
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
                        <span class="span-line-height span-as-color">您已经取消售后申请。</span>
                    </c:if>
                    <c:if test="${obj.asState == 5}"> <!-- 申请后还未处理 -->
                        <span class="span-line-height span-as-color">您已经成功发起申请售后，请耐心等待商家处理。</span>
                    </c:if>
                    <c:if test="${obj.asState == 1}"> <!-- 退货中 -->
                        <span class="span-line-height span-as-color">商家已经同意退货申请，请将商品退回商家。</span>
                    </c:if>
                    <c:if test="${obj.asState == 2}"> <!-- 退款中 -->
                        <span class="span-line-height span-as-color">商家已经同意退款申请，请耐心等待售后退款。</span>
                    </c:if>
                    <c:if test="${obj.asState == 3}"> <!-- 售后完成 -->
                        <span class="span-line-height span-as-color">您申请的售后已经完成。</span>
                    </c:if>
                    <c:if test="${obj.asState == 4}"> <!-- 售后拒绝 -->
                        <span class="span-line-height span-as-color">商家拒绝了您的售后申请。<br></span>
                        <c:if test="${obj.refuse != null}">
                            <span class="span-line-height span-as-color">
                                拒绝理由：${obj.refuse}
                            </span>
                        </c:if>
                    </c:if>
                    <c:if test="${obj.asState == 6}"> <!-- 撤销售后 -->
                        <span class="span-line-height span-as-color">您已成功撤销申请售后，若后续有问题仍可联系商家协商。</span>
                    </c:if>
                </div>
                <div class="as-ctl bottom-border"> <!-- 这里需要判断状态，显示不同的信息 -->
                    <c:if test="${obj.asState == 6}"> <!-- 撤销售后 -->
                        <strong>您还可以：</strong>
                        <a class="trigger" style="color: blue"
                           href="ddxq.htm?moiId=${obj.moiId}" target="_blank">查看订单</a>
                    </c:if>
                    <c:if test="${obj.asState == 5}"> <!-- 申请后还未处理 -->
                        <a class="btn btn-primary" href="javascript:editAppli('${obj.moiId}','${obj.masId}','${obj.masCode}',1)">修改申请</a>
                        <a class="" style="margin-left: 50px;text-decoration: underline; color: blue"
                           href="javascript:editAppli('${obj.moiId}',2);">取消申请</a>
                    </c:if>
                    <c:if test="${obj.asState == 1}"> <!-- 退货中 -->
                        <a class="btn btn-primary" style="float: right;"
                           href="javascript:editAppli('${obj.moiId}',2);">取消申请</a>
                        <div style="line-height: 40px">
                            <span class="span-font">退货地址：</span><br/>
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
                        <div>
                            <div style="display: inline-block; width: 300px">
                                <input type="checkbox" id="expressSelect" name="expressSelect" checked="checked"/>退货物流信息(选择后则需要填写物流信息)
                            </div>
                            <div style="display: inline-block; text-align: right">
                                <a id="applyAsAddress1" class="btn btn-primary" href="javascript:;"
                                   onclick="applyASAddress()" style="display: none">提交售后</a>
                            </div>
                        </div>
                        <div id="expressInfo">
                            <form id="expressForm" class="form-inline">
                                <label>物流公司：</label>
                                <c:if test="${obj.expressName == null}">
                                    <input id="expressName" class="form-control" name="expressName" value=""/>
                                    <span id="expressNameSpan" style="display: none">无</span>
                                </c:if>
                                <c:if test="${obj.expressName != null}">
                                    <input id="expressName" class="form-control" name="expressName"
                                           style="display:none;" value=""/>
                                    <span id="expressNameSpan">${obj.expressName == '' ? '无' : obj.expressName}</span>
                                </c:if>

                                <label style="margin-left: 20px">物流单号：</label>
                                <c:if test="${obj.expressCode == null}">
                                    <input id="expressCode" class="form-control" name="expressCode" value=""/>
                                    <span id="expressCodeSpan" style="display: none">无</span>
                                </c:if>
                                <c:if test="${obj.expressCode != null}">
                                    <input id="expressCode" class="form-control" name="expressCode"
                                           style="display: none" value=""/>
                                    <span id="expressCodeSpan">${obj.expressCode == '' ? '无' : obj.expressCode}</span>
                                </c:if>
                            </form>
                            <c:if test="${obj.expressName == null && obj.expressCode == null}">
                                <a id="applyAsAddress" class="btn btn-primary" href="javascript:;"
                                   onclick="applyASAddress()">提交售后</a>
                                <a id="cancelAsAddress" class="btn btn-primary" href="javascript:;"
                                   onclick="cancelASAddress()" style="display: none">取消</a>
                                <a id="editAsAddress" class="btn btn-primary" href="javascript:;"
                                   onclick="editASAddress()" style="display: none">编辑</a>
                            </c:if>
                            <c:if test="${obj.expressName != null || obj.expressCode != null}">
                                <a id="applyAsAddress" class="btn btn-primary" href="javascript:;"
                                   onclick="applyASAddress()" style="display: none">提交售后</a>
                                <a id="cancelAsAddress" class="btn btn-primary" href="javascript:;"
                                   onclick="cancelASAddress()" style="display: none">取消</a>
                                <a id="editAsAddress" class="btn btn-primary" href="javascript:;"
                                   onclick="editASAddress()">编辑</a>
                            </c:if>
                        </div>
                    </c:if>
                    <c:if test="${obj.asState == 2}"> <!-- 退款中 -->
                        <span>商家退款中……</span>
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
                        <a class="btn btn-primary"
                           href="asapply.htm?moiId=${obj.moiId}&masId=${obj.masId}&masCode=${obj.masCode}">再次申请售后</a>
                    </c:if>
                </div>
                <div class="as-type bottom-border">
                    <span class="span-font">售后类型及协议:</span>
                    <table>
                        <tr>
                            <td>售后服务：</td>
                            <td>
                                ${obj.afterSaleName}
                            </td>
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
                                <span style="color: red">￥<fmt:formatNumber type="number" value="${obj.afterSaleMoney}"
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
                    <div><span class="span-font">售后商品列表<a class="btn btn-success" href="javascript:;"
                                                          onclick="exportLierAsmx()"
                                                          style="float: right;">导出售后商品</a></span></div>
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
                                                <label>单价(元)</label>
                                            </span>
                                        </div>
                                    </th>
                                    <th class="td-text-align" style="width: 5%;text-align: center">
                                        <div>
                                             <span class="hd-middle">
                                                 <label>数量</label>
                                             </span>
                                        </div>
                                    </th>
                                    <th class="td-text-align" style="width: 5%">
                                        <div style="display: inline;width: 60%;">
                                            <div class="hd-right" style="display: inline;">
                                                <label>单位</label>
                                            </div>
                                        </div>
                                    </th>
                                    <th class="td-text-align" style="width: 7%;text-align: center">
                                        <div style="display: inline;width: 60%;">
                                            <div class="hd-right" style="display: inline;">
                                                <label>金额</label>
                                            </div>
                                        </div>
                                    </th>
                                    <%--                                    <th class="td-text-align" style="width: 15%;text-align: center">--%>
                                    <%--                                        <div style="display: inline;width: 60%;">--%>
                                    <%--                                            <div class="hd-right" style="display: inline;">--%>
                                    <%--                                                <label>发货/总数</label>--%>
                                    <%--                                            </div>--%>
                                    <%--                                        </div>--%>
                                    <%--                                    </th>--%>
                                    <th class="td-text-align" style="width: 18%;text-align: center;padding-left: 27px;">
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
                        <div class="pagination m-pagenum pagination" style="display: inline" id="Pagination"></div>
                    </div>

                </div>
            </div>
            <div class="content-right">
                <div class="supplier supplier-content bottom-border">
                    <div class="as-supplier">
                        您还可以联系商家：<br>

                        商家名称：<a href="pcjxiangxi.htm?wzId=${mdSupplier.wzId}"
                                target="_blank">${supplier.applicantName}</a><br>

                        联系人姓名：${supplier.legalPerson}<br>

                        联系人方式：${supplier.phoneNumber}<br>

                        订单号：${obj.orderCode}
                    </div>
                    <div class="as-supplier-img">
                        <img height="70px" width="70px" src="${supplier.logoFilePath}">
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
                        <strong>售后类型:</strong> ${obj.afterSaleName}
                        <br/>
                        <strong>退款金额:</strong> <span style="color: red;">￥<fmt:formatNumber type="number"
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
                    <div style="text-align: right;margin-top: 10px">
                        <div class="pagination m-pagenum pagination" style="display: inline" id="AsPagination"></div>
                    </div>
                </div>
            </div>
            <div style="clear: both"></div>
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
</div>

<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<input type="hidden" id="moiId" value="${obj.moiId}">
<input type="hidden" id="masId" value="${obj.masId}">
<input type="hidden" id="masCode" value="${obj.masCode}">
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/orderaftersale/asmx.js?v=24' />"></script>
