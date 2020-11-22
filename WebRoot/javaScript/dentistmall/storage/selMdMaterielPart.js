var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';

var _mdpId;
var _mdpsId;

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
                $("#mdpId").val(treeNode.id);
                backToSelected(treeNode);
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

function backToSelected(treeNode) {
    let id = 0;
    let name = '';
    let sid = 0;
    let sname = '';
    if (treeNode.level == 0) {
        sid = treeNode.id;
        sname = treeNode.name;
    }
    if (treeNode.level == 1) {
        id = treeNode.id;
        name = treeNode.name;
        sid = treeNode.pId;
        sname = $('#' + treeNode.parentTId + '_span').text();
    }
    if (sid == null || sid == '') {
        $.supper("alert", {title: "操作提示", msg: "请选择数据！"});
        return;
    }
    $.supper("setProductArray", {"name": "selPart", "value": {id: id, name: name, sid: sid, sname: sname}});
    $.supper("closeWin");
}

function refresh() {
    loadItemZtree();
}

function addNewPart() {
    var att_url= $.supper("getServicePath", {url:"/jsp/dentistmall/storage/materielPart.jsp"});
    $.supper("showTtemWin",{ "url":att_url,"title":"物料分类管理"});
    $.supper("closeWin");
}

$(function () {
    loadItemZtree();
    // initTree();
})