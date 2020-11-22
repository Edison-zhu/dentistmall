<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
    <title>My JSP 'sysFeedback.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?2' />">
    <script src="<c:url value='/js/uitl/echarts.js'/>"></script>
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">
</head>
<script src="<c:url value='/javaScript/dentistmall/modifyprice/addWarehousing.js?v=436'/>"></script>
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
      rel="stylesheet">
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<%--//引入时间插件--%>
<script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<style>
    .mmGrid .mmg-bodyWrapper .mmg-body {
        width: auto !important;
    }

    .mmg-bodyWrapper {
        width: 0 p !important;
    }

    /*定义滚动条宽高及背景，宽高分别对应横竖滚动条的尺寸*/
    *::-webkit-scrollbar {
        width: 10px;
        height: 10px;
        background-color: rgba(255, 255, 255, 0);
    }

    /*定义滚动条的轨道，内阴影及圆角*/
    *::-webkit-scrollbar-track {
        border-radius: 10px;
        background-color: rgba(230, 230, 230, 0.05);
    }

    *:hover::-webkit-scrollbar-track {
        background-color: rgba(230, 230, 230, 0.5);
    }

    /*定义滑块，内阴影及圆角*/

    *::-webkit-scrollbar-thumb {
        height: 20px;
        border-radius: 10px;
        -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0);
        background-color: rgba(216, 216, 216, 0.4);
        transition: background-color 1s;
    }

    *:hover::-webkit-scrollbar-thumb {
        background-color: rgba(216, 216, 216, 1);
    }

    *::-webkit-scrollbar-thumb:hover {
        background-color: rgba(190, 190, 190, 1);
    }

    *::-webkit-scrollbar-thumb:active {
        background-color: rgba(160, 160, 160, 1);
    }

    /*新样式*/
    .newtitbox {
        margin: 30px 30px 15px 30px;
        border-top: 4px solid #e7eaec;
        padding: 14px 15px 7px;
        min-height: 48px;
        background: #FCFCFC;
        font-weight: bold;
        font-size: 16px;
    }

    .newtitbox span {
        font-size: 14px;
        font-weight: normal;
    }

    .newtopbox {
        margin: 0 30px 30px 30px;
        background: #FCFCFC;
        padding: 14px 15px;
        display: flex;
        justify-content: flex-start;
        align-items: center;
    }

    .newinput {
        width: 201px;
        height: 28px;
        border: 1px solid #e7eaec;
        padding-left: 5px;
    }

    .nmlf20 {
        margin-left: 14px;
    }

    .newtopbox .text {
        margin-left: 5px;
        font-size: 14px;
        font-weight: normal;
        display: inline;
    }

    .newtopbox .btn1 {
        width: 100px;
        height: 27px;
        background: #1ab394;
        border-radius: 15px;
        border: 1px solid #1ab394;
        text-align: center;
        line-height: 27px;
        color: #fff;
        margin-left: 57px;
    }

    .newtopbox .btn2 {
        width: 100px;
        height: 27px;
        background: #fff;
        border-radius: 15px;
        border: 1px solid #1ab394;
        text-align: center;
        line-height: 27px;
        color: #333;
        margin-left: 2px;
    }

    .newtopbox form {
        display: flex;
        justify-content: flex-start;
        align-items: center;
    }

    .btn3 {
        width: 100px;
        height: 27px;
        background: #1ab394;
        border-radius: 15px;
        border: 1px solid #1ab394;
        text-align: center;
        line-height: 27px;
        color: #fff;
        margin-left: 80px;
    }

    .newbtn1 {
        width: 120px;
        height: 30px;
        background: #1ab394;
        border-radius: 15px;
        border: 1px solid #1ab394;
        text-align: center;
        color: #fff;
        margin-left: 30px;
        display: inline;
    }

    .selectbtn1 {
        width: 60px;
        height: 22px;
        background: #1ab394;
        border-radius: 11px;
        border: 1px solid #1ab394;
        text-align: center;
        line-height: 22px;
        color: #fff;
        font-size: 12px;
    }

    #tables {
        display: none;
        z-index: 999;
        position: fixed;
        top: 248px;
        width: 800px;
        margin-left: 192px;
        background-color: #FFFFFF;
        border-radius: 0px;
        background-clip: padding-box;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        font-size: 14px;
    }

    #tables2 {
        display: none;
        z-index: 999;
        position: fixed;
        top: 194px;
        width: 1100px;
        margin-left: 192px;
        background-color: #FFFFFF;
        border-radius: 0px;
        background-clip: padding-box;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        font-size: 14px;
    }

    @media screen and (max-width: 1400px) {
        .zsywidth {
            /*width: 1200px;*/
            width: auto;
        }

        #tables {
            top: 50px;
            height: 600px;
            left: -105px;
        }

        .minibr {
            display: block;
        }

        .miniwidth {
            width: 115px;
        }
    }

    @media screen and (min-width: 1401px) and (max-width: 1660px) {
        .zsywidth {
            /*width: 1430px;*/
            width: auto;
        }

        .minibr {
            display: none;
        }

        .miniwidth {
            width: 180px;
        }
    }

    @media screen and (min-width: 1661px) {
        .zsywidth {
            /*width: 1540px;*/
            width: auto;
        }

        #tables {
            top: 100px;
            height: 600px
        }

        .minibr {
            display: none;
        }

        .miniwidth {
            width: 180px;
        }
    }

