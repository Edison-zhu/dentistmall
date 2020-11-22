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
<link rel="stylesheet" href="<c:url value='/css/shopping/css/index.css?03' />">
<%@ include file="/jsp/dentistmall/shopping/navigation.jsp" %>
<div class="banner">
    <div class="slider banner-slide">
        <ul class="slides">
            <c:forEach var="lb" items="${lbList}">
                <li>
                    <a
                            <c:if test="${lb.linkUrl!=null && lb.linkUrl!=''}">href="${lb.linkUrl}"</c:if>
                            <c:if test="${lb.linkUrl==null || lb.linkUrl==''}">href="commInfo.htm?swmId=${lb.swmId}"</c:if>
                            target="_blank"><img src="${lb.rootNewsIconFilePath}" style="height:100%;width:100%"
                                                 alt=""/></a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
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
                                <p class="product-name newEllipsis">
                                        ${child.matName}
                                </p>
                            </a>
                            <p class="product-desc m-top10 ellipsis">供应商：<a style="color:#888"
                                                                            href="pcjxiangxi.htm?wzId=${child.wzId}"
                                                                            target="_blank">${child.applicantName}</a>
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
                                <c:if test="${sessionScope.shoppingAccount != null &&  sessionScope.shoppingAccount.suiId !=null}">
                                    <span class="red">￥<fmt:formatNumber type="number" value="${child.money1}"
                                                                         pattern="0.00" maxFractionDigits="2"/></span>
                                </c:if>
                                <span class="product-sale-count">销售量：<b>
              	<c:if test="${empty child.number1}">0</c:if>
				<c:if test="${!empty child.number1}"><fmt:formatNumber value="${child.number1}" pattern="#0"
                                                                       maxFractionDigits="0"/></c:if></b>${child.basicUnit}</span>
                            </p>
                            <img class="i-new-product" src="<c:url value='/css/shopping/images/new.png' />" alt="">
                            <div class="product-operation">
                                <a href="javascript:setFavorites('${child.wmsMiId}','index-new-product-fav');;"
                                   title="加入收藏夹" class="product-operation-favorite"><span
                                        id="index-new-product-fav-${child.wmsMiId}" name="${child.wmsMiId}"
                                        class="icon-favorite"></span></a>
                                <a href="javascript:setCar('${child.wmsMiId}', 'index-new-product-cart');"
                                   title="加入购物车"><span id="index-new-product-cart-${child.wmsMiId}"
                                                       name="${child.wmsMiId}"
                                                       class="icon-shopping-cart add-product-cart"></span></a>
                                <a href="javascript:;"><span class="icon-search"></span></a>
                                <a href="javascript:;"><span class="icon-scanning"></span></a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </section>
        </c:if>
        <c:if test="${!s.first && lcModel.mszType==2}">
            <section class="hot-product w1080 m-top20 anchor-info">
                <div class="product-title"><p class="product-title-info"><span>${lcModel.columnName}</span></p>
                    <c:if test="${lcModel.isMore!=null && lcModel.isMore=='1'}">
                        <a class="product-more" href="${lcModel.linkUrl}">查看更多 ></a>
                    </c:if>
                </div>
                <div class="flex hot-product-container m-top10">
                    <div class="product-slider w208 ">
                        <c:if test="${lcModel.childMapList!=null && fn:length(lcModel.childMapList)>0}">
                            <div class="product-slide-container">
                                <div class="slider w208">
                                    <ul class="slides w208 h436">
                                        <c:forEach var="map" items="${lcModel.childMapList}">
                                            <li class="slider-img">
                                                <c:if test="${map.linkUrl!=null && map.linkUrl!=''}">
                                                    <a href="${map.linkUrl}" target="_blank"><img
                                                            src="${map.rootNewsIconFilePath}"></a>
                                                </c:if>
                                                <c:if test="${map.linkUrl==null || map.linkUrl==''}">
                                                    <img src="${map.rootNewsIconFilePath}">
                                                </c:if>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                        </c:if>
                        <div class="hot-product-categories">
                            <p class="hot-product-categories-info">
                                <c:if test="${lcModel.childWordList!=null && fn:length(lcModel.childWordList)>0}">
                                <c:forEach var="word" items="${lcModel.childWordList}">
                                    <a href="${word.linkUrl}" target="_blank">${word.newsTitle}</a>
                                </c:forEach>
                            </p>
                            </c:if>
                        </div>

                    </div>
                    <ul class="flex  w862 product-lists ">
                        <c:forEach var="child" items="${lcModel.childList}" varStatus="cIndex">
                            <li <c:if test="${cIndex.index%4 > 0}"> style="margin-left:10px"</c:if> >
                                <a href="xiangxi.htm?wmsMiId=${child.wmsMiId}" target="_blank">
                                    <div class="product-img-container">
                                        <img src="${child.lessenFilePath}" alt="">
                                    </div>
                                    <p class="product-name newEllipsis">
                                            ${child.matName}
                                    </p>
                                </a>
                                <p class="product-desc m-top10 ellipsis">供应商：<a style="color:#888"
                                                                                href="pcjxiangxi.htm?wzId=${child.wzId}"
                                                                                target="_blank">${child.applicantName}</a>
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
                                    <c:if test="${sessionScope.shoppingAccount != null &&  sessionScope.shoppingAccount.suiId !=null}">
                                        <span class="red">￥<fmt:formatNumber type="number" value="${child.money1}"
                                                                             pattern="0.00"
                                                                             maxFractionDigits="2"/></span>
                                    </c:if>
                                    <span class="product-sale-count">销售量：<b>
              	<c:if test="${empty child.number1}">0</c:if>
				<c:if test="${!empty child.number1}"><fmt:formatNumber value="${child.number1}" pattern="#0"
                                                                       maxFractionDigits="0"/></c:if></b>${child.basicUnit}</span>
                                </p>
                                <div class="product-operation">
                                    <a href="javascript:setFavorites('${child.wmsMiId}','index-product-operation-fav');"
                                       title="加入收藏夹" class="product-operation-favorite"><span
                                            id="index-product-operation-fav-${child.wmsMiId}" name="${child.wmsMiId}"
                                            class="icon-favorite"></span></a>
                                    <a href="javascript:setCar('${child.wmsMiId}','index-product-operation-cart');"
                                       title="加入购物车"><span id="index-product-operation-cart-${child.wmsMiId}"
                                                           name="${child.wmsMiId}"
                                                           class="icon-shopping-cart add-product-cart"></span></a>
                                    <a href="javascript:;"><span class="icon-search"></span></a>
                                    <a href="javascript:;"><span class="icon-scanning"></span></a>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </section>
        </c:if>
        <c:if test="${lcModel.mszType==3}">
            <section class="cooperative-factory w1080 m-top20 anchor-info">
                <div class="product-title"><p class="product-title-info"><span>${lcModel.columnName}</span></p>
                    <c:if test="${lcModel.isMore!=null && lcModel.isMore=='1'}">
                        <a class="product-more" href="${lcModel.linkUrl}">查看更多 ></a>
                    </c:if>
                </div>
                <div class="flex m-top10">
                    <c:if test="${lcModel.childMapList!=null && fn:length(lcModel.childMapList)>0}">
                        <div class="cooperative-factory-img w208">
                            <c:forEach var="map" items="${lcModel.childMapList}">
                                <c:if test="${map.linkUrl!=null && map.linkUrl!=''}">
                                    <a href="${map.linkUrl}" target="_blank"><img src="${map.rootNewsIconFilePath}"></a>
                                </c:if>
                                <c:if test="${map.linkUrl==null || map.linkUrl==''}">
                                    <img src="${map.rootNewsIconFilePath}">
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>
                    <ul class="cooperative-factory-lists flex ">
                        <c:forEach var="child" items="${lcModel.childList}" varStatus="cIndex">
                            <li>
                                <a href="pcjxiangxi.htm?wzId=${child.wzId}" target="_blank">
                                    <img src="${child.logoFilePath}">
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </section>
        </c:if>
    </c:forEach>
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<div class="anchor" id="anchor">
    <ul>
        <c:forEach items="${lcModelList}" varStatus="stauts">
            <li><span>${stauts.index+1}F</span></li>
        </c:forEach>
        <li class="last"><span class="icon-arrow-up"></span></li>
    </ul>
</div>
</body>
<style>
    .newEllipsis{
        text-overflow: -o-ellipsis-lastline;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-line-clamp: 2;
        line-clamp: 2;
        -webkit-box-orient: vertical;
    }
    .product-lists > li .product-name {
        height: 60px;
    }
</style>
<script type="text/javascript" src="<c:url value='/js/shopping/jquery.slider-min.js' />"></script>
<script src="<c:url value='/js/shopping/layer/layer.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/shopping/ui.js?08' />"></script>
</html>
<script>
    $(document).ready(function () {
        $('.slider').flexslider({
            directionNav: true,
            pauseOnAction: false,
            slideshowSpeed: 3000
        });
    })
</script>