var _all_accountForm = $("#accountForm");
var _all_win_searchForm = $("#win_form_search");
var _all_div_hidden = $("#win_form_edithidden");
var _all_div_body = $("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");
var meiArray = [];
var needTips = [];
var layerTips = null;
var largeUnit;
var smallUnit;
/***
 * 修改部分begin
 */
var _rbbId;
var _mooId;
// var _all_table_Id="wewId";
var _all_table_Id = "mooId";
var _all_saveAction = "MdOutWarehouseService.saveMdOutWarehouse";
var _all_questAction = "MdOutWarehouseService.findFormObject";

var _all_edit_icon = "gears";

var _all_questOrderAction = 'mdOutOrderService.findFormObject';
var _saveForm = {
    lineNum: 4,
    columnNum: 2,
    control: [],
    groupTag: [],
    hiddenitems: [
        {name: "wowId", id: "wowId", value: "", type: "hidden"},
        {name: "rbaId", id: "rbaId", value: "", type: "hidden"},
        {name: "rbsId", id: "rbsId", value: "", type: "hidden"},
        {name: "rbbId", id: "rbbId", value: "", type: "hidden"},
        // {name:'orderTime',id:"orderTime",value:"",type:"hidden"},
        // {title:'创建时间' ,name:'createDate',type:"hidden"},
        {title: '创建人', name: 'createRen', type: "hidden"},
        {title: '修改时间', name: 'editDate', type: "hidden"},
        {title: '修改人', name: 'editRen', type: "hidden"},
        {title: '出库单号', name: 'wowCodeName', type: "hidden", prefixCode: "CK"},
        // {title:'申领单号', name:'relatedBill1', type: 'hidden'},
    ],
    items: [
        //
        //{title:'申领单号', name:'relatedBill1Div', placeholder:"申领单号" ,type:'userdefined',width:80,  align:'center',renderer:initSel },
        {title: '申领单号', name: 'mooCode', placeholder: "申领单号", width: 40, align: 'center', readOnly: true,},
        {title: '申领时间', name: 'createDate', readOnly: true, width: 40, align: 'center'},
        {title: '申领部门', name: 'groupName', width: 40, readOnly: true, placeholder: "申领部门", align: 'center'},
        {title: '申领人', name: 'userName', width: 40, readOnly: true, placeholder: "申领人", align: 'center'},
        {title: '申领总数量', name: 'number1', readOnly: true, width: 40, align: 'center'},
        {title: '需要出库数量', name: 'missingNumber', readOnly: true, placeholder: '0', width: 40, align: 'center'},
        {title: '备注', name: 'remarks', readOnly: true, placeholder: "备注", type: "textarea"}

    ]
};
// function total() {
//     let str;
//     str += '<input id="number1">' + number1 +'/'+number6+'</input>'
//     return str;
// }
function initSel() {
    var str = "<input type=\"text\" id=\"relatedBill1\" class=\"form-control2\" readonly name=\"relatedBill1\" aria-required=\"true\" aria-invalid=\"false\" placeholder=\"申领编号\"/>&nbsp;&nbsp;&nbsp;&nbsp;"
    str += "<a class=\"btn btn-danger btn-xs\" id=\"selCodeBut\" onclick=\"selCode()\">选择</a>";
    return str;
}

var _Toolbar = {
    toolBarId: "win_edit_toolbar",
    items: [
        {title: "保存", id: "win_but_save", icon: "save", colourStyle: "primary", clickE: saveOut},
        {title: "返回", id: "win_but_add", icon: "close", colourStyle: "default", clickE: backTo},
        // {title: "返回", id: "win_but_add", icon: "close", colourStyle: "default", clickE: closeWin},

        // {title: "导出", id: "win_but_add1", icon: "bolt", colourStyle: "success", clickE: main_export},
        // {title: "关闭", id: "win_but_add", icon: "close", colourStyle: "default", clickE: closeWin},
        //增加导出  导出详情中的全部
        // {title:"导出",id:"win_but_add",icon:"bolt", colourStyle:"success",clickE:main_export}
        //{title:"导出",id:"win_but_add",icon:"bolt", colourStyle:"success",clickE:main_export}
    ]
};
$(function () {


})
var outType = -1; // 0领料出库，1退货出库，2破损出库
// var idx1=0;
function outEnter(idx) {
    // outType=idx;
    // changeColor();
    // alert(mmg.rowsLength());
    if (mmg.rowsLength() > 0) {
        $.supper("confirm", {
            title: "操作提示", msg: "存在未保存的数据，是否跳转？", yesE: function () {
                selEnter(idx);
            }
        });
    } else {
        selEnter(idx);
        // changeColor();
    }
    // if (idx!=idx1&&mmg.rowsLength() > 0){
    //
    // }
    // idx1=idx;
}

function selEnter(idx) {
    meiArray = [];
    getNewCode();
    needNumber = 0;
    allNumberCount = 0;
    allAreadyNumberCount = 0;
    if (idx == 0) {
        selOutOrderInfo();
    } else if (idx == 1) {
        selAfterSale();

    } else {
        selBroken();
    }
}

function initFormHidden() {
    _all_div_hidden.xform('createhidden', _saveForm.hiddenitems);

}

//2020年07月07日09:16:26朱燕冰修改根据状态改变按钮颜色
function changeColor() {
    if (outType == 0) {
        $("#colors1").css("background-color", "#ffffff");
        $("#colors1").css("color", "#212a31");
    } else {
        $("#colors1").css("background-color", "#1ab394");
        $("#colors1").css("color", "#ffffff");
    }
    if (outType == 1) {
        $("#colors2").css("background-color", "#ffffff");
        $("#colors2").css("color", "#212a31");
    } else {
        $("#colors2").css("background-color", "#1ab394");
        $("#colors2").css("color", "#ffffff");
    }
    if (outType == 2) {
        $("#colors3").css("background-color", "#ffffff");
        $("#colors3").css("color", "#212a31");
    } else {
        $("#colors3").css("background-color", "#1ab394");
        $("#colors3").css("color", "#ffffff");
    }
}

