var _all_win_tools_but = $("#win_tools_but");

let _all_queryAction = 'mdMaterielPartService.getPagerModelObject';
let _all_saveAction = 'mdMaterielPartService.saveObject';
let _all_updateAction = 'mdMaterielPartService.saveOrUpdateObject';
let _all_deleteAction = 'mdMaterielPartService.deleteObject';
let _all_deleteAllAction = 'mdMaterielPartService.deleteAllObject';

let _all_updateNeedShow = 'mdMaterielPartService.updateMaterielPartNeedShow';

let _all_updateSeqAction = 'mdMaterielPartService.updateMaterielPartSeq';
let _all_updateSeqBatchAction = 'mdMaterielPartService.updateMaterielPartSeqBatch';

let _all_querySecondAction = 'mdMaterielPartSecondService.getPagerModelObject';
let _all_saveSecondAction = 'mdMaterielPartSecondService.saveObject';
let _all_updateSecondAction = 'mdMaterielPartSecondService.saveOrUpdateObject';
let _all_deleteSecondAction = 'mdMaterielPartSecondService.deleteObject';
let _all_deleteSecondAllAction = 'mdMaterielPartSecondService.deleteAllObject';

let _all_updateSecondSeqAction = 'mdMaterielPartSecondService.updateMaterielPartSecondSeq';
let _all_updateSecondSeqBatchAction = 'mdMaterielPartSecondService.updateMaterielPartSecondSeqBatch';

let viewInfo = '/jsp/dentistmall/storage/materielpartinfo.jsp';

let _all_limit_number = 10;

var _Toolbar = {
    toolBarId: "tools_but",
    items: [
        {
            title: "&nbsp;刷新&nbsp;",
            id: "win_but_search",
            icon: "search",
            colourStyle: "default",
            rounded: true,
            clickE: main_search
        },
    ]
};

function initPage() {
    let data = 'orderStr=seq desc';
    $.supper('initPagination', {
        id: "Pagination",
        data: data,
        service: _all_queryAction,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initPartFirst
    });
}

var mdpIdList = [];
var mdpsIdList = {};

var checkMdpId = [];
var checkMdpsId = [];

var needOpenMdpId = [];

// 渲染一级分类
function initPartFirst(dataList) {
    var str = "";
    let data;
    for (let idx in dataList) {
        data = dataList[idx];
        if (mdpIdList.indexOf(data.mdpId) < 0) {
            mdpIdList.push(data.mdpId);
            if (mdpsIdList[data.mdpId] == undefined) {
                mdpsIdList[data.mdpId] = [];
            }
        }
        if (data.seq == -1) {
            str = renderFirtsList(data, idx, dataList.length) + str;
        } else {
            str += renderFirtsList(data, idx, dataList.length);
        }
    }
    if (str == '') {
        // str = '<div style="text-align: center"><span>未找到数据</span></div>';
    }
    $("#content").html(str);

    for (let idx in needOpenMdpId) {
        showOrHideSecond(needOpenMdpId[idx], true);
    }
}

function addCheckMdpId(mdpId, checked) {
    let index = checkMdpId.indexOf(mdpId);
    if (checked == true) {
        if (index < 0) {
            checkMdpId.push(mdpId);
        }
    } else {
        if (index >= 0) {
            checkMdpId.splice(index, 1);
        }
    }
}

