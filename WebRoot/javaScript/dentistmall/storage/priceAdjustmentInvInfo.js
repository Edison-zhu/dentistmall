var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid;
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");

var _all_queryAction = 'mdInventoryService.getPagerModelPAWithSave';
var _all_QueryEditAction = 'mdInventoryService.getPagerModelPAEx';
var _all_saveAction = 'mdInventoryService.saveMdInventoryPA';
var _all_updateAction = 'mdInventoryService.updateMdInventoryPA';
var _all_deleteExAvtion = 'mdInventoryService.deleteMdInventoryPaEx';

var _queryAction_WithSave = "mdMaterielInfoService.getMyPagerModelObjectWithString";
var _all_findPAAction = 'mdInventoryService.findPriceAjustmentFormObject';
var queryAction = "mdMaterielInfoService.getMyPagerModelObject";
var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
var _all_datagrid_height;

var _wmsMiIds = [];
var _mmfIds = [];

var _paId; //查看或者修改
var _edit; //修改

var isLoading = false; // 物料入库是否正在查询中，false为未加载
var lastSearchName = ''; // 当isLoading为true时，记录最近一次输入的值进行查询
var canHideTable = true;
var timeOut;

//2020年07月09日16:38:01添加
let _mdpId;
let _mdpsId;
var queryAction = "mdMaterielInfoService.getMyPagerModelObject";
//2020年07月09日16:32:40朱燕冰修改新增表格
var initDataGrid = function () {
    var cols = [
        {title: '物料编号', name: 'mmfCode', width: 150, align: 'center'},
        {title: '物料名称', name: 'matName', width: 170, align: 'center'},
        {title: '物料规格', name: 'mmfName', width: 100, align: 'center'},
        {title: '品牌', name: 'brand', width: 80, align: 'center'},
        {title: '生产厂家', name: 'productFactory', width: 80, align: 'center'},
        {title: '单位', name: 'basicUnit', width: 80, align: 'center'},
        {title: '采购均价', name: 'avgPrice', width: 80, align: 'center'},
        {title: '零售价', name: 'retailPrice', width: 80, align: 'center',},
        {title: '操作', name: 'con', width: 100, align: 'center', renderer: controls}
    ];

    var att_mmgurl = getUrl();
    mmg = $('#datagrid1').mmGrid({
        height: 'auto'
        , cols: cols
        , method: 'get'
        , nowrap: true
        , remoteSort: true
        , url: att_mmgurl
        , sortName: 'SECUCODE'
        , sortStatus: 'asc'
        , multiSelect: true
        , checkCol: false
        , fullWidthRows: true
        , showBackboard: false
        , autoLoad: false
        , plugins: [
            $('#pg').mmPaginator({})
        ]
    });
    mmg.load();
    mmg.on('loadSuccess', nextSearch);
}

// 存在新的搜索词，加载完上次的数据后，搜索最近一次的输入值
function nextSearch() {
    if (lastSearchName == '') {
        isLoading = false;
    }
    if (isLoading == false)
        return;
    lastSearchName = '';
    search();
}
function getUrl(exclude) {
    var data = 'searchName=' + (lastSearchName == '' ? $("#searchName1").val() : lastSearchName);
    // if (exclude != undefined)
    //     data = 'searchName=' + $('#searchName').val();
    let att_mmgurl = $.supper("getServicePath", {"service":queryAction, "data":data});
    // let att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
        if (_mdpId != undefined) {
            att_mmgurl += '&mdpId=' + _mdpId;
        }
        if (_mdpsId != undefined) {
            att_mmgurl += '&mdpsId=' + _mdpsId;
        }
    // if ($('#searchName').val() != undefined && $('#searchName').val() != null && $('#searchName').val() != '') {
    //     att_mmgurl += '&searchName=' + $('#searchName').val();
    // }
    return att_mmgurl;
}
function entersearch(value){
    if (value == '') {
        $("#tables").hide();
    }else {
        canHideTable = false;
        $("#tables").show();
    }
    var event = window.event || arguments.callee.caller.arguments[0];
    if (event.keyCode == 13)
    {
        search();
    }
}

function showTable(value) {
    if (value == '') {
        $("#tables").hide();
    }else {
        // canHideTable = false;
        $("#tables").show();
    }
}

function search(exclude) {
    var att_mmgurl = getUrl(exclude);
    mmg.opts.url = att_mmgurl;
    mmg.load();

}
// function enterSearch() {
//     if (event.keyCode == 13)
//         search(true);
// }
function controls(val, item, rowIndex) {
    return "<a onclick=\"selectRow(" + item.wmsMiId + ", " + item.mmfId + ")\" style='display:block;width: 70px;height: 24px;border-radius: 12px;background: #23c6c8;text-align: center;line-height: 24px;color: #fff;margin: 0 auto;font-size: 12px'>选择</a>";
}