// 获取出库订单信息
function selOutOrderInfo() {
    // $.supper("setProductArray", {"name": "selCodeInfo", "value": item});
    // eval("var data= '"+_all_table_Id+"= " + item.mooId+ "'");
    var att_url = $.supper("getServicePath", {"data": '', url: "/jsp/dentistmall/storage/out/selOutOrderInfo.jsp"});
    $.supper("setProductArray", {"name": "addNewOut", "value": {url: att_url}});
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: '选择领料单',
        icon: "fa-" + _all_edit_icon,
        width: 1300,
        height: 550,
        BackE: function () {
            var selCodeInfo = $.supper("getProductArray", "selCodeInfo"); // 获取到的moocode\
            if (selCodeInfo != undefined && selCodeInfo != null && Object.prototype.toString.call(selCodeInfo) !== '[object Object]') {
                outType = 0;
                $('#outType').text('领料出库');
                $('#afterSale').hide();
                $('#win_form_body').show();
                $('#hide').show();
                _mooId = selCodeInfo;
                initFormHidden();
                initForm();
                loadGrid(selCodeInfo);
                changeColor();
            }
            $.supper("setProductArray", {"name": "selCodeInfo", "value": null});
        }
    });
}

// 获取采购售后信息
function selAfterSale() {
    // $.supper("setProductArray", {"name": "selCodeInfo", "value": item});
    // eval("var data= '" + _all_table_Id + "= " + item.mooId + "'");
    var att_url = $.supper("getServicePath", {
        url: "/jsp/dentistmall/transaction/selOrderInfoList",
        "data": "selType=2"
    });
    // var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
    // var att_url = $.supper("getServicePath", {"data": data, url: "/jsp/dentistmall/storage/out/selOutOrderInfo.jsp"});
    $.supper("setProductArray", {"name": "addNewOut", "value": {url: att_url}});
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: '选择退货商品',
        icon: "fa-" + _all_edit_icon,
        width: 1300,
        height: 600,
        BackE: function () {
            var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
            if (selCodeInfo != null && (selCodeInfo.moiId != undefined || selCodeInfo.wewId != undefined)) {
                outType = 1;
                initDataGrid();
                changeColor()
                $('#outType').text('退货出库');
                $('#afterSale').show();
                $('#win_form_body').hide();
                $('#hide').hide();
                _all_div_body.hide();
                $("#relatedBill1").val(selCodeInfo.relationBillCode);
                $("#supplierName").val(selCodeInfo.applicantName);
                $("#consignee").val(selCodeInfo.purchaseAccount);
                $("#orderTime").val(selCodeInfo.placeOrderTime);
                if (selCodeInfo.moiId != undefined) {
                    initAfterForm('moiId', selCodeInfo.moiId);
                    loadGrid(selCodeInfo.moiId, 1);
                } else {
                    initAfterForm('wewId', selCodeInfo.wewId);
                    loadGrid(selCodeInfo.wewId, 2);
                }

            }
            $.supper("setProductArray", {"name": "selCodeInfo", "value": null});
            // loadGrid();
        }
    });
}

function initAfterForm(str, id) {
    let data = str + '=' + id;
    let service = 'mdEnterWarehouseService.findFormObjectNewApply';
    // if (str == 'moiId')
    //     service = '';
    // else
    //     service = '';
    $.supper("doservice", {
        "service": service,
        "ifloading": 1,
        "data": data,
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                let val = jsondata.obj;
                $("#accountForm span").each(function (i, v) {
                    var att_name = $(v).attr("name");
                    eval("var vals=val." + att_name + ";");
                    if (vals) {
                        $(v).text(vals);
                        $(v).val(vals);
                    }
                    if (att_name == 'billType') {
                        if (vals == 1)
                            $(v).text('物料入库');
                        else
                            $(v).text('订单入库');
                    }
                });
            }
        }
    });
}

// 获取破损
function selBroken() {
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/out/selInventoryInfo"});
    var tt_win = $.supper("showWin", {
        url: att_url, title: "选择库存信息", icon: "fa-flask", width: 1200, height: 540, BackE: function () {
            var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
            if (selCodeInfo != null && selCodeInfo.wiId != null) {
                outType = 2;
                changeColor();
                initDataGrid();
                $('#outType').text('报损出库');
                $('#afterSale').hide();
                $('#win_form_body').hide();
                $('#hide').hide();
                _all_div_body.hide();
                // $("#relatedBill1").val(selCodeInfo.orderCode);
                // $("#supplierName").val(selCodeInfo.applicantName);
                // $("#consignee").val(selCodeInfo.purchaseAccount);
                // $("#orderTime").val(selCodeInfo.placeOrderTime);
                loadGrid(selCodeInfo.wiId);
            }
            $.supper("setProductArray", {"name": "selCodeInfo", "value": null});
            // loadGrid();
        }
    });
    // selectMat();
    // $.supper("setProductArray", {"name": "selCodeInfo", "value": item});
    // eval("var data= '" + _all_table_Id + "= " + item.mooId + "'");
    // var att_url = $.supper("getServicePath", {"data": data, url: "/jsp/dentistmall/storage/out/selBrokenInfo.jsp"});
    // $.supper("setProductArray", {"name": "addNewOut", "value": {url: att_url}});
    // var tt_win = $.supper("showWin", {
    //     url: att_url,
    //     title: '选择物料',
    //     icon: "fa-" + _all_edit_icon,
    //     width: 1300,
    //     height: 800,
    //     BackE: function () {
    //         loadGrid();
    //     }
    // });
}

function selectMatBefore(value) {
    if (value == '') {
        value = $('#selectMat').val();
    }

    if (event.keyCode == 13) {
        selectMat(value);
    }
}

function selectMatBeforeBtn() {
    let value = $('#selectMat').val();
    selectMat(value);
}

