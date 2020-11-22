var _qgc_find_action = 'MdNoBuyService.getMdNoBuyMapPagerModel';
var _qgc_delect_action = 'MdNoBuyService.delectAllObjectBySuiId';
var _qgc_delect_select_action = 'MdNoBuyService.deleteMdNoBuyBySuiIdsAndMnbIds';
var _scj_search_action = 'MdNoBuyService.searchMdNoBuyBySearch';

var _qgc_;
var _batch_btn_can = false;
var _limit_number = 10;
var _total_count = 0;

$(function(){
	initQhCount();
	findAction('');
	var timer1=window.setTimeout(initQhCount,5*60*1000); 
});
function findAction (searchName){
	$.supper('initPagination',{id:"Pagination",service:_qgc_find_action, "data": searchName,limit:_limit_number,isAjax:"1","BackE":initList});
}
function initList(dataList, totalCount){
	var str = "";
	_total_count = totalCount;
	$('#qigouCout').text(totalCount);
	if(dataList != null && dataList.length > 0){
		for(var i =0;i < dataList.length;i++){
			var data = dataList[i];
			str += "<div id='qgc-info-div" + data.mnb_id + "'><div class=\"order-info\" id='orderIngo" + data.mnb_id + "' data-mnbId='" + data.mnb_id + "' data-mmfId='"+data.mmf_id+"' data-isSelect='false' onclick='selectQgcOrderInfo(" + data.mnb_id + ","+data.mmf_id+")' style='height: 135px;background-color: rgba(192, 192, 192, 0);'><div class=\"left\"><a href=\"xiangxi.htm?wmsMiId="+data.wms_mi_id+"\" target=\"_blank\"><img src=\""+data.lessen_file_path+"\" width=\"68\" height=\"68\"></a>";
			str += "<ul><li class=\"name\"><a href=\"xiangxi.htm?wmsMiId="+data.wms_mi_id+"\" target=\"_blank\">"+data.mat_name+"</a></li>";
			str += "<li class=\"type\">供应商：<strong><a href=\"pcjxiangxi.htm?wzId="+data.wz_id+"\" target=\"_blank\">"+data.applicant_name+"</a></strong></li>";
			str += "<li class=\"type\">型号："+data.mmf_name+"</li>";
			str += '<li class="type">编号：'+data.mmf_code+'</li>';
			str += "</ul></div>";
			str += "<div class=\"right\"><div class=\"money\">￥"+toDecimal2(data.money1)
				+"</div><div class=\"unit\" style=\"margin-left:30px\">"+data.basic_unit+"</div>";
			str +="<div class=\"operation\" style=\"width:100px\"><p class=\"trigger\" onclick=\"addToCar('"+data.mmf_id+"')\"><i class=\"icon-revoke\"></i>加入购物车</p>"
				+"<p class=\"trigger\" onclick=\"delMd('"+data.mnb_id+"')\"><i class=\"icon-cross\"></i>移出弃购车</p></div></div>";
			str += "<div class='checkImg'><img id='checkImg" + data.mnb_id + "' class='checkOrderInfImg' src=\"/dentistmall/img/success.png\" style='display: none;'>";
			str += "<img id='checkImgGray" + data.mnb_id + "' class='checkOrderInfImgGray' src=\"/dentistmall/img/successgray.png\" style='display: none'></div>";
			str +="</div></div>";
		}
	}
	$("#divList").html(str);
	if(totalCount > 0) {
		$('#bacthManageBtn').removeAttr('disabled');
	}else {
		resetBatchQgcManage();
	}
}

