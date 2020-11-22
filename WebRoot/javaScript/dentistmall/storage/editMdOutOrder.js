var _all_accountForm=$("#accountForm");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");
var _all_win_tools_but_RR = $("#win_edit_toolbarRR");
var _collectCount = $('#collectCount');
var _collectAllCount = $('#collectAllCount');
//提交数量与数量
var orderCount = 0;
var allOrderCount = 0;
var wiidCount = {};

var viewPage = false; //查看权限
var reApply = false; //再次申请权限
var _searching = false;

/***
 * 修改部分begin
 */
var _rbbId;
var _mooCode;
var _updateData = false;
var _all_table_Id="mooId";
var _all_table_moo_code = 'mooCode';
var _all_saveAction = "MdOutOrderService.saveMdOutOrder";
var _all_updateAction = "MdOutOrderService.updateMdOutOrderMx";
var _all_questAction = "MdOutOrderService.copyFormObject";
var _all_win_url_orderlist = "/dentistmall/jsp/dentistmall/storage/mdOutOrderList.jsp";
var _all_this_win_url = '/dentistmall/jsp/dentistmall/storage/editMdOutOrder.jsp';
var _current_win_url = '';
var _all_edit_orderview_title = '申领管理';
var _saveForm = {
	lineNum: 2,
	columnNum: 2,
	control: [],
	groupTag: [],
	hiddenitems: [
		{name: "mooId", id: "mooId", value: "", type: "hidden"},
		{name: "rbaId", id: "rbaId", value: "", type: "hidden"},
		{name: "rbsId", id: "rbsId", value: "", type: "hidden"},
		{name: "rbbId", id: "rbbId", value: "", type: "hidden"},
		// {title: '创建时间', name: 'createDate', type: "hidden"},
		{title: '创建人', name: 'createRen', type: "hidden"},
		{title: '修改时间', name: 'editDate', type: "hidden"},
		{title: '修改人', name: 'editRen', type: "hidden"},
		{title: '申领人', name: 'userName', type: "hidden", placeholder: "申领人", align: 'center'},
		{title: '申领部门', name: 'groupName', type: "hidden", placeholder: "申领部门", align: 'center'},
	],
	items: [
		{
			title: '申领单号',
			name: 'mooCode',
			placeholder: "申领单号",
			width: 120,
			align: 'center',
			readOnly: true,
			prefixCode: "CK"
		},
		{title: '申领时间', readOnly: true, name: 'orderTime', width: 80, align: 'center'},
		// {title: '备注', name: 'remarks', placeholder: "选填字段，字数不超过100", align: 'center', renderer: remarksInput}
	]
};
var _ToolbarRR = {
	toolBarIdRR: "",
	items: [
		{title: "继续添加", id: "win_but_add", icon: "plus", colourStyle: "primary", clickE: selInv}
	]
};

var _Toolbar={
		toolBarId:"",
		items:[
		   
		        {title:"提交申请",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:save},
		       	{title:'取消申请',id:"win_but_cancel",icon:"close", colourStyle:"default",clickE:cancelApply}
		       ] 
	} ;

function initFormHidden(){
	_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}
var mmgOrder;
/**
 * 2019-12-10 2019-12-10
 * yanglei  yangfeng补充
 * 更改分页问题，dataType必填，否则无法分页本地数据
 */
var mmPaginator = $('#pgOutOrder').mmPaginator({dataType: {}, limit: 30});
var initDataGrid = function(){
	var cols = [   
	            {title:'商品名称', name:'matName' ,width:120, align:'center'  },
	            {title:'规格', name:'mmfName', width:80, align: 'center'},  
	            {title:'单位', name:'basicUnit' ,width:30, align:'center'} ,
	            {title:'库存数量', name:'baseNumber' ,width:60,  align:'center'},
	            {title:'申领数量', name:'matNumber', width: 60, align: 'center',renderer:formateInp},
	            {title:'操作' ,name:'control',width:100,  align:'center',renderer:control }
	        ];  
	          mmgOrder = $('#datagrid1').mmGrid({
	            height:'auto'
	            , cols: cols
	            , method: 'get'
	            , remoteSort:false
	            , sortName: 'serialNumber'
	            , sortStatus: 'asc'
	            , multiSelect: true
	            , checkCol: true
	            , fullWidthRows: true
	            ,showBackboard:false
	            , autoLoad: true
				  , plugins: [
					  mmPaginator
					]
	        });  
	        mmgOrder.load([]);
}

