var _sameProductCode;
var _search_win_form = $('#win_form_search');
var _win_tools_but = $("#win_tools_but");
var _all_SelectAction = "SysSameProductService.getSameProductProducts";
var _delete_Product = 'SysSameProductService.deleteObject';

var _applicantNameService = 'SysSameProductService.getAllPagerModelObjectDistinct';
var _applicantNameSelectAction = {serviceName: _applicantNameService};

var _searchForm = {
    lineNum: 2,
    columnNum: 2,
    control: [],
    groupTag: [],
    items: [
        {
            title: "供应商",
            name: "applicationName",
            placeholder: "供应商"
        },
        {
            title: "状态",
            name: "state",
            type: "select",
            impCode: "",
            placeholder: "状态",
            data: [{id: '1', name: '上架'}, {id: 2, name: '下架'}]
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
    _sameProductCode = $.supper("getProductArray", "sameProductCode");

    _search_win_form.xform('createForm', _searchForm);
    _win_tools_but.xtoolbar('create', _supplierToolbar);
    $("#win_but_search").css("width", "95px");
    $("#win_but_search").css("vertical-align", "middle");

    initProductList();
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
    _applicantNameSelectAction.data = 'sysSameProductCode=' + _sameProductCode + '&distinctName=applicant_name&limit=0';
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
    initProductList();
}

function resetSearch() {
    _search_win_form.xform('cleanForm', _searchForm);
    main_search();
}

function initProductList() {
    let data = _search_win_form.serialize();
    data += '&sysSameProductCode=' + _sameProductCode;
    $.supper('initPagination', {
        id: "Pagination",
        service: _all_SelectAction,
        data: data,
        limit: 5,
        isAjax: "1",
        ifloading: true,
        "BackE": initList
    });
}

function initList(dataList) {
    let str = '';
    let css = '';
    if (dataList != null && dataList.length > 0) {
        for (let i = 0; i < dataList.length; i++) {
            let data = dataList[i];
            let mmfList = data.mmf;
            if (i % 2 == 0) {
                css = 'back-color';
            } else {
                css = '';
            }
            str += '<div class="li-display display-flex border-bottom-list ' + css + '">';
            str += '<div class="flex-1 line-heigt ">' + data.wms_mi_id + '</div>';
            str += '<div class="flex-3 " style="text-align: left; min-height: 98px; padding-top: 8px;">';
            str += '<div style="float: left"><a href="' + $.supper('getbasepath') + 'xiangxi.htm?wmsMiId=' + data.wms_mi_id + '" target="_blank"><img src="' + data.less_file_path + '" width="68px" height="68px"></a></div>';
            str += '<ul>' +
                '<li><a href="' + $.supper("getbasepath") + 'xiangxi.htm?wmsMiId=' + data.wms_mi_id + '" target="_blank">' + data.mat_name + '</a></li>' +
                '<i style="color: red">' + data.applicant_Name + '</i>' +
                '</ul>';
            str += '</div>';
            str += initMaterielList(data, mmfList, data.wms_mi_id);
            str += '<div class="flex-1 line-heigt ">' + (data.state == 1 ? '上架' : '下架') + '</div>';
            str += initCtl(data.wms_mi_id);
            str += '</div>';
        }
    } else {
        str = '<div style="text-align: center; line-height: 30px; background: whitesmoke"><strong>抱歉，没有查到相关数据</strong></div>';
    }
    $("#tkList").html(str);
}

//初始化商品
function initMaterielList(data, mmfList, wmsMiId) {
    let mmf = null;
    let str = '<div class="display-mmf flex-4  " style="padding: 8px 2px">';
    for (let i = 0; i < mmfList.length; i++) {
        mmf = mmfList[i];
        str += '<div class="flex-2">' +
            '<div><span class="type">' + mmf.mmfName + '</span><span>/￥' + mmf.price + '</span></div>' +
            '</div>'
    }
    str += '</div>';
    return str;
}

function initCtl(wmsMiId) {
    let str = '';
    str += '<div class="flex-1 line-heigt">' +
        '<a class="trigger" style="color: blue;margin-left: 6px;margin-right: 6px" onclick="deleteAll(\'' + wmsMiId + '\')">删除</a>' +
        '</div>';
    return str;
}

function deleteAll(wmsMiId) {
    if (_sameProductCode === undefined || _sameProductCode === null) {
        return;
    }
    $.supper("confirm", {
        title: "删除操作", msg: "确认删除商品？", yesE: function () {
            let data = 'sameProductCode=' + _sameProductCode + '&wmsMiId=' + wmsMiId;
            $.supper("doservice", {
                "service": _delete_Product, "data": data, ifloading: true, "BackE": function (jsondata) {
                    if (jsondata.code == 1) {
                        initProductList();
                    } else if (jsondata.code == 3) {
                        $.supper("showTtemWin", {
                            "url": $.supper("getbasepath") + "/jsp/website/sysSameProductList.jsp",
                            "title": "同款商品"
                        });

                        setTimeout(function () {
                            var att_url = $.supper("getServicePath", {
                                url: "/jsp/website/sysSameProductInfo"
                            });
                            $.supper("closeTtemWin", {"url": att_url, "title": "同款详情"});
                        }, 500);

                    }
                }
            });
        }
    })
}

//增加导出同款商品信息SameCommodity
function exportTK() {
    if (_sameProductCode === undefined || _sameProductCode === null) {
        return;
    }
    var data = 'sameProductCode=' + _sameProductCode
    var newTab = window.open('about:blank');
    $.supper("doservice", {
        "service": "SysSameProductService.exportSameCommodity", "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}