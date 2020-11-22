var _all_accountForm = $('#queryForm');
// var _all_winform = $('#win_form_salseman_body');
var _all_winform_hidden = $('#win_form_edithidden');
var _rbaId;
var idType;
var rId;
var _all_table_Id;
var all_action;
var _excluldeId;
var _saveForm = {
    renew: true,
    lineNum: 2,
    columnNum: 2,
    control: [],
    groupTag: [],
    hiddenitems: [
        {name: "rbaId", id: "rbaId", value: "", type: "hidden"},
        {name: "rbbId", id: "rbbId", value: "", type: "hidden"},
        {name: "rbsId", id: "rbsId", value: "", type: "hidden"},

        // {name: "wzId", id: "wzId", value: "", type: "hidden"},
        // {name: "orgGxId", id: "orgGxId", type: "hidden"},
        // {title: '创建时间', name: 'createDate', type: "hidden"},
        // {title: '创建人', name: 'createRen', type: "hidden"},
        // {title: '修改时间', name: 'editDate', type: "hidden"},
        // {title: '修改人', name: 'editRen', type: "hidden"}
    ],
    items: []
};

function initFormHidden() {
    _all_winform_hidden.xform('createhidden', _saveForm.hiddenitems);

}

function cleanFormWin() {
    rId = $.supper("getParam", 'rId');
    all_action = $.supper("getParam", 'action');
    idType = $.supper("getParam", 'idType');
    // _all_winform.xform('createForm', _saveForm);
    $('#province').change(function () {
        initShi();
    });

    $('#city').change(function () {
        initArea();
    });
    initShen(null);
    initShi(null);
    initArea(null);
}

function initForm() {
    cleanFormWin();
    var att_data = idType + "=" + rId;
    if (rId != undefined && rId != null && rId != "") {
        _all_accountForm.xform('loadAjaxForm', {
            'ActionUrl': all_action, "data": att_data, "BackE": function (data) {
                initShi(data.obj.city);
                initArea(data.obj.area);
            }
        });
    }
}

function initShen(selProvince) {
    var str = "<option value=\"\">所在省</option>";
    for (var i = 0; i < shqJson.length; i++) {
        str += "<option value=\"" + shqJson[i].name + "\">" + shqJson[i].name + "</option>";
    }
    $("#province").html(str);
    if (selProvince != null && selProvince != "")
        $("#province").val(selProvince);
}

function initShi(selCity) {
    var selSheng = $("#province").val();
    var str = "<option value=\"\">所在市</option>";
    for (var i = 0; i < shqJson.length; i++) {
        if (shqJson[i].name == selSheng) {
            for (var j = 0; j < shqJson[i].city.length; j++) {
                str += "<option value=\"" + shqJson[i].city[j].name + "\">" + shqJson[i].city[j].name + "</option>";
            }
            break;
        }

    }
    $("#city").html(str);
    if (selCity != null && selCity != "")
        $("#city").val(selCity);
}

function initArea(selArea) {
    var selSheng = $("#province").val();
    var selShi = $("#city").val();
    var str = "<option value=\"\">所在区、县</option>";
    for (var i = 0; i < shqJson.length; i++) {
        if (shqJson[i].name == selSheng) {
            for (var j = 0; j < shqJson[i].city.length; j++) {
                if (shqJson[i].city[j].name == selShi) {
                    for (var k = 0; k < shqJson[i].city[j].area.length; k++) {
                        str += "<option value=\"" + shqJson[i].city[j].area[k] + "\">" + shqJson[i].city[j].area[k] + "</option>";
                    }
                    break;
                }

            }
            break;
        }
    }
    $("#area").html(str);
    if (selArea != null && selArea != "")
        $("#area").val(selArea);
}

//表格区域
var mmg;
var salesmanIds = '';
var salesNames = '';
var excludeType = 0;
var salesOpen = false;
var _queryAction = "SysSalesmanService.getSalesManViewPagerModel";
$(function () {
    var excluldeId = $.supper("getProductArray", "excluldeId");
    salesOpen = $.supper("getProductArray", "salesOpen");
    if (salesOpen == true && excluldeId !== undefined && excluldeId != null) {
        _excluldeId = excluldeId.excluldeId;
        excludeType = excluldeId.excludeType;
        excludeTypes = excluldeId.excludeTypes;
        if(excluldeId.hideType !== undefined && excluldeId.hideType == true){
            $('#hideType').hide();
        } else {
            $('#hideType').show();
        }
    } else {
        $('#hideType').show();
    }
    initFormHidden();
    initForm();
    initDataGrid();
});