function formatMatNum(val,item,rowIndex){
	var tt = item.matNumber-item.enterNumber - item.backNumber;
	return tt;
}

function control(val,item,rowIndex){
	var str = "";
	if(viewPage === false) {
		str += "<a onclick=\"delRow('" + item.wiId + "')\"  class='btn btn-danger  btn-xs'>移除商品</a> ";
	}
	// if(item.micId != null)
	// 	str += "&nbsp;&nbsp;&nbsp;<a onclick=\"removeCollect('"+item.micId+"','"+rowIndex+"')\"  class='btn btn-success  btn-xs'>移出收藏</a> ";
	// else
	// 	str += "&nbsp;&nbsp;&nbsp;<a onclick=\"addCollect('"+item.wiId+"','"+rowIndex+"')\"  class='btn btn-primary  btn-xs'>加入收藏</a> ";
   return str;
}

function delRow(wiId){
	var rows = mmgOrder.rows();
    var base_num = 0;
	for(var i =0;i < rows.length;i++){
		if(rows[i] != null && rows[i].wiId==wiId){
            base_num = $("#" + wiId + "Inp").val();
            orderCount--;
            allOrderCount -= base_num;
            mmgOrder.removeRow(i);
			wiidCount[wiId].shu = -1;
		}
	}
	if(_searching === true) {
		removeInvDatas(wiId);
	}
	reLoadInvCount();
	altOderCount();
	altAllOrderCount();
    mmgOrder.refreshData();
}

