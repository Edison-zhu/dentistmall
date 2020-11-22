<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2019/12/26
  Time: 22:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp" %>
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
    .shopping-cart-content .hd .hd-right label {
        display: inline-block;
        color: white;
        font-weight: bold;
        width: auto;
        margin-left: 0px;
    }
    .shopping-cart-content .hd{
        width: 1080px;
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
<link rel="stylesheet" href="<c:url value='/jsp/util/orderlistutil.css?v=3' />">
<p class="bread-crumb w1080">
    <i class="icon-homepage"></i>
    <label>
        <a href="index.htm">
            首页
        </a>
    </label>
    <label>
        我的售后
    </label>
</p>
<div class="content">
    <div class="shopping-cart-body w1080">
        <div class="shopping-cart-header">
            <div class="shopping-flow">
                <div class="shopping-cart-icon">
                    <img src="<c:url value='/css/shopping/images/shopping-order.png'/>">
                    <span class="cn">我的售后:</span>
                    <span class="un">Query Order</span>
                    <a class="taps" href="qhtx.htm">缺货提醒<strong id="newsCount"></strong></a>
                    <a class="taps" href="scj.htm">我的收藏夹</a>
                    <a class="taps" href="qgc.htm">弃购车</a>
                    <a class="taps active" href="ddlb.htm">查询订单</a>
                    <a class="taps" href="zhsz.htm">账号设置</a>
                </div>
            </div>
        </div>
    <div class="shopping-cart-body w1080">
        <div class="shopping-cart-content">
            <div class="content-taps" style="display: block">
                <ul id="statetable">
                    <li><a id="_item1" class="clickA taps-item-active" onclick="changeType('','syas')">所有售后单(<strong
                            id="allCount">0</strong>)</a></li>
                    <li><a id="_item2" class="clickA taps-item" onclick="changeType('1','th')">退货(<strong
                            id="thCount">0</strong>)</a>
                    </li>
                    <li><a id="_item3" class="clickA taps-item" onclick="changeType('2','tk')">退款(<strong
                            id="tkCount">0</strong>)</a>
                    </li>
                    <li><a id="_item4" class="clickA taps-item" onclick="changeType('3','wc')">售后完成(<strong
                            id="wcCount">0</strong>)</a>
                    </li>
                </ul>

            </div>
            <div class="ddBatchManage form-inline">
                <input type="text" id="searchDdName" name="searchDdName" class="form-control"
                       placeholder="请输入售后单号或者商品名称"/>
                <input type="text" id="searchDdStartTime" name="searchDdStartTime" class="form-control"
                       placeholder="请选择售后创建时间范围" style="width: 240px;"/>
                <select id="searchDDPayType" class="form-control" placeholder="选择售后服务">
                    <option value="">选择售后服务</option>
                    <option value="1">退货</option>
                    <option value="2">退款</option>
                    <option value="3">售后完成</option>
                </select>
                <select id="searchDDState" class="form-control">
                    <option value="">选择售后状态</option>
                    <option value="1">退货中</option>
                    <option value="2">退款中</option>
                    <option value="3">售后成功</option>
                    <option value="4">售后拒绝</option>
                    <option value="5">申请中</option>
                    <option value="6">已撤销申请</option>
                </select>
                <button id="searchDdBtn" class="btn btn-primary" onclick="searchDd()">搜索</button>
                <button id="" class="btn btn-white" onclick="resetSearch()">重置</button>
                <button id="prePageDD" class="btn btn-primary">上一页</button>
                <button id="nextPageDD" class="btn btn-primary">下一页</button>
            </div>
            <div class="hd">
                <table style="width: 100%;">
                    <tr>
                        <th style="width: 44%">
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
                        <th class="td-text-align" style="width: 14%">
                            <div style="text-align: left;padding-left: 28px;">
                                 <span class="hd-middle">
                                <label>数量</label>
                                 </span>
                            </div>
                        </th>
<%--                        <th class="td-text-align" style="width: 7%">--%>
<%--                            <div>--%>
<%--                                <span class="hd-middle">--%>
<%--                                    <label>售后服务</label>--%>
<%--                                </span>--%>
<%--                            </div>--%>
<%--                        </th>--%>
                        <th class="td-text-align" style="width: 10%">
                            <div style="display: inline;width: 60%;">
                                <div class="hd-right" style="display: inline;">
                                    <%--                    <label>单位</label>--%>
                                    <label>实付款</label>
                                </div>
                            </div>
                        </th>
                        <th class="td-text-align" style="width: 10%">
                            <div style="display: inline;width: 60%;">
                                <div class="hd-right" style="display: inline;">
                                    <label>售后状态</label>
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
                <%@include file="/jsp/util/aftersale/orderaslistutil.jsp" %>
            </div>
        </div>
    </div>
</div>
<div class="pagination w1080 m-pagenum pagination" id="Pagination"></div>
    <%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<input type="hidden" id="moiId" value="${moiId}" />
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/orderaftersale/orderas.js?v=3' />"></script>