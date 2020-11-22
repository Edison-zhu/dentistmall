var wmsMiId;
var url;
var mmtId1 = "";
var _mdpId;
var _mdpsId;
var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
var treeClickLevel = 0;
var datalist;
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
                jump();
            }
        }

    }
    //设置树的初始数据
    var zNodes = [
        // {id: 0, pId: "", name: "类别列表", isParent: true}
    ];
    $.fn.zTree.init($("#tree"), setting, zNodes);
    //自动展现第一层树
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var node = zTree.getNodesByParam("id", "0");
    lastExpandNode = node;
    zTree.expandNode(node[0], true, false, false);

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
    searchName2();
}

//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null) {
        var zTree = $.fn.zTree.getZTreeObj("tree");
        if (lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
            zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");
    }
    searchName2();
}
$(function () {
    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if (selOutOrderType != null && selOutOrderType.wmsMiId != null) {
        wmsMiId = selOutOrderType.wmsMiId;
        url = selOutOrderType.url;
    }
    if (wmsMiId != null && wmsMiId != undefined) {
        // searchAll3(wmsMiId);
        searchAll2(wmsMiId);
    }
    countCollect1();
    loadItemZtree();
    //2020年07月06日09:12:22朱燕冰修改
    var vdata = "&wmsMiId=" + wmsMiId;
    $.supper("doservice", {
        "service": "MdOutOrderService.seachCollect", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                count = jsondata.obj;
                if (count > 0) {
                    $("#dv_2").removeClass().addClass('icon-favorite-solid');
                } else {
                    // $.supper("alert",{ title:"操作提示", msg:jsondata.meg});
                    $("#dv_2").removeClass().addClass('icon-favorite');
                }
            }
        }
    });
    if (datalist.length = 1) {
        if ($("#dv_2").attr('class') == 'icon-favorite') {
            const BTN2 = document.getElementById('shoucang');
            BTN2.click();
        }
    }
});




var ratios1=0;
function searchAll1(wmsMiId,ratios) {
    ratios1=ratios;
    data = "&wmsMiId=" + wmsMiId;
    $.supper('initPagination', {
        id: "Pagination",
        service: "MdOutOrderService.getPagerModelBySearchName",
        data: data,
        isAjax: "1",
        "BackE": initList1
    });
}

var wmms = {};
var quantity = 0;
var basicUnit = '';
var baseNumber = 0;
var unit = '';

