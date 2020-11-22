var _selType="2";
var selected = [];
var wiidCount = {};
var selInventoryTips = {};
var selInventoryTip = null;
var rowsTemp = {};
$(function(){   
	$('.myscrollinfo').slimScroll({
        height: 370,
         wheelStep:'5px'
    });
	initDataGrid();
	//search();
	
	//增加物料名称，规格回车查询
	 $("#mat1").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#button3").trigger("click");
		  }
	});
	 $("#mmf1").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#button3").trigger("click");
		  }
	});
});
var mmg ;
var queryAction = "MdInventoryService.getPagerViewObject";
var queryAction2 = "MdInventoryCollectService.getPagerModelObject";
var initDataGrid = function () {
	var cols = [
		{title: '物料名称', name: 'matName', width: 120, align: 'center'},
		{title: '规格', name: 'mmfName', width: 100, align: 'center'},
		{title: '库存数量', name: 'baseNumber', width: 80, align: 'center'},
		{title: '基本单位', name: 'basicUnit', width: 80, align: 'center'},
		{title: '包装方式', name: 'productName', width: 80, align: 'center'},
		{title: '申领数量', name: 'matNumber', width: 60, align: 'center', renderer: formatMatNumberInp},
		{title: '操作', name: 'basicUnit', width: 80, align: 'center', renderer: control}
	];
	var att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
	mmg = $('#datagrid1').mmGrid({
		height: '280px'
		, cols: cols
		, method: 'post'
		, remoteSort: true
		, url: att_mmgurl
		, sortName: 'SECUCODE'
		, sortStatus: 'asc'
		, multiSelect: true
		, showBackboard: false
		, checkCol: true
		, fullWidthRows: true
		, autoLoad: false
		, nowrap: true
		, plugins: [
			$('#pg').mmPaginator({})
		]
	});
	mmg.load();
}

