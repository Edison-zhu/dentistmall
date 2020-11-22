var _search_win_form = $('#win_form_search');
var _win_tools_but = $("#win_tools_but");
var _all_queryAction = 'SysSameProductService.getPagerModelObject';
var _all_saveNewProduct = 'SysSameProductService.saveSameProductByWmsMiIds';
var _all_deleteAll = 'SysSameProductService.deleteAllObject';
var _all_checkProduct = 'SysSameProductService.getObjectBySPIdAndWmsMiId';

var _applicantNameService = 'SysSameProductService.getAllPagerModelObjectDistinct';
var _applicantNameSelectAction = {serviceName: _applicantNameService};

var _searchForm = {
    lineNum: 2,
    columnNum: 3,
    control: [],
    groupTag: [],
    items: [
        {title: "同款商品编号", name: "sysSameProductCode", type: "text", placeholder: "同款商品编号、商品ID、商品名"},
        {
            title: "供应商",
            name: "applicationName",
            placeholder: "供应商"
        },
        {
            title: "状态",
            name: "spState",
            type: "select",
            impCode: "",
            placeholder: "状态",
            data: [{id: '1', name: '有效'}, {id: 2, name: '无效'}]
        },
    ]
};
var _supplierToolbar = {
    toolBarId: "tools_but",
    items: [
        {title: "搜索", id: "win_but_search", icon: "search", colourStyle: "primary", rounded: true, clickE: main_search},
        {title: "重置", id: "win_but_search", icon: "search", colourStyle: "default", rounded: true, clickE: resetSearch},
    ]
}

$(function () {
    _search_win_form.xform('createForm', _searchForm);
    _win_tools_but.xtoolbar('create', _supplierToolbar);
    $("#win_but_search").css("width", "95px");
    $("#win_but_search").css("vertical-align", "middle");

    initSameProduct();

    $('#applicationName').editableSelect({
        keyEnter: function () {
            $("#win_but_search").trigger("click");
        }
    });
    _search_win_form.serialize({
        keyEnter: function () {
            $("#win_but_search").trigger("click");
        }
    });
    _applicantNameSelectAction.data = 'distinctName=applicant_name&limit=0';
    $('#applicationName').on('focus', function () {
        $('#applicationName').editableSelect('clear');
        var shqJson = $.supper("doselectService", _applicantNameSelectAction);
        var items = shqJson.obj;
        for (var i = 0; i < items.length; i++) {
            $('#applicationName').editableSelect('add', items[i].applicant_name)
        }
        $('#applicationName').editableSelect('show');
    })
})

function main_search() {
    initSameProduct();
}

function resetSearch() {
    _search_win_form.xform('cleanForm', _searchForm);
    main_search();
}

function initSameProduct() {
    let data = _search_win_form.serialize();
    $.supper('initPagination', {
        id: "Pagination",
        service: _all_queryAction,
        data: data,
        limit: 5,
        isAjax: "1",
        "BackE": initList
    });
}

//初始化
function initList(dataList) {
    let str = '';
    let css = '';
    if (dataList != null && dataList.length > 0) {
        for (let i = 0; i < dataList.length; i++) {
            let data = dataList[i];
            let materielList = data.materielList;

            if (i % 2 == 0) {
                css = 'back-color';
            } else {
                css = '';
            }
            str += '<div class="li-display display-flex border-bottom-list ' + css + '">';
            str += '<div class="flex-1 line-heigt ">' + data.sysSameProductCode + '</div>';
            str += initMaterielList(materielList, data.sysSameProductCode);
            str += '<div class="flex-1 line-heigt ">' + data.count + '</div>';
            str += '<div class="flex-1 line-heigt ">' + (data.spState == 1 ? '有效' : '无效') + '</div>';
            str += initCtl(data.sysSameProductCode);
            str += '</div>';
        }
    } else {
        str = '<div style="text-align: center; line-height: 30px; background: whitesmoke"><strong>抱歉，没有查到相关数据</strong></div>';
    }
    $("#tkList").html(str);
}

