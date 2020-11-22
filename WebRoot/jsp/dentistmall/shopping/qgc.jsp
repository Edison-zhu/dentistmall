<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
  <title>优牙库</title>
  <%@ include file="/jsp/lib.jsp"%>
	<style>
		.qigouCount{
			font-size: 20px;
			padding-left: 2%;
			margin-top: 2%;
		}
		.qigouCount span{
			font-size: 20px;
			padding-left: 1%;
		}
		#qigouCout{
			font-size: 20px;
			padding-left: 5px;
			padding-right: 5px;
		}
		.qigouBatchManage{
			font-size: 16px;
			text-align: center;
			float: right;
			position: absolute;
			top: 15px;
			right: 2%;
		}
		.qigouBatchManage input{
			padding-top: 4px;
		}
		.split{
			font-weight: bold;
			 margin-left: 5px;
			 margin-right: 2px;
		 }
		.checkImg{
			margin-top: 8%;
			float: right;
			position: absolute;
			right: 2%;
		}
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
</head>
<script>
var _isFix=1;
var _isCar=2;
</script>
</head>
<body >
<header class="header">
  <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?12' />">
<p class="bread-crumb w1080">
  <i class="icon-homepage"></i>
  <label>
    <a href="index.htm">
      	首页
    </a>
  </label>
  <label>
      我的收藏夹
  </label>
</p>
<div class="content">
	<div class="shopping-cart-body w1080">
		<div class="shopping-cart-header">
	      <div class="shopping-flow">
	        <div class="shopping-cart-icon">
	          <img src="<c:url value='/css/shopping/images/shopping-order.png'/>">
	          <span class="cn" >弃购车:</span>
	          <span class="un">Back-Out Shopping Car</span>
	          <a class="taps" href="qhtx.htm">缺货提醒<strong id="newsCount"></strong></a>
	          <a class="taps" href="scj.htm">我的收藏夹</a>
	          <a class="taps active" href="qgc.htm">弃购车</a>
	          <a class="taps" href="ddlb.htm">查询订单</a>
	          <a class="taps" href="zhsz.htm">账号设置</a>
	        </div>
	      </div>
	    </div>
	    <div class="shopping-cart-content">
			<div class="qigouCount">
                全部商品(<span id="qigouCout">0</span>)<span>|</span>
                <a href="#" onclick="clearQgc()">一键清除所有商品</a>
            </div>
            <div class="qigouBatchManage form-inline">
                <span id="showHideManage" style="display: none;">
                    <input type="checkbox" onclick="selectAllMats()" value="全选" /><span>全选</span><span class="split">|</span>
                    <a href="#" onclick="clearSelectMats()">移除</a><span class="split">|</span>
                    <a href="#" onclick="addCarts()">加入购物车</a>
                </span>
                <button id="bacthManageBtn" class="btn btn-primary" onclick="batchManageClick()" disabled>批量管理</button>
				<div style="display: inline">
					<input type="text" id="searchQgcName" name="searchQgcName" class="form-control" placeholder="请输入内容" />
					<button id="searchQgcBtn" class="btn btn-primary" onclick="searchQgc()">搜索</button>
				</div>
            </div>
		     <div class="hd" style="margin-top:20px">
		        <div class="hd-left">
		          <label class="l32">货品</label>
		        </div>
		        <div class="hd-right">
		          <label>单价（元）</label>
		          <label>单位</label>
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
<script src="<c:url value='/javaScript/dentistmall/shopping/qgc.js?13' />"></script>