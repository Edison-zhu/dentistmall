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
<link rel="stylesheet" href="<c:url value='/css/shopping/css/productInfo.css?11' />">
<%@ include file="/jsp/dentistmall/shopping/navigation.jsp" %>
<p class="bread-crumb w1080">
  <i class="icon-homepage"></i>
  <label>
    <a href="index.htm">
      首页
    </a>
  </label>
  <label >${comment.newsTitle}</label>
</p>
<ul class="product-info-tab-bd">
	<li class="active">${comment.newsComment}</li>
</ul>


<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
</body>
</html>
