<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>牙医商城平台</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?13' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <link rel="stylesheet" href="<c:url value='/jsp/util/orderlistutil.css?v=3' />">
    <link rel="stylesheet" href="<c:url value='/js/formSelects-v4.css?v=1'/>">
    <script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
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
</head>
<style>
    .collectText {
        float: left;
        position: relative;
        left: 1%;
        font-size: 14px;
    }

    .collectText span {
        color: black;
        font-weight: bold;
    }

    /*#statetable td {*/
    /*    padding: 7px 10px 1px 10px;*/
    /*    border-right: #c8c8c8 solid 1px;*/
    /*}*/

    /*#statetable td a {*/
    /*    display: block;*/
    /*}*/
    /*#statetable td a:active{*/
    /*    outline: lightgrey 1px solid;*/
    /*}*/
    #statetable li {
        /*padding: 7px 10px 1px 10px;*/
        /*border-right: #c8c8c8 solid 1px;*/
        float: left;
        display: block;
    }

    #statetable li a {
        display: block;
    }

    #statetable .taps-item {
        font-size: 18px;
        color: #27619D;
        font-weight: bold;
        cursor: pointer;
    }
    #statetable .taps-item-active {
        font-size: 18px;
        color: #0bc176;
        font-weight: bold;
        cursor: pointer;
    }
    #statetable .taps-item-active:nth-child(1):after {
        content: "|";
        display: inline-block;
        color: #ddd;
        margin: 0 10px
    }
    #statetable .taps-item:nth-child(1):after {
        content: "|";
        display: inline-block;
        color: #ddd;
        margin: 0 10px
    }
    #statetable .taps-item:nth-child(2):after {
        content: "|";
        display: inline-block;
        color: #ddd;
        margin: 0 10px
    }
    #statetable .taps-item-active:nth-child(2):after {
        content: "|";
        display: inline-block;
        color: #ddd;
        margin: 0 10px
    }
    .orderinfo {
        position: relative;
        top: -1px;
        border-top: 4px solid #0bc176;
        /*box-shadow: inset 0 0 5px gray;*/
        margin-bottom: 20px;
        padding-bottom: 20px;
    }

    .orderinfo .hd {
        /*background: url("../../../css/shopping/images/nav-background.png") no-repeat top left;*/
        width: 100%;
        margin: 0 auto;
        height: 30px;
        line-height: 30px;
        /*display: flex;*/
    }

    .orderinfo table .td-text-align {
        text-align: center;
    }

    .orderinfo .hd-left {
        /*flex: 1;*/
    }

    .orderinfo .hd-left2 {
        width: 480px
    }

    .orderinfo .hd-left input {
        position: relative;
        top: 2px;
        margin: 0 10px 0 32px;
    }

    .orderinfo .hd-left label {
        /*color: white;*/
        font-weight: bold;
    }

    .orderinfo .hd-left2 label {
        /*color: white;*/
        font-weight: bold;
    }

    .orderinfo .hd-middle {
        /*flex: 0.3;*/
    }

    .orderinfo .hd .hd-middle label {
        /*color: white;*/
        font-width: bold;
        /*padding-left: 10px;*/
    }

    .orderinfo .hd-right {
        /*flex: 1;*/
    }

    .orderinfo .hd-right label {
        display: inline-block;
        /*color: white;*/
        font-weight: bold;
        /*width: 75px;*/
        /*margin-left: 49px;*/
    }

    .orderinfo .hd-right2 label {
        display: inline-block;
        /*color: white;*/
        font-weight: bold;
        width: 75px;
        margin-left: 10px;
        text-align: center
    }

    #divSearch {
        /* margin-left:950px; */
    }

    #open {
        float: right;
    }

    #divSearch3 {
        margin-left: 20px;
        width: 95px;
        height: 25px;
    }

    #divSearch4 {
        width: 95px;
        height: 25px;
    }

    #divSearch1 {
        margin-left: 650px;
        width: 95px;
        height: 25px;
    }

    #divSearch2 {
        width: 95px;
        height: 25px;
    }
    #open{
        cursor: pointer;
        margin-top: -20px;
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
</style>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5>
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>订单信息</span>
        </h5>
        <!-- 标题文字区域end -->
        <div class="ibox-tools" id="win_tools_but">
        </div>
    </div>
    <!-- 标题栏区域 end-->

    <!-- 查询区域begin -->
    <div class="ibox-content" style="padding: 5px 10px 10px;">

        <form class="form-horizontal" id="win_form_search">
            <div id="win_form_hidden"></div>
        </form>
        <form class="form-horizontal" id="win_form_search_buyer" style="display:none">
        </form>
        <div id="divSearch" class="divFloat" style="display:none"><!--btn btn-default btn-rounded btn-sm fa fa-refresh-->
            <button id="divSearch3" class="btn btn-success btn-xs " onclick="export_all2()">导出订单</button>
            <button id="divSearch4" class="btn btn-primary btn-xs" onclick="export_orderList()">导出对账单</button>
            <button id="divSearch1" class="btn btn-success btn-xs" onclick="main_search()">搜索</button>
            <button id="divSearch2" class="btn btn-xs" onclick="resetSearch()">重置</button>
        </div>
        <div id="open" class="divFloat">展开
        </div>
    </div>
    <!-- 查询区域end -->
    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <c:if test="${sessionScope.sessionUser.organizaType == '100' || sessionScope.sessionUser.organizaType == '0' }">
            <ul id="statetable">
