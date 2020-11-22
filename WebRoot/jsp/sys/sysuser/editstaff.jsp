<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>如彼对账平台系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <script src="<c:url value='/javaScript/sys/sysuser/editstaff.js?v=31'/>"></script>
    <style>
        .checkboxThree input[type=checkbox] {
            visibility: hidden;
        }

        .checkboxThree {
            width: 65px;
            height: 24px;
            background: #666;
            /*margin: 20px 60px;*/
            border-radius: 12px;
            position: relative;
            margin-top: -5px;
        }

        /**
         * Create the text for the On position
         */
        .checkboxThree:before {
            content: 'on';
            position: absolute;
            top: 1px;
            left: 10px;
            height: 2px;
            color: #18a689;
            font-size: 14px;
        }

        /**
         * Create the label for the off position
         */
        .checkboxThree:after {
            content: 'off';
            position: absolute;
            top: 2px;
            left: 38px;
            height: 2px;
            color: #ddd;
            font-size: 14px;
        }

        /**
* Create the pill to click
*/
        .checkboxThree label {
            display: block;
            width: 30px;
            height: 18px;
            border-radius: 9px;

            -webkit-transition: all .5s ease;
            -moz-transition: all .5s ease;
            -o-transition: all .5s ease;
            -ms-transition: all .5s ease;
            transition: all .5s ease;
            cursor: pointer;
            position: absolute;
            top: 3px;
            z-index: 1;
            left: 5px;
            background: #ddd;
        }

        /**
         * Create the checkbox event for the label
         */
        .checkboxThree input[type=checkbox]:checked + label {
            left: 30px;
            background: #18a689;
        }
        .resetCodeBtn{
            position: absolute;
            left: 85px;
            top:3px;
            width: 80px;
            cursor: pointer;
        }

    </style>
</head>