function renderFirtsList(data, idx, length) {
    let str = '';
    str += '<div id="mdp' + data.mdpId + '"><div class="flex border-bottom">';
    if(data.seq != -1){
        str += '<div class="flex-1 border-right" style="padding: 0 10px;width: 75px;"><div class="paddingTopBottom" style="display: flex;justify-content: center;"><input name="mdpfs" id="mdpf' + data.mdpId + '" data-mdpid="' + data.mdpId + '" type="checkbox" onclick="addCheckMdpId(' + data.mdpId + ', this.checked)" style="margin-top: 0px;"/>' +
            '<div style="margin-left: 10px;width: 30px;">';
    }else {
        str += '<div class="flex-1 border-right" style="padding: 0 10px;width: 75px;"><div class="paddingTopBottom" style="display: flex;justify-content: center;"><input type="checkbox" onclick="return false;"  style="margin-top: 0px;"/>' +
            '<div style="margin-left: 10px;width: 30px;">';
    }
    if (data.needShow != undefined && data.needShow == 1) {
        str += '  <img style="width: 30px;height:16px;" src="/dentistmall/img/xiala.png" id="simg' + data.mdpId + '"  onclick="showOrHideSecond2(' + data.mdpId + ')"/>';
        needOpenMdpId.push(data.mdpId);
    }else{
        str += '  <img style="width: 30px;height:16px;" src="/dentistmall/img/xiala2.png" id="simg' + data.mdpId + '"  onclick="showOrHideSecond2(' + data.mdpId + ')"/>';
        // needOpenMdpId.push(data.mdpId);
    }
    str += '</div></div></div>';
    str += '<div class="flex-4 border-right"><div class="paddingTopBottom" style="margin-left: 10px;"><span>' + (data.partCode == undefined ? '' : (data.partCode + ' ')) + data.mdpName + '</span></div></div>';

    str += '<div id="upDownContainer' + data.mdpId + '" class="flex-2 border-right text-align">';
    str += '<div class="paddingTopBottom"><div id="upDown' + data.mdpId + '">';
    if (length > 1 && data.seq > -1) {
        str += renderUpDown(data, idx - 1, length - 1);
    }
    str += '</div></div>';
    str += '</div>';

    str += '<div class="flex-2 border-right text-align">' +
        '<div class="paddingTopBottom"><div class="switch-container"><input id="switch' + data.mdpId + '" type="checkbox" class="switch"';
    if (data.needShow != undefined && data.needShow == 1) {
        str += ' checked';
        needOpenMdpId.push(data.mdpId);
    }
    str += ' />' +
        '<label id="swtichLabel' + data.mdpId + '" for="switch' + data.mdpId + '" onclick="showOrHideSecond(' + data.mdpId + ')"></label></div>' +
        // '<div class="layui-form"><input type="checkbox" name="needShow" lay-skin="switch"></div>' +
        // '<a href="javascript:showOrHideSecond(' + data.needShow + ', ' + data.mdpId + ')">' + data.needShow + '</a>' +
        // '<form class="layui-form"><div class="layui-form-item" pane>' +
        // '<div class="layui-input-inline">' +
        // '<input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" title="开关">' +
        // '</div></div></form>' +
        '</div></div>';
    str += '<div class="flex-3  text-align">' +
        '<div class="paddingTopBottom"><a class="btn btn-info btn-xs" href="javascript:addSecondPart(' + data.mdpId + ')">添加二级分类</a>&nbsp;&nbsp;' +
        '<a class="btn btn-info btn-xs" href="javascript:viewMaterielPart(' + data.mdpId + ')" style="width: 60px">查看</a>&nbsp;&nbsp';
    if (data.seq != -1)
        str += '<a class="btn btn-danger btn-xs" href="javascript:deleteMaterielPart(' + data.mdpId + ')" style="width: 60px">删除</a>&nbsp;&nbsp;';
    str += '</div></div>';
    str += '<div id="hidden' + data.mdpId + '" style="display: none;"></div>';
    str += '</div>';
    str += '<div id="secondPart' + data.mdpId + '" style="display: none"><div id="secondShow' + data.mdpId + '"></div></div></div>';
    return str;
}

function renderUpDown(data, idx, length) {
    let str = '';
    let seq = data.seq;
    let mdpId = data.mdpId;
    str = renderStr(mdpId, idx, length);
    return str;
}

function renderSecondUpDown(data, idx, length) {
    let str = '';
    let seq = data.seq;
    let mdpsId = data.mdpsId;
    let mdpId = data.mdpId;
    str = renderSecondStr(mdpsId, idx, length, mdpId);
    return str;
}

// &#8679; 向上
// &#8681; 向下
function renderStr(id, idx, length) {
    let str = '';
    if (idx == 0) {
        str += '<a class="upDown" onclick="jumpTo(' + id + ', 1)" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/down.png" /></a><a class="upDown" onclick="jumpTo(' + id + ', 3)" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/downall.png" /></a>';
    } else if (idx == length - 1) {
        str += '<a class="upDown" onclick="jumpTo(' + id + ', 2)" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/upall.png" /></a><a class="upDown" onclick="jumpTo(' + id + ', 0)" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/up.png" /></a>';
    } else {
        str += '<a class="upDown" onclick="jumpTo(' + id + ', 1)" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/down.png" /></a><a class="upDown" onclick="jumpTo(' + id + ', 3)" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/downall.png" /></a>';
        str += '<a class="upDown" onclick="jumpTo(' + id + ', 2)" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/upall.png" /></a><a class="upDown" onclick="jumpTo(' + id + ', 0)" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/up.png" /></a>';
    }
    return str;
}