function initList1(dataList) {
    var str = "";
    var isLogin = $("#isLogin").val();
    let aliasName = '';
    if (dataList != null && dataList.length > 0) {

        var wmsMiId2;
        wmsMiId2 = wmsMiId;
        for (var i = 0; i < dataList.length; i++) {
            var data = dataList[i];
            if (wmms[wmsMiId2] == undefined)
                wmms[wmsMiId2] = {};
            wmms[wmsMiId2].item = data;
            str += "<div class=\"img\"><img style='width: 300px;height: 300px;' src=\"" + data.lessenFilePath + "\" alt=\"\"></div>";
            str += "<div class=\"textbox\"><div class=\"text\"><b><span style='font-size: 14px' id='searchNameMatch" + data.matName + "'>" + data.matName + "</span></b></div>";
                aliasName = data.aliasName == undefined ? '' : data.aliasName;
            if (aliasName != "") {
                str += "<div class=\"text\">别名："
                    + "<a style=\"color:#888\" href=\"pcjxiangxi.htm?wzId=" + data.wzId + "\" target=\"_blank\"><span id='searchNameMatch" + aliasName + "'>" + aliasName + "</span></a><a style='margin-left: 20px' onclick='Editalias(" + wmsMiId + ")'><img style=\" width: 25px;height: 23px\"\n" +
                    "                                 src=\"../../../../dentistmall/css/shopping/images/edit1.png\"></a></div>";
            } else {
                str += "<div class=\"text\"><span style=''></span>"
                    + "<a style=\"color:#888\" href=\"pcjxiangxi.htm?wzId=" + data.wzId + "\" target=\"_blank\"><span id='searchNameMatch" + aliasName + "'>别名:" + aliasName + "</span></a><a style='margin-left: 20px' onclick='Editalias(" + wmsMiId + ")'><img style=\" width: 25px;height: 23px\"\n" +
                    "                                 src=\"../../../../dentistmall/css/shopping/images/edit1.png\"></a></div>";
            }
            str += "<div class=\"text\">物料号：<span id='searchNameMatch" + data.matCode + "'>" + data.matCode + "</span></div>";
            str += "<div class=\"text\">包装方式：<span id='searchNameMatch" + data.productName + "'>" + data.productName + "</span></div>";
            str += "<div class=\"text\">单位：<span id='searchNameMatch" + data.basicUnit + "'>" + data.basicUnit + "</span></div>";
            var splitQ=0;
            if(data.splitQuantitys1!=null&&data.base_numbers!=undefined&&data.splitQuantitys1!=undefined>0){
                splitQ=Number(data.splitQuantitys)-(Number(data.quantity)*Number(ratios1));
            }else {
                splitQ=0;
            }
            str += "<div class=\"text\">库存总数量：<span id='searchNameMatch" + data.base_numbers + "'>" + (data.base_numbers != null ? data.base_numbers : 0) + data.basicUnit+(data.splitBaseNumber!=null?data.splitBaseNumber:0)+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'') + "</span></div>";
            str += "</div></div>";
            quantity = data.quantity == undefined ? 0 : data.quantity;
            basicUnit = data.basicUnit == undefined ? '' : data.basicUnit;
            baseNumber = data.baseNumber == undefined ? 0 : data.baseNumber;
            unit = data.splitUnit == undefined ? '' : data.splitUnit;
            ratio = data.basicCoefficent == undefined ? 1 : data.basicCoefficent;
        }
        $("#mx1").html(str);
    }
}
function searchAll2(wmsMiId) {
    data = "&wmsMiId=" + wmsMiId;
    $.supper('initPagination', {
        id: "Pagination1",
        service: "MdOutOrderService.getmmfMx",
        data: data,
        isAjax: "1",
        "BackE": initList2
    });
}
var mmfId3 = 0;
function initList2(dataList) {
    datalist = dataList;
    var str = "";
    if (dataList != null && dataList.length > 0) {
        for (var i = 0; i < dataList.length; i++) {
            var data = dataList[i];
            ratios= data.ratio;
            if (i == 0) {
                mmfId3 = data.mmfId;
            }
            if (i % 5 == 0) {
                str += "<div style='clear: both'></div>";
                str += "<a id='shoucang' href='javascript:click("+data.mmfId+",\"" + data.mmfCode+ "\", \"" + data.mmfName + "\", " + data.quantity + ", " + data.baseNumber + ", " + data.ratio + ", " + data.splitQuantity + ");'><div id='mmfId" + data.mmfId + "' class=\"item\"><div>" + data.mmfName + "</div></div></a>";
            }
            else
                str += "<a  href='javascript:click("+data.mmfId+",\"" + data.mmfCode+ "\", \"" + data.mmfName + "\", " + data.quantity + ", " + data.baseNumber + ", " + data.ratio + ", " + data.splitQuantity + ");'><div id='mmfId" + data.mmfId + "' class=\"item\"><div>&nbsp;&nbsp;"+data.mmfName+"&nbsp;&nbsp;</div></div></a>";
        }
        $("#mx2").html(str);
    }
}

var ratios=0;
var mmfId3 = 0;
function initList2(dataList) {
    datalist = dataList;
    var str = "";

    var mmfId1='';
    var mmfCode1='';
    var mmfName1='';
    var quantity1='';
    var baseNumber1='';
    var ratio1='';
    var splitQuantity1='';
    if (dataList != null && dataList.length > 0) {
        for (var i = 0; i < dataList.length; i++) {
            var data = dataList[i];
            ratios= data.ratio;
            if (i == 0) {
                mmfId3 = data.mmfId;

                mmfId1=data.mmfId;
                mmfCode1=data.mmfCode;
                mmfName1=data.mmfName;
                quantity1=data.quantity;
                baseNumber1=data.baseNumber;
                ratio1=data.ratio;
                splitQuantity1=data.splitQuantity;
            }
            if (i % 5 == 0) {
                str += "<div style='clear: both'></div>";
                str += "<a id='shoucang' href='javascript:click("+data.mmfId+",\"" + data.mmfCode+ "\", \"" + data.mmfName + "\", " + data.quantity + ", " + data.baseNumber + ", " + data.ratio + ", " + data.splitQuantity + ");'><div id='mmfId" + data.mmfId + "' class=\"item\"><div>" + data.mmfName + "</div></div></a>";
            }
            else
                str += "<a  href='javascript:click("+data.mmfId+",\"" + data.mmfCode+ "\", \"" + data.mmfName + "\", " + data.quantity + ", " + data.baseNumber + ", " + data.ratio + ", " + data.splitQuantity + ");'><div id='mmfId" + data.mmfId + "' class=\"item\"><div>&nbsp;&nbsp;"+data.mmfName+"&nbsp;&nbsp;</div></div></a>";
            ratios=data.ratio;
            // if (i==0){
            //    click(data.mmfId+",\"" + data.mmfCode+ "\", \"" + data.mmfName + "\", " + data.quantity + ", " + data.baseNumber + ", " + data.ratio + ", " + data.splitQuantity);
            // }
        }
        searchAll1(wmsMiId,ratios);
        $("#mx2").html(str);
        click(mmfId1,mmfCode1,mmfName1,quantity1,baseNumber1,ratio1,splitQuantity1);
    }


}

