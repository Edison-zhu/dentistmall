var _all_accountForm=$("#accountForm");
var _all_win_searchForm = $("#win_form_search");
var _all_div_hidden= $("#win_form_edithidden");
var _all_div_body=$("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");
var meiArray=[];
var needTips = [];
var layerTips = null;
/***
 * 修改部分begin
 */
var _rbbId;
var _wowId;
var _moiId;
var _wewId;
var _wiId;
var _mooId;
// var _all_table_Id="wewId";
var _all_table_Id="mooId";
var _all_saveAction = "MdOutWarehouseService.saveMdOutWarehouse";
var _all_questAction = "MdOutWarehouseService.findFormObject";

var _all_questOrderAction = 'mdOutOrderService.findFormObject';
var _all_questOrderWowIdAction = 'mdOutOrderService.findFormObjectByWowId';
var _all_questOrderAfterAction = 'MdOutWarehouseService.findFormObject1';
// var _all_questOutAction = 'mdInventoryService.findFormObject1';

var _saveForm={ 
		lineNum:4,
		columnNum:2,
		control:[],
		groupTag:[],
		hiddenitems:[
						// {name:"wowId",id:"wowId",value:"",type:"hidden"},
						// {name:"rbaId",id:"rbaId",value:"",type:"hidden"},
						// {name:"rbsId",id:"rbsId",value:"",type:"hidden"},
						// {name:"rbbId",id:"rbbId",value:"",type:"hidden"},
						// {name:'orderTime',id:"orderTime",value:"",type:"hidden"},
						// {title:'创建时间' ,name:'createDate',type:"hidden"},
			            // {title:'创建人' ,name:'createRen',type:"hidden"},
			            // {title:'修改时间' ,name:'editDate',type:"hidden"},
			            // {title:'修改人' ,name:'editRen',type:"hidden"},
			// {title:'出库单号', name:'wowCode', type:"hidden",prefixCode:"CK"},
                        {title:'申领单号', name:'relatedBill1', type: 'hidden'},
		             ],
		items:[ 
		       	  //
		       	  //{title:'申领单号', name:'relatedBill1Div', placeholder:"申领单号" ,type:'userdefined',width:80,  align:'center',renderer:initSel },
		       	  	{title:'申领单号', name:'mooCode',placeholder:"申领单号" ,width:40,align:'center',readOnly: true ,},
                    {title:'申领时间', name:'createDate', readOnly: true ,width:40,  align:'center' },
                    {title:'申领部门', name:'groupName' ,width:40,  readOnly: true ,placeholder:"申领部门",align:'center'},
                    {title:'申领人', name:'userName' ,width:40, readOnly: true , placeholder:"申领人",align:'center'},
                    {title:'申领总数量', name:'number1', readOnly: true  ,width:40, align:'center'},
                    {title:'需要出库数量', name:'missingNumber',readOnly: true, placeholder: '0' ,width:40,align:'center'},
	               {title:'备注', name:'remarks',readOnly: true,placeholder:"备注",type:"textarea"}
	               
		]
	};

/*var _searchForm={ 
		lineNum:2,
		columnNum:3,
		control:[],
		groupTag:[],
		hiddenitems:[
			{title:"出库单类型",name:"companyType",type:"hidden",value:'1',placeholder:"出库单类型"}
		],
		items:[
			// {title:"出库单号",name:"wowCode",type:"text",placeholder:"出库单号"},
			{title:'申领单号', id: 'mooCodeSearchInput', name:'mooCode', width:80, placeholder:"输入申领单号,回车查询"},
			// {title:"申领单号", id: 'relatedBill1SearchInput', name:"relatedBill1", placeholder:"申领单号"},
			{title:"申领部门", id:'customer', name:"customer",type:"text",placeholder:"输入申领部门,回车查询"},
			{title:"申领人", id: 'customerName', name:"customerName",type:"text",placeholder:"输入申领人,回车查询"},
			{title:"申领日期范围", name:"orderTime_str", type:'userdefined', readOnly:true, renderer: orderTimeInput},
			{title:"出库日期范围", name:"outTime_str", type:'userdefined', readOnly:true, renderer: outTimeInput},
			{title:'申领状态', name:'flowState', width:80, type: 'select',impCode:"PAR171113111313225", placeholder:"订单状态"},
		]
	}*/

function initSel(){
	var str="<input type=\"text\" id=\"relatedBill1\" class=\"form-control2\" readonly name=\"relatedBill1\" aria-required=\"true\" aria-invalid=\"false\" placeholder=\"申领编号\"/>&nbsp;&nbsp;&nbsp;&nbsp;"
	str += "<a class=\"btn btn-danger btn-xs\" id=\"selCodeBut\" onclick=\"selCode()\">选择</a>";
	return str;
}

var _Toolbar={
		toolBarId:"win_edit_toolbar",
		items:[
		        {title:"出库",id:"win_but_save",icon:"save", colourStyle:"primary",clickE:saveOut},
		       	{title:"导出",id:"win_but_add1",icon:"bolt", colourStyle:"success",clickE:main_export},
		    	{title:"关闭",id:"win_but_add",icon:"close", colourStyle:"default",clickE:closeWin},
		       	//增加导出  导出详情中的全部
		       // {title:"导出",id:"win_but_add",icon:"bolt", colourStyle:"success",clickE:main_export}
		       	//{title:"导出",id:"win_but_add",icon:"bolt", colourStyle:"success",clickE:main_export}
		       ] 
	} ;

function initFormHidden(){
	if (reload == false)
		_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);
	 
}
function initFormHidden1(){
	if (reload == false)
		_all_div_hidden.xform('createhidden',_saveForm.hiddenitems);

}
var mmg;
var initDataGrid = function(){
	if (wowType == 3) {
		var cols = [
			{title: '物料编码', name: 'matCode1', width: 180, align: 'center', renderer: controlMatCode1},
			{title: '物料名称', name: 'matName', width: 120, align: 'center', renderer: controlInfo2},
			{title: '规格型号', name: 'norm', width: 80, align: 'center'},
			{title: '单位', name: 'basicUnit', width: 60, align: 'center'},
			{title: '库存数量', name: 'quantity', width: 80, align: 'center'},
			// {title: '出库数量', name: 'quantity', width: 80, align: 'center'},
			{title: '出库数量', name: 'quantity', width: 250, align: 'center', renderer: formateInp},
			{title: '采购价（元）', name: 'avgPrice', width: 100, align: 'center', renderer: renderAvgPrice3},
			{title: '采购金额（元）', name: 'allPrice', width: 100, align: 'center'},
			{title: '批号', name: 'batchCode', width: 140, align: 'center', renderer: renderBatchCode},
			{title: '有效期', name: 'valiedDate', width: 140, align: 'center', renderer: renderValiedDate},
			{title: '注册证号/备案号', name: 'backPrinting', width: 180, align: 'center', renderer: renderBp},
			{title: '备注', name: 'remarks', width: 180, align: 'center', renderer: renderRemarks},
			{title: '操作', name: 'state', width: 100, align: 'center', renderer: control},
			// {title: '物料图标', name: 'logo', width: 80, align: 'center', renderer: renderLogo},
			// {title: '品牌', name: 'brand', width: 50, align: 'center'},
			// {title: '库存数量/包装方式', name: 'quantity', width: 100, align: 'center', renderer: renderQuantity},
			// {title: '包装方式', name: 'productName', width: 60, align: 'center'},
			// {title: '申领数量', name: 'baseNumber', width: 80, align: 'center', renderer: formatMatNum},
			// {title: '出库数量', name: 'quantity', width: 250, align: 'center', renderer: formateInp},
			// {title: '缺少数量', name: 'missNumber', width: 60, align: 'center', renderer: formateMissNumberInp},
			// {title: '零售价格', name: 'avgRetailPrice', width: 60, align: 'center'},
			// {title: '零售金额', name: 'allRetailPrice', width: 60, align: 'center', renderer: renderAllRPrice},
			// {title: '批号/有效期', name: 'batchCode', width: 80, align: 'center', renderer: renderB}
			// {title: '选择库存', name: 'wiId', width: 100, align: 'center', renderer: fomateSel},
			// {title: '缺少数量', name: 'missNumber', width: 100, align: 'center', renderer: formateMissNumberInp},
			// {title: '操作', name: 'state', width: 100, align: 'center', renderer: control}
		];
	}else {
		var cols = [
			{title: '物料编码', name: 'matCode1', width: 80, align: 'center'},
			{title: '物料名称', name: 'matName', width: 80, align: 'center', renderer: controlInfo},
			{title: '物料图标', name: 'logo', width: 80, align: 'center', renderer: renderLogo},
			{title: '品牌', name: 'brand', width: 40, align: 'center'},
			{title: '规格型号', name: 'norm', width: 100, align: 'center'},
			{title: '包装方式', name: 'productName', width: 80, align: 'center'},
			{title: '单位', name: 'basicUnit', width: 60, align: 'center'},
			{title: '库存数量', name: 'quantity', width: 80, align: 'center'},
			// {title: '申领数量', name: 'baseNumber', width: 60, align: 'center', renderer: formatMatNum},
			{title: '申领数量', name: 'baseNumber', width: 80, align: 'center'},
			{title: '出库数量', name: 'quantity', width: 60, align: 'center', renderer: formateInp},
			// {title: '出库数量', name: 'number1', width: 80, align: 'center', renderer: formateInp0},
			{title: '缺少数量', name: 'missNumber', width: 60, align: 'center', renderer: formateMissNumberInp},
			// {title: '缺少数量', name: 'missNumber', width: 80, align: 'center', renderer: formateInp1},
			{title: '采购均价', name: 'avgPrice', width: 80, align: 'center', renderer: changePrice0},
			// {title: '零售价格', name: 'avgRetailPrice', width: 60, align: 'center'},
			{title: '采购金额', name: 'allPrice', width: 80, align: 'center', renderer: renderAllPrice0},
			// {title: '零售金额', name: 'allRetailPrice', width: 60, align: 'center', renderer: renderAllRPrice},
			{title: '操作', name: 'state', width: 80, align: 'center', renderer: control},
			{title: '批次号', name: 'batchCode', width: 80, align: 'center'},
			{title: '有效期', name: 'valiedDate', width: 80, align: 'center'},
			{title: '注册号/备案号', name: 'backPrinting', width: 140, align: 'center'},
			{title: '备注', name: 'remarks', width: 180, align: 'center', renderer: remarks0},
			// {title: '批号/有效期', name: 'batchCode', width: 80, align: 'center', renderer: renderB}
			// {title: '选择库存', name: 'wiId', width: 100, align: 'center', renderer: fomateSel},
			// {title: '缺少数量', name: 'missNumber', width: 100, align: 'center', renderer: formateMissNumberInp},
			// {title: '操作', name: 'state', width: 100, align: 'center', renderer: control}
		];
		if (wowType != 1 && wowType != undefined) {
			for (let idx = cols.length - 1; idx >= 0; idx--) {
				if (cols[idx].title == '缺少数量')
					cols.splice(idx, 1);
				if (cols[idx].title == '申领数量')
					cols.splice(idx, 1);
			}
		}
	}
	$('#gridDiv').empty();
	$('#gridDiv').html('<table id="datagrid1" class="mmg"></table>');
	mmg = $('#datagrid1').mmGrid({
		width: '1650px'
		, height: 'auto'
		, cols: cols
		, method: 'get'
		, remoteSort: false
		// , sortName: 'serialNumber'
		// , sortStatus: 'asc'
		, multiSelect: true
		, checkCol: false
		, fullWidthRows: true
		, showBackboard: false
		, autoLoad: false
		, nowrap: true
	});
	mmg.load([]);
	mmg.on('loadSuccess', refreshCount);
}

