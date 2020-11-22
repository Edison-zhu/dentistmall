var tips = null;
var _all_limit_number = 5;
var page = 2; //懒加载是从第二页开始
var moiIdData = {}; //记录当前页面订单的lilmit，page
var option = {}; //server,用于分页查询
var width = 30;
var allMall = true; //商城or后台：true为商城
function initOderListUtil(opt) {
    option = $.extend(true, option, opt);
}
var fileName = '';
$(function () {
    $('#returnsLabel').on('click', function () {
        $(this).css('border', 'red solid 1px');
        $('#refundLabel').css('border', 'lightgrey solid 1px');
    });
    // $('#returnsLabel').click();

    $('#refundLabel').on('click', function () {
        $(this).css('border', 'red solid 1px');
        $('#returnsLabel').css('border', 'lightgrey solid 1px');
    });


})
var data = null;
function initAfterSaleOrder(datalist, mall) {
    if (_momIds != undefined) {
        momIds += _momIds;
    }
    if (mall === undefined || mall === null) {
        mall = true;
    }
    allMall = mall;
    if (allMall === false) {
        width = 25;
    }
    var str = '';
    var orderAs = $('#afterSale').val();
    if(orderAs == '1' || orderAs == ''){
        $('#returnsLabel').click();
    }else if (orderAs == '2'){
        $('#refundLabel').click();
    }
    if (datalist != null && datalist.length > 0) {
        for (var i = 0; i < datalist.length; i++) {
            data = datalist[i];

            str += "<a class=\"blue font-weight-trigger\" href=\"" + $.supper("getbasepath") + "ddxq.htm?moiId=" + data.moiId + "\" target='_blank'><span class=\"l32\">订单号：<span id='highLightSpanDD'>" + data.orderCode + "</span></span></a>";
            if (allMall === true) {
                str += "<span>供应商：<a class=\"blue font-weight-trigger\" href=\"" + $.supper("getbasepath") + "pcjxiangxi.htm?wzId=" + data.wzId + "\" target=\"_blank\">";

                if (data.applicantName.length > 10)
                    str += data.applicantName.substring(0, 9) + "...";
                else
                    str += data.applicantName;
                str += "</a></span>";
            }
            str += "<span>下单时间：<strong>" + data.placeOrderTime.substring(0, 16) + "</strong></span>";
            str += '<div style=\'float: right;position: relative;right: 20px\'></div>';
            $('#order-header').html(str);
            var vdata = 'moiId=' + data.moiId;
            $.supper("initPagination", {
                id: "Pagination",
                service: "ShoppingService.getOrderMxModelByMoiId",
                data: vdata,
                limit: _all_limit_number,
                isAjax: "1",
                "BackE": _initAsList
            });
        }
    }
}
//此方法下调用
function _initAsList(mxList) {
    initAsListFunc(mxList, null, null, null);
}
//提供外部调用,noCtl是否显示右方的操作
function initAsList(mxList, vdata, noCtl, isMx) {
    initAsListFunc(mxList, vdata, noCtl, isMx);
}
function initAsListFunc(mxList, vdata, noCtl, isMx) {
    var str ='';
    if (mxList != null && mxList.length > 0) {
        str += "<div class=\"order-body\">";
        str += '<table style="width: 100%">'
        str += initTable(mxList, vdata, noCtl, isMx);
        str += '</table>';
        str += "</div>";
        if(noCtl === undefined || noCtl === null || noCtl !==true){
            let expressMoney = mxList[mxList.length - 1].express_money;
            _expressMoney = (expressMoney == undefined ? 0 : expressMoney);
            let money = toDecimal2(expressMoney);
            $('#expressMoney').text('￥' + (money == '--' ? '0.00' : money));
            maxMoney += _expressMoney;
            $('#maxMoney').text(toDecimal2(maxMoney));
        }
    }
    if (str == '') {
        str = '<div style="float: left;margin: 1px 44%;font-size: 15px;width: 500px;"><b>抱歉，搜索不到相关商品</b></div>';
    }
    $("#divList").html(str);
}
//仅仅生成table标签下的内容
function initTable(mxList, vdata, noCtl, isMx) {
    if(vdata === undefined || vdata === null){
        vdata = data;
    }
    var str = '';
    var recieveStr = '';
    var allRe = 0;
    var partRe = 0;
    var noRe = 0;
    var showSelectAll = false;
    // var number3 = vdata.number3 ? vdata.number3 : 0;
    for (var j = 0; j < mxList.length - 1; j++) {
        var mx = mxList[j];
        str += '<tr>';
        var width_str = 'width: 44%';
        if(noCtl === undefined || noCtl === null || noCtl !==true) {
            str += '<td style="text-align: right;vertical-align: middle;width: 3%" class="order-info">';
            width_str = 'width: 39%';
            var cheked = '';
            var shouldCheck = false;
            var id = 'id' + mx.wms_mi_id;
            if(_momIds.indexOf(mx.mom_id + ',') >= 0){
                shouldCheck = true;
            }
            if(shouldCheck == true){
                cheked = 'checked';
            // }else if(shouldCheck == false && newAs == 'false' && mx.mas_id !== undefined && (mx.after_sale == '5')){
            //     cheked = 'checked';
            } else {
                cheked = '';
            }
            if(newAs == 'false' && mx.mas_id !== undefined && (masId == mx.mas_id.toString() || mx.after_sale == '4' || mx.after_sale == '6')){
                showSelectAll = true;
                str += '<input id="' + id + '" type="checkbox" name="items" ' + cheked + ' onclick="clickAsMat(that = this, ' + mx.mom_id + ',' + toDecimal2(mx.mat_number * mx.Unit_money) + ')" />';
                maxMoney += mx.mat_number * mx.Unit_money;
            }else if(newAs == 'false' && mx.mas_id !== undefined && masId != mx.mas_id.toString()){
                // str += '<input type="checkbox" onclick="clickAsMat(this, ' + mx.mom_id + ',' + toDecimal2(mx.mat_number * mx.Unit_money) + ')" />';
            }else if(newAs == 'true' && (mx.after_sale == '4' || mx.after_sale == '6')){ //被拒绝后或撤销还能继续申请
                showSelectAll = true;
                maxMoney += mx.mat_number * mx.Unit_money;
                str += '<input id="' + id + '" type="checkbox" name="items" ' + cheked + ' onclick="clickAsMat(that = this, ' + mx.mom_id + ',' + toDecimal2(mx.mat_number * mx.Unit_money) + ')" />';
            }else if(mx.after_sale_name === undefined || mx.after_sale_name === 'undefined' || mx.after_sale_name === null || mx.after_sale_name === '') {
                showSelectAll = true;
                maxMoney += mx.mat_number * mx.Unit_money;
                str += '<input id="' + id + '" type="checkbox" name="items" ' + cheked + ' onclick="clickAsMat(that = this, ' + mx.mom_id + ',' + toDecimal2(mx.mat_number * mx.Unit_money) + ')" />';
            }
            if(newAs == 'false'){
                if(cheked == 'checked'){
                    // momIds += mx.mom_id + ',';
                }
            }else if(shouldCheck != true && cheked == 'checked'){
                // $('#' + id).click();
                // $('#' + id).attr('checked', true);
                onlyAddAsMat(mx.mom_id, toDecimal2(mx.mat_number * mx.Unit_money));
            }
            str += '</td>';
        }
        str += "<td style=\"" + width_str + "\" t2><div class=\"order-info\" style='height: auto'><div class=\"left\" style='display: inline-block;'><a href=\"" + $.supper("getbasepath") + "xiangxi.htm?wmsMiId=" + mx.wms_mi_id + "\" target=\"_blank\"><img src=\"" + mx.less_file_path + "\" width=\"68\" height=\"68\"></a>";
        str += "<ul><li class=\"name\"><a href=\"" + $.supper("getbasepath") + "xiangxi.htm?wmsMiId=" + mx.wms_mi_id + "\" target=\"_blank\">" + mx.mat_name + "</a></li>";
        str += "<li class=\"type\">型号：<strong>" + mx.NORM + "</strong></li>";
        str += '<li class="type">编号：' + mx.mmf_code + '</li>';
        str += "</ul></div></div></td>";
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span class="money">￥' + toDecimal2(mx.Unit_money) + '</span></div></td>';
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span class="number">' + mx.mat_number + '</span></div></td>';
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span class="number">' + mx.Basic_unit + '</span></div></td>';
        str += '<td class="td-text-align" style="width: 14%"><div class=\"order-info\"><span class="number">￥' + toDecimal2(mx.mat_number * mx.Unit_money) + '</span></div></td>';
        // if(isMx === undefined || isMx === null || isMx === '') {
        //     str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span style="display: inline;line-height: 98px;width: 100%"></span></div></td>'
        // } else {
        //     str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span style="display: inline;line-height: 98px;width: 100%">';
        //     if(mx.after_sale_name != undefined){
        //         str += mx.after_sale_name;
        //     }
        //     str += '</span></div></td>'
        // }
        str += '<td id="" class="td-text-align rigth-td" style="width: 20%;vertical-align: middle;">';

        if(isMx === undefined || isMx === null || isMx === '') {
            str += "<div style=\"text-align: center;display:inline-block;width: 49%;line-height: 98px\">" +
                "<span class=\"money\">" + (mx.number2 == undefined ? 0 : mx.number2) + "/" + mx.shure_number + "</span></div>";
            str += '<div class="right" style="display:inline-block;width: 50%;">';

            var number2 = mx.number2 == undefined ? 0 : mx.number2;
            if(mx.shure_number >= mx.mat_number){
                str += "<span class=\"money\">已收货</span><br/>";
            }else if (number2 <= 0) {
                str += "<span class=\"money\">待发货</span><br/>";
            } else if (number2 < mx.mat_number) {
                str += "<span class=\"money\">部分发货</span><br/>";
            } else if (number2 >= mx.mat_number) {
                str += "<span class=\"money\">已发货</span><br/>";
            }
            str += "<div style=\"text-align: center;\">" +
                "<span class=\"money\" style='color: red'>" + (mx.after_sale_name != undefined ? mx.after_sale_name : '') + "</span><br />";
            str += "</div>";

        }else if(isMx !== undefined && isMx !== null && isMx === true) {
            str += '<div class="right" style="display:inline-block;">';

            var number2 = mx.number2 == undefined ? 0 : mx.number2;
            if(mx.shure_number >= mx.mat_number){
                str += "<span class=\"money\">已收货</span><br/>";
            }else if (number2 <= 0) {
                str += "<span class=\"money\">待发货</span><br/>";
            } else if (number2 < mx.mat_number) {
                str += "<span class=\"money\">部分发货</span><br/>";
            } else if (number2 >= mx.mat_number) {
                str += "<span class=\"money\">已发货</span><br/>";
            }
            str += "<div style=\"text-align: center;\">" +
                "<span class=\"money\" style='color: red'>" + (mx.after_sale_name != undefined ? mx.after_sale_name : '') + "</span><br />";
            str += "</div>";
        }
        if(mx.shure_number >= mx.mat_number) { //已收货
            allRe++;
        }else if (mx.shure_number > 0){ //部分收货
            partRe++;
        }else if (mx.shure_number <= 0){ //未收货
            noRe++;
        }
        if(noCtl === undefined || noCtl === null || noCtl !==true){
            // str += "<div style=\"text-align: center;display:inline-block;width: " + width + "%\">";
            // if (mx.processStatus == '2') {
            //     str += "<a class=\" btn btn-primary btn-sm\" href=\"" + $.supper("getbasepath") + "ddpay.htm?moiId=" + vdata.moiId + "\">立即支付</a>";
            // } else if (vdata.processStatus == '1' || vdata.processStatus == '3' || vdata.processStatus == '4') {
            //     str += "<a class=\" btn btn-primary btn-sm\" href=\"javascript:backMat('" + vdata.moiId + "')\">退货</a>";
            // }
            // str += "</div>";
        }
        str += '</div>';
        str += '</td>';
        str += '</tr>'
        if(showSelectAll === true){
            $('#allDiv').show();
        }
        if(noCtl === undefined || noCtl === null || noCtl !==true && maxMoney !== 0){
            $('#maxMoney').text(toDecimal2(maxMoney));
        }
    }
    if(allRe > 0) {
        if(noRe > 0 || partRe > 0){
            recieveStr = '部分收货';
        } else {
            recieveStr = '已收货';
        }
    } else if (partRe > 0) {
        recieveStr = '部分收货';
    } else if (noRe > 0) {
        if(partRe > 0 || allRe > 0){
            recieveStr = '部分收货';
        } else {
            recieveStr = '未收货';
        }
    }

    if(recieveStr !== '') {
        let isRecive = $("#isRecieve");
        if (isRecive !== undefined && isRecive !== null) {
            isRecive.text(recieveStr);
        }
    }
    return str;
}

