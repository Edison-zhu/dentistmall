<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/6/3
  Time: 8:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>采购中心系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <style>
        .focus{
            /*outline: 0px;*/
            box-shadow: 0 0 6px 2px lightblue;
            /*border: 0px;*/
            /*outline: 1px solid lightskyblue;*/
        }
        /*新样式*/
        .hahath{
            width: 20%;
            text-align: center;
            font-weight: bold;
            padding: 4px 6px;
            line-height: 30px;
        }
        .hahath2{
            text-align: center;
            padding: 6px;
        }
        .hahath2 input{
            border: 1px solid #dddddd;
            height: 30px;
            line-height: 30px;
            width: 250px;
            padding-left: 5px;
        }
        .hahath3{
            text-align: center;
            padding: 6px;
        }
        .hahabtn1{
            background: #23c6c8;
            width: 80px;
            height: 22px;
            line-height: 22px;
            border-radius: 11px;
            color: #fff;
            display: inline-block;
            text-align: center;
            margin-right: 20px;
        }
        .hahabtn1:hover{
            color: #fff;
        }
        .hahabtn2{
            background: #f7a54a;
            width: 80px;
            height: 22px;
            line-height: 22px;
            border-radius: 11px;
            color: #fff;
            display: inline-block;
            text-align: center;
        }
        .hahabtn2:hover{
            color: #fff;
        }
        .hahamore{
            float: right;
            line-height: 40px;
            margin-right: 8px;
            color: #337ab7;
            font-size: 14px;
        }
        .hahamore:hover{
            color: #337ab7;
        }
        .newbtn1{
            width: 120px;
            height: 30px;
            background: #1ab394;
            border-radius: 15px;
            border: 1px solid #1ab394;
            text-align: center;
            color: #fff;
            margin-left: 30px;
            display: inline;
        }
        .newbtn2{
            width: 150px;
            height: 30px;
            background: #1ab394;
            border-radius: 15px;
            border: 1px solid #1ab394;
            text-align: center;
            color: #fff;
            margin-left: 30px;
            display: inline;
        }
    </style>
</head>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="row  wrapper wrapper-content ">
    <div class="col-sm-2">
        <div class="ibox float-e-margins">
            <!-- 标题栏区域 begin-->
            <div class="ibox-title">
                <!-- 标题文字区域begin -->
                <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp分类信息</h5>
                <!-- 标题文字区域end -->
                <!-- 标题栏工具区域begin -->
                <div class="ibox-tools">
                </div>
            </div>
            <!-- 标题栏区域 end-->
            <!-- 操作区域 begin-->
            <div class="ibox-content" style="padding: 0px;">
                <div class="scrollbarlist">
                    <div class="panel-body">
                        <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                    </div>
                </div>
            </div>
            <!-- 操作区域 end-->
        </div>
    </div>

    <div class="col-sm-10">
        <div class="row">
            <div class="ibox float-e-margins">
                <!-- 标题栏区域 begin-->
                <!-- 标题栏区域 end-->
                <!-- 操作区域 begin-->
                <div class="ibox-content">
                    <div class="row" style="margin-bottom: 10px">
                        <div style="text-align: left;display: inline">
                            <form id="queryForm" style="display: inline">
<%--                                <input type="hidden" id="mdpsId" name="mdpsId">--%>
<%--                                <input type="hidden" id="mdpId" name="mdpId"/>--%>
                                <div id="searchForm" class="form-inline" style="display: inline">
                                    <label>关键字查询</label>
                                    <input type="text" id="searchName" onfocus="addClass()" onblur="removeClass()" onkeyup="enterSearch(true)" class="form-control" placeholder="物料号/物料名称/规格号/规格名称"
                                           name="searchName" value="" style="width: 300px"/>
                                </div>
                            </form>
                            <button id="btnSearch" class="newbtn1" onclick="search(true)">
                                查询
                            </button>
                        </div>
                        <div style="text-align: right;display: inline">
                            <button id="addOnceBtn" class="newbtn2" onclick="oneAdd()">
                                一键添加物料
                            </button>
                            <button id="newAddBtn" class="newbtn1" onclick="newAdd()">
                                新建物料
                            </button>
                        </div>
                    </div>
                    <table id="datagrid1" class="mmg"></table>
                    <div id="pg" style="text-align: right;"></div>
                </div>
                <!-- 操作区域 end-->
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/selMateriel.js?v=33'/>"></script>