function delMd(mnbId){
	var vdata="mnbId="+mnbId;
	$.supper("confirm",{ title:"移出操作", msg:"确认移出弃购车？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdNoBuyService.deleteObject","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert", {
					title: "操作提示", msg: "操作成功！", BackE: function () {
						$('#bacthManageBtn').attr('disabled', 'true');
						refreshQgc(mnbId);
					}
				});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}
function addToCar(mmfId){
	$.supper("doservice", {"service":"MdMaterielFormatService.findFormObject","data":"mmfId="+mmfId, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			addOneOrder(jsondata.obj.mmfId,jsondata.obj.price);
		}
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
function resetBatchQgcManage() {
	_batch_btn_can = false;
	$('#showHideManage').hide();
	$('.checkOrderInfImg').hide();
	$('.checkOrderInfImgGray').hide();
	$('#bacthManageBtn').text('批量管理');
	$(".order-info").attr('style', 'height: 135px;background-color: rgba(192, 192, 192, 0);');
}
function batchManageClick(){
	var display =$('#showHideManage').css('display');
	if(display == 'none') {
		_batch_btn_can = true;
		$('#showHideManage').show();
		$('#bacthManageBtn').text('取消管理');
		$('.checkOrderInfImgGray').show();
		$(".order-info").attr('style', 'height: 135px;background-color: rgba(192, 192, 192, 0.2);');
	}else{
		_batch_btn_can = false;
		$('#showHideManage').hide();
		$('.checkOrderInfImg').hide();
		$('.checkOrderInfImgGray').hide();
		$('#bacthManageBtn').text('批量管理');
		$(".order-info").attr('style', 'height: 135px;background-color: rgba(192, 192, 192, 0);');
	}
}
//清除所有商品
function refreshQgc(refreshmnbIds){
	if(_total_count < _limit_number){
		removeQgcView(refreshmnbIds);
	}else {
		var serachQgcName = 'searchName=' + $('#searchQgcName').val();
		findAction(serachQgcName);
	}
	batchManageClick();
	mnbIds.splice(0, mnbIds.length);
	mmfIds.splice(0, mmfIds.length);
}
function clearQgc(){
	$.supper("confirm", {
		title: "删除操作", msg: "确认删除所有商品？不可恢复！", yesE: function () {
			$.supper("doservice", {
				"service": _qgc_delect_action, "data": '', "BackE": function (jsondata) {
					if (jsondata.code == "1") {
						$.supper("alert", {
							title: "操作提示", msg: "操作成功！", BackE: function () {
								$('#bacthManageBtn').attr('disabled', 'true');
								refreshQgc('all');
							}
						});
					} else
						$.supper("alert", {title: "操作提示", msg: "操作失败！"});
				}
			});
		}
	});
}
var mnbIds = new Array();
var mmfIds = new Array();
//移除元素
function removeQgcView(refreshmnbIds) {
	var divs = $('div[id^=qgc-info-div]')
	if(refreshIds == 'all'){
		divs.each(function () {
			$(this).remove();
		})
		_total_count = 0;
		$('#qigouCout').text(_total_count);
	}else {
		var mfids = refreshIds.split(',');
		mfids.forEach(function (value) {
			$('#qgc-info-div' + value).remove();
			_total_count --;
			$('#qigouCout').text(_total_count);
		});
	}
	if(_total_count > 0) {
		$('#bacthManageBtn').removeAttr('disabled');
	}else{
		$('#bacthManageBtn').attr('disabled');
	}
	resetBatchScjManage();
}
//选择
function selectQgcOrderInfo(mnb_id, mmf_id){
	if(_batch_btn_can === false){
		return;
	}
	var display =$('#checkImg' + mnb_id).css('display');
	if(display == 'none') {
		mnbIds.push(mnb_id);
		mmfIds.push(mmf_id);
		$('#checkImg' + mnb_id).show();
		$('#checkImgGray' + mnb_id).hide();
		$('#orderIngo' + mnb_id).attr('data-isSelect', true);
	}else{
		var mnbIndex = mnbIds.findIndex(function (item) {
			return item == mnb_id;
		})
		mnbIds.splice(mnbIndex, 1);

		var mmfIndex = mmfIds.findIndex(function (item) {
			return item == mmf_id;
		})
		mmfIds.splice(mmfIndex, 1);
		$('#checkImg' + mnb_id).hide();
		$('#checkImgGray' + mnb_id).show();
		$('#orderIngo' + mnb_id).attr('data-isSelect', false);
	}
}
//选择所有
var isSelectALl = false;
function selectAllMatsExtend(){
	var sQQI = this.selectQgcOrderInfo;
	$('.order-info').each(function (index, item) {
		var data_isSelect =$(this).attr('data-isSelect');
		if(data_isSelect == 'false') {
			sQQI($(this).attr('data-mnbId'), $(this).attr('data-mmfId'));
		} else if(isSelectALl === false){
			sQQI($(this).attr('data-mnbId'), $(this).attr('data-mmfId'));
		}
	})
}
function selectAllMats(){
	if(isSelectALl) {
		isSelectALl = false;
		selectAllMatsExtend();
	}else {
		isSelectALl = true;
		selectAllMatsExtend();
	}
}
//删除所选择
function clearSelectMats(){
	if(mnbIds.length <= 0){
		$.supper("alert", {title: "操作提示", msg: "请选择商品！"});
		return;
	}
	var mmbIdsStr = '';
	mnbIds.forEach(function (value) {
		mmbIdsStr += value + ',';
	});
	mmbIdsStr = mmbIdsStr.substring(0, mmbIdsStr.length - 1);
	mmbIdsStr = 'mnbIds=' + mmbIdsStr;
	$.supper("confirm", {
		title: "删除操作", msg: "确认删除商品？不可恢复！", yesE: function () {
			$.supper("doservice", {
				"service": _qgc_delect_select_action, "data": mmbIdsStr, "BackE": function (jsondata) {
					if (jsondata.code == "1") {
						$.supper("alert", {
							title: "操作提示", msg: "操作成功！", BackE: function () {
								$('#bacthManageBtn').attr('disabled', 'true');
								refreshQgc(mmbIdsStr);
							}
						});
					} else
						$.supper("alert", {title: "操作提示", msg: "操作失败！"});
				}
			});
		}
	});
}
//加入购物车
function addCarts(){
	if (isLoging() == false) {
		//$.supper("alert",{ title:"操作提示", msg:"请先登录商城！"});
		fLogin.openLogin();
		return;
	}
	if(mmfIds.length <= 0){
		$.supper("alert", {title: "操作提示", msg: "请选择商品！"});
		return;
	}
	mmfIds.forEach(function (value) {
		addToCar(value);
	});
	mmfIds.splice(0, mmfIds.length);
}
//弃购车查询
function searchQgc() {
	var serachQgcName = 'searchName=' + $('#searchQgcName').val();
	$.supper("initPagination", {
		id: "Pagination",
		"service": _scj_search_action, "data": serachQgcName,limit: _limit_number, isAjax: '1', "BackE": initList
	});

}