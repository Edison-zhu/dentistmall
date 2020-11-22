var _all_queryAction = "MdMaterielInfoService.getPagerViewLogObject";
// var _all_win_searchForm = $("#win_form_search");
// var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
// var _all_div_hidden=$("#win_form_hidden");
var _all_datagrid_height;
var _all_win_datagrid;
let _wmsMiId;

// var _DataGrid = {
//     cols: [
//         {title: '操作时间', sortable: true, name: 'createDate', width: 100, align: 'center'},
//         {
//             title: '操作人',
//             sortable: true,
//             name: 'createRen',
//             width: 80,
//             align: 'center',
//             sortable: true,
//         },
//         {title: '操作', sortable: true, name: 'logName', width: 80, align: 'center'},
//         {title: '操作明细', sortable: true, name: 'log', width: 70, align: 'center'},
//     ]
//     , remoteSort: false
//     , height: _all_datagrid_height
//     , gridtype: '2'
//     , nowrap: true
//     , url: getMMGridUrl()
//     , mmPaginatorOpt: _all_win_datagrid_pg
// }
var _searchForm={
    lineNum:1,
    columnNum:3,
    control:[],
    groupTag:[],
    hiddenitems:[
    ],
    items:[
        {title:"商品名称",name:"matName",type:"text", placeholder:"输入之后回车查询"},
        {title:"规格",name:"mmfName",type:"text",placeholder:"输入之后回车查询"},
        {title:"供应商",name:"applicantName",type:"text",placeholder:"输入之后回车查询"}
    ]
};

var _Toolbar={
    toolBarId:"tools_but",
    items:[
        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search}
    ]
};

var _DataGrid = {
    cols: [
        {title: '操作时间', sortable: true, name: 'createDate', width: 100, align: 'center'},
        {title: '操作人', sortable: true, name: 'createRen', width: 80, align: 'center', sortable: true},
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
    // var data = _all_win_searchForm.serialize();
    let data = '';
    if (_wmsMiId != undefined) {
        data += 'wmsMiId=' + _wmsMiId;
    }
    var att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
    return att_url;
}

function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

$(function () {
    // _all_win_searchForm.xform('createForm',_searchForm);
    //
    // _all_div_hidden.xform('createhidden',_searchForm.hiddenitems);
    //
    // _all_win_tools_but.xtoolbar('create',_Toolbar);
    _wmsMiId = $.supper("getParam", "wmsMiId");
    _all_datagrid_height=   $(window).height()-64-95;

    _DataGrid.height = _all_datagrid_height;

    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);

    main_search();
})
