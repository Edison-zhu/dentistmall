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
    .divFilter {
        display: none;
        float: left;
        margin: 0 auto;
        position: absolute;
        top: 10%;
        left: 10%;
        width: 80%;
        background-color: whitesmoke;
        z-index: 901;
        height: 500px;
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
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5 id="show1">
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>业务员信息</span>
        </h5>

        <h5 id="show2">业务员：<span id="salesName">${sessionScope.sessionUser.userName}</span> <span
                style="padding-left: 10px"></span>共计发展<span
                id="agentCount">0</span>代理商，<span
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
    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <c:if test="${sessionScope.sessionUser.userType == 6}">
            <div class="tabs-container">
                <ul class="nav nav-tabs">
                    <li class="active" id="waitOutWarehouser"><a data-toggle="tab"
                                                                 onclick="javascript:changeSalesShow(2)"
                                                                 aria-expanded="true" style="color: black">本人发展</a>
                    </li>
                    <c:if test="${sessionScope.sessionUser.userRole == 1 || sessionScope.sessionUser.userRole == 3}">
                        <li id="sz"><a id="readyOutWarehouser" data-toggle="tab"
                                       onclick="javascript:changeSalesShow(1)" aria-expanded="false" style="color: black">关联发展</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </c:if>
        <c:if test="${sessionScope.sessionUser.userRole == 1 || sessionScope.sessionUser.userRole == 3}">
        <a onclick="main_exportAllDaiLi()"
            id ="export1"  class='btn btn-success  btn-xs'
           style="color:#fff;margin: 8px 0px 8px 0px" id="export1">导出全部代理商</a>
        </c:if>
        <c:if test="${sessionScope.sessionUser.userRole == 1 || sessionScope.sessionUser.userRole == 3}">
            <a onclick="main_exportS1()"
               id ="export2"  class='btn btn-success  btn-xs'
               style="color:#fff;margin: 8px 0px 8px 0px;width: 95px;" id="export1">导出全部</a>
        </c:if>
        <c:if test="${sessionScope.sessionUser.organizaType == 0&&sessionScope.sessionUser.userType!=6&&sessionScope.sessionUser.userType!=3}">
        <a onclick="main_export()"
           id ="export1"  class='btn btn-success  btn-xs'
           style="color:#fff;margin: 8px 0px 8px 0px" id="export1">导出全部代理商</a>
        </c:if>
        <div id="otherShow">
            <table id="win_datagrid_main" class="mmg"></table>
            <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
        </div>
        <div id="myShow">
            <table id="win_datagrid_main1" class="mmg"></table>
            <div id="_all_win_datagrid_pg1" style="text-align: right;"></div>
        </div>

    </div>
    <!-- 表格区域end -->
</div>
<div class="u-mask" id="addressId"></div>
<div id="divFilter" class="divFilter">
    <div style="border-bottom: lightgrey solid 1px;">
        <div style="float: left;padding: 8px;padding-left: 31px;font-size: 20px;font-weight: bold;">过滤</div>
        <div style="float: right;margin-top: 5px;">
            <a class="btn btn-white" onclick="cancelFilter()">X</a>
        </div>
        <div style="clear: both"></div>
    </div>
    <form id="filterForm" class="form-horizontal form-bordered" style="margin-top: 10px;margin-bottom: 20px" role="form"
          style="padding-left: 10px">
        <div id="win_form_edithidden"></div>
        <div class="ibox float-e-margins">
            <div class="form-group">
                <label class="col-sm-2 control-label">账号：</label>
                <div class="col-sm-8">
                    <input class="form-control" placeholder="请输入账号" name="salesAccount" id="salesAccount"
                           value=""/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">手机号码：</label>
                <div class="col-sm-8">
                    <input class="form-control" placeholder="请输入手机号码" name="salesPhone" id="salesPhone"
                           value=""/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">公司名称：</label>
                <div class="col-sm-8">
                    <input class="form-control" placeholder="公司名称" id="sales_company" name="agentCompany"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">所在省：</label>
                <div class="col-sm-8 form-inline">
                    <input type="hidden" id="provinceHidden" value=""/>
                    <input type="hidden" id="cityHidden" value=""/>
                    <input type="hidden" id="areaHidden" value=""/>
                    <select id="province" name="province" class="form-control" placeholder="所在省"
                            value="${obj.province}">
                        <option value="">所在省</option>
                    </select>
                    <select id="city" name="city" class="form-control" placeholder="所在市" value="">
                        <option value="">所在市</option>
                    </select>
                    <select id="area" name="area" class="form-control" placeholder="所在地区" value="">
                        <option value="">所在地区</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">状态：</label>
            <div class="col-sm-8 form-inline">
                <select class="form-control" name="state">
                    <option value="">选择状态</option>
                    <option value="1">启用</option>
                    <option value="2">禁用</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">创建日期：</label>
            <div class="col-sm-8">
                <input class="form-control" placeholder="选择日期范围" name="createDateStart" id="createDateStart"
                       value=""/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">创建人：</label>
            <div class="col-sm-8">
                <input class="form-control" placeholder="" name="createRen" id="createRen"
                       value=""/>
            </div>
        </div>
    </form>
    <div class="form-group" style="clear:both;">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="layui-btn layui-btn-blue" onclick="main_search()">过滤</button>
            <button class="layui-btn layui-btn-primary" onclick="cancelFilter()">取消</button>
        </div>
    </div>
</div>
</body>
</html>
<script>
    var _user_name = "${sessionScope.sessionUser.userName}";
    var _user_org_type = "${sessionScope.sessionUser.organizaType}";
    var _user_type = "${sessionScope.sessionUser.userType}";
    var _user_role = "${sessionScope.sessionUser.userRole}";
</script>
<script src="<c:url value='/javaScript/dentistmall/salesman/salesman.js?v=43'/>"></script>