var treeClickLevel = 0;
var loadItemZtree = function () {
    let data = '';
    if (_mdpId != undefined) {
        data += 'mdpId=' + _mdpId;
    }
    if (_mdpsId != undefined) {
        data += '&mdpsId=' + _mdpsId;
    }
    //设置树形数据开始
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                tags: "tags",
                isParent: "isParent"
            }
        },
        async: {
            enable: true,
            url: $.supper("getServicePath", {
                "service": _all_SiteFirst,
                "data": data,
                autoParam: ["id"],
            }),  //获取异步数据的地址
            autoParam: ["id"],
            dataFilter: filter //设置数据的展现形式
        },
        callback: {//增加点击事件
            beforeClick: function (treeId, treeNode) {
                lastExpandNode = treeNode;//记录当前点击的节点
                treeClickLevel = treeNode.level;
                _mdpId = undefined;
                _mdpsId = undefined;
                if (treeNode.level == 1) {
                    _mdpId = treeNode.id;
                    _mdpIdname = treeNode.name;
                    _mdpsId = undefined;
                } else if(treeNode.level == 2){
                    _mdpsId = treeNode.id;
                    _mdpsIdname = treeNode.name;
                } else {
                    _mdpId = undefined;
                    _mdpsId = undefined;
                    _mdpIdname = '';
                    _mdpsIdname = '';
                }
                search();
            }
        }
    }
    //设置树的初始数据
    var zNodes = [
        {id: 0, pId: "", name: "全部", isParent: true}
    ];
    $.fn.zTree.init($("#tree"), setting, zNodes);
    //自动展现第一层树
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var node = zTree.getNodesByParam("id","0");
    lastExpandNode=node;
    zTree.expandNode(node[0],  true, false, false);
}

function initTree() {
    let data = '';
    if (_mdpId != undefined) {
        data += 'mdpId=' + _mdpId;
    }
    if (_mdpsId != undefined) {
        data += '&mdpsId=' + _mdpsId;
    }
    //设置树的初始数据
    $.supper("doservice", {
        "service": _all_SiteFirst, 'data': data, "BackE": function (jsondata) {
            $.fn.zTree.init($("#roleTree"), rolSetting, jsondata);
            //设置树形数据结束
            $("#roleTree").css("height", $(window).height() - 120);
        }
    });
}

//设置数据的展现形式
function filter(treeId, parentNode, childNodes) {
    if (!childNodes)
        return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        if (childNodes[i].name !== undefined)
            childNodes[i].name = childNodes[i].name.replace('', '');
    }
    return childNodes;
}


//--------------------------------tree---------------------------

//当增加树的数据后刷新当前节点
function loadAddTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null)//刷新当前节点
        zTree.reAsyncChildNodes(lastExpandNode, "refresh");
    search();
}

//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null) {
        var zTree = $.fn.zTree.getZTreeObj("tree");
        if (lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
            zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");
    }
    search();
}

//--------------------------------tree---------------------------
//=============================>
var _Toolbar = {
    toolBarId: "tools_but",
    items: [
        {
            title: "保存",
            id: "win_but_search",
            icon: "save",
            colourStyle: "primary",
            rounded: true,
            clickE: saveNewCheck
        },
        {title: "返回", id: "win_but_search", icon: "search", colourStyle: "default", rounded: true, clickE: backTo},
    ]
};

var _saveObject = {};
var _paieIds = {};

var _DataGrid = {
    cols: [
        {title: '物料编码', name: 'mmfCode', width: 100, align: 'center', renderer: renderCode},
        {title: '商品名称', name: 'matName', width: 80, align: 'center'},
        {title: '品牌', name: 'brand', width: 70, align: 'center'},
        {title: '规格型号/编码', name: 'mmfName', width: 70, align: 'center', renderer: renderNorm},
        {title: '调价单位', name: 'baseUnit', width: 70, align: 'center', renderer: renderUnit},
        {title: '采购均价', name: 'avgPrice1', width: 70, align: 'center'},
        {title: '原零售价', name: 'origionRetailPrice', width: 70, align: 'center', renderer: renderBasePrice},
        {title: '现零售价', name: 'retailPrice', width: 70, align: 'center', renderer: renderRetailPrice},
        {title: '差价', name: 'minusPrice', width: 70, align: 'center', renderer: renderMiusPrice},
        {title: '调价比例', name: 'paiePercent', width: 70, align: 'center', renderer: renderPercent},
        {title: '批号', name: 'batchCode', width: 70, align: 'center'},
        {title: '注册号/备案号', name: 'backPrinting', width: 70, align: 'center'},
        {title: '库存数量', name: 'baseNumber', width: 70, align: 'center'},
        {title: '操作', name: 'control', width: 70, align: 'center', renderer: control},
    ]
    , remoteSort: false
    , height: _all_datagrid_height
    , gridtype: '2'
    , checkCol: true
    , nowrap: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
}

