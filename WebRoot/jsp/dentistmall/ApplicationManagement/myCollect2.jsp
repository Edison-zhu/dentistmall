<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<link href="<c:url value='/css/font-awesome.min.css?v=4.3.0' />" rel="stylesheet">
<link href="<c:url value='/css/animate.css' />" rel="stylesheet">
<%--<script src="<c:url value='/js/jquery-2.1.1.min.js' />"></script>--%>
<script src="<c:url value='/js/bootstrap.min.js?v=3.4.0' />"></script>
<link href="<c:url value='/css/bootstrap.min.css?v=3.4.0' />" rel="stylesheet">
<script type="text/javascript" src="<c:url value='/js/supper/Xsupper.js?v=71'/>"></script>
<link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?13' />">
<script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
<script src="<c:url value='/js/cookie.js' />"></script>
<script src="<c:url value='/js/plugins/layer/layer.min.js' />"></script>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/common.css?46' />">
<link rel="stylesheet" href="<c:url value='/css/shopping/css/font/font.css?02' />">
<script type="text/javascript" src="<c:url value='/js/shopping/include.js?03' />"></script>
<script src="<c:url value='/javaScript/dentistmall/ApplicationManagement/myCollect.js?v=71' />"></script>
<style>
    .flexs {
        display: -webkit-flex;
        display: flex;
    }
    .w1081 {
        width: 1080px;
        margin: 0 auto;

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
    .hot-search-title {
        float: left;
        margin: 0 auto;
        top: 43px;
        display: inline-block;
        text-align: center;
        width: 50px;
        height: 22px;
        overflow: hidden;
        text-overflow: ellipsis;
        z-index: 8;
        /*white-space: nowrap;*/
    }

    .input-container {
        z-index: 99;
    }

    .hidden-search {
        display: none;
        float: left;
        margin: 0 auto;
        top: 40px;
        background-color: white;
        height: auto;
        overflow-y: auto;
    }

    .hidden-search ul {
        width: 100%;
        border: 1px solid #979797;
        box-sizing: border-box;
    }

    .hidden-search ul li {
        color: #7a77c8;
        cursor: pointer;
        box-sizing: border-box;
    }

    .hidden-search ul li:hover {
        background-color: lightgrey;
        color: white;
    }

    .hidden-search ul a {
        display: block;
        width: 100%;
        height: 100%;
        padding-left: 30px;
    }

    .select-box-div:hover {
        cursor: pointer;
    }

    .header-middle-container-content {
        width: 1080px;
        display: flex;
        justify-content: center;
        margin: 0 auto;
    }
    /* 新顶部样式 */
    .newtop{
        display: flex;
    justify-content: space-between;
    align-items: center;
    border: 1px solid #EBEBEB;
        margin:40px 30px;
        padding: 10px 0 7px 0;
    }
    .newtop .form{
        padding-left: 20px;
display: flex;
justify-content: flex-start;
align-items: center;
    }
    .newtop .form .tit{
font-size: 14px;
color: #333333;
margin-right: 10px;
    }
    .newtop .form .input{

    }
    .newtop .form .input input{
        width: 420px;
    height: 30px;
    outline: none;
    border: 1px solid #EBEBEB;
    font-size: 13px;
    color: #666;
    padding-left: 5px;     
    }
    .newtop .form .btn{

    }
.newtop .form .btn a{
display: flex;
justify-content: center;
    align-items: center;
    width: 100px;
    height: 30px;
    border: 1px solid #1ab394;
    font-size: 14px;
    border-radius: 15px;
}

    .newtop .itemssbox{
        margin-left: -158px;
        display: flex;
    justify-content: center;
    align-items: center;
    }
    .newtop .itemss{
     display: flex;
    justify-content: center;
    align-items: center;
    width: 100px;
    height: 30px;
    background: #1ab394;
    font-size: 14px;
    border-radius: 15px;
    margin-right: 20px;
        color: #ffffff;
    }
     .newtop .itemss:hover{
         cursor:pointer;
         color: #ffffff;
     }
    .newtop .itemss a{
        color: #fff; 
    }
    .newtop .itemss span{
        width: 18px;
height: 18px;
text-align: center;
line-height: 18px;
        position: absolute;
top:-5px;
right:-5px;
display: block;
border: 1px solid #ed5565;
border-radius: 50%;
color: #ed5565;
font-size: 12px;
font-weight: bold;
    }
    .newtop .posre{
        position: relative;
    }
    .newmlf{
        margin-left: -855px
    }
    .newmlf2{
        margin-left: -428px
    }
    @media (max-width: 1366px) {
        .newmlf{
            margin-left: -935px
        }
        .newmlf2{
            margin-left: -522px
        }
        .newtop .itemssbox{
            margin-left: -335px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
    }
</style>
<div class="newtop">
  <form class="form">
<%--      <%@ include file="/jsp/dentistmall/shopping/navigation1.jsp" %>--%>
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
    <div class="input" id="input-container"class="input-container">
        <input type="text" class="newmlf" id="searchName" placeholder="商品物料名称、型号、别名、物料编码、物料名称的等首字母关键字"/>
    </div>
    <div class="btn">
        <a class="search-button newmlf2" onclick="jump()"id="search_button">搜索</a>
    </div>
 </form>
  <div class="itemssbox">
      <a onclick="shoucangs()" class="itemss">我的收藏</a>
      <a onclick="shenqings()" class="itemss">我的申请单</a>
      <a onclick="wuliaos()" class="itemss posre">购物车<span id="countCollects"></span></a>
  </div>
</div>
<script type="text/javascript" src="<c:url value='/javaScript/dentistmall/shopping/shophead1.js?v=44'/>"></script>
<script>
    // $(document).ready(function() {
    //     //initSortList();
    //     $(".product-categories-c").bind("click",function(e){
    //         $(".nav-category").toggle();
    //         if($("#ibox").is(":hidden")){
    //             document.getElementById("ibox").style.display = "block";
    //         }else{
    //             $("#ibox").hide();     //如果元素为显现,则将其隐藏
    //         }
    //         $(".category-p-level-1").removeClass("active");
    //         $(this).find("span").toggleClass("icon-arrow-down-solid icon-arrow-up-solid");
    //     });
    //
    //     $(".category-p-level-1").bind("mouseover",function(){
    //         $("#ibox").hide();
    //         $(".category-p-level-1").removeClass("active");
    //         $(this).addClass("active");
    //         $(this).next().show();
    //     });
    // });
    // $("#ibox").hover(
    //     null,
    //     function() {
    //         $(".nav-category").toggle();
    //         $(".category-ul-level-2").hide();
    //         $(".category-p-level-1").removeClass("active");
    //         $("#_allType").find("span").toggleClass("icon-arrow-down-solid icon-arrow-up-solid");
    //     });
    // function aee(mmtId) {
    //     var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPicking.jsp"});
    //     $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{mmtId:mmtId}});
    //     $.supper("showTtemWin",{ "url":att_url,"title":"新增领料"});
    // }
</script>
