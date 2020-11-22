// var _all_saveAction = 'MdMaterielPartDetailService.saveObject';
// var _all_saveAction = "MdMaterielInfoService.saveOrUpdateObject";
var _all_questAction = "MdMaterielInfoService.findFormObject";
// var _all_updateAction = 'MdMaterielPartDetailService.saveOrUpdateObject';

var _all_saveAction = "MdMaterielInfoService.saveOrUpdateObject1";
var _all_updateStateAction = 'MdMaterielFormatService.updateObjectState';
// var _all_deleteNormAction = 'MdMaterielPartDetailService.deleteNorm';
// var _all_saveNormAction = 'MdMaterielPartDetailService.saveNormObject';
// var _all_queryAction = "MdMaterielPartDetailService.getPagerViewNormObject";

var _rbbId;
var _all_table_Id = "mmfId";
var _all_saveNormAction = "MdMaterielFormatService.saveOrUpdateObject";
var _all_saveNormAction1 = "MdMaterielFormatService.saveOrUpdateObject1";
var _all_questNormAction = "MdMaterielFormatService.findFormObject";
var _all_checkNormAvtion = 'MdMaterielFormatService.checkFormMmfCode';
var _all_saveAliasAction = 'MdMaterielInfoService.saveAliasName';
var needCheckMmfCode = true; //是否需要检查重复
var isUpdateData = false;
var autoMmfCode = false; //是否自动生成
var layerAlert = null;
var _mmfCode = ''; //保存修改功能拿到的mmfcode，不检查修改时同一个mmfcode

var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_datagrid_height;
var _all_win_datagrid;

let _wmsMiId; //还需要一个专门用来一键添加的
var _wmsMiIdAdd;
var _mmfId;
var isEdit = false;
var isAdd = false;

var addWmsMiId;
var addMmfId;
var addMmfCode;

var aliasNameCache = [];

let _mdpId;
let _mdpsId;
let _mdpIdname;
let _mdpsIdname;

var _DataGrid = {
    cols: [
        {title: '物料规格', sortable: true, name: 'mmfName', width: 100, align: 'center'},
        {
            title: '采购价格',
            sortable: true,
            name: 'price',
            width: 80,
            align: 'center',
            sortable: true
        },
        {title: '零售价格', sortable: true, name: 'retailPrice', width: 80, align: 'center'},
        {title: '物料编号', sortable: true, name: 'mmfCode', width: 70, align: 'center'},
        {title: '操作', sortable: true, name: 'mddCon', width: 80, align: 'center', renderer: control},
    ]
    , remoteSort: false
    , name: 'inventoryListGrid'
    , height: 'auto'
    , width: 'auto'
    , gridtype: '2'
    , nowrap: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
}

function getMMGridUrl() {
    var data = '';
    if (_wmsMiId != undefined) {
        data += 'wmsMiId=' + _wmsMiId;
    }
    var att_url = $.supper("getServicePath", {
        "service": 'MdMaterielFormatService.getMdMaterielFormatPagerModelByWmsMiId',
        "data": data
    });
    return att_url;
}

function searchNorm() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

let that = this;

function checkMatCode() {
    let matCode = $('#matCode').val();
    let data = 'matCode=' + matCode;
    if (_wmsMiId != undefined)
        data += '&wmsMiId=' + _wmsMiId;
    $.supper("doservice", {
        "service": 'mdMaterielInfoService.getMatCodeCount', "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
            } else if (jsondata.code == "2") {
                $.supper("alert", {title: "操作提示", msg: "此仓库存在该物料号！"});
            }
        }
    });
}

