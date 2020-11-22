var _mxList;
var _moiId;
$(function(){
	_moiId=$.supper("getParam", "moiId");
	initData();
}); 

function initData(){
	var vdata = "moiId="+_moiId;
	$.supper("doservice", {"service":"MdOrderMxService.getOrderMxListByMoiId2","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			_mxList = jsondata.obj;
			var str = "";
			if(_mxList != null && _mxList.length > 0){
				for(var i =0;i < _mxList.length;i++){
					str += "<tr><td style=\"text-align:center\"><a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+_mxList[i].wmsMiId+"\" target=\"_blank\"><strong>"+_mxList[i].matName+"</strong></a></td>";
					str += "<td style=\"text-align:center\">"+_mxList[i].norm+"</td>";
					str += "<td style=\"text-align:center\"><input type=\"text\" id=\"_inp"+i+"\" value=\""+_mxList[i].unitMoney+"\" style=\"width:80px;text-align:center\" onkeyup=\"this.value=this.value.replace(/[^0-9.]/g,'')\"></td>";
					str += "<td style=\"text-align:center\">"+_mxList[i].basicUnit+"</td>";
					str += "<td style=\"text-align:center\">"+_mxList[i].matNumber+"</td>";
					str += "<td style=\"text-align:center\">￥"+toDecimal2(_mxList[i].totalMoney)+"</td>";
					str += "</tr>";
				}
				$("#mxList").html(str);
			}
		}
 	}});
}

function save(){
	var money3=$("#money3").val();
	var test=$('#test').val();
	var momIds="";
	var prices ="";
	if(_mxList != null && _mxList.length > 0){
		for(var i =0;i < _mxList.length;i++){
			var mx = _mxList[i];
			var x = mx.money2;
			var v = $("#_inp"+i).val();
			if(v != null && v != "" && v >0){
				if(v > x){
					$.supper("alert", {title:"操作提示",msg : "单价不能大于购买时商品单价！"});
					return;
				}else{
					momIds += mx.momId+",";
					prices += v+",";
				}
			}else{
				$.supper("alert", {title:"操作提示",msg : "请输入正确的商品单价！"});
				return;
			}
		}
	}
	
	momIds = momIds.substring(0,momIds.length-1);
	prices = prices.substring(0,prices.length-1);
	var vdata="moiId="+_moiId+"&prices="+prices+"&money3="+money3+"&momIds="+momIds+"&test="+test;
	$.supper("confirm",{ title:"保存操作", msg:"确认保存操作？", yesE:function(){	
		$.supper("doservice", {"service" : "MdOrderInfoService.updateOrderInfo","ifloading" : 1,"options":{"type":"post","data":vdata},"BackE" : function(jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert", {
						title : "操作提示",
						msg : "操作成功！",
						BackE : function (){
							window.location.reload();
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
	var vdata="moiId="+_moiId;
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
	var vdata="moiId="+_moiId;
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



 
