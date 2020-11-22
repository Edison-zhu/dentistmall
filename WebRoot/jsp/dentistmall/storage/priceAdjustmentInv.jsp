<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/25
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>库存盘点</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?v=2' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
</head>
<style>

    .rangeDate {
        position: fixed;
        top: 130px;
        left: 30%;
        /* float: left; */
        z-index: 4;
        background: white;
        border: 1px solid;
        box-shadow: 1px 0px 10px 3px;
        padding: 10px;
    }
    .ibox-tools button{
        width: 120px;
    }
</style>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5>
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>调价</span>
        </h5>
        <!-- 标题文字区域end -->
        <div class="ibox-tools" id="win_tools_but">
        </div>
    </div>

    <!-- 操作栏 -->
    <div>
        <div class="ibox-content" style="padding: 13px 33px 10px;">
            <div style="width: 86%;display: inline-block">
                <form class="form-horizontal" id="win_form_search">
                    <div id="win_form_hidden"></div>
                    <!--2020年07月06日13:48:00朱燕冰修改调整查询按钮被覆盖问题 -->
                    <div class="form-group" style="margin-bottom:0px;margin-right: 83px"><label
                            class="col-sm-1 control-label">调价号：</label>
                        <div class="col-sm-2"><input id="checkCode" name="paiCode" class="form-control"
                                                     placeholder="" type="text" aria-required="false"
                                                     aria-invalid="false"></div>
                        <label class="col-sm-1 control-label">关键字：</label>
                        <div class="col-sm-2"><input id="searchName" name="searchName" class="form-control"
                                                     placeholder="编号/规格"
                                                     type="text" aria-required="false" aria-invalid="false"></div>
                        <label class="col-sm-1 control-label">类型：</label>
                        <div class="col-sm-2"><select type="select" class="form-control" name="paiType"
                                                      id="checkType">
                            <option value="">选择盘点类型</option>
                            <option value="1">市场调价</option>
                            <option value="2">正常调价</option>
                        </select></div>

                        <label class="col-sm-1 control-label">日期：</label>
                        <div class="col-sm-2">
                            <input id="startDate" name="startDate" class="form-control" style="width: 300px;"
                                                     placeholder="选择时间" type="text"  readonly onclick="selectRangeDate()">
                        </div>
                    </div>
                </form>
            </div>
            <div style="vertical-align: top;margin-left: 90px;text-align: right;display: inline-block">
                <button class="btn btn-primary btn-xss" onclick="main_search()" onkeyup="main_search()" id="onSubmit" style="width: 100px;height: 26px;padding: 0;margin-top: 4px;border-radius: 13px">查询</button>
            </div>
        </div>
    </div>

    <!-- 表格栏 -->

    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <table id="win_datagrid_main" class="mmg"></table>
        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
    </div>
</div>
<div id="rangeDateDiv" style="display: none" class="rangeDate">
    <div>
        <a style="position: relative;left: 635px;margin-bottom: 10px;" onclick="closeSelect()">X</a>
    </div>
    <div class="col-sm-2">
        <ul>
            <li><a onclick="selectDate(0)">今天</a></li>
            <li><a onclick="selectDate(1)">昨天</a></li>
            <li><a onclick="selectDate(2)">近三天</a></li>
            <li><a onclick="selectDate(3)">近一周</a></li>
            <li><a onclick="selectDate(4)">近两周</a></li>
            <li><a onclick="selectDate(5)">近三周</a></li>
            <li><a onclick="selectDate(6)">最近一个月</a></li>
            <li><a onclick="selectDate(7)">近两个月</a></li>
        </ul>
    </div>
    <div class="col-sm-8">
        <div id="rangeDate">

        </div>
    </div>
</div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/priceAdjustmentInv.js?v=16'/>"></script>
<script>

    $('#win_form_search').on('keyup', function () {
        if (event.keyCode == 13) {
            main_search();
        }
    })
</script>