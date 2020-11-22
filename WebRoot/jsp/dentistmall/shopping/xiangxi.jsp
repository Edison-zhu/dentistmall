<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>优牙库</title>
    <%@ include file="/jsp/lib.jsp" %>
</head>
<script>
    var _isFix = 1;
    var _isCar = 1;
</script>
</head>
<style>
    .nameButton {
        background-color: #f2f2f2;
        /*height: 30px;*/
        width: auto;
        max-width: 220px;
        outline: lightgray solid 1px;
        padding: 8px 10px;
        display: inline-block;
        /*overflow: hidden;*/
        /*text-overflow: ellipsis;*/
        /*white-space: nowrap;*/
        word-break: break-all;
        word-wrap: break-word;
    }

    .nameButton:hover {
        cursor: default;
        outline: lightgray solid 1px;
    }

    .description-left .cooperation-info p {
        height: 30px;
        line-height: 35px;

        /*padding-left: 10px;*/
        font-size: 14px;
    }

    .description-right {
        padding: 10px;
        max-height: 260px;
    }

    .cooperation-description {
        margin-bottom: 50px;
    }

    .description-left {
        font-size: 24px;
        /*padding-left: 10px;*/
        /*width: 208px;*/
    }

    description-right {
        font-size: 24px;
        padding: 10px;
        max-height: 260px;
    }

    .description-right .description-content {
        line-height: 20px;
        max-height: 230px;
        text-align: justify;
        color: #8e8e8e;
        overflow: hidden;
        text-overflow: ellipsis;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 11;
    }
    .product-info-tab-bd li img{
        width: 100%;
        /*height: 100%;*/
    }
</style>
<body>
<header class="header">
        <%@ include file="/jsp/dentistmall/shopping/shophead.jsp" %>
</header>
<link rel="stylesheet" href="<c:url value='/css/shopping/css/productInfo.css?16' />">
<link rel="stylesheet" href="<c:url value='/fonts/icon/favorite/iconfont.css?16' />">
<script src="<c:url value='/js/plugins/highcharts/highstock.js' />"></script>
<c:if test="${ohmy != true}">
<%@ include file="/jsp/dentistmall/shopping/navigation.jsp" %>
</c:if>
<p class="bread-crumb w1080">
    <i class="icon-homepage"></i>
    <label>
        <a href="index.htm">
            首页
        </a>
    </label>
    <label>
        <a href="pcjxiangxi.htm?wzId=${mdSupplier.wzId}">
            ${mdSupplier.applicantName}
        </a>
    </label>
    <label>${matInfo.matName}</label>
</p>
<div class="product-info flex w1080">
    <div class="product-info-preview">
        <div id="preview" class="jqzoom spec-preview flex">
            <IMG id="previewImg" height=280 src="${firstFile.rootPath}" jqimg="${firstFile.rootPath}" width=430>
        </div>
        <div class="spec-scroll"><a class="prev">&lt;</a> <a class="next">&gt;</a>
            <div class="items">
                <ul>
                    <c:forEach var="file" items="${fileList}">
                        <li><img src="${file.rootPath}" onmousemove="preview(this);"></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div style="float: right; position: relative; right: 0px;margin-top: 10px;">
            <button class="product-info-addFavorite font-weight-trigger" style="border: 0px;"
                    onclick="setXiangxiFavorites('${matInfo.wmsMiId}')">
                <span id="fav" class="iconfont icon-nofav"></span><font id="favtext">加入收藏夹</font>
            </button>
        </div>
    </div>
    <div class="product-sale-info">
        <p class="product-info-title blue">${matInfo.matName}</p>
        <ul class="product-info-props">
