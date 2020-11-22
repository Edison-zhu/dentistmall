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
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">
</head>
<link rel="stylesheet" href="<c:url value='/css/homePageClaimant.css?v=7' />">
<script src="<c:url value='/js/uitl/echarts.js'/>"></script>
<%--<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>

<link href="<c:url value='/css/font-awesome.min.css?v=4.3.0' />" rel="stylesheet">
<link href="<c:url value='/css/animate.css' />" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/css/shopping/css/common.css?46' />">
<link rel="stylesheet" href="<c:url value='/css/shopping/css/font/font.css?02' />">
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?11' />">
<link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
<script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
<script src="<c:url value='/js/bootstrap.min.js?v=3.4.0' />"></script>
<link href="<c:url value='/css/bootstrap.min.css?v=3.4.0' />" rel="stylesheet">

<link rel="stylesheet" href="<c:url value='/js/formSelects-v4.css?v=1'/>">
<script src="<c:url value="/js/plugins/layui/layui.js?v=1" /> "></script>
<script type="text/javascript">
    //需要先配置layui，并使用use方法，这是单独将formSelects模块加入
    layui.config({
        base: '../../../js/' //此处路径请自行处理
    }).extend({
        formSelects: 'formSelects-v4'
    });
</script>
<style>
    .tipbox {
        /*position: relative;*/
        /*bottom: 67px;*/
        /*top: 0px;*/
        /*right: 80px;*/
        /*color: whitesmoke;*/
        /*width: 300px;*/
        text-align: center;
        font-size: 12px;
        padding: 8px 12px;
        border-radius: 4px;
        background-color: #F2F2F2;
        z-index: 1000;
        display: none;
        white-space: nowrap;
        line-height: 20px;
    }
    .tipbox1 {
        position: relative;
        bottom: 67px;
        right: 80px;
        width: 300px;
    }

    .ddBatchManage {
        display: block;
        font-size: 16px;
        text-align: right;
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

    /* 新样式 */
    .mainbox {
        margin: 0 30px 30px 30px;
    }
    .mainbox a{cursor:pointer;}
    .mainbox a.active{color:#337ab7;}
    /*.mainbox a{*/
    /*    color: #337ab7;*/
    /*}*/
    .mainbox2 {
        margin: 0 30px 30px 30px;
        border-top: 1px solid #ededed;
    }

    .tabsbox {
        display: flex;
justify-content: flex-start;
align-items: center;
padding-top: 15px;
    }
    .tabsbox .item1{
        margin-right: 0px;
    }
    .tabsbox .item1 input{
        margin:0;
    }
    .tabsbox .item1 span{
        margin-left:5px;
    }
    .tabsbox .item2{
        margin-right: 10px;
    }
    .tabsbox .item2 .daochubtn {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 140px;
        height: 30px;
        border: 1px solid #1ab394;
        font-size: 14px;
        border-radius: 15px;
    }
    .tabsbox .item3{
        margin-right: 10px;
    }
    .tabsbox .item4{
        margin-right: 10px;
    }
    .tabsbox .item4 span{
        display: block;
        float: left;
        line-height: 36px;
        margin-right: 5px;
    }
    .tabsbox .item5{
        margin-right: 10px;
    }
    .tabsbox .item6{
        /*margin-right: 72px;*/
        display:flex;
       justify-content: flex-start;
        align-items: center;

    }
    .tabsbox .item6 .searchbtn{
         display: flex;
        justify-content: center;
        align-items: center;
        width: 100px;
        height: 30px;
        border: 1px solid #1ab394;
        font-size: 14px;
        border-radius: 15px;
        background: #1ab394;
        color:#fff;
        margin-right: 10px;
    }
    .tabsbox .item6 .resetbtn{
         display: flex;
        justify-content: center;
        align-items: center;
        width: 100px;
        height: 30px;
        border: 1px solid #1ab394;
        font-size: 14px;
        border-radius: 15px;
        background: #fff;
    }
    .tabsbox .item7{
        display:flex;
       justify-content: flex-start;
        align-items: center;
        margin-left: 0px;
    }
    .tabsbox .item7 .btnbtn{
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100px;
        height: 30px;
        border: 1px solid #1ab394;
        font-size: 14px;
        border-radius: 15px;
        background: #1ab394;
        color:#fff;
        /*margin-left: 10px;*/
    }
    /*.-caidan:before{*/
    /*    content:"";*/
    /*}*/
    /*.{*/
    /*    */
    /*}*/
    .rangeDate {
        position: fixed;
        top: 130px;
        left: 30%;
        /* float: left; */
        z-index: 4;
        background: white;
        border: 1px solid;
        box-shadow: 1px 0px 10px 3px;
        padding: 10px;
    }
    .newlistH{
        /*height: 180px;*/
        height: 139px;
    }
    .newlstW{
        width: 30%;
    }
    .newlstW2{
        width: 31%;
    }
    .newlstW3{
        width: 10%;
    }
    @media (min-width: 1601px){
        .newbody{
            background-color: white;overflow-y: auto;
            width: 1315px !important;
        }
    }
    @media (min-width: 1441px) and (max-width: 1600px) {
        .newbody{
            background-color: white;overflow-y: auto;
            width: 1315px !important;
        }
        .newlistH{
            /*height: 180px !important;*/
            height: 139px !important;
        }
    }
    @media (min-width: 1367px) and (max-width: 1440px) {
        .newbody{
            background-color: white;overflow-y: auto;
            width: 1315px !important;
        }
        .newlistH{
            /*height: 180px !important;*/
            height: 139px !important;
        }
    }
    @media (max-width: 1366px) {
        .newbody{
            background-color: white;overflow-y: auto;
            width: 1170px !important;
        }
        .newlistH{
            /*height: 180px !important;*/
            height: 139px !important;
        }
        .newlstW{
            width: 34% !important;
        }
        .newlstW2{
            width: 40% !important;
        }
        .newlstW3{
            width: 9% !important;
        }
    }

</style>

<body class="newbody">
    <header class="header"style="height: auto">
        <%@ include file="/jsp/dentistmall/ApplicationManagement/myCollect2.jsp" %>
<%--        <script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/myCollect.js?v=68' />"></script>--%>
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
    <link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?8' />">
    <div class="mainbox" id="mainbox" style="margin-top: 50px">
        <label>
            <span onclick="clickAll(1)" id="allI1"><a onclick="change(1)" id="allColor">全部</a><a id="a6">(</a><a id="countAll"onclick="change(1)"></a><a id="b6">)</a></span>

            <span style="margin-left: 30px">|</span>
            <span style="margin-left: 30px"onclick="clickAll(2)" id="allIn2"><a onclick="change(2)" id="allColor2">申请中</a><a id="a2">(</a><a id="countApplying"onclick="change(2)"></a><a id="b2">)</a></span>
            <span style="margin-left: 30px">|</span>
            <span style="margin-left: 30px"onclick="clickAll(3)" id="allIn3"><a onclick="change(3)"id="allColor3">部分出库</a><a id="a3">(</a><a id="countPartial"onclick="change(3)"></a><a id="b3">)</a></span>
            <span style="margin-left: 30px">|</span>
            <span style="margin-left: 30px"onclick="clickAll(4)"id="allI4"><a onclick="change(4)"id="allColor4">已完成</a><a id="a4">(</a><a id="countComplete"onclick="change(4)"></a><a id="b4">)</a></span>
            <span style="margin-left: 30px">|</span>
            <span style="margin-left: 30px"onclick="clickAll(5)"id="allI5"><a onclick="change(5)"id="allColor5">撤销<a id="a5">(</a><a id="countrevoke"onclick="change(5)"></a><a id="b5">)</a></a></span>
        </label>
    </div>

<!-- 2020年07月07日11:03:57朱燕冰修改-->
<div class="mainbox2" style="width:100%;">

    <div class="tabsbox">
        <div class="item1"><input type="checkbox" class="loginName" onclick="exportItem(this,-1)" id="all"><span>全选/反选</span></div>
        <div class="item2" style="margin-left: 10px"><a onclick="exportPick()" class='daochubtn' id="export1">批量导出物料清单</a></div>
        <form id="formAll">
        <div class="item3" style="float: left"><input type="text" style="width: 180px;" id="searchDdName" name="searchDdName" class="form-control" placeholder="申领单号或物料名称、规格" style="width: 240px;" /></div>
        <div class="item4" style="float: left"><span>申领时间</span>
            <input type="text" value="" id="searchDdStartTime" name="searchDdStartTime" class="form-control" placeholder="选择时间"  style="width: 225px;" type="text" readonly onclick="selectRangeDate()"/></div>
        <div class="item5" style="float: left">
            <select xm-select="selectState1"
                    class="form-control" placeholer="" xm-select-show-count="1"
                    name="selectState1" id="selectState1" xm-select-skin="primary" >
            </select>
        </div>
        </form>
        <div class="item6"><button id="searchDdBtn" class="searchbtn"
                                   onclick="initData()"  style="margin-left: 10px">&nbsp;&nbsp;&nbsp;搜索&nbsp;&nbsp;&nbsp;</button>
            <button id="" class="resetbtn"
                    onclick="resetSearch()">&nbsp;&nbsp;&nbsp;重置&nbsp;&nbsp;&nbsp;</button></div>
        <div class="item7">
            <button id="prePageDD" class="btnbtn">上一页</button>
            <button id="nextPageDD" class="btnbtn">下一页</button>
        </div>
    </div>

    <div style="margin-top: 20px;width: auto;">
        <div class="hd" style="background-color: #F0F0F0;height: 40px;margin-top: 5px;">
            <div style="line-height: 3;width: 100%;display: flex;">
                <div style='float: left;width:3%;'><div style="width: 13px;height: 13px;"></div></div>
                <div style="text-align: left;" class="newlstW2">物料信息</div>
                <div style="width: 10%;text-align: center;">申请数量</div>
                <div style="width: 10%;text-align: center;">出库数量</div>
                <div style="width: 10%;text-align: center;">差异数</div>
                <div style="width: 25%;text-align: center;">出库时间</div>
                <div style="width: 10%;text-align: center;">状态</div>
                <div style="width: 10%;text-align: center;">操作</div>
            </div>
        </div>
        <div class="hd1" id="mxList"></div>
        <div class="pagination" id="Pagination1" style="text-align: center;"></div>
    </div>
    <div class="pagination" id="Pagination2" style="margin-left: 40%;display: none;"></div>
</div>
<%-- </div> --%>
</body>

</html>
<script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/picking.js?v=229'/>"></script>
<script>
    //2020年07月02日20:34:48 朱燕冰修改
    function change(i){
        if ($('#all').prop('checked') == true){
            $('#all').prop('checked',false);
        }
            // checked = 'checked';
            // alert(1234);
        // layui.formSelects.value('selectState1', [i]);
      if (i == "1"){
          $("#allI5 a").removeClass("active")
          $("#allI4 a").removeClass("active")
          $("#allIn3 a").removeClass("active")
          $("#allIn2 a").removeClass("active")
          $("#allI1 a").addClass("active")
      }
        if (i == "2"){
            $("#allI5 a").removeClass("active")
            $("#allI4 a").removeClass("active")
            $("#allIn3 a").removeClass("active")
            $("#allIn2 a").addClass("active")
            $("#allI1 a").removeClass("active")
        }
        if (i == "3"){
            $("#allI5 a").removeClass("active")
            $("#allI4 a").removeClass("active")
            $("#allIn3 a").addClass("active")
            $("#allIn2 a").removeClass("active")
            $("#allI1 a").removeClass("active")
        }
        if (i == "4"){
            $("#allI5 a").removeClass("active")
            $("#allI4 a").addClass("active")
            $("#allIn3 a").removeClass("active")
            $("#allIn2 a").removeClass("active")
            $("#allI1 a").removeClass("active")
        }
        if(i == 5){
            $("#allI5 a").addClass("active")
            $("#allI4 a").removeClass("active")
            $("#allIn3 a").removeClass("active")
            $("#allIn2 a").removeClass("active")
            $("#allI1 a").removeClass("active")
        }
    }

    // $("#mainbox a").click(function(){
    //     $(this).addClass("active").siblings().removeClass("active");
    // })
    $(".ibox").mouseover(function (){
        $(".ibox").show();
    }).mouseout(function (){
        $(".ibox").hide();
    });
</script>