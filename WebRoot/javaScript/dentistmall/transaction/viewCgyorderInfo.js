var moiId;
$(function(){
	moiId=$.supper("getParam", "moiId");
	initData(moiId);
}); 

function initData(moiId){
	var vdata = "moiId="+moiId;
	$.supper("doservice", {"service":"MdOrderMxService.getOrderMxListByMoiId2","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			var mxList = jsondata.obj;
			var str = "";
			if(mxList != null && mxList.length > 0){
				for(var i =0;i < mxList.length;i++){
					/*增加型号编号*/
					str +="<tr><td style=\"text-align:center\">"+mxList[i].mmfCode+"</td>";
					str += "<td style=\"text-align:center\"><a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+mxList[i].wmsMiId+"\" target=\"_blank\"><strong>"+mxList[i].matName+"</strong></a></td>";					
					str += "<td style=\"text-align:center\">"+mxList[i].norm+"</td>";
					str += "<td style=\"text-align:center\">￥"+toDecimal2(mxList[i].unitMoney)+"</td>";
					str += "<td style=\"text-align:center\">"+mxList[i].basicUnit+"</td>";
					str += "<td style=\"text-align:center\">"+mxList[i].matNumber+"</td>";
					str += "<td style=\"text-align:center\">"+mxList[i].shureNumber+"</td>";
					str += "<td style=\"text-align:center\">￥"+toDecimal2(mxList[i].totalMoney)+"</td>";
					str += "<td style=\"text-align:center\">￥"+toDecimal2(mxList[i].money1?mxList[i].money1:0)+"</td>";
					str += "</tr>";
				}
				$("#mxList").html(str);
			}
		}
 	}});
}
function main_export(){
	var vdata="moiId="+moiId;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
/**
 * 增加导出配送单
 */
function main_exportC(){
	var vdata="moiId="+moiId;
	if($('#test').val() !== null && $('#test').val() !== undefined){
		var str="&test="+$('#test').val();
		vdata+=str;
	}
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportSupplierInfoSheetC","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}


 
