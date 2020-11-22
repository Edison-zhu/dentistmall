<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>基础平台系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <style>
        .color{
            background-color: red;
            width: 200px;
            height: 100px;
        }
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
        }

        #button1 {
            width: 95px;
            height: 25px;
        }
        .table {

        }
        .tr{

        }
        .rtd{
            padding: 20px !important;
            text-align: right;
            width: 10%;
        }
        .ltd{
            width: 10%;
        }
        /*2020年07月06日15:45:13朱燕冰修改 */
        .btn1{
            cursor: pointer;
            width: 130px;
            height: 28px;
            border: 1px solid #1ab394;
            background-color: #1ab394;
            font-size: 14px;
            color: white;
            border-radius: 14px;
            margin-right: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .input1{
            border: 1px solid #bcbcbc;
            outline: none;
            padding-left: 5px;
        }
        #gridDiv::-webkit-scrollbar {
            /*滚动条整体样式*/
            width : 10px;  /*高宽分别对应横竖滚动条的尺寸*/
            height: 10px;
        }
        #gridDiv::-webkit-scrollbar-thumb {
            /*滚动条里面小方块*/
            border-radius   : 10px;
            background-color: #D3D3D3;
            background-image: -webkit-linear-gradient(
                    45deg,
                    rgba(255, 255, 255, 0.2) 25%,
                    transparent 25%,
                    transparent 50%,
                    rgba(255, 255, 255, 0.2) 50%,
                    rgba(255, 255, 255, 0.2) 75%,
                    transparent 75%,
                    transparent
            );
        }
        #gridDiv::-webkit-scrollbar-track {
            /*滚动条里面轨道*/
            box-shadow   : inset 0 0 5px rgba(0, 0, 0, 0.1);
            background   : #ededed;
            border-radius: 10px;
        }
        .selectbtn1 {
            width: 60px;
            height: 25px;
            background: #1ab394;
            border-radius: 11px;
            border: 1px solid #1ab394;
            text-align: center;
            line-height: 22px;
            color: #fff;
            font-size: 12px;
            margin-left: 5px;
        }
    </style>
</head>

<body style="overflow:hidden; ">
<!-- 表单部分begin -->

<div class="form-group" style="float:right;position: absolute;bottom: 2px;right: 30px;display: inline">
    <div id="win_edit_toolbar">
    </div>
</div>
<div class="panel panel-default">
    <!-- 隐藏域begin -->
    <div id="win_form_edithidden"></div>
    <!--隐藏区域end -->
    <!-- 正文部分begin -->
    <div class="panel-body scrollbarinfo">
        <div style="background:rgba(245,245,245,0.6);padding: 10px 20px;">
            <div style="display: flex;justify-content: flex-start;align-items: center" id="colors">
                <div type="button" class="btn1" onclick="outEnter(0)" id="colors1">领料出库</div>
                <div type="button" class="btn1" onclick="outEnter(1)"id="colors2">退货出库</div>
                <div type="button" class="btn1" onclick="outEnter(2)"id="colors3">报损出库</div>
            </div>
            <div id="outOrder" style="line-height: 3;display: block;margin-top: 10px">
                <form id="saveCkForm" style="display: flex;width: 100%; justify-content: space-between;align-items: center">
						<span>
							出库编号：<input type="text" readonly id="wowCode" name="wowCode" style="width: 200px;height: 25px;" class="input1"/>
						</span>
                        <span>
							制作人：<input type="text" readonly id="createRen" name="createRen" style="width: 200px;height: 25px;" value="${sessionScope.sessionUser.userName}" class="input1"/>
						</span>
                        <span>
							出库类型：<span id="outType"></span>
						</span>
                        <span>
							接收对象：<input type="text" id="receivingObject" name="receivingObject" style="width: 200px;height: 25px;" class="input1"/>
						</span>
                        <span>
							出库备注：<input type="text" id="outRemark" name="wowRemarks" style="width: 200px;height: 25px;" class="input1"/>
						</span>
                </form>
            </div>
            <div style="display: block;height: auto">
                <form id="accountForm" class="form-horizontal form-bordered"
                      role="form">
                    <!-- 领料出库 -->
                    <div id="win_form_body" style="display: none">
                        <h3>申领信息</h3>
                    </div>
                    <!-- 退货出库 -->
                    <div id="afterSale" style="line-height: 3;display: none">
                        <table class="table">
                            <tr class="tr">
                                <td class="rtd">入库单号:</td>
                                <td class="ltd"><span id="billCode" name="billcode" style="width: 140px;height: 25px;"></span></td>
                                <td class="rtd">入库类型:</td>
                                <td class="ltd"><span id="billType" name="billType" style="width: 140px;height: 25px;"></span></td>
                                <td class="rtd">入库数量:</td>
                                <td class="ltd"><span id="expectNumber" name="expectNumber"></span></td>
                                <td class="rtd">发票号:</td>
                                <td class="ltd"><span id="invoiceCode" name="invoiceCode" style="width: 140px;height: 25px;"></span></td>
                            </tr>
                            <tr class="tr">
                                <td class="rtd">入库日期:</td>
                                <td class="ltd"><span id="receiptDatetime" name="receiptDatetime" style="width: 140px;height: 25px;"></span></td>
                                <td class="rtd">制作人:</td>
                                <td class="ltd"><span id="createRenEnter" name="createRen" style="width: 140px;height: 25px;"></span></td>
                                <td class="rtd">采购/零售价格:</td>
                                <td class="ltd"><span id="purchaseMoney" name="purchaseMoney" style="width: 140px;height: 25px;"></span>/<span id="retailMoney"></span></td>
                                <td class="rtd">入库说明:</td>
                                <td class="ltd"><span id="warehousingRemarks" name="warehousingRemarks" style="width: 140px;height: 25px;"></span></td>
                            </tr>
                        </table>
                    </div>
                </form>
            </div>
            <!-- 退货出库 -->

        </div>

        <div class="ibox float-e-margins" style="border:1px solid #e7eaec;margin-top:5px">

            <div id="gridDiv" style="width: 100%;overflow: auto;">
               <table id="datagrid1" class="mmg"></table>
            </div>
        </div>
    </div>
    <!-- 正文部分end -->

    <!-- 工具栏部分begin -->
    <div class="panel-footer">
        <div class="collectText">
            汇总：共计商品<span id="collectCount">0</span>条，申请总数量合计<span id="collectAllCount">0</span>，已选择出库数量<span
                id="collectReadyAllCount" style="color: red;">0</span>
        </div>

    </div>
    <!-- 工具栏部分end -->
</div>
<input type="hidden" id="relatedBill1"/>
<!-- 表单部分end-->
</body>

</html>
<script src="<c:url value='/javaScript/dentistmall/storage/newApplyMdOutWarehouser.js?v=155'/>"></script>