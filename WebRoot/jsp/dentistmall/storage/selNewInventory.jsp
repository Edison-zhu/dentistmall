<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/27
  Time: 11:10
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
<div class="row  wrapper wrapper-content " style="margin-left: 0;margin-right: 0;">
    <div class="col-sm-2" style="padding-left: 0;">
        <div class="ibox float-e-margins">
            <!-- 标题栏区域 begin-->
            <div class="ibox-title">
                <!-- 标题文字区域begin -->
                <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp分类信息</h5>
                <!-- 标题文字区域end -->
                <!-- 标题栏工具区域begin -->
                <div class="ibox-tools">
                </div>
            </div>
            <!-- 标题栏区域 end-->
            <!-- 操作区域 begin-->
            <div class="ibox-content" style="padding: 0px;">
                <div class="scrollbarlist">
                    <div class="panel-body">
                        <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                    </div>
                </div>
            </div>
            <!-- 操作区域 end-->
        </div><div class="ibox float-e-margins">
            <!-- 标题栏区域 begin-->
            <div class="ibox-title">
                <!-- 标题文字区域begin -->
                <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp分类信息</h5>
                <!-- 标题文字区域end -->
                <!-- 标题栏工具区域begin -->
                <div class="ibox-tools">
                </div>
            </div>
            <!-- 标题栏区域 end-->
            <!-- 操作区域 begin-->
            <div class="ibox-content" style="padding: 0px;">
                <div class="scrollbarlist">
                    <div class="panel-body">
                        <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                    </div>
                </div>
            </div>
            <!-- 操作区域 end-->
        </div>
    </div>

    <div class="col-sm-10">
        <div class="row">
            <div class="ibox float-e-margins">
                <!-- 标题栏区域 begin-->
                <div class="ibox-title" style="display: flex;justify-content: space-between;align-items: center;">
                    <!-- 标题文字区域begin -->
<%--                    <h5><i class="fa fa-tint"></i>&nbsp&nbsp物料信息</h5>--%>
                    <!-- 标题文字区域end -->
                    <!-- 标题栏工具区域begin -->
                    <div id="show" class="ibox-tools">
                        <button type="button" class="btn btn-round btn-info btn-sm fa fa-edit"
                                onclick="addMaterielDetail()">&nbsp;新增物料
                        </button>
                        <button type="button" class="btn btn-round btn-default btn-sm fa fa-search" onclick="search()">
                            &nbsp;刷新
                        </button>
                        <button type="button" class="btn btn-round btn-info btn-sm fa fa-edit" onclick="exportExcel()">
                            &nbsp;导出excel
                        </button>
                    </div>
                    <div class="" id="selShow" style="display: none;text-align: right;padding-right: 20px;float: right">
                        <div class="row" style="margin-bottom: 10px">
                            <div style="text-align: left;display: flex;justify-content: space-between;align-items: center;width: 387px;">
                                <form id="queryForm" style="display: inline">
                                    <div id="searchForm" class="form-inline" style="display: inline">
                                        <label>关键字查询：</label>
                                        <input type="text" id="searchName" onfocus="addClass()" onblur="removeClass()" onkeyup="enterSearch()" class="form-control" placeholder="关键字查询"
                                               name="searchName" value=""/>
                                    </div>
                                </form>
                                <button id="btnSearch" style="display: inline; margin-left: 20px;width: 80px;height: 28px;border-radius: 5px;background: #1ab394;text-align: center;line-height: 28px;border: none;color: #fff;margin-left: 0;"
                                        onclick="search(true)">
                                    查询
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- 标题栏工具区域end -->
                </div>
                <!-- 标题栏区域 end-->
                <!-- 操作区域 begin-->
                <div class="ibox-content">
                    <div class="row" style="margin-bottom: 10px">
<%--                        <form id="queryForm" style="display: inline">--%>
<%--                            <input type="hidden" id="mdpsId" name="mdpsId">--%>
<%--                            <input type="hidden" id="mdpId" name="mdpId"/>--%>
<%--                            <!--  <label class="control-label col-sm-2">栏目名称</label>--%>
<%--                             <div class="controls col-sm-2">--%>
<%--                                 <input type="text" class="form-control" placeholder="栏目名称" name="columnName" value="">--%>
<%--                             </div> -->--%>
<%--                            <div id="searchForm" class="form-inline" style="display: inline">--%>
<%--                                <label>关键字查询</label>--%>
<%--                                <input type="text" id="searchName" class="form-control" placeholder="关键字查询"--%>
<%--                                       name="searchName" value=""/>--%>

<%--                                <label>物料状态</label>--%>
<%--                                <input type="text" id="searchState" class="form-control" placeholder="物料状态" name="state"--%>
<%--                                       value=""/>--%>

<%--                                <label>操作时间</label>--%>
<%--                                <input type="text" placeholder="选择开始日期" id="cTime_start" name="startDate" readonly--%>
<%--                                       class="form-control" style="display: inline"/> ---%>
<%--                                <input type="text" placeholder="选择结束日期" id="cTime_end" name="endDate" readonly--%>
<%--                                       class="form-control" style="display: inline"/>--%>
<%--                            </div>--%>
<%--                        </form>--%>
<%--                        <button class="btn btn-primary" style="display: inline; margin-left: 20px;" onclick="search()">--%>
<%--                            查询--%>
<%--                        </button>--%>
<%--                    </div>--%>
                    <table id="datagrid1" class="mmg"></table>
                    <div id="pg" style="text-align: right;"></div>
                </div>
                <!-- 操作区域 end-->
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/selNewInventory.js?v=9'/>"></script>