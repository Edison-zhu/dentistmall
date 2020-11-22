let _mdpId;
let _mdpsId;

var _all_win_tools_but = $("#win_tools_but");

let _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
let _all_SiteSecond = 'MdMaterielPartSecondService.getSecondObject';
// let _all_deleteAction = 'MdMaterielPartDetailService.deleteObject';
// let _all_normAction = 'MdMaterielPartDetailService.getNormObjectList';
// var queryAction_all_queryAction = "mdInventoryService.getPagerModelObject";

// var _all_queryAction = "MdInventoryService.getPagerViewObject";

var queryAction = "MdInventoryService.getPagerViewObject";

var _all_win_url_edit = "/jsp/dentistmall/storage/editInventoryMerge.jsp";

var _all_edit_title = "合并";
var _all_edit_width = 1000;
var _all_edit_height = 500;

var mmg;
var lastExpandNode;
var _dblClick = function (data, row, col) {
    viewInfo(data.wiId);
}

var _Toolbar = {
    toolBarId: "tools_but",
    items: [
        //{title:"",id:"safetyEwBtn",colourStyle:"default"},
        {title: "", id: "safetyEwBtn", icon: "qqq", colourStyle: "default", rounded: true, clickE: chickSafety},
        // {title:"&nbsp查询&nbsp",id:"win_but_search",icon:"main_search", colourStyle:"default",rounded:true,clickE:main_search},
        {
            title: "全部导出",
            id: "win_but_export",
            icon: "download",
            colourStyle: "success",
            rounded: true,
            clickE: export_all
        },
        {
            title: "&nbsp导入&nbsp",
            id: "win_but_export1",
            icon: "cloud-upload",
            colourStyle: "info",
            rounded: true,
            clickE: imp_all
        }
    ]
};

function chickSafety() {
    if ($("#safetyhide").is(":hidden")) {
        $("#safetyhide").show();
    }else{
        $("#safetyhide").hide();
    }
}

var initDataGrid = function () {
    var cols = [
        {title: '物料编号',sortable: true,name: 'matCode', width: 100, align: 'center'},
        {title: '物料图标',sortable: false, name: 'logo', width: 50, align: 'center', renderer: renderLogo},
        {title: '物料名称', sortable: true,name: 'matName', width: 100, align: 'center'},
        {title: '规格', sortable: true,name: 'mmfName', width: 50, align: 'center'},
        {title: '库存数量', sortable: true,name: 'baseNumber', width: 20, align: 'center', renderer: renderQuantity},
        {title: '有效期预警', sortable: true,name: 'dateIsNot', width: 20, align: 'center', renderer: renderValiedDate},
        {title: '采购均价/零售均价', sortable: true,name: 'price', width: 60, align: 'center', renderer: renderAvg},
        {title: '单位', sortable: true,name: 'basicUnit', width: 20, align: 'center'},
        {title: '预警数最低/最高', sortable: true,name: 'warningShu', width: 50, align: 'center', renderer: renderWarning},
        {title: '出入库日志', sortable: false, name: 'log', width: 20, align: 'center', renderer: renderLog},
        {title: '操作', name: 'mddCon', width: 100, align: 'center', renderer: control}
    ]
    var att_mmgurl = '';
    if (_warning == undefined)
        att_mmgurl = getUrl();
    mmg = $('#datagrid1').mmGrid({
        height: 'auto'
        , cols: cols
        , method: 'get'
        , remoteSort: false
        , url: att_mmgurl
        , sortName: 'SECUCODE'
        , sortStatus: 'asc'
        , multiSelect: true
        , checkCol: true
        , fullWidthRows: true
        , showBackboard: false
        , autoLoad: false
        , dblClickFunc: _dblClick
        , plugins: [
            $('#pg').mmPaginator({})
        ]
    });
    mmg.load();
    mmg.on('loadSuccess', initHUI);
}
//2020-07-03  增加库存采购均价 零售均价合计  yanglei
function sumPrice() {
    var vdata = $("#queryForm").serialize();
    $.supper("doservice", {
        "service": "MdInventoryService.getPriceSum", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#buyMoneyAll").html(jsondata.obj.money1s);
                $("#retioMoneyAll").html(jsondata.obj.money2s);
            }else {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        }
    });
}