function formatMatNumberInp(val,item,rowIndex){
	var matNumber = 0;
	var readOnly = false;
	var type = 'button';
	if(item.matNumber != undefined && item.matNumber != null && reApply === false){
		matNumber = item.matNumber;
	}
	matNumber = initSelInventoryWiidCountArray(item, matNumber);

	var btton_pre = '<input type="'+ type + '" class="input-button btn-white" value="-" id="'+item.wiId+'Min" onclick="minSelInventoryNum('+item.wiId+')" />';
	var tt = "<input class='input-text' type=\"text\" id=\""+item.wiId+"Inp\" onfocus='closeTips(" + item.wiId + ")' onmousedown='onMouseDwonSelInventoryNum("+item.wiId+")' onkeyup=\"onKeyUpSelInventoryNum("+item.wiId+"," + item.baseNumber + ")\" style=\"width:60px\;text-align: center\" value='"+ matNumber +"'/>";
	var btton_next = '<input type="'+ type + '" class="input-button btn-white" value="+" id="'+item.wiId+'Add" onclick="addSelInventoryNum('+item.wiId+',' + item.baseNumber + ')" />';

	return btton_pre + tt + btton_next;
}
function closeTips(wiId) {
	layer.close(selInventoryTips[wiId]);
}
function initSelInventoryWiidCountArray(item, matNumber){
	if(wiidCount[item.wiId] === undefined || wiidCount[item.wiId] === null){
		wiidCount[item.wiId]= {};
	}
	if(wiidCount[item.wiId].shu !== undefined &&wiidCount[item.wiId].shu !== null &&  wiidCount[item.wiId].shu > 0){
		matNumber = wiidCount[item.wiId].shu;
	}else {
		wiidCount[item.wiId].shu = matNumber;
		wiidCount[item.wiId].momId = item.momId;
	}
	return matNumber;
}
function onSelInventoryBlur(wiId, baseNumber) {
	var number = $("#" + wiId + "Inp").val();;
	if(number == ''){
		layer.close(selInventoryTips[wiId]);
		selInventoryTips[wiId] = layer.tips('申领数量不能为空', $("#" + wiId + "Inp"), {time: 0, tipsMore: true});
		return;
	}
	if(number > baseNumber){
		layer.close(selInventoryTips[wiId]);
		selInventoryTips[wiId] = layer.tips('申领数量超出库存数量', $("#" + wiId + "Inp"),{time: 0, tipsMore: true});
		return;
	}
}
function minSelInventoryNum(wiId){
	var base_num = $("#" + wiId + "Inp").val();
	base_num--;
	if(base_num <= 0) {
		base_num = 0;
	}
	$("#" + wiId + "Inp").val(base_num);
	wiidCount[wiId].shu = base_num;
}
function addSelInventoryNum(wiId) {
	var base_num = $("#" + wiId + "Inp").val()
	base_num++;
	$("#" + wiId + "Inp").val(base_num);
	wiidCount[wiId].shu = base_num;
}
var old_num = 0;
function onKeyUpSelInventoryNum(wiId, baseNumber){
	$("#" + wiId + "Inp").val($("#" + wiId + "Inp").val().replace(/[^0-9]/g,''));
	var new_num = $("#" + wiId + "Inp").val();
	// if(Number(new_num) >= baseNumber) {
	// 	new_num = baseNumber;
	// 	$("#" + wiId + "Inp").val(new_num);
	// }
	if(old_num == new_num){
		return;
	}
	old_num = new_num;
	wiidCount[wiId].shu = new_num;
}
function onMouseDwonSelInventoryNum(wiId){
	old_num = $("#" + wiId + "Inp").val();
}
function control(val,item,rowIndex){
	var str = "";
	str += '<input type="button" class="btn btn-primary btn-xs" value="确认" style="margin-right: 5px" onclick="addToMdOutrOrder(' + JSON.stringify(item).replace(/"/g, '&quot;') + ', ' + item.wiId + ')" />';
	if(item.micId != null)
		str += "<a onclick=\"removeCollect('"+item.micId+"','"+rowIndex+"')\"  class='btn btn-success  btn-xs'>移出收藏</a> ";  
	else
		str += "<a onclick=\"addCollect('"+item.wiId+"','"+rowIndex+"')\"  class='btn btn-primary  btn-xs'>加入收藏</a> ";  
   return str;
}
function addToMdOutrOrder(item, wiId) {
	var matNumber = $("#" + wiId + "Inp").val();
	item.matNumber = matNumber;
	rowsTemp[wiId] = item;
	layer.msg('成功加入申领信息中，关闭页面可查看');

}
function search2(){
	var att_mmgurl ="";
	if(_selType=='1')
		att_mmgurl = rpc.getUrlByForm(queryAction2,"queryForm");
	else
		att_mmgurl = rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
}

function changeType(aType){
	_selType = aType;
	search2();
}

function  closeWin(){
	$.each(selInventoryTips, function (key, value) {
		layer.close(value);
	})
	wiidCount = {};
	selInventoryTips = {};
	rowsTemp = {};
	$.supper("closeWin");
}
function beforeClose() {
	var rows = []
	$.each(rowsTemp, function (key, value) {
		rows.push(value);
	});
	if(rows != null || rows.length > 0) {
		$.supper("setProductArray", {"name": "selInvInfoArray", "value": rows});
	}
	closeWin();
}
function save(){
	var selected = mmg.selectedRecordsData();
	var rows = []
	if(selected < 0){
		rows = mmg.selectedRows();
	} else{
		selected.forEach(function (value) {
			value.forEach(function (item) {
				var matNumber = $('#' + item.wiId + 'Inp').val();
				item.matNumber = matNumber;
				rows.push(item);
			})
		})
	}
	$.each(rowsTemp, function (key, value) {
		var needAdd = true;
		rows.forEach(function (value1) {
			if(key == value1.wiId){
				needAdd = false;
				return false;
			}
		})
		if(needAdd === true) {
			rows.push(value);
		}
	});
	if(rows==null || rows.length <=0){
		$.supper("alert",{ title:"操作提示", msg:"请选择数据！"});
		return;
	}
	$.supper("setProductArray", {"name":"selInvInfoArray", "value":rows});
	closeWin();
}

function removeCollect(micId,rowIndex){
	var vdata="micId="+micId;
	$.supper("confirm",{ title:"移出操作", msg:"确认移出收藏夹操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdInventoryCollectService.deleteObject","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				// $.supper("alert",{ title:"操作提示", msg:"操作成功！","BackE":function () {
					var rows=mmg.rows();
					rows[rowIndex].micId=null;
					mmg.updateRow(rows[rowIndex],rowIndex);
				// }});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

function addCollect(wiId,rowIndex){
	var vdata="wiId="+wiId;
	$.supper("confirm",{ title:"加入操作", msg:"确认加入收藏夹操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdInventoryCollectService.saveOrUpdateObject","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				// $.supper("alert",{ title:"操作提示", msg:"操作成功！","BackE":function () {
					var rows=mmg.rows();
					rows[rowIndex].micId=jsondata.obj.micId;
					mmg.updateRow(rows[rowIndex],rowIndex);
				// }});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

