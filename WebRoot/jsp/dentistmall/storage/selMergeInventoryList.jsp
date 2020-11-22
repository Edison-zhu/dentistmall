<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/29
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>采购中心系统</title>
    <%@ include file="/jsp/lib.jsp"%>
    <%@ include file="/jsp/link.jsp"%>
</head>
<style>
    .btn1{
        width: 80px;
        height: 30px;
        border-radius: 5px;
        text-align: center;
        line-height: 30px;
        background: #1ab394;
        border: none;
        color: #ffffff;
        font-size: 14px;
    }
    .btn2{
        width: 80px;
        height: 30px;
        border-radius: 5px;
        text-align: center;
        line-height: 30px;
        background: #e6e6e6;
        border: none;
        color: #333333;
        font-size: 14px;
        margin-left: 20px;
        margin-right: 50px;
    }
    .btn3{
        width: 80px;
        height: 30px;
        border-radius: 5px;
        text-align: center;
        line-height: 30px;
        background: #1ab394;
        border: none;
        color: #ffffff;
        font-size: 14px;
        display: inline-block;
        margin-left: 20px;
    }
    .btn3:hover{
        color: #ffffff;
    }

</style>
<body style="overflow:hidden; ">
<!-- 表单部分begin -->
<form id="queryForm" class="form-horizontal form-bordered" role="form" onsubmit="return false;">
    <div class="panel panel-default">
        <!-- 正文部分begin -->
        <div class="panel-body">
            <div class="col-sm-2" style="height: 333px">
                <div class="ibox float-e-margins">
                    <!-- 标题栏区域 begin-->
<%--                    <div class="ibox-title">--%>
<%--                        <!-- 标题文字区域begin -->--%>
<%--                        <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp分类信息</h5>--%>
<%--                        <!-- 标题文字区域end -->--%>
<%--                        <!-- 标题栏工具区域begin -->--%>
<%--                        <div class="ibox-tools">--%>
<%--                        </div>--%>
<%--                    </div>--%>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->
                    <div class="ibox-content" style="height: 333px;width: 190px">
                        <div class="scrollbarlist"style="width: 150px">
                            <div class="panel-body"style="padding: 0px">
                                <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                            </div>
                        </div>
                    </div>
                    <!-- 操作区域 end-->
                </div>
            </div>
            <!--条件输入区域begin-->
            <div class="controls col-sm-10">
                <input type="hidden" name="state" value="1">
                <input type="hidden" name="matType" id="matType" value="">
                <div class="form-inline form-group">
                    <div class="controls" style="font-size:15px;display: inline-block;">
                        物料名称:
                        <input type="text" class="form-control" placeholder="物料名称" id="searchName" name="searchName" value="" style="width:200px">
                    </div>
<%--                    <div class="controls col-sm-5" style="font-size:15px">--%>
<%--                        规格:--%>
<%--                        <input type="text" class="form-control" placeholder="规格" name="mmfName" value="" style="width:120px">--%>
<%--                    </div>--%>

                        <a class="btn3" onclick="search2()" onkeyup="search2()">查询</a>

                </div>
                <table id="datagrid1" class="mmg" ></table>
                <div id="pg" style="text-align: right;"></div>
            </div>
        </div>
        <!-- 正文部分end -->
        <!-- 工具栏部分begin -->
        <div class="panel-footer">
            <div class="form-group">
                <div class="col-sm-offset-2" style="float: right">
                        <button id="saveMerge" type="button" onclick="save()" class="btn1">确定</button>
                        <button id="cancelMerge" type="button"  onclick="closeWin()" class="btn2">关闭</button>
<%--                    <button id="saveMerge" type="button"  onclick="save()" class="btn btn-primary"style="margin-left: 800px">确定</button>--%>
<%--                    <button id="cancelMerge" type="button"  onclick="closeWin()" class="btn btn-default"style="margin-left: 880px;margin-top: -51px">关闭</button>--%>
                </div>
            </div>
        </div>
        <!-- 工具栏部分end -->
    </div>
</form>
<!-- 表单部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/selMergeInventory.js?v=10'/>"></script>
<script>
    $('#searchName').on('keyup', function () {
        if (event.keyCode == 13) {
            search2();
        }
    })
</script>