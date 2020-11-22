<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>牙医商城平台</title>
    <meta charset="UTF-8">
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
</head>
<style>
    a:hover {
        color: #1ab394;
    }

    #uiSpan a.hover1:hover {
        color: #333;
    }

    #uiSpan a.hover2:hover {
        color: #1ab394;
    }

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

    .collectText {
        float: left;
        position: relative;
        left: 1%;
        font-size: 14px;
    }

    .collectText span {
        color: black;
        font-weight: bold;
    }

    #s1 {
        float: right;
    }

    .ibox-title .item2 .daochubtn {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 130px;
        height: 25px;
        border: 1px solid #1ab394;
        background-color: #1ab394;
        font-size: 14px;
        color: white;
        border-radius: 13px;
        float: right;
        margin-right: 20px;
    }

    .ibox-title .item3 .daochubtn {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 130px;
        height: 25px;
        border: 1px solid #1ab394;
        background-color: white;
        font-size: 14px;
        color: #1ab394;
        border-radius: 13px;
        float: right;
    }

    .a-click {
        color: #1ab394;
    }

    /*#uiSpan ul{*/
    /*    !*display: flex;*!*/
    /*    !*align-items: flex-start;*!*/
    /*}*/
    /*#uiSpan li {*/
    /*    float: left;*/
    /*    !*flex: 1;*!*/
    /*}*/
    #uiSpan a {
        margin-top: 10px;
        padding: 10px;
        font-weight: bolder;
    }

    @media screen and (max-width: 1400px) {
        .tabwidth {
            width: 1160px;
        }
    }

    @media screen and (min-width: 1401px) and (max-width: 1660px) {
        .tabwidth {
            width: 1310px;
        }
    }

    @media screen and (min-width: 1661px) {
        .tabwidth {
            width: 1635px;
        }
    }
</style>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5>
            <i class="fa fa-calendar"></i>&nbsp&nbsp<span>申领出库信息</span>
        </h5>
        <div class="item3"><a onclick="main_wait_search()" class='daochubtn' id="Refresh">刷新</a></div>
        <div class="item2"><a onclick="main_add1(true)" class='daochubtn' id="addOutbound">新增出库</a></div>
        <!-- 标题文字区域end -->
        <!-- <div class="ibox-tools" id="win_tools_but"> -->
        <!-- </div> -->
    </div>
    <!-- 标题栏区域 end-->

    <!-- 查询区域begin -->
    <div id="readyFormSearch" class="ibox-content" style="padding: 5px 10px 10px;">
        <form class="form-horizontal" id="win_form_search">
            <div id="win_form_hidden"></div>
        </form>
    </div>
    <!--2020年07月03日13:52:13 朱燕冰修改不同点击状态高亮显示 -->
    <div id="uiSpan" style="background-color: white;height: 78px">
        <div style="padding: 10px">
             <span id="span1" class="" lay-separator="|" style="float:left;margin-left: 17px">
<%--                 <a onclick="aSearchWowTypeFilter(this)"><a id="allColor1" onclick="changes(1)">出库类型(<span id="wowType">0</span>)</a></a>--%>
<%--                 <a id="afterOrder" onclick="aSearchWowTypeFilter(this, 2)"><a id="allColor2" onclick="changes(2)">采购退货(<span id="wowType2">0</span>)</a></a>--%>
<%--                 <a onclick="aSearchWowTypeFilter(this, 1)"><a id="allColor3" onclick="changes(3)">科室领料(<span id="wowType1">0</span>)</a></a>--%>
<%--                 <a onclick="aSearchWowTypeFilter(this, 3)"><a id="allColor4" onclick="changes(4)">报损出库(<span id="wowType3">0</span>)</a></a>--%>

                 <a onclick="aSearchWowTypeFilter(this)" id="allColor1" onclick="changes(1)">出库类型(<span
                         id="wowType0">0</span>)</a>
                 <a id="afterOrder" onclick="aSearchWowTypeFilter(this, 2)" id="allColor2"
                    onclick="changes(2)">采购退货(<span id="wowType2">0</span>)</a>
                 <a onclick="aSearchWowTypeFilter(this, 1,1)" id="allColor3" onclick="changes(3)">科室领料(<span
                         id="wowType1">0</span>)</a>
                 <a onclick="aSearchWowTypeFilter(this, 3)" id="allColor4" onclick="changes(4)">报损出库(<span
                         id="wowType3">0</span>)</a>
            </span>
            <%--           <span style="margin-left: 45px;margin-right: 45px" >|</span>--%>
            <ul class="nav nav-tabs" style="padding-bottom: 5px">
                <div id="s1">
                    <div class="ibox-tools" id="win_tools_but" style="margin-right: 20px;margin-top: 35px"></div>
                </div>

                <span id="span2" class="" lay-separator="|" style="position: absolute;left: 19px;top: 193px">
