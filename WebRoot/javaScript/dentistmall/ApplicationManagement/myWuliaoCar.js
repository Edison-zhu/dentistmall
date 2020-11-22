var url;
$(function(){

	initMatList();
});
var _dataList;
var _find_carts_all_action = 'ShoppingService.findMdCartsAll'
var _add_min_carts_action = 'ShoppingService.addCarts';
var _delete_carts_action = 'ShoppingService.deleteMdCartsByMmfId';
var _delete_carts_all = 'ShoppingService.deleteMdCartsAll';

function initMatList() {
	var mmfIds = '';
	var shus = '';
	var minShus='';
		$.supper("doservice", {
			"service": _find_carts_all_action, "BackE": function (jsondata) {
				var items = jsondata.items;
				if (items.length <= 0) {
					return;
				}
				items.forEach(function (item) {
					mmfIds += item.mmfId + ',';
					shus += item.mcCount + ',';
					if (item.mcSplitCount!=undefined&&item.mcSplitCount!=null){
						minShus+=item.mcSplitCount+',';
					}else {
						minShus+=0+',';
					}

				})
				if (mmfIds.length > 0) {
					mmfIds = mmfIds.substring(0, mmfIds.length - 1);
				}
				if (shus.length > 0) {
					shus = shus.substring(0, shus.length - 1);
				}
				if (minShus.length>0) {
					minShus = minShus.substring(0, minShus.length - 1);
				}
				initMatCartList(mmfIds, shus,minShus);
			}
		});
}
var mmfIdsss="";
function initMatCartList(mmfIds, shus,minShus) {
	if(mmfIds != null && mmfIds!= ""){
		var vdata = "mmfIds="+mmfIds+"&shus="+shus+"&minShus="+minShus;
		var str = "";
		var wmsIds=0;
		var wmsId2=0;
		var mmfId1s=0;
		var mmfId1=0;
		$.supper("doservice", {"service":"ShoppingService.getCarMatInfo","data":vdata, "BackE":function (jsondata) {
				if (jsondata.code == "1") {
					_dataList = jsondata.obj;
					if(_dataList != null && _dataList.length > 0){
						for(var i = 0;i < _dataList.length;i++){
							var mdSupplier = _dataList[i].mdSupplier;
							var matList= _dataList[i].matList;
							str += "<div class=\"order\" style='margin-top: 0px;margin-bottom: 0px;border-top: 0px;width: 100%;'><div class=\"\">";
							str+="</div>";
							str +="<div class=\"order-body\">";
							if(matList != null && matList.length>0){
								for(var j = 0;j < matList.length;j++){
									var mat = matList[j];
									if (mat.wmsMiId!=wmsId2){
										wmsIds+=1;
									}else {
										wmsIds+=0;
									}
									if (mat.mmfId!=mmfId1){
										mmfId1s+=1;
									}else {
										mmfId1s+=0;
									}
									mmfId1=mat.mmfId;
									wmsId2=mat.wmsMiId;
									// alert(mmfId1);
									mmfIdsss+=mat.mmfId+",";
									// mmfIdSum+=mat.mmfId+",";
									// wmsIdSum+=mat.wmsMiId+",";
									str+="<div class=\"order-info\" style='margin: 0px;height: 155px;' id=\"info_"+mat.mmfId+"\"><div class=\"left\">";
									// if(mdSupplier.state=='1' && mat.state=='1' && mat.mmfState=='1'){//判断该商家是否下架，判断该商品是否下架
										str+="<input style='top:-40px;' name=\"box_"+mdSupplier.wzId+"\" onclick=\"clickMat(this,'"+mdSupplier.wzId+"')\" checked=true type=\"checkbox\"  value=\""+mat.mmfId+"\" />";
										str +="<a href=\"xiangxi.htm?wmsMiId="+mat.wmsMiId+"\" target=\"_blank\"><img src=\""+mat.lessenFilePath+"\" width=\"68\" height=\"68\"></a><ul>";
										// str +="<li class=\"name\">"+mat.matName+"</a></li>";
										str+="<li class=\"name\"><a onclick='getNormMx("+mat.wmsMiId+")'style='color:#1E90FF;cursor:pointer;'>"+mat.matName+"</a></li>";
									// }else{
									// 	str+="<input  type=\"checkbox\" name=\"box_"+mdSupplier.wzId+"\" disabled=disabled />";
									// 	str +="<a href=\"xiangxi.htm?wmsMiId="+mat.wmsMiId+"\" target=\"_blank\"><img src=\""+mat.lessenFilePath+"\" width=\"68\" height=\"68\"></a><ul>";
									// 	// str +="<li class=\"type\">"+mat.matName+"</li>";
									// 	str+="<li class=\"type\"><a onclick='getNormMx("+mat.wmsMiId+")'style='color:#1E90FF;cursor:pointer;'>"+mat.matName+"</a></li>";
									// }
									str+="<input  type=\"text\" id=\"countwmsId_"+mat.mmfId+"\" class=\"\" value=\""+mat.wmsMiId+"\" style='display: none'>"
									str += "<li class=\"type\" style='text-align: left;margin-top: 10px'>品牌：<strong><a target=\"_blank\">"+mat.brand+"</a></strong></li>";
									str +="<li class=\"type\">型号："+mat.mmfName+"</li>";
									str += '<li class="type">编号：'+mat.mmfCode+'</li>';
									str += '<li class="\type\" style=\'text-align: left\'>包装方式：'+(mat.productName !== undefined ? mat.productName : '')+'</li>';
									str +="</ul></div><div style='width: 50%;float: left;display: flex;align-items: center'>";
									// if(mdSupplier.state=='1' && mat.state=='1' && mat.mmfState=='1'){
										// str +="<div class=\"kucun\">￥"+mat.quantity+"</div>";splitqQuantity
										// str +="<div style='text-align: center;width: 20%;float: left;'>"+(mat.quantity!=null?mat.quantity:"0")+mat.basicUnit+(mat.splitqQuantitySum!=null?mat.splitqQuantitySum:"0")+(mat.splitUnit !== undefined ? mat.splitUnit : ''+mat.basicUnit+'')+"</div><div style='width: 30%;text-align: center;float: left;'>"+mat.productName+"</div><div style='width: 30%;text-align: center;float: left;'><div class=\"number\" style='display: inline-block'><span>";
										// str += "<a href=\"javascript:minusShu('"+mat.mmfId+"')\" class=\"a-left\" title=\"减1\" style='width: 15px;display: inline-block;height: 19px;background-color: #ddd;position: relative;top: 1px;line-height: 20px;text-align: center;'>-</a>";
										// str +="<input  type=\"text\" id=\"countShu_"+mat.mmfId+"\" class=\"\" value=\""+mat.countShu+"\" "
										// 	+"onfocus='this.select()' onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');saveShu('"+mat.mmfId+"')\" onblur=\"this.value=this.value.replace(/[^0-9]/g,'');saveShu('"+mat.mmfId+"')\" min='1'  maxlength=\"4\" title=\"请输入购买量\" style='width: 45px;text-align: center;color: #666;border: 1px solid #ddd;outline: 0;height: 20px;'>";
										// str +="<a href=\"javascript:addShu('"+mat.mmfId+"')\" class=\"a-right\" title=\"加1\" style='width: 15px;display: inline-block;height: 19px;background-color: #ddd;position: relative;top: 1px;line-height: 20px;text-align: center;'>+</a></span>"+mat.basicUnit+"</div>";
										str +="<div style='text-align: center;width: 20%;float: left;'>"+(mat.quantity!=null?mat.quantity:"0")+mat.basicUnit+(mat.splitqQuantitySum!=null?mat.splitqQuantitySum:"0")+(mat.splitUnit !== undefined ? mat.splitUnit : ''+mat.basicUnit+'')+"</div><div style='width: 30%;text-align: center;float: left;'>"+mat.productName+"</div><div style='width: 30%;text-align: center;float: left;'><div class=\"number\" style='display: inline-block'><span>";
										// str += "<a href=\"javascript:minusShu('"+mat.mmfId+"')\" class=\"a-left\" title=\"减1\" style='width: 15px;display: inline-block;height: 19px;background-color: #ddd;position: relative;top: 1px;line-height: 20px;text-align: center;'>-</a>";
										str +="<input  type=\"text\" id=\"countShu_"+mat.mmfId+"\" class=\"\" value=\""+mat.countShu+"\" "
											+"onfocus='this.select()' onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');saveShu('"+mat.mmfId+"')\" onblur=\"this.value=this.value.replace(/[^0-9]/g,'');saveShu('"+mat.mmfId+"')\" min='1'  maxlength=\"4\" title=\"请输入购买量\" style='width: 45px;text-align: center;color: #666;border: 1px solid #ddd;outline: 0;height: 20px;'>";
										str +="</span>"+mat.basicUnit+"</div>";


										// if (mat.splitQuantity!=null&&mat.splitQuantity!=undefined&&mat.splitQuantity!="") {
											str +="<div style='display: inline-block;margin-left: 5px'><input id=\"splitQuantity"+mat.mmfId+"\" value=\""+mat.mcSplitCount+"\" type='text' style='width: 25px;text-align: center;color: #666;border: 1px solid #ddd;outline: 0;height: 20px;'>"+(mat.splitUnit !== undefined ? mat.splitUnit : ''+mat.basicUnit+'')+"</div></div>"
										// }else {
										// 	str +="<div style='display: inline-block;margin-left: 5px'><input id=\"splitQuantity"+mat.mmfId+"\" value='' type='text' style='width: 25px;text-align: center;color: #666;border: 1px solid #ddd;outline: 0;height: 20px;display: none'></div></div>"//"+mat.basicUnit+"
										// }
										// str +="<div class=\"unit\">"+mat.basicUnit+"</div>";
										shu2=mat.mmfId;
										str +="<div class=\"operation\" style='color:#1E90FF;width: 20%;text-align: center;float: left;'>"//<p class=\"trigger\" onclick=\"setFavorites('"+mat.wmsMiId+"')\"><i class=\"icon-revoke\"></i><span id='gwcscj'>加入收藏</span></p>"
											+"<p style='' class=\"trigger\" onclick=\"delMat('"+mat.mmfId+"','"+mat.wzId+"')\"><i class=\"icon-cross\"></i>删除</p>"
											+"<p class=\"trigger\"></i></p></div></div>";
										str += "<input type='hidden' id=\"countMoney_"+mat.mmfId+"\" value=\""+mat.countMoney+"\">";
										str += "<input type='hidden' id=\"price_"+mat.mmfId+"\" value=\""+mat.price+"\">";
									// }else{
									// 	str+="<div class=\"money\" style=\"width: 20%;text-align: center;float: left;\">商品已下架</div><div class=\"number\" style=\"width: 30%;text-align: center;float: left;\">--</div><div class=\"unit\" style=\"width: 30%;text-align: center;float: left;\">--</div>";
									// 	str +="<div class=\"operation\" style='color:#1E90FF;width: 20%;text-align: center;float: left;'><p class=\"trigger\" onclick=\"delMat('"+mat.mmfId+"','"+mat.wzId+"')\"><i class=\"icon-cross\"></i>删除</p>";
									// 	str +="</div></div>";
									// }
									// alert($("#countShu_"+mat.mmfId).val());
									str +="</div>";
								}
							}
							str +="</div></div>";
						}
					}
					$("#gwcList").html(str);
					$("#wmsIdNumber").html(wmsIds);
					$("#mmfIdNumber").html(mmfId1s);

					intShu();
				}
			}});
	}
}
function clickSupplier(wzId){
	if(!$("#supplier_"+wzId).is(":checked")){
		$("[name = 'box_"+wzId+"']:checkbox").prop("checked", false);
	}else{
		$("[name = 'box_"+wzId+"']:checkbox").prop("checked",true);
	}
	checkIsAll();
	intShu();
}

