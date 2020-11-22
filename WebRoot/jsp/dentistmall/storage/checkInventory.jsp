<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/25
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>库存盘点</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/formSelects-v4.css?v=1'/>">
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?v=2' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <script type="text/javascript">
        //需要先配置layui，并使用use方法，这是单独将formSelects模块加入
        layui.config({
            base: '../../../js/' //此处路径请自行处理
        }).extend({
            formSelects: 'formSelects-v4'
        });
        // layui.use(['jquery', 'formSelects'], function () {
        //     var formSelects = layui.formSelects;
        // });

    </script>
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

        .ibox-tools button {
            width: 120px;
        }
        @media screen and (min-width: 1401px){
            .newbtn{
                background: #1ab394;
                border: none;
                color: #ffffff;
                border-radius: 7px;
                padding: 7px 30px;
            }
            .newtpdiv{
                width: 285px;
                display: inline-block;
                line-height: 34px;
                margin-right: 20px;
            }
            .newtpdiv label{
                display: inline-block;
                padding-top: 0px
            }
            .newtpdiv input{
                display: inline-block;
                width: 210px
            }
            .xm-select-parent{
                display: inline-block;
                width: 210px
            }

        }

        @media screen and (max-width: 1400px){
            .newbtn{
                background: #1ab394;
                border: none;
                color: #ffffff;
                border-radius: 7px;
                padding: 7px 30px;
                margin-top: 23px;
            }
            .newtpdiv{
                width: 200px;
                display: inline-block;
                line-height: 34px;
                margin-right: 20px;
            }
            .newtpdiv label{
                display: inline-block;
                padding-top: 0px
            }
            .newtpdiv input{
                display: inline-block;
                width: 210px
            }
            .xm-select-parent{
                display: inline-block;
                width: 210px
            }
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
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>盘点管理</span>
        </h5>
        <!-- 标题文字区域end -->
        <div class="ibox-tools" id="win_tools_but">
        </div>
    </div>

    <!-- 操作栏 -->
    <div>
        <div class="ibox-content" style="padding: 13px 10px 10px;">
            <div style="display: inline-block">
                <form class="form-horizontal" id="win_form_search">
                    <div id="win_form_hidden"></div>
                    <div class="form-group" style="margin-bottom:0px;padding-left: 20px;">
                        <div class="newtpdiv">
                            <label class="control-label">盘点单号：</label>
<%--                            <div class="col-sm-2">--%>
                                <input id="checkCode" name="checkCode" class="form-control" placeholder="单号关键字"
                                       type="text" aria-required="false" aria-invalid="false">
<%--                            </div>--%>
                        </div>
                        <div class="newtpdiv">
                        <label class="control-label">盘点类型：</label>
<%--                        <div class="col-sm-2">--%>
                            <select class="form-control" name="checkTypeStr" xm-select="checkType"
                                    xm-select-show-count="1"
                                    id="checkType" xm-select-skin="primary">
                                <%--                            <option value="">选择盘点类型</option>--%>
                                <%--                            <option value="1">月度</option>--%>
                                <%--                            <option value="2">季度</option>--%>
                                <%--                            <option value="3">年度</option>--%>
                                <%--                            <option value="4">每次</option>--%>
                            </select>
<%--                        </div>--%>
                        </div>
                        <div class="newtpdiv">
                        <label class="control-label">制作人：</label>
<%--                        <div class="col-sm-2">--%>
                            <input id="createRen" name="createRen" class="form-control" placeholder="" type="text"
                                   aria-required="false" aria-invalid="false">
<%--                        </div>--%>
                        </div>
                        <div class="newtpdiv">
                        <label class="control-label">盘点时间：</label>
<%--                        <div class="col-sm-2">--%>
                            <input id="startDate" name="startDate" class="form-control" placeholder="选择时间" type="text"
                                   readonly onclick="selectRangeDate()" class="form-control">
<%--                        </div>--%>
                        </div>
                    </div>
                </form>
            </div>
            <div style="vertical-align: center;text-align: center;display: inline-block;margin-left: 50px">
                <button class="newbtn" onkeyup="main_search()" onclick="main_search()">查询</button>
            </div>
        </div>
    </div>

    <!-- 表格栏 -->
    <div class="flex border-bottom" style="padding: 15px 20px 0px 21px;background-color: white">
        <%--        <input id="selectAll" type="checkbox" onclick="selectAll(this.checked)"/><span--%>
        <%--            style="padding-left: 15px">数据列表</span>--%>
        <div style="float: right">
            <%--            <select id="showLimit">--%>
            <%--                <option value="0">显示条数</option>--%>
            <%--                <option value="5">5</option>--%>
            <%--                <option value="10">10</option>--%>
            <%--            </select>--%>
            <%--            <select id="showOrder">--%>
            <%--                <option value="0">排序方式</option>--%>
            <%--                <option value="1">升序</option>--%>
            <%--                <option value="2">降序</option>--%>
            <%--            </select>--%>
        </div>
    </div>

    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <table id="win_datagrid_main" class="mmg"></table>
        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
        <div style="margin-top: -32px;">
            <%--            <input id="selectAll1" type="checkbox" onclick="selectAll(this.checked)"/>--%>
            <%--            <button class="btn btn-white btn-xs" onclick="exportExcel()">导出Excel</button>--%>
            <div id="countDiv">共计<span id="allCount">0</span>条数据，其中月度盘点<span id="monthCount">0</span>条，季度盘点<span
                    id="seasonCout">0</span>条，年度盘点<span id="yearCount">0</span>条，每次盘点<span
                    id="everyCount">0</span>条
            </div>
        </div>
    </div>
</div>
<div id="selectDateDiv" style="display: none" class="rangeDate">
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
<script src="<c:url value='/javaScript/dentistmall/storage/checkInventory.js?v=16'/>"></script>
<script>

    $('#win_form_search').on('keyup', function () {
        if (event.keyCode == 13) {
            main_search();
        }
    })
</script>