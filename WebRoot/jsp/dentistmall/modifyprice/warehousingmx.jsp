<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%--    <base href="<%=basePath%>">--%>

    <title>My JSP 'sysFeedback.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?2' />">
    <script src="<c:url value='/js/uitl/echarts.js'/>"></script>
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">
</head>
<script src="<c:url value='/javaScript/dentistmall/modifyprice/warehousingmx.js?v=119'/>"></script>
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

    .close {
        position: relative;
        width: 0.3em;
        height: 1.5em;
        background: #333;
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
        display: inline-block;
    }

    .close:after {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width: 0.3em;
        height: 1.5em;
        background: #333;
        -webkit-transform: rotate(270deg);
        -moz-transform: rotate(270deg);
        -o-transform: rotate(270deg);
        -ms-transform: rotate(270deg);
        transform: rotate(270deg);
    }

    /*新样式*/
    .newtitbox {
        margin: 30px 30px 15px 30px;
        border-top: 4px solid #e7eaec;
        padding: 14px 15px 7px;
        min-height: 48px;
        background: #FCFCFC;
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-weight: bold;
        font-size: 16px;
    }

    .btn4 {
        width: 130px;
        height: 27px;
        background: #1ab394;
        border-radius: 15px;
        border: 1px solid #1ab394;
        text-align: center;
        line-height: 27px;
        color: #fff;
        font-size: 14px;
        font-weight: normal;
    }

    .btn5 {
        width: 130px;
        height: 27px;
        background: #fff;
        border-radius: 15px;
        border: 1px solid #1ab394;
        text-align: center;
        line-height: 27px;
        color: #1ab394;
        margin-left: 30px;
        font-size: 14px;
        font-weight: normal;
    }

    .iptb {
        border: 1px solid #e7eaec;
        padding-left: 5px;
    }

    .iptb2 {
        border: 1px solid #DCDCDC;
        padding-left: 5px;
    }
</style>
<body>
<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<div class="newtitbox">
    <div>入库管理<span>【详情/编辑】</span></div>
    <div>
        <button type="button" onclick="save()" class="btn4" id="savebtnhaha">保存</button>
        <button type="button" onclick="backTo()" class="btn5">返回</button>
        <button type="button" onclick="exportExcel()" class="btn5">导出excel</button>
    </div>
</div>


