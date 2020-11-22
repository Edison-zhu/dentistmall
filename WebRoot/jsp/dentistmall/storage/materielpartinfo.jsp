<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/11
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>物料分类详细信息</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <%--    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">--%>
    <%--    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>--%>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>

</head>
<style>
    .ibox-tools button {
        width: 95px;
        vertical-align: middle;
        align-items: center;
    }

    #searchForm {
        width: 50%;
        display: inline;
        margin-left: 18px;
    }

    .divFilter {
        display: none;
        float: left;
        margin: 0 auto;
        position: absolute;
        /*top: 20%;*/
        /*left: 20%;*/
        width: 50%;
        background-color: whitesmoke;
        z-index: 901;
        height: 380px;
    }

    .u-mask {
        display: none;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 900;
        width: 100%;
        height: 100%;
        background-color: #000000;
        filter: alpha(opacity=80);
        -moz-opacity: 0.8;
        -khtml-opacity: 0.8;
        opacity: 0.5;
    }

    /*开关的大小*/
    .switch-container {
        height: 15px;
        width: 30px;
    }

    /*设置checkbox不显示*/
    .switch {
        display: none;
    }

    /*设置label标签为椭圆状*/
    .switch-container label {
        display: block;
        background-color: #EEEEEE;
        height: 100%;
        width: 100%;
        cursor: pointer;
        border-radius: 25px;
    }

    /*在label标签内容之前添加如下样式，形成一个未选中状态*/
    .switch-container label:before {
        content: '';
        display: block;
        border-radius: 25px;
        height: 100%;
        width: 15px;
        background-color: white;
        opacity: 1;
        box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.08);
        -webkit-transition: all 0.2s ease;
    }

    /*在label标签内容之后添加如下样式，形成一个选中状态*/
    .switch-container label:after {
        position: relative;
        top: -15px;
        left: 15px;
        content: '';
        display: block;
        border-radius: 25px;
        height: 100%;
        width: 15px;
        background-color: white;
        opacity: 0;
        box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.08);
        -webkit-transition: all 0.2s ease;
    }

    /*选中后，选中样式显示*/
    .switch-container input:checked ~ label:after {
        opacity: 1;
    }

    /*选中后，未选中样式消失*/
    .switch-container input:checked ~ label:before {
        opacity: 0;
    }

    /*选中后label的背景色改变*/
    .switch-container input:checked ~ label {
        background-color: green;
    }

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

    .newbtn1 {
        width: 120px;
        height: 30px;
        background: #1ab394;
        border-radius: 15px;
        border: 1px solid #1ab394;
        text-align: center;
        line-height: 30px;
        color: #fff;
        margin-left: 20px;
        display: inline;
    }

    .newbtn2 {
        width: 120px;
        height: 30px;
        background: #fff;
        border-radius: 15px;
        border: 1px solid #1ab394;
        text-align: center;
        line-height: 30px;
        color: #1ab394;
        margin-left: 20px;
        display: inline-block;
    }
    .newbtn2:hover{
        color: #1ab394;
    }
</style>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="row  wrapper wrapper-content ">
    <div class="col-sm-2">
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
            <div class="ibox-content">
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
                <div class="ibox-title">
                    <!-- 标题文字区域begin -->
                    <h5><i class="fa fa-tint"></i>&nbsp&nbsp物料信息</h5>
                    <!-- 标题文字区域end -->
                    <!-- 标题栏工具区域begin -->
                    <div class="ibox-tools">
                        <button type="button" class="btn btn-round btn-info btn-sm fa fa-edit"
                                onclick="main_add()">&nbsp;新增物料
                        </button>
                        <button type="button" class="btn btn-round btn-default btn-sm fa fa-search" onclick="search()">
                            &nbsp;刷新
                        </button>
                        <button type="button" class="btn btn-round btn-info btn-sm fa fa-edit" onclick="exportExcel()">
                            &nbsp;导出excel
                        </button>
                    </div>
                    <!-- 标题栏工具区域end -->
                </div>
                <!-- 标题栏区域 end-->
                <!-- 操作区域 begin-->
                <div class="ibox-content">
                    <div class="row" style="margin-bottom: 10px">
                        <input type="hidden" id="mdpsId" name="mdpsId">
                        <input type="hidden" id="mdpId" name="mdpId"/>
                        <form id="queryForm" style="display: inline" onkeyup="entersearch()">
                            <!--  <label class="control-label col-sm-2">栏目名称</label>
                             <div class="controls col-sm-2">
                                 <input type="text" class="form-control" placeholder="栏目名称" name="columnName" value="">
                             </div> -->
                            <div id="searchForm" class="form-inline" style="display: inline">
                                <label>关键字查询</label>
                                <input style="width: 260px" type="text" id="searchName" class="form-control" placeholder="生产厂家/物料编号/名称/别名/规格"
                                       name="searchName" value="" onkeyup="entersearch()"/>

                                <label style="margin-left: 20px">物料状态</label>
                                <select id="state" class="form-control" name="state">
                                    <option value="">全部</option>
                                    <option value="1">启用</option>
                                    <option value="2">停用</option>
                                </select>

                                <label style="margin-left: 20px">操作时间</label>
                                <input type="text" placeholder="选择日期" id="cTime_start" name="startDate" readonly
                                       onclick="selectRangeDate()"
                                       class="form-control" style="display: inline;width: 300px;"/>
                            </div>
                        </form>
                        <button class="newbtn1" onclick="search(true)">
                            查询
                        </button>
                        <div style="display: inline-block"><a class="newbtn2" onclick="moveToNewPart()">移动分类</a></div>
                    </div>
                    <script>
                        function entersearch() {
                            var event = window.event || arguments.callee.caller.arguments[0];
                            if (event.keyCode == 13) {
                                search();
                            }
                        }
                    </script>

                    <table id="datagrid1" class="mmg"></table>
                    <div id="pg" style="text-align: right;"></div>
                </div>
                <!-- 操作区域 end-->
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
<!--内容部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/materielpartinfo.js?v=47'/>"></script>