<body style="overflow:hidden; ">
<!-- 表单部分begin -->
<form id="accountForm" class="form-horizontal form-bordered" role="form">
    <div class="panel panel-default">
        <!-- 隐藏域begin -->
        <input type="hidden" name="suiId" id="suiId" value="${obj.suiId}"/>
        <input type="hidden" name="createRen" id="createRen" value="${obj.createRen}"/>
        <input type="hidden" name="password" id="password" value="${obj.password}"/>
        <input type="hidden" name="createDate" value="${obj.createDate}"/>
        <input type="hidden" name="state" id="state" value="${obj.state}"/>
        <input type="hidden" name="barCode" id="barCode" value="${obj.barCode}"/>
        <input type="hidden" name="barCodeFileCode" id="barCodeFileCode" value="${obj.barCodeFileCode}"/>
        <input type="hidden" name="organizaType" id="organizaType" value="${obj.organizaType}"/>
        <input type="hidden" name="userType" id="userType" value="${obj.userType}"/>
        <input type="hidden" name="orgGxId" id="orgGxId" value="${obj.orgGxId}"/>
        <input type="hidden" name="userRole" id="userRole" value="${obj.userRole}"/>
        <!--隐藏区域end -->

        <!-- 正文部分begin -->
        <div class="panel-body scrollbarinfo">
            <div class="form-group" styel="">
                <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>用户编号</label>
                <div class="controls col-sm-3">
                    <input type="text" class="form-control" name="userCode" id="userCode" value="${obj.userCode}"
                           readonly=true>
                </div>
                <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>用户账号</label>
                <div class="controls col-sm-3">
                    <input type="text" class="form-control" onkeyup="checkAccount()" name="account" id="account"
                           value="${obj.account}"
                    <c:if test="${obj.account != null}"> readonly=true</c:if> placeholder="用户账号">
                    <span style="color:red;display:none" id="accountCheck">*该账号已存在</span>
                </div>
            </div>
            <div class="form-group" styel="">
                <label class="control-label col-sm-2"><span style="color:red;margin-right:5px">*</span>用户名称</label>
                <div class="controls col-sm-3">
                    <input type="text" class="form-control" name="userName" id="userName" value="${obj.userName}"
                           placeholder="用户名称">
                </div>
                <label class="control-label col-sm-2">手机号</label>
                <div class="controls col-sm-3">
                    <input type="text" class="form-control" name="phoneNumber" id="phoneNumber"
                           value="${obj.phoneNumber}">
                </div>
            </div>
            <div class="form-group" styel="">
                <label class="control-label col-sm-2">邮箱</label>
                <div class="controls col-sm-3">
                    <input type="text" class="form-control" name="email" id="email" value="${obj.email}"
                           placeholder="邮箱">
                </div>
                <label class="control-label col-sm-2">用户角色</label>
                <div class="controls col-sm-5" style="padding-top:8px">

                    <c:if test="${sessionScope.sessionUser.organizaType =='0'}">
                        <%--							<c:if test="${obj.userType == null || obj.userType == ''}">--%>
                        <%--								<input type="checkbox" name="userRoles" value="1"   checked=true  /> 供应商管理员&nbsp;&nbsp;--%>
                        <%--								<input type="checkbox" name="userRoles" value="2"    checked=true  /> 供应商员工&nbsp;&nbsp;--%>
                        <%--							</c:if>--%>
                        <%--							<c:if test="${obj.userType =='1'}">--%>
                        <input type="checkbox" name="userRoles" value="1" checked=true/> 供应商管理员&nbsp;&nbsp;
                        <%--							</c:if>--%>
                        <%--							<c:if test="${obj.userType !='1'}">--%>
                        <input type="checkbox" name="userRoles" value="2" checked=true/> 供应商员工&nbsp;&nbsp;
                        <%--							</c:if>--%>
                    </c:if>
                    <c:if test="${obj.organizaType =='100' && sessionScope.sessionUser.organizaType !='0'}">
                        <c:if test="${obj.userType =='1'}">
                            <input type="checkbox" name="userRoles" value="1" checked=true/> 供应商管理员&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${obj.userType !='1'}">
                            <input type="checkbox" name="userRoles" value="2" checked=true/> 供应商员工&nbsp;&nbsp;
                        </c:if>
                        <%--						  <input type="checkbox" name="openSecurity" value="5" /> 安全码--%>
                    </c:if>


                    <c:if test="${obj.organizaType =='20001'}">
                        <c:if test="${obj.userType =='1'}">
                            <input type="checkbox" name="userRoles" value="1" checked=true/> 集团管理员&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${obj.userType !='1'}">
                            <input type="checkbox" onclick="hidclick()" name="userRoles" value="2" <c:if
                                    test="${fn:contains(obj.userRole,'2')}"> checked=true </c:if>/> 采购员&nbsp;&nbsp;
                            <input type="checkbox" name="userRoles" value="3" <c:if
                                    test="${fn:contains(obj.userRole,'3')}"> checked=true </c:if>/> 仓库管理员&nbsp;&nbsp;
                            <input type="checkbox" name="userRoles" value="4" <c:if
                                    test="${fn:contains(obj.userRole,'4')}"> checked=true </c:if>/> 领料员&nbsp;&nbsp;
                        </c:if>
                    </c:if>
                    <c:if test="${obj.organizaType =='20002'}">
                        <c:if test="${obj.userType =='1'}">
                            <input type="checkbox" name="userRoles" value="1" <c:if
                                    test="${fn:contains(obj.userRole,'1')}"> checked=true </c:if>/> 医院管理员&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${obj.userType !='1'}">
                            <input type="checkbox" onclick="hidclick()" name="userRoles" value="2" <c:if
                                    test="${fn:contains(obj.userRole,'2')}"> checked=true </c:if>/> 采购员&nbsp;&nbsp;
                            <input type="checkbox" name="userRoles" value="3" <c:if
                                    test="${fn:contains(obj.userRole,'3')}"> checked=true </c:if>/> 仓库管理员&nbsp;&nbsp;
                            <input type="checkbox" name="userRoles" value="4" <c:if
                                    test="${fn:contains(obj.userRole,'4')}"> checked=true </c:if>/> 领料员&nbsp;&nbsp;
                        </c:if>

                    </c:if>

                    <c:if test="${obj.organizaType =='20003'}">
                        <c:if test="${obj.userType =='1'}">
                            <input type="checkbox" name="userRoles" value="1" <c:if
                                    test="${fn:contains(obj.userRole,'1')}"> checked=true </c:if>/> 门诊管理员&nbsp;&nbsp;
                        </c:if>

                        <c:if test="${obj.userType !='1'}">
                            <input type="checkbox" onclick="hidclick()" name="userRoles" value="2" <c:if
                                    test="${fn:contains(obj.userRole,'2')}"> checked=true </c:if>/> 采购员&nbsp;&nbsp;
                            <input type="checkbox" name="userRoles" value="3" <c:if
                                    test="${fn:contains(obj.userRole,'3')}"> checked=true </c:if>/> 仓库管理员&nbsp;&nbsp;
                            <input type="checkbox" name="userRoles" value="4" <c:if
                                    test="${fn:contains(obj.userRole,'4')}"> checked=true </c:if>/> 领料员&nbsp;&nbsp;
                        </c:if>
                    </c:if>

                </div>
            </div>
            <%--song 20200901 s--%>
            <c:if test="${fn:contains(obj.userRole,'2')}">
                <div class="form-group" style="display: block" id="hid1">
                    <label class="control-label col-sm-2">安全码</label>
                    <div class="controls col-sm-5" style="padding-top:8px">
                        <div class="checkboxThree">
                            <input type="checkbox" value="" id="checkboxThreeInput" name="openSecurity" <c:if
                                    test="${fn:contains(obj.openSecurity,'1')}"> checked </c:if> />
                            <label for="checkboxThreeInput"></label>
                            <div style="margin-top: 10px;width: 131px" >
                                <span style="color: grey">默认安全码：123456</span>
                            </div>
                            <c:if test="${fn:contains(obj.openSecurity,'1')}">
                            <div class="resetCodeBtn" onclick="resetSafetyCode(${obj.suiId})">重置安全码</div>
                            </c:if>
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="form-group" style="display: none" id="hid1">
                <label class="control-label col-sm-2">安全码</label>
                <div class="controls col-sm-5" style="padding-top:8px">
                    <div class="checkboxThree">
                        <input type="checkbox" value="" id="checkboxThreeInput" name="openSecurity" <c:if
                                test="${fn:contains(obj.openSecurity,'1')}"> checked </c:if> />
                        <label for="checkboxThreeInput" onclick="labelClick()"></label>
                    </div>
                    <div style="margin-top: 10px;display: none" id="hid1s">
                        <span style="color: grey">默认安全码：123456</span>
                    </div>
                </div>
            </div>
            <%--song 20200901 e--%>
        </div>
        <!-- 正文部分end -->
        <!-- 工具栏部分begin -->
        <div class="panel-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4" style="margin-left: 310px">
                    <button type="button" onclick="save()" class="btn btn-primary">保存</button>
                    <button type="button" onclick="closeWin()" class="btn btn-default">关闭</button>
                </div>
            </div>
        </div>
        <!-- 工具栏部分end -->
    </div>
</form>
<!-- 表单部分end-->
</body>
</html>