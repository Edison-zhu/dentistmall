var fLogin;
var _selMdaId;
var _delete_carts_ddqr_action = 'ShoppingService.deleteMdCartsByMmfId';
var _savebuy = 'shoppingService.savePurchased';
$(function () {
	initMatList();
	initAddressList();
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
    //$('#m-login').click(fLogin.openLogin); //打开弹窗监听
    $('#addressClose').click(fLogin.closeLogin); //关闭弹窗监听
});

function initAddressList() {
    var str = "";
    $.supper("doservice", {
        "service": "MdDeliveryAddressService.getMdDeliveryAddressByUser", "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                var addressList = jsondata.obj;
                if (addressList != null && addressList.length > 0) {
                    var aa = $.supper("getbasepath");
                    for (var i = 0; i < addressList.length; i++) {
                        var address = addressList[i];
                        if (i == 0) {
                            _selMdaId = address.mdaId;
                            str += "<div id='firstaddress' class=\"address-items active\" onclick=\"selAddress(this,'" + address.mdaId + "')\">";
                        } else
                            str += "<div class=\"address-items\" onclick=\"selAddress(this,'" + address.mdaId + "')\">";
                        str += "<p><i class=\"icon-people\"></i>" + address.addressee + "</p>";
                        str += "<p><i class=\"icon-address\"></i>" + address.province + address.city + address.area + "</p>";
                        str += "<p><i class=\"icon-phone\"></i>" + address.addresseeTelephone + "</p>";
                        str += "<a class=\"blue font-weight-trigger\" href=\"javascript:editAddress('" + address.mdaId + "')\">修改</a>";
                        str += "</div>";
                    }
                }
                str += "<div class=\"address-items-add\" onclick=\"editAddress()\">+</div>";
                $("#addressList").html(str);
                juggeAddressNum();
                initText($('#firstaddress'));
            }
        }
    });
}

var _dataList;

