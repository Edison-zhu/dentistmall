<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2019/12/24
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="<c:url value='/jsp/util/ordermxutil/ordermxutil.css?v=2' />">
<script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
<div class="content">
    <div class="shopping-cart-body w1080">
        <div class="shopping-cart-header">
            <div class="shopping-flow">
                <div class="shopping-cart-icon">
                    <img src="<c:url value='/css/shopping/images/shopping-list.png'/>">
                    <span class="cn">订单信息：</span>
                    <span class="un">View order info</span>
                </div>
            </div>
        </div>
        <div class="shopping-cart-content">
            <div>
                <div style="margin-left: 3%;float: left; margin-top: 0px;font-size: 20px">
                    <b>订单状态：${obj.processStatusName}</b></div>
                <div style="float: right;margin-right: 2.6%;text-align: center">
                    <a id="backTo" class="btn btn-primary btn-xs" onclick="backTo()" style="display: none;width: 95px;height: 22px;margin-right: -5px;margin-bottom: 10px">返回</a>
                    <c:if test="${sessionScope.sessionUser.organizaType == '100'}">
                        <c:if test="${obj.processStatus == 1}"> <!-- 待发货 -->
                            <a class="btn btn-primary btn-xs" onclick="sendMat('${obj.moiId}')" style="width: 95px;height: 22px;margin-right: -5px;margin-bottom: 10px">立即发货</a><br/>
                        </c:if>
                        <c:if test="${obj.processStatus == 2}"> <!-- 待付款 -->
                            等待买家付款……
                        </c:if>
                        <c:if test="${obj.processStatus == 3}"> <!-- 待收货 -->
                            等待买家确认收货……
                        </c:if>
                        <c:if test="${obj.processStatus == 4}"> <!-- 已发货/部分发货 -->
                            等待买家确认收货……<br/>
                            <c:if test="${sessionScope.sessionUser.organizaType == '100'}">
                                <a class="btn btn-primary btn-xs" onclick="sendMat('${obj.moiId}')" style="width: 95px;height: 22px;margin-right: -100px;margin-bottom: 10px">立即发货</a><br/>
                            </c:if>
                        </c:if>
                        <c:if test="${obj.processStatus == 5}"> <!-- 交易成功 -->
                            <%--                            <a class="btn btn-primary" style="margin-top: 10px;"--%>
                            <%--                               href="javascript=buyAgain(${obj.moiId})">再次购买</a>--%>
                        </c:if>
                        <c:if test="${obj.processStatus == 6}"> <!-- 交易失败 -->
                            <%--                            <a class="btn btn-primary" style="margin-top: 10px;"--%>
                            <%--                               href="javascript=buyAgain(${obj.moiId})">再次购买</a>--%>
                        </c:if>
                        <c:if test="${obj.afterSale==1}"> <!-- 售后 obj.afterSale!=null&&-->
                            <a class="btn btn-primary btn-xs" style="width: 95px;height: 22px;"
                               onclick="viewSupplierOrderAs(${obj.moiId})">售后信息</a>
                        </c:if>
                    </c:if>
                    <c:if test="${sessionScope.sessionUser.organizaType == '20001' || sessionScope.sessionUser.organizaType == '20002' || sessionScope.sessionUser.organizaType == '20003'}">
                        <div class="ibox-tools" id="win_tools_but" style="margin-right: -15px; text-align: right;padding-right: 5px;">
                            <a onclick="main_exportC()" class='btn btn-success  btn-xs' style="color:#fff;margin-bottom: 10px;margin-right:10px;width: 95px;height: 22px"
                           id="button2">导出详情</a>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.sessionUser.organizaType == '100' || sessionScope.sessionUser.organizaType == '0' }">
                        <div class="ibox-tools" id="win_tools_but" style="margin-right: -15px; text-align: right;padding-right: 5px;">
                            <a onclick="main_exportC()" class='btn btn-success  btn-xs' style="color:#fff;margin-bottom: 10px;margin-right:10px;width: 95px;height: 22px"
                               id="button2">导出详情</a>
                        </div>
                    </c:if>

                    <c:if test="${sessionScope.sessionUser.organizaType == '20001' || sessionScope.sessionUser.organizaType == '20002' || sessionScope.sessionUser.organizaType == '20003'}">
                        <c:if test="${obj.processStatus == 1}"> <!-- 待发货 -->

                            <a class="trigger" href="javascript:cancelMat('${obj.moiId}')"
                               style="text-decoration: underline;color: blue">取消订单</a><br/>
                        </c:if>
                        <c:if test="${obj.processStatus == 2}"> <!-- 待付款 -->

                            <a class="trigger" href="javascript:cancelMat('${obj.moiId}')"
                               style="text-decoration: underline;color: blue">取消订单</a><br/>
                            <a class="btn btn-primary btn-xs" style="margin-top: 10px;width: 95px;height: 22px"
                               href="ddpay.htm?moiId=${obj.moiId}">立即支付</a>
                        </c:if>
                        <c:if test="${obj.processStatus == 3}"> <!-- 待发货 -->

                            <a class="trigger" href="javascript:cancelMat('${obj.moiId}')"
                               style="text-decoration: underline;color: blue;margin-right: -95px">取消订单</a><br/>
                            <a class="btn btn-primary btn-xs" style="margin-top: 10px;width: 95px;height: 22px;"
                               href="asapply.htm?moiId=${obj.moiId}">申请售后</a>
                            <a class="btn btn-primary btn-xs" style="margin-top: 10px;width: 95px;height: 22px"
                               href="ddsh.htm?moiId=${obj.moiId}">确认收货</a>
                        </c:if>
                        <c:if test="${obj.processStatus == 4}"> <!-- 已发货 -->
                            <%--                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"--%>

                            <a class="btn btn-primary btn-xs" style="margin-top: 10px;width: 95px;height: 22px;"
                               href="asapply.htm?moiId=${obj.moiId}">申请售后</a><!-- -->
                            <a class="btn btn-primary btn-xs" style="margin-top: 10px;width: 95px;height: 22px"
                               href="ddsh.htm?moiId=${obj.moiId}">待确认收货</a>
                        </c:if>
                        <c:if test="${obj.processStatus == 5}"> <!-- 交易成功 -->
                            <%--                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"--%>
                            <%--                           style="text-decoration: underline;color: blue">取消订单</a><br/>--%>
                            <a class="btn btn-primary btn-xs" style="margin-top: 10px;width: 95px;height: 22px;"
                               href="asapply.htm?moiId=${obj.moiId}">申请售后</a>
                            <a class="btn btn-primary btn-xs" style="margin-top: 10px;width: 95px;height: 22px;"
                               href="javascript:;" onclick="buyAgain(${obj.moiId})">再次购买</a>
                        </c:if>
                        <c:if test="${obj.processStatus == 6}"> <!-- 交易失败 -->
                            <%--                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"--%>
                            <%--
                                                     style="text-decoration: underline;color: blue">取消订单</a><br/>--%>
                                <a class="btn btn-primary btn-xs" style="margin-top: 10px;width: 95px;height: 22px"
                                   href="javascript:;" onclick="buyAgain(${obj.moiId})">再次购买</a>
                        </c:if>
                        <c:if test="${obj.afterSale == 1}"> <!-- 售后 -->
