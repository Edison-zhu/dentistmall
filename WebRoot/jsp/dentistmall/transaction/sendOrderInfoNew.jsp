<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>AI平台系统</title>
    <meta charset="UTF-8">
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <%--    <script src="<c:url value='/javaScript/dentistmall/transaction/viewSupplierorderInfo.js?v=24'/>"></script>--%>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?13' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/css/shopping/css/confirmOrder.css?v=4' />">
    <link rel="stylesheet" href="<c:url value='/jsp/util/ordermxutil/ordermxutil.css?v=3' />">
    <script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
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

    #export1 {
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

    .a {
        display: inline;
        padding: 3px 6px;;
        border: 1px lightgrey solid;
        text-align: center;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none
    }

    .divFilter {
        display: none;
        float: left;
        margin: 0 auto;
        position: absolute;
        /*top: 20%;*/
        /*left: 20%;*/
        width: 50%;
        background-color: whitesmoke;
        z-index: 901;
        height: 380px;
    }

    .u-mask {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 900;
        width: 100%;
        height: 100%;
        background-color: #000000;
        filter: alpha(opacity=80);
        -moz-opacity: 0.8;
        -khtml-opacity: 0.8;
        opacity: 0.5;
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
        <div class="ibox-tools" id="win_tools_but">
            <a onclick="main_export()" class='btn btn-success  btn-xs' style="color:#fff" id="button2">导出详情</a>
        </div>
    </div>
    <div class="ibox-content">
        <div style="width: 1080px; margin: 0 auto;">
            <div class="content">
                <div class="shopping-cart-body w1080">
                    <div class="shopping-cart-header">
                        <div class="shopping-flow">
                            <div class="shopping-cart-icon">
                                <img src="<c:url value='/css/shopping/images/shopping-list.png'/>">
                                <span class="cn">订单信息：</span>
                                <span class="un">View order info</span>
                            </div>
                        </div>
                    </div>
                    <div class="shopping-cart-content">
                        <div>
                            <div style="margin-left: 3%;float: left; margin-top: 0px;font-size: 20px">
                                <b>订单状态：${obj.processStatusName}</b></div>
                            <div style="float: right;margin-right: 4%;text-align: center">
                                <c:if test="${sessionScope.sessionUser.organizaType != '20001' && sessionScope.sessionUser.organizaType != '20002' && sessionScope.sessionUser.organizaType != '20003'}">
                                    <a onclick="main_exportC()"
                                       class='btn btn-primary  btn-xs'
                                       style="color:#fff" id="export1">导出配送单</a>
                                    <br/>
                                </c:if>
                                <c:if test="${sessionScope.sessionUser.organizaType != '20001' && sessionScope.sessionUser.organizaType != '20002' && sessionScope.sessionUser.organizaType != '20003'}">
                                    <c:if test="${obj.processStatus == 1}"> <!-- 待发货 -->
                                        <%--                                        <a class="btn btn-primary" onclick="sendMat('${obj.moiId}')">立即发货</a><br/>--%>
                                    </c:if>
                                    <c:if test="${obj.processStatus == 2}"> <!-- 待付款 -->
                                        等待买家付款……
                                    </c:if>
                                    <c:if test="${obj.processStatus == 3}"> <!-- 待发货 -->
                                        等待买家确认收货……
                                    </c:if>
                                    <c:if test="${obj.processStatus == 4}"> <!-- 已发货 -->
                                        等待买家确认收货……
                                    </c:if>
                                    <c:if test="${obj.processStatus == 5}"> <!-- 交易成功 -->
                                        <%--                            <a class="btn btn-primary" style="margin-top: 10px;"--%>
                                        <%--                               href="javascript=buyAgain(${obj.moiId})">再次购买</a>--%>
                                    </c:if>
                                    <c:if test="${obj.processStatus == 6}"> <!-- 交易失败 -->
                                        <%--                            <a class="btn btn-primary" style="margin-top: 10px;"--%>
                                        <%--                               href="javascript=buyAgain(${obj.moiId})">再次购买</a>--%>
                                    </c:if>
                                    <c:if test="${obj.processStatus == 7}"> <!-- 售后 -->
                                        <a class="btn btn-primary" style="margin-top: 10px;"
                                           onclick="viewSupplierOrderAs(${obj.moiId})">售后信息</a>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>

                        <div class="address-list">
                            <div class="address-items-info">
                                <div class="ellips_line" style="float:left;width:330px;padding-left:20px">
                                    订单号：${obj.orderCode}</div>
                                <div class="ellips_line" style="float:left;width:330px;padding-left:20px">
                                    供应商：${obj.applicantName}</div>
                                <div class="ellips_line" style="float:left;width:330px;padding-left:40px">
                                    订单状态：${obj.processStatusName}</div>
                                <div class="ellips_line" style="float:left;width:330px;padding-left:20px">
                                    下单时间：<fmt:formatDate
                                        value="${obj.placeOrderTime}" type="both"/></div>
                                <div class="ellips_line" style="float:left;width:330px;padding-left:20px">
                                    下单数量：${obj.commodityNumber}</div>
                                <div class="ellips_line" style="float:left;width:330px;padding-left:40px">订单金额：
                                    ¥<fmt:formatNumber type="number" value="${obj.placeOrderMoney}" pattern="0.00"
                                                       maxFractionDigits="2"/></div>
                                <div class="ellips_line" style="float:left;width:330px;padding-left:20px">
                                    完结时间：<fmt:formatDate
                                        value="${obj.endTime}" type="both"/></div>
                                <div class="ellips_line" style="float:left;width:330px;padding-left:20px">确认数量：
                                    <c:if test="${obj.number1!=null}"><fmt:formatNumber value="${obj.number1}"
                                                                                        pattern="#0"
                                                                                        maxFractionDigits="0"/></c:if>
                                    <c:if test="${obj.number1==null}">-</c:if>
                                </div>
                                <div class="ellips_line" style="float:left;width:330px;padding-left:40px">确认金额：
                                    <c:if test="${obj.actualMoney!=null}">¥<fmt:formatNumber type="number"
                                                                                             value="${obj.actualMoney}"
                                                                                             pattern="0.00"
                                                                                             maxFractionDigits="2"/></c:if>
                                    <c:if test="${obj.actualMoney==null}">-</c:if>
                                </div>
                            </div>
                            <div class="address-items">
                                <p><i class="icon-people"></i>${obj.addressee}</p>
                                <p>
                                    <i class="icon-address"></i>${obj.province}${obj.city}${obj.area}${obj.deliveryAddress}
                                </p>
                                <p><i class="icon-phone"></i>${obj.addresseeTelephone}</p>
                                <span class="font-weight-trigger">收货地址</span>
                            </div>
                            <div class="address-items" style="margin-left:14px">
                                <c:if test="${obj.needBill=='1'}">
                                    <p>抬头:${obj.billHeard}(<c:if test="${obj.billType=='1'}">个人</c:if><c:if
                                            test="${obj.billType=='2'}">商家</c:if>)</p>
                                    <c:if test="${obj.billType=='2'}">
                                        <p>纳税人识别号:${obj.billCode}</p>
                                    </c:if>
                                </c:if>
                                <c:if test="${obj.needBill!='1'}">
                                    <p>无</p>
                                </c:if>
                                <span class="font-weight-trigger">发票信息</span>
                            </div>
                            <div class="address-items" style="margin-left:15px">
                                <div style="text-align: center; margin-top: 26px;padding-left: 10px; margin-bottom: 4px">
                                    <div style="display: flex">
                                        <div style="flex: 1">运送方式</div>
                                        <div style="flex: 2">物流公司</div>
                                        <div style="flex: 2">运单号</div>
                                    </div>
                                </div>
                                <div style="border-top: 1px solid lightgrey"></div>
                                <div style="text-align: center; margin-top: 4px;padding-left: 10px">
                                    <c:if test="${obj.expressNameAndCode == null}">
                                        <c:if test="${obj.expressName != null&&obj.expressCode!=null&&obj.expressName!=undefined&&obj.expressCode!=undefined&&obj.expressName!=''&&obj.expressCode!=''}">
                                            <div style="display: flex">
                                                <div style="flex: 1">快递</div>
                                                <div style="flex: 2">
                                                    <c:if test="${obj.expressName!=null}">${obj.expressName}</c:if>
                                                    <c:if test="${obj.expressName==null}">-</c:if>
                                                </div>
                                                <div style="flex: 2">
                                                    <c:if test="${obj.expressCode!=null}">${obj.expressCode}</c:if>
                                                    <c:if test="${obj.expressCode==null}">-</c:if>
                                                </div>
                                            </div>
                                        </c:if>
                                        <c:if test="${obj.expressName == null||obj.expressCode==null||obj.expressName==undefined||obj.expressCode==undefined||obj.expressName==''||obj.expressCode==''}">
                                            <div style="display: flex">
                                                <div style="flex: 1">上门配送</div>
                                                <div style="flex: 2">
                                                    <c:if test="${obj.expressName!=null}">${obj.expressName}</c:if>
                                                    <c:if test="${obj.expressName==null}">-</c:if>
                                                </div>
                                                <div style="flex: 2">
                                                    <c:if test="${obj.expressCode!=null}">${obj.expressCode}</c:if>
                                                    <c:if test="${obj.expressCode==null}">-</c:if>
                                                </div>
                                            </div>
                                        </c:if>

                                    </c:if>
                                    <c:if test="${obj.expressNameAndCode != null}">
                                        <c:set var="nameAndCodes" value="${fn:split(obj.expressNameAndCode, ',')}"/>
                                        <c:forEach var="nameAndCode" items="${nameAndCodes}">
                                            <div style="display: flex">
                                                <c:set var="nameCode" value="${fn:split(nameAndCode, '#')}"/>
                                                <div style="flex: 1">上门配送</div>
                                                <div style="flex: 2">
                                                    <c:if test="${nameCode[0]!=null}">${nameCode[0]}</c:if>
                                                    <c:if test="${nameCode[0]==null}">-</c:if>
                                                </div>
                                                <div style="flex: 2">
                                                    <c:if test="${nameCode[1]!=null}">${nameCode[1]}</c:if>
                                                    <c:if test="${nameCode[1]==null}">-</c:if>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:if>
                                </div>
                                <span class="font-weight-trigger">物流信息 </span>
                            </div>
                        </div>
                        <div style="clear: both">
                            <div class="ddBatchManage form-inline">
                                <div style="display: inline">
                                    <input type="text" id="searchDdName" name="searchDdName" class="form-control"
                                           placeholder="请输入商品名称"/>
                                    <select id="searchDDState" class="form-control">
                                        <option value="">选择发货状态</option>
                                        <option value="1">待发货</option>
                                        <%--                            <option value="2">待付款</option>--%>
                                        <option value="3">部分发货货</option>
                                        <option value="4">已发货</option>
                                        <%--                            <option value="5">交易成功</option>--%>
                                        <%--                            <option value="6">交易失败</option>--%>
                                        <%--                                        <option value="7">售后</option>--%>
                                    </select>
                                    <button id="searchDdBtn" class="btn btn-primary btn-xs" onclick="searchDd()">搜索
                                    </button>
                                    <button id="resetting" class="btn btn-white btn-xs" onclick="resetSearch()">重置
                                    </button>

                                </div>
                            </div>
                        </div>
                        <div class="order">
                            <div class="order-header">
                                <div class="order-header-left">
                                    <label>商品</label>
                                </div>
                                <div class="order-header-right">
                                    <span class="order-header-item">单价</span>
                                    <span class="order-header-item">单位</span>
                                    <span class="order-header-item">库存数量</span>
                                    <span class="order-header-item">订单数量</span>
                                    <span class="order-header-item">已发货</span>
                                    <span class="order-header-item" style="flex: 2">发货数量</span>
                                    <span class="order-header-item">收货数量</span>
                                    <%--                                    <span class="order-header-item">单价</span>--%>
                                    <%--                                    <span class="order-header-item">订单数量</span>--%>
                                    <%--                                    <span class="order-header-item">发货数量</span>--%>
                                    <%--                                    <span class="order-header-item">收货数量</span>--%>
                                    <%--                                    <span class="order-header-item">订单总额</span>--%>
                                    <%--                                    <span class="order-header-item">实际总额</span>--%>
                                </div>
                            </div>
                            <div class="order-body" id="matList">
                            </div>
                            <div class="pagination w1080 m-pagenum pagination" id="Pagination"
                                 style="float: right"></div>
                        </div>
                        <div style="clear: both"></div>
                        <div class="note">
                            <label class="note-label">买家留言：</label>
                            <textarea style="border-style:none;resize:none;overflow:hidden;margin-top: -2px;"  class="note-textarea" placeholder="" readonly=true>${obj.scopeBusiness}</textarea>
<%--                            <c:if test="${obj.enterpriseType!=null}">--%>
<%--                                <label class="note-label">摘&nbsp&nbsp&nbsp要：&nbsp&nbsp&nbsp&nbsp</label>--%>
<%--                                <textarea style="width:850px;height: 40px" class="note-textarea" id="test" placeholder=" ">${obj.enterpriseType}</textarea>--%>
<%--                                <button id="saveButtom" onclick="saveZhaiYao()">保存摘要</button>--%>
<%--                            </c:if >--%>
<%--                            <c:if test="${obj.enterpriseType==null}">--%>
                                <label class="note-label">摘&nbsp&nbsp&nbsp要：&nbsp&nbsp&nbsp&nbsp</label>
                                <textarea style="width:850px;height: 40px" class="note-textarea" id="test" placeholder="请输入摘要">${obj.enterpriseType}</textarea>
                                <span style="position: absolute;margin-top: 8px;margin-left: 8px"><button id="saveButtom" onclick="saveZhaiYao()">保存摘要</button></span>
<%--                            </c:if>--%>
                        </div>
                        <div class="ft" style="height: auto; line-height: 20px;">
                            <div style="width: 100%;display: block">
                                <table class="xqtabl">
                                    <tr>
                                        <td class="right">商品总价：</td>
                                        <td>
                                            <strong class="red">¥<span id="totalmoney"></span></strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="right">店铺优惠：</td>
                                        <td class="left">
                                            <strong class="red">-<c:if test="${obj.money3!=null}">¥<fmt:formatNumber
                                                    type="number"
                                                    value="${obj.money3}"
                                                    pattern="0.00"
                                                    maxFractionDigits="2"/></c:if><c:if
                                                    test="${obj.money3==null}">¥0.00</c:if>
                                            </strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="right">运费(快递)：</td>
                                        <td class="left">
                                            <strong class="red">
                                                <c:if test="${obj.money2!=null}">¥<fmt:formatNumber type="number"
                                                                                                    value="${obj.money2}"
                                                                                                    pattern="0.00"
                                                                                                    maxFractionDigits="2"/></c:if>
                                                <c:if test="${obj.money2==null}">0.00</c:if>
                                            </strong>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="right">订单总价：</td>
                                        <td class="left">
                                            <strong class="red"><c:if
                                                    test="${obj.placeOrderMoney!=null}"> ¥<fmt:formatNumber
                                                    type="number"
                                                    value="${obj.placeOrderMoney}"
                                                    pattern="0.00"
                                                    maxFractionDigits="2"/>
                                            </c:if></strong>
                                        </td>
                                    </tr>
                                    <c:if test="${obj.placeOrderMoney==null}">-</c:if>
                                    <c:if test="${obj.processStatus == 1}"> <!-- 待发货 -->

                                    </c:if>
                                    <c:if test="${obj.processStatus == 2}"> <!-- 待付款 -->

                                    </c:if>
                                    <c:if test="${obj.processStatus == 3}"> <!-- 待发货 -->

                                    </c:if>
                                    <c:if test="${obj.processStatus == 4}"> <!-- 已发货 -->

                                    </c:if>
                                    <c:if test="${obj.processStatus == 5}"> <!-- 交易成功 -->

                                    </c:if>
                                    <c:if test="${obj.processStatus == 6}"> <!-- 交易失败 -->
                                        <tr>
                                            <td class="right">退款金额：</td>
                                            <td class="left">
                                                <strong class="red"><c:if
                                                        test="${obj.placeOrderMoney!=null}"> ¥<fmt:formatNumber
                                                        type="number"
                                                        value="${obj.placeOrderMoney}"
                                                        pattern="0.00"
                                                        maxFractionDigits="2"/>
                                                </c:if></strong>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="right">实付金额：</td>
                                            <td class="left">
                                                <strong class="red"><c:if
                                                        test="${obj.placeOrderMoney!=null}"> ¥<fmt:formatNumber
                                                        type="number"
                                                        value="${obj.actualMoney}"
                                                        pattern="0.00"
                                                        maxFractionDigits="2"/>
                                                </c:if></strong>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${obj.processStatus == 7}"> <!-- 售后 -->

                                    </c:if>
                                    <tr style="color: grey">
                                        <td class="right">
                                            支付方式：
                                        </td>
                                        <td class="left">${obj.payTypeName}</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <c:if test="${obj.processStatus == 1 || obj.processStatus == 4}"> <!-- 待发货 -->
                                            <td>
                                                <a class="btn btn-success"
                                                   onclick="confirmAddress()">立即发货</a><br/>
                                            </td>
                                        </c:if>
                                    </tr>
                                </table>
                            </div>
                            <div>
                                <%--                    商品总价，运费，订单总价，实付金额 付款方式，退款金额，支付方式--%>
                                <%--                    <button class="saveOrder font-weight-trigger" onclick="main_export()">导出</button>--%>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<div class="u-mask" id="addressId"></div>
<div id="addressDiv" class="divFilter" style="display: none">
    <div style="border-bottom: lightgrey solid 1px;">
        <div style="float: left;padding: 8px;padding-left: 31px;font-size: 20px;font-weight: bold;">物流信息</div>
        <div style="float: right;margin-top: 5px;">
            <a class="btn btn-white" onclick="cancelChange()">X</a>
        </div>
        <div style="clear: both"></div>
    </div>
    <div style=" margin-left: 50px;margin-top: 10px">
        <input type="checkbox" id="isDelivery" onclick="deliveryClick()">上门配送
    </div>
    <form id="filterForm" class="form-horizontal form-bordered" style="margin-top: 30px;margin-bottom: 20px" role="form"
          style="padding-left: 10px">
        <div class="form-group">
            <label class="col-sm-2 control-label">物流公司：</label>
            <div class="col-sm-8">
                <input class="form-control" id="expressName" name="expressName" placeholder="请填写物流公司" value=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">物流号：</label>
            <div class="col-sm-8">
                <input class="form-control" id="expressCode" name="expressCode" placeholder="请填写物流号" value=""/>
            </div>
        </div>
    </form>
    <div class="form-group" style="clear:both;">
        <div class="col-sm-offset-2 col-sm-10">
            <input id="sendBtn" type="button"  class="btn btn-success" onclick="send()" value="立即发货"/>
            <input id="cancelBtn" type="button" class="btn btn-white" onclick="cancelChange()" value="取消"/>
        </div>
    </div>
</div>
<%--<script src="<c:url value='/jsp/util/ordermxutil/ordermxutil.js?v=3' />"></script>--%>
<script src="<c:url value='/javaScript/dentistmall/transaction/sendOrderInfoNew.js?v=23'/>"></script>
</html>
 