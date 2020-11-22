var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_queryAction = 'mdMaterielInfoService.getMdmaterielFormatList';
var _all_datagrid_height;
let _wmsMiId;
var _matCode;

var _DataGrid = {
    cols: [
        {title: 'sku编号', sortable: true, name: 'mmfCode', width: 70, align: 'center'},
        {title: '规格名称', sortable: true, name: 'mmfName', width: 100, align: 'center'},
        {title: '销售价格', sortable: true, name: 'price', width: 40, align: 'center'},
        {title: '商品库存', sortable: true, name: 'quantity', width: 70, align: 'center'}
    ]
    , remoteSort: false
    , checkCol: false
    , name: 'inventoryListGrid'
    , height: _all_datagrid_height
    , nowrap: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg

}

/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl() {
    var data = 'mmfCode=' + $('#mmfNameSearch').val();
    if (_wmsMiId != undefined) {
        data += '&wmsmiId=' + _wmsMiId;
    }
    var att_url = $.supper("getServicePath", {"service":_all_queryAction, "data": data});
    return att_url;
}

/**
 * 对主表进行查询（刷新）操作
 */
function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

function searchEnter() {
    if (event.keyCode == 13)
        main_search();
}

/**
 * 页面初始化函数
 */

$(function () {
    _wmsMiId = $.supper("getParam", 'wmsMiId');
    _matCode = $.supper("getParam", 'matCode');
    console.log('接收--------',_matCode)
    $("#matCode").html(_matCode);
    _all_datagrid_height = $(window).height() - 120 ;
    _DataGrid.height = _all_datagrid_height;
    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);
    main_search();
});