// $.supper("doservice", {
//     "service": "MdOutOrderService.saveDeletemomId",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
//         if (jsondata.code == "1") {
//             momId="";
//             initDataMx();
//             $.supper("alert",{ title:"操作提示", msg:"移除成功!"});
//         } else{
//             $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
//         }
//     }
// });


function getUrl(exclude){
    let att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
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
    }
    // else if ($('#mdpsId').val() != '') {
    //     att_mmgurl += '&mdpsId=' + $('#mdpsId').val();
    // }
    if (_inventoryWarn == true) {
        att_mmgurl += '&inventoryWarn=1';
    }
    if (_validateWarn == true) {
        att_mmgurl += '&validdateWarn=1';
        // alert(att_mmgurl);
    }
    if (_zeroWarn == true) {
        att_mmgurl += '&zeroWarn=1';
        // alert(att_mmgurl);
    }
    return att_mmgurl;
}
//2020-07-03  增加库存采购均价 零售均价合计  yanglei
function main_search(exclude) {
    mmg.opts.url = getUrl(exclude);
    sumPrice();
    mmg.load();
}

function searchEnter() {
    if (event.keyCode == 13)
        main_search(true);
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
                } else if(treeNode.level == 2){
                    _mdpsId = treeNode.id;
                    _mdpsIdname = treeNode.name;
                } else {
                    _mdpId = undefined;
                    _mdpsId = undefined;
                    _mdpIdname = '';
                    _mdpsIdname = '';
                }
                main_search();
            }
        }
    }
    //设置树的初始数据   main_search();
    var zNodes = [
         {id: 0, pId: "", name: "全部", isParent: true}
    ];
    $.fn.zTree.init($("#tree"), setting, zNodes);
    //设置树形数据结束自动展示第一层树
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
    main_search();
}

//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null) {
        var zTree = $.fn.zTree.getZTreeObj("tree");
        if (lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
            zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");
    }
    main_search();
}

//--------------------------------tree---------------------------

var _inventoryWarn = false;
var _validateWarn = false;
var _zeroWarn = false;
// function main_search() {
//     var att_mmgurl = getUrl();
//     mmg.opts.url = att_mmgurl;
//     mmg.load();
// }

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

// function toUpdate(mddId) {
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

function renderWarning(val, item, nowIndex) {
    let str = '';
    // if (item.warningDay != undefined) {
        str += '<span id="warningShu' + item.wiId + '">' + (item.warningShu == undefined ? '' : item.warningShu) + '</span>/<span id="maxShu' + item.wiId + '">' + (item.maxShu == undefined ? '' : item.maxShu) + '</span>'+'&nbsp-<span id="warningDay' + item.wiId + '">'+(item.warningDay == undefined ? '' : item.warningDay)+'</span>'+'&nbsp';
    // }else {
    // str += '<span id="warningShu' + item.wiId + '">' + (item.warningShu == undefined ? '' : item.warningShu) + '</span>/<span id="maxShu' + item.wiId + '">' + (item.maxShu == undefined ? '' : item.maxShu) + '</span>'+'&nbsp';
    // }
        str += '<a class=\'btn btn-warning  btn-xs\' onclick="setWarning(' + item.wiId + ', ' + item.warningShu + ', ' + item.maxShu+ ', ' + item.warningDay + ')">设置</a>';
    return str;
}

function renderLog(val, item, rowIndex) {
    let str = '';
    str += '<img onclick="toDetailLog(' + item.wiId + ', ' + item.wmsMiId + ')" style="width: 25px;" src="/dentistmall/img/format_log.png" />';
    // str += '<a onclick="toDetailLog(' + item.wmsMiId + ')">日志</a>';
    return str;
}

