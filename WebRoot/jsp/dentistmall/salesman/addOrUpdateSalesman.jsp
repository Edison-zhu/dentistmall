<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/1/3
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>牙医商城平台系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
</head>
<style>
    .content-left {
        display: inline-block;
        width: 49%;
        /*float: left;*/
    }

    .content-right {
        display: inline-block;
        /*width: 49%;*/
        /*float: left;*/
    }

    .required {
        color: red;
    }

    .div-float {
        display: inline-block;
        width: 49%;
        float: left;
    }

    .ibox {
        clear: none;
    }

    .panel {
        border-radius: 20px;
        width: 80%;
        margin: 0 auto;
        margin-top: 5%;
    }
</style>
<body class="gray-bg">
<div class="panel">
    <div class=" panel-body">

        <form id="searchForm" class="form-horizontal form-bordered" style="margin-top: 10px" role="form"
              style="padding-left: 10px">
            <div id="win_form_edithidden"></div>
            <div class="ibox float-e-margins div-float"
                 style="display: flex; flex-direction: column;justify-content: flex-start">

                <div class="form-group">
                    <label class="col-sm-2 control-label">账号<span class="required">(*)</span>：</label>
                    <div class="col-sm-8">
                        <input class="form-control" placeholder="请输入账号" name="salesAccount" id="salesAccount"
                               value="${obj.salesAccount}"/>
                        <div style="color: lightgrey;margin-top: 5px;">初始登录密码：123456</div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">类型<span class="required">(*)</span>：</label>
                    <div class="col-sm-8">
                        <input type="hidden" id="salesTypeHidden" value="${obj.salesType}"/>
                        <input type="hidden" id="userType" value="${sessionScope.sessionUser.userType}"/>
                        <c:if test="${sessionScope.sessionUser.userType != 6}">
                            <label id="saleType6" class="">
                                <input type="radio" id="salesMan" checked value="1" name="salesTypeRadio"
                                       onclick="hideCompany()">机构门诊业务<!--业务员-->
                            </label>
                        </c:if>
                        <label class="" style="margin-left: 20px">
                            <input type="radio" id="agent" value="2" name="salesTypeRadio" onclick="showCompany()">代理商
                        </label>
                        <c:if test="${sessionScope.sessionUser.userType != 6 && sessionScope.sessionUser.organizaType == 0 && sessionScope.sessionUser.userType!=3}">
                            <label class="" style="margin-left: 20px">
                                <input type="radio" id="supplierSales" value="3" name="salesTypeRadio"
                                       onclick="hideCompany()">供应商业务
                            </label>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">姓名<span class="required">(*)</span>：</label>
                    <div class="col-sm-8">
                        <input class="form-control" placeholder="请输入姓名" name="salesName" id="salesName"
                               value="${obj.salesName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">所在区域<span class="required">(*)</span>：</label>
                    <div class="col-sm-8 form-inline">
                        <input type="hidden" id="provinceHidden" value="${obj.province}"/>
                        <input type="hidden" id="cityHidden" value="${obj.city}"/>
                        <input type="hidden" id="areaHidden" value="${obj.area}"/>
                        <select id="province" name="province" class="form-control" placeholder="所在省"
                                value="${obj.province}">
                            <option value="">所在省</option>
                        </select>
                        <select id="city" name="city" class="form-control" placeholder="所在市" value="${obj.city}">
                            <option value="">所在市</option>
                        </select>
                        <select id="area" name="area" class="form-control" placeholder="所在地区" value="${obj.area}">
                            <option value="">所在地区</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">代理编号：</label>
                    <div class="col-sm-8">
                        <input type="hidden" class="form-control" placeholder="" name="salesCode" id="salesCode"
                               readonly
                               value="${obj.salesCode}"/>
                        <span id="salesCodeSpan" style="line-height: 30px;">${obj.salesCode}</span>
                    </div>
                </div>
            </div>
            <div class="ibox float-e-margins div-float"
                 style="display: flex; flex-direction: column;justify-content: flex-start">

                <div class="form-group">
                    <label class="col-sm-2 control-label">状态<span class="required">(*)</span>：</label>
                    <div class="col-sm-8 form-inline">
                        <input type="hidden" id="salesState" value="${obj.state}"/>
                        <input type="radio" class="form-control" name="stateRadio" id="stateOn" value="启用"
                               checked/><label
                            id="stateOnLabel"
                            for="stateOn">启用</label>
                        <input type="radio" class="form-control" name="stateRadio" id="stateOff" value="禁用"/><label
                            id="stateOffLabel"
                            for="stateOff">禁用</label>
                    </div>
                </div>
                <div class="form-group" id="salesCompanyShow" <c:if
                        test="${obj.salesType != 2 && sessionScope.sessionUser.userType != 6}"> style="display: none" </c:if>>
                    <label class="col-sm-2 control-label">公司名称<span class="required">(*)</span>：</label>
                    <div class="col-sm-8">
                        <input class="form-control" placeholder="请输入公司名称，最长50字" maxlength="50" name="agentCompany"
                               id="agentCompany"
                               value="${obj.agentCompany}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号码<span class="required">(*)</span>：</label>
                    <div class="col-sm-8">
                        <input class="form-control" placeholder="请输入手机号码" name="salesPhone" id="salesPhone"
                               value="${obj.salesPhone}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">详细地址：</label>
                    <div class="col-sm-8">
                        <input class="form-control" placeholder="请输入详细地址，最长50字" name="salesAddress" id="salesAddress"
                               maxlength="50"
                               value="${obj.salesAddress}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">微信ID：</label>
                    <div class="col-sm-8">
                        <input class="form-control" placeholder="" name="salesWechat" id="salesWechat" readonly
                               value="${obj.salesWechat}"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-1 control-label"></label>
                <div class="col-sm-9">

                </div>
            </div>
            <div style="display: flex; flex-direction: column;justify-content: flex-start">
                <div class="form-group" style="flex: 1">
                    <label class="col-sm-1 control-label">描述：</label>
                    <div class="col-sm-9">
                    <textarea class="form-control" placeholder="最长100字" name="describes" id="describes" maxlength="100"
                              value="">${obj.describes}</textarea>
                    </div>
                </div>
                <div class="form-group" id="leaderShow"
                     <c:if test="${obj.salesType != 2 && sessionScope.sessionUser.userType != 6}">style="display: none"</c:if>>
                    <label class="col-sm-1 control-label">上级<span class="required">(*)</span>：</label>
                    <div class="col-sm-9">
                        <input id="bindSalesMan" readonly class="form-control" placeholder="选择上级业务员" name="leaderName"
                                <c:if test="${sessionScope.sessionUser.userType == 6}"> value="${sessionScope.sessionUser.userName}" </c:if>
                                <c:if test="${sessionScope.sessionUser.userType != 6}"> value="${obj.leaderName}" onclick="openSalesMan()" </c:if>/>
                        <input type="hidden" id="bindSalesmanId" name="leaderId" value="${obj.leaderId}"/>
                    </div>
                </div>
            </div>
            <input type="hidden" id="salesmanId" value="${obj.salesmanId}"/>
            <input type="hidden" id="rbaId" value="${obj.rbaId}"/>
            <input type="hidden" id="rbsId" value="${obj.rbsId}"/>
            <input type="hidden" id="rbbId" value="${obj.rbbId}"/>
        </form>
        <div class="form-group" style="clear:both;">
            <div style="float: right;margin-right: 140px;">
                <button class="btn btn-primary" onclick="saveSalesman()">保存</button>
                <button class="btn btn-primary" onclick="closeWin()">取消</button>
            </div>
        </div>
    </div>
</div>

</body>
</html>
<script>
    var _user_name = "${sessionScope.sessionUser.userName}";
    var _user_org_type = "${sessionScope.sessionUser.organizaType}";
    var _user_type = "${sessionScope.sessionUser.userType}";
    var _user_role = "${sessionScope.sessionUser.userRole}";
</script>
<script src="<c:url value='/javaScript/dentistmall/salesman/addOrUpdateSalesman.js?v=26'/>"></script>