<%--            <c:if test="${matInfo.matCode!=null && matInfo.matCode!='' }">--%>
<%--                <li>商品编号：${matInfo.matCode}</li>--%>
<%--            </c:if>--%>
<%--            <li>供应商名称：<a class="blue" href="pcjxiangxi.htm?wzId=${mdSupplier.wzId}"--%>
<%--                         target="_blank">${mdSupplier.applicantName}</a></li>--%>
            <c:if test="${matInfo.brand!=null && matInfo.brand!='' }">
                <li>品牌名称：${matInfo.brand}</li>
            </c:if>
            <c:if test="${matInfo.basicUnit!=null && matInfo.basicUnit!='' }">
                <li>销售单位：${matInfo.basicUnit}</li>
            </c:if>
            <c:if test="${matInfo.productName!=null && matInfo.productName!='' }">
                <li>包装方式：${matInfo.productName}</li>
            </c:if>
<%--            <c:if test="${matInfo.backPrinting!=null && matInfo.backPrinting!='' }">--%>
<%--                <li>注册证号：${matInfo.backPrinting}</li>--%>
<%--            </c:if>--%>
<%--            <c:if test="${matInfo.basicUnitAccuracy!=null && matInfo.basicUnitAccuracy!='' }">--%>
<%--                <li>注册证有效期：${matInfo.basicUnitAccuracy}</li>--%>
<%--            </c:if>--%>
        </ul>
        <c:if test="${sessionScope.shoppingAccount != null &&  sessionScope.shoppingAccount.suiId !=null}">
            <p class="product-info-price">
                销售价：<b id="matPrice">￥<fmt:formatNumber type="number" value="${matInfo.money1}" pattern="0.00"
                                                        maxFractionDigits="2"/></b>
            </p>
        </c:if>
        <div class="product-info-format m-top10">
            <span>型号：</span>
            <div class="product-info-format-lists">
                <c:forEach var="format" items="${formatList}" varStatus="s">
                    <c:if test="${s.first}">
                        <a id="li_${format.mmfId}"
                           href="javascript:selFormate('${format.mmfCode}','${format.mmfName}','${format.price}','${format.mmfId}');"
                           class="selected"><em>${format.mmfName}</em></a>
                    </c:if>
                    <c:if test="${!s.first}">
                        <a id="li_${format.mmfId}"
                           href="javascript:selFormate('${format.mmfCode}','${format.mmfName}','${format.price}','${format.mmfId}');"><em>${format.mmfName}</em></a>
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <div id="gwcList">
            <c:forEach var="format" items="${formatList}" varStatus="s">
                <c:if test="${s.first}">
                    <table id="gwcListTable">
                        <tr id="tr_${format.mmfId}">
                            <input type="hidden" id="mmfCodes" value="${format.mmfId},">

                                <%--                        <span>购买数量：</span>--%>
                            <td>
                                <div class="product-info-choose-count m-top20">
                                    <a class="nameButton" disabled><span
                                            class="product-info-inventory ">${format.mmfName}</span></a>
                                </div>
                            </td>
                            <td style="width: 100px;text-align: left">
                                <div class="m-top20">
                                <span style="margin-left: 6px"
                                      class="product-info-inventory ">No.${format.mmfCode}</span>
                                </div>
                            </td>

                            <c:if test="${sessionScope.shoppingAccount != null &&  sessionScope.shoppingAccount.suiId !=null}">
                                <td style="text-align: center">
                                    <div class="product-info-choose-count m-top20">

										<span class="product-info-inventory ">
										<font style="color:#e64419">￥<fmt:formatNumber type="number"
                                                                                       value="${format.price}"
                                                                                       pattern="0.00"
                                                                                       maxFractionDigits="2"/></font></span>
                                            <%--                            <span class="product-info-inventory ">总价:<font style="color:#e64419"--%>
                                            <%--                                                                           id="count_${format.mmfId}">￥<fmt:formatNumber--%>
                                            <%--                                    type="number" value="${format.price}" pattern="0.00" maxFractionDigits="2"/></font></span>--%>
                                    </div>
                                </td>
                            </c:if>
                            <td style="width: 140px;">
                                <div class="product-info-choose-count m-top20" style="padding-left: 20px">
                                    <input id="min" name="" type="button" value="-"
                                           onclick="minusShu('${format.mmfId}')">
                                    <input type="hidden" id="id_${format.mmfId}" value="${format.mmfId}">
                                    <input type="hidden" id="price_${format.mmfId}" value="${format.price}">
                                    <input id="inp_${format.mmfId}" name="" style="padding: 4px 8px;" type="text"
                                           value="1"
                                           class="product-info-count"
                                           onkeyup="this.value=this.value.replace(/[^0-9]/g,'');countMoney('${format.mmfCode}')">
                                    <input id="add" name="" type="button" value="+"
                                           onclick="addShu('${format.mmfId}')">
                                </div>
                            </td>
                            <td>
                                <div class="product-info-choose-count m-top20">
                                    <input id="remove_${format.mmfId}" type="button" value="移除"
                                           onclick="removeTr('${format.mmfId}')" style="display: none">
                                </div>
                            </td>
                        </tr>
                    </table>
                </c:if>
            </c:forEach>
        </div>
        <p class="product-info-button " style="margin-top:10px">
            <c:if test="${matInfo.state == 1 }">
            <button class="product-info-addCart font-weight-trigger" onclick="buyNow()">
                <span class="icon-finance"></span>立即购买
            </button>
            </c:if>
            <c:if test="${matInfo.state == 2 }">
                <button class="product-info-addCart font-weight-trigger">
                    <span class="icon-finance"></span>已下架
                </button>
            </c:if>
            <c:if test="${matInfo.state == 3 }">
                <button class="product-info-addCart font-weight-trigger">
                    <span class="icon-finance"></span>已下架
                </button>
            </c:if>
            <button class="product-info-addCart font-weight-trigger" onclick="addOrder()">
                <span class="icon-shopping-cart"></span>加入购物车
            </button>
            <%--            <button class="product-info-addFavorite font-weight-trigger" onclick="addEvaluate('${matInfo.wmsMiId}')">--%>
            <%--                <span class="icon-list"></span>价格点评--%>
            <%--            </button>--%>
        </p>

    </div>
