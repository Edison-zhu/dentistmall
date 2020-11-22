var _all_searchLBName = 'this is an empty search name'; //查询结果是否存在高亮显示
var needHighlight = false;
$(function(){
	$('.search-sort .icon-finance').click(function () {
      $(this).toggleClass("sort-up sort-down");
    });
	var data = $("#searchForm").serialize();
	var searchName = $('#searchLBName').val();
	if(searchName !== null && searchName !== undefined && searchName !== ''){
		needHighlight = true;
		_all_searchLBName = searchName;
	}
	$.supper('initPagination',{id:"Pagination",service:"ShoppingService.getMatInfoPager",data:data,limit:20, limits: [20,30,40],isAjax:"1","BackE":initList});
});

  function weightShowMore(){
  	$("#weightOpen").hide();
  	$("#weightClose").show();
  	$(".font-weight-trigger").each(function(){
        $(this).show();
    });
  }
  function weightCloseMore(){
  	$("#weightOpen").show();
  	$("#weightClose").hide();
  	var index=0;
  	$(".font-weight-trigger").each(function(){
  		index++;
  		if(index > 9)
        	$(this).hide();
    });
  }
  
  function categoryShowMore(){
	  	$("#categoryOpen").hide();
	  	$("#categoryClose").show();
	  	$(".category-trigger").each(function(){
	        $(this).show();
	    });
	  }
function categoryCloseMore(){
	$("#categoryOpen").show();
	$("#categoryClose").hide();
	var index=0;
	$(".category-trigger").each(function(){
		index++;
	  	if(index > 9)
	        $(this).hide();
	 });
}


function brandSearch(brand,bid){
	$(".font-weight-trigger").removeClass("active");
	$("#brand_"+bid).addClass("active");
	$("#brand").val(brand);
	var data = $("#searchForm").serialize();
	$.supper('initPagination',{id:"Pagination",service:"ShoppingService.getMatInfoPager",data:data,limit:20,isAjax:"1","BackE":initList});
}

function categorySearch(category,bid){
	$(".category-trigger").removeClass("active");
	$("#category_"+bid).addClass("active");
	$("#mmtId").val(category);
	var data = $("#searchForm").serialize();
	$.supper('initPagination',{id:"Pagination",service:"ShoppingService.getMatInfoPager",data:data,limit:20,isAjax:"1","BackE":initList});
}