<%--                <tr>--%>
                    <!-- 除了第一个方法不用传参外，剩余的都需要传参，待定参数 -->
                    <li><a id="_item1" class="clickA taps-item-active" onclick="main_search_bystate(0)">全部(<span id="allCount">0</span>)</a></li>
                    <li><a id="_item2" class="clickA taps-item" onclick="main_search_bystate(2)">待付款(<span id="dfkCount">0</span>)</a></li>
                    <li><a id="_item3" class="clickA taps-item" onclick="main_search_bystate(1)">待发货(<span id="dfhCount">0</span>)</a></li>
                    <li><a id="_item4" class="clickA taps-item" onclick="main_search_bystate(4)">部分发货(<span id="bffhCount">0</span>)</a></li>
                    <li><a id="_item5" class="clickA taps-item" onclick="main_search_bystate(3)">待收货(<span id="dshCount">0</span>)</a></li>
                    <li><a id="_item6" class="clickA taps-item" onclick="main_search_bystate(7)">售后(<span id="sh">0</span>)</a></li>
                    <li><a id="_item7" class="clickA taps-item" onclick="main_search_bystate(5)">交易成功(<span id="jycgCount">0</span>)</a></li>
                    <li><a id="_item8" class="clickA taps-item" onclick="main_search_bystate(6)">交易关闭(<span id="jysbCount">0</span>)</a></li>
<%--                </tr>--%>
            </ul>
            <div style="float: right">
                <a id="refreshBtn" class="btn btn-default btn-rounded btn-sm fa fa-refresh" style="width: 95px;margin-right:4px;vertical-align: center;text-align: center" onclick="main_refresh()">刷新</a>
            </div>
            <div style="clear:both;"></div>
        </c:if>

    </div>
    <!-- 表格区域begin -->
    <c:if test="${sessionScope.sessionUser.organizaType == '100' || sessionScope.sessionUser.organizaType == '0' }">
        <div class="export">
     	<span>
     		<input type="checkbox" class="loginName" onclick="exportItem1(this,-1)" id="all">
     	</span>
            <span>
     		全选
     	</span>
            <span>
     	<a onclick="export_batch_order()" class='btn btn-success  btn-xs' style="color:#fff;margin:10px" id="export1">批量导出订单信息</a>
     	<a onclick="export_batch_order1()" class='btn btn-success  btn-xs' style="color:#fff" id="export1">批量导出对账单信息</a>
     	</span>
        </div>
    </c:if>


    <c:if test="${sessionScope.sessionUser.organizaType == '20001' || sessionScope.sessionUser.organizaType == '20002' || sessionScope.sessionUser.organizaType == '20003'}">
        <div class="ibox-content" style="padding: 5px 10px 10px;">
            <table id="win_datagrid_main" class="mmg"></table>
            <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
        </div>
    </c:if>
    <c:if test="${sessionScope.sessionUser.organizaType == '100' || sessionScope.sessionUser.organizaType == '0' }">
        <div class="orderinfo">
            <div class="ibox-content" style="padding: 5px 10px 10px;">
                <div class="hd">
                    <table style="width: 100%;border-bottom: lightgrey solid 1px">
                        <tr>
                            <th style="width: 40%">
                                <div style="display: inline;width: 60%;">
                                    <div class="hd-left">
                                        <label class="l32">货品</label>
                                    </div>
                                </div>
                            </th>
                            <th class="td-text-align" style="width: 7%">
                                <div>
                                <span class="hd-middle">
                                    <label>单价（元）</label>
                                </span>
                                </div>
                            </th>
                            <th class="td-text-align" style="width: 7%">
                                <div>
                                 <span class="hd-middle">
                                <label>数量</label>
                                 </span>
                                </div>
                            </th>
                            <th class="td-text-align" style="width: 7%">
                                <div>
                                <span class="hd-middle">
                                    <label>商品状态</label>
                                </span>
                                </div>
                            </th>
                            <th class="td-text-align" style="width: 8%">
                                <div>
                                <span class="hd-middle">
                                    <label>售后状态</label>
                                </span>
                                </div>
                            </th>
                            <th class="td-text-align" style="width: 7%">
                                <div style="display: inline;width: 60%;">
                                    <div class="hd-right" style="display: inline;">
                                            <%--                    <label>单位</label>--%>
                                        <label>采购人</label>
                                    </div>
                                </div>
                            </th>

                            <th class="td-text-align" style="width: 7%">
                                <div style="display: inline;width: 60%;">
                                    <div class="hd-right" style="display: inline;">
                                            <%--                    <label>单位</label>--%>
                                        <label>实付款</label>
                                    </div>
                                </div>
                            </th>
                            <th class="td-text-align" style="width: 7%">
                                <div style="display: inline;width: 60%;">
                                    <div class="hd-right" style="display: inline;">
                                        <label>交易状态</label>
                                    </div>
                                </div>
                            </th>
                            <th class="td-text-align">
                                <div style="display: inline;width: 60%;">
                                    <div class="hd-right" style="display: inline;">
                                        <label>操作</label>
                                    </div>
                                </div>
                            </th>
                        </tr>
                    </table>
                </div>
                <div id="divList">
                    <%@include file="/jsp/util/orderlistutil.jsp" %>
                </div>
                <div class="pagination w1080 m-pagenum pagination" style="float: right" id="Pagination">
                </div>
            </div>
        </div>
    </c:if>


    <!-- 表格区域end -->


    <div>
        <!-- <div class="collectText">
            合计订单<span id="allCount">0</span>条,
            待发货<span id="waitCount">0</span>条,
            待收货<span id="waitFinCount">0</span>条,
            部分发货<span id="partCount">0</span>条,
            已完成<span id="finishCount">0</span>条,
            退货<span id="backCount">0</span>条,
            取消<span id="cancelCount">0</span>条
        </div> -->


    </div>