function renderCode(val, item, rowIndex) {
    let str = item.matCode;

    // if (_saveObject[item.mmfId] == undefined) {
    //     _saveObject[item.mmfId] = {};
        // _saveObject[item.mmfId].mmfId = item.mmfId == undefined ? '' : item.mmfId;
        // _saveObject[item.mmfId].wmsMiId = item.wmsMiId == undefined ? '' : item.wmsMiId;
        // _saveObject[item.mmfId].basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
        // _saveObject[item.mmfId].basePrice = item.price == undefined ? -1 : item.price;
        // _saveObject[item.mmfId].retailPrice = item.retailPrice == undefined ? -1 : item.retailPrice;
        // _saveObject[item.mmfId].paieId = item.paieId == undefined ? '' : item.paieId;
        // _saveObject[item.mmfId].avgPrice = item.avgPrice == undefined ? '' : item.avgPrice;
    // }
    if (item.paieId != undefined) {
        if (_paieIds[item.mmfId] == undefined)
            _paieIds[item.mmfId] = {};
        _paieIds[item.mmfId].paieId = item.paieId;
    }

    return str;
}

function renderNorm(val, item, rowIndex) {
    let str = '';
    str = item.mmfName + '/' + item.mmfCode;
    return str;
}

function renderUnit(val, item, rowIndex) {
    let str = '';
    // str += _saveObject[item.mmfId].basicUnit != '' ? _saveObject[item.mmfId].basicUnit : item.basicUnit;
    str += (item.basicUnit == undefined || item.basicUnit == 'undefined') ? '' : item.basicUnit;
    return str;
}

function renderBasePrice(val, item, rowIndex) {
    let str = '';
    let price = item.price == undefined ? '' : item.price;
    let readonly = '';
    if (_paId != undefined && _edit == undefined) {
        readonly = 'readonly';
    }
    // str += '<input ' + readonly + ' id="basePrice' + item.mmfId + '" data-mmfId="' + item.mmfId + '" name="basePrice" style="text-align: center; width: 50px;" value="' + (_saveObject[item.mmfId].basePrice != -1 ? toDecimal2(_saveObject[item.mmfId].basePrice) : toDecimal2(price)) + '"';
    str += '<input ' + readonly + ' id="basePrice' + item.mmfId + '" data-mmfId="' + item.mmfId + '" name="basePrice" style="text-align: center; width: 50px;" value="' + toDecimal2(price) + '"' +
        ' onblur="logPrice(' + item.mmfId + ', ' + item.wmsMiId + ')"';
    if (_paId != undefined && _edit == undefined) {
        str += ' rendonly ';
    }
    str += ' />';
    return str;
}

function renderRetailPrice(val, item, rowIndex) {
    let str = '';
    let readonly = '';
    if (_paId != undefined && _edit == undefined) {
        readonly = 'readonly';
    }
    let per = $('#paiPercentForm').val();
    let retailPrice = (item.retailPrice == undefined ? item.price : item.retailPrice);
    let price = item.price == undefined ? '' : item.price;
    if (per != '' && price != '') {
        retailPrice = price + price * per / 100;
    }
    str += '<input ' + readonly + ' id="retailPrice' + item.mmfId + '" data-mmfId="' + item.mmfId + '" name="retailPrice" ' + //onkeyup="$(this).val($(this).val().replace(/(-)?[^0-9.]/g,\'\'));"' +
        // ' onblur="logRetailPrice(' + item.mmfId + ')" style="text-align: center; width: 50px;" value="' + (_saveObject[item.mmfId].retailPrice != -1 ? toDecimal2(_saveObject[item.mmfId].retailPrice) : toDecimal2(retailPrice)) + '"';
        ' onblur="logRetailPrice(' + item.mmfId + ', ' + item.wmsMiId + ')" style="text-align: center; width: 50px;" value="' + toDecimal2(retailPrice) + '"';
    if (_paId != undefined && _edit == undefined) {
        str += ' rendonly ';
    }
    str += ' />';
    return str;
}

function renderMiusPrice(val, item, rowIndex) {
    let str = '';
    // let basePrice = (_saveObject[item.mmfId].basePrice != -1 ? _saveObject[item.mmfId].basePrice : item.price);
    // let retailPrice = (_saveObject[item.mmfId].retailPrice != -1 ? _saveObject[item.mmfId].retailPrice : item.retailPrice);
    let per = $('#paiPercentForm').val();
    if (item.percent != undefined)
        per = item.percent;
    let basePrice = item.price == undefined ? 0 : item.price;
    let retailPrice = item.retailPrice == undefined ? item.price : item.retailPrice;
    if (per != '') {
        retailPrice = basePrice + basePrice * per / 100;
    }
    let minusPrice =  retailPrice - basePrice;
    let min = 0;
    if (CheckUtil.isInteger(minusPrice) == false && CheckUtil.isFloat(minusPrice) == false) {
        min = '';
    } else {
        min = minusPrice;
    }
    str += '<span id="minus' + item.mmfId + '" data-mmfId="' + item.mmfId + '">' + toDecimal2(min) + '</span>';
    return str;
}

