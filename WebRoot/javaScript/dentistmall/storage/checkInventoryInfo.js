var _all_win_tools_but = $("#win_tools_but");
var _all_win_searchForm = $("#win_form_search");
var _all_div_hidden = $("#win_form_hidden");
var _all_win_datagrid;
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");

var _all_queryAction = 'mdInventoryService.getPagerModelCheckInventory';
var _all_findAction = 'mdInventoryService.getOneCheckInventory';

var _all_queryInventoryAction = 'mdInventoryService.getPagerModelObject1';

var _all_saveCheckInventory = 'mdInventoryService.saveCheckInventory';
var _all_updateCheckInventory = 'mdInventoryService.updateCheckInventory';
var _all_saveCheckInventoryMx = 'mdInventoryService.saveCheckInventoryMx';
var _all_updateCheckInventoryMx = 'mdInventoryService.updateCheckInventoryMx';
var _all_saveChekInventoryMxRemark = 'mdInventoryService.saveCheckInventoryMxRemark';
var _all_updateCheckInventoryState = 'mdInventoryService.updateCheckInventoryState';

var _all_datagrid_height;

var checkwiIds = [];
var checkNowNumber = {};

var _searchForm = {
    lineNum: 1,
    columnNum: 4,
    control: [],
    groupTag: [],
    hiddenitems: [],
    items: [
        {title: "盘点名称", ariaRequired: true, name: "checkName", type: "text", placeholder: "盘点名称"},
        {title: "盘点单号", ariaRequired: true, name: "checkCode", type: "text", placeholder: "盘点单号"},
        {
            title: "盘点类型",
            ariaRequired: true,
            name: "checkType",
            type: "select",
            placeholder: "盘点类型",
            data: [{id: 1, name: '月度'}, {id: 2, name: '季度'}, {id: 3, name: '年度'}, {id: 4, name: '每次'}]
        },
        {
            title: "盘点分类",
            ariaRequired: true,
            name: "checkPart",
            type: "select",
            placeholder: "盘点分类",
            data: [{id: 1, name: '一级分类'}, {id: 2, name: '二级分类'}]
        },
        {title: "备注", name: "remark", type: "text", placeholder: "备注"},
    ]
};

var _Toolbar = {
    toolBarId: "tools_but",
    items: [
        {
            title: "保存",
            id: "win_but_search",
            icon: "save",
            colourStyle: "primary",
            rounded: true,
            clickE: saveAll
        },
        {title: "返回", id: "win_but_search", icon: "search", colourStyle: "default", rounded: true, clickE: backTo},
        {
            title: "导出Excel",
            id: "win_but_search",
            icon: "success",
            colourStyle: "default",
            rounded: true,
            clickE: exportExcel
        }
    ]
};

var _DataGrid = {
    cols: [
        {title: '物料编码',sortable: true , name: 'mmfCode', width: 100, align: 'center'},
        {title: '分类',sortable: true , name: 'mdpName', width: 80, align: 'center', renderer: renderMdpName},
        {title: '商品名称',sortable: true , name: 'matName', width: 80, align: 'center', renderer: renderMatName},
        {title: '品牌',sortable: true , name: 'brand', width: 70, align: 'center'},
        {title: '批次号',sortable: true , name: 'batchCode', width: 70, align: 'center'},
        {title: '规格型号',sortable: true , name: 'mmfName', width: 70, align: 'center'},
        {title: '原有库存数',sortable: true , name: 'baseNumber', width: 70, align: 'center', renderer: renderBaseNumber},
        {title: '现有库存数',sortable: true , name: 'nowNumber', width: 70, align: 'center', renderer: renderQuantity},
        {title: '最小单位数量',sortable: true , name: 'nowSplitNumber', width: 70, align: 'center', renderer: renderSplitUnit},
        {title: '盘点盈亏',sortable: true , name: 'checkSale', width: 70, align: 'center', renderer: renderCheck},
        {title: '生产厂家',sortable: true , name: 'productFactory', width: 70, align: 'center'},
        {title: '备注',sortable: false, name: 'checkRemark', width: 90, align: 'center', renderer: renderRemark},
        {title: '状态',sortable: true , name: 'checkInventory', width: 50, align: 'center', renderer: renderCheckInventory},
    ]
    , remoteSort: false
    , height: _all_datagrid_height
    , gridtype: '2'
    , nowrap: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
}

