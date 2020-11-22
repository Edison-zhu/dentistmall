<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp" %>
    <link rel="stylesheet" href="<c:url value='/css/shopping/css/confirmOrder.css?v=57' />">
</head>
<style>
    .ddBatchManage {
        display: block;
        font-size: 16px;
        text-align: right;
        margin-right: 2%;
        margin-bottom: 10px;
        float: right;
        clear: both;
        /*position: absolute;*/
        /*top: 15px;*/
        /*right: 2%;*/
    }

    .ddBatchManage input {
        padding-top: 4px;
    }

    .name {
        width: 300px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .type {
        width: 300px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .highLight {
        color: red;
    }

    #statetable li {
        /*padding: 7px 10px 1px 10px;*/
        /*border-right: #c8c8c8 solid 1px;*/
        float: left;
        display: block;
    }

    #statetable li a {
        display: block;
    }
</style>
<script>
    var _isFix = 1;
    var _isCar = 2;
</script>
</head>
<body>
<header class="header">
    <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?11' />">
<link rel="stylesheet" href="<c:url value='/jsp/util/orderlistutil.css?v=6' />">
<p class="bread-crumb w1080">
    <i class="icon-homepage"></i>
    <label>
        <a href="index.htm">
            首页
        </a>
    </label>
    <label>
        我的订单
    </label>
