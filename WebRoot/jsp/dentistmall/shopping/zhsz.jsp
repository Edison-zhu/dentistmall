<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp" %>
</head>
<script>
    var _isFix = 1;
    var _isCar = 2;
</script>
</head>
<style>
    table {

    }

    table tr {
        padding-top: 20px;
    }

    table tr td {
        padding-top: 20px;
        padding-left: 10px;
    }

    .td-right {
        padding-left: 70px;
    }
</style>
<body>
<header class="header">
    <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?16' />">
<p class="bread-crumb w1080">
    <i class="icon-homepage"></i>
    <label>
        <a href="index.htm">
            首页
        </a>
    </label>
    <label>
        账号设置
    </label>
</p>
<div class="content">
    <div class="shopping-cart-body w1080">
        <div class="shopping-cart-header">
            <div class="shopping-flow">
                <div class="shopping-cart-icon">
                    <img src="<c:url value='/css/shopping/images/shopping-order.png'/>">
                    <span class="cn">账号设置:</span>
                    <span class="un">Account Settings</span>
                    <a class="taps" href="qhtx.htm">缺货提醒<strong id="newsCount"></strong></a>
                    <a class="taps" href="scj.htm">我的收藏夹</a>
                    <a class="taps" href="qgc.htm">弃购车</a>
                    <a class="taps" href="ddlb.htm">查询订单</a>
                    <a class="taps active" href="zhsz.htm">账号设置</a>
                </div>
            </div>
        </div>
        <div class="shopping-cart-content">
            <div class="content-taps">
                <a class="taps-item-active" href="zhsz.htm">个人信息</a>
                <a class="taps-item" href="upass.htm">密码修改</a>

                    <a class="taps-item" href="upsafetycode.htm">安全码修改</a>

            </div>
            <div class="conen_qw">
                <div id="div1" style="display :block; border:0px;" class="divContent1">
                    <form id="accountForm">
                        <input type="hidden" name="suiId" id="suiId" value="${userInfo.suiId}"/>
                        <input type="hidden" name="createRen" id="createRen" value="${userInfo.createRen}"/>
                        <input type="hidden" name="password" id="password" value="${userInfo.password}"/>
                        <input type="hidden" name="createDate" value="${userInfo.createDate}"/>
                        <input type="hidden" name="state" id="state" value="${userInfo.state}"/>
                        <input type="hidden" name="barCode" id="barCode" value="${userInfo.barCode}"/>
                        <input type="hidden" name="barCodeFileCode" id="barCodeFileCode"
                               value="${userInfo.barCodeFileCode}"/>
                        <input type="hidden" name="userType" id="userType" value="${userInfo.userType}"/>
                        <input type="hidden" name="orgGxId" id="orgGxId" value="${userInfo.orgGxId}"/>
                        <input type="hidden" name="organizaType" id="organizaType" value="${userInfo.organizaType}"/>
                        <input type="hidden" name="oldId" id="oldId" value="${userInfo.oldId}"/>
                        <input type="hidden" name="userRole" id="userRole" value="${userInfo.userRole}"/>
                        <table width="" border="0" style="margin-top:15px;">
                            <tr>
                                <td height="">
                                    <div align="right" class="zitiwe">账号：</div>
                                </td>
                                <td width="">
                                    <div><input type="text" class="form-control" name="account" id="account"
                                                value="${userInfo.account}" readonly=true style="width:164px;"/></div>
                                </td>
                                <td class="td-right" height="">
                                    <div align="right" class="zitiwe">姓名：</div>
                                </td>
                                <td width="">
                                    <div><input type="text" class="form-control" name="userName" id="userNames"
                                                value="${userInfo.userName}" style="width:164px;" placeholder="姓名"/>
                                    </div>
                                </td>

                            </tr>

                            <tr>
                                <td width="" height="">
                                    <div align="right" class="zitiwe">手机号：</div>
                                </td>
                                <td width="">
                                    <div><input type="text" class="form-control" name="phoneNumber" id="phoneNumber"
                                                value="${obj.phoneNumber}" style="width:164px;" placeholder="手机号"/>
                                </td>
                                <td class="td-right" width="" height="">
                                    <div align="right" class="zitiwe">邮箱：</div>
                                </td>
                                <td width="">
                                    <div><input type="text" class="form-control" name="email" id="email"
                                                value="${obj.email}" style="width:164px;" placeholder="邮箱"/></div>
                                </td>

                            </tr>
                            <tr>
                                <td width="" height="">
                                    <div align="right" class="zitiwe">单位：</div>
                                </td>
                                <td width="">
                                    <div><input type="text" class="form-control" value="${userInfo.orgName}"
                                                readonly=true style="width:164px;" placeholder="所属单位"/></div>
                                </td>
                                <td class="td-right" width="" height="">
                                    <div align="right" class="zitiwe">第三方账号绑定：</div>
                                </td>
                                <td width="">
                                    <div><input type="text" class="form-control" name="thirdParty" id="thirdParty"
                                                value="${userInfo.thirdParty}" style="width:164px;" placeholder="微信号"/>
                                    </div>
                                </td>
                                <%--
    <c:if test="${obj.thirdParty!=null}">--%>
                                <%--									<td width=""><div><input type="text" class="form-control"  name= "thirdParty" id="thirdParty" value="${obj.thirdParty}" style="width:164px;" placeholder="${obj.thirdParty}"/></div></td>--%>
                                <%--								</c:if>--%>
                            </tr>
                            <tr>
                                <td colspan="4"><a href="javascript:save()" class='btn btn-success  btn-sm'>保存</a></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
</body>
</html>
<script type="text/javascript">
    $(function () {
        initQhCount();
        var timer1 = window.setTimeout(initQhCount, 5 * 60 * 1000);
    });
    function save() {
        var userNames = $("#userNames").val();
        if (userNames == null || userNames == "") {
            $.supper("alert", {title: "操作提示", msg: "姓名不能为空！"});
            return;
        }
        var data = $('#accountForm').serialize();
        $.supper("doservice", {
            "service": "SysUserService.saveWebSysUser",
            "ifloading": 1,
            "options": {"type": "post", "data": data},
            "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    $.supper("alert", {
                        title: "操作提示", msg: "操作成功！", BackE: function () {
                            window.location.reload();
                        }
                    });
                } else
                    $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        });
    }

    function initQhCount() {
        $.supper("doservice", {
            "service": "MdNewsInfoService.getInventoryNewList", "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    if (jsondata.obj != null) {
                        if (jsondata.obj.length > 0)
                            $("#newsCount").html(jsondata.obj.length);
                        else
                            $("#newsCount").html("");
                    }

                }
            }
        });
    }

</script>
