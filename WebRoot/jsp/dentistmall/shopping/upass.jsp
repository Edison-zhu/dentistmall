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
     密码修改
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
		        <a class="taps-item-active" href="upass.htm">密码修改</a>

					<a class="taps-item" href="upsafetycode.htm">安全码修改</a>

		     </div>
		     <div class="conen_qw">
				<div   id="div1" style ="display :block; border:0px;" class ="divContent1">
					<form id="accountForm" >
						<table width="" border="0" style="margin-top:15px;">
						  <tr>
						    <td width="" height=""><div align="right" class="zitiwe">当前密码：</div></td>
						    <td width=""><div><input type="password" class="form-control" name= "oldPwd" id="oldPass" value="" placeholder="旧密码"/></div></td>
						  </tr>
						  <tr>
						    <td height=""><div align="right" class="zitiwe"> 设置新密码：  </div></td>
						    <td><div><input type="password" class="form-control" name= "newPwd" id="newPass" value="" placeholder="新密码"/></div></td>
						  </tr>
						  <tr>
						    <td height=""><div align="right" class="zitiwe">  确定新密码：</div></td>
						    <td><div><input type="password" class="form-control" id="newPass2"  value="" placeholder="密码确认"/></div></td>
						  </tr>
						  <tr>
							  <td></td>
						    <td colspan="2" ><a href="javascript:savePass()" class='btn btn-success  btn-sm'>保存</a></td>
						  </tr>
						</table>
					</form>
				</div>
				</div>
	    </div>
	</div>
</div>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
</body>
</html>
<script type="text/javascript">
	$(function(){
		initQhCount();
		var timer1=window.setTimeout(initQhCount,5*60*1000); 
	});
   function savePass(){
   		if($("#oldPass").val() == null || $("#oldPass").val()== ""){
   			$.supper("alert",{ title:"操作提示", msg:"旧密码不能为空！"});
   			return;
   		}
   		if($("#newPass").val() == null || $("#newPass").val()== ""){
   			$.supper("alert",{ title:"操作提示", msg:"新密码不能为空！"});
   			return;
   		}
   		if($("#newPass").val().length < 6){
   			$.supper("alert",{ title:"操作提示", msg:"新密码长度不能小于6位！"});
   			return;
   		}
   		if($("#newPass2").val() == null || $("#newPass2").val()== ""){
   			$.supper("alert",{ title:"操作提示", msg:"密码确认不能为空！"});
   			return;
   		}
   		var pass1 = $("#newPass").val();
		var pass2 = $("#newPass2").val();
		if(pass1 != pass2){
			$.supper("alert",{ title:"操作提示", msg:"两次密码输入 不一致！"});
   			return;
		}
   		
   		var data =$('#accountForm').serialize();
   		$.supper("doservice", {"service":"SysUserService.updateLoginUserPass","data":data, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:function(){
						$("#oldPass").val("");
						$("#newPass").val("");
						$("#newPass2").val("");
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
