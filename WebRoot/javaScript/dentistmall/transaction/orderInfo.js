$(function(){
	var moiId=$.supper("getParam", "moiId");
	initData(moiId);
}); 

function initData(moiId){
	var vdata = "moiId="+moiId;
	$.supper("doservice", {"service":"MdOrderInfoService.getMdOrderInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			var mdOrderInfo=jsondata.obj.mdOrderInfo;
			var mdSupplier=jsondata.obj.mdSupplier;
			var mxList = jsondata.obj.mxList;
			$("#addressee").html(mdOrderInfo.addressee);
			$("#deliveryAddress").html(mdOrderInfo.deliveryAddress);
			$("#addresseeTelephone").html(mdOrderInfo.addresseeTelephone);
			$("#orderCode").html(mdOrderInfo.orderCode);
			$("#applicantName").html(mdSupplier.applicantName);
			$("#corporateDomicile").html(mdSupplier.corporateDomicile);
			$("#phoneNumber").html(mdSupplier.phoneNumber);
			$("#placeOrderTime").html(mdOrderInfo.placeOrderTime);
			$("#commodityNumber").html(mdOrderInfo.commodityNumber);
			$("#actualMoney").html("￥"+mdOrderInfo.actualMoney);
			var shureNumber=0;
			var backNumber=0;
			var str = "";
			if(mxList != null && mxList.length > 0){
				for(var i =0;i < mxList.length;i++){
					str += "<tr><td><div><strong>"+mxList[i].matName+"</strong></div></td>";
					str += "<td>"+mxList[i].matNumber+"</td>";
					str += "<td>"+mxList[i].shureNumber+"</td>";
					str += "<td>"+mxList[i].backNumber+"</td>";
					str += "<td>￥"+mxList[i].unitMoney+"</td>";
					str += "<td>"+mxList[i].basicUnit+"</td>";
					str += "<td>￥"+mxList[i].totalMoney+"</td>";
					str += "<td><button class=\"btn btn-primary  btn-sm btn-info /\"><i class=\"fa fa-dollar\"></i>收货确认</button>&nbsp;&nbsp;&nbsp;&nbsp;"
						+"<button class=\"btn btn-primary  btn-sm  \"><i class=\"fa fa-dollar\"></i>退货</button></td></tr>";
					shureNumber += mxList[i].shureNumber;
					backNumber += mxList[i].backNumber;
				}
				$("#mxList").html(str);
			}
			$("#shureNumber").html(shureNumber);
			$("#backNumber").html(backNumber);
			$("#noShureNumber").html(mdOrderInfo.commodityNumber-shureNumber);
		}
 	}});
}



 