function initMatList() {
    var mmfIds = $("#mmfIds").val();
    var shus = $("#shus").val();
    if (mmfIds != null && mmfIds != "") {
        var vdata = "mmfIds=" + mmfIds + "&shus=" + shus;
        var str = "";
        $.supper("doservice", {
            "service": "ShoppingService.getCarMatInfo", "data": vdata, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    _dataList = jsondata.obj;
                    if (_dataList != null && _dataList.length > 0) {
                        var allCount = 0;
                        var expressMoneyAll = 0;
                        for (var i = 0; i < _dataList.length; i++) {
                            var mdSupplier = _dataList[i].mdSupplier;
                            var matList = _dataList[i].matList;
                            var noExpressMoney = mdSupplier.noExpressMoney != null ? mdSupplier.noExpressMoney : 0;
                            var expressMoney = mdSupplier.expressMoney != null ? mdSupplier.expressMoney : 0;
                            if(wmsMiIdCount[mdSupplier.wzId] === undefined){
                                wmsMiIdCount[mdSupplier.wzId] = {};
                            }
                            wmsMiIdCount[mdSupplier.wzId].noExpressMoney = noExpressMoney;
                            wmsMiIdCount[mdSupplier.wzId].expressMoney = expressMoney;
                            str += "<div class=\"order\"><div class=\"order-header\"><div class=\"order-header-left\">";
                            str += "<label>供应商：<span class=\"supplier font-weight-trigger\">" + mdSupplier.applicantName + "</span></label></div>";
                            str += "<div class=\"order-header-right\"><span class=\"order-header-item\">单价（元）</span>";
                            str += "<span class=\"order-header-item\">数量</span><span class=\"order-header-item\">小计（元）</span></div></div>";
                            str += "<div class=\"order-body\">";
                            var sCount = 0;
                            var matCountMoney = 0;
                            var shu = 0;
                            if (matList != null && matList.length > 0) {
                                for (var j = 0; j < matList.length; j++) {
                                    var mat = matList[j];
                                    if(wmsMiIdCount[mdSupplier.wzId][mat.wmsMiId] === undefined){
                                        wmsMiIdCount[mdSupplier.wzId][mat.wmsMiId] = {};
                                    }
                                    wmsMiIdCount[mdSupplier.wzId][mat.wmsMiId].price = mat.price;
                                    str += "<div class=\"order-info\"><div class=\"left\"><img src=\"" + mat.lessenFilePath + "\" width=\"68\" height=\"68\"><ul>";
                                    str += "<li class=\"name\"><a href=\"xiangxi.htm?wmsMiId=" + mat.wmsMiId + "\" target=\"_blank\">" + mat.matName + "</a></li>";
                                    str += "<li class=\"type\">型号：<strong>" + mat.mmfName + "</strong></li></ul></div>";
                                    str += "<div class=\"right\"><div class=\"money\">￥" + toDecimal2(mat.price) + "</div>" +
                                        "<div class=\"number\"><span>" +
                                        '<a class="a-left" value="-" id="' + mat.wmsMiId + 'Min" onclick="minOutOrderNum(' + mat.wmsMiId + ',' + mdSupplier.wzId + ')" >-</a>' +
                                        "<input class='' type=\"text\" id=\"InpMat" + mat.wmsMiId + "\" onmousedown='onMouseDwonOrderNum(" + mat.wmsMiId + "," + mdSupplier.wzId + ")' onkeyup=\"onKeyUpOrderNum(" + mat.wmsMiId + "," + mdSupplier.wzId + ")\" min='1'  maxlength=\"4\" value='" + mat.countShu + "'/>" +
                                        '<a class="a-right" value="+" id="' + mat.wmsMiId + 'Add" onclick="addnOutOrderNum(' + mat.wmsMiId + ',' + mdSupplier.wzId + ')" >+</a>' +
                                        "</span></div>";
                                    str += "<div class=\"money red\">￥<span id='littleAll" + mat.wmsMiId + "'>" + toDecimal2(mat.countMoney) + "</span></div></div></div>";
                                    sCount += parseFloat(mat.countMoney);
                                    allCount += parseFloat(mat.countMoney);
                                    matCountMoney += parseFloat(mat.countMoney);
                                    shu += Number(mat.countShu);
                                }
                            }
                            if (noExpressMoney == 0 && expressMoney > 0) {
                                mdSupplier.expressAll = expressMoney;
                                sCount += expressMoney;
                                allCount += parseFloat(expressMoney);
                            } else if (noExpressMoney > 0 && expressMoney > 0 && sCount < noExpressMoney) {
                                mdSupplier.expressAll = expressMoney;
                                sCount += expressMoney;
                                allCount += parseFloat(expressMoney);
                            } else
                                mdSupplier.expressAll = 0;

                            str += "</div></div><div class=\"note\"><label class=\"note-label\">买家留言：</label>"
                                + "<textarea class=\"note-textarea\" placeholder=\"在此输入给商家的留言\" id=\"area_" + mdSupplier.wzId + "\"></textarea>";

                            str += "</div>";
                            expressMoneyAll += Number(mdSupplier.expressAll);
                            if (_dataList.length == 1) {
								str += '<div style="display: none">' +
									'<label class="note-label" style="margin-top: 10px">支付方式：</label>' +
									'<div class="form-inline" style="display: inline-block; margin-top: 10px">' +
									'<label id="olinePayLabel" style="border-radius: 4px;" class="radio-info radio-inline form-control" for="onlinePay"><input type="radio" id="onlinePay" name="payRadio" value="1" />在线支付</label>' +
									'<label id="monthPayLabel" style="border-radius: 4px;" class="radio-info radio-inline form-control" for="monthPay"><input type="radio" id="monthPay" name="payRadio" value="2"/>月结</label>' +
									'</div></div>';
								str += initBillAndAddress();
                                str += "<div class=\"ft\" style='display: block;border-bottom: 0px;line-height: 33px;margin-bottom: -33px;height: 45px;padding-left:10px'>商品种类：" + matList.length + "种，数量总计：" + shu + "</div>" +
                                    "<div class=\"ft\" style='border-top: 0px;height: 130px;'>" +
                                    "<div class=\"ft-left\"><span>商品总额：￥<strong id='allMatMoney" + mdSupplier.wzId + "' class=\"red\">" + toDecimal2(matCountMoney) + "</strong></span></div>" +
                                    "<div class=\"ft-left\"><span>运费：￥<strong id='expressMoney" + mdSupplier.wzId + "' class=\"red\">" + toDecimal2(mdSupplier.expressAll) + "</strong></span></div>";
                                // str += "<span>店铺合计：<strong class=\"red\">￥" + toDecimal2(sCount) + "</strong></span></div>";
                                str += "<div class=\"ft-right\" ><span>应付金额(含运费)：￥<strong id='sureMoney" + mdSupplier.wzId + "' class=\"red\">" + toDecimal2(allCount) + "</strong><strong>(￥<span id='expressMoney1" + mdSupplier.wzId + "' class=\"red\">" + toDecimal2(mdSupplier.expressAll) + "</span>)</strong></span>";
                                str += "<button class=\"saveOrder font-weight-trigger\" style='float: left' onclick=\"orderSave()\">订单确定</button></div></div>";
                            } else {
                                str += "<div class=\"ft\" style='display: block;border-bottom: 0px;line-height: 33px;margin-bottom: -33px;height: 45px;padding-left:10px'>商品种类：" + matList.length + "种，数量总计：" + shu + "</div>" +
                                    "<div class=\"ft\" style='border-top: 0px'>" +
                                    "<div class=\"ft-left\"><span>商品总额：￥<strong id='allMatMoney" + mdSupplier.wzId + "' class=\"red\">" + toDecimal2(matCountMoney) + "</strong></span></div>" +
                                    "<div class=\"ft-left\"><span>运费：￥<strong id='expressMoney" + mdSupplier.wzId + "' class=\"red\">" + toDecimal2(mdSupplier.expressAll) + "</strong></span></div>";
                                str += "<div class=\"ft-right\" ><span>应付金额(含运费)：￥<strong id='sureMoney" + mdSupplier.wzId + "' class=\"red\">" + toDecimal2(sCount) + "</strong>￥<strong>(<span id='expressMoney1" + mdSupplier.wzId + "' class=\"red\">" + toDecimal2(mdSupplier.expressAll) + "</span>)</strong></span></div></div>";
                            }
                        }
                        if (_dataList.length > 1) {
							str += '<div style="display: none; margin-left: 20px;">' +
								'<label class="note-label" style="margin-top: 10px">支付方式：</label>' +
								'<div class="form-inline" style="display: inline-block; margin-top: 10px">' +
								'<label id="olinePayLabel" style="border-radius: 4px;" class="radio-info radio-inline form-control" for="onlinePay"><input type="radio" id="onlinePay" name="payRadio" value="1" />在线支付</label>' +
								'<label id="monthPayLabel" style="border-radius: 4px;" class="radio-info radio-inline form-control" for="monthPay"><input type="radio" id="monthPay" name="payRadio" value="2"/>月结</label>' +
								'</div></div>';
							str += initBillAndAddress();
                            str += "<div class=\"ft\"><div class=\"ft-left\"></div>";
                            str += "<div class=\"ft-right\" ><span>应付金额(含运费)：￥<strong id='sureAllMoney' class=\"red\">" + toDecimal2(allCount) + "</strong><strong>(￥<span id='expressMoneyAll' class=\"red\">" + toDecimal2(expressMoneyAll) + "</span>)</strong></span>";
                            str += "<button class=\"saveOrder font-weight-trigger\" onclick=\"orderSave()\">订单确定</button></div></div>";
                        }
                    }

                    $("#matList").html(str);
                    $("#allMoney").html("¥" + toDecimal2(allCount));
                    $('#matList').find('#olinePayLabel').on('click', function () {
                        $(this).css('border', 'red solid 1px');
                        $('#monthPayLabel').css('border', '0px');
                    });
                    $('#matList').find('#monthPayLabel').on('click', function () {
                        $(this).css('border', 'red solid 1px');
                        $('#olinePayLabel').css('border', '0px');
                    });
                }
            }
        });
    }
}
var wmsMiIdCount = {};
function updateMoney(money, wzId, wmsMiId) {
    var noExpressMoney = wmsMiIdCount[wzId].noExpressMoney;
    var expressMoney = wmsMiIdCount[wzId].expressMoney;

    var allMoney = parseFloat($('#allMatMoney' + wzId).text());
    var sureMoney = parseFloat($('#sureMoney' + wzId).text());
    var sureAllMoney = parseFloat($('#sureAllMoney').text());
    var expressMoneyAll = parseFloat($('#expressMoneyAll').text());
    var littleAll = parseFloat($('#littleAll' + wmsMiId).text());
    var em = $('#expressMoney' + wzId).text();
    // expressMoney += parseFloat(money);
    allMoney += parseFloat(money);
    sureMoney += parseFloat(money);
    sureAllMoney += parseFloat(money);
    littleAll += parseFloat(money);

    if(allMoney >= noExpressMoney) {
        sureMoney -= expressMoney;
        sureAllMoney -= expressMoney;
        expressMoneyAll -= expressMoney;
        expressMoney = 0;
    } else if (allMoney < noExpressMoney && em <= 0) {
        sureMoney += expressMoney;
        sureAllMoney += expressMoney;
        expressMoneyAll += expressMoney;
    }

    $('#expressMoney' + wzId).text(toDecimal2(expressMoney));
    $('#expressMoney1' + wzId).text(toDecimal2(expressMoney));
    $('#expressMoneyAll').text(toDecimal2(expressMoneyAll));
    $('#allMatMoney' + wzId).text(toDecimal2(allMoney));
    $('#sureAllMoney').text(toDecimal2(sureAllMoney));
    $('#sureMoney' + wzId).text(toDecimal2(sureMoney))
    $('#littleAll' + wmsMiId).text(toDecimal2(littleAll));
}
function minOutOrderNum(wmsMiId, wzId){
    var base_num = $("#InpMat" + wmsMiId).val();
    base_num--;
    var flag = 1;
    if(base_num < 0) {
        flag = 0;
    }
    if(base_num <= 0) {
        base_num = 0;
    }
    $("#InpMat" + wmsMiId).val(base_num);
    wmsMiIdCount[wzId][wmsMiId].shu = base_num;
    var price = wmsMiIdCount[wzId][wmsMiId].price;

    updateMoney(-price * flag, wzId, wmsMiId)
    // altAllOrderCount();
}
function addnOutOrderNum(wmsMiId, wzId) {
    var base_num = $("#InpMat" + wmsMiId).val()
    base_num++;
    $("#InpMat" + wmsMiId).val(base_num);
    wmsMiIdCount[wzId][wmsMiId].shu = base_num;
    var price = wmsMiIdCount[wzId][wmsMiId].price;
    updateMoney(price, wzId, wmsMiId)
    // altAllOrderCount();
}
var old_num = 0;
function onKeyUpOrderNum(wmsMiId, wzId){
    $("#InpMat" + wmsMiId).val($("#InpMat" + wmsMiId).val().replace(/[^0-9]/g,''));
    var new_num = $("#InpMat" + wmsMiId).val();
    // if(Number(new_num) >= baseNumber) {
    // 	new_num = baseNumber;
    // 	$("#InpMat" + wmsMiId).val(new_num);
    // }
    var price = wmsMiIdCount[wzId][wmsMiId].price;

    if(old_num > new_num){
        updateMoney(-(old_num - new_num) *price, wzId, wmsMiId)
    } else if(old_num < new_num){
        updateMoney((new_num - old_num) *price, wzId, wmsMiId)
    } else{
        return;
    }
    old_num = new_num;
    wmsMiIdCount[wzId][wmsMiId].shu = new_num;
    // altAllOrderCount();
}
function onMouseDwonOrderNum(wmsMiId, wzId){
    old_num = $("#InpMat" + wmsMiId).val();
}

