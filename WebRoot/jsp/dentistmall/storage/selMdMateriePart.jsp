<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/14
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>采购中心系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
</head>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="row  wrapper wrapper-content ">
    <div class="col-sm-4">
        <div class="ibox float-e-margins">
            <!-- 标题栏区域 begin-->
            <div class="ibox-title">
                <!-- 标题文字区域begin -->
                <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp分类信息</h5>
                <!-- 标题文字区域end -->
                <!-- 标题栏工具区域begin -->
                <div class="ibox-tools">
                    <a onclick="refresh()" style="color: blue; margin-right: 20px">刷新</a>
                    <a class="btn btn-white" style="color: black" onclick="addNewPart()">新增分类</a>
                </div>
            </div>
            <!-- 标题栏区域 end-->
            <!-- 操作区域 begin-->
            <div class="ibox-content">
                <div class="scrollbarlist" style="text-align: center">
                    <div class="panel-body">
                        <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                    </div>
                </div>
            </div>
            <!-- 操作区域 end-->
        </div>
    </div>
</div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/selMdMaterielPart.js?v=4'/>"></script>