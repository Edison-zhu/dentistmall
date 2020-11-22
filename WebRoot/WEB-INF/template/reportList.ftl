<!DOCTYPE html> 
<html>
  <head> 
   <title>心电报告历史记录</title> 
   <meta http-equiv="X-UA-Compatible" content="edge"/>
		<meta name="renderer" content="webkit">
		<meta http-equiv="Content-Script-Type" content="text/javascript"/>
		<meta name="format-detection" content="telephone=no"/>
		<meta name="viewport" content="width=device-width,user-scalable=no">
	<#include "link.html"/> 
  </head> 
<body >
<body style="overflow:hidden;">
	<div class="panel-body" style="">
		<div class="form-group" style="background-color:#F8F9F9;padding:5px;font-size:10px" >
		历史记录
    </div>
    <#list historyList as history> 
	<a class="form-group" style="font-size:13px;padding-left:5px" href="${history.report_path}">
		<div>
			<span style="margin-right:2px;">最高心率:</span><span style="width:50px">${history.zgxl}</span>
			<span style="margin-right:2px;margin-left:80px">最低心率</span><span >${history.zdxl}</span>
		</div>
		<div>
			<span style="margin-right:2px">${history.time}</span>
			<span style="margin-right:2px;margin-left:10px">${history.file_size}</span>
		</div>
	</a>
	</#list> 
	<div class="form-group" style="font-size:13px;" >
		<a href="${pageId}_1.html"> 首页 </a> 
		<#if curPage gt 1>  
		<a href="${pageId}_${curPage-1}.html"> 上一页</a> 
		</#if>
		${curPage}
		<#if curPage lt countPage>  
		 <a href="${pageId}_${curPage+1}.html">下一页</a>
		 </#if>
		 <a href="${pageId}_${countPage}.html"> 尾页</a>
    </div>
	</div>
</body>
</html>


 