function addPage() {
    page += 1;
}

function minPage() {
    page -= 1;
    if (page <= 0) {
        page = 1;
    }
}

//懒加载方法
function onClickloadData(totalCount, moiId) {
    if (moiId === undefined || moiId === 'undefined' || moiId === null) {
        return;
    }
    // $('#load-more' + moiId).remove();
    // $('#table-mx' + moiId).remove('#load-more' + moiId);
    var pageAndLimit = 'page=' + moiIdData[moiId].page + '&limit=' + moiIdData[moiId].limit;
    var data = 'moiId=' + moiId + '&' + pageAndLimit;
    $.supper("doservice", {
        "service": option.server, "data": data, "BackE": function (jsondata) {
            var mxList = jsondata.items;
            var showCount = (moiIdData[moiId].page - 1) * limit;
            // mxList.splice(mxList.length - 1, 1);
            var mdata = moiIdData[moiId].data;
            showCount += mxList.length - 1;
            var str = initTable(mdata, mxList, true);
            $('#table-mx' + moiId).append(str);
            var rowSpan = $('#row-chang' + moiId).attr('rowspan');
            $('#row-chang' + moiId).attr('rowspan', rowSpan + mxList.length - 1);
            moiIdData[moiId].page += 1;
            $('#left-count' + moiId).text(totalCount - showCount);
            if (showCount >= totalCount) {
                $('#load-more' + moiId).remove();
            }
        }
    });
}

