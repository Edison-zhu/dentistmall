<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/1/3
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>牙医商城平台系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
</head>
<style>
    .collectText {
        /*float: left;*/
        /*position: relative;*/
        /*bottom: 10%;*/
        /*right: 1%;*/
        font-size: 16px;
    }

    .collectText span {
        color: black;
        font-weight: bold;
    }

    .input-text {
        height: 22px;
    }

    .input-button {
        height: 22px;
        padding: 4px 9px;
        line-height: 1px;
        padding-bottom: 5px;
    }

    #button1 {
        width: 95px;
        height: 25px;
    }

    #button2 {
        width: 95px;
        height: 25px;
    }

    #button3 {
        width: 95px;
        height: 25px;
        margin-left: -25px;
    }
</style>
<body style="overflow:hidden; ">
<!-- 表单部分begin -->
<form id="searchForm" class="form-horizontal form-bordered" style="margin-top: 10px;padding-left: 10px" role="form">
    <div id="win_form_edithidden"></div>
    <div class="panel">
        <div id="win_form_salseman_body" style="display: inline-block; width: 80%">
            <div class="form-inline">
                <label>姓名：</label>
                <input class="form-control" placeholder="姓名" id="sales_name" name="salesAccount"/>

                <label style="padding-left: 20px">公司名称：</label>
                <input class="form-control" placeholder="公司名称" id="sales_company" name="agentCompany"/>
                <%--                <div class="form-inline">--%>
                <span id="hideType" style="display: none">
                <label style="padding-left: 20px">类型：</label>
                <label class="">
                    <input type="checkbox" id="salesMan" name="salesType1" checked value="1">业务员
                </label>
                <label class="" style="margin-left: 20px">
                    <input type="checkbox" id="agent" name="salesType2" checked value="2">代理商
                </label>
                </span>
                <%--                </div>--%>
            </div>
            <div class="form-inline" style="margin-top: 10px">
<%--                <label style="padding-left: 20px">手机号：</label>--%>
<%--                <input class="form-control" placeholder="手机号" id="sales_phone" name="salesPhone"/>--%>
                <label>所在省：</label>
                <select id="province" name="province" class="form-control" placeholder="所在省">
                    <option value="">所在省</option>
                </select>
                <select id="city" name="city" class="form-control" placeholder="所在市">
                    <option value="">所在市</option>
                </select>
                <select id="area" name="area" class="form-control" placeholder="所在地区">
                    <option value="">所在地区</option>
                </select>
            </div>
        </div>
        <a class="btn btn-primary" onclick="searchSalesMan()">查询</a>
    </div>
</form>
<form id="queryForm" class="form-horizontal form-bordered" role="form">
    <div class="panel panel-default">
        <!-- 正文部分begin -->
        <div class="panel-body">
            <!--条件输入区域begin-->
            <div class="controls col-sm-12">
                <%--                <input type="hidden" name="wiId" id="wiId" value="">--%>

                <table id="datagrid1" class="mmg"></table>
                <div id="pg" style="text-align: right;"></div>
            </div>
        </div>
        <!-- 正文部分end -->
        <!-- 工具栏部分begin -->
        <div class="panel-footer">
            <div class="form-group" style="text-align: right; margin-right: 10px">
                <div>
                    <button type="button" onclick="closeWin()" class="btn btn-default btn-xs" id="button2">关闭</button>
                </div>
            </div>
        </div>
        <!-- 工具栏部分end -->
    </div>
</form>
<!-- 表单部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/salesman/choosesalesman.js?v=19'/>"></script>
