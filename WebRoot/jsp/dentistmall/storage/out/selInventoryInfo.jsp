<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/6/14
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Title</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
</head>
<body>
<!-- 表单部分begin -->
<style>
    .ibox-content{
        background-color: #ffffff;
        color: inherit;
        padding: 0px 0px 0px 0px;
        border-color: #e7eaec;
        border-image: none;
        border-style: solid solid none;
        border-width: 1px 0px;
    }
    #button3 {
        position: absolute;
        left: 440px;
        width: 95px;
        height: 21px;
        vertical-align: middle;
        align-items: center;
    }
    #button4 {
        position: relative;
        left: 540px;
        width: 95px;
        height: 21px;
        vertical-align: middle;
        align-items: center;
    }
</style>

<div class="panel panel-default">
    <!-- 隐藏域begin -->
    <div id="win_form_edithidden"></div>
    <!--隐藏区域end -->
    <!-- 正文部分begin -->
<%--    <div class="col-sm-2">--%>
<%--        <div class="ibox float-e-margins">--%>
            <!-- 标题栏区域 begin-->
<%--            <div class="ibox-title" style="width: 183px">--%>
<%--                <!-- 标题文字区域begin -->--%>
<%--                <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp分类信息</h5>--%>
<%--                <!-- 标题文字区域end -->--%>
<%--                <!-- 标题栏工具区域begin -->--%>
<%--                <div class="ibox-tools">--%>
<%--                </div>--%>
<%--            </div>--%>
            <!-- 标题栏区域 end-->
            <!-- 操作区域 begin-->
<%--            <div class="ibox-content" style="width: 183px">--%>
<%--                <div class="scrollbarlist" style="width: 150px">--%>
<%--                    <div class="panel-body" style="padding: 0px">--%>
<%--&lt;%&ndash;                        <ul id="tree" class="ztree" style="overflow:auto;"></ul>&ndash;%&gt;--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
            <!-- 操作区域 end-->
<%--        </div>--%>
<%--    </div>--%>

    <div class="col-sm-10">
        <div class="panel-body scrollbarinfo">
            <div class="ibox float-e-margins" style="border:1px solid #e7eaec;margin-top:5px">
                <!--标题栏区域 begin-->
                <div class="ibox-title"
                     style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:45px;margin-bottom:10px">
<%--                    <form id="accountForm" class="form-horizontal form-bordered"--%>
<%--                          role="form">--%>
<%--                        <!-- 标题文字区域begin -->--%>
<%--                        <label>关键字：</label><input id="searchName" name="searchName"/>--%>
<%--                        <label>入库日期：</label><input id="createDate" name="createDate"/>--%>
<%--                        <label>采购人：</label><input id="customerName" name="customerName"/>--%>
<%--                    </form>--%>
                    <input id="searchName" style="width: 231px" placeholder="订单编号/物料号/物料名称/规格"/>
                    <a onclick="main_wait_search()" class='btn btn-success  btn-xs' style="color:#fff;margin-top: 4px;width: 107px;margin-left: 10px"
                       id="button1">搜索</a>
                </div>
                <div id="gridDiv">
                    <table id="win_datagrid_wait_main" class="mmg"></table>
                    <div id="_all_win_datagrid_wait_pg" style="text-align: right;"></div>
                </div>
                <!-- !--表格区域begin -->
                <div class="panel-footer">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-4">
                            <button type="button"  onclick="saveBaosun()" class="btn btn-primary btn-xs" id="button3">确定</button>
                            <button type="button"  onclick="closeWin()" class="btn btn-default btn-xs" id="button4">关闭</button>
                        </div>
                    </div>
                </div>
                <!--!--表格区域end-->

            </div>
        </div>
    </div>
    <!-- 正文部分end -->
</div>

<!-- 表单部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/out/selInventoryInfo.js?v=31'/>"></script>