var momIds = '';
var totalRefund = 0;
var asTotalCount = 0;
function onlyAddAsMat(momId, refund) {
    momIds += momId + ',';
    totalRefund += Number(refund);
    asTotalCount ++;
    // $('#refundMoneInp').val(toDecimal2(totalRefund));
    $('#reMoney').text('￥' + toDecimal2(totalRefund + _expressMoney));
    $('#asMoney').text('￥' + toDecimal2(totalRefund + _expressMoney));
    $('#asCout').text(asTotalCount);
    // $('#refundMoneInp1').val(toDecimal2(totalRefund));
}
function clickAsMat(selector, momId, refund) {
    if(selector.checked === true){
        momIds += momId + ',';
        _momIds = _momIds + ',';
        totalRefund += Number(refund);
        asTotalCount ++;
    }else {
        momIds = momIds.replace(momId + ',', '');
        _momIds = _momIds.replace(momId + ',', '');
        totalRefund -= Number(refund);
        asTotalCount --;
    }

    let expressMoney = _expressMoney;
    if (asTotalCount <= 0) {
        expressMoney = 0
    }

    $('#refundMoneInp').val(toDecimal2(totalRefund + expressMoney));
    $('#reMoney').text('￥' + toDecimal2(totalRefund + expressMoney));
    $('#asMoney').text('￥' + toDecimal2(totalRefund + expressMoney));
    $('#asCout').text(asTotalCount);
    $('#refundMoneInp1').val(toDecimal2(totalRefund + expressMoney));
}

