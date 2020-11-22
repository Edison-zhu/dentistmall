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
<script src="<c:url value='/javaScript/main/FinancePageMx.js?v=54'/>"></script>
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<%--//引入时间插件--%>
<script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<style type="text/css">
    table {
        position: relative;
        width: 100%;
        display: block;
    }

    thead {
        float: left;
    }

    tbody tr {
        display: inline-block;
    }

    th,td，{
        height: 29px;
        width: 200px;
        display: block;
    }
</style>
<body>
    <div id="top1" style="width:1480px;height: 50px;background-color: #E0E0E0">
        <div style="width: 100px;height: 10px"></div>
        <div style="width: 100px;margin-left: 30px">对账单明细</div>
    </div>
    <span style="font-size: 16px;margin-left: 30px">订单信息</span>
    <div id="top2" style="width:1382px;height: 300px;margin-left: 30px;border: 0px solid #F0F0F0">
    <table  class="table table-bordered table-hover">
        <thead>
        <tr>
            <th style="text-align: center;">订单编号</th>
            <th style="text-align: center;">订单日期</th>
            <th style="text-align: center;">支付方式</th>
            <th style="text-align: center;">支付时间</th>
            <th style="text-align: center;">支付单号</th>
            <th style="text-align: center;">应付金额</th>
            <th style="text-align: center;">实收金额</th>
            <th style="text-align: center;">采购商</th>
            <th style="text-align: center;">供应商</th>
            <th style="text-align: center;">订单状态</th>
        </tr>
        </thead>
        <tbody id="mxList">
        </tbody>
    </table>
    </div>
    <span style="font-size: 16px;margin-left: 30px">对账信息</span>
    <div id="top3" style="width:1382px;height: 150px;margin-left: 30px;border: 0px solid #F0F0F0">
        <table  class="table table-bordered table-hover">
            <thead>
            <tr>
                <th style="text-align: center;">状态</th>
                <th style="text-align: center;">对账金额</th>
                <th style="text-align: center;">对账信息</th>
            </tr>
            </thead>
            <tbody id="mxList1">
            </tbody>
        </table>
    </div>
    <div style="text-align: center">
            <a><button type="button" onclick="requestFinance()" class="btn btn-default btn-xs" id="button1" style="width: 95px;height: 22px;margin-top: -5px;margin-left: 10px;margin-right: 10px">返回</button></a>
    </div>

</body>
</html>