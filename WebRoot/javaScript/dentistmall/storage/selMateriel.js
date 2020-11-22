let _mdpId;
let _mdpsId;

var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
let _all_SiteSecond = 'MdMaterielPartSecondService.getSecondObject';
// let _all_deleteAction = 'MdMaterielPartDetailService.deleteObject';
// let _all_normAction = 'MdMaterielPartDetailService.getNormObjectList';

var mmg;
var lastExpandNode;
var queryAction = "mdMaterielInfoService.getMyPagerModelObject";

var _searchName;
var _wmsMiId;
var _mmfId;
var _type; //是那种类型跳转到此页面，1入库
var initDataGrid = function () {
    var cols = [
        {title: '物料编号', sortable: true, name: 'mmfCode', width: 100, align: 'center'},
        {title: '物料名称', sortable: true, name: 'matName', width: 100, align: 'center'},
        // {title: '图标', name: 'logo', width: 60, align: 'center', renderer: renderLogo},
        {title: '规格型号', sortable: true, name: 'mmfName', width: 50, align: 'center'},
        {title: '当前库存', name: 'baseNumber', width: 20, align: 'center'},
        {title: '单位', name: 'basicUnit', width: 40, align: 'center', renderer: renderUnit},
        // {title: '品牌', sortable: true, name: 'brand', width: 20, align: 'center'},
        // {title: '生产厂家', sortable: true, name: 'productFactory', width: 40, align: 'center'},
        // {title: '采购均价/零售价', sortable: true, name: 'avgPrice', width: 70, align: 'center', renderer: renderPrice},
        {title: '操作', name: 'con', width: 80, align: 'center', renderer: control}
    ];

    var att_mmgurl = getUrl();
    mmg = $('#datagrid1').mmGrid({
        height: 'auto'
        , cols: cols
        , nowrap: false
        , method: 'get'
        // , remoteSort: true
        , url: att_mmgurl
        , sortName: 'SECUCODE'
        , sortStatus: 'asc'
        , multiSelect: false
        , checkCol: false
        , fullWidthRows: true
        , showBackboard: false
        , autoLoad: false
        , plugins: [
            $('#pg').mmPaginator({})
        ],
        dblClickFunc: _dblClick
    });
    mmg.load();
    // if (_type != undefined && _type == 1)
    //     mmg.on('loadSuccess', checkRows);
}
function _dblClick(data, row, col){
    selectRow(data.mmfId);
    console.log("111")
}
function getUrl(exclude) {
    let att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
    if (exclude == undefined) {
        if (_mdpId != undefined) {
            att_mmgurl += '&mdpId=' + _mdpId;
        }
        if (_mdpsId != undefined) {
            att_mmgurl += '&mdpsId=' + _mdpsId;
        }
    }
    // if (_searchName != undefined && _searchName != null && _searchName != '') {
    //     att_mmgurl += '&searchName=' + _searchName;
    // }
    return att_mmgurl;
}

function renderLogo(val, item, rowIdex) {
    let str = '';
    if (item.lessenFilePath != undefined && item.lessenFilePath != '') {
        str += '<img src="' + item.lessenFilePath + '" style="width: 40px; height: 40px;" />';
    }
    return str;
}

function renderUnit(val, item, rowIdex) {
    let str = '';
    str += '<span>' + item.basicUnit+ '</span>/<span>' + (item.splitUnit == undefined ? '' : item.splitUnit) + '</span>';
    return str;
}