function renderAvgPrice3(val, item, rowIndex) {
	let str = '';
	str += '<input id="avgPrice' + item.mieId + '" value="' + (item.avgPrice == undefined ? '' : item.avgPrice) + '" />';
	return str;
}
function renderBatchCode(val, item, rowIndex) {
	let str = '';
	str += '<input id="bc' + item.mieId + '" value="' + (item.batchCode == undefined ? '' : item.batchCode) + '" />';
	return str;
}

function renderValiedDate(val, item, rowIndex) {
	let str = '';
	str += '<input id="vd' + item.mieId + '" value="' + (item.valiedDate == undefined ? '' : item.valiedDate) + '" />';
	return str;
}

function renderBp(val, item, rowIndex) {
	let str = '';
	str += '<input id="bp' + item.mieId + '" value="' + (item.backPrinting == undefined ? '' : item.backPrinting) + '" />';
	return str;
}

//领料出库-出库数量：默认是申领出库数量，若是库存数量小于申领数量则显示库存数量。
// 直接显示文本框输入即可，默认是根据入库单倒序出库（即先入先出），不可为空，可修改，不可大于出库数量。
function formateInp0(val, item, rowIndex) {
	let str = '';
	if (item.baseNumber > item.number1) {  //申领数量  大于  库存数量
		str = '<input class="input-text" type="text" value="' + item.number1 + '" style="width:60px;text-align:center;" />';
	} else {
		str = '<span>' + item.number1 + '</span>';
	}
	return str;
}

//领料出库-缺少数量1.不带单位2.缺少数量是申请数量减去出库数量变化而来3.部分出库数量：先读取缺少的数量，当出库当数量变化当时候缺少数量也会改变
function formateInp1(val, item, rowIndex) {
	let str = '';
	var leftNumber;
	var leftNumber = (item.baseNumber == undefined ? 0 : item.baseNumber) - (item.number1 != undefined ? item.number1 : 0);
	if (leftNumber > 0) {
		str = '<span style="color: red;">' + leftNumber + '</span>';
	} else {
		str = '<span style="color: red;">' + 0 + '</span>';
	}
	return str;
}

//领料出库-采购均价
function changePrice0(val, item, rowIndex) {
	console.log('item---->', item)
	let str = '';
	var avgPrice = 0;
	if (item.avgPrice > 0) {
		avgPrice = item.avgPrice.toFixed(2)
	}
	str = '<input id="avgPrice' + item.wiId + '" class="input-text" type="text" value="' + avgPrice + '"  style="width:60px;text-align:center;" />';
	return str;
}

//领料出库-采购金额
function renderAllPrice0(val, item, rowIndex) {
	var str = '';
	var price = item.avgPrice;
	var allprice = 0;
	if (item.number1 > 0) {
		allprice = (price * item.number1).toFixed(2)
	}
	str = '<span>' + allprice + '</span>';
	return str;
}

//领料出库-备注
function remarks0(val, item, rowIndex) {
	let str = '';
	str = '<input id="remark' + item.wiId + '" class="input-text" type="text" value="" placeholder="可为空，30字内"  style="width:100px;text-align:left;" />';
	return str;
}
//退货出库 破损出库  -- 物料编号
function controlMatCode1(val, item, rowIndex) {
	var str = "";
	str = "<input type='text' value='" + (item.matCode1 == undefined ? '' : item.matCode1) + "'onblur='hideClass()' id='selectMat' style='width: 100px;height: 25px' onclick=''><button onclick='selectMatBefores(1)' class='selectbtn1'>查询</button>"
	return str;
}
//退货出库 破损出库  -- 物料名称
function controlInfo2(val, item, rowIndex) {
	var str = "";
	str = "<input type='text' value='" + (item.matName == undefined ? '' : item.matName) + "'onblur='hideClass()' id='selectMat' style='width: 110px;height: 25px' onclick=''> ";
	return str;
}
//退货出库 破损出库  -- 采购价（元）
function renderAvgPrice(val, item, rowIndex) {
	var str = "";
	var price = 0;
	price = item.avgPrice.toFixed(2)
	str = " <span>"+price+"</span>";
	return str;
}
//退货出库 破损出库  -- 备注
function renderRemarks(val, item, rowIndex) {
	let str = '';
	str = '<input id="remark' + item.mieId + '" class="input-text" type="text" value="" placeholder="可为空，30字内"  style="width:100px;text-align:left;" />';
	return str;
}

function renderB(val, item, rowIndex) {
	let bc = item.batchCode == undefined ? '' : item.batchCode;
	let pv = item.valiedDate == undefined ? '' : item.valiedDate;
	if (wowType == 1)
		pv = item.valiedDate1 == undefined ? '' : item.valiedDate1;
	return bc + '/' + pv;
}

function renderQuantity(val, item, rowIndex) {
	let str = '';
	let ratio = item.ratio == undefined ? 1 : item.ratio;
	ratio = ratio == 0 ? 1 : ratio;
	let num = (item.quantity == undefined ? 0 : item.quantity) * ratio;
	let bNumber = 0;
	let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
	str += '<span>' + (item.quantity == undefined ? 0 : item.quantity) + '</span>'
	str += '<span>' + basicUnit+ '</span>';
	if ((wowType == undefined || wowType == 1) && view == true) {
		bNumber = (item.splitBaseNumber == undefined ? 0 : item.splitBaseNumber)  - num;
		str += '<span>' + bNumber + '</span>';
	} else if ((wowType == undefined || wowType == 1)) {
		bNumber = (item.splitQuantity1 == undefined ? 0 : item.splitQuantity1) - num;
		str += '<span>' + bNumber + '</span>';
	} else if (wowType == 2) {
		bNumber = (item.splitQuantity == undefined ? 0 : item.splitQuantity) - num;
		str += '<span>' + bNumber + '</span>';
	} else if (wowType == 3) {
		bNumber = (item.baseNumber == undefined ? 0 : item.baseNumber) - num;
		str += '<span>' + bNumber + '</span>';
	} else if (wowType != 4) {
		bNumber = (item.splitQuantity == undefined ? 0 : item.splitQuantity) - num;
		str += '<span>' + bNumber + '</span>';
	} else {
		bNumber = (item.splitQuantity1 == undefined ? 0 : item.splitQuantity1) - num;
		str += '<span>' + bNumber + '</span>';
	}
	str += '<span>' + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit)+ '</span>';
	str += '/<span>' + (item.productName == undefined ? '' : item.productName) + '</span>';
	return str;
}
function control(val, item, rowIndx) {
	let str = '';
	if (delObj[item.wiId] == undefined)
		delObj[item.wiId] = 0;
	delObj[item.wiId] = rowIndx;
	if (view != true && view1 != true) {
		str += '<a class="btn btn-white btn-xs" onclick="del(' + item.wiId + ',' + rowIndx + ')">删除</a>';
	}
	return str;
}
var reload = false;
var delObj = {};
function del(wiId, rowIndx) {
	if (orderDel == true) {
		if (meiArray != null && meiArray.length > 0) {
			for (var i = meiArray.length - 1; i >= 0; i--) {
				if (meiArray[i].wiId == wiId) {
					meiArray.splice(i, 1);
				}
			}
		}
		let idx = delObj[wiId];
		delObj[wiId] = -1;
		mmg.removeRow(idx);
		for (let id in delObj) {
			if (delObj[id] > idx)
				delObj[id] -= 1;
		}
	} else {
		console.log("222")
		let item = mmg.rows()[rowIndx];
		let data = '';
		if (_wowId != undefined)
			data += '&wowId=' + _wowId;
		else if (item.wowId != undefined)
			data += '&wowId=' + item.wowId;
		if (wowType != undefined)
			data += '&wowType=' + wowType;
		if (item.wowMxId != undefined)
			data += '&wowMxId=' + item.wowMxId;
		if (item.wiId != undefined)
			data += '&wiId=' + item.wiId;
		if (item.momId != undefined)
			data += '&momId=' + item.momId;
		$.supper("doservice", {
			"service": 'MdOutWarehouseService.deleteOutWarehouseMx',
			"ifloading": 1,
			"options": {"type": "post", "data": data},
			"BackE": function (jsondata) {
				console.log(jsondata)
				if (jsondata.code == "1") {
					console.log("111")
					mmg.load();
					$.supper("alert", {
						title: "操作提示",
						msg: "操作成功！",
						BackE: function () {
							reload = true;
							initData();
						}
					});
				} else
					$.supper("alert", {
						title: "操作提示",
						msg: "操作失败！"
					});
			}
		});
	}
}
function renderLogo(val, item, rowIndx) {
	let str = '';
	str+='<input type="hidden" id="'+item.wiId +'ratio"'+' value="'+item.ratio+'" />';
	if (item.logoPath == undefined || item.logoPath == '')
		str = '';
	else
		str += '<img src="' + item.logoPath + '" style="width: 40px; height: 40px;" />';
	return str;
}
function renderAllPrice(val, item, rowIndex) {
	let str = '';
	let price = item.price1 == undefined ? 0 : item.price1;
	let allPrice = item.quantity * (item.avgPrice == undefined ? 0 : item.avgPrice);
	return toDecimal2(allPrice);
	// let str = '';
	// // if (item.stype == 1) {
	// str += toDecimal2(item.number * item.price);
	// // }
	// return str;
}

