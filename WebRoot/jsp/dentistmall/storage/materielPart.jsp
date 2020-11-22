<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/11
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>物料分类</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?v=2' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <style>
        .flex {
            display: flex;
            /*align-items: flex-start;*/
            /*justify-content: space-around;*/
        }

        .flex-1 {
            flex: 0.2;
        }

        .flex-2 {
            flex: 2;
        }

        .flex-3 {
            flex: 3;
        }

        .flex-4 {
            flex: 4;
        }

        /*开关的大小*/
        .switch-container {
            height: 20px;
            width: 50px;
            margin: 0 auto;
        }

        /*设置checkbox不显示*/
        .switch {
            display: none;
        }

        /*设置label标签为椭圆状*/
        label {
            display: block;
            background-color: #EEEEEE;
            height: 100%;
            width: 100%;
            cursor: pointer;
            border-radius: 25px;
        }

        /*在label标签内容之前添加如下样式，形成一个未选中状态*/
        label:before {
            content: '';
            display: block;
            border-radius: 25px;
            height: 100%;
            width: 25px;
            background-color: white;
            opacity: 1;
            box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.08);
            -webkit-transition: all 0.2s ease;
        }

        /*在label标签内容之后添加如下样式，形成一个选中状态*/
        label:after {
            position: relative;
            top: -20px;
            left: 30px;
            content: '';
            display: block;
            border-radius: 25px;
            height: 100%;
            width: 25px;
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
            background-color: blue;
        }

        .upDown {
            margin-right: 2px;
            padding: 1px 5px;
            border: 1px solid lightgray;
        }

        .border-top {
            border-top: 1px solid lightgrey;
        }

        .border-bottom {
            border-bottom: 1px solid lightgrey;
        }

        .border-right {
            border-right: 1px solid lightgrey;
        }

        .border-left {
            border-left: 1px solid lightgrey;
        }

        .text-align {
            text-align: center;
        }

        .paddingTopBottom {
            padding: 10px 0px;
        }

        .addbtn {
            width: 90px;
            height: 25px;
            text-align: center;
            line-height: 25px;
            background: none;
            border: 1px solid #23c6c8;
            background: #23c6c8;
            font-size: 12px;
            border-radius: 13px;
            color: #fff;
        }
    </style>
</head>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5>
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>物料分类管理</span>
        </h5>
        <!-- 标题文字区域end -->
        <div class="ibox-tools" id="win_tools_but">
        </div>
    </div>

    <!-- 操作栏 -->
    <div style="margin-bottom:10px; margin-top: 10px;padding: 10px;background: #fff;">
        <span><input type="checkbox" class="loginName" onclick="selectAllPart(this.checked)" id="all"></span>
        <span>全选</span>
        <span><a onclick="batchDelete()" class='btn btn-success  btn-xs' style="color:#fff"
                 id="batchDelete">批量删除</a></span>
        <span style="float: right;"><button class="addbtn" id="addPart" onclick="addMaterielPart()">添加一级分类</button></span>
    </div>

    <!-- 表格栏 -->
    <div class="flex border-bottom" style="padding: 0px 20px 0px 20px;background-color: white">
        <div class="flex-1" style="padding: 0 10px;width: 75px;">
            <div class="paddingTopBottom">
                <div style="margin-left: 10px;width: 44px;"></div>
            </div>
        </div>
        <div class="flex-4 border-right text-align">
            <div class="paddingTopBottom">分类名称</div>
        </div>
        <div class="flex-2 border-right text-align">
            <div class="paddingTopBottom">顺序</div>
        </div>
        <div class="flex-2 border-right text-align">
            <div class="paddingTopBottom">是否显示二级分类</div>
        </div>
        <div class="flex-3 text-align">
            <div class="paddingTopBottom">操作</div>
        </div>
    </div>
    <div id="content" class="ibox-content" style="padding-top: 0px;">

    </div>

    <!-- 表格区域begin -->
    <div>
        <div>
        </div>
    </div>
    <!-- 表格区域end -->

</div>
<div class="pagination w1080 m-pagenum pagination" id="Pagination"></div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/materielpart.js?v=47'/>"></script>
<%--<script>--%>
<%--    layui.use(['form'], function () {--%>
<%--        var form = layui.form;--%>

<%--        //监听指定开关--%>
<%--        form.on('switch(switchTest)', function (data) {--%>
<%--            this.checked;--%>
<%--        });--%>

<%--    });--%>
<%--</script>--%>
