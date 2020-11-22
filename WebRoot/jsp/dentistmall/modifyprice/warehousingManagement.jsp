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
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
</head>
<script src="<c:url value='/javaScript/dentistmall/modifyprice/warehousingManagement.js?v=103'/>"></script>
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css"
      rel="stylesheet">
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
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

    .newtopbox {
        margin: 0 30px 30px 30px;
        background: #FCFCFC;
        padding: 14px 15px;
        display: flex;
        justify-content: flex-start;
        align-items: center;
    }

    .newinput {
        /* width:230px; */
        height: 28px;
        border: 1px solid #e7eaec;
        padding-left: 5px;
    }

    .newinput2 {
        width: 80px;
        height: 28px;
        border: 1px solid #e7eaec;
        padding-left: 5px;
    }

    .nmlf20 {
        margin-left: 20px;
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
        margin-left: 80px;
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
        margin-left: 20px;
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

    @media screen and (max-width: 1440px) {
        .zsywidth {
            width: 1200px;
        }

        .zsywidth_tp {
            width: 1140px;
        }

        .newinput {
            width: 200px;
        }

        .m_lf {
            margin-left: 350px
        }
    }

    @media screen and (min-width: 1441px) and (max-width: 1600px) {
        .zsywidth {
            width: 1460px;
        }

        .zsywidth_tp {
            width: 1400px;
        }

        .newinput {
            width: 230px;
        }

        .m_lf {
            margin-left: 650px
        }
    }

    @media screen and (min-width: 1601px) {
        .zsywidth {
            width: 1540px;
        }

        .zsywidth_tp {
            width: 1523px;
        }

        .newinput {
            width: 230px;
        }

        .m_lf {
            margin-left: 650px
        }
    }

</style>
<body>

<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<%-- 新的 s --%>
<div class="newtitbox zsywidth_tp">
    <div>入库管理</div>
    <div>
        <button type="button" onclick="addNewEnter()" class="btn4">新增入库</button>
        <button type="button" onclick="searchAll(0)" class="btn5">刷新</button>
        <button type="button" onclick="export1()" class="btn5">导出excel</button>
    </div>
</div>
<div class="newtopbox zsywidth_tp">
    <form id="win_form_search">
        <div>入库单号 <input class="newinput" type="text" id="warehousCode" value="" name="billcode" placeholder=""></div>
        <div class="nmlf20">入库类型：
            <select id="select1" class="newinput2">
                <option selected="selected" value="0">全部</option>
                <option value="1">订单入库</option>
                <option value="2">物料入库</option>
            </select>
        </div>
        <div class="nmlf20">关键字：<input onkeyup="seachEnter()" type="text" id="remarks" class="newinput" value=""
                                       placeholder="别名/名称/规格型号"></div>
        <%--        <div class="nmlf20">操作时间： <input type="text" onkeyup="seachEnter()" id="operationDate" class="newinput" value=""></div>--%>
        <div class="nmlf20">
            <input id="operationDate" name="startDate" class="newinput"
                   placeholder="选择时间" type="text"
                   onkeyup="seachEnter()" readonly onclick="selectRangeDate()"
            >
        </div>
    </form>
    <div>
        <button type="button" onkeyup="searchAll(1)" onclick="searchAll(1)" class="btn1">查询</button>
    </div>
</div>
<%-- 新的 e --%>


<div id="containerWhole" class="zsywidth">

    <%--    <div class="container33" style="width: 100%;height: 45px;background-color: #FCFCFC;border-bottom:#F0F0F0 1.8px solid; ">--%>
    <%--            <div style="line-height: 3"><span style="font-size: 18px;margin-left: 20px">入库管理</span>--%>
    <%--&lt;%&ndash;                save()&ndash;%&gt;--%>
    <%--                <span style="margin-left: 50%;"><button type="button" onclick="addNewEnter()" class="btn btn-success btn-xs" style="margin-left: 30px;width: 95px;vertical-align:middle;border-radius: 20px;"><span style="margin-bottom: 10px">新增入库</span></button></span>--%>
    <%--                <span><button type="button" onclick="searchAll(0)" class="btn btn-default btn-xs" style="margin-left: 30px;width: 95px;border-radius: 20px;">刷新</button></span>--%>
    <%--                <span><button type="button" onclick="export1()" class="btn btn-default btn-xs" style="margin-left: 30px;width: 95px;border-radius: 20px;">导出excel</button></span>--%>
    <%--            </div>--%>
    <%--        </div>--%>
    <%--    <div class="container33" style="width: 100%;height: 60px;background-color: #F0F0F0; margin-top: 10px">--%>
    <%--    <div style="margin-left: 30px;margin-top: -38px;line-height: 1.5;line-height:60px;">--%>
    <%--        <form class="form-horizontal" id="win_form_search" style="margin-top: 46px">--%>
    <%--        <span style="font-size: 12px;margin-left: 38px">入库单号：<input type="text" id="warehousCode" style="width:150px;height: 28px" value="" placeholder=""></span>--%>
    <%--        <span style="font-size: 12px;margin-left: 35px">入库类型：--%>
    <%--            <span>--%>
    <%--                <select id="select1">--%>
    <%--                    <option selected="selected" value="0">全部</option>--%>
    <%--                    <option  value="1">订单入库</option>--%>
    <%--                    <option value="2">物料入库</option>--%>
    <%--                </select>--%>
    <%--            </span>--%>
    <%--        </span>--%>
    <%--        <span style="font-size: 12px;margin-left: 50px">关键字：<input onkeyup="seachEnter()" type="text" id="remarks" style="width:150px;height: 28px" value="" placeholder="编号/名称/规格型号"></span>--%>
    <%--&lt;%&ndash;        <span style="font-size: 12px;margin-left: 150px">发票号：<input type="text" id="billCode" style="width:150px;height: 28px" value="" placeholder=""></span>&ndash;%&gt;--%>
    <%--        <span style="font-size: 12px;margin-left: 60px">操作时间：<input type="text" onkeyup="seachEnter()" id="operationDate" style="width:150px;height: 28px" value=""></span>--%>
    <%--        </form>--%>
    <%--        <span><button type="button" onkeyup="searchAll(1)" onclick="searchAll(1)" class="btn btn-default btn-xs" style="margin-left: 980px;width: 95px;margin-top: -39px">查询结果</button></span>--%>
    <%--    </div>--%>
    <%--    </div>--%>


    <div class="container33" style="height: 600px;background-color: white;margin: 30px 30px 0 30px;">
        <div style="line-height: 4;width: 100%;height: 50px;background-color: #F0F0F0;"><span
                style="font-size: 12px;margin-left: 20px">数据列表</span></div>
        <table class="table invoice-table" id="myTable">
            <thead style="border: 1.5px solid #F0F0F0;">
            <tr>
                <%--                text-align:center;--%>
                <th style="font-size: 12px;border: 1.5px solid #F0F0F0;"><input type="checkbox" id="all" onclick="exportItem(this,-1)"></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><a onclick="a(1)" style="text-decoration: underline">入库单号</a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><a onclick="a(2)" style="text-decoration: underline">入库类型</a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">入库备注</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><a onclick="a(4)" style="text-decoration: underline">采购金额</a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><a onclick="a(5)" style="text-decoration: underline">零售金额</a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><a onclick="a(6)" style="text-decoration: underline">制作人</a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><a onclick="a(7)" style="text-decoration: underline">时间</a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;"><a onclick="a(8)" style="text-decoration: underline">发票号</a></th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作信息</th>
            </tr>
            </thead>
            <tbody id="mxList">
            </tbody>
        </table>
        <div class="count" id="count1" style="margin-left: 30px">合计:共计<span id="c1"></span>条数据,总合计采购金额<span
                id="c2"></span>元,零售金额<span id="c3"></span>元
        </div>
        <div class="pagination m_lf" id="Pagination1"></div>
    </div>
    <%--    <div class="alert" style="" id="" style="margin-left: 30px"></div>--%>
</div>
<%--</div>--%>
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
<style type="text/css">

</style>
<script>
    $('#win_form_search').on('keyup', function () {
        if (event.keyCode == 13) {
            searchAll(1);
        }
    })
</script>