function renderAllRPrice(val, item, rowIndex) {
	let str = '';
	let price = item.retailPrice1 == undefined ? 0 : item.retailPrice1;
	let allPrice = item.quantity * (item.avgRetailPrice == undefined ? 0 : item.avgRetailPrice);
	return toDecimal2(allPrice);
	// let str = '';
	// // if (item.stype == 1) {
	// str += toDecimal2(item.number * (item.retailPrice == undefined ? 0 : item.retailPrice));
	// // }
	// return str;
}

function formateMissNumberInp(val, item, rowIndex){
	//console.log("新增出库"+JSON.stringify(item))
	//basicUnit(盒)
	let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
	if (wowType != undefined && wowType != 1) {
		return 0 + basicUnit + 0 + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit);
	}
	if ((wowType == undefined || wowType == 1) && view == true && _moiId == undefined) {
		return 0 + '' + basicUnit + 0 + '' + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit);
	}

	if ((wowType == undefined || wowType == 1) && view == true) {
		return (item.alNumber == undefined ? 0 : item.alNumber) + basicUnit + (item.salNumber == undefined ? 0 : item.salNumber) + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit);
	}
	let ratio = item.ratio == undefined ? 1 : item.ratio;
	ratio = ratio == 0 ? 1 : ratio;
	let bNumber = (item.baseNumber == undefined ? 0 : item.baseNumber) * ratio; // 计算成最小单位数量
	bNumber += (item.splitQuantity == undefined ? 0 : item.splitQuantity); // 申领最小单位总共数量

	let alreadyNumber1 = (item.number1 != undefined ? item.number1 : 0) * ratio; // 计算已出库最小单位数量
	alreadyNumber1 += (item.splitNumber1 != undefined ? item.splitNumber1 : 0); // 已出库最小单总共位数量

	let minusNumber1 = bNumber - alreadyNumber1; // 剩下总共的最小数量
	let minusBaseNumber = Math.floor(minusNumber1 / ratio); // 换算成最大单位数量
	minusNumber1 = minusNumber1 - minusBaseNumber * ratio; // 重新计算剩下的最小单位数量

	var tt = '';
	var leftNumber = (item.baseNumber == undefined ? 0 : item.baseNumber) - (item.number1 != undefined ? item.number1 : 0);
	if (leftNumber > 0) {
		tt = '<span style="color: red;">' + leftNumber + '</span>' + basicUnit;
	} else {
		tt = '<span style="color: red;">' + 0 + '</span>' + basicUnit;
	}
	tt += '';
	var leftSplitN = (item.splitQuantity == undefined ? 0 : item.splitQuantity) - (item.splitNumber1 != undefined ? item.splitNumber1 : 0);
	if (leftSplitN >= 0)
		tt += '<span style="color: red;">' + leftSplitN + '</span>' + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit);
	else
		tt += '<span style="color: red;">' + 0 + '</span>' + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit);
	return tt;
}
var needNumber = 0;
var allNumberCount = 0;
var allSplitNumberCount = 0;
var allAreadyNumberCount = 0;

function formatMatNum(val,item,rowIndex){
	let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
	if ((wowType == undefined || wowType == 1) && view == true && _moiId == undefined) {
		allNumberCount += item.baseNumber;
		// allSplitNumberCount += (item.splitNumber == undefined ? 0 : item.splitNumber);
		// console.log(JSON.stringify(item))
		$('#collectAllCount').text(allNumberCount);
		return (item.baseNumber == undefined ? 0 : item.baseNumber) + basicUnit + (item.splitNumber == undefined ? 0 : item.splitNumber) + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit);
	}
	if (wowType == 3 || wowType == 4) {
		// allNumberCount += item.baseNumber1;
		// allSplitNumberCount += (item.splitNumber1 == undefined ? 0 : item.splitNumber1);
		// console.log(JSON.stringify(item))
		// $('#collectAllCount').text(allNumberCount + '/' + allSplitNumberCount);
		return item.baseNumber1 + basicUnit + (item.splitNumber1 == undefined ? 0 : item.splitNumber1) + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit);
	} else if (wowType == 3) {
		// allNumberCount += item.baseNumber;
		// allSplitNumberCount += (item.splitNumber1 == undefined ? 0 : item.splitNumber1);
		// console.log(JSON.stringify(item))
		// $('#collectAllCount').text(allNumberCount + '/' + allSplitNumberCount);
		return item.baseNumber + basicUnit + (item.splitNumber1 == undefined ? 0 : item.splitNumber1) + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit);
	}
	else if (wowType == 2) {
		// allNumberCount += item.baseNumber;
		// allSplitNumberCount += (item.splitNumber == undefined ? 0 : item.splitNumber);
		// console.log(JSON.stringify(item))
		// $('#collectAllCount').text(allNumberCount + '/' + allSplitNumberCount);
		return item.baseNumber + basicUnit + (item.splitNumber == undefined ? 0 : item.splitNumber) + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit);
	}
	else if ((wowType == undefined || wowType == 1) && view == true) {
		allNumberCount += item.number1;
		// allSplitNumberCount += (item.splitNumber == undefined ? 0 : item.splitNumber);
		// console.log(JSON.stringify(item))
		$('#collectAllCount').text(allNumberCount);
		return item.number1 + basicUnit + (item.splitNumber == undefined ? 0 : item.splitNumber) + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit);
	}
	var tt = item.baseNumber-(item.number1!=null?item.number1:0);
	needNumber += tt;
	if(needNumber <= 0){
		needNumber = 0;
	}
	$('#needNumber').val(needNumber);
	allNumberCount += item.baseNumber;
	// allSplitNumberCount += (item.splitQ/uantity == undefined ? 0 : item.splitQuantity);
	console.log(JSON.stringify(item))
	$('#collectAllCount').text(allNumberCount);
	return item.baseNumber + basicUnit + (item.splitQuantity == undefined ? 0 : item.splitQuantity) + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit);
}

function controlInfo(val,item,rowIndex){
	var str = "";
	if (item.wmsMiId != undefined)
		str += "<a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+item.wmsMiId+"\" target=\"_blank\" title=\""+item.matName+"\">"+item.matName+"</a> ";
	else
		str += item.matName;
   return str;
}