var allPer = 0;
function renderPercent(val, item, rowIndex) {
    let str = '';
    let readonly = '';
    if (_paId != undefined && _edit == undefined) {
        readonly = 'readonly';
    }
    // let basePrice = (_saveObject[item.mmfId].basePrice != -1 ? _saveObject[item.mmfId].basePrice : item.price);
    // let retailPrice = (_saveObject[item.mmfId].retailPrice != -1 ? _saveObject[item.mmfId].retailPrice : item.retailPrice);
    let basePrice = item.price == undefined ? 0 : item.price;
    let retailPrice = item.retailPrice == undefined ? item.price : item.retailPrice;
    let minusPrice = retailPrice - basePrice;
    let per = 0;
    if (CheckUtil.isInteger(basePrice) || CheckUtil.isFloat(basePrice) || basePrice != '0'){
        per = minusPrice / basePrice;
    } else {
        per = 0;
    }
    if ($('#paiPercentForm').val() != '' && item.percent == undefined) {
        per = $('#paiPercentForm').val();
    } else
        per = item.percent;
    allPer = toDecimal2(Number(allPer) + Number(toDecimal2(per)));
    str += '<input ' + readonly + ' id="percent' + item.mmfId + '" data-wmsmiid="' + item.wmsMiId + '" data-mmfId="' + item.mmfId + '"'+// onkeyup="$(this).val($(this).val().replace(/[^0-9.]/g,\'\'));"' +
        ' onfocus="logOldPer(' + item.mmfId + ')" onblur="changeSinglePer(' + item.mmfId + ', ' + item.wmsMiId + ')" style="text-align: center; width: 50px;" value="' + toDecimal2(per) + '" />%';
    return str;
}

function control(val, item, rowIndex) {
    let str = '';
    str += "<a onclick=\"deleteEx('" + item.paieId + "'," + rowIndex + ", " + item.mmfId + ", " + item.wmsMiId+ ")\"  class='btn btn-success  btn-xs'>删除</a>&nbsp;&nbsp;";
    return str;
}
var _oldPer = 0;
function logOldPer(mmfId) {
    _oldPer = Number($('#percent' + mmfId).val());
}
function changeSinglePer(mmfId, wmsMiId) {
    if (_paId != undefined && _edit == undefined) {
        return;
    }
    let per = Number($('#percent' + mmfId).val());
    if((CheckUtil.isInteger(per) === false && CheckUtil.isFloat(per) === false) || per <= 0){
        per = 0;
    }
    let basePrice = Number($('#basePrice' + mmfId).val());
    $('#retailPrice' + mmfId).val(toDecimal2(basePrice + basePrice * per / 100));
    $('#minus' + mmfId).text( toDecimal2(basePrice * per / 100));
    if (_saveObject[mmfId] == undefined) {
        _saveObject[mmfId] = {};
        _saveObject[mmfId].mmfId = mmfId;
        _saveObject[mmfId].wmsMiId = wmsMiId;
    }
    _saveObject[mmfId].basePrice = basePrice;
    _saveObject[mmfId].retailPrice = basePrice + basePrice * per / 100

    allPer = toDecimal2(allPer - _oldPer + per);
    $('#totalPricePercent').text(toDecimal2(allPer / ($("#mxMatListMx tr").length <= 0 ? 1 : $("#mxMatListMx tr").length)));

    // _saveObject[mmfId].basePrice = basePrice;
    // _saveObject[mmfId].retailPrice = retailPrice;
}

function logPrice(mmfId, wmsMiId) {
    if (_paId != undefined && _edit == undefined) {
        return;
    }
    let mon = Number($('#basePrice' + mmfId).val());
    if((CheckUtil.isPlusInteger(mon) === false && CheckUtil.isPlusFloat(mon) === false) || mon <= 0){
        mon = 0;
        $('#basePrice' + mmfId).val(mon);
    }
    if (_saveObject[mmfId] == undefined) {
        _saveObject[mmfId] = {};
        _saveObject[mmfId].mmfId = mmfId;
        _saveObject[mmfId].wmsMiId = wmsMiId;
    }
    _saveObject[mmfId].retailPrice = mon;
    let retailPrice = Number($('#retailPrice' + mmfId).val());
    let basePrice = mon;
    let minusPrice = retailPrice - basePrice;
    let per = 0;
    if (CheckUtil.isInteger(basePrice) || CheckUtil.isFloat(basePrice) || basePrice != '0'){
        per = toDecimal2(minusPrice / basePrice);
    } else {
        per = 0;
    }
    let oldPer = $('#percent' + mmfId).val();
    $('#percent' + mmfId).val(isNaN(per * 100) == true ? '--' : per * 100);
    $('#minus' + mmfId).text(toDecimal2(minusPrice));

    allPer = allPer - oldPer + per * 100;
    $('#totalPricePercent').text(toDecimal2(allPer / ($("#mxMatListMx tr").length <= 0 ? 1 : $("#mxMatListMx tr").length)));

    _saveObject[mmfId].basePrice = basePrice;
    _saveObject[mmfId].retailPrice = retailPrice;
}

