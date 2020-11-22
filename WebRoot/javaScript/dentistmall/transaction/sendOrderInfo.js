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
					var cha=parseInt(_mxList[i].matNumber)-parseInt(number2);
					str += "<tr><td style=\"text-align:center\"><a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+_mxList[i].wmsMiId+"\" target=\"_blank\"><strong>"+_mxList[i].matName+"</strong></a></td>";
					str += "<td style=\"text-align:center\">"+_mxList[i].norm+"</td>";
					str += "<td style=\"text-align:center\">￥"+toDecimal2(_mxList[i].unitMoney)+"</td>";
					str += "<td style=\"text-align:center\">"+_mxList[i].basicUnit+"</td>";
					str += "<td style=\"text-align:center\">"+_mxList[i].matNumber+"</td>";
					str += "<td style=\"text-align:center\">"+number2+"</td>";
					if(number2 < _mxList[i].matNumber)
						str += "<td style=\"text-align:center\"><input type=\"text\" id=\"_inp"+i+"\" value=\""+cha+"\" style=\"width:80px;text-align:center\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\"></td>";
					else
						str += "<td style=\"text-align:center\">-</td>";
					str += "</tr>";
				}
				$("#mxList").html(str);
			}
		}
 	}});
}

function send(){
	var expressName=$("#expressName").val();
	var expressCode = $("#expressCode").val();
	var test=$('#test').val();
	var momIds="";
	var shus ="";
	if(_mxList != null && _mxList.length > 0){
		for(var i =0;i < _mxList.length;i++){
			var mx = _mxList[i];
			var x = mx.matNumber-(mx.number2?mx.number2:0);
			var v = $("#_inp"+i).val();
			if(v != null && v != "" && v >0){
				momIds += mx.momId+",";
				shus += v+",";
			}
		}
	}
	
	if(momIds ==null || momIds==""){
		$.supper("alert", {title:"操作提示",msg : "请输入发货数量！"});
		return;
	}
	momIds = momIds.substring(0,momIds.length-1);
	shus = shus.substring(0,shus.length-1);
	var vdata="moiId="+_moidId+"&expressName="+expressName+"&expressCode="+expressCode+"&momIds="+momIds+"&shus="+shus+"&test="+test;
	$.supper("confirm",{ title:"发货操作", msg:"确认发货操作？", yesE:function(){	
		$.supper("doservice", {"service" : "MdOrderInfoService.saveSendOrderInfo","ifloading" : 1,"options":{"type":"post","data":vdata},"BackE" : function(jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert", {
						title : "操作提示",
						msg : "操作成功！",
						BackE : function (){
							var vdata="moiId="+_moidId;
							var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/viewSupplierOrderInfo"});
							window.location.href=att_url;
						}
					});
				} else
					$.supper("alert", {
						title : "操作提示",
						msg : "操作失败！"
					});
			}
		});
	}});
}
//导出详情
function main_export(){
	var vdata="moiId="+_moidId;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportSupplierInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});}
/**
 * 增加导出配送单
 */
function main_exportC(){
	var vdata="moiId="+_moidId;
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



 
