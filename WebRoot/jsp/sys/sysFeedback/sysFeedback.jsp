
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
<script src="<c:url value='/javaScript/sys/sysFeedback/sysFeedback.js?v=24'/>"></script>
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
    <div class="container33" style="width: 100%;height: 40px;background-color: #FCFCFC;">
        <div style="line-height:1.5;margin-left: 30px;margin-top: 10px">
            <span style="font-size: 12px;"><input type="text" id="input1" style="" value="" placeholder="搜索关键字"></span>
            <span style="font-size: 12px;margin-left: 50px">类型:
                <select id="selectType">
                    <option selected="selected" value="0">全部</option>
                    <option value="2">小程序商城</option>
                    <option value="3">小程序代理商</option>
                </select>
            </span>
            <span style="font-size: 12px;margin-left: 50px">问题类型:
                <select id="questionType">
                    <option selected="selected" value="0">全部</option>
                    <option value="1">建议</option>
                    <option value="2">咨询</option>
                    <option value="3">其他</option>
                </select>
            </span>
            <span style="font-size: 12px;margin-left: 50px">状态:
                <select id="state">
                    <option selected="selected" value="0">全部</option>
                    <option value="1">待处理</option>
                    <option value="2">已处理</option>
                </select>
            </span>
            <span style="font-size: 12px;margin-left: 50px">日期:
                <input  value="" id="dateInput1"> —
                <input  value="" id="dateInput2">
            </span>
              <button type="button" onclick="searchAll()" class="btn btn-primary btn-xs" style="width: 95px;height: 22px;margin-left: 30px" >搜索</button>
    </div>
    <div class="container33" style="width: 100%;height: 40px;background-color: #FCFCFC;">
        <div style="line-height:3;margin-left: 30px">
            <span style="font-size: 12px">问题反馈次数:共计<span id="countFeedBack"></span></span>
        </div>
    </div>
    <div class="container33" style="width: 100%;height: 600px;background-color: white;">
        <table class="table invoice-table">
            <thead style="border: 1.5px solid #F0F0F0;">
            <tr><th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">编号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">日期</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">问题类型</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">类型</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">状态</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">反馈内容</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">处理日志</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作</th>
            </tr>
            </thead>
            <tbody id="mxList">
            </tbody>
        </table>
    </div>
        <div id="hideFeedBack" style="width: 600px;height: 500px;background-color: #F0F0F0;position: absolute;top:100px;left: 400px;display: none">
            <div class="hideFeedBack-top" style="width: 100%;height: 40px">
                <span class="close" style="margin-right: 13px" onclick="showFeedBackTop()"></span>
            </div>
            <div style="text-align: center" style="width: 100%;height: 40px">
                <span style="font-size: 20px">编号<span id="fildSpan"></span>反馈明细</span>
            </div>
            <div  class="dv-1" >
                反馈时间：<span id="feedDate"></span>
            </div>
            <div class="dv-1">
                反馈类型：<span id="feedType"></span>
            </div>
            <div  class="dv-2" >
                反馈人：<span id="feedUserName"></span>
            </div>
            <div class="dv-2">
                反馈人所属机构：<span id="feedNodeName"></span>
            </div>
            <div class="dv-3">
                反馈人手机号码：<span id="feedPhone"></span>
            </div>
            <div class="dv-3">
                问题类型：<span id="questionType1"></span>
            </div>
            <div class="dv-4">
                反馈状态：<span id="feedState"></span>
            </div>
            <div class="dv-4">

            </div>
            <div class="dv-5">
                反馈描述：
            </div>
            <div class="dv-5">

            </div>
            <div class="dv-6" id="fbValue">
            </div>
            <div class="dv-7" id="dv7">
                <span style="float: right"><button type="button"  onclick="feedback1()" class="btn btn-primary">标记处理</button></span>
            </div>
        </div>
        <div class="pagination" id="Pagination1" style="margin-left: 650px">
        </div>
</div>
</div>

</body>
</html>
<style type="text/css">
    .feedBack{
        font-size: 15px;
    }
    .dv-1{
        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;
        line-height: 3;
    }
    .dv-2{
        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;
        line-height: 3;
    }
    .dv-3{
        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;
        line-height: 3;
    }
    .dv-4{
        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;
        line-height: 3;
    }
    .dv-5{
        width:255px;height: 50px;margin-left:40px;border:0px solid black;float:left;
        line-height: 4;
    }
    .dv-6{
        width:500px;height: 100px;margin-left:40px;border:0px solid black;text-align:left;float:left;
    }
    .dv-7{
        width:500px;height: 100px;margin-left:40px;border:0px solid black;text-align:left;float:left;display: none;
    }
</style>