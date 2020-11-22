var _all_deleteAction = "SysSalesmanService.saveSalesman";
var _all_updateAction = "SysSalesmanService.updateSysSalesmanBySalesId";
var salesState = null;
var _rbaId;
var _rbsId;
var _rbbId;
var idType;
var rId;
var _all_table_Id;
var all_action;
var salesmanId;
var _salesCode;
var _salesman_url = '/dentistmall/jsp/dentistmall/salesman/salesman.jsp';
var _all_this_win_url = '/dentistmall/jsp/dentistmall/salesman/addOrUpdateSalesman.jsp';
_all_edit_orderview_title = '业务员管理';
var _current_win_url;
var openSalesList;
var backTo = true;
var _salesType = 1;
var _salesUserType;
$(function () {
    salesmanId = $('#salesmanId').val();
    salesState = $('#salesState').val();
    _salesCode = $('#salesCode').val();
    _salesType = $('#salesTypeHidden').val();
    _salesUserType = $('#userType').val();
    _rbaId = $('#rbaId').val();
    _rbsId = $('#rbsId').val();
    _rbbId = $('#rbbId').val();

    if (salesState == 1) {
        $('#stateOn').prop('checked', 'checked');
    } else if (salesState == 2) {
        $('#stateOff').prop('checked', 'checked');
    }

    if(_salesUserType != 6) {
        $('#saleType6').show();
        if(_salesType != 2) {
            $('#salesMan').prop('checked', 'checked');
        } else {
            $('#agent').prop('checked', 'checked');
        }
    } else {
        $('#agent').prop('checked', 'checked');
        $('#saleType6').hide();
    }
    _current_win_url = $.supper("getProductArray", "menuItemSalesmanUrl").url;
    var isEdit = $.supper("getProductArray", "menuItemSalesmanUrl").isEdit;
    initForm();

    openSalesList = false;
    if (isEdit === true) {
        openSalesList = true;
    }
});


function saveSalesman() {
    var account = $('#salesAccount').val();
    if (account == '') {
        $.supper("alert", {
            title: "操作提示", msg: "请输入账户！"
        });
        return;
    }
    if (CheckUtil.isDigit(account) == true) {
        $.supper("alert", {
            title: "操作提示", msg: "账户不能全为数字！"
        });
        return;
    }
    // if (CheckUtil.isLetter(account) == true) {
    //     $.supper("alert", {
    //         title: "操作提示", msg: "账户必须数字和字母混合！"
    //     });
    //     return;
    // }
    if ($('#salesName').val() == '') {
        $.supper("alert", {
            title: "操作提示", msg: "请输入姓名！"
        });
        return;
    }
    var name = $('#salesName').val();
    if (name == '') {
        $.supper("alert", {
            title: "操作提示", msg: "请输入姓名！"
        });
        return;
    }
    if (name.length > 12 || name.length < 2) {
        $.supper("alert", {
            title: "操作提示", msg: "姓名长度必须在2-12位！"
        });
        return;
    }
    if ($('#salesPhone').val() == '') {
        $.supper("alert", {
            title: "操作提示", msg: "请输入手机号！"
        });
        return;
    }
    let ywy = '确认保存吗？';
    if($('#agent').is(':checked')) {
        if($('#agentCompany').val() == '') {
            $.supper("alert", {
                title: "操作提示", msg: "请输入公司名称！"
            });
            return;
        }

        if(_user_type != '6') {
            if ($('#bindSalesMan').val() == '') {
                $.supper("alert", {
                    title: "操作提示", msg: "请选择上级！"
                });
                return;
            }
            if ($('#bindSalesmanId').val() == '') {
                $.supper("alert", {
                    title: "操作提示", msg: "上级人员选择有误，请重新选择！"
                });
                return;
            }
        }
    } else {
        hideCompany();
    }

    if (CheckUtil.isMobile($('#salesPhone').val()) == false) {
        $.supper("alert", {title: "操作提示", msg: "手机号填写有误！"});
        return false;
    }
    if ($('#province').val() == '' || $('#city').val() == '' || $('#area').val() == '') {
        $.supper("alert", {title: "操作提示", msg: "请选择完整的区域！"});
        return false;
    }
    var vdata = $('#searchForm').serialize();
    var state = 2;
    var salesType = 1;
    if ($('#stateOn').prop('checked') === true) {
        state = 1;
    }
    if ($('#stateOff').prop('checked') === true) {
        state = 2;
    }
    if ($('#salesMan').prop('checked') === true) {
        salesType = 1;
    }
    if ($('#agent').prop('checked') === true) {
        salesType = 2;
    }
    if ($('#supplierSales').prop('checked') === true) {
        salesType = 3;
    }
    vdata += '&state=' + state;
    vdata += '&salesType=' + salesType;
    if (salesmanId !== undefined && salesmanId !== null && salesmanId !== '') {
        vdata += '&salesmanId=' + salesmanId;
    }

    if (openSalesList === true) {//修改操作
        $.supper("confirm", {
            title: "操作提示", msg: ywy, yesE: function () {
                $.supper("doservice", {
                    "service": _all_updateAction, "data": vdata, "BackE": function (jsondata) {
                        if (jsondata.code == "1") {
                            sureClose();
                        }else if (jsondata.code == '2') {
                            $.supper("alert", {title: "操作提示", msg: jsondata.meg});
                        }else if (jsondata.code == '3') {
                            $.supper("alert", {title: "操作提示", msg: jsondata.meg});
                        } else
                            $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                    }
                });
            }
        });
    } else {
        $.supper("confirm", {
            title: "操作提示", msg: ywy, yesE: function () {
                $.supper("doservice", {
                    "service": _all_deleteAction, "data": vdata, "BackE": function (jsondata) {
                        if (jsondata.code == "1") {
                            openSalesList = true
                            sureClose();
                            // addNew();
                        } else if (jsondata.code == '2') {
                            $.supper("alert", {title: "操作提示", msg: jsondata.meg});
                        } else
                            $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                    }
                });
            }
        });
    }
}

