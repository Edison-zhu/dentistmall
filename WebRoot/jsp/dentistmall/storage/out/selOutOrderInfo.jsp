<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/6/13
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
</head>
<style>
    .btn1{
        width: 110px;
        height: 22px;
        border: 1px solid #1ab394;
        background-color: #1ab394;
        font-size: 14px;
        color: white;
        border-radius: 11px;
        display: flex;
        justify-content: center;
        align-items: center;
        margin-left: 20px;
    }
    .btn1:hover{
        color: #ffffff;
    }
    .input1{
        border: 1px solid #bcbcbc;
        outline: none;
        padding-left: 5px;
    }
</style>
<body style="overflow:hidden; ">
<!-- 表单部分begin -->

<div class="panel panel-default">
    <!-- 隐藏域begin -->
    <div id="win_form_edithidden"></div>
    <!--隐藏区域end -->
    <!-- 正文部分begin -->
    <div class="panel-body scrollbarinfo">
        <div class="ibox float-e-margins" style="border:1px solid #e7eaec;margin-top:5px">
            <!--标题栏区域 begin-->
            <div class="ibox-title"
                 style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:45px;margin-bottom:10px;display: flex;justify-content: flex-start;align-items: center">
                <form id="accountForm" class="form-horizontal form-bordered" onkeyup="formEnter()"
                      role="form">
                    <!-- 标题文字区域begin -->
                    <label>申请单号：</label><input id="mooCode" name="mooCode" class="input1"/>
                    <label style="margin-left: 10px">申请日期：</label><input id="orderTime" name="orderTime" class="input1"/>
                    <label style="margin-left: 10px">申请部门：</label><input id="groupName" name="groupName" class="input1"/>
                    <label style="margin-left: 10px">申请人：</label><input id="userName" name="userName" class="input1"/>
                </form>
                <a onclick="main_wait_search()" class='btn1' id="button1">搜索</a>
            </div>
            <div id="gridDiv">
                <table id="win_datagrid_wait_main" class="mmg"></table>
                <div id="_all_win_datagrid_wait_pg" style="text-align: right;"></div>
            </div>
            <!-- !--表格区域begin -->

            <!--!--表格区域end-->

        </div>
    </div>
    <!-- 正文部分end -->
</div>

<!-- 表单部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/out/selOutOrderInfo.js?v=20'/>"></script>