var mmfIds = "";
var wmsIds = "";
var sumIndex = 0;
var numberSums = "";
var ratio = 1;
// var count1=0;
var sumIndexToo;
function click(mmfId, mmfCode, mmfName, quantity, baseNumber, ratio,splitQuantity) {
    if (mmfIds.indexOf(mmfId) != -1) {
        $("#mmfId" + mmfId).css('background', '#FFFFFF');
        var mmfId1 = mmfId + ',';
        wmsIds = wmsMiId + ',';
        mmfIds = removeChars(mmfIds, mmfId1);
        // wmsIds=removeChars(wmsIds,wmsId1);
        numberSums = 1 + ",";
        sumIndex = sumIndex - 1;
        remoceFormate(mmfCode, mmfName, mmfId);
    }else {
        $("#mmfId"+mmfId).css('background','#66B3FF');
        mmfIds+=mmfId+",";
        wmsIds+=wmsMiId+",";
        numberSums+=1+",";
        sumIndex+=1;
        selFormate(mmfCode, mmfName, mmfId, quantity, baseNumber, ratio,splitQuantity);
    }
    if (sumIndex != 0 && sumIndex != null && sumIndex != undefined) {
        sumIndexToo = sumIndex
        $("#mx31").html(sumIndex);
        $("#mx311").show();
    } else {
        $("#mx311").hide();
    }
    var vdata = "&wmsMiId=" + wmsMiId + "&mmfIds=" + mmfIds;
    var mmfIds1 = mmfIds.substring(0, mmfIds.length - 1);
    var mmfIds2 = mmfIds1.split(",");
    if (mmfIds2.length > 1) {
        $("#dv_2").removeClass().addClass('icon-favorite');
    } else {
        $.supper("doservice", {
            "service": "MdOutOrderService.getCollectMx", "data": vdata, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    count = jsondata.obj;
                    if (count == 1) {
                        $("#dv_2").removeClass().addClass('icon-favorite-solid');
                    } else {
                        $("#dv_2").removeClass().addClass('icon-favorite');
                    }
                } else
                    $.supper("alert", {title: "操作提示", msg: jsondata.meg});
            }
        });
        $("#dv_2").removeClass().addClass('icon-favorite-solid');
    }
    if (datalist.length > 1) {
        $.supper("doservice", {
            "service": "MdOutOrderService.getCollectMx", "data": vdata, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    count = jsondata.obj;
                    if (count == 1) {
                        $("#dv_2").removeClass().addClass('icon-favorite-solid');
                    } else {
                        $("#dv_2").removeClass().addClass('icon-favorite');
                    }
                } else
                    $.supper("alert", {title: "操作提示", msg: jsondata.meg});
            }
        });
    }
}

