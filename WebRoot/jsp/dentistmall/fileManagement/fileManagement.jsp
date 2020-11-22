<%-- Author: songwenwen  Date: 2020/7/30 --%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>商品模型相册管理</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/css/fileManagement/fileManagement.css?v=1' />">
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <script type="text/javascript">
        //需要先配置layui，并使用use方法，这是单独将formSelects模块加入
        layui.config({
            base: '../../../js/' //此处路径请自行处理
        }).extend({
            formSelects: 'formSelects-v4'
        });
    </script>
</head>
<body>


<%-- 头部 s --%>
<div class="toptit d_flex_between">
    <div class="tit"><i class="layui-icon layui-icon-template-1"></i> 商品模型相册管理</div>
    <div class="white_btn" onclick="main_search()">刷新</div>
</div>
<%-- 头部 e --%>

<!-- 查询 s -->
<div class="public_box">
    <div class="tpbox d_flex_between">筛选查询</div>
    <div class="ftbox">
        <form class="d_flex_between" id="win_form_search">
            <div class="d_flex_left lf_input_box">
                <span>输入搜索：</span>
                <input id="searchName" name="searchName" class="form-control" placeholder="相册名称" type="text"></div>
            <div class="green_btn" onkeyup="main_search()" onclick="main_search()">开始查询</div>
        </form>
    </div>
</div>
<!-- 查询 e -->

<!-- 表格 s -->
<div class="public_box2">
    <div class="tpbox d_flex_between">数据列表
        <div class="white_btn" onclick="open_Add_File_Box()">新建相册</div>
    </div>
    <div class="ftbox">
        <!-- ----------------- -->
        <table id="win_datagrid_main" class="mmg"></table>
        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
        <!-- ----------------- -->
    </div>
</div>
<!-- 表格 e -->

<!-- 新建相册 s -->
<div id="add_File_box" class="addfileform">
    <div class="nameline">
        <label><span>*</span>相册名称:</label><input type="text" name="file_name" id="file_name" placeholder="请输入相册名称">
    </div>
    <div class="descline">
        <label>相册描述:</label><textarea name="file_desc" id="file_desc" placeholder="请输入相册描述"></textarea></div>
    <div class="btnline">
        <div class="btn" onclick="close_Add_File_Box()">返回</div>
        <div class="btn" onclick="Add_File()">提交</div>
    </div>
</div>
<!-- 新建相册 e -->

</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/fileManagement/fileManagement.js?v=47'/>"></script>
