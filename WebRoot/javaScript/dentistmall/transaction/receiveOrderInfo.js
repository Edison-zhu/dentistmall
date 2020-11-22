var _moidId;
var _mxList;
$(function(){
	_moidId=$.supper("getParam", "moiId");
	initData(_moidId);
}); 
function initData(moiId){
	var vdata = "moiId="+moiId;
	$.supper("doservice", {"service":"MdOrderMxService.getOrderMxListByMoiId2","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			_mxList = jsondata.obj;
			var str = "";
			if(_mxList != null && _mxList.length > 0){
				for(var i =0;i < _mxList.length;i++){
					var number2 = (_mxList[i].number2?_mxList[i].number2:0);
					var enterNumber = (_mxList[i].enterNumber?_mxList[i].enterNumber:0);
					var shureNumber = (_mxList[i].shureNumber?_mxList[i].shureNumber:0);
					var cha = parseInt(enterNumber)-parseInt(shureNumber);
					str += "<tr><td style=\"text-align:center\"><a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+_mxList[i].wmsMiId+"\" target=\"_blank\"><strong>"+_mxList[i].matName+"</strong></a></td>";
					str += "<td style=\"text-align:center\">"+_mxList[i].norm+"</td>";
					str += "<td style=\"text-align:center\">￥"+toDecimal2(_mxList[i].unitMoney)+"</td>";
					str += "<td style=\"text-align:center\">"+_mxList[i].basicUnit+"</td>";
					str += "<td style=\"text-align:center\">"+_mxList[i].matNumber+"</td>";
					str += "<td style=\"text-align:center\">"+number2+"</td>";
					str += "<td style=\"text-align:center\">"+enterNumber+"</td>";
					str += "<td style=\"text-align:center\">"+shureNumber+"</td>";
					if(shureNumber < enterNumber)
						str += "<td style=\"text-align:center\"><input type=\"text\" id=\"_inp"+i+"\" style=\"width:80px;text-align:center\" value="+cha+" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\"></td>";
					else
						str += "<td style=\"text-align:center\">-</td>";
					str += "</tr>";
				}
				$("#mxList").html(str);
			}
		}
 	}});
}

function receive(){
	var momIds="";
	var shus ="";
	if(_mxList != null && _mxList.length > 0){
		for(var i =0;i < _mxList.length;i++){
			var mx = _mxList[i];
			var x = mx.enterNumber-(mx.shureNumber?mx.shureNumber:0);
			var v = $("#_inp"+i).val();
			if(v != null && v != "" && v != "0"){
				if(v > x){
					$.supper("alert", {title:"操作提示",msg : "收货数量不能大于入库数量！"});
					return;
				}else{
					momIds += mx.momId+",";
					shus += v+",";
				}
			}
		}
	}
	
	if(momIds ==null || momIds==""){
		$.supper("alert", {title:"操作提示",msg : "请输入收货数量！"});
		return;
	}
	momIds = momIds.substring(0,momIds.length-1);
	shus = shus.substring(0,shus.length-1);
	var vdata="moiId="+_moidId+"&momIds="+momIds+"&shus="+shus;
	$.supper("confirm",{ title:"收货操作", msg:"确认收货操作？", yesE:function(){	
		$.supper("doservice", {"service" : "MdOrderInfoService.saveReceiveOrderInfo","ifloading" : 1,"options":{"type":"post","data":vdata},"BackE" : function(jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert", {title : "操作提示",msg : "操作成功！",BackE : function (){
						var obj = jsondata.obj;
						$("#yqrDiv").html(obj.number1);
						
						if(obj.processStatus=='4'){
							$("#saveBut").show();
							$("#closeBut").show();
						}else{
							var vdata="moiId="+_moidId;
							var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/viewCgyOrderInfo"});
							window.location.href=att_url;
						}
						initData(_moidId);
					}});
				} else
					$.supper("alert", {
						title : "操作提示",
						msg : "操作失败！"
					});
			}
		});
	}});
}

function closeOrder(){
	if(_mxList != null && _mxList.length > 0){
		for(var i =0;i < _mxList.length;i++){
			var enterNumber = (_mxList[i].enterNumber?_mxList[i].enterNumber:0);
			var shureNumber = (_mxList[i].shureNumber?_mxList[i].shureNumber:0);
			if(enterNumber > shureNumber){
				$.supper("alert", {title : "操作提示",msg : "商品:"+_mxList[i].matName+" 型号:"+_mxList[i].norm+" 入库数量大于确认数量，不允许关闭订单！"});
				return;
			}
		}
	}
	var vdata="moiId="+_moidId;
	$.supper("confirm",{ title:"关闭订单操作", msg:"确认关闭订单操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdOrderInfoService.saveEndOrderInfo","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:function (){
					var vdata="moiId="+_moidId;
					var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/viewCgyOrderInfo"});
					window.location.href=att_url;
				}});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}



 