function remoceFormate(mmfCode, mmfName, mmfId) {
    $("#li_" + mmfId).removeClass();
    $("#tr_" + mmfId).remove();
    mmfIds = mmfIds.replace(mmfId + ",", "");
    $("#gwcListTable").remove("#tr_" + mmfId);
    // }
}
function selFormate(mmfCode, mmfName, mmfId, quantity, baseNumber, ratio,splitQuantity) {
    var str = "<tr id=\"tr_" + mmfId + "\">";
    str += "<td><div class=\"product-info-choose-count m-top20\">";//<span>购买数量：</span>";
    str += '<div style="display: inline"><a class="nameButton" disabled><span class="product-info-inventory ">' + mmfName + '</span></a>';
    str += "</div>";
    str += '</div></td>';
    str += '<td style="width: 100px;text-align: left" >';
    // str += '<div class="m-top20"><span style="margin-left: 6px" class="product-info-inventory">No.' + mmfCode + '</span></div>';
    str += '</td>'
    let baseNumberTemp = quantity * ratio;
    baseNumber -= baseNumberTemp;
    str += '<td style="width: 140px;"><div class="product-info-choose-count m-top20" style="padding-left: 14px">' + (quantity == undefined ? 0 : quantity) + basicUnit + (splitQuantity == undefined ? 0 : splitQuantity) + (unit == '' ? basicUnit : unit) + '</div></td>';
    str += '<td style="width: 200px;"><div class="product-info-choose-count m-top20" style="padding-left: 0px">';
    str += "<input id=\"min" + mmfId + "\" name=\"\" style=\"margin-left:5px\" type=\"button\" value=\"-\" onclick=\"minusShu('" + mmfId + "')\">";
    str += "<input type=\"hidden\" id=\"id_" + mmfId + "\" value=\"" + mmfId + "\"> ";
    str += "<input id=\"inp_" + mmfId + "\" name=\"\" style=\"padding: 4px 8px;\" type=\"text\" value=\"\" class=\"product-info-count\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');changeNumber('" + mmfId + "')\"/>";
    str += "<input id=\"add" + mmfId + "\" style=\"margin-left:5px\" name=\"\" type=\"button\" value=\"+\" onclick=\"addShu('" + mmfId + "')\">";
    str += basicUnit;
    // str += "<input id=\"minS" + mmfId + "\" name=\"\" style=\"margin-left:10px\" type=\"button\" value=\"-\" onclick=\"minusShuS('" + mmfId + "')\">";
    // str += "<input id=\"inpS_" + mmfId + "\" name=\"\" style=\"margin-left: 5px;padding: 4px 8px;\" type=\"text\" value=\"\" class=\"product-info-count\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');changeSplitNumber('" + mmfId + "')\"/>";
    // str += "<input id=\"addS" + mmfId + "\" style=\"margin-left:5px\" name=\"\" type=\"button\" value=\"+\" onclick=\"addShuS('" + mmfId + "')\">";
    // str += (unit == '' ? basicUnit : unit);
    str += "</div>";
    str += '<td>';
    // str += '<div class="product-info-choose-count m-top20"><input id="remove_${format.mmfId}" type="button" value="移除" onclick="removeTr(' + mmfId + ')"></div>';
    str += '</td>';
    str += "</td></tr>";
    $("#gwcListTable").append(str);
}

function changeNumber(mmfId) {
    let number = $('#inp_' + mmfId).val();
    if (number == '')
        return;
}

function changeSplitNumber(mmfId) {
    let number = $('#inp_' + mmfId).val();
    if (number == '')
        return;
}

function removeTr(mmfId) {
    $("#li_" + mmfId).removeClass();
    $("#tr_" + mmfId).remove();
    mmfIds = mmfIds.replace(mmfId + ",", "");
    $("#gwcListTable").remove("#tr_" + mmfId);
    $("#mmfId" + mmfId).css('background', '#FFFFFF');
    var mmfId1 = mmfId + ',';
    mmfIds = removeChars(mmfIds, mmfId1);
    numberSums = 1 + ",";
    sumIndex = sumIndex - 1;
    if (sumIndex != 0 && sumIndex != null && sumIndex != undefined) {
        $("#mx31").html(sumIndex);
        $("#mx311").show();
    } else {
        $("#mx311").hide();
    }
}

function showOrHideRemoveBtn(showOrHide) {
    if (showOrHide === true) {
        $('input[id^="remove_"]').each(function () {
            $(this).show();
        })
    } else {
        $('input[id^="remove_"]').each(function () {
            $(this).hide();
        })
    }

}

//减少符号
function minusShu(mmfId) {
    var shu = $("#inp_" + mmfId).val();
    var price = $("#price_" + mmfId).val();
    if (shu > 0)
        shu--;
    $("#inp_" + mmfId).val(shu);
}

//增加符号
function addShu(mmfId) {
    var shu = $("#inp_" + mmfId).val();
    var price = $("#price_" + mmfId).val();
    shu++;
    $("#inp_" + mmfId).val(shu);

}