function renderCheckInventory(val, item, rowIndex) {
    let str = '';
    if (item.checkInventory == 1) {
        str += '已盘点';
    } else {
        str += '未盘点';
    }
    return str;
}
//
// function renderCheck(val, item, rowIndex) {
//     let num1 = 0;
//     let num2 = 0;
//     let re = '';
//     if (item.checkNowNumber != undefined) {
//         num1 = item.quantity - item.checkNowNumber
//     }
//     re += num1 + item.basicUnit;
//
//     if (item.checkSplitNowNumber != undefined) {
//         num2 = item.splitQuantity - item.checkNowSplitNumber
//     }
//     re += num2 + item.unit;
//
//     let style = 'black';
//     if (num1 < 0 || num2 < 0) {
//         style = 'red';
//     }
//     let str = '<span style="' + style + '">';
//     str += re;
//     str += '</span>';
//     return str;
// }

function renderSplitUnit(val, item, rowIndex) {
    let str = '';
    let num;
    let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    if (item.nowSplitNumber != undefined) {
        num = item.nowSplitNumber;
    }
    str += '<input id="splitNumber' + item.wiId + '" style="text-align:center;width:80px" onblur="changeSplitNumber(' + item.wiId + ', ' + (item.quantity == undefined ? 0 : item.quantity) +', ' + (item.baseNumber == undefined ? 0 : item.baseNumber) + ', ' + item.ratio + ')" value="' + (num == undefined ? '' : num) + '"';
    if (_ciId != undefined && _edit == undefined) {
        str += ' readonly ';
    }
    str += '/>';
    str += ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit);
    return str;
}

function renderQuantity(val, item, rowIndex) {
    let str = '';
    let num;
    if (_ciId != undefined && item.nowNumber != undefined && item.nowNumber != '' && item.nowNumber > 0) {
        excludeCheckArray.push(item.wiId);
    }
    if (item.nowNumber != undefined) {
        num = item.nowNumber;
    }
    str += '<input id="nowNumber' + item.wiId + '" style="text-align:center;width:80px" onblur="changeNowNumber(' + item.wiId + ', ' + (item.quantity == undefined ? 0 : item.quantity) + ', ' + item.baseNumber + ', ' + item.ratio + ')" value="' + (num == undefined ? '' : num) + '"';
    if (_ciId != undefined && _edit == undefined) {
        str += ' readonly ';
    }
    str += ' />';
    str += item.basicUnit == undefined ? '' : item.basicUnit;
    return str;
}

var excludeCheckArray = [];
function changeNowNumber(wiId, qua, baseNumber, ratio) {
    let nuwNumber = $('#nowNumber' + wiId).val();

    if (nuwNumber == '' && CheckUtil.isDigit(nuwNumber) != true) {
        $('#nowNumber' + wiId).val('');
        // return;
    }
    if (checkNowNumber[wiId] == undefined) {
        checkNowNumber[wiId] = {}
        if (_ciId != undefined) {
            if (excludeCheckArray.indexOf(wiId) < 0) {
                countCheckWithSave++;
                changeCountWithSave();
            }
        } else {
            countCheckWithSave++;
            changeCountWithSave();
        }
    }

    let sn = $('#splitNumber' + wiId).val();
    sn = sn == '' ? 0 : sn;
    if (CheckUtil.isDigit(sn) == false)
        sn = 0;

    let oN = qua * ratio;
    let oSN = (baseNumber == undefined ? 0 : baseNumber) - oN + oN;
    let nN = nuwNumber * ratio;
    let nSN = Number(sn) + nN;

    let color = '';
    let nowSNum = nSN - oSN;
    if (nowSNum < 0)
        color = 'color:red';
    let nowNum = Math.floor(nowSNum / ratio);
    nowSNum = nowSNum - nowNum * ratio;
    // let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;

    checkNowNumber[wiId].checkNowNumber = nuwNumber;
    $('#qua' + wiId).text(nowNum);
    $('#splQua' + wiId).text(nowSNum);
    let nnum = nowNum;
    if (nnum < 0) {
        $('#qua' + wiId).css("color","red");
        $('#splQua' + wiId).css("color","red");
    }else {
        $('#qua' + wiId).css("color","#333");
        $('#splQua' + wiId).css("color","#333");
    }
    // let splitNumber=  nuwNumber * (ratio == undefined ? 1 : ratio);
    // checkNowNumber[wiId].checkNowSplitNumber = splitNumber;
    // $('#splitNumber' + wiId).val(splitNumber);
    // $('#splQua' + wiId).text(splitNumber - baseNumber);


}
function changeSplitNumber(wiId, qua, splitQuantity, ratio) {
    if (_ciId != undefined && _edit == undefined) {
        return;
    }
    let splitNumber = $('#splitNumber' + wiId).val();
    if (splitNumber == '' || CheckUtil.isDigit(splitNumber) != true)
        $('#splitNumber' + wiId).val('');
        // return;
    if (checkNowNumber[wiId] == undefined)
        checkNowNumber[wiId] = {}

    checkNowNumber[wiId].checkNowSplitNumber = splitNumber;

    let n = $('#nowNumber' + wiId).val();
    n = n == '' ? 0 : n;
    if (CheckUtil.isDigit(n) == false)
        n = 0;

    let oN = qua * ratio;
    let oSN = (splitQuantity == undefined ? 0 : splitQuantity) - oN + oN;
    let nN = n * ratio;
    let nSN = Number(n) + splitNumber;

    let color = '';
    let nowSNum = nSN - oSN;
    if (nowSNum < 0)
        color = 'color:red';
    let nowNum = Math.floor(nowSNum / ratio);
    nowSNum = nowSNum - nowNum * ratio;
    // let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    $('#qua' + wiId).text(nowNum);
    $('#splQua' + wiId).text(nowSNum);
    let nnum = splitNumber - splitQuantity;
    if (nnum < 0) {
        $('#qua' + wiId).css("color","red");
        $('#splQua' + wiId).css("color","red");
    }else {
        $('#qua' + wiId).css("color","#333");
        $('#splQua' + wiId).css("color","#333");
    }
}

