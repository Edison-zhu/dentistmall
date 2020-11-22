var _all_moiId;
var _mxList;
var momData = {};

// var deliveryMode='';
var state=0;
$(function () {
    _all_moiId = $.supper("getParam", "moiId");
    initData(_all_moiId);
});

function initList(jsondata) {
    _mxList = jsondata;
    // initXQList()
    var str = "";
    if (_mxList != null && _mxList.length > 0) {
        for (var i = 0; i < _mxList.length - 1; i++) {
            var mat = _mxList[i];
            var number2 = (mat.number2 !== undefined ? mat.number2 : 0);
            var cha = parseInt(mat.mat_number) - parseInt(number2);
            var maxNum = mat.inventory_count >= mat.mat_number ? mat.mat_number : mat.inventory_count;
            if(maxNum >= cha){
                maxNum = cha;
            }
            str += "<div class=\"order-info\"><div class=\"left\" style=\"margin-left:10px\"><img src=\"" + mat.less_file_path + "\" width=\"68\" height=\"68\"><ul>";
            str += "<li class=\"name\"><a href=\"" + $.supper("getbasepath") + "xiangxi.htm?wmsMiId=" + mat.wms_mi_id + "\" target=\"_blank\">" + mat.mat_name + "</a></li>";
            str += "<li class=\"type\">型号：<strong>" + mat.NORM + "</strong></li>";
            str += "<li class=\"type\">编号：<strong>" + mat.mmf_code + "</strong></li>";
            str += "</ul></div>";
            str += "<div class=\"right\"><div class=\"money\">￥" + toDecimal2(mat.Unit_money) + "</div>"
                + "<div class=\"number\">" + mat.Basic_unit + "</div>"
                + "<div class=\"number\">" + mat.inventory_count + "</div>"
                + "<div class=\"number\">" + mat.mat_number + "</div>"
                + "<div class=\"number\">" + (mat.number2 != null ? mat.number2 : "--") + "</div>"
            if(number2 < mat.mat_number){
                if (momData[mat.mom_id] === undefined || momData[mat.mom_id] === null) {
                    momData[mat.mom_id] = {};
                    momData[mat.mom_id].momId = mat.mom_id;
                    momData[mat.mom_id].shu = cha;
                } else {
                    cha = momData[mat.mom_id].shu;
                }
                str += '<div style="flex: 2">' +
                    '<a id="min_' + mat.mom_id + '" onclick="minusSend(' + mat.mom_id + ')" class="a" value="-" >-</a>' +
                    '<input type="text" id="_inp' + mat.mom_id + '" value="' + cha + '"  style="display:inline;width:33px;height: 20px;text-align:center" max="' + maxNum + '" onblur="keyUp(this,' + mat.mom_id + ', '+ maxNum + ')" onkeyup="keyUp(this,' + mat.mom_id + ', '+ maxNum + ')" />' +
                    '<a id="add_' + mat.mom_id + '" onclick="addSend(' + mat.mom_id + ',' + maxNum + ')" type="button" class="a" value="+">+</a>' +
                    '</div>';
            }else{
                str += "<div class=\"number\" style=\"flex: 2\">--</div>";
            }
            str += "<div class=\"number\">" + (mat.shure_number != null ? mat.shure_number : "0") + "</div>";

            // + "<div class=\"money red\">￥" + toDecimal2(mat.Total_money) + "</div>"
            // + "<div class=\"money red\">￥" + toDecimal2((mat.money1 != null ? mat.money1 : "0")) + "</div>";
            str += "</div></div>";
        }
        $('#totalmoney').text(toDecimal2(_mxList[_mxList.length - 1].mx_total_money));
        $("#matList").html(str);
    }
}

function initData(moiId) {
    var vdata = "moiId=" + moiId;
    $.supper("initPagination", {
        id: "Pagination",
        service: "MdOrderMxService.getOrderMxListByMoiId",
        data: vdata,
        limit: 5,
        isAjax: "1",
        "BackE": function (jsondata) {
            initList(jsondata);
        }
    });
}
function keyUp(selector, mom_id, maxNum) {
    $(selector).val($(selector).val().replace(/[^0-9]/g,''));
    if($('#_inp' + mom_id).val() > maxNum) {
        this.value = maxNum;
        $('#_inp' + mom_id).val(maxNum);
    }
    momData[mom_id].shu=this.value;
}
function minusSend(mom_id) {
    var num = $('#_inp' + mom_id).val();
    num --;
    if(num <= 0){
        num = 0;
    }
    $('#_inp' + mom_id).val(num);
    momData[mom_id].shu = num;
}

