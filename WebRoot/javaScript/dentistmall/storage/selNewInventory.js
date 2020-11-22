let _mdpId;
let _mdpsId;

var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
let _all_SiteSecond = 'MdMaterielPartSecondService.getSecondObject';
// let _all_deleteAction = 'MdMaterielPartDetailService.deleteObject';
// let _all_normAction = 'MdMaterielPartDetailService.getNormObjectList';

var mmg;
var lastExpandNode;
var queryAction = "mdMaterielInfoService.getMyPagerModelObject";
var _dblClick = function (data, row, col) {
    addMaterielDetail(data.wmsMiId);
}

var _searchName;
var initDataGrid = function () {
    var cols = [
        {title: '物料编号', name: 'mmfCode', width: 100, align: 'center'},
        {title: '物料名称', name: 'matName', width: 100, align: 'center'},
        {title: '物料规格', name: 'mmfName', width: 50, align: 'center'},
        {title: '品牌', name: 'brand', width: 20, align: 'center'},
        {title: '生产厂家', name: 'productFactory', width: 20, align: 'center'},
        {title: '单位', name: 'basicUnit', width: 20, align: 'center'},
        {title: '采购均价', name: 'avgPrice', width: 40, align: 'center'},
        {title: '零售价', name: 'retailPrice', width: 20, align: 'center',},
        {title: '操作', name: 'con', width: 100, align: 'center', renderer: control}
    ];

    var att_mmgurl = getUrl();
    mmg = $('#datagrid1').mmGrid({
        height: 'auto'
        , cols: cols
        , method: 'get'
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
    if ($('#searchName').val() != undefined && $('#searchName').val() != null && $('#searchName').val() != '') {
        att_mmgurl += '&searchName=' + $('#searchName').val();
    }
    return att_mmgurl;
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
                if (treeNode.level == 0) {
                    _mdpId = treeNode.id;
                } else {
                    _mdpsId = treeNode.id;
                }
                search();
            }
        }
    }
    //设置树的初始数据
    var zNodes = [
        // {id: 0, pId: "", name: "栏目列表", isParent: true}
    ];
    $.fn.zTree.init($("#tree"), setting, zNodes);
    //设置树形数据结束
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

function search(exclude) {
    var att_mmgurl = getUrl(exclude);
    mmg.opts.url = att_mmgurl;
    mmg.load();

}

function enterSearch() {
    if (event.keyCode == 13)
        search(true);
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

function control(val, item, rowIndex) {
    return "<a onclick=\"selectRow(" + item.wmsMiId + ", " + item.mmfId + ")\" style='display:block;width: 70px;height: 24px;border-radius: 12px;background: #23c6c8;text-align: center;line-height: 24px;color: #fff;margin: 0 auto;font-size: 12px'>选择</a>";
}

function selectRow(wmsMiId, mmfId) {
    if (wmsMiId == undefined || wmsMiId == '') {
        $.supper("alert", {title: "操作提示", msg: "请选择数据！"});
    }
    if (mmfId == undefined || mmfId == '') {
        $.supper("alert", {title: "操作提示", msg: "请选择数据！"});
    }
    $.supper("setProductArray", {"name": "selPartAndNorm", "value": {wmsMiId: wmsMiId, mmfId: mmfId}});
    closeWin();
}

function  closeWin(){
    $.supper("setProductArray", {"name": "searchPAName", "value": null});
    $.supper("closeWin");
}

$(function () {
    var searchName = $.supper("getProductArray", "searchPAName");
    if(searchName != undefined && searchName != null && searchName.searchName != undefined){
        _searchName = searchName.searchName;
        $('#searchName').val(_searchName);
        if (searchName.hide != undefined && searchName.hide == true) {
            $('#selShow').show();
            $('#show').hide();
        }
    }

    $("#tree").css("height", $(window).height() - 165);
    loadItemZtree();
    // initTree();
    initDataGrid();

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