function renderBaseNumber(val, item, rowIndex) {
    let str = '';
    let ratio = item.ratio == undefined ? 1 : item.ratio;
    let num = item.quantity * ratio;
    let bNumber = (item.baseNumber == undefined ? 0 : item.baseNumber) - num;
    let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    str = item.quantity + '' + basicUnit + "" + bNumber + ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit);
    return str;
}

function changeCheckRemark(wiId) {
    if (checkNowNumber[wiId] == undefined)
        checkNowNumber[wiId] = {}
    let remark = $('#remark' + wiId).val();
    checkNowNumber[wiId].checkRemark = remark;
    $('#remark' + wiId).val(remark);
}


function renderMdpName(val, item, rowIndex) {
    let str = '';
    if (item.mdpName != undefined) {
        str += '<span>' + item.mdpName + '</span>';
    }
    if (item.mdpsName != undefined) {
        str += '<span>' + item.mdpsName + '</span>';
    }
    return str;
}

function renderMatName(val, item, rowIndex) {
    checkwiIds.push(item.wiId);
    // if (checkNowNumber[item.wiId] == undefined) {
    //     checkNowNumber[item.wiId] = {};
    //     checkNowNumber[item.wiId].checkNowNumber = item.nowNumber == undefined ? '' : item.nowNumber;
    //     checkNowNumber[item.wiId].checkNowSplitNumber = item.nowSplitNumber == undefined ? '' : item.nowSplitNumber;
    //     checkNowNumber[item.wiId].checkRemark = item.checkRemark == undefined ? '' : item.checkRemark;
    // }

    let str = '';
    str = item.matName;
    return str;
}

function renderRemark(val, item, rowIndex) {
    let str = '';
    // str += '<input id="remark' + item.wiId + '" onblur="changeCheckRemark(' + item.wiId + ')" value="' + checkNowNumber[item.wiId].checkRemark + '" />';
    str += '<input id="remark' + item.wiId + '" onblur="changeCheckRemark(' + item.wiId + ')" value="' + ( item.checkRemark == undefined ? '' : item.checkRemark) + '" style="width: 100px;" ';
    if (_ciId != undefined && _edit == undefined) {
        str += ' readonly ';
    }
    str += '/>';
    return str;
}

function renderCheck(val, item, rowIndex) {
    let str = '';
    // str = '<span>' + item.alreadyCheck + '</span>/<span>' + item.noCheck + '</span>';
    // let quantity = (checkNowNumber[item.wiId].checkNowNumber == '' ? 0 : checkNowNumber[item.wiId].checkNowNumber) - item.quantity;
    // if (checkNowNumber[item.wiId].checkNowNumber == '')
    //     quantity = 0;
    // let splitQuantity = (checkNowNumber[item.wiId].checkNowSplitNumber == '' ? 0 : checkNowNumber[item.wiId].checkNowSplitNumber) - (item.splitQuantity == undefined ? 0 : item.splitQuantity);
    // if (checkNowNumber[item.wiId].checkNowSplitNumber == '')
    //     splitQuantity = 0;

    let ratio = item.ratio == undefined ? 1 : item.ratio;
    let oN = item.quantity * ratio;
    let oSN = (item.baseNumber == undefined ? 0 : item.baseNumber) - oN + oN;
    let nN = (item.nowNumber == undefined ? 0 : item.nowNumber) * ratio;
    let nSN = (item.nowSplitNumber == undefined ? 0 : item.nowSplitNumber) + nN;

    let color = '';
    let nowSNum = nSN - oSN;
    if (nowSNum < 0)
        color = 'color:red';
    let nowNum = Math.floor(nowSNum / ratio);
    nowSNum = nowSNum - nowNum * ratio;
    let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    str = '<span>' +
        '<span id="qua' + item.wiId + '" style="' + color + '">' + nowNum + '</span>' +
        basicUnit + "" +
        '<span id="splQua' + item.wiId + '" style="' + color + '">' + nowSNum + '</span>' +
        ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
    return str;
    // let quantity = (item.nowNumber == undefined ? 0 : item.nowNumber) - item.quantity;
    // if (item.nowNumber == undefined)
    //     // quantity = 0;
    //     quantity = 0 - item.quantity;
    //
    // // let ratio = item.ratio == undefined ? 1 : item.ratio;
    // let splitQuantity = (item.nowSplitNumber == undefined ? 0 : item.nowSplitNumber) - (item.baseNumber == undefined ? 0 : item.baseNumber);
    // if (item.nowSplitNumber == undefined)
    //     // splitQuantity = 0;
    //     splitQuantity = 0 - (item.baseNumber == undefined ? 0 : item.baseNumber);
    //
    // if (quantity < 0) {
    //     color = 'color:red';
    // }
    // let splitColor = '';
    // if (splitQuantity < 0) {
    //     splitColor = 'color:red';
    // }
    // let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    // str = '<span>' +
    //     '<span id="qua' + item.wiId + '" style="' + color + '">' + quantity + '</span>' +
    //     basicUnit + "" +
    //     '<span id="splQua' + item.wiId + '" style="' + splitColor + '">' + splitQuantity + '</span>' +
    //     ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit) + '</span>';
    // return str;
}