function changeSplit(value, mieId, ratio) {
	ratio = ratio == undefined ? 1 : ratio;
	value = Number(value);
	$('#' + mieId + 'InpS').val(value * ratio);
}
//设置已选择出库数量
function formateInp(val,item,rowIndex){
	//console.log("新增"+JSON.stringify(item))
	var tt = '';
	var color = '';
	var readOnly = '';
	let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
	let ratio = item.ratio == undefined ? 1 : item.ratio;
	if (view == true)
		readOnly = 'readonly';
	if (wowType == 3) {
		if (item.quantity <= 0) {
			color = 'color:red';
			tt = "<input class='input-text' type=\"text\" id=\"" + item.mieId + "Inp\" " + readOnly + " value='" + (item.baseNumber1 == undefined ? 0 : item.baseNumber1) + "' style=\"width:60px;text-align:center;" + color + "\" />";// onclick=\"selInvExtend('"+  item.wiId+"','"+item.momId+"','"+item.baseNumber+"')\"/>";
		}else
			tt = "<input class='input-text' type=\"text\" id=\"" + item.mieId + "Inp\" " + readOnly + " value='" + (item.baseNumber1 == undefined ? 0 : item.baseNumber1)+ "' style=\"width:60px;text-align:center;" + color + "\" />";// onclick=\"selInvExtend('"+  item.wiId+"','"+item.momId+"','"+item.baseNumber+"')\"/>";
		//onkeyup='changeSplit(this.value, " + item.mieId + ", " + item.ratio + ")'
		// tt += '<span>' + basicUnit + '</span>';
		tt += "<input class='input-text' type=\"hidden\" id=\"" + item.mieId + "InpS\" " + readOnly + " value='" + (item.splitNumber1 == undefined ? 0 : item.splitNumber1) + "' style=\"width:60px;text-align:center;" + color + "\" />";
		// tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
		if (item.quantity <= 0)
			tt += '<span style="margin-left: 5px;' + color + '">库存不足</span>';
		var ss = {
			wiId: item.wiId,
			momId: item.momId,
			baseNumber: item.baseNumber,
			splitNumber1: (item.splitNumber1 == undefined ? 0 : item.splitNumber1),
			mieId: item.mieId,
			shu: (item.baseNUmber1 == undefined ? null : item.baseNUmber1),
			shuSplit: (item.splitNumber1 == undefined ? null : item.splitNumber1),
			leftNumber: item.quantity,
			leftSplitNumber: leftSplitN
		};
		meiArray.push(ss);
		allAreadyNumberCount += item.baseNumber1;
	} else if (wowType == 4) {
		if (item.quantity <= 0)
			color = 'color:red';
		tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" readonly value='" + (item.baseNumber == undefined ? 0 : item.baseNumber)+ "' style=\"width:60px;text-align:center;" + color + "\" />";// onclick=\"selInvExtend('"+  item.wiId+"','"+item.momId+"','"+item.baseNumber+"')\"/>";
		// tt += '<span>' + basicUnit + '</span>';
		tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" readonly value='" + (item.splitNumber1 == undefined ? 0 : item.splitNumber1) + "' style=\"width:60px;text-align:center;" + color + "\" />";
		// tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
		selItems[item.wiId] = item;
		if (item.quantity <= 0)
			tt += '<span style="margin-left: 5px;' + color + '">库存不足</span>';
		// else if (view != true)
		// 	tt += "<a onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.quantity + "', '" + (item.splitQuantity1 == undefined ? 0 : item.splitQuantity1) + "', " + item.wowMxId + ", " + item.wewMxId + ")\"  class='trigger'>选择</a>";
		var ss = {
			wiId: item.wiId,
			momId: item.momId,
			baseNumber: item.quantity,
			splitNumber1: (item.splitQuantity1 == undefined ? 0 : item.splitQuantity1),
			mieId: item.mieId,
			shu: (item.baseNumber == undefined ? null : item.baseNumber),
			shuSplit: (item.splitNumber1 == undefined ? null : item.splitNumber1),
			leftNumber: item.quantity,
			leftSplitNumber: leftSplitN,
			wewMxId: item.wewMxId,
			wowMxId: item.wowMxId
		};
		meiArray.push(ss);
		allAreadyNumberCount += item.baseNumber;
	}else if (wowType == 2) {
		if (item.quantity <= 0)
			color = 'color:red';
		tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" readonly value='" + (item.baseNumber == undefined ? 0 : item.baseNumber)+ "' style=\"width:60px;text-align:center;" + color + "\" />";// onclick=\"selInvExtend('"+  item.wiId+"','"+item.momId+"','"+item.baseNumber+"')\"/>";
		// tt += '<span>' + basicUnit + '</span>';
		tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" readonly value='" + (item.splitNumber == undefined ? 0 : item.splitNumber) + "' style=\"width:60px;text-align:center;" + color + "\" />";
		// tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
		selItems[item.wiId] = item;
		if (item.quantity <= 0)
			tt += '<span style="margin-left: 5px;' + color + '">库存不足</span>';
		// else if (view != true)
		// 	tt += "<a onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.quantity + "', '" + (item.splitQuantity == undefined ? 0 : item.splitQuantity) + "', " + item.wowMxId + ")\"  class='trigger'>选择</a>";
		var ss = {
			wiId: item.wiId,
			momId: item.momId,
			baseNumber: item.quantity,
			splitNumber1: (item.splitQuantity == undefined ? 0 : item.splitQuantity),
			mieId: item.mieId,
			shu: (item.baseNUmber == undefined ? null : item.baseNUmber),
			shuSplit: (item.splitNumber == undefined ? null : item.splitNumber),
			leftNumber: item.quantity,
			leftSplitNumber: leftSplitN,
			wowMxId: item.wowMxId
		};
		meiArray.push(ss);
		allAreadyNumberCount += item.baseNumber;
	} else {
		if (view == true) {
			tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" readonly value='" + (item.baseNumber == undefined ? 0 : item.baseNumber) + "' style=\"width:60px;text-align:center;" + color + "\" />";// onclick=\"selInvExtend('"+  item.wiId+"','"+item.momId+"','"+item.baseNumber+"')\"/>";
			// tt += '<span>' + basicUnit + '</span>';
			tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" readonly value='" + (item.splitQuantity == undefined ? 0 : item.splitQuantity) + "'  style=\"width:60px;text-align:center;" + color + "\" />";
			// tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
			allAreadyNumberCount += item.baseNumber;
		} else {
			// 还要最小拆分单位的计算保存
			var splitQuantity = item.splitQuantity == undefined ? 0 : item.splitQuantity;
			var leftNumber = item.baseNumber - (item.number1 != null ? item.number1 : 0);
			var leftSplitN = splitQuantity - (item.splitNumber1 != undefined ? item.splitNumber1 : 0);
			var baseNumber = item.baseNumber;
			var splitN = item.splitQuantity1;
			let bNumber = item.splitQuantity1;
			let allSplitNumber = leftNumber * ratio + leftSplitN;

			if (leftNumber <= 0 && leftSplitN <= 0) {
				leftNumber = 0
				leftSplitN = 0;
			} else if (allSplitNumber > bNumber) {
				// 最大单位换算成最小单位是否足够
				if (leftNumber * ratio > bNumber) {
					leftNumber = baseNumber;
				}
				allSplitNumber -= leftNumber * ratio;
				bNumber -= leftNumber * ratio;
				if (allSplitNumber <= 0) {
					leftSplitN = 0;
				} else if (allSplitNumber > bNumber) {
					// 计算最小单位是否足够
					leftSplitN = splitN;
				}
			}
			// if (leftSplitN >= splitQuantity)
			// 	leftSplitN = splitQuantity;
			// var color = '';
			// var tt = '';
			// if (item.quantity < leftNumber || item.splitQuantity1 < leftSplitN) {
			if (bNumber < allSplitNumber) {
				color = 'color: red';
				if (item.quantity <= 0 && item.splitQuantity1 <= 0) {
					tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" " + readOnly + " value='" + 0 + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";// onclick=\"selInvExtend('"+  item.wiId+"','"+item.momId+"','"+item.baseNumber+"')\"/>";
					// tt += '<span>' + basicUnit + '</span>';
					tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" " + readOnly + " value='" + 0 + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
					// tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
					tt += '<span style="margin-left: 5px;' + color + '">库存不足</span>';
				} else {
					let q = item.quantity;
					let sq = item.splitQuantity1;
					sq = item.splitQuantity1 - item.quantity * ratio;
					// allSplitNumber
					// leftSplitN
					// leftNumber
					tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" " + readOnly + " value='" + item.quantity + "'  style=\"width:60px;text-align:center;" + color + "\" />";// onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.baseNumber + "', '" + item.splitNumber1 + "')\"/>";
					// tt += '<span>' + basicUnit + '</span>';
					tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" " + readOnly + " value='" + (item.splitQuantity1 - item.quantity * ratio) + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
					// tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
					// alert(1234);
					// if (view != true)
					// 	tt += "<a onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.quantity + "', '" + (item.splitQuantity1 - item.quantity * ratio) + "')\"  class='trigger'>选择</a>";
					// alert(1234);
					var ss = {
						wiId: item.wiId,
						momId: item.momId,
						baseNumber: item.baseNumber,
						splitNumber1: (item.splitQuantity == undefined ? 0 : item.splitQuantity),
						mieId: null,
						shu: null,
						shuSplit: null,
						leftNumber: leftNumber,
						leftSplitNumber: leftSplitN
					};
					meiArray.push(ss);
					allAreadyNumberCount += item.quantity;
				}

			} else if (leftNumber > 0 || leftSplitN > 0) {
				tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" " + readOnly + " value='" + leftNumber + "' style=\"width:60px;text-align:center;" + color + "\" />";//onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.baseNumber + "', '" + item.splitNumber1 + "')\"/>";
				// tt += '<span>' + basicUnit + '</span>';
				tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" " + readOnly + " value='" + leftSplitN + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
				// tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
				selItems[item.wiId] = item;
				// if (view != true)
				// 	tt += "<a onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.baseNumber + "', '" + item.splitNumber1 + "')\"  class='trigger'>选择</a>";

				var ss = {
					wiId: item.wiId,
					momId: item.momId,
					baseNumber: item.baseNumber,
					splitNumber1: (item.splitQuantity == undefined ? 0 : item.splitQuantity),
					mieId: null,
					shu: null,
					shuSplit: null,
					leftNumber: leftNumber,
					leftSplitNumber: leftSplitN
				};
				meiArray.push(ss);
				allAreadyNumberCount += leftNumber;
			} else {
				tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" " + readOnly + " value='" + leftNumber + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
				// tt += '<span>' + basicUnit + '</span>';
				tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" " + readOnly + " value='" + leftSplitN + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
				// tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
				allAreadyNumberCount += leftNumber;
			}
		}
	}
	// allAreadyNumberCount = 0;
	if (item.quantity != ""){
		// allAreadyNumberCount++;
		// console.log(allAreadyNumberCount)
	}
	$('#collectReadyAllCount').text(allAreadyNumberCount);
	return tt;
}
var selItems = {};
function fomateSel(val,item,rowIndex){
	var outNumber = item.baseNumber-(item.number1!=null?item.number1:0);
	if(outNumber <= 0){
		return '';
	}
	selItems[item.wiId] = item;
	var str = "<a onclick=\"selInvExtend('"+  item.wiId+"','"+item.momId+"','"+item.baseNumber+"', '" + item.splitNumber1 + "')\"  class='btn btn-info  btn-xs'>选择</a>";
	return str;
}