function initBillAndAddress() {
	var str = '<div class="invoiceInfo">' +
		'<div class="invoiceInfo-item">' +
		'<input type="checkbox" id="needBill" onclick="changeNeedBill()">' +
		'<label>索要发票</label>' +
		'</div>' +
		'<div class="invoiceInfo-item" id="billTypeDiv" style="display:none">' +
		'<label class="invoiceInfo-label">发票抬头：</label>' +
		'<select class="invoiceInfo-select" id="billType" onchange="changeBillType()">' +
		'<option value="2">公司</option>' +
		'<option value="1">个人</option>' +
		'</select>' +
		'<input class="invoiceInfo-input" type="text" id="billHeard" placeholder="公司名称">' +
		'</div>' +
		'<div  class="invoiceInfo-item" id="billCodeDiv" style="display:none">' +
		'<label class="invoiceInfo-label">纳税人识别号：</label>' +
		'<input class="invoiceInfo-input w315"  id="billCode" type="text" maxlength="18" onKeyUp="value=value.replace(/[\\W]/g,\'\')" onBlur="checkPassword()" " placeholder="纳税人识别号">' +
		'</div>' +
		'</div>';
	var address = '<div class="address-div" style="display: inline-block; text-align: center;">' +
		'<span id="confirm-address" class="note-label" style="font-size: 20px"></span>' +
		'</div>';
	return str + address;
}
    var pwdReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;//8到16位数字与字母组合
    function checkPassword(){
        var value = $('#billCode').val();
        if (value.length<15 || value.length!=15 && value.length!=18){
            alert('请填写完整的纳税人识别号!');
            return
        }
    }
