$(function(){
	initMatList();

	var $gaddress = $('#addressInfo');
	var $addressMask = $('#addressId');
	fLogin = {
		/* 打开弹窗 */
		'openLogin': function () {
			$gaddress.css({'top': '200px', 'left': ($(window).width() - $gaddress.width()) / 2 + 'px'}).fadeIn();
			$addressMask.fadeIn();
		},
		/* 关闭弹窗 */
		'closeLogin': function () {
			$gaddress.fadeOut();
			$addressMask.fadeOut();
		}
	};
	$('#addressClose').click(fLogin.closeLogin);
}); 
var _mxList;
function initMatList(){
	var str = "";
	var vdata="moiId="+$("#moiId").val();
	$.supper("doservice", {"service":"ShoppingService.getOrderMxByMoiId","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			_mxList = jsondata.obj;
			if(_mxList != null && _mxList.length > 0){
				for(var i = 0;i < _mxList.length;i++){
					var mat = _mxList[i];
					var enterNumber = (mat.enter_number!=null?mat.enter_number:0);
					var shureNumber = (mat.shure_number!=null?mat.shure_number:0);
					var cha = parseInt(enterNumber)-parseInt(shureNumber);
					str +="<div class=\"order-info\"><div class=\"left\" style=\"margin-left:10px\"><img src=\""+mat.less_file_path+"\" width=\"68\" height=\"68\"><ul>";
					str +="<li class=\"name\"><a href=\"xiangxi.htm?wmsMiId="+mat.wms_mi_id+"\" target=\"_blank\">"+mat.mat_name+"</a></li>";
					str +="<li class=\"type\">型号：<strong>"+mat.NORM+"</strong></li></ul></div>";
					str +="<div class=\"right\"><div class=\"money\">￥"+toDecimal2(mat.Unit_money)+"</div>"
						+"<div class=\"number\">"+mat.mat_number+"</div>"
						+"<div class=\"number\">"+(mat.number2!=null?mat.number2:"--")+"</div>"
						+"<div class=\"number\">"+mat.enter_number+"</div>"
						+"<div class=\"number\">"+shureNumber+"</div>";
					// if(shureNumber<enterNumber)
					// 	str +="<div class=\"number\"><input type=\"text\" id=\"_inp"+i+"\" style=\"width:80px;text-align:center;margin-top: 40px;height: 20px;\" value="+cha+" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\"></div>";
					// else
					// 	str +="<div class=\"number\">-</div>";
					str += "</div></div>";
				}
			}
			$("#matList").html(str);
		}
	}});
}

// 检查是否需要安全码，验证安全码 yangfeng 2020-08-28
var getOpenSecurityService = 'sysUserService.getOpenSecurity';
var checkSecurityCode = 'sysUserService.updateOpenSecurityState';

function receive(){
	// 获取是否需要安全码
	$.supper("doservice", {
		"service": getOpenSecurityService, "ifloading": 1, "BackE": function (jsondata) {
			if (jsondata.code == "1") {
				// 需要安全码则输入安全码
				fLogin.openLogin();
			} else if (jsondata.code == '2') {
				// 不需要, 直接走确认收货
				sureReceive();
			} else if (jsondata.code == '3') {
				$.supper("alert", {
					title: "操作提示",
					msg: "未登录！"
				});
			} else {
				$.supper("alert", {
					title: "操作提示",
					msg: "操作失败！"
				});
			}
		}
	});
}

// 验证安全码，通过执行相关方法
function checkSecurityCodeFunc() {
	let securityCode = $('#securityCode').val();
	if (securityCode == '') {
		$.supper("alert", {
			title: "操作提示",
			msg: "请输入验证码！"
		});
		return;
	}
	$.supper("doservice", {
		"service": checkSecurityCode, "ifloading": 1, 'data': 'securityCode=' + securityCode, "BackE": function (jsondata) {
			if (jsondata.code == "1") {
				fLogin.closeLogin();
				sureReceive();
			} else
				$.supper("alert", {
					title: "操作提示",
					msg: jsondata.meg
				});
		}
	});
}