function selCode(){
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/storage/selOutOrderList"});
	var tt_win=$.supper("showWin",{url:att_url,title:"选择申领单信息",icon:"fa-th",width:800,height:500,BackE:function () {
		var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
		if(selCodeInfo != null && selCodeInfo.mooId != null){
			_mooId=selCodeInfo.mooId;
			meiArray=[];
			$("#relatedBill1").val(selCodeInfo.mooCode);
			$("#customerName").val(selCodeInfo.userName);
			$("#customer").val(selCodeInfo.groupName);
			$("#orderTime").val(selCodeInfo.orderTime);
            $('#allNumber').val(selCodeInfo.number1);
			loadGrid(selCodeInfo.mooId);
			/*win_form_search(selCodeInfo.mooId);
			$("#winMooId.val").val()*/
			$.supper("setProductArray", {"name":"selCodeInfo", "value":null});
		}
 	}}); 
}
function selInvExtend(wiId,momId,baseNumber,splitNumber1, wowMxId, wewMxId){
	if (wowType != undefined && (_moiId == undefined &&  _wiId == undefined && _wewId == undefined))
		return;
	var alNumber = $('#' + wiId + 'Inp').val();
	var alSplitNumber = $('#' + wiId + 'InpS').val();
	var vdata="wiId="+wiId + '&alNumber=' + alNumber + '&alSplitNumber=' + alSplitNumber;
	$.supper("setProductArray", {"name":"selInventoryExtendInfo", "value":selItems[wiId]});
	var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/storage/selInventoryExtendList","data":vdata});
	var tt_win=$.supper("showWin",{url:att_url,title:"选择库存信息",icon:"fa-th", area: ['90%', '90%'],width:1180,height:620,BackE:function () {

			var shouldClose = $.supper("getProductArray", "shouldClose");
			if (shouldClose != undefined && shouldClose == '1') {
				$.supper("setProductArray", {"name":"selInvExtend", "value":null});
				$.supper("setProductArray", {"name":"shouldClose", "value":null});
				closeWin();
				return;
			}
		var selInvExtendList = $.supper("getProductArray", "selInvExtend");
		if(selInvExtendList != null && selInvExtendList.length >0){
			if(meiArray !=null && meiArray.length > 0){
				for(var i = meiArray.length - 1;i >= 0;i--){
					if(meiArray[i].wiId==wiId){
						meiArray.splice(i, 1);
					}
				}
			}
			var counShu=0;
			var countShuSplit=0;
			for(var i =0;i < selInvExtendList.length;i++){
				if (wowType == 2 || wowType == 4) {
					if(selInvExtendList[i].shu <= 0 && selInvExtendList[i].shuSplit <= 0){
						continue;
					}
				}
				var ss={
						wiId:wiId,
						momId:momId,
						baseNumber:baseNumber,
					splitNumber1: (splitNumber1 == undefined || splitNumber1 == 'undefined') ? 0 : splitNumber1,
						mieId:selInvExtendList[i].mieId,
						shu:selInvExtendList[i].shu,
					shuSplit: selInvExtendList[i].shuSplit,
					leftNumber: null,
					leftSplitNumber: null,
					wewMxId: wewMxId,
					wowMxId: wowMxId
				};
				meiArray.push(ss);
				counShu+=parseInt(selInvExtendList[i].shu);
				countShuSplit+=parseInt(selInvExtendList[i].shuSplit);
			}
			$("#"+wiId+"Inp").val(counShu);
			$('#'+wiId+'InpS').val(countShuSplit);
			allAreadyNumberCount += counShu - alNumber;
			console.log("counShu"+counShu)
			console.log("111")
			$('#collectReadyAllCount').text(allAreadyNumberCount);
			$.supper("setProductArray", {"name":"selInvExtend", "value":null});
		}
 	}}); 
}


var saveAfter = 1;
function loadGrid(mooId, idx){
	let att_url = ''
	if (mooId == undefined) {
		att_url = $.supper("getServicePath", {
			service: "MdOutWarehouseMxService.getMdEnterWarehouseByWowId",
			data: "wowId=" + _wowId
		});
	}  else if (wowType == 2) {
			saveAfter = 1;
			att_url = $.supper("getServicePath", {
				service: "MdOrderMxService.getMdOrderMxListByMoiId",
				data: "moiId=" + mooId + '&wowId=' + _wowId
			});
		// att_url = $.supper("getServicePath", {
		// 	service: "MdOrderMxService.getMdOrderMxListByMoiId",
		// 	data: "moiId=" + mooId + '&wowId=' + _wowId
		// });
	} else if (wowType == 4){
		saveAfter = 2;
		att_url = $.supper("getServicePath", {
			service: "MdEnterWarehouseMxService.getMdEnterWarehouseMxListByWewId",
			data: "wewId=" + mooId + '&wowId=' + _wowId
		});
	} else if (wowType == 3) {
		// if (_wmsMiIds.length > 0) {
		//     data += 'wmsMiIds=' + _wmsMiIds.join(',');
		// }
		// if (_mmfIds.length > 0) {
		//     data += '&mmfIds=' + _mmfIds.join(',');
		// }
		att_url = $.supper("getServicePath", {
			service: "mdInventoryService.getInventoryMxList",
			data: 'wiIds=' + mooId + '&wowId=' + _wowId
		});

	}else {
		att_url = $.supper("getServicePath", {
			service: "MdOutOrderMxService.getOutOrderMxListByMooId",
			data: "mooId=" + mooId + '&wowId=' + _wowId
		});
	}

	// } else {
	// 	att_url = $.supper("getServicePath", {
	// 		service: "MdOutOrderMxService.getOutOrderMxListByMooId",
	// 		data: "mooId=" + mooId
	// 	});
	// }
	mmg.opts.url = att_url;
	mmg.load();
}

function refreshCount(e, data){
	if(data === null || data === undefined){
		return;
	}
	if(data.items === null || data.items === undefined){
		return;
	}
	$('#collectCount').text(data.items.length);
	// $('#collectReadyAllCount').text(data.items.length);
}

function initForm(){
	_rbbId = $.supper("getParam", _all_table_Id);
	_rbbId = _rbbId.trim();
	if (reload == false)
		_all_div_body.xform('createForm',_saveForm);
	if(_rbbId != undefined && _rbbId != null && CheckUtil.isInteger(_rbbId)){
		_rbbId = Number(_rbbId);
		var att_data=_all_table_Id+"="+_rbbId;
		_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questOrderAction,"data":att_data, BackE: function (jsondata) {
				$('#receivingObject').val(jsondata.obj.receivingObject);
				$("#relatedBill1").val(jsondata.obj.mooCode);
				// let number6 = jsondata.obj.number6 == undefined ? 0 : jsondata.obj.number6;
				// let number7 = jsondata.obj.number7 == undefined ? 0 : jsondata.obj.number7;
				// $('#number1').val(jsondata.obj.number1 + '/' + number6);
				// $('#missingNumber').val(jsondata.obj.missingNumber + '/' + (number6 - number7));
				// let number1 = jsondata.obj.number1;
				// number1 = number1 == undefined ? 0 : number1;
				// let missingNumber = jsondata.obj.missingNumber;
				// missingNumber = missingNumber == undefined ? 0 : missingNumber;
				// $('#collectReadyAllCount').text(missingNumber);
			}});
	}
	if (view == true){

	}else {
		getNewCode();
	}
	if (reload == false)
		initDataGrid();
	else
		win_form_search();
}

function initForm1(str, id) {
	// _wowId = $.supper("getParam", 'wowId');

	if (reload == false)
		_all_div_body.xform('createForm', _saveForm);
	if (id != undefined && id != null) {
		// _wowId = Number(_wowId);
		let att_data = '';
		if (_wowId != undefined)
			att_data = 'wowId=' + _wowId;
		else
			att_data = str + "=" + id;
		// if (str == 'moiId')
		_all_accountForm.xform('loadAjaxForm', {
			'ActionUrl': _all_questOrderAfterAction, "data": att_data, BackE: function (jsondata) {
				$('#wowCode').val(jsondata.obj.wowCode);
				$('#createRen1').val(jsondata.obj.createRen);
				$('#receivingObject').val(jsondata.obj.receivingObject);
				$('#outRemark').val(jsondata.obj.wowRemarks);
				// if (wowType == 3) {
				// 	let number6 = jsondata.obj.splitNumber == undefined ? 0 : jsondata.obj.splitNumber;
				// 	$('#number1').val(jsondata.obj.number1 + '/' + number6);
				// } else {
				// 	let number6 = jsondata.obj.splitNumber == undefined ? 0 : jsondata.obj.splitNumber;
				// 	$('#number1').val(jsondata.obj.number1 + '/' + number6);
				// }
			}
		});
		// else if (str == 'wiId')
		// 	_all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questOrderAfterAction,"data":att_data});
	}
	if (reload == false)
		initDataGrid();
	else
		win_form_search();
}

function initForm2(){
	// _rbbId = $.supper("getParam", _all_table_Id);
	// _rbbId = _rbbId.trim();
	if (reload == false)
		_all_div_body.xform('createForm',_saveForm);
	var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
	if (selCodeInfo != null && selCodeInfo.wowId != null) {

		var att_data = "wowId=" + selCodeInfo.wowId;
		_all_accountForm.xform('loadAjaxForm', {
			'ActionUrl': _all_questOrderWowIdAction, "data": att_data, BackE: function (jsondata) {
				$('#receivingObject').val(jsondata.obj.receivingObject);
				$("#relatedBill1").val(jsondata.obj.mooCode);
				// let number6 = jsondata.obj.number6 == undefined ? 0 : jsondata.obj.number6;
				// let number7 = jsondata.obj.number7 == undefined ? 0 : jsondata.obj.number7;
				// $('#number1').val(jsondata.obj.number1 + '/' + number6);
				// $('#missingNumber').val(jsondata.obj.missingNumber + '/' + (number6 - number7));
			}
		});

	}
	if (reload == false)
		initDataGrid();
	else
		win_form_search();
}

function initToolBar(){
	if (reload == false)
		_all_win_tools_but.xtoolbar('create',_Toolbar);
}
function initToolBar1(){
	if (reload == false)
		_all_win_tools_but.xtoolbar('create',_Toolbar);
}

