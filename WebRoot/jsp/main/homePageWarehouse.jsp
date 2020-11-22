<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>My JSP 'homePageWarehouse.jsp' starting page</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
    <%@ include file="/jsp/lib.jsp"%>
    <%@ include file="/jsp/link.jsp"%>
    <style type="text/css">
        .a1{
        border:2px solid red
        }
    </style>
</head>
<script src="<c:url value='/javaScript/main/homePageWarehouse.js?v=39'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/homePageWarehouse.css?v=7' />">
<script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<%--<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>--%>
<%--<script src="js/jquery-1.11.3.js"></script>--%>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<body>
<!-- 布局  全部containerWhole 包含的div分别用container1,2...表示 -->
<div id="containerWhole" style="width:1307px">
    <div id="container1">
        <b><%--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--%><span style="font-size: 25px">今天,</span> </b><br/>
        <span class="newDate"><font size="2px"><%--&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp--%><span id="currentDate"></span>,欢迎回来!</span></font>
    </div>
    <div id="export" style="width:1407px;height:20px;margin-left: 1046px;margin-top:7px">
        <button type="button" class="btn btn-default btn-xs" onclick="exportCumulativeWarning()">导出数据</button>
    </div>
    <div id="container2" style="width:1407px;height:180px;margin-left: 27px;" class="container23 con22">

        <a onclick="leftA1()">
            <div id="container2Left" class="container2">
            <img style="float:right" width="80px" height="80px" src="../../../dentistmall/css/shopping/images/leftOne.png">
            <span class="container2Span" id="OutStock"></span></br></br>
            <span class="container2Span"><b>待申领出库</b></span>
        </div>
        </a>
        <a onclick="leftA2()">
        <div id="container2Middle" class="container2">
            <img style="float:right" width="90px" height="90px" src="../../../dentistmall/css/shopping/images/leftTwo.png">
            <span class="container2Span" id="WarehousedOrder"></span></br></br>
            <span class="container2Span"><b>订单待入库</b></span>
        </div>
        </a>
        <a onclick="leftA3()">
        <div id="container2Right" class="container2">
            <img style="float:right;margin-right: 12px;" width="90px" height="90px" src="../../../dentistmall/css/shopping/images/leftThree.png">
            <span class="container2Span" id="ReturnStock"></span></br></br>
            <span class="container2Span"><b>退货出库</b></span>
        </div>
        </a>
        <a onclick="leftA4()">
        <div id="container2Right1" class="container2">
            <img style="float:right;margin-right: 12px;" width="90px" height="90px" src="../../../dentistmall/css/shopping/images/leftFour.png">
            <span class="container2Span" id="CumulativeWarning"></span></br></br>
            <span class="container2Span"><b>申领库存告警</b></span>
        </div>
        </a>
        <a onclick="leftA5()">
        <div id="container2Right2" class="container2">
            <img style="float:right;margin-right: 12px;" width="90px" height="90px" src="../../../dentistmall/css/shopping/images/leftFour.png">
            <span class="container2Span" id="RetreatPlaceOrderMoney"></span></br></br>
            <span class="container2Span"><b>安全库存预警</b></span>
        </div>
        </a>
    </div>
    <div id="container3">
        <div id="mxlist">
            <div id="mxlist1"></div>
        </div>
