<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
  <title>优牙库</title>
  <%@ include file="/jsp/lib.jsp"%>
</head>
<style>
	.name {
		width: 300px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
	.type {
		width: 300px;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
	}
</style>
<script>
var _isFix=1;
var _isCar=2;
</script>
</head>
<body >
<header class="header">
  <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/shoppingCart.css?v=15' />">
<%--<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?9' />">--%>
<p class="bread-crumb w1080">
  <i class="icon-homepage"></i>
  <label>
    <a href="index.htm">
      首页
    </a>
  </label>
  <label>购物车</label>
</p>
<div class="content">
	<div class="shopping-cart-body w1080">
		<div class="shopping-cart-header">
			<div class="shopping-flow">
				<div class="shopping-cart-icon">
		          <img src="<c:url value='/css/shopping/images/shopping-bag.png'/>">
		          <span class="cn">购物车</span>
		          <span class="un">Shopping Cart</span>
		        </div>
		        <div class="flow">
		          <div class="ready">
		            <i class="icon-circle green"></i>
		            <ul>
		              <li class="green">我的购物车</li>
		              <li class="green">STEP 1</li>
		            </ul>
		          </div>
		          <div class="done"></div>
		          <div class="ready l30">
		            <i class="icon-circle"></i>
		            <ul class="l10">
		              <li>确认订单信息</li>
		              <li>STEP 2</li>
		            </ul>
		          </div>
					<div class="done"></div>
					<div class="ready l30">
						<i class="icon-circle"></i>
						<ul class="l3">
							<li>支付信息</li>
							<li>STEP 3</li>
						</ul>
					</div>
		          <div class="done"></div>
		          <div class="ready l30">
		            <i class="icon-circle"></i>
		            <ul class="l3">
		              <li>确认收货</li>
		              <li>STEP 3</li>
		            </ul>
		          </div>
		          <div class="done"></div>
		          <div class="ready l30">
		            <i class="icon-circle"></i>
		            <ul class="l3">
		              <li>完成</li>
		              <li>STEP 4</li>
		            </ul>
		          </div>
		        </div>
			</div>
		</div>
		<div class="shopping-cart-content">
			<div class="hd">
		        <div class="hd-left">
		          <input type="checkbox" checked="checked" id="all1" onclick="checkAll(this)">
		          <label for="">全选</label>
		          <label class="l170">货品</label>
		        </div>
		        <div class="hd-right">
		          <label>单价（元）</label>
		          <label>数量</label>
		          <label>单位</label>
		          <label>操作</label>
		        </div>
		    </div>
		    <div id="gwcList">
		    	
		    </div>
		    <div class="ft">
		        <input type="checkbox" checked="checked" id="all2" onclick="checkAll(this)">
		        <label for="">全选</label>
		        <a  class="delete font-weight-trigger" href="javascript:delSel()">删除所选</a>
		        <!-- 导出按钮改为导出采购清单 -->
		        <button class="export font-weight-trigger" onclick="exportInfo()">导出采购清单</button>
		        <button class="saveOrder font-weight-trigger" onclick="shure()">确认下单</button>
		        <p class="allOrderInfo">
		          <span>数量总计：<strong id="allShu">0件</strong></span>
		          <span class="l20">货品金额总计：<strong class="red" id="allMoney">￥0.00</strong></span>
		        </p>
		    </div>
		</div>
	</div>
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/gwc.js?v=28' />"></script>