function backTo() {
    var view_url = 'jsp/dentistmall/storage/checkInventory.jsp';
    $.supper("showTtemWin", {"url": view_url, "title": '盘点管理'});
    $.supper("setProductArray", {"name": "checkInventory", "value": null });
    setTimeout(function () {
        $.supper("closeTtemWin", {url: _url});
    }, 200);
}

var editStartCheck = false;
function startCheck() {
    if (checkRender != undefined) {
        let value = checkRender.getValue();
        if (value.length <= 0) {
            $.supper("alert", {title: "操作提示", msg: '盘点分类必选'});
            return;
        }
    }
    if ((_all_win_datagrid.rows().length == 1 && _all_win_datagrid.rows()[0] != undefined) ||
        (_all_win_datagrid.rows().length > 0 && _all_win_datagrid.rows().length != 1 && _all_win_datagrid.rows()[0] != undefined)) {
        if (_ciId != undefined && _edit != undefined) {
            $.supper("confirm", {
                title: "确认操作", msg: "尚有已盘点数据，是否覆盖？", yesE: function () {
                    $.supper("doservice", {
                        "service": 'mdInventoryService.deleteCheck', "data": 'ciId=' + _ciId, "ifloading" : 1, "BackE": function (jsondata) {
                            if (jsondata.code == "1") {
                                editStartCheck = true;
                                checkwiIds = [];
                                checkNowNumber = {};
                                filterCheck();
                            } else {
                                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
                            }
                        }
                    });
                }
            });
        } else {
            $.supper("confirm", {
                title: "确认操作", msg: "尚有未保存数据，是否覆盖？", yesE: function () {
                    checkwiIds = [];
                    checkNowNumber = {};
                    filterCheck();
                }
            });
        }
    } else {
        filterCheck();
    }
    initBrandSel();
}

function saveAll() {
    // saveNewCheck(saveCheckMx)
    if(_edit == true){
        $.supper("confirm",{ title:"确定保存", msg:"保存将更新库存数量，确实要保存吗？" ,yesE:function(){
                saveNewCheck(saveCheckMx)
            }});
    }else {
        saveNewCheck(saveCheckMx)
    }
}