function selectMat(value) {
    $.supper("setProductArray", {"name": "searchName", "value": {searchName: value}});

    var att_url = $.supper("getServicePath", {"data": '', url: "/jsp/dentistmall/modifyprice/selMateriel"});
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: "物料信息",
        icon: "fa-bars",
        width: "1250",
        height: '700',
        BackE: function () {
            addMat();
        }
    });
}

var _wmsMiIds = [];
var _mmfIds = [];

function addMat() {
    let selPartAndNorm = $.supper("getProductArray", "selPartAndNorm");
    let data;
    if (selPartAndNorm != undefined && selPartAndNorm != null) {
        if (selPartAndNorm.data == '') {
        } else {
            data = selPartAndNorm.data;

            if (_wmsMiIds.indexOf(data.wmsMiId) < 0) {
                _wmsMiIds.push(data.wmsMiId);
            }
            if (_mmfIds.indexOf(data.mmfId) < 0) {
                _mmfIds.push(data.mmfId);
            }
            loadGrid(data);

        }
    }
    $.supper("setProductArray", {"name": "selPartAndNorm", "value": null});
}

//退货出库  报损出库
var mmg;
var initDataGrid = function () {
    if (outType == 2) {
        //退货出库  破损出库 列表
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
            {title: '备注', name: 'remarks', width: 180, align: 'center', renderer:renderRemarks},
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
    } else {
        //领料出库 列表
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
        if (outType != 0) {
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
    // if (outType == 0)
    //     pv = item.valiedDate1 == undefined ? '' : item.valiedDate1;
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
    str += '<span>' + basicUnit + '</span>-';
    if (outType == 0) {
        bNumber = (item.splitQuantity1 == undefined ? 0 : item.splitQuantity1) - num;
        str += '<span>' + bNumber + '</span>';
    } else if (outType == 1 && saveAfter == 1) {
        bNumber = (item.splitQuantity == undefined ? 0 : item.splitQuantity) - num;
        str += '<span>' + bNumber + '</span>';
    } else if (outType == 2) {
        bNumber = (item.baseNumber == undefined ? 0 : item.baseNumber) - num;
        str += '<span>' + bNumber + '</span>';
    } else if (outType == 1 && saveAfter == 2) {
        bNumber = (item.splitQuantity1 == undefined ? 0 : item.splitQuantity1) - num;
        str += '<span>' + bNumber + '</span>';
    } else {
        bNumber = (item.splitQuantity1 == undefined ? 0 : item.splitQuantity1) - num;
        str += '<span>' + bNumber + '</span>';
    }
    let productName = item.productName == undefined ? '' : item.productName;
    // if (outType == 1)
    //     productName = item.packageWay == undefined ? '' : item.packageWay;
    str += '<span>' + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit) + '</span>';
    str += '/<span>' + productName + '</span>';
    return str;
}

function control(val, item, rowIndex) {
    let str = '';
    if (delObj[item.wiId] == undefined)
        delObj[item.wiId] = 0;
    delObj[item.wiId] = rowIndex;
    str += '<a class="btn btn-white btn-xs" onclick="del(' + item.wiId + ', ' + rowIndex + ')">删除</a>';
    return str;
}

var delObj = {};

function del(wiId, rowIndex) {
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
}

function renderLogo(val, item, rowIndx) {
    let str = '';
    if (item.logoPath == undefined)
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
    // if (item.stype == 1) {
    // str += toDecimal2(item.number * item.price);
    // }
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

//缺少数量
function formateMissNumberInp(val, item, rowIndex) {
    if (outType != 0) {
        return 0;
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
    // // var leftNumber = item.baseNumber - (item.number1 != null ? item.number1 : 0);
    let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    // if (minusBaseNumber > 0) {
    //     tt = '<span style="color: red;">' + minusBaseNumber + '</span>' + basicUnit;
    // }
    // tt += '';
    // // var leftSplitN = (item.splitQuantity == undefined ? 0 : item.splitQuantity) - (item.splitNumber1 != undefined ? item.splitNumber1 : 0);
    // if (minusNumber1 >= 0)
    //     tt += '<span style="color: red;">' + minusNumber1 + '</span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit);
    // return tt;

    var leftNumber = (item.baseNumber == undefined ? 0 : item.baseNumber) - (item.number1 != undefined ? item.number1 : 0);
    if (leftNumber > 0) {
        tt = '<span style="color: red;">' + leftNumber + '</span>';// + basicUnit;
    } else {
        tt = '<span style="color: red;">' + 0 + '</span>';// + basicUnit;
    }
    // tt += '';
    // var leftSplitN = (item.splitQuantity == undefined ? 0 : item.splitQuantity) - (item.splitNumber1 != undefined ? item.splitNumber1 : 0);
    // if (leftSplitN >= 0)
    //     tt += '<span style="color: red;">' + leftSplitN + '</span>' + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit);
    // else
    //     tt += '<span style="color: red;">' + 0 + '</span>' + (((item.unit == undefined || item.unit == '') || item.unit == '') ? basicUnit : item.unit);
    return tt;
}

var needNumber = 0;
var allNumberCount = 0;
var allAreadyNumberCount = 0;

function formatMatNum(val, item, rowIndex) {
    if (outType != 0) {
        return '--';
    }
    var tt = item.baseNumber - (item.number1 != null ? item.number1 : 0);
    needNumber += tt;
    if (needNumber <= 0) {
        needNumber = 0;
    }
    $('#needNumber').val(needNumber);
    allNumberCount += item.baseNumber;
    // $('#collectAllCount').text(allNumberCount);
    let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    return item.baseNumber + basicUnit + (item.splitQuantity == undefined ? 0 : item.splitQuantity) + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit);
}

function controlInfo(val, item, rowIndex) {
    var str = "";
    if (item.wmsMiId != undefined)
        str += "<a href=\"" + $.supper("getbasepath") + "xiangxi.htm?wmsMiId=" + item.wmsMiId + "\" target=\"_blank\" title=\"" + item.matName + "\">" + item.matName + "</a> ";
    else
        str += item.matName;
    return str;
}

function changeSplit(value, mieId, ratio) {
    // ratio = ratio == undefined ? 1 : ratio;
    // value = Number(value);
    // $('#' + mieId + 'InpS').val(value * ratio);
}

function formateInp(val, item, rowIndex) {
    var tt = '';
    var color = '';
    let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    let ratio = item.ratio == undefined ? 1 : item.ratio;
    if (outType == 2) {
        if (item.quantity <= 0) {
            color = 'color:red';
            tt = "<input class='input-text' type=\"text\" id=\"" + item.mieId + "Inp\" value='0' style=\"width:60px;text-align:center;" + color + "\" />";
        }
        else
            tt = "<input class='input-text' type=\"text\" id=\"" + item.mieId + "Inp\" value='0' style=\"width:60px;text-align:center;" + color + "\" />";
        // onkeyup='changeSplit(this.value, " + item.mieId + ", " + item.ratio + ")'
        // tt += '<span>' + basicUnit + '</span>';
        tt += "<input class='input-text' type=\"hidden\" id=\"" + item.mieId + "InpS\" value='0' style=\"width:60px;text-align:center;" + color + "\" />";
        // tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
        if (item.quantity <= 0)
            tt += '<span style="margin-left: 5px;' + color + '">库存不足</span>';
        var ss = {
            wiId: item.wiId,
            momId: item.momId,
            baseNumber: item.baseNumber,
            splitNumber1: (item.splitNumber1 == undefined ? 0 : item.splitNumber1),
            mieId: item.mieId,
            shu: null,
            shuSplit: null,
            leftNumber: item.quantity,
            leftSplitNumber: leftSplitN
        };
        meiArray.push(ss);
    } else if (outType == 1) {
        tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" value='0' style=\"width:60px;text-align:center;" + color + "\" />";
        // tt += '<span>' + basicUnit + '</span>';
        tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" readonly value='0' style=\"width:60px;text-align:center;" + color + "\" />";
        // tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
        selItems[item.wiId] = item;
        let splitQuantity = 0;
        if (saveAfter == 1)
            splitQuantity = item.splitQuantity;
        else
            splitQuantity = item.splitQuantity1;
        splitQuantity = splitQuantity == undefined ? 0 : splitQuantity;
        if (item.quantity <= 0) {
            color = 'color:red';
            tt += '<span style="margin-left: 5px;' + color + '">库存不足</span>';
        }
        // else
        //     tt += "<a onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.quantity + "', '" + splitQuantity + "', " + item.wewMxId + ")\"  class='trigger'>选择</a>";
        var ss = {
            wiId: item.wiId,
            momId: item.momId,
            baseNumber: item.quantity,
            splitNumber1: splitQuantity,
            mieId: item.mieId,
            shu: null,
            shuSplit: null,
            leftNumber: item.quantity,
            leftSplitNumber: item.splitQuantity1,
            wewMxId: item.wewMxId
        };
        meiArray.push(ss);
    } else {
        // 还要最小拆分单位的计算保存
        var splitQuantity = item.splitQuantity == undefined ? 0 : item.splitQuantity;
        var leftNumber = item.baseNumber == undefined ? 0 : item.baseNumber - (item.number1 != null ? item.number1 : 0);
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
        if (leftSplitN >= splitQuantity)
            leftSplitN = splitQuantity;
        // if (item.quantity < leftNumber) {
        if (bNumber < allSplitNumber) {
            color = 'color: red';
            if (item.quantity <= 0 && item.splitQuantity1 <= 0) {
                tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" value='" + 0 + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
                // tt += '<span>' + basicUnit + '</span>';
                tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" value='" + 0 + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
                // tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
                tt += '<span style="margin-left: 5px;' + color + '">库存不足</span>';
            } else {
                tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" value='" + item.quantity + "' style=\"width:60px;text-align:center;" + color + "\" />"; //onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.baseNumber + "', '" + item.splitNumber1 + "')\"/>";
                // tt += '<span>' + basicUnit + '</span>';
                tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" value='" + (item.splitQuantity1 - item.quantity * ratio) + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
                // tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
                // if (view != true)
                // tt += "<a onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.quantity + "', '" + (item.splitQuantity1 - item.quantity * ratio) + "')\"  class='trigger'>选择</a>";
                var ss = {
                    wiId: item.wiId,
                    momId: item.momId,
                    baseNumber: (item.baseNumber == undefined ? 0 : item.baseNumber),
                    splitNumber1: (item.splitQuantity == undefined ? 0 : item.splitQuantity),
                    mieId: null,
                    shu: null,
                    shuSplit: null,
                    leftNumber: leftNumber,
                    leftSplitNumber: leftSplitN
                };
                meiArray.push(ss);
            }

        } else if (leftNumber > 0 || leftSplitN > 0) {
            tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" value='" + leftNumber + "' style=\"width:60px;text-align:center;" + color + "\" />";//onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.baseNumber + "', '" + item.splitNumber1 + "')\"/>";
            // tt += '<span>' + basicUnit + '</span>';
            tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" value='" + leftSplitN + "' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
            // tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
            selItems[item.wiId] = item;
            // tt += "<a onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.baseNumber + "', '" + item.splitNumber1 + "')\"  class='trigger'>选择</a>";

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
        } else {
            // tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" value='0' readOnly=true style=\"width:60px;text-align:center;" + color + "\" onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.baseNumber + "', '" + item.splitNumber1 + "')\"/>";
            tt = "<input class='input-text' type=\"text\" id=\"" + item.wiId + "Inp\" value='0' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
            // tt += '<span>' + basicUnit + '</span>';
            tt += "<input class='input-text' type=\"hidden\" id=\"" + item.wiId + "InpS\" value='0' readOnly=true style=\"width:60px;text-align:center;" + color + "\" />";
            // tt += '<span>' + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
            selItems[item.wiId] = item;
            // tt += "<a onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.baseNumber + "', '" + item.splitNumber1 + "')\"  class='trigger'>选择</a>";
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
        }
    }
    return tt;
}

var selItems = {};

function fomateSel(val, item, rowIndex) {
    var outNumber = item.baseNumber - (item.number1 != null ? item.number1 : 0);
    if (outNumber <= 0) {
        return '';
    }
    selItems[item.wiId] = item;
    var str = "<a onclick=\"selInvExtend('" + item.wiId + "','" + item.momId + "','" + item.baseNumber + "', '" + item.splitNumber1 + "')\"  class='btn btn-info  btn-xs'>选择</a>";
    return str;
}

function selCode() {
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/selOutOrderList"});
    var tt_win = $.supper("showWin", {
        url: att_url, title: "选择申领单信息", icon: "fa-th", width: 800, height: 500, BackE: function () {
            var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
            if (selCodeInfo != null && selCodeInfo.mooId != null) {
                _mooId = selCodeInfo.mooId;
                meiArray = [];
                $("#relatedBill1").val(selCodeInfo.mooCode);
                $("#customerName").val(selCodeInfo.userName);
                $("#customer").val(selCodeInfo.groupName);
                $("#orderTime").val(selCodeInfo.orderTime);
                $('#allNumber').val(selCodeInfo.number1);
                loadGrid(selCodeInfo.mooId);
            }
            /*win_form_search(selCodeInfo.mooId);
            $("#winMooId.val").val()*/
            $.supper("setProductArray", {"name": "selCodeInfo", "value": null});
        }
    });
}

function selInvExtend(wiId, momId, baseNumber, splitNumber1, wewMxId) {
    if (outType == 0) { // 申领出库
        selOutOrderExtend(wiId, momId, baseNumber, splitNumber1);
    } else if (outType == 1) { // 退货出库
        selOutOrderExtend(wiId, momId, baseNumber, splitNumber1, wewMxId);
    } else { // 破损出库
        selOutOrderExtend(wiId, momId, baseNumber, splitNumber1);
    }

}

//2020年07月07日10:51:03朱燕冰修改
function selOutOrderExtend(wiId, momId, baseNumber, splitNumber1, wewMxId) {
    var alNumber = $('#' + wiId + 'Inp').val();
    var alSplitNumber = $('#' + wiId + 'InpS').val();
    var vdata = "wiId=" + wiId + '&alNumber=' + alNumber + '&alSplitNumber=' + alSplitNumber;
    $.supper("setProductArray", {"name": "selInventoryExtendInfo", "value": selItems[wiId]});
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/selInventoryExtendList", "data": vdata});
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: "选择库存信息",
        icon: "fa-th",
        area: ['75%', '90%'],
        width: 1180,
        height: 620,
        BackE: function () {
            var shouldClose = $.supper("getProductArray", "shouldClose");
            if (shouldClose != undefined && shouldClose == '1') {
                $.supper("setProductArray", {"name": "selInvExtend", "value": null});
                $.supper("setProductArray", {"name": "shouldClose", "value": null});
                closeWin();
                return;
            }
            var selInvExtendList = $.supper("getProductArray", "selInvExtend");
            if (selInvExtendList != null && selInvExtendList.length > 0) {
                if (meiArray != null && meiArray.length > 0) {
                    for (var i = meiArray.length - 1; i >= 0; i--) {
                        if (meiArray[i].wiId == wiId) {
                            meiArray.splice(i, 1);
                        }
                    }
                }
                var counShu = 0;
                var countShuSplit = 0;
                for (var i = 0; i < selInvExtendList.length; i++) {
                    if (outType == 1) {
                        if (selInvExtendList[i].shu <= 0 && selInvExtendList[i].shuSplit <= 0) {
                            continue;
                        }
                    }
                    var ss = {
                        wiId: wiId,
                        momId: momId,
                        baseNumber: baseNumber,
                        splitNumber1: (splitNumber1 == undefined || splitNumber1 == 'undefined' || splitNumber1 == null) ? 0 : splitNumber1,
                        mieId: selInvExtendList[i].mieId,
                        shu: selInvExtendList[i].shu,
                        shuSplit: selInvExtendList[i].shuSplit,
                        leftNumber: null,
                        leftSplitNumber: null,
                        wewMxId: wewMxId
                    };
                    meiArray.push(ss);
                    counShu += parseInt(selInvExtendList[i].shu);
                    countShuSplit += parseInt(selInvExtendList[i].shuSplit);
                }
                $("#" + wiId + "Inp").val(counShu);
                $('#' + wiId + 'InpS').val(countShuSplit);
                allAreadyNumberCount += counShu;
                if (allAreadyNumberCount == undefined) {
                    allAreadyNumberCount = ''
                }
                $('#collectReadyAllCount').text(allAreadyNumberCount);
                $.supper("setProductArray", {"name": "selInvExtend", "value": null});
            }
        }
    });
}

