var _scj_find_action = 'MdOutOrderService.getCollectList';
var _scj_delect_action = 'MdOutOrderService.delectAllObjectBySuiId';
var _scj_delect_select_action = 'MdFavoritesService.deleteMdCollectBySuiIdsAndMfIds';
var _scj_delect_select_action1 = 'MdFavoritesService.deleteMdCollectBySuiIdsAndMcIds';
var _scj_search_action = 'MdOutOrderService.searchMdFavoritesBySearch';
var _batch_btn_can = false;
var _limit_number = 20;
var _total_count = 0;
var  mmtId1="";
var _mdpId;
var _mdpsId;
var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
var treeClickLevel = 0;

function treeJump(mdpId, mdpsId) {
	console.log('bb', mdpId, mdpsId)
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPicking.jsp"});
	$.supper("setProductArray", {"name":"treeJumps", "value":{_mdpId:mdpId,_mdpsId:mdpsId}});
	$.supper("showTtemWin",{ "url":att_url,"title":"新增领料"});
}

var loadItemZtree = function () {
	let data = '';
	if (_mdpId != undefined) {
		data += 'mdpId=' + _mdpId;
	}
	if (_mdpsId != undefined) {
		data += '&mdpsId=' + _mdpsId;
	}
	//设置树形数据开始
	var setting = {
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pId",
				tags: "tags",
				isParent: "isParent"
			}
		},
		async: {
			enable: true,
			url: $.supper("getServicePath", {
				"service": _all_SiteFirst,
				"data": data,
				autoParam: ["id"],
			}),  //获取异步数据的地址
			autoParam: ["id"],
			dataFilter: filter //设置数据的展现形式
		},
		callback: {//增加点击事件
			beforeClick: function (treeId, treeNode) {
				lastExpandNode = treeNode;//记录当前点击的节点
				treeClickLevel = treeNode.level;
				_mdpId = undefined;
				_mdpsId = undefined;
				if (treeNode.level == 0) {
					_mdpId = treeNode.id;
					console.log("+++"+treeNode.id)
				} else if(treeNode.level == 1){
					_mdpsId = treeNode.id;
				} else {
					_mdpId = undefined;
					_mdpsId = undefined;
				}
				console.log('aa', _mdpId, _mdpsId)
				treeJump(_mdpId, _mdpsId);
			}
		}

	}

	//设置树的初始数据
	var zNodes = [
		// {id: 0, pId: "", name: "类别列表", isParent: true}
	];
	$.fn.zTree.init($("#tree"), setting, zNodes);
	//自动展现第一层树
	var zTree = $.fn.zTree.getZTreeObj("tree");
	var node = zTree.getNodesByParam("id","0");
	lastExpandNode=node;
	zTree.expandNode(node[0],  true, false, false);

}

function initTree() {
	let data = '';
	if (_mdpId != undefined) {
		data += 'mdpId=' + _mdpId;
	}
	if (_mdpsId != undefined) {
		data += '&mdpsId=' + _mdpsId;
	}
	//设置树的初始数据
	$.supper("doservice", {
		"service": _all_SiteFirst, 'data': data, "BackE": function (jsondata) {
			$.fn.zTree.init($("#roleTree"), rolSetting, jsondata);
			//设置树形数据结束
			$("#roleTree").css("height", $(window).height() - 120);
		}
	});
}

//设置数据的展现形式
function filter(treeId, parentNode, childNodes) {
	if (!childNodes)
		return null;
	for (var i = 0, l = childNodes.length; i < l; i++) {
		if (childNodes[i].name !== undefined)
			childNodes[i].name = childNodes[i].name.replace('', '');
	}
	return childNodes;
}


//--------------------------------tree---------------------------

//当增加树的数据后刷新当前节点
function loadAddTree() {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if (lastExpandNode != null)//刷新当前节点
		zTree.reAsyncChildNodes(lastExpandNode, "refresh");
	searchName2();
}

