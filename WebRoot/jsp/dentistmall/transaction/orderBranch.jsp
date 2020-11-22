<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--    <base href="<%=basePath%>">--%>

    <title>My JSP 'orderBranch.jsp' starting page</title>

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
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?v=2' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64'/>"></script>
<%--    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">--%>
</head>
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
<%--//引入时间插件--%>
<script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<body>
<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<div id="containerWhole" style="width:1407px">
    <div id="container1">
        <b><%--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--%><span style="font-size: 25px">今天,</span> </b><br/>
        <span class="newDate"><font size="2px"><%--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--%><span
                id="currentDate"></span>,欢迎回来!</span></font>
    </div>
    <div id="container2" style="width:1407px;height:180px;margin-left: 27px;" class="container23 con22">
<%--        <a onclick="ClaimantLeft1()" style="color: #FFFFFF">--%>
        <div id="container2Left" class="container21" style="width: 350px;height: 180px;background-color:#46A3FF;float: left;margin-left: 200px;border-radius: 5px 5px 5px 5px;">
            <img style="float:right;margin-right: 20px;margin-top: 20px" width="130px" height="130px"
                 src="../../../../dentistmall/css/shopping/images/container2Left.png">
            <div class="container2Span1"  id="branchleft1" style="width: 125px; height:30px;margin-top: 80px;text-align: left;margin-left: 20px;color: white"></div>
            <div class="container2Span" style="width: 125px; height:30px;margin-top: 5px;text-align: left;margin-left: 20px;color: white"><b>待结算金额</b></div>
        </div>
<%--        </a>--%>
<%--        <a onclick="ClaimantLeft2()" style="color: #FFFFFF">--%>
        <div id="container2Middle" class="container22" style="width: 350px;height: 180px;background-color: #FF8000;float: left;margin-left: 300px;border-radius: 5px 5px 5px 5px;">
            <img style="float:right;margin-right: 20px;margin-top: 20px" width="130px" height="130px"
                 src="../../../../dentistmall/css/shopping/images/money.png">
            <div class="container2Span1" id="branchleft2" style="width: 125px; height:30px;margin-top: 80px;text-align: left;margin-left: 20px;color: white"></div>
            <div class="container2Span" style="width: 125px; height:30px;margin-top: 5px;text-align: left; margin-left: 20px;color: white"><b>累计金额</b></div>
        </div>
<%--        </a>--%>
    </div>
    <div class="container3" style="width: 1367px;height: auto;margin-top: 30px;border-radius: 5px 5px 5px 5px;">
        <div class="container31" style="width: 1367px;height: 30px;margin-left: 76px;border-radius: 5px 5px 5px 5px;">
            <span>交易时间</span>
            <input  value="" id="dateInput1" style="width: 110px">
            <input  value="" id="dateInput2" style="width: 110px">
            <span>关键词</span>
            <span>
                <select id="selectGuanjian">
                    <option selected value="1">订单号</option>
                    <option value="2">退款编号</option>
                    <option value="3">对方名称</option>
                </select>
                <input type="text" value="" id="inputGuanjian"/>
            </span>
            <span>
                <select id="zhiFu">
                    <option selected value=1>全部</option>
                    <option value=4>月结</option>
                    <option value=2>支付宝</option>
                    <option value=3>微信</option>
                </select>
            </span>
            <span>
                <select id="state">
                    <option selected value="1">状态</option>
                    <option value="2">交易成功</option>
                    <option value="3">交易失败</option>
                </select>
            </span>
            <span>
                金额范围:
                <input type="text" value="" id="jinE1"/>~<input type="text" value="" id="jinE2"/>
            </span>
            <span>
                <select id="ziJin">
                    <option selected value="1">资金状态</option>
                    <option value="2">已结算</option>
                    <option value="3">未结算</option>
                </select>
            </span>
            <span><button type="button" onclick="searchAll();" class="btn btn-primary btn-xs searchBtn" id="button1" style="width: 95px;height: 22px;margin-top: -5px">搜索</button></span>
        </div>
        <div class="container32" style="width: 1327px;height: 30px;margin-left: 76px;border-radius: 5px 5px 5px 5px;">
            <img src="../../../../dentistmall/css/shopping/images/container4Top1.png" style="width: 20px;height: 20px"/>
<%--            click1--%>
            <a onclick="click1()" style="color: #0e9aef;text-decoration: underline">统计金额:</a>
            <span>下载账单:</span>
            <a onclick="exportExcel1()" style="color:#0e9aef;text-decoration: underline">Excel格式</a>
                <%--  2020年4月26日 去除隐藏数据功能功能 display: none--%>
            <span style="margin-left: 50px;" id="countMoney">已结算<span id="yiJieSuanCount"></span>笔共<span id="yiJieSuanMoney"></span>,待结算<span id="weiJieSuanCount"></span>笔共<span id="weiJieSuanMoney"></span>
                |已退款<span id="yiTuiKuanCount"></span>笔共<span id="yiTuiKuanMoney"></span>,未待款<span id="weiTuiKuanCount"></span>笔共<span id="weiTuiKuanMoney"></span>
                </span>
        </div>
        <div class="container33" style="width: 1327px;height: 400px;margin-top:20px;background-color: white;margin-left: 76px;border-radius: 5px 5px 5px 5px;">
            <table class="table invoice-table">
                <thead>
<%--                border-top:1.5px solid #F0F0F0;--%>
                    <tr style="background-color:#E0E0E0 ">
                        <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">支付时间</th>
                        <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">订单号/退款编号</th>
                        <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">对方名称</th>
                        <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">支付方式</th>
                        <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">交易金额</th>
                        <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">状态</th>
                        <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">资金状态</th>
                        <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;border-right:1.5px solid #F0F0F0;">操作</th>
                    </tr>
                </thead>
                <tbody id="mxList">
                </tbody>
            </table>
            <div class="pagination" id="Pagination1" style="margin-left: 550px"></div>
        </div>
    </div>
    </div>

</body>

</html>
<script src="<c:url value='/javaScript/dentistmall/transaction/orderBranch.js?v=50'/>"></script>
<style type="text/css">
    hr{
        border-top: dotted 1px black;
    }
</style>
