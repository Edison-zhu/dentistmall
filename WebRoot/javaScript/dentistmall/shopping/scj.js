var _scj_find_action = 'MdFavoritesService.getMdFavoritesMapPagerModel';
var _scj_delect_action = 'MdFavoritesService.delectAllObjectBySuiId';
var _scj_delect_select_action = 'MdFavoritesService.deleteMdFavoritesBySuiIdsAndMfIds';
var _scj_search_action = 'MdFavoritesService.searchMdFavoritesBySearch';
var _scj_find_actions = 'shoppingService.getPurchasedInfo';
var sort;
var _batch_btn_can = false;
var _limit_number = 20;
var _total_count = 0;
var dataListes;
$(function(){
	initQhCount();
	findScjAction('');
	var timer1=window.setTimeout(initQhCount,5*60*1000); 
	
	//新增回车查询 2019-12-10 yanglei
	 $("#searchScjName").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#searchScjBtn").trigger("click");
		  }
	});
});
function findScjAction(searchName){
	$.supper('initPagination',{id:"Pagination",service: _scj_find_action, "data": searchName, limit: _limit_number,isAjax:"1","BackE":initList});
}

function findByedAction(searchName,sort){
	$('.hd').hide()
	$('.hds').show()
	sort = sort
	if (searchName == undefined){
		searchName = ''
	}
	if (sort == undefined){
		searchName = ''
	}
	let data = '';
	data += 'date1=' + sort;
	data += '&matNameAndCode=' +searchName
	$.supper('initPagination',{id:"Pagination",service: _scj_find_actions, "data": data,limit: _limit_number,isAjax:"1","BackE":buyGoods});
}
function initList(dataList, totalCount){
	var str = "";
	_total_count = totalCount;
	$('#scjCount').text(totalCount);
	if(dataList != null && dataList.length > 0){
		for(var i =0;i < dataList.length;i++){
			var data = dataList[i];
			str += "<div id='order-info-div" + data.mf_id + "'><div class=\"order-info\" id='orderIngo" + data.mf_id + "' data-mfId='" + data.mf_id + "' data-isSelect='false' onclick='selectScjOrderInfo(" + data.mf_id + ")' style='height: 135px;background-color: rgba(192, 192, 192, 0);' class=\"order-info\"><div class=\"left\"><a href=\"xiangxi.htm?wmsMiId="+data.wms_mi_id+"\" target=\"_blank\"><img src=\""+data.lessen_file_path+"\" width=\"68\" height=\"68\"></a>";
			str += "<ul><li class=\"name\"><a href=\"xiangxi.htm?wmsMiId="+data.wms_mi_id+"\" target=\"_blank\">"+data.mat_name+"</a></li>";
			str += "<li class=\"type\">供应商：<strong><a href=\"pcjxiangxi.htm?wzId="+data.wz_id+"\" target=\"_blank\">"+data.applicant_name+"</a></strong></li>";
			str +="<li class=\"type\">型号："+(data.mmf_name !== undefined ? data.mmf_name : '')+"</li>";
			str += '<li class="type">编号：'+(data.mmf_code !== undefined ? data.mmf_code : '')+'</li>';
			str += "</ul></div>";
			str += "<div class=\"right\"><div class=\"money\">￥"+toDecimal2(data.money1)
				+"</div><div class=\"unit\">"+data.basic_unit+"</div>";
			str += "<div class=\"unit\"><a href=\"javascript:delFavorites('"+data.mf_id+"')\" class=\"btn\">移出收藏夹</a></div></div>";
			str += "<div class='checkscjImg'><img id='checkscjImg" + data.mf_id + "' class='checkscjOrderInfImg' src=\"/dentistmall/img/success.png\" style='display: none;'>";
			str += "<img id='checkscjImgGray" + data.mf_id + "' class='checkscjOrderInfImgGray' src=\"/dentistmall/img/successgray.png\" style='display: none'></div>";
			str +="</div></div>";
		}
	}
	$("#divList").html(str);
	if(totalCount > 0) {
		$('#bacthscjManageBtn').removeAttr('disabled');
	} else {
		resetBatchScjManage();
	}
}
function buyGoods(dataList, totalCount){
	 // _dataLists = jsondata.obj;
	var str = "";
	_total_count = totalCount;
	$('#scjCount').text(totalCount);
	if(dataList != null && dataList.length > 0){
		for(var i =0;i < dataList.length;i++){
			var data = dataList[i];
			str +="<input id=\"supplier_"+data.mf_id+"\" type=\"checkbox\" checked=true onclick=\"clickSupplier('"+data.mf_id+"')\" value=\"\"  />";
			str += "<div id='order-info-div" + data.mf_id + "'><div class=\"order-info\" id='orderIngo" + data.mf_id + "' data-mfId='" + data.mf_id + "' data-isSelect='false' onclick='selectScjOrderInfo(" + data.mf_id + ")' style='height: 135px;background-color: rgba(192, 192, 192, 0);' class=\"order-info\"><div class=\"left\"><a href=\"xiangxi.htm?wmsMiId="+data.wms_mi_id+"\" target=\"_blank\"><img src=\""+data.lessenFilecode+"\" width=\"68\" height=\"68\"></a>";
			str += "<ul><li class=\"name\"><a href=\"xiangxi.htm?wmsMiId="+data.wms_mi_id+"\" target=\"_blank\">"+data.mat_name+"</a></li>";
			str +="<li class=\"type\">型号："+(data.mmf_name !== undefined ? data.mmf_name : '')+"</li>";
			str += '<li class="type">编号：'+(data.mmf_code !== undefined ? data.mmf_code : '')+'</li>';
			str += "</ul></div>";
			str += "<div class=\"right\"><div class=\"money\" style='margin-left: -14px'>￥"+toDecimal2(data.price)
				+"</div><div class=\"unit\" style='margin-left: -47px'>"+data.basic_unit+"</div>";
			str += "<a href=\"javascript:minusShu('"+data.wms_mi_id+"')\"  style='background-color: #ddd' class=\"a-left\" title=\"减1\" >-</a>";
			str +="<input  type=\"text\" style='width: 50px' id=\"countShu_"+data.wms_mi_id+"\" class=\"\" value=\""+1+"\" "
				+"onfocus='this.select()' onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');saveShu('"+data.wms_mi_id+"')\" onblur=\"this.value=this.value.replace(/[^0-9]/g,'');saveShu('"+data.wms_mi_id+"')\" min='1'  maxlength=\"4\" title=\"请输入购买量\">";
			str +="<a href=\"javascript:addShu('"+data.wms_mi_id+"')\" class=\"a-right\" title=\"加1\" >+</a></span></div>";
			str +="</div></div>";

		}
	}
	$("#divList").html(str);
	if(totalCount > 0) {
		$('#bacthscjManageBtn').removeAttr('disabled');
	} else {
		resetBatchScjManage();
	}
	// $('#divList').hide()

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
function clickSupplier(wzId){
	if(!$("#supplier_"+wzId).is(":checked")){
		$("[name = 'box_"+wzId+"']:checkbox").prop("checked", false);
	}else{
		$("[name = 'box_"+wzId+"']:checkbox").prop("checked",true);
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
function bySort(){
	console.log("排序")
	if (sort == 1){
		sort = 2
		findByedAction('',sort)
		return
	}else if (sort == 2){
		sort = 1
		findByedAction('',sort)
		return
	}

}
function myFavouter(){

}
function delFavorites(mfId){
	var vdata="mfId="+mfId;
	$.supper("confirm",{ title:"移出操作", msg:"确认移出收藏夹？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdFavoritesService.deleteObject","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert", {
					title: "操作提示", msg: "操作成功！", BackE: function () {
						$('#bacthscjManageBtn').attr('disabled', 'true');
						refreshScj(mfId);
					}
				});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}
function reloadData(){
    $.supper('paginationReload',{"data":"", id: 'Pagination'});
}
function initQhCount(){
	$.supper("doservice", {"service" : "MdNewsInfoService.getInventoryNewList","BackE" : function(jsondata) {
			if (jsondata.code == "1") {
				if(jsondata.obj != null){
					if(jsondata.obj.length >0)
						$("#newsCount").html(jsondata.obj.length);
					else
						$("#newsCount").html("");
				}
			}
		}
	});
}
//20191129 yangfeng 新增全选批量操作等功能
//批量管理操作
function resetBatchScjManage() {
	_batch_btn_can = false;
	$('#showHideManage').hide();
	$('.checkscjOrderInfImg').hide();
	$('.checkscjOrderInfImgGray').hide();
	$('#bacthscjManageBtn').text('批量管理');
	$(".order-info").attr('style', 'height: 135px;background-color: rgba(192, 192, 192, 0);');
}
function batchscjManageClick(){
	var display =$('#showHideManage').css('display');
	if(display == 'none') {
		_batch_btn_can = true;
		$('#showHideManage').show();
		$('#bacthscjManageBtn').text('取消管理');
		$('.checkscjOrderInfImgGray').show();
		$(".order-info").attr('style', 'height: 135px;background-color: rgba(192, 192, 192, 0.2);');
	}else{
		_batch_btn_can = false;
		$('#showHideManage').hide();
		$('.checkscjOrderInfImg').hide();
		$('.checkscjOrderInfImgGray').hide();
		$('#bacthscjManageBtn').text('批量管理');
		$(".order-info").attr('style', 'height: 135px;background-color: rgba(192, 192, 192, 0);');
	}
}
//清除所有商品
function refreshScj(refreshIds){
	if(_total_count < _limit_number){
		removeScjView(refreshIds);
	}else {
		var serachScjName = 'searchName=' + $('#searchScjName').val();
		findScjAction(serachScjName);
		batchscjManageClick();
	}
	mfIds.splice(0, mfIds.length);
}
function clearQgc(){
	$.supper("confirm", {
		title: "删除操作", msg: "确认删除所有商品？不可恢复！", yesE: function () {
			$.supper("doservice", {
				"service": _scj_delect_action, "data": '', "BackE": function (jsondata) {
					if (jsondata.code == "1") {
						$.supper("alert", {
							title: "操作提示", msg: "操作成功！", BackE: function () {
								$('#bacthscjManageBtn').attr('disabled', 'true');
								refreshScj('all');
							}
						});
					} else
						$.supper("alert", {title: "操作提示", msg: "操作失败！"});
				}
			});
		}
	});
}
var mfIds = new Array();
//移除元素
function removeScjView(refreshIds) {
	var divs = $('div[id^=order-info-div]')
	if(refreshIds == 'all'){
		divs.each(function () {
			$(this).remove();
		})
		_total_count = 0;
		$('#scjCount').text(_total_count);
	}else {
		var mfids = refreshIds.split(',');
		mfids.forEach(function (value) {
			$('#order-info-div' + value).remove();
			_total_count --;
			$('#scjCount').text(_total_count);
		});
	}
	if(_total_count > 0) {
		$('#bacthscjManageBtn').removeAttr('disabled');
	}else{
		$('#bacthscjManageBtn').attr('disabled');
	}
	resetBatchScjManage();
}
//选择
function selectScjOrderInfo(mf_id){
	if(_batch_btn_can === false){
		return;
	}
	var display =$('#checkscjImg' + mf_id).css('display');
	if(display == 'none') {
		mfIds.push(mf_id);
		$('#checkscjImg' + mf_id).show();
		$('#checkscjImgGray' + mf_id).hide();
		$('#orderIngo' + mf_id).attr('data-isSelect', true);
	}else{
		var mnbIndex = mfIds.findIndex(function (item) {
			return item == mf_id;
		})
		mfIds.splice(mnbIndex, 1);

		$('#checkscjImg' + mf_id).hide();
		$('#checkscjImgGray' + mf_id).show();
		$('#orderIngo' + mf_id).attr('data-isSelect', false);
	}
}
//选择所有
var isSelectALl = false;
function selectscjAllMatsExtend(){
	var sQQI = this.selectScjOrderInfo;
	$('.order-info').each(function (index, item) {
		var data_isSelect =$(this).attr('data-isSelect');
		if(data_isSelect == 'false') {
			sQQI($(this).attr('data-mfId'));
		} else if(isSelectALl === false){
			sQQI($(this).attr('data-mfId'));
		}
	})
}
function selectscjAllMats(){
	if(isSelectALl) {
		isSelectALl = false;
		selectscjAllMatsExtend();
	}else {
		isSelectALl = true;
		selectscjAllMatsExtend();
	}
}
//删除所选择
function clearscjSelectMats(){
	if(mfIds.length <= 0){
		$.supper("alert", {title: "操作提示", msg: "请选择商品！"});
		return;
	}
	var mfIdsStr = '';
	mfIds.forEach(function (value) {
		mfIdsStr += value + ',';
	});
	mfIdsStr = mfIdsStr.substring(0, mfIdsStr.length - 1);
	var refreshIds = mfIdsStr;
	mfIdsStr = 'mfIds=' + mfIdsStr;
	$.supper("confirm", {
		title: "删除操作", msg: "确认删除商品？不可恢复！", yesE: function () {
			$.supper("doservice", {
				"service": _scj_delect_select_action, "data": mfIdsStr, "BackE": function (jsondata) {
					if (jsondata.code == "1") {
						$.supper("alert", {
							title: "操作提示", msg: "操作成功！", BackE: function () {
								$('#bacthscjManageBtn').attr('disabled', 'true');
								refreshScj(refreshIds);
							}
						});
					} else
						$.supper("alert", {title: "操作提示", msg: "操作失败！"});
				}
			});
		}
	});
}
//收藏夹查询
function searchScj() {
	var serachScjName = 'searchName=' + $('#searchScjName').val();
	$.supper("initPagination", {
		id: "Pagination",
		"service": _scj_search_action, "data": serachScjName,limit:_limit_number , isAjax: '1', "BackE": initList
	});

}