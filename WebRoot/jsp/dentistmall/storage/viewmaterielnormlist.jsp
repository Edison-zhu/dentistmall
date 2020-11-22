<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/13
  Time: 8:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title></title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <style>
        /*开关的大小*/
        .switch-container {
            height: 15px;
            width: 30px;
        }

        /*设置checkbox不显示*/
        .switch {
            display: none;
        }

        /*设置label标签为椭圆状*/
        .switch-container label {
            display: block;
            background-color: #EEEEEE;
            height: 100%;
            width: 100%;
            cursor: pointer;
            border-radius: 25px;
        }

        /*在label标签内容之前添加如下样式，形成一个未选中状态*/
        .switch-container label:before {
            content: '';
            display: block;
            border-radius: 25px;
            height: 100%;
            width: 15px;
            background-color: white;
            opacity: 1;
            box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.08);
            -webkit-transition: all 0.2s ease;
        }

        /*在label标签内容之后添加如下样式，形成一个选中状态*/
        .switch-container label:after {
            position: relative;
            top: -15px;
            left: 15px;
            content: '';
            display: block;
            border-radius: 25px;
            height: 100%;
            width: 15px;
            background-color: white;
            opacity: 0;
            box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.08);
            -webkit-transition: all 0.2s ease;
        }

        /*选中后，选中样式显示*/
        .switch-container input:checked~label:after {
            opacity: 1;
        }

        /*选中后，未选中样式消失*/
        .switch-container input:checked~label:before {
            opacity: 0;
        }

        /*选中后label的背景色改变*/
        .switch-container input:checked~label {
            background-color: green;
        }
        .sbtn1{
            display: inline-block;
            height: 26px;
            width: 80px;
            line-height: 26px;
            background: #1ab394;
            font-size: 14px;
            color: #ffffff;
            text-align: center;
            border-radius: 5px;
            margin-left: 10px;
        }
        .sbtn1:hover{
            color: #ffffff;
        }
        button{
            outline: none;
        }
        input{
            border: 1px solid #e5e6e7;
        }

    </style>
</head>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5>
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>规格型号</span>
        </h5>
        <!-- 标题文字区域end -->
        <div class="ibox-tools" id="win_tools_but">
        </div>
    </div>
    <!-- 标题栏区域 end-->

    <!-- 查询区域begin -->
    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <div style="float: left;line-height: 25px">
            物料号：<span id="matCode"></span>
        </div>
        <div>
            <input id="mmfNameSearch" name="mmfName" placeholder="请输入关键字搜索" onkeyup="searchEnter()" style="width: 270px;height: 30px;line-height: 30px"/>
            <a type="button" onclick="main_search()" class="sbtn1">搜索</a>
            <a type="button" id="addBtn" onclick="main_add()" class="sbtn1">添加</a>
        </div>


        <script type="text/javascript" language="javascript">
            window.onload = function(){

                document.getElementById('mmfNameSearch').focus();
                document.getElementById("mmfNameSearch").style.outline="thin solid #1ab394";
                }
        </script>



<%--        <form class="form-horizontal" id="win_form_search">--%>
<%--            <div id="win_form_hidden"></div>--%>
<%--        </form>--%>
    </div>
    <!-- 查询区域end -->

    <!-- 表格区域begin -->
    <div class="ibox-content" style="padding: 5px 10px 10px;">
        <table id="win_datagrid_main" class="mmg"></table>
        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
    </div>
    <!-- 表格区域end -->
</div>
<div class="" style="">
    <div style="text-align: right;padding-right: 20px;margin-top: 20px">
        <button class="btn btn-white" style="margin-right: 30px;width: 150px;height: 35px;line-height: 35px;padding: 0;border-radius: 17px" onclick="closeWin()">关闭</button>
<%--        <button class="btn btn-primary" style="margin-right: 30px;width: 150px;height: 35px;line-height: 35px;padding: 0;border-radius: 17px" onclick="updateInfo()">确定</button>--%>
    </div>
</div>
<!-- 主信息区域end -->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/viewmaterielnormlist.js?v=22'/>"></script>