var saveAfter = 1;

function loadGrid(data, idx) {
    let att_url = '';
    if (outType == 0) {
        att_url = $.supper("getServicePath", {
            service: "MdOutOrderMxService.getOutOrderMxListByMooId",
            data: "mooId=" + data
        });
    } else if (outType == 1) {
        if (idx == 1) {
            saveAfter = 1;
            att_url = $.supper("getServicePath", {
                service: "MdOrderMxService.getMdOrderMxListByMoiId",
                data: "moiId=" + data
            });
        } else {
            saveAfter = 2;
            att_url = $.supper("getServicePath", {
                service: "MdEnterWarehouseMxService.getMdEnterWarehouseMxListByWewId",
                data: "wewId=" + data
            });
        }
    } else {
        // if (_wmsMiIds.length > 0) {
        //     data += 'wmsMiIds=' + _wmsMiIds.join(',');
        // }
        // if (_mmfIds.length > 0) {
        //     data += '&mmfIds=' + _mmfIds.join(',');
        // }
        att_url = $.supper("getServicePath", {
            service: "mdInventoryService.getInventoryMxList",
            data: 'wiIds=' + data
        });

    }
    mmg.opts.url = att_url;
    mmg.load();
}

function refreshCount(e, data) {
    if (data === null || data === undefined) {
        return;
    }
    if (data.items === null || data.items === undefined) {
        return;
    }
    $('#collectCount').text(data.items.length);
}