//input class="tele" type="password" name="password" id="password" onkeyup="value=value.replace(/[\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4E00-\u9FA5]/g,''))">
function editAddress(mdaId) {
    clearAddressForm();
    if (mdaId != null) {
        var data = "mdaId=" + mdaId;
        $.supper("doservice", {
            "service": "MdDeliveryAddressService.findFormObject", "data": data, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    $("#deliveryAddress").val(jsondata.obj.deliveryAddress);
                    $("#mdaId").val(jsondata.obj.mdaId);
                    $("#suiId").val(jsondata.obj.suiId);
                    $("#ifDefault").val(jsondata.obj.ifDefault);
                    $("#addressee").val(jsondata.obj.addressee);
                    $("#addresseeTelephone").val(jsondata.obj.addresseeTelephone);
                    $("#province").val(jsondata.obj.province);
                    initShi();
                    $("#city").val(jsondata.obj.city);
                    initArea();
                    $("#area").val(jsondata.obj.area);
                }
            }
        });
    }
    fLogin.openLogin();
}

function saveAddress() {
    var addressee = $("#addressee").val();
    if (addressee == null || addressee == "") {
        $.supper("alert", {title: "操作提示", msg: "请输入联系人!"});
        return;
    }
    var addresseeTelephone = $("#addresseeTelephone").val();
    if (addresseeTelephone == null || addresseeTelephone == "") {
        $.supper("alert", {title: "操作提示", msg: "请输入联系电话!"});
        return;
    }
    var province = $("#province").val();
    if (province == null || province == "") {
        $.supper("alert", {title: "操作提示", msg: "请选择所在省!"});
        return;
    }
    var city = $("#city").val();
    if (city == null || city == "") {
        $.supper("alert", {title: "操作提示", msg: "请选择所在市!"});
        return;
    }
    var area = $("#area").val();
    if (area == null || area == "") {
        $.supper("alert", {title: "操作提示", msg: "请选择所在区/县!"});
        return;
    }
    var deliveryAddress = $("#deliveryAddress").val();
    if (deliveryAddress == null || deliveryAddress == "") {
        $.supper("alert", {title: "操作提示", msg: "请输入详细地址!"});
        return;
    }
    var data = $("#accountForm").serialize();
    $.supper("doservice", {
        "service": "MdDeliveryAddressService.saveOrUpdateObject",
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {title: "操作提示", msg: "操作成功!"});
                fLogin.closeLogin();
                initAddressList();
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败!"});
            }
        }
    });
}