</div>
<div class="flex w1080 m-top20">
    <div class="cooperation-description"
         style="width: 246px; float: left; height: 370px; border: lightgrey 1px solid">
        <div class="description-left">
            <div style="border-bottom: lightgrey 1px solid">
                <div class="cooperation-logo" style="display: inline;padding-left: 10px;">
                    <img src="${mdSupplier.logoFilePath}" style="display: inline;height: 34px;width: 34px;">
                </div>
                <div style="padding-left: 10px;margin: 10px 0;display: inline;font-size: 24px;position: relative;top: 5px;">
                    <a style="color:#888"
                       href="pcjxiangxi.htm?wzId=${mdSupplier.wzId}"
                       target="_blank">
                        <b class="description-title">
                            ${mdSupplier.applicantName}
                        </b>
                    </a>
                </div>
            </div>
            <div class="cooperation-info" style="padding-left: 10px;">
                <p class="icon-people">联系人：${mdSupplier.legalPerson}</p>
                <p class="icon-phone">联系电话：${mdSupplier.phoneNumber}</p>
                <p class="icon-address">${mdSupplier.corporateDomicile}</p>
            </div>
        </div>
        <div class="description-right">
            <div class="description-content">
                ${mdSupplier.scopeBusiness}
            </div>
        </div>
    </div>
    <div class="product-info-tabs" style="width: 880px;padding-left: 10px;">
        <div class="product-info-tab">
            <ul class="product-info-tab-hd">
                <li class="active">产品详情</li>
                <%--                <li>商家信息</li>--%>
                <div style="display: inline">
                    <li>成交量(
                        <c:if test="${empty matInfo.number1}">0</c:if>
                        <c:if test="${!empty matInfo.number1}"><fmt:formatNumber value="${matInfo.number1}" pattern="#0"
                                                                                 maxFractionDigits="0"/>
                        </c:if>
                        )
                    </li>
                </div>
                <%--		<li>联系方式</li>--%>
                <%--		<c:if test="${sessionScope.shoppingAccount != null &&  sessionScope.shoppingAccount.suiId !=null}">--%>
                <%--			<li >历史价格</li>--%>
                <%--		</c:if>--%>
                <%--		<li>价格点评<span id="evaluateTitle" style="color:#ff6f3f;"></span></li>--%>
            </ul>
            <ul class="product-info-tab-bd">
                <li class="active">${matInfo.describe1}</li>
                <%--                <li style="font-size:16px">--%>
                <%--                    <div class="cooperation-description">--%>
                <%--                        <div class="description-left">--%>
                <%--                            <div class="cooperation-logo" style="display: inline">--%>
                <%--                                <img src="${mdSupplier.logoFilePath}" style="display: inline;height: 34px;width: 34px;">--%>
                <%--                            </div>--%>
                <%--                            <div style="margin: 10px 0;display: inline;font-size: 24px;position: relative;top: 5px;">--%>
                <%--                                <a style="color:#888"--%>
                <%--                                   href="pcjxiangxi.htm?wzId=${mdSupplier.wzId}"--%>
                <%--                                   target="_blank">--%>
                <%--                                    <b class="description-title">--%>
                <%--                                        ${mdSupplier.applicantName}--%>
                <%--                                    </b>--%>
                <%--                                </a>--%>
                <%--                            </div>--%>
                <%--                            <div class="cooperation-info">--%>
                <%--                                <p class="icon-people">联系人：${mdSupplier.legalPerson}</p>--%>
                <%--                                <p class="icon-phone">联系电话：<b style="color: black">${mdSupplier.phoneNumber}</b></p>--%>
                <%--                                <p class="icon-address">${mdSupplier.corporateDomicile}</p>--%>
                <%--                            </div>--%>
                <%--                        </div>--%>
                <%--                        <div class="description-right">--%>
                <%--                            <div class="description-content">--%>
                <%--                                ${mdSupplier.scopeBusiness}--%>
                <%--                            </div>--%>
                <%--                        </div>--%>
                <%--                    </div>--%>
                <%--                    &lt;%&ndash;                ${mdSupplier.scopeBusiness}&ndash;%&gt;--%>
                <%--                </li>--%>

                <%--			<li style="font-size:16px">--%>
                <%--				☆联系人：${mdSupplier.legalPerson}<br/>--%>
                <%--	   			☆联系地址：${mdSupplier.corporateDomicile}<br/>--%>
                <%--				☆联系电话：${mdSupplier.phoneNumber}--%>
                <%--			</li>--%>
                <%--			<c:if test="${sessionScope.shoppingAccount != null &&  sessionScope.shoppingAccount.suiId !=null}">--%>
                <%--			<li>--%>
                <%--				<div style="width:1020px" id="chars">--%>
                <%--				</div>--%>
                <%--			</li>--%>
                <%--			</c:if>--%>
                <%--			<li>--%>
                <%--				<div style="width:1020px;display:none;" id="evaluateList">--%>
                <%--					<div class="content w1080" id="contentList"></div>--%>
                <%--					<div class="pagination w1080 m-pagenum pagination" id="Pagination_evt"> </div> --%>
                <%--				</div>--%>
                <%--				<div style="width:1020px;display:block;" id="evaluateNullList" >--%>
                <%--				<img alt="" src="<c:url value='/img/pt1.png'/>" /> --%>
                <%--						 --%>
                <%--				</div>--%>
                <%--			</li>--%>
            </ul>
        </div>
    </div>