function logRetailPrice(mmfId, wmsMiId) {
    if (_paId != undefined && _edit == undefined) {
        return;
    }
    let mon = Number($('#retailPrice' + mmfId).val());
    if((CheckUtil.isPlusInteger(mon) === false && CheckUtil.isPlusFloat(mon) === false) || mon <= 0){
        mon = 0;
        $('#retailPrice' + mmfId).val(mon);
    }
    if (_saveObject[mmfId] == undefined) {
        _saveObject[mmfId] = {};
        _saveObject[mmfId].mmfId = mmfId;
        _saveObject[mmfId].wmsMiId = wmsMiId;
    }
    _saveObject[mmfId].retailPrice = mon;
    let basePrice = Number($('#basePrice' + mmfId).val());
    let retailPrice = mon;
    let minusPrice = retailPrice - basePrice;
    let per = 0;
    if (CheckUtil.isInteger(basePrice) || CheckUtil.isFloat(basePrice) || basePrice != '0'){
        per = toDecimal2(minusPrice / basePrice);
    } else {
        per = 0;
    }
    let oldPer = $('#percent' + mmfId).val();
    $('#percent' + mmfId).val(isNaN(per * 100) == true ? '--' : per * 100);
    $('#minus' + mmfId).text(toDecimal2(minusPrice));

    allPer = allPer - oldPer + per * 100;
    $('#totalPricePercent').text(toDecimal2(allPer / ($("#mxMatListMx tr").length <= 0 ? 1 : $("#mxMatListMx tr").length)));

    _saveObject[mmfId].basePrice = basePrice;
    _saveObject[mmfId].retailPrice = retailPrice;
}

function changePercent(percent) {
    if (_paId != undefined && _edit == undefined) {
        return;
    }
    let per = Number(percent);
    let mmfId;
    let wmsMiId;
    let p;
    let minus;
    let retail;
    let price;
    $('input[id^=percent]').each(function () {
        mmfId = $(this).data('mmfid');
        wmsMiId = $(this).data('wmsmiid');
        // p = Number($(this).val());
        price = Number($('#basePrice' + mmfId).val());
        $(this).val(toDecimal2(per));
        $('#retailPrice' + mmfId).val(toDecimal2(price + price * per / 100));
        $('#minus' + mmfId).text(toDecimal2(price * per / 100));
        if (_saveObject[mmfId] == undefined) {
            _saveObject[mmfId] = {};
            _saveObject[mmfId].mmfId = mmfId;
            _saveObject[mmfId].wmsMiId = wmsMiId;
        }
        _saveObject[mmfId].basePrice = price;
        _saveObject[mmfId].retailPrice = price + price * per / 100
    });
    allPer = toDecimal2(per) * ($("#mxMatListMx tr").length < 0 ? 0 : $("#mxMatListMx tr").length);
    $('#totalPricePercent').text(toDecimal2(allPer / ($("#mxMatListMx tr").length <= 0 ? 1 : $("#mxMatListMx tr").length)));
}

