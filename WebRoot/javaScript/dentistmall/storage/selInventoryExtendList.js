var _all_inventoryex_div_body=$("#win_form_inventoryex_body");
var alNumber = 0;
var alSplitNumber = 0;
$(function(){
	laydate({
		elem: '#receiptDatetime',
		format: 'YYYY-MM-DD' //日期格式
	});
  // laydate({
	// 	elem: '#endDate',
	// 	format: 'YYYY-MM-DD' //日期格式
	// });
	var wiId= $.supper("getParam", "wiId");
	alNumber = $.supper('getParam', 'alNumber');
	alSplitNumber = $.supper('getParam', 'alSplitNumber');
	$("#wiId").val(wiId);
	initDataGrid();
	// search2();
	_all_inventoryex_div_body.xform('createForm', _saveForm);
	initCode()
});
function initCode(){
	var selCodeInfo = $.supper("getProductArray", "selInventoryExtendInfo");
	if(selCodeInfo != null){
		$("#matName").val(selCodeInfo.matName);
		$("#norm").val(selCodeInfo.norm);
		$("#basicUnit").val(selCodeInfo.basicUnit);
		$("#productName").val(selCodeInfo.productName);

		let allNumber = 0;
		let needNumber = 0;
		let wowType = selCodeInfo.wowType;
		if (wowType != undefined) {
			if (wowType == 2) {
				allNumber = selCodeInfo.baseNumber == undefined ? 0 : selCodeInfo.baseNumber;
				needNumber = allNumber;
			} else if (wowType == 3 || wowType == 4) {
				allNumber = selCodeInfo.baseNumber1 == undefined ? 0 : selCodeInfo.baseNumber1;
				needNumber = allNumber;
			}else {
				allNumber = selCodeInfo.baseNumber-(selCodeInfo.number1 !=null ? selCodeInfo.number1 : 0);
				needNumber = selCodeInfo.baseNumber-(selCodeInfo.number1!=null?selCodeInfo.number1:0);
			}
		} else {
			allNumber = (selCodeInfo.baseNumber == undefined ? 0 : selCodeInfo.baseNumber)-(selCodeInfo.number1 !=null ? selCodeInfo.number1 : 0);
			needNumber = (selCodeInfo.baseNumber == undefined ? 0 : selCodeInfo.baseNumber)-(selCodeInfo.number1!=null?selCodeInfo.number1:0);
		}

		$('#allNumber').val(allNumber);
		$('#collectIventoryCount').text(allNumber);
		$('#alreadyNumber').val(allNumber - needNumber);
		$('#needNumber').val(needNumber);
		if(needNumber > 0){
			$('#needNumber').css('color', 'red');
		}
		$.supper("setProductArray", {"name":"selInventoryExtendInfo", "value":null});
	}
}

//2020年07月07日10:50:46朱燕冰修改
var _saveForm={
	lineNum:2,
	columnNum:4,
	control:[],
	groupTag:[],
	items:[
		{title:'商品名称', name:'matName', readOnly: true, placeholder:"商品名称", width: 100, align: 'left'},
		// {title:'申领单号', name:'relatedBill1Div', placeholder:"申领单号" ,type:'userdefined',width:80,  align:'center',renderer:initSel },
		{title:'规格', name:'norm', readOnly: true ,width:60,  align:'center' },
		{title:'单位', name:'basicUnit', readOnly: true ,width:80,  placeholder:"单位",align:'center'},
		{title:'包装方式', name:'productName', readOnly: true ,width:80,  placeholder:"包装方式",align:'center'},
		{title:'申领数量', name:'allNumber', readOnly: true  ,width:80, align:'center'},
		{title:'已出库数量', name:'alreadyNumber', readOnly: true, placeholder: '0' ,width:120,align:'center'},
		{title:'缺少数量', name:'needNumber', readOnly: true, placeholder:"缺少数量",type:"text"}

	]
};