function minusShuS(mmfId) {
    var shu = $("#inpS_" + mmfId).val();
    var price = $("#price_" + mmfId).val();
    if (shu > 0)
        shu--;
    $("#inpS_" + mmfId).val(shu);
}

function addShuS(mmfId) {
    var shu = $("#inpS_" + mmfId).val();
    var price = $("#price_" + mmfId).val();
    shu++;
    $("#inpS_" + mmfId).val(shu);
}

function removeChars(source, chars) {
    var reg = new RegExp(chars, "gi");
    var result = source.replace(reg, "");
    return result;
}

function shoucang2(dataList) {
    var vdata = "&wmsMiId=" + wmsMiId + "&mmfId=" + mmfIds;
    var mmfIds1 = mmfIds.substring(0, mmfIds.length - 1);
    var mmfIds2 = mmfIds1.split(",");
    if (mmfIds2.length > 1) {
        $.supper("alert", {title: "操作提示", msg: "单次只能收藏一个规格"});
        return;
    }

    if (mmfIds == null || mmfIds.length < 1) {
        vdata = "&wmsMiId=" + wmsMiId + "&mmfId=" + mmfId3;
    }
    if ($("#dv_2").attr('class') == 'icon-favorite') {
        //未收藏状态下并且规格只有一个
        if (datalist.length <= 1) {
            const BTN2 = document.getElementById('shoucang');
            BTN2.click();
        } else {
            if (mmfIds2 == "") {
                $.supper("alert", {title: "操作提示", msg: "请先选择规格"});
                return;
            }
        }
        $.supper("doservice", {
            "service": "MdOutOrderService.saveCollect", "data": vdata, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    count = jsondata.obj;
                    $("#dv_2").removeClass().addClass('icon-favorite-solid');
                } else {
                    $.supper("alert", {title: "操作提示", msg: jsondata.meg});
                }
            }
        });
        layer.msg('成功加入收藏夹！');
    } else {
        $.supper("doservice", {
            "service": "MdOutOrderService.deleteMicId", "data": vdata, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    count = jsondata.obj;
                    $("#dv_2").removeClass().addClass('icon-favorite');
                } else {
                    $.supper("alert", {title: "操作提示", msg: jsondata.meg});
                }
            }
        });
        if (datalist.length <= 1) {
            const BTN2 = document.getElementById('shoucang');
            BTN2.click();
        }
        $("#dv_2").removeClass().addClass('icon-favorite');
        layer.msg('成功移除收藏夹！');

    }

}

function addCar() {//wmsMiId,state
    if (mmfIds == "" || mmfIds == undefined) {
        $.supper("alert", {title: "操作提示", msg: "请选择要加入购物车的规格"});
        return;
    }
    var vdata = "&wmsMiId=" + wmsMiId + "&mmfId=" + mmfIds;
    let shus = '';
    let splitShus = '';
    let shu;
    let splitShu;
    let mmfIdArray = mmfIds.split(",");
    for (let idx = 0; idx < mmfIdArray.length; idx++) {
        if (mmfIdArray[idx] == '')
            continue;
        shu = $('#inp_' + mmfIdArray[idx]).val();
        // if (shu == '' || shu == '0' || shu <= 0) {
        //     $.supper("alert",{ title:"操作提示", msg:"请填写数目!"});
        //     return;
        // }
        // splitShu = $('#inpS_' + mmfIdArray[idx]).val();
        splitShu=0;
        var zeroa = 0;
        var zerob = 0;
        if (shu == '' || shu == '0' || shu <= 0 ) {
            zeroa = 1
        }
        if (splitShu == '' || splitShu == '0' || splitShu <= 0 ) {
            zerob = 1
        }
        if (zeroa == 1 && zerob == 1) {
            $.supper("alert", {title: "操作提示", msg: "请填写数目!"});
            return;
        }

        splitShus += (splitShu == '' ? 0 : splitShu) + ',';
        shus += (shu == '' ? 0 : shu) + ',';
    }
    if (shus != '')
        shus = shus.substring(0, shus.length);
    if (splitShus != '')
        splitShus = splitShus.substring(0, splitShus.length);
    vdata += '&shus=' + shus + '&splitShus=' + splitShus;

    $.supper("doservice", {
        "service": "MdOutOrderService.addCarts", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {title: "操作提示", msg: "添加到购物车成功"});
            } else
                $.supper("alert", {title: "操作提示", msg: jsondata.meg});
        }
    });
}

