<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/26
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <title>物料分类详细信息</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/modules/layer/default/layer.css?v=1'/>">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <%--    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">--%>
    <%--    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/modules/layer/default/layer.css?v=1'/>">--%>
    <%--    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>--%>
    <%--    <script src="<c:url value="/js/plugins/layui/lay/modules/layer.js" />"></script>--%>
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

        .topbox {
            background: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px;
            margin-bottom: 20px;
        }

        .topbox .inputbox {
            display: flex;
            align-items: center;
        }

        .topbox .inputbox .input {
            margin-right: 20px;
        }

        .topbox .inputbox .input input {
            height: 30px;
            line-height: 30px;
            padding-left: 5px;
        }

        .topbox .inputbox .input span {
            font-size: 14px;
            margin-right: 5px;
        }

        .topbox .btnbox {
            /* margin-right:2%; */
        }

        .topbox .btnbox button {
            width: 90px;
            height: 25px;
            text-align: center;
            line-height: 25px;
            background: none;
            border: 1px solid #1ab394;
            font-size: 12px;
            border-radius: 13px;
            color: #1ab394;
        }

        .topbox .aqbox {
            display: flex;
            align-items: center;
            /* margin-right: 1%; */
            line-height: 30px;

        }

        .topbox .aqbox input {
            margin-top: 0px;
            margin-right: 5px;
        }

        .topbox .aqbox span {
            font-size: 12px;
            font-weight: normal;
        }

        button {
            outline: none;
        }

        #safeBox {
            background-color: white;
            width: 380px;
            height: 285px;
            position: absolute;
            top: 100px;
            left: 1100px;
            z-index: 9999;
            display: none;
            padding: 10px;
            border-radius: 5px;
            box-shadow: 0 0 5px gray;
        }

        #safeBox .tit {
            width: 100%;
            height: 30px;
            margin-top: 8px;
            border-bottom: #C0C0C0 solid 1px;
            text-align: left;
        }

        #safeBox .tit img {
            width: 20px;
            height: 20px;
        }

        #safeBox .tit span {
            font-size: 14px;
        }
    </style>
</head>


