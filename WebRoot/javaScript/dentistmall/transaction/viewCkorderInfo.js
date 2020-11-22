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
					str +="<tr><td style=\"text-align:center\">"+mxList[i].mmfCode+"</td>";
					str += "<td style=\"text-align:center\"><a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+mxList[i].wmsMiId+"\" target=\"_blank\"><strong>"+mxList[i].matName+"</strong></a></td>";
					/*增加型号编号*/
					str += "<td style=\"text-align:center\">"+mxList[i].norm+"</td>";
					str += "<td style=\"text-align:center\">￥"+toDecimal2(mxList[i].unitMoney)+"</td>";
					str += "<td style=\"text-align:center\">"+mxList[i].basicUnit+"</td>";
					str += "<td style=\"text-align:center\">"+mxList[i].matNumber+"</td>";
					str += "<td style=\"text-align:center\">"+(mxList[i].number2!= null?mxList[i].number2:0)+"</td>";
					str += "<td style=\"text-align:center\">"+mxList[i].enterNumber+"</td>";
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