</div>
<section class="new-product w1080  m-top20">
    <div class="product-title"><p class="product-title-info"><span>同类热门</span></p></div>
    <ul class="flex w1080 product-lists m-top10 ">
        <c:forEach var="child" items="${hotList}" varStatus="cIndex">
            <li <c:if test="${!cIndex.first}"> style="margin-left:10px"</c:if> >
                <a href="xiangxi.htm?wmsMiId=${child.wmsMiId}" target="_blank">
                    <div class="product-img-container">
                        <img src="${child.lessenFilePath}" alt="">
                    </div>
                    <p class="product-name ellipsis">
                            ${child.matName}
                    </p>
                </a>
                <p class="product-desc m-top10 ellipsis">供应商：<a style="color:#888"
                                                                href="pcjxiangxi.htm?wzId=${child.wzId}"
                                                                target="_blank">${child.applicantName}</a></p>
                <p class="product-desc ellipsis"> 规格：<span>
	            <c:if test="${fn:length(child.norm) > 14}">
                    ${fn:substring(child.norm, 0,14)}...
                </c:if>
				<c:if test="${fn:length(child.norm) <= 14}">
                    ${child.norm}
                </c:if>
            </span></p>
                <p class="product-price-container flex m-top10">
                    <c:if test="${sessionScope.shoppingAccount != null &&  sessionScope.shoppingAccount.suiId !=null}">
                        <span class="red">￥<fmt:formatNumber type="number" value="${child.money1}" pattern="0.00"
                                                             maxFractionDigits="2"/></span>
                    </c:if>
                    <span class="product-sale-count">销售量：<b>
              	<c:if test="${empty child.number1}">0</c:if>
				<c:if test="${!empty child.number1}"><fmt:formatNumber value="${child.number1}" pattern="#0"
                                                                       maxFractionDigits="0"/></c:if></b>${child.basicUnit}</span>
                </p>
                <img class="i-new-product" src="<c:url value='/css/shopping/images/new.png' />" alt="">
                <div class="product-operation">
                    <a href="javascript:setFavorites('${child.wmsMiId}', 'xiangxi-product-operation-fav');" title="加入收藏夹"
                       class="product-operation-favorite"><span id="xiangxi-product-operation-fav-${child.wmsMiId}"
                                                                name="${child.wmsMiId}"
                                                                class="icon-favorite"></span></a>
                    <a href="javascript:setCar('${child.wmsMiId}','xiangxi-product-operation-cart');"
                       title="加入购物车"><span id="xiangxi-product-operation-cart-${child.wmsMiId}" name="${child.wmsMiId}"
                                           class="icon-shopping-cart add-product-cart"></span></a>
                    <a href="javascript:;"><span class="icon-search"></span></a>
                    <a href="javascript:;"><span class="icon-scanning"></span></a>
                </div>
            </li>
        </c:forEach>
    </ul>