function changeNeedBill() {
    $("#billType").val("2");
    $("#billHeard").attr('placeholder', '公司名称');
    $("#billHeard").val("");
    $("#billCode").val("");
    if ($('#needBill').is(':checked')) {
        $("#billTypeDiv").show();
        $("#billCodeDiv").show();
    } else {
        $("#billTypeDiv").hide();
        $("#billCodeDiv").hide();
    }
}

function changeBillType() {
    var billType = $("#billType").val();
    $("#billCode").val("");
    if (billType == '1') {
        $("#billCodeDiv").hide();
        $("#billHeard").attr('placeholder', '个人姓名');
    } else if (billType == '2') {
        $("#billCodeDiv").show();
        $("#billHeard").attr('placeholder', '公司名称');
    }
}

function clearAddressForm() {
    $("#deliveryAddress").val("");
    $("#mdaId").val("");
    $("#suiId").val("");
    $("#ifDefault").val("2");
    $("#addressee").val("");
    $("#addresseeTelephone").val("");
    $("#province").val("");
    $("#city").val("");
    $("#area").val("");
    $("#deliveryAddress").val("");
    initShen();
    initShi();
    initArea();
}

function initShen() {
    var str = "<option value=\"\">所在省</option>";
    for (var i = 0; i < shqJson.length; i++) {
        str += "<option value=\"" + shqJson[i].name + "\">" + shqJson[i].name + "</option>";
    }
    $("#province").html(str);

}

