<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html>
  <head> 
   <title>采购中心系统</title> 
   <%@ include file="/jsp/lib.jsp"%> 
   <%@ include file="/jsp/link.jsp"%>
<%--	  <link rel="stylesheet" href="../myIcon/iconfont.css">--%>
  </head>
<body class="gray-bg">
	<!-- 主信息区域begin -->
<div class="wrapper wrapper-content animated fadeInRight ">
		    <!-- 标题栏区域 begin-->
						<div class="ibox-title" style="margin: 0 15px;">
							<!-- 标题文字区域begin -->
							<h5>
								<i class="fa fa-flask"></i>&nbsp&nbsp<span>库存信息</span> 
							</h5>
							<!-- 标题文字区域end --> 
							<div class="ibox-tools" id="win_tools_but"   > 
							</div>
						</div>
						<!-- 标题栏区域 end-->
						
						<!-- 查询区域begin -->
						<div class="ibox-content" style="padding: 5px 10px 10px;margin: 0 15px;">
							<form class="form-horizontal" id="win_form_search"> 
							<div  id="win_form_hidden" ></div>
							</form>
						</div>
	<div style="float: right;margin: 10px 15px;"><button id="win_but_search" onclick="main_search()" type="button" style="margin-left: 5px; width: 95px; vertical-align: middle;" class="btn btn-rounded btn-default btn-sm fa fa-search ">&nbsp;查询&nbsp;</button></div>
						<!-- 查询区域end -->
 
						<!-- 表格区域begin -->
						<div class="ibox-content" style="padding: 5px 10px 10px;margin: 0 15px;">
							<table id="win_datagrid_main" class="mmg"></table>
							<div id="_all_win_datagrid_pg" style="text-align: right;"></div> 
						</div>
						<!-- 表格区域end -->
		 
	</div>
<%--	position: absolute;left: 1095px;top: 40px    <a onclick="chickSafety()"> --%>
	<div id="safetyEw" style="display: none"><img width="10px" height="10px" src="/dentistmall/img/SafetyEarlyWarning.png"/><span  style="font-size: 12px;color: #333333">安全预警设置</span></div>
<%--	<a onclick="safetyhideClick()">--%>
	<div id="safetyhide" style="background-color:white;width: 350px;height:250px;border: #0a2b39 solid 0px;position: absolute;top: 100px;left: 1100px;z-index:9999;display: none">
			<div class="top" style="width: 350px;height: 30px;margin-top: 8px;border-bottom:#C0C0C0 solid 1px">
				<img width="25px" height="25px" src="/dentistmall/img/SafetyEarlyWarning.png"/>
				<span style="font-size: 14px;"><b>安全预警设置</b></span>
			</div>
			<div class="middle" style="margin-top: 20px">
				<span style="margin-left: 20px">库存数量设置 &nbsp;&nbsp;<input id="inputMin" type="text" placeholder="最小安全数" onclick="yzInput()" style="width:100px;"/>&nbsp;~&nbsp;
				<input id="inputMax" type="text" placeholder="最大安全数" onclick="yzInput()" style="width:100px;"/>
				</span>
			</div>
			<div id="yz" style="width: 200px;height: 13px;margin-left:20px;display: none"><span style="font-size: 10px;color: red">请输入正确的数字</span></div>
			<div id="yz1" style="width: 200px;height: 13px;margin-left:20px;display: none"><span style="font-size: 10px;color: red">最小安全数不能大于最大安全数</span></div>
		<div class="middle2" style="margin-top: 20px">
				<span style="margin-left: 20px"><input type="checkbox" onclick="checkboxMatName(0)" value="0">产品名称:&nbsp;&nbsp;&nbsp;&nbsp;<select style="width: 80px" id="select1"><option value="1" selected >包含</option><option value="2">不包含</option></select>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width:130px" id="input1" value=""></span>
			</div>
			<div class="bottom" style="margin-top: 25px">
				<span style="margin-left: 20px"><input type="checkbox" onclick="checkboxNorm(1)">产品规格:&nbsp;&nbsp;&nbsp;&nbsp;<select style="width: 80px" id="select2"><option value="1" selected>包含</option><option value="2">不包含</option></select>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width:130px" id="input2" value=""></span>
			</div>
			<div style="margin-top: 20px">
				<span style="float: right"><button type="button"  onclick="onclickSafety()" class="btn btn-primary">一键设置</button></span>
			</div>
	</div>

<%--	</a>--%>
	<!-- 主信息区域end -->
</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/storage/inventoryList.js?v=164'/>"></script>

 