function renderSecondStr(id, idx, length, mdpId) {
    let str = '';
    if (idx == 0) {
        str += '<a class="upDown" onclick="jumpSecondTo(' + id + ', 1,' + mdpId + ')" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/down.png" /></a><a class="upDown" onclick="jumpSecondTo(' + id + ', 3,' + mdpId + ')" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/downall.png" /></a>';
    } else if (idx == length - 1) {
        str += '<a class="upDown" onclick="jumpSecondTo(' + id + ', 2,' + mdpId + ')" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/upall.png" /></a><a class="upDown" onclick="jumpSecondTo(' + id + ', 0,' + mdpId + ')" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/up.png" /></a>';
    } else {
        str += '<a class="upDown" onclick="jumpSecondTo(' + id + ', 1,' + mdpId + ')" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/down.png" /></a><a class="upDown" onclick="jumpSecondTo(' + id + ', 3,' + mdpId + ')" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/downall.png" /></a>';
        str += '<a class="upDown" onclick="jumpSecondTo(' + id + ', 2,' + mdpId + ')" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/upall.png" /></a><a class="upDown" onclick="jumpSecondTo(' + id + ', 0,' + mdpId + ')" style="border:none;"><img style="width: 18px;height:16px;" src="/dentistmall/img/up.png" /></a>';
    }
    return str;
}

// 渲染二级分类
function initPartSecond(mdpId) {
    mdpsIdList[mdpId] = [];
    $.supper("doservice", {
        "service": _all_querySecondAction,
        "data": "mdpId=" + mdpId + '&orderStr=seq desc',
        // ifloading: true,
        "BackE": function (jsondata) {
            let items = jsondata.items
            initPartSecondView(mdpId, items);
        }
    });
}

function initPartSecondView(mdpId, obj) {
    let str = '';
    let secondPart;
    let len = obj.length;
    for (let idx in obj) {
        secondPart = obj[idx];
        if (mdpsIdList[mdpId].indexOf(secondPart.mdpsId) < 0) {
            mdpsIdList[mdpId].push(secondPart.mdpsId);
        }
        str += rendererSecondList(secondPart, idx, len, obj);
    }
    if (str == '') {
        if (isSecondAdding == true) {
            initAddSecondPart(true, mdpId);
        } else {
            // str = '<div style="text-align: center"><span>未找到数据</span></div>';
            $('#secondShow' + mdpId).html(str);
        }
    } else {
        $('#secondShow' + mdpId).html(str);
    }

    if (isSecondAdding == true) {
        initAddSecondPart(false, mdpId);
    }
}

function addCheckMdpsId(mdpId, mdpsId, checked) {
    let index = checkMdpsId[mdpId].indexOf(mdpsId);
    if (checked == true) {
        if (index < 0) {
            checkMdpsId.push(mdpsId);
        }
    } else {
        if (index >= 0) {
            checkMdpsId.splice(index, 1);
        }
    }
}

function rendererSecondList(secondPart, idx, len, obj) {
    let str = '';
    str += '<div class="flex border-bottom" id="mdps' + secondPart.mdpsId + '">';
    str += '<div class="flex-1 border-right" style="padding: 0 10px;width: 75px;"><div class="paddingTopBottom"><input type="checkbox" onclick="addCheckMdpsId(' + secondPart.mdpId + ', ' + secondPart.mdpsId + ', this.check)" style="margin-top: 0px;"/><div style="margin-left: 10px;width: 43px;"></div></div></div>';
    str += '<div class="flex-4 border-right"><div class="paddingTopBottom" style="margin-left: 10px;"><span style="float: left;margin-left: 10px;margin-right: 10px;"><img style="width: 35px;height:20px;" src="/dentistmall/img/zhe.png" /></span><span>' + (secondPart.secondPartCode == undefined ? '' : (secondPart.secondPartCode + ' ')) + secondPart.mdpsName + '</span></div></div>';

    str += '<div id="upDownContainerS' + secondPart.mdpsId + '" class="flex-2 border-right text-align">';
    str += '<div class="paddingTopBottom"><div id="upDownS' + secondPart.mdpsId + '">';
    if (len > 1) {
        str += renderSecondUpDown(secondPart, idx, obj.length);
    }
    str += '</div></div>';
    str += '</div>';

    str += '<div class="flex-2 border-right text-align"><div class="paddingTopBottom"></div></div>';

    str += '<div class="flex-3  text-align">' +
        '<div class="paddingTopBottom"><a class="btn btn-info btn-xs" href="javascript:viewMaterielPart(' + secondPart.mdpId + ', ' + secondPart.mdpsId + ')" style="width: 60px">查看</a>&nbsp;&nbsp;' +
        '<a class="btn btn-danger btn-xs" href="javascript:deleteMaterielPartSecond(' + secondPart.mdpId + ', ' + secondPart.mdpsId + ')" style="width: 60px">删除</a></div>' +
        '</div>';
    str += '</div>';
    return str;
}