function clickMat(obj,wzId){
	if(!$(obj).is(":checked")){//没有选中
		//判断是否还有选中的商品
		var checkedBoxs = $("input[name='box_"+wzId+"']:checked");
		if(checkedBoxs == null || checkedBoxs.length ==0)
			$("#supplier_"+wzId).prop("checked", false);
	}else{
		//判断是否还有未选中的商品
		var unCheckedBoxs = $("input[name='box_"+wzId+"']").not("input:checked");
		if(unCheckedBoxs == null || unCheckedBoxs.length ==0)
			$("#supplier_"+wzId).prop("checked", true);
	}
	checkIsAll();
	intShu();
}

function checkIsAll(){
	var flag=true;
	if(_dataList != null && _dataList.length > 0){
		for(var i=0;i < _dataList.length;i++){
			var mdSupplier = _dataList[i].mdSupplier;
			if(mdSupplier.state=='1'){
				var unCheckedBoxs = $("input[name='box_"+mdSupplier.wzId+"']").not("input:checked");
				if(unCheckedBoxs != null && unCheckedBoxs.length >0){
					flag=false;
					break;
				}
			}
		}
	}
	if(flag){
		$("#all1").prop("checked", true);
		$("#all2").prop("checked", true);
	}else{
		$("#all1").prop("checked", false);
		$("#all2").prop("checked", false);
	}
}

