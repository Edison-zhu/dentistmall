var queryAction = 'mdInventoryService.getMdInventoryLogMxListByWiId';
var _all_infoAction = 'MdInventoryService.findFormObject';
// var queryEnterAction = "MdEnterWarehouseMxService.getMdEnterWarehouseMxListByWiId";
// var queryOutAction = "MdOutWarehouseMxService.getMdOutWarehouseMxListByWiId";

var _wiId;

var initDataGrid = function () {
    var cols = [
        {title: '操作时间',sortable: true , name: 'createDate1', width: 100, align: 'center'},
        {title: '入库单号',sortable: true , name: 'cCode', width: 100, align: 'center'},
        {title: '类型',sortable: true , name: 'stype', width: 50, align: 'center', renderer: renderStype},
        {title: '数量',sortable: true , name: 'number', width: 20, align: 'center', renderer: renderBaseNumber},
        {title: '采购价格',sortable: true , name: 'price', width: 50, align: 'center'},
        {title: '零售价格',sortable: true , name: 'retailPrice', width: 50, align: 'center'},
        {title: '采购金额',sortable: true , name: 'allPrice', width: 50, align: 'center', renderer: renderAllPrice},
        {title: '零售金额',sortable: true , name: 'allRetailPrice', width: 50, align: 'center', renderer: renderAllRPrice},
        {title: '当前库存变化',sortable: true , name: 'curNumber', width: 20, align: 'center', renderer: renderCurNumber},
        {title: '有效期',sortable: true , name: 'productValitime', width: 20, align: 'center'},
        {title: '品牌',sortable: true , name: 'brand', width: 40, align: 'center'},
        {title: '生产厂家',sortable: true , name: 'applicantName', width: 20, align: 'center'},
        {title: '生产场地',sortable: true , name: 'productPlace', width: 40, align: 'center'},
        {title: '批次号',sortable: true , name: 'batchCode1', width: 20, align: 'center'},
        {title: '注册证号',sortable: true , name: 'backPrinting', width: 20, align: 'center'},
        {title: '关联订单号',sortable: true , name: 'relatedCode', width: 100, align: 'center'},
        {title: '状态',sortable: true , name: 'state', width: 100, align: 'center', renderer: control}
    ];

    var att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
    att_mmgurl += '&wiId=' + _wiId;

    mmg = $('#datagrid1').mmGrid({
        height: 'auto'
        , cols: cols
        , method: 'get'
        , remoteSort: false
        , url: att_mmgurl
        , sortName: 'SECUCODE'
        , sortStatus: 'asc'
        , multiSelect: true
        , checkCol: false
        , fullWidthRows: true
        , showBackboard: false
        , autoLoad: false
        , nowrap: true
        , plugins: [
            $('#pg').mmPaginator({})
        ]
    });
    mmg.load();
}

function renderStype(val, item, rowIndex) {
    let str = '';
    if (item.stype == 1) {
        str += '入库';
    } else if (item.stype == 2) {
        str += '出库';
    } else if (item.stype == 3) {
        str += '合并库存';
    }
    return str;
}

function renderCurNumber(val, item, rowIndex) {
    let baseNumber = item.curNumber == undefined ? 0 : item.curNumber;
    let ratio = item.ratio == undefined ? 1 : item.ratio;
    let quantity = Math.floor(baseNumber / ratio);
    return quantity + '' + item.BasicUnit + (baseNumber - ratio * quantity) + '' + item.unit;
}

function renderBaseNumber(val, item, rowIndex) {
    let str = '';

    let baseNumber = item.splitQuantity == undefined ? 0 : item.splitQuantity;
    let ratio = item.ratio == undefined ? 1 : item.ratio;
    let quantity = Math.floor(baseNumber / ratio);

    if (item.stype == 2) {
        str += '<span style="color: red;">-' + (item.number == undefined ? 0 : item.number) + '' + item.BasicUnit + (item.splitQuantity == undefined ? 0 : item.splitQuantity) + '' + item.unit + '</span>';
    } else {
        str += quantity + '' + item.BasicUnit + (baseNumber - ratio * quantity) + '' + item.unit;
    }
    return str;
}