function showOrHideSecond(mdpId, needInit) {
    if (needInit != undefined && needInit == true) {
        $('#secondPart' + mdpId).slideDown();
        initPartSecond(mdpId);
        return;
    }  

    if ($('#switch' + mdpId).prop('checked') === true) {
        // $('#switch' + mdpId).prop('checked', false)
        $('#secondPart' + mdpId).slideUp();
        if (isSecondAdding == true) {
            cancelSecondAdd();
        }
        updateNeedShow(2, mdpId);
        $('#simg' + mdpId).prop('src', '/dentistmall/img/xiala2.png')
    } else{
        // $('#switch' + mdpId).prop('checked', true)
        $('#secondPart' + mdpId).slideDown();
        initPartSecond(mdpId);
        updateNeedShow(1, mdpId);

        $('#simg' + mdpId).prop('src', '/dentistmall/img/xiala.png')
    }
}
function showOrHideSecond2(mdpId, needInit) {
    if (needInit != undefined && needInit == true) {
        $('#secondPart' + mdpId).slideDown();
        initPartSecond(mdpId);
        return;
    }  

    if($('#switch' + mdpId).prop('checked') === true) {
        $('#secondPart' + mdpId).slideUp();
        if (isSecondAdding == true) {
            cancelSecondAdd();
        }
        updateNeedShow(2, mdpId);
    
        $('#simg' + mdpId).prop('src', '/dentistmall/img/xiala2.png')
        $('#switch' + mdpId).prop('checked', false)

    }else{
        $('#secondPart' + mdpId).slideDown();
        initPartSecond(mdpId);
        updateNeedShow(1, mdpId);
        $('#simg' + mdpId).prop('src', '/dentistmall/img/xiala.png')
        $('#switch' + mdpId).prop('checked', true)
    }
}

function updateNeedShow(needShow, mdpId) {
    $.supper("doservice", {
        "service": _all_updateNeedShow,
        "data": "needShow=" + needShow + '&mdpId=' + mdpId,
        ifloading: true,
        "BackE": function (jsondata) {

        }
    });
}

function swapArray(arrayData, idxBefore, idxAfter, upDown) {
    let data = arrayData[idxBefore];
    let deleteIndex = idxBefore;
    if (idxAfter == 0) {
        arrayData.splice(0, 0, data);
        deleteIndex += 1;
    } else if (idxAfter == arrayData.length) {
        arrayData.splice(arrayData.length, 0, data);
    } else {
        if (upDown == 0) {
            arrayData.splice(idxAfter, 0, data);
            deleteIndex += 1;
        } else {
            arrayData.splice(idxAfter + 1, 0, data);
        }
    }
    arrayData.splice(deleteIndex, 1);
    return arrayData;
}

