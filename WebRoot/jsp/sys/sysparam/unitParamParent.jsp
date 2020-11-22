<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?13' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/js/formSelects-v4.css?v=1'/>">
    <script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
    <script type="text/javascript">
        //需要先配置layui，并使用use方法，这是单独将formSelects模块加入
        layui.config({
            base: '/dentistmall/js/' //此处路径请自行处理
        }).extend({
            formSelects: 'formSelects-v4'
        });
        // layui.use(['jquery', 'formSelects'], function () {
        //     var formSelects = layui.formSelects;
        // });

    </script>
    <style>
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
    </style>
</head>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5>
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>单位编码</span>
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
        <table id="win_datagrid_main" class="mmg"></table>
        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
    </div>
    <!-- 表格区域end -->

</div>
<div class="u-mask" id="addressId"></div>
<div id="divFilter" class="divFilter">
    <div style="border-bottom: lightgrey solid 1px;">
        <div style="float: left;padding: 8px;padding-left: 31px;font-size: 20px;font-weight: bold;">编辑分类</div>
        <div style="float: right;margin-top: 5px;">
            <a class="btn btn-white" onclick="cancelUnit(1)">X</a>
        </div>
        <div style="clear: both"></div>
    </div>
    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <form class="form-horizontal" id="win_form_edit_add">
            <div id="win_form_edit_add_hidden"><input type="hidden" name="upId" id="upIdForm" value=""></div>
            <div class="form-group" style="margin-bottom:0px;"><label class="col-sm-3 control-label">单位名称<span
                    class="text-danger">(*)</span>：</label>
                <div class="col-sm-8"><input id="unitNameForm" name="unitName" class="form-control" placeholder="单位名称"
                                             type="text" aria-required="true" aria-invalid="true"></div>
            </div>
            <div class="hr-line-dashed" style="margin:5px 0px;"></div>
            <div class="hr-line-dashed" style="margin:5px 0px;"></div>
            <div class="form-group" style="margin-bottom:0px;"><label class="col-sm-3 control-label">是否显示<span
                    class="text-danger">(*)</span>：</label>
                <div class="col-sm-8">
                    <div><input type="radio" name="needShow" id="needShow1" checked="" value="">是&nbsp;&nbsp;<input
                            type="radio" name="needShow" id="needShow2" value="">否&nbsp;&nbsp;
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="form-group" style="clear:both;">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="layui-btn layui-btn-primary" onclick="cancelUnit()">取消</button>
            <button class="layui-btn layui-btn-blue" onclick="submitUnit()">提交</button>
        </div>
    </div>
</div>
<!-- 主信息区域end -->
</body>
</html>
<script src="<c:url value='/javaScript/sys/sysparam/unitParamParent.js?v=2' />"></script>