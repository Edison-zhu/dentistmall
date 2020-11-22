// var _all_win_searchForm = $("#win_form_search");
// var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden = $("#win_form_hidden");

var _all_updateStateAction = 'MdMaterielFormatService.updateObjectState';

var _all_queryAction = 'MdMaterielFormatService.getPagerModelObject';
var _all_queryAction1 = 'MdMaterielFormatService.getPagerModelObject1';
var _all_questmmfNameAction = "MdMaterielFormatService.findFormObject";

var _all_checkNormAvtion = 'MdMaterielFormatService.checkFormMmfCode';
var _all_saveNormAction = "MdMaterielFormatService.saveOrUpdateObject";
var _all_saveNormAction1 = "MdMaterielFormatService.saveOrUpdateObject1"; // 新的操作方式，用这个接口，删除新增修改都用这个方法
var _all_saveAction = "MdMaterielFormatService.saveOrUpdateBatchObject";

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/storage/editInventoryWanning.jsp";

var _all_datagrid_height;

let _wmsMiId;

var layerAlert = null;
var isUpdateData;

var mmfIds = {};

// var _searchForm = {
//     lineNum: 2,
//     columnNum: 2,
//     control: [],
//     groupTag: [],
//     hiddenitems: [],
//     items: [
//         {title: "物料规格", name: "mmfName", type: "text", placeholder: "物料名称"},
//     ]
// };

// var _Toolbar = {
//     toolBarId: "tools_but",
//     items: [
//         {title: "查询", id: "win_but_search", icon: "search", colourStyle: "default", rounded: true, clickE: main_search},
//         {title: "添加", id: "win_but_add", icon: "plus", colourStyle: "primary", rounded: true, clickE: main_add}
//     ]
// };

var _DataGrid = {
    cols: [
        {title: '物料规格', sortable: true, name: 'mmfName', width: 100, align: 'center', renderer: renderMatName},
        // {
        //     title: '采购价格',
        //     sortable: true,
        //     name: 'price',
        //     width: 40,
        //     align: 'center',
        //     sortable: true,
        //     renderer: rendererprice
        // },
        // {title: '零售价格', sortable: true, name: 'retailPrice', width: 40, align: 'center', renderer: rendererretailPrice},
        // {title: '物料编号', sortable: true, name: 'mmfCode', width: 70, align: 'center', renderer: rendererId},
        {title: '操作', sortable: true, name: 'mddCon', width: 80, align: 'center', renderer: control},
    ]
    , remoteSort: false
    , name: 'inventoryListGrid'
    , height: _all_datagrid_height
    , gridtype: '2'
    , nowrap: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
}

// function rendererprice(val, item, rowIndex) {
//     let str = '';
//     str += '<input id="mmfName' + item.wmsMiId + '" name="mmfName" onclick="" value="' + item.mmfName + '" />';
//     return str;
// }

function changePrice(mmfId) {
    if (mmfIds[mmfId] == undefined) {
        mmfIds[mmfId] = {};
    }
    if (mmfIds[mmfId].price == undefined) {
        mmfIds[mmfId].price = 0;
    }
    let mon = Number($('#priceChange' + mmfId).val());
    if((CheckUtil.isPlusInteger(mon) === false && CheckUtil.isPlusFloat(mon) === false) || mon <= 0){
        mon = 0;
    }
    mmfIds[mmfId].price = mon;
}

function changeRetailPrice(mmfId) {
    if (mmfIds[mmfId] == undefined) {
        mmfIds[mmfId] = {};
    }
    if (mmfIds[mmfId].retailPrice == undefined) {
        mmfIds[mmfId].retailPrice = 0;
    }
    let mon = Number($('#retailPriceChange' + mmfId).val());
    if((CheckUtil.isPlusInteger(mon) === false && CheckUtil.isPlusFloat(mon) === false) || mon <= 0){
        mon = 0;
    }
    mmfIds[mmfId].retailPrice = mon;
}