<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="row  wrapper wrapper-content " >

    <!-- 新写头部 s -->
    <div class="topbox">
        <form id="queryForm" class="inputbox" onkeyup="searchEnter()">
            <div class="input"><span>关键字：</span><input id="searchName" name="searchName" placeholder="物料号/物料编号/规格/名称/别名"
                                                       type="text" aria-required="false" aria-invalid="false"></div><!--物料名称，别名，规格，编号 -->
            <div class="input"><span>库存数量：</span><input id="min" name="InventoryStart" placeholder="最小数"/> ~ <input
                    id="max" name="InventoryEnd" placeholder="最大数"/></div>
        </form>
        <div class="btnbox">
            <button onclick="main_search(true)">查询</button>
        </div>

        <div class="aqbox"><input type="checkbox" id="warning"
                                  onclick="inventoryWarn(this.checked)"/><span>库存预警</span></div>
        <div class="aqbox"><input type="checkbox" id="validDate"
                                  onclick="validateWarn(this.checked)"/><span>有效预警</span></div>
        <div class="aqbox"><input type="checkbox" id="noInventory"
                                  onclick="zeroWarn(this.checked)"/><span>无库存</span></div>

        <div class="ibox-tools" id="win_tools_but">
            <button onclick="openYJbox()" id="safetyEwBtn" type="button"
                    style="margin-left: 15px; width: 120px; vertical-align: middle;"
                    class="btn btn-rounded btn-default btn-sm fa fa-qqq "><img width="10px" height="10px"
                                                                               src="/dentistmall/img/SafetyEarlyWarning.png"><span
                    style="font-size: 12px;color: #333333">安全预警设置</span></button>
            <button id="win_but_export" type="button"
                    style="margin-left: 10px; width: 95px; vertical-align: middle;"
                    class="btn btn-rounded btn-success btn-sm fa fa-download " onclick="export_all()">全部导出
            </button>
            <button id="win_but_export1" type="button"
                    style="margin-left: 10px; width: 95px; vertical-align: middle;"
                    class="btn btn-rounded btn-info btn-sm fa fa-cloud-upload " onclick="imp_all()">&nbsp;导入&nbsp;
            </button>
        </div>


        <div id="safeBox" style="z-index: 9999">
            <div class="tit">
                <img src="/dentistmall/img/SafetyEarlyWarning.png"/>
                <span>安全预警设置</span>
            </div>
            <form id="saveForm">
                <div class="middle" style="margin-top: 20px">
                    <span style="margin-left: 20px">库存数量设置 &nbsp;&nbsp;<input id="inputMin" type="text"
                                                                              placeholder="最小安全数" onclick="yzInput()"
                                                                              style="width:100px;"/>&nbsp;~&nbsp;
                        <input id="inputMax" type="text" placeholder="最大安全数" onclick="yzInput()" style="width:100px;"/>
                    </span>
                </div>
                <div id="yz" style="width: 200px;height: 13px;margin-left:20px;display: none"><span
                        style="font-size: 10px;color: red">请输入正确的数字</span></div>
                <div id="yz1" style="width: 200px;height: 13px;margin-left:20px;display: none"><span
                        style="font-size: 10px;color: red">最小安全数不能大于最大安全数</span></div>
                <div class="middle" style="margin-top: 20px">
                    <span style="margin-left: 20px">预警天数 &nbsp;&nbsp;<input id="inputDay" type="text" placeholder=""
                                                                            onclick="" style="width:100px;"/>&nbsp;天
                    </span>
                </div>
                <div class="middle2" style="margin-top: 20px">
                    <span style="margin-left: 20px"><input type="checkbox" onclick="checkboxMatName(0)"
                                                           value="0">产品名称:&nbsp;&nbsp;&nbsp;&nbsp;<select
                            style="width: 80px" id="select1">
                            <option value="1" selected>包含</option>
                            <option value="2">不包含</option>
                        </select>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width:130px" id="input1"
                                                                value=""></span>
                </div>
                <div class="bottom" style="margin-top: 25px">
                    <span style="margin-left: 20px"><input type="checkbox"
                                                           onclick="checkboxNorm(1)">产品规格:&nbsp;&nbsp;&nbsp;&nbsp;<select
                            style="width: 80px"
                            id="select2">
                            <option value="1" selected>包含</option>
                            <option value="2">不包含</option>
                        </select>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width:130px" id="input2"
                                                                value=""></span>
                </div>
            </form>
            <div style="margin-top: 20px">
                    <span style="float: right"><button type="button" onclick="onclickSafety()" class="btn btn-primary">一键设置</button></span>
            </div>
        </div>
        <script>
        </script>
    </div>
    <div class="col-sm-2">
        <div class="ibox float-e-margins">
            <!-- 标题栏区域 begin-->
            <div class="ibox-title" style="width: 265px">
                <!-- 标题文字区域begin -->
                <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp分类信息</h5>
                <!-- 标题文字区域end -->
                <!-- 标题栏工具区域begin -->
                <div class="ibox-tools">
                </div>
            </div>
            <!-- 标题栏区域 end-->
            <!-- 操作区域 begin-->
            <div class="ibox-content" style="width: 265px">
                <div class="scrollbarlist"style="width: 150px">
                    <div class="panel-body"style="padding: 0px">
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
                    </div>
                    <!-- 标题栏工具区域end -->
                </div>
                <!-- 标题栏区域 end-->
                <!-- 操作区域 begin-->
                <div class="ibox-content">
                    <div class="row" style="margin-bottom: 10px">
                    </div>
                    <table id="datagrid1" class="mmg"></table>
                    <div id="pg" style="text-align: right;"></div>
                    <div>
                        本页合计：采购均价<span id="buyMoney">0</span>，零售均价<span id="retioMoney">0</span>
                        所有页合计：采购均价<span id="buyMoneyAll">0</span>，零售均价<span id="retioMoneyAll">0</span>
                    </div>
                </div>
                <!-- 操作区域 end-->
            </div>
        </div>
    </div>
</div>
<!--内容部分end-->
</body>

</html>
<script src="<c:url value='/javaScript/dentistmall/storage/viewInventory.js?v=57'/>"></script>