<%--                <a onclick="aSearchFlowStateFilter(this)"><a id="allColors1" onclick="changes(5)">全部状态(<span id="flowState">0</span>)</a></a>--%>
<%--                <a id="waitOut" onclick="aSearchFlowStateFilter(this, 2)"><a id="allColors2" onclick="changes(6)">待出库(<span id="flowState2">0</span>)</a></a>--%>
<%--                <a onclick="aSearchFlowStateFilter(this, 3)"><a id="allColors3" onclick="changes(7)">部分出库(<span id="flowState3">0</span>)</a></a>--%>
<%--                <a onclick="aSearchFlowStateFilter(this, 4)"><a id="allColors4" onclick="changes(8)">已出库(<span id="flowState4">0</span>)</a></a>--%>
<%--                <a onclick="aSearchFlowStateFilter(this, 5)"><a id="allColors5" onclick="changes(9)">已撤销(<span id="flowState5">0</span>)</a></a>--%>

                 <a onclick="aSearchFlowStateFilter(this)" id="allColors1" onclick="changes(5)">全部状态(<span
                         id="flowState">0</span>)</a>
                <a id="waitOut" onclick="aSearchFlowStateFilter(this, 2)" id="allColors2" onclick="changes(6)"
                   style="margin-left: 3px">待出库(<span id="flowState2">0</span>)</a>
                <a onclick="aSearchFlowStateFilter(this, 3)" id="allColors3" onclick="changes(7)">部分出库(<span
                        id="flowState3">0</span>)</a>
                <a onclick="aSearchFlowStateFilter(this, 4)" id="allColors4" onclick="changes(8)">已出库(<span
                        id="flowState4">0</span>)</a>
                <a onclick="aSearchFlowStateFilter(this, 5)" id="allColors5" onclick="changes(9)">已撤销(<span
                        id="flowState5">0</span>)</a>
            </span>
            </ul>
        </div>
    </div>
    <!-- 查询区域end -->
    <!-- 表格区域begin -->
    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <div class="tabs-container">
        </div>

        <div id="waitDiv">
            <div style="width: 98%;">
                <table id="win_datagrid_wait_main" class="mmg"></table>
            </div>
            <div class="collectText">
                汇总：共计申请<span id="collectCount">0</span>条
            </div>
            <div id="_all_win_datagrid_wait_pg" style="text-align: right;"></div>
        </div>
        <div id="readyDiv">
            <table id="win_datagrid_main" class="mmg"></table>
            <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
        </div>

    </div>
    <!-- 表格区域end -->

</div>
<div class="col-sm-8">
    <div id="rangeDate">

    </div>
</div>
<!-- 主信息区域end -->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/applyMdOutWarehouserList.js?v=186'/>"></script>
<script>
    // $("#uiSpan a").on('mouseover', function () {
    //     let color = $(this).css('color');
    //     console.log(color);
    //     // $(this).style('color')
    // })
    // $('#uiSpan a').hover(function(){
    //     console.log($(this).attr('id'), 'allColors' + clickI)
    //     if ($(this).attr('id') == 'allColors' + clickI)
    //         $(this).addClass('hover2')
    //     else
    //         $(this).addClass('hover2')
    // },function(){
    //     if ($(this).attr('id') == 'allColors' + clickI)
    //         $(this).addClass('hover2')
    //     else
    //         $(this).addClass('hover2')
    // });
    // $("#uiSpan a").removeClass("hover");
    //  $("#uiSpan a").addClass("focus").css("a-hover","none");
</script>
 