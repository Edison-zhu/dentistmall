
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%--    <base href="<%=basePath%>">--%>

    <title>My JSP 'sysFeedback.jsp' starting page</title>

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
<script src="<c:url value='/javaScript/dentistmall/modifyprice/warehousingManagement.js?v=46'/>"></script>
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<%--//引入时间插件--%>
<script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<script src="<c:url value="/js/laydate/laydate.js?v=1" /> "></script>

<style>
    .close{
        position:relative;
        width:0.3em;
        height:1.5em;
        background: #333;
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
        display: inline-block;
    }
    .close:after{
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width:0.3em;
        height:1.5em;
        background: #333;
        -webkit-transform: rotate(270deg);
        -moz-transform: rotate(270deg);
        -o-transform: rotate(270deg);
        -ms-transform: rotate(270deg);
        transform: rotate(270deg);
    }
</style>
<body>
<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<div id="containerWhole" style="width: 100%;">
    <div class="container33" style="width: 100%;height: 45px;background-color: #FCFCFC;border-bottom:#F0F0F0 1.8px solid; ">
            <div style="line-height: 3"><span style="font-size: 18px;margin-left: 20px">入库管理</span>
<%--                save()--%>
                <span style="margin-left: 50%;"><button type="button" onclick="" class="btn btn-success btn-xs" style="margin-left: 30px;width: 95px;vertical-align:middle;border-radius: 20px;"><span style="margin-bottom: 10px">新增入库</span></button></span>
                <span><button type="button" onclick="searchAll(0)" class="btn btn-default btn-xs" style="margin-left: 30px;width: 95px;border-radius: 20px;">刷新</button></span>
                <span><button type="button" onclick="export1()" class="btn btn-default btn-xs" style="margin-left: 30px;width: 95px;border-radius: 20px;">导出excel</button></span>
            </div>
        </div>
    <div class="container33" style="width: 100%;height: 60px;background-color: #F0F0F0; margin-top: 10px">
    <div style="margin-left: -38px;margin-top: 20px;line-height: 1.5;line-height:60px;">
        <span style="font-size: 12px;margin-left: 47px">入库单号：<input type="text" id="warehousCode" style="width:150px;height: 28px" value="" placeholder=""></span>
        <span style="font-size: 12px;margin-left: 20px">入库类型：
            <span>
                <select id="select1">
                    <option selected="selected" value="0">全部</option>
                    <option  value="1">订单入库</option>
                    <option value="2">物料入库</option>
                </select>
            </span>
        </span>
        <span style="font-size: 12px;margin-left: 60px">关键字：<input type="text" id="remarks" style="width:150px;height: 28px" value="" placeholder="编号/名称/规格型号"></span>
<%--        <span style="font-size: 12px;margin-left: 150px">发票号：<input type="text" id="billCode" style="width:150px;height: 28px" value="" placeholder=""></span>--%>
        <span style="font-size: 12px;margin-left: 60px">操作时间：<input type="text" id="operationDate" style="width:150px;height: 28px" value=""></span>
        <span><button type="button" onclick="searchAll(1)" class="btn btn-default btn-xs" style="margin-left: 30px;width: 95px;">查询结果</button></span>
    </div>
    </div>
    <div class="container33" style="width: 100%;height: 600px;background-color: white;margin-top: 30px">
        <div style="line-height: 4;width: 100%;height: 50px;background-color: #F0F0F0;"><span style="font-size: 12px;margin-left: 20px">数据列表</span></div>
        <table class="table invoice-table">
            <thead style="border: 1.5px solid #F0F0F0;">
            <tr><th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><input type="checkbox"></th>
<%--                <img style="" width="" height="18px" src="../../../../dentistmall/css/shopping/images/paixu1.png">--%>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">入库单号<a onclick="a(1)"><img style="" width="13px" height="13px" src="../../../../dentistmall/css/shopping/images/paixu1.png"></a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">入库类型<a onclick="a(2)"><img style="" width="13px" height="13px" src="../../../../dentistmall/css/shopping/images/paixu1.png"></a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">入库备注</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购金额<a onclick="a(4)"><img style="" width="13px" height="13px" src="../../../../dentistmall/css/shopping/images/paixu1.png"></a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">零售金额<a onclick="a(5)"><img style="" width="13px" height="13px" src="../../../../dentistmall/css/shopping/images/paixu1.png"></a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">制作人<a onclick="a(6)"><img style="" width="13px" height="13px" src="../../../../dentistmall/css/shopping/images/paixu1.png"></a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">时间<a onclick="a(7)"><img style="" width="13px" height="13px" src="../../../../dentistmall/css/shopping/images/paixu1.png"></a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">发票号<a onclick="a(8)"><img style="" width="13px" height="13px" src="../../../../dentistmall/css/shopping/images/paixu1.png"></a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作信息</th>
            </tr>
            </thead>
            <tbody id="mxList">
            </tbody>
        </table>
        <div class="count" id="count1" style="margin-left: 30px">合计:共计<span id="c1"></span>条数据,总合计采购金额<span id="c2"></span>元,零售金额<span id="c3"></span>元</div>
        <div class="pagination" id="Pagination1" style="margin-left: 650px"></div>
    </div>
<%--    <div class="alert" style="" id="" style="margin-left: 30px"></div>--%>
</div>
<%--</div>--%>
</body>
</html>
<style type="text/css">

</style>