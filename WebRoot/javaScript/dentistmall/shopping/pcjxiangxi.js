$(function(){
	initMdType();
	var data = $("#searchForm").serialize();
	$.supper('initPagination',{id:"Pagination",service:"ShoppingService.getMatInfoPager",data:data,limit:8,isAjax:"1","BackE":initList});
	$("#floatShow").click(function() {
		$("#onlineService").toggle();
		$("#floatShow").hide();
		$("#floatHide").show();

	});

	$("#floatHide").click(function() {
		$("#onlineService").toggle();
		$("#floatHide").hide();
		$("#floatShow").show();
	});
}); 
function initList(dataList){
	var str = "";
	var isLogin=$("#isLogin").val();
	if(dataList != null && dataList.length > 0){
		for(var i =0;i < dataList.length;i++){
			var data = dataList[i];
			if(i%4==0)
				str +="<li>";
			else
				str +="<li style=\"margin-left:10px\">";
			str +="<a href=\"xiangxi.htm?wmsMiId="+data.wmsMiId+"\" target=\"_blank\" >";
			str += "<div class=\"product-img-container\"><img src=\""+data.lessenFilePath+"\" alt=\"\"></div>";
			str += "<p class=\"product-name ellipsis\">"+data.matName+"</p></a>";
			str +="<p class=\"product-desc m-top10 ellipsis\">供应商："
				+"<a style=\"color:#888\" href=\"pcjxiangxi.htm?wzId="+data.wzId+"\" target=\"_blank\">"+data.applicantName+"</a> </p>";
			str += "<p class=\"product-desc ellipsis\"> 规格：<span>";
			if(data.norm!=null && data.norm.length >15)
				str += data.norm.substring(0,14)+"...";
			else if(data.norm!=null && data.norm.length <=15)
				str += data.norm;
			else
				str += "&nbsp;&nbsp;";
			str += "</span></p><p class=\"product-price-container flex m-top10\">";
			if(isLogin=='1')
				str += "<span class=\"red\">￥"+toDecimal2(data.money1)+"</span>";
			str +="<span class=\"product-sale-count\">销售量：<b>"+(data.number1!=null?data.number1:0)+"</b>"+data.basicUnit+"</span></p>";
			str += "<div class=\"product-operation\">";
			str += "<a href=\"javascript:setFavorites('"+data.wmsMiId+"','pcj-product-operation-fav');\" title=\"加入收藏夹\" style=\"margin-left:5px;\"  class=\"product-operation-favorite\"><span id=\"pcj-product-operation-fav-"+data.wmsMiId+"\" name=\""+data.wmsMiId+"\" class=\"icon-favorite\"></span></a>";
			str += "<a href=\"javascript:setCar('"+data.wmsMiId+"','pcj-product-operation-cart');\" title=\"加入购物车\" style=\"margin-left:5px;\" ><span id=\"pcj-product-operation-cart-"+data.wmsMiId+"\" name=\""+data.wmsMiId+"\" class=\"icon-shopping-cart add-product-cart\"></span></a>";
			str +="</div></li>";
		}
	}
	$("#contentList").html(str);
	_intCart();
	setTimeout(function () {
		$('span[id^="pcj-product-operation-fav-"]').each(function (index, item) {
			var wmsMId = $(this).attr('name');
			checkViewFav(wmsMId, 'pcj-product-operation-fav');
		});
		$('span[id^="pcj-product-operation-cart-"]').each(function (index, item) {
			var wmsMId = $(this).attr('name');
			if(wmsMId == null || wmsMId == undefined || wmsMId == ''){
				return;
			}
			setCar(wmsMId, 'pcj-product-operation-cart', true);
		})
	}, 200);

}
function initMdType(){
	var vdata = "wzId="+$("#wzId").val();
	$.supper("doservice", {"service":"ShoppingService.getMdMaterielTypeByWzId","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			var dataList = jsondata.obj;
			var str ="";
			if(dataList != null && dataList.length > 0){
				for(var  x=0;x < dataList.length;x++){
					var one = dataList[x];
					str+="<li class=\"li-level-1\"><p class=\"p-level-1 icon-square\" onclick=\"javascript:searchPro('"+one.id+"')\">"+one.name+"</p><ul class=\"ul-level-2\">";
					var twoList = one.childList;
					if(twoList != null && twoList.length > 0){
						for(var i =0,j=1;i < twoList.length ;i ++,j++){
							var two = twoList[i];
							str += "<li class=\"li-level-2\"><p class=\"p-level-2\" onclick=\"javascript:searchPro('"+two.id+"')\">"+two.name+"</p><ul class=\"ul-level-3\"><li class=\"li-level-3\">";
							var treeList = two.childList;
			        		for(var k =0;k < treeList.length ;k ++){
			        			var three = treeList[k];
			        			str += "<p class=\"p-level-3 icon-list-item\" onclick=\"javascript:searchPro('"+three.id+"')\">"+three.name+"</p>";
			        		}
							str += "</li></ul></li>";
						}
					}
					str += "</ul></li>";
				}
			}
			$("#proType").html(str);
			$('.product-category p').click(function () {
		      $(this).next("ul").toggle();
		      $(this).toggleClass("active");
		      $(this).siblings().removeClass("active");
		      $(getObjByClassName(this, "li-level-2")).siblings().find("p").removeClass("active");
		      $(getObjByClassName(this, "li-level-1")).siblings().find("p").removeClass("active");
		      $(getObjByClassName(this, "li-level-2")).siblings().find("p:first").removeClass("active");
		      $(getObjByClassName(this, "li-level-1")).siblings().find("p:first").removeClass("active");
		    });
		    initCategoryTree();
		}
 	}});
}

function getObjByClassName(obj, _className) {
      if (!obj) return '';
      if (!$(obj).get(0)) return '';
      if ($(obj).hasClass(_className) )
        return obj;
      obj = $(obj).parent();
      return getObjByClassName(obj,_className);
    };
function initCategoryTree(){
      var _ul_1 = $('.product-category ul:first');
      var _ul_2 = $(_ul_1).find('ul:first');
      var _ul_3 = $(_ul_2).find('ul:first');
      $(_ul_2).show();
      $(_ul_3).show();
      var _p_1 = $(_ul_1).find('p:first');
      var _p_2 = $(_ul_2).find('p:first');
      var _p_3 = $(_ul_3).find('p:nth-child(4)');
      $(_p_1).addClass("active");
      $(_p_2).addClass("active");
      $(_p_3).addClass("active");
}

function searchPro(mmtId){
	$("#mmtId").val(mmtId);
	var data = $("#searchForm").serialize();
	$.supper('initPagination',{id:"Pagination",service:"ShoppingService.getMatInfoPager",data:data,limit:9,isAjax:"1","BackE":initList});
}

function _intCart() {
	var tips;
	$(".product-operation a:nth-child(1)").bind("mouseover", function () {
		var message = '加入收藏夹';
		if ($(this).children('span').attr('class') != 'icon-favorite') {
			message = '移除收藏夹';
		}
		tips = layer.tips(message, this);
	});
	$(".product-operation a:nth-child(2)").bind("mouseover", function () {
		tips = layer.tips('加入购物车', this);
	});
	$(".product-operation a").bind("mouseout", function () {
		layer.close(tips);
	});
};