function addNew() {
    $.supper("confirm", {
        title: "操作提示", msg: "添加成功，是否要添加新的业务员？",
        yesE: function () {
            $(':input', '#searchForm')
                .not(':button, :submit, :reset')
                .val('');
            salesmanId = '';
            salesState = '';
            _salesCode = '';
            _rbaId = '';
            _rbsId = '';
            _rbbId = '';
            cleanFormWin();
        },
        noE: function () {
            closeThisView();
        }
    });
}

function sureClose() {
    $.supper("alert", {
        title: "操作提示", msg: "操作成功！",
        BackE: function () {
            closeThisView();
        },
        // noE: function () {
        // }
    });
}

function closeWin() {
    var account = $('#salesAccount').val();
    if (account != '') {
        $.supper("confirm", {
            title: "操作提示", msg: "账号不为空，是否需要放弃操作？",
            yesE: function () {
                closeThisView();
            },
            noE: function () {
            }
        });
        return;
    }
    closeThisView();
}

function closeThisView() {
    if (openSalesList === true) {
        $.supper("showTtemWin", {"url": _salesman_url, "title": _all_edit_orderview_title});
    }
    setTimeout(function () {
        if (openSalesList === true) {
            $.supper("setProductArray", {"name": "menuItemSalesmanUrl", "value": ''});
            $.supper("closeTtemWin", {url: _current_win_url});
        } else {
            $.supper("closeTtemWin", {url: _all_this_win_url});
        }
    }, 100)
}

function cleanFormWin() {
    // rId = $.supper("getParam", 'rId');
    // all_action = $.supper("getParam", 'action');
    // idType = $.supper("getParam", 'idType');
    // _all_winform.xform('createForm', _saveForm);
    $('#province').change(function () {
        initShi();
    });

    $('#city').change(function () {
        initArea();
    });
    initShen(null);
    initShi(null);
    initArea(null);
    //初始化业务员编号
    if (_salesCode === undefined || _salesCode === null || _salesCode === '') {
        $.supper("doservice", {
            "service": "SysParameterService.getNewCode",
            "options": {
                "type": "post",
                "data": "prefix=YWY",
                "async": false
            }, "ifloading": 1, "BackE": function (jsondata) {
                if (jsondata.code == "1" && jsondata.obj) {
                    $('#salesCode').val(jsondata.obj);
                    $('#salesCodeSpan').text(jsondata.obj);
                }
            }
        });
    }
}