function checkAll(obj){
	console.log("111")
	if(!$(obj).is(":checked")){//没有选中
		console.log(JSON.stringify(_dataList))
		if(_dataList != null && _dataList.length > 0){
			for(var i=0;i < _dataList.length;i++){
				var mdSupplier = _dataList[i].mdSupplier;
				// if(mdSupplier.state=='1'){
					// $("#supplier_"+mdSupplier.wzId).prop("checked", false);
					$("[name = 'box_"+mdSupplier.wzId+"']:checkbox").prop("checked", false);
				// }
			}
		}
		$("#all1").prop("checked", false);
		$("#all2").prop("checked", false);
	}else{
		if(_dataList != null && _dataList.length > 0){
			for(var i=0;i < _dataList.length;i++){
				var mdSupplier = _dataList[i].mdSupplier;
				// if(mdSupplier.state=='1'){
				 	// $("#supplier_"+mdSupplier.wzId).prop("checked", true);
				 	$("[name = 'box_"+mdSupplier.wzId+"']:checkbox").prop("checked", true);
				// }
			}
		}
		$("#all1").prop("checked", true);
		$("#all2").prop("checked", true);
	}
	intShu();
}

function delMat(mmfId,wzId){
	countCollect1();
	var data = 'mmfIds='+mmfId;
	$.supper("doservice", {
		"service": _delete_carts_action, 'data': data, "BackE": function (jsondata) {
			if(jsondata.code == '1'){
				$("#info_"+mmfId).remove();
				var boxs = $("input[name='box_"+wzId+"']");
				if(boxs==null || boxs.length==0)
					$("#order_"+wzId).remove();
				intShu();
                countCollect1();
			}
		}
	});
}

