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
<body >
<header class="header">
  <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/cooperation.css?04' />">
<%@ include file="/jsp/dentistmall/shopping/navigation.jsp" %>
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
<div class="content">
	<div class="cooperation-list w1080" id="contentList">
	</div>
</div>
<div class="pagination w1080 m-pagenum pagination" id="Pagination">
  
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<form id="searchForm" >
	<input type="hidden" name="searchName" value="${searchName}"/>
</form>
</body>
</html>
<script src="<c:url value='/js/shopping/layer/layer.js' />"></script>
<script src="<c:url value='/javaScript/dentistmall/shopping/hzcj.js?06' />"></script>