//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree() {
	var zTree = $.fn.zTree.getZTreeObj("tree");
	if (lastExpandNode != null) {
		var zTree = $.fn.zTree.getZTreeObj("tree");
		if (lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
			zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");
	}
	searchName2();
}
$(function(){
	initQhCount();
	findScjAction();
	var timer1=window.setTimeout(initQhCount,5*60*1000);

	//新增回车查询 2019-12-10 yanglei
	 $("#searchScjName").on('keydown', function(){
		  if (event.keyCode==13) {
		  	console.log("111")
			  $("#searchScjBtns").trigger("click");
		  }
	});
	loadItemZtree();
});
function findScjAction(searchName){
	$.supper('initPagination',{id:"Pagination",service: _scj_find_action, "data": searchName, limit: _limit_number,isAjax:"1","BackE":initList});
}
var wmsIds="";
var mmfIds1="";
var wmms = {};
var micId1='';
var wmsIdcheck="";
var mmfIdCheck="";
function initList(dataList, totalCount){
	var str = "";
	_total_count = totalCount;
	// alert(totalCount);
	$('#scjCount').text(totalCount);
	var wmsMiId2;
	if(dataList != null && dataList.length > 0){
		for(var i =0;i < dataList.length;i++){
			var data = dataList[i];
			wmsIds+=data.wmsMiId+",";
			mmfIds1+=data.mmfId+",";
			wmsMiId2=data.wmsMiId;
			// micId1+=data.micId+",";
			var micId2=data.micId;
			if (micId1.indexOf(micId2) < 0) {
				micId1+=micId2+",";
			}
			
			if (wmsIdcheck.indexOf(data.wmsMiId)<0){
				wmsIdcheck+=data.wmsMiId+",";
			}else {
				if (mmfIdCheck.indexOf(data.mmfId)<0) {
					wmsIdcheck+=data.wmsMiId+",";
				}
			}

			if (mmfIdCheck.indexOf(data.mmfId)<0){
				mmfIdCheck+=data.mmfId+",";
			}
			
			if (wmms[wmsMiId2] == undefined)
				wmms[wmsMiId2] = {};
			wmms[wmsMiId2].item = data;
			// alert(wmms[wmsMiId2].item);
			str += "<div id='order-info-div'><div class=\"order-info\" id='orderIngo" + data.mmfId + "' data-mfId='" + data.mmfId + "' data-isSelect='false' onclick='selectScjOrderInfo(" + data.mmfId + ")' style='height: 155px;background-color: rgba(192, 192, 192, 0);' class=\"order-info\"><div style='width: 45%'><div style='float:left;margin-left: 20px;width:68px;height: 100%;display: flex;justify-content: center;align-items: center;'><input class='checkbox' name='items' type=\"checkbox\" id='check"+data.micId +"' onclick='checkd(this," + data.micId + "," + data.wmsMiId + "," + data.mmfId + ")' style='margin: 0'/><a href=\"xiangxi.htm?wmsMiId="+data.wmsMiId+"\" target=\"_blank\" style='display: block'><img src=\""+data.lessenFilecode+"\" width=\"68\" height=\"68\"></a></div>";
			str += "<ul style='display: inline-block;margin-left: 20px;line-height: 28px;padding-top: 5px'><li class=\"name\"><a onclick='getNormMx("+data.wmsMiId+")'style='color:#1E90FF;cursor:pointer;'>"+data.matName+"</a></li>";
			str += "<li class=\"type\" style='text-align: left;margin-top: 0px'>品牌：<strong><a target=\"_blank\">"+data.brand+"</a></strong></li>";
			str += "<li class=\"type\">规格型号：<strong><a target=\"_blank\">"+data.mmfName+"</a></strong></li>";
			str +="<li class=\"type\">物料编号："+(data.matCode !== undefined ? data.matCode : '')+"</li>";
			str += '<li class="\type\" style=\'text-align: left\'>包装方式：'+(data.product_name !== undefined ? data.product_name : '')+'</li>';
			str += "</ul></div>";
			str += "<div style='width: 15%;text-align: center;line-height: 155px'>"+(data.basicUnit !== undefined ? data.basicUnit : '')
				+"</div><div style='width: 15%;text-align: center;line-height: 155px'>"+data.brand+"</div><div style='width: 15%;text-align: center;line-height: 155px'>"+data.product_name+"</div>";
			str+="<div style='width: 5%;text-align: right;line-height: 155px'><a onclick='Editalias("+wmsMiId2+")'><img style=\" width: 25px;height: 23px\"\n" +
				"                                 src=\"../../../../dentistmall/css/shopping/images/edit1.png\"></div></a>"
			str += "<div style='width: 5%;text-align: left;line-height: 155px'><a href=\"javascript:delFavorites('"+data.micId+"')\" class=\"btn\" style='color:#1E90FF;text-decoration: underline;'>移除</a></div>";
			str += "<div class='checkscjImg' style='margin-top: 113px;right: 3px'><img id='checkscjImg" + data.mmfId + "' class='checkscjOrderInfImg' src=\"/dentistmall/img/success.png\" style='display: none;'>";
			str += "<img id='checkscjImgGray" + data.mmfId + "' class='checkscjOrderInfImgGray' src=\"/dentistmall/img/successgray.png\" style='display: none'></div>";
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

function delFavorites(micId){
	var vdata="micId="+micId;
	$.supper("confirm",{ title:"移出操作", msg:"确认移出收藏夹？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdOutOrderService.deleteMicId",
			"data":vdata,
			"BackE":function (jsondata) {
			if (jsondata.code == "1") {
				findScjAction();
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


//yanglei  0903 增加复选框选中
$("#all").click(function() {
	$("[name=items]:checkbox").prop("checked", this.checked);
	$("#all").prop("checked", this.checked);
});

var micIds='';
var mmfIdChecks='';
var wmsIdchecks='';
function exportItem(selector,micId){
	if(selector.checked === true){
		micIds=micId1;
		mmfIdChecks=mmfIdCheck;
		wmsIdchecks=wmsIdcheck;
	}else {
		micIds='';
		mmfIdChecks='';
		wmsIdchecks='';
	}
}

function checkd(selector1,micId,wmsMiId,mmfId) {
	if ($(":checkbox[name='items']").length== $(":checkbox[name='items']:checked").length ){
		$('#all').prop('checked', true);
	}else {
		$('#all').prop('checked', false);
	}
	if (micIds.indexOf(micId) >= 0) {
		micIds = micIds.replace(micId + ',', '');
		wmsIdchecks=wmsIdchecks.replace(wmsMiId + ',', '');
		mmfIdChecks=mmfIdChecks.replace(mmfId + ',', '');
	} else {
		micIds += micId + ',';
		wmsIdchecks+= wmsMiId + ',';
		mmfIdChecks+= mmfId + ',';
	}
}

//删除所选择
function clearscjSelectMats1(){
	if(micIds.length <= 0){
		$.supper("alert", {title: "操作提示", msg: "请选择商品！"});
		return;
	}
	var mfIdsStr = '';
	micIds = micIds.substring(0, micIds.length - 1);
	mfIdsStr = 'micIds=' + micIds;
	$.supper("confirm", {
		title: "删除操作", msg: "移除商品后无法恢复，是否确定操作？", yesE: function () {
			$.supper("doservice", {
				"service": _scj_delect_select_action1, "data": mfIdsStr, "BackE": function (jsondata) {
					if (jsondata.code == "1") {
						const BTN2 = document.getElementById('searchScjBtns');
						BTN2.onclick();
					} else
						$.supper("alert", {title: "操作提示", msg: "操作失败！"});
				}
			});
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
		$('#bacthscjManageBtn').text('取消批量管理');
		$('.checkscjOrderInfImgGray').show();
		$(".order-info").attr('style', 'height: 155px;background-color: rgba(192, 192, 192, 0.2);');
	}else{
		_batch_btn_can = false;
		$('#showHideManage').hide();
		$('.checkscjOrderInfImg').hide();
		$('.checkscjOrderInfImgGray').hide();
		$('#bacthscjManageBtn').text('批量管理');
		$(".order-info").attr('style', 'height: 155px;background-color: rgba(192, 192, 192, 0);');
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
		title: "删除操作", msg: "移除商品后无法恢复，是否确定操作？", yesE: function () {
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
		title: "删除操作", msg: "移除商品后无法恢复，是否确定操作？", yesE: function () {
			$.supper("doservice", {
				"service": _scj_delect_select_action, "data": mfIdsStr, "BackE": function (jsondata) {
					if (jsondata.code == "1") {
						const BTN2 = document.getElementById('searchScjBtns');
						BTN2.onclick();
						// $.supper("alert", {
						// 	title: "操作提示", msg: "操作成功！", BackE: function () {
						// 		$('#bacthscjManageBtn').attr('disabled', 'true');
						// 		refreshScj(refreshIds);
						// 	}
						// });
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

//一键加入物料清单
// function  oneClickJoin(){
// 	var vdata='&wmsIds='+wmsIds+'&mmfIds1='+mmfIds1
// 			$.supper("doservice", {
// 				"service": "MdOutOrderService.oneClickJoin", "data": 'vdata', "BackE": function (jsondata) {
// 					if (jsondata.code == "1") {
// 						// $.supper("alert", {
// 						// 	title: "操作提示", msg: "操作成功！", BackE: function () {
// 						// 		$('#bacthscjManageBtn').attr('disabled', 'true');
// 						// 		refreshScj('all');
// 						// 	}
// 						// });
// 					} else
// 						$.supper("alert", {title: "操作提示", msg: "操作失败！"});
// 				}
// 			});
// }
//一键加入物料清单

// var mmfIdChecks='';
// var wmsIdchecks='';
// function oneClickJoin() {//wmsMiId,state
// 	if (mmfIds1==""||mmfIds1==undefined){
// 		$.supper("alert",{ title:"操作提示", msg:"没有加入物料清单的商品！"});
// 		return;
// 	}
// 	var vdata="&wmsMiId="+wmsIds+"&mmfId="+mmfIds1;
// 	$.supper("doservice", {"service":"MdOutOrderService.addCarts2","data":vdata, "BackE":function (jsondata) {
// 			if (jsondata.code == "1") {
// 				$.supper("alert",{ title:"操作提示", msg:"添加到购物车成功！"});
// 				countCollect1();
// 			}else
// 				$.supper("alert",{ title:"操作提示", msg:jsondata.meg});
// 		}});
// }
function oneClickJoin() {//wmsMiId,state
	if (mmfIdChecks==""||mmfIdChecks==undefined){
		$.supper("alert",{ title:"操作提示", msg:"没有加入物料清单的商品！"});
		return;
	}
	var vdata="&wmsMiId="+wmsIdchecks+"&mmfId="+mmfIdChecks;
	// alert(vdata);
	$.supper("doservice", {"service":"MdOutOrderService.addCarts2","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"添加到购物车成功！"});
				countCollect1();
			}else
				$.supper("alert",{ title:"操作提示", msg:jsondata.meg});
		}});
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


//设置别名
var AliasNameWmsMiId;
var aliasNamesNUmber=0;
// var aliasNames;
var matname1;
function Editalias(wmsMiId) {
	var item = wmms[wmsMiId].item;
	$("#Editalias1").show();
	var str = "<div><div style='float: left;'><img style='height: 70px;width: 70px' src=\""+item.lessenFilecode+"\" alt=\"\"></div>" +
		"<div style='float: left;margin-left: 50px;width: 200px;line-height: 30px;'><ul><li>"+item.matName+"</li><li>包装方式："+item.product_name+"</li></ul></div>" +
		"<div style='float: left;margin-left: 50px;width: 200px;line-height: 30px;'><ul><li>单位："+item.basicUnit+"</li><li>库存数量："+(item.sumQuantity!=null?item.sumQuantity:0)+(item.basicUnit !== undefined ? item.basicUnit : '')+(item.splitNumber!=null?item.splitNumber:0)+(item.splitUnit !== undefined ? item.splitUnit : ''+item.basicUnit+'')+"</li></ul></div>" +
		"</div>";
	$("#EditList").html(str);
	AliasNameWmsMiId=item.wmsMiId;
	var aliasNames='';
	// if (item.linkWmsMiId != undefined)
	// 	aliasNames = item.aliasName2 == undefined ? '' : item.aliasName2;
	// else if (item.aliasName3 != undefined)
	// 	aliasNames = item.aliasName3 == undefined ? '' : item.aliasName3;
	// else
		aliasNames = item.aliasName == undefined ? '' : item.aliasName;
	matname1=item.matName;
	// if (aliasNames!=null&&aliasNames!=undefined&&aliasNames!=""){
		// aliasNames= aliasNames.split(",");
		// aliasNamess=aliasNames;
		// var str2="";
		// var str3="<span style=\"margin-left: 40px\">参考别名</span>";
		forList(aliasNames);
		// $("#bmCount").html(str3 + a[2]);
		// $("#bms").html(str2 + a[1]);

	// }else {
	// 	var str3="<span style=\"margin-left: 40px\">参考别名</span><span style=\"margin-left: 20px\">"+item.matName+"1</span></span><span style=\"margin-left: 20px\">"+item.matName+"2</span></span><span style=\"margin-left: 20px\">"+item.matName+"3</span>";
	// 	$("#bmCount").html(str3);
	// 	$("#bms").html("");
	// }
}
function forList(aliasNames) {
	/**
	 * 2020年07月13日13:31:13 朱燕冰
	 * 判断是否已有别名
	 */
	if (aliasNames!=null&&aliasNames!=undefined&&aliasNames!=""){
		aliasNames= aliasNames.split(",");
		//已有别名
		aliasNamess=aliasNames;
		console.log("已有参考别名"+aliasNamess);
		var str2="";
		// var str3="<span style=\"margin-left: 0px\">参考别名:</span><span style=\"margin-left: 20px\">"+matname1+"1</span></span><span style=\"margin-left: 10px\">"+matname1+"2</span></span><span style=\"margin-left: 10px\">"+matname1+"3</span>";
		for (i=0;i<aliasNames.length ;i++ )
		{
			aliasNamesNUmber=aliasNames.length;
			str2+="<div style=\"margin-left: 0px\" class=\"btn\" ><a id='deleteAli"+i+"' class=\"search-button\" onclick='deleteAliasNames(\""+aliasNames[i]+"\","+i+")'>"+aliasNames[i]+"<div style='position: relative'><div style='position: absolute;bottom: -2px;left: 2px;color: #0e9aef'>删除</div></div></a></div>";
		}
		// if (aliasNamesNUmber == 3){
		//     $("#bmCount").show();
		//     $("#bmCount").html(str2);
		//     $("#bms").hide();
		// }else {
		//     $("#bmCount").show();
		//     $("#bmCount").html(str2);
		//     $("#bms").show();
		// }
		$("#bms").html(str2);
		$("#bmCount").hide();
	}else {
		//2020年07月03日09:31:25朱燕冰修改
		aliasNamesNUmber=0;
		var str3="<span style=\"margin-left: 43px\">参考别名:</span><span style=\"margin-left: 20px\">"+matname1+"1</span></span><span style=\"position: absolute;left: 137px;margin-top: 38px\">"+matname1+"2</span></span><span style=\"position: absolute;left: 137px;margin-top: 80px\">"+matname1+"3</span>";
		$("#bmCount").hide();
		$("#bms").show()
		$("#bms").html(str3);
	}

}

// function forList(aliasNames) {
// 	// alert();
// 	if (aliasNames!=null&&aliasNames!=undefined&&aliasNames!=""){
// 	aliasNames= aliasNames.split(",");
// 	aliasNamess=aliasNames;
// 	console.log("参考别名"+aliasNamess)
// 	var str2="";
// 	var str3="<span style=\"margin-left: 0px\">参考别名</span>";
// 	for (i=0;i<aliasNames.length ;i++ )
// 	{
// 		aliasNamesNUmber=aliasNames.length;
// 		str2+="<div style=\"margin-left: 20px\" class=\"btn\" ><a id='deleteAli"+i+"' class=\"search-button\" onclick='deleteAliasNames(\""+aliasNames[i]+"\","+i+")'>"+aliasNames[i]+"<div style='position: relative'><div style='position: absolute;bottom: -2px;left: 2px;color: #0e9aef'>删除</div></div></a></div>";
// 		str3+="<span style=\"margin-left: 20px\">"+aliasNamess[i]+"</span>";
// 	}
// 	$("#bmCount").html(str3);
// 	$("#bms").html(str2);}
// 	else {
// 		var str3="<span style=\"margin-left: 0px\">参考别名</span><span style=\"margin-left: 20px\">"+matname1+"1</span></span><span style=\"margin-left: 20px\">"+matname1+"2</span></span><span style=\"margin-left: 20px\">"+matname1+"3</span>";
// 		$("#bmCount").html(str3);
// 		$("#bms").html("");
// 	}
// }

function hide2() {
	$("#Editalias1").hide();
}
function success() {
	$("#Editalias1").hide();
}

function deleteAliasNames(aliasName,i) {
	var vdata="&wmsMiId="+AliasNameWmsMiId+"&aliasName="+aliasName;
	$.supper("doservice", {
		"service": "MdOutOrderService.saveDeleteInventoryMaterielAliasName",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
			if (jsondata.code == "1") {
				var a1=jsondata.obj;
				forList(a1);
				$.supper("alert",{ title:"操作提示", msg:"删除成功!"});
			}else{
				$.supper("alert",{ title:"操作提示", msg:"操作失败!"});
			}
		}
	});
}
function addAliasName() {
	if (aliasNamesNUmber>=3) {
		console.log("最多可添加三个别名")
		$.supper("alert",{ title:"操作提示", msg:"最多可添加三个别名!"});
		return;
	}
	var AliasNameId=$("#AliasNameId").val()
	if($("#AliasNameId").val() == ""){
		$.supper("alert",{ title:"操作提示", msg:"请输入别名!"});
	}
	var vdata="&wmsMiId="+AliasNameWmsMiId+"&aliasName="+AliasNameId;
	$.supper("doservice", {
		"service": "MdOutOrderService.saveInventoryMaterielAliasName",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
			if (jsondata.code == "1") {
				// // searchAll();
				var a1=jsondata.obj;
				forList(a1);
				$.supper("alert",{ title:"操作提示", msg:"添加成功!"});
			}else{
				console.log("操作出错")
				$.supper("alert",{ title:"操作提示", msg:"请输入别名!"});
			}
		}
	});
}
$(document).ready(function() {
	//initSortList();
	$(".product-categories-c").bind("click",function(e){
		$(".nav-category").toggle();
		if($("#ibox").is(":hidden")){
			document.getElementById("ibox").style.display = "block";
		}else{
			$("#ibox").hide();     //如果元素为显现,则将其隐藏
		}
		$(".category-p-level-1").removeClass("active");
		$(this).find("span").toggleClass("icon-arrow-down-solid icon-arrow-up-solid");
	});

	$(".category-p-level-1").bind("mouseover",function(){
		$("#ibox").hide();
		$(".category-p-level-1").removeClass("active");
		$(this).addClass("active");
		$(this).next().show();
	});
});
$("#ibox").hover(
	null,
	function() {
		$(".nav-category").toggle();
		$(".category-ul-level-2").hide();
		$(".category-p-level-1").removeClass("active");
		$("#_allType").find("span").toggleClass("icon-arrow-down-solid icon-arrow-up-solid");
	});
function aee(mmtId) {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPicking.jsp"});
	$.supper("setProductArray", {"name":"selOutOrderInfo", "value":{mmtId:mmtId}});
	$.supper("showTtemWin",{ "url":att_url,"title":"新增领料"});
}