<%--                            <a class="btn btn-primary" style="margin-top: 10px;"--%>
                            <%--                               href="asapply.htm?moiId=${obj.moiId}">申请售后</a>--%>
                            <a class="btn btn-primary btn-xs" style="margin-top: 10px;width: 95px;height: 22px"
                               href="orderas.htm?moiId=${obj.moiId}">售后信息</a>
                            <%--                        <a class="trigger" href="javascript:cancelMat('${order.moiId}')"--%>
                            <%--                           style="text-decoration: underline;color: blue">取消订单</a><br/>--%>
                            <%--                        <a class="btn btn-primary" style="margin-top: 10px;"--%>
                            <%--                           href="ddsh.htm?moiId=${order.moiId}">确认收货</a>--%>
                        </c:if>
                    </c:if>
                </div>
            </div>

            <div class="address-list">
                <div class="address-items-info">
                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">
                        订单号：${obj.orderCode}</div>
                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">
                        供应商：${obj.applicantName}</div>
                    <div class="ellips_line" style="float:left;width:330px;padding-left:40px">
                        订单状态：${obj.processStatusName}</div>
                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">下单时间：<fmt:formatDate
                            value="${obj.placeOrderTime}" type="both"/></div>
                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">
                        下单数量：${obj.commodityNumber}</div>
                    <div class="ellips_line" style="float:left;width:330px;padding-left:40px">订单金额：
                        ¥<fmt:formatNumber type="number" value="${obj.placeOrderMoney}" pattern="0.00"
                                           maxFractionDigits="2"/></div>
                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">完结时间：<fmt:formatDate
                            value="${obj.endTime}" type="both"/></div>
                    <div class="ellips_line" style="float:left;width:330px;padding-left:20px">确认数量：
                        <c:if test="${obj.number1!=null}"><fmt:formatNumber value="${obj.number1}" pattern="#0"
                                                                            maxFractionDigits="0"/></c:if>
                        <c:if test="${obj.number1==null}">-</c:if>
                    </div>
                    <div class="ellips_line" style="float:left;width:330px;padding-left:40px">确认金额：
                        <c:if test="${obj.actualMoney!=null}">¥<fmt:formatNumber type="number"
                                                                                 value="${obj.actualMoney}"
                                                                                 pattern="0.00"
                                                                                 maxFractionDigits="2"/></c:if>
                        <c:if test="${obj.actualMoney==null}">-</c:if>
                    </div>
                </div>
                <div class="address-items">
                    <p><i class="icon-people"></i>${obj.addressee}</p>
                    <p><i class="icon-address"></i>${obj.province}${obj.city}${obj.area}${obj.deliveryAddress}
                    </p>
                    <p><i class="icon-phone"></i>${obj.addresseeTelephone}</p>
                    <span class="font-weight-trigger">收货地址</span>
                </div>
                <div class="address-items" style="margin-left:14px">
                    <c:if test="${obj.needBill=='1'}">
                        <p>抬头:${obj.billHeard}(<c:if test="${obj.billType=='1'}">个人</c:if><c:if
                                test="${obj.billType=='2'}">商家</c:if>)</p>
                        <c:if test="${obj.billType=='2'}">
                            <p>纳税人识别号:${obj.billCode}</p>
                        </c:if>
                    </c:if>
                    <c:if test="${obj.needBill!='1'}">
                        <p>无</p>
                    </c:if>
                    <span class="font-weight-trigger">发票信息</span>
                </div>
                <div class="address-items" style="margin-left:15px">
                    <div style="text-align: center; margin-top: 26px;padding-left: 10px; margin-bottom: 4px">
                        <div style="display: flex">
                            <div style="flex: 1">运送方式</div>
                            <div style="flex: 2">物流公司</div>
                            <div style="flex: 2">运单号</div>
                        </div>
                    </div>
                    <div style="border-top: 1px solid lightgrey"></div>
                    <div style="text-align: center; margin-top: 4px;padding-left: 10px">
                        <c:if test="${obj.expressNameAndCode == null}">