function delSel(){
	$.supper("confirm",{ title:"删除商品", msg:"确定要删除所选商品吗？" ,yesE:function(){
	if(_dataList != null && _dataList.length > 0){
		for(var i = 0;i < _dataList.length;i++){
			var mdSupplier = _dataList[i].mdSupplier;
			$("input:checkbox[name='box_"+mdSupplier.wzId+"']:checked").each(function() { // 遍历name=test的多选框
				delMat($(this).val(),mdSupplier.wzId);
			});
		}
	}
		}});

}
function delSel2(){
			if(_dataList != null && _dataList.length > 0){
				for(var i = 0;i < _dataList.length;i++){
					var mdSupplier = _dataList[i].mdSupplier;
					$("input:checkbox[name='box_"+mdSupplier.wzId+"']:checked").each(function() { // 遍历name=test的多选框
						delMat($(this).val(),mdSupplier.wzId);
					});
				}
			}
}

function minusShu(mmfId){
	var shu =$("#countShu_"+mmfId).val();
	var money =$("#price_"+mmfId).val();
	if(shu >1)
		shu--;
	var countMoney=shu*money;
	$("#countShu_"+mmfId).val(shu);
	$("#countMoney_"+mmfId).val(countMoney);
	//$("#countMoneyDiv_"+mmfId).html(toDecimal2(countMoney));
	var data = 'mmfId=' + mmfId + '&shu=' + shu;
	$.supper("doservice", {
		"service": _add_min_carts_action, 'data': data, "BackE": function (jsondata) {
			intShu();
		}
	});
}
function addShu(mmfId){
	var shu =$("#countShu_"+mmfId).val();
	var money =$("#price_"+mmfId).val();
	shu++;
	var countMoney=shu*money;
	$("#countShu_"+mmfId).val(shu);
	$("#countMoney_"+mmfId).val(countMoney);
	//$("#countMoneyDiv_"+mmfId).html(toDecimal2(countMoney));
	var data = 'mmfId=' + mmfId + '&shu=' + shu;
	$.supper("doservice", {
		"service": _add_min_carts_action, 'data': data, "BackE": function (jsondata) {
			intShu();
		}
	});
}