function initCode(){
	var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
	if(selCodeInfo != null && selCodeInfo.mooId != null){
		_mooId=selCodeInfo.mooId;
		meiArray=[];
		$("#relatedBill1").val(selCodeInfo.mooCode);
		$("#customerName").val(selCodeInfo.userName);
		$("#customer").val(selCodeInfo.groupName);
		$("#orderTime").val(selCodeInfo.orderTime);
		$('#allNumber').val(selCodeInfo.number1);
		loadGrid(selCodeInfo.mooId);
		$.supper("setProductArray", {"name":"selCodeInfo", "value":null});
	}
}

function initCode1(id){
	var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
	if(selCodeInfo != null && selCodeInfo.wowId != null){
		// _wowId=selCodeInfo.wowId;
		meiArray=[];
		$("#relatedBill1").val(selCodeInfo.mooCode);
		$("#customerName").val(selCodeInfo.userName);
		$("#customer").val(selCodeInfo.groupName);
		$("#orderTime").val(selCodeInfo.orderTime);
		$('#allNumber').val(selCodeInfo.number1);
		$('#collectAllCount').text(selCodeInfo.number1);
		console.log("111")
		$('#collectReadyAllCount').text(selCodeInfo.number1);
		// if (moiId != undefined)
			loadGrid(id, 1);
		// else
		// 	loadGrid(wewId, 2);
		$.supper("setProductArray", {"name":"selCodeInfo", "value":null});
	}
}

function initCode2(id){
	var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
	if(selCodeInfo != null && selCodeInfo.wowId != null){
		// _wowId=selCodeInfo.wowId;
		meiArray=[];
		$("#wowCode").val(selCodeInfo.wowCode);
		$("#wowRemarks").val(selCodeInfo.wowRemarks);
		$("#receivingObject").val(selCodeInfo.receivingObject);
		$("#createRen1").val(selCodeInfo.createRen);
		$('#baseNumber').val($('#number1').val());

		// if (moiId != undefined)
		loadGrid(id, 1);
		// else
		// 	loadGrid(wewId, 2);
		$.supper("setProductArray", {"name":"selCodeInfo", "value":null});
	}
}

var url;
var newOrOne;
var wowType;
var view = false;
var view1 = false;
var orderDel = false;
var func = undefined;
$(function(){
	var selOutOrderType = $.supper("getProductArray", "addNewOut");
	if (selOutOrderType != undefined && selOutOrderType != null) {
		if (selOutOrderType.view == 1) {
			view = true;
			$('input').attr('readonly', 'readonly');
		}
		if ( selOutOrderType.view1 == 1) {
			view1 = true;
			$('input').attr('readonly', 'readonly');
		}
		if (selOutOrderType.orderDel == 1)
			orderDel = true;
		url = selOutOrderType.url;
		if (selOutOrderType.oth == undefined) {

			newOrOne = selOutOrderType.newOrOne;
			wowType = selOutOrderType.wowType;
			_wowId = selOutOrderType.wowId;
			_wiId = selOutOrderType.wiId;
			_moiId = selOutOrderType.moiId;
			_wewId = selOutOrderType.wewId;
		}
		if (selOutOrderType.func != undefined) {
			func = selOutOrderType.func;
		}
		// $.supper("setProductArray", {"name": "salesManLoan", "value": null});

	} else {
		$('input').attr('readonly', 'readonly');
	}
	initData();
});

function initData() {
	if (newOrOne != undefined) {
		$('#win_form_body').hide();
		$('#hide').hide();
	}

	if (wowType == 2){
		initFormHidden1();
		// if (_moiId != undefined)
		initForm1('moiId', _moiId);
		// else
		// 	initForm1('wewId', _wewId);
		initToolBar1();
		initCode1(_moiId, _wewId);
	} else if (wowType == 3) {
		initFormHidden1();
		initForm1('wiId', _wiId);
		initToolBar1();
		initCode1(_wiId);
	} else if (wowType == 4) {
		initFormHidden1();
		initForm1('wewId', _wewId);
		initToolBar1();
		initCode1(_wewId);
	}else {
		if (view == true && ($.supper("getParam", _all_table_Id) == undefined || $.supper("getParam", _all_table_Id) == '')) {
			initFormHidden1();
			initForm2('wewId', _wewId);
			initToolBar1();
			initCode2(_wewId);
		} else {
			initFormHidden();
			initForm(1);
			initToolBar();
			initCode(1);
		}
	}

	$("#win_but_save").css("width","95px");
	$("#win_but_add").css("width","95px");
	$("#win_but_add1").css("width","95px");

	if (view == true || view1 == true) {
		$('#win_but_save').hide();
		$('#win_but_add').hide();
	}

	$('#spmc').removeAttr('readonly');
	$('#gg').removeAttr('readonly');
}

function  closeWin(){
	console.log(url)

	 	// let url = 'dentistmall/storage/editApplyMdOutWarehouser.jsp';
		if (func != undefined)
			func();
		 $.supper("closeTtemWin", {url: url});

}

function saveOut() {
	if (wowType == 2 || wowType == 4) {
		saveAfterSaleOut();
	} else if (wowType == 3) {
		saveBroken();
	} else {
		save();
	}
}

function saveBroken() {
	var _all_saveAfterAction = "MdOutWarehouseService.saveEditMdOutWarehouse";
	var data = $('#saveCkForm').serialize();
	var rows = mmg.rows();
	var mieIds = "";
	var shus = "";
	var shuSplits = "";
	var momIds = "";
	var number1s = "";
	var splitQuatitys = '';
	var wowMxIds = '';
	let remarks = '';
	let avgPrices = '';
	let batchCodes = '';
	let valiedDates = '';
	let backPrintings = '';

	let zeroCount = 0;
	// if ($("#relatedBill1").val() == null || $("#relatedBill1").val() == "") {
	//     $.supper("alert", {title: "操作提示", msg: "请选择订单号！"});
	//     return;
	// }
	if (rows != null && rows.length > 0 && rows[0] != null) {
		var flag = false;
		for (var i = 0; i < rows.length; i++) {
			var mieId = rows[i].mieId;
			var shu = $("#" + mieId + "Inp").val();
			var shuSplit = $('#' + mieId + 'InpS').val();
			let sq = rows[i].baseNumber == undefined ? 0 : rows[i].baseNumber;
			if (shu <= 0 && shuSplit <= 0) {
				zeroCount ++;
			}
			if ((shu != null && shu != "" && shu > rows[i].quantity)) {
				$.supper("alert", {title: "操作提示", msg: "出库数量不能大于库存数量！"});
				return;
			} else if ((shuSplit != null && shuSplit != "" && shuSplit > sq)) {
				$.supper("alert", {title: "操作提示", msg: "最小出库数量不能大于对应库存数量！"});
				return;
			}
				// else if (shu != null && shu != "" && shu > rows[i].quantity) {
				//     $.supper("alert", {title: "操作提示", msg: "出库数量不能大于订单数量！"});
				//     return;
			// }
			else if ((shu != null && shu != "") || (shuSplit != null && shuSplit != "")) {
				flag = true;
				mieIds += mieId + ",";
				shus += shu + ",";
				shuSplits += shuSplit + ",";
				momIds += rows[i].momId + ",";
				number1s += (rows[i].quantity ? 0 : rows[i].quantity) + ",";
				splitQuatitys += (rows[i].splitQuantity == undefined ? 0 : rows[i].splitQuantity) + ',';
				wowMxIds += (rows[i].wowMxId == undefined ? -1 : rows[i].wowMxId) + ',';
				remarks += $('#remark' + mieId).val() + '##';
				avgPrices += $('#avgPrice' + mieId).val() + ',';
				batchCodes += $('#bc' + mieId).val() + ',';
				valiedDates += $('#vd' + mieId).val() + ',';
				backPrintings += $('#bp' + mieId).val() + ',';
			}


		}
		if (!flag) {
			$.supper("alert", {title: "操作提示", msg: "请输出库数量！"});
			return;
		}
		mieIds = mieIds.substring(0, mieIds.length - 1);
		shus = shus.substring(0, shus.length - 1);
		shuSplits = shuSplits.substring(0, shuSplits.length - 1);
		momIds = momIds.substring(0, momIds.length - 1);
		number1s = number1s.substring(0, number1s.length - 1);
		splitQuatitys = splitQuatitys.substring(0, splitQuatitys.length - 1);
		if (wowMxIds != '')
			wowMxIds = wowMxIds.substring(0, wowMxIds.length - 1);
		if (remarks != '')
			remarks = remarks.substring(0, remarks.length - 2);
		if (avgPrices != '')
			avgPrices = avgPrices.substring(0, avgPrices.length - 1);
		if (batchCodes != '')
			batchCodes = batchCodes.substring(0, batchCodes.length - 1);
		if (valiedDates != '')
			valiedDates = valiedDates.substring(0, valiedDates.length - 1);
		if (backPrintings != '')
			backPrintings = backPrintings.substring(0, backPrintings.length - 1);
	} else {
		$.supper("alert", {title: "操作提示", msg: "没有出库明细，不允许出库！"});
		return;
	}
	if (zeroCount >= rows.length) {
		$.supper("alert", {title: "操作提示", msg: "请输入出库数量！"});
		return;
	}
	data += "&shus=" + shus +
		"&shuSplits=" + shuSplits +
		"&mieIds=" + mieIds + "&momIds=" + momIds + "&companyType=3&number1s=" + number1s +
		'&splitQuantitys=' + splitQuatitys + '&remarks=' + remarks + '&avgPrices=' + avgPrices +
		'&batchCodes=' + batchCodes + '&valiedDates=' + valiedDates + '&backPrintings=' + backPrintings;
	if (wowMxIds != '')
		data += '&wowMxIds=' + wowMxIds;
	if (_wowId != undefined)
		data += '&wowId=' + _wowId;
	$.supper("doservice", {
		"service": _all_saveAfterAction,
		"ifloading": 1,
		"options": {"type": "post", "data": data + '&wowType=3'},
		"BackE": function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert", {
					title: "操作提示",
					msg: "操作成功！",
					BackE: closeWin
				});
			} else
				$.supper("alert", {
					title: "操作提示",
					msg: "操作失败！"
				});
		}
	});
}