<%--            <div class="container3-1">--%>
<%--                <span>各科室申领次数统计 (单位/次)</span>--%>
<%--            </div>--%>
    <div class="btn-group btn-group-sm"style="position: relative;top:50px;left: 1000px;z-index: 99999"></span>
        <button type="button" class="btn btn-default" id="btn1" onclick="initDataAnalysis(0)">今日</button>
        <button type="button" class="btn btn-default" id="btn2" onclick="initDataAnalysis(1)">昨日</button>
        <button type="button" class="btn btn-default" id="btn3" onclick="initDataAnalysis(2)">最近7日</button>
        <button type="button" class="btn btn-default" id="btn4" onclick="initDataAnalysis(3)">最近一个月</button>
    </div>
        <div id="main" style="width: 1407px;height:400px;">
        </div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        myChart.setOption({
            title: {
                text: '各科室申领次数统计 (次/单位)',
                textStyle: {
                    fontSize: 14
                },
            },
            tooltip: {},
            legend: {
                data: ['销量']
            },
            xAxis: {
                //data:["儿童口腔","手术室","正畸科","口腔综合科","牙周科","洁牙中心","其他科室"]
                data: ["儿童口腔","手术室","正畸科","口腔综合科","牙周科","洁牙中心","其他科室"]
            },

            yAxis: {
                    type: 'value',
                    boundaryGap: [0, 0.01]
            },
            series: [{
                name: '',
                type: 'bar',
                barWidth : 50,
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
                data:[]
            }]
        });

    </script>
    </div>