function saveMdd(aliasName, normSave) {
    console.log("111")
    if (isAdd == 'true' && normSave == undefined) {
        if ($('#normTable tr').length > 1) {
            $.supper("alert", {title: "操作提示", msg: "请先保存物料规格信息！"});
            return;
        }
    }
    if (normSave == undefined) {
        if (_all_win_datagrid.rows().length < 1 || (_all_win_datagrid.rows().length == 1 && _all_win_datagrid.rows()[0] == undefined)) {
            console.log(_all_win_datagrid.rows().length)
            $.supper("alert", {title: "操作提示", msg: "请先填写物料规格信息！"});
            return;
        }
    }
    if ($('#mdpId').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "一级分类必填！"});
        return;
    }
    if ($('#matCode').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "物料号必填！"});
        return;
    }
    if ($('#matCode').val().length < 5) {
        $.supper("alert", {title: "操作提示", msg: "物料号长度大于5位！"});
        return;
    }
    checkMatCode();
    // if (checkMatCode() == false) {
    //     $.supper("alert", {title: "操作提示", msg: "此仓库存在该物料号！"});
    //     return;
    // }
    if ($('#matName').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "物料名称必填！"});
        return;
    }
    if ($('#basicUnit').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "基本单位必填！"});
        return;
    }
    if ($('#basicCoefficent').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "拆分系数必填！"});
        return;
    }
    if ($('#splitUnit').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "拆分单位必填！"});
        return;
    }
    if ($('#productName').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "包装方式必填！"});
        return;
    }

    let data = $('#saveForm').serialize();
    let action = _all_saveAction;
    if (_wmsMiId != undefined && (isAdd == undefined || isAdd == false)) {
        //     action = _all_updateAction;
        data += '&wmsMiId=' + _wmsMiId;
    }
    if (aliasNameCache.length > 0) {
        data += '&aliasName=' + aliasNameCache.join(',');
        aliasNameCache = [];
    }
    $.supper("doservice", {
        "service": action,
        "data": data + '&state=1',
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                let val = jsondata.obj;
                if (_wmsMiId == undefined) {
                    _wmsMiId = val.wmsMiId;
                    isEdit = true;
                }
                $('#initDiv input').each(function (i, v) {
                    var att_name = $(v).attr("name");
                    eval("var vals=val." + att_name + ";");
                    if (vals != undefined) {
                        $(v).val(vals);
                    }
                })
                if (aliasName == undefined && normSave == undefined)
                    if (isAdd == 'true')
                        that.backTo();
                    else
                        $.supper("alert", {
                            title: "操作提示", msg: "操作成功！"
                        });
                else if (aliasName != undefined) {
                    let count = $('#aliasNameDiv a[id^=aliasName]').length;
                    let c = $('#aliasNameDiv a[id^=aliasA]').length;
                    let str = '<a id="aliasA' + (c + 1) + '" onmouseover="showDeleteA(' + (c + 1) + ', true)" onmouseout="showDeleteA(' + (c + 1) + ', false)" style="margin-right: 8px;background-color: whitesmoke;padding: 5px 10px;border: 1px solid lightgray">' + aliasName;
                    str += '<a id="aliasName' + (count + 1) + '" onclick="deleteAliasNames(\'' + aliasName + '\', ' + (count + 1) + ')"  style="position: relative;top: -3px;left: -18px;color: #fff;background: gray;font-size: 12px;border-radius: 9px;height: 18px;width: 18px;line-height: 18px;text-align: center;">X</a>';
                    str += '</a>';
                    $('#aliasNameDiv').append(str);
                } else if (normSave != undefined)
                    normSave();
                readOnlyRatio();
            } else if (jsondata.code == "2") { // 不弹出， 已经在这之前查询过了
                // $.supper("alert", {title: "操作提示", msg: "此仓库存在该物料号！"});
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

function saveMdd1() {
    if ($('#mdpId').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "一级分类必填！"});
        return;
    }
    if ($('#matCode').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "物料号必填！"});
        return;
    }
    // if ($('#matCode').val().length < 5) {
    //     $.supper("alert", {title: "操作提示", msg: "物料号长度大于5位！"});
    //     return;
    // }
    checkMatCode();
    // if (checkMatCode() == false) {
    //     $.supper("alert", {title: "操作提示", msg: "此仓库存在该物料号！"});
    //     return;
    // }
    if ($('#matName').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "物料名称必填！"});
        return;
    }
    if ($('#basicUnit').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "基本单位必填！"});
        return;
    }
    let data = $('#saveForm').serialize();
    let action = _all_saveAction;
    if (_wmsMiId != undefined && (isAdd == undefined || isAdd == false)) {
        //     action = _all_updateAction;
        data += '&wmsMiId=' + _wmsMiId;
    }

    $.supper("doservice", {
        "service": action,
        "data": data + '&state=1',
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                let val = jsondata.obj;
                if (_wmsMiId == undefined) {
                    _wmsMiId = val.wmsMiId;
                    isEdit = true;
                }
                saveNorm1();
                readOnlyRatio();
            } else if (jsondata.code == "2") { // 不弹出， 已经在这之前查询过了
                // $.supper("alert", {title: "操作提示", msg: "此仓库存在该物料号！"});
            } else if (jsondata.code == "3") { // 不弹出， 已经在这之前查询过了
                $.supper("alert", {title: "操作提示", msg: "此仓库存在该物料名称！"});
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

function saveNorm1() {
    var data = '';
    data += 'wmsMiId=' + _wmsMiId;
    data += '&state=1';
    data += '&mmfName=' + encodeURIComponent($('#norm').val());
    $.supper("doservice", {
        "service": _all_saveNormAction1, "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                if (_wmsMiIdAdd != undefined && _wmsMiIdAdd != null) {
                    addMmfId = jsondata.obj.mmfId;
                    addMmfCode = jsondata.obj.mmfCode;
                    addWmsMiId = jsondata.obj.wmsMiId;
                }
                backTo1()
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        }
    });
}

function backTo1() {
    if (isAdd == 'true') {
        // var searchName = $.supper("getProductArray", "oneAddMat");
        // let url = '';
        // if(searchName != undefined && searchName != null){
        //     url = searchName.url;
        let data = {wmsMiId: _wmsMiId, mmfId: addMmfId, mmfCode: addMmfCode};
        $.supper("setProductArray", {"name":"oneAddMat", "value": {wmsMiId: _wmsMiId, mmfId: addMmfId, mmfCode: addMmfCode}});
        $.supper("closeWin");
        // }
        // $.supper("closeWin");
    } else {
        let urls = '/dentistmall/jsp/dentistmall/storage/mdmaterielpartDetail.jsp'
        $.supper("setProductArray", {"name": "mdMaterielPartDetail", "value": null});
        var view_url = '/dentistmall/jsp/dentistmall/storage/materielpartinfo.jsp';
        $.supper("showTtemWin", {"url": view_url, "title": '物料字典管理'});
        setTimeout(function () {
            if (url != undefined) {
                $.supper("closeTtemWin", {url: url});
            }
        }, 200);
    }
}

