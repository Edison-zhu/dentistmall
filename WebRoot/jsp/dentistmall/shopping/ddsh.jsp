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
var _isCar=4;
</script>
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
<body >
<header class="header">
  <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/confirmOrder.css?59' />">
<script src="<c:url value='/js/ChineseCities.js?v=1'/>"></script>
<p class="bread-crumb w1080">
  <i class="icon-homepage"></i>
  <label>
    <a href="index.htm">
      	首页
    </a>
  </label>
  <label>
  	<a href="gwc.htm">
      	订单列表
    </a>
  </label>
  <label>
      	订单信息
  </label>
</p>
<div class="content">
	<div class="shopping-cart-body w1080">
		<div class="shopping-cart-header">
			<div class="shopping-flow">
				<div class="shopping-cart-icon">
		          <img src="<c:url value='/css/shopping/images/shopping-list.png'/>">
		          <span class="cn">订单信息：</span>
		          <span class="un">View order info</span>
		        </div>
		        <div class="flow">
		          <div class="ready">
		            <i class="icon-circle green"></i>
		            <ul>
		              <li class="green">我的购物车</li>
		              <li class="green">STEP 1</li>
		            </ul>
		          </div>
		          <div class="done bGreen"></div>
		          <div class="ready l30">
		            <i class="icon-circle green"></i>
		            <ul class="l10">
		              <li class="green">确认订单信息</li>
		              <li class="green">STEP 2</li>
		            </ul>
		          </div>
				  <div class="done bGreen"></div>
				  <div class="ready l30">
				  	<i class="icon-circle green"></i>
				  	<ul class="ready l3">
				  		<li class="green">支付信息</li>
				  		<li class="green">STEP 3</li>
				  	</ul>
				  </div>
		          <div class="done bGreen"></div>
		          <div class="ready l30">
		            <i class="icon-circle green"></i>
		            <ul class="ready l3">
		              <li class="green">确认收货</li>
		              <li class="green">STEP 3</li>
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
			<div class="address-list">
				<div class="address-items-info">
		          <div class="ellips_line" style="float:left;width:330px;padding-left:20px">订单号：${order.orderCode}</div>
                  <div class="ellips_line" style="float:left;width:330px;padding-left:20px" >供应商：${order.applicantName}</div>
                  <div class="ellips_line" style="float:left;width:330px;padding-left:40px">订单状态：${order.processStatusName}</div>
                  <div class="ellips_line" style="float:left;width:330px;padding-left:20px">下单时间：<fmt:formatDate value="${order.placeOrderTime}" type="both" /></div>
                  <div class="ellips_line" style="float:left;width:330px;padding-left:20px">下单数量：${order.commodityNumber}</div>
                  <div class="ellips_line" style="float:left;width:330px;padding-left:40px" >订单金额：
                  ¥<fmt:formatNumber type="number" value="${order.placeOrderMoney}" pattern="0.00" maxFractionDigits="2"/></div>
                  <div class="ellips_line" style="float:left;width:330px;padding-left:20px">完结时间：<fmt:formatDate value="${order.endTime}" type="both" /></div>
                  <div class="ellips_line" style="float:left;width:330px;padding-left:20px">确认数量：
                      <c:if test="${order.number1!=null}"><fmt:formatNumber value="${order.number1}" pattern="#0" maxFractionDigits="0"/></c:if>
                      <c:if test="${order.number1==null}">-</c:if>
                  </div>
                  <div class="ellips_line" style="float:left;width:330px;padding-left:40px" >确认金额：
                      <c:if test="${order.actualMoney!=null}">¥<fmt:formatNumber type="number" value="${order.actualMoney}" pattern="0.00" maxFractionDigits="2"/></c:if>
                      <c:if test="${order.actualMoney==null}">-</c:if>
                  </div>
		        </div>
		        <div class="address-items">
		          <p><i class="icon-people"></i>${order.addressee}</p>
		          <p><i class="icon-address"></i>${order.province}${order.city}${order.area}${order.deliveryAddress}</p>
		          <p><i class="icon-phone"></i>${order.addresseeTelephone}</p>
		          <span class="font-weight-trigger">收货地址</span>
		        </div>
				<div class="address-items" style="margin-left:14px">
					<c:if test="${order.needBill=='1'}">
						<p>抬头:${order.billHeard}(<c:if test="${order.billType=='1'}">个人</c:if><c:if test="${order.billType=='2'}">商家</c:if>)</p>
						<c:if test="${order.billType=='2'}">
                    		<p>纳税人识别号:${order.billCode}</p>
                    	</c:if>
					</c:if>
		          <c:if test="${order.needBill!='1'}">
                    	<p>无</p>
                   </c:if>
		          <span class="font-weight-trigger">发票信息</span>
		        </div>
		        <div class="address-items" style="margin-left:15px">
				  <p>运送方式:快递</p>
				  <p>物流公司:
				   <c:if test="${order.expressName!=null}">${order.expressName}</c:if>
                   <c:if test="${order.expressName==null}">-</c:if>
				  </p>
				  <p>运单号:
				   <c:if test="${order.expressCode!=null}">${order.expressCode}</c:if>
                   <c:if test="${order.expressCode==null}">-</c:if>
				  </p>
		          <span class="font-weight-trigger">物流信息 </span>
		        </div>
			</div>
			<div class="order">
				<div class="order-header">
		          <div class="order-header-left">
		            <label>供应商：<a class="supplier font-weight-trigger" href="pcjxiangxi.htm?wzId=${order.wzId}" target="_blank">${order.applicantName}</a></label>
		          </div>
		          <div class="order-header-right">
		            <span class="order-header-item">单价</span>
		            <span class="order-header-item">订单数量</span>
		            <span class="order-header-item">发货数量</span>
		            <span class="order-header-item">入库数量</span>
		            <span class="order-header-item">已确认数量</span>