function saveNewCheck(saveMxFunc) {
    // var a = $("#splitNumber" + item.wiId)
    // console.log("111"+a)
    // console.log(_all_win_datagrid.rows())
    // var DataRows = _all_win_datagrid.rows()
    // let _enterData = {}
    // for (let idx in DataRows){
    //     var wiId = DataRows[idx].wiId;
    //     $("#splitNumber" + wiId)
    //     var baseNumber = DataRows[idx].nowNumber
    //     var nowNumber = DataRows[idx].nowSplitNumber
    //     console.log(baseNumber,nowNumber)
    // }
    if ($('#checkName').val() == '') {
        $.supper("alert", {title: "操作提示", msg: '盘点名称必填'});
        return;
    }
    if ($('#checkCode').val() == '') {
        $.supper("alert", {title: "操作提示", msg: '盘点编号必填'});
        return;
    }
    if ($('#checkType').val() == '') {
        $.supper("alert", {title: "操作提示", msg: '盘点类型必填'});
        return;
    }
    let data = $('#saveForm').serialize();
    // let data1 = $('#filterForm').serialize();
    // data += '&' + data;
    if (checkRender != undefined) {
        console.log("111"+checkRender)
        let value = checkRender.getValue();
        let checkPart = '';
        let checkParts = '';
        for (let idx = 0; idx < value.length; idx ++) {
            if (value[idx].group == '1')
                checkPart += value[idx].value + ',';
            else
                checkParts += value[idx].value.split('_')[0] + ',';
        }

        if (checkPart != '') {
            checkPart = checkPart.substring(0, checkPart.length - 1);
            data += '&checkPart=' + checkPart;
        }
        if (checkParts != '') {
            checkParts = checkParts.substring(0, checkParts.length - 1);
            data += '&checkParts=' + checkParts;
        }
    }
    let service = _all_saveCheckInventory;
    if (_ciId != undefined || _ciIdTemp != undefined) {
        service = _all_updateCheckInventory;
        data += '&ciId=' + (_ciId == undefined ? _ciIdTemp : _ciId);
    }

    $.supper("doservice", {
        "service": service, "data": data, "ifloading" : 1, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                if (saveMxFunc != undefined) {
                    if (_ciId != undefined) {
                        saveMxFunc(_ciId, true);
                    } else {
                        _ciId = jsondata.obj.ciId;
                        saveMxFunc(jsondata.obj.ciId, false);
                    }
                } else {
                    if (_ciIdTemp == undefined)
                        _ciIdTemp = jsondata.obj.ciId;
                    // else
                    //     saveCheckMx(_ciIdTemp);
                    // $('#saveForm input').attr('readonly', 'readonly');
                    // $('#saveForm select').attr('disabled', 'disabled');
                    // $('#save').hide();
                    $.supper("alert", {title: "操作提示", msg: "操作成功！"});
                }
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: jsondata.meg});
            } else if (jsondata.code == '3') {
                $.supper("alert", {title: "操作提示", msg: '存在同名'});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function saveCheckMx(ciId, updateOrSave) {
    let wiIds = '';
    let nowNumbers = '';
    let nowSplitNumbers = '';
    for (let idx in checkNowNumber) {
        wiIds += idx + ',';
        if (checkNowNumber[idx].checkNowNumber != undefined) {
            nowNumbers += checkNowNumber[idx].checkNowNumber + ',';
        } else {
            nowNumbers += '-1,';
        }

        if (checkNowNumber[idx].checkNowSplitNumber != undefined) {
            nowSplitNumbers += checkNowNumber[idx].checkNowSplitNumber + ',';
        } else {
            nowSplitNumbers += '-1,';
        }
    }
    if (wiIds != '') {
        wiIds = wiIds.substring(0, wiIds.length - 1);
    }
    if (nowNumbers != '') {
        nowNumbers = nowNumbers.substring(0, nowNumbers.length - 1);
    }
    if (nowSplitNumbers != '') {
        nowSplitNumbers = nowSplitNumbers.substring(0, nowSplitNumbers.length - 1);
    }
    let data = 'wiIds=' + wiIds + '&nowNumbers=' + nowNumbers + '&nowSplitNumbers=' + nowSplitNumbers + '&ciId=' + ciId;
    if (_edit == undefined || editStartCheck == true) {
        // data += '&' + $('#filterForm').serialize();
        let value = checkRender.getValue();
        let checkPart = '';
        let checkParts = '';
        for (let idx = 0; idx < value.length; idx ++) {
            if (value[idx].group == '1')
                checkPart += value[idx].value + ',';
            else
                checkParts += value[idx].value.split('_')[0] + ',';
        }

        if (checkPart != '') {
            checkPart = checkPart.substring(0, checkPart.length - 1);
            data += '&checkPart=' + checkPart;
        }
        if (checkParts != '') {
            checkParts = checkParts.substring(0, checkParts.length - 1);
            data += '&checkParts=' + checkParts;
        }
    }
    if (updateOrSave == true && editStartCheck == false)
        data += '&updateOrSave=1';
    $.supper("doservice", {
        "service": _all_saveCheckInventoryMx, "data": data, "ifloading" : 1, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert", {title: "操作提示", msg: "操作成功！"});
                saveMxRemark(ciId);
                // main_search();
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: jsondata.meg});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function saveMxRemark(ciId) {
    // 保存备注方法修改
    // if (items == undefined) {
    //     main_search();
    //     return;
    // }
    let wiId = '';
    let remark = '';
    let cieId = '';
    let idx = 0;
    let data = '';
    let wiIds = '';

    let count = 0;
    for (let wiId in checkNowNumber) {
        // wiId = '';
        remark = '';
        // cieId = '';
        if (checkNowNumber[wiId].checkRemark == undefined || checkNowNumber[wiId].checkRemark == '')
            continue;
        count += 1;
        wiIds += wiId + ',';
        // cieId += checkNowNumber[wiId].cieId + ',';
        remark += checkNowNumber[wiId].checkRemark + '#*#';

        // if (cieId != '')
        //     cieId = cieId.substring(0, cieId.length - 1);

        if (idx < checkNowNumber.length && idx < 5) {
            idx ++;
            continue;
        }

        if (wiIds != '')
            wiIds = wiId.substring(0, wiIds.length - 1);
        if (remark != '')
            remark = remark.substring(0, remark.length - 3);
        data = 'checkRemarks=' + remark + '&ciId=' + ciId + '&wiIds=' + wiIds;
        $.supper("doservice", {
            "service": _all_saveChekInventoryMxRemark, "data": data, "ifloading" : 1, "BackE": function (jsondata) {
                // if (jsondata.code == "1") {
                //     $.supper("alert", {title: "操作提示", msg: "操作成功！"});
                $.supper("alert", {title: "操作提示", msg: '操作成功', BackE: backTo});
                // main_search();
                // } else if (jsondata.code == '2') {
                //     $.supper("alert", {title: "操作提示", msg: jsondata.meg});
                // } else {
                //     $.supper("alert", {title: "操作提示", msg: "操作出错！"});
                // }
            }
        });

        idx = 0;
        data = '';
        remark = '';
    }
    if (count == 0) {
        $.supper("alert", {title: "操作提示", msg: '操作成功', BackE: backTo});
    }
}

function getMMGridUrl() {
    // let data = $('#saveForm').serialize();
    let data1 = $('#filterForm').serialize();
    // if ($('#checkPart').val() != '' && $('#checkPart').val() != 'null') {
    //     data1 += '&checkPart=' + $('#checkPart').val();
    // }
    if (checkRender != undefined) {
        let value = checkRender.getValue();
        let checkPart = '';
        let checkParts = '';
        for (let idx = 0; idx < value.length; idx ++) {
            if (value[idx].group == '1')
                checkPart += value[idx].value + ',';
            else
                checkParts += value[idx].value.split('_')[0] + ',';
        }

        if (checkPart != '') {
            checkPart = checkPart.substring(0, checkPart.length - 1);
            data1 += '&checkPart=' + checkPart;
        }
        if (checkParts != '') {
            checkParts = checkParts.substring(0, checkParts.length - 1);
            data1 += '&checkParts=' + checkParts;
        }
    }
    data1 += '&ciId=' + _ciId;
    let att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data1});
    return att_url;
}

