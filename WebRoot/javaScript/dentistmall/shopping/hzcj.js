$(function(){
	var data = $("#searchForm").serialize();
	$.supper('initPagination',{id:"Pagination",service:"ShoppingService.getSupplierPager",data:data,limit:20,isAjax:"1","BackE":initList});
}); 
function initList(dataList){
	var str = "";
	if(dataList != null && dataList.length > 0){
		for(var i =0;i < dataList.length;i++){
			var data = dataList[i];
			str +="<div class=\"cooperation-item\"><div class=\"item-logo\">";
			str += "<div class=\"logo\"><a href=\"pcjxiangxi.htm?wzId="+data.wzId+"\" target=\"_blank\"><img src=\""+data.logoFilePath+"\"></a></div>";
			str += "<div class=\"cooperation-name trigger\"><a href=\"pcjxiangxi.htm?wzId="+data.wzId+"\" target=\"_blank\">"+data.applicantName+"</a></div><div class=\"cooperation-address\">地址："+data.corporateDomicile+"</div></div>";
			str += "<div class=\"item-images\">";
			if(data.matList != null && data.matList.length > 0){
				for(var j =0 ; j < data.matList.length;j++){
					var matInfo = data.matList[j];
					str += "<div class=\"image\"><a href=\"xiangxi.htm?wmsMiId="+matInfo.wmsMiId+"\"><div class=\"image-header\">";
					str += "<img src=\""+matInfo.lessenFilePath+"\"></div><div class=\"image-description trigger\">"+matInfo.matName+"</div></a>";
					str += "<div class=\"product-operation\">";
					str += "<a href=\"javascript:setFavorites('"+matInfo.wmsMiId+"','hzcj-product-operation-fav');\" title=\"加入收藏夹\" style=\"margin-left:5px;\" class=\"product-operation-favorite\"><span id=\"hzcj-product-operation-fav-"+matInfo.wmsMiId+"\" name=\""+matInfo.wmsMiId+"\" class=\"icon-favorite\"></span></a>";
					str += "<a href=\"javascript:setCar('"+matInfo.wmsMiId+"','hzcj-product-operation-cart');\" title=\"加入购物车\" style=\"margin-left:5px;\"><span id=\"hzcj-product-operation-cart-"+matInfo.wmsMiId+"\" name=\""+matInfo.wmsMiId+"\" class=\"icon-shopping-cart add-product-cart\"></span></a>";
					str +="</div></div>";
				}
			}
			str +="</div></div>";
		}
	}
	$("#contentList").html(str);
	_intCart();
	setTimeout(function () {
		$('span[id^="hzcj-product-operation-fav-"]').each(function (index, item) {
			var wmsMId = $(this).attr('name');
			checkViewFav(wmsMId, 'hzcj-product-operation-fav');
		});
		$('span[id^="hzcj-product-operation-cart-"]').each(function (index, item) {
			var wmsMId = $(this).attr('name');
			if(wmsMId == null || wmsMId == undefined || wmsMId == ''){
				return;
			}
			setCar(wmsMId, 'hzcj-product-operation-cart', true);
		})
	}, 200)

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