function formateInp(val,item,rowIndex){
	var matNumber = 0;
	var readOnly = false;
	var type = 'button';
	if(item.matNumber != undefined && item.matNumber != null && reApply === false){
		matNumber = item.matNumber;
		if(item.matNumber != 0 && matNumber == item.outNumber){
			readOnly = true;
			type = 'hidden';
		}
	}
	matNumber = initWiidCountArray(item, matNumber);

	var btton_pre ='';
	var tt = "<input readonly class='input-text' type=\"text\" id=\""+item.wiId+"Inp\" onmousedown='onMouseDwonOrderNum("+item.wiId+")' onkeyup=\"onKeyUpOrderNum("+item.wiId+"," + item.baseNumber + ")\" style=\"width:80px\;text-align: center\" value='"+ matNumber +"'/>";
	var btton_next = '';
	if(viewPage === false){
		btton_pre = '<input type="'+ type + '" class="input-button btn-white" value="-" id="'+item.wiId+'Min" onclick="minOutOrderNum('+item.wiId+')" />';
		tt = "<input class='input-text' type=\"text\" id=\""+item.wiId+"Inp\" onmousedown='onMouseDwonOrderNum("+item.wiId+")' onkeyup=\"onKeyUpOrderNum("+item.wiId+"," + item.baseNumber + ")\" style=\"width:80px\;text-align: center\" value='"+ matNumber +"'/>";
		btton_next = '<input type="'+ type + '" class="input-button btn-white" value="+" id="'+item.wiId+'Add" onclick="addnOutOrderNum('+item.wiId+',' + item.baseNumber + ')" />';
	}

	var hiddenMomId = '<input id="' + item.momId + 'MomInp" type="hidden" value="' + item.momId + '"/>'
	return btton_pre + tt + btton_next + hiddenMomId;
}
//20191210 yangfeng 初始化本地数据的记录
function initWiidCountArray(item, matNumber){
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

// 20191120 yangfeng
function minOutOrderNum(wiId){
	var base_num = $("#" + wiId + "Inp").val();
	base_num--;
	if(base_num <= 0) {
		base_num = 0;
	}
	$("#" + wiId + "Inp").val(base_num);
	wiidCount[wiId].shu = base_num;
	allOrderCount--;
	altAllOrderCount();
}
function addnOutOrderNum(wiId) {
	var base_num = $("#" + wiId + "Inp").val()
	base_num++;
	$("#" + wiId + "Inp").val(base_num);
	wiidCount[wiId].shu = base_num;
	allOrderCount++;
	altAllOrderCount();
}
var old_num = 0;
function onKeyUpOrderNum(wiId, baseNumber){
	$("#" + wiId + "Inp").val($("#" + wiId + "Inp").val().replace(/[^0-9]/g,''));
	var new_num = $("#" + wiId + "Inp").val();
	// if(Number(new_num) >= baseNumber) {
	// 	new_num = baseNumber;
	// 	$("#" + wiId + "Inp").val(new_num);
	// }
	if(old_num > new_num){
		allOrderCount = allOrderCount - (old_num - new_num);
	} else if(old_num < new_num){
		allOrderCount += new_num - old_num;
	} else{
		return;
	}
	old_num = new_num;
	wiidCount[wiId].shu = new_num;
	altAllOrderCount();
}
function onMouseDwonOrderNum(wiId){
	old_num = $("#" + wiId + "Inp").val();
}
function remarksInput(val, item, rowIndex){
	var tt = '<input type="text" sytle="width: 120;text-align: left;" maxlength="100"/>';
	return tt;
}

var invRows = {
	items: [],
	rows: [],
	total: 0,
	totalCount: 0
	// limit: 10
}
//20190910 增加删除本地数据方法
function removeInvDatas(wiId){
	var index = -1;
	invRows.items.every(function (value) {
		++index;
		if(value.wiId == wiId){
			return false;
		}
	});
	if(index >= 0){
		removeInvData(index);
	}

}
function removeInvSearchDatas(wiId){
	var index = -1;
	invRowsSearch.items.every(function (value) {
		++index;
		if(value.wiId == wiId){
			return false;
		}
	});
	if(index >= 0){
		removeInvSearchData(index);
	}
}
function reLoadInvData(index, number, data){
    invRows.items.splice(index, number, data);
    invRows.rows.splice(index, number, data);
}
function removeInvData(index){
	invRows.items.splice(index, 1);
	invRows.rows.splice(index, 1);
}
function removeInvSearchData(index){
	invRowsSearch.items.splice(index, 1);
	invRowsSearch.rows.splice(index, 1);
}
function clearInvRows(){
	invRows.items.splice(0, invRows.items.length);
	invRows.rows.splice(0, invRows.rows.length);
	invRows.total = 0;
	invRows.totalCount = 0;
}
function clearInvRowsSearch(){
	invRowsSearch.items.splice(0, invRowsSearch.items.length);
	invRowsSearch.rows.splice(0, invRowsSearch.rows.length);
	invRowsSearch.totalCount = 0;
	invRowsSearch.total = 0;
}
function reLoadInvCount(){
    invRows.total = orderCount;
    invRows.totalCount = orderCount;
}

function addToTable() {
	var selInvInfoArray = $.supper("getProductArray", "selInvInfoArray");
	var rows = mmgOrder.rows();
	var addIndex = 0;
	if(selInvInfoArray != null && selInvInfoArray.length >0){
		for(var i =0;i < selInvInfoArray.length ;i++){
			var selInvInfo = selInvInfoArray[i];
			var flag=false;
			if(rows != null && rows.length > 0 && rows[0] != null){
				for(var j =0;j<rows.length;j++){
					if(selInvInfo.wiId==rows[j].wiId){
						flag=true;
						break;
					}
				}
			}
			if(!flag) {
				reLoadInvData(addIndex, 0, selInvInfo);
				orderCount++;
				addIndex++;
			}
		}

		reLoadInvCount();
		mmgOrder.refreshData(invRows);
		$.supper("setProductArray", {"name":"selInvInfoArray", "value":null});
	}
	altOderCount();
}
function selInv(){
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/storage/selInventoryList"});
	var tt_win=$.supper("showWin",{url:att_url,title:"选择库存信息",icon:"fa-flask",width:1200,height:540,BackE: addToTable});
}

//移除，批量移除
// 20191121 yangfeng
function removeBatch() {
	var selectRowsIndex = mmgOrder.selectedRowsIndex();
	if (selectRowsIndex == undefined || selectRowsIndex == null ||  selectRowsIndex.length <= 0){
		$.supper("alert", {title : "操作提示",msg : "请选择要移除的商品！"});
		return;
	}
    $.supper("confirm", {
        title : "操作提示",
        msg : "确认需要移除商品？",
        yesE: function () {
            selectRowsIndex.reverse();
            var base_num = 0;
            for (var i = 0; i < selectRowsIndex.length; i++){
                var rowIndex = selectRowsIndex[i];
                var rows=mmgOrder.rows();
                base_num = $("#" + rows[rowIndex].wiId + "Inp").val();
                orderCount--;
                allOrderCount -= base_num;
                rows[rowIndex].micId=null;
                mmgOrder.updateRow(rows[rowIndex],rowIndex);
                mmgOrder.removeRow(rowIndex);
				wiidCount[rows[rowIndex].wiId].shu = -1;
				wiidCount[rows[rowIndex].wiId].momId = 0;
            }

            if(_searching === true) {
				removeInvDatas(rows[rowIndex].wiId);
			}
            altOderCount();
            altAllOrderCount();
            mmgOrder.refreshData();
        }
    });

}
function altOderCount(){
	if(orderCount <= 0){
		orderCount = 0;
	}
	_collectCount.text(orderCount);
}
function altAllOrderCount(){
	if(allOrderCount <= 0){
		allOrderCount = 0;
	}
	_collectAllCount.text(allOrderCount);
}

function initForm(){
	
	_rbbId = $.supper("getParam", _all_table_Id);
	var att_data=_all_table_Id+"="+_rbbId;
	_all_div_body.xform('createForm',_saveForm);
    _mooCode = $.supper("getParam", _all_table_moo_code);

    if( _mooCode !== undefined  &&_mooCode !== null){
        $('#mooCode').val(_mooCode);
        _updateData = true;
    }
    if(_mooCode === undefined || _mooCode === null){
		_all_div_body.hide();
		$('#exportBtn').hide();
	}
	initDataGrid();
	if(_rbbId != null){
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data});
		
		$.supper("doservice", {"service":"MdOutOrderMxService.getOutOrderMxInventory","data":att_data, "BackE":function (jsondata) {
			if(jsondata!=null && jsondata.length > 0){
				var item = null;
				for(var i =0;i < jsondata.length;i++){
					if(jsondata[i].wiId !=null) {
						// mmgOrder.addRow(jsondata[i]);'
						item = jsondata[i];
						reLoadInvData(i, 0, item);
						orderCount++;
						if( item.matNumber != undefined  &&item.matNumber != null){
							if(reApply === true){
								item.matNumber = 0;
							}
							allOrderCount += item.matNumber;
						}

						initWiidCountArray(item, item.matNumber);
					}
				}
			}
			altAllOrderCount();
			altOderCount();
			reLoadInvCount();
			mmgOrder.refreshData(invRows);
	 	}});
	}else{
		setTimeout(function () {
			var uName= $("#uName").val();
			$("#userName").val(uName);
			selInv();
		}, 500);

	}
}