//初始化商品
function initMaterielList(materielList, sysSameProductCode) {

    let str = '<div class="flex-6 block " style="display: flex; align-items: center; text-align: left; min-height: 98px; padding-top: 8px; vertical-align: center">';
    str += '<div style="width: 10%; flex: 1;vertical-align: top;line-height: 86px" class="line-heigt">' +
        '<a style="vertical-align: top;outline: 1px solid;" class="butyfile add btn btn-white" onclick="addSameProductNewOrOld(0, \'' + sysSameProductCode + '\')" value="选择商品">' +
        '<span style="font-size: 34px">+</span><br><span>选择商品</span>' +
        '</a>' +
        '</div>';
    if (materielList == null || materielList.length < 0) {
        str += '</div>';
        return str;
    }
    let mat = null;
    for (let i = 0; i < materielList.length; i++) {
        mat = materielList[i];
        str += '<div style="flex: 2; display: inline-block; margin-left: 10px">';
        str += '<div style="float: left"><a href="' + $.supper('getbasepath') + 'xiangxi.htm?wmsMiId=' + mat.wms_mi_id + '" target="_blank"><img src="' + mat.less_file_path + '" width="68px" height="68px"></a></div>';
        str += '<ul>' +
            '<li><a href="' + $.supper("getbasepath") + 'xiangxi.htm?wmsMiId=' + mat.wms_mi_id + '" target="_blank">' + mat.mat_name + '</a></li>' +
            '<i style="color: red">' + mat.applicant_name + '</i>' +
            '</ul>';
        str += '</div>';
    }
    str += '</div>';
    return str;
}

function initCtl(sysSameProductCode) {
    let str = '';
    str += '<div class="flex-1 line-heigt">' +
        '<a class="trigger" style="color: blue;margin-left: 6px;margin-right: 6px" onclick="deleteAll(\'' + sysSameProductCode + '\')">删除</a>' +
        '<a class="trigger" style="color: blue;margin-left: 6px;margin-right: 6px" onclick="moreInfo(\'' + sysSameProductCode + '\')">更多同款</a>' +
        '</div>';
    return str;
}

function deleteAll(sysSameProductCode) {
    $.supper("confirm", {
        title: "删除操作", msg: "确认删除同款商品？", yesE: function () {
            let data = 'sameProductCode=' + sysSameProductCode;
            $.supper("doservice", {
                "service": _all_deleteAll, "data": data, ifloading: true, "BackE": function (jsondata) {
                    // if (jsondata.code == 1) {
                    initSameProduct();
                    // }
                }
            });
        }
    });
}

function moreInfo(sysSameProductCode) {
    $.supper("setProductArray", {"name": "sameProductCode", "value": sysSameProductCode});
    var att_url = $.supper("getServicePath", {
        url: "/jsp/website/sysSameProductInfo"
    });
    $.supper("showTtemWin", {"url": att_url, "title": "同款详情"});
}

let _sysSameProductCode = '';

//添加同款商品，1表示全新添加，0表示对存在的同款商品继续增加
function addSameProductNewOrOld(newOrOld, sysSameProductCode) {
    if (newOrOld == 1) {
        _sysSameProductCode = '';
        showAddPanel(newOrOld);
    } else {
        _sysSameProductCode = sysSameProductCode;
        showAddPanel(newOrOld);
    }
}

function showAddPanel(newOrOld) {
    $('#divFilter').show('fast');
    $('.u-mask').show();
    if (newOrOld == true) {
        $('#showDiv').show();
    } else {
        $('#showDiv').hide();
    }
    let top = ($(window).height() - $('#divFilter').height()) / 4;
    let left = ($(window).width() - $('#divFilter').width()) / 4;
    let scrollTop = $(document).scrollTop();
    let scrollLeft = $(document).scrollLeft();
    $('#divFilter').css({position: 'absolute', 'top': top + scrollTop, left: left + scrollLeft});
}

var wmsMiIds = '';

function checkURL(selector, url, index) {
    if (url.indexOf($.supper("getbasepath") + "xiangxi.htm?wmsMiId=") > 0) {
        let reg = new RegExp('wmsMiId=[0-9]*', 'i');
        let str = url.match(reg);
        let wmsMiId = str[0].substring(8);
        if (index == 1) {
            wmsMiIdStr1 = wmsMiId;
        } else {
            wmsMiIdStr2 = wmsMiId;
        }
        if (wmsMiIdStr1 == wmsMiIdStr2) {
            $(selector).addClass('border-wrong');
            $('#saveBtn').attr('disabled', 'disabled');
            $('#saveBtn').addClass('layui-btn-disabled');
            tips[index] = layer.tips('请输入不同商品', selector, {time: 0});
            if (index == 1) {
                $('#loading1').hide();
            } else {
                $('#loading2').hide();
            }
            return true;
        }
        if (CheckUtil.isPlusInteger(Number(wmsMiId)) == false) {
            return false;
        }
        checkProduct(selector, wmsMiId, index);
        return true;
    }
    return false;
}

