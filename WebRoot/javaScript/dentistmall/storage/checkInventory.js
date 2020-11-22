var _all_win_tools_but = $("#win_tools_but");
var view_url = 'jsp/dentistmall/storage/checkInventoryInfo.jsp';
var _all_datagrid_height;
var _all_win_datagrid;
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");

var _all_queryAction = 'mdInventoryService.getPagerModelCI';

var _Toolbar = {
    toolBarId: "tools_but",
    items: [
        {
            title: "新增盘点",
            id: "win_but_search",
            icon: "search",
            colourStyle: "primary",
            rounded: true,
            clickE: addNewCheck
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
        {title: '盘点单号', sortable: true ,name: 'checkCode', width: 100, align: 'center'},
        {title: '盘点名称', sortable: true ,name: 'checkName', width: 80, align: 'center',},
        {title: '盘点类型', sortable: true ,name: 'checkType', width: 80, align: 'center', renderer: renderCheckType},
        {title: '总行数', sortable: true ,name: 'allCheck', width: 70, align: 'center'},
        {title: '已/未盘点行数', sortable: true ,name: 'checkInventory', width: 70, align: 'center', renderer: renderCheck},
        {title: '制作人', sortable: true ,name: 'createRen', width: 70, align: 'center'},
        {title: '制作时间', sortable: true ,name: 'createDate', width: 70, align: 'center'},
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

function renderCheckType(val, item, rowIndex) {
    let str = '';
    if (item.checkType == 1) {
        str = '月度';
    } else if (item.checkType == 2) {
        str = '季度';
    } else if (item.checkType == 3) {
        str = '年度';
    } else if (item.checkType == 4) {
        str = '每次';
    }
    return str;
}

function renderCheck(val, item, rowIndex) {
    let str = '';
    str = '<span>' + item.checkCount + '</span>/<span>' + item.noCheckCount + '</span>';
    return str;
}

function control(val, item, rowIndex) {
    let str = '';
    str += "<a onclick=\"viewInfo(" + item.ciId + ")\"  class='btn btn-success  btn-xs' style='width: 60px'>详情</a>&nbsp;&nbsp;";
    str += "<a onclick=\"editInfo(" + item.ciId + ")\"  class='btn btn-success  btn-xs' style='width: 60px'>编辑</a>&nbsp;&nbsp;";
    return str;
}

function viewInfo(ciId) {
    let att_title = "盘点查看";
    let att_userType = "1";
    var att_url = $.supper("getServicePath", {url: view_url});
    $.supper("setProductArray", {"name": "checkInventory", "value": {ciId: ciId, url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}

function editInfo(ciId) {
    let att_title = "盘点维护";
    let att_userType = "1";
    let att_url = $.supper("getServicePath", {url: view_url});
    $.supper("setProductArray", {"name": "checkInventory", "value": {ciId: ciId, isEdit: true, url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}

function getMMGridUrl() {
    let data = $('#win_form_search').serialize();
    let att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
    return att_url;
}

function addNewCheck() {
    let att_title = "新增盘点";
    let att_userType = "1";
    let att_url = $.supper("getServicePath", {url: view_url, url: view_url});
    $.supper("setProductArray", {"name": "checkInventory", "value": {url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}

function main_search() {
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
    initCount();
}
//导出Excel
function exportExcel() {
    let vdata = $('#win_form_search').serialize();
    // var vdata="ciId="+_ciId;
    _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid);
    var selectedRows = _all_win_datagrid.selectedRows();
    if (selectedRows.length==0){
        $.supper("confirm", {
            title: "操作提示", msg: "您暂时未选择要导出的数据，系统将所有数据导出是否确定导出？", yesE: function () {
                var newTab=window.open('about:blank');
                $.supper("doservice", {"service":"EnterWarehouseExportService.exportCheckInvent","data":vdata, "BackE":function (jsondata) {
                        if (jsondata.code == "1") {
                            newTab.location.href=jsondata.obj.path;
                        }else
                            $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
                    }});
            }
        });
        return;
    }
    var ciIds='';
    for (var i = 0; i <selectedRows.length ; i++) {
        ciIds+=selectedRows[i].ciId+',';
    }
    if (ciIds!=null&&ciIds!=''&&ciIds!=undefined) {
        ciIds=ciIds.substring(0,ciIds.length-1);
    }
    vdata+="&ciIds="+ciIds;
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"EnterWarehouseExportService.exportCheckInvent","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}

function initCount() {
    $.supper("doservice", {
        "service": "mdInventoryService.getCheckCount", "data": '', "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                let val = jsondata.obj;
                $('#countDiv span').each(function (i, v) {
                    var att_name = $(v).attr("id");
                    eval("var vals=val." + att_name + ";");
                    if (vals) {
                        $(v).text(vals);
                    }
                })
            }
        }
    });
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
    $('#selectDateDiv').hide();
}

function selectRangeDate() {
    $('#selectDateDiv').show();
}

function closeSelect() {
    $('#selectDateDiv').hide();
}

$(function () {
    _all_win_tools_but.xtoolbar('create', _Toolbar);

    _all_datagrid_height = $(window).height() - 104 - 95;

    _DataGrid.height=_all_datagrid_height;

    _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid);

    layui.use(['jquery', 'formSelects'], function () {
        var formSelects = layui.formSelects;
        formSelects.data('checkType', 'local', {
            arr: [
                {"name": "月度", "value": 1},
                {"name": "季度", "value": 2},
                {"name": "年度", "value": 3},
                {"name": "每次", "value": 4}
            ]
        });
        clearSelects = function () {
            formSelects.value('checkType', [])
        }
        formSelects.btns('checkType', []);
    });

    // layui.use(['jquery', 'formSelects'], function () {
    //     var formSelects = layui.formSelects;
    //     formSelects.config('processStatus', {
    //         keyName: 'name',
    //         keyVal: 'id'
    //     }).data('processStatus', 'server', {
    //         beforeSuccess: function (id, url, searchVal, result) {        //success之前的回调, 干嘛呢? 处理数据的, 如果后台不想修改数据, 你也不想修改源码, 那就用这种方式处理下数据结构吧
    //             return result.obj;  //必须return一个结果, 这个结果要符合对应的数据结构
    //         },
    //         url: $.supper("getServicePath", {
    //             "service": 'sysParameterService.getTreeNodeListByParentCode',
    //             "data": 'parentCode=PAR171023031218563'
    //         })
    //     });
    //     clearSelects = function () {
    //         formSelects.value('processStatus', [])
    //     }
    // });

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#rangeDate'
            , type: 'datetime'
            , range: "~" //或 range: '~' 来自定义分割字符
            , position: 'static'
            , theme: 'grid'
            , done: function (value, date) {
                $('#startDate').val(value)
                $('#selectDateDiv').hide();
            }
        });
    });

    main_search();
})