<div id="containerWhole" style="margin: 0 30px">

    <%--    <div class="container33"--%>
    <%--         style="width: 100%;height: 45px;background-color: #FCFCFC;border-bottom:#F0F0F0 1.8px solid; ">--%>
    <%--        <div style="line-height: 3"><span style="font-size: 18px;margin-left: 20px">入库管理&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;【详情/编辑】</span>--%>
    <%--            <span style="margin-left: 50%;"><button type="button" onclick="save()" class="btn btn-success btn-xs"--%>
    <%--                                                    style="margin-left: 30px;width: 95px;vertical-align:middle;border-radius: 20px;"><span--%>
    <%--                    style="margin-bottom: 10px">保存</span></button></span>--%>
    <%--            <span><button type="button" onclick="backTo()" class="btn btn-default btn-xs"--%>
    <%--                          style="margin-left: 30px;width: 95px;border-radius: 20px;">返回</button></span>--%>
    <%--            <span><button type="button" onclick="exportExcel()" class="btn btn-primary btn-xs"--%>
    <%--                          style="margin-left: 30px;width: 95px;border-radius: 20px;">导出excel</button></span>--%>
    <%--        </div>--%>
    <%--    </div>--%>
    <div class="container33"
         style="width: 100%;background-color: #F0F0F0; margin-top: 10px;display: flex;justify-content: space-around;align-items: center">
        <form id="saveForm"
              style="width: 100%;line-height:60px;display: flex;justify-content: space-around;align-items: center">
            <div>
            <span style="font-size: 12px;">入库单号：<input type="text" id="billCode" name="billcode"
                                                       style="width:150px;height: 28px" value=""
                                                       placeholder="" class="iptb"></span>
                <span style="font-size: 12px;margin-left: 20px">入库类型：<span><input type="radio" disabled
                                                                                  id="orderWarehous"
                                                                                  name="billType" onclick="radio1()"
                                                                                  value="2">订单入库</span><span
                        style="margin-left: 8px"><input disabled type="radio" id="orderWarehous2" name="billType"
                                                        onclick="radio2()" value="1">商品物料入库</span></span></div>
            <span style="font-size: 12px;">入库备注：<input type="text" id="warehousingRemarks"
                                                       style="width:250px;height: 28px"
                                                       name="warehousingRemarks" value=""
                                                       placeholder="50字内" class="iptb"></span>
            <span style="font-size: 12px;">发票号：<input type="text" id="invoiceCode" name="invoiceCode"
                                                      style="width:150px;height: 28px" value=""
                                                      placeholder="" class="iptb"></span>
            <div><span style="font-size: 12px;">制作人：<span id="createRen"></span></span>
                <span style="font-size: 12px;margin-left: 20px">制作时间：<span id="createDate"></span></span></div>
        </form>
    </div>
    <div class="container33" id="mx1"
         style="width: 100%;min-height: 600px;background-color: white;margin-top: 30px;display: none">
        <table class="table invoice-table" style="width: 100%;">
            <thead style="border: 1.5px solid #F0F0F0;">
            <%--                        str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type=\"checkbox\" onclick='a1(" + mxList[i].momId + ")'></td>";
            --%>
            <tr>
                <%--                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><input type="checkbox"></th>--%>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">商品编号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">商品名称</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">品牌</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">规格/型号编码</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">单位</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">订单数量/发货数</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">入库数量</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">拆分总数量</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">包装方式</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">零售价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">批号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">有效期</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">注册证号/备案号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">选择物料</th>
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
            <%--                        str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type=\"checkbox\" onclick='a1(" + mxList[i].momId + ")'></td>";
            --%>
            <tr>
                <%--                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><input type="checkbox"></th>--%>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">物料编号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">物料名称</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">品牌</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">规格/型号编码</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">数量</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">系数</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">拆分最小单位</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">包装方式</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">零售价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">批号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">有效期</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">注册证号/备案号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作</th>
            </tr>
            </thead>
            <tbody id="mxMatListMx">
            </tbody>
        </table>
        <div>
            <input id="selectMat" onclick="selectMat()"/>
        </div>
    </div>
    <div class="pagination" id="Pagination1" style="margin-left: 650px">
    </div>
    <%--z-index--%>
    <div class="hideDiv" id="hideDiv"
         style="width: 900px;height: 540px;position:absolute;top: 200px;left: 300px;border: 1px solid #F0F0F0;display: none;z-index: 9999;">
        <div class="hideDiv 1" style="width: 100%;height: 50px;background:rgba(245,245,245,0.6); ">
            <div class="hideDiv 1 dv" style="margin-left: 30px;line-height: 3"><span
                    style="font-size: 15px">订单入库选择</span></div>
        </div>
        <div class="hideDiv 2" style="width: 100%;height: 50px;margin-top:10px;background:rgba(245,245,245,0.6);">
            <div class="hideDiv 1 dv" style="line-height: 3">
                    <span style="font-size: 12px;margin-left: 15px;">关键字：<input type="text" id="gjz"
                                                                                style="width:200px;height: 28px"
                                                                                value=""
                                                                                placeholder="订单编号/商品名称/规格/物流单号"></span>
                <span style="font-size: 12px;margin-left: 30px">采购日期：<input type="text" id="cgDate"
                                                                            style="width:150px;height: 28px"
                                                                            value="" placeholder=""></span>
                <span style="font-size: 12px;margin-left: 30px">采购人：<input type="text" id="cgRen"
                                                                           style="width:150px;height: 28px" value=""
                                                                           placeholder=""></span>
                <span><button type="button" onclick="main_add(0)" class="btn btn-default btn-xs"
                              style="margin-left: 30px;width: 95px;">查询</button></span>
                <div class="hideDiv 3" style="width: 100%;height: 300px;background-color: white;margin-top: 18px">
                    <table class="table invoice-table">
                        <thead style="border: 1.5px solid #F0F0F0;">
                        <tr>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><input
                                    type="checkbox"></th>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">订单编号</th>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购时间</th>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购金额</th>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购数量</th>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">发货/入库数量</th>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">供应商</th>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购人</th>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">订单状态</th>
                            <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作</th>
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
    <div style="width: 2200px;height: 1px;display: none"></div>
</div>
</div>

<div id="gridDiv">
    <table id="datagrid1" class="mmg"></table>
</div>

</div>

</body>
</html>
<style type="text/css">
    button {
        border-radius: 8px;
    }
</style>