function deleteEx(paieId, rowIndex, mmfId, wmsMiId) {
    if (_paId != undefined) {
        $.supper("confirm", {title: "删除操作", msg: "确认删除？", yesE: function () {
                $.supper("doservice", {
                    "service": _all_deleteExAvtion,
                    "data": 'paieIds=' + paieId,
                    "BackE": function (jsondata) {
                        if (jsondata.code == "1") {
                            if (_wmsMiIds.indexOf(wmsMiId) >= 0)
                                _wmsMiIds.splice(_wmsMiIds.indexOf(wmsMiId), 1);
                            if (_mmfIds.indexOf(mmfId) >= 0)
                                _mmfIds.splice(_mmfIds.indexOf(mmfId), 1)
                            _saveObject[mmfId] = undefined;
                            searchMatMx();
                            // main_search()
                            // $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                        } else{
                            // $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                        }
                    }
                });
            }
        });
    } else {
        if (_wmsMiIds.indexOf(wmsMiId) >= 0)
            _wmsMiIds.splice(_wmsMiIds.indexOf(wmsMiId), 1);
        if (_mmfIds.indexOf(mmfId) >= 0) {
            _mmfIds.splice(_mmfIds.indexOf(mmfId), 1)
        }
        console.log(_wmsMiIds);
        console.log(_mmfIds)
        _saveObject[mmfId] = undefined;
        searchMatMx();
        // _all_win_datagrid.removeRow(rowIndex);
        // main_search();
    }
}

function backTo() {
    var view_url = 'jsp/dentistmall/storage/priceAdjustmentInv.jsp';
    $.supper("showTtemWin", {"url": view_url, "title": '调价管理'});
    $.supper("setProductArray", {"name": "priceAdjustment", "value": null });
    setTimeout(function () {
        if (url != undefined) {
            $.supper("closeTtemWin", {url: url});
        }
    }, 200);
}

function saveNewCheck() {
    if (_edit != undefined) {
        if (_paId == undefined) {
            $.supper("alert", {title: "操作提示", msg: "数据有误，请重新进入此页面！"});
            return;
        }
    }
    if ($('#paiCodeForm').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "调价号必填！"});
        return;
    }
    if ($('#createRenForm').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "制作人必填！"});
        return;
    }
    if ($('#paiTypeForm').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "调价类型必填！"});
        return;
    }
    // if ($('#paiPercentForm').val() == '') {
    //     $.supper("alert", {title: "操作提示", msg: "调价比例必填！"});
    //     return;
    // }

    if (_saveObject.length <= 0 || JSON.stringify(_saveObject) == '{}' || $("#mxMatListMx tr").length <= 0) {
        $.supper("alert", {title: "操作提示", msg: "没有调价内容！"});
        return;
    }

    let data = $('#saveForm').serialize();
    if (_paId != undefined && _edit != undefined)
        data += '&paiId=' + _paId;
    let mmfId = '';
    let wmsMiId = '';
    let unit = '';
    let basePrice = '';
    let retailPrice = '';
    let paieId = '';
    let updateNew = false;
    let service = _all_saveAction;
    if (_paId != undefined && _edit != undefined) {
        for (let idx in _saveObject) {
            if (_saveObject[idx] == undefined)
                continue;

            if (CheckUtil.isDigit(_saveObject[idx].basePrice) == false || CheckUtil.isDigit(_saveObject[idx].retailPrice) == false) {
                $.supper("alert", {title: "操作提示", msg: "价格有错误，请检查！"});
                return;
            }

            mmfId += _saveObject[idx].mmfId + ',';
            if (_mmfIds.length > 0)
                mmfId += _mmfIds.join(',') + ',';
            wmsMiId += _saveObject[idx].wmsMiId + ',';
            if (_wmsMiIds.length > 0)
                wmsMiId += _wmsMiIds.join(',') + ',';
            // unit += _saveObject[idx].basicUnit + ',';
            basePrice += _saveObject[idx].basePrice + ',';
            retailPrice += _saveObject[idx].retailPrice + ',';
            // paieId += _saveObject[idx].paieId + ',';
            if (_paieIds[_saveObject[idx].mmfId] == undefined || _paieIds[_saveObject[idx].mmfId].paieId == undefined)
                paieId += ",";
            else
                paieId += _paieIds[_saveObject[idx].mmfId].paieId + ',';
        }
        if (_mmfIds.length > 0)
            updateNew = true;
        service = _all_updateAction;
        if (paieId != '') {
            paieId = paieId.substring(0, paieId.length - 1);
            data += '&paieIds=' + paieId;
        }
    } else {
        let includeWmsMiId = '';
        let includeMmfId = '';
        let idx = 0;
        let mmfIdtemp;
        for (let inx in _mmfIds) {
            mmfIdtemp =  _mmfIds[inx];
            if (_saveObject[mmfIdtemp] == undefined){
                includeMmfId += mmfIdtemp + ',';
                includeWmsMiId += _wmsMiIds[idx] + ',';
                continue;
            }

            if (CheckUtil.isDigit(_saveObject[mmfIdtemp].basePrice) == false || CheckUtil.isDigit(_saveObject[mmfIdtemp].retailPrice) == false) {
                $.supper("alert", {title: "操作提示", msg: "价格有错误，请检查！"});
                return;
            }

            mmfId += _saveObject[mmfIdtemp].mmfId + ',';
            wmsMiId += _saveObject[mmfIdtemp].wmsMiId + ',';
            unit += _saveObject[mmfIdtemp].baseUnit + ',';
            basePrice += _saveObject[mmfIdtemp].basePrice + ',';
            retailPrice += _saveObject[mmfIdtemp].retailPrice + ',';

            idx ++;
        }
        if (includeWmsMiId != '')
            includeWmsMiId = includeWmsMiId.substring(0, includeWmsMiId.length - 1);
        if (includeMmfId != '')
            includeMmfId = includeMmfId.substring(0, includeMmfId.length - 1);
        data += '&includeWmsMiIds=' + includeWmsMiId + '&includeMmfIds=' + includeMmfId;
    }
    if (mmfId != '') {
        mmfId = mmfId.substring(0, mmfId.length - 1);
        data += '&mmfIds=' + mmfId;
    }
    if (wmsMiId != '') {
        wmsMiId = wmsMiId.substring(0, wmsMiId.length - 1);
        data += '&wmsMiIds=' + wmsMiId;
    }
    if (unit != '') {
        unit = unit.substring(0, unit.length - 1);
        data += '&units=' + unit;
    }
    if (basePrice != '') {
        basePrice = basePrice.substring(0, basePrice.length - 1);
        data += '&origionRetailPrices=' + basePrice;
    }
    if (retailPrice != '') {
        retailPrice = retailPrice.substring(0, retailPrice.length - 1);
        data += '&retailPrices=' + retailPrice;
    }
    $.supper("doservice", {
        "service": service,
        "data": data,
        "ifloading" : 1,
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // _paId = jsondata.obj.paiId;
                // _edit = true;
                if (updateNew == true) {
                    updateNewFunc();
                } else {
                    $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: backTo});
                }
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