function checkNumber(selector, val, index) {
    if (CheckUtil.isPlusInteger(Number(val))) {
        if (index == 1) {
            wmsMiIdStr1 = Number(val);
        } else {
            wmsMiIdStr2 = Number(val);
        }
        if (wmsMiIdStr2 == wmsMiIdStr1) {
            $(selector).addClass('border-wrong');
            $('#saveBtn').attr('disabled', 'disabled');
            $('#saveBtn').addClass('layui-btn-disabled');
            tips[index] = layer.tips('请输入不同商品', selector, {time: 0});
            if (index == 1) {
                $('#loading1').hide();
            } else {
                $('#loading2').hide();
            }
            return true;
        }
        if (CheckUtil.isPlusInteger(Number(val)) == false) {
            return false;
        }
        checkProduct(selector, val, index);
        return true;
    }
    return false;
}

let wmsMiIdStr1 = '';
let wmsMiIdStr2 = '';

function checkProduct(selector, wmsMiId, index) {
    let data = 'wmsMiIdString=' + wmsMiId + '&sysSameProductCode=' + _sysSameProductCode;

    $.supper("doservice", {
        "service": _all_checkProduct, "data": data, "BackE": function (jsondata) {
            if ($('#loading1').is(':visible')) {
                $('#loading1').hide();
            }
            if ($('#loading2').is(':visible')) {
                $('#loading2').hide();
            }
            if (jsondata.code == 1 && jsondata.obj != undefined && jsondata.obj != null && jsondata.obj.materielList != undefined) {
                if (wmsMiIds.indexOf(wmsMiId + ',') >= 0) {
                    wmsMiIds = wmsMiIds.replace(wmsMiId + ',', '');
                }
                wmsMiIds += wmsMiId + ',';
                $(selector).removeClass('border-wrong');
                if (index == 1) {
                    if (wmsMiId == wmsMiIdStr2) {
                        tips[index] = layer.tips('请输入不同商品', selector, {time: 0});
                        $(selector).addClass('border-wrong');
                        $('#saveBtn').attr('disabled', 'disabled');
                        $('#saveBtn').addClass('layui-btn-disabled');
                        $('#imgShow1').hide();
                        return;
                    }

                    initImg1(jsondata.obj.materielList[0]);
                } else {
                    if (wmsMiId == wmsMiIdStr1) {
                        tips[index] = layer.tips('请输入不同商品', selector, {time: 0});
                        $(selector).addClass('border-wrong');
                        $('#saveBtn').attr('disabled', 'disabled');
                        $('#saveBtn').addClass('layui-btn-disabled');
                        $('#imgShow2').hide();
                        return;
                    }
                    wmsMiIdStr2 = wmsMiId;
                    initImg2(jsondata.obj.materielList[0]);
                }
                checkBtnDisable();
                // initSameProduct();
            } else if (jsondata.code == 2) {
                tips[index] = layer.tips('该同款商品已存在', selector, {time: 0});
                $('#saveBtn').attr('disabled', 'disabled');
                $('#saveBtn').addClass('layui-btn-disabled');
                $(selector).addClass('border-wrong');
            } else if (jsondata.code == 3 || jsondata.code == 4) {
                tips[index] = layer.tips('该同款商品不存在', selector, {time: 0});
                $('#saveBtn').attr('disabled', 'disabled');
                $('#saveBtn').addClass('layui-btn-disabled');
                $(selector).addClass('border-wrong');
            }
        }
    });
}

let checkOne = false;
let checkTwo = false;

function disableBtn(index) {
    if (index == 1) {
        $('#imgShow1').hide();
    } else {
        $('#imgShow2').hide();
    }
    if (tips[index] !== null) {
        layer.close(tips[index]);
        tips[index] = null;
    }
    $('#saveBtn').attr('disabled', 'disabled');
    $('#saveBtn').addClass('layui-btn-disabled');
}

function checkBtnDisable() {
    if (_sysSameProductCode == '') {
        if (checkOne == true && checkTwo == true) {
            $('#saveBtn').removeAttr('disabled');
            $('#saveBtn').removeClass('layui-btn-disabled');
            $('#saveBtn').addClass('layui-btn-info');
        }
    } else {
        if (checkOne == true) {
            $('#saveBtn').removeAttr('disabled');
            $('#saveBtn').removeClass('layui-btn-disabled');
            $('#saveBtn').addClass('layui-btn-info');
        }
    }
}

