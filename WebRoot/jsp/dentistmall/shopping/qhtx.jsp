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
var _isCar=2;
</script>
<style>
	.left2 img{
		vertical-align: top;
	}
</style>
</head>
<body >
<header class="header">
  <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?17' />">
<p class="bread-crumb w1080">
  <i class="icon-homepage"></i>
  <label>
    <a href="index.htm">
      	首页
    </a>
  </label>
  <label>
      缺货提醒
  </label>
</p>
<div class="content">
	<div class="shopping-cart-body w1080">
		<div class="shopping-cart-header">
	      <div class="shopping-flow">
	        <div class="shopping-cart-icon">
	          <img src="<c:url value='/css/shopping/images/shopping-order.png'/>">
	          <span class="cn" >缺货提醒:</span>
	          <span class="un">Shortage Reminding</span>
	          <a class="taps active" href="qhtx.htm">缺货提醒<strong id="newsCount"></strong></a>
	          <a class="taps " href="scj.htm">我的收藏夹</a>
	          <a class="taps" href="qgc.htm">弃购车</a>
	          <a class="taps" href="ddlb.htm">查询订单</a>
	          <a class="taps" href="zhsz.htm">账号设置</a>
	        </div>
	      </div>
	    </div>
	    <div class="shopping-cart-content">
		     <div class="hd" style="margin-top:20px">
		        <div class="hd-left2">
		          <label class="l32">货品</label>
		        </div>
		        <div class="hd-right2">
		          <label>单价（元）</label>
		          <label>单位</label>
		          <label>库存数量</label>
		          <label>告警数量</label>
		          <label>到货时间</label>
		          <label>操作</label>
		        </div>
		     </div>
		     <div class="order" style="border-top:0px;margin-top:0px">
		     	<div class="order-body" id="divList" >
		     	</div>
		     </div>
	    </div>
	</div>
</div>
<div class="pagination w1080 m-pagenum pagination" id="Pagination"></div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/qhtx.js?42' />"></script>