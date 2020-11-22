var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden = $("#win_form_hidden");

var _all_queryAction = "SysSalesmanService.getPageModelInfo";
var _all_count = 'SysSalesmanService.getPageModelInfoCount';
var _all_datagrid_height;

var _searchForm = {
    lineNum: 1,
    columnNum: 2,
    control: [],
    groupTag: [],
    items: [
        {title: "名称关键字", name: "salesName", type: "text", placeholder: "名称关键字"},
        {title: "状态", name: "state", type: "select", placeholder: "状态", impCode: 'PAR170926033732594'}
    ]
}
let salesmanId = null;
var _Toolbar = {
    toolBarId: "tools_but",
    items: [
        {title: "查询", id: "win_but_search", icon: "search", colourStyle: "default", rounded: true, clickE: main_search},
    ]
}


var _DataGrid = {
    cols: [
        {title: '业务员', sortable: true, name: 'salesName', width: 80, align: 'center', renderer: formatStr},
        {title: '代理商', sortable: true, name: 'agentName', width: 80, align: 'center', renderer: formatAgent},
        {title: '机构名称', sortable: true, name: 'otherName', width: 80, align: 'center', renderer: formatOther},
        {title: '状态', sortable: true, name: 'state', width: 40, align: 'center', renderer: formatState},
        {title: '发展日期', sortable: true, name: 'create_date', width: 40, align: 'center'},
    ]
    , remoteSort: false
    , name: 'salesManListGrid'
    , height: _all_datagrid_height
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
}

function formatState(val, item, rowIndex) {
    let str = '';
    str += '<span>';
    if (item.state == 1) {
        str += '正常';
    } else {
        str += '禁用';
    }
    str += '</span>';
    return str;
}
function formatTime(val, item, rowIndex){
    let str = '';
    str += '<span>';
    if (item.create_date != undefined && item.create_date != null){
        Date.format(new Date(item.create_date.toString().replace(/-/g,"/")), "yyyy-MM-dd HH:mm:ss");
    }
    str += '</span>';
    return str;
}
function formatStr(val, item, rowIndex) {
    let str = '';
    str += '<span>' + (salesName == undefined ? '-' : salesName) + '</span>';
    return str;
}

function formatAgent(val, item, rowIndex) {
    let str = '';
    if(agentCompany != undefined && agentCompany != '') {
        str += '<span>' + agentCompany + '</span>';
    }else {
        str += '<span>' + (item.agent_company == undefined ? '-' : item.agent_company) + '</span>';
    }
    return str;
}

function formatOther(val, item, rowIndex) {
    let str = '';
    let rbaName = item.rba_name == undefined ? '' : item.rba_name;
    let rbsName = item.rbs_name == undefined ? '' : item.rbs_name;
    let rbbName = item.rbb_name == undefined ? '' : item.rbb_name;
    if (rbaName == '' && rbsName == '' && rbbName == '') {
        str = '<span>-</span>';
        return str;
    }
    str += '<span>';
    if (rbaName != ''){
        str += rbaName + '-';
    }
    if (rbsName != '') {
        str += rbsName + '-';
    }
    if (rbbName != '') {
        str += rbbName + '-';
    }
    str = str.substring(0, str.length - 1);
    str += '</span>';

    return str;
}

let salesName = '-';
let agentCompany = '';
$(function () {
    let salesman = $.supper("getProductArray", "salesmanId");
    if (salesman != undefined && salesman != null) {
        let name = salesman.name;
        $('#salesName').text(name);
        salesName = name;
        agentCompany = salesman.agentCompany;
    }

    _all_win_searchForm.xform('createForm', _searchForm);

    _all_div_hidden.xform('createhidden', _searchForm.hiddenitems);

    _all_win_tools_but.xtoolbar('create', _Toolbar);

    _all_datagrid_height = $(window).height() - _all_win_searchForm.height() - 64 - 95;

    _DataGrid.height = _all_datagrid_height;

    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);

    main_search();

    /**
     * 改变查询,添加按钮样式
     */
    $("#win_but_search").css("width", "95px");
    $("#win_but_search").css("vertical-align", "middle");
    intiCount();
});

function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

function getMMGridUrl() {
    let salesman = $.supper("getProductArray", "salesmanId");
    if (salesmanId == null && salesman != undefined && salesman != null) {
        salesmanId = salesman.id;
    }
    if (salesmanId == null) {
        return;
    }
    var data = _all_win_searchForm.serialize();
    data += '&salesmanId=' + salesmanId;
    var att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
    return att_url;
}

function intiCount() {
    if (salesmanId == null) {
        return;
    }
    let data = 'salesmanId=' + salesmanId;
    $.supper("doservice", {
        "service": _all_count, "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $('#agentCount').text(jsondata.obj.agentCount);
                $('#otherCount').text(jsondata.obj.otherCount);
            }
        }
    });
}
//管理员登陆导出查看关联下的导出全部
//20200428新表格已经完成
function main_export(){
    var vdata='salesmanId=' + salesmanId;
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"ExportExcelService.exportSalesManAdmin","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}