function initToolBar(){
	if(viewPage === false) {
		_all_win_tools_but.xtoolbar('create', _Toolbar);
		if( _mooCode !== undefined  &&_mooCode !== null){
			$('#win_but_save').text('保存修改');
			$('#win_but_cancel').text('返回');
		}
		if(reApply === true){
			$('#win_but_cancel').text('返回');
		}
		$("#win_but_cancel").css("width","80px");
		$("#win_but_cancel").css("height","23px");
		$("#win_but_cancel").css("font-size","12px");
		$("#win_but_cancel").css("vertical-align","middle");
	}
}
function initToolBarRR(){
	_all_win_tools_but_RR.xtoolbar('create',_ToolbarRR);
	if( _mooCode !== undefined  &&_mooCode !== null){
		$('#win_but_add').hide();
	}
}

$(function(){
	var mainView = $.supper("getParam", 'mainview');
	if(mainView !== undefined && mainView !== null) {
		viewPage = true;
		$('#remarks').attr('readonly', 'readonly')
	}else{
		// $('#ibox-tools-moo').show();
		$('#ibox-tools-moo').css('display', 'inline');
	}
	var reapllyOrder = $.supper("getParam", 'reapply');
	if(reapllyOrder == '1'){
		reApply = true;
	}

	initFormHidden();
	initForm();
	initToolBarRR();
	initToolBar();

	if(reApply === true){
		$('#remarks').val('');
	}

	_current_win_url = $.supper("getProductArray", "menuItemUrl");
	$("#win_but_add").css("width","80px");
	$("#win_but_add").css("height","23px");
	$("#win_but_add").css("font-size","12px");
	$("#win_but_add").css("vertical-align","middle");
	//回车查询
	 $("#searchMatName").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#button1").trigger("click");
		  }
	});
	 $("#searchNorm").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#button1").trigger("click");
		  }
	});

}); 

