<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
  <title>优牙库</title>
  <%@ include file="/jsp/lib.jsp"%>
</head>
<script>
var _isFix=1;
var _isCar=1;
</script>
</head>
<style>
    .highLight{
        color: red;
    }
    .ahover {
        text-decoration: underline;
        padding: 5px 10px ;
    }
    .ahover:hover{
        cursor: pointer;
    }
</style>
<body >
<header class="header">
  <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/productList.css?06' />">
<%@ include file="/jsp/dentistmall/shopping/navigation.jsp" %>
<script type="text/javascript"	src="<c:url value='/javaScript/dentistmall/mdMaterielType.js?v=03'/>"></script>
<p class="bread-crumb w1080">
  <i class="icon-homepage"></i>
  <label>
    <a href="index.htm">
      首页
    </a>
  </label>
  <c:if test="${Lehem !=null && Lehem!=''}">
  	<label class="product-category">${Lehem}</label>
  </c:if>
</p>
<div class="search-filter w1080">
  <div class="search-condition border-line demo-nav-2">
    <div class="filter-section flex border-bottom-line">
      <div class="filter-title border-right-line">
        品牌
      </div>
      <div class="filter-content filter-brand">
        <div class="filter-item font-weight-trigger active" id="brand_all" onclick="brandSearch('','all')">全部</div>
        <c:forEach var="brand" items="${brandList}" varStatus="status">
	        <div class="filter-item font-weight-trigger" <c:if test="${status.index>=9}"> style="display:none" </c:if> id="brand_${brand.paramCode}" onclick="brandSearch('${brand.paramName}','${brand.paramCode}')">
	        	${brand.paramName}
	        </div>
		</c:forEach>
      </div>
      <c:if test="${fn:length(brandList)>9}">
      	<div class="load-more icon-arrow-down trigger" id="weightOpen" onclick="weightShowMore()">更多</div>
      	<div class="close-load-more icon-arrow-down trigger" id="weightClose" onclick="weightCloseMore()">收起</div>
      </c:if>
      
    </div>
    <div class="filter-section flex">
      <div class="filter-title border-right-line">
        类别
      </div>
      <div class="filter-content filter-category">
        <div class="filter-item category-trigger active" id="category_all" onclick="categorySearch('${mmtId}','all')">全部</div>
        <c:forEach var="category" items="${mmtList}" varStatus="status">
	        <div class="filter-item category-trigger" <c:if test="${status.index>=12}"> style="display:none" </c:if> id="category_${category.mmtCode}" onclick="categorySearch('${category.mmtId}','${category.mmtCode}')">
	        	${category.mmtName}
	        </div>
		</c:forEach>
      </div>
       <c:if test="${fn:length(mmtList)>12}">
       	<div class="load-more icon-arrow-down trigger" id="categoryOpen" onclick="categoryShowMore()">更多</div>
      	<div class="close-load-more icon-arrow-down trigger" id="categoryClose" onclick="categoryCloseMore()">收起</div>
      </c:if>
      
    </div>
  </div>
  <div class="search-sort border-line">
    <div class="filter-title">
      排序：
    </div>
    <label class="icon-list default trigger active" onclick="setDefault()" id="default">默认(最新上架)</label>
    <label class="icon-sort trigger" onclick="setNumber()" id="number">销量</label>
    <label class="icon-finance trigger sort-up" onclick="setPrice()" id="price">价格</label>
  </div>
</div>
<div class="content w1080">
	<section class="new-product w1080">
		<ul class="flex w1080 product-lists " id="contentList">
			
		</ul>
	</section>
</div>
<div class="pagination w1080 m-pagenum pagination" id="Pagination">
  
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<form id="searchForm" >
	<input type="hidden" name="mmtId" id="mmtId" value="${mmtId}"/>
	<input type="hidden" name="isDefault" id="isDefault" value="1"/>
	<input type="hidden" name="numOrder" id="numOrder" value="0"/>
	<input type="hidden" name="priceOrder" id="priceOrder" value="0"/>
	<input type="hidden" name="searchName" id="searchLBName" value="${searchName}"/>
	<input type="hidden" name="brand" id="brand" value=""/>
</form>
</body>
</html>
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
<script src="<c:url value='/js/shopping/layer/layer.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/shopping/ui.js?10' />"></script>
<script src="<c:url value='/javaScript/dentistmall/shopping/liebiao.js?18' />"></script>