function main_search() {
    if (_ciId == undefined && _edit == undefined) {
        find_inventory();
        return;
    } else if (editStartCheck == true && _edit != undefined) {
        find_inventory();
        return;
    }
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
    initCount();
}

function find_inventory() {
    let data = $('#filterForm').serialize();
    if (checkRender != undefined) {
        let value = checkRender.getValue();
        let checkPart = '';
        let checkParts = '';
        for (let idx = 0; idx < value.length; idx ++) {
            if (value[idx].group == '1')
                checkPart += value[idx].value + ',';
            else
                checkParts += value[idx].value.split('_')[0] + ',';
        }

        if (checkPart != '') {
            checkPart = checkPart.substring(0, checkPart.length - 1);
            data += '&checkPart=' + checkPart;
        }
        if (checkParts != '') {
            checkParts = checkParts.substring(0, checkParts.length - 1);
            data += '&checkParts=' + checkParts;
        }

    }
    let att_url = $.supper("getServicePath", {"service": _all_queryInventoryAction, "data": data});
    _all_win_datagrid.opts.url = att_url;
    _all_win_datagrid.load();

}

// function exportExcel() {
// }

function exportExcel() {
    var vdata=$('#filterForm').serialize();
    var ciId=parseInt(_ciId);
    if (_ciId!=undefined&&_ciId!=null&&_ciId!=""){
        vdata+="&ciId="+ciId;
    }else {
        $.supper("alert",{ title:"操作提示", msg:"无数据，无法下载表格！"});
        return;
    }
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"EnterWarehouseExportService.exportCheckInvent","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}

var countCheckWithSave = 0;
var countNoCheckWithSave = 0;
var allCount = 0;
function initSaveCount() {
    $('#startCheck').attr('disabled', false);
    if (_ciId != undefined)
        return;
    allCount = $($('#_all_win_datagrid_pg .totalCountLabel span')[0]).text();
    $('#allCount').text(allCount);
    $('#checkCount').text(countCheckWithSave);
    $('#noCheckCount').text(countCheckWithSave);
}

function changeCountWithSave() {
    $('#checkCount').text(countCheckWithSave);
    $('#noCheckCount').text(allCount - countCheckWithSave);
}


function initCount() {
    if (_ciId == undefined)
        return;
    let data = 'ciId=' + _ciId;
    $.supper("doservice", {
        "service": "mdInventoryService.getCheckNoCheckCount", "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                let val = jsondata.obj;
                allCount = val.allCount;
                $('#allCount').text(val.allCount);
                $('#checkCount').text(val.checkCount);
                $('#noCheckCount').text(val.noCheckCount);
                countCheckWithSave = val.checkCount;
            }
        }
    });
}

