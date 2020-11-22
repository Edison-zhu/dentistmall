var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden = $("#win_form_hidden");
// var _all_win_edit_add = $('#win_form_edit_add');
// var _all_win_edit_add_hidden = $('#win_form_edit_add_hidden');

var _all_queryAction = "SysUnitParamService.getPagerModelObject";
var _all_saveActoion = "SysUnitParamService.saveOrUpdateObject";
var _all_deleteAction = "SysUnitParamService.deleteObject";
var _all_deleteAllAction = "SysUnitParamService.deleteAllObject";

var _unitNameService = 'SysUnitParamService.getUnitParamBelongType';
var _unitNameSelectAction = {serviceName: _unitNameService};

var _all_win_datagrid;
var _all_edit_width = 800;
var _all_edit_height = 700;
var _all_datagrid_height;
var _dblClick = function (data, row, col) {
    editUnit(data.upId, data.unitName, data.belongType, data.needShow);
}

let isLoad = false;
var _supId;

var _searchForm = {
    id: 'searchForm',
    lineNum: 1,
    columnNum: 3,
    control: [],
    groupTag: [],
    items: [
        {title: "单位名称", name: "unitName", type: "text", placeholder: "单位名称"},
        {
            title: "是否显示",
            name: "needShow",
            type: "select",
            placeholder: "是否显示",
            data: [{id: 1, name: '是'}, {id: 2, name: '否'}]
        },
        {title: "所属分类", name: "belongType", type: "multipleselect", placeholder: "分类"},
    ]
}
var _searchForm1 = {
    id: 'searchForm',
    lineNum: 1,
    columnNum: 2,
    control: [],
    groupTag: [],
    items: [
        {title: "单位名称", name: "unitName", type: "text", placeholder: "单位名称"},
        {
            title: "是否显示",
            name: "needShow",
            type: "select",
            placeholder: "是否显示",
            data: [{id: 1, name: '是'}, {id: 2, name: '否'}]
        },
    ]
}

var _eidtAddForm = {
    id: 'editAddForm',
    lineNum: 3,
    columnNum: 1,
    control: [],
    groupTag: [],
    hiddenitems: [
        {name: "upId", id: "upIdForm", value: "", type: "hidden"},
    ],
    items: [
        {
            title: "单位名称",
            name: "unitName",
            id: "unitNameForm",
            type: "text",
            placeholder: "单位名称",
            ariaRequired: true,
            ariaInvalid: 'length',
            aiData: {min: 0, max: 8}
        },
        {title: "所属分类", name: "belongType", id: "belongTypeForm", type: "text", placeholder: "分类", ariaRequired: true},
        {
            title: "是否显示",
            name: "needShow",
            id: "needShowForm",
            type: "userdefined",
            placeholder: "是否显示",
            renderer: renderNeedShowForm,
            data: [{id: 1, name: '是'}, {id: 2, name: '否'}],
            ariaRequired: true,
        },
    ]
}

function initBelongType(upId) {
    // $('#belongType').editableSelect({
    //     keyEnter: function () {
    //         $("#win_but_search").trigger("click");
    //     }
    // });
    let data = '';
    if (upId !== undefined) {
        data = 'upId=' + upId;
    }
    _unitNameSelectAction.data = data;
    // $('#belongTypeForm').on('focus', function () {
    isLoad = true
    $('#belongTypeForm').editableSelect('clear');
    const shqJson = $.supper("doselectService", _unitNameSelectAction);
    const items = shqJson.obj;
    for (let i = 0; i < items.length; i++) {
        $('#belongTypeForm').editableSelect('add', items[i].belong_type)
    }
    // $('#belongTypeForm').editableSelect('show');
    // })
}

var _Toolbar = {
    toolBarId: "tools_but",
    items: [
        {title: "查询", id: "win_but_search", icon: "search", colourStyle: "default", rounded: true, clickE: main_search},
        {title: "新增单位", id: "win_but_add", icon: "plus", colourStyle: "primary", rounded: true, clickE: editUnit}
    ]
}


var _DataGrid = {
    cols: [
        {title: '编号', sortable: true, name: 'unitCode', width: 80, align: 'center'},
        {title: '名称', sortable: true, name: 'unitName', width: 80, align: 'center'},
        {title: '是否显示', sortable: true, name: 'needShow', width: 80, align: 'center', renderer: renderNeedShow},
        {title: '所属分类', sortable: true, name: 'SUnitName', width: 80, align: 'center'},
        {title: '创建人', sortable: true, name: 'createRen', width: 80, align: 'center'},
        {title: '创建时间', sortable: true, name: 'createDate', width: 80, align: 'center'},
        {title: '操作', name: 'control', width: 160, align: 'center', renderer: control}
    ]
    , remoteSort: false
    , name: 'companyGroupListGrid'
    , height: _all_datagrid_height
    , url: getMMGridUrl()
    , dblClickFunc: _dblClick
    , mmPaginatorOpt: _all_win_datagrid_pg
}

function renderNeedShow(val, item, rowIndex) {
    let str = '';
    if (item.needShow == 2) {
        str = '否';
    } else {
        str = '是';
    }
    return str;
}

function control(val, item, rowIndex) {
    let str = '';
    if (item.isLink != 1) {
        str += '<a onclick="deleteUnit(' + item.upId + ')" class="btn btn-danger  btn-xs">删除</a>&nbsp;&nbsp;';
    }
    str += '<a onclick="editUnit(' + item.upId + ', \'' + item.unitName + '\', \'' + item.belongType + '\', \'' + item.needShow + '\')" class="btn btn-warning  btn-xs">编辑</a>&nbsp;&nbsp;';
    return str;
}