function toDetailLog(wiId, wmsMiId) {
    var data = "wiId=" + wiId + "&wmsMiId=" + wmsMiId;
    // var att_url = $.supper("getServicePath", {
    //     "data": data,
    //     url: "/jsp/dentistmall/storage/viewMdMaterielPartDetailLog.jsp"
    // });
    // var tt_win = $.supper("showTtemWin", {
    //     url: att_url,
    //     title: "规格型号",
    //     icon: "fa-calendar",
    //     height: _all_edit_height
    // });

    var att_url = $.supper("getServicePath", {
        'data': data,
        url: "/jsp/dentistmall/storage/viewInventoryLog.jsp"
    });
    var tt_win = $.supper("showWin", {
        url: att_url, title: "出入库日志", icon: "fa-th-list", width: 1400, height: 700
    });
}

function renderLogo(val, item, rowIdex) {
    let str = '';
    if (item.logoPath == undefined)
        str += '';
    else
        str += '<img src="' + item.logoPath + '" style="width: 40px; height: 40px;" />';
    return str;
}

function renderQuantity(val, item, rowIndex) {
    let str = '';
    let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    str += item.quantity + '';
    str += basicUnit + '/';
    let ratio = item.ratio == undefined ? 1 : item.ratio;
    str += (item.baseNumber - item.quantity * ratio) + '';
    str += ((item.unit == undefined || item.unit == '') ? basicUnit : item.unit);
    return str;
}

function renderValiedDate(val, item, rowIndex) {
    let str = '-';
    if (item.dateIsNot == 1) {
        str = '是';
    } else {
        str = '否';
    }
    return str;
}
//2020-07-03  增加库存采购均价 零售均价合计  yanglei
var money1=0;
var  money2=0;
function renderAvg(val, item, rowIndex) {
    let str = '';
    money1+=item.avgPrice;
    money2+=item.avgRetailPrice;
    str = '<span>' + (item.avgPrice == undefined ? '' : toDecimal2(item.avgPrice)) + '</span>/<span>' + (item.avgRetailPrice == undefined ? '' : toDecimal2(item.avgRetailPrice))+ '</span>';
    return str;
}
//2020-07-03  增加库存采购均价 零售均价合计  yanglei
function initHUI() {
    $("#buyMoney").html(toDecimal2(money1));
    $("#retioMoney").html(toDecimal2(money2));
    money1=0;
    money2=0;
}

function control(val, item, rowIndex) {
    return "<a onclick=\"viewInfo('" + item.wiId + "')\" class='btn btn-info btn-sm'>详情</a> &nbsp;" +
        "<a onclick=\"mergeInventory('" + item.wiId + "')\" class='btn btn-info btn-sm'>库存合并</a> &nbsp;";
}

function mergeInventory(wiId) {
    eval("var data= 'wiId=" + wiId + "'");
    var att_url = $.supper("getServicePath", {"data": data, url: _all_win_url_edit});

    var tt_win = $.supper("showWin", {
        url: att_url,
        title: _all_edit_title,
        icon: "fa-gears",
        width: 1250,
        height: 700,
        BackE: main_search
    });
}

function viewInfo(wiId) {
    eval("var data='wiId=" + wiId + "'");
    var att_url = $.supper("getServicePath", {
        // "service": "MdMaterielPartDetailService.doFindObject",
        "data": data,
        url: "/jsp/dentistmall/storage/viewInventoryInfo.jsp"
    });
    $.supper("setProductArray", {"name":"closeUrl", "value": att_url});
    $.supper("setProductArray", {"name": "viewInfoUrl", "value": {url: att_url} });
    $.supper("showTtemWin", {
        url: att_url,
        title: "规格型号",
    });
}

