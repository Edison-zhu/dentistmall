<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp"%>
    <%@ include file="/jsp/link.jsp" %>
</head>
<style>
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
</style>
<script>
    var _isFix=1;
    var _isCar=2;
</script>
</head>
<body>
<header class="header"style="height: 132px">
    <%@ include file="/jsp/dentistmall/ApplicationManagement/myCollect2.jsp" %>
</header>
<div>
    <link rel="stylesheet" href="<c:url value='/css/style.css?46' />">
    <div class="col-sm-2" style="position: absolute;left: 44px;top:127px;width: 261px;z-index:1000;">
        <div class="ibox float-e-margins" style="margin-left: -31px;display: none;border:1px solid #EBEBEB" id="ibox">
            <div class="ibox-content">
                <div class="scrollbarlist">
                    <div class="panel-body">
                        <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                    </div>
                </div>
            </div>
            <!-- 操作区域 end-->
        </div>
    </div>
</div>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/shoppingCart.css?v=15' />">
<%--<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?9' />">--%>
<p class="bread-crumb" style="padding: 0 30px;">
    <i class="icon-homepage"></i>

    <label>清单确认</label>
</p>
<div class="content">
    <div class="shopping-cart-body" style="padding: 0 30px;">

        <div class="shopping-cart-content">
            <div class="hd" style="width: 100%;">
                <div class="hd-left" style="width: 50%;float: left;">
                    <input type="checkbox" checked="checked" id="all1" onclick="checkAll(this)">
                    <label for="">全选</label>
                    <label class="l170">物料信息</label>
                </div>
                <div style="width: 50%;float: left;">
                    <div style="width: 20%;text-align: center;float: left;color: #fff;">库存数量</div>
                    <div style="width: 30%;text-align: center;float: left;color: #fff;">包装方式</div>
                    <div style="width: 30%;text-align: center;float: left;color: #fff;">申请数量</div>
                    <div style="width: 20%;text-align: center;float: left;color: #fff;">操作</div>
                </div>
            </div>
            <div id="gwcList" style="width: 100%;">

            </div>
            <div class="ft" style="width: 100%;">
                <input type="checkbox" checked="checked" id="all2" onclick="checkAll(this)">
                <label for="">全选</label>
                <a  class="delete font-weight-trigger" href="javascript:delSel()">删除所选</a>

                <p class="allOrderInfo" style="margin-right: 400px;">

                    <span>您共计申请：<strong id="wmsIdNumber">0</strong>件商品，<strong id="mmfIdNumber">0</strong>种型号规格，共计数量<strong id="allShu">0件</strong></span>
                </p>
            </div>
            <div class="" style="width:100%;height: 60px;margin-left: 20px">
                <div class="" style="margin-left: 20px;float: left;line-height: 60px">申请留言:</div>
                <input class="" id="remarks" value="" style="margin-left: 20px;float: left;width: 90%;height: 60px;border: 1px silver solid;" placeholder="50个字以内"/>
            </div>
            <div class="" style="width:100%;height: 50px;margin-left: 20px;margin-top: 20px">
                <button style="float: right;margin-right: 10%" id="" class="btn btn-success" onclick="SubmitDetailed()">立即提交清单</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/myWuliaoCar.js?v=101' />"></script>
<%--<script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/myCollect.js?v=70' />"></script>--%>
<script>
    $(".ibox").mouseover(function (){
        $(".ibox").show();
    }).mouseout(function (){
        $(".ibox").hide();
    });

</script>