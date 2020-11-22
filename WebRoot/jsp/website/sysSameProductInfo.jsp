<%--
  Created by IntelliJ IDEA.
  User: crazyoung
  Date: 2020/3/25
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>同款商品</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?13' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>">
    <link rel="stylesheet" href="<c:url value='/css/shopping/css/sameProduct.css?v=3' />">
</head>
<body>
<div class="same-product">
    <div style="padding-left: 12px">
        <button class="btn btn-primary" onclick="exportTK()">导出同款商品</button>
        <button class="btn btn-info" style="width: 110px; height: 34px" onclick="main_search()">刷&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;新</button>
    </div>
    <div class="ibox-title">
        <div class="ibox-tools" id="win_tools_but">
        </div>
    </div>
    <div>
        <form class="form-horizontal" id="win_form_search">
            <div id="win_form_hidden"></div>
        </form>
    </div>
    <div>
        <div class="div-padding">
            <div class="li-display display-flex nav"
                 style="">
                <div class="out-line flex-1">
                    商品ID
                </div>
                <div class="out-line flex-3">
                    商品信息
                </div>
                <div class="out-line flex-4">
                    型号/价格范围
                </div>
                <div class="out-line flex-1">
                    状态
                </div>
                <div class="out-line flex-1">
                    操作
                </div>
            </div>
            <div id="tkList" style="border-left: black 1px solid; border-right: black 1px solid; "></div>
            <div class="pagination w1080 m-pagenum pagination" style="float: right" id="Pagination"></div>
        </div>
    </div>
</div>
<div class="u-mask" id="addressId"></div>
<div id="divFilter" class="divFilter">
    <div style="border-bottom: lightgrey solid 1px;">
        <div style="float: left;padding: 8px;padding-left: 31px;font-size: 20px;font-weight: bold;">增加同款商品</div>
        <div style="float: right;margin-top: 5px;">
            <a class="btn btn-white" onclick="cancelTK()">X</a>
        </div>
        <div style="clear: both"></div>
    </div>
    <div style="color: grey;text-align: center;">
        <span>复制商品的URL或者商品ID即可进行同款商品关联。商品URL或ID号可在商品详情页面的浏览器地址查看。</span>
    </div>
    <form id="filterForm" class="form-horizontal form-bordered" style="margin-top: 30px;margin-bottom: 20px" role="form"
          style="padding-left: 10px">
        <div id="win_form_edithidden"></div>
        <div class="ibox float-e-margins">
            <div class="form-group">
                <label class="col-sm-2 control-label">请输入商品(URL)<span style="color: red">*</span>：</label>
                <div class="col-sm-7">
                    <input class="form-control" placeholder="商品编号或者URL" name="productName1"
                           onblur="searchProduct1(this, this.value)"
                           id="productName1"
                           value=""/>
                    <%--                    <a class="layui-icon layui-icon-search"></a>--%>

                    <div id="imgShow1" style="">
                        <div style="display: none">
                            <div style="width: 25%; display: inline-block">
                                <div style="float: left"><img id="productImg1" src="" width="68px" height="68px"></div>
                                <ul>
                                    <li>ff</li>
                                    <li>ff</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="showDiv" class="form-group">
                <label class="col-sm-2 control-label">请输入商品(URL)<span style="color: red">*</span>：</label>
                <div class="col-sm-7">
                    <input class="form-control" placeholder="商品编号或者URL" name="productName2" id="productName2"
                           onblur="searchProduct2(this, this.value)"
                           value=""/>

                    <div id="imgShow2" style="">
                        <div style="display: none">
                            <div style="width: 25%; display: inline-block">
                                <div style="float: left"><img id="productImg2" src="" width="68px" height="68px"></div>
                                <ul>
                                    <li>ff</li>
                                    <li>ff</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="form-group" style="clear:both;">
        <div class="col-sm-offset-2 col-sm-10">
            <button class="layui-btn layui-btn-blue" onclick="saveTK()">确定</button>
            <button class="layui-btn layui-btn-primary" onclick="cancelTK()">取消</button>
        </div>
    </div>
</div>
<input type="hidden" id="organizaType" value="${sessionScope.sessionUser.organizaType}">
<input type="hidden" id="userRole" value="${sessionScope.sessionUser.userRole}">
</body>
</html>
<script src="<c:url value='/javaScript/website/sysSameProductInfo.js?v=16'/>"></script>