function export_all() {
    var selectedRows = mmg.selectedRows();
    if (selectedRows.length==0){
        $.supper("confirm", {
            title: "操作提示", msg: "您暂时未选择要导出的数据，系统将所有数据导出是否确定导出？", yesE: function () {
                var newTab=window.open('about:blank');
                $.supper("doservice", {"service":"EnterWarehouseExportService.exportInventoryView","data":vdata, "BackE":function (jsondata) {
                        if (jsondata.code == "1") {
                            newTab.location.href=jsondata.obj.path;
                        }else
                            $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
                    }});
            }
        });
        return;
    }
    var vdata = $("#queryForm").serialize();
    // var selectedRows = mmg.selectedRows();
    var wiIds='';
    for(var i=0;i<selectedRows.length;i++){
        wiIds+=selectedRows[i].wiId+',';
    }
    if (wiIds!=null&&wiIds!=undefined&&wiIds!='') {
        wiIds=wiIds.substring(0,wiIds.length-1);
    }
    vdata+='&wiId='+wiIds;
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"EnterWarehouseExportService.exportInventoryView","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}

function imp_all() {
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/impInventory.jsp"});
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: "库存导入",
        icon: "cloud-upload",
        width: 500,
        height: 500,
        BackE: main_search
    });
}

function setWarning(wiId, warningShu, maxShu, warningDay) {
    var title = '<div id="safetyEw" style="display: block"><img width="10px" height="10px"\n' +
        '                                                          src="/dentistmall/img/SafetyEarlyWarning.png"/><span\n' +
        '                    style="font-size: 12px;color: #333333">安全预警设置</span></div>';
    var str = '<div id="safetyhide"\n' +
        '                 style="background-color:white;width: 350px;height:250px;border: #0a2b39 solid 0px;z-index:9999;display: block">\n' +
        '                <form id="warningForm"><div class="top" style="width: 350px;height: 30px;margin-top: 8px;border-bottom:#C0C0C0 solid 1px">\n' +
        '                    <img width="25px" height="25px" src="/dentistmall/img/SafetyEarlyWarning.png"/>\n' +
        '                    <span style="font-size: 14px;"><b>安全预警设置</b></span>\n' +
        '                </div>\n' +
        '                <div class="middle" style="margin-top: 20px">\n' +
        '\t\t\t\t<span style="margin-left: 20px">库存数量设置 &nbsp;&nbsp;<input id="inputMin1"  type="text" value="' + (warningShu == undefined ? '' : warningShu) + '" placeholder="最小安全数"\n' +
        '                                                                          onclick="yzInput()" style="width:100px;"/>&nbsp;~&nbsp;\n' +
        '\t\t\t\t<input id="inputMax1" type="text" value="' + (maxShu == undefined ? '' : maxShu) + '" placeholder="最大安全数" onclick="yzInput()" style="width:100px;"/>\n' +
        '\t\t\t\t</span>\n' +
        '                </div>\n' +
        '                <div id="yz2" style="width: 200px;height: 13px;margin-left:20px;display: none"><span\n' +
        '                        style="font-size: 10px;color: red">请输入正确的数字</span></div>\n' +
        '                <div id="yz3" style="width: 200px;height: 13px;margin-left:20px;display: none"><span\n' +
        '                        style="font-size: 10px;color: red">最小安全数不能大于最大安全数</span></div>\n' +
        '                <div class="middle" style="margin-top: 20px">\n' +
        '\t\t\t\t<span style="margin-left: 20px">预警天数 &nbsp;&nbsp;<input id="inputDay1" value="' + (warningDay == undefined ? '' : warningDay) + '" type="text" placeholder=""\n' +
        '                                                                          onclick="" style="width:100px;"/>&nbsp;天\n' +
        '\t\t\t\t</span>\n' +
        '                </div>\n' +
        '                <div style="margin-top: 20px">\n' +
        // '                    <span style="float: right"><button type="button" onclick="onclickSafetyOne(' + mieId + ')" class="btn btn-primary">设置</button></span>\n' +
        '                </div></form>\n' +
        '            </div>';
    layer.open({
        title: title,
        area: '500px',
        content: str,//$('#supplier-address'),
        closeBtn: 2,
        btn: ['设置', '取消'],
        yes: function (index, layero) {
            console.log("111")
            let inputMin = $('#inputMin1').val();
            let inputMax = $('#inputMax1').val();
            let inputDay = $('#inputDay1').val();
            // if (inputMax == '' && inputMin == '' && inputDay == '') {
            //     layer.tips('未填写数据', $('#safetyhide'));
            //     return;
            // }

            // if (inputDay == '') {
            //     layer.tips('未填写数据', $('#safetyhide'));
            //     return;
            // }
            // if (inputMin == "" || inputMin == null) {
            //     inputMin = -1;
            // }
            // if (inputMax == "" || inputMax == null) {
            //     inputMax = -1;
            // }
            var pattern = /^[0-9]+(.[0-9]{0,3})?$/;
            // if (!pattern.test(inputMin) || !pattern.test(inputMax)) {
            //     $("#yz2").show();
            //     return;
            // }
            // if (inputMin > inputMax) {
            //     $("#yz3").show();
            //     return;
            // }
            // let data = $('#warningForm').serialize();
            let data = '';
            data += '&inputDay=' + inputDay + '&inputMin=' + inputMin + '&inputMax=' + inputMax;
            data += '&wiId=' + wiId;
            $.supper("doservice", {
                "service": "MdInventoryService.saveSafetyNewByWiId", "data": data, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $('#warningShu' + wiId).text(inputMin);
                        $('#maxShu' + wiId).text(inputMax);
                        $('#warningDay' + wiId).text(inputDay);
                        $.supper("alert",{ title:"操作提示", msg:"设置成功!"});
                        layer.close(index);
                        // main_search();

                    } else if (jsondata.code == '3') {
                        $.supper("alert", {title: "操作提示", msg: "未找到数据！"});
                    } else
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                }
            });
        },
        btn2: function (index, layero) {
            // main_search();
            layer.close(index);
        }
    })
}