function deleteNorm(mmfId) {
    var vdata = "mmfId=" + mmfId;
    $.supper("confirm", {
        title: "删除操作", msg: "确认删除记录操作？", yesE: function () {
            $.supper("doservice", {
                "service": "MdMaterielFormatService.deleteObject", "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: searchNorm});
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
    //             "service": _all_deleteNormAction, "data": data, "BackE": function (jsondata) {
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


// function saveNorm() {
//     let mmfName = $('#mmfName').val();
//     if (mmfName == '') {
//         return;
//     }
//     if (_mddId == undefined) {
//         $.supper("alert", {title: "操作提示", msg: "请先保存新物料字典！"});
//         return;
//     }
//     let data = 'mdnNorm=' + mmfName + '&mddId=' + _mddId;
//     $.supper("confirm", {
//         title: "保存操作", msg: "确认保存？", yesE: function () {
//             $.supper("doservice", {
//                 "service": _all_saveNormAction, "data": data, "BackE": function (jsondata) {
//                     if (jsondata.code == "1") {
//                         $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: searchNorm});
//                     } else {
//                         $.supper("alert", {title: "操作提示", msg: "操作失败！"});
//                     }
//                 }
//             });
//         }
//     });
// }

function control(val, item, rowIndex) {
    // if (item.mmfId == _mmfId) {
    //     // addMmfId = item.mmfId;
    //     // addMmfCode = item.mmfCode;
    //     // addWmsMiId = item.wmsMiId;
    // }
    let str = '';
    if (_wmsMiId != undefined && isEdit == undefined) {
        str = '';
    } else {
        // str += '<a style="" class="btn btn-info btn-xs" onclick="saveNormTr(' + item.mmfId + ')" >修改</a>&nbsp;&nbsp;';
        str += '<a class="btn btn-danger btn-xs" style="display: inline-block" onclick="deleteNorm(' + item.mmfId + ')" >删除</a>&nbsp;&nbsp;';
        str += '<div class="switch-container" style="display: inline-block;vertical-align: middle" ><input id="switch' + item.mmfId + '" type="checkbox" class="switch"';
        if (item.state != undefined && item.state == 1) {
            str += ' checked';
        }
        str += ' />' +
            '<label for="switch' + item.mmfId + '" onclick="updateState(' + item.mmfId + ', ' + item.state + ')"></label></div>' +
            '</div>';
    }
    return str;

}

var firstCode = '';
var newCode = '';

function selectPart() {
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/selMdMateriePart.jsp"});
    var tt_win = $.supper("showWin", {
        url: att_url, title: "选择分类", icon: "fa-th-list", width: 500, height: 800, BackE: function () {
            var selMdMaterielType = $.supper("getProductArray", "selPart");
            if (selMdMaterielType != null && selMdMaterielType.id != null) {
                $('#mdpName').text(selMdMaterielType.sname);
                $('#mdpId').val(selMdMaterielType.sid);
                let code = '';
                firstCode = selMdMaterielType.sname.split(' ')[0];
                if (!CheckUtil.isInteger(firstCode)) {
                    firstCode = '';
                }
                if (selMdMaterielType.id != 0) {
                    $('#mdpsId').val(selMdMaterielType.id);
                } else {
                    $('#mdpsId').val('');
                }
                if (selMdMaterielType.name != '') {
                    $('#mdpsName').text(selMdMaterielType.name);
                    if (selMdMaterielType.name.split(' ').length > 1) {
                        firstCode = selMdMaterielType.name.split(' ')[0];
                    }
                } else {
                    $('#mdpsName').text('');
                }
                // if (CheckUtil.isInteger(firstCode)) {
                    newCode = firstCode;
                    getNewCode();
                // }
                $.supper("setProductArray", {"name": "selPart", "value": null});
            }
        }
    });
}

//选择基本单位
function selectUnit(idx) {
    if (_wmsMiId != undefined && isEdit == undefined) {
        return;
    }
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/selSysUnitParam.jsp"});
    var tt_win = $.supper("showWin", {
        url: att_url, title: "选择单位", icon: "fa-th-list", width: 300, height: 500, BackE: function () {
            var selMdMaterielType = $.supper("getProductArray", "selPart");
            if (selMdMaterielType != null && selMdMaterielType.id != null) {
                if (idx == 1) {
                    $('#basicUnit').val(selMdMaterielType.name);
                    if ($('#basicCoefficent').val() == '')
                        $('#basicCoefficent').val("1");
                    // if ($('#splitUnit').val() == '')
                    //     $('#splitUnit').val($('#basicUnit').val())
                    // $('#mdpId').val(selMdMaterielType.id);
                } else {
                    $('#splitUnit').val(selMdMaterielType.name);
                }

                $.supper("setProductArray", {"name": "selPart", "value": null});
            }
        }
    });

}

function initBasicUnit() {

}

function initSplitUnit() {

}

function saveNormTr(mmfId) {
    if (isAdding != undefined && isAdding == true) {
        $.supper("alert", {
            title: "操作提示",
            msg: "请先完成上一次操作！"
        });
        return;
    }
    main_add(mmfId);
}

function saveNorm() {
    // if (_wmsMiId == undefined) {
    //     $.supper("alert", {
    //         title: "操作提示",
    //         msg: "请先保存物料字典信息！"
    //     });
    //     return;
    // }
    // if (_all_div_body.xform('checkForm')) {
    let saveNormAction = function () {
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

        var mmfCode = $('#mmfCodeTemp').val();
        if (checkMmfCodeEmpty(mmfCode) === false) {
            return;
        }
        if (checkMmfCodeLength(mmfCode) === false) {
            return;
        }
        if (checkMmfCodeIsNumber(mmfCode) === false) {
            return;
        }
        if (checkMmfCodeExsitView(mmfCode) == false) {
            return;
        }
        if (checkMmfNameExsitView($('#mmfNameTemp').val()) == false){
            return;
        }
        var data = '';
        data += 'mmfName=' + $('#mmfNameTemp').val();
        data += '&price=' + $('#priceTemp').val();
        data += '&retailPrice=' + $('#retailPriceTemp').val();
        data += '&mmfCode=' + $('#mmfCodeTemp').val();
        data += '&wmsMiId=' + _wmsMiId;
        data += '&state=1';
        checkMffCode(mmfCode, saveAction, data);
    }
    if (_wmsMiId == undefined)
        saveMdd(null, saveNormAction);
    else
        saveNormAction();
    // }
}

function saveAction(data) {
    let str = '#mmfNameTemp';
    $.supper("doservice", {
        "service": _all_saveNormAction,
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert", {
                //     title: "操作提示",
                //     msg: "操作成功！",
                //     BackE: function () {
                        isAdding = false;
                        if (_wmsMiId != undefined)
                            isEdit = true;
                        $('#tempAdd').remove();
                        $('#normTable').hide();
                        searchNorm();
                //     }
                // });
            } else if (jsondata.code == '2') {
                layerAlert = layer.tips('已存在规格名称，请重新填写', $(str));
            } else if (jsondata.code == '3') {
                layerAlert = layer.tips('已存在规格编码，请重新填写', $(str));
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
    mmfCode = mmfCode.trim();
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
    mmfCode = mmfCode.trim();
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
    mmfCode = mmfCode.trim();
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

function checkMmfCodeExsitView(mmfCode, mmfId) {
    let rows = _all_win_datagrid.rows();
    if (rows.length <= 0)
        return true;

    let str = '#mmfCodeTemp';
    if (mmfId != undefined) {
        str = '#mmfCodeTemp' + mmfId;
    }
    for (let idx in rows) {
        if (rows[idx] != undefined && rows[idx].mmfCode.trim() == mmfCode.trim()) {
            layerAlert = layer.tips('规格编码重复', $(str));
            return false;
        }
    }
    return true;
}

function checkMmfNameRepeat(mmfName, save, vdata, extData) {
    var data = 'mmfName=' + mmfName.trim();
    // if (_wmsMiIdAdd != undefined && _wmsMiIdAdd != null)
    //     data += '&wmsMiId=' + _wmsMiIdAdd;
    // else
        if (_wmsMiId != undefined)
        data += '&wmsMiId=' + _wmsMiId;
    let str = '#mmfNameTemp';
    if (extData != undefined) {
        str = '#mmfNameTemp' + extData.mmfId;
        // data += '&mmfId=' + extData.mmfId;
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
            }
            // else if (jsondata.code == '5') {
            //     layerAlert = layer.tips('此账号无法执行操作', $(str));
            // }
        }
    });
}

function checkMmfNameExsitView(mmfName, mmfId) {
    mmfName = mmfName.trim();
    let rows = _all_win_datagrid.rows();
    if (rows.length <= 0)
        return true;

    let str = '#mmfNameTemp';
    if (mmfId != undefined) {
        str = '#mmfNameTemp' + mmfId;
    }
    for (let idx in rows) {
        if (rows[idx] != undefined && rows[idx].mmfName.trim() == mmfName) {
            layerAlert = layer.tips('规格名称重复', $(str));
            return false;
        }
    }
    return true;
}

function checkMmfNameExsitViewOnce(mmfName, mmfId) {
    mmfName = mmfName.trim();
    if (oneceList.length <= 0) {
        return true;
    }
    let str = '#mmfNameTemp' + mmfId;
    for (let idx in oneceList) {
        if (oneceList[idx].mmfId == mmfId)
            continue;
        if (oneceList[idx].mmfName.trim() == mmfName) {
            layerAlert = layer.tips('规格名称重复', $(str));
            return false;
        }
    }
    return true;
}

function checkMmfCodeExsitViewOnce(mmfCode, mmfId) {
    mmfCode = mmfCode.trim();
    if (oneceList.length <= 0) {
        return true;
    }
    let str = '#mmfCodeTemp' + mmfId;
    for (let idx in oneceList) {
        if (oneceList[idx].mmfId == mmfId)
            continue;
        if (oneceList[idx].mmfCode.trim() == mmfCode) {
            layerAlert = layer.tips('规格编码重复', $(str));
            return false;
        }
    }
    return true;
}

function checkMffCode(mmfCode, save, vdata, extData) {
    // if (_wmsMiId == undefined) {
    //     $.supper("alert", {
    //         title: "操作提示",
    //         msg: "请先保存物料字典信息！"
    //     });
    //     return;
    // }
    mmfCode = mmfCode.trim();
    var checkSaveAndRun = function () {
        if (save !== undefined && save !== null && typeof save === 'function') {
            save(vdata, extData);
        }
    }
    let str = '#mmfCodeTemp';
    if (extData != undefined) {
        str = '#mmfCodeTemp' + extData.mmfId;
    }
    var data = 'mmfCode=' + mmfCode;
    // if (_wmsMiIdAdd != undefined && _wmsMiIdAdd != null)
    //     data += '&wmsMiId=' + _wmsMiIdAdd;
    // else
        if (_wmsMiId != undefined)
        data += '&wmsMiId=' + _wmsMiId;
    $.supper("doservice", {
        "service": _all_checkNormAvtion,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {// 存在
                if (isUpdateData === true) {
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

let isAdding = false;

function main_add(mmfId) {
    if (isAdding == true) {
        return;
    }
    $('#normTable').show();
    isAdding = true;
    let str = '<tr id="tempAdd" style="background: rgba(233,251,27,0.37)">';
    str += '<td class="hahath2"><input id="mmfNameTemp" name="mmfName" onclick="" value="' + $('#norm').val() + '" /></td>';
    str += '<td class="hahath2"><input id="priceTemp" name="price" onclick="" value="" /></td>';
    str += '<td class="hahath2"><input id="retailPriceTemp" name="retailPrice" onclick="" value="" /></td>';
    str += '<td class="hahath2"><input id="mmfCodeTemp" name="mmfCode" onclick="" value="" placeholder="输入5-15个数字或字母" /></td>';
    str += '<td class="hahath3"><a class="hahabtn1" onclick="saveNorm()">保存</a><a class="hahabtn2" onclick="cancelAdd()">取消</a>';
    if (mmfId != undefined) {
        isUpdateData = false;
        // str += '<input type="hidden" name="' + mmfId + '" value="' + mmfId + '" />';
    }
    str += '</td>';
    str += '</tr>';
    $('#normTable').append($(str));
    $('#mmfCodeTemp').on('blur', function () {
        var mmfCode = $(this).val();
        mmfCode = mmfCode.trim();
        $(this).val(mmfCode);
        if (isUpdateData === true && mmfCode == _mmfCode) {
            return;
        }
        if (checkMmfCodeEmpty(mmfCode) === false) {
            return false;
        }
        if (checkMmfCodeLength(mmfCode) === false) {
            return false;
        }
        if (checkMmfCodeIsNumber(mmfCode) === false) {
            return false;
        }
        checkMffCode($(this).val());
    })
    $('#mmfCodeTemp').on('focus', function () {
        //隐藏mmfcode的警告内容
        if(layerAlert !== null){
            layer.close(layerAlert);
        }
    })
    $('#mmfNameTemp').on('blur', function () {
        let mmfName = $(this).val();
        mmfName = mmfName.trim();
        $(this).val(mmfName);
        if (mmfName == '') {
            $.supper("alert", {title: "操作提示", msg: "规格名称必填"});
            return;
        }
        if (checkMmfNameExsitView(mmfName, mmfId) == false) {
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

function oneceAdd() {
    let data = '';
    if (_wmsMiIdAdd != undefined) {
        data += "wmsMiId=" + _wmsMiIdAdd
    }
    if (_mmfId != undefined)
        data += "&mmfId=" + _mmfId;
    $.supper("doservice", {
        "service": "MdMaterielFormatService.getMdMaterielFormatPagerModelByWmsMiId", "data": data, "BackE": function (jsondata) {
            initOnce1(jsondata.items);
        }
    });
}

function initOnce1(list) {
    const data = list[0];
    if (data != null) {
        $('#norm').val(data.mmfName);
    }
}
var oneceList = [];
function initOnce(list) {
    $('#normTable').show();
    // isAdding = true;
    let str = '';
    let mmfId;
    if (list.length > 0) {
        oneceList = list;
        let needThis = false;
        for (let idx in list) {
            mmfId = list[idx].mmfId;
            if (mmfId == _mmfId)
                needThis = true;
            str = '<tr id="tempAdd' + mmfId + '" style="background: rgba(233,251,27,0.37)">';
            str += '<td class="hahath2"><input id="mmfNameTemp' + mmfId + '" name="mmfName" onkeyup="changeMmfName(this.value, ' + mmfId + ')" data-mmfname="' + list[idx].mmfName + '" data-mmfid="' + mmfId + '" onclick="" value="' + list[idx].mmfName + '" /></td>';
            str += '<td class="hahath2"><input id="priceTemp' + mmfId + '" name="price" onclick="" value="' + list[idx].price+ '" /></td>';
            str += '<td class="hahath2"><input id="retailPriceTemp' + mmfId + '" name="retailPrice" onclick="" value="' + (list[idx].retailPrice == undefined ? list[idx].price : list[idx].retailPrice) + '" /></td>';
            str += '<td class="hahath2"><input id="mmfCodeTemp' + mmfId + '" name="mmfCode" onkeyup="changeMmfCode(this.value, ' + mmfId + ')" data-mmfid="' + mmfId + '" onclick="" value="" /></td>';
            str += '<td class="hahath3"><a  class="hahabtn1" onclick="saveOnceNorm(' + mmfId + ', ' + needThis + ')">保存</a>&nbsp;&nbsp;<a  class="hahabtn2" onclick="cancelOnceAdd(' + mmfId + ')">取消</a>&nbsp;&nbsp;</td>';
            str += '</tr>';
        }
    }
    $('#normTable').append($(str));
    $('input[id^=mmfCodeTemp]').on('blur', function () {
        var mmfCode = $(this).val();
        mmfCode = mmfCode.trim();
        $(this).val(mmfCode);
        if (isUpdateData === true && mmfCode == _mmfCode) {
            return;
        }
        if (checkMmfCodeEmpty(mmfCode, $(this).data('mmfid')) === false) {
            return false;
        }
        if (checkMmfCodeLength(mmfCode, $(this).data('mmfid')) === false) {
            return false;
        }
        if (checkMmfCodeIsNumber(mmfCode, $(this).data('mmfid')) === false) {
            return false;
        }
        if (checkMmfCodeExsitViewOnce(mmfCode, $(this).data('mmfid')) == false) {
            return;
        }
        checkMffCode($(this).val(), null, null, {mmfId: $(this).data('mmfid')});
    })
    $('input[id^=mmfCodeTemp]').on('focus', function () {
        //隐藏mmfcode的警告内容
        if(layerAlert !== null){
            layer.close(layerAlert);
        }
    })
    $('input[id^=mmfNameTemp]').on('blur', function () {
        let mmfName = $(this).val();
        mmfName = mmfName.trim();
        $(this).val(mmfName);
        if (mmfName == '') {
            layerAlert = layer.tips('规格名称必填', $(this));
            return;
        }
        if (checkMmfNameExsitView(mmfName, $(this).data('mmfid')) == false) {
            return;
        }
        if (checkMmfNameExsitViewOnce(mmfName, $(this).data('mmfid')) == false) {
            return;
        }
        checkMmfNameRepeat(mmfName, null, null, {mmfId: $(this).data('mmfid')});
    })
    $('input[id^=mmfNameTemp]').on('focus', function () {
        //隐藏mmfcode的警告内容
        if(layerAlert !== null){
            layer.close(layerAlert);
        }
    })
}

function changeMmfName(mmfName, mmfId) {
    mmfName = mmfName.trim();
    for (let idx in oneceList) {
        if (oneceList[idx].mmfId == mmfId)
            oneceList[idx].mmfName = mmfName;
    }
}

function changeMmfCode(mmfCode, mmfId) {
    mmfCode = mmfCode.trim();
    for (let idx in oneceList) {
        if (oneceList[idx].mmfId == mmfId)
            oneceList[idx].mmfCode = mmfCode;
    }
}

function saveOnceNorm(mmfId, needThis) {
    // if (_wmsMiId == undefined) {
    //     return;
    // }
    let saveNormAction = function () {
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

        var mmfCode = $('#mmfCodeTemp' + mmfId).val();
        if (checkMmfCodeEmpty(mmfCode, mmfId) === false) {
            return;
        }
        if (checkMmfCodeLength(mmfCode, mmfId) === false) {
            return;
        }
        if (checkMmfCodeIsNumber(mmfCode, mmfId) === false) {
            return;
        }
        if (checkMmfCodeExsitView(mmfCode, mmfId) == false) {
            return;
        }
        if (checkMmfNameExsitView($('#mmfNameTemp' + mmfId).val(), mmfId) == false){
            return;
        }
        if (checkMmfNameExsitViewOnce($('#mmfNameTemp' + mmfId).val(), mmfId) == false){
            return;
        }

        var data = '';
        data += 'mmfName=' + $('#mmfNameTemp' + mmfId).val().trim();
        data += '&price=' + $('#priceTemp' + mmfId).val();
        data += '&retailPrice=' + $('#retailPriceTemp' + mmfId).val().trim();
        data += '&mmfCode=' + $('#mmfCodeTemp' + mmfId).val().trim();
        data += '&wmsMiId=' + _wmsMiId;
        data += '&state=1';
        checkMffCode(mmfCode, saveAction1, data, {mmfId: mmfId, needThis: needThis});
    }
    if (_wmsMiId == undefined)
        saveMdd(null, saveNormAction);
    else
        saveNormAction();
}

function saveAction1(data, extData) {
    let str = '#mmfNameTemp';
    if (extData != undefined) {
        str = '#mmfNameTemp' + extData.mmfId;
    }
    $.supper("doservice", {
        "service": _all_saveNormAction,
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                let obj = jsondata.obj;
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作成功！",
                    BackE: function () {
                        // isAdding = false;
                        if (extData.needThis == true) {
                            addMmfId = obj.mmfId;
                            addMmfCode = obj.mmfCode;
                            addWmsMiId = obj.wmsMiId;
                        }
                        $('#tempAdd' + extData.mmfId).remove();
                        $('#normTable').hide();
                        // $('#normTable').hide();
                        searchNorm();
                    }
                });
            } else if (jsondata.code == '2') {
                layerAlert = layer.tips('已存在规格名称，请重新填写', $(str));
            }else {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作失败！"
                });
            }
        }
    });
}

function cancelOnceAdd(mmfId) {
    let delIndex = -1;
    for (let idx in oneceList) {
        if (oneceList[idx].mmfId == mmfId)
            delIndex = idx;
    }
    if (delIndex != -1) {
        oneceList.splice(delIndex, 1);
    }
    $('#tempAdd' + mmfId).remove();
}

function cancelAdd() {
    isAdding = false;
    $('#tempAdd').remove();
    $('#normTable').hide();
}

function hideError() {
    $('#aliasNameError').hide();
}
function addAliasName() {
    if (_wmsMiId == undefined && aliasNameCache.length >= 3) {
        $('#aliasNameError').show();
        $('#aliasNameError').text('别名不可超过3个');
        // $.supper("alert", {title: "操作提示", msg: "请输入别名！"});
        return;
    }

    let aliasName = $('#aliasName').val().trim();

    if (aliasName == '') {
        $('#aliasNameError').show();
        $('#aliasNameError').text('别名不可为空');
        // $.supper("alert", {title: "操作提示", msg: "请输入别名！"});
        return;
    }

    if (aliasName.length > 30) {
        $('#aliasNameError').show();
        $('#aliasNameError').text('别名不可超过30个字符');
        return;
    }

    if (_wmsMiId == undefined && aliasNameCache.length < 3) {
        if (aliasNameCache.indexOf(aliasName) >= 0) {
            $('#aliasNameError').show();
            $('#aliasNameError').text('别名不可重复');
            return;
        }
        aliasNameCache.push(aliasName);
        let count = $('#aliasNameDiv a[id^=aliasName]').length;
        let c = $('#aliasNameDiv a[id^=aliasA]').length;
        let str = '<a id="aliasA' + (c + 1) + '" onmouseover="showDeleteA(' + (c + 1) + ', true)" onmouseout="showDeleteA(' + (c + 1) + ', false)" style="margin-right: 8px;background-color: whitesmoke;padding: 5px 10px;border: 1px solid lightgray">' + aliasName;
        str += '<a id="aliasName' + (count + 1) + '" onclick="deleteAliasNames(\'' + aliasName + '\', ' + (count + 1) + ')"  style="position: relative;top: -3px;left: -18px;color: #fff;background: gray;font-size: 12px;border-radius: 9px;height: 18px;width: 18px;line-height: 18px;text-align: center;">X</a>';
        str += '</a>';
        $('#aliasNameDiv').append(str);
        $('#aliasName').val('');
        // $.supper("alert", {title: "操作提示", msg: "请输入别名！"});
        return;
    }

    let data = 'wmsMiId=' + _wmsMiId;
    let aliasNames = '';
    if (aliasNameCache.length > 1) {
        if (aliasNameCache.length > 3) {
            aliasNameCache.splice(2, aliasNameCache.length - 3);
        }
        aliasNames = aliasNameCache.join(',');
    }
    if (aliasNames != '')
        aliasNames += ',' + aliasName;
    else
        aliasNames = aliasName;

    data += '&aliasName=' + aliasNames;
    let saveAlias = function () {
        $.supper("doservice", {
            "service": _all_saveAliasAction,
            "ifloading": 1,
            "data": data,
            "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    let count = $('#aliasNameDiv a[id^=aliasName]').length;
                    let c = $('#aliasNameDiv a[id^=aliasA]').length;
                    let str = '<a id="aliasA' + (c + 1) + '" onmouseover="showDeleteA(' + (c + 1) + ', true)" onmouseout="showDeleteA(' + (c + 1) + ', false)" style="margin-right: 8px;background-color: whitesmoke;padding: 5px 10px;border: 1px solid lightgray">' + aliasName + '</a>';
                    str += '<a id="aliasName' + (count + 1) + '" onclick="deleteAliasNames(\'' + aliasName + '\', ' + (count + 1) + ')" style="position: relative;top: -3px;left: -18px;color: #fff;background: gray;font-size: 12px;border-radius: 9px;height: 18px;width: 18px;line-height: 18px;text-align: center;">X</a>';
                    str += '</a>';
                    $('#aliasNameDiv').append(str);
                    $('#aliasName').val('');
                } else if (jsondata.code == "2") {
                    $.supper("alert", {
                        title: "操作提示",
                        msg: "请先保存物料字典信息！"
                    });
                } else if (jsondata.code == "3") {
                    $.supper("alert", {
                        title: "操作提示",
                        msg: "请输入别名！"
                    });
                } else if (jsondata.code == "4") {
                    $.supper("alert", {
                        title: "操作提示",
                        msg: "别名已存在！"
                    });
                } else if (jsondata.code == "6") {
                    $('#aliasNameError').show();
                    $('#aliasNameError').text('别名不可超过三个');
                }else {
                    $.supper("alert", {
                        title: "操作提示",
                        msg: "操作失败！"
                    });
                }
            }
        });
    }
    if (_wmsMiId == undefined)
        saveMdd(aliasName);
    else
        saveAlias();

}

function deleteAliasNames(aliasName, i) {
    if (_wmsMiId == undefined) {
        aliasNameCache.splice(aliasNameCache.indexOf(aliasName), 1);
        $('#aliasA' + i).remove();
        $('#aliasName' + i).remove();
        return;
    }
    var vdata = "&wmsMiId=" + _wmsMiId + "&aliasName=" + aliasName;
    $.supper("doservice", {
        "service": "MdMaterielInfoService.saveDeleteAliasName", data: vdata, isAjax: "1", "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $('#aliasA' + i).remove();
                $('#aliasName' + i).remove();
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败!"});
            }
        }
    });
}

function showDeleteA(i, flag) {
    if (i != undefined)
        return;
    if (flag == true)
        $('#aliasName' + i).show();
    else
        $('#aliasName' + i).hide();
}

function backTo() {
    console.log("111")
    if (isAdd == 'true') {
        // var searchName = $.supper("getProductArray", "oneAddMat");
        // let url = '';
        // if(searchName != undefined && searchName != null){
        //     url = searchName.url;
        let data = {wmsMiId: _wmsMiId, mmfId: addMmfId, mmfCode: addMmfCode};
        $.supper("setProductArray", {"name":"oneAddMat", "value": {wmsMiId: _wmsMiId, mmfId: addMmfId, mmfCode: addMmfCode}});
        $.supper("closeWin");
        // }
        // $.supper("closeWin");
    } else {
        // var view_url = 'jsp/dentistmall/storage/materielpartinfo.jsp';
        // $.supper("showTtemWin", {"url": view_url, "title": '物料字典管理'});
        let urls = '/dentistmall/jsp/dentistmall/storage/mdmaterielpartDetail.jsp'
        $.supper("setProductArray", {"name": "mdMaterielPartDetail", "value": null });
        setTimeout(function () {
            if (urls != undefined) {
                $.supper("closeTtemWin", {url: urls});
            }
        }, 200);
    }
}

function renderUnitParamSelect(items, selector) {
    if (items == undefined || items.length  <= 0) {
        return;
    }
    let str = '';
    for (let idx in items) {
        str += '<option value="' + items[idx].unitName + '">' + items[idx].unitName + '</option>'
    }
    if (str != '') {
        $(selector).html(str);
    }
}

// function changeCustomer() {
//     if ($('#customer').prop('checked') == false) {
//         $('#matCode').removeAttr('readonly');
//     } else {
//         $('#matCode').attr('readonly', 'readonly');
//     }
// }
function changeCustomer1(checked) {
    if (checked == true) {
        $('#matCode').removeAttr('readonly');
    } else {
        getNewCode();
        $('#matCode').attr('readonly', 'readonly');
    }
}
var treeClickLevel = 0;
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
                if (treeNode.level == 1) {
                    _mdpId = treeNode.id;
                } else if(treeNode.level == 2){
                    _mdpsId = treeNode.id;
                } else {
                    _mdpId = undefined;
                    _mdpsId = undefined;
                }
                search();
            }
        }

    }
    //设置树的初始数据
    var zNodes = [
        {id: 0, pId: "", name: "全部", isParent: true}
    ];
    $.fn.zTree.init($("#tree"), setting, zNodes);
    //自动展现第一层树
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var node = zTree.getNodesByParam("id","0");
    lastExpandNode=node;
    zTree.expandNode(node[0],  true, false, false);

}

var url;
$(function () {
    _mdpId = $.supper("getParam", 'mdpId');
    _mdpsId = $.supper("getParam", 'mdpsId');
    _mdpIdname = $.supper("getParam", 'mdpIdnameee');
    _mdpsIdname = $.supper("getParam", 'mdpsIdnameee');

    isEdit = $.supper("getParam", 'isEdit');
    isAdd = $.supper("getParam", 'isAdd');
    let isPart = $.supper("getParam", 'isPart');
    _mmfId = $.supper("getParam", 'mmfIdAdd');
    if (isPart == true || isPart == 'true') { //携带物料分类添加物料时候用
        if (_mdpId != undefined && _mdpId != 'undefined'){
            $('#mdpId').val(_mdpId);
        }
        if ( _mdpIdname != undefined && _mdpIdname != 'undefined') {
            $('#mdpName').text(_mdpIdname);
            firstCode = _mdpIdname.split(' ')[0];
            if (!CheckUtil.isInteger(firstCode)) {
                firstCode = '';
            }
        }else {
            $('#mdpName').text('');
        }
        if (_mdpsId != undefined && _mdpsId != 'undefined'){
            $('#mdpsId').val(_mdpsId);
        }
        if (_mdpsIdname != undefined && _mdpsIdname != 'undefined') {
            $('#mdpsName').text(_mdpsIdname);
            if (_mdpsIdname.split(' ').length > 1) {
                firstCode = _mdpsIdname.split(' ')[0];
            }
        }else {
            $('#mdpsName').text('');
        }
        newCode = firstCode;
        getNewCode();
    }
    if (isAdd != undefined && isAdd == 'true') { //一键添加物料
        _wmsMiIdAdd = $.supper("getParam", 'wmsMiId');
    } else {
        _wmsMiId = $.supper("getParam", 'wmsMiId');
    }
    // _all_datagrid_height = $(window).height() - 64 - 95;

    // _DataGrid.height = _all_datagrid_height;

    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);

    var checkInventory = $.supper("getProductArray", "mdMaterielPartDetail");
    if (checkInventory != null) {
        url = checkInventory.url;
    }
    $.supper("setProductArray", {"name": "mdMaterielPartDetail", "value": null });
    // laydate({
    //     elem: '#valiedDate',
    //     type: 'datetime'//日期格式
    // });
    //
    // laydate({
    //     elem: '#BasicUnitAccuracy',
    //     type: 'datetime'//日期格式
    // });

       // initBasicUnit();
    // initSplitUnit();

    // $('#validPeriodUnit')
    $.supper("doservice", {
        "service": 'sysUnitParamService.getPagerModelObject',
        "ifloading": 1,
        "data": 'supId=11',
        "BackE": function (jsondata) {
            let items = jsondata.items;
            renderUnitParamSelect(items, '#validPeriodUnit');
        }
    });
    $.supper("doservice", {
        "service": 'sysUnitParamService.getPagerModelObject',
        "ifloading": 1,
        "data": 'supId=13',
        "BackE": function (jsondata) {
            let items = jsondata.items;
            renderUnitParamSelect(items, '#weightUnit');
        }
    });


    $('#validPeriodUnit').val($('#validPeriodUnit1').val());
    $('#weightUnit').val($('#weightUnit1').val());
    // $('#basicUnit').val($('#basicUnitHidden').val());
    // $('#splitUnit').val($('#splitUnitHidden').val());

    // $('#mmfCode').on('blur', function () {
    //     var mmfCode = $(this).val();
    //     if (isUpdateData === true && mmfCode == _mmfCode) {
    //         return;
    //     }
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
    // $('#mmfCode').on('focus', function () {
    //     //隐藏mmfcode的警告内容
    //     if (layerAlert !== null) {
    //         layer.close(layerAlert);
    //     }
    // })

    initOneUploadImg("lessenFilecode","lessenFileDiv", null, {
        accept: 'image/png,image/jpg,image/jpeg,image/gif',
        acceptType: 'image',
        size: 2 * 1024
    });
    initListUploadImg("listFilecode", "imglist");

    // initOneUploadImg("lessenFilecode", "lessenFileDiv");
    // initListUploadImg("listFilecode", "imglist", null, {
    //     limit: 1,
    //     accept: 'image/png,image/jpg,image/jpeg,image/gif',
    //     acceptType: 'image',
    //     size: 3 * 1024/*KB*/
    // });

    if (_wmsMiId != undefined && isEdit == undefined) { // 详情操作
        $('#saveForm input').each(function () {
            $(this).attr('readonly', 'readonly');
        });
        $('#saveNormA').hide();
        $('#aliasAdd').hide();
        $('#customerId').hide();
        $('select').attr('disabled', 'disabled');
        $('#saveBtn').hide();
        $('#saveBtn1').hide();
        $('#selectPartA').hide();
        $('#lessenFileDiv[class=addLi]').hide();
        // $('#lessenFilecode_file').hide();
        // $('#lessenFilecode_add').hide();
        //$('#lessenFileDiv a').remove();
        $('#lessenFileDiv').on('mouseover', function () {
            $('#lessenFileDiv a').each(function (i, v) {
                $(this).remove();
            });
        });
        $('a[id^=aliasName]').remove();
    } else {

        layui.use('laydate', function () {
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#valiedDate'
                , type: 'datetime'
                // , range: "~" //或 range: '~' 来自定义分割字符
            });
        });

        layui.use('laydate', function () {
            var laydate = layui.laydate;

            //执行一个laydate实例
            laydate.render({
                elem: '#BasicUnitAccuracy'
                , type: 'datetime'
                // , range: "~" //或 range: '~' 来自定义分割字符
            });
        });
    }

    if (_wmsMiId == undefined) {
        // getNewCode();
    }

    if (_wmsMiId != undefined && (isAdd == undefined || isAdd == false)) {
        searchNorm();
    } else if (isAdd != undefined && (isAdd == true || isAdd == 'true')) {
        oneceAdd();
    }

    KindEditor_init();
    if (_wmsMiId != undefined && isEdit == undefined) { // 详情操作
        disableKindEditor();
    }

    readOnlyRatio();
    // $('#describe1').text($('#desHide').val());
    if (_wmsMiIdAdd != undefined) {
        $('#matCode').val('');
    }
})