function saveShu(mmfId){
	var shu =$("#countShu_"+mmfId).val();
	if(shu <= 0 || shu == ''){
		shu = 1;
		$("#countShu_"+mmfId).val(shu);
	}
	var money =$("#price_"+mmfId).val();
	var countMoney=shu*money;
	$("#countShu_"+mmfId).val(shu);
	$("#countMoney_"+mmfId).val(countMoney);
	//$("#countMoneyDiv_"+mmfId).html(toDecimal2(countMoney));
	var data = 'mmfId=' + mmfId + '&shu=' + shu;
	$.supper("doservice", {
		"service": _add_min_carts_action, 'data': data, "BackE": function (jsondata) {
			intShu();
		}
	});
}
//统计数量
var sumbitShu;
var numberSums="";
var mmfIdSums="";
var wmsIdSums="";

function intShu(){
	var countShu=0;
	var  numberSum="";
	var countMoney=0;
	var mmfIdSum="";
	var wmsIdSum="";
	if(_dataList != null && _dataList.length > 0){
		for(var i = 0;i < _dataList.length;i++){
			var mdSupplier = _dataList[i].mdSupplier;
			var noExpressMoney = mdSupplier.noExpressMoney!=null?mdSupplier.noExpressMoney:0;
			var expressMoney = mdSupplier.expressMoney!=null?mdSupplier.expressMoney:0;
			var supplierCount=0;
			$("input:checkbox[name='box_"+mdSupplier.wzId+"']:checked").each(function() { // 遍历name=test的多选框
				var mmfId=$(this).val();  // 每一个被选中项的值
				countShu+=parseInt($("#countShu_"+mmfId).val());
				wmsIdSum+=$("#countwmsId_"+mmfId).val()+",";
				numberSum+=parseInt($("#countShu_"+mmfId).val())+",";
				mmfIdSum+=mmfId+",";
				// alert($("#splitQuantity"+mmfId).val());
				// splitQuantity+=$("#splitQuantity"+mmfId).val()+",";
				countMoney+=parseFloat($("#countMoney_"+mmfId).val());
				supplierCount += parseFloat($("#countMoney_"+mmfId).val());
			});
			if(supplierCount ==0){
				$("#express_"+mdSupplier.wzId).html("0.00");
			}else if(noExpressMoney ==0 && expressMoney>0){
				$("#express_"+mdSupplier.wzId).html(toDecimal2(expressMoney));
				countMoney += parseFloat(expressMoney);
			} else if(noExpressMoney >0 && expressMoney > 0 && supplierCount< noExpressMoney){
				$("#express_"+mdSupplier.wzId).html(toDecimal2(expressMoney));
				countMoney += parseFloat(expressMoney);
			}else
				$("#express_"+mdSupplier.wzId).html("0.00");
		}
	}
	mmfIdSums=mmfIdSum;
	wmsIdSums=wmsIdSum
	sumbitShu=countShu;
	numberSums=numberSum;
	// splitQuantitys=splitQuantity;
	$("#allShu").html(countShu);
	$("#allMoney").html(toDecimal2(countMoney));
}
function setFavorites(wmsMiId){
	var isLogin=$("#isLogin").val();
	if(isLogin!="1"){
		$.supper("alert",{ title:"操作提示", msg:"请先登录商城！"});
		return;
	}
	var data="wmsMiId="+wmsMiId;
	$.supper("doservice", {
		"service" : "MdFavoritesService.saveMdFavorites",
		"ifloading" : 1,
		"options":{"type":"post","data":data},
		"BackE" : function(jsondata) {
			if (jsondata.code == "1") {
				layer.msg('成功加入收藏夹！');
				$('#gwcscj').text('加入收藏');
			} else if (jsondata.code == '2') {
				layer.msg('成功移出收藏夹！');
				$('#gwcscj').text('移出收藏');
			} else {
				$.supper("alert", {title: "操作提示", msg: "操作失败！"});
			}
		}
	});
}
function shure(){
	var isLogin=$("#isLogin").val();
	if(isLogin!="1"){
		//$.supper("alert",{ title:"操作提示", msg:"请先登录商城！"});
		fLogin.openLogin();
		return;
	}
	var mmfIds="";
	var shus = "";
	if(_dataList != null && _dataList.length > 0){
		var canOder = true;
		for(var i = 0;i < _dataList.length;i++){
			var mdSupplier = _dataList[i].mdSupplier;
			$("input:checkbox[name='box_"+mdSupplier.wzId+"']:checked").each(function() { // 遍历name=test的多选框
				var mmfId=$(this).val();  // 每一个被选中项的值
				mmfIds += mmfId+",";
				var shu = $("#countShu_"+mmfId).val();
				if(shu == '' || shu <= 0){
					canOder = false;
					return false;
				}
				shus+=shu+",";
			});
		}
		if(canOder === false){
			$.supper("alert",{ title:"操作提示", msg:"请输入购买数量！"});
			return;
		}
	}

	if(mmfIds==null || mmfIds==""){
		$.supper("alert",{ title:"操作提示", msg:"请选择购买的商品!"});
		return;
	}
	mmfIds = mmfIds.substring(0,mmfIds.length-1);
	shus = shus.substring(0,shus.length-1);
	window.location.href="ddqr.htm?mmfIds="+mmfIds+"&shus="+shus;
}

