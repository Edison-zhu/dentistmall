var masCode = null;
var masId = null;
var moiId = null;
var _all_limit_number = 5;
$(function () {
    moiId = $('#moiId').val();
    masCode = $('#masCode').val();
    masId = $('#masId').val();
    initMxList();

    initAsMxListAction();
});

function initMxList() {
    var str = "";
    var vdata = "masId=" + masId;
    $.supper("initPagination", {
        id: "Pagination",
        service: "MdOrderAfterSaleService.getMdOrderSaleAfterMxByMasId",
        data: vdata,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initASMXList
    });
}

function initASMXList(datalist) {
    initAsList(datalist, null, true);
}

function editASAddress() {
    agreeReturnBack();
}

//同意退货
function agreeReturnBack() {
    var title = '请输入收货地址';
    var str = '<div id="supplier-address" > <!-- 同意退货填写收货地址 -->\n' +
        '    <form class="layui-form">\n' +
        '       <div class="layui-form-item">' +
        '        <label class="layui-form-label">收货人姓名：</label>\n' +
        '        <div class="layui-input-block">' +
        '         <input type="text" required  lay-verify="required" lay-reqtext="请填写收货人姓名" autocomplete="off" class="layui-input" id="supplierName" name="supplierName"/>\n' +
        '        </div>' +
        '       </div>' +
        '       <div class="layui-form-item">' +
        '        <label class="layui-form-label">收货人电话：</label>\n' +
        '        <div class="layui-input-block">' +
        '         <input type="tel" required  lay-verify="required" lay-verify="required|phone" lay-reqtext="请填写收货人电话" autocomplete="off" class="layui-input" id="supplierPhone" name="supplierPhone"/>\n' +
        '        </div>' +
        '       </div>' +
        '       <div class="layui-form-item">' +
        '        <label class="layui-form-label">收货地址：</label>\n' +
        '        <div class="layui-input-block">' +
        '         <input type="text" required  lay-verify="required" autocomplete="off" lay-reqtext="请填写收货人地址" class="layui-input" id="supplierAddress" name="supplierAddress"/>\n' +
        '        </div>' +
        '       </div>' +
        '    </form>\n' +
        '</div>';
    layer.open({
        title: title,
        area: '500px',
        content: str,//$('#supplier-address'),
        closeBtn: 2,
        btn: ['提交', '取消'],
        yes: function (index, layero) {
            var supplierName = $('#supplierName').val();
            var supplierPhone = $('#supplierPhone').val();
            var supplierAddress = $('#supplierAddress').val();
            if (supplierName === '' || supplierPhone === '' || supplierAddress === '') {
                $.supper("alert", {title: "操作提示", msg: "必填项存在空，请检查！"});
                return;
            }
            if (CheckUtil.isMobile(supplierPhone) == false) {
                $.supper("alert", {title: "操作提示", msg: ("收货人电话填写有误！")});
                return false;
            }
            var vdata = 'supplierName=' + supplierName + '&supplierPhone=' + supplierPhone + '&supplierAddress=' + supplierAddress;
            AsAction(1, vdata);
        },
        btn2: function (index, layero) {

        }
    })
}

//同意退款
function agreeRefund() {
    $.supper("confirm", {
        title: "退款操作", msg: "您是否确认同意退款？", yesE: function () {
            AsAction(3);
        }
    });
}

//确认收货
function ensureGet() {
    $.supper("confirm", {
        title: "收货操作", msg: "确认已收到退货？", yesE: function () {
            AsAction(2);
        }
    });
}

//确认已退款
function ensureRefund() {
    $.supper("confirm", {
        title: "退款操作", msg: "确认已退款？", yesE: function () {
            AsAction(3);
        }
    });
}

//拒绝售后{
function refuseAs() {
    var title = '请输入拒绝理由';
    var str = '<div id="refuse-as"> <!-- 拒绝售后 -->\n' +
        '    <label>请输入拒绝理由：</label>\n' +
        '    <textarea class="layui-textarea" id="refuse"></textarea>\n' +
        '</div>';
    layer.open({
        area: '500px',
        title: title,
        content: str, //$('#refuse-as'),
        closeBtn: 2,
        btn: ['提交', '取消'],
        yes: function (index, layero) {
            var refuse = $('#refuse').val();
            var vdata = null;
            if (refuse !== '') {
                vdata = 'refuse=' + refuse;
            }
            AsAction(4, vdata);
        },
        btn2: function (index, layero) {

        }
    })
}

function AsAction(asState, data) {
    if (masId === undefined || masId === null || masId === '') {
        return;
    }
    var vdata = "masId=" + masId + '&asState=' + asState;
    if (vdata !== undefined && vdata !== null && vdata !== '') {
        vdata += '&' + data;
    }
    $.supper("doservice", {
        "service": 'MdOrderAfterSaleService.updateMdOrderSaleAfterState', "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == '1') {
                checkOrder(asState, jsondata.obj, masId)
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        }
    });
}

function checkOrder(asState, moiId, masId) {
    $.supper("doservice", {
        "service": 'MdOrderAfterSaleService.updateMdOrderByAfterState',
        "data": 'moiId=' + moiId,
        "BackE": function (jsondata) {
            if (jsondata.code == '1') {
                var vdata = "masId=" + masId;
                var att_url = $.supper("getServicePath", {
                    "service": "MdOrderAfterSaleService.getMdIrderSaleAfterObjectByMasId",
                    "data": vdata,
                    url: "/jsp/dentistmall/transaction/orderaftersale/supplierasmx"
                });
                // if(asState == 4) {
                    $.supper("closeTtemWin", {"url": att_url, "title": "售后明细"});
                // }else {
                //     setTimeout(function () {
                //         $.supper("showTtemWin", {"url": att_url, "title": "售后明细"});
                //     }, 500);
                //     $.supper("closeTtemWin", {"url": att_url, "title": "售后明细"});
                // }

            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        }
    });
}

//查看订单
function viewOrder(moiId) {
    var vdata = "moiId=" + moiId;
    var att_url = $.supper("getServicePath", {
        "service": "MdOrderInfoService.doFindObject",
        "data": vdata,
        url: "/jsp/dentistmall/transaction/viewSupplierOrderInfo"
    });
    $.supper("showTtemWin", {"url": att_url, "title": "查看订单"});
}

function initAsMxListAction() {
    var data = 'masId=' + masId;
    $.supper("initPagination", {
        id: "AsPagination",
        service: "ShoppingService.getMdOrderSaleAfterExPageModel",
        data: data,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initAsMxList
    });
}

function initAsMxList(datalist) {
    if (datalist.length <= 0) {
        return;
    }
    var str = '';
    datalist.forEach(function (item, key) {
        str += "<div class='mx-div'>";
        str += '<strong>' + item.orgName + ': </strong>' + '于<strong style="color: red">' + item.ctlDate + '</strong>' + item.asRemarks + '<br/>';
        str += '<strong>售后服务: </strong>' + item.asName + '<br/>';
        str += '<strong>退款金额: </strong><span style="color: red">￥' + toDecimal2(item.as_money) + '</span><br/>';
        str += '<strong>说明: </strong>' + item.supplierCtlContent + '<br/>';
        str += '</div>';
    })
    $('#asList').html(str);
}

//导出售后商品
function exportLierAsmx() {
    masId = $('#masId').val();
    vdata = "masId=" + masId;
    var newTab = window.open('about:blank');
    $.supper("doservice", {
        "service": "MdOrderAfterSaleService.exportLierAsmx", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}