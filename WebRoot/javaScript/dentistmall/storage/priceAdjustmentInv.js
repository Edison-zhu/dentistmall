var _all_win_tools_but = $("#win_tools_but");
var view_url = 'jsp/dentistmall/storage/priceAdjustmentInvInfo.jsp';
var _all_datagrid_height;
var _all_win_datagrid;
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");

var _all_queryAction = 'mdInventoryService.getPagerModelPA';
var _all_deleteAction = 'mdInventoryService.deleteMdInventoryPA';

var _Toolbar = {
    toolBarId: "tools_but",
    items: [
        {
            title: "新增调价",
            id: "win_but_search",
            icon: "search",
            colourStyle: "primary",
            rounded: true,
            clickE: addNewPriceAdj
        },
        {title: "刷新", id: "win_but_search", icon: "search", colourStyle: "info", rounded: true, clickE: main_search},
        {
            title: "导出Excel",
            id: "win_but_search",
            icon: "search",
            colourStyle: "default",
            rounded: true,
            clickE: exportExcel
        }
    ]
};

var _DataGrid = {
    cols: [
        {title: '调价号', sortable: true,name: 'paiCode', width: 100, align: 'center'},
        {title: '调价类型', sortable: true,name: 'paiType', width: 80, align: 'center', renderer: renderPriceType},
        {title: '备注', sortable: true,name: 'remark', width: 80, align: 'center'},
        {title: '制作人', sortable: true,name: 'createRen', width: 70, align: 'center'},
        {title: '日期时间', sortable: true,name: 'createDate', width: 70, align: 'center'},
        {title: '操作信息', name: 'checkInventory', width: 70, align: 'center', renderer: control},
    ]
    , remoteSort: false
    , multiSelect:true
    , height: _all_datagrid_height
    , gridtype: '2'
    , nowrap: true
    , checkCol: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
}

function renderPriceType(val, item, rowIndex) {
    let str = '';
    if (item.paiType == 1) {
        str += '市场调价';
    } else if (item.paiType == 2) {
        str += '正常调价';
    }
    return str;
}

function control(val, item, rowIndex) {
    let str = '';
    str += "<a onclick=\"viewInfo('" + item.paiId + "')\"  class='btn btn-success  btn-xs' style='width: 60px'>详情</a>&nbsp;&nbsp;";
    str += "<a onclick=\"editInfo('" + item.paiId + "')\"  class='btn btn-success  btn-xs' style='width: 60px'>编辑</a>&nbsp;&nbsp;";
    str += "<a onclick=\"deletePrice('" + item.paiId + "')\"  class='btn btn-success  btn-xs' style='width: 60px'>删除</a>&nbsp;&nbsp;";
    return str;
}

function viewInfo(paiId) {
    let att_title = "调价详情";
    let att_userType = "1";
    var att_url = $.supper("getServicePath", {url: view_url});
    $.supper("setProductArray", {"name": "priceAdjustment", "value": {paiId: paiId, url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}

function editInfo(paiId) {
    let att_title = "调价维护";
    let att_userType = "1";
    let att_url = $.supper("getServicePath", {url: view_url});
    $.supper("setProductArray", {"name": "priceAdjustment", "value": {paiId: paiId, edit: true, url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}

function deletePrice(paiId) {
    $.supper("confirm", {
        title: "删除操作", msg: "删除后数据不可恢复并调价无法撤销是否确定删除？", yesE: function () {
            $.supper("doservice", {
                "service": _all_deleteAction, "data": 'paiIds=' + paiId, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                    } else if (jsondata.code == '2') {
                        $.supper("alert", {title: "操作提示", msg: jsondata.meg});
                    } else {
                        $.supper("alert", {title: "操作提示", msg: "操作出错！"});
                    }
                }
            });
        }
    });

}

function getMMGridUrl() {
    let data = $('#win_form_search').serialize();
    let att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
    return att_url;
}

function addNewPriceAdj() {
    let att_title = "新增调价";
    let att_userType = "1";
    let att_url = $.supper("getServicePath", {url: view_url});
    $.supper("setProductArray", {"name": "priceAdjustment", "value": {url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}

function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
    initCount();
}

// function exportExcel() {
//
// }

function initCount() {

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

function selectDate(dateIndex) {
    let date = new Date();
    let renderDate = formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    let range = false;
    switch (dateIndex) {
        case 1:
            date.setDate(date.getDate() - 1);
            range = true;
            break;
        case 2:
            date.setDate(date.getDate() - 2);
            range = true;
            break;
        case 3:
            date.setDate(date.getDate() - 6);
            range = true;
            break;
        case 4:
            date.setDate(date.getDate() - 13);
            range = true;
            break;
        case 5:
            date.setDate(date.getDate() - 20);
            range = true;
            break;
        case 6:
            date.setDate(date.getDate() - 29);
            range = true;
            break;
        case 7:
            date.setDate(date.getDate() - 59);
            range = true;
            break;
    }
    if (range == true) {
        renderDate = formatDate(date,'yyyy-MM-dd hh:mm:ss') + '~' + renderDate;
    }
    $('#startDate').val(renderDate);
    $('#rangeDateDiv').hide();
}

function closeSelect() {
    $('#rangeDateDiv').hide();
}

function selectRangeDate() {
    $('#rangeDateDiv').show();
}

$(function () {
    _all_win_tools_but.xtoolbar('create', _Toolbar);

    _all_datagrid_height = $(window).height() - 104 - 95;

    _DataGrid.height=_all_datagrid_height;

    _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid);

    main_search();

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#rangeDate'
            // ,type: 'year'
            , type: 'datetime'
            , range: "~" //或 range: '~' 来自定义分割字符
            , position: 'static'
            , done: function (value, date) {
                $('#startDate').val(value);
                $('#rangeDateDiv').hide();
            }
        });
    });
})

function exportExcel() {
    var rows=_all_win_datagrid.selectedRows();
    if(rows==null || rows.length <=0){
        $.supper("confirm",{ title:"操作提示", msg:"您未选择要导出的数据，系统将所有数据导出是否确定导出？" ,yesE:function(){
    var paiIds='';
    for (var i = 0; i <rows.length ; i++) {
        paiIds+=rows[i].paiId+'';
    }
    var vdata=$('#saveForm').serialize();
    vdata+="&paiIds="+paiIds;
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"EnterWarehouseExportService.exportPriceAdjustmentInv","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
        }});
        return;
    }
    var vdata=$('#saveForm').serialize();
    var paiIds='';
    for (var i = 0; i <rows.length ; i++) {
        paiIds+=rows[i].paiId+',';
    }
    if (paiIds!=null&&paiIds!=''&&paiIds!=undefined) {
        paiIds=paiIds.substring(0,paiIds.length-1);
    }
    vdata+="&paiIds="+paiIds;
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"EnterWarehouseExportService.exportPriceAdjustmentInv","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}