function filterCheck() {
    let data = $('#filterForm').serialize();
    console.log(data)
    if (checkRender != undefined) {
        let value = checkRender.getValue();
        let checkPart = '';
        let checkParts = '';
        for (let idx = 0; idx < value.length; idx ++) {
            if (value[idx].group == '1')
                checkPart += value[idx].value + ',';
            else
                checkParts += value[idx].value.split('_')[0] + ',';
        }

        if (checkPart != '') {
            checkPart = checkPart.substring(0, checkPart.length - 1);
            data += '&checkPart=' + checkPart;
        }
        if (checkParts != '') {
            checkParts = checkParts.substring(0, checkParts.length - 1);
            data += '&checkParts=' + checkParts;
        }
    }
    main_search();
}
var checkPartArray = [];
var checkRender;
function initPart(checkPart, checkPartCombine) {
    let dis = false;
    if (_ciId != undefined && _edit == undefined) {
        dis = true;
    }
    checkRender = xmSelect.render({
        el: '#checkPart',
        // autoRow: true,
        size: 'medium',
        filterable: true,
        showCount: 3,
        disabled: dis,
        tree: {
            show: true,
            showFolderIcon: true,
            showLine: true,
            indent: 20,
            strict: false, //是否开启严选，点父级是否选择子级
            expandedKeys: [ -3 ],
        },
        toolbar: {
            show: true,
            list: [ "ALL", "CLEAR"]
        },
        filterable: true,
        // height: 'auto',
        data(){
            return []
        }
    })
    // if (_ciId != undefined && _edit == undefined) {
    //     checkRender.update({
    //         disabled: true,
    //         data: checkParts.split(',') == '' ? [] :  checkParts.split(',')
    //     })
    // } else {
        $.supper("doservice", {
            "service": "mdMaterielPartService.getMdMaterielPartList1", "data": '', "BackE": function (jsondata) {
                let list = jsondata;
                checkRender.update({
                    data: list,
                    // autoRow: true,
                })
                if (_ciId != undefined) {
                    let checkPartArr = [];
                    if (checkPart != undefined && checkPart != '') {
                        checkPartArr = checkPart.split(',');
                    }
                    let checkPartsArr = [];
                    if (checkPartCombine != undefined && checkPartCombine != '') {
                        checkPartsArr = checkPartCombine.split(',');
                    }
                    checkPartArr = checkPartArr.concat(checkPartsArr);
                    checkRender.append(checkPartArr);
                    // checkRender.update({
                    //     disabled: true,
                        // data: checkParts.split(',') == '' ? [] : checkParts.split(',')
                    // })
                }
            }
        });
    // }
    // layui.use(['jquery', 'formSelects'], function () {
    //     var formSelects = layui.formSelects;
    //     formSelects.config('checkPart', {
    //         keyName: 'name',
    //         keyVal: 'id'
    //     }).data('checkPart', 'server', {
    //         beforeSuccess: function (id, url, searchVal, result) {        //success之前的回调, 干嘛呢? 处理数据的, 如果后台不想修改数据, 你也不想修改源码, 那就用这种方式处理下数据结构吧
    //             checkPartArray = result;
    //             return result;  //必须return一个结果, 这个结果要符合对应的数据结构
    //         },
    //         url: $.supper("getServicePath", {
    //             "service": 'mdMaterielPartService.getMdMaterielPartList1',
    //             "data": ''
    //         })
    //     });
    //     clearSelects = function () {
    //         formSelects.value('checkPart', [])
    //     }
    // });

    // var str = "<option value=\"\">请选择分类</option>";
    //
    // $.supper("doservice", {
    //     "service": "mdMaterielPartService.getMdMaterielPartList1", "data": '', "BackE": function (jsondata) {
    //         let list = jsondata;
    //         if (list.length <= 0)
    //             return;
    //         for (var i = 0; i < list.length; i++) {
    //             str += "<option value=\"" + list[i].id + "\">" + list[i].name + "</option>";
    //         }
    //         $('#checkPart').html(str);
    //     }
    // });

}

var _ciId;
var _ciIdTemp;
var _edit;
var _url;
var _applicantNameService = 'mdInventoryService.getBrandDistinct';
var _applicantNameSelectAction = {serviceName: _applicantNameService};

var _applicantNameCheckService = 'mdInventoryService.getCheckBrandDistinct';
var _applicantNameCheckSelectAction = {serviceName: _applicantNameCheckService};

