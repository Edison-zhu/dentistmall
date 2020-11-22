<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
    .beian:link{
        text-decoration: underline ;
    }
    .beian:hover{
        color: blue;
        text-underline: underline ;
    }
</style>
<footer class="footer">
  <div class="footer-nav" style="margin-bottom:10px">
  	<c:forEach var="comm" items="${dbProList}"   begin="0"  varStatus="s" step="1">
		<a>${comm.newsTitle}</a>
	</c:forEach>
  </div>
  <img src="<c:url value='/css/shopping/images/copyright-1.jpg' />">
  <img src="<c:url value='/css/shopping/images/copyright-2.jpg' />">
  <img src="<c:url value='/css/shopping/images/copyright-4.jpg' />">
    <div style="margin-bottom: 10px;margin-top: 10px">
        <a class="beian" id="beian" href="http://www.beian.miit.gov.cn" target="_blank">版权所有：优牙库网络科技(苏州)有限公司    备案号：苏ICP备18035594号</a>
    </div>
<%--    <a class="beian" href="http://www.beian.miit.gov.cn" target="_blank"><p class="footer-info">©苏ICP备18035594号-1</p></a>--%>
</footer>