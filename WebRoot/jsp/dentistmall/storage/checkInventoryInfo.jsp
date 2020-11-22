<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/25
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>库存盘点</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?v=2' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <%--    <link rel="stylesheet" href="<c:url value='/js/formSelects-v4.css?v=1'/>">--%>
    <script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
    <script src="<c:url value="/js/xm-select.js?v=1" /> "></script>
    <%--    <script type="text/javascript">--%>
    <%--        //需要先配置layui，并使用use方法，这是单独将formSelects模块加入--%>
    <%--        layui.config({--%>
    <%--            base: 'js/' //此处路径请自行处理--%>
    <%--        }).extend({--%>
    <%--            formSelects: 'formSelects-v4'--%>
    <%--        });--%>
    <%--        // layui.use(['jquery', 'formSelects'], function () {--%>
    <%--        //     var formSelects = layui.formSelects;--%>
    <%--        // });--%>

    <%--    </script>--%>
</head>
<style>
    .ibox-tools button {
        width: 120px;
    }

    /*新样式*/

    .newmlf {
        margin-left: 20px;
    }

    @media screen and (min-width: 1401px) {
        .newbtn {
            background: #1ab394;
            border: none;
            color: #ffffff;
            border-radius: 7px;
            padding: 6px 30px;
        }

        .newtpdiv {
            width: 285px;
            display: inline-block;
            line-height: 34px;
        }

        .newtpdiv2 {
            width: 225px;
            display: inline-block;
            line-height: 34px;
        }

        .newtpdiv3 {
            width: 245px;
            display: inline-block;
            line-height: 34px;
        }

    }

    @media screen and (max-width: 1400px) {
        .newbtn {
            background: #1ab394;
            border: none;
            color: #ffffff;
            border-radius: 7px;
            padding: 6px 30px;
            margin-top: 23px;
        }

        .newtpdiv {
            width: 200px;
            display: inline-block;
            line-height: 34px;
        }

        .newtpdiv2 {
            width: 135px;
            display: inline-block;
            line-height: 34px;
        }

        .newtpdiv3 {
            width: 135px;
            display: inline-block;
            line-height: 34px;
        }
    }
</style>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5>
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>盘点维护</span>
        </h5>
        <!-- 标题文字区域end -->
        <div class="ibox-tools" id="win_tools_but">
        </div>
    </div>

    <!-- 操作栏 -->
    <div>
        <div class="ibox-content" style="padding: 13px 10px 10px;">
            <div style="display: inline-block">
                <div class="form-inline" style="margin-bottom:0px;">
                    <form id="saveForm">
                        <div class="newtpdiv">
                            <label class="control-label">盘点名称<span
                                    class="text-danger">(*)</span>：</label>
                            <input id="checkName" name="checkName" class="form-control" placeholder="盘点名称"
                                   type="text" aria-required="true" aria-invalid="false">
                        </div>
                        <div class="newtpdiv newmlf">
                            <label class="control-label">盘点单号<span class="text-danger">(*)</span>：</label>
                            <input id="checkCode" name="checkCode" class="form-control" placeholder="盘点单号"
                                   type="text" readonly aria-required="true" aria-invalid="false">
                        </div>
                        <div class="newtpdiv2 newmlf">
                            <label class="control-label">盘点类型<span class="text-danger">(*)</span>：</label>
                            <select type="select" class="form-control" name="checkType" id="checkType">
                                <option value="">选择盘点类型</option>
                                <option value="1">月度</option>
                                <option value="2">季度</option>
                                <option value="3">年度</option>
                                <option value="4">每次</option>
                            </select>
                        </div>
                        <div class="newtpdiv newmlf">
                            <label class="control-label">盘点分类<span class="text-danger">(*)</span>：</label>
                            <div class="" style="display: inline-block;width: 185px;vertical-align: middle;"
                                 name="checkPart" id="checkPart">
                            </div>
                        </div>
                        <div class="newtpdiv3 newmlf">
                            <label class="control-label">备注：</label>
                            <input type="text" class="form-control" name="remark" id="remark"/>
                        </div>
                    </form>
                </div>
            </div>
            <div style="vertical-align: center;text-align: center;float: right">
                <button id="startCheck" disabled class="newbtn" onclick="startCheck()">导入盘点数据</button>
            </div>
            <br/>
            <div style="display: inline-block;margin-top: 20px;">
                <div class="form-inline">
                    <form id="filterForm">
                        <div class="newtpdiv">
                            <label class="control-label">物料名称：</label>
                            <input id="mddName" name="searchName" class="form-control" placeholder="编号/名称/规格"
                                   type="text" aria-required="true" aria-invalid="false" onkeydown="entersearch()">
                        </div>
                        <div class="newtpdiv newmlf">
                            <label class="control-label">品牌选择：</label>
                            <input id="brandSel" name="brand" class="form-control" placeholder=""
                                   type="text" aria-required="true" aria-invalid="false" onkeydown="entersearch()">
                        </div>
                        <div class="newtpdiv newmlf">
                            <label class="control-label">批次号：</label>
                            <input type="text" class="form-control" name="batchCode" id="batchCode"
                                   onkeydown="entersearch()"/>
                        </div>
                    </form>
                </div>
            </div>
            <div style="vertical-align: top;text-align: center;display: inline-block;margin-top: 20px;margin-left: 50px">
                <button id="filter" class="newbtn" onclick="filterCheck()">筛选</button>
            </div>
            <script>
                function entersearch() {
                    var event = window.event || arguments.callee.caller.arguments[0];
                    if (event.keyCode == 13) {
                        filterCheck();
                    }
                }
            </script>
        </div>
    </div>

    <!-- 表格栏 -->


    <div class="ibox-content" style="padding: 5px 10px 10px">
        <table id="win_datagrid_main" class="mmg"></table>
        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>

        <div style="float:left;position: relative;top: -29px;">共计<span id="allCount">0</span>条数据，已盘点<span
                id="checkCount">0</span>条，未盘点<span
                id="noCheckCount">0</span>条
        </div>
    </div>


</div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/checkInventoryInfo.js?v=99'/>"></script>