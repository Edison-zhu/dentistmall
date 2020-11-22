var fLogin;
var _selMdaId;
var _ddpay_action = 'ShoppingService.savePayOrder';
var _ddpay_getorder = 'ShoppingService.getOrderByMoiId';
var moiId = ''
var orderMoney = 0;
var allMoney = '';
$(function () {
    history.pushState(null, null, document.URL);
    history.length = 0;
    window.addEventListener('popstate',back_common);
    $('#olinePayLabel').on('click', function () {
        $(this).css('border', 'red solid 1px');
    });
    $('#olinePayLabel').click();
	moiId = $('#moiId').val();
	if(moiId === undefined || moiId === null || moiId == ''){
		return;
	}
    refreshMoney(moiId);

    var $gaddress = $('#addressInfo');
    var $addressMask = $('#addressId');
    fLogin = {
        /* 打开弹窗 */
        'openLogin': function () {
            $gaddress.css({'top': '200px', 'left': ($(window).width() - $gaddress.width()) / 2 + 'px'}).fadeIn();
            $addressMask.fadeIn();
        },
        /* 关闭弹窗 */
        'closeLogin': function () {
            $gaddress.fadeOut();
            $addressMask.fadeOut();
        }
    };
    $('#addressClose').click(fLogin.closeLogin);
});
function back_common() {
    history.pushState(null, null, document.URL);
}

// 检查是否需要安全码，验证安全码 yangfeng 2020-08-28
var getOpenSecurityService = 'sysUserService.getOpenSecurity';
var checkSecurityCode = 'sysUserService.updateOpenSecurityState';

function payNow() {
    console.log('获取是否需要安全码--------')
    // 获取是否需要安全码
    $.supper("doservice", {
        "service": getOpenSecurityService, "ifloading": 1, "BackE": function (jsondata) {
            console.log('获取是否需要安全码--------',jsondata)
            if (jsondata.code == "1") {
                // 需要安全码则输入安全码
                fLogin.openLogin();
            } else if (jsondata.code == '2') {
                // 不需要, 直接走确认付款
                surePayNow();
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
    console.log("123")
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
                surePayNow();
            } else
                $.supper("alert", {
                    title: "操作提示",
                    msg: jsondata.meg
                });
        }
    });
}

function surePayNow() {
    // var wechatPay = $('#wechatPay').prop('checked');
    var monthPay = $('#monthPay').prop('checked');
    if(monthPay === false){
        $.supper("alert", {title: "操作提示", msg: "请选择支付方式!"});
        return;
    }
    var data = 'moiIds=' + moiId + '&payType=' + $('#monthPay').val();
    if(allMoney !== ''){
        data += '&money=' + allMoney;
    }
    $.supper("doservice", {
        "service": _ddpay_action,
        "ifloading": 1,
        "options": {"type": "post", 'data': data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert", {
                //     title: "操作提示", msg: "操作成功!", BackE: function () {
                window.location.replace("ddpayres.htm?moiId=" + moiId)
                //     }
                // });
            } else if (jsondata.code == "3"){
                $.supper("alert", {
                    title: "操作提示", msg: jsondata.meg, BackE: function () {
                        window.location.reload();
                    }
                });
            }else {
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
                allMoney = 0;
                initOrder(jsondata.obj)
                // $('#orderCode').text(jsondata.obj.orderCode);
                // $('#orderMoney').text('¥' + toDecimal2(jsondata.obj.orderMoney));
                // $('#totalmoney').text(toDecimal2(jsondata.obj.totalMoney));
                // $('#money2').text(toDecimal2(jsondata.obj.money2 == undefined ? 0 : jsondata.obj.money2));
                // $('#money3').text(toDecimal2(jsondata.obj.money3 == undefined ? 0 : jsondata.obj.money3));
                // $('#placeOrderMoney').text(toDecimal2(jsondata.obj.orderMoney));
                // orderMoney = jsondata.obj.orderMoney;
            } else {
                $.supper("alert", {title: "操作提示", msg: "获取订单号失败!"});
            }
        }
    });
}

function initOrder(data) {
    let str = '';
    for (let index in data) {
        let item = data[index]
        str += '<div style="padding-top: 10px;padding-bottom: 10px"><div class="left">\n' +
            '\t\t\t\t\t<img width="50px" height="50px" src="../../../dentistmall/css/shopping/images/orderSucess.png">\n' +
            '\t\t\t\t</div>\n' +
            '                <div class="middle">\n' +
            '\t\t\t\t\t<h1><span class="refreshFont">订单号：<strong class="refreshFont" id="orderCode">' + item.orderCode + '</strong></span></h1>\n' +
            '\t\t\t\t\t<h3 style="font-size: 10px; color: grey;margin-top: 5px"><span>您的订单已成功提交，请尽快完成支付</span></h3>\n' +
            '                </div>\n' +
            '                <div class="right">\n' +
            '                    <span><strong id="orderMoney" class="red money">¥' + toDecimal2(item.orderMoney) + '</strong></span>\n' +
            '                </div>\n' +
            '\n' +
            '                <div style="height: auto; line-height: 20px;margin-right: 50px;float: right">\n' +
            '                    <div style="width: 100%;display: block">\n' +
            '                        <table class="xqtabl">\n' +
            '                            <tr>\n' +
            '                                <td class="right">商品总价：</td>\n' +
            '                                <td class="left">\n' +
            '                                    <strong class="red">¥<span id="totalmoney">' + item.totalMoney + '</span></strong>\n' +
            '                                </td>\n' +
            '                            </tr>\n' +
            '                            <tr>\n' +
            '                                <td class="right">店铺优惠：</td>\n' +
            '                                <td class="left">\n' +
            '                                    <strong class="red">-¥<span id="money3">' + toDecimal2(item.money3) + '</span>\n' +
            '                                    </strong>\n' +
            '                                </td>\n' +
            '                            </tr>\n' +
            '                            <tr>\n' +
            '                                <td class="right">运费(快递)：</td>\n' +
            '                                <td class="left">\n' +
            '                                    <strong class="red">¥<span id="money2">\n' +
            '                                        ' + toDecimal2(item.money2) + '</span>\n' +
            '                                    </strong>\n' +
            '                                </td>\n' +
            '                            </tr>\n' +
            '                        </table>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                <div style="clear: both"></div></div>';
        orderMoney += item.orderMoney;
        allMoney += item.orderMoney + ',';
    }
    allMoney = allMoney.substring(0, allMoney.length - 1);
    str += '<div style="text-align: right;margin-right: 46px;padding-top: 10px">\n' +
        '                               <span class="right">订单总价：</span>\n' +
        '                                <span class="left">\n' +
        '                                    <strong class="red">¥<span id="placeOrderMoney">' + toDecimal2(orderMoney) + '</span></strong>\n' +
        '                                </span>\n' +
        '                            </div>'
    $('#payinfo').html(str)

}