function renderNeedShowForm() {
    let str = '';
    str += '<div><input type="radio" name="needShow" id="needShow1" checked value="1" />是&nbsp;&nbsp;';
    str += '<input type="radio" name="needShow" id="needShow2" value="2" />否&nbsp;&nbsp;</div>';
    return str;
}

function deleteUnit(upId) {
    let data = 'upId=' + upId;
    $.supper("confirm", {
        title: "删除操作", msg: "确认删除？", yesE: function () {
            $.supper("doservice", {
                "service": _all_deleteAction, "data": data, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                    } else {
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                    }
                }
            });
        }
    });
}

function editUnit(upId, unitName, belongType, needShow) {
    if (_supId == undefined) {
        initBelongType()
    }
    eiditUnitParam(upId, unitName, belongType, needShow);
}

function getMMGridUrl() {
    let data1 = _all_win_searchForm.serialize();
    if (_supId != undefined) {
        data1 += '&supId=' + _supId;
    }
    var att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data1});
    return att_url;
}

/**
 * 对主表进行查询（刷新）操作
 */
function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

function submitUnit() {
    let unitName = $('#unitNameForm').val();
    if (unitName == '') {
        $.supper("alert", {title: '操作提示', msg: '单位名称不能为空'});
        return;
    }
    if (unitName.length > 8) {
        $.supper("alert", {title: '操作提示', msg: '单位名称不能超过8个字符'});
        return;
    }
    let belongType = $('#belongTypeForm').val();
    if (_supId == undefined && belongType == '') {
        $.supper("alert", {title: '操作提示', msg: '单位所属分类不能为空'});
        return;
    }

    let data = $('#win_form_edit_add').serialize();
    if (_supId != undefined) {
        data += '&supId=' + _supId;
    }
    $.supper("confirm", {
        title: "保存操作", msg: "确认保存？", yesE: function () {
            $.supper("doservice", {
                "service": _all_saveActoion, "data": data, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                        cancelUnit();
                    } else {
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                    }
                }
            });
        }
    });
}

function eiditUnitParam(upId, unitName, belongType, needShow) {
    $('#divFilter').show('fast');
    $('.u-mask').show();

    if (upId !== undefined) {
        $('#upIdForm').val(upId);
        $('#unitNameForm').val(unitName);
        $('#belongTypeForm').val(belongType);
        $('#needShowForm').val(needShow)
    }

    var top = ($(window).height() - $('#divFilter').height()) / 4;
    var left = ($(window).width() - $('#divFilter').width()) / 4;
    var scrollTop = $(document).scrollTop();
    var scrollLeft = $(document).scrollLeft();
    $('#divFilter').css({position: 'absolute', 'top': top + scrollTop, left: left + scrollLeft});
}

function cancelUnit(idx) {
    $('#divFilter').hide('fast');
    $('.u-mask').hide();
    $('#upIdForm').val('');
    $('#unitNameForm').val('');
    $('#belongTypeForm').val('');
    $('#needShowForm').val('')
    // _all_win_edit_add.xform('cleanForm', _eidtAddForm);
    if (isLoad == true) {
        $('#belongTypeForm').editableSelect('reset');
    }
}

/**
 * 页面初始化函数
 */
$(function () {
    _supId = $.supper("getParam", "supId");
    console.log(_supId);

    if (_supId == undefined) {
        _all_win_searchForm.xform('createForm', _searchForm);
        _all_div_hidden.xform('createhidden', _searchForm.hiddenitems);
    } else {
        _all_win_searchForm.xform('createForm', _searchForm1);
        _all_div_hidden.xform('createhidden', _searchForm1.hiddenitems);
    }

    _all_win_tools_but.xtoolbar('create', _Toolbar);

    // _all_win_edit_add.xform('createForm', _eidtAddForm);
    // _all_win_edit_add_hidden.xform('createhidden', _eidtAddForm.hiddenitems);

    _all_datagrid_height = $(window).height() - _all_win_searchForm.height() - 64 - 95;

    _all_win_searchForm = $('#win_form_search');
    // _all_win_edit_add = $('#win_form_edit_add');

    _DataGrid.height = _all_datagrid_height;

    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);

    layui.use(['jquery', 'formSelects'], function () {
        var formSelects = layui.formSelects;
        formSelects.config('belongType', {
            keyName: 'belong_type',
            keyVal: 'belong_type'
        }).data('belongType', 'server', {
            beforeSuccess: function (id, url, searchVal, result) {        //success之前的回调, 干嘛呢? 处理数据的, 如果后台不想修改数据, 你也不想修改源码, 那就用这种方式处理下数据结构吧
                return result.obj;  //必须return一个结果, 这个结果要符合对应的数据结构
            },
            url: $.supper("getServicePath", {
                "service": 'sysUnitParamService.getUnitParamBelongType',
                "data": _supId == undefined ? '' : 'supId=' + _supId
            })
        });
        clearSelects = function () {
            formSelects.value('belongType', [])
        }
    });

    main_search();

    /**
     * 改变查询,添加按钮样式
     */
    $("#win_but_search").css("width", "95px");
    $("#win_but_search").css("vertical-align", "middle");

    $("#win_but_add").css("width", "95px");
    $("#win_but_add").css("vertical-align", "middle");
    if (_supId != undefined) {
        $('#belongTypeDiv').hide();
    } else {
        initBelongType();
    }
});

