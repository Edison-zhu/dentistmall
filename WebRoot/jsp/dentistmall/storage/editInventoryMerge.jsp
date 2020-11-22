<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/28
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>基础平台系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
</head>
<style>
    #win_edit_toolbar {
        float: right;
    }
</style>
<body style="overflow:hidden; ">
<!-- 表单部分begin -->

<div class="panel panel-default">
    <!-- 隐藏域begin -->
    <div id="win_form_edithidden"></div>
    <!--隐藏区域end -->
    <!-- 正文部分begin -->
    <div class="panel-body">
        <form id="accountForm" class="form-horizontal form-bordered"
              role="form">
            <div id="win_form_body">
            </div>
        </form>
        <div class="ibox float-e-margins" style="border:1px solid #e7eaec;margin-top:5px">
            <!--标题栏区域 begin-->
            <div class="ibox-title"
                 style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:45px;margin-bottom:10px">
                <!-- 标题文字区域begin -->
                <h5>&nbsp&nbsp商品列表</h5>
                <!-- 标题文字区域end -->
                <div class="ibox-tools" id="win_toolbar"></div>
            </div>
            <div>
                <table id="win_datagrid_main" class="mmg"></table>
            </div>
        </div>
    </div>
    <!-- 正文部分end -->
    <!-- 工具栏部分begin -->
    <div class="panel-footer">
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-4" id="win_edit_toolbar">
            </div>
        </div>
    </div>
    <!-- 工具栏部分end -->
</div>

<!-- 表单部分end-->
</body>

</html>
<script src="<c:url value='/javaScript/dentistmall/storage/editInventoryMerge.js?v=10'/>"></script>