function changeMmfName(mmfId, mmfName) {
    mmfName = $('#mmfNameChange' + mmfId).val().trim();
    if (mmfName == '') {
        layerAlert = layer.tips('规格名称不能为空！', $('#mmfNameChange' + mmfId));
        return;
    }
    $('#mmfNameChange' + mmfId).val(mmfName);
    checkMmfNameRepeat(mmfName, null, null, {mmfId: mmfId, updateOld: true});
    if (mmfIds[mmfId] == undefined) {
        mmfIds[mmfId] = {};
    }
    mmfIds[mmfId].mmfName = mmfName;
}

function changeMmfCode(mmfId, mmfCode) {
    mmfCode = $('#mmfCodeChange' + mmfId).val().trim();
    $('#mmfCodeChange' + mmfId).val(mmfCode);
    if (checkMmfCodeEmpty(mmfCode, mmfId) === false) {
        return false;
    }
    if (checkMmfCodeLength(mmfCode, mmfId) === false) {
        return false;
    }
    if (checkMmfCodeIsNumber(mmfCode, mmfId) === false) {
        return false;
    }
    if (checkMffCode(mmfCode, null, null, {mmfId: mmfId}) == false) {
        return false;
    }
    if (mmfIds[mmfId] == undefined) {
        mmfIds[mmfId] = {};
    }
    if (mmfIds[mmfId].mmfCode == undefined) {
        mmfIds[mmfId].mmfCode = '';
    }
    mmfIds[mmfId].mmfCode = $('#mmfCodeChange' + mmfId).val();
}

function rendererprice(val, item, rowIndex) {
    let str = '';
    str += '<input style="width: 90px;text-align: center" id="priceChange' + item.mmfId + '" name="price" onkeyup="$(this).val($(this).val().replace(/[^0-9.]/g,\'\'));" onblur="changePrice(' + item.mmfId + ')" value="' + (item.price == undefined ? '' : item.price) + '" />';
    return str;
}

function rendererretailPrice(val, item, rowIndex) {
    let str = '';
    str += '<input style="width: 90px;text-align: center" id="retailPriceChange' + item.mmfId + '" name="retailPrice" onkeyup="$(this).val($(this).val().replace(/[^0-9.]/g,\'\'));" onblur="changeRetailPrice(' + item.mmfId + ')" value="' + (item.retailPrice == undefined ? '' : item.retailPrice) + '" />';
    return str;
}

function renderMatName(val, item, rowIndex) {
    let str = '';
    str += '<input style="text-align: center" id="mmfNameChange' + item.mmfId + '" name="mmfName" onblur="changeMmfName(' + item.mmfId + ', this.value)" value="' + (item.mmfName == undefined ? '' : item.mmfName) + '" />';
    return str;
}

function rendererId(val, item, rowIndex) {
    let str = '';
    str += '<input style="text-align: center" id="mmfCodeChange' + item.mmfId + '" name="wmsMiId" onblur="changeMmfCode(' + item.mmfId + ', this.value)" value="' + (item.mmfCode == undefined ? '' : item.mmfCode) + '" />';
    return str;
}
// onblur="a1(\'mmfCode\',' + item.mmfId + ', this.value)"

// var rows = {};
// function a1(key,mmfId,val) {
//     // rows=rowIndex;
//     if (rows[mmfId] == undefined)
//         rows[mmfId] = {};
//     rows[mmfId][key] = val;
//     control(val, rows, null);
// }