function exportInfo(){
	var mmfIds="";
	var shus = "";
	var wzIds="";
	var expressMoneys="";
	if(_dataList != null && _dataList.length > 0){
		for(var i = 0;i < _dataList.length;i++){
			var mdSupplier = _dataList[i].mdSupplier;
			$("input:checkbox[name='box_"+mdSupplier.wzId+"']:checked").each(function() { // 遍历name=test的多选框
				var mmfId=$(this).val();  // 每一个被选中项的值
				mmfIds += mmfId+",";
				shus+=$("#countShu_"+mmfId).val()+",";
			});
			var expressMoney=$("#express_"+mdSupplier.wzId).html();
			if(expressMoney > 0){
				wzIds += mdSupplier.wzId+",";
				expressMoneys += expressMoney+",";
			}
		}
	}
	if(mmfIds==null || mmfIds==""){
		$.supper("alert",{ title:"操作提示", msg:"请选择购买的商品!"});
		return;
	}

	mmfIds = mmfIds.substring(0,mmfIds.length-1);
	shus = shus.substring(0,shus.length-1);
	wzIds = wzIds.substring(0,wzIds.length-1);
	expressMoneys = expressMoneys.substring(0,expressMoneys.length-1);
	var data="mmfIds="+mmfIds+"&shus="+shus+"&wzIds="+wzIds+"&expressMoneys="+expressMoneys;
	var newTab=window.open('about:blank');
	$.supper("doservice", {
		"service" : "ShoppingService.exportOrderInfo",
		"ifloading" : 1,
		"options":{"type":"post","data":data},
		"BackE" : function(jsondata) {
			if (jsondata.code == "1") {
				newTab.location.href=jsondata.obj.path;
			} else{
				$.supper("alert",{ title:"操作提示", msg:"操作失败!"});
			}
		}
	});

}

//SubmitDetailed 立即提交清单

function SubmitDetailed() {
	var splitQuantitys1="";
	anumber=mmfIdSums;
	anumber=anumber.substr(0, anumber.length - 1);
	anumber= anumber.split(",");
	var splitQuantity=0;
	for (i=0;i<anumber.length ;i++ )
	{
		 splitQuantity=$("#splitQuantity"+anumber[i]).val();
		 if (splitQuantity!=null&&splitQuantity!=undefined&&splitQuantity!="") {
			 splitQuantitys1+=splitQuantity+",";
		 }else {
			 splitQuantitys1+=0+",";
		 }
	}
	if (mmfIdSums==null|| mmfIdSums==""){
		$.supper("alert",{ title:"操作提示", msg:"购物车里没有商品!"});
		return;
	}
	var remarks=$("#remarks").val();

	var data = 'numberSums=' + numberSums + '&mmfIdSums=' + mmfIdSums+ '&wmsIdSums=' + wmsIdSums+ '&remarks=' + remarks+ '&splitQuantitys1=' + splitQuantitys1;
	$.supper("doservice", {
		"service": "MdOutOrderService.savePicking", 'data': data, "BackE": function (jsondata) {
			// intShu();
            $.supper("alert",{ title:"操作提示", msg:"已提交申领单，可在申领管理中查看！"});
			delSel2();
			var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/myWuliaoCar.jsp"});
			$.supper("setProductArray", {"name": "addEnter", "value": null });
			$.supper("closeTtemWin", {url: att_url});
		}
	});
}
function countCollect1() {
    var vdata='';
    $.supper("doservice", {"service":"MdOutOrderService.countCollect1","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                $("#countCollects").html(jsondata.obj);
            }else{

            }
        }});
}