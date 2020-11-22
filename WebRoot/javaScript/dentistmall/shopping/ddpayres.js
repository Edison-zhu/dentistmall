var fLogin;
var _selMdaId;
var _ddpay_action = 'ShoppingService.savePayOrder';
var _ddpay_getorder = 'ShoppingService.getOrderByMoiId';
var moiId = ''
var orderMoney = 0;
$(function () {
    history.pushState(null, null, document.URL);
    history.length = 0;
    window.addEventListener('popstate', back_common);
    $('#olinePayLabel').on('click', function () {
        $(this).css('border', 'red solid 1px');
    });
    $('#olinePayLabel').click();
    moiId = $('#moiId').val();
    if (moiId === undefined || moiId === null || moiId == '') {
        return;
    }
    refreshMoney(moiId);
});

function back_common() {
    history.pushState(null, null, document.URL);
}

function viewDd() {
    location.href = 'ddlb.htm';
}

function payNow() {
    // var wechatPay = $('#wechatPay').prop('checked');
    var monthPay = $('#monthPay').prop('checked');
    if (monthPay === false) {
        $.supper("alert", {title: "操作提示", msg: "请选择支付方式!"});
        return;
    }
    var data = 'moiIds=' + moiId + '&payType=' + $('#monthPay').val();
    if (orderMoney !== undefined) {
        data += '&money=' + orderMoney;
    }
    $.supper("doservice", {
        "service": _ddpay_action,
        "ifloading": 1,
        "options": {"type": "post", 'data': data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {
                    title: "操作提示", msg: "操作成功!", BackE: function () {
                        window.location.replace("ddpayres.htm?moiId=" + moiId)
                    }
                });
            } else if (jsondata.code == "3") {
                $.supper("alert", {
                    title: "操作提示", msg: jsondata.meg, BackE: function () {
                        window.location.reload();
                    }
                });
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败!"});
            }
        }
    });
}

//刷新价格
function refreshMoney(_moiId) {
    if (_moiId === undefined) {
        _moiId = moiId;
    }
    var data = 'moiIds=' + _moiId;
    $.supper("doservice", {
        "service": _ddpay_getorder,
        "options": {"type": "post", 'data': data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                orderMoney = 0;
                initPayres(jsondata.obj)
                // $('#orderCode').text(jsondata.obj.orderCode);
                // $('#orderMoney').text('¥' + jsondata.obj.orderMoney);
                // $('#payTypeName').text(jsondata.obj.payTypeName);
                // orderMoney = jsondata.obj.orderMoney;
            } else {
                $.supper("alert", {title: "操作提示", msg: "获取订单号失败!"});
            }
        }
    });
}

function initPayres(data) {
    let str = '';
    for (let index in data) {
        let item = data[index]
        str += '<div style="padding-top: 10px;padding-bottom: 10px"><div style="display: inline-block;width: 60%">\n' +
            '                    <div class="left">\n' +
            '                        <img width="50px" height="50px" src="../../../dentistmall/css/shopping/images/orderSucess.png">\n' +
            '                    </div>\n' +
            '                    <div class="middle">\n' +
            '                        <h1><span class="refreshFont">订单号：<strong class="refreshFont" id="orderCode">' + item.orderCode + '</strong></span>\n' +
            '                        </h1>\n' +
            '                        <h3 style="font-size: 10px; color: grey;margin-top: 5px"><span>您的订单已成功支付，商家会尽快为您发货</span></h3>\n' +
            '                    </div>\n' +
            '                    <div class="btn-style">\n' +
            '                        <button class="saveOrder font-weight-trigger" onclick="viewDd()">立即查看订单</button>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                <div class="" style="display: inline-block;vertical-align: text-top;line-height: 48px;">\n' +
            '                    <div>\n' +
            '                        支付方式：\n' +
            '                        <span id="payTypeName">' + item.payTypeName + '</span>\n' +
            '                    </div>\n' +
            '                    <span><strong id="orderMoney" class="red money">¥' + toDecimal2(item.orderMoney) + '</strong></span>\n' +
            '                </div>\n' +
            '                <div style="clear: both"></div></div>'
        orderMoney += item.orderMoney;
    }
    // str += '<div style="text-align: right;">\n' +
    //     '                               <span class="right">订单总价：</span>\n' +
    //     '                                <span class="left">\n' +
    //     '                                    <strong class="red">¥<span id="placeOrderMoney">' + toDecimal2(orderMoney) + '</span></strong>\n' +
    //     '                                </span>\n' +
    //     '                            </div>'
    $('#allOrderMoney').text('￥' + toDecimal2(orderMoney))
    $('#payinfo').html(str)
}