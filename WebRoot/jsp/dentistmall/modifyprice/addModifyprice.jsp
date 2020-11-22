
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
<script src="<c:url value='/javaScript/sys/sysFeedback/sysFeedback.js?v=27'/>"></script>
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<%--//引入时间插件--%>
<script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">

<style>
    .close{
        position:relative;
        width:0.3em;
        height:1.5em;
        background: #333;
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        -o-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
        display: inline-block;
    }
    .close:after{
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        width:0.3em;
        height:1.5em;
        background: #333;
        -webkit-transform: rotate(270deg);
        -moz-transform: rotate(270deg);
        -o-transform: rotate(270deg);
        -ms-transform: rotate(270deg);
        transform: rotate(270deg);
    }
</style>
<body>
<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<div id="containerWhole" style="width: 100%;">
    <div class="container33" style="width: 100%;height: 60px;background-color: #FCFCFC;border-bottom:#F0F0F0 1.8px solid; ">
            <div style="line-height: 3"><span style="font-size: 18px;margin-left: 20px">新增调价【新增】</span>
                <span style="margin-left: 50%;"><button type="button" onclick="save()" class="btn btn-success btn-sm" style="margin-left: 30px;width: 70px;border-radius: 20px;">保存</button></span>
                <span><button type="button" onclick="return1()" class="btn btn-default btn-sm" style="margin-left: 30px;width: 70px;border-radius: 20px;">返回</button></span>
            </div>
        </div>
    <div class="container33" style="width: 100%;height: 60px;background-color: #F0F0F0; margin-top: 10px">
        <div style="margin-left: 30px;margin-top: 20px;line-height: 1.5;line-height:60px;">
            <span style="font-size: 12px;margin-left: 20px">调价号：<input type="text" id="modifyCode" style="width:150px;height: 28px;" value="" placeholder=""></span>
            <span style="font-size: 12px;margin-left: 40px">制作人：<input type="text" id="createRen" style="width:150px;height: 28px" value="" placeholder=""></span>
            <span style="font-size: 12px;margin-left: 250px">调价类型：<input type="text" id="modifyType" style="width:150px;height: 28px" value="" placeholder="正常调价/市场调价"></span>
            <span style="font-size: 12px;margin-left: 40px">调价比例：<input type="text" id="modifyProportion" style="width:150px;height: 28px" value="" placeholder="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%"></span>
            <span style="font-size: 12px;">备注：<input type="text" id="remarks" style="width:150px;height: 28px" value="" placeholder=""></span>
        </div>
    </div>
    <div class="container33" style="width: 100%;height: 600px;background-color: white;margin-top: 30px">
        <table class="table invoice-table">
            <thead style="border: 1.5px solid #F0F0F0;">
            <tr><th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">物料编码</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">商品名称</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">品牌</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">规格型号/编码</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">调价单位</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购均价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">原零售价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">现零售价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">差价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">调价比例</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">批号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">注册号/备案号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">库存数量</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作</th>
            </tr>
            </thead>
            <tbody id="mxList">
            </tbody>
        </table>
    </div>
<%--        <div id="hideFeedBack" style="width: 600px;height: 500px;background-color: #F0F0F0;position: absolute;top:100px;left: 400px;display: none">--%>
<%--            <div class="hideFeedBack-top" style="width: 100%;height: 40px">--%>
<%--                <span class="close" style="margin-right: 13px" onclick="showFeedBackTop()"></span>--%>
<%--            </div>--%>
<%--            <div style="text-align: center" style="width: 100%;height: 40px">--%>
<%--                <span style="font-size: 20px">编号<span id="fildSpan"></span>反馈明细</span>--%>
<%--            </div>--%>
<%--            <div  class="dv-1" >--%>
<%--                反馈时间：<span id="feedDate"></span>--%>
<%--            </div>--%>
<%--            <div class="dv-1">--%>
<%--                反馈类型：<span id="feedType"></span>--%>
<%--            </div>--%>
<%--            <div  class="dv-2" >--%>
<%--                反馈人：<span id="feedUserName"></span>--%>
<%--            </div>--%>
<%--            <div class="dv-2">--%>
<%--                反馈人所属机构：<span id="feedNodeName"></span>--%>
<%--            </div>--%>
<%--            <div class="dv-3">--%>
<%--                反馈人手机号码：<span id="feedPhone"></span>--%>
<%--            </div>--%>
<%--            <div class="dv-3">--%>
<%--                问题类型：<span id="questionType1"></span>--%>
<%--            </div>--%>
<%--            <div class="dv-4">--%>
<%--                反馈状态：<span id="feedState"></span>--%>
<%--            </div>--%>
<%--            <div class="dv-4">--%>

<%--            </div>--%>
<%--            <div class="dv-5">--%>
<%--                反馈描述：--%>
<%--            </div>--%>
<%--            <div class="dv-5">--%>

<%--            </div>--%>
<%--            <div class="dv-6" id="fbValue">--%>
<%--            </div>--%>
<%--            <div class="dv-7" id="dv7">--%>
<%--                <span style="float: right"><button type="button"  onclick="feedback1()" class="btn btn-primary">标记处理</button></span>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="pagination" id="Pagination1" style="margin-left: 650px">
        </div>
</div>
</div>

</body>
</html>
<%--<style type="text/css">--%>
<%--    .feedBack{--%>
<%--        font-size: 15px;--%>
<%--    }--%>
<%--    .dv-1{--%>
<%--        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;--%>
<%--        line-height: 3;--%>
<%--    }--%>
<%--    .dv-2{--%>
<%--        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;--%>
<%--        line-height: 3;--%>
<%--    }--%>
<%--    .dv-3{--%>
<%--        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;--%>
<%--        line-height: 3;--%>
<%--    }--%>
<%--    .dv-4{--%>
<%--        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;--%>
<%--        line-height: 3;--%>
<%--    }--%>
<%--    .dv-5{--%>
<%--        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;--%>
<%--        line-height: 4;--%>
<%--    }--%>
<%--    .dv-6{--%>
<%--        width:500px;height: 100px;margin-left:40px;border:0px solid black;text-align:left;float:left;--%>
<%--    }--%>
<%--    .dv-7{--%>
<%--        width:500px;height: 100px;margin-left:40px;border:0px solid black;text-align:left;float:left;display: none;--%>
<%--    }--%>
<%--</style>--%>
<style type="text/css">
    button{
        border-radius: 8px;
    }
</style>