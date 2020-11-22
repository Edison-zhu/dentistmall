<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript"	src="<c:url value='/javaScript/dentistmall/mdMaterielType.js?v=04'/>"></script>
<style>
  .flexs {
    display: -webkit-flex;
    display: flex;
  }
  .navs {
    height: 65px;
    background: url("../images/nav-background.png") no-repeat top left;
  }
  .product-categories >.product-categories-c {
    width: 208px;
    display: block;
    height: 65px;
    line-height: 65px;
    /*background: #145897;*/
    /*color: #ffffff;*/
    text-align: center;
    font-size: 16px;
    cursor: pointer;
  }
  .product-categories >.product-categories-a .icon-arrow-down-solid {
    color: #0e0d0d;
    font-weight: bold;
  }
  .category-ul-level-1 .category-p-level-1 a {
    font-size: 14px;
    color: black;
  }
  .category-ul-level-1 .category-p-level-1 {
    background-color: white;
    color: black;
    height: 35px;
    line-height: 35px;
    padding-left: 15px;
    cursor: pointer;
  }
  .category-ul-level-2 {
    width: 750px;
    position: absolute;
    top: 0;
    left: 199px;
    background-color: #fff;
    border: 1px solid #eee;
    border-left: none;
    display: none;
  }
  .category-list li a {
    color: #666666;
    display: block;
    padding: 5px 0;
  }
  .panel-category .category-list li:after{
    content: ' ';
    margin: 0 5px 0 10px;
  }
  .nav-lists > li > a {
    color: #666666;
    padding: 0 10px;
    font-size: 16px;
  }
</style>
<nav class="navs">
  <div class="nav-info w1081 flexs">
    <div class="product-categories" >
      <b class="product-categories-c" id="_allType" style="margin-left: -30px">全部商品分类 <span class="icon-arrow-down-solid"></span></b>
      <div class="nav-category"style="margin-left: -21px;background: white;border-width:1px;border-color: #f3f3f4">
        <ul class="category-ul-level-1" id="sortList">
        </ul>
      </div>
    </div>
  </div>
</nav>
  </div>

<input type="hidden" id="selType" value="${selType}">
<input type="hidden" id="selMmtId" value="${mmtId}">
<script>
  $(document).ready(function() {
    //initSortList();
    $(".product-categories-c").bind("click",function(e){
      $(".nav-category").toggle();
      if($("#ibox").is(":hidden")){
        document.getElementById("ibox").style.display = "block";
      }else{
        $("#ibox").hide();     //如果元素为显现,则将其隐藏
      }
      $(".category-p-level-1").removeClass("active");
      $(this).find("span").toggleClass("icon-arrow-down-solid icon-arrow-up-solid");
    });

    $(".category-p-level-1").bind("mouseover",function(){
      $("#ibox").hide();
      $(".category-p-level-1").removeClass("active");
      $(this).addClass("active");
      $(this).next().show();
    });
  });
	$("#ibox").hover(
			null,
			function() {
				$(".nav-category").toggle();
		      	$(".category-ul-level-2").hide();
		      	$(".category-p-level-1").removeClass("active");
		      	$("#_allType").find("span").toggleClass("icon-arrow-down-solid icon-arrow-up-solid");
			});
  function aee(mmtId) {
          var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPicking.jsp"});
          $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{mmtId:mmtId}});
          $.supper("showTtemWin",{ "url":att_url,"title":"新增领料"});
  }
</script>
<script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/addPicking.js?3'/>"></script>