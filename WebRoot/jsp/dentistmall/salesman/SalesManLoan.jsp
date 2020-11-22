<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%--    <base href="<%=basePath%>">--%>

    <title>My JSP 'homePageWarehouse.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?2' />">
    <script src="<c:url value='/js/uitl/echarts.js'/>"></script>
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">
</head>

<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
      rel="stylesheet">
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<%--//引入时间插件--%>
<script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<style type="text/css">
    .a1 {
        border: 2px solid red
    }

    .div {
        margin: 0 auto;
        width: 90%;
        border: solid 1px lightgray;
        border-radius: 12px;
        margin-top: 10px;
        background: white;
    }
</style>
<body style="background-color: #f3f3f4">

<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<div id="containerWhole" class="div">
    <div id="container4"
         style="width: 1375px;height: 30px; border: 0px solid black;margin-top: 30px;margin-left: 57px;">
        <span>订单号:</span>
        <span><input type="text" style="width: 200px;height: 26px" id="orderCodeGJ" placeholder="请输入单号关键字"/></span>
        <span style="margin-left: 70px">时间范围:</span>
        <input value="" id="dateInput1" style="height: 26px">
        <input value="" id="dateInput2" style="height: 26px">
        <span style="margin-left: 70px">状态:</span>
        <span>
            <select id="selectValue" style="height: 26px">
                <option selected value=0>所有</option>
                <option value=1>已放款</option>
                <option value=2>待放款</option>
                <option value=3>未放款</option>
            </select>
        </span>
        <span style="margin-left: 70px"><button type="button" onclick="searchAll();"
                                                class="btn btn-primary btn-xs searchBtn" id="button1"
                                                style="width: 95px;height: 22px;margin-top: -5px">搜索</button></span>
        <span><a style="color: #0e9aef; margin-top: -10px;margin-left: 70px" onclick="searchAlls()">高级检索</a></span>
    </div>
    <div class="container33"
         style="width: 1377px;height: 400px;background-color: white;margin-left: 46px; margin-top:30px;border-radius: 5px 5px 5px 5px;">
        <span><a onclick="plJieSuan()" style="margin-left: 80px;color: #46A3FF;font-size: 13px">批量放款</a></span>
        <span><a onclick="exportFinanciaBranchMxList()"
                 style="margin-left: 80px;color: #46A3FF;font-size: 13px">导出放款明细</a></span>
        <table class="table invoice-table">
            <thead>
            <tr style="border-top:1.5px solid #F0F0F0;">
                <th style="text-align:center;font-size: 13px; border-left:1.5px solid #F0F0F0;">
                    <input name="all"
                           type="checkbox"
                           id="all"
                           onclick="all()">
                </th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">业务代理名称</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">服务类型</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">订单号</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">订单日期</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">支付方式</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">支付时间</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">支付单号</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">应付金额</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">实收金额</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">订单状态</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">放款状态</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;border-right:1.5px solid #F0F0F0;">
                    操作
                </th>
            </tr>
            </thead>
            <tbody id="mxList">
            </tbody>
        </table>
        <div style="margin-left: 20px">汇总：共计<span id="total1"></span>条;待放款共计<span id="money1"></span>元;已放款<span
                id="money2"></span>元
        </div>
        <div class="pagination" id="Pagination1" style="margin-left: 600px">

        </div>
    </div>
</div>
<div id="divHide1"
     style="border: 1px solid #F0F0F0; width: 300px;height: 380px;position: absolute;top:82px;left: 1214px;display: none">
    <div style="width: 300px;height: 30px;background-color: #F0F0F0">
        <span>高级检索</span>
        <span style="margin-left: 200px"><a onclick="offTall()">关闭</a></span>
    </div>
    <div style="width: 300px;height: 528px;background-color: #FCFCFC">
        <span style="margin-left: 25px">订单号:</span><span><input type="text" style="width: 200px;" id="orderCodeGJ1"
                                                                placeholder="请输入单号关键字"/></span><br/><br/><br/>
        <span style="margin-left: 26px">订单状态
            <select id="selectValue2" style="width: 150px">
                <option selected value="0">所有</option>
                <option value="1">待发货</option>
                <option value="2">待付款</option>
                <option value="3">待收货</option>
                <option value="4">部分发货</option>
                <option value="5">交易成功</option>
                <option value="6">交易失败</option>
                <option value="7">售后</option>
                <option value="8">已结算</option>
                <option value="9">未结算</option>
            </select>
        </span><br/><br/><br/>
        <span style="margin-left: 26px">下单时间:</span>
        <input value="" id="dateInput5" style="width: 80px">至
        <input value="" id="dateInput6" style="width: 80px"><br/><br/><br/>
        <span style="margin-left: 26px">放款状态
            <select id="selectValue1" style="width: 150px">
                <option selected value="0">所有</option>
                <option value="1">已放款</option>
                <option value="2">待放款</option>
                <option value="3">未放款</option>
            </select>
        </span><br/><br/><br/>
        <span style="margin-left: 26px">金额范围:</span>
        <input id="moneyFw1" value="" style="width: 80px"/>至
        <input id="moneyFw2" value="" style="width: 80px"/><br/><br/><br/>
        <span style="margin-left: 26px">供应商:</span>
        <input id="gongYingS" value="" style="width: 200px"/><br/><br/><br/>
        <span style="margin-left: 26px">业务代理商</span>
        <input id="yeWuDaiLiS1" readonly onclick="selectSalesMan()" value="" style="width: 154px"/><a onclick="clearSales()">清空</a>
        <input id="yeWuDaiLiS" type="hidden" value="" /><br/><br/>
        <span style="margin-left: 26px">机构门诊</span>
        <input id="jiGouMenZhen" value="" style="width: 200px"/><br/><br/>
        <span style="margin-left: 26px">放款时间:</span>
        <input value="" id="dateInput7" style="width: 80px">至
        <input value="" id="dateInput8" style="width: 80px"><br/><br/><br/>
        <span style="margin-left: 26px">支付方式
            <select id="payType" style="width: 150px">
                <option selected value="0">所有</option>
                <option value="3">月结</option>
<%--                <option value="1">支付宝</option>--%>
<%--                <option value="2">微信</option>--%>
            </select>
        </span><br/><br/><br/>
        <span style="margin-left: 110px"><button type="button" onclick="searchAll1();"
                                                 class="btn btn-primary btn-xs searchBtn" id="button3"
                                                 style="width: 95px;height: 22px;margin-top: -5px">搜索</button>
        </span>
    </div>
</div>

</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/salesman/salesManLoan.js?v=7'/>"></script>
