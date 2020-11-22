<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>基础平台系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
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
        }

        #button1 {
            width: 95px;
            height: 25px;
        }
        .input1{
            width: 200px;
            height: 35px;
            border: 1px solid #bcbcbc;
            outline: none;
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
    </style>
</head>

<body style="overflow:hidden; ">
<!-- 表单部分begin -->
<form id="accountForm" class="form-horizontal form-bordered"
      role="form">
    <div class="panel panel-default">
        <!-- 隐藏域begin -->
        <div id="win_form_edithidden"></div>
        <!--隐藏区域end -->
        <!-- 正文部分begin -->
        <div class="panel-body scrollbarinfo">
            <div style="height: 50px;width:100%;background:rgba(245,245,245,0.6);">
                <form id="saveForm" style="display: flex;width: 100%;height:100%;">
                    <div style="display: flex;width: 100%;height:100%;align-items: center">
						<span style="flex: 1;text-align: center">
							出库编号：<input type="text" readonly id="wowCode" name="wowCode" style="width: 200px;height: 25px;" class="input1">
						</span>
                        <span style="flex: 1;text-align: center">
							制作人：<input type="text" readonly id="createRen1" style="width: 200px;height: 25px;" class="input1" value="${sessionScope.sessionUser.userName}">
						</span>
<%--                        <span style="flex: 1;text-align: center">--%>
<%--							出库类型:<span>科室领料</span>--%>
<%--						</span>--%>
                        <span style="flex: 1;text-align: center">
							接收对象：<input type="text" id="receivingObject" name="receivingObject"
                                        style="width: 200px;height: 25px;" class="input1"/>
						</span>
                        <span style="flex: 1;text-align: center">
							出库备注：<input type="text" id="outRemark" name="wowRemarks"
                                        style="width: 200px;height: 25px;" class="input1"/>
						</span>
                    </div>
                </form>
            </div>
            <div id="win_form_body">
                <h3>申领信息</h3>
            </div>
            <div class="ibox float-e-margins" style="border:1px solid #e7eaec;margin-top:5px">
                <!--标题栏区域 begin-->
                <div class="ibox-title"
                     style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:45px;margin-bottom:10px">
                    <!-- 标题文字区域begin -->
                    <div id="hide">
                        <h5>&nbsp&nbsp申领明细信息表&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp 商品名称：
                            <input type="text" id="spmc" value="" style="width: 200px;height: 25px;" class="input1" onkeydown="entersearch()"/>
                            规格：<input type="text" id="gg" value="" style="width: 200px;height: 25px;" class="input1" onkeydown="entersearch()"/>
                            <a onclick="win_form_search()" class='btn btn-success  btn-xs' style="color:#fff"
                               id="button1">搜索</a>
                            <!--  <a onclick="loadGrid(mooId)"  class='btn btn-success  btn-xs' style="color:#fff">导出对账单</a> -->
                        </h5>
                        <script>
                            function entersearch(){
                                var event = window.event || arguments.callee.caller.arguments[0];
                                if (event.keyCode == 13)
                                {
                                    win_form_search();
                                }
                            }
                        </script>
                        <!--
                         -->                            <!-- 标题文字区域end -->
                        <div class="ibox-tools">
                        </div>
                    </div>
                </div>
                <div id="gridDiv" style="width: 100%;overflow: auto;">
                    <table id="datagrid1" class="mmg"></table>
                </div>
                <!-- !--表格区域begin -->

                <!--!--表格区域end-->

            </div>
        </div>
        <!-- 正文部分end -->

        <!-- 工具栏部分begin -->
        <div class="panel-footer">
            <div class="collectText">
                汇总：共计商品<span id="collectCount">0</span>条，申请总数量合计<span id="collectAllCount">0</span>，已选择出库数量<span
                    id="collectReadyAllCount" style="color: red;">0</span>
            </div>
            <div class="form-group" style="float:right;position: absolute;bottom: 2px;right: 30px;display: inline">
                <div id="win_edit_toolbar">
                </div>
            </div>
        </div>
        <!-- 工具栏部分end -->
    </div>
</form>
<!-- 表单部分end-->
</body>

</html>
<script src="<c:url value='/javaScript/dentistmall/storage/editApplyMdOutWarehouser.js?v=165'/>"></script>