var initFormReady = false;

function initForm() {
    _all_div_body.show();
    // _rbbId = $.supper("getParam", _all_table_Id);
    if (initFormReady == false) {
        initFormReady = true;
        _all_div_body.xform('createForm', _saveForm);
    }
    loadForm();

    initDataGrid();
}

function loadForm() {
    if (_mooId != undefined && _mooId != null) {
        // _rbbId = Number(_rbbId);
        var att_data = "mooId=" + _mooId;
        _all_accountForm.xform('loadAjaxForm', {
            'ActionUrl': _all_questOrderAction, "data": att_data, BackE: function (jsondata) {
                $('#receivingObject').val(jsondata.obj.receivingObject);
                $("#relatedBill1").val(jsondata.obj.mooCode);
                console.log("111")
                if (jsondata.obj.number1 == undefined || jsondata.obj.number1 == 0) {
                    console.log("111")
                    jsondata.obj.number1 = '0'
                    var smallUnit = jsondata.obj.number1
                    $("#number1").val(smallUnit);
                }
                smallUnit = jsondata.obj.number1
                let str = '';
                str += smallUnit;// + '/' + (jsondata.obj.number6 != null ? jsondata.obj.number6 : '0')
                $("#number1").val(str)
                $("#missingNumber").val(str)
                $("#collectAllCount").text(str)
                // $('#wowCode').val($('#wowCodeName').val());
            }
        });
    }
}

