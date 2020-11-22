var _dd_search_action = 'ShoppingService.getOrderMxName';
var _all_limit_number = 5;
$(function(){
	initMatList();
}); 

function initMatList(){
	var str = "";
	var vdata="moiId="+$("#moiId").val();
	$.supper("initPagination", {id:"Pagination", service:"ShoppingService.getOrderMxModelByMoiId",data:vdata,limit:_all_limit_number, isAjax:"1", "BackE": initShoppingXQList});
}

function initShoppingXQList(dataList) {
	initXQList('matList', dataList, true);
	// var str ='';
	// if (dataList != null && dataList.length > 0) {
	// 	if (dataList != null && dataList.length > 1) {
	// 		// var totalMoney = 0;
	// 		for (var i = 0; i < dataList.length - 1; i++) {
	// 			var mat = dataList[i];
	// 			str += "<div class=\"order-info\"><div class=\"left\" style=\"margin-left:10px\"><img src=\"" + mat.less_file_path + "\" width=\"68\" height=\"68\"><ul>";
	// 			str += "<li class=\"name\"><a href=\"xiangxi.htm?wmsMiId=" + mat.wms_mi_id + "\" target=\"_blank\">" + mat.mat_name + "</a></li>";
	// 			str += "<li class=\"type\">型号：<strong>" + mat.NORM + "</strong></li>";
	// 			str += "<li class=\"type\">编号：<strong>" + mat.mmf_code + "</strong></li>";
	// 			str += "</ul></div>";
	// 			str += "<div class=\"right\"><div class=\"money\">￥" + toDecimal2(mat.Unit_money) + "</div>"
	// 				+ "<div class=\"number\">" + mat.mat_number + "</div>"
	// 				+ "<div class=\"number\">" + (mat.number2 != null ? mat.number2 : "--") + "</div>"
	// 				+ "<div class=\"number\">" + (mat.shure_number != null ? mat.shure_number : "0") + "</div>"
	// 				+ "<div class=\"money red\">￥" + toDecimal2(mat.Total_money) + "</div>"
	// 				+ "<div class=\"money red\">￥" + toDecimal2((mat.money1 != null ? mat.money1 : "0")) + "</div>";
	// 			str += "</div></div>";
	// 			// totalMoney += Number(mat.Total_money);
	// 		}
	// 		$('#totalmoney').text(toDecimal2(dataList[dataList.length - 1].mx_total_money));
	// 	}
	// 	$("#matList").html(str);
	// }
	// if (str == '') {
	// 	str = '<div style="float: left;margin: 1px 44%;font-size: 15px;width: 500px;"><b>抱歉，搜索不到相关商品</b></div>';
	// 	$("#matList").html(str);
	// }
}

function main_exportC(){
	var vdata="moiId="+$("#moiId").val();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

function cancelMat(moiId) {
	var vdata = "moiId=" + moiId;
	$.supper("confirm", {title: "取消操作", msg: "确认取消操作？", yesE: function () {
			$.supper("doservice", {"service": "MdOrderInfoService.saveCancelBackInfo", "data": vdata, "BackE": function (jsondata) {
					if(jsondata.code == "1"){
						$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:reSearch});
					} else {
						$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
					}
				}})
		}});
}

function buyAgain(moiId) {
	var data = 'moiId=' + moiId;
	$.supper("doservice", {
		"service": "ShoppingService.buyAgain", "data": data, "BackE": function (jsondata) {
			var items = jsondata.items;
			if (items.length <= 0) {
				$.supper("alert", {title: "操作提示", msg: "操作失败！"});
				return false;
			}
			var mmfIds = '';
			var shus = '';
			$.each(items, function (key, item) {
				mmfIds += item.mmf_id + ',';
				shus += item.mat_number + ',';
			})
			mmfIds = mmfIds.substring(0, mmfIds.length - 1);
			shus = shus.substring(0, shus.length - 1);
			window.location.href = "ddqr.htm?mmfIds=" + mmfIds + "&shus=" + shus;
		}
	});
}

//订单列表查询
function searchDd() {
	var matName = $('#searchDdName').val();
	var moiId= $("#moiId").val();
	var data = 'matName=' + matName + '&moiId=' + moiId;
	$.supper("initPagination", {id:"Pagination", service: _dd_search_action , data:data, limit: _all_limit_number, isAjax:"1", "BackE": initShoppingXQList});
}
function resetSearch() {
	$('#searchDdName').val('');
	searchDd();
}