function initForm() {
    cleanFormWin();
    if (salesmanId != undefined && salesmanId != null && salesmanId != "") {
        initShen($('#provinceHidden').val());
        initShi($('#cityHidden').val());
        initArea($('#areaHidden').val());
        // _all_accountForm.xform('loadAjaxForm', {
        //     'ActionUrl': all_action, "data": att_data, "BackE": function (data) {
        //         initShi(data.obj.city);
        //         initArea(data.obj.area);
        //     }
        // });
    }
}

function initShen(selProvince) {
    var str = "<option value=\"\">所在省</option>";
    for (var i = 0; i < shqJson.length; i++) {
        str += "<option value=\"" + shqJson[i].name + "\">" + shqJson[i].name + "</option>";
    }
    $("#province").html(str);
    if (selProvince != null && selProvince != "")
        $("#province").val(selProvince);
}

function initShi(selCity) {
    var selSheng = $("#province").val();
    var str = "<option value=\"\">所在市</option>";
    for (var i = 0; i < shqJson.length; i++) {
        if (shqJson[i].name == selSheng) {
            for (var j = 0; j < shqJson[i].city.length; j++) {
                str += "<option value=\"" + shqJson[i].city[j].name + "\">" + shqJson[i].city[j].name + "</option>";
            }
            break;
        }

    }
    $("#city").html(str);
    if (selCity != null && selCity != "")
        $("#city").val(selCity);
}

function initArea(selArea) {
    var selSheng = $("#province").val();
    var selShi = $("#city").val();
    var str = "<option value=\"\">所在区、县</option>";
    for (var i = 0; i < shqJson.length; i++) {
        if (shqJson[i].name == selSheng) {
            for (var j = 0; j < shqJson[i].city.length; j++) {
                if (shqJson[i].city[j].name == selShi) {
                    for (var k = 0; k < shqJson[i].city[j].area.length; k++) {
                        str += "<option value=\"" + shqJson[i].city[j].area[k] + "\">" + shqJson[i].city[j].area[k] + "</option>";
                    }
                    break;
                }

            }
            break;
        }
    }
    $("#area").html(str);
    if (selArea != null && selArea != "")
        $("#area").val(selArea);
}

var _rbbId;
var _all_table_Id = "rbaId";
var _all_questAction = "MdCompanyGroupService.findFormObject";
//打开业务员列表
function openSalesMan() {
    var vdata = 'idType=' + _all_table_Id + '&action=' + _all_questAction;;
    if(_rbbId != undefined && _rbbId != null && _rbbId != ''){
        vdata += '&rId=' + _rbbId;
    }
    var att_url= $.supper("getServicePath", {"data":vdata,url:"/jsp/dentistmall/salesman/choosesalesman"});
    $.supper("setProductArray", {"name":"excluldeId", "value":{excluldeId: salesmanId, hideType: true, excludeType: 2}});
    $.supper("setProductArray", {"name":"salesOpen", "value": true});
    var tt_win=$.supper("showWin",{url:att_url,title:"业务员信息",icon:"fa-bars",width:"1080",height: '570', BackE:addSalesManToInput});
}
function addSalesManToInput() {
    var selSalesManList = $.supper("getProductArray", "selSalesMan");
    if(selSalesManList != null && selSalesManList.length > 0 && selSalesManList.indexOf(',') >= 0){
        var salesmanDataArray = selSalesManList.split(',');
        var salesmanName = salesmanDataArray[1];
        var salesmanId = salesmanDataArray[0];
        $('#bindSalesMan').val(salesmanName);
        $('#bindSalesmanId').val(salesmanId);
        $.supper("setProductArray", {"name":"selSalesMan", "value":null});
    }
}

function showCompany() {
    $('#salesCompanyShow').show();
    $('#leaderShow').show();
}

function hideCompany() {
    $('#agentCompany').val('');
    $('#bindSalesmanId').val('');
    $('#bindSalesMan').val('');
    $('#salesCompanyShow').hide();
    $('#leaderShow').hide();
}