<%--		            <span class="order-header-item">确认数量</span>--%>
		          </div>
		        </div>
		        <div class="order-body" id="matList">
		        </div>
				
			</div>
		    <div class="note">
		        <label class="note-label">买家留言：</label>
		        <textarea class="note-textarea" placeholder="在此输入给商家的留言" readonly=true>${order.scopeBusiness}</textarea>
		      </div>
		      <div class="ft">
		        <div class="ft-left">
		          
		        </div>
		        
		        <div class="ft-right">
		          <c:if test="${order.processStatus !='5' && order.processStatus !='6'}">
<%--		          	<button class="endOrder font-weight-trigger" id="closeBut" <c:if test="${order.processStatus!='4'}"> style="display:none"</c:if> onclick="closeOrder()">完结订单</button>--%>
		          	<button class="saveOrder font-weight-trigger" onclick="receive()">确认收货</button>
		          </c:if>
		        </div>
		      </div>
		</div>
	</div>
</div>
<div class="u-mask"  id="addressId"></div>
<div class="g-login" style="width:500px" id="addressInfo">
	<a class="open-close" id="addressClose">关闭</a>
	<div class="wrap" style="border: 0px solid #FF5F0F">
		<form id="accountForm" onkeydown="if(event.keyCode==13){return false;}">
			<input type="hidden" id="mdaId" name="mdaId" />
			<input type="hidden" id="suiId" name="suiId"/>
			<input type="hidden" id="ifDefault" name="ifDefault" value="2" />
			<div class="control-group">
				<div class="hd">
					安全码：
				</div>
				<div class="bd">
					<input type="password" class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" id="securityCode" name="securityCode">
				</div>
			</div>
			<div class="control-bottom clearfix" style="text-align:center">
				<a href="javascript:checkSecurityCodeFunc()" class="fl btn ui-btn-theme" style="color:#fff;" >确认</a>
			</div>
		</form>
	</div>
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<input type="hidden" id="moiId" value="${order.moiId}">
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/ddsh.js?34' />"></script>