function saveAfterSaleOut() {
	var _all_saveAfterAction = "MdOutWarehouseService.saveEditMdOutWarehouse";
	if (_all_div_body.xform('checkForm')) {
		var data = _all_accountForm.serialize();
		var rows = mmg.rows();
		var mieIds = "";
		var shus = "";
		var shuSplits = "";
		var momIds = "";
		var number1s = "";
		var splitQuatitys = '';
		var wowMxIds = '';
		var wewMxIds = '';
		let remarks = '';
		let avgPrices = '';
		if (wowType == 2 && ($("#relatedBill1").val() == null || $("#relatedBill1").val() == "")) {
			$.supper("alert", {title: "操作提示", msg: "请选择订单号！"});
			return;
		}
		let zeroCunt = 0;
		if (rows != null && rows.length > 0 && rows[0] != null) {
			var flag = false;
			for (var i = 0; i < rows.length; i++) {
				var mieId = rows[i].mieId;
				var wiId = rows[i].wiId;
				var shu = $("#" + wiId + "Inp").val();
				var shuSplit = $('#' + wiId + 'InpS').val();
				let sq1 = rows[i].splitQuantity1 == undefined ? 0 : rows[i].splitQuantity1;
				if (shu <= 0 && shuSplit <= 0) {
					zeroCunt ++;
				}
				if ((shu != null && shu != "" && shu > rows[i].quantity)) {
					$.supper("alert", {title: "操作提示", msg: "出库数量不能大于库存数量！"});
					return;
				} else if ((shuSplit != null && shuSplit != "" && shuSplit > sq1)) {
					$.supper("alert", {title: "操作提示", msg: "最小单位出库数量不能大于对应库存数量！"});
					return;
				} else if (shu != null && shu != "") {
					flag = true;
					// mieIds += meiArray[i].mieId + ",";
					// shus += shu + ",";
					// shuSplits += shuSplit + ",";
					// momIds += rows[i].momId + ",";
					// number1s += (rows[i].enterNumber == undefined ? 0 : rows[i].enterNumber == undefined) + ",";
					// splitQuatitys += (rows[i].splitQuantity == undefined ? 0 : rows[i].splitQuantity) + ',';
					// wowMxIds += (rows[i].wowMxId == undefined ? -1 : rows[i].wowMxId) + ',';
					// if (saveAfter == 2)
					// 	wewMxIds += (rows[i].wewMxId == undefined ? -1 : rows[i].wewMxId) + ',';
				}
				if (rows[i].enterNumber == undefined || (shu != null && shu != "" && shu > rows[i].enterNumber)) {
					$.supper("alert", {title: "操作提示", msg: "出库数量不能大于订单数量！"});
					return;
				}


			}
			if (!flag) {
				$.supper("alert", {title: "操作提示", msg: "请输出库数量！"});
				return;
			}
			if (zeroCunt >= rows.length) {
				$.supper("alert", {title: "操作提示", msg: "请输入出库数量！"});
				return;
			}
			// mieIds = mieIds.substring(0, mieIds.length - 1);
			// shus = shus.substring(0, shus.length - 1);
			// shuSplits = shuSplits.substring(0, shuSplits.length - 1);
			// momIds = momIds.substring(0, momIds.length - 1);
			// number1s = number1s.substring(0, number1s.length - 1);
			// splitQuatitys = splitQuatitys.substring(0, splitQuatitys.length - 1);
			// if (wowMxIds != '')
			// 	wowMxIds += wowMxIds.substring(0, wowMxIds.length - 1);
			// if (wewMxIds != '')
			// 	wewMxIds = wewMxIds.substring(0, wewMxIds.length - 1);
		} else {
			$.supper("alert", {title: "操作提示", msg: "没有出库明细，不允许出库！"});
			return;
		}
		for (var i = 0; i < meiArray.length; i++) {
			mieIds += meiArray[i].mieId + ",";
			shus += $("#" + meiArray[i].wiId + "Inp").val() + ',';//meiArray[i].shu + ",";
			shuSplits += $('#' + meiArray[i].wiId + 'InpS').val() + ',';//meiArray[i].shuSplit + ',';
			momIds += meiArray[i].momId + ",";
			number1s += meiArray[i].baseNumber + ",";
			splitQuatitys += (meiArray[i].splitNumber1 == undefined ? 0 : meiArray[i].splitNumber1) + ',';
			wowMxIds += (meiArray[i].wowMxId == undefined ? -1 : meiArray[i].wowMxId) + ',';
			if (saveAfter == 2)
				wewMxIds += (meiArray[i].wewMxId == undefined ? -1 : meiArray[i].wewMxId) + ',';
			remarks += $('#remark' + meiArray[i].wiId).val() + '##';
			avgPrices += $('#avgPrice' + meiArray[i].wiId).val() + ',';
			// wiIds += meiArray[i].wiId + ",";
			// leftNumbers += meiArray[i].leftNumber + ",";
			// leftSplitNumbers += meiArray[i].leftSplitNumber + ',';
		}
		mieIds = mieIds.substring(0, mieIds.length - 1);
		shus = shus.substring(0, shus.length - 1);
		shuSplits = shuSplits.substring(0, shuSplits.length - 1);
		momIds = momIds.substring(0, momIds.length - 1);
		number1s = number1s.substring(0, number1s.length - 1);
		splitQuatitys = splitQuatitys.substring(0, splitQuatitys.length - 1);
		if (wowMxIds != '')
			wowMxIds = wowMxIds.substring(0, wowMxIds.length - 1);
		if (wewMxIds != '')
			wewMxIds = wewMxIds.substring(0, wewMxIds.length - 1);
		data += "&shus=" + shus +
			"&shuSplits=" + shuSplits +
			"&mieIds=" + mieIds + "&momIds=" + momIds + "&companyType=2&number1s=" + number1s +
			'&splitQuantitys=' + splitQuatitys;
		if (wowMxIds != '')
			data += '&wowMxIds=' + wowMxIds;
		if (_wowId != undefined)
			data += '&wowId=' + _wowId;
		if (saveAfter == 2) {
			data += '&companyType=4&wowType=4';
			data += '&wewMxIds=' + wewMxIds;
		}
		else
			data += '&companyType=2&wowType=2';
		if (remarks != '')
			remarks = remarks.substring(0, remarks.length - 2);
		if (avgPrices != '')
			avgPrices = avgPrices.substring(0, avgPrices.length - 1);
		data += '&remarks=' + remarks;
		data += '&avgPrices=' + avgPrices;
		$.supper("doservice", {
			"service": _all_saveAfterAction,
			"ifloading": 1,
			"options": {"type": "post", "data": data},
			"BackE": function (jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert", {
						title: "操作提示",
						msg: "操作成功！",
						BackE: closeWin
					});
				} else
					$.supper("alert", {
						title: "操作提示",
						msg: "操作失败！"
					});
			}
		});
	}
}
 
