<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/26
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>物料分类详细信息</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <%--    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">--%>
    <%--    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/modules/layer/default/layer.css?v=1'/>">--%>
    <%--    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>--%>
    <%--    <script src="<c:url value="/js/plugins/layui/lay/modules/layer.js" />"></script>--%>
</head>
<style>
    .ibox-tools button {
        width: 95px;
        vertical-align: middle;
        align-items: center;
    }

    #searchForm {
        width: 50%;
        display: inline;
        margin-left: 18px;
    }

    .divFilter {
        display: none;
        float: left;
        margin: 0 auto;
        position: absolute;
        /*top: 20%;*/
        /*left: 20%;*/
        width: 50%;
        background-color: whitesmoke;
        z-index: 901;
        height: 380px;
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

    /*开关的大小*/
    .switch-container {
        height: 15px;
        width: 30px;
    }

    /*设置checkbox不显示*/
    .switch {
        display: none;
    }

    /*设置label标签为椭圆状*/
    .switch-container label {
        display: block;
        background-color: #EEEEEE;
        height: 100%;
        width: 100%;
        cursor: pointer;
        border-radius: 25px;
    }

    /*在label标签内容之前添加如下样式，形成一个未选中状态*/
    .switch-container label:before {
        content: '';
        display: block;
        border-radius: 25px;
        height: 100%;
        width: 15px;
        background-color: white;
        opacity: 1;
        box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.08);
        -webkit-transition: all 0.2s ease;
    }

    /*在label标签内容之后添加如下样式，形成一个选中状态*/
    .switch-container label:after {
        position: relative;
        top: -15px;
        left: 15px;
        content: '';
        display: block;
        border-radius: 25px;
        height: 100%;
        width: 15px;
        background-color: white;
        opacity: 0;
        box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.08);
        -webkit-transition: all 0.2s ease;
    }

    /*选中后，选中样式显示*/
    .switch-container input:checked ~ label:after {
        opacity: 1;
    }

    /*选中后，未选中样式消失*/
    .switch-container input:checked ~ label:before {
        opacity: 0;
    }

    /*选中后label的背景色改变*/
    .switch-container input:checked ~ label {
        background-color: green;
    }

    .rtd{
        width: 10%;
    }
    .ltd{
        width: 10%;
        padding: 20px !important;
        text-align: right;
    }
</style>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="row  wrapper wrapper-content " style="margin: 0 10px">
    <div class="ibox-content" style="padding: 13px 10px 10px;">
        <div>
            <div class="ibox-title"
                 style="padding-left: 0;padding-right: 0;display: flex;justify-content: space-between;align-items: center;">
                <!-- 标题文字区域begin -->
                <div style="display: inline-block;width: 180px;">
                    <h5>
                        <i class="fa fa-flask"></i>&nbsp&nbsp<span style="font-size: 16px;font-weight: bold;color: #000000;">库存查看【详情】</span>
                    </h5>
                </div>
                <!-- 标题文字区域end -->
                <div class="" style="display: inline-block;width: 70%;text-align: right" id="win_tools_but">
                    <div style="display: inline-block;margin-right: 30px">
                        <form id="queryForm" class="form-horizontal">
                            <label>品牌:</label>
                            <input id="brand" name="brand" onkeydown="entersearch()"/>
                            <label>日期:</label>
                            <input id="createDate" name="createDate1"/>
                        </form>
                    </div>
                    <div style="display: inline-block;">
                        <button id="safetyEwBtn" type="button"
                                style="margin-left: 5px; width: 120px; vertical-align: middle;"
                                class="btn btn-rounded btn-default btn-sm fa fa-qqq " onclick="search()"><img
                                width="10px"
                                height="10px"
                                src="/dentistmall/img/SafetyEarlyWarning.png"><span
                                style="font-size: 12px;color: #333333">查询</span></button>
                        <button id="win_but_export" type="button"
                                style="margin-left: 5px; width: 95px; vertical-align: middle;"
                                class="btn btn-rounded btn-success btn-sm fa fa-download " onclick="backTo()">返回
                        </button>
                        <button id="win_but_export1" type="button"
                                style="margin-left: 5px; width: 95px; vertical-align: middle;"
                                class="btn btn-rounded btn-info btn-sm fa fa-cloud-upload " onclick="exportAll()">&nbsp;导出Excel&nbsp;
                        </button>
                    </div>
                    <script>
                        function entersearch() {
                            var event = window.event || arguments.callee.caller.arguments[0];
                            if (event.keyCode == 13) {
                                search();
                            }
                        }
                    </script>
                </div>
            </div>

        </div>
    </div>

    <div class="ibox-content" style="padding: 13px 10px 10px;">
        <div id="infoDiv" style="">
            <table>
                <tr>
                    <td class="ltd"><label>商品物料信息:</label></td>
                    <td class="rtd" style="">

                        <span id="matName"></span>
                    </td>
                    <td class="ltd"><label>商品物料分类:</label></td>
                    <td class="rtd" style="">

                        <span id="mdpName"></span>
                    </td>
                    <td class="ltd"><label>商品物料规格:</label></td>
                    <td class="rtd" style="">

                        <span id="mmfName"></span>
                    </td>
                    <td class="ltd"><label>库存总数:</label></td>
                    <td class="rtd" style="">
                        <span id="baseNumber"></span>
                    </td>
                    <td class="ltd"><label>图标:</label></td>
                    <td class="rtd" style="">
                        <img style="width: 50px" id="logoPath"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <div class="col-sm-12">
        <div class="row">
            <div class="ibox float-e-margins">
                <!-- 标题栏区域 begin-->
                <div class="ibox-title">
                    <!-- 标题文字区域begin -->
                    <h5><i class="fa fa-tint"></i>&nbsp&nbsp物料信息</h5>
                    <!-- 标题文字区域end -->
                    <!-- 标题栏工具区域begin -->
                    <div class="ibox-tools">
                    </div>
                    <!-- 标题栏工具区域end -->
                </div>
                <!-- 标题栏区域 end-->
                <!-- 操作区域 begin-->
                <div class="ibox-content">
                    <div class="row" style="margin-bottom: 10px">
                    </div>
                    <table id="datagrid1" class="mmg"></table>
                    <div id="pg" style="text-align: right;"></div>
                </div>
                <!-- 操作区域 end-->
            </div>
        </div>
    </div>
</div>
<!--内容部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/viewInventoryInfo.js?v=20'/>"></script>