var mmg ;
var queryAction = "MdInventoryService.getExtendEnterViewPagerModel";
var initDataGrid = function () {
	var cols = [
		{title: '入库编号', name: 'relatedCode', width: 100, align: 'center'},
		{title: '入库时间', name: 'createDate', width: 100, align: 'center'},
		{title: '生产厂家', name: 'productFactory', width: 50, align: 'center'},
		{title: '品牌', name: 'brand', width: 30, align: 'center'},
		{title: '有效日期', name: 'productValitime', width: 60, align: 'center'},
		{title: '批次号', name: 'batchCertNo', width: 40, align: 'center'},
		{title: '库存数量', name: 'baseNumber', width: 100, align: 'center', renderer: renderInventory},
		{title: '出库数量', id: 'mieIdNumber', name: 'mieId', width: 200, align: 'center', renderer: formateInp},
		{title: '制作人', name: 'editRen', width: 70, align: 'center'},

	];
	var att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
	mmg = $('#datagrid1').mmGrid({
		height: '400px'
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

function renderInventory(val, item, rowIndex) {
	let ratio = (item.ratio == undefined || item.ratio == 0) ? 1 : item.ratio;
	let num = item.quantity * ratio;
	let bNumber = (item.baseNumber == undefined ? 0 : item.baseNumber)  - num;

	let str = '';
	str = item.quantity + item.basicUnit + (item.unit == undefined ? '' : (bNumber + item.unit));
	return str;
}

var mieIdCount = {};
var allOrderCount = 0;
var allSplitCount = 0;
function formateInp(val,item,rowIndex){
	var matNumber = 0;
	var splitNumber = 0;
	var readOnly = false;
	var type = 'button';
	var baseNumber = item.quantity;
	var splitN = item.baseNumber;

	let ratio = (item.ratio == undefined || item.ratio == 0) ? 1 : item.ratio;
	let num = item.quantity * ratio;
	let bNumber = (item.baseNumber == undefined ? 0 : item.baseNumber) - num + num; // 总的最小数量

	let allSplitNumber = alNumber * ratio + Number(alSplitNumber); // 申领的最小单位数量
	let a = Math.floor(allSplitNumber / ratio);
	let s = a * ratio;

//先计算满足的最小或者最大单位数量，再计算剩下的数量
//后计算剩余的，依次类推

	if(mieIdCount[item.mieId] !== undefined && mieIdCount[item.mieId] !== null && mieIdCount[item.mieId] > 0){
		matNumber = mieIdCount[item.mieId].matNumber;
		splitNumber = mieIdCount[item.mieId].splitNumber;
	}else {
		if (alNumber <= 0 && alSplitNumber <= 0) {
			matNumber = 0
			splitNumber = 0;
		} else if (allSplitNumber <= bNumber) {
			// 申领数量小于库存，直接赋值
			matNumber = alNumber;
			splitNumber = alSplitNumber;
			alNumber = 0;
			alSplitNumber = 0;
		} else if (allSplitNumber > bNumber) {
			// 最大单位换算成最小单位是否足够
			if (alNumber * ratio <= bNumber) {
				matNumber = alNumber;
				alNumber = 0;
			} else {
				matNumber = baseNumber;
				alNumber -= baseNumber;
			}
			allSplitNumber -= matNumber * ratio;
			bNumber -= matNumber * ratio;
			if (bNumber <= 0) {
				splitNumber = 0;
				alSplitNumber -= 0;
			} else if (allSplitNumber <= 0) {
				splitNumber = 0;
				alSplitNumber -= 0;
			} else  if (allSplitNumber <= bNumber) {
				// 计算最小单位是否足够
				splitNumber = alSplitNumber;
				alSplitNumber = 0;
			} else {
				splitNumber = splitN;
				alSplitNumber -= splitN;
			}

			// if (alSplitNumber <= bNumber) {
			// 	// 计算最小单位是否足够
			// 	splitNumber = alSplitNumber;
			// 	alSplitNumber = 0;
			// } else {
			// 	splitNumber = splitN;
			// 	alSplitNumber -= splitN;
			// }
			//
			// if (alSplitNumber <= bNumber) {
			// 	// 计算最小单位是否足够
			// 	splitNumber = alSplitNumber;
			// 	alSplitNumber = 0;
			// } else {
			// 	splitNumber = splitN;
			// 	alSplitNumber -= splitN;
			// }
			// bNumber -= splitNumber;
			// allSplitNumber -= splitNumber;
			// if (bNumber <= allSplitNumber) {
			// 	// 申领最大数量小于库存，直接赋值
			// 	matNumber = alNumber;
			// 	alNumber = 0;
			// } else {
			// 	// 申领最大数量不足
			// 	matNumber = Math.floor(bNumber / ratio);
			// 	alNumber -= matNumber;
			// }
		}

		// if (alNumber <= 0 || alSplitNumber <= 0) {
		// 	if (alNumber <= 0) {
		// 		matNumber = 0
		// 	}
		// 	if (alSplitNumber <= 0) {
		// 		splitNumber = 0;
		// 	}
		// }else {
		//
		// 	let minusNmber = bNumber - allSplitNumber;
		// 	if (minusNmber < 0) {
		// 		minusNmber = Math.abs(minusNmber);
		// 		matNumber = Math.floor(minusNmber / ratio);
		// 		splitNumber = allSplitNumber - matNumber * ratio;
		// 		alNumber -= matNumber;
		// 		alSplitNumber -= splitNumber;
		// 	} else {
		// 		matNumber = Math.floor(allSplitNumber / ratio);
		// 		splitNumber = allSplitNumber - matNumber * ratio;
		// 		alNumber -= matNumber;
		// 		alSplitNumber -= splitNumber;
		// 	}
		// }

		// var lNumber = 0;
		// if(alNumber <= 0){
		// 	matNumber = 0;
		// }else if(alNumber <= baseNumber){
		// 	matNumber = alNumber;
		// 	alNumber -= baseNumber;
		// } else{
		// 	matNumber = baseNumber;
		// 	alNumber -= baseNumber;
		// }
		if (mieIdCount[item.mieId] == undefined)
			mieIdCount[item.mieId] = {};
		mieIdCount[item.mieId].matNumber = matNumber;
		//
		// if(alSplitNumber == undefined || alSplitNumber == 'undefined' || alSplitNumber <= 0){
		// 	splitNumber = 0;
		// }else if(alSplitNumber <= splitN){
		// 	splitNumber = alSplitNumber;
		// 	alSplitNumber -= splitN;
		// } else{
		// 	splitNumber = splitN;
		// 	alSplitNumber -= splitN;
		// }

		mieIdCount[item.mieId].splitNumber = splitNumber;
	}

	let tt = '';

	// let ratio = item.ratio == undefined ? 1 : item.ratio;

	tt = "<input class='input-text' type=\"text\" id=\""+item.mieId+"Inp\" onmousedown='onMouseDwonOrderNum("+item.mieId+")' onkeyup=\"onKeyUpOrderNum("+item.mieId+"," + item.quantity + ", " + ratio + ")\" style=\"width:80px\;text-align: center\" value='"+ matNumber +"'/>";
	tt += '<span>' + item.basicUnit+ '</span>';
	tt += "<input class='input-text' type=\"text\" id=\""+item.mieId+"InpS\" onmousedown='onMouseDwonOrderNumSplit("+item.mieId+")' onkeyup=\"onKeyUpOrderNumSplit("+item.mieId+"," + item.splitQuantity + ")\" style=\"width:80px\;text-align: center\" value='"+ splitNumber +"'/>";
	// tt += "<input class='input-text' type=\"text\" readonly id=\""+item.mieId+"InpS\" style=\"width:80px\;text-align: center\" value='" + (matNumber * ratio) + "'/>";
	tt += '<span>' + item.unit+ '</span>';
	return tt;
	// var btton_pre = '<input type="'+ type + '" class="input-button btn-white" value="-" id="'+item.mieId+'Min" onclick="minOutOrderNum('+item.mieId+')" />';
	// var tt = "<input class='input-text' type=\"text\" id=\""+item.mieId+"Inp\" onmousedown='onMouseDwonOrderNum("+item.mieId+")' onkeyup=\"onKeyUpOrderNum("+item.mieId+"," + item.baseNumber + ")\" style=\"width:80px\;text-align: center\" value='"+ matNumber +"'/>";
	// var btton_next = '<input type="'+ type + '" class="input-button btn-white" value="+" id="'+item.mieId+'Add" onclick="addnOutOrderNum('+item.mieId+',' + item.baseNumber + ')" />';
	// return btton_pre + tt + btton_next;
}
function minOutOrderNum(mieId){
	var base_num = $("#" + mieId + "Inp").val();
	base_num--;
	if(base_num < 0) {
		base_num = 0;
		return;
	}
	$("#" + mieId + "Inp").val(base_num);
	mieIdCount[mieId] = base_num;
	allOrderCount--;
	altAllOrderCount();
}
function addnOutOrderNum(mieId, baseNumber) {
	var base_num = $("#" + mieId + "Inp").val()
	base_num++;
	if(base_num > baseNumber) {
		base_num = baseNumber;
		return;
	}
	$("#" + mieId + "Inp").val(base_num);
	mieIdCount[mieId] = base_num;
	allOrderCount++;
	altAllOrderCount();
}
var old_num = 0;
function onKeyUpOrderNum(mieId, baseNumber, ratio){
	$("#" + mieId + "Inp").val($("#" + mieId + "Inp").val().replace(/[^0-9.]/g,''));
	var new_num = $("#" + mieId + "Inp").val();
	if(Number(new_num) >= baseNumber) {
		new_num = baseNumber;
		$("#" + mieId + "Inp").val(new_num);
	}
	if(old_num > new_num){
		allOrderCount = allOrderCount - (old_num - new_num);
	} else if(old_num < new_num){
		allOrderCount += new_num - old_num;
	} else{
		return;
	}
	old_num = new_num;
	mieIdCount[mieId].matNumber = old_num;

	// old_numS = old_num * ratio;
	// mieIdCount[mieId].splitNumber = old_numS;
	// $("#" + mieId + "InpS").val(old_numS);
	altAllOrderCount();
}
function onMouseDwonOrderNum(mieId){
	old_num = $("#" + mieId + "Inp").val();
}

var old_numS = 0;
function onKeyUpOrderNumSplit(mieId, baseNumber){
	$("#" + mieId + "Inp").val($("#" + mieId + "Inp").val().replace(/[^0-9.]/g,''));
	var new_num = $("#" + mieId + "Inp").val();
	if(Number(new_num) >= baseNumber) {
		new_num = baseNumber;
		$("#" + mieId + "Inp").val(new_num);
	}
	if(old_numS > new_num){
		allSplitCount = allSplitCount - (old_numS - new_num);
	} else if(old_numS < new_num){
		allSplitCount += new_num - old_numS;
	} else{
		return;
	}
	old_numS = new_num;
	mieIdCount[mieId].splitNumber = old_numS;
	// altAllOrderCount();
}
function onMouseDwonOrderNumSplit(mieId){
	old_numS = $("#" + mieId + "InpS").val();
}

function altAllOrderCount(){
	if(allOrderCount <= 0){
		allOrderCount = 0;
	}
	$('#collectIventoryAllCount').text(allOrderCount);
}
function search2(){
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
}

function toInventory() {
	var searchName1 = $("#matName").val();
	var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/viewInventory.jsp"});
	$.supper("showTtemWin", {url: att_url, title: "库存查询",});
	$.supper("setProductArray", {"name": "shouldClose", "value": {searchName1: searchName1}});
	closeWin();
}

function controlInp(val,item,rowIndex){
	var tt = "<input type=\"text\" id=\""+item.mieId+"Inp\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" style=\"width:60px;text-align:center\"/>";
	return tt;
}

function  closeWin(){
	$.supper("closeWin");
}

function save(){
	var reArry=[];
	var rows=mmg.rows();
	var flag=false;
	if(rows!=null && rows.length > 0){
		for(var i =0;i < rows.length;i++){
			var mieId=rows[i].mieId;
			var baseNumber=rows[i].quantity;
			var splitNumber = rows[i].baseNumber;
			var shu = $("#"+mieId+"Inp").val();
			var shuSplit = $("#"+mieId+"InpS").val();
			if((shu!=null && shu!="" && shu >0) || (shuSplit != null && shuSplit != '' && shuSplit > 0)){
				if(shu >baseNumber || shuSplit > splitNumber){
					$.supper("alert",{ title:"操作提示", msg:"出库数量大于库存数量！"});
					return;
				}else{
					reArry.push({
						mieId:mieId,
						shu:shu,
						shuSplit: shuSplit
					});
					flag=true;
				}
					
			} else if(shu==null || shu=="" || shu <=0){
				reArry.push({
					mieId:mieId,
					shu:0,
					shuSplit: 0
				});
			}
		}
	}
	// if(!flag){
	// 	$.supper("alert",{ title:"操作提示", msg:"请输入出库数量！"});
	// 	return;
	// }
	$.supper("setProductArray", {"name":"selInventoryExtendInfo", "value":null});
	$.supper("setProductArray", {"name":"selInvExtend", "value":reArry});
	closeWin();
}


