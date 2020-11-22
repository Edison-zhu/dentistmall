<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>My JSP 'homePageWarehouse.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?2' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=65' />"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">
    <style>
        .box1 {
            width: 1200px;
            margin: 30px auto 0 auto;
            display: flex;
            justify-content: flex-start;
            align-content: flex-start;
        }

        .box1 .img {
            width: 300px;
            height: 300px;
        }

        .box1 .textbox {
            margin-left: 30px;
        }

        .box1 .textbox .text {
            font-size: 14px;
            line-height: 40px;
        }

        .box2 {
            width: 1200px;
            margin: 30px auto 0 auto;
            padding-left: 330px;
            min-height: 30px;
        }

        .box2 .item {
            border: 1px solid #F0F0F0;
            background-color: white;
            width: auto;
            height: 30px;
            float: left;
            margin-right: 20px;
            margin-top: 10px;
            padding: 0 10px;
            line-height: 30px;
        }

        .box3 {
            width: 1200px;
            margin: 30px auto 0 auto;
            padding-left: 330px;
            min-height: 50px;

        }

        .box3 .xuanze {
            color: #2894FF;
            margin-bottom: 30px;
            margin-top: 10px;
        }

        .box3 .btns {

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

        .hot-search-title {
            float: left;
            margin: 0 auto;
            top: 43px;
            display: inline-block;
            text-align: center;
            width: 50px;
            height: 22px;
            overflow: hidden;
            text-overflow: ellipsis;
            z-index: 8;
            /*white-space: nowrap;*/
        }

        .input-container {
            z-index: 99;
        }

        .hidden-search {
            display: none;
            float: left;
            margin: 0 auto;
            top: 40px;
            background-color: white;
            height: auto;
            overflow-y: auto;
        }

        .hidden-search ul {
            width: 100%;
            border: 1px solid #979797;
            box-sizing: border-box;
        }

        .hidden-search ul li {
            color: #7a77c8;
            cursor: pointer;
            box-sizing: border-box;
        }

        .hidden-search ul li:hover {
            background-color: lightgrey;
            color: white;
        }

        .hidden-search ul a {
            display: block;
            width: 100%;
            height: 100%;
            padding-left: 30px;
        }

        .select-box-div:hover {
            cursor: pointer;
        }

        .header-middle-container-content {
            width: 1080px;
            display: flex;
            justify-content: center;
            margin: 0 auto;
        }

        /* 新顶部样式 */
        .newtop {
            display: flex;
            justify-content: space-between;
            align-items: center;
            border: 1px solid #EBEBEB;
            margin: 40px 30px;
            padding: 10px 0 7px 0;
        }

        .newtop .form {
            padding-left: 20px;
            display: flex;
            justify-content: flex-start;
            align-items: center;
        }

        .newtop .form .tit {
            font-size: 14px;
            color: #333333;
            margin-right: 10px;
        }

        .newtop .form .input {

        }

        .newtop .form .input input {
            width: 420px;
            height: 30px;
            outline: none;
            border: 1px solid #EBEBEB;
            font-size: 13px;
            color: #666;
            padding-left: 5px;
        }

        .newtop .form .btn {

        }

        .newtop .form .btn a {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100px;
            height: 30px;
            border: 1px solid #1ab394;
            font-size: 14px;
            border-radius: 15px;
        }

        .newtop .itemssbox {
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .newtop .itemss {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100px;
            height: 30px;
            background: #1ab394;
            font-size: 14px;
            border-radius: 15px;
            margin-right: 20px;
        }

        .newtop .itemss:hover {
            cursor: pointer;
        }

        .newtop .itemss a {
            color: #fff;
        }

        .newtop .itemss span {
            width: 18px;
            height: 18px;
            text-align: center;
            line-height: 18px;
            position: absolute;
            top: -5px;
            right: -5px;
            display: block;
            border: 1px solid #ed5565;
            border-radius: 50%;
            color: #ed5565;
            font-size: 12px;
            font-weight: bold;
        }

        .newtop .posre {
            position: relative;
        }

        /*.submitbox{*/
        /*    display: flex;*/
        /*    justify-content: flex-end;*/
        /*    align-items: center;*/
        /*}*/
        /*.submitbox button{*/
        /*    display: flex;*/
        /*    justify-content: center;*/
        /*    align-items: center;*/
        /*    width: 200px;*/
        /*    height: 30px;*/
        /*    border: 1px solid #1ab394;*/
        /*    font-size: 14px;*/
        /*    border-radius: 15px;*/
        /*    margin-right: 200px;*/
        /*    color:#fff;*/
        /*    background:#1ab394;*/
        /*}*/
        .newbtn {
            display: flex;
            justify-content: center;
            align-items: center;
            width: 150px;
            height: 30px;
            border: 1px solid #1ab394;
            font-size: 14px;
            border-radius: 15px;
            margin-right: 20px;
            margin-left: 5px;
            color: #333;
        }

        .newbtn:hover {
            text-decoration: none;
        }

        .product-info-choose-count {
            bottom: 90px;
        }

        .product-info-choose-count input {
            background: #fff;
            border: 1px solid #ccc;
            padding: 5px 8px;
            outline: none;
        }

        .product-info-choose-count input.product-info-count {
            width: 50px;
            text-align: center;
            color: #0F0F0F;
        }

        .flexs {
            display: -webkit-flex;
            display: flex;
        }

        .w1081 {
            width: 1080px;
            margin: 0 auto;

        }

        .navs {
            height: 65px;
            background: url("../images/nav-background.png") no-repeat top left;
        }

        .product-categories > .product-categories-c {
            width: 208px;
            display: block;
            height: 65px;
            line-height: 65px;
            /*background: #145897;*/
            /*color: #ffffff;*/
            text-align: center;
            font-size: 16px;
            cursor: pointer;
        }

        .product-categories > .product-categories-a .icon-arrow-down-solid {
            color: #0e0d0d;
            font-weight: bold;
        }

        .category-ul-level-1 .category-p-level-1 a {
            font-size: 14px;
            color: black;
        }

        .category-ul-level-1 .category-p-level-1 {
            background-color: white;
            color: black;
            height: 35px;
            line-height: 35px;
            padding-left: 15px;
            cursor: pointer;
        }

        .category-ul-level-2 {
            width: 750px;
            position: absolute;
            top: 0;
            left: 199px;
            background-color: #fff;
            border: 1px solid #eee;
            border-left: none;
            display: none;
        }

        .category-list li a {
            color: #666666;
            display: block;
            padding: 5px 0;
        }

        .panel-category .category-list li:after {
            content: ' ';
            margin: 0 5px 0 10px;
        }

        .nav-lists > li > a {
            color: #666666;
            padding: 0 10px;
            font-size: 16px;
        }
    </style>
</head>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/font/font.css?02' />">
<script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/addPickingMxFindId.js?v=205'/>"></script>
<link rel="stylesheet" href="<c:url value='/css/homePageClaimant.css?v=8' />">
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
    function shoucangs() {
        var att_url = $.supper("getServicePath", {
            "service": "",
            url: "/jsp/dentistmall/ApplicationManagement/myCollect.jsp"
        });
        $.supper("showTtemWin", {"url": att_url, "title": "我的收藏"});
    }

    function shenqings() {
        var att_url = $.supper("getServicePath", {
            "service": "",
            url: "/jsp/dentistmall/ApplicationManagement/picking.jsp"
        });
        $.supper("showTtemWin", {"url": att_url, "title": "我的申请单"});
    }

    function wuliaos() {
        var att_url = $.supper("getServicePath", {
            "service": "",
            url: "/jsp/dentistmall/ApplicationManagement/myWuliaoCar.jsp"
        });
        $.supper("showTtemWin", {"url": att_url, "title": "购物车"});
    }
</script>
<body style="background-color: white">

<div class="newtop">
    <form class="form">
        <nav class="navs">
            <div class="nav-info w1081 flexs">
                <div class="product-categories">
                    <b class="product-categories-c" id="_allType" style="margin-left: -30px">全部商品分类 <span
                            class="icon-arrow-down-solid"></span></b>
                    <div class="nav-category"
                         style="margin-left: -21px;background: white;border-width:1px;border-color: #f3f3f4">
                        <ul class="category-ul-level-1" id="sortList">
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <div class="input" id="input-container" class="input-container" style="margin-left: -800px">
            <input type="text" id="searchName" placeholder="商品物料名称、型号、别名、物料编码、物料名称的等首字母关键字"/>
        </div>
        <div class="btn"><a class="search-button" id="search" onclick="jump()">搜索</a></div>
    </form>
    <div class="itemssbox">
        <div class="itemss"><a onclick="shoucangs()">我的收藏</a></div>
        <div class="itemss"><a onclick="shenqings()">我的申请单</a></div>
        <div class="itemss posre"><a onclick="wuliaos()">购物车<span id="countCollects"></span></a></div>
    </div>
</div>
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
<div id="mx1" class="box1"></div>
<div id="mx2" class="box2"></div>
<div class="box2">
    <table id="gwcListTable">

    </table>
</div>
<div id="mx3" class="box3">
    <div id="mx311" style="display: none;">
        <div class="xuanze"><b><span>您共计采购<span id="mx31"></span>种型号规格</span></b></div>
    </div>
    <%--        style="display: none;"--%>
    <div id="mx312"></div>
    <%--        <input type="button" style="width: 100px;height: 30px;background-color: white;border: #F5F5F5 1px solid">--%>
    <div class="btns">
        <!--2020年07月03日11:04:58朱燕冰修改 -->
        <button style="margin-left: 30px;width:136px" class="btn btn-primary btn-sm" onclick="saveSalesman()">立即提交申领单
        </button>
        <button style="margin-left: 30px;width:136px" class="btn btn-primary btn-sm" onclick="addCar()">加入购物车</button>
        <button style="margin-left: 30px;width:136px" class="btn btn-primary btn-sm" onclick="returnPicking()">返回
        </button>
        <%--            <a class="newbtn"  onclick="returnPicking()">返回</a>--%>
    </div>
</div>
<div id="Editalias1"
     style="width: 675px;height: 400px;background-color: white;border-radius:2px 2px 2px 2px;border: 1px solid #1ab394;position: absolute;left: 300px;top: 240px;display: none;z-index: 900">
    <div style="background-color:#1ab394;width: 100%;height: 10%">
        <div style="line-height: 40px;margin-left: 30px;color: #fff;">别名设置<a style="margin-left: 80%;color: #fff;"
                                                                             onclick="hide2()">关闭</a></div>
    </div>
    <div id="Editalias1List" style="padding: 20px;">
        <div style="width: 100%;height: 80px;">
            <div id="EditList"></div>
        </div>
        <!-- 2020年07月03日09:42:57朱燕冰修改-->
        <div style="width: 100%;height: 180px;">
            <div style="line-height: 20px;width: 100%;height: 40px"><span
                    style="position:absolute;left: 89px;top: 156px">别名:</span><input id="AliasNameId"
                                                                                     placeholder="最多可添加三个别名" value=""
                                                                                     style="width: 378px;height: 30px;margin-left: 120px">
                <div class="btn"><a class="search-button" onclick="addAliasName()">添加别名</a></div>
            </div>
            <div style="line-height: 40px;width: 100%;height: 40px;padding-left: 0px;margin-top: 10px"
                 id="bmCount"></div>
            <div style="line-height: 25px;width: 100%;height: 40px;margin-top: 20px" id="bms"></div>
        </div>
        <div style="width: 100%;height: 115px;">
            <div style="width: 100%;height: 5px;"><a class="search-button"></a></div>
            <div style="margin-left: 80%" class="btn"><a class="search-button" onclick="success()">完成</a></div>
        </div>
    </div>
</div>
<div class="pagination" id="Pagination" style="margin-left: 600px;display: none"></div>
<div class="pagination" id="Pagination1" style="margin-left: 600px;display: none"></div>
<div class="pagination" id="Pagination2" style="margin-left: 600px;display: none"></div>

<div style="position: absolute;top: 296px;left: 508px">
    <a onmouseover="shoucang1()" onclick="shoucang2()">
        <div id="dv_1"
             style="width: 25px;height: 25px;background-color: #F0F0F0;border-radius: 5px 5px 5px 5px;margin-left: 19px;margin-top: 175px">
            <div class="icon-favorite" id="dv_2" style="text-align: center;line-height: 2">
            </div>
        </div>
    </a>
</div>
</body>

</html>
<script>
    $(document).ready(function () {
        //initSortList();
        $(".product-categories-c").bind("click", function (e) {
            $("#ibox").slideToggle();
            $(".category-p-level-1").removeClass("active");
            $(this).find("span").toggleClass("icon-arrow-down-solid icon-arrow-up-solid");
        });
    });
    $(".ibox").mouseover(function () {
        $(".ibox").show();
    }).mouseout(function () {
        $(".ibox").hide();
    });
    $("#searchName").on('keydown', function () {
        console.log("111")
        if (event.keyCode == 13) {
            $("#search").trigger("click");
        }
    });
</script>