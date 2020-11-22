<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>好看商城</title>
    <%@ include file="/jsp/lib.jsp" %>
</head>
<script>
    var _isFix = 1;
    var _isCar = 1;
</script>
</head>
<body>
<header class="header">
    <%@ include file="/jsp/ohmy/shophead1.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/index.css?03' />">
<div class="content w1080">

    <!--新品推荐-->
    <c:forEach var="lcModel" items="${lcModelList}" varStatus="s">
        <!--新品推荐-->
        <c:if test="${s.first}">
            <section class="new-product w1080  m-top20 anchor-info">
                <div class="product-title"><p class="product-title-info"><span>${lcModel.columnName}</span></p></div>
                <ul class="flex w1080 product-lists m-top10 ">
                    <c:forEach var="child" items="${lcModel.childList}" varStatus="cIndex">
                        <li <c:if test="${cIndex.index%5 > 0}"> style="margin-left:10px"</c:if> >
                            <a href="xiangxi.htm?wmsMiId=${child.wmsMiId}" target="_blank">
                                <div class="product-img-container">
                                    <img src="${child.lessenFilePath}" alt="">
                                </div>
                                <p class="product-name ellipsis">
                                        ${child.matName}
                                </p>
                            </a>
                            <p class="product-desc m-top10 ellipsis">供应商：<a style="color:#888"
                                                                           >${child.applicantName}</a>
                            </p>
                            <p class="product-desc ellipsis"> 规格：<span>
	            <c:if test="${fn:length(child.norm) > 14}">
                    ${fn:substring(child.norm, 0,14)}...
                </c:if>
				<c:if test="${fn:length(child.norm) <= 14}">
                    ${child.norm}
                </c:if>
            </span></p>
                            <p class="product-price-container flex m-top10">

                                    <span class="red">￥<fmt:formatNumber type="number" value="${child.money1}"
                                                                         pattern="0.00" maxFractionDigits="2"/></span>

                                <span class="product-sale-count">销售量：<b>
              	<c:if test="${empty child.number1}">0</c:if>
				<c:if test="${!empty child.number1}"><fmt:formatNumber value="${child.number1}" pattern="#0"
                                                                       maxFractionDigits="0"/></c:if></b>${child.basicUnit}</span>
                            </p>
                            <img class="i-new-product" src="<c:url value='/css/shopping/images/new.png' />" alt="">
                            <div class="product-operation">
                                <a
                                   title="加入收藏夹" class="product-operation-favorite"><span
                                        id="index-new-product-fav-${child.wmsMiId}" name="${child.wmsMiId}"
                                        class="icon-favorite"></span></a>
                                <a
                                   title="加入购物车"><span id="index-new-product-cart-${child.wmsMiId}"
                                                       name="${child.wmsMiId}"
                                                       class="icon-shopping-cart add-product-cart"></span></a>
                                <a><span class="icon-search"></span></a>
                                <a><span class="icon-scanning"></span></a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </section>
        </c:if>
        <c:if test="${!s.first && lcModel.mszType==2}">
            <section class="hot-product w1080 m-top20 anchor-info">
                <c:if test="${lcModel.swcId!=null && lcModel.swcId==13}">
                    <div class="product-title"><p class="product-title-info"><span>必备装扮</span></p>
                    </div>
                </c:if>
                <c:if test="${lcModel.swcId!=null && lcModel.swcId!=13}">
                    <div class="product-title"><p class="product-title-info"><span>${lcModel.columnName}</span></p>
                    </div>
                </c:if>
                <div class="flex hot-product-container m-top10">
                    <ul class="flex  w1080 product-lists ">
                        <c:forEach var="child" items="${lcModel.childList}" varStatus="cIndex">
                            <li <c:if test="${cIndex.index%4 > 0}"> style="margin-left:10px"</c:if> >
                                <a href="xiangxi.htm?wmsMiId=${child.wmsMiId}" target="_blank">
                                    <div class="product-img-container">
                                        <img src="${child.lessenFilePath}" alt="">
                                    </div>
                                    <p class="product-name ellipsis">
                                            ${child.matName}
                                    </p>
                                </a>
                                <p class="product-desc m-top10 ellipsis">供应商：<a style="color:#888"
                                                                               >${child.applicantName}</a>
                                </p>
                                <p class="product-desc ellipsis"> 规格：<span>
	            <c:if test="${fn:length(child.norm) > 14}">
                    ${fn:substring(child.norm, 0,14)}...
                </c:if>
				<c:if test="${fn:length(child.norm) <= 14}">
                    ${child.norm}
                </c:if>
            </span></p>
                                <p class="product-price-container flex m-top10">

                                        <span class="red">￥<fmt:formatNumber type="number" value="${child.money1}"
                                                                             pattern="0.00"
                                                                             maxFractionDigits="2"/></span>

                                    <span class="product-sale-count">销售量：<b>
              	<c:if test="${empty child.number1}">0</c:if>
				<c:if test="${!empty child.number1}"><fmt:formatNumber value="${child.number1}" pattern="#0"
                                                                       maxFractionDigits="0"/></c:if></b>${child.basicUnit}</span>
                                </p>
                                <div class="product-operation">
                                    <a
                                       title="加入收藏夹" class="product-operation-favorite"><span
                                            id="index-product-operation-fav-${child.wmsMiId}" name="${child.wmsMiId}"
                                            class="icon-favorite"></span></a>
                                    <a
                                       title="加入购物车"><span id="index-product-operation-cart-${child.wmsMiId}"
                                                           name="${child.wmsMiId}"
                                                           class="icon-shopping-cart add-product-cart"></span></a>
                                    <a><span class="icon-search"></span></a>
                                    <a><span class="icon-scanning"></span></a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </section>
        </c:if>
    </c:forEach>
</div>
<%@ include file="/jsp/ohmy/shopfoot1.jsp?v=1" %>
</body>

<script type="text/javascript" src="<c:url value='/js/shopping/jquery.slider-min.js' />"></script>
<script src="<c:url value='/js/shopping/layer/layer.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/shopping/ui.js?08' />"></script>
<script src="<c:url value='/jsp/ohmy/oh.js' />"></script>
</html>
<script>

</script>