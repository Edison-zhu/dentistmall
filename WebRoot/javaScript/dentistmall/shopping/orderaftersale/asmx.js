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

    $('#expressSelect').on('click', function () {
        if(this.checked == true){
            $('#expressInfo').show();
            $('#applyAsAddress1').hide();
        } else {
            $('#expressInfo').hide();
            $('#applyAsAddress1').show();
        }
    })
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
// 检查是否需要安全码
var getOpenSecurityService = 'sysUserService.getOpenSecurity';
var checkSecurityCode = 'sysUserService.updateOpenSecurityState';
var moiIds=''
var masIds=''
var masCodes=''
var isTrue = ''
function editAppli(moiId,masId,masCode,i) {
    moiIds = moiId
    masIds = masId
    masCodes = masCode
    isTrue = i
    // 获取是否需要安全码
    $.supper("doservice", {
        "service": getOpenSecurityService, "ifloading": 1, "BackE": function (jsondata) {
            console.log('获取是否需要安全码--------',jsondata)
            if (jsondata.code == "1") {
                // 需要安全码则输入安全码
                fLogin.openLogin();
            } else if (jsondata.code == '2') {
                // 不需要
                if (i==1){
                    editApplication(moiId,masId,masCode);
                }else {
                    cancelApplyAs(moiId)
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
                if (isTrue == 1){
                    editApplication(moiIds,masIds,masCodes);
                }else {
                    cancelApplyAs(moiIds)
                }

            } else
                $.supper("alert", {
                    title: "操作提示",
                    msg: jsondata.meg
                });
        }
    });
}
function editApplication(moiId,masId,masCode){
     window.location.href='asapply.htm?moiId='+moiId+'&masId='+masId+'&masCode='+masCode
    // console.log(moiId,masId,masCode)
}
function initMxList() {
    var str = "";
    var vdata = "masId=" + masId;
    $.supper("initPagination", {
        id: "Pagination",
        service: "ShoppingService.getMdOrderSaleAfterMxByMasId",
        data: vdata,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initASMXList
    });
}

function initASMXList(datalist) {
    initAsList(datalist, null, true, true);
}
//编辑售后信息
function editASAddress() {
    $('#expressName').show();
    $('#expressCode').show();
    $('#expressNameSpan').hide();
    $('#expressCodeSpan').hide();
    $('#editAsAddress').hide();
    $('#cancelAsAddress').show();
    $('#applyAsAddress').show();
}
function cancelASAddress() {
    $('#expressName').hide();
    $('#expressCode').hide();
    $('#expressNameSpan').show();
    $('#expressCodeSpan').show();
    $('#editAsAddress').show();
    $('#cancelAsAddress').hide();
    $('#applyAsAddress').hide();
}
// 提交售后信息
function applyASAddress() {
    if($('#expressSelect').prop('checked') == true){
        if($('#expressName').val() == ''){
            $.supper("alert",{ title:"操作提示", msg:"请填写快递名！"});
            return;
        }
        if($('#expressCode').val() == ''){
            $.supper("alert",{ title:"操作提示", msg:"请填写运单号！"});
            return;
        }
    } else {
        $('#expressName').val('');
        $('#expressCode').val('');
    }

    var data = $('#expressForm').serialize();
    data += '&masId=' + masId;
    $.supper("doservice", {
        'service': "ShoppingService.saveApplyASAddress", 'data': data, "BackE": function (jsondata) {
            if(jsondata.code == '1') {
                var obj = jsondata.obj;
                $('#applyAsAddress').hide();
                $('#expressNameSpan').text(obj.expressName);
                $('#expressNameSpan').show();
                $('#expressName').hide();

                $('#expressCodeSpan').text(obj.expressCode);
                $('#expressCodeSpan').show();
                $('#expressCode').hide();
                $.supper("alert",{ title:"操作提示", msg: '提交成功'});
            } else if(jsondata.code == '2'){
                $.supper("alert",{ title:"操作提示", msg: jsondata.meg});
            }
            cancelASAddress();
        }
    });
}

//取消申请
function cancelApplyAs(moiIdT) {
    var data = 'masId=' + masId + '&moiId=' + moiIdT;
    $.supper("confirm", {
        title: "撤销操作", msg: "确认撤销申请？", yesE: function () {
            $.supper("doservice", {
                'service': "ShoppingService.deleteApplyAfterSale", 'data': data, "BackE": function (jsondata) {
                    if (jsondata.code == '1') {
                        window.location.reload();
                    } else if (jsondata.code == '2'){
                        $.supper("alert",{ title:"操作提示", msg: jsondata.meg, BackE:function () {
                            window.location.reload();
                        }});
                    }
                }
            });
        }
    });

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

function initAsMxList(datalist){
    if(datalist.length <= 0){
        return;
    }
    var str = '';
    datalist.forEach(function (item, key) {
        str += "<div class='mx-div'>";
        str += '<strong>' + item.orgName + ': </strong>' + '于<strong style="color: red;">' + item.ctlDate + '</strong>' + item.buyerCtlContent + '<br/>';
        str += '<strong>售后服务: </strong>' + item.asName + '<br/>';
        str += '<strong>退款金额: </strong><span style="color: red">￥' + toDecimal2(item.as_money) + '</span><br/>';
        str += '<strong>说明: </strong>' + item.buyerCtlContent + '<br/>';
        str += '</div>';
    })
    $('#asList').html(str);
}
//导出售后商品
function exportLierAsmx(){
	masId = $('#masId').val();
	vdata = "masId="+masId;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderAfterSaleService.exportLierAsmx","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}