function control(val, item, rowIndex) {
    let str = '';
     // str += '<a class="btn btn-info btn-xs" onclick="updateObject(' + item.wmsMiId + ')" >保存</a>&nbsp;&nbsp;';
    str += '<a class="btn btn-info btn-xs" onclick="updateInfo3(' + item.wmsMiId + ',' + item.mmfId + ')" >保存</a>&nbsp;&nbsp;';

    // str += '<a style="display: none" class="btn btn-info btn-xs" onclick="updateObject(' + item.wmsMiId + ')" >保存</a>&nbsp;&nbsp;';
    str += '<a class="btn btn-danger btn-xs" style="display: inline-block" onclick="deletemmfName(' + item.mmfId + ',' + item.wmsMiId + ')" >删除</a>&nbsp;&nbsp;';
    str += '<div class="switch-container" style="display: inline-block;vertical-align: middle"><input id="switch' + item.mmfId + '" type="checkbox" class="switch"';
    if (item.state != undefined && item.state == 1) {
        str += ' checked';
    }
    str += ' />' +
        '<label for="switch' + item.mmfId + '" onclick="updateState(' + item.mmfId + ', ' + item.state+ ')"></label>' +
        '</div>';
    return str;

}

function updateInfo3(wmsMiId, mmfId) {
    let mmfName = $('#mmfNameChange' + mmfId).val();

    let data = 'wmsMiId=' + wmsMiId + '&mmfId=' + mmfId + '&mmfName=' + mmfName;
    $.supper("doservice", {
        "service": _all_saveNormAction1, "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // setTimeout(function () {
                //     closeWin();
                // }, 200);
                $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: "存在相同的规格编号！"});
            }  else if (jsondata.code == '3') {
                $.supper("alert", {title: "操作提示", msg: "存在相同的规格名称！"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function updateInfo2(wmsMiId,mmfId) {
        // let mmfId = '';
        let price = '';
        let retailPrice = '';
        let mmfCode = '';
        let mmfName = '';
        for (let idx in mmfIds) {
            // mmfId += idx + ',';
            price += (mmfIds[idx].price == undefined ? -1 : mmfIds[idx].price) + ',';
            retailPrice += (mmfIds[idx].retailPrice == undefined ? -1 : mmfIds[idx].retailPrice) + ',';
            // if(mmfId == idx){
                mmfCode += (mmfIds[idx].mmfCode == undefined ? '' : mmfIds[idx].mmfCode) + ',';
                mmfName += (mmfIds[idx].mmfName == undefined ? '' : mmfIds[idx].mmfName) + ',';
            // }else {
            //     mmfCode = ''
            // }
        }
        price = price.substring(0, price.length - 1);
        retailPrice = retailPrice.substring(0, retailPrice.length - 1);
        mmfCode = mmfCode.substring(0, mmfCode.length - 1);
        mmfName = mmfName.substring(0, mmfName.length - 1);
        let data = 'wmsMiId=' + wmsMiId + '&mmfIds=' + mmfId + '&prices=' + price + '&retailPrices=' + retailPrice + '&mmfCodes=' + mmfCode + '&mmfNames=' + mmfName;
        $.supper("doservice", {
            "service": _all_saveAction, "data": data, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    // setTimeout(function () {
                    //     closeWin();
                    // }, 200);
                    $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                } else if (jsondata.code == '2') {
                    $.supper("alert", {title: "操作提示", msg: "存在相同的规格编号！"});
                }  else if (jsondata.code == '3') {
                    $.supper("alert", {title: "操作提示", msg: "存在相同的规格名称！"});
                } else {
                    $.supper("alert", {title: "操作提示", msg: "操作出错！"});
                }
            }
        });
}
//
// function updateObject(wmsMiId) {
//     $.supper("doservice", {
//         "service": _all_saveAction, "data": 'wmsMiId=' + wmsMiId, "BackE": function (jsondata) {
//             console.log('111----->',jsondata)
//             if (jsondata.code == "1") {
//                 $.supper("alert", {
//                     title: "操作提示", msg: "操作成功！", BackE: () => {
//                         main_search();
//                     }
//                 });
//             } else {
//                 $.supper("alert", {title: "操作提示", msg: "操作出错！"});
//             }
//         }
//     });
// }


function deletemmfName(mmfId, wmsMiId) {
    var vdata ="mmfId="+mmfId + '&wmsMiId=' + wmsMiId + '&mmfName=';
    let a = 'MdMaterielFormatService.deleteObject';
    $.supper("confirm",{ title:"删除操作", msg:"确认删除记录操作？" ,yesE:function(){
            $.supper("doservice", {
                "service": _all_saveNormAction1, "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                    } else
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                }
            });
        }
    });
    // let data = '';
    // if (mmfId != undefined) {
    //     data += 'mmfId=' + mmfId;
    // }
    // $.supper("confirm", {
    //     title: "删除操作", msg: "确认删除？", yesE: function () {
    //         $.supper("doservice", {
    //             "service": _all_deletemmfNameAction, "data": data, "BackE": function (jsondata) {
    //                 if (jsondata.code == "1") {
    //                     $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
    //                 } else {
    //                     $.supper("alert", {title: "操作提示", msg: "操作失败！"});
    //                 }
    //             }
    //         });
    //     }
    // });
}