var count;

function shoucang1() {

    var vdata = "&wmsMiId=" + wmsMiId + "&mmfId=" + mmfIds;
    $.supper("doservice", {
        "service": "MdOutOrderService.seachCollect", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                if ($("#dv_2").attr('class') == 'icon-favorite') {
                    $("#dv_2").attr("title", "加入收藏夹");
                } else {
                    $("#dv_2").attr("title", "移除收藏夹");
                    $("#dv_2").attr('class') == 'icon-favorite-solid'
                }
                count = jsondata.obj;
                var tips;
                $("#dv_1").bind("mouseover", function () {
                    if (count > 0) {
                        $("#dv_2").attr("title", "加入收藏夹");
                    }
                    //tips = layer.tips(message, this, {tipsMore: true});
                });
            } else
                $.supper("alert", {title: "操作提示", msg: jsondata.meg});
        }
    });

}

function saveSalesman() {

    if (mmfIds == "" || mmfIds == undefined) {
        $.supper("alert", {title: "操作提示", msg: "请先选择型号!"});
        return;
    }
    var vdata = "&mmfIdSums=" + mmfIds + "&wmsIdSums=" + wmsIds;
    let shus = '';
    let splitShus = '';
    let shu;
    let splitShu;
    let mmfIdArray = mmfIds.split(",");
    for (let idx = 0; idx < mmfIdArray.length; idx++) {
        if (mmfIdArray[idx] == '')
            continue;
        shu = $('#inp_' + mmfIdArray[idx]).val();
        // splitShu = $('#inpS_' + mmfIdArray[idx]).val();
        splitShu=0;
        // if (shu == '' || shu == '0' || shu <= 0 || splitShu == '' || splitShu <= 0) {
        //     $.supper("alert",{ title:"操作提示", msg:"请填写数目!"});
        //     return;
        // }
        var zeroa = 0;
        var zerob = 0;
        if (shu == '' || shu == '0' || shu <= 0) {
            zeroa = 1
        }
        if (splitShu == '' || splitShu == '0' || splitShu <= 0) {
            zerob = 1
        }
        if (zeroa == 1 && zerob == 1) {
            $.supper("alert", {title: "操作提示", msg: "请填写数目!"});
            return;
        }

        shus += (shu == '' ? 0 : shu) + ',';
        // splitShus += splitShu + ',';
        splitShus += (splitShu == '' ? 0 : splitShu) + ',';
    }
    if (shus != '')
        shus = shus.substring(0, shus.length);
    if (splitShus != '')
        splitShus = splitShus.substring(0, splitShus.length);
    vdata += '&numberSums=' + shus + '&splitQuantitys1=' + splitShus;

    $.supper("doservice", {
        "service": "MdOutOrderService.savePicking", "data": vdata, "BackE": function (jsondata) {

            if (jsondata.code == "1") {
                $.supper("alert", {title: "操作提示", msg: "已提交申领单可在申领管理中查看!"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "请先选择型号!"});
            }
        }
    });
}

//设置别名
var AliasNameWmsMiId;
var aliasNamesNUmber = 0;
// var aliasNames;
var matname1;

function Editalias(wmsMiId) {
    var item = wmms[wmsMiId].item;
    $("#Editalias1").show();
    var str = "<div><div style='float: left;'><img style='height: 70px;width: 70px' src=\"" + item.lessenFilePath + "\" alt=\"\"></div>" +
        "<div style='float: left;margin-left: 50px;width: 200px;line-height: 30px;'><ul><li>" + item.matName + "</li><li>包装方式：" + item.productName + "</li></ul></div>" +
        "<div style='float: left;margin-left: 50px;width: 200px;line-height: 30px;'><ul><li>单位：" + item.basicUnit + "</li><li>库存数量:" + (item.quantity != null ? item.quantity : 0)+ (item.basicUnit!=null?item.basicUnit:'') +(item.splitBaseNumber != null ? item.splitBaseNumber : 0)+(item.splitUnit !== undefined ? item.splitUnit : ''+item.basicUnit+'') +"</li></ul></div>" +
        "</div>";
    $("#EditList").html(str);
    AliasNameWmsMiId = item.wmsMiId;
    var aliasNames = '';
    if (item.linkWmsMiId != undefined){
        aliasNames = item.aliasName2 == undefined ? '' : item.aliasName2
    } else if (item.aliasName3 != undefined){
        aliasNames = item.aliasName3 == undefined ? '' : item.aliasName3
    } else{
        aliasNames = item.aliasName == undefined ? '' : item.aliasName
    }
    matname1 = item.matName;
    forList(aliasNames);

}

