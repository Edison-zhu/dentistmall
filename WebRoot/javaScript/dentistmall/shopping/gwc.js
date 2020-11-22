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
	if (isLoging() === true) {
		$.supper("doservice", {
			"service": _find_carts_all_action, "BackE": function (jsondata) {
				var items = jsondata.items;
				if (items.length <= 0) {
					return;
				}
				items.forEach(function (item) {
					mmfIds += item.mmfId + ',';
					shus += item.mcCount + ',';
				})
				if (mmfIds.length > 0) {
					mmfIds = mmfIds.substring(0, mmfIds.length - 1);
				}
				if (shus.length > 0) {
					shus = shus.substring(0, shus.length - 1);
				}
				initMatCartList(mmfIds, shus);
			}
		});
	}
}
function initMatCartList(mmfIds, shus) {
	if(mmfIds != null && mmfIds!= ""){
		var vdata = "mmfIds="+mmfIds+"&shus="+shus;
		var str = "";
		$.supper("doservice", {"service":"ShoppingService.getCarMatInfo","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				_dataList = jsondata.obj;
				if(_dataList != null && _dataList.length > 0){
					for(var i = 0;i < _dataList.length;i++){
						var mdSupplier = _dataList[i].mdSupplier;
						var matList= _dataList[i].matList;
						str += "<div class=\"order\" id=\"order_"+mdSupplier.wzId+"\"><div class=\"order-header\">";
						if(mdSupplier.state=='1')
							str +="<input id=\"supplier_"+mdSupplier.wzId+"\" type=\"checkbox\" checked=true onclick=\"clickSupplier('"+mdSupplier.wzId+"')\" value=\"\"  />";
						else
							str +="<input name=\"\" type=\"checkbox\" value=\"\" disabled=disabled />";
						if(mdSupplier.state!='1'){
							str +="<label for=\"supplier-1\">供应商：<span class=\"supplier font-weight-trigger\">"+mdSupplier.applicantName+"</span></label>";
							str+="<p class=\"sale\"><span>该供应商已下架</span> </p>";
						}else{
							str +="<label for=\"supplier-1\">供应商：<a class=\"supplier font-weight-trigger\" href=\"pcjxiangxi.htm?wzId="+mdSupplier.wzId+"\" target=\"_blank\">"+mdSupplier.applicantName+"</a></label>";
							var noExpressMoney = mdSupplier.noExpressMoney!=null?mdSupplier.noExpressMoney:0;
							var expressMoney = mdSupplier.expressMoney!=null?mdSupplier.expressMoney:0;
							str +="<p class=\"sale\">";
							if(noExpressMoney > 0 && expressMoney>0){
								str +="满<strong class=\"red\">"+toDecimal2(noExpressMoney)+"</strong>，免运费<span>";
								str +="<span class=\"l20\">运费：<strong class=\"red\" id=\"express_"+mdSupplier.wzId+"\">0.00</strong></span>";
							}else{
								str +="<span class=\"l20\">运费：<strong class=\"red\" id=\"express_"+mdSupplier.wzId+"\">0.00</strong></span>";
							}
							str +="</p>";
						}
						str+="</div>";
						str +="<div class=\"order-body\">";
						if(matList != null && matList.length>0){
							for(var j = 0;j < matList.length;j++){
								var mat = matList[j];
								str+="<div class=\"order-info\" id=\"info_"+mat.mmfId+"\"><div class=\"left\">";
								if(mdSupplier.state=='1' && mat.state=='1' && mat.mmfState=='1'){//判断该商家是否下架，判断该商品是否下架
									str+="<input name=\"box_"+mdSupplier.wzId+"\" onclick=\"clickMat(this,'"+mdSupplier.wzId+"')\" checked=true type=\"checkbox\"  value=\""+mat.mmfId+"\" />";
									str +="<a href=\"xiangxi.htm?wmsMiId="+mat.wmsMiId+"\" target=\"_blank\"><img src=\""+mat.lessenFilePath+"\" width=\"68\" height=\"68\"></a><ul>";
									str +="<li class=\"name\"><a href=\"xiangxi.htm?wmsMiId="+mat.wmsMiId+"\" target=\"_blank\">"+mat.matName+"</a></li>";
								}else{
									str+="<input  type=\"checkbox\" name=\"box_"+mdSupplier.wzId+"\" disabled=disabled />";
									str +="<a href=\"xiangxi.htm?wmsMiId="+mat.wmsMiId+"\" target=\"_blank\"><img src=\""+mat.lessenFilePath+"\" width=\"68\" height=\"68\"></a><ul>";
									str +="<li class=\"name\">"+mat.matName+"</li>";
								}
									
								
								str +="<li class=\"type\">型号："+mat.mmfName+"</li>";
								str += '<li class="type">编号：'+mat.mmfCode+'</li>';
								str +="</ul></div><div class=\"right\">";
								if(mdSupplier.state=='1' && mat.state=='1' && mat.mmfState=='1'){
									str +="<div class=\"money\">￥"+toDecimal2(mat.price)+"</div><div class=\"number\"><span>";
									str += "<a href=\"javascript:minusShu('"+mat.mmfId+"')\" class=\"a-left\" title=\"减1\" >-</a>";
									str +="<input  type=\"text\" id=\"countShu_"+mat.mmfId+"\" class=\"\" value=\""+mat.countShu+"\" "
										+"onfocus='this.select()' onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');saveShu('"+mat.mmfId+"')\" onblur=\"this.value=this.value.replace(/[^0-9]/g,'');saveShu('"+mat.mmfId+"')\" min='1'  maxlength=\"4\" title=\"请输入购买量\">";
									str +="<a href=\"javascript:addShu('"+mat.mmfId+"')\" class=\"a-right\" title=\"加1\" >+</a></span></div>";
									str +="<div class=\"unit\">"+mat.basicUnit+"</div>";
									str +="<div class=\"operation\"><p class=\"trigger\" onclick=\"setFavorites('"+mat.wmsMiId+"')\"><i class=\"icon-revoke\"></i><span id='gwcscj'>加入收藏</span></p>"
										+"<p class=\"trigger\" onclick=\"delMat('"+mat.mmfId+"','"+mat.wzId+"')\"><i class=\"icon-cross\"></i>删除商品</p></div></div>";
									str += "<input type='hidden' id=\"countMoney_"+mat.mmfId+"\" value=\""+mat.countMoney+"\">";
									str += "<input type='hidden' id=\"price_"+mat.mmfId+"\" value=\""+mat.price+"\">";
								}else{
									str+="<div class=\"money\">商品已下架</div><div class=\"number\">--</div><div class=\"unit\">--</div>";
									str +="<div class=\"operation\"><p class=\"trigger\" onclick=\"delMat('"+mat.mmfId+"','"+mat.wzId+"')\"><i class=\"icon-cross\"></i>删除商品</p>";
									str +="</div></div>";
								}
								str +="</div>";
							}
						}
						str +="</div></div>";
					}
				}
				$("#gwcList").html(str);
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
	if(!$(obj).is(":checked")){//没有选中
		if(_dataList != null && _dataList.length > 0){
			for(var i=0;i < _dataList.length;i++){
				var mdSupplier = _dataList[i].mdSupplier;
				if(mdSupplier.state=='1'){
					$("#supplier_"+mdSupplier.wzId).prop("checked", false);
					$("[name = 'box_"+mdSupplier.wzId+"']:checkbox").prop("checked", false);
				}
			}
		}
		$("#all1").prop("checked", false);
		$("#all2").prop("checked", false);
	}else{
		if(_dataList != null && _dataList.length > 0){
			for(var i=0;i < _dataList.length;i++){
				var mdSupplier = _dataList[i].mdSupplier;
				if(mdSupplier.state=='1'){
					$("#supplier_"+mdSupplier.wzId).prop("checked", true);
					$("[name = 'box_"+mdSupplier.wzId+"']:checkbox").prop("checked", true);
				}
			}
		}
		$("#all1").prop("checked", true);
		$("#all2").prop("checked", true);
	}
	intShu();
}

function delMat(mmfId,wzId){
	var data = 'mmfIds='+mmfId;
	$.supper("doservice", {
		"service": _delete_carts_action, 'data': data, "BackE": function (jsondata) {
			if(jsondata.code == '1'){
				$("#info_"+mmfId).remove();
				var boxs = $("input[name='box_"+wzId+"']");
				if(boxs==null || boxs.length==0)
					$("#order_"+wzId).remove();
				intShu();
			}
		}
	});
}

function delSel(){
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
function intShu(){
	var countShu=0;
	var countMoney=0;
	if(_dataList != null && _dataList.length > 0){
		for(var i = 0;i < _dataList.length;i++){
			var mdSupplier = _dataList[i].mdSupplier;
			var noExpressMoney = mdSupplier.noExpressMoney!=null?mdSupplier.noExpressMoney:0;
			var expressMoney = mdSupplier.expressMoney!=null?mdSupplier.expressMoney:0;
			var supplierCount=0;
			$("input:checkbox[name='box_"+mdSupplier.wzId+"']:checked").each(function() { // 遍历name=test的多选框
			  	var mmfId=$(this).val();  // 每一个被选中项的值
			  	countShu+=parseInt($("#countShu_"+mmfId).val());
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