</div>
<div class="u-mask" id="addressId"></div>
<div id="divFilter" class="divFilter">
    <div style="border-bottom: lightgrey solid 1px;">
        <div style="float: left;padding: 8px;padding-left: 31px;font-size: 20px;font-weight: bold;">修改订单金额以及运费</div>
        <div style="float: right;margin-top: 5px;">
            <a class="btn btn-white" onclick="cancelChange()">X</a>
        </div>
        <div style="clear: both"></div>
    </div>
    <form id="filterForm" class="form-horizontal form-bordered" style="margin-top: 30px;margin-bottom: 20px" role="form"
          style="padding-left: 10px">
        <div id="win_form_edithidden"></div>
        <div class="ibox float-e-margins">
            <div class="form-group">
                <label class="col-sm-2 control-label">金额(不含运费)：</label>
                <div class="col-sm-8">
                    <input disabled class="form-control" placeholder="请输入实际金额" name="placeOrderMoney" id="placeOrderMoney"
                           value=""/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">运费：</label>
                <div class="col-sm-8">
                    <input class="form-control" placeholder="请输入实际运费" name="exoressMoney" id="exoressMoney"
                           value=""/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">优惠金额：</label>
                <div class="col-sm-8">
                    <input class="form-control" placeholder="请输入优惠金额" name="saleMoney" id="saleMoney"
                           value=""/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">实收金额：</label>
                <div class="col-sm-8" style="margin-top: 8px;">
                    <label id="allMoney" style="color: red">0.00</label>
                    <%--                    <input class="form-control" placeholder="请输入实际运费" name="allMoney" id="allMoney"--%>
<%--                           value=""/>--%>
                </div>
            </div>
        </div>
    </form>
    <div class="form-group" style="clear:both;">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="layui-btn layui-btn-blue" onclick="changeAllMoey()">修改</button>
            <button class="layui-btn layui-btn-primary" onclick="cancelChange()">取消</button>
        </div>
    </div>
</div>
<!-- 主信息区域end -->
<input type="hidden" id="organizaType" value="${sessionScope.sessionUser.organizaType}">
<input type="hidden" id="userRole" value="${sessionScope.sessionUser.userRole}">
</body>
</html>

<script src="<c:url value='/javaScript/dentistmall/transaction/orderInfoList.js?v=102'/>"></script>

