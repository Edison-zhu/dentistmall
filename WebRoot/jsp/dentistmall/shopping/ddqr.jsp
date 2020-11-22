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
	.address-div{
		background-color: #ffbc00;
		display: inline-block;
		width: 100%;
		text-align: center;
		padding: 14px;
	}
	/*#olinePayLabel:focus{*/
	/*	outline: red solid 1px;*/
	/*}*/
</style>
<script>
var _isFix=1;
var _isCar=3;
</script>
</head>
<body >
<header class="header">
  <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/confirmOrder.css?v=59' />">
<%--<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?9' />">--%>
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
      	购物车
    </a>
  </label>
  <label>
      	订单确认
  </label>
</p>
<div class="content">
	<div class="shopping-cart-body w1080">
		<div class="shopping-cart-header">
			<div class="shopping-flow">
				<div class="shopping-cart-icon">
		          <img src="<c:url value='/css/shopping/images/shopping-list.png'/>">
		          <span class="cn">选择收货地址：</span>
		          <span class="un">Select delivery address</span>
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
			<div class="address-list" id="addressList">
			</div>
			<div class="confirm-address-bar" style="margin-left:20px;margin-top:10px;display:none">
	            <a href="javascript:dropAddress();" class="drop" id="drop" data-action="drop" style="font-size: 14px;">显示全部地址</a>
	        </div>

		    <div id="matList">
		    </div>
<%--			<div class="invoiceInfo">--%>
<%--				<div class="invoiceInfo-item">--%>
<%--					<input type="checkbox" id="needBill" onclick="changeNeedBill()">--%>
<%--					<label>索要发票</label>--%>
<%--				</div>--%>
<%--				<div class="invoiceInfo-item" id="billTypeDiv" style="display:none">--%>
<%--					<label class="invoiceInfo-label">发票抬头：</label>--%>
<%--					<select class="invoiceInfo-select" id="billType" onchange="changeBillType()">--%>
<%--						<option value="2">公司</option>--%>
<%--						<option value="1">个人</option>--%>
<%--					</select>--%>
<%--					<input class="invoiceInfo-input" type="text" id="billHeard" placeholder="公司名称">--%>
<%--				</div>--%>
<%--				<div  class="invoiceInfo-item" id="billCodeDiv" style="display:none">--%>
<%--					<label class="invoiceInfo-label">纳税人识别号：</label>--%>
<%--					<input class="invoiceInfo-input w315" type="number" id="billCode" placeholder="纳税人识别号">--%>
<%--				</div>--%>
<%--			</div>--%>
		</div>
	</div>
</div>
<div class="u-mask"  id="addressId"></div>
<div class="g-login" style="width:500px" id="addressInfo">
<a class="open-close" id="addressClose">关闭</a>
    <div class="wrap" style="border: 0px solid #FF5F0F">
        <form id="accountForm">
        	 <input type="hidden" id="mdaId" name="mdaId" /> 
        	<input type="hidden" id="suiId" name="suiId"/> 
        	<input type="hidden" id="ifDefault" name="ifDefault" value="2" /> 
            <div class="control-group">
                <div class="hd">
                   	 收货人姓名：
                </div>
                <div class="bd">
                   <input class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" id="addressee" name="addressee">
                </div>
            </div>
            <div class="control-group">
                <div class="hd">
                                       联系电话：
                </div>
                <div class="bd">
                    <input class="ui-txtin ui-txtin-thin" style="width: 200px;" type="text" id="addresseeTelephone" name="addresseeTelephone">
                </div>
            </div>
            <div class="control-group">
                <div class="hd">
                                      所在省：
                </div>
                <div class="bd">
                	<select id="province" name="province" class="ui-txtin ui-txtin-thin" style="width: 200px;" onchange="initShi()">
                	</select>
                </div>
            </div>
            <div class="control-group">
                <div class="hd">
                                       所在市：
                </div>
                <div class="bd">
                    <select id="city" name="city" class="ui-txtin ui-txtin-thin" style="width: 200px;" onchange="initArea()">
                	</select>
                </div>
            </div>
            <div class="control-group">
                <div class="hd">
                                       所在区/县：
                </div>
                <div class="bd">
                    <select id="area" name="area" class="ui-txtin ui-txtin-thin" style="width: 200px;">
                	</select>
                </div>
            </div>
            <div class="control-group">
                <div class="hd">
                   	 详细地址：
                </div>
                <div class="bd">
                    <div class="bd">
                        <input class="ui-txtin ui-txtin-thin" style="width: 300px;" type="text" name="deliveryAddress" id="deliveryAddress" placeholder="请填写具体地址，不需要重复填写省/市/区">
                    </div>
                </div>
            </div>
            
            <div class="control-bottom clearfix" style="text-align:center">
                <a href="javascript:saveAddress()" class="fl btn ui-btn-theme" style="color:#fff;" >保存</a>
            </div>
        </form>
    </div>
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<input type="hidden" id="mmfIds" value="${mmfIds}">
<input type="hidden" id="shus" value="${shus}">
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/shopping/ddqr.js?v=59' />"></script>