function forList(aliasNames) {
    /**
     * 2020年07月13日13:31:13 朱燕冰
     * 判断是否已有别名
     */
    if (aliasNames != null && aliasNames != undefined && aliasNames != "") {
        aliasNames = aliasNames.split(",");
        //已有别名
        aliasNamess = aliasNames;
        var str2 = "";
        for (i = 0; i < aliasNames.length; i++) {
            aliasNamesNUmber = aliasNames.length;
            str2 += "<div style=\"margin-left: 0px\" class=\"btn\" ><a style='display:block;width: auto' id='deleteAli" + i + "' class=\"search-button\" onclick='deleteAliasNames(\"" + aliasNames[i] + "\"," + i + ")'>" + aliasNames[i] + "<div style='float: right;margin-top: -7px'><div style='color: #0e9aef'>删除</div></div></a></div>";
        }
        $("#bms").html(str2);
        $("#bmCount").hide();
    } else {
        //2020年07月03日09:31:25朱燕冰修改
        aliasNamesNUmber = 0;
        var str3 = "<span style=\"margin-left: 43px\">参考别名:</span><span style=\"margin-left: 20px\">" + matname1 + "1</span></span><span style=\"position: absolute;left: 137px;margin-top: 38px\">" + matname1 + "2</span></span><span style=\"position: absolute;left: 137px;margin-top: 80px\">" + matname1 + "3</span>";
        $("#bmCount").hide();
        $("#bms").show()
        $("#bms").html(str3);
    }

}

function hide2() {
    $("#Editalias1").hide();
}

function success() {
    $("#Editalias1").hide();
}

function deleteAliasNames(aliasName, i) {
    var vdata = "&wmsMiId=" + AliasNameWmsMiId + "&aliasName=" + aliasName;
    $.supper("doservice", {
        "service": "MdOutOrderService.saveDeleteInventoryMaterielAliasName",
        data: vdata,
        isAjax: "1",
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                var a1 = jsondata.obj;
                forList(a1);
                searchAll1(wmsMiId);
                $.supper("alert", {title: "操作提示", msg: "删除成功!"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败!"});
            }
        }
    });
}

function addAliasName() {
    if (aliasNamesNUmber >= 3) {
        $.supper("alert", {title: "操作提示", msg: "最多可添加三个别名!"});
        return;
    }
    var AliasNameId = $("#AliasNameId").val()
    if ($("#AliasNameId").val() == "") {
        $.supper("alert", {title: "操作提示", msg: "请输入别名!"});
        return
    }
    var vdata = "&wmsMiId=" + AliasNameWmsMiId + "&aliasName=" + AliasNameId;
    $.supper("doservice", {
        "service": "MdOutOrderService.saveInventoryMaterielAliasName",
        data: vdata,
        isAjax: "1",
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // // searchAll();
                var a1 = jsondata.obj;
                forList(a1);
                searchAll1(wmsMiId);
                $.supper("alert", {title: "操作提示", msg: "添加成功!"});
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败!"});
            }
        }
    });
}

function countCollect1() {
    var vdata = '';
    $.supper("doservice", {
        "service": "MdOutOrderService.countCollect1", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // alert(jsondata.obj);
                $("#countCollects").html(jsondata.obj)
            } else {

            }
        }
    });
}

function jumpPick(searchName1) {
    var att_url = $.supper("getServicePath", {
        "service": "", url: "/jsp/dentistmall/ApplicationManagement/addPicking.jsp"
    });
    $.supper("setProductArray", {"name": "selOutOrderInfo", "value": {searchName1: searchName1}});
    $.supper("showTtemWin", {"url": att_url, "title": "新增领料"});
}

function jump() {
    var searchName1 = $("#searchName").val();
    jumpPick(searchName1);
}

function returnPicking() {
    if (url != undefined) {
        $.supper("closeTtemWin", {url: url});
    }
    // }, 200);
}

//增加回车搜索功能
$("#searchName").on('keydown', function () {
    if (event.keyCode == 13) {
        $("#search").trigger("click");
    }
});