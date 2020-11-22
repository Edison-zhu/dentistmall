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
        /* 新样式 */
    .top1{
        padding:0 30px;
        display:flex;
        justify-content: flex-end;
    }
    .newbtn{
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
    color:#333;
    }
    .newbtn:hover{
    text-decoration: none;
    }
.top2{
padding: 0 30px;
    display: flex;
    margin-top: 20px;
    justify-content: space-between;
    align-items: center;
}
.top2 input{
width: 200px;height: 25px;background-color:#DCDCDC;border: 1px solid #DCDCDC;
padding-left:10px;
}
.box1{
     margin: 0 30px;
    padding: 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border: 1px solid #f5f5f5;
}
.box1 input{
   width: 280px;height: 25px;background-color:#fff;border: 1px solid #DCDCDC;
   padding-left:10px;
}
.box1 .searchbox{
display: flex;
justify-content: flex-end;
    align-items: center;
}
.box1 button{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100px;
    height: 30px;
    border: 1px solid #1ab394;
    font-size: 14px;
    border-radius: 15px;
    margin-left: 20px;
    color:#fff;
    background:#1ab394;
}
.box2{
background-color: #F0F0F0;height: 40px;margin: 25px 30px 0 30px;
}
.box2 .topp{
line-height: 3
}
.box2 .topp span{
display: inline-block;
text-align: center;
}
.listbox{

}
.listbox .items{
    height: 135px;background-color: rgba(192, 192, 192, 0);
    border: 1px solid #F5F5F5;
display: flex;
justify-content: flex-start;
align-items: center;
}
.listbox .items .imgbox{
    width: 37%;
    display: flex;
justify-content: flex-start;
align-items: center;
}
.listbox .items .imgbox .img{
    margin-left: 30px;
margin-right: 20px;
}
.listbox .items .rtbox{
width: 15%;
text-align: center;
}
.bookbox{
margin-top: 30px;
margin-bottom: 30px;
}
.bookbox h1{
    font-size:14px;
    font-weight: bold;
display: inline-block;
margin-right: 20px;
}
.submitbox{
display: flex;
justify-content: flex-end;
align-items: center;
}
.submitbox button{
    display: flex;
    justify-content: center;
    align-items: center;
    width: 200px;
    height: 30px;
    border: 1px solid #1ab394;
    font-size: 14px;
    border-radius: 15px;
    margin-right: 200px;
    color:#fff;
    background:#1ab394;
}
</style>
<body style="background-color: white">
<div style="margin-top: 30px; ">
    <div>
     <div class="top1"></div>
        <div class="top2">
           <div>申领单号：<input id="mooCode" readonly="readonly"></div>
           <div>申领时间：<input id="orderTime" readonly="readonly"></div>
        </div>
    </div>
</div>

<div style="margin-top: 30px; ">
    <div class="box1">
       <div>申领状态:<span id="flowState"></span></div>
       <div class="searchbox">物料关键字：<input id="gjz" placeholder="名称、别名、编号、规格搜索" onkeydown="entersearch()">
       <button id="searchDdBtn" onclick="initDataMx()">搜索</button></div>
    </div>
    <script>
        function entersearch(){
            var event = window.event || arguments.callee.caller.arguments[0];
            if (event.keyCode == 13)
            {
                initDataMx();
            }
        }
    </script>
</div>
<div class="box2">
                    <div class="topp">
                        <div style="width: 10%;float: left;text-align: left"><input type="checkbox" class="loginName" onclick="exportItem(this,-1)" id="all"><span>全选/反选</span></div>
                        <div style="width: 35%;float: left;text-align: left">物料信息</div>
                        <div style="width: 10%;float: left;text-align: center">库存数量</div>
                        <div style="width: 15%;float: left;text-align: center">申请数量</div>
                        <div style="width: 10%;float: left;text-align: center">出库数量</div>
                        <div style="width: 10%;float: left;text-align: center">差数</div>
                        <div style="width: 10%;float: left;text-align: center">操作</div>
                    </div>
    <div style="clear: both"></div>
<%--            <div class="hd1" id="mxList"></div>--%>
            <div style='background-color: white;height: auto'><tr><div id="mxList"></div></tr></div>
    <div class="" style="width:1380px;height: 30px;margin-left: 0px;margin-bottom: 20px;margin-top: 15px">

            <div><input type="checkbox" class="loginName" onclick="exportItem(this,-1)" id="all1"><span>全选/反选</span>
<%--                <label for="">全选</label>--%>
                <a  class="delete font-weight-trigger" onclick="delAll()">移除选中</a><span style="margin-left: 100px">您共申请<span id="mmfCount"></span>种型号规格,共计数量<span id="numberCount1"></span><span id="numberCount2"></span></span></div>
    </div>
    <div class="" style="width:1380px;height: 60px;margin-left: 0px;margin-bottom: 20px">
        <div class="" style="margin-left: 0px;float: left;line-height: 60px">申请留言:</div>
        <%--                <div class="allOrderInfo" style="margin-right: 120px;border: 1px solid black;width: 400px;height: 58px">--%>
        <input class="" id="remarks" value="" placeholder="50个字以内" style="margin-left: 20px;float: left;width: 1118px;height: 60px;border: 1px silver solid;padding-left: 10px"/>
    </div>
    <div class="submitbox" style="padding-bottom: 10px;">
        <button id="cancelAllbtn" onclick="submits()" style="width: 150px;background: #DCDCDC;border:1px solid #DCDCDC">全部撤销</button>
        <button onclick="submit()" style="width: 150px;margin-left: -126px">提交申请</button>
    </div>
</div>
    <div class="pagination" id="Pagination1" style="margin-left: 40%;display: none"></div>
<%--<div style="margin-top: 30px;">留言</div>--%>
<%--</div>--%>

</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/pickingEdit.js?v=38'/>"></script>

