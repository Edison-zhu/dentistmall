<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>商家商品管理</title>
    <%@ include file="/jsp/lib.jsp"%>
    <%@ include file="/jsp/link.jsp"%>

</head>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="row  wrapper wrapper-content ">
    <div class="col-sm-12">
        <div class="row">
            <div class="col-sm-12">
                <!-- 表格区域begin -->
                <div class="ibox-content" style="padding: 10px 10px 10px;">
                    <table id="win_datagrid_main" class="mmg"></table>
                    <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
                </div>
                <!-- 表格区域end -->
            </div>
        </div>
    </div>
</div>
<!-- 主信息区域end -->
</body>
</html>
<script   src="<c:url value='/js/cookie.js' />"></script>
<script src="<c:url value='/javaScript/dentistmall/business/vieGoodOperationLog.js?v=6'/>"></script>

