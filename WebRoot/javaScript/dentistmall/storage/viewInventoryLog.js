var _all_queryAction = "mdInventoryService.getMdInventoryEnOutLogMxListByWiId";
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_datagrid_height;
var _all_win_datagrid;
let _wmsMiId;
let _wiId;

var _searchForm={
    lineNum:1,
    columnNum:3,
    control:[],
    groupTag:[],
    hiddenitems:[
    ],
    items:[
        {title:"时间",name:"createDate1",type:"text", placeholder:"输入之后回车查询"},
        {title:"类型",name:"stype",type:"select",placeholder:"选择类型", data: [{id: 1, name: '入库'}, {id: 2, name: '出库'}]},
        {title:"操作人",name:"createRen",type:"text",placeholder:"输入之后回车查询"}
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
        {title: '时间', name: 'createDate1', width: 100, align: 'center'},
        {title: '类型', name: 'stype', width: 80, align: 'center', renderer: rendererType},
        {title: '规格', name: 'mmfName', width: 70, align: 'center'},
        {title: '数量', name: 'number', width: 100, align: 'center'},
        {title: '单价', name: 'price', width: 80, align: 'center'},
        {title: '品牌', name: 'brand', width: 80, align: 'center'},
        {title: '零售价', name: 'retailPrice', width: 70, align: 'center'},
        {title: '有效期', name: 'validDate', width: 100, align: 'center'},
        {title: '注册号', name: 'backPrinting', width: 80, align: 'center'},
        {title: '批号', name: 'batchCode', width: 80, align: 'center'},
        {title: '总金额', name: 'allPrice', width: 70, align: 'center', renderer: renderAllPrice},
        {title: '出入库单号', name: 'cCode', width: 70, align: 'center'},
        {title: '操作人', name: 'createRen', width: 80, align: 'center'},
    ]
    , remoteSort: false
    , height: _all_datagrid_height
    , gridtype: '2'
    , nowrap: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
}

function rendererType(val, item, rowIndex) {
    console.log(item.stype)
    if (item.stype == 1)
        return '入库';
    else if (item.stype == 2)
        return '出库';
}

function renderAllPrice(val, item, rowIndex) {
    let str = '';
    let price = item.price == undefined ? 0 : item.price;
    let number = item.number == undefined ? 0 : item.number;
    str = price * number;
    return str;
}

function getMMGridUrl() {
    // var data = _all_win_searchForm.serialize();
    let data = '';
    if ($('#createDate1').val() != '')
        data += '&createDate1=' + $('#createDate1').val();
    if ($('#stype').val() != '')
        data += '&stype=' + $('#stype').val();
    if ($('#createRen').val() != '')
        data += '&createRen=' + $('#createRen').val();

    // if (_wmsMiId != undefined) {
    //     data += 'wmsMiId=' + _wmsMiId;
    // }
    if (_wiId != undefined)
        data += '&wiId=' + _wiId;
    var att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
    return att_url;
}

function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

$(function () {
    _wmsMiId = $.supper("getParam", "wmsMiId");
    _wiId = $.supper("getParam", "wiId");
    _all_datagrid_height=   $(window).height()-104-95;

    _DataGrid.height = _all_datagrid_height;

    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);

    main_search();

    if (_wiId != undefined) {
        $.supper("doservice", {
            "service": 'MdInventoryService.findFormObject', "data": 'wiId=' + _wiId, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    let val = jsondata.obj;
                    $("#ajaxLoad span").each(function (i, v) {
                        var att_name = $(v).attr("id");
                        eval("var vals=val." + att_name + ";");
                        if (vals != undefined) {
                            $(v).text(vals);
                        }
                        if (att_name == 'baseNumber') {
                            const str = val.quantity + val.basicUnit + '/' + (val.baseNumber == undefined ? '' : val.baseNumber) + (val.unit == undefined ? '' : val.unit);
                            $(v).text(str);
                        }
                    });
                }
            }
        });
    }

    laydate({
        elem: '#createDate1',
        format: 'YYYY-MM-DD' //日期格式
    });
})

function exportExcel() {
    var wiId= parseInt(_wiId);
    var vdata='&wiId='+wiId;
    var matCode=$("#matCode").text();
    if (matCode!=null&&matCode!=undefined){
        vdata+='&matCode='+matCode;
    }
    var matName=$("#matName").text();
    if (matName!=null&&matName!=undefined){
        vdata+='&matName='+matName;
    }
    var quantity=$("#quantity").text();
    if (quantity!=null&&quantity!=undefined){
        vdata+='&quantity='+quantity;
    }
    var newTab=window.open('about:blank');
//重新提交
    $.supper("doservice", {"service":"EnterWarehouseExportService.exportWzidExcel","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}