//获取单选框的值 ****start******
var checkValue="";
var checkValueCount =  1;
function checkboxMatName(checkboxNameValue) {
    if(checkValueCount%2!=0){
        checkValue+=checkboxNameValue;
        checkValueCount++;
    }else if (checkValueCount%2==0){
        checkValue="";
        checkValueCount++;
    }
}
var checkValue2="";
var checkValueNormCount =  1;
function checkboxNorm(checkboxNormValue) {
    if(checkValueNormCount%2!=0){
        checkValue2+=checkboxNormValue;
        checkValueNormCount++;
    }else if (checkValueNormCount%2==0){
        checkValue2="";
        checkValueNormCount++;
    }
}

function onclickSafetyOne(mieId) {

}

function onclickSafety() {
    var select1 = $("#select1").val();
    var select2 = $("#select2").val();

    if (select1 == '' && select2 == '') {
        $.supper("alert", {title: "操作提示", msg: "未选择规格或者名称！"});
        return
    }

    var input1 = $("#input1").val();
    var input2 = $("#input2").val();
    //获取文本框的值
    var inputMin = $("#inputMin").val();
    var inputMax = $("#inputMax").val();
    var inputDay = $('#inputDay').val();
    if (input1 == '' && input2 == '' && inputMax == '' && inputMin == '' && inputDay == '') {
        layer.tips('未填写数据', $('#safeBox'));
        return;
    }
    if (inputMin == "" || inputMin == null) {
        inputMin = -1;
    }
    if (inputMax == "" || inputMax == null) {
        inputMax = -1;
    }
    var pattern = /^[0-9]+(.[0-9]{0,3})?$/;
    if (!pattern.test(inputMin) || !pattern.test(inputMax)) {
        $("#yz").show();
        return;
    }
    if (inputMin > inputMax) {
        $("#yz1").show();
        return;
    }
    var vdata = "inputDay=" + inputDay + "&input1=" + input1 + "&input2=" + input2 + "&" + "inputMin=" + inputMin + "&" + "inputMax=" + inputMax + "&" + "checkValue=" + checkValue + "&" + "checkValue2=" + checkValue2 + "&" + "select1=" + select1 + "&" + "select2=" + select2;
    $.supper("doservice", {
        "service": "MdInventoryService.saveSafetyNew", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                if (jsondata == "" && jsondata == null && !jsondata.equals("")) {
                }
                $.supper("alert", {title: "操作提示", msg: "操作成功！"});
                wmsIdStrs = "";
                main_search();
            } else if (jsondata.code == '3'){
                $.supper("alert", {title: "操作提示", msg: "未选择规格或者名称！"});
            }else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

// let openSafeBox = false;
function hideSafeBox() {
    // if (openSafeBox == false)
    //     return;
    // openSafeBox = false;
    $('#safeBox').hide();
    $("#yz").hide();
    $("#yz1").hide();
    $('#saveForm input').val('');
    $('#saveForm select').val('1');
    $('#saveForm input[type=checkbox]').prop('checked', false);
}
function yzInput(){
    $("#yz").hide();
    $("#yz1").hide();
}
function doNotHide() {
    // openSafeBox = false;
}

function openYJbox() {
    // setTimeout(function () {
    //     openSafeBox = !openSafeBox;
    // }, 200);
    var aitem = document.getElementById("safeBox");
    if (aitem.style.display == "block") {
        aitem.style.display = "none";
        hideSafeBox();
    } else {
        aitem.style.display = "block";
    }
}


function inventoryWarn(checked) {
    _inventoryWarn = checked;
    main_search();
}
function validateWarn(checked) {
    _validateWarn = checked;
    main_search();
}
function zeroWarn(checked) {
    _zeroWarn = checked;
    main_search();
}
var _warning;
$(function () {
    var selOutOrderTypes = $.supper("getProductArray", "shouldClose");
    if (selOutOrderTypes != null && selOutOrderTypes.searchName1 != null) {
        console.log("上一个页面带过来的参数"+selOutOrderTypes.searchName1)
        let allClaomantes = selOutOrderTypes.searchName1;
        $("#searchName").val(allClaomantes);
        // main_search()
        // searchAll();*/
        $.supper("setProductArray", {"name": "shouldClose", "value": null});
    }
    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if (selOutOrderType != null) {
        console.log("上一个页面带过来的参数二")
        let warning = selOutOrderType.warning;
        _warning = warning;
        $.supper("setProductArray", {"name": "selOutOrderType", "value": null});
    }
    _mdpId = $.supper("getParam", 'mdpId');
    _mdpsId = $.supper("getParam", 'mdpsId');

    $("#tree").css("height", $(window).height() - 165);
    loadItemZtree();
    // initTree();
    initDataGrid();
    if (_warning != undefined && _warning == true) {
        $('#warning').click();
    }
    // initOneUploadImg("lessenFilecode","lessenFileDiv");
    // initListUploadImg("listFilecode","imglist", null, {limit: 1, accept: 'image/png,image/jpg,image/jpeg,image/gif', acceptType: 'image', size: 3 * 1024/*KB*/});
    //
    // laydate({
    //     elem: '#cTime_start',
    //     format: 'YYYY-MM-DD'//日期格式
    // });
    // laydate({
    //     elem: '#cTime_end',
    //     format: 'YYYY-MM-DD' //日期格式
    // });

    //设置按钮宽度与图标随文字居中
    $("#win_but_search").css("width", "95px");
    $("#win_but_search").css("vertical-align", "middle");
    $("#win_but_export").css("width", "95px");
    $("#win_but_export").css("vertical-align", "middle");
    $("#win_but_export1").css("width", "95px");
    $("#win_but_export1").css("vertical-align", "middle");

    $("#safetyEwBtn").html($("#safetyEw").html())
    $("#safetyEwBtn").css("width", "120px");
    $("#safetyEwBtn").css("vertical-align", "middle");
    sumPrice();
})
// $(document).bind("click", function(e) {
//     var target = $(e.target);
//     if (target.closest("#safetyhide").length == 0&&target.closest("#safetyEwBtn").length == 0) {
//         $("#safetyhide").hide();
//     }
// })
function exportExcel() {

}
//增加安全预警设置点击空白部分隐藏
$(document).bind("click", function(e) {
    var target = $(e.target);
    if (target.closest("#safeBox").length == 0&&target.closest("#safeBox").length == 0) {
        if (target.closest("#safetyEwBtn").length == 0&&target.closest("#safetyEwBtn").length == 0) {
            if ($("#safeBox").css("display") != "none") {
                $("#safeBox").hide();
            }
        }
    }
})