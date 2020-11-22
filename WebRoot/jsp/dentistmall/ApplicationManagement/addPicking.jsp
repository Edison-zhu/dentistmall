<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <%--    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">--%>
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
</head>
<script>
    var _isFix = 1;
    var _isCar = 1;
</script>
</head>
<style>
    #ibox {
        display: none;
    }

    .highLight {
        color: red;
    }

    .ahover {
        text-decoration: underline;
        padding: 5px 10px;
    }

    .ahover:hover {
        cursor: pointer;
    }

    .btn a {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100px;
        height: 30px;
        border: 1px solid #1ab394;
        font-size: 14px;
        border-radius: 15px;
    }
    .addclass{
        color: red;
    }

</style>
<body class="test" style="overflow-x: auto;">
<div style="position: fixed;top: 0;z-index: 100000;height: 50px;padding:0 30px;margin-bottom:10px">

        <%@ include file="/jsp/dentistmall/ApplicationManagement/shopheads.jsp" %>
</div>

<div class="search-filter" style="padding:0 30px;">
    <div class="col-sm-2" style="position: absolute;left: 16px;top:83px;width: 261px;z-index:1000;">
        <div class="ibox float-e-margins" style="margin-left: -31px;display: none;border:1px solid #EBEBEB" id="ibox">
            <div class="ibox-content" style="height: 400px">
                <div class="scrollbarlist">
                    <div class="panel-body">
                        <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                    </div>
                </div>
            </div>
            <!-- 操作区域 end-->
        </div>
    </div>
    <div style="padding:0 30px;margin-bottom:10px;margin-top: -51px;height: 50px;background-color: white;z-index: 9999"
         class="topheader">
        <div style="line-height: 50px">
            <a id="brand0" href="javascript:brandSearch1('', 0)">全部</a>
            <a id="brand1" style="margin-left: 30px" href="javascript:brandSearch1('brand1', 1)">麦迪康</a>
            <a id="brand2" style="margin-left: 15px" href="javascript:brandSearch1('brand2', 2)">登士柏</a>
            <a id="brand3" style="margin-left: 15px" href="javascript:brandSearch1('brand3', 3)">美国3M</a></div>
    </div>
    <div class="" style="width: auto">
        <section class="new-product" style="background-color: white;">
            <ul class="flex product-lists" id="contentList">
            </ul>
        </section>
    </div>
    <div class="pagination m-pagenum pagination" id="Pagination">

    </div>
    <form id="searchForm">
        <input type="hidden" name="mmtId" id="mmtId" value="${mmtId}"/>
        <input type="hidden" name="isDefault" id="isDefault" value="1"/>
        <input type="hidden" name="numOrder" id="numOrder" value="0"/>
        <input type="hidden" name="priceOrder" id="priceOrder" value="0"/>
        <input type="hidden" name="searchName" id="searchLBName" value="${searchName}"/>
        <input type="hidden" name="brand" id="brand" value=""/>
    </form>
    <div class="" id="hide1"
         style="width: 1080px;height: 1340px;position:absolute;top:138px;left:190px;background-color: white;z-index: 9999;display: none">
    </div>
    <style>
        .nclose {
            color: #fff;
        }

        .nclose:hover {
            cursor: pointer;
            color: #fff;
        }
    </style>
    <div id="Editalias1"
         style="width: 675px;height: 400px;background-color: white;border-radius:2px 2px 2px 2px;border: 1px solid #1ab394;position: fixed;left: 300px;top: 240px;display: none;z-index: 900">
        <div style="background-color:#1ab394;width: 100%;height: 10%">
            <div style="line-height: 40px;margin-left: 30px;color: #fff;">别名设置<a style="margin-left: 80%;" onclick="hide2()" class="nclose">关闭</a>
            </div>
        </div>
        <div id="Editalias1List" style="padding: 20px;">
            <div style="width: 100%;height: 80px;">
                <div id="EditList"></div>
            </div>
            <div style="clear: both">
                <div>
                    <div style="width: 101%;height: 160px;">
                        <div style="line-height: 20px;width: 100%;height: 40px"><span style="margin-left: 69px">别名:</span><input id="AliasNameId" placeholder="最多可添加三个别名" value="" style="width: 378px;height: 30px;margin-left: 20px">
                            <div class="btn"><a class="search-button" onclick="addAliasName()">添加别名</a></div></div>
                        <div style="line-height: 40px;width: 100%;height: 40px;padding-left: 0px;margin-top: 10px" id="bmCount"></div>
                        <div style="line-height: 25px;width: 100%;height: 40px;margin-top: 20px" id="bms"></div>
                    </div>
                    <div style="width: 100%;height: 115px;">
                        <div style="width: 100%;height: 5px;"><a class="search-button"></a></div>
                        <div style="margin-left: 80%" class="btn"><a class="search-button" onclick="success()">完成</a>
                        </div>
                    </div>
                </div>
            </div>
</body>
</html>
<script src="<c:url value='/js/shopping/layer/layer.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/shopping/ui.js?10' />"></script>
<script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/addPicking.js?162' />"></script>
<script>

    $(".ibox").mouseover(function () {
        $(".ibox").show();
    }).mouseout(function () {
        $(".ibox").hide();
    });
</script>