function updateNewFunc() {
    let data = $('#saveForm').serialize();
    data += '&paiId=' + _paId;
    let mmfId = '';
    let wmsMiId = '';
    let unit = '';
    let basePrice = '';
    let retailPrice = '';
    let paieId = '';
    let updateNew = false;
    let service = _all_saveAction;
    let includeWmsMiId = '';
    let includeMmfId = '';
    let idx = 0;
    let mmfIdtemp;
    for (let inx in _mmfIds) {
        mmfIdtemp = _mmfIds[inx];
        if (_saveObject[mmfIdtemp] == undefined) {
            includeMmfId += mmfIdtemp + ',';
            includeWmsMiId += _wmsMiIds[idx] + ',';
            continue;
        }

        mmfId += _saveObject[mmfIdtemp].mmfId + ',';
        wmsMiId += _saveObject[mmfIdtemp].wmsMiId + ',';
        unit += _saveObject[mmfIdtemp].baseUnit + ',';
        basePrice += _saveObject[mmfIdtemp].basePrice + ',';
        retailPrice += _saveObject[mmfIdtemp].retailPrice + ',';

        idx++;
    }
    if (includeWmsMiId != '')
        includeWmsMiId = includeWmsMiId.substring(0, includeWmsMiId.length - 1);
    if (includeMmfId != '')
        includeMmfId = includeMmfId.substring(0, includeMmfId.length - 1);
    data += '&includeWmsMiIds=' + includeWmsMiId + '&includeMmfIds=' + includeMmfId;

    if (mmfId != '') {
        mmfId = mmfId.substring(0, mmfId.length - 1);
        data += '&mmfIds=' + mmfId;
    }
    if (wmsMiId != '') {
        wmsMiId = wmsMiId.substring(0, wmsMiId.length - 1);
        data += '&wmsMiIds=' + wmsMiId;
    }
    if (unit != '') {
        unit = unit.substring(0, unit.length - 1);
        data += '&units=' + unit;
    }
    if (basePrice != '') {
        basePrice = basePrice.substring(0, basePrice.length - 1);
        data += '&origionRetailPrices=' + basePrice;
    }
    if (retailPrice != '') {
        retailPrice = retailPrice.substring(0, retailPrice.length - 1);
        data += '&retailPrices=' + retailPrice;
    }
    $.supper("doservice", {
        "service": service,
        "data": data,
        "ifloading" : 1,
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: backTo});
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

function getMMGridUrl() {
    let data = '';
    let service = _queryAction_WithSave;
    if (_paId != undefined) {
        service = _all_QueryEditAction;
        data = 'paiId=' + _paId;
        if (_wmsMiIds.length > 0) {
            data += '&wmsMiIds=' + _wmsMiIds.join(',');
        }
        if (_mmfIds.length > 0) {
            data += '&mmfIds=' + _mmfIds.join(',');
        }
    } else {
        if (_wmsMiIds.length <= 0 && _mmfIds.length <= 0)
            return;
        if (_wmsMiIds.length > 0) {
            data += '&wmsMiIds=' + _wmsMiIds.join(',');
        }
        if (_mmfIds.length > 0) {
            data += '&mmfIds=' + _mmfIds.join(',');
        }
    }

    let att_url = $.supper("getServicePath", {"service": service, "data": data});
    return att_url;
}

function main_search() {
    allPer = 0;
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
    // initCount();
}

function exportExcel() {

}

function initCount() {
    if (_paId != undefined) { //维护或者详情
        let data = 'paiId=' + _paId;
        $.supper("doservice", {
            "service": 'mdMaterielInfoService.getPriceCount',
            "data": data,
            "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    let count = jsondata.obj.count == undefined ? 0 : jsondata.obj.count;
                    let percent = Number(jsondata.obj.percent == undefined ? 0 : jsondata.obj.percent);
                    if (_wmsMiIds.length > 0) {
                        count += _wmsMiIds.length;
                        percent += (_wmsMiIds.length * ($('#paiPercentForm').val() == '' ? 0 : $('#paiPercentForm').val()));
                        percent = percent / _wmsMiIds.length;
                    }
                    $('#allCount').text(count);
                    $('#totalPricePercent').text(toDecimal2(percent));
                }
            }
        });
    } else { // 新建
        let count = 0;
        let percent = allPer;
        if (_wmsMiIds.length > 0) {
            count += _wmsMiIds.length;
            // percent += (_wmsMiIds.length * percent);
        }
        $('#allCount').text(count);
        $('#totalPricePercent').text(toDecimal2(percent / ($("#mxMatListMx tr").length <= 0 ? 1 : $("#mxMatListMx tr").length)));
    }

}