function initShi() {
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
}

function initArea() {
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
}

function juggeAddressNum() {
    var col = $('.address-items');
    if (col.length > 3) {

        var index = 0;
        $(".address-items").each(function () {
            if (index > 2) {
                $(this).hide();
            }
            index++;
        });
        $('.address-items-add').hide();
        $(".confirm-address-bar").show();
    } else {
        $('.address-items-add').show();
        $(".confirm-address-bar").hide();

    }
}

function dropAddress() {
    $('.address-items').show();
    $('.address-items-add').show();
    $(".confirm-address-bar").hide();
}

function selAddress(obj, mdaId) {
    _selMdaId = mdaId;
    var pobj = $(".address-items");
    pobj.removeClass();
    pobj.addClass("address-items");
    $(obj).addClass("active");
    var address = ''
	$(obj).find('p').each(function () {
		address += $(this).text() + '  ';
	})
    $('#confirm-address').text('请认真核对您的地址信息：' + address);
}
function initText(obj) {
    setTimeout(function () {
        var address = ''
        $(obj).find('p').each(function () {
            address += $(this).text() + '  ';
        })
        $('#confirm-address').text('请认真核对您的地址信息：' + address);
    }, 400);
}
function buySave(){
    var mmfIds = $("#mmfIds").val();
    var mmfIdsArray = mmfIds.split(',');
            var data = 'mmfIds=' +mmfIdsArray;
            $.supper("doservice", {
                "service": _savebuy, 'data': data, "BackE": function (jsondata) {

                }
            });

}
function orderSave() {
        console.log("111")
    var billType = $("#billType").val();
        if ($('#needBill').is(':checked')){
            if(billType == '2' && $("#billHeard").val() == ''){
                alert("请填写完整的公司名称")
                return
            }
        }

    if (billType == '2'){
        if ($('#needBill').is(':checked')) {
            var value = $('#billCode').val();
            var billType = $("#billType").val();
            console.log(billType)
            if (value.length<15 || value.length!=15 && value.length!=18){
                alert('请填写完整的纳税人识别号!');
                return
            }
        }
    }

    var onlinePay = $('#onlinePay').prop('checked');
    var monthPay = $('#monthPay').prop('checked');
    // if (onlinePay === false && monthPay === false) {
    //     $.supper("alert", {title: "操作提示", msg: "请选择付款方式！"});
    //     return;
    // }
    if (_dataList == null || _dataList.length <= 0) {
        $.supper("alert", {title: "操作提示", msg: "请选择要购买的商品！"});
        return;
    }
    var needBill;
    if ($('#needBill').is(':checked')) {
        needBill = 1;
    } else {
        needBill = 2;
    }
    var billType = $("#billType").val();
    var billHeard = $("#billHeard").val();
    var billCode = $("#billCode").val();
    var mmfIds = $("#mmfIds").val();
    var shus = $("#shus").val();
    if (_selMdaId == null || _selMdaId == "") {
        $.supper("alert", {title: "操作提示", msg: "请选择收货地址！"});
        return;
    }
    var wzIds = "";
    var contents = "";
    var expressMoneys = "";
    if (_dataList != null && _dataList.length > 0) {
        for (var i = 0; i < _dataList.length; i++) {
            var mdSupplier = _dataList[i].mdSupplier;
            var content = $("#area_" + mdSupplier.wzId).val();
            var express = mdSupplier.expressAll;
            contents += (content != "" ? content : " ") + ",";
            wzIds += mdSupplier.wzId + ",";
            expressMoneys += express + ",";
        }
    }

    if (wzIds != null && wzIds != "") {
        wzIds = wzIds.substring(0, wzIds.length - 1);
        contents = contents.substring(0, contents.length - 1);
        expressMoneys = expressMoneys.substring(0, expressMoneys.length - 1);
    }
    shus = '';
    var zeroCount = 0;
    $('input[id^=InpMat]').each(function () {
        var s = $(this).val();
        if(s == '0'){
            zeroCount++;
        }
        shus += $(this).val() + ",";
    })
    shus = shus.substring(0, shus.length - 1);
    var shusArray = shus.split(',');
    if(zeroCount == shusArray.length){
        $.supper("alert", {title: "操作提示", msg: "购买数量不能全为0！"});
        return;
    }
    var data = "mmfIds=" + mmfIds + "&shus=" + shus + "&needBill=" + needBill + "&billType=" + billType + "&billHeard="
        + billHeard + "&billCode=" + billCode + "&wzIds=" + wzIds + "&contents=" + contents + "&expressMoneys=" + expressMoneys + "&mdaId=" + _selMdaId;
    if (monthPay === true) {
        data += '&payType=3'
    }

    $.supper("doservice", {
        "service": "ShoppingService.saveOrder",
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert", {
                //     title: "操作提示", msg: "操作成功！", BackE: function () {
                clearSubmitCarts();
                buySave()
                        // if (onlinePay === true) { //支付
                        //     window.location.replace("ddpay.htm?moiId=" + jsondata.obj);
                        // } else if (monthPay === true) {
                            window.location.href = "ddlb.htm";
                        // }

                //     }
                // });
            } else if(jsondata.code =='2'){
                $.supper("alert", {title: "操作提示", msg: jsondata.meg});
            }else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

function clearSubmitCarts() {
    var mmfIds = $("#mmfIds").val();
    var shus = $("#shus").val();
    var mmfIdsArray = mmfIds.split(',');
    mmfIdsArray.forEach(function (value) {
        if (value != '') {
            var data = 'mmfIds=' + value;
            $.supper("doservice", {
                "service": _delete_carts_ddqr_action, 'data': data, "BackE": function (jsondata) {

                }
            });
        }
    })
}