// 检查是否需要安全码，验证安全码 yangfeng 2020-08-28
var getOpenSecurityService = 'sysUserService.getOpenSecurity';
var checkSecurityCode = 'sysUserService.updateOpenSecurityState';
var checkState = ''
function applyAs(i) {
    checkState = i
    // 获取是否需要安全码
    $.supper("doservice", {
        "service": getOpenSecurityService, "ifloading": 1, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // 需要安全码则输入安全码
                fLogin.openLogin();
            } else if (jsondata.code == '2') {
                // 不需要, 直接走确认收货
                if (checkState == 1){
                    canceles()
                }else {
                    sureApplyAs();
                }
            } else if (jsondata.code == '3') {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "未登录！"
                });
            } else {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作失败！"
                });
            }
        }
    });
}

// 验证安全码，通过执行相关方法
function checkSecurityCodeFunc() {
    let securityCode = $('#securityCode').val();
    if (securityCode == '') {
        $.supper("alert", {
            title: "操作提示",
            msg: "请输入验证码！"
        });
        return;
    }
    $.supper("doservice", {
        "service": checkSecurityCode, "ifloading": 1, 'data': 'securityCode=' + securityCode, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                fLogin.closeLogin();
                if (checkState == 1){
                    canceles()
                }else {
                    sureApplyAs();
                }
            } else
                $.supper("alert", {
                    title: "操作提示",
                    msg: jsondata.meg
                });
        }
    });
}
function canceles(){
    window.location.href = 'ddlb.htm'
}
function sureApplyAs() {
    if(momIds === '' || momIds.length <= 0){
        $.supper("alert",{ title:"操作提示", msg:"请选择需要售后的商品！"});
        return;
    }
    if (fileName !== '' && fileName.length > 0){
        fileName = fileName.substring(0, fileName.length - 1);
    }
    var refund = $('#refund').prop('checked');
    var asState = 1;
    // if(refund === true){
    //     asState = 2;
    // } else {
    //     totalRefund = $('#refundMoneInp').val() + _expressMoney;
    // }
    totalRefund +=  _expressMoney;
    var listFilecode = $("#listFilecode").val();
    momIds = momIds.substring(0, momIds.length - 1);
    var vdata = 'moiId=' + moiId + '&masCode=' + masCode + '&momIds=' + momIds + '&afterSale=' + asState + '&afterSaleMoney=' + totalRefund;
    if(masId != undefined && masId != null && masId != ''){
        vdata += '&masId=' + masId;
    }
    vdata += '&' + $('#form').serialize();
    $.supper("doservice", {
        "service": "ShoppingService.saveMdOrderSaleAfter", "data": vdata, ifloading: true, "BackE": function (jsondata) {
            if(jsondata.code == '1'){
                var obj = jsondata.obj;
                var masIdN = obj.masId;
                window.location.href = 'asmx.htm?masId=' + masIdN;
            }else if (jsondata.code == '2'){
                $.supper("alert",{ title:"操作提示", msg: jsondata.meg});
            }
        }
    });
}

/**
 * 增加复选框全选/取消
 */
function selectAll(selector) {
    var checked = selector.checked;
    $("[name=items]:checkbox").each(function () {
        let _this = $(this);
        if(_this.prop('checked') !== checked) {
            $(this).click();
        }
    });
}