function renderAllPrice(val, item, rowIndex) {
    let str = '';
    // if (item.stype == 1) {
        str += toDecimal2(item.number * item.price);
    // }
    return str;
}

function renderAllRPrice(val, item, rowIndex) {
    let str = '';
    // if (item.stype == 1) {
        str += toDecimal2(item.number * (item.retailPrice == undefined ? 0 : item.retailPrice));
    // }
    return str;
}

function getDateRange(dateNow,intervalDays,bolPastTime){
    let oneDayTime = 24 * 60 * 60 * 1000;
    let list = [];
    let lastDay;

    if(bolPastTime == true){
        lastDay = new Date(dateNow.getTime() - intervalDays * oneDayTime);
        list.push(this.formateDate(lastDay));
        list.push(this.formateDate(dateNow));
    }else{
        lastDay = new Date(dateNow.getTime() + intervalDays * oneDayTime);
        list.push(this.formateDate(dateNow));
        list.push(this.formateDate(lastDay));
    }
    return list;
}
function formateDate(time) {
    let year = time.getFullYear()
    let month = time.getMonth() + 1
    let day = time.getDate()

    if (month < 10) {
        month = '0' + month
    }

    if (day < 10) {
        day = '0' + day
    }

    return year + '-' + month + '-' + day + ''

}

function formatDate(date, fmt) {
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//计算两个日期的天数差
function dateDiff(firstDate,secondDate){
    var firstDate = new Date(firstDate);
    var secondDate = new Date(secondDate);
    var diff = firstDate.getTime() - secondDate.getTime()
    var result = parseInt(diff / (1000 * 60 * 60 * 24));
    return result
}

function control(val, item, rowIndex) {
    if (item.productValitime == undefined)
        return '未设置';

    let str = '';
    let date = new Date();
    let result = dateDiff(item.productValitime, new Date());
    let warningDay = item.warningDay;
    if (warningDay == undefined || result > warningDay)
        str = '正常';
    else if (result >= 0 && result <= warningDay)
        str = '快过期(' + result + ')';
    else
        str = '已过期';

    return str;
}

function backTo() {
    var view_url = 'jsp/dentistmall/storage/viewInventory.jsp';
    $.supper("showTtemWin", {"url": view_url, "title": '库存查询'});
    $.supper("setProductArray", {"name": "viewInfoUrl", "value": null });
    setTimeout(function () {
        if (url != undefined) {
            $.supper("closeTtemWin", {url: url});
        }
    }, 200);
}

function exportAll() {
    var vdata = $("#queryForm").serialize();
    vdata += '&wiId=' + _wiId;
    // alert(vdata);
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"EnterWarehouseExportService.exportInventoryView","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}

function search() {
    var att_mmgurl = rpc.getUrlByForm(queryAction, "queryForm");
    att_mmgurl += '&wiId=' + _wiId;
    mmg.opts.url = att_mmgurl;
    mmg.load();
}

function initInfo() {
    $.supper("doservice", {
        "service": _all_infoAction, "data": 'wiId=' + _wiId, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                let val = jsondata.obj;
                $("#infoDiv span").each(function (i, v) {
                    var att_name = $(v).attr("id");
                    eval("var vals=val." + att_name + ";");
                    if (vals) {
                        $(v).text(vals);
                        $(v).val(vals);
                    }
                    if (att_name == 'baseNumber') {
                        let baseNumber = val.baseNumber == undefined ? 0 : val.baseNumber;
                        baseNumber = baseNumber - val.quantity * val.ratio;
                        const str = val.quantity + val.basicUnit + '/' + baseNumber + (val.unit == undefined ? '' : val.unit);
                        $(v).text(str);
                    }
                });
                $('#logoPath').attr('src', val.logoPath);
            }
        }
    });
}

var url;

$(function () {
    var searchName = $.supper("getProductArray", "viewInfoUrl");
    if(searchName != undefined && searchName != null){
        url = searchName.url;
    }
    _wiId = $.supper("getParam", 'wiId');
    if (_wiId == undefined || _wiId == null) {
        return;
    }
    initInfo();
    initDataGrid();

    laydate({
        elem: '#createDate',
        format: 'YYYY-MM-DD' //日期格式
    });
})
