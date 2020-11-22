<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"	src="<c:url value='/javaScript/dentistmall/mdMaterielType.js?v=04'/>"></script>
<nav class="nav">
  <div class="nav-info w1080 flex">
    <div class="product-categories">
      <b class="product-categories-a" id="_allType">全部商品分类 <span class="icon-arrow-down-solid"></span></b>
      <div class="nav-category">
        <ul class="category-ul-level-1" id="sortList">
        </ul>
      </div>
    </div>
    <ul class="flex nav-lists">
      <li class="index <c:if test="${selType=='index'}">active</c:if>" ><a href="index.htm">首页</a></li>
      <c:forEach var="tab" items="${tabList}">
		  <li class="tab_${tab.mmtId} <c:if test="${mmtId==tab.mmtId}">active</c:if>">
		  	<a href="liebiao.htm?mmtId=${tab.mmtId}" >${tab.mmtName}</a>
		  </li>
	  </c:forEach>
      <li class="hzcj <c:if test="${selType=='hzcj'}">active</c:if>" ><a href="hzcj.htm">合作厂家</a></li>
    </ul>
  </div>
</nav>
<input type="hidden" id="selType" value="${selType}">
<input type="hidden" id="selMmtId" value="${mmtId}">
<script>
  $(document).ready(function() {
  	
    initSortList();
    $(".product-categories-a").bind("click",function(){
      $(".nav-category").toggle();
      $(".category-ul-level-2").hide();
      $(".category-p-level-1").removeClass("active");
      $(this).find("span").toggleClass("icon-arrow-down-solid icon-arrow-up-solid");
    });

    $(".category-p-level-1").bind("mouseover",function(){
      $(".category-ul-level-2").hide();
      $(".category-p-level-1").removeClass("active");
      $(this).addClass("active");
      $(this).next().show();
    });
  });
  function initSortList(){
  	var str=""; 
	if(_sortList != null && _sortList.length > 0){
		for(var  x=0;x < _sortList.length;x++){
			var one = _sortList[x];
			str += "<li class=\"category-li-level-1\"><p class=\"category-p-level-1 icon-list-item\"><a href=\"liebiao.htm?mmtId="+one.id+"\">"+one.name+"</a></p>";
	        str +="<ul class=\"category-ul-level-2\">";
	        var twoList = one.childList;
	        if(twoList != null && twoList.length > 0){
	        	for(var i =0,j=1;i < twoList.length ;i ++,j++){
	        		var two = twoList[i];
	        		str += "<li class=\"category-li-level-2\"><div class=\"panel-category flex\">";
	        		str += "<div class=\"category-name icon-arrow-right\"><a href=\"liebiao.htm?mmtId="+two.id+"\">"+two.name+"</a></div>";
	        		str += "<div class=\"category-list\"><ul>";
	        		var treeList = two.childList;
	        		for(var k =0;k < treeList.length ;k ++){
	        			var three = treeList[k];
	        			str += "<li><a href=\"liebiao.htm?mmtId="+three.id+"\">"+three.name+"</a></li>";
	        		}
	        		str += "</ul></div>"
	        			+"</div></li>";
	        	}
	        }
	        str += "</ul></li>";
		}
	}
	$("#sortList").html(str);
	$("#sortList").hover(
			null,
			function() {
				$(".nav-category").toggle();
		      	$(".category-ul-level-2").hide();
		      	$(".category-p-level-1").removeClass("active");
		      	$("#_allType").find("span").toggleClass("icon-arrow-down-solid icon-arrow-up-solid");
			});
  }
</script>