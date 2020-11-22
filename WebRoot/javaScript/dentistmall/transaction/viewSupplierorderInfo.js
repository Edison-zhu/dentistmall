var moiId;
var preUrl;
var orderUrl;
$(function(){
	var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
	if (selOutOrderType != null && selOutOrderType.preUrl != null) {
		preUrl = selOutOrderType.preUrl;
		orderUrl = selOutOrderType.orderUrl;
		$('#backTo').show();
		$.supper("setProductArray", {"name": "selOutOrderType", "value": null});
	}
	moiId=$.supper("getParam", "moiId");
	initData(moiId);
	//新增商品回车查询
	$("#searchDdName").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#searchDdBtn").trigger("click");
		  }
	});

	// //保存摘要
	// $("#saveButtom").click(function () {
	//
	// })
});

function backTo() {
	setTimeout(function () {
		$.supper("closeTtemWin", {"url": orderUrl, "title": "售后明细"});
	}, 200);
	$.supper("showTtemWin",{ "url":preUrl,"title":'订单放款明细'});
}
/*var test;
$(function(){
	//moiId=$.supper("getParam", "test");
	initData(test);
}); */
function initData(moiId){
	var vdata = "moiId="+moiId;
	$.supper("initPagination", {id:"Pagination", service:"MdOrderMxService.getOrderMxListByMoiId",data:vdata,limit:5, isAjax:"1", "BackE": function (dataList) {
			initXQList('matList',dataList, false);
		// if (jsondata.code == "1") {
		// 	var mxList = jsondata.obj;
		// 	var str = "";
		// 	if(mxList != null && mxList.length > 0){
		// 		for(var i =0;i < mxList.length;i++){
		// 			/*增加型号编号*/
		// 			str +="<tr><td style=\"text-align:center\">"+mxList[i].mmfCode+"</td>";
		// 			str += "<td style=\"text-align:center\"><a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+mxList[i].wmsMiId+"\" target=\"_blank\"><strong>"+mxList[i].matName+"</strong></a></td>";
		// 			str += "<td style=\"text-align:center\">"+mxList[i].norm+"</td>";
		// 			str += "<td style=\"text-align:center\">￥"+toDecimal2(mxList[i].unitMoney)+"</td>";
		// 			str += "<td style=\"text-align:center\">"+mxList[i].basicUnit+"</td>";
		// 			str += "<td style=\"text-align:center\">"+mxList[i].matNumber+"</td>";
		// 			str += "<td style=\"text-align:center\">"+(mxList[i].number2!=null?mxList[i].number2:0)+"</td>";
		// 			str += "<td style=\"text-align:center\">"+mxList[i].shureNumber+"</td>";
		// 			str += "<td style=\"text-align:center\">￥"+toDecimal2(mxList[i].totalMoney)+"</td>";
		// 			str += "<td style=\"text-align:center\">￥"+toDecimal2(mxList[i].money1?mxList[i].money1:0)+"</td>";
		// 			str += "</tr>";
		// 		}
		// 		$("#mxList").html(str);
		// 	}
		// }
 	}});
}
function searchDd() {
	var matName = $('#searchDdName').val();
	var data = 'matName=' + matName + '&moiId=' + moiId;
	$.supper("initPagination", {id:"Pagination", service:"MdOrderMxService.getOrderMxName", data:data, limit: 5, isAjax:"1", "BackE": function (dataList){
		initXQList('matList',dataList, false);
	}});
}
function resetSearch() {
	$('#searchDdName').val('');
	searchDd();
}
function main_export(){
	var vdata="moiId="+moiId;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportSupplierInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});}

function saveZhaiYao(){
	var vdata="moiId="+moiId;
	if($('#test').val() !== null && $('#test').val() !== undefined){
		var str="&test="+$('#test').val();
		vdata+=str;
		$.supper("doservice", {"service":"MdOrderInfoService.saveZhaiYao","data":vdata, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
			}});
	}
}


/**
 * 增加导出配送单
 */
function main_exportC(){
	var vdata="moiId="+moiId;
	if($('#test').val() !== null && $('#test').val() !== undefined){
		var str="&test="+$('#test').val();
		vdata+=str;
		$.supper("doservice", {"service":"MdOrderInfoService.saveZhaiYao","data":vdata, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					var newTab=window.open('about:blank');
					$.supper("doservice", {"service":"MdOrderInfoService.exportSupplierInfoSheetC","data":vdata, "BackE":function (jsondata) {
							if (jsondata.code == "1") {
								newTab.location.href=jsondata.obj.path;
							}else
								$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
						}});
				}else
					$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
			}});
	}
	// var vdata="moiId="+moiId;
	// if($('#test').val() !== null && $('#test').val() !== undefined){
	// 	var str="&test="+$('#test').val();
	// 	vdata+=str;
	// }
	// var newTab=window.open('about:blank');
	// $.supper("doservice", {"service":"MdOrderInfoService.exportSupplierInfoSheetC","data":vdata, "BackE":function (jsondata) {
	// 	if (jsondata.code == "1") {
	// 		newTab.location.href=jsondata.obj.path;
	// 	}else
	// 		$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	// }});
}

	



 