function jumpTo(mdpId, upDown) {
    let index = mdpIdList.indexOf(mdpId);
    let service = _all_updateSeqAction;
    let data = "mdpIdBefore=" + mdpId + '&upDown=' + upDown;
    if (upDown >= 2) {
        service = _all_updateSeqBatchAction;
        if (upDown == 2) {
            data += '&length=' + index;
        }
        if (upDown == 3) {
            data += '&length=' + (mdpIdList.length - index);
        }
    }
    $.supper("doservice", {
        "service": service, "data": data, ifloading: true, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                main_search()
                // switch (upDown) {
                //     case 0: //向上
                //         $('#mdp' + mdpId).insertBefore($('#mdp' + mdpIdList[index - 1]));
                //         mdpIdList = swapArray(mdpIdList, index, index - 1, upDown);
                //         break;
                //     case 1: //向下
                //         $('#mdp' + mdpId).insertAfter($('#mdp' + mdpIdList[index + 1]));
                //         mdpIdList = swapArray(mdpIdList, index, index + 1, upDown);
                //         break;
                //     case 2: //置顶
                //         $('#mdp' + mdpId).insertBefore($('#mdp' + mdpIdList[0]));
                //         mdpIdList = swapArray(mdpIdList, index, 0);
                //         break;
                //     case 3: //置底
                //         $('#mdp' + mdpId).insertAfter($('#mdp' + mdpIdList[mdpIdList.length - 1]));
                //         mdpIdList = swapArray(mdpIdList, index, mdpIdList.length - 1);
                //         break;
                // }
                // let str = '';
                // for (let idx in mdpIdList) {
                //     $('#upDown' + mdpIdList[idx]).remove();
                //     str = '<div class="paddingTopBottom"><div id="upDown' + mdpIdList[idx] + '">';
                //     str += renderStr(mdpIdList[idx], idx, mdpIdList.length);
                //     str += '</div></div>';
                //     $('#upDownContainer' + mdpIdList[idx]).html(str);
                // }
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function jumpSecondTo(mdpsId, upDown, mdpId) {
    let index = mdpsIdList[mdpId].indexOf(mdpsId);
    let tempList = mdpsIdList[mdpId];
    let service = _all_updateSecondSeqAction;
    let data = "mdpsIdBefore=" + mdpsId + '&upDown=' + upDown;
    if (upDown >= 2) {
        service = _all_updateSecondSeqBatchAction;
        if (upDown == 2) {
            data += '&length=' + index;
        }
        if (upDown == 3) {
            data += '&length=' + (mdpsIdL.length - index);
        }
    }
    $.supper("doservice", {
        "service": service, "data": data, ifloading: true, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                switch (upDown) {
                    case 0:
                        $('#mdps' + mdpsId).insertBefore($('#mdps' + tempList[index - 1]));
                        mdpsIdList[mdpId] = swapArray(tempList, index, index - 1, upDown);
                        break;
                    case 1:
                        $('#mdps' + mdpsId).insertAfter($('#mdps' + tempList[index + 1]));
                        mdpsIdList[mdpId] = swapArray(tempList, index, index + 1, upDown);
                        break;
                    case 2:
                        $('#mdps' + mdpsId).insertBefore($('#mdps' + tempList[0]));
                        mdpsIdList[mdpId] = swapArray(tempList, index, 0);
                        break;
                    case 3:
                        $('#mdps' + mdpsId).insertAfter($('#mdps' + tempList[tempList.length - 1]));
                        mdpsIdList[mdpId] = swapArray(tempList, index, tempList.length - 1);
                        break;
                }
                let str = '';
                for (let idx in mdpsIdList[mdpId]) {
                    $('#upDownS' + mdpsIdList[mdpId][idx]).remove();
                    str = '<div class="paddingTopBottom"><div id="upDownS' + mdpsIdList[mdpId][idx] + '">';
                    str += renderSecondStr(mdpsIdList[mdpId][idx], idx, mdpsIdList[mdpId].length, mdpId);
                    str += '</div></div>';
                    $('#upDownContainerS' + mdpsIdList[mdpId][idx]).html(str);
                }
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function viewMaterielPart(mdpId, mdpsId) {
    let str = "var data= 'mdpId=" + mdpId;
    if (mdpsId != undefined) {
        str += "&mdpsId=" + mdpsId;
    }
    str += "'";
    eval(str);
    let att_url = $.supper("getServicePath", {"data": data, url: viewInfo});
    $.supper("showTtemWin", {"url": att_url, "title": '物料分类详细信息'});
}

function deleteMaterielPart(mdpId) {
    if (mdpId == undefined) {
        $.supper("alert", {title: "操作提示", msg: "请选择需要删除的数据！"});
        return;
    }
    $.supper("doservice", {
        "service": _all_deleteAction, "data": "mdpId=" + mdpId, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                main_search();
                // $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: "存在二级分类，无法删除！"});
            } else if (jsondata.code == "3") {
                $.supper("alert", {title: "操作提示", msg: "此类别下已有物料，不允许删除！"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function deleteMaterielPartSecond(mdpId, mdpsId) {
    if (mdpId == undefined || mdpsId == undefined) {
        $.supper("alert", {title: "操作提示", msg: "请选择需要删除的数据！"});
        return;
    }
    $.supper("doservice", {
        "service": _all_deleteSecondAction,
        "data": "mdpsId=" + mdpsId + '&mdpId=' + mdpId,
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert", {
                //     title: "操作提示", msg: "操作成功！", BackE: () => {
                        // mdpsIdList[mdpId].splice(mdpsIdList[mdpId].indexOf(mdpsId), 1);
                        initPartSecond(mdpId);
                    // }
                // });
            } else if (jsondata.code == "2") {
                $.supper("alert", {title: "操作提示", msg: "此类别下已有物料，不允许删除！"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function selectAllPart(checked) {
    if (checked == true) {
        $('input:checkbox[id^=mdpf]').each(function () {
            // $(this).prop("checked", true);
            $(this).click();
        })
    } else {
        $('input:checkbox[id^=mdpf]').each(function () {
            // $(this).prop("checked", false);
            $(this).click();
        })
    }
}

function batchDelete() {
    if (checkMdpId.length <= 0 && checkMdpsId.length <= 0) {
        $.supper("alert", {title: "操作提示", msg: "请选择需要删除的数据！"});
        return;
    }

    // 如果一级分类有数据，先删除一级
    if (checkMdpId.length > 0) {
        deleteFirstPart();
    } else if (checkMdpsId.length > 0) {
        deleteSecondPart();
    }
}

function deleteFirstPart() {
    let mdpIds = checkMdpId.join(',');
    $.supper("doservice", {
        "service": _all_deleteAllAction,
        "data": "mdpIds=" + mdpIds,
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert", {
                    // title: "操作提示", msg: "操作成功！", BackE: () => {
                        // mdpsIdList[mdpId].splice(mdpsIdList[mdpId].indexOf(mdpsId), 1);
                        main_search();
                        //删除二级分类
                        if ( checkMdpsId.length > 0) {
                            deleteSecondPart();
                        }
                    // }
                // });
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: "存在二级分类，无法删除！"});
            } else if (jsondata.code == '3') {
                $.supper("alert", {title: "操作提示", msg: "该分类下面已有物品，不能直接删除类别！"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function deleteSecondPart() {
    let mdpsIds = checkMdpsId.join(',');
    $.supper("doservice", {
        "service": _all_deleteSecondAllAction,
        "data": "mdpsIds=" + mdpsIds,
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert", {
                    // title: "操作提示", msg: "操作成功！", BackE: () => {
                        // mdpsIdList[mdpId].splice(mdpsIdList[mdpId].indexOf(mdpsId), 1);
                        main_search();
                        //删除二级分类
                        if ( checkMdpsId.length > 0) {
                            deleteSecondPart();
                        }
                    // }
                // });
            }  else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: "该分类下面已有物品，不能直接删除类别！"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

let isAdding = false;
//添加一级分类
function addMaterielPart(mdpId) {
    if (isAdding == true) {
        $.supper("alert", {title: "操作提示", msg: "请取消上次添加分类！"});
            return;
    }
    //var isChecked = document.getElementById("mdpf").checked;
    let mdpId1;
    // $( "input[id^='mdpf'][type=checkbox]" ).each(function() {
    //     if ($(this).prop('checked') == true) {
    //         mdpId1 = $(this).data('mdpid');
    //         return true;
            // console.log('ddddd', mdpId1)
    //     }
    // })
    // if (mdpId1 != undefined){
    //     addSecondPart(mdpId1)
    //     return;
    // }
    // if(isChecked == true){
    //     if ($('#switch' + mdpId).prop('checked') !== true) {
    //         $('#swtichLabel' + mdpId).click();
    //     } else {
    //         if (mdpsIdList[mdpId].length <= 0) {
    //             initAddSecondPart(true, mdpId);
    //         } else {
    //             initAddSecondPart(false, mdpId);
    //         }
    //     }
    //     initAddSecondPart(true, mdpId);
    //     return;
    // }
    isAdding = true;
    let str = '';
    str += '<div class="flex border-bottom" id="mdpTemp">';
    str += '<div class="flex-1 border-right" style="padding: 0 10px;width: 75px;"><div class="paddingTopBottom" style="display: flex;justify-content: center;"><input type="checkbox"  /><div style="margin-left: 10px;width: 30px;"><img style="width: 30px;height:16px;" src="/dentistmall/img/xiala2.png" /></div></div></div>';
    str += '<div class="flex-4 border-right"><div class="paddingTopBottom" style="margin-left: 10px;"><input id="mbpNameTemp" name="mdpName" style="width: 300px;height: 30px;border: 1px solid #DCDCDC;" maxlength="20" placeholder="不可超过20个字符" /><span id="tips" style="margin-left: 5px;font-size: 12px;display:none;">不可超过20个字符</span></div></div>';

    str += '<div class="flex-2 border-right">';
    if (isAdding == false) {
        str += renderUpDown(data, dataList.length);
    }
    str += '</div>';

    str += '<div class="flex-2 border-right">' +
        '<div class="paddingTopBottom text-align"><div class="switch-container"><input id="needShowTemp" type="checkbox" class="switch" checked /><label for="needShowTemp" onclick=""></label></div>' +
        // '<div class="layui-form"><input type="checkbox" name="needShow" lay-skin="switch"></div>' +
        // '<input id="needShowTemp" name="needShow" />' +
        // '<form class="layui-form"><div class="layui-form-item" pane>' +
        // '<div class="layui-input-inlie">' +
        // '<input type="checkbox" id="needShowTemp" checked="" name="needShowTemp" lay-skin="switch" lay-filter="switchTest" title="开关">' +
        // '</div></div></form>' +
        '</div></div>';

    str += '<div class="flex-3">' +
        '<div class="paddingTopBottom text-align"><a class="btn btn-info btn-xs" onclick="saveMP()">保存</a>&nbsp;&nbsp;' +
        '<a class="btn btn-warning btn-xs" onclick="cancelAdd()">取消</a>&nbsp;&nbsp;' +
        '</div></div>';
    str += '</div>';
    if (mdpIdList == undefined || mdpIdList == null || mdpIdList.length <= 0) {
        $("#content").html(str);
    } else {
        $(str).insertBefore($('#mdp' + mdpIdList[0]));
    }
    // $('#content').append($(str));
    layui.use(['form'], function () {
        var form = layui.form;

        //监听指定开关
        form.on('switch(switchTest)', function (data) {
            this.checked;
        });

    });

    $("#mbpNameTemp").bind('input mdpName',function(){
        var aitem = document.getElementById("tips");
        if ($(this).val().length > 20) {
            aitem.style.display = "block";
        } else {
            aitem.style.display = "none";
        }
    })
}

let isSecondAdding = false;

//添加二级分类
function addSecondPart(mdpId) {
    if (isSecondAdding == true) {
        $.supper("alert", {title: "操作提示", msg: "请取消上次添加分类！"});
        return;
    }
    isSecondAdding = true;

    if ($('#switch' + mdpId).prop('checked') !== true) {
        $('#swtichLabel' + mdpId).click();
    } else {
        if (mdpsIdList[mdpId].length <= 0) {
            initAddSecondPart(true, mdpId);
        } else {
            initAddSecondPart(false, mdpId);
        }
    }

    // if (mdpsIdList[mdpId].length <= 0) {
    //     if ($('#switch' + mdpId).prop('checked') !== true) {
    //         $('#swtichLabel' + mdpId).click();
    //     }
    // } else {
    //     if ($('#switch' + mdpId).prop('checked') !== true) {
    //         $('#swtichLabel' + mdpId).click();
    //     }
    // }
    // $('#content').append($(str));
    // layui.use(['form'], function () {
    //     var form = layui.form;
    //
    //     //监听指定开关
    //     form.on('switch(switchTest)', function (data) {
    //         this.checked;
    //     });
    //
    // });
}

function initAddSecondPart(addOrInsert, mdpId) {
    let str = '';
    str += '<div class="flex border-bottom" id="mdpsTemp">';
    str += '<div class="flex-1 border-right" style="padding: 0 10px;width: 75px;"><div class="paddingTopBottom"><input type="checkbox" style="margin-top: 0px;"/><div style="margin-left: 10px;width: 43px;"></div></div></div>';
    str += '<div class="flex-4 border-right"><div class="paddingTopBottom" style="margin-left: 10px;"><span style="float: left;margin-left: 10px;margin-right: 10px;"><img style="width: 35px;height:20px;" src="/dentistmall/img/zhe.png" /></span><input id="mbpsNameTemp" name="mdpsName" style="width: 300px;height: 30px;border: 1px solid #DCDCDC;" maxlength="20" placeholder="不可超过20个字符" /><span id="tipss" style="margin-left: 5px;font-size: 12px;display:none;">不可超过20个字符</span></div></div>';

    str += '<div class="flex-2 border-right">';
    if (isSecondAdding == false) {
        str += renderUpDown(data, dataList.length);
    }
    str += '</div>';

    str += '<div class="flex-2 border-right">' +
        // '<div class="switch-container"><input id="needShowSTemp" type="checkbox" class="switch" /><label for="needShowSTemp" onclick=""></label></div>' +
        // '<div class="layui-form"><input type="checkbox" name="needShow" lay-skin="switch"></div>' +
        // '<input id="needShowTemp" name="needShow" />' +
        // '<form class="layui-form"><div class="layui-form-item" pane>' +
        // '<div class="layui-input-inlie">' +
        // '<input type="checkbox" id="needShowTemp" checked="" name="needShowTemp" lay-skin="switch" lay-filter="switchTest" title="开关">' +
        // '</div></div></form>' +
        '</div>';

    str += '<div class="flex-3">' +
        '<div class="paddingTopBottom text-align"><a class="btn btn-info btn-xs" onclick="saveMPSecond(' + mdpId + ')">保存</a>&nbsp;&nbsp;' +
        '<a class="btn btn-warning btn-xs" onclick="cancelSecondAdd()">取消</a>&nbsp;&nbsp;</div>' +
        '</div>';
    str += '</div>';

    if (addOrInsert == true) {
        $('#secondShow' + mdpId).html(str);
    } else {
        $(str).insertBefore($('#mdps' + mdpsIdList[mdpId][0]));
    }

    $("#mbpsNameTemp").bind('input mdpsName',function(){
        var aitem = document.getElementById("tipss");
        if ($(this).val().length > 20) {
            aitem.style.display = "block";
        } else {
            aitem.style.display = "none";
        }
    })



}

function saveMP() {
    let name = $('#mbpNameTemp').val();

    let needShow = 0;

    if ($('#needShowTemp').prop('checked') === true) {
        needShow = 1;
    }
    let data = 'mdpName=' + name + '&needShow=' + needShow;
    $.supper("doservice", {
        "service": _all_saveAction, "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert", {
                //     title: "操作提示", msg: "操作成功！", BackE: () => {
                        cancelAdd();
                        main_search();
                //         mdpIdList.unshift(jsondata.obj.mdpId);
                //
                //         let str = renderFirtsList(jsondata.obj, 0, mdpIdList.length);
                //         if (mdpIdList.length <= 1) {
                //             $("#content").html(str);
                //         } else {
                //             $(str).insertBefore($('#mdp' + mdpIdList[1]));
                //         }
                //         // $('#content').append($(str));
                //
                //
                //
                //         $('#upDown' + mdpIdList[1]).remove();
                //         str = '<div class="paddingTopBottom"><div id="upDown' + mdpIdList[1] + '">';
                //         str += renderStr(mdpIdList[1], 1, mdpIdList.length);
                //         str += '</div></div>';
                //         $('#upDownContainer' + mdpIdList[1]).html(str);
                // if (jsondata.obj.needShow == 1) {
                //     needOpenMdpId.push(jsondata.obj.mdpId);
                //     showOrHideSecond(jsondata.obj.mdpId, true);
                // }
                    // }
                // });
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: "存在同名！"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}

function saveMPSecond(mdpId) {
    let name = $('#mbpsNameTemp').val();
    if (name == '') {
        $.supper("alert", {title: "操作提示", msg: "不可为空！"});
        return;
    }
    if (name.length > 20) {
        $('#tipss').show();
        // $.supper("alert", {title: "操作提示", msg: "不可超过20个字符！"});
        return;
    }

    let needShow = 0;

    if ($('#needShowTempS').prop('checked') === true) {
        needShow = 1;
    }
    let data = 'mdpsName=' + name + '&needShow=1' + '&mdpId=' + mdpId;
    $.supper("doservice", {
        "service": _all_saveSecondAction, "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert", {
                //     title: "操作提示", msg: "操作成功！", BackE: () => {
                        cancelSecondAdd();
                        mdpsIdList[mdpId].unshift(jsondata.obj.mdpsId);

                        let str = rendererSecondList(jsondata.obj, 0, mdpsIdList[mdpId].length, mdpsIdList[mdpId]);
                        if (mdpsIdList[mdpId].length <= 1)
                            $('#secondShow' + mdpId).html(str);
                        else
                            $(str).insertBefore($('#mdps' + mdpsIdList[mdpId][1]));
                        // $('#content').append($(str));

                        $('#upDownS' + mdpsIdList[mdpId][1]).remove();
                        str = '<div class="paddingTopBottom"><div id="upDownS' + mdpsIdList[mdpId][1] + '">';
                        str += renderSecondStr(mdpsIdList[mdpId][1], 1, mdpsIdList[mdpId].length);
                        str += '</div></div>';
                        $('#upDownContainerS' + mdpsIdList[mdpId][1]).html(str);
                    // }
                // });
                main_search();
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: "存在同名！"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作出错！"});
            }
        }
    });
}


function cancelAdd() {
    $('#mdpTemp').remove();
    isAdding = false;
    if (mdpIdList == undefined || mdpIdList == null || mdpIdList.length <= 0) {
        // $("#content").html('未找到数据');
        $("#content").html('');
    }
}

function cancelSecondAdd() {
    $('#mdpsTemp').remove();
    isSecondAdding = false;
}

function main_search() {
    mdpIdList = [];
    mdpsIdList = {};
    isAdding = false;
    initPage()
}

$(function () {
    _all_win_tools_but.xtoolbar('create', _Toolbar);

    // _all_datagrid_height = $(window).height() - _all_win_searchForm.height() - 64 - 95;

    $("#win_but_search").css("width", "95px");
    $("#win_but_search").css("vertical-align", "middle");

    main_search();
});