function initToolBar() {
    _all_win_tools_but.xtoolbar('create', _Toolbar);
}

function initCode() {
    var selCodeInfo = $.supper("getProductArray", "selCodeInfo");
    if (selCodeInfo != null && selCodeInfo.mooId != null) {
        _mooId = selCodeInfo.mooId;
        meiArray = [];
        $("#relatedBill1").val(selCodeInfo.mooCode);
        $("#customerName").val(selCodeInfo.userName);
        $("#customer").val(selCodeInfo.groupName);
        $("#orderTime").val(selCodeInfo.orderTime);
        $('#allNumber').val(selCodeInfo.number1);
        loadGrid(selCodeInfo.mooId);
    }
    $.supper("setProductArray", {"name": "selCodeInfo", "value": null});
}

var url;
var newOrOne;
$(function () {
    var selOutOrderType = $.supper("getProductArray", "addNewOut");
    if (selOutOrderType != undefined && selOutOrderType != null) {
        url = selOutOrderType.url;
        newOrOne = selOutOrderType.newOrOne;
        // $.supper("setProductArray", {"name": "salesManLoan", "value": null});
    }
    if (newOrOne != undefined) {
        $('#win_form_body').hide();
        $('#hide').hide();
    }

    initDataGrid();
    initToolBar();
    initCode();

    $("#win_but_save").css("width", "95px");
    $("#win_but_add").css("width", "95px");
    $("#win_but_add1").css("width", "95px");
});

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

function closeWin() {
    $.supper("setProductArray", {"name": "addNewOut", "value": null});
    $.supper("closeWin");
}

function saveOut() {
    if (outType == 0)
        save();
    if (outType == 1)
        saveAfterSaleOut();
    if (outType == 2)
        saveBroken();
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
    let zeroCount = 0;
    let remarks = '';
    let avgPrices = '';
    let batchCodes = '';
    let valiedDates = '';
    let backPrintings = '';
    // if ($("#relatedBill1").val() == null || $("#relatedBill1").val() == "") {
    //     zeroCount ++;
    // }
    if (rows != null && rows.length > 0 && rows[0] != null) {
        var flag = false;
        for (var i = 0; i < rows.length; i++) {
            var mieId = rows[i].mieId;
            var shu = $("#" + mieId + "Inp").val();
            var shuSplit = $('#' + mieId + 'InpS').val();
            let sq = rows[i].baseNumber == undefined ? 0 : rows[i].baseNumber;
            if (shu <= 0 && shuSplit <= 0) {
                zeroCount++;
            }
            if ((shu != null && shu != "" && shu > rows[i].quantity)) {
                $.supper("alert", {title: "操作提示", msg: "出库数量不能大于库存数量！"});
                return;
            } else if (shuSplit != null && shuSplit != "" && shuSplit > sq) {
                $.supper("alert", {title: "操作提示", msg: "最小单位出库数量不能大于对应库存数量！"});
                return;
            }
            // else if (shu != null && shu != "" && shu > rows[i].quantity) {
            //     $.supper("alert", {title: "操作提示", msg: "出库数量不能大于订单数量！"});
            //     return;
            // }
            else if (shu != null && shu != "") {
                flag = true;
                mieIds += mieId + ",";
                shus += shu + ",";
                shuSplits += shuSplit + ",";
                momIds += rows[i].momId + ",";
                number1s += (rows[i].quantity ? 0 : rows[i].quantity) + ",";
                splitQuatitys += (rows[i].splitQuantity == undefined ? 0 : rows[i].splitQuantity) + ',';
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
    $.supper("doservice", {
        "service": _all_saveAfterAction,
        "ifloading": 1,
        "options": {"type": "post", "data": data + '&wowType=3'},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作成功！",
                    BackE: closeJump
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
    // if (_all_div_body.xform('checkForm')) {
    var data = $('#saveCkForm').serialize();
    data += '&relatedBill1=' + $('#relatedBill1').val();
    var rows = mmg.rows();
    var mieIds = "";
    var shus = "";
    var shuSplits = "";
    var momIds = "";
    var number1s = "";
    var splitQuatitys = '';
    var wewMxIds = '';
    let remarks = '';
    let avgPrices = '';
    if (saveAfter == 1 && ($("#relatedBill1").val() == null || $("#relatedBill1").val() == "")) {
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
                zeroCunt++;
            }
            if ((shu != null && shu != "" && shu > rows[i].quantity)) {
                $.supper("alert", {title: "操作提示", msg: "出库数量不能大于库存数量！"});
                return;
            } else if (shuSplit != null && shuSplit != "" && shuSplit > sq1) {
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
                // if (saveAfter == 2)
                //     wewMxIds += (rows[i].wewMxId == undefined ? -1 : rows[i].wewMxId) + ',';
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

    } else {
        $.supper("alert", {title: "操作提示", msg: "没有出库明细，不允许出库！"});
        return;
    }

    if (zeroCunt >= rows.length) {
        $.supper("alert", {title: "操作提示", msg: "请输入出库数量！"});
        return;
    }
    for (var i = 0; i < meiArray.length; i++) {
        mieIds += meiArray[i].mieId + ",";
        shus += $("#" + meiArray[i].wiId + "Inp").val() + ',';//meiArray[i].shu + ",";
        shuSplits += $('#' + meiArray[i].wiId + 'InpS').val() + ',';//meiArray[i].shuSplit + ',';
        momIds += meiArray[i].momId + ",";
        number1s += meiArray[i].baseNumber + ",";
        splitQuatitys += (meiArray[i].splitNumber1 == undefined ? 0 : meiArray[i].splitNumber1) + ',';
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
    if (wewMxIds != '')
        wewMxIds = wewMxIds.substring(0, wewMxIds.length - 1);
    data += "&shus=" + shus +
        "&shuSplits=" + shuSplits +
        "&mieIds=" + mieIds + "&momIds=" + momIds + "&number1s=" + number1s +
        '&splitQuantitys=' + splitQuatitys;
    if (saveAfter == 2) {
        data += '&companyType=4&wowType=4';
        data += '&wewMxIds=' + wewMxIds;
    } else
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
                    //朱燕冰2020年07月12日22:22:22修改
                    BackE: closeJump
                });
            } else
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作失败！"
                });
        }
    });
    // }
}