function readOnlyRatio() {
    if (_wmsMiId != undefined) {
        $('#basicCoefficent').attr('readonly', 'readonly');
    }
}

var editorObj = new Array();
function KindEditor_init(keval){
    var att_keval_afterChange =keval?keval.afterChange:null;
    $.each($(".KindEditor"), function(i, n){
        var k_name=n.id;
        if(k_name){
            var editor = KindEditor.create('textarea[id="'+k_name+'"]',{
                cssData:'body {font-family: "微软雅黑"; font: 14px/1.5}',
                cssPath : '/dentistmall/js/plugins/kindeditor/plugins/code/prettify.css',
                uploadJson : '/dentistmall/jsp/upload_json.htm',
                fileManagerJson : '/dentistmall/jsp/file_manager_json.htm',
                allowFileManager: true,
                autoHeightMode : false ,
                afterChange : att_keval_afterChange,
                afterCreate : function() {
                    this.sync();
                },
                afterBlur:function(){
                    this.sync();
                }
            });
            editorObj[i]=editor;
        }
        if (_wmsMiId != undefined && isEdit == undefined) { // 详情操作
            $(this).attr('disabled', 'disabled');
        }
    });

}
function  all_sync(){
    $.each(editorObj, function(i, n_editor){
        n_editor.sync();
    });
}

