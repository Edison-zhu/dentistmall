<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%--    <base href="<%=basePath%>">--%>

    <title>My JSP 'homePageWarehouse.jsp' starting page</title>

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
<script src="<c:url value='/javaScript/main/FinancePage.js?v=83'/>"></script>
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<script src="https://cdn.bootcss.com/moment.js/2.24.0/moment-with-locales.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>

<%--//引入时间插件--%>
<script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<style type="text/css">
    .a1{
        border:2px solid red
    }
</style>
<body>

<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<div id="containerWhole" style="width:1407px">
    <div id="container2" style="width:677px;height:220px;margin-left: 57px;background-color: #FCFCFC;border-radius: 5px 5px 5px 5px;margin-top: 30px" class="container23 con22">
        <div style="width:677px; height: auto;margin-left: 478px">
            <div class="btn-group btn-group-xs">
                <button type="button" style="background-color:#F5F5F5" class="btn btn-default" id="btn11" onclick="initDataAnalysis(0)">今日</button>
                <button type="button" style="background-color:#F5F5F5" class="btn btn-default" id="btn21" onclick="initDataAnalysis(1)">昨日</button>
                <button type="button" style="background-color:#F5F5F5" class="btn btn-default" id="btn31" onclick="initDataAnalysis(2)">最近7日</button>
                <button type="button" style="background-color:#F5F5F5" class="btn btn-default" id="btn41" onclick="initDataAnalysis(3)">最近一个月</button>
            </div>
        </div>
        <div id="container2Left" class="container21" style="width: 250px;height: 180px;margin-top: 20px;background-color:#46A3FF;float: left;margin-left: 20px;border-radius: 5px 5px 5px 5px;">
            <img style="float:right;margin-right: 20px;margin-top: 20px" width="100px" height="100px"
                 src="../../../dentistmall/css/shopping/images/container2Left.png">
            <div class="container2Span1"  style="width: 125px; height:30px;margin-top: 80px;text-align: left"><span id="FinanceRight" style="margin-left: 30px;color: white"></span></div>
            <div class="container2Span" style="width: 125px; height:30px;margin-top: 5px;text-align: center;"><b><span style="color: white">待结算金额</span></b></div>
        </div>
        <div id="container2Middle" class="container22" style="width: 250px;height: 180px;background-color: #46A3FF;float: left;margin-left: 130px;margin-top: 20px;border-radius: 5px 5px 5px 5px;">
            <img style="float:right;margin-right: 20px;margin-top: 20px" width="100px" height="100px"
                 src="../../../dentistmall/css/shopping/images/container2Left.png">
            <div class="container2Span1"  style="width: 125px; height:30px;margin-top: 80px;text-align: left"><span id="FinanceLeft" style="margin-left: 30px;color: white"></span></div>
            <div class="container2Span" style="width: 125px; height:30px;margin-top: 5px;text-align: center;"><b><span style="color: white">已结算金额</span></b></div>
        </div>
        <div id="container3" style="width:677px;height:220px;background-color: #FCFCFC;border-radius: 5px 5px 5px 5px;position: absolute;top: 32px;left: 760px" class="container23 con22">
            <div id="main" style="width: 677px;height:230px;">
            </div>
        </div>
    </div>
    <div style="width: 480px;position: absolute;top: 32px;left: 1020px">
        <span>选择日期</span>
        <span><input value="" id="dateInput3" style="width: 100px" onMouseOut="a6()"/>~</span>
        <span><input value="" id="dateInput4" style="width: 100px"/></span>

<%--        <span><input value="" id="dateInput3" style="width: 100px" onMouseOut="a6()"/>~</span>--%>
<%--        <span><input value="" id="dateInput4" style="width: 100px"/></span>--%>
        <a onclick="a5()">确认</a>
        <span><button type="button" onclick="exportapplicName();" class="btn btn-primary btn-xs searchBtn" id="button2" style="width: 95px;height: 22px">导出</button></span>
    </div>
    <div id="container4" style="width: 1375px;height: 30px; border: 0px solid black;margin-top: 30px;margin-left: 57px;">
        <span>订单号:</span>
        <span><input type="text" style="width: 200px;height: 26px" id="orderCodeGJ" placeholder="请输入单号关键字"/></span>
        <span style="margin-left: 70px">时间范围:</span>
        <input  value="" id="dateInput1" style="height: 26px">
        <input  value="" id="dateInput2" style="height: 26px">
        <span style="margin-left: 70px">状态:</span>
        <span>
            <select id="selectValue" style="height: 26px">
                <option selected value=0>所有</option>
                <option value=1>未结算</option>
                <option value=2>已结算</option>
                <option value=3>部分结算</option>
            </select>
        </span>
        <span style="margin-left: 70px"><button type="button" onclick="searchAll();" class="btn btn-primary btn-xs searchBtn" id="button1" style="width: 95px;height: 22px;margin-top: -5px">搜索</button></span>
        <span><a style="color: #0e9aef; margin-top: -10px;margin-left: 70px" onclick="searchAlls()">高级检索</a></span>
    </div>
    <div class="container33" style="width: 1377px;height: 400px;background-color: white;margin-left: 46px; margin-top:30px;border-radius: 5px 5px 5px 5px;">
        <span><a onclick="plJieSuan()" style="margin-left: 80px;color: #46A3FF;font-size: 13px" >批量结算</a></span>
        <span><a onclick="exportFinanciaBranchMxList()" style="margin-left: 80px;color: #46A3FF;font-size: 13px" >导出对账明细</a></span>
        <table class="table invoice-table">
            <thead>
            <tr style="border-top:1.5px solid #F0F0F0;">
                <th style="text-align:center;font-size: 13px; border-left:1.5px solid #F0F0F0;"><input name="all" type="checkbox" id="all" onclick="all()"></th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">订单号</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">订单日期</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">支付方式</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">支付时间</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">支付单号</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">应付金额</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">实收金额</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">采购商</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">供应商</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;">状态</th>
                <th style="text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;border-right:1.5px solid #F0F0F0;">操作</th>
            </tr>
            </thead>
            <tbody id="mxList">
            </tbody>
        </table>
        <div style="margin-left: 20px">汇总：共计<span id="total1"></span>条;未对账共计<span id="money1"></span>元;已对账<span id="money2"></span>元</div>

    </div>