function initImg1(materiel) {
    checkOne = true;
    $('#imgShow1').show();
    $('#productImg1').attr('src', materiel.less_file_path);
    $('#matName1').text(materiel.mat_name);
    $('#applicantName1').text(materiel.applicant_name);
}

function initImg2(materiel) {
    checkTwo = true;
    $('#imgShow2').show();
    $('#productImg2').attr('src', materiel.less_file_path);
    $('#matName2').text(materiel.mat_name);
    $('#applicantName2').text(materiel.applicant_name);
}

let tips = {};

function searchProduct1(selector, value) {
    let val = value;
    if (val == '') {
        return;
    }
    $('#loading1').show();
    // if (val == wmsMiIdStr2) {
    //     $(selector).addClass('border-wrong');
    //     return;
    // }
    if (checkURL(selector, val, 1) == false && checkNumber(selector, val, 1) == false) {
        $(selector).addClass('border-wrong');
        tips[1] = layer.tips('输入同款商品有误', selector, {time: 0});
        $('#loading1').hide();
        return;
    }
}

function searchProduct2(selector, value) {
    let val = value;
    if (val == '') {
        return;
    }
    // console.log(wmsMiIdStr1 + "  " + val)
    // if (val == wmsMiIdStr1) {
    //     $(selector).addClass('border-wrong');
    //     return;
    // }
    $('#loading2').show();
    if (checkURL(selector, val, 2) == false && checkNumber(selector, val, 2) == false) {
        $(selector).addClass('border-wrong');
        tips[2] = layer.tips('输入同款商品有误', selector, {time: 0});
        $('#loading2').hide();
        return;
    }
}

function cancelTK() {
    checkOne = false;
    checkTwo = false;

    wmsMiIds = '';

    wmsMiIdStr1 = '';
    wmsMiIdStr2 = '';

    if (tips[1] !== null) {
        layer.close(tips[1]);
        tips[1] = null;
    }
    if (tips[2] !== null) {
        layer.close(tips[2]);
        tips[2] = null;
    }

    $('#productName1').removeClass('border-wrong');
    $('#productName2').removeClass('border-wrong');

    $('#divFilter').hide('fast');
    $('.u-mask').hide('fast');

    $('#saveBtn').attr('disabled', 'disabled');
    $('#saveBtn').addClass('layui-btn-disabled');

    $('#productName1').val('');
    $('#imgShow1').hide();
    $('#productImg1').attr('src', '');
    $('#matName1').text('');
    $('#applicantName1').text('');

    $('#productName2').val('');
    $('#imgShow2').hide();
    $('#productImg2').attr('src', '');
    $('#matName2').text('');
    $('#applicantName2').text('');
}

function saveTK() {
    if (wmsMiIds == null || wmsMiIds == undefined || wmsMiIds.length <= 0) {
        $.supper("alert", {title: "操作提示", msg: "数据有误，请重新查询！"});
        return;
    }
    wmsMiIds = wmsMiIds.substring(0, wmsMiIds.length - 1);
    let data = 'wmsMiId=' + wmsMiIds;
    if (wmsMiIdStr1 != '') {
        let vdata = wmsMiIdStr1;
        if (wmsMiIdStr2 != '') {
            vdata = wmsMiIdStr2 + "," + wmsMiIdStr1;
        }
        data = 'wmsMiId=' + vdata;
    }
    if (_sysSameProductCode !== '') {
        data += '&sysSameProductCode=' + _sysSameProductCode;
    }
    $.supper("doservice", {
        "service": _all_saveNewProduct, "data": data, ifloading: true, "BackE": function (jsondata) {
            if (jsondata.code == 1) {
                cancelTK();
                initSameProduct();
            } else {
                $.supper("alert", {title: "操作提示", msg: "数据有误，请重新查询！"});
            }

        }
    });

}

// //增加导出同款商品信息SameCommodity  --2020.03.27 导出同款商品全部功能，方法写好，之后用
// function exportSameCommodity(){
//     var vdata = _search_win_form.serialize();
//     var newTab=window.open('about:blank');
//     $.supper("doservice", {"service":"SysSameProductService.exportSameCommodity","data":vdata, "BackE":function (jsondata) {
//             if (jsondata.code == "1") {
//                 newTab.location.href=jsondata.obj.path;
//             }else
//                 $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
//         }});
// }