</style>

<body>
<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<%-- 新的 s --%>
<div class="newtitbox">新增入库</div>
<div class="newtopbox">
    <form id="saveForm">
        <div>入库单号 <input class="newinput" type="text" id="warehousCode" value="" name="billcode" placeholder=""></div>
        <div class="nmlf20">入库类型：
            <span><input type="radio" id="orderWarehous" onclick="radio1()" value="1"><h1 class="text">订单入库</h1></span>
            <span style="margin-left: 8px"><input type="radio" id="orderWarehous2" onclick="radio2()" value="2"
                                                  class="input2"><h1 class="text">商品物料入库</h1></span>
        </div>
        <div class="nmlf20" style="margin-left: 23px">入库备注： <input class="newinput" type="text" id="remarks"
                                                                   name="warehousingRemarks" value=""
                                                                   placeholder="50字内"></div>
        <div class="nmlf20">发票号： <input class="newinput" type="text" id="billCode" name="invoiceCode" value=""
                                        placeholder="20字内"></div>
    </form>
    <div>
        <button type="button" onclick="save()" class="btn1">保存</button>
        <button type="button" onclick="goBack()" class="btn2">返回</button>
    </div>
</div>
<%-- 新的 e --%>

<div id="containerWhole" style="overflow:auto;" class="zsywidth">
    <div style="height: 750px;margin: 0 30px;">
        <div class="container33" id="mx1"
             style="width: 100%;min-height: 600px;background-color: white;margin-top: 30px;display: none;line-height:25px">
            <table class="table invoice-table" style="width: 100%;">
                <thead style="border: 1.5px solid #F0F0F0;">
                <%--                        str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type=\"checkbox\" onclick='a1(" + mxList[i].momId + ")'></td>";
        --%>
                <tr>
                    <%--                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><input type="checkbox"></th>--%>
                    <%--订单入库--%>
                    <%--<th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">商品编号</th>--%>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">商品名称</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">品牌</th>
                    <%--<th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">规格/型号编码</th>--%>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">规格/型号</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">包装方式</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">单位</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">订单数量</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">发货数量</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">物料编号</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">物料名称</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">规格型号</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">单位</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">当前库存</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">入库数量</th>
                    <%--<th style="width:85px;text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">拆分总数量</th>--%>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购价（元）</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购金额（元）</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">批号</th>
                    <th style="width:85px;text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">有效期</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">注册证号/备案号</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">备注</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作</th>
                </tr>
                </thead>
                <tbody id="mxListMx">
                </tbody>
            </table>
        </div>
        <div class="container33" id="mx2"
             style="width: 100%;min-height: 600px;background-color: white;margin-top: 30px;display: none">
            <table class="table invoice-table" style="width: 100%;">
                <thead style="border: 1.5px solid #F0F0F0;">
                <tr>
                    <%--物料入库--%>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">物料编号</th>
                    <%--                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">更多搜索</th>--%>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">物料名称</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">规格型号</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">单位</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">当前库存</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">入库数量</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购价</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购金额（元）</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">批号</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">有效期</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">注册证号/备案号</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">备注</th>
                    <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作</th>
                </tr>
                </thead>
                <tbody id="mxMatListMx">
                </tbody>
            </table>
            <div>
                <input type="text" placeholder="物料号/物料名称/规格号/规格名称" id="selectMats" onblur="hideClass()"
                       onfocus="selectMatBeforesToo(this.value)"
                       onkeyup="selectMatBeforestodo(this.value)"
                <%--                       onchange="selectMatBeforesToo(this.value)"--%>
                       style="width: 218px;line-height: 23px;"/>
                <img onclick="selectMatBeforestodo(1)" src="../../../img/xiaohua.png" alt=""
                     style="width: 39px;height: 26px"/>
                <style>
                    @media screen and (max-width: 1440px) {
                        #tables {
                            top: 50px;
                            height: 300px;
                            left: -105px;
                        }
                    }

                    @media screen and (min-width: 1441px) {
                        #tables {
                            top: 200px;
                            height: 300px;
                            left: -163px;
                        }
                    }
                </style>
                <div id="tables">
                    <div>
                        <div style="margin-top: 10px;margin-bottom: 10px;">
                        </div>
                        <button type="button" style="margin-left: 776px;width: 19px" onclick="houseCancel()">X</button>
                        <table id="datagrid2" class="mmg" style="width: 986px !important;"></table>
                    </div>
                </div>

                <div id="tables2">
                    <div>
                        <div class="col-sm-2">
                            <div class="ibox float-e-margins">
                                <!-- 标题栏区域 begin-->
                                <div class="ibox-title" style="width: 183px">
                                    <%--                <!-- 标题文字区域begin -->--%>
                                    <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp分类信息</h5>
                                    <!-- 标题文字区域end -->
                                    <%--                <!-- 标题栏工具区域begin -->--%>
                                    <div class="ibox-tools">
                                    </div>
                                </div>
                                <!-- 标题栏区域 end-->
                                <!-- 操作区域 begin-->
                                <div class="ibox-content" style="width: 204px">
                                    <div class="scrollbarlist" style="width: 150px">
                                        <div class="panel-body" style="padding: 0px">
                                            <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                                        </div>
                                    </div>
                                </div>
                                <!-- 操作区域 end-->
                            </div>
                        </div>
                        <div style="margin-top: 10px;margin-bottom: 10px;">
                            <div>
                                <form id="queryForm" style="display: inline;margin-left: 40px">
                                    <div id="searchForms" class="form-inline" style="display: inline">
                                        <label>关键字查询</label>
                                        <input type="text" id="searchNames" onfocus="addClass()" onblur="removeClass()"
                                               onkeyup="searchs(this, true)" class="form-control"
                                               placeholder="物料号/物料名称/规格号/规格名称"
                                               name="searchNames"  style="width: 300px"/>
                                    </div>
                                </form>
                                <button id="btnSearch" class="newbtn1" onclick="searchs(true)">查询</button>
                                <button type="button" style="margin-left: 776px;width: 19px;margin-top: -27px"
                                        onclick="houseCancels()">X
                                </button>
                            </div>
                        </div>

                        <table id="datagrid3" class="mmgs" style="width: 986px !important;"></table>
                        <%--                        <div id="pg" style="text-align: right;height: 23px"></div>--%>
                        <%--                        <button id="" class="newbtn1" onclick="queding()">确定</button>--%>
                        <%--                        <button id="" class="newbtn1" onclick="houseCancel()">取消</button>--%>
                    </div>
                </div>


            </div>
            <div class="pagination" id="Pagination1" style="margin-left: 650px">
            </div>

            <div style="width: 2200px;height: 1px;display: none"></div>
        </div>

        <%--z-index--%>
        <div class="hideDiv" id="hideDiv"
             style="width: auto;max-width: 1600px;height: 540px;border: 1px solid #F0F0F0;display: none;z-index: 9999;">
            <div class="hideDiv 1" style="width: 100%;height: 50px;background:rgba(245,245,245,0.6); ">
                <div class="hideDiv 1 dv" style="margin-left: 30px;line-height: 3"><span
                        style="font-size: 15px">订单入库选择</span></div>
            </div>
            <div class="hideDiv 2"
                 style="width: 100%;height: 50px;margin-top:10px;background:rgba(245,245,245,0.6);">
                <div class="hideDiv 1 dv" style="line-height: 3">
                        <span style="font-size: 12px;margin-left: 15px;">关键字：<input type="text" id="gjz"
                                                                                    style="width:200px;height: 28px"
                                                                                    value=""
                                                                                    placeholder="订单编号/商品名称/规格/物流单号"
                                                                                    onkeydown="entersearch()"
                                                                                    class="newinput"></span>
                    <span style="font-size: 12px;margin-left: 30px">采购日期：<input type="text" id="cgDate"
                                                                                style="width:150px;height: 28px"
                                                                                value="" placeholder=""
                                                                                onkeydown="entersearch()"
                                                                                class="newinput"></span>
                    <span style="font-size: 12px;margin-left: 30px">采购人：<input type="text" id="cgRen"
                                                                               style="width:150px;height: 28px"
                                                                               value="" placeholder=""
                                                                               onkeydown="entersearch()"
                                                                               class="newinput"></span>
                    <span><button type="button" onclick="main_add(0)" class="btn3">查询</button></span>
                    <script>
                        function entersearch() {
                            var event = window.event || arguments.callee.caller.arguments[0];
                            if (event.keyCode == 13) {
                                main_add(0);
                            }
                        }
                    </script>
                    <div class="hideDiv 3"
                         style="width: 100%;height: 300px;background-color: white;margin-top: 18px">
                        <table class="table invoice-table">
                            <thead style="border: 1.5px solid #F0F0F0;">
                            <tr>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">
                                </th>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">订单编号
                                </th>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购时间
                                </th>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购金额
                                </th>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购数量
                                </th>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">
                                    发货/入库数量
                                </th>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">供应商
                                </th>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购人
                                </th>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">订单状态
                                </th>
                                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作
                                </th>
                            </tr>
                            </thead>
                            <tbody id="mxList1">
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="pagination" id="Pagination2" style="margin-left: 400px">
                </div>
                <div style="border-bottom: 1px solid #F0F0F0;"></div>
                <div style="margin-top: 5px">
                    <div style="float: right">
                        <button id="btn2" type="button" onclick="queD()" class="btn btn-primary btn-xs"
                                style="margin-left: 30px;margin-right:20px;width: 95px;border-radius: 20px;">确定
                        </button>
                    </div>
                    <div style="float: right">
                        <button type="button" onclick="quXiao()" class="btn btn-default btn-xs"
                                style="width: 95px;border-radius: 20px;">取消
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--    <div id="gridDiv" style="display: none">--%>
    <%--        <table id="datagrid1" class="mmg"></table>--%>
    <%--    </div>--%>