</div>
    <div id="divHide1" style="border: 1px solid #F0F0F0; width: 300px;height: 380px;position: absolute;top:319px;left: 1082px;display: none">
        <div style="width: 300px;height: 30px;background-color: #F0F0F0">
            <span>高级检索</span>
            <span style="margin-left: 200px"><a onclick="offTall()">关闭</a></span>
        </div>
        <div style="width: 300px;height: 350px;background-color: #FCFCFC">
        <span style="margin-left: 25px">订单号:</span><span><input type="text" style="width: 200px;" id="orderCodeGJ1" placeholder="请输入单号关键字"/></span><br/><br/><br/>
        <span style="margin-left: 26px">时间范围:</span>
        <input  value="" id="dateInput5" style="width: 80px">至
        <input  value="" id="dateInput6" style="width: 80px"><br/><br/><br/>
        <span style="margin-left: 26px">对账状态
            <select id="selectValue1" style="width: 150px">
                <option selected value="0">所有</option>
                <option value="1">未对账</option>
                <option value="2">已对账</option>
                <option value="3">部分对账</option>
            </select>
        </span><br/><br/><br/>
        <span style="margin-left: 26px">金额范围:</span>
        <input id="moneyFw1" value="" style="width: 80px"/>至
        <input id="moneyFw2" value="" style="width: 80px"/><br/><br/><br/>
        <span style="margin-left: 26px">供应商:</span>
        <input id="gongYingS" value="" style="width: 200px"/><br/><br/><br/>
        <span style="margin-left: 26px">采购商</span>
        <input id="caiGou" value="" style="width: 200px"/><br/><br/>
        <span style="margin-left: 110px"><button type="button" onclick="searchAll1();" class="btn btn-primary btn-xs searchBtn" id="button3" style="width: 95px;height: 22px;margin-top: -5px">搜索</button>
        </span>
        </div>
    </div>
<div class="pagination" id="Pagination1" style="margin-left: 600px">

</div>
</body>
</html>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    myChart.setOption({
        title: {
            text: '供应商交易金额分析(单位/万元)',
            textStyle: {
                fontSize: 14
            },
        },
        tooltip: {},
        legend: {
            data: ['全部金额','待结算金额'],
            icon:'rect',
            x:'center',      //可设定图例在左、右、居中
            y:'bottom',     //可设定图例在上、下、居中
            padding:[0,0,20,0],
        },
        xAxis: {
            //data:["儿童口腔","手术室","正畸科","口腔综合科","牙周科","洁牙中心","其他科室"]
            data: ["一站式服务","上海魅晋商贸","奥齿态供应室","3M医疗"]
        },

        yAxis: {
            type: 'value',
            boundaryGap: [0, 0.01]
        },
        series: [{
            name: '全部金额',
            type: 'bar',
            barWidth : 20,
            itemStyle: {
                normal: {
                    color: '#0099FF',
                    label: {show: true, //开启显示
                        position: 'top', //在上方显示
                        textStyle: { //数值样式
                            color: 'black',
                            fontSize: 16
                        }
                    }
                }
            },
            barMaxWidth:80,
            data:[]

        }, {
            name: '待结算金额',
            type: 'bar',
            barWidth: 20,
            barGap:1,
            itemStyle: {
                normal: {
                    color: '#00EC00',
                    label: {
                        show: true, //开启显示
                        position: 'top', //在上方显示
                        textStyle: { //数值样式
                            color: 'black',
                            fontSize: 16
                        }
                    }
                }
            },
            barMaxWidth:20,
            data: []
        }]

    });

</script>
