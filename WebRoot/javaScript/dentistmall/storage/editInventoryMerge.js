var _all_accountForm = $("#accountForm");
var _all_div_hidden = $("#win_form_edithidden");
var _all_div_body = $("#win_form_body");
var _all_win_tools_but = $("#win_edit_toolbar");
var _all_win_tools_but2 = $("#win_toolbar");
var _all_win_datagrid;
/***
 * 修改部分begin
 */
var _rbbId;
var _all_table_Id = "wiId";
var _all_saveAction = "MdInventoryService.saveWanning";
var _all_questAction = "MdInventoryService.findFormObject";
var _saveForm = {
    lineNum: 2,
    columnNum: 4,
    control: [],
    groupTag: [],
    hiddenitems: [
        {name: "wiId", id: "wiId", value: "", type: "hidden"}
    ],
    items: [
        {title: '物料编号', name: 'mmfCode', placeholder: "", width: 80, align: 'center'},
        {title: '商品物料名称', name: 'matName', placeholder: "", width: 80, align: 'center'},
        {title: '规格型号', name: 'mmfName', placeholder: "", width: 80, align: 'center'},
        {title: '品牌', name: 'brand', placeholder: "", width: 80, align: 'center'},
        {title: '库存数量', name: 'baseNumber', placeholder: "", width: 80, align: 'center'},
        {title: '生产厂家', name: 'productName', placeholder: "", width: 80, align: 'center'},
        {title: '批号', name: 'batchCode', placeholder: "", width: 80, align: 'center'},
        {title: '注册号', name: 'backPrinting', placeholder: "", width: 80, align: 'center'}
    ]
};


var _Toolbar = {
    toolBarId: "win_edit_toolbar",
    items: [
        {title: "取消", id: "win_but_add", icon: "close", colourStyle: "default", clickE: closeWin},
        {title: "确定合并", id: "win_but_save", icon: "save", colourStyle: "primary", clickE: saveMerge}
    ]
};

var _Toolbar2 = {
    toolBarId: "win_toolbar",
    items: [
        {title: "合并记录", id: "win_but_addlog", icon: "save", colourStyle: "success", clickE: mergeLog},
        {title: "选择物料", id: "win_but_addjc", icon: "plus", colourStyle: "success", clickE: addItem}
    ]
}

function initFormHidden() {
    _all_div_hidden.xform('createhidden', _saveForm.hiddenitems);

}


function initForm() {
    _rbbId = $.supper("getParam", _all_table_Id);
    var att_data = _all_table_Id + "=" + _rbbId;
    _all_div_body.xform('createForm', _saveForm);
    // _all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data});
    if (_rbbId != null) {
        $.supper("doservice", {
            "service": _all_questAction,
            "ifloading": 1,
            "data": att_data,
            "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    let val = jsondata.obj;
                    $("#accountForm input").each(function (i, v) {
                        var att_name = $(v).attr("name");
                        eval("var vals=val." + att_name + ";");
                        if (att_name == 'baseNumber') {
                            let quantity = val.quantity == undefined ? 0 : val.quantity;
                            let baseNumber = val.baseNumber == undefined ? 0 : val.baseNumber;
                            let ratio = val.ratio == undefined ? 1 : val.ratio;
                            baseNumber = baseNumber - quantity * ratio;
                            $(v).val(quantity + '' + val.basicUnit + baseNumber + '' + val.unit);
                        } else if (vals) {
                            $(v).text(vals);
                            $(v).val(vals);
                        }
                    });
                }
            }
        });
    }
}


function initToolBar() {
    _all_win_tools_but.xtoolbar('create', _Toolbar);
    _all_win_tools_but2.xtoolbar('create', _Toolbar2);
}

$(function () {
    initFormHidden();
    initForm();
    initToolBar();
    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);
    main_search();
    /*
     *优化按钮
     */
    $("#win_but_save").css("width", "95px");
    $("#win_but_add").css("width", "95px");
});


function closeWin() {
    $.supper("closeWin");
}