<%--                            <div style="display: flex">--%>
<%--                                <div style="flex: 1">快递</div>--%>
<%--                                <div style="flex: 2">--%>
<%--                                    <c:if test="${obj.expressName!=null}">${obj.expressName}</c:if>--%>
<%--                                    <c:if test="${obj.expressName==null}">-</c:if>--%>
<%--                                </div>--%>
<%--                                <div style="flex: 2">--%>
<%--                                    <c:if test="${obj.expressCode!=null}">${obj.expressCode}</c:if>--%>
<%--                                    <c:if test="${obj.expressCode==null}">-</c:if>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                            <c:if test="${obj.expressName != null&&obj.expressCode!=null&&obj.expressName!=undefined&&obj.expressCode!=undefined&&obj.expressName!=''&&obj.expressCode!=''}">
                                <div style="display: flex">
                                    <div style="flex: 1">快递</div>
                                    <div style="flex: 2">
                                        <c:if test="${obj.expressName!=null}">${obj.expressName}</c:if>
                                        <c:if test="${obj.expressName==null}">-</c:if>
                                    </div>
                                    <div style="flex: 2">
                                        <c:if test="${obj.expressCode!=null}">${obj.expressCode}</c:if>
                                        <c:if test="${obj.expressCode==null}">-</c:if>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${obj.expressName == null||obj.expressCode==null||obj.expressName==undefined||obj.expressCode==undefined||obj.expressName==''||obj.expressCode==''}">
                                <div style="display: flex">
                                    <div style="flex: 1">上门配送</div>
                                    <div style="flex: 2">
                                        <c:if test="${obj.expressName!=null}">${obj.expressName}</c:if>
                                        <c:if test="${obj.expressName==null}">-</c:if>
                                    </div>
                                    <div style="flex: 2">
                                        <c:if test="${obj.expressCode!=null}">${obj.expressCode}</c:if>
                                        <c:if test="${obj.expressCode==null}">-</c:if>
                                    </div>
                                </div>
                            </c:if>
                        </c:if>
                        <c:if test="${obj.expressNameAndCode != null}">
                            <c:set var="nameAndCodes" value="${fn:split(obj.expressNameAndCode, ',')}"/>
                            <c:forEach var="nameAndCode" items="${nameAndCodes}" end="2">
                                <div style="display: flex">
                                    <c:set var="nameCode" value="${fn:split(nameAndCode, '#')}"/>
                                    <div style="flex: 1">快递</div>
                                    <div style="flex: 2">
                                        <c:if test="${nameCode[0]!=null}">${nameCode[0]}</c:if>
                                        <c:if test="${nameCode[0]==null}">-</c:if>
                                    </div>
                                    <div style="flex: 2">
                                        <c:if test="${nameCode[1]!=null}">${nameCode[1]}</c:if>
                                        <c:if test="${nameCode[1]==null}">-</c:if>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <span class="font-weight-trigger">物流信息 </span>
                </div>
            </div>
            <div style="clear: both">
                <div class="ddBatchManage form-inline">
                    <div style="display: inline">
                        <input type="text" id="searchDdName" name="searchDdName" class="form-control"
                               placeholder="请输入商品名称" style="margin-right: 12px;margin-bottom:-17px"/>
                        <%--                        <select id="searchDDState" class="form-control">--%>
                        <%--                            <option value="">选择订单状态</option>--%>
                        <%--                            <option value="1">待发货</option>--%>
                        <%--                            <option value="2">待付款</option>--%>
                        <%--                            <option value="3">待收货</option>--%>
                        <%--                            <option value="4">已发货</option>--%>
                        <%--                            <option value="5">交易成功</option>--%>
                        <%--                            <option value="6">交易失败</option>--%>
                        <%--                            <option value="7">售后</option>--%>
                        <%--                        </select>--%>
                        <button id="searchDdBtn" class="btn btn-primary btn-xs" onclick="searchDd()"
                                style="margin-bottom:-18px;width: 95px;height: 22px">搜索
                        </button>
                        <button id="resetting" class="btn btn-white btn-xs" onclick="resetSearch()"
                                style="margin-right:-1px;margin-bottom:-18px;width: 95px;height: 22px">重置
                        </button>

                    </div>
                </div>
            </div>
            <div class="order">
                <div class="order-header">
                    <div class="order-header-left">
                        <label>供应商：<a class="supplier font-weight-trigger" href="pcjxiangxi.htm?wzId=${obj.wzId}"
                                      target="_blank">${obj.applicantName}</a></label>
                    </div>
                    <div class="order-header-right">
                        <span class="order-header-item">单价</span>
                        <span class="order-header-item">订单数量</span>
                        <span class="order-header-item">发货数量</span>
                        <span class="order-header-item">收货数量</span>
                        <span class="order-header-item">订单总额</span>
                        <span class="order-header-item">实际总额</span>
                    </div>
                </div>
                <div class="order-body" id="matList">
                </div>
                <div class="pagination w1080 m-pagenum pagination" id="Pagination" style="float: right"></div>
            </div>
            <div style="clear: both"></div>
            <div class="note">
                <label class="note-label">买家留言：</label>
                <textarea style="border-style:none;resize:none;overflow:hidden;margin-top: -2px;" class="note-textarea" placeholder="" readonly=true>${obj.scopeBusiness}</textarea>
                <c:if test="${sessionScope.sessionUser.organizaType != '20001' && sessionScope.sessionUser.organizaType != '20002'
                && sessionScope.sessionUser.organizaType != '20003' && obj.processStatus !=6}">
                    <!-- 修改权限,订单所有状态下摘要都可以出来， && obj.processStatus == 1 && obj.processStatus == 4 -->
                    <label class="note-label" style="margin-top: 10px">摘&nbsp&nbsp&nbsp要：&nbsp&nbsp&nbsp&nbsp</label>
                        <textarea style="width:850px;height: 40px" class="note-textarea" id="test" placeholder="在此输入摘要">${obj.enterpriseType}</textarea>
                        <span style="position: absolute;margin-top: 8px;margin-left: 8px"><button id="saveButtom" onclick="saveZhaiYao()">保存摘要</button></span>
                </c:if>
            </div>
            <div class="ft" style="height: auto; line-height: 20px;">
                <div style="width: 100%;display: block">
                    <table class="xqtabl">
                        <tr>
                            <td class="right">商品总价：</td>
                            <td>
                                <strong class="red">¥<span id="totalmoney"></span></strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="right">店铺优惠：</td>
                            <td class="left">
                                <strong class="red"><c:if test="${obj.money3!=null}">¥<fmt:formatNumber type="number"
                                                                                                         value="${obj.money3}"
                                                                                                         pattern="0.00"
                                                                                                         maxFractionDigits="2"/></c:if><c:if
                                        test="${obj.money3==null}">¥0.00</c:if>
                                </strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="right">运费(快递)：</td>
                            <td class="left">
                                <strong class="red">
                                    <c:if test="${obj.money2!=null}">¥<fmt:formatNumber type="number"
                                                                                        value="${obj.money2}"
                                                                                        pattern="0.00"
                                                                                        maxFractionDigits="2"/></c:if>
                                    <c:if test="${obj.money2==null}">0.00</c:if>
                                </strong>
                            </td>
                        </tr>
                        <tr>
                            <td class="right">订单总价：</td>
                            <td class="left">
                                <strong class="red"><c:if test="${obj.placeOrderMoney!=null}"> ¥<fmt:formatNumber
                                        type="number"
                                        value="${obj.placeOrderMoney}"
                                        pattern="0.00"
                                        maxFractionDigits="2"/>
                                </c:if></strong>
                            </td>
                        </tr>
                        <c:if test="${obj.placeOrderMoney==null}">-</c:if>
                        <c:if test="${obj.processStatus == 1}"> <!-- 待发货 -->

                        </c:if>
                        <c:if test="${obj.processStatus == 2}"> <!-- 待付款 -->

                        </c:if>
                        <c:if test="${obj.processStatus == 3}"> <!-- 待发货 -->

                        </c:if>
                        <c:if test="${obj.processStatus == 4}"> <!-- 已发货 -->

                        </c:if>
                        <c:if test="${obj.processStatus == 5}"> <!-- 交易成功 -->

                        </c:if>
                        <c:if test="${obj.processStatus == 6}"> <!-- 交易失败 -->
                            <tr>
                                <td class="right">退款金额：</td>
                                <td class="left">
                                    <strong class="red"><c:if test="${obj.placeOrderMoney!=null}"> ¥<fmt:formatNumber
                                            type="number"
                                            value="${obj.placeOrderMoney}"
                                            pattern="0.00"
                                            maxFractionDigits="2"/>
                                    </c:if></strong>
                                </td>
                            </tr>
                            <tr>
                                <td class="right">实付金额：</td>
                                <td class="left">
                                    <strong class="red"><c:if test="${obj.placeOrderMoney!=null}"> ¥<fmt:formatNumber
                                            type="number"
                                            value="${obj.actualMoney == null ? 0 : obj.actualMoney}"
                                            pattern="0.00"
                                            maxFractionDigits="2"/>
                                    </c:if></strong>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${obj.processStatus == 7}"> <!-- 售后 -->

                        </c:if>
                        <tr style="color: grey">
                            <td class="right">
                                支付方式：
                            </td>
                            <td class="left">${obj.payTypeName}</td>
                        </tr>
                        <tr>
                            <td></td>
                            <c:if test="${sessionScope.sessionUser.organizaType != '20001' && sessionScope.sessionUser.organizaType != '20002' && sessionScope.sessionUser.organizaType != '20003'}">
                                <td class="left" align="left"><a onclick="main_exportC()"
                                                                 class='btn btn-success  btn-xs'
                                                                 style="color:#fff;margin: 8px 0px 8px 0px" id="export1">导出配送单</a></td>
                            </c:if>
                        </tr>
                    </table>
                </div>
                <div>
                    <%--                    商品总价，运费，订单总价，实付金额 付款方式，退款金额，支付方式--%>
                    <%--                    <button class="saveOrder font-weight-trigger" onclick="main_export()">导出</button>--%>
                </div>

            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/jsp/util/ordermxutil/ordermxutil.js?v=5' />"></script>