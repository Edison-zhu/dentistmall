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
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">
</head>
<script src="<c:url value='/javaScript/main/homePageClaimant.js?v=114'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/homePageClaimant.css?v=7' />">
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<div id="containerWhole" style="width:1407px">
    <div id="container1">
        <b><%--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--%><span style="font-size: 25px">今天,</span> </b><br/>
        <span class="newDate"><font size="2px"><%--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--%><span
                id="currentDate"></span>,欢迎回来!</span></font>
        <div class="kstjsld" style="">
            <a class='btn btn-success btn-xs' style="color:#fff;float: right;margin-right: 620px;margin-bottom: -5px;height: 23px;width: 120px"
               onclick="ClaimantTop2()">快速添加申领单</a>
        </div>
        <div class="kstjsld">
            <a class='btn btn-success btn-xs' style="color:#fff;position: absolute;left: 1122px;margin-bottom: -5px;height: 23px;width: 120px"
               onclick="ClaimantTop3()">申领管理</a>
        </div>
    </div>
    <div id="container2" style="width:1407px;height:180px;margin-left: 27px;" class="container23 con22">
        <a onclick="ClaimantLeft1()" style="color: #FFFFFF">
        <div id="container2Left" class="container2">
            <img style="float:right" width="100px" height="100px"
                 src="../../../dentistmall/css/shopping/images/leftOne.png">
            <div class="container2Span1"  id="Claim" style="width: 125px; height:auto;"></div>
            <span class="container2Span" style="font-size: 20px"><b>申领中</b></span>
        </div>
        </a>
        <a onclick="ClaimantLeft2()" style="color: #FFFFFF">
        <div id="container2Middle" class="container2">
            <img style="float:right" width="100px" height="100px"
                 src="../../../dentistmall/css/shopping/images/ClaimantLeftTwo.png">
            <div class="container2Span1" id="PartialOutgoing" style="width: 135px; height:auto;"></div>
            <span class="container2Span" style="font-size: 20px"><b>部分出库</b></span>
        </div>
        </a>
        <a onclick="ClaimantLeft3()" style="color: #FFFFFF">
        <div id="container2Right" class="container2">
            <img style="float:right;margin-right: 12px;" width="100px" height="100px"
                 src="../../../dentistmall/css/shopping/images/ClaimantLeftThree.png">
            <div class="container2Span1" id="CompleteOut" style="width: 165px; height:auto;"></div>
            <span class="container2Span" style="font-size: 20px"><b>已完成出库</b></span>
        </div>
        </a>
    </div>
<%--        <div id="hide">--%>
<%--            <span id="loading1" class="layui-icon layui-icon-loading-1 layui-anim layui-anim-rotate layui-anim-loop" style="position:absolute;top:400px;left:660px;font-size: 41px;display: none;"></span>--%>
<%--        </div>--%>
    <div class="container3" style="width: 1167px;height: auto;background-color: white;margin-left: 76px;margin-top: 30px;border-radius: 5px 5px 5px 5px;">
        <div id="ck"
             style="border:0px solid #000; background-color:#F0F0F0;width: 700px;height: 450px; position: absolute;top: 320px;left: 450px;display:none">
            <table class="table invoice-table1" style="border: #0C0C0C 1px solid;width:660px;margin-left: 20px;margin-right: 20px;background-color: white">
                <thead>
                <span><a onclick="close1()" style="float: right;;font-size: 13px">关闭</a></span></br>
<%--                //border: #0C0C0C 1px solid;--%>
                <div style="margin-top: 30px">
                    <span style="margin-left: 30px;font-size: 13px">订单号:
                    <span id="outCode"></span></span>
                    <span style="margin-left: 280px;font-size: 13px" id="flowState1"></span>
                </div>
                <tr style="border: #0C0C0C 1px solid">
                    <th style="text-align:center;font-size: 13px;border: #0C0C0C 1px solid;">商品名称</th>
                    <th style="text-align:center;font-size: 13px;border: #0C0C0C 1px solid;">规格</th>
                    <th style="text-align:center;font-size: 13px;border: #0C0C0C 1px solid;">申请数量/缺少数量</th>
                </tr>
                </thead>
                <tbody style="border: #0C0C0C 1px solid;" id="mxList1">

                </tbody>
            </table>
            <div class="pagination" id="Pagination2" style="position:absolute;top: 380px;left: 260px"></div>
        </div>
        <span>近7天申领通知实时动态</span>
        <table class="table invoice-table">
            <thead>
            <div><a onclick="yiRead()" style="margin-left: 25px;color: #46A3FF;font-size: 13px" >标记为已读</a></div>
            <tr>
                <th style="text-align:center;font-size: 13px;margin-left: 10px"><input name="all" type="checkbox" id="all" onclick="all()"/>全选</th>
                <th style="text-align:center;font-size: 13px">日期</th>
                <th style="text-align:center;font-size: 13px">申领单号</th>
                <th style="text-align:center;font-size: 13px">处理状态</th>
                <th style="text-align:center;font-size: 13px">申领/出库数量</th>
                <th style="text-align:center;font-size: 13px">缺少数量</th>
                <th style="text-align:center;font-size: 13px">缺少商品</th>
                <th style="text-align:center;font-size: 13px">仓管留言</th>
            </tr>
            </thead>
            <tbody id="mxList">
            </tbody>
        </table>
    </div>
</div>
<div class="pagination" id="Pagination1" style="margin-left: 600px"></div>
<%--<div class="pagination" id="Pagination1" style="margin-left: 600px "></div>--%>
</body>


</html>
