<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>采购中心系统</title>
    <%@ include file="/jsp/lib.jsp"%>
    <%@ include file="/jsp/link.jsp"%>
<style>
    .tbAlighRight {
        text-align: right;
        padding: 12px;
    }
    .xhbtn{
        display: inline-block;
        margin-left: 40px;
        width: 115px;
        height: 30px;
        border-radius: 15px;
        background: #23c6c8;
        color: #fff;
        text-align: center;
        line-height: 30px;
    }
    .xhbtn:hover{
        color: #fff;
    }
</style>

</head>
<body class="gray-bg">
<!-- 主信息区域begin -->
<div class="row  wrapper wrapper-content ">
    <!-- 左边区域begin -->
    <!-- 左边区域end -->
    <!-- 右边区域begin  -->
    <div class="col-sm-12">

        <div class="row">
            <div class="col-sm-12">
                <!-- 标题栏区域 begin-->
                <div class="ibox-title">
                    <!-- 标题文字区域begin -->
<%--                    <h5>--%>
<%--                        <i class="fa fa-bell-o"></i>&nbsp&nbsp<span>日志</span>--%>
<%--                    </h5>--%>
                    <!-- 标题文字区域end -->
<%--                    <div class="ibox-tools" id="win_tools_but"   >--%>
                        <table>
                            <tr id="ajaxLoad">
                                <td class="tbAlighRight">物料编号：</td>
                                <td><span id="matCode"></span></td>
                                <td class="tbAlighRight">商品物料名称：</td>
                                <td><span id="matName"></span></td>
                                <td class="tbAlighRight">库存数量：</td>
                                <td><span id="quantity"></span></td>
                            </tr>
                            <tr>
                                <td class="tbAlighRight">时间：</td>
                                <td><input class="form-control" id="createDate1" name="createDate1" /></td>
                                <td class="tbAlighRight">类型：</td>
                                <td>
                                    <select class="form-control" id="stype">
                                        <option value="" selected>所有类型</option>
                                        <option value="1">入库</option>
                                        <option value="2">出库</option>
                                    </select>
                                </td>
                                <td class="tbAlighRight">操作人：</td>
                                <td><input class="form-control" id="createRen" /></td>
                                <td><a class="xhbtn" onclick="main_search()">查询</a></td>
                                <td><a class="xhbtn" onclick="exportExcel()">导出excel表格</a></td>
                            </tr>
                        </table>
<%--                    </div>--%>
                </div>
                <!-- 标题栏区域 end-->

                <!-- 查询区域begin -->
                <div class="ibox-content" style="padding: 5px 10px 10px;">
                    <form class="form-horizontal" id="win_form_search">
                        <div  id="win_form_hidden" ></div>
                    </form>
                </div>
                <!-- 查询区域end -->

                <!-- 表格区域begin -->
                <div class="ibox-content" style="padding: 5px 10px 10px;">
                    <table id="win_datagrid_main" class="mmg"></table>
                    <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
                </div>
                <!-- 表格区域end -->
            </div>
        </div>

    </div>
    <!-- 右边区域end -->
</div>
<!-- 主信息区域end -->
</body>
</html>
<script   src="<c:url value='/js/cookie.js' />"></script>
<script src="<c:url value='/javaScript/dentistmall/storage/viewInventoryLog.js?v=7'/>"></script>