function save() {
    var matName = $("#matName").val();
    var mmfName = $("#mmfName").val();
    var warningShu = $("#warningShu").val();
    var maxShu = $("#maxShu").val();
    var minDay = $("#minDay").val();
    var ratio = $("#ratio").val();
    var basicUnit = $("#basicUnit").val();
    if (matName == null || matName == '') {
        $.supper("alert", {title: "操作提示", msg: ("请输入商品名称！")});
        return false;
    }
    if (mmfName == null || mmfName == '') {
        $.supper("alert", {title: "操作提示", msg: ("请输入规格！")});
        return false;
    }
    if (basicUnit == null || basicUnit == '') {
        $.supper("alert", {title: "操作提示", msg: ("请输入单位！")});
        return false;
    }
    if (basicUnit == null || basicUnit == '') {
        $.supper("alert", {title: "操作提示", msg: ("请输入单位！")});
        return false;
    }
    if (warningShu != null && !warningShu == '') {
        var lb_back = CheckUtil.isPlusFloat(warningShu);
        var lb_back2 = CheckUtil.isPlusInteger(warningShu);
        if (lb_back == false && lb_back2 == false) {
            $.supper("alert", {title: "操作提示", msg: ("告警数量必须为数字！")});
            return false;
        }
    }
    if (maxShu != null && !maxShu == '') {
        var lb_back = CheckUtil.isPlusFloat(maxShu);
        var lb_back2 = CheckUtil.isPlusInteger(maxShu);
        if (lb_back == false && lb_back2 == false) {
            $.supper("alert", {title: "操作提示", msg: ("最高数量必须为数字！")});
            return false;
        }
    }
    if (minDay != null && !minDay == '') {
        var lb_back2 = CheckUtil.isPlusInteger(minDay);
        if (lb_back == false && lb_back2 == false) {
            $.supper("alert", {title: "操作提示", msg: ("到货日期必须为正整数！")});
            return false;
        }
    }
    if (ratio != null && !ratio == '') {
        var lb_back2 = CheckUtil.isPlusInteger(ratio);
        if (lb_back == false && lb_back2 == false) {
            $.supper("alert", {title: "操作提示", msg: ("拆分系数必须为正整数！")});
            return false;
        }
    } else {
        $.supper("alert", {title: "操作提示", msg: ("拆分系数不能为空！")});
        return false;
    }
    var data = "wiId=" + _rbbId + "&wanningShu=" + $("#warningShu").val() + "&maxShu=" + $("#maxShu").val()
        + "&minDay=" + $("#minDay").val() + "&ratio=" + ratio + "&baseUnit=" + basicUnit + "&matName=" + matName + "&mmfName=" + mmfName;
    $.supper("doservice", {
        "service": _all_saveAction,
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

var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_queryAction = "MdInventoryService.getItemKeyMxList";
var _all_queryAction1 = "MdInventoryService.getPagerModelObject1";

var _all_datagrid_height;
var _DataGrid = {
    cols: [
        {title: '物料编号', name: 'mmfCode', width: 100, align: 'center'},
        {title: '物料名称', name: 'matName', width: 100, align: 'center'},
        {title: '物料图标', name: 'logo', width: 50, align: 'center', renderer: renderLogo},
        {title: '规格型号', name: 'mmfName', width: 50, align: 'center'},
        {title: '库存数量', name: 'baseNumber', width: 20, align: 'center', renderer: renderNumber},
        {title: '品牌', name: 'brand', width: 20, align: 'center'},
        {title: '生产厂家', name: 'productName', width: 20, align: 'center'},
        {title: '采购均价/零售价', name: 'price', width: 40, align: 'center'},
        {title: '操作', name: 'mddCon', width: 100, align: 'center', renderer: control}
    ]
    , remoteSOrt: false
    , name: 'itemKeyMxList'
    , height: 300
    , url: ""
}

function renderNumber(val, item, rowIndex) {
    let quantity = item.quantity == undefined ? 0 : item.quantity;
    let baseNumber = item.baseNumber == undefined ? 0 : item.baseNumber;
    let ratio = item.ratio == undefined ? 1 : item.ratio;
    quantity = Math.floor(baseNumber / ratio);
    baseNumber = baseNumber - quantity * ratio;
    let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    return quantity + '' + basicUnit + baseNumber + '' + (item.splitUnit == undefined ? basicUnit : item.splitUnit);
}


/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl() {
    var data = _all_table_Id + "=" + _rbbId;
    let awIds = addWiIds.join(',');
    if (addWiIds != undefined && addWiIds != '')
        data += '&addWiIds=' + awIds;
    var att_action = _all_queryAction;
    var att_url = $.supper("getServicePath", {"service": att_action, "data": data});
    return att_url;
}

/**
 * 对主表进行查询（刷新）操作
 */
function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

function renderLogo(val, item, rowIdex) {
    let str = '';
    if (item.logoPath == undefined)
        str = '';
    else
        str += '<img src="' + item.logoPath + '" style="width: 40px; height: 40px;" />';
    return str;
}

function control(val, item, rowIndex) {
    var str = "";
    str += "<a onclick=\"main_delete('" + item.mimId + "', " + item.wiId + ", " + rowIndex + ")\"  class='btn btn-danger  btn-xs'>移出</a> ";
    return str;
}

/**
 * 删除选中行记录
 * @param id 选中行记录主键值
 */
function main_delete(id, wiId, rowIndex) {
    if (addWiIds.indexOf(wiId) >= 0) {
        // _all_win_datagrid.removeRow(rowIndex);
        addWiIds.splice(addWiIds.indexOf(wiId), 1);
        main_search();
        return;
    }
    var rows = _all_win_datagrid.rows();
    if (rows.length > 1) {
        var vdata = _all_table_Id + "=" + _rbbId + "&mimId=" + id;
        $.supper("confirm", {
            title: "移出操作", msg: "确认移出库存操作？", yesE: function () {
                $.supper("doservice", {
                    "service": "MdInventoryService.delItemKeyMx", "data": vdata, "BackE": function (jsondata) {
                        if (jsondata.code == "1") {
                            $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                        } else
                            $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                    }
                });
            }
        });
    } else {
        $.supper("alert", {title: "操作提示", msg: "不允许删除唯一的商品明细！"});
        return;
    }

}

var addWiIds = [];

function addItem() {
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/selMergeInventoryList"});
    var tt_win = $.supper("showWin", {
        url: att_url, title: "选择库存信息", icon: "fa-flask", width: 1200, height: 540, BackE: function () {
            var selInvInfoArray = $.supper("getProductArray", "selInvInfoArray");
            if (selInvInfoArray != null && selInvInfoArray.length > 0) {
                for (var i = 0; i < selInvInfoArray.length; i++) {
                    var selInvInfo = selInvInfoArray[i];
                    if (selInvInfo.wiId != _rbbId && addWiIds.indexOf(selInvInfo.wiId) < 0)
                        addWiIds.push(selInvInfo.wiId);
                }
                main_search();
                $.supper("setProductArray", {"name": "selInvInfoArray", "value": null});
            }
        }
    });
}

function saveMerge() {
    // var selInvInfoArray = $.supper("getProductArray", "selInvInfoArray");
    // if (selInvInfoArray != null && selInvInfoArray.length > 0) {
    //     var addWiIds = "";
    //     for (var i = 0; i < selInvInfoArray.length; i++) {
    //         var selInvInfo = selInvInfoArray[i];
    //         if (selInvInfo.wiId != _rbbId)
    //             addWiIds += selInvInfo.wiId + ",";
    //     }
        if (addWiIds != null && addWiIds.length > 0) {
            // addWiIds = addWiIds.substring(0, addWiIds.length - 1);
            $.supper("confirm", {
                title: "合并操作", msg: "确认合并库存操作？", yesE: function () {
                    let aWiIds = addWiIds.join(',');
                    var vdata = "wiId=" + _rbbId + "&addWiIds=" + aWiIds;
                    $.supper("doservice", {
                        "service": "MdInventoryService.addItemKeyMx",
                        "data": vdata,
                        "BackE": function (jsondata) {
                            if (jsondata.code == "1") {
                                $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                            } else if(jsondata.code == '2') {
                                $.supper("alert", {title: "操作提示", msg: jsondata.meg});
                            } else
                                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                            addWiIds = [];
                            main_search();
                        }
                    });
                }
            });

        } else {
            $.supper("alert", {title: "操作提示", msg: "没有需要合并的数据！"});
        }
    //     $.supper("setProductArray", {"name": "selInvInfoArray", "value": null});
    // }
}

function mergeLog() {
    $.supper("setProductArray", {"name": "viewInventoryMerge", "value": {viewMerge: true, wiId: _rbbId}});
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/selMergeInventoryList.jsp"});
    var tt_win = $.supper("showWin", {url: att_url, title: "合并记录", icon: "fa-flask", width: 1200, height: 540});
}