/**
 * /*yesE : removeAllOrderData
 * 取消申请之后返回申领管理页面
 */
function  cancelApply(){
	if(JSON.stringify(_current_win_url) !== "{}" && _current_win_url !== null && _current_win_url !== '' && _current_win_url !== _all_this_win_url) {
		removeAllOrderData();
		$.supper("setProductArray", {"name":"menuItemUrl", "value":''});
		$.supper("closeTtemWin", {url: _current_win_url});
		mainAddOrderView();
	}else {
		var rows = mmgOrder.rows();
		if (rows == undefined || rows == null || rows.length <= 0) {
			$.supper("alert", {
				title: "操作提示",
				msg: "没有数据"
			});
			return;
		}
		if (rows.length == 1 && rows[0] === undefined) {
			$.supper("alert", {
				title: "操作提示",
				msg: "没有数据"
			});
			return;
		}
		$.supper("confirm", {
			title: "操作提示",
			msg: "取消后则所选信息将会被清空，是否取消？",
			yesE: function () {
				removeAllOrderData();
				mainAddOrderView();
			}
		});
	}
}
function removeAllOrderData(){
	mmgOrder.removeRow();
	_collectCount.text(0);
	_collectAllCount.text(0);
	orderCount = 0;
	allOrderCount = 0;
	wiidCount = {};
	clearInvRows();
	clearInvRowsSearch()
    mmgOrder.refreshData(invRows);
}
//跳转tab页
function mainAddOrderView(){
	$.supper("showTtemWin",{ "url":_all_win_url_orderlist,"title":_all_edit_orderview_title});
	setTimeout(function () {
		if(JSON.stringify(_current_win_url) !== "{}" && _current_win_url !== null && _current_win_url !== '' && _current_win_url !== _all_this_win_url) {
			$.supper("setProductArray", {"name": "menuItemUrl", "value": ''});
			$.supper("closeTtemWin", {url: _current_win_url});
		}else{
			$.supper("closeTtemWin", {url: _all_this_win_url});
		}
	}, 100)

}
 