// function deleteObject(wmsMiId) {
//     $.supper("doservice", {
//         "service": _all_deleteAction, "data": 'wmsMiId=' + wmsMiId, "BackE": function (jsondata) {
//             if (jsondata.code == "1") {
//                 $.supper("alert", {
//                     title: "操作提示", msg: "操作成功！", BackE: () => {
//                         main_search();
//                     }
//                 });
//             } else {
//                 $.supper("alert", {title: "操作提示", msg: "操作出错！"});
//             }
//         }
//     });
// }

function updateState(mmfId, state) {
    if ($('#switch' + mmfId).prop('checked') === true) {
        state = 2;
    } else {
        state = 1;
    }
    $.supper("doservice", {
        "service": _all_updateStateAction, "data": 'mmfId=' + mmfId + '&state=' + state, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl() {
    // var data = _all_win_searchForm.serialize();
    let data = 'mmfName=' + $('#mmfNameSearch').val();
    if (_wmsMiId != undefined) {
        data += '&wmsMiId=' + _wmsMiId;
    }
    var att_url = $.supper("getServicePath", {"service": _all_queryAction1, "data": data});
    return att_url;
    // var data = _all_win_searchForm.serialize();
    // if (_wmsMiId != undefined) {
    //     data += '&mddId=' + _wmsMiId;
    // }
    // var att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
    // return att_url;
}

/**
 * 对主表进行查询（刷新）操作
 */
function main_search() {
    isAdding = false;
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

function searchEnter() {
    if (event.keyCode == 13)
        main_search();
}

let isAdding = false;

function main_add() {
    if (isAdding == true) {
        $.supper("alert", {title: "操作提示", msg: "请完成上一次操作"});
        return;
    }
    isAdding = true;
    let str = '<tr id="tempAdd" style="background: rgba(233,251,27,0.37)">';
    str += '<td><input id="mmfNameTemp" name="mmfNameTemp" onclick="" value="" /></td>';
    // str += '<td><input id="priceTemp" name="priceTemp" onclick="" value="" /></td>';
    // str += '<td><input id="retailPriceTemp" name="retailPriceTemp" onclick="" value="" /></td>';
    // str += '<td><input id="mmfCodeTemp" name="mmfCodeTemp" onclick="" value="" /></td>';
    str += '<td style="text-align: center"><a class="btn btn-info btn-xs" onclick="savemmfName1()">保存</a>&nbsp;&nbsp;<a class="btn btn-warning btn-xs" onclick="cancelAdd()">取消</a>&nbsp;&nbsp;</td>';
    str += '</tr>';
    $('#win_datagrid_main').append($(str));
    // $('#mmfCodeTemp').on('blur', function () {
    //     var mmfCode = $(this).val();
    //     mmfCode = mmfCode.trim();
    //     $(this).val(mmfCode);
    //     // if (isUpdateData === true && mmfCode == _mmfCode) {
    //     //     return;
    //     // }
    //     if (checkMmfCodeEmpty(mmfCode) === false) {
    //         return false;
    //     }
    //     if (checkMmfCodeLength(mmfCode) === false) {
    //         return false;
    //     }
    //     if (checkMmfCodeIsNumber(mmfCode) === false) {
    //         return false;
    //     }
    //     checkMffCode($(this).val());
    // })
    // $('#mmfCodeTemp').on('focus', function () {
    //     //隐藏mmfcode的警告内容
    //     if(layerAlert !== null){
    //         layer.close(layerAlert);
    //     }
    // })
    $('#mmfNameTemp').on('blur', function () {
        let mmfName = $(this).val();
        mmfName = mmfName.trim();
        $(this).val(mmfName);
        if (mmfName == '') {
            $.supper("alert", {title: "操作提示", msg: "规格名称必填"});
            return;
        }
        checkMmfNameRepeat(mmfName);
    })
    $('#mmfNameTemp').on('focus', function () {
        //隐藏mmfcode的警告内容
        if(layerAlert !== null){
            layer.close(layerAlert);
        }
    })
}

function checkMmfNameRepeat(mmfName, save, vdata, extData) {
    var data = 'mmfName=' + mmfName + '&wmsMiId=' + _wmsMiId;
    let str = '#mmfNameTemp';
    if (extData != undefined) {
        str = '#mmfNameTemp' + extData.mmfId;
        data += '&mmfId=' + extData.mmfId;
        if (extData.updateOld != undefined) {
            str = '#mmfNameChange' + extData.mmfId;
        }
    }
    $.supper("doservice", {
        "service": 'MdMaterielFormatService.checkFormMmfName',
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {// 存在
                if (isUpdateData === true) {
                    // checkSaveAndRun();
                } else {
                    //显示重复警告信息
                    layerAlert = layer.tips('已存在规格名称，请重新填写', $(str));
                }
            } else if (jsondata.code == '2') {//不存在
                // checkSaveAndRun();
            } else if (jsondata.code == '3') {
                layerAlert = layer.tips('请填写规格编号', $(str));
            } else if (jsondata.code == '4') {
                layerAlert = layer.tips('您已下线，请重新登录', $(str));
            } else if (jsondata.code == '5') {
                layerAlert = layer.tips('此账号无法执行操作', $(str));
            }
        }
    });
}

function cancelAdd() {
    isAdding = false;
    $('#tempAdd').remove();
}

function savemmfName1() {
    if (_wmsMiId == undefined) {
        return;
    }
    let mmfName = $('#mmfNameTemp').val();

    let data = 'wmsMiId=' + _wmsMiId + '&mmfName=' + mmfName;
    $.supper("doservice", {
        "service": _all_saveNormAction1, "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // setTimeout(function () {
                //     closeWin();
                // }, 200);
                $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: "存在相同的规格编号！"});
            }  else if (jsondata.code == '3') {
                $.supper("alert", {title: "操作提示", msg: "存在相同的规格名称！"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function savemmfName(backEvent) {
    if (_wmsMiId == undefined) {
        return;
    }
    let lb_back = false;
    let lb_back2 = false;
    var price = $("#priceTemp").val();
    if (price != null && !price == '') {
        lb_back = CheckUtil.isPlusFloat(Number(price));
        lb_back2 = CheckUtil.isPlusInteger(Number(price));
        if (lb_back == false && lb_back2 == false) {
            $.supper("alert", {title: "操作提示", msg: ("价格必须为数字！")});
            return false;
        }
    }
    let retailPrice = $('#retailPriceTemp').val();
    if (retailPrice != null && !retailPrice == '') {
        lb_back = CheckUtil.isPlusFloat(Number(retailPrice));
        lb_back2 = CheckUtil.isPlusInteger(Number(retailPrice));
        if (lb_back == false && lb_back2 == false) {
            $.supper("alert", {title: "操作提示", msg: ("零售价格必须为数字！")});
            return false;
        }
    }

    var mmfCode = $('#mmfCodeTemp').val().trim();
    if (checkMmfCodeEmpty(mmfCode) === false) {
        return;
    }
    if (checkMmfCodeLength(mmfCode) === false) {
        return;
    }
    if (checkMmfCodeIsNumber(mmfCode) === false) {
        return;
    }
    var data = '';
    data += 'mmfName=' + $('#mmfNameTemp').val().trim();
    data += '&price=' + $('#priceTemp').val();
    data += '&retailPrice=' + $('#retailPriceTemp').val();
    data += '&mmfCode=' + $('#mmfCodeTemp').val().trim();
    data += '&wmsMiId=' + _wmsMiId;
    data += '&state=1';
    checkMffCode(mmfCode, saveAction, data, backEvent);
    //
    // let mmfName = $('#mmfNameTemp').val();
    //
    // let bm = $('#priceTemp').val();
    //
    // let sm = $('#retailPriceTemp').val();
    //
    // let wmsMiId = $('#mmfCodeTemp').val();
    //
    // let data = 'mmfNames=' + mmfName + '&prices=' + bm + '&retailPrices=' + sm + '&mmfCodes=' + wmsMiId + '&wmsMiId=' + _wmsMiId;
    // $.supper("doservice", {
    //     "service": _all_saveAction, "data": data, "BackE": function (jsondata) {
    //         if (jsondata.code == "1") {
    //             $.supper("alert", {
    //                 title: "操作提示", msg: "操作成功！", BackE: () => {
    //                     cancelAdd();
    //                     main_search();
    //                 }
    //             });
    //         } else {
    //             $.supper("alert", {title: "操作提示", msg: "操作出错！"});
    //         }
    //     }
    // });
}
function saveAction(data, backEvent) {
    $.supper("doservice", {
        "service": _all_saveNormAction,
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                if (backEvent != undefined)
                    setTimeout(function () {
                        backEvent();
                    }, 200)

                else {
                    $.supper("alert", {
                        title: "操作提示",
                        msg: "操作成功！",
                        BackE: function () {
                            isAdding = false;
                            $('#tempAdd').remove();
                            $('#normTable').hide();
                            main_search();
                        }
                    });
                }
            } else if (jsondata.code == '2') {
                layerAlert = layer.tips('已存在规格名称，请重新填写', $(str));
            } else {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作失败！"
                });
            }
        }
    });
}
function checkMmfCodeEmpty(mmfCode, mmfId) {
    let str = '#mmfCodeTemp';
    if (mmfId != undefined) {
        str = '#mmfCodeTemp' + mmfId;
    }
    if (mmfCode.toString().trim() === '') {
        layerAlert = layer.tips('不能为空', $(str));
        //是否为空警告信息
        return false;
    }
    return true;
}

function checkMmfCodeLength(mmfCode, mmfId) {
    let str = '#mmfCodeTemp';
    if (mmfId != undefined) {
        str = '#mmfCodeTemp' + mmfId;
    }

    if (mmfCode.length < 5 || mmfCode.length > 15) {
        //字数警告信息
        layerAlert = layer.tips('长度在5-15位', $(str));
        return false;
    }
    return true;
}

function checkMmfCodeIsNumber(mmfCode, mmfId) {
    let str = '#mmfCodeTemp';
    if (mmfId != undefined) {
        str = '#mmfCodeTemp' + mmfId;
    }
    if (/^\w+$/.test(mmfCode) === false) {
        layerAlert = layer.tips('必须是数字或者字母组成', $(str));
        return false;
    }
    return true;
}


function checkMffCode(mmfCode, save, vdata, extData, backEvent) {
    if (_wmsMiId == undefined) {
        $.supper("alert", {
            title: "操作提示",
            msg: "请先保存物料字典信息！"
        });
        return;
    }
    var checkSaveAndRun = function () {
        if (save !== undefined && save !== null && typeof save === 'function') {
            save(vdata, extData, backEvent);
        }
    }
    var data = 'mmfCode=' + mmfCode + '&wmsMiId=' + _wmsMiId;
    let str = '#mmfCodeTemp';
    if (extData != undefined) {
        str = '#mmfCodeChange' + extData.mmfId;
        data += '&mmfId=' + extData.mmfId;
    }

    $.supper("doservice", {
        "service": _all_checkNormAvtion,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {// 存在
                if (isUpdateData != undefined && isUpdateData === true) {
                    checkSaveAndRun();
                } else {
                    //显示重复警告信息
                    layerAlert = layer.tips('已存在规格编号，请重新填写', $(str));
                }
            } else if (jsondata.code == '2') {//不存在
                checkSaveAndRun();
            } else if (jsondata.code == '3') {
                layerAlert = layer.tips('请填写规格编号', $(str));
            } else if (jsondata.code == '4') {
                layerAlert = layer.tips('您已下线，请重新登录', $(str));
            } else if (jsondata.code == '5') {
                layerAlert = layer.tips('此账号无法执行操作', $(str));
            }
        }
    });
}

function updateInfo() {
    if (_wmsMiId == undefined) {
        $.supper("alert", {title: "操作提示", msg: "数据出错！"});
        return;
    }
    let backEvent = function () {
        let mmfId = '';
        let price = '';
        let retailPrice = '';
        let mmfCode = '';
        for (let idx in mmfIds) {
            mmfId += idx + ',';
            price += (mmfIds[idx].price == undefined ? -1 : mmfIds[idx].price) + ',';
            retailPrice += (mmfIds[idx].retailPrice == undefined ? -1 : mmfIds[idx].retailPrice) + ',';
            mmfCode += (mmfIds[idx].mmfCode == undefined ? '' : mmfIds[idx].mmfCode) + ',';
        }
        if (mmfId == '') {
            closeWin();
            // $.supper("alert", {title: "操作提示", msg: "没有修改的数据！"});
            return;
        }
        mmfId = mmfId.substring(0, mmfId.length - 1);
        price = price.substring(0, price.length - 1);
        retailPrice = retailPrice.substring(0, retailPrice.length - 1);
        mmfCode = mmfCode.substring(0, mmfCode.length - 1);
        let data = 'wmsMiId=' + _wmsMiId + '&mmfIds=' + mmfId + '&prices=' + price + '&retailPrices=' + retailPrice + '&mmfCodes=' + mmfCode;
        $.supper("doservice", {
            "service": _all_saveAction, "data": data, "BackE": function (jsondata) {
                mmfIds = {};
                if (jsondata.code == "1") {
                    setTimeout(function () {
                        closeWin();
                    }, 200);
                    // $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                } else if (jsondata.code == '2') {
                    $.supper("alert", {title: "操作提示", msg: "存在相同的规格编号！"});
                } else {
                    $.supper("alert", {title: "操作提示", msg: "操作出错！"});
                }
            }
        });
    }

    if (isAdding == true) {
        savemmfName(backEvent);
    } else {
        backEvent();
    }
}

function closeWin() {
    $.supper("closeWin");
}

/**
 * 页面初始化函数
 */
$(function () {
    _wmsMiId = $.supper("getParam", 'wmsMiId');

    // _all_win_searchForm.xform('createForm', _searchForm);

    // _all_div_hidden.xform('createhidden', _searchForm.hiddenitems);

    // _all_win_tools_but.xtoolbar('create', _Toolbar);

    _all_datagrid_height = $(window).height() - 100 - 64 - 95;

    _DataGrid.height = _all_datagrid_height;

    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);

    _all_win_datagrid.on('loadSuccess', function () {
        let rows = _all_win_datagrid.rows()
        console.log(rows)
        if (rows.length <= 1 && rows[0] == undefined) {
            $('#addBtn').show();
        } else {
            $('#addBtn').hide();
        }
    })

    main_search();
});