</div>
</body>
</html>
<style type="text/css">

</style>
<script>
    $("#tables").keydown(function () {
        if (event.keyCode == "13") {//keyCode=13是回车键
            if (_currentList.length == 1) {
                $('#selectMatRows').click();//换成按钮的id即可
            } else {
                selectMatRow(saveWmsMiIds, saveMmfIds)
                console.log(222)
            }

        }
    });
    $(document).bind('click', function(e) {
        var e = e || window.event; //浏览器兼容性
        var elem = e.target || e.srcElement;
        while (elem) { //循环判断至跟节点，防止点击的是div子元素
            if (elem.id && elem.id == 'tables') {
                return;
            }
            if (elem.id && elem.id == 'tables') {
                $("#tables").css("display","block");
                return;
            }
            elem = elem.parentNode;
        }
        $('#tables').css('display', 'none'); //点击的不是div或其子元素
    });
    $(document).bind('click', function(e) {
        var e = e || window.event; //浏览器兼容性
        var elem = e.target || e.srcElement;
        while (elem) { //循环判断至跟节点，防止点击的是div子元素
            if (elem.id && elem.id == 'selectbtn1') {
                return;
            }
            if (elem.id && elem.id == 'selectbtn1') {
                $("#tables2").css("display","block");
                return;
            }
            elem = elem.parentNode;
        }
        $('#tables2').css('display', 'none'); //点击的不是div或其子元素
    });
</script>