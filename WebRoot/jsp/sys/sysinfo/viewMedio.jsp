<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
  <head> 
   <title>视屏播放</title> 
   <%@ include file="/jsp/lib.jsp"%> 
	<%@ include file="/jsp/link.jsp"%>
  </head> 
 
<body style="text-align:center">
	<div style="margin:0 auto;margin-top:100px;width:900px">
	<video id="my_video" class="video-js vjs-default-skin" controls preload="auto" width="900" height="500" data-setup="{}">
		<source id="visId" src="" type='video/mp4' />
	</video>
	</div>
</body>
</html>
<link href="<c:url value='/js/plugins/video-js/video-js.css?02'/>" rel="stylesheet" type="text/css">
<script src="<c:url value='/js/plugins/video-js/video.min.js?02'/>"></script>
<script>
var filePath = $.supper("getParam", "filePath");
$("#visId").attr("src",filePath);
</script>
 
 