function getKindEditor(att_id){
    $.each($(".X_kindereditor"), function(i, n){
        var k_name=n.id;
        if(k_name==att_id){
            return  editorObj[i] ;
        }
    });
}

function disableKindEditor() {
    $.each(editorObj, function(i, n_editor){
        n_editor.readonly(true);
    });
}



function getNewCode() {
    // $.supper("doservice", {
    //     "service": "SysParameterService.getNewCode",
    //     "options": {
    //         "type": "post",
    //         "data": "prefix=MAT",
    //         "async": false
    //     }, "ifloading": 1, "BackE": function (jsondata) {
    //         if (jsondata.code == "1" && jsondata.obj) {
    //             $("#matCode").val(jsondata.obj);
    //         }
    //     }
    // });
    $.supper("doservice", {
        "service": "mdMaterielInfoService.getNewCode",
        "options": {
            "type": "post",
            "async": false
        }, "ifloading": 1, "BackE": function (jsondata) {
            if (jsondata.code == "1" && jsondata.obj) {
                $("#matCode").val((newCode == '' ? '' : (newCode + '-')) + jsondata.obj);
            }
        }
    });
}

var slide = false;
function moreInfo() {
    if (slide == false)
        $('#colTable').slideDown();
    else
        $('#colTable').slideToggle();
    slide = !slide;
}
