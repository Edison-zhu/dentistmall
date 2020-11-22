var _all_limit_number = 5;
var _dd_search_action = 'MdOrderAfterSaleService.findOrderAfterSaleBySearch';
var moiId = null;

$(function () {
    moiId = $('#moiId').val();
    initCount();
    var vdata = 'moiId=' + moiId;
    $.supper('initPagination', {
        id: "Pagination",
        service: "MdOrderAfterSaleService.getOrderAfterSalePager",
        limit: _all_limit_number,
        isAjax: "1",
        data: vdata,
        "BackE": initList
    });

    $('#prePageDD').on('click', function () {
        prePageDD();
    })
    $('#nextPageDD').on('click', function () {
        nextPageDD();
    })
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#searchDdStartTime'
            , range: "~"
        });
    });

    $('.clickA').on('click', function () {
        var thisId = $(this).attr('id');
        $('a[id^="_item"]').each(function () {
            var id = $(this).attr('id');
            $(this).removeClass('taps-item-active');
            $(this).removeClass('taps-item');
            if (thisId == id) {
                $(this).addClass('taps-item-active');
            } else {
                $(this).addClass('taps-item');
            }
        })
    })
    initOderListUtil({server: 'MdOrderAfterSaleService.getOrderAfterSaleMxModelByMasId', paginationServer: 'MdOrderAfterSaleService'});

    $('#searchDdName').on('keyup', function () {
        if (event.keyCode == 13) {
            searchDd();
        }
    })
});
var _status = '';

function changeType(status, vid) {
    $('#searchDdName').val('');
    $('#searchDDState').val('');
    $("#_item").removeClass();
    $("#_item1").removeClass();
    $("#_item2").removeClass();
    if (status != "") {
        $('#searchDDState').hide();
    } else {
        $('#searchDDState').show();
    }
    _searchName = '';
    $('#searchDdName').val('');
    _status = status;
    $(".box_tittle").children().removeClass();
    $("#" + vid).addClass("qhbeij");
    var vdata = "moiId=" + moiId;
    if (status != null && status != "") {
        vdata += "&afterSale=" + status;
    }
    $.supper('initPagination', {
        id: "Pagination",
        service: "MdOrderAfterSaleService.getOrderAfterSalePager",
        data: vdata,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initList
    });
}

function initList(dataList) {
    initOderAsList('divList', dataList, false);
}

function initCount() {
    var vdata = 'moiId=' + moiId;
    $.supper("doservice", {
        "service": "MdOrderAfterSaleService.countOrderAfterSale", data: vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#allCount").html(jsondata.obj.all);
                $("#thCount").html(jsondata.obj.th);
                $("#tkCount").html(jsondata.obj.tk);
                $("#wcCount").html(jsondata.obj.wc);
            }
        }
    });
}

function reSearch() {
    var vdata = "1=1";
    if (_status != null && _status != "")
        var vdata = "processStatus=" + _status;
    $.supper('initPagination', {
        id: "Pagination",
        service: "MdOrderAfterSaleService.getOrderAfterSalePager",
        data: vdata,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initList
    });
    _searchName = '';
    $('#searchDdName').val('');
    initCount();
}

function main_export(id) {
    var vdata = "moiId=" + id;
    var newTab = window.open('about:blank');
    $.supper("doservice", {
        "service": "MdOrderInfoService.exportInfo", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

function main_export_mx(id) {
    var vdata = "moiId=" + id;
    var newTab = window.open('about:blank');
    $.supper("doservice", {
        "service": "MdOrderInfoService.exportInfo", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

//订单列表查询
var _searchName = '';
function searchDd() {
    var serachDdName = 'searchName=' + $('#searchDdName').val();
    _searchName = $('#searchDdName').val();
    var searchAsState = 'searchAsState=' + $('#searchDDState').val();
    var searchDate = 'placeOrderTime=' + $('#searchDdStartTime').val();
    var searchAsType = 'searchAsType=' + $('#searchDDPayType').val();
    var data = serachDdName + '&' + searchAsState + '&' + searchDate + '&' + searchAsType + '&moiId=' + moiId + '&processStatus2=' + _status;
    $.supper('initPagination', {
        id: "Pagination",
        service: _dd_search_action,
        "data": data,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initList
    });
}

function resetSearch() {
    $('#searchDdName').val('');
    $('#searchDDState').val('');
    $('#searchDdStartTime').val('');
    $('#searchDDPayType').val('');
    _searchName = '';
    $('#searchDdName').val('');
    searchDd();
}

//搜索边上的上一页下一页
function prePageDD() {
    var pre = document.querySelector('#prev');
    if (pre !== null) {
        pre.click();
    }
}

function nextPageDD() {
    var next = document.querySelector('#next');
    if (next !== null) {
        next.click();
    }
}

function viewSupplierOrderAsMx(masId) {
    var vdata="masId="+masId;
    var att_url= $.supper("getServicePath", {"service":"MdOrderAfterSaleService.getMdIrderSaleAfterObjectByMasId","data":vdata,url:"/jsp/dentistmall/transaction/orderaftersale/supplierasmx"});
    $.supper("showTtemWin",{ "url":att_url,"title":"售后明细"});
}