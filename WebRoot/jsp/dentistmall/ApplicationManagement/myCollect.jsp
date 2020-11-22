<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
</head>
<style>
    .scjCount {
        font-size: 20px;
        padding-left: 2%;
        margin-top: 2%;
    }

    .scjCount span {
        font-size: 20px;
        padding-left: 1%;
    }

    #scjCount {
        font-size: 20px;
        padding-left: 5px;
        padding-right: 5px;
    }

    .scjBatchManage {
        font-size: 16px;
        text-align: center;
        float: right;
        position: absolute;
        top: 15px;
        right: 2%;
    }

    .scjBatchManage input {
        padding-top: 4px;
    }

    .split {
        font-weight: bold;
        margin-left: 5px;
        margin-right: 2px;
    }

    .checkscjImg {
        margin-top: 6%;
        float: right;
        position: absolute;
        right: 2%;
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

    .newbtn {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 105px;
        height: 30px;
        border: 1px solid #1ab394;
        font-size: 14px;
        border-radius: 15px;
        margin-right: 20px;
        margin-left: 5px;
        outline: none;
    }

    .newbtn2 {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 80px;
        height: 30px;
        border: 1px solid #1ab394;
        background: #1ab394;
        font-size: 14px;
        border-radius: 15px;
        color: #fff;
        margin-left: 5px;
        outline: none;
    }

    .newbtn3 {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 180px;
        height: 30px;
        border: 1px solid #1ab394;
        font-size: 14px;
        border-radius: 15px;
        margin-left: 20px;
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
</style>
<script>
    var _isFix = 1;
    var _isCar = 2;
</script>
</head>
<body>
<header class="header" style="height: 132px">
    <%@ include file="/jsp/dentistmall/ApplicationManagement/myCollect2.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?9' />">
<p class="bread-crumb" style="padding: 0 30px;">
    <i class="icon-homepage"></i>
    <label>
        我的收藏夹
    </label>
</p>
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
<div class="content">
    <div class="shopping-cart-body" style="padding: 0 30px;">
        <div class="shopping-cart-content">
            <div class="scjCount" style="display: flex;">
                全部商品(<span id="scjCount">0</span>)<span>|</span>
                <a class="newbtn3" href="#" onclick="clearscjSelectMats1()">一键移除商品</a>
                <a class="newbtn3" href="#" onclick="oneClickJoin()">一键加入购物车</a>
            </div>
            <div class="scjBatchManage form-inline" style="display: flex;top:30px;">
                <span id="showHideManage" style="display: none;">
                    <input type="checkbox" onclick="selectscjAllMats()" value="全选"/><span>全选</span><span
                        class="split">|</span>
                    <a href="#" onclick="clearscjSelectMats()" style="text-decoration: underline;color: #1E90FF;">移除</a>
                </span>
                <button id="bacthscjManageBtn" class="newbtn" onclick="batchscjManageClick()" disabled
                        style="margin-left: 20px;">批量管理
                </button>
                <div style="display: flex">
                    <input type="text" id="searchScjName" name="searchScjName" class="form-control"
                           placeholder="请输入关键字进行搜索"/>
                    <button id="searchScjBtns" class="newbtn2" onclick="searchScj()">搜索</button>
                </div>
            </div>
            <div class="hd" style="margin-top:20px;width: 100%;">
                <%--                <div class="hd-left" style="width: 100%">--%>
                <div style="width: 5%;text-indent: 10px;display: inline-block;font-size: 14px;color: #fff">
                    <input type="checkbox" class="loginName" onclick="exportItem(this,-1)" id="all">
                    <label for="">全选</label></div>
                <div style="width: 45%;text-indent: 50px;display: inline-block;font-size: 14px;color: #fff">物料信息</div>
                <%--                </div>--%>
                <!--2020年07月03日11:26:38 朱燕冰修改调整table组件宽度 -->
                <!--2020年07月08日14:00 宋  已修改为百分比以适应不同屏幕大小 请勿再修改写死 请勿再修改写死 请勿再修改写死-->
                <%--                <div class="hd-right">--%>
                <div style="width: 15%;text-align: center;display: inline-block;font-size: 14px;color: #fff">单位</div>
                <div style="width: 15%;text-align: center;display: inline-block;font-size: 14px;color: #fff">品牌</div>
                <div style="width: 15%;text-align: center;display: inline-block;font-size: 14px;color: #fff">包装方式</div>
                <div style="width: 10%;text-align: center;display: inline-block;font-size: 14px;color: #fff">操作</div>
                <%--                </div>--%>
            </div>
            <div class="order" style="border-top:0px;margin-top:0px;width: 100%;">
                <div class="order-body" id="divList">
                </div>
            </div>
        </div>
    </div>
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
        <div style="line-height: 40px;margin-left: 30px; color: #fff;">别名设置<a style="margin-left: 80%;"
                                                                              onclick="hide2()" class="nclose">关闭</a>
        </div>
    </div>
    <div id="Editalias1List" style="padding: 20px;">
        <div style="width: 100%;height: 80px;">
            <div id="EditList"></div>
        </div>
        <div style="clear: both">
            <div>
                <div style="width: 100%;height: 160px;">
                    <div style="line-height: 20px;width: 100%;height: 40px"><span
                            style="margin-left: 69px">别名:</span><input id="AliasNameId" placeholder="最多可添加三个别名" value=""
                                                                       style="width: 378px;height: 30px;margin-left: 20px">
                        <div class="btn"><a class="search-button" onclick="addAliasName()">添加别名</a></div>
                    </div>
                    <div style="line-height: 40px;width: 100%;height: 40px;padding-left: 20px;" id="bmCount"></div>
                    <div style="line-height: 60px;width: 100%;height: 40px" id="bms"></div>
                </div>
                <div style="width: 100%;height: 115px;">
                    <div style="width: 100%;height: 0px;"><a class="search-button"></a></div>
                    <div style="margin-left: 80%" class="btn"><a class="search-button" onclick="success()">完成</a></div>
                </div>
            </div>
        </div>
        <div class="pagination w1080 m-pagenum pagination" id="Pagination" style="display: none"></div>
        <%--<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>--%>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/myCollect.js?v=100' />"></script>
<script>
    $("#searchScjName").on('keydown', function () {
        if (event.keyCode == 13) {
            console.log("111")
            $("#searchScjBtns").trigger("click");
            $('#showHideManage').hide();
            $('.checkscjOrderInfImg').hide();
            $('.checkscjOrderInfImgGray').hide();
            $('#bacthscjManageBtn').text('批量管理');
            $(".order-info").attr('style', 'height: 135px;background-color: rgba(192, 192, 192, 0);');
        }
    });
    $(".ibox").mouseover(function () {
        $(".ibox").show();
    }).mouseout(function () {
        $(".ibox").hide();
    });
</script>