function setDefault(){
	$(".search-sort label").removeClass("active");
    $("#default").addClass("active");
	$("#isDefault").val("1");
	$("#numOrder").val("0");
	$("#priceOrder").val("0");
	var data = $("#searchForm").serialize();
	$.supper('initPagination',{id:"Pagination",service:"ShoppingService.getMatInfoPager",data:data,limit:20,isAjax:"1","BackE":initList});
}
function setPrice(){
	var orType=$("#price").attr("class");
	$("#isDefault").val("0");
	$("#numOrder").val("0");
	if(orType.indexOf('sort-up')>-1){//由高到底
		$("#priceOrder").val("2");
	}else{//由低到高
		$("#priceOrder").val("1");
	}
	$(".search-sort label").removeClass("active");
    $("#price").addClass("active");
	var data = $("#searchForm").serialize();
	$.supper('initPagination',{id:"Pagination",service:"ShoppingService.getMatInfoPager",data:data,limit:20,isAjax:"1","BackE":initList});
}
function setNumber(){
	$(".search-sort label").removeClass("active");
    $("#number").addClass("active");
	$("#isDefault").val("0");
	$("#priceOrder").val("0");
	$("#numOrder").val("2");
	var data = $("#searchForm").serialize();
	$.supper('initPagination',{id:"Pagination",service:"ShoppingService.getMatInfoPager",data:data,limit:20,isAjax:"1","BackE":initList});
}
function initList(dataList){
	var str = "";
	var isLogin=$("#isLogin").val();

	if(dataList != null && dataList.length > 0){
		for(var i =0;i < dataList.length;i++){
			var data = dataList[i];
			if(i%5==0)
				str +="<li>";
			else
				str +="<li style=\"margin-left:10px\">";
			str +="<a href=\"xiangxi.htm?wmsMiId="+data.wmsMiId+"\" target=\"_blank\" >";
			str += "<div class=\"product-img-container\"><img src=\""+data.lessenFilePath+"\" alt=\"\"></div>";
			// str += "<p class=\"product-name ellipsis\"><span id='searchNameMatch" + data.matName + "'>"+data.matName+"</span></p></a>";
			str += "<p class=\"product-name newEllipsis\" id='searchNameMatch" + data.matName + "'>"+data.matName+"</p></a>";
			str +="<p class=\"product-desc m-top10 ellipsis\">供应商："
				+"<a style=\"color:#888\" href=\"pcjxiangxi.htm?wzId="+data.wzId+"\" target=\"_blank\"><span id='searchNameMatch" + data.applicantName + "'>"+data.applicantName+"</span></a> </p>";
			str += "<p class=\"product-desc ellipsis\"> 规格：<span>";
			if(data.norm!=null && data.norm.length >15) {
				str += "<span id='searchNameMatch" + data.norm + "'>" + data.norm.substring(0, 14) + "...</span>";
			} else if(data.norm!=null && data.norm.length <=15) {
				str += "<span id='searchNameMatch" + data.norm + "'>" + data.norm+ "</span>";
			} else
				str += "&nbsp;&nbsp;";
			str += "</span></p><p class=\"product-price-container flex m-top10\">";
			if(isLogin=='1')
				str += "<span class=\"red\">￥"+toDecimal2(data.money1)+"</span>";
			str +="<span class=\"product-sale-count\">销售量：<b>"+(data.number1!=null?data.number1:0)+"</b>"+data.basicUnit+"</span></p>";
			str += "<div class=\"product-operation\">";
			str += "<a href=\"javascript:setFavorites('"+data.wmsMiId+"','liebiao-product-operation-fav');\" title=\"加入收藏夹\" style=\"margin-left:5px;\" class=\"product-operation-favorite\"><span id=\"liebiao-product-operation-fav-"+data.wmsMiId+"\" name=\""+data.wmsMiId+"\" class=\"icon-favorite\"></span></a>";
			str += "<a href=\"javascript:setCar('"+data.wmsMiId+"','liebiao-product-operation-cart');\" title=\"加入购物车\" style=\"margin-left:5px;\"><span id=\"liebiao-product-operation-cart-"+data.wmsMiId+"\" name=\""+data.wmsMiId+"\" class=\"icon-shopping-cart add-product-cart\"></span></a>";
			str +"<a href=\"javascript:;\"><span class=\"icon-search\"></span></a>";
			str +"<a href=\"javascript:;\"><span class=\"icon-scanning\"></span></a>";
			str +="</div></li>";
		}
	}
	if(str === ''){
		if(_all_searchLBName != 'this is an empty search name') {
			str = '<div style="float: left;margin: 5% auto;font-size: 14px"><div>抱歉！没有找到与<b style="margin: 0 5px 0 5px; font-size: 15px">' + _all_searchLBName + '</b>相关的宝贝</div>';
			_all_searchLBName = _all_searchLBName.replace(/\s/g, "");
			if (_all_searchLBName.length >= 2) {
				var lbName = _all_searchLBName.split('');
				str += '<div>别担心，我们根据部分搜索词帮您找到了一些结果：';

				let firstT = '';
				lbName.forEach(function (value) {
					firstT += value + ' ';
				});
				if (firstT != '') {
					firstT = firstT.substring(0, firstT.length - 1);
					str += '<a class="search-button ahover" onClick="searchName2(\'' + firstT + '\')"><b>' + firstT + '</b></a>、';
				}
				lbName.forEach(function (value) {
					str += '<a class="search-button ahover" onClick="searchName2(\'' + value + '\')"><b>' + value + '</b></a>、';
				})

				// switch (_all_searchLBName.length) {
				//     case 2:
				//         lbName.forEach(function (value) {
				//             str += '<a class="search-button" onClick="searchName2(\'' + value + '\')"><b>' + value + '</b></a>、';
				//         })
				//         break;
				// 	default:
				// 		str += '<a class="search-button ahover" onClick="searchName2(\'' + lbName[0] + ' ' + lbName[1] + ' ' + lbName[2] + '\')"><b>' + lbName[0] + ' ' + lbName[1] + ' ' + lbName[2] + '</b></a>、';
				//         str += '<a class="search-button ahover" onClick="searchName2(\'' + lbName[0] + ' ' + lbName[1] + '\')"><b>' + lbName[0] + ' ' + lbName[1] + '</b></a>、';
				//         str += '<a class="search-button ahover" onClick="searchName2(\'' + lbName[1] + ' ' + lbName[2] + '\')"><b>' + lbName[1] + ' ' + lbName[2] + '</b></a>、';
				//         break;
				// default:
				//     str += '<a class="search-button" onClick="searchName2(\'' + lbName[0] + lbName[1] + '\')"><b>' + lbName[0] + lbName[1] + '</b></a>、';
				//     str += '<a class="search-button" onClick="searchName2(\'' + lbName[1] + lbName[2] + '\')"><b>' + lbName[1] + lbName[2] + '</b></a>、';
				//     break;
				// }
				str = str.substring(0, str.length - 1);
				str += '</div></div>';
			}
		} else {
			str = '<div style="float: left;margin: 5% auto;font-size: 14px"><div>抱歉！没有找到相关的宝贝</div></div>';
		}
	}
	$("#contentList").html(str);
	if(needHighlight) {
		highLightSpan();
	}
	_intCart();
	setTimeout(function () {
		$('span[id^="liebiao-product-operation-fav-"]').each(function (index, item) {
			var wmsMId = $(this).attr('name');
			checkViewFav(wmsMId, 'liebiao-product-operation-fav');
		});
		$('span[id^="liebiao-product-operation-cart-"]').each(function (index, item) {
			var wmsMId = $(this).attr('name');
			if(wmsMId == null || wmsMId == undefined || wmsMId == ''){
				return;
			}
			setCar(wmsMId, 'liebiao-product-operation-cart', true);
		});
	}, 200);

}

function highLightSpan() {
	var strArray1 = new Array();
	var strArray2 = new Array();
	strArray1 = _all_searchLBName.split(' ');
	strArray2 = _all_searchLBName.split('');
	strArray1.forEach(function (value) {
		strArray2.forEach(function (value1) {
			if (value1.trim() != value1.trim()) {
				strArray1.push(value1);
			}
		})
	})
	strArray1.reverse();
	$("#contentList").find('span[id^=searchNameMatch]').each(function () {
		var reStr = $(this).text();
		var that = this;
		strArray1.forEach(function (value) {
			if (value != '') {
				var reg = new RegExp(value, 'gi');
				reStr = reStr.replace(reg, '<span class="highLight">' + value + '</span>');
				$(that).html(reStr);
			}
		})
	});
}
function _intCart() {
	var tips;
	$(".product-operation a:nth-child(1)").bind("mouseover", function () {
		var message = '加入收藏夹';
		if ($(this).children('span').attr('class') != 'icon-favorite') {
			message = '移除收藏夹';
		}
		tips = layer.tips(message, this, {tipsMore: true});
	});
	$(".product-operation a:nth-child(2)").bind("mouseover", function () {
		tips = layer.tips('加入购物车', this);
	});
	$(".product-operation a").bind("mouseout", function () {
		layer.close(tips);
	});
};