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
</head>
<style>
	table{

	}
	table tr{
		padding-top: 20px;
	}
	table tr td{
		padding-top: 20px;
		padding-left: 10px;
	}
	.td-right{
		padding-left: 70px;
	}
</style>
<body >
<header class="header">
  <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/myOrder.css?14' />">
<p class="bread-crumb w1080">
  <i class="icon-homepage"></i>
  <label>
    <a href="index.htm">
      	首页
    </a>
  </label>
  <label>
     安全码修改
  </label>
</p>
<div class="content">
	<div class="shopping-cart-body w1080">
		<div class="shopping-cart-header">
	      <div class="shopping-flow">
	        <div class="shopping-cart-icon">
	          <img src="<c:url value='/css/shopping/images/shopping-order.png'/>">
	          <span class="cn">账号设置:</span>
	          <span class="un">Account Settings</span>
	          <a class="taps" href="qhtx.htm">缺货提醒<strong id="newsCount"></strong></a>
	          <a class="taps" href="scj.htm">我的收藏夹</a>
	          <a class="taps" href="qgc.htm">弃购车</a>
	          <a class="taps" href="ddlb.htm">查询订单</a>
	          <a class="taps active" href="zhsz.htm">账号设置</a>
	        </div>
	      </div>
	    </div>
	    <div class="shopping-cart-content">
	    	<div class="content-taps">
		        <a class="taps-item" href="zhsz.htm">个人信息</a>
		        <a class="taps-item" href="upass.htm">密码修改</a>

					<a class="taps-item-active" href="upsafetycode.htm">安全码修改</a>

		     </div>
		     <div class="conen_qw">
				<div   id="div1" style ="display :block; border:0px;" class ="divContent1">
					<div style="font-size: 11px;color: #999999;">提示：安全码由6位数字组成，用于确认下单或收货及售后使用，请谨慎保管!</div>
					<form id="accountForm">
						<table width="" border="0" style="margin-top:15px;">
						  <tr>
						    <td width="" height=""><div align="right" class="zitiwe">当前安全码：</div></td>
						    <td width=""><div><input type="password" class="form-control" name= "oldSecurity" id="oldSecurity" value="" placeholder="当前安全码"/></div></td>
						  </tr>
						  <tr>
						    <td height=""><div align="right" class="zitiwe"> 新安全码：  </div></td>
						    <td><div><input type="password" class="form-control" name= "newSecurity" id="newSecurity" value="" placeholder="新安全码"/></div></td>
						  </tr>
						  <tr>
						    <td height=""><div align="right" class="zitiwe">  确认安全码：</div></td>
						    <td><div><input type="password" class="form-control" id="newSecurity2"  value="" placeholder="确认安全码"/></div></td>
						  </tr>
						  <tr>
							  <td></td>
						    <td colspan="2" ><a href="javascript:saveSafetyCode()" class='btn btn-success  btn-sm'>保存</a></td>
						  </tr>
						</table>
					</form>

				</div>
				 <span id="message">安全码未开启！</span>
				</div>
	    </div>
	</div>
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
</body>
</html>
<script type="text/javascript">
	$(function(){
		var getOpenSecurityService = 'sysUserService.getOpenSecurity';
		$.supper("doservice", {
			"service": getOpenSecurityService, "ifloading": 1, "BackE": function (jsondata) {
				console.log('获取是否需要安全码--------',jsondata)
				if (jsondata.code == "1") {
					$('#div1').show()
					$('#message').hide()
				}else {
					$('#div1').hide()
					$('#message').show()

				}
			}
		});
		initQhCount();
		var timer1=window.setTimeout(initQhCount,5*60*1000); 
	});
   function saveSafetyCode(){
   		if($("#oldSecurity").val() == null || $("#oldSecurity").val()== ""){
   			$.supper("alert",{ title:"操作提示", msg:"当前安全码不能为空！"});
   			return;
   		}
   		if($("#newSecurity").val() == null || $("#newSecurity").val()== ""){
   			$.supper("alert",{ title:"操作提示", msg:"新安全码不能为空！"});
   			return;
   		}
   		if($("#newSecurity").val().length < 6){
   			$.supper("alert",{ title:"操作提示", msg:"新安全码长度不能小于6位！"});
   			return;
   		}
	   if($("#newSecurity").val().length > 6){
		   $.supper("alert",{ title:"操作提示", msg:"新安全码长度不能大于6位！"});
		   return;
	   }
   		if($("#newSecurity2").val() == null || $("#newSecurity2").val()== ""){
   			$.supper("alert",{ title:"操作提示", msg:"安全码确认不能为空！"});
   			return;
   		}
   		var pass1 = $("#newSecurity").val();
		var pass2 = $("#newSecurity2").val();
		if(pass1 != pass2){
			$.supper("alert",{ title:"操作提示", msg:"两次安全码输入 不一致！"});
   			return;
		}

	   var reg = new RegExp(/^\d{6}$/); ;
	   if(!reg.test($("#oldSecurity").val())){
		   $.supper("alert",{ title:"操作提示", msg:"当前安全码只能为6位数字！"});
		   return;
	   }
	   if(!reg.test($("#newSecurity").val())){
		   $.supper("alert",{ title:"操作提示", msg:"新安全码只能为6位数字！"});
		   return;
	   }
	   if(!reg.test($("#newSecurity2").val())){
		   $.supper("alert",{ title:"操作提示", msg:"确认安全码只能为6位数字！"});
		   return;
	   }
   		
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"sysUserService.updateSecurityCode","data":data, "BackE":function (jsondata) {
   			console.log(jsondata)
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:function(){
						$("#oldSecurity").val("");
						$("#newSecurity").val("");
						$("#newSecurity2").val("");
					}});
				}else
					$.supper("alert",{ title:"操作提示", msg:jsondata.meg});
 		}});
   		
   }
   function initQhCount(){
		$.supper("doservice", {"service" : "MdNewsInfoService.getInventoryNewList","BackE" : function(jsondata) {
				if (jsondata.code == "1") {
					if(jsondata.obj != null){
						if(jsondata.obj.length >0)
							$("#newsCount").html(jsondata.obj.length);
						else
							$("#newsCount").html("");
					}
				}
			}
		});
	}
</script>
