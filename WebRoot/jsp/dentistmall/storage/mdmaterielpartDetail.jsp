<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/13
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <style>
        .red {
            color: red;
        }

        .table {
            /*margin-left: 20%;*/
        }

        .table td {
            padding: 2px;
        }

        .textAlginRight {
            text-align: right;
            width: 11%;
        }

        .form-control {
            border: 1px solid gray
        }

        .table td {
            border-top: 0px;
        }

        #basicUnit:disabled {
            background: whitesmoke;
        }

        .normTable th td {
            text-align: center;
        }

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
        .switch-container input:checked ~ label:after {
            opacity: 1;
        }

        /*选中后，未选中样式消失*/
        .switch-container input:checked ~ label:before {
            opacity: 0;
        }

        /*选中后label的背景色改变*/
        .switch-container input:checked ~ label {
            background-color: green;
        }
        .titbj{
            height: 40px;
            background: url('/dentistmall/img/tbj.png') left center no-repeat;
            margin-bottom: 10px;
            font-size: 16px;
            color: #fff;
            text-indent: 50px;
            line-height: 40px
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
    </style>
</head>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="ibox float-e-margins">
            <!-- 标题栏区域 begin-->
            <div class="ibox-title">
                <!-- 标题文字区域begin -->
                <h5><i class="fa fa-tint"></i>&nbsp&nbsp物料字典</h5>
                <!-- 标题文字区域end -->
                <!-- 标题栏工具区域begin -->
                <div class="ibox-tools">
                    <button id="saveBtn" type="button" class="btn btn-round btn-default btn-sm fa fa-search" style="width: 139px" onclick="saveMdd1()">
                        &nbsp;保存
                    </button>
                    <button type="button" class="btn btn-round btn-info btn-sm fa fa-edit" style="width: 139px"onclick="backTo1()">
                        &nbsp;返回
                    </button>
                </div>
                <!-- 标题栏工具区域end -->
            </div>
            <!-- 标题栏区域 end-->
            <!-- 操作区域 begin-->
            <form id="saveForm" onsubmit="return false;">
                <div class="ibox-content">

                    <div class="titbj"><span>基本信息</span></div>
                    <div>
                        <table class="table">
                            <tr>
                                <td style="border:0px;" class="textAlginRight">
                                    <div><span class="red">*</span>物料分类：</div>
                                </td>
                                <td style="border:0px;">
                                    <div class="red"><span id="mdpName">${obj.mdpName}</span>
                                        /<span id="mdpsName">${obj.mdpsName}</span>
                                        <input type="hidden" name="mdpId" id="mdpId"
                                               value="${obj.mdpId}"/>
                                        <input type="hidden" id="mdpsId" name="mdpsId" value="${obj.mdpsId}"/>
                                        <a class="" style="color: blue" id="selectPartA" onclick="selectPart()">选择分类</a></div>
                                </td>
                            </tr>
                            <tr>
                                <td style="border:0px;" class="textAlginRight">
                                    <div><span class="red">*</span>物料编号：</div>
                                </td>
                                <td style="border:0px;">
                                    <div>
                                        <div class="form-inline">
                                            <input class="form-control" readonly name="matCode" id="matCode" value="${obj.matCode}"/>
                                            <label id="customerId">
                                                <input class="form-control" type="checkbox" id="customer" onclick="changeCustomer1(this.checked)"/>自定义
                                            </label>
                                        </div>
                                    </div>
                                </td>
                                <td style="border:0px;" class="textAlginRight">
                                    <div>
                                        <span class="red">*</span>物料名称：
                                    </div>
                                </td>
                                <td style="border:0px;">
                                    <div>
                                        <input class="form-control" class="form-control" name="matName" id="matName"
                                               value="${obj.matName}" placeholder="最多输入30个汉字"/>
                                    </div>
                                </td>
                                <td style="border:0px;" class="textAlginRight">
                                    <div><span class="red">*</span>基本单位：</div>
                                </td>
                                <td style="border:0px;">
                                    <div>
                                        <%--                                        <input type="hidden" id="basicUnitHidden" value="${obj.basicUnit}" />--%>
                                        <%--                                        <select class="form-control" id="basicUnit" name="basicUnit" value="${obj.basicUnit}">--%>

                                        <%--                                        </select>--%>
                                        <input class="form-control" id="basicUnit" readonly onclick="selectUnit(1)"
                                               name="basicUnit" value="${obj.basicUnit}" placeholder="选择单位"/>
                                    </div>
                                </td>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>库位：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div>--%>
<%--                                        <input class="form-control" name="inventoryLocation"--%>
<%--                                               value="${obj.inventoryLocation}"--%>
<%--                                               placeholder="仓管简称及位置"/>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
                            </tr>
                            <tr>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div><span class="red">*</span>基本单位：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div>--%>
<%--                                        &lt;%&ndash;                                        <input type="hidden" id="basicUnitHidden" value="${obj.basicUnit}" />&ndash;%&gt;--%>
<%--                                        &lt;%&ndash;                                        <select class="form-control" id="basicUnit" name="basicUnit" value="${obj.basicUnit}">&ndash;%&gt;--%>

<%--                                        &lt;%&ndash;                                        </select>&ndash;%&gt;--%>
<%--                                        <input class="form-control" id="basicUnit" readonly onclick="selectUnit(1)"--%>
<%--                                               name="basicUnit" value="${obj.basicUnit}" placeholder="选择单位"/>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div><span class="red">*</span>基本系数：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div>--%>
                                        <input type="hidden" class="form-control" name="basicCoefficent" id="basicCoefficent"
                                               value="1"
                                               placeholder=""/>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div><span class="red">*</span>最小拆分单位：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div>--%>
<%--                                        &lt;%&ndash;                                        <input type="hidden" id="splitUnitHidden" value="${obj.splitUnit}" />&ndash;%&gt;--%>
<%--                                        &lt;%&ndash;                                        <select class="form-control" id="splitUnit" name="splitUnit" value="${obj.splitUnit}">&ndash;%&gt;--%>

<%--                                        &lt;%&ndash;                                        </select>&ndash;%&gt;--%>
<%--                                        <input class="form-control" id="splitUnit" readonly onclick="selectUnit(2)"--%>
<%--                                               name="splitUnit" value="${obj.splitUnit}"--%>
<%--                                               placeholder="选择最小拆分单位"/>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
                                <td style="border:0px;" class="textAlginRight">
                                    <div>规格：</div>
                                </td>
                                <td style="border:0px;">
                                    <div>
                                        <input class="form-control" id="norm" maxlength="30"
                                               name="norm" value="${obj.norm}"
                                               placeholder="不能超过30个字符"/>
                                    </div>
                                </td>
                                <td style="border:0px;" class="textAlginRight">
                                    <div>包装方式：</div>
                                </td>
                                <td style="border:0px;">
                                    <div>
                                        <input class="form-control" id="productName"
                                               name="productName" value="${obj.productName}"
                                               placeholder=""/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td style="border:0px;" class="textAlginRight">
                                    <div>
                                        物料图标：
                                    </div>
                                </td>
                                <td style="border:0px;" colspan="5">
                                    <div class="form-group" style="margin-bottom:0px;">
                                        <div class="col-sm-3">
                                            <div class="awards-imgs">
                                                <ul class="imgul f-cb" >
                                                    <li class="one">
                                                        <div id="lessenFileDiv">
                                                        </div>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                            <input type="hidden" id="lessenFilecode" name="lessenFilecode" value="${obj.lessenFilecode}"/>
                                            <input type="hidden" id="listFilecode" name="listFilecode"
                                                   value="${obj.listFilecode}"/>
                                </td>
                            </tr>
<%--                            <tr>--%>
<%--                                <td style="border:0px;vertical-align: top;" class="textAlginRight">--%>
<%--                                    <div>--%>
<%--                                        别名：--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;" colspan="5">--%>
<%--                                    <div>--%>
<%--                                        <input onfocus="hideError()" maxlength="30" class="form-control" style="width: 34%;display: inline" id="aliasName" name=""--%>
<%--                                               value=""/><a class="btn btn-white" style="margin-left: 20px" id="aliasAdd" onclick="addAliasName()">添加</a>--%>
<%--                                        <span id="aliasNameError" style="display: none;color: red"></span><br>--%>

<%--                                        <div id="aliasNameDiv" style="display: flex">--%>
<%--                                            <c:if test="${obj.aliasName != null}">--%>
<%--                                                <c:set var="alisNameList" value="${fn:split(obj.aliasName, ',')}"/>--%>
<%--                                                <c:forEach var="alisName" items="${alisNameList}"--%>
<%--                                                           varStatus="indexStatus">--%>
<%--                                                    <c:if test="${alisName != ''}">--%>
<%--                                                        <a id="aliasA${indexStatus.count}" onmouseover="showDeleteA(${indexStatus.count}, true)" onmouseout="showDeleteA(${indexStatus.count}, false)" style="margin-right: 8px;background-color: whitesmoke;padding: 5px 10px;border: 1px solid lightgray">--%>
<%--                                                                ${alisName}--%>
<%--                                                            <a id="aliasName${indexStatus.count}" onclick="deleteAliasNames('${alisName}', ${indexStatus.count})"--%>
<%--                                                               style="position: relative;top: -3px;left: -18px;color: #fff;background: gray;font-size: 12px;border-radius: 9px;height: 18px;width: 18px;line-height: 18px;text-align: center;">X</a>--%>
<%--                                                        </a>--%>
<%--                                                    </c:if>--%>
<%--                                                </c:forEach>--%>
<%--                                            </c:if>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
                        </table>
                    </div>
                </div>
<%--                <div class="ibox-content">--%>
<%--                    <div class="titbj"><span>其他信息</span><a class="hahamore" onclick="moreInfo()">更多信息&nbsp;》</a></div>--%>
<%--                    <table class="table">--%>
<%--                        <tr>--%>
<%--                            <td style="border:0px;" class="textAlginRight">--%>
<%--                                <div><span class="red">*</span>包装方式：</div>--%>
<%--                            </td>--%>
<%--                            <td style="border:0px;width: 39.5%;">--%>
<%--                                <div><input class="form-control" name="productName" id="productName" value="${obj.productName}"/></div>--%>
<%--                            </td>--%>
<%--                            <td style="border:0px;" class="textAlginRight">--%>
<%--                                <div>有效期：</div>--%>
<%--                            </td>--%>
<%--                            <td style="border:0px;">--%>
<%--                                <div><input class="form-control" readonly id="valiedDate" name="valiedDate"--%>
<%--                                            value="<fmt:formatDate value="${obj.valiedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>--%>
<%--                                </div>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </table>--%>
<%--                    <div id="colTable" style="display: none">--%>
<%--                        <table class="table">--%>
<%--                            <tr>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>品牌：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="brand" value="${obj.brand}"/></div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>注册证有效期/备案日期：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input readonly class="form-control" id="BasicUnitAccuracy" name="basicUnitAccuracy"--%>
<%--                                                value="${obj.basicUnitAccuracy}"/>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>货号：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="serialNumber" value="${obj.serialNumber}"/>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>产品标准：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="standard" value="${obj.standard}"/></div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>材质：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="materielName" value="${obj.materielName}"/>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>注册证号：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="backPrinting" value="${obj.backPrinting}"/>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>生产企业：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="productFactory"--%>
<%--                                                value="${obj.productFactory}"/>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>产地：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="productPlace"--%>
<%--                                                value="${obj.productPlace}"/>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>主要成分：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="ingredient" value="${obj.ingredient}"/></div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>产品用途：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="productUse" value="${obj.productUse}"/></div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>保质期：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div>--%>
<%--                                        <div class="form-inline">--%>
<%--                                            <input class="form-control" name="validPeriod" value="${obj.validPeriod}"/>--%>
<%--                                            <input class="form-control" type="hidden" id="validPeriodUnit1"--%>
<%--                                                   value="${obj.validPeriodUnit}"/>--%>
<%--                                            <select id="validPeriodUnit" class="form-control" name="validPeriodUnit">--%>
<%--                                                <option value="年">年</option>--%>
<%--                                                <option value="月">月</option>--%>
<%--                                                <option value="日">日</option>--%>
<%--                                                <option value="时">分</option>--%>
<%--                                                <option value="分">分</option>--%>
<%--                                                <option value="秒">秒</option>--%>
<%--                                            </select>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>重量：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div>--%>
<%--                                        <div class="form-inline">--%>
<%--                                            <input class="form-control" name="weight" value="${obj.weight}"/>--%>
<%--                                            <input class="form-control" type="hidden" id="weightUnit1"--%>
<%--                                                   value="${obj.weightUnit}"/>--%>
<%--                                            <select class="form-control" id="weightUnit" name="weightUnit">--%>
<%--                                                <option value="年">年</option>--%>
<%--                                                <option value="月">月</option>--%>
<%--                                                <option value="日">日</option>--%>
<%--                                                <option value="时">分</option>--%>
<%--                                                <option value="分">分</option>--%>
<%--                                                <option value="秒">秒</option>--%>
<%--                                            </select>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                            <tr>--%>
<%--                                <td style="border:0px;" class="textAlginRight">--%>
<%--                                    <div>批号：</div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div><input class="form-control" name="batchCode" value="${obj.batchCode}"/></div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div></div>--%>
<%--                                </td>--%>
<%--                                <td style="border:0px;">--%>
<%--                                    <div></div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </table>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="ibox-content">--%>
<%--                    <div class="titbj"><span>规格信息</span></div>--%>
<%--                    <div class="form-inline"><span class="red">*</span>物料规格：<input class="form-control" id="norm"/> <a id="saveNormA"--%>
<%--                            class="form-control" onclick="saveNormTr()">添加</a>--%>
<%--                        <table id="normTable" style="display: none;width: 100%;margin-top: 10px;">--%>
<%--                            <tr>--%>
<%--                                <th class="hahath">--%>
<%--                                    <span style="color: red">*</span>&nbsp;物料规格--%>
<%--                                </th>--%>
<%--                                <th class="hahath">--%>
<%--                                    采购价格--%>
<%--                                </th>--%>
<%--                                <th class="hahath">--%>
<%--                                    零售价格--%>
<%--                                </th>--%>
<%--                                <th class="hahath">--%>
<%--                                    <span style="color: red">*</span>&nbsp;物料编号--%>
<%--                                </th>--%>
<%--                                <th class="hahath">--%>
<%--                                    操作--%>
<%--                                </th>--%>
<%--                            </tr>--%>
<%--                        </table>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--                <div class="ibox-content" style="padding: 5px 10px 10px;">--%>
<%--                    <table class="table" id="win_datagrid_main" class="mmg"></table>--%>
<%--                    <div id="_all_win_datagrid_pg" style="text-align: right;"></div>--%>
<%--                </div>--%>
<%--                <div class="ibox-tools">--%>
<%--                    <button id="saveBtn1" type="button" class="btn btn-round btn-default btn-sm fa fa-search" style="width: 139px" onclick="saveMdd()">--%>
<%--                        &nbsp;保存--%>
<%--                    </button>--%>
<%--                    <button type="button" class="btn btn-round btn-info btn-sm fa fa-edit" style="width: 139px"onclick="backTo()">--%>
<%--                        &nbsp;返回--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--                <div class="ibox-content">--%>
<%--                    <div>--%>
<%--                        备注：--%>
<%--                        <div style="width: 97%;display: inline-block;vertical-align: text-top;">--%>
<%--                            <textarea class="form-control KindEditor" placeholder="商品详情" name="describe1" id="describe1"--%>
<%--                                      style="height:400px">${obj.describe1}</textarea>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
                <div id="initDiv">
                    <input type="hidden" name="createDate" id="createDate" value="${obj.createDate}"/>
                    <input type="hidden" name="createRen" id="createRen" value="${obj.createRen}"/>
                    <input type="hidden" name="state" id="state" value="${obj.state}"/>
                    <%--                <input type="hidden" name="wmsMiId" id="wmsMiId" value="${obj.wmsMiId}"/>--%>
                    <input type="hidden" name="mdWmsMiId" id="mdWmsMiId" value="${obj.mdWmsMiId}"/>
                    <input type="hidden" name="purchaseType" id="purchaseType" value="${obj.purchaseType}"/>
                    <input type="hidden" name="wzId" id="wzId" value="${obj.wzId}"/>
                    <input type="hidden" name="number1" id="number1" value="${obj.number1}"/>
                    <input type="hidden" name="matType1" id="matType1" value="${obj.matType1}"/>
                    <input type="hidden" name="matType2" id="matType2" value="${obj.matType2}"/>
                    <input type="hidden" name="applicantName" id="applicantName" value="${obj.applicantName}"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/materielpartDetail.js?v=111'/>"></script>