function save() {
	if (_all_div_body.xform('checkForm')) { 
		var data = _all_accountForm.serialize();
		data += '&' + $('#saveForm').serialize();
		var rows=mmg.rows();
		
		if($("#mooCode").val()==null || $("#mooCode").val()==""){
			$.supper("alert", {title : "操作提示",msg : "请选择申领单号！"});
			return;
		}
		if(rows != null && rows.length > 0 && rows[0] != null){
			var flag=false;
			var countNotEnough = 0; // 库存
			var leftZeroCount = 0; // 已结束
			var zeroCount = 0;
			for(var i=0;i < rows.length;i ++){
				var wiId=rows[i].wiId;

				var ratio=$("#"+wiId+"ratio").val();
				var shu = $("#"+wiId+"Inp").val();
				var shuSplit = $('#'+wiId+'InpS').val();
				var cha = rows[i].baseNumber-(rows[i].number1!=null?rows[i].number1:0);
				var splitCha = rows[i].splitNumber1 - (rows[i].number6 != undefined ? rows[i].number6 : 0);

				var baseNumber1=rows[i].baseNumber;
				var splitNumber1=rows[i].splitQuantity;
				// var var

				if (shu != null && shu != "" && ((parseInt(shu) * parseInt(ratio) + parseInt(shuSplit)) > (parseInt(cha) * parseInt(ratio) + parseInt(splitCha)))) {
					$.supper("alert", {title: "操作提示", msg: "对应出库数量不能大于对应申领数量！"});
					return;
				}
				if (shuSplit != null && shuSplit != "" && (shuSplit - (rows[i].number6!=null?rows[i].number6:0)) > 0) {
					$.supper("alert", {title: "操作提示", msg: "对应出库数量不能大于对应申领数量！"});
					return;
				}

				// alert(splitNumber1);
				let sq = rows[i].splitQuantity == undefined ? 0 : rows[i].splitQuantity;
				if(cha <= 0 && splitCha <= 0){
					leftZeroCount ++;
					continue;
				}
				if (rows[i].quantity <= 0 && (rows[i].splitQuantity == undefined || (rows[i].splitQuantity != undefined && rows[i].splitQuantity <= 0))) {
					countNotEnough++;
				}
				// if ((shu != null && shu != "" && shu > rows[i].quantity)) {
				// 	$.supper("alert", {title: "操作提示", msg: "出库数量不能大于库存数量！"});
				// 	return;
				// } else if ((shuSplit != null && shuSplit != "" && shuSplit > sq)) {
				// 	$.supper("alert", {title: "操作提示", msg: "最小单位出库数量不能大于对应库存数量！"});
				// 	return;
				// } else if ((shu != null && shu != "" && shu > cha)) {
				// 	$.supper("alert", {title: "操作提示", msg: "出库数量不能大于申领数量！"});
				// 	return;
				// } else if ((shuSplit != null && shuSplit != "" && shuSplit > splitCha)) {
				// 	$.supper("alert", {title: "操作提示", msg: "对应出库数量不能大于对应申领数量！"});
				// 	return;
				// } else if (shu > 0 || shuSplit > 0) {
				// 	flag = true;
				// }
				// alert(splitCha);
				if (shu != null && shu != ""&&(parseInt(shu)*parseInt(ratio)+parseInt(shuSplit))>(parseInt(rows[i].quantity)*parseInt(ratio)+parseInt(sq))){
					$.supper("alert", {title: "操作提示", msg: "出库数量不能大于库存数量！"});
					return;
				}
				else if (shu != null && shu != ""&&parseInt(shu)*parseInt(ratio)+parseInt(shuSplit)>(parseInt(baseNumber1)*parseInt(ratio)+parseInt(splitNumber1))) {
					$.supper("alert", {title: "操作提示", msg: "出库数量不能大于申领数量！"});
					return;
				}
				else if (shu > 0 || shuSplit > 0) {
						flag = true;
					}
				if ((shu == null || shu <= 0) && (shuSplit == null || shuSplit <= 0)) {
					zeroCount++;
				}
			}
			// return;
			if (countNotEnough == rows.length || countNotEnough == (rows.length - leftZeroCount)){
				$.supper("alert", {title : "操作提示",msg : "库存数量不足，无法出库！"});
				return;
			}
			if (leftZeroCount >= rows.length) {
				flag = true;
			}
			if(!flag){
				$.supper("alert", {title : "操作提示",msg : "请输入出库数量！"});
				return;
			}
			if(zeroCount >= rows.length){
				$.supper("alert", {title : "操作提示",msg : "请输入出库数量！"});
				return;
			}
		}else{
			$.supper("alert", {title : "操作提示",msg : "没有出库明细，不允许出库！"});
			return;
		}
		var mieIds="";
		var shus="";
		var shuSplits="";
		var momIds="";
		var number1s="";
		var splitQuatitys = '';
		var wiIds="";
		var leftNumbers = "";
		var leftSplitNumbers = '';
		let remarks = '';
		let avgPrices = '';
		for(var i =0 ;i < meiArray.length ;i++){
			mieIds+=meiArray[i].mieId+",";
			shus += $("#" + meiArray[i].wiId + "Inp").val() + ',';//meiArray[i].shu + ",";
			shuSplits += $('#' + meiArray[i].wiId + 'InpS').val() + ',';//meiArray[i].shuSplit + ',';
			momIds += meiArray[i].momId+",";
			number1s += meiArray[i].baseNumber+",";
			splitQuatitys += (meiArray[i].splitNumber1 == undefined ? 0 : meiArray[i].splitNumber1) + ',';
			wiIds += meiArray[i].wiId + ",";
			leftNumbers += meiArray[i].leftNumber + ",";
			leftSplitNumbers += meiArray[i].leftSplitNumber + ',';
			remarks += $('#remark' + meiArray[i].wiId).val() + '##';
			avgPrices += $('#avgPrice' + meiArray[i].wiId).val() + ',';
		}
		mieIds=mieIds.substring(0,mieIds.length-1);
		shus=shus.substring(0,shus.length-1);
		shuSplits=shuSplits.substring(0,shuSplits.length-1);
		momIds=momIds.substring(0,momIds.length-1);
		number1s=number1s.substring(0,number1s.length-1);
		splitQuatitys = splitQuatitys.substring(0, splitQuatitys.length - 1);
		wiIds=wiIds.substring(0,wiIds.length-1);
		leftNumbers=leftNumbers.substring(0,leftNumbers.length-1);
		leftSplitNumbers=leftSplitNumbers.substring(0, leftSplitNumbers.length - 1);
		if (remarks != '')
			remarks = remarks.substring(0, remarks.length - 2);
		if (avgPrices != '')
			avgPrices = avgPrices.substring(0, avgPrices.length - 2);
		data += "&shus="+shus+
			"&shuSplits="+shuSplits+
			"&mieIds="+mieIds+"&momIds="+momIds+"&companyType=1&number1s="+number1s +
			'&splitQuatitys=' + splitQuatitys +
			"&wiIds="+wiIds+'&leftNumbers=' + leftNumbers +
			'&leftSplitNumbers=' + leftSplitNumbers +
			'&wowType=1' + '&remarks=' + remarks + '&avgPrices=' + avgPrices;
		$.supper("doservice", {
			"service" : _all_saveAction,
			"ifloading" : 1,
			"options":{"type":"post","data":data},
			"BackE" : function(jsondata) {
				if (jsondata.code == "1") {
					$.supper("alert", {
						title : "操作提示",
						msg : "操作成功！",
						BackE : function (){
							isSave = true;
							closeWin();
						}
					});
				} else
					$.supper("alert", {
						title : "操作提示",
						msg : "操作失败！"
					});
			}
		});
	}
}
function main_export(){
	var vdata='';
	if (_mooId!=null) {
		vdata="mooId="+_mooId;
	}else if (_wowId!=null){
		vdata="wowId="+_wowId;
	}

	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"enterWarehouseExportService.exportOutWarehousing","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

function win_form_search(){
	allNumberCount=0;
	allAreadyNumberCount = 0;
	$('#collectAllCount').text(allNumberCount);
	console.log("222")
	$('#collectReadyAllCount').text(allAreadyNumberCount);
	var spmc=$('#spmc').val(); 
	var gg=$('#gg').val(); 
	var mooId=_mooId;
	let str = '';
	let att_urll = '';
	if (spmc != null && spmc != undefined && spmc != "" || gg != null && gg != undefined && gg != "") {
		var spmc = $('#spmc').val(); // + ",";
		var gg = $('#gg').val(); //+",";
		// var mooId = _mooId;
		str = spmc + ',' + gg; //+_mooId
	}
	let data = 'wowId=' + _wowId;
	if (str != '')
		data += '&str=' + str;
	if (wowType == 2) {
		data += '&moiId=' + _moiId;
		att_urll = $.supper("getServicePath", {
			service: "MdOrderMxService.getMdOrderMxListByMoiId",
			data: data
		});
	} else if (wowType == 4) {
		if (spmc != '')
			data += '&matName=' + spmc;
		if (gg != '')
			data += '&mmfName=' + gg;
		data += '&wewId=' + _wewId;
		att_urll = $.supper("getServicePath", {
			service: "MdEnterWarehouseMxService.getMdEnterWarehouseMxListByWewId",
			data: data
		});
	} else if (wowType == 3)  {
		// if (_wmsMiIds.length > 0) {
		//     data += 'wmsMiIds=' + _wmsMiIds.join(',');
		// }
		// if (_mmfIds.length > 0) {
		//     data += '&mmfIds=' + _mmfIds.join(',');
		// }
		data += '&wiIds=' + _wiId;
		att_urll = $.supper("getServicePath", {
			service: "mdInventoryService.getInventoryMxList",
			data: data
		});

	} else if (mooId == undefined) {
		data += '&wowId=' + _wowId;
		att_urll = $.supper("getServicePath", {
			service: "MdOutWarehouseMxService.getMdEnterWarehouseByWowId",
			data: data
		});
	} else{
		data += '&mooId=' + mooId;
		if (spmc != '')
			data += '&matName=' + spmc;
		if (gg != '')
			data += '&mmfName=' + gg;
		// data += '&matName=' + spmc
		att_urll = $.supper("getServicePath", {
			service: "MdOutOrderMxService.getOutOrderMxListByMooId",
			data: data
		});
	}

	// var att_urll = $.supper("getServicePath", {
	// 	service: "MdOutOrderMxService.getMdOutOrderMxListByName",
	// 	data: "str=" + str
	// });
	mmg.opts.url = att_urll;
	// }else{
	// 	var att_url=$.supper("getServicePath", {service:"MdOutOrderMxService.getOutOrderMxListByMooId",data:"mooId="+_mooId});
	// 	mmg.opts.url = att_urll;
	// }
	mmg.load();
}
/*function win_form_search(){
	if($('#spmc').val() !== null && $('#spmc').val() !== undefined){
		var str="&spmc="+$('#spmc').val();
	}
	if ($('#gg').val() !== null && $('#gg').val() !== undefined) {
		var str="&gg="+$('#gg').val()+str;
		vdata=str;
	}
	//alert(vdata);
	$.supper("doservice", {"service":"MdOutOrderMxService.getMdOutOrderMxListByName","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			$.supper("alert",{ title:"操作提示", msg:"操作成功！",});
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}
	});
	var att_url= $.supper("getServicePath", {service:"MdOutOrderMxService.getMdOutOrderMxListByName",data:"mooId="+str});
	mmg.opts.url = att_url;
    mmg.load();
}*/
	/*
	 * var wowIds="";
	for ( var int = 0; int < rows.length; int++) {
		wowIds += rows[int].wowId+",";
	}
	wowIds=wowIds.substring(0,wowIds.length-1);
	vdata="&wowIds="+wowIds;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOutWarehouseService.exportApplyListPi","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});*/




function getNewCode() {
	$.supper("doservice", {
		"service": "SysParameterService.getNewCode",
		"options": {
			"type": "post",
			"data": "prefix=CK",
			"async": false
		}, "ifloading": 1, "BackE": function (jsondata) {
			if (jsondata.code == "1" && jsondata.obj) {
				$("#wowCode").val(jsondata.obj);
			}
		}
	});
}