</section>
<%@ include file="/jsp/dentistmall/shopping/shopfoot.jsp" %>
<!--客服-->
<c:if test="${cusList != null}">
    <div id="online_qq_layer">
        <div id="online_qq_tab" style="margin-top:0px">
            <a id="floatShow" style="display:block;" href="javascript:void(0);">收缩</a>
            <a id="floatHide" style="display:none;" href="javascript:void(0);">展开</a>
        </div>
        <div id="onlineService">
            <div class="onlineMenu">
                <h3 class="tQQ">商家QQ客服</h3>
                <ul style="margin-bottom:0px">
                    <li class="tli zixun">商家咨询</li>
                    <c:forEach var="cus" items="${cusList}" varStatus="s">
                        <li>
                            <a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${cus}&site=qq&menu=yes">
                                <img border="0" src="http://wpa.qq.com/pa?p=2:1464743635:51" alt="点这里给我发消息"/>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="btmbg"></div>
        </div>
    </div>
</c:if>
</body>
</html>
<script type="text/javascript" src="<c:url value='/js/shopping/jquery.slider-min.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/shopping/163css.js?15' />"></script>
<script type="text/javascript" src="<c:url value='/js/shopping/base.js' />"></script>
<script src="<c:url value='/js/shopping/layer/layer.js' />"></script>
<script type="text/javascript" src="<c:url value='/js/shopping/ui.js?10' />"></script>
<script type="text/javascript" src="<c:url value='/javaScript/dentistmall/shopping/xiangxi.js?v=46' />"></script>
