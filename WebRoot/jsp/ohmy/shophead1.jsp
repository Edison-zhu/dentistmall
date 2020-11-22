<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<link href="<c:url value='/css/font-awesome.min.css?v=4.3.0' />" rel="stylesheet">
<link href="<c:url value='/css/animate.css' />" rel="stylesheet">
<script src="<c:url value='/js/jquery-2.1.1.min.js' />"></script>
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
</style>
<div class="header-middle-container w1080">
    <div class="header-middle-container-content">
        <div class="logo">
            <a href="index.htm">
<%--                <img src="<c:url value='/css/shopping/images/logo.png' />">--%>
            </a>
        </div>
        <div class="search">
            <form>
                <div class="search-container flex">
                    <div class="flex search-container-info">
                        <div class="select-box select-box-div">
                            <input class="select-box-input" style="outline: none;" type="text" value="商品"
                                   readonly="readonly"/>
                            <ul class="select-ul" style="display: none;">
                                <li class="" onClick="checkSearch('mat')">商品</li>
                                <li class="" onClick="checkSearch('supplier')">商家</li>
                            </ul>
                        </div>
                        <div id="input-container" class="input-container">
                            <input type="text" value='${searchName}' id="searchName" autocomplete="off"/>
                            <div id="hidden-search-div" tabindex="-1" class="hidden-search">
                                <ul id="hidden-search"></ul>
                            </div>
                        </div>
<%--                        <div class="hot-search hot-search-title" style="line-height:20px">热门搜索：--%>
<%--                            <c:forEach var="comm" items="${rmssList}">--%>
<%--                                <a href="#" onclick="searchName2('${comm.hotTitle}')">${comm.hotTitle}</a><span--%>
<%--                                    class="hot-search-split">/</span>--%>
<%--                            </c:forEach>--%>
<%--                        </div>--%>
                    </div>
                    <a class="search-button">搜索</a> <a href="#" id="m-login" style="margin-left: 10px" class="search-button">登录</a>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- 登陆弹窗相关 -->
<div class="u-mask" id="g-loginId"></div>
<div class="g-login" id="g-login">
    <h2 class="addh2">登录</h2>
    <a class="open-close" id="loginClose">关闭</a>
    <div class="wrap">
        <form id="loginForm" method="GET">
            <dl class="nativedlt">
                <input class="ipt" id="userName" name="userName"
                       type="text" aria-required="true" placeholder="用户名"
                       onkeydown="if(event.keyCode==13){ReceptionLogin();}">
            </dl>
            <dl>
                <input class="ipt" id="password" name="password"
                       type="passWord" aria-required="true" placeholder="输入密码"
                       onkeydown="if(event.keyCode==13){ReceptionLogin();}">
            </dl>
            <div style="color:red;margin-top:10px" id="errorId">
            </div>
            <div class="button-wrap" onclick="ReceptionLogin()">
                <a id="btn-ok" class="btn-ok" hidefocus="">确定</a>

            </div>
            </a>
        </form>
    </div>
</div>
<script type="text/javascript" src="<c:url value='/jsp/ohmy/shophead1.js?v=2'/>"></script>