<%--    <div id="container3" style="width:400px;height:180px" class="container23 con23">--%>
<%--        <div id="container3Top" >--%>
<%--            <img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--            <font size="2px" color="#FFFFFF">快捷方式</font>--%>
<%--        </div>--%>
<%--        <div id="container3Middle" >--%>
<%--            <div class="container3Middle1">--%>
<%--                <a onclick="allOrder()" style="color:#000000"><font size="2px" style="line-height:3">全部订单</font></a>--%>
<%--            </div>--%>
<%--            <div class="container3Middle1">--%>
<%--                <a onclick="commodity1()" style="color:#000000"><font size="2px" style="line-height:3">商品管理</font></a>--%>
<%--            </div>--%>
<%--            <div class="container3Middle1">--%>
<%--                <a onclick="supplier1()" style="color:#000000"><font size="2px" style="line-height:3">供应商管理</font></a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div id="container3Bottom" >--%>
<%--            <div class="container3Bottme1">--%>
<%--                <a onclick="group1()" style="color:#000000"><font size="2px" style="line-height:3">集团管理</font></a>--%>
<%--            </div>--%>
<%--            <div class="container3Bottme1">--%>
<%--                <a onclick="hospital1()" style="color:#000000"><font size="2px" style="line-height:3">医院管理</font></a>--%>
<%--            </div>--%>
<%--            <div class="container3Bottme1">--%>
<%--                <a onclick="outpatient()" style="color:#000000"><font size="2px" style="line-height:3">门诊管理</font></a>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div id="container4" style="width:807px;height:221px;margin-top:8px" >--%>
<%--        <div class="container4 con1">--%>
<%--            <img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--            <font size="2px" color="#FFFFFF">交易概况</font>--%>
<%--            <div class="container4 con1-con1">--%>
<%--                <button>导出数据</button>--%>
<%--                <img width="20px" height="23px" src="../../../dentistmall/css/shopping/images/container4Top2.png">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div id="container4Middle" >--%>
<%--            <div class="container4Middle1">--%>
<%--                <font size="2px" style="line-height:3">待付款订单</font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle1">--%>
<%--                <font size="2px" style="line-height:3" id="dfkCount"></font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle1">--%>
<%--                <font size="2px" style="line-height:3">待收货订单</font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle1">--%>
<%--                <font size="2px" style="line-height:3" id="dshCount"></font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle1">--%>
<%--                <font size="2px" style="line-height:3">售后退款订单<font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle1">--%>
<%--                <font size="2px" style="line-height:3">(0)<font>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div id="container4Middle2" >--%>
<%--            <div class="container4Middle2">--%>
<%--                <font size="2px" style="line-height:3">待发货订单</font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle2">--%>
<%--                <font size="2px" style="line-height:3" id="dfhCount"></font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle2">--%>
<%--                <font size="2px" style="line-height:3">交易成功订单</font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle2">--%>
<%--                <font size="2px" style="line-height:3" id="jycgCount"></font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle2">--%>
<%--                <font size="2px" style="line-height:3">售后退货订单<font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle2">--%>
<%--                <font size="2px" style="line-height:3">(0)<font>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div id="container4Middle3" >--%>
<%--            <div class="container4Middle3">--%>
<%--                <font size="2px" style="line-height:3">部分发货订单</font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle3">--%>
<%--                <font size="2px" style="line-height:3" id="bffhCount"></font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle3">--%>
<%--                <font size="2px" style="line-height:3">交易失败订单</font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle3">--%>
<%--                <font size="2px" style="line-height:3" id="jysbCount"></font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle3">--%>
<%--                <font size="2px" style="line-height:3">售后完成订单<font>--%>
<%--            </div>--%>
<%--            <div class="container4Middle3">--%>
<%--                <font size="2px" style="line-height:3">(0)<font>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div id="container5" style="width:400px;height:500px">--%>
<%--        <div class="container5" style="border-bottom: solid 1px lightgrey">--%>
<%--            <img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--            <span><font color="#FFFFFF">交易实时战报</font></span>--%>
<%--            <span class="container5Con1">--%>
<%--    				<font color="#FFFFFF">更多>></font>--%>
<%--    			</span>--%>
<%--        </div>--%>
<%--        <div>--%>
<%--            <div class="container5ConMiddle1">--%>
<%--                <span class="container5Con1Font" id="TransactionMoneyCount"></span></br>--%>
<%--                <span class="container5Con1Font2">&nbsp&nbsp&nbsp今日销售额</span>--%>
<%--            </div>--%>
<%--            <div class="container5ConMiddle1">--%>
<%--                <span class="container5Con1Font1" id="TransactionCount"></span></br>--%>
<%--                <span class="container5Con1Font2">今日下单</span>--%>
<%--            </div>--%>
<%--            <div class="container5ConMiddle1">--%>
<%--                <span class="container5Con1Font11" id="PercentageCount">%</span></br>--%>
<%--                <span class="container5Con1Font2">今日下单</span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="container5Bottom">--%>
<%--            <table class="table invoice-table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th style="text-align:center">下单时间</th>--%>
<%--                    <th style="text-align:center">采购人</th>--%>
<%--                    <th style="text-align:center">下单件数</th>--%>
<%--                    <th style="text-align:center">下单金额</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody id="mxList">--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div id="container6" style="width:478px;height:230px;">--%>
<%--        <div class="container6con1">--%>
<%--            <img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--            <span class="container6con2" style="line-height:1"><font color="#FFFFFF">商品种类统计(一类)</font></span>--%>
<%--        </div>--%>
<%--        <div class="container6Bottom">--%>
<%--            <table class="table materiel-table">--%>
<%--                <thead>--%>
<%--                <th style="text-align:center">修复填充类</th>--%>
<%--                <th style="text-align:center">车针/扩锉/磨头</th>--%>
<%--                <th style="text-align:center">口腔常用材料</th>--%>
<%--                <th style="text-align:center">医用耗材</th>--%>
<%--                <th style="text-align:center">口腔科常用器械</th>--%>
<%--                <th style="text-align:center">正畸产品类</th>--%>
<%--                <th style="text-align:center">口腔护理</th>--%>
<%--                <th style="text-align:center">种植产品类</th>--%>
<%--                </thead>--%>
<%--                <tbody id="mxmaterielList">--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div id="container7" style="width:310px;height:110px">--%>
<%--        <div class="container7con1"><img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--            <span class="container7con11" style="line-height:1"><font color="#FFFFFF">商品总览</font></span>--%>
<%--            <span class="container7con12"><img width="20px" height="23px" src="../../../dentistmall/css/shopping/images/container4Top2.png"></span>--%>
<%--        </div>--%>
<%--        <div class="container7con2" style="width:380px;height:80px">--%>
<%--            <div class="container7con21" style="width:380px;height:40px">--%>
<%--                <div class="container7con211">--%>
<%--                    <span style="line-height:2" id="allmaterielCount"></span>--%>
<%--                </div>--%>
<%--                <div class="container7con2111">--%>
<%--                    <span style="" id="ysjCount"></span>--%>
<%--                </div>--%>
<%--                <div class="container7con21111">--%>
<%--                    <span style="line-height:2" id="yxjCount"></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="container7con22" style="width:380px;height:40px">--%>
<%--                <div class="container7con212">--%>
<%--                    <span style="line-height:2">全部商品</span>--%>
<%--                </div>--%>
<%--                <div class="container7con2121">--%>
<%--                    <span style="line-height:2">已上架</span>--%>
<%--                </div>--%>
<%--                <div class="container7con21211">--%>
<%--                    <span style="line-height:2">已下架</span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div id="container8" style="width:310px;height:110px">--%>
<%--        <div class="container8con1"><img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--            <span class="container8con2" style="line-height:1"><font color="#FFFFFF">用户总览</font></span>--%>
<%--        </div>--%>
<%--        <div class="container8con2" style="width:380px;height:80px">--%>
<%--            <div class="container8con21" style="width:380px;height:40px">--%>
<%--                <div class="container8con211">--%>
<%--                    <span style="line-height:3.5" id="ghdCount">500</span>--%>
<%--                </div>--%>
<%--                <div class="container8con2111">--%>
<%--                    <span style="line-height:3.5" id="groupCount">500</span>--%>
<%--                </div>--%>
<%--                <div class="container8con21111">--%>
<%--                    <span style="line-height:3.5" id="hospitalCount">500</span>--%>
<%--                </div>--%>
<%--                <div class="container8con211111">--%>
<%--                    <span style="line-height:3.5" id="departmentCount">500</span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="container8con22" style="width:380px;height:40px">--%>
<%--                <div class="container8con212">--%>
<%--                    <span style="line-height:2">全部用户</span>--%>
<%--                </div>--%>
<%--                <div class="container8con2121">--%>
<%--                    <span style="line-height:2">集团用户</span>--%>
<%--                </div>--%>
<%--                <div class="container8con21211">--%>
<%--                    <span style="line-height:2">医院用户</span>--%>
<%--                </div>--%>
<%--                <div class="container8con212111">--%>
<%--                    <span style="line-height:2">门诊用户</span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div id="container9" style="width:810px;height:155px">--%>
<%--        <div id="container9Left" style="width:478px;height:145px;margin-top:6px;">--%>
<%--            <div class="container9Leftcon1"><img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--                <span class="container9Leftcon12" style="line-height:1"><font color="#FFFFFF">交易分析</font></span>--%>
<%--            </div>--%>
<%--            <div class="container9Leftcon2">--%>
<%--                <span><b>交易概述</b></span>--%>
<%--                <div class="container9Leftcon21">--%>
<%--                    <span class="container9Leftcon211"><a onclick="initDataAnalysis(0)">今日</a></span>--%>
<%--                    <span class="container9Leftcon211"><a onclick="initDataAnalysis(1)">昨日</a></span>--%>
<%--                    <span class="container9Leftcon211"><a onclick="initDataAnalysis(2)">最近七日</a></span>--%>
<%--                    <span class="container9Leftcon211"><a onclick="initDataAnalysis(3)">本月</a></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="container9Leftcon3" style="width:380px;height:80px">--%>
<%--                <div class="container9Leftcon31" style="width:380px;height:20px">--%>
<%--                    <div class="container9Leftcon311">--%>
<%--                        <span style="line-height:1" id="AnalysisCount"></span><span style="line-height:1" id="AnalysisCounts"></span>--%>
<%--                    </div>--%>
<%--                    <div class="container9Leftcon3111">--%>
<%--                        <span style="line-height:1" id="ActualMoney"></span></span><span style="line-height:1" id="ActualMoneys"></span>--%>
<%--                    </div>--%>
<%--                    <div class="container9Leftcon31111">--%>
<%--                        <span style="line-height:1" id="MoneyAvgs"></span>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="container9Leftcon32" style="width:380px;height:20px">--%>
<%--                    <div class="container9Leftcon322">--%>
<%--                        <span style="line-height:1">成交量/交易量</span>--%>
<%--                    </div>--%>
<%--                    <div class="container9Leftcon322">--%>
<%--                        <span style="line-height:1">成交额/交易额</span>--%>
<%--                    </div>--%>
<%--                    <div class="container9Leftcon322">--%>
<%--                        <span style="line-height:1">人均消费</span>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--        <div id="container9Right" style="width:305px;height:145px;margin-top: 8px">--%>
<%--            <div class="container9Leftcon1">--%>
<%--                <img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--                <span class="container9Leftcon12" style="line-height:1"><font color="#FFFFFF">交易转换率</font></span>--%>
<%--            </div>--%>
<%--            <div class="container9Rightcon2">--%>
<%--                <div class="container9Rightcon21">--%>
<%--                    <span class="container9Leftcon211"><a onclick="initDataConversion(0)">今日</a></span>--%>
<%--                    <span class="container9Leftcon211"><a onclick="initDataConversion(1)">昨日</a></span>--%>
<%--                    <span class="container9Leftcon211"><a onclick="initDataConversion(2)">最近七日</a></span>--%>
<%--                    <span class="container9Leftcon211"><a onclick="initDataConversion(3)">本月</a></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="container9Rightcon3" style="width:305px;height:80px">--%>
<%--                <div class="container9Rightcon31" style="width:380px;height:20px">--%>
<%--                    <div class="container9Rightcon311">--%>
<%--                        <span style="line-height:1" id=OrderBrowse></span>--%>
<%--                    </div>--%>
<%--                    <div class="container9Rightcon311">--%>
<%--                        <span style="line-height:1" id="OrderPayment"></span>--%>
<%--                    </div>--%>
<%--                    <div class="container9Rightcon311">--%>
<%--                        <span style="line-height:1" id="PaymentBrowse"></span>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="container9Rightcon32" style="width:380px;height:20px">--%>
<%--                    <div class="container9Rightcon322">--%>
<%--                        <span style="line-height:1">下单转化率</span>--%>
<%--                    </div>--%>
<%--                    <div class="container9Rightcon322">--%>
<%--                        <span style="line-height:1">付款转化率</span>--%>
<%--                    </div>--%>
<%--                    <div class="container9Rightcon322">--%>
<%--                        <span style="line-height:1">成交转化率</span>--%>
<%--                    </div>--%>
<%--                </div>--%>