function save() {
	if (_all_div_body.xform('checkForm')) { 
		var data = _all_accountForm.serialize();
		var totalCount = invRows.totalCount;
		var count = 0;

		var rows=mmgOrder.rows();
		var wiIds="";
		var shus="";
		var momIds="";
		var flag = false;
		$.each(wiidCount, function (key, value) {
			var wiId = key;
			var shu = value.shu;
			if (shu == '0' || shu == '') {
				flag = false;
				//$.supper("alert", {title: "操作提示", msg: "请确认所有页数申领数量都超过0！"});
				return false;
			}
			count++;
			if (shu != null && shu != "" && shu > 0) {
				flag = true;
				wiIds += wiId + ",";
				shus += shu + ",";
				momIds += value.momId + ",";
			}

		})
		if(count == 0){
			$.supper("alert", {title : "操作提示",msg : "请选择商品！"});
			return;
		}
		wiIds = wiIds.length > 0 ? wiIds.substring(0, wiIds.length - 1) : "";
		shus = shus.length > 0 ? shus.substring(0, shus.length - 1) : "";
		momIds = momIds.length > 0 ? momIds.substring(0, momIds.length-1) : "";
		if(flag === false || count < totalCount){
			$.supper("alert", {title : "操作提示",msg : "申领数量未填写，请确认！"});
			return;
		}
		data += "&shus="+shus+"&wiIds="+wiIds;

		if(_updateData){
			data += "&momIds="+momIds;
            $.supper("doservice", {
                "service": _all_updateAction,
                "ifloading": 1,
                "options": {"type": "post", "data": data},
                "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("confirm", {
                            title: "操作提示",
                            msg: "申领单已经提交成功，您可以在已申请管理列表中查看！",
                            yesE: function () {
                                removeAllOrderData();
                                mainAddOrderView();
                            }
                            // noE: removeAllOrderData
                        });
                    } else
                        $.supper("alert", {
                            title: "操作提示",
                            msg: "抱歉，系统或网络原因提交失败请稍后再试！"
                        });
                }
            });
        }else {
            $.supper("doservice", {
                "service": _all_saveAction,
                "ifloading": 1,
                "options": {"type": "post", "data": data},
                "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("confirm", {
                            title: "操作提示",
                            msg: "申领单已经提交成功，您可以在已申请管理列表中查看！",
                            yesE: function () {
                                removeAllOrderData();
                                mainAddOrderView();
                            },
                            noE: function () {
                            	_saveForm.renew = true;
								_all_div_body.xform('createForm',_saveForm);
							}
                        });
                    } else
                        $.supper("alert", {
                            title: "操作提示",
                            msg: "抱歉，系统或网络原因提交失败请稍后再试！"
                        });
                }
            });
        }
	} 
}

function removeCollect(micId,rowIndex){
	var vdata="micId="+micId;
	$.supper("confirm",{ title:"移出操作", msg:"确认移出收藏夹操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdInventoryCollectService.deleteObject","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！","BackE":function () {
					var rows=mmgOrder.rows();
					rows[rowIndex].micId=null;
					mmgOrder.updateRow(rows[rowIndex],rowIndex);
					wiidCount[rows[rowIndex].wiId].shu = -1;
					orderCount--;
					altOderCount();
				}});
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
				$.supper("alert",{ title:"操作提示", msg:"操作成功！","BackE":function () {
					var rows=mmgOrder.rows();
					rows[rowIndex].micId=jsondata.obj.micId;
					mmgOrder.updateRow(rows[rowIndex],rowIndex);
				}});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}
//搜索功能 20191209 yangfeng
var invRowsSearch = {
	items: [],
	rows: [],
	total: 0,
	totalCount: 0
	// limit: 10
}
function searchmoo() {
	var searchItems = [];
	var matName = $('#searchMatName').val();
	var norm = $('#searchNorm').val();
	var items = invRows.items;
	var totalCount = 0;
	if(matName == '' && norm == ''){
		_searching = false;
		mmgOrder.refreshData(invRows);
		return
	}
	_searching = true;
	if(matName !== ''){
		items.forEach(function (value) {
			if(value.matName.indexOf(matName) >= 0){
				searchItems.push(value);
				totalCount++;
			}
			if(value.matNamePy.indexOf(matName) >= 0){
				if($.inArray(value, searchItems) < 0) {
					searchItems.push(value);
					totalCount++;
				}
			}
		})
	}
	if(norm !== ''){
		items.forEach(function (value) {
			if(value.mmfName.indexOf(norm) >= 0){
				if($.inArray(value, searchItems) < 0) {
					searchItems.push(value);
					totalCount++;
				}
			}
		})
	}
	invRowsSearch.items = searchItems;
	invRowsSearch.rows = searchItems;
	invRowsSearch.total = totalCount;
	invRowsSearch.totalCount = totalCount;
	mmgOrder.refreshData(invRowsSearch);
}
/**
 * 2019-12-10
 * 导出申领管理中的详情
 * yanglei
 * 
 */
function exportFile(){
	var dat=mmgOrder.opts.data;
	var rows=null;
	if (dat!=undefined && dat!=null) {
		rows=dat.items;
	}
	console.log(rows)
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}
	var mooIds=rows[0].mooId;
	/*for ( var int = 0; int < rows.length; int++) {
		mooIds += rows[int].mooId+",";
	}*/
	//mooIds=mooIds.substring(0,mooIds.length);
	vdata="&mooIds="+mooIds;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOutOrderService.exportList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
 
