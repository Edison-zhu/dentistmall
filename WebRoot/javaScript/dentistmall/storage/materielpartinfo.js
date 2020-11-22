let _mdpId;
let _mdpsId;
let _mdpIdname;
let _mdpsIdname;
let _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
let _all_SiteSecond = 'MdMaterielPartSecondService.getSecondObject';
var _all_queryAction = "MdMaterielInfoService.getPagerModelObject";
var _all_deleteAction = "MdMaterielInfoService.deleteObjectInventory";
var _all_deleteAllAction = "MdMaterielInfoService.deleteAllObjectInventory";
var _all_table_Id = "wmsMiId";
var _all_edit_icon = "gears";
var _all_edit_title = "物料信息维护";
var _all_edit_width = 800;
var _all_edit_height = 400;
var _all_datagrid_height;
var mmg;
var lastExpandNode;
var queryAction = "mdMaterielInfoService.getPagerModelObject";
var _dblClick = function (data, row, col) {
    addMaterielDetail(data.wmsMiId);
}
var initDataGrid = function () {
    var cols = [
        {title: '物料编号', name: 'matCode', sortable: true, width: 100, align: 'center'},
        {title: '物料名称', name: 'matName', sortable: true, width: 100, align: 'center'},
        {title: '物料图标', name: 'logo', width: 50, align: 'center', renderer: renderLogo},
        {title: '物料规格', name: 'norm', sortable: true, width: 80, align: 'center', renderer: renderNorm},
        {title: '品牌', sortable: true,name: 'brand', width: 10, align: 'center'},
        {title: '单位', sortable: true, name: 'basicUnit', width: 10, align: 'center', renderer: renderUnit},
        {
            title: '生产厂家/场地',
            sortable: true,
            name: 'applicantNamePlace',
            width: 20,
            align: 'center',
            renderer: renderProduct
        },
        {title: '状态', sortable: true, name: 'state', width: 40, align: 'center', renderer: formatState},
        {title: '日志', sortable: true, name: 'log', width: 5, align: 'center', renderer: renderLog},
        {title: '操作', sortable: true, name: 'mddCon', width: 100, align: 'center', renderer: control}
    ];
    var att_mmgurl = getUrl();
    mmg = $('#datagrid1').mmGrid({
        height: 'auto'
        , cols: cols
        , method: 'get'
        , remoteSort: false
        , url: att_mmgurl
        // , sortName: 'SECUCODE'
        // , sortStatus: 'asc'
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
}

function renderUnit(val, item, rowIndex) {
    let basicUnit = item.basicUnit == undefined ? '' : item.basicUnit;
    let unit = item.splitUnit == undefined ? '' : item.splitUnit;
    return basicUnit + '/' + unit;
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
                console.log("树的等级"+treeNode.level)
                _mdpId = undefined;
                _mdpIdname = undefined;
                _mdpsId = undefined;
                _mdpsIdname = undefined;

                if (treeNode.level == 1) {
                    _mdpId = treeNode.id;
                    _mdpIdname = treeNode.name;
                    _mdpsId = undefined;
                    _mdpsIdname = undefined;
                } else if(treeNode.level == 2){
                    var data1 = treeNode.getParentNode();
                    _mdpId = treeNode.pId;
                    _mdpIdname = data1.name;
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
        if (childNodes[i].id == _mdpId)
        _mdpIdname = childNodes[i].name;
        
        if ( childNodes[i].nodes != undefined && childNodes[i].nodes.length > 0 && childNodes[i].nodes[0].id == _mdpsId)
            _mdpsIdname = childNodes[i].nodes[0].name;
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

function getUrl(excludePart) {
    var att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
    if (excludePart == undefined) {
        if (_mdpId != undefined) {
            console.log(_mdpId)
            att_mmgurl += '&mdpId=' + _mdpId;
        } else if ($('#mdpId').val() != '') {
            att_mmgurl += '&mdpId=' + $('#mdpId').val();
        }
        if (_mdpsId != undefined) {
            console.log(_mdpsId)
            att_mmgurl += '&mdpsId=' + _mdpsId;
        } else if ($('#mdpsId').val() != '') {
            att_mmgurl += '&mdpsId=' + $('#mdpsId').val();
        }
    }
    return att_mmgurl;
}

function search(excludePart) {
    let att_mmgurl = getUrl(excludePart);
    mmg.opts.url = att_mmgurl;
    mmg.load();

}
function toDelete(id) {
    eval("var vdata= 'wmsMiId=" + id + "'");
    $.supper("confirm", {
        title: "删除操作", msg: "确认删除记录操作？", yesE: function () {
            $.supper("doservice", {
                "service": _all_deleteAction, "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: search});
                    } else
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                }
            });
        }
    });
}

function renderLogo(val, item, rowIdex) {
    let str = '';
    if (item.lessenFilePath == undefined)
        str = '';
    else
        str += '<img src="' + item.lessenFilePath + '" style="width: 40px; height: 40px;" />';
    return str;
}

function renderNorm(val, item, rowIndex) {
    if (item.normList.length <= 0) {
        return '无';
    }
    let str = '';
    let normList = item.normList;
    let norm = '';
    let index = 0;
    for (let idx in normList) {
        norm += normList[idx].mmfName + ',';
        if (index > normList.length) {
            break;
        }
        index ++;
    }
    if (norm != '') {
        norm = norm.substring(0, norm.length - 1);
    }
    str += norm;
    let s = '<input id="norm' + item.wmsMiId + '" onblur="savemmfName1(' + item.wmsMiId + ')" value="' + str + '" style="width: 100px"/>'
    // str += '<img onclick="viewMaterielNorm(' + item.wmsMiId + ')" style="width: 25px;" src="/dentistmall/img/edit_norm.png" />';
    // str += '<a onclick="viewMaterielNorm(' + item.wmsMiId + ')">查看</a>';
    return s;
}

var _all_saveNormAction1 = "MdMaterielFormatService.saveOrUpdateObject1";
function savemmfName1(wmsMiId) {
    if (wmsMiId == undefined) {
        return;
    }
    let mmfName = $('#norm' + wmsMiId).val();

    let data = 'wmsMiId=' + wmsMiId + '&mmfName=' + mmfName;
    $.supper("doservice", {
        "service": _all_saveNormAction1, "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // setTimeout(function () {
                //     closeWin();
                // }, 200);
                // $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: "存在相同的规格编号！"});
            }  else if (jsondata.code == '3') {
                $.supper("alert", {title: "操作提示", msg: "存在相同的规格名称！"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function renderProduct(val, item, rowIndex) {
    let str = '';
    str += '<span>' + (item.productFactory == undefined ? '' : item.productFactory) + '</span>/<span>' + (item.productPlace == undefined ? '' : item.productPlace) + '</span>';
    return str;
}

function formatState(val, item, rowIndex) {
    let str = '';
    if (item.state == 1) {
        str += '<div class="switch-container" style="display: inline-block;"><input id="stateInp' + item.wmsMiId + '" type="checkbox" checked class="switch" /><label for="state' + item.wmsMiId + '" onclick="changeState(' + item.wmsMiId + ')" style="margin-bottom: 0px;margin-top: 2px;"></label></div><div id="sstips' + item.wmsMiId + '" style="display: inline-block;margin-left: 10px;">启用</div>';
    } else {
        str += '<div class="switch-container" style="display: inline-block;"><input id="stateInp' + item.wmsMiId + '" type="checkbox" class="switch" /><label for="state' + item.wmsMiId + '" onclick="changeState(' + item.wmsMiId + ')" style="margin-bottom: 0px;margin-top: 2px;"></label></div><div id="sstips' + item.wmsMiId + '" style="display: inline-block;margin-left: 10px;">停用</div>';
    }
    return str;
}

function changeState(wmsMiId) {
    let state = $('#stateInp' + wmsMiId).prop('checked') == false ? 1 : 2;
    let data = 'state=' + state + '&wmsMiId=' + wmsMiId;
    $.supper("doservice", {
        "service": "MdMaterielInfoService.updateObjectState", "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                if (state == 1) {
                    $('#stateInp' + wmsMiId).prop('checked', true);
                    $('#sstips' + wmsMiId).html('启用');
                } else {
                    $('#stateInp' + wmsMiId).prop('checked', false);
                    // $.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
                    $('#sstips' + wmsMiId).html('停用');
                }
            }
        }
    });
}

function renderLog(val, item, rowIndex) {
    let str = '';
    str += '<img onclick="toDetailLog(' + item.wmsMiId + ')" style="width: 25px;" src="/dentistmall/img/format_log.png" />';
    // str += '<a onclick="toDetailLog(' + item.wmsMiId + ')">日志</a>';
    return str;
}

function toDetailLog(wmsMiId) {
    var data = "wmsMiId=" + wmsMiId;
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
        url: "/jsp/dentistmall/storage/vieMaterielPartDetailLog.jsp"
    });
    var tt_win = $.supper("showWin", {
        url: att_url, title: "操作日志", icon: "fa-th-list", width: 800, height: 500
    });
}

function control(val, item, rowIndex) {
    return "<a onclick=\"addMaterielDetail('" + item.wmsMiId + "')\" class='btn btn-info btn-sm'>查看</a> &nbsp;" +
        "<a onclick=\"addMaterielDetail('" + item.wmsMiId + "', true)\" class='btn btn-info btn-sm'>编辑</a> &nbsp;" +
        "<a onclick=\"toDelete('" + item.wmsMiId + "')\" class='btn btn-danger btn-sm'>删除</a>";
}

function addMaterielDetail(wmsMiId, isEdit) {
    var data = '';
    let title = '';
    if (wmsMiId != undefined) {
        data += 'wmsMiId=' + wmsMiId;
        title = '物料详情查看';
    }
    if (isEdit != undefined) {
        data += '&isEdit=true';
        title = '物料详情编辑';
    }
    var att_url = $.supper("getServicePath", {
        "service": "MdMaterielInfoService.findObject",
        "data": data,
        url: "/jsp/dentistmall/storage/mdmaterielpartDetail"
    });
    $.supper("setProductArray", {"name": "mdMaterielPartDetail", "value": {url: att_url}});
    $.supper("showTtemWin", {
        url: att_url,
        title: title,
    });
    // $.supper("showTtemWin", {
    //     url: att_url,
    //     title: '物料详情编辑',
    // });
    // var att_url = $.supper("getServicePath", {
    //     "service": "MdMaterielInfoService.findObject",
    //     "data": data,
    //     url: "/jsp/dentistmall/storage/mdmaterielpartDetail"
    // });
    // $.supper("setProductArray", {"name": "mdMaterielPartDetail", "value": {url: att_url}});
    // $.supper("showTtemWin", {
    //     url: att_url,
    //     title: '物料详情查看',
    // });
}

function main_add() {
    let str = "var data= 'isPart=true&mdpId=" + _mdpId +"&mdpIdnameee="+_mdpIdname;
    if (_mdpsId != undefined) {
        str += "&mdpsId=" + _mdpsId +"&mdpsIdnameee="+_mdpsIdname;
    }
    str += "'";
    eval(str);
    var att_url = $.supper("getServicePath", {"data": data, url: "/jsp/dentistmall/storage/mdmaterielpartDetail"});
    $.supper("setProductArray", {"name": "mdMaterielPartDetail", "value": {url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": _all_edit_title});
}

/**
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function main_edit(id) {
    eval("var data= 'wmsMiId=" + id + "'");
    var att_url = $.supper("getServicePath", {"data": data, url: "/jsp/dentistmall/storage/mdmaterielpartDetail"});
    $.supper("setProductArray", {"name": "mdMaterielPartDetail", "value": {url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": _all_edit_title});
}


function viewMaterielNorm(wmsMiId) {
    eval("var data='wmsMiId=" + wmsMiId + "'");
    // var data = "wmsMiId=" + wmsMiId;
    var att_url = $.supper("getServicePath", {"data": data, url: "/jsp/dentistmall/storage/viewmaterielnormlist.jsp"});
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: "规格型号",
        icon: "fa-th-list",
        width: 1000, height: 800
    });
}

function formatDate(date, fmt) {
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function selectDate(dateIndex) {
    let date = new Date();
    let renderDate = formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    let range = false;
    switch (dateIndex) {
        case 1:
            date.setDate(date.getDate() - 1);
            range = true;
            break;
        case 2:
            date.setDate(date.getDate() - 2);
            range = true;
            break;
        case 3:
            date.setDate(date.getDate() - 6);
            range = true;
            break;
        case 4:
            date.setDate(date.getDate() - 13);
            range = true;
            break;
        case 5:
            date.setDate(date.getDate() - 20);
            range = true;
            break;
        case 6:
            date.setDate(date.getDate() - 29);
            range = true;
            break;
        case 7:
            date.setDate(date.getDate() - 59);
            range = true;
            break;
    }
    if (range == true) {
        renderDate = formatDate(date, 'yyyy-MM-dd hh:mm:ss') + '~' + renderDate;
    }
    $('#cTime_start').val(renderDate);
    $('#selectDateDiv').hide();
}

function selectRangeDate() {
    $('#selectDateDiv').show();
}

function closeSelect() {
    $('#selectDateDiv').hide();
}

function showLoading() {

    var inntHtml = '';
    inntHtml += '<div id="loading-mask"  class="u-shade" style="z-index:9098910159999;display:block ">';
    inntHtml += '<img src="/dentistmall/img/loading.gif" alt=""></div>';

    // if (top == self) {
    $(this.document.body).append(inntHtml);
    $('#loading-mask').fadeIn('fast');
    // 是顶级窗口
    // } else {
    //     // 不是顶级窗口
    //     return top.showLoading();
    // }
}

function closeLoading(){
    // if (top == self) {
    $('#loading-mask').fadeOut('fast');
    $('#loading-mask').remove();
    // 是顶级窗口
    // } else {
    //     // 不是顶级窗口
    //     return top.closeLoading();
    // }

}

// 移动分类
function moveToNewPart() {
    let rows = mmg.selectedRows();
    if (rows.length <= 0) {
        $.supper("alert", {title: "操作提示", msg: "请选择数据！"});
        return;
    }
    let wmsMiIds = '';
    for (let idx = 0; idx < rows.length; idx ++) {
        wmsMiIds += rows[idx].wmsMiId + ',';
    }
    if (wmsMiIds == '') {
        $.supper("alert", {title: "操作提示", msg: "请选择数据！"});
        return;
    }
    wmsMiIds = wmsMiIds.substring(0, wmsMiIds.length - 1)
    var att_url = $.supper("getServicePath", {url: "/jsp/dentistmall/storage/selMdMateriePart.jsp"});
    var tt_win = $.supper("showWin", {
        url: att_url, title: "选择分类", icon: "fa-th-list", width: 500, height: 800, BackE: function () {
            var selMdMaterielType = $.supper("getProductArray", "selPart");
            if (selMdMaterielType != null && selMdMaterielType.id != null) {
                let sid = selMdMaterielType.sid;
                let id = selMdMaterielType.id;
                $.supper("setProductArray", {"name": "selPart", "value": null});
                let data = 'wmsMiIds=' + wmsMiIds;
                if (sid != undefined)
                    data += '&mdpId=' + sid;
                if (id != undefined)
                    data += '&mdpsId=' + id;
                showLoading();
                $.supper("doservice", {
                    "service": 'mdMaterielInfoService.updateMaterielPart', "data": data, "BackE": function (jsondata) {
                        closeLoading();
                        if (jsondata.code == "1") {
                            $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: search});
                        } else
                            $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                    }
                });
            }
        }
    });
}

$(function () {
    _mdpId = $.supper("getParam", 'mdpId');
    _mdpsId = $.supper("getParam", 'mdpsId');
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

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#rangeDate'
            , type: 'datetime'
            , range: "~" //或 range: '~' 来自定义分割字符
            , position: 'static'
            , theme: 'grid'
            , done: function (value, date) {
                $('#cTime_start').val(value)
                $('#selectDateDiv').hide();
                $('#cTime_start').focus();
            }
        });
    });
})

function exportExcel() {
    var selectedRows = mmg.selectedRows();
    var wmsMiIds='';
    for(var i=0;i<selectedRows.length;i++){
        wmsMiIds+=selectedRows[i].wmsMiId+',';
    }
    var wmsMiId1=wmsMiIds.substring(0,wmsMiIds.length-1);
    if (wmsMiId1==null||wmsMiId1==''||wmsMiId1==undefined) {
        $.supper("confirm", {
            title: "操作提示", msg: "您未选择要导出的数据，系统将所有数据导出是否确定导出？", yesE: function () {
                var vdata='wmsMiIds='+wmsMiId1;
                var newTab=window.open('about:blank');
//重新提交
                $.supper("doservice", {"service":"EnterWarehouseExportService.exportWarehousingListMx","data":vdata, "BackE":function (jsondata) {
                        if (jsondata.code == "1") {
                            newTab.location.href=jsondata.obj.path;
                        }else
                            $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
                    }});
            }
        });
        return;
    }
    var vdata='wmsMiIds='+wmsMiId1;
    var newTab=window.open('about:blank');
//重新提交
    $.supper("doservice", {"service":"EnterWarehouseExportService.exportWarehousingListMx","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}