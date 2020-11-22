/**
 * 2020年07月10日17:03:01
 * Create by Yanbnig
 */
// var _wmsMiIdsToo = [];
// var _mmfIdsToo = [];
// var _enterData = {};
// var _queryAction_WithSave = "mdMaterielInfoService.getMyPagerModelObjectWithString";
//页面初始化方法
$(function () {
    // _wmsMiIdsToo = [];
    // _mmfIdsToo = [];
    $('#mx2').show();
})
/*
点击选择按钮事件
 */
function selectRow(wmsMiId, mmfId) {
    $("#tables").hide();
    if (_paieIds[mmfId] != undefined) {
        return;
    }
    // if (_wmsMiIds.indexOf(wmsMiId) < 0) {
        _wmsMiIds.push(wmsMiId);
    // }
    if (_mmfIds.indexOf(mmfId) < 0) {
        _mmfIds.push(mmfId);
    }
    // if (wmsMiId == undefined || wmsMiId == '') {
    //     $.supper("alert", {title: "操作提示", msg: "请选择数据！"});
    // }
    // if (mmfId == undefined || mmfId == '') {
    //     $.supper("alert", {title: "操作提示", msg: "请选择数据！"});
    // }
    newAddInevneoty(wmsMiId, mmfId)
}
//选择数据接收参数回显数据方法
function newAddInevneoty(wmsMiId,mmfId) {
    // if (wmsMiId == undefined || mmfId == undefined || wmsMiId == '' || mmfId == '') {
    // } else {
    //     if (_wmsMiIdsToo.indexOf(wmsMiId) < 0) {
    //         _wmsMiIdsToo.push(wmsMiId);
    //     }
    //     if(_mmfIdsToo.indexOf(mmfId) < 0) {
    //         _mmfIdsToo.push(mmfId);
    //     }
        searchMatMx(wmsMiId,mmfId);
    // }
}
function searchMatMx(wmsMiId, mmfId) {
    allPer = 0;
    let data = '';
    $("#mxMatListMx").html('');
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
        if (_wmsMiIds.length <= 0 && _mmfIds.length <= 0) {
            return;
        }
        if (_wmsMiIds.length > 0) {
            data += '&wmsMiIds=' + _wmsMiIds.join(',');
        }
        if (_mmfIds.length > 0) {
            data += '&mmfIds=' + _mmfIds.join(',');
        }
    }
    showLoading();
    $.supper('initPagination', {
        id: "Pagination1",
        service: service,
        data: data,
        limit: 10,
        isAjax: "1",
        "BackE": initListMx
    });
}
//tbody数据回显
function initListMx(jsondata) {
    closeLoading();
    var mxList = jsondata;
    var wmsMiId;
    let mmfId;
    var str = "";
    var productNameInt;
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            wmsMiId = mxList[i].wmsMiId;
            mmfId = mxList[i].mmfId;
            // if (_wmsMiIds.indexOf(wmsMiId) < 0) {
            //     _wmsMiIds.push(wmsMiId);
            // }
            // if (_mmfIds.indexOf(mmfId) < 0) {
            //     _mmfIds.push(mmfId);
            // }

            if (mxList[i].paieId != undefined) {
                if (_paieIds[mxList[i].mmfId] == undefined)
                    _paieIds[mxList[i].mmfId] = {};
                _paieIds[mxList[i].mmfId].paieId = mxList[i].paieId;
            }
            str += "<tr>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].mmfCode == undefined ? '' : mxList[i].mmfCode) + "<span style='display: none' id='wmsMiId" + mmfId + "'>" + mxList[i].wmsMiId + "</span><span style='display: none' id='mmfId" + mmfId + "'>" + mxList[i].mmfId + "</span><span style='display: none' id='mmtId" + mmfId + "'>" + mxList[i].mmtId + "</span></td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].matName == undefined ? '' : mxList[i].matName) + "</td>";
             str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].brand == undefined ? '' : mxList[i].brand) + "</td>";
             str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='mmfName" + mmfId + "'>" + (mxList[i].mmfName == undefined ? '' : mxList[i].mmfName) + "</span>/<span id='mmfCode" + mmfId + "'>" + (mxList[i].mmfCode == undefined ? '' : mxList[i].mmfCode) + "</span></td>";
             str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].basicUnit == undefined ? '' : mxList[i].basicUnit) + "</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].avgPrice1 == undefined ? '' : mxList[i].avgPrice1) + "</td>";

            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">";
            let price = mxList[i].retailPrice == undefined ? '' : mxList[i].retailPrice;
            let readonly = '';
            if (_paId != undefined && _edit == undefined) {
                price = mxList[i].origionRetailPrice == undefined ? '' : mxList[i].origionRetailPrice;
                readonly = 'readonly';
            }
            if (_paId != undefined && _edit != undefined) {
                price = mxList[i].origionRetailPrice == undefined ? '' : mxList[i].origionRetailPrice;
            }
            // str += '<input ' + readonly + ' id="basePrice' + mxList[i].mmfId + '" data-mmfId="' + mxList[i].mmfId + '" name="basePrice" style="text-align: center; width: 50px;" value="' + (_saveObject[mxList[i].mmfId].basePrice != -1 ? toDecimal2(_saveObject[mxList[i].mmfId].basePrice) : toDecimal2(price)) + '"';
            str += '<input ' + readonly + ' id="basePrice' + mxList[i].mmfId + '" data-mmfId="' + mxList[i].mmfId + '" name="basePrice" style="text-align: center; width: 50px;" value="' + toDecimal2(price) + '"' +
                ' onblur="logPrice(' + mxList[i].mmfId + ', ' + mxList[i].wmsMiId + ')"';
            if (_paId != undefined && _edit == undefined) {
                str += ' rendonly ';
            }
            str += "</td>";

            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">";
            if (_paId != undefined && _edit == undefined) {
                readonly = 'readonly';
            }
            let per = $('#paiPercentForm').val();
            let retailPrice = (mxList[i].retailPrice == undefined ? price : mxList[i].retailPrice);
            if (per != '' && price != '') {
                retailPrice = price + price * per / 100;
            }
            str += '<input ' + readonly + ' id="retailPrice' + mxList[i].mmfId + '" data-mmfId="' + mxList[i].mmfId + '" name="retailPrice" ' + //onkeyup="$(this).val($(this).val().replace(/(-)?[^0-9.]/g,\'\'));"' +
                // ' onblur="logRetailPrice(' + mxList[i].mmfId + ')" style="text-align: center; width: 50px;" value="' + (_saveObject[mxList[i].mmfId].retailPrice != -1 ? toDecimal2(_saveObject[mxList[i].mmfId].retailPrice) : toDecimal2(retailPrice)) + '"';
                ' onblur="logRetailPrice(' + mxList[i].mmfId + ', ' + mxList[i].wmsMiId + ')" style="text-align: center; width: 50px;" value="' + toDecimal2(retailPrice) + '"';
            if (_paId != undefined && _edit == undefined) {
                str += ' rendonly ';
            }
            str += ' />';
            str += "</td>";

            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">";
            per = $('#paiPercentForm').val();
            if (mxList[i].percent != undefined)
                per = mxList[i].percent;
            let basePrice = mxList[i].retailPrice == undefined ? 0 : mxList[i].retailPrice;
            if (_paId != undefined && _edit == undefined)
                basePrice = mxList[i].origionRetailPrice == undefined ? '' : mxList[i].origionRetailPrice;
            if (_paId != undefined && _edit != undefined)
                basePrice = mxList[i].origionRetailPrice == undefined ? '' : mxList[i].origionRetailPrice;
            retailPrice = mxList[i].retailPrice == undefined ? price : mxList[i].retailPrice;
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
            str += '<span id="minus' + mxList[i].mmfId + '" data-mmfId="' + mxList[i].mmfId + '">' + toDecimal2(min) + '</span>';
            str += "</td>";

            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">";
            if (_paId != undefined && _edit == undefined) {
                readonly = 'readonly';
            }
            // let basePrice = (_saveObject[mxList[i].mmfId].basePrice != -1 ? _saveObject[mxList[i].mmfId].basePrice : mxList[i].price);
            // let retailPrice = (_saveObject[mxList[i].mmfId].retailPrice != -1 ? _saveObject[mxList[i].mmfId].retailPrice : mxList[i].retailPrice);
            minusPrice = retailPrice - basePrice;
            per = 0;
            if (CheckUtil.isInteger(basePrice) || CheckUtil.isFloat(basePrice) || basePrice != '0'){
                per = minusPrice / basePrice;
            } else {
                per = 0;
            }
            if ($('#paiPercentForm').val() != '' && mxList[i].percent == undefined) {
                per = $('#paiPercentForm').val();
            } else
                per = mxList[i].percent;
            allPer = toDecimal2(Number(allPer) + Number(toDecimal2(per)));
            str += '<input ' + readonly + ' id="percent' + mxList[i].mmfId + '" data-wmsmiid="' + mxList[i].wmsMiId + '" data-mmfId="' + mxList[i].mmfId + '"'+// onkeyup="$(this).val($(this).val().replace(/[^0-9.]/g,\'\'));"' +
                ' onfocus="logOldPer(' + mxList[i].mmfId + ')" onblur="changeSinglePer(' + mxList[i].mmfId + ', ' + mxList[i].wmsMiId + ')" style="text-align: center; width: 50px;" value="' + toDecimal2(per) + '" />%';
            str += "</td>";

            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].batchCode == undefined ? '' : mxList[i].batchCode) + "</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].backPrinting == undefined ? '' : mxList[i].backPrinting) + "</td>";

            let baseNumber = mxList[i].baseNumber == undefined ? 0 : mxList[i].baseNumber;
            let quantity = mxList[i].quantity1 == undefined ? 0 : mxList[i].quantity1;
            let ratio = mxList[i].ratio == undefined ? 1 : mxList[i].ratio;
            baseNumber = baseNumber - quantity * ratio;
            let basicUnit = mxList[i].basicUnit == undefined ? '' : mxList[i].basicUnit;
            let unit = mxList[i].splitUnit == undefined ? basicUnit : mxList[i].splitUnit;

            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + quantity + '' + basicUnit + '/' + baseNumber + '' + unit + "</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">";
            if (_paId != undefined && _edit == undefined) {}
            else
                str += "<a onclick=\"deleteEx('" + mxList[i].paieId + "'," + i + ", " + mxList[i].mmfId + ", " + mxList[i].wmsMiId+ ")\"  class='btn btn-success  btn-xs'>删除</a>&nbsp;&nbsp;";
            str += "</td>";
            str += "</tr>";
        }
    }
    $("#mxMatListMx").html(str);
    initCount();
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