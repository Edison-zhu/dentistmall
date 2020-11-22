<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/13
  Time: 8:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>商家商品管理</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <style>
        .sbtn1 {
            display: inline-block;
            height: 26px;
            width: 80px;
            line-height: 26px;
            background: #1ab394;
            font-size: 14px;
            color: #ffffff;
            text-align: center;
            border-radius: 5px;
            margin-left: 10px;
        }

        .sbtn1:hover {
            color: #ffffff;
        }

        button {
            outline: none;
        }

        input {
            border: 1px solid #e5e6e7;
            margin-left: 10px;
            width: 230px;
            height: 30px;
            line-height: 30px;
            padding-left: 5px
        }
    </style>
</head>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 查询区域begin -->
    <div class="ibox-content" style="padding: 15px 10px 10px;">
        <div style="float: left;line-height: 25px">
            商家商品编码：<span id="matCode"></span>
        </div>
        <div>
            <input id="mmfNameSearch" name="mmfName" placeholder="按SKU编号搜索" onkeyup="searchEnter()"/>
            <a type="button" onclick="main_search()" class="sbtn1">搜索</a>
        </div>
        <script type="text/javascript" language="javascript">
            window.onload = function () {
                document.getElementById('mmfNameSearch').focus();
                document.getElementById("mmfNameSearch").style.outline = "thin solid #1ab394";
            }
        </script>
    </div>
    <!-- 查询区域end -->

    <!-- 表格区域begin -->
    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <table id="win_datagrid_main" class="mmg"></table>
        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
    </div>
    <!-- 表格区域end -->
</div>
<!-- 主信息区域end -->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/business/viewGoodSkuInfo.js?v=23'/>"></script>