var initDataGrid = function () {
    var cols = [
        {title: '姓名', name: 'salesName', width: 80, align: 'center'},
        {title: '公司名称', name: 'agentCompany', width: 80, align: 'center'},
        {title: '类型', name: 'salesType', width: 80, align: 'center', impCode: 'PAR200330102555875'},
        {title: '选择', name: 'salesAccount', width: 80, align: 'center', renderer: selectSalesMan}

    ];
    var att_mmgurl = rpc.getUrlByForm(_queryAction, "searchForm");
    let userTypeStr = '';
    if($('#salesMan').is(':checked') && $('#salesMan').val() != '') {
        userTypeStr += $('#salesMan').val() + ',';
    }
    if($('#agent').is(':checked') && $('#agent').val() != '') {
        userTypeStr += $('#agent').val() + ',';
    }
    if(userTypeStr != ''){
        userTypeStr = userTypeStr.substring(0, userTypeStr.length - 1);
    } else {
        userTypeStr = '0';
    }
    if (salesOpen == true) {
        if (excludeTypes != undefined || excludeTypes != null || excludeTypes != '') {
            att_mmgurl += '&userTypeStr=';
        } else if (excludeType !== 0) {
            att_mmgurl += '&userTypeStr=' + userTypeStr;
        }

        if (_excluldeId !== undefined && _excluldeId != null) {
            att_mmgurl += '&exludeId=' + _excluldeId;
        }
        if (excludeType !== undefined && excludeType !== 0) {
            att_mmgurl += '&excludeType=' + excludeType;
        }
        if (excludeTypes !== undefined && excludeTypes !== 0) {
            att_mmgurl += '&excludeTypes=' + excludeTypes;
        }
    }else {
        att_mmgurl += '&userTypeStr=' + userTypeStr;
    }
    att_mmgurl += '&state=1';
    mmg = $('#datagrid1').mmGrid({
        height: '280px'
        , cols: cols
        , method: 'post'
        , remoteSort: true
        , url: att_mmgurl
        // , sortName: 'SECUCODE'
        // , sortStatus: 'asc'
        // , multiSelect: true
        // , showBackboard: false
        // , checkCol: true
        , fullWidthRows: true
        , autoLoad: false
        , nowrap: true
        , plugins: [
            $('#pg').mmPaginator({})
        ]
    });
    mmg.load();
}

function selectSalesMan(val, item, rowIndex) {
    var str = '<input type="button" class="btn btn-info" value="选择" id="sales' + item.salesmanId + '" onclick="selectThis(' + item.salesmanId + ',\'' + item.salesName + '\')" />';
    return str;
}

function selectThis(salesId, salesName) {
    if (salesId === undefined || salesId === null) {
        return;
    }
    if (salesName === undefined || salesName === null) {
        return;
    }
    $.supper("setProductArray", {"name": "selSalesMan", "value": salesId + ',' + salesName});
    closeWin();
}

function closeWin() {
    $.supper("setProductArray", {"name": "excluldeId", "value": null});
    $.supper("closeWin");
}

function searchSalesMan() {
    var att_mmgurl = rpc.getUrlByForm(_queryAction, "searchForm");
    let userTypeStr = '';
    if($('#salesMan').is(':checked') && $('#salesMan').val() != '') {
        userTypeStr += $('#salesMan').val() + ',';
    }
    if($('#agent').is(':checked') && $('#agent').val() != '') {
        userTypeStr += $('#agent').val() + ',';
    }
    if(userTypeStr != ''){
        userTypeStr = userTypeStr.substring(0, userTypeStr.length - 1);
    } else {
        userTypeStr = '0';
    }
    if (salesOpen == true) {
        if (excludeTypes != undefined || excludeTypes != null || excludeTypes != '') {
            att_mmgurl += '&userTypeStr=';
        } else if (excludeType !== 0) {
            att_mmgurl += '&userTypeStr=' + userTypeStr;
        }

        if (_excluldeId !== undefined && _excluldeId != null) {
            att_mmgurl += '&exludeId=' + _excluldeId;
        }
        if (excludeType !== undefined && excludeType !== 0) {
            att_mmgurl += '&excludeType=' + excludeType;
        }
        if (excludeTypes !== undefined && excludeTypes !== 0) {
            att_mmgurl += '&excludeTypes=' + excludeTypes;
        }
    }else {
        att_mmgurl += '&userTypeStr=' + userTypeStr;
    }
    att_mmgurl += '&state=1';
    mmg.opts.url = att_mmgurl;
    mmg.load();
}