function renderPrice(val, item, rowIndx) {
    let str = '';
    str += '<span>' + (item.avgPrice == undefined ? '' : item.avgPrice)+ '</span>/<span>' + (item.price == undefined ? '' : item.price) + '</span>';
    return str;
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

function enterSearch(exclude) {
    if (event.keyCode == 13)
        search(exclude);
}

function search(exclude) {
    var att_mmgurl = getUrl(exclude);
    mmg.opts.url = att_mmgurl;
    mmg.load();

}

function checkRows() {
    if (mmg.rows().length == 1) {
        let item = mmg.rows()[0];
        $.supper("setProductArray", {"name": "selPartAndNorm", "value": {data: {wmsMiId: item.wmsMiId, mmfId: item.mmfId}}});
        closeWin();
    }
}

// function add() {
//     var mdpId = $("#mdpId").val();
//     if (mdpId == '0')
//         mdpId = "";
//     var data = "mdpId=" + mdpId;
//     var att_url = $.supper("getServicePath", {
//         "service": "SysWebsiteColumnsService.addSysWebsiteColumns",
//         "data": data,
//         url: "/jsp/website/sysWebsiteColumns"
//     });
//     var tt_win = $.supper("showWin", {
//         url: att_url,
//         title: "栏目信息",
//         icon: "fa-tint",
//         width: 800,
//         height: 450,
//         BackE: loadAddTree
//     });
// }

// function toUpdate(wmsMiId) {
//     var data = "swcId=" + swcId;
//     var att_url = $.supper("getServicePath", {
//         "service": "SysWebsiteColumnsService.GetOneSysWebsiteColumns",
//         "data": data,
//         url: "/jsp/website/sysWebsiteColumns"
//     });
//     var tt_win = $.supper("showWin", {
//         url: att_url,
//         title: "栏目信息",
//         icon: "fa-tint",
//         width: 800,
//         height: 450,
//         BackE: loadAddTree
//     });
// }

function renderNorm(val, item, rowIndex) {
    if (item.normCount <= 0 || item.normList.length <= 0) {
        return '无';
    }
    let str = '';
    let normList = item.normList;
    let norm = '';
    for (let idx in normList) {
        norm += normList[idx].mmfName + ',';
    }
    if (norm != '') {
        norm = norm.substring(0, norm.length - 1);
    }
    str += norm;
    str += '<a onclick="viewMaterielNorm(' + item.wmsMiId + ')">查看</a>';
    return str;
}

var _items = {};
function control(val, item, rowIndex) {
    let str = '';
    if (_wmsMiId != undefined) {
        if (_items[item.mmfId] == undefined) {
            _items[item.mmfId] = {};
        }
        _items[item.mmfId] = item;

        // str += (item.mdpId == undefined ? '' : item.mdpId) + ',';
        // str += (item.mdpsId == undefined ? '' : item.mdpsId) + ',';
        // str += (item.mmfCode == undefined ? '' : item.mmfCode) + ',';
        // str += (item.batchCode == undefined ? '' : item.batchCode) + ',';
        // str += (item.retailPrice == undefined ? '' : item.retailPrice) + ',';
        // str += (item.backPrinting == undefined ? '' : item.backPrinting) + ',';
        // str += (item.splitUnit == undefined ? '' : item.splitUnit) + ',';
        // str += (item.mmfId == undefined ? '' : item.mmfId);
        // str += (item.mmfCode == undefined ? '' : item.mmfId);
        // str.substring(0, str.length - 1);
        return "<a onclick=\"selectRow(" + item.mmfId + ")\" class='btn btn-info btn-sm'>确定</a>";
    } else {
        return "<a onclick=\"selectMatRow(" + item.wmsMiId + ", " + item.mmfId + ")\" class='btn btn-info btn-sm'>确定</a>";
    }
}

function selectMatRow(wmsMiId, mmfId) {
    $.supper("setProductArray", {"name": "selPartAndNorm", "value": {data: {wmsMiId: wmsMiId, mmfId: mmfId}}});

   // closeWin();
}

function selectRow(wmsMiId) {
    console.log(wmsMiId)
    $.supper("setProductArray", {"name": "selPartAndNorm", "value": {data: _items[wmsMiId]}});
    closeWin();
}

function  closeWin(){
    $.supper("setProductArray", {"name": "searchName", "value": null});
    $.supper("closeWin");
}


function removeClass() {
    $('#searchName').removeClass('focus');
}

function addClass() {
    $('#searchName').addClass('focus');
}

$(function () {
    var searchName = $.supper("getProductArray", "searchName");
    if(searchName != undefined && searchName != null){
        _searchName = searchName.searchName;
        _wmsMiId = searchName.wmsMiId;
        _mmfId = searchName.mmfId;
        _type = searchName.type;
    }
    if (_searchName != undefined) {
        $('#searchName').val(_searchName);
    }

    if (_wmsMiId == undefined) {
        $('#addOnceBtn').hide();
        $('#newAddBtn').hide();
    }

    $("#tree").css("height", $(window).height() - 165);
    loadItemZtree();
    // initTree();
    initDataGrid();

    $('#searchName').focus();
    $('#searchName').addClass('focus');
    // initOneUploadImg("lessenFilecode","lessenFileDiv");
    // initListUploadImg("listFilecode","imglist", null, {limit: 1, accept: 'image/png,image/jpg,image/jpeg,image/gif', acceptType: 'image', size: 3 * 1024/*KB*/});

    // laydate({
    //     elem: '#cTime_start',
    //     format: 'YYYY-MM-DD'//日期格式
    // });
    // laydate({
    //     elem: '#cTime_end',
    //     format: 'YYYY-MM-DD' //日期格式
    // });
})

function exportExcel() {

}

function oneAdd() {
    var data = '';

    data += 'wmsMiId=' + _wmsMiId;
    data += '&mmfIdAdd=' + _mmfId;
    data += '&isEdit=true';
    data += '&isAdd=true';
    var att_url = $.supper("getServicePath", {
        "service": "MdMaterielInfoService.findObject",
        "data": data,
        url: "/jsp/dentistmall/storage/mdmaterielpartDetail"
    });
    // var att_url = $.supper("getServicePath", {"data": '', url: "/jsp/dentistmall/storage/mdmaterielpartDetail.jsp"});
    $.supper("setProductArray", {"name": "oneAddMat", "value": {url: att_url} });
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: "物料字典",
        icon: "fa-bars",
        width: "1400",
        height: '800',
        // closeBtn: false,
        BackE: function () {
            let extData = $.supper("getProductArray", "oneAddMat");
            $.supper("setProductArray", {"name": "oneAddMat", "value": null });
            addMatInfo(extData);
        }
    });
}
let that = this;
function addMatInfo(extData) {
    if (extData == undefined || extData.wmsMiId == undefined || extData.mmfId == undefined) {
        return;
    }
    let data = '';
    if (extData.wmsMiId != undefined)
        data += '&wmsMiId=' + extData.wmsMiId
    if (extData.mmfId != undefined)
        data += '&mmfId=' + extData.mmfId;
    $.supper("doservice", {
        "service": queryAction, 'data': data, "BackE": function (jsondata) {
            if (jsondata.items.length > 0) {
                let item = jsondata.items[0];
                $.supper("confirm", {
                    title: "操作提示", msg: "是否将此条物料信息作为默认填选择物料？", yesE: function () {
                        $.supper("setProductArray", {"name": "selPartAndNorm", "value": {data: item}});
                        setTimeout(function () {
                            that.closeWin();
                        }, 200)

                    }, noE: function () {
                        search();
                    }
                });

            }
        }
    });
}

function newAdd() {
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/mdmaterielpartDetail"});
    $.supper("setProductArray", {"name": "mdMaterielPartDetail", "value": {url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": '物料信息维护'});
    closeWin();
}