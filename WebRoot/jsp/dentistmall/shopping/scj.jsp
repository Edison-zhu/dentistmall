<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
  <title>优牙库</title>
  <%@ include file="/jsp/lib.jsp"%>

</head>
<style>
	.ft {
		height: 60px;
		line-height: 60px;
		background-color: #f9f9f9;
		width: 1040px;
		margin: 0 auto;
		border: 1px solid #ddd;
	}
	.scjCount{
		font-size: 20px;
		padding-left: 2%;
		margin-top: 2%;
	}
	.scjCount span{
		font-size: 20px;
		padding-left: 1%;
	}
	#scjCount{
		font-size: 20px;
		padding-left: 5px;
		padding-right: 5px;
	}
	.scjBatchManage{
		font-size: 16px;
		text-align: center;
		float: right;
		position: absolute;
		top: 15px;
		right: 2%;
	}
	.scjBatchManage input{
		padding-top: 4px;
	}
	.split{
		font-weight: bold;
		margin-left: 5px;
		margin-right: 2px;
	}
	.checkscjImg{
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
	.favite{
		font-size: 20px;
		color: grey;
		margin-left: 10px;
		margin-top: 10px;
	}
	.ft input {
		position: relative;
		top: 2px;
		margin: 0 10px 0 32px;
	}
	.ft .delete {
		margin-left: 20px;
		color: #155898;
	}
	.ft .allOrderInfo {
		display: inline-block;
		float: right;
		margin-right: 20px;
	}
	.l20 {
		margin-left: 20px;
	}
	.ft button {
		float: right;
		width: 150px;
		height: 30px;
		border: 2px solid #0bc176;
		border-radius: 5px;
		margin: 15px 10px;
		font-size: 18px;
		font-family: null;
		line-height: 25px;
	}
	.ft .export {
		 background-color: #ffffff;
		 color: #0bc176;
	 }
	.a-left{
		width: 14px;
		display: inline-block;
		height: 20px;
		background-color: #ddd;
		position: relative;
		top: 1px;
		line-height: 25px;
		text-align: center;
	 }
	.a-right{
		width: 14px;
		display: inline-block;
		height: 20px;
		background-color: #ddd;
		position: relative;
		top: 1px;
		line-height: 25px;
		text-align: center;
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
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?8' />">
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
	          <span class="cn" >我的收藏夹:</span>
	          <span class="un">My Favorite</span>
	          <a class="taps" href="qhtx.htm">缺货提醒<strong id="newsCount"></strong></a>
	          <a class="taps active" href="scj.htm">我的收藏夹</a>
	          <a class="taps" href="qgc.htm">弃购车</a>
	          <a class="taps" href="ddlb.htm">查询订单</a>
	          <a class="taps" href="zhsz.htm">账号设置</a>
	        </div>
	      </div>
	    </div>
	    <div class="shopping-cart-content">
			<div style="margin-top: 10px">
				<a class="favite" href="#" onclick="findByedAction()">已购商品</a>
				<a class="favite" href="#" onclick="myFavouter()">我的收藏夹</a>
			</div>

			<div class="scjCount">
				全部商品(<span id="scjCount">0</span>)<span>|</span>
				<a href="#" onclick="clearQgc()">一键清除所有商品</a><br/>
				<div>
					<span onclick="bySort()" style="cursor: pointer">按照购买时间排序</span>
				</div>

			</div>
			<div class="scjBatchManage form-inline">
                <span id="showHideManage" style="display: none;">
                    <input type="checkbox" onclick="selectscjAllMats()" value="全选" /><span>全选</span><span class="split">|</span>
                    <a href="#" onclick="clearscjSelectMats()">移除</a>
                </span>
				<button id="bacthscjManageBtn" class="btn btn-primary" onclick="batchscjManageClick()" disabled>批量管理</button>
				<div style="display: inline">
					<input type="text" id="searchScjName" name="searchScjName" class="form-control" placeholder="请输入内容" />
					<button id="searchScjBtn" class="btn btn-primary" onclick="searchScj()">搜索</button>
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

			<div class="hds" style="margin-top:20px;display: none">
				<div class="hd-left">
					<input type="checkbox" checked="checked" id="all1" onclick="checkAll(this)">
					<label for="">全选</label>
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
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/scj.js?v=96' />"></script>