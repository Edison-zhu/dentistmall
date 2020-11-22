<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/3/31
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>牙医商城平台系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <script src="<c:url value='/js/ChineseCities.js?v=2'/>"></script>
</head>
<body>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <%--        <h5>--%>
        <%--            <i class="fa fa-flask"></i>&nbsp&nbsp<span>关联信息</span>--%>
        <%--        </h5>--%>
        <h5>业务员：<span id="salesName"></span> <span style="padding-left: 10px"></span>共计发展<span id="agentCount">0</span>代理商，<span
                id="otherCount">0</span>家机构（含集团、医院、门诊等）
        </h5>
        <!-- 标题文字区域end -->
        <div class="ibox-tools" id="win_tools_but">
        </div>
    </div>
    <!-- 标题栏区域 end-->

    <!-- 查询区域begin -->
    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <form class="form-horizontal" id="win_form_search">
            <div id="win_form_hidden"></div>
        </form>
    </div>
    <!-- 查询区域end -->

    <!-- 表格区域begin -->
    <div class="export">
        <a onclick="main_export()" class='btn btn-success  btn-xs' style="width:95px;height:22px;color:#fff;float: right;margin-right: 15px;" id="button2">导出全部</a>
    </div>
    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <table id="win_datagrid_main" class="mmg"></table>
        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
    </div>
    <!-- 表格区域end -->
</div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/salesman/salesmanLink.js?v=15'/>"></script>