function searchNotJump() {
    $.supper("setProductArray", {"name": "searchPAName", "value": {searchName: $('#searchName1').val(), hide: true}});
    var att_url = $.supper("getServicePath", {"data": '', url: "/jsp/dentistmall/storage/selNewInventory"});
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: "物料信息",
        icon: "fa-bars",
        width: "1080",
        height: '670',
        BackE: addInevneoty
    });
}
//跳转方法
function searchInventory() {
        $.supper("setProductArray", {"name": "searchPAName", "value": {searchName: $('#searchName1').val(), hide: true}});
        var att_url = $.supper("getServicePath", {"data": '', url: "/jsp/dentistmall/storage/selNewInventory"});
        var tt_win = $.supper("showWin", {
            url: att_url,
            title: "物料信息",
            icon: "fa-bars",
            width: "1080",
            height: '670',
            BackE: addInevneoty
        });
}
//选择数据接收参数回显数据方法
// function addInevneoty() {
//     let selPartAndNorm = $.supper("getProductArray", "selPartAndNorm");
//     if (selPartAndNorm != undefined && selPartAndNorm != null) {
//         if (selPartAndNorm.wmsMiId == undefined || selPartAndNorm.mmfId == undefined || selPartAndNorm.wmsMiId == '' || selPartAndNorm.mmfId == '') {
//
//         } else {
//             if (_wmsMiIds.indexOf(selPartAndNorm.wmsMiId) < 0) {
//                 _wmsMiIds.push(selPartAndNorm.wmsMiId);
//             }
//             if(_mmfIds.indexOf(selPartAndNorm.mmfId) < 0) {
//                 _mmfIds.push(selPartAndNorm.mmfId);
//             }
//             main_search();
//         }
//     }
//     $.supper("setProductArray", {"name": "selPartAndNorm", "value": null});
//
// }
var url;
$(function () {
    _wmsMiIds = [];
    _mmfIds = [];
    let priceAdjustment = $.supper("getProductArray", "priceAdjustment");
    if (priceAdjustment != undefined && priceAdjustment != null) {
        url = priceAdjustment.url;
        if (priceAdjustment.paiId != undefined)
            _paId = priceAdjustment.paiId;
        if (priceAdjustment.edit != undefined)
            _edit = priceAdjustment.edit;
    }

    _all_win_tools_but.xtoolbar('create', _Toolbar);

    // _all_datagrid_height = $(window).height() - 304 - 195;

    // _DataGrid.height = _all_datagrid_height;

    // _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);

    // _all_win_datagrid.on('loadSuccess', initCount);
    $("#tree").css("height", $(window).height() - 165);
    loadItemZtree();

    if (_paId != undefined) {
        $('#saveForm').xform('loadAjaxForm',{'ActionUrl':_all_findPAAction,"data":'paiId=' + _paId});
        if (_edit == undefined) {
            $('#searchName1').hide();
            $('#searchInvBtn').hide();
            $('#win_but_search').hide();
            $('#saveForm input').each(function () {
                $(this).attr('readonly', 'readonly');
            });
            $('#paiTypeForm').attr('readonly', 'readonly');
        }
        searchMatMx();
        // main_search();
    }

    if (_paId == undefined && _edit == undefined) {
        $.supper("doservice", {
            "service": "SysParameterService.getNewCode",
            "options": {
                "type": "post",
                "data": "prefix=PA",
                "async": false
            }, "ifloading": 1, "BackE": function (jsondata) {
                if (jsondata.code == "1" && jsondata.obj) {
                    $('#paiCodeForm').val(jsondata.obj);
                }
            }
        });
    }
    // laydate({
    //     elem: '#createDate',
    //     format: 'YYYY-MM-DD'//日期格式
    // });
    //
    initDataGrid();

    $("#searchName1").bind("input propertychange",function(event){
        // $('#searchName1').val($(this).val());
        // 如果正在加载数据中，保存现有的搜索词
        if (isLoading == true) {
            lastSearchName = $(this).val();
            return;
        }
        if (timeOut != undefined && timeOut != null)
            clearTimeout(timeOut);
        timeOut = setTimeout(function () {
            isLoading = true;
            search();
        }, 500)
    });
    $('#searchName1').on('focus', function () {
        canHideTable = true;
        if ($(this).val() != '')
            $("#tables").show();
    })
    $('#searchName1').on('blur', function () {
        setTimeout(function () {
            if (canHideTable == true || $(this).val() == '') {
                canHideTable = false;
                $('tables').hide();
            }
        }, 200);
    })
    $('#tables').on('mouseover', function () {
        canHideTable = false;
    })
    $('#tables').on('mouseleave', function () {
        canHideTable = true;
    })

    $(document).on('click', function (event) {
        if ($('#searchName1').is(':focus') == false) {
            if (canHideTable == true) {
                canHideTable = false;
                $("#tables").hide();
            }
        }
    })
})