function sureReceive() {
	var momIds="";
	var shus ="";
	var masIds = "";
	var moiId=$("#moiId").val();
	var flag = false;
	var message = '是否确认收货';
	if(_mxList != null && _mxList.length > 0){
		for(var i =0;i < _mxList.length;i++){
			var mx = _mxList[i];
			var enterNumber = (mx.enter_number!=null?mx.enter_number:0);
			var shureNumber = (mx.shure_number!=null?mx.shure_number:0);
			var x = enterNumber-shureNumber;
			var matNumber = mx.mat_number;
			var sendNumebr = mx.number2;
			if(matNumber <= sendNumebr){ //发货的情况
				momIds += mx.mom_id+",";
			} else { //存在部分发货，不允许确认
				flag = true
			}
			if( x == 0 || x > 0){
				message = '存在未入库的商品，是否确认收货？';
			}
			// var v = $("#_inp"+i).val();
			// if(v != null && v != "" && v != "0"){
			// 	if(v > x){
			// 		$.supper("alert", {title:"操作提示",msg : "确认数量不能大于入库数量！"});
			// 		return;
			// 	}else{
			// 		momIds += mx.mom_id+",";
			// 		shus += v+",";
			// 	}
			// }
			if(mx.mas_id !== undefined && mx.mas_id !== null && mx.mas_id !== '' && masIds.indexOf(mx.mas_id) < 0){
				masIds += mx.mas_id;
			}
		}
	}


	// if((momIds ==null || momIds=="") && masIds != ''){
	// 	message = '存在未确认的数量以及售后商品，是否确认收货？';
	// }else
	if(flag === true){
		// message = '存在未确认的数量，是否确认收货？';
		$.supper("alert", {title:"操作提示",msg : "订单存在商家部分商品未发货，无法进行确认收货！"});
		return;
	} else if (masIds != ''){
		message = '存在售后的商品，是否确认收货';
	}
	momIds = momIds.substring(0,momIds.length-1);
	// shus = shus.substring(0,shus.length-1);
	var vdata="moiId="+moiId+"&momIds="+momIds+"&shus="+shus;
	if(masIds != ''){
		masIds = masIds.substring(0, masIds.length - 1);
		vdata += '&masIds=' + masIds;
	}
	$.supper("confirm",{ title:"收货操作", msg: message, yesE:function(){
			$.supper("doservice", {"service" : "MdOrderInfoService.saveReceiveOrderInfo","ifloading" : 1,"options":{"type":"post","data":vdata},"BackE" : function(jsondata) {
					if (jsondata.code == "1") {
						$.supper("alert", {title : "操作提示",msg : "操作成功！",BackE : function (){
								var obj = jsondata.obj;
								if(obj.processStatus=='4'){
									$("#saveBtn").show();
									$("#closeBut").show();
								}else{
									window.location.href="ddxq.htm?moiId="+moiId;
								}


								initMatList();
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
			var enterNumber = (_mxList[i].enter_number?_mxList[i].enter_number:0);
			var shureNumber = (_mxList[i].shure_number?_mxList[i].shure_number:0);
			if(enterNumber > shureNumber){
				$.supper("alert", {title : "操作提示",msg : "商品:"+_mxList[i].mat_name+" 型号:"+_mxList[i].NORM+" 入库数量大于确认数量，不允许关闭订单！"});
				return;
			}
		}
	}
	
	var moiId=$("#moiId").val();
	var vdata="moiId="+moiId;
	$.supper("confirm",{ title:"关闭订单操作", msg:"确认关闭订单操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdOrderInfoService.saveEndOrderInfo","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:function(){
					window.location.href="ddxq.htm?moiId="+moiId;
				}});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}