$(function () {

    var checkInventory = $.supper("getProductArray", "checkInventory");
    if (checkInventory != null) {
        _url = checkInventory.url;
        if (checkInventory.ciId != undefined)
            _ciId = checkInventory.ciId;
        if (checkInventory.isEdit != undefined) {
            _edit = checkInventory.isEdit;
        }
    }
    // $.supper("setProductArray", {"name": "checkInventory", "value": null});

    _all_win_tools_but.xtoolbar('create', _Toolbar);

    _all_win_searchForm.xform('createForm', _searchForm);
    _all_div_hidden.xform('createhidden', _searchForm.hiddenitems);
    _all_datagrid_height = $(window).height() - 104 - 140;

    _DataGrid.height = _all_datagrid_height;

    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);
    _all_win_datagrid.on("loadSuccess",initSaveCount);

    if (_ciId != undefined && _edit == undefined) {
        $('#save').hide();
        // $('#filter').hide();
        $('#win_but_search').hide();
        $('#startCheck').hide();
        $('#saveForm input').attr('readonly', 'readonly');
        $('#saveForm select').attr('disabled', 'disabled');
    }

    if (_ciId == undefined) {
        $('#startCheck').attr('disabled', false);
        $.supper("doservice", {
            "service": "SysParameterService.getNewCode",
            "options": {
                "type": "post",
                "data": "prefix=CI",
                "async": false
            }, "ifloading": 1, "BackE": function (jsondata) {
                if (jsondata.code == "1" && jsondata.obj) {
                    $('#checkCode').val(jsondata.obj);
                }
            }
        });
    }

    let checkPart = '';
    let checkPartCombine = '';
    if (_ciId != undefined) {
        $.supper("doservice", {
            "service": _all_findAction, "data": 'ciId=' + _ciId, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    $('#checkName').val(jsondata.obj.checkName);
                    $('#checkCode').val(jsondata.obj.checkCode);
                    $('#checkType').val(jsondata.obj.checkType);
                    // $('#checkPart').val(jsondata.obj.checkPart);
                    checkPart = jsondata.obj.checkPart;
                    checkPartCombine = jsondata.obj.checkPartCombine;
                    $('#remark').val(jsondata.obj.remark);
                    $('#mddName').val(jsondata.obj.mddName);
                    $('#brandSel').val(jsondata.obj.brand);
                    $('#batchCode').val(jsondata.obj.batchCode);
                    initPart(checkPart, checkPartCombine);
                }
            }
        });
        // main_search();
    }
    let service = '';
    if (_ciId == undefined) {
        _applicantNameSelectAction.data = '&distinctName=brand';
        service = _applicantNameSelectAction;
    } else {
        _applicantNameCheckSelectAction.data = '&distinctName=brand&ciId=' + _ciId;
        service = _applicantNameCheckSelectAction;
    }

    $('#brandSel').on('focus', function () {
        $('#brandSel').editableSelect('clear');
        var shqJson=$.supper("doselectService", service);
        var items = shqJson.obj;
        for(var  i = 0 ;i < items.length ;i++){
            $('#brandSel').editableSelect('add', items[i].brand)
        }
        $('#brandSel').editableSelect('show');
    })
    // if (_ciId == undefined) {
    //     main_search();
    // }
    if (_ciId == undefined){
        initPart();
    }
    filterCheck();
})

function initBrandSel() {
    $('#brandSel').editableSelect('reset');
    let service = '';
    if (_ciId == undefined || editStartCheck == true) {
        let data = '&distinctName=brand';
        if (checkRender != undefined) {
            let value = checkRender.getValue();
            let checkPart = '';
            let checkParts = '';
            for (let idx = 0; idx < value.length; idx ++) {
                if (value[idx].group == '1')
                    checkPart += value[idx].value + ',';
                else
                    checkParts += value[idx].value.split('_')[0] + ',';
            }
            if (checkPart != '') {
                checkPart = checkPart.substring(0, checkPart.length - 1);
                data += '&checkPart=' + checkPart;
            }
            if (checkParts != '') {
                checkParts = checkParts.substring(0, checkParts.length - 1);
                data += '&checkParts=' + checkParts;
            }
        }
        _applicantNameSelectAction.data = data;
        service = _applicantNameSelectAction;
    } else {
        _applicantNameCheckSelectAction.data = '&distinctName=brand&ciId=' + _ciId;
        service = _applicantNameCheckSelectAction;
    }
    $('#brandSel').editableSelect('clear');
    var shqJson=$.supper("doselectService", service);
    var items = shqJson.obj;
    for(var  i = 0 ;i < items.length ;i++){
        $('#brandSel').editableSelect('add', items[i].brand)
    }
}