//出库保存按钮
function save() {
    if (_all_div_body.xform('checkForm')) {
        var data = _all_accountForm.serialize();
        data = data.replace('%2F', '.');
        var data1 = $('#saveCkForm').serialize();
        data += '&' + data1;
        data += '&relatedBill1=' + $('#relatedBill1').val();
        var rows = mmg.rows();

        if ($("#mooCode").val() == null || $("#mooCode").val() == "") {
            $.supper("alert", {title: "操作提示", msg: "请选择申领单号！"});
            return;
        }
        if (rows != null && rows.length > 0 && rows[0] != null) {
            var flag = false;
            var countNotEnough = 0; // 库存
            var leftZeroCount = 0; // 已结束
            var zeroCount = 0;
            for (var i = 0; i < rows.length; i++) {
                var wiId = rows[i].wiId;
                var shu = $("#" + wiId + "Inp").val();
                var shuSplit = $('#' + wiId + 'InpS').val();
                var cha = rows[i].quantity - (rows[i].number1 != null ? rows[i].number1 : 0);
                var splitCha = rows[i].splitNumber1 - (rows[i].number6 != undefined ? rows[i].number6 : 0);
                let sq = rows[i].splitQuantity == undefined ? 0 : rows[i].splitQuantity;
                if (cha <= 0 && splitCha <= 0) {
                    leftZeroCount++;
                    continue;
                }
                if (rows[i].quantity <= 0 && (rows[i].splitQuantity == undefined || (rows[i].splitQuantity != undefined && rows[i].splitQuantity <= 0))) {
                    countNotEnough++;
                }
                if ((shu != null && shu != "" && shu > rows[i].quantity)) {
                    $.supper("alert", {title: "操作提示", msg: "出库数量不能大于库存数量！"});
                    return;
                } else if (shuSplit != null && shuSplit != "" && shuSplit > sq) {
                    $.supper("alert", {title: "操作提示", msg: "最小单位出库数量不能大于对应库存数量！"});
                    return;
                } else if ((shu != null && shu != "" && shu > cha)) {
                    $.supper("alert", {title: "操作提示", msg: "出库数量不能大于申领数量！"});
                    return;
                } else if ((shuSplit != null && shuSplit != "" && shuSplit > splitCha)) {
                    $.supper("alert", {title: "操作提示", msg: "最小单位出库数量不能大于对应申领数量！"});
                    return;
                } else if (shu > 0 || shuSplit > 0) {
                    flag = true;
                }
                if ((shu == null || shu <= 0) && (shuSplit == null || shuSplit <= 0)) {
                    zeroCount++;
                }
            }
            if (countNotEnough == rows.length || countNotEnough == (rows.length - leftZeroCount)) {
                $.supper("alert", {title: "操作提示", msg: "库存数量不足，无法出库！"});
                return;
            }
            if (leftZeroCount >= rows.length) {
                flag = true;
            }
            if (!flag) {
                $.supper("alert", {title: "操作提示", msg: "请输入出库数量！"});
                return;
            }
            if (zeroCount >= rows.length) {
                $.supper("alert", {title: "操作提示", msg: "请输入出库数量！"});
                return;
            }
        } else {
            $.supper("alert", {title: "操作提示", msg: "没有出库明细，不允许出库！"});
            return;
        }
        var mieIds = "";
        var shus = "";
        var shuSplits = "";
        var momIds = "";
        var number1s = "";
        var splitQuatitys = '';
        var wiIds = "";
        var leftNumbers = "";
        var leftSplitNumbers = '';
        let remarks = '';
        let avgPrices = '';
        for (var i = 0; i < meiArray.length; i++) {
            mieIds += meiArray[i].mieId + ",";
            shus += $("#" + meiArray[i].wiId + "Inp").val() + ',';//meiArray[i].shu + ",";
            shuSplits += $('#' + meiArray[i].wiId + 'InpS').val() + ',';//meiArray[i].shuSplit + ',';
            momIds += meiArray[i].momId + ",";
            number1s += meiArray[i].baseNumber + ",";
            splitQuatitys += (meiArray[i].splitNumber1 == undefined ? 0 : meiArray[i].splitNumber1) + ',';
            wiIds += meiArray[i].wiId + ",";
            leftNumbers += meiArray[i].leftNumber + ",";
            leftSplitNumbers += meiArray[i].leftSplitNumber + ',';
            remarks += $('#remark' + meiArray[i].wiId).val() + '##';
            avgPrices += $('#avgPrice' + meiArray[i].wiId).val() + ',';
        }
        mieIds = mieIds.substring(0, mieIds.length - 1);
        shus = shus.substring(0, shus.length - 1);
        shuSplits = shuSplits.substring(0, shuSplits.length - 1);
        momIds = momIds.substring(0, momIds.length - 1);
        number1s = number1s.substring(0, number1s.length - 1);
        splitQuatitys = splitQuatitys.substring(0, splitQuatitys.length - 1);
        wiIds = wiIds.substring(0, wiIds.length - 1);
        leftNumbers = leftNumbers.substring(0, leftNumbers.length - 1);
        leftSplitNumbers = leftSplitNumbers.substring(0, leftSplitNumbers.length - 1);
        if (remarks != '')
            remarks = remarks.substring(0, remarks.length - 2);
        if (avgPrices != '')
            avgPrices = avgPrices.substring(0, avgPrices.length - 1);
        data += "&shus=" + shus +
            "&shuSplits=" + shuSplits +
            "&mieIds=" + mieIds + "&momIds=" + momIds + "&companyType=1&number1s=" + number1s +
            '&splitQuatitys=' + splitQuatitys +
            "&wiIds=" + wiIds + '&leftNumbers=' + leftNumbers +
            '&leftSplitNumbers=' + leftSplitNumbers + '&remarks=' + remarks + '&avgPrices=' + avgPrices;
        $.supper("doservice", {
            "service": _all_saveAction,
            "ifloading": 1,
            "options": {"type": "post", "data": data + '&wowType=1'},
            "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    $.supper("alert", {
                        title: "操作提示",
                        msg: "操作成功！",
                        BackE: function () {
                            isSave = true;
                            closeJump();
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

function main_export() {
    var vdata = "mooId=" + _mooId;
    var newTab = window.open('about:blank');
    $.supper("doservice", {
        "service": "MdOutOrderService.exportInfoApply", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

function inputEnter() {
    if (event.keyCode == 13)
        win_form_search();
}

function win_form_search() {
    allNumberCount = 0;
    allAreadyNumberCount = 0;
    $('#collectAllCount').text();
    $('#collectReadyAllCount').text(allAreadyNumberCount);
    var spmc = $('#spmc').val();
    var gg = $('#gg').val();
    var mooId = _mooId;
    if (spmc != null && spmc != undefined && spmc != "" || gg != null && gg != undefined && gg != "") {
        var spmc = $('#spmc').val();
        var gg = $('#gg').val();
        var att_urll = $.supper("getServicePath", {
            service: "MdOutOrderMxService.getOutOrderMxListByMooId",
            data: "mooId=" + _mooId + '&matName=' + spmc + '&mmfName=' + gg
        });
        mmg.opts.url = att_urll;
    } else {
        var att_url = $.supper("getServicePath", {
            service: "MdOutOrderMxService.getOutOrderMxListByMooId",
            data: "mooId=" + _mooId
        });
        mmg.opts.url = att_url;
    }
    mmg.load();
}

function backTo() {
    //  closeWin();
    closeJump();
}

function closeJump2() {
    var index = parent.layer.getFrameIndex(window.name);
    if (index == '' || index == undefined) {
        url = '/dentistmall/jsp/dentistmall/storage/newApplyMdOutWarehouser.jsp';
        $.supper("setProductArray", {"name": "addEnter", "value": null});
        $.supper("closeTtemWin", {url: url});
    } else {
        parent.layer.close(index);//关闭当前页
    }

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
        $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        *
        *      var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);//关闭当前页
    var view_url = 'jsp/dentistmall/storage/applyMdOutWarehouserList.jsp';
    $.supper("showTtemWin", {"url": view_url, "title": '出库管理'});
    $.supper("setProductArray", {"name": "addNewOut", "value": null});
    setTimeout(function () {
        if (url != undefined) {
            $.supper("closeTtemWin", {url: url});
        }
    }, 200);
        * */


/**
 * 朱燕冰2020年07月12日22:22:00添加方法
 */
function closeJump() {
    // var index = parent.layer.getFrameIndex(window.name);

    // if (index == '' || index == undefined){
    //     var view_url = 'jsp/dentistmall/storage/applyMdOutWarehouserList.jsp';
    //     $.supper("showTtemWin", {"url": view_url, "title": '出库管理'});
    //     $.supper("setProductArray", {"name": "addNewOut", "value": null});
    //     setTimeout(function () {
    //         if (url != undefined) {
    //             $.supper("closeTtemWin", {url: url});
    //         }
    //     }, 200);
    // }else {
    //     parent.layer.close(index);//关闭当前页
    // }

// 2020-07-14 宋 修改
    var index = parent.layer.getFrameIndex(window.name);
    if (index == '' || index == undefined) {
        var view_url = 'jsp/dentistmall/storage/applyMdOutWarehouserList.jsp';
        $.supper("showTtemWin", {"url": view_url, "title": '出库管理'});
        $.supper("setProductArray", {"name": "addNewOut", "value": null});
        $.supper("closeTtemWin", {url: url});
    } else {
        parent.layer.close(index);//关闭当前页
    }
}


