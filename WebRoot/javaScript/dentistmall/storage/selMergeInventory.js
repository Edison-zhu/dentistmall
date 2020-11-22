var treeClickLevel = 0;
var _mdpId;
var _mdpsId;
var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
let _all_SiteSecond = 'MdMaterielPartSecondService.getSecondObject';

var loadItemZtree = function () {
    let data = '';
    // if (_mdpId != undefined) {
    //     data += 'mdpId=' + _mdpId;
    // }
    // if (_mdpsId != undefined) {
    //     data += '&mdpsId=' + _mdpsId;
    // }
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
                console.log("树的等级"+treeNode.level)
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
                search2();
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
    search2();
}

//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null) {
        var zTree = $.fn.zTree.getZTreeObj("tree");
        if (lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
            zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");
    }
    search2();
}

//--------------------------------tree---------------------------

function search() {
    let service = queryAction;
    if (isView == true) {
        service = viewQueyAction;
    }
    var att_mmgurl = rpc.getUrlByForm(service, "queryForm");
    if (isView == true) {
        att_mmgurl += '&wiId=' + wiId;
    }
    if (_mdpId != undefined) {
        att_mmgurl += '&mdpId=' + _mdpId;
    } else if ($('#mdpId').val() != '') {
        att_mmgurl += '&mdpId=' + $('#mdpId').val();
    }
    if (_mdpsId != undefined) {
        att_mmgurl += '&mdpsId=' + _mdpsId;
    } else if ($('#mdpsId').val() != '') {
        att_mmgurl += '&mdpsId=' + $('#mdpsId').val();
    }
    if (_searchName != undefined && _searchName != null && _searchName != '') {
        att_mmgurl += '&searchName=' + _searchName;
    }
    mmg.opts.url = att_mmgurl;
    mmg.load();

}

var mmg ;
var queryAction = "MdInventoryService.getPagerModelObject1";
var viewQueyAction = "MdInventoryService.getPagerMergeInventoryLog";
var initDataGrid =  function(){
    var cols = [
        {title: '物料编号', sortable: true,name: 'mmfCode', width: 100, align: 'center'},
        {title: '物料名称', sortable: true,name: 'matName', width: 100, align: 'center'},
        {title: '物料图标', sortable: true,name: 'logo', width: 100, align: 'center', renderer: renderLogo},
        {title: '物料规格', sortable: true,name: 'mmfName', width: 50, align: 'center'},
        {title: '库存数量', sortable: true,name: 'brand', width: 20, align: 'center'},
        {title: '单位/最小单位', sortable: true,name: 'unitString', width: 20, align: 'center', renderer: renderUnit},
        {title: '生产厂家', sortable: true,name: 'productFactory', width: 20, align: 'center'},
        {title: '品牌', sortable: true,name: 'brand', width: 40, align: 'center'},
        {title: '采购均价/零售价', sortable: true,name: 'priceString', width: 20, align: 'center', renderer: renderPrice},
        // {title: '操作', name: 'con', width: 100, align: 'center', renderer: control}
    ];
    var att_mmgurl =  getUrl();
    mmg = $('#datagrid1').mmGrid({
        height:'280px'
        ,cols: cols
        , method: 'post'
        , remoteSort:true
        ,url : att_mmgurl
        , sortName: 'SECUCODE'
        , sortStatus: 'asc'
        , multiSelect: true
        ,showBackboard:false
        , checkCol: true
        , fullWidthRows: true
        , autoLoad: false
        , nowrap:true
        , plugins: [
            $('#pg').mmPaginator({})
        ]
    });
    mmg.load();
}

function renderPrice(val, item, rowIndx) {
    let str = '';
    str += '<span>' + (item.avgPrice == undefined ? '' : item.avgPrice)+ '</span>/<span>' + (item.price == undefined ? '' : item.price) + '</span>';
    return str;
}

function renderLogo(val, item, rowIndx) {
    let str = '';
    if (item.logoPath == undefined)
        str = '';
    else
        str += '<img src="' + item.logoPath + '" style="width: 40px; height: 40px;" />';
    return str;
}

function renderUnit(val, item, rowIndex) {
    let str = '';
    str += '<span>' + (item.basicUnit == undefined ? '' : item.basicUnit) + '</span>/<span>' + (item.unit == undefined ? '' : item.unit) + '</span>';
    return str;
}

function control(val, item, rowIndex) {

}

function getUrl(exclude) {
    let service = queryAction;
    if (isView == true) {
        service = viewQueyAction;
    }
    let att_mmgurl = rpc.getUrlByForm(service,"queryForm");
    if (isView == true) {
        att_mmgurl += '&wiId=' + wiId;
    }
    if (exclude == undefined) {
        if (_mdpId != undefined) {
            att_mmgurl += '&mdpId=' + _mdpId;
        }
        // else if ($('#mdpId').val() != '') {
        //     att_mmgurl += '&mdpId=' + $('#mdpId').val();
        // }
        if (_mdpsId != undefined) {
            att_mmgurl += '&mdpsId=' + _mdpsId;
        }
        // else if ($('#mdpsId').val() != '') {
        //     att_mmgurl += '&mdpsId=' + $('#mdpsId').val();
        // }
    }
    if ($('#searchName').val() != '')
        att_mmgurl += '&searchName=' + $('#searchName').val();
    return att_mmgurl;
}

function search2(exclude){
    var att_mmgurl = getUrl(exclude);
    mmg.opts.url = att_mmgurl;
    mmg.load();
}

function  closeWin(){
    $.supper("closeWin");
}

function save(){
    var rows=mmg.selectedRows();
    if(rows==null || rows.length <=0){
        $.supper("alert",{ title:"操作提示", msg:"请选择数据！"});
        return;
    }
    $.supper("setProductArray", {"name":"selInvInfoArray", "value":rows});
    closeWin();
}

var isView = false;
var wiId;
$(function () {
    var viewMerge = $.supper("getProductArray", "viewInventoryMerge");
    if (viewMerge != undefined && viewMerge != null && viewMerge.viewMerge == true) {
        isView = true;
        wiId = viewMerge.wiId;
    }
    $.supper("setProductArray", {"name": "viewInventoryMerge", "value": null});
    $("#tree").css("height", $(window).height() - 165);
    loadItemZtree();
    // initTree();
    initDataGrid();
    if (isView == true) {
        $('#saveMerge').hide();
        $('#cancelMerge').hide();
    }
})