function addSend(mom_id, maxNum) {
    var num = $('#_inp' + mom_id).val();
    num ++;
    if(num >= maxNum){
        num = maxNum;
    }
    $('#_inp' + mom_id).val(num);
    momData[mom_id].shu = num;
}
//确认发货前填写物流信息
function confirmAddress() {
    $.each(momData, function (key, value) {
        if (value.shu == ''){
            value.shu = 0;
        }
        momIds += value.momId + ',';
        shus += value.shu + ',';
    });

    if (momIds == null || momIds == "") {
        $.supper("alert", {title: "操作提示", msg: "请输入发货数量！"});
        momIds = '';
        shus = '';
        return;
    }
    $('#addressDiv').show('fast');
    $('#addressId').show();
    var top = ($(window).height() - $('#addressDiv').height())/4;
    var left = ($(window).width() - $('#addressDiv').width())/4;
    var scrollTop = $(document).scrollTop();
    var scrollLeft = $(document).scrollLeft();
    $('#addressDiv').css( { position : 'absolute', 'top' : top + scrollTop, left : left + scrollLeft } );  
}

var momIds = "";
var shus = "";
function cancelChange() {
    momIds = '';
    shus = '';
    $('#addressDiv').hide('fast');
    $('.u-mask').hide('fast');
}



function deliveryClick() {
    if ($("#filterForm").css("visibility")!="hidden") {
        $("#filterForm").css("visibility","hidden");
        state=0
    }else {
        $("#filterForm").css("visibility","visible");
        state=1;
    }
}

function send() {
    var expressName = $("#expressName").val();
    var expressCode = $("#expressCode").val();
    if (state==1){
        if(expressName == ''){
            $.supper("alert", {title: "操作提示", msg: "请输入物流公司！"});
            return;
        }
        if(expressCode == ''){
            $.supper("alert", {title: "操作提示", msg: "请输入物流号！"});
            return;
        }
    }
    var test = $('#test').val();
    if(momIds.lastIndexOf(',') == momIds.length - 1) {
        momIds = momIds.substring(0, momIds.length - 1);
    }
    if(shus.lastIndexOf(',') == shus.length - 1){
        shus = shus.substring(0, shus.length - 1);
    }
    var vdata = "moiId=" + _all_moiId + "&expressName=" + expressName + "&expressCode=" + expressCode + "&momIds=" + momIds + "&shus=" + shus + "&test=" + test;
    $.supper("confirm", {
        title: "发货操作", msg: "确认发货操作？", yesE: function () {
            $.supper("doservice", {
                "service": "MdOrderInfoService.saveSendOrderInfo",
                "ifloading": 1,
                "options": {"type": "post", "data": vdata},
                "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {
                            title: "操作提示",
                            msg: "操作成功！",
                            BackE: function () {
                                var vdata = "moiId=" + _all_moiId;
                                var att_url = $.supper("getServicePath", {
                                    "service": "MdOrderInfoService.doFindObject",
                                    "data": vdata,
                                    url: "/jsp/dentistmall/transaction/sendOrderInfoNew"
                                });
                                window.location.href = att_url;
                            }
                        });
                    } else if (jsondata.code == '2'){
                        $.supper("alert", {
                            title: "操作提示",
                            msg: jsondata.meg
                        });
                    } else
                        $.supper("alert", {
                            title: "操作提示",
                            msg: "操作失败！"
                        });
                }
            });
        }
    });
}

//导出详情
function main_export() {
    var vdata = "moiId=" + _all_moiId;
    var newTab = window.open('about:blank');
    $.supper("doservice", {
        "service": "MdOrderInfoService.exportSupplierInfo", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

/**
 * 增加导出配送单
 */
function main_exportC() {
    var vdata = "moiId=" + _all_moiId;
    if ($('#test').val() !== null && $('#test').val() !== undefined) {
        var str = "&test=" + $('#test').val();
        vdata += str;
    }
    //var newTab = window.open('about:blank');
    $.supper("doservice", {"service":"MdOrderInfoService.saveZhaiYao","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                var newTab=window.open('about:blank');
                $.supper("doservice", {"service":"MdOrderInfoService.exportSupplierInfoSheetC","data":vdata, "BackE":function (jsondata) {
                        if (jsondata.code == "1") {
                            newTab.location.href=jsondata.obj.path;
                        }else
                            $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
                    }});
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}

function searchDd() {
    var matName = $('#searchDdName').val();
    var state = $('#searchDDState').val();
    var vdata = 'matName=' + matName + '&moiId=' + _all_moiId;
    if(state != ''){
        vdata += '&state=' + state;
    }
    $.supper("initPagination", {
        id: "Pagination",
        service: "MdOrderMxService.getOrderMxListBySearch",
        data: vdata,
        limit: 5,
        isAjax: "1",
        "BackE": function (jsondata) {
            initList(jsondata);
        }
    });
}
function resetSearch() {
    $('#searchDdName').val('');
    $('#searchDDState').val('');
    searchDd();
}
//保存摘要
function saveZhaiYao(){
    var vdata="moiId="+_all_moiId;
    if($('#test').val() !== null && $('#test').val() !== undefined){
        var str="&test="+$('#test').val();
        vdata+=str;
        $.supper("doservice", {"service":"MdOrderInfoService.saveZhaiYao","data":vdata, "BackE":function (jsondata) {
                if (jsondata.code == "1") {
                }else
                    $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
            }});
    }
}