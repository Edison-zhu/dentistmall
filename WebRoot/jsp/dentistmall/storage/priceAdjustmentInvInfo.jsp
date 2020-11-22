<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/5/25
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <title>库存盘点</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?v=2' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>

</head>
<style>
    .ibox-tools button{
        width: 125px;
    }
    #tables{
        border-radius: 0px;
        background-clip: padding-box;
        width: 350px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        /*display: none;*/
        z-index:999;
        position:fixed;
        width: 1164px;
        margin-left: 192px;
    }
</style>

<body class="gray-bg" >
<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
    <!-- 标题栏区域 begin-->
    <div class="ibox-title">
        <!-- 标题文字区域begin -->
        <h5>
            <i class="fa fa-flask"></i>&nbsp&nbsp<span>调价维护</span>
        </h5>
        <!-- 标题文字区域end -->
        <div class="ibox-tools" id="win_tools_but">
        </div>
    </div>

    <!-- 操作栏 -->
    <div>
        <div class="ibox-content" style="padding: 13px 10px 10px;">
            <div style="display: inline-block">
                <div class="form-inline" style="margin-bottom:0px;">
                    <form id="saveForm">
                        <label class="control-label">调价号<span
                                class="text-danger">(*)</span>：</label>
                        <input id="paiCodeForm" name="paiCode" readonly class="form-control" placeholder="调价号"
                               type="text" aria-required="true" aria-invalid="false">
                        <label class="control-label" style="margin-left: 28px;">制作人<span
                                class="text-danger">(*)</span>：</label>
                        <input id="createRenForm" name="createRen" class="form-control" placeholder="制作人" value="${sessionScope.sessionUser.userName}"
                               type="text" readonly aria-required="true" aria-invalid="false">
                        <label class="control-label" style="margin-left: 28px;">调价类型<span
                                class="text-danger">(*)</span>：</label>
                        <select type="select" class="form-control" name="paiType" id="paiTypeForm">
                            <option value="">选择调价类型</option>
                            <option value="1">市场调价</option>
                            <option value="2">正常调价</option>
                        </select>
                        <label class="control-label" style="margin-left: 28px;">调价比例
<%--                            <span class="text-danger">(*)</span>--%>
                            ：</label>
                        <input type="text" onKeyUp="clearNoNum(event,this)"class="form-control" onblur="changePercent(this.value)" name="paiPercent"
                               id="paiPercentForm"/>%
                        <label class="control-label" style="margin-left: 28px;">备注：</label>
                        <input type="text" class="form-control" name="remark" id="remarkForm" placeholder="50字以内" style="width: 260px"/>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 表格栏 -->

    <div class="ibox-content" style="padding: 5px 10px 10px;">
<%--        <table id="win_datagrid_main" class="mmg" style="height: 400px"></table>--%>
<%--        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>--%>
    <div class="container33" id="mx2" style="width: 100%;height: auto; background-color: white;margin-top: 30px;">
        <table class="table invoice-table" style="width: 90%;">
            <thead style="border: 1.5px solid #F0F0F0;">
            <tr>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">物料编号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">物料名称</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">品牌</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">规格/型号编码</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">调价单位</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">采购均价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">原零售价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">现零售价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">差价</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">调价比例</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">批号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">注册号/备案号</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">库存数量</th>
                <th style="text-align:center;font-size: 12px;border: 1.5px solid #F0F0F0;">操作</th>
            </tr>
            </thead>
            <tbody id="mxMatListMx">
            </tbody>
        </table>
    </div>
        <div>
            <input id="searchName1" value="" placeholder="输入物料编号或名称" style="border: 1px solid #e5e6e7;padding: 6px 12px;"  onkeyup="showTable(this.value)"/>
<%--            <div id="searchInvBtn" onclick="searchInventory()" onkeyup="searchInventory()" style="display: inline-block;width: 80px;height: 25px;background: #18a689;text-align: center;line-height: 25px;color: #ffffff;border-radius: 5px;margin-left: 10px;cursor: pointer">选择</div>--%>
            <div id="tables" style="position: fixed;display: none;top: 140px;height: 600px">
                <form id="queryForm" style="display: inline">
                    <div id="searchForm" class="form-inline" style="display: inline">
                        <label style="font-size: 20px;margin-left: 22px;color: #0fabbc;padding-top: 10px">高级搜索</label>
<%--                        <input type="text" id="searchName" onkeyup="enterSearch()" class="form-control" placeholder="关键字查询"--%>
<%--                               name="searchName" value=""/>--%>
                    </div>
                </form>
<%--                <button id="btnSearch" style="display: inline; margin-left: 20px;width: 80px;height: 28px;border-radius: 5px;background: #1ab394;text-align: center;line-height: 28px;border: none;color: #fff;margin-left: 0;"--%>
<%--                        onclick="search(true)">--%>
<%--                    查询--%>
<%--                </button>--%>
                    <!-- 标题栏区域 begin-->
<%--                    <div class="ibox-title">--%>
                        <div style="border-bottom: 1px solid #e7eaec;border-top: 4px solid #e7eaec;padding: 14px 15px 7px;min-height: 48px;">
                        <!-- 标题文字区域begin -->
                        <h5><i class="fa fa-sitemap"></i>&nbsp&nbsp分类信息</h5>
                        <!-- 标题文字区域end -->
                        <!-- 标题栏工具区域begin -->
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 操作区域 begin-->
                    <div class="" style="padding: 0px;width: 200px;border: none;position: absolute;left:5px;top:78px;">
                        <div class="scrollbarlist" style="height: 500px">
                            <div class="panel-body">
                                <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                            </div>
                        </div>
                    </div>
                    <!-- 操作区域 end-->
                <div style="position: absolute;left:200px;top: 95px;width: 930px">
                <table id="datagrid1" class="mmg" style="width:50%; margin-top: -421px;margin-left: 111px"></table>
                <div id="pg" style="text-align: right;"></div>

                </div>
            </div>
            <div style="line-height: 50px">合计：总共<span id="allCount">0</span>条数据，总体调价比例<span id="totalPricePercent">0</span>%
            </div>
        </div>
    </div>
</div>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/priceAdjustmentInvInfo.js?v=67'/>"></script>
<script src="<c:url value='/javaScript/dentistmall/storage/priveTableData.js?v=32'/>"></script>
<script>
    function clearNoNum(event, obj) {
        console.log("1111")
        //响应鼠标事件，允许左右方向键移动
        event = window.event || event;
        if (event.keyCode == 37 | event.keyCode == 39) {
            return;
        }
        var t = obj.value.charAt(0);
        //先把非数字的都替换掉，除了数字和.
        obj.value = obj.value.replace(/[^\d.]/g, "");
        //必须保证第一个为数字而不是.
        obj.value = obj.value.replace(/^\./g, "");
        //保证只有出现一个.而没有多个.
        obj.value = obj.value.replace(/\.{2,}/g, ".");
        //保证.只出现一次，而不能出现两次以上
        obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
        //如果第一位是负号，则允许添加   如果不允许添加负号 可以把这块注释掉
        if (t == '-') {
            obj.value = '-' + obj.value;
        }
    }
</script>