<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>

<%--    <div id="container10"  style="width:400px;height:400px">--%>
<%--        <div class="container10C" style="line-height:1" >--%>
<%--            <img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png"/>--%>
<%--            <span><font color="#FFFFFF">商品排行榜(TOP10)</font></span>--%>
<%--            <div class="container10Con1"><button>导出数据</button></div>--%>
<%--        </div>--%>
<%--        <div class="container10Bottom">--%>
<%--            <table class="table invoice-table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th style="text-align:center">商品名称</th>--%>
<%--                    <th style="text-align:center">销量</th>--%>
<%--                    <th style="text-align:center">收藏数量</th>--%>
<%--                    <th style="text-align:center">总销售额</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody id="mxListTen">--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>

<%--    </div>--%>
<%--    <div id="container11"  style="width:400px;height:200px;margin-top: 5px;">--%>
<%--        <div class="container11C" style="line-height:1" >--%>
<%--            <img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--            <span><font color="#FFFFFF">仓管申领概况</font></span>--%>
<%--            <span class="container11C1"><img width="20px" height="23px" src="../../../dentistmall/css/shopping/images/container4Top2.png"></span>--%>
<%--        </div>--%>
<%--        <div class="container11con2" style="width:380px;height:30px">--%>
<%--            <div class="container11con21" style="width:380px;height:20px">--%>
<%--                <div class="container11con211">--%>
<%--                    <span style="line-height:1" id="WarehouseCount"></span><span style="line-height:1" id="WarehouseMoney"></span>--%>
<%--                </div>--%>
<%--                <div class="container11con211">--%>
<%--                    <span style="line-height:1" id="ApplyCount"></span><span style="line-height:1" id="ApplyMoney"></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="container11con22" style="width:380px;height:30px">--%>
<%--                <div class="container11con212">--%>
<%--                    <span style="line-height:3">入库次数/金额</span>--%>
<%--                </div>--%>
<%--                <div class="container11con212">--%>
<%--                    <span style="line-height:3">申领次数/金额</span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="container11con23" style="width:380px;height:30px">--%>
<%--                <div class="container11con213">--%>
<%--                    <span style="line-height:2" id="ApplyCount1"></span><span style="line-height:2" id="ApplyMoney1"></span>--%>
<%--                </div>--%>
<%--                <div class="container11con213">--%>
<%--                    <span style="line-height:2" id="RetreatCount"></span><span style="line-height:2" id="RetreatMoney"></span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <div class="container11con24" style="width:380px;height:30px">--%>
<%--                <div class="container11con214">--%>
<%--                    <span style="line-height:4">申领次数/金额</span>--%>
<%--                </div>--%>
<%--                <div class="container11con214">--%>
<%--                    <span style="line-height:4">退款次数/金额</span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div id="container12" style="width:805px;height:200px;margin-top: -20px">--%>
<%--        <div class="container12Top">--%>
<%--            <img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--            <span class="container12Top1" style="line-height:1"><font color="#FFFFFF">新老客户构成</font></span>--%>
<%--            <div class="container12Rightcon21">--%>
<%--                <span class="container12Leftcon211"><a onclick="initDataOleNew(0)">本月</a></span>--%>
<%--                <span class="container12Leftcon211"><a onclick="initDataOleNew(1)">上月</a></span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <div class="container12Bottom">--%>
<%--            <table class="table invoice-table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th style="text-align:center">   </th>--%>
<%--                    <th style="text-align:center">付款金额</th>--%>
<%--                    <th style="text-align:center">较前一月</th>--%>
<%--                    <th style="text-align:center">付款人数</th>--%>
<%--                    <th style="text-align:center">较前一月</th>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td style="text-align:center">新客户</td>--%>
<%--                    <td style="text-align:center"><span id="NewSumMoney"></span></td>--%>
<%--                    <td style="text-align:center"><span id="compare1"></span></td>--%>
<%--                    <td style="text-align:center"><span id="NewSumCount"></span></td>--%>
<%--                    <td style="text-align:center"><span id="compare2"></span></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td style="text-align:center">老客户</td>--%>
<%--                    <td style="text-align:center"><span id="OldSumMoney"></span></td>--%>
<%--                    <td style="text-align:center"><span id="compare3"></span></td>--%>
<%--                    <td style="text-align:center"><span id="OldSumCount"></span></td>--%>
<%--                    <td style="text-align:center"><span id="compare4"></span></td>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--    <div id="container13" style="width:805px;height:200px;margin-top: -20px">--%>
<%--        <div class="container13Top">--%>
<%--            <img width="30px" height="28px" src="../../../dentistmall/css/shopping/images/container4Top1.png">--%>
<%--            <span class="container13Top1" style="line-height:1"><font color="#FFFFFF">企业机构统计</font></span>--%>
<%--            <!-- <div class="container13Table">--%>
<%--             <table class="table invoice-table">--%>
<%--                                <thead>--%>
<%--                                <tr>--%>
<%--                                  <th style="text-align:center">序号</th>--%>
<%--                                    <th style="text-align:center">集团</th>--%>
<%--                                      <th style="text-align:center">医院</th>--%>
<%--                                    <th style="text-align:center">日期</th>--%>
<%--                                    <th style="text-align:center">购买数量</th>--%>
<%--                                    <th style="text-align:center">交易数量</th>--%>
<%--                                    <th style="text-align:center">产品分类</th>--%>
<%--                                    <th style="text-align:center">平均单价</th>--%>
<%--                                    <th style="text-align:center">总金额</th>--%>
<%--                                    <th style="text-align:center">所属业务员</th>--%>
<%--                               </tr>--%>
<%--                              </thead>--%>
<%--                                  <tbody id="mxListAll">--%>
<%--                              </tbody>--%>
<%--             </table>--%>
<%--             </div> -->--%>
<%--        </div>--%>
<%--    </div>--%>
    <!-- 最后一级DIV -->
</div>
</body>


</html>
