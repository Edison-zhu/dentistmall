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
<style>
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
    .newtop {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border: 1px solid #EBEBEB;
        /*margin:4px 30px;*/
        padding: 10px 0 7px 0;
    }

    .newtop .form {

        display: flex;
        justify-content: flex-start;
        align-items: center;
    }

    .newtop .form .tit {
        font-size: 14px;
        color: #333333;
        margin-right: 10px;
    }

    .newtop .form .input {

    }

    .newtop .form .input input {
        width: 420px;
        height: 42px;
        outline: none;
        border: 1px solid #EBEBEB;
        font-size: 13px;
        color: #666;
        padding-left: 5px;
    }

    .newtop .form .btn {

    }

    .newtop .form .btn a {
        display: flex;
        justify-content: center;
        align-items: center;
        width: 100px;
        height: 30px;
        border: 1px solid #1ab394;
        font-size: 14px;
        border-radius: 15px;
    }

    .itemssbox {
        float: right;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .itemss {
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

    .itemsss {
        cursor: pointer;
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

    .itemss:hover {
        cursor: pointer;
        color: #ffffff;
    }

    .itemss a {
        color: #fff;
    }

    .itemss span {
        width: 18px;
        height: 18px;
        text-align: center;
        line-height: 18px;
        position: absolute;
        top: -5px;
        right: -5px;
        display: block;
        border: 1px solid #ed5565;
        border-radius: 50%;
        color: #ed5565;
        font-size: 12px;
        font-weight: bold;
    }

    .countCollects {
        width: 18px;
        height: 18px;
        text-align: center;
        line-height: 18px;
        position: absolute;
        top: -5px;
        right: -5px;
        display: block;
        border: 1px solid #ed5565;
        border-radius: 50%;
        color: #ed5565;
        font-size: 12px;
        font-weight: bold;
    }

    .posre {
        position: relative;
    }
    .newmlf{
        margin-left: -932px
    }
    .newmlf2{
        margin-left: -503px
    }
    @media (max-width: 1366px) {
        .newmlf{
            margin-left: -935px
        }
        .newmlf2{
            margin-left: -522px
        }
    }
</style>

<div class="newtop" style="background-color: white">
    <div class="form">
        <div>
        <div class="tit">
            <%@ include file="/jsp/dentistmall/shopping/navigation1.jsp" %>
            <link rel="stylesheet" href="<c:url value='/css/shopping/css/productList.css?06' />">
            <script type="text/javascript"
                    src="<c:url value='/javaScript/dentistmall/mdMaterielType.js?v=03'/>"></script>
        </div>

            <div class="input" id="input-container"class="input-container">
                <input type="text" class="newmlf" id="searchName" placeholder="商品物料名称、型号、别名、物料编码、物料名称的等首字母关键字"/>
            </div>
            <div class="btn">
                <a class="search-button newmlf2" onclick="searchName2()"id="search_button">搜索</a>
            </div>

        <div class="itemssbox" style="margin-left: -415px">
            <a onclick="shoucangs()" class="itemss">我的收藏</a>
            <a onclick="shenqings()" id="itemss" class="itemss">我的申请单</a>
            <a onclick="wuliaos()" class="itemss posre">购物车<span id="countCollects" class="countCollects"></span></a>
        </div>

    </div>

</div>
<script type="text/javascript" src="<c:url value='/javaScript/dentistmall/shopping/shophead1.js?v=42'/>"></script>