</p>
<div class="content">
    <div class="shopping-cart-body w1080">
        <div class="shopping-cart-header">
            <div class="shopping-flow">
                <div class="shopping-cart-icon">
                    <img src="<c:url value='/css/shopping/images/shopping-order.png'/>">
                    <span class="cn">我的订单:</span>
                    <span class="un">Query Order</span>
                    <a class="taps" href="qhtx.htm">缺货提醒<strong id="newsCount"></strong></a>
                    <a class="taps" href="scj.htm">我的收藏夹</a>
                    <a class="taps" href="qgc.htm">弃购车</a>
                    <a class="taps active" href="ddlb.htm">查询订单</a>
                    <a class="taps" href="zhsz.htm">账号设置</a>
                </div>
            </div>
        </div>
        <div class="shopping-cart-content">
            <div class="content-taps" style="display: block">
                <%--                <a class="taps-item-active" id="_item" onClick="changeType('','sydd')">所有订单<strong--%>
                <%--                        id="allCount"></strong></a>--%>
                <%--                <a class="taps-item" id="_item1" onClick="changeType('1','dfh')">待发货<strong id="dfhCount"></strong></a>--%>
                <%--                <a class="taps-item" id="_item2" onClick="changeType('2','dsh')">待收货<strong id="dshCount"></strong></a>--%>
                <ul id="statetable">
                    <%--                    <tr>--%>
                    <!-- 除了第一个方法不用传参外，剩余的都需要传参，待定参数 -->
                    <li><a id="_item1" class="clickA taps-item-active" onclick="changeType('','sydd')">所有订单(<strong
                            id="allCount">0</strong>)</a></li>
                    <li><a id="_item2" class="clickA taps-item" onclick="changeType('2','dfk')">待付款(<strong
                            id="dfkCount">0</strong>)</a>
                    </li>
                    <li><a id="_item3" class="clickA taps-item" onclick="changeType('1','dfh')">待发货(<strong
                            id="dfhCount">0</strong>)</a>
                    </li>
                    <li><a id="_item4" class="clickA taps-item" onclick="changeType('3','dsh')">待收货(<strong
                            id="dshCount">0</strong>)</a>
                    </li>
                    <li><a id="_item5" class="clickA taps-item" onclick="changeType('4','bfh')">部分发货(<strong
                            id="bfhCount">0</strong>)</a>
                    </li>
                    <li><a id="_item7" class="clickA taps-item" onclick="changeType('5','succ')">交易成功(<strong
                            id="succCount">0</strong>)</a></li>
                    <li><a id="_item8" class="clickA taps-item" onclick="changeType('6','fail')">交易关闭(<strong
                            id="failCount">0</strong>)</a></li>
                    <li><a id="_item6" class="clickA taps-item" onclick="changeType('7','sh')">售后(<strong
                            id="shCount">0</strong>)</a></li>
                    <%--                    </tr>--%>
                </ul>

            </div>
            <div class="ddBatchManage form-inline">
                <%--                <div style="display: inline">--%>
                <input type="text" id="searchDdName" name="searchDdName" class="form-control"
                       placeholder="请输入订单号或者商品名称"/>
                <input type="text" id="searchDdStartTime" name="searchDdStartTime" class="form-control"
                       placeholder="请选择下单时间范围" style="width: 240px;"/>
                <select style="display: none" id="searchDDPayType" class="form-control" placeholder="选择支付方式">
                    <option value="">选择支付方式</option>
                    <option value="1">支付宝</option>
                    <option value="2">微信</option>
                    <option value="3">月结</option>
                </select>
                <select id="searchDDState" class="form-control">
                    <option value="">选择订单状态</option>
                    <option value="1">待发货</option>
                    <option value="2">待付款</option>
                    <option value="3">待收货</option>
                    <option value="4">部分发货</option>
                    <option value="5">交易成功</option>
                    <option value="6">交易关闭</option>
                    <%--                    <option value="7">售后</option>--%>
                </select>
                <button id="searchDdBtn" class="btn btn-primary" onclick="searchDd()">&nbsp;&nbsp;&nbsp;搜索&nbsp;&nbsp;&nbsp;</button>
                <button id="" class="btn btn-white" onclick="resetSearch()">&nbsp;&nbsp;&nbsp;重置&nbsp;&nbsp;&nbsp;</button>
                <%--                    <div style="display: inline-block;">--%>
                <button id="prePageDD" class="btn btn-primary">上一页</button>
                <button id="nextPageDD" class="btn btn-primary">下一页</button>
                <%--                    </div>--%>
                <%--                </div>--%>
            </div>

            <div class="hd">
                <table style="width: 100%;">
                    <tr>
                        <th style="width: 35%">
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
                        <th class="td-text-align" style="width: 7%">
                            <div>
                                <span class="hd-middle">
                                    <label>售后状态</label>
                                </span>
                            </div>
                        </th>
                        <th class="td-text-align" style="width: 7%">
                            <div>
                                <%--                    <label>单位</label>--%>
                                <span class="hd-middle">
                                    <label>实付款</label>
                                </span>
                            </div>
                        </th>
                        <th class="td-text-align" style="width: 10%">
                            <div>
                                <span class="hd-middle">
                                    <label>交易状态</label>
                                 </span>
                            </div>
                        </th>
                        <th class="td-text-align" style="width: 10%">
                            <div>
                                <span class="hd-middle">
                                    <label>操作</label>
                                </span>
                            </div>
                        </th>
                    </tr>
                </table>
            </div>
            <div class="export" style="margin-left:33px;margin-top:15px;">
     			<span>
     				<input type="checkbox" class="loginName" onclick="exportItem(this,-1)" id="all">
     			</span>
                <span>
     				全选/反选
     			</span>
                <span>
     				<a onclick="export_batch_order()" class='btn btn-success  btn-xs' style="color:#fff" id="export1">批量导出订单信息</a>	
     				<a onclick="export_batch_order1()" class='btn btn-success  btn-xs' style="color:#fff" id="export1">批量导出对账单信息</a>
     			</span>
            </div>
            <div id="divList">
                <%@include file="/jsp/util/orderlistutil.jsp" %>
            </div>
        </div>
    </div>
</div>
<div class="pagination w1080 m-pagenum pagination" id="Pagination"></div>
<div class="u-mask"  id="addressId"></div>
<div class="g-login" style="width:500px" id="addressInfo">
    <a class="open-close" id="addressClose">关闭</a>
    <div class="wrap" style="border: 0px solid #FF5F0F">
        <form id="accountForm" onkeydown="if(event.keyCode==13){return false;}">
            <input type="hidden" id="mdaId" name="mdaId" />
            <input type="hidden" id="suiId" name="suiId"/>
            <input type="hidden" id="ifDefault" name="ifDefault" value="2" />
            <div class="control-group">
                <div class="hd">
                    安全码：
                </div>
                <div class="bd">
                    <input type="password" class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" id="securityCode" name="securityCode">
                </div>
            </div>
            <div class="control-bottom clearfix" style="text-align:center">
                <a href="javascript:checkSecurityCodeFunc()" class="fl btn ui-btn-theme" style="color:#fff;" >确认</a>
            </div>
        </form>
    </div>
</div>
    <%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/ddlb.js?51' />"></script>