var _all_queryAction = "mdMaterielInfoService.getMdMaterielInfoLog";
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_datagrid_height;
var _all_win_datagrid;
let _wmsMiId;

var _DataGrid = {
    cols: [
        {title: '时间', sortable: true, name: 'createDate', width: 100, align: 'center'},
        {title: '操作人员', sortable: true, name: 'createRen', width: 80, align: 'center', sortable: true},
        {title: '操作', sortable: true, name: 'logName', width: 80, align: 'center'},
        {title: '操作详情', sortable: true, name: 'log', width: 70, align: 'center'},
    ]
    , remoteSort: false
    , height: _all_datagrid_height
    , gridtype: '2'
    , nowrap: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
}

function getMMGridUrl() {
    let data = '';
    if (_wmsMiId != undefined) {
        data += 'wmsmiId=' + _wmsMiId;
    }
    var att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
    return att_url;
}

function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

$(function () {
    _wmsMiId = $.supper("getParam", "wmsMiId");
    // _all_datagrid_height=   $(window).height()-64-95;
    _all_datagrid_height = 400;
    _DataGrid.height = _all_datagrid_height;
    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);
    main_search();
})
