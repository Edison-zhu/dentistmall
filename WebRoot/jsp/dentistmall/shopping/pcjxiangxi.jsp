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
    var _isCar = 1;
</script>
</head>
<body>
<header class="header">
    <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/cooperationDescription.css?08' />">
<%@ include file="/jsp/dentistmall/shopping/navigation.jsp" %>
<p class="bread-crumb w1080">
    <i class="icon-homepage"></i>
    <label>
        <a href="index.htm">
            首页
        </a>
    </label>
    <label>
        <a href="hzcj.htm">
            合作厂家
        </a>
    </label>
    <label>${mdSupplier.applicantName}</label>
</p>
<div class="content w1080">
    <div class="section-title">
        <p class="section-title-info"><span>厂家介绍</span></p>
    </div>
    <div class="cooperation-description">
        <div class="description-left">
            <div class="cooperation-logo">
                <img src="${mdSupplier.logoFilePath}">
            </div>
            <div class="cooperation-title">
                <img src="<c:url value='/css/shopping/images/cooperation-header.jpg' />">
            </div>
            <div class="cooperation-info">
                <p class="icon-people">联系人：${mdSupplier.legalPerson}</p>
                <p class="icon-phone">联系电话：<strong>${mdSupplier.phoneNumber}</strong></p>
                <p class="icon-address">${mdSupplier.corporateDomicile}</p>
            </div>
        </div>
        <div class="description-right">
            <p class="description-title">
                ${mdSupplier.applicantName}
            </p>
            <div class="description-content">
                ${mdSupplier.scopeBusiness}
            </div>
        </div>
    </div>
    <div class="section-title">
        <p class="section-title-info"><span>厂家产品</span></p>
    </div>
    <div class="cooperation-products">
        <div class="product-category">
            <ul class="ul-level-1" id="proType">
            </ul>
        </div>
        <div class="product-list">
            <ul class="flex product-lists" id="contentList">
            </ul>
        </div>
    </div>
    <div class="pagination w1080 m-pagenum pagination" id="Pagination">

    </div>
    <form id="searchForm">
        <input type="hidden" name="mmtId" id="mmtId" value=""/>
        <input type="hidden" name="wzId" id="wzId" value="${mdSupplier.wzId}"/>
    </form>
    <%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
    <!--客服-->
    <c:if test="${cusList != null}">
    <div id="online_qq_layer">
        <div id="online_qq_tab" style="margin-top:0px">
            <a id="floatShow" style="display:block;" href="javascript:void(0);">收缩</a>
            <a id="floatHide" style="display:none;" href="javascript:void(0);">展开</a>
        </div>
        <div id="onlineService">
            <div class="onlineMenu">
                <h3 class="tQQ">商家QQ客服</h3>
                <ul style="margin-bottom:0px">
                    <li class="tli zixun">商家咨询</li>
                    <c:forEach var="cus" items="${cusList}" varStatus="s">
                        <li>
                            <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${cus}&site=qq&menu=yes">
                                <img border="0" src="http://wpa.qq.com/pa?p=2:1464743635:51" alt="点这里给我发消息"/>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="btmbg"></div>
        </div>
    </div>
    </c:if>
</body>
</html>
<script src="<c:url value='/js/shopping/layer/layer.js' />"></script>
<script src="<c:url value='/javaScript/dentistmall/shopping/pcjxiangxi.js?09' />"></script>
