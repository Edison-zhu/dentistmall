<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>采购中心系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
</head>
<style>
    .collectText {
        /*float: left;*/
        /*position: relative;*/
        /*bottom: 10%;*/
        /*right: 1%;*/
        font-size: 16px;
    }

    .collectText span {
        color: black;
        font-weight: bold;
    }

    .input-text {
        height: 22px;
    }

    .input-button {
        height: 22px;
        padding: 4px 9px;
        line-height: 1px;
        padding-bottom: 5px;
    }

    #button1 {
        width: 95px;
        height: 25px;
        margin-left: 1070px;
        margin-top: -89px;
    }

    #button2 {
        width: 95px;
        height: 25px;
        /*margin-right: 120px;*/
        margin-top: -89px;

    }

    #button3 {
        width: 95px;
        height: 25px;
        margin-left: -25px;
    }

    #win_form_inventoryex_body.form-group {
        margin-right: -1px;
    }
</style>
<body>
<!-- 表单部分begin -->
<form class="form-horizontal form-bordered" style="margin-top: 10px;margin-right: 38px" role="form">
    <div class="panel">
        <div id="win_form_inventoryex_body" style="height: auto">
            <!-- 表单区域 -->
        </div>
    </div>
</form>
<form id="queryForm" class="form-horizontal form-bordered" role="form">
    <div class="panel panel-default">
        <!-- 正文部分begin -->
        <div class="panel-body">
            <!--条件输入区域begin-->
            <div class="controls col-sm-12">
                <input type="hidden" name="wiId" id="wiId" value="">
                <div class="form-inline form-group">
                    <div class="controls col-sm-3" style="font-size:15px">
                        入库单号:
                        <input type="text" class="form-control" placeholder="单号" name="relatedCode" value=""
                               style="width:206px">
                    </div>
                    <div class="controls col-sm-3" style="font-size:15px">
                        开始时间:
                        <input type="text" class="form-control" placeholder="入库时间" id="receiptDatetime"
                               name="receiptDatetime" readOnly value="" style="width:206px">
                    </div>
                    <div class="controls col-sm-3" style="font-size:15px;width: auto">
                        入库类型:
                        <select id="billType" class="form-control" name="billType">
                            <option value="">所有</option>
                            <option value="2">订单入库</option>
                            <option value="1">物料入库</option>
                        </select>
                        <%--							<input type="text" class="form-control" placeholder="入库时间" id="endDate" name="endDate" readOnly value="" style="width:120px">--%>
                    </div>

                    <div class="controls col-sm-2 text-right" id="div1">
                        <a class="trigger" onclick="toInventory()" style="margin-right: -291px">没找到？试试库存合并</a>
                        <a class="btn btn-success btn-xs" style="margin-left: 10px;" onclick="search2();" id="button3">查询</a>
                    </div>
                </div>
                <table id="datagrid1" class="mmg"></table>


                <div style="display: flex;justify-content: space-between;align-items: center">
                    <div class="collectText">
                        汇总：共计申请出库数量<span id="collectIventoryCount">0</span>，已选择出库数量<span id="collectIventoryAllCount"
                                                                                         style="color: red;">0</span>
                    </div>
                    <div id="pg" style="text-align: right;"></div>
                </div>


            </div>
        </div>
        <!-- 正文部分end -->
        <!-- 工具栏部分begin -->
        <div class="panel-footer" style="height: 36px;padding: 0;display: flex;justify-content: flex-end;align-items: center;padding-right: 30px;margin-top: -10px">
            <%--<div class="collectText">--%>
            <%--汇总：共计申请出库数量<span id="collectIventoryCount">0</span>，已选择出库数量<span id="collectIventoryAllCount"--%>
            <%--style="color: red;">0</span>--%>
            <%--</div>--%>
            <%--		           <div class="form-group" style="float:right;position: absolute;bottom: 77px;right: 5px;display: inline">--%>
            <button type="button" onclick="save()" class="btn btn-primary btn-xs" id="button1" style="margin: 0">确定</button>
            <button type="button" onclick="closeWin()" class="btn btn-default btn-xs" id="button2" style="margin: 0 0 0 20px">关闭</button>
        </div>
        <!-- 工具栏部分end -->
    </div>
</form>
<!-- 表单部分end-->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/selInventoryExtendList.js?v=33'/>"></script>