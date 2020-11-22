var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_main1 = $("#win_datagrid_main1");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_win_datagrid_pg1 = $("#_all_win_datagrid_pg1");
var _all_div_hidden = $("#win_form_hidden");

var _all_queryAction = "SysSalesmanService.getPagerModelObject";
var _all_updateAction = 'SysSalesmanService.updateSysSalesmanBySalesId';
var _all_deleteAction = "SysSalesmanService.deleteObject";
var _all_deleteAllAction = "SysSalesmanService.deleteAllObject";
var _verify_pass = 'SysSalesmanService.updateVerify';

var _all_queryAction1 = "SysSalesmanService.getPageModelInfo";
var _all_count = 'SysSalesmanService.getPageModelInfoCount';

var _all_win_datagrid;
var _all_win_datagrid1;
var _all_win_url_edit = "/jsp/dentistmall/salesman/addOrUpdateSalesman.jsp";
var _all_win_view_link = '/jsp/dentistmall/salesman/salesmanLink.jsp';
var _all_table_Id = "rbaId";
var _all_edit_icon = "gears";
var _all_edit_title = "添加业务员";
var _all_edit_width = 800;
var _all_edit_height = 700;
var _all_datagrid_height;
var _dblClick = function (data, row, col) {
    main_edit(data.salesmanId);
}

var _searchForm = {
    lineNum: 1,
    columnNum: 4,
    control: [],
    groupTag: [],
    items: [
        {title: "账号", name: "salesAccount", type: "text", placeholder: "账号"},
        {title: "姓名", name: "salesName", type: "text", placeholder: "姓名"},
        {title: "类型", name: "salesType", type: "select", defaultData: '全部', placeholder: "类型", impCode: 'PAR200330102555875'},
        {title: "状态", name: "state", type: "select", defaultData: '全部', placeholder: "状态", impCode: 'PAR170926033732594'}
    ]
}

function formatState(val, item, rowIndex) {
    let str = '';
    str += '<span>';
    if (item.state == 1) {
        str += '正常';
    } else {
        str += '禁用';
    }
    str += '</span>';
    return str;
}

var _Toolbar = {
    toolBarId: "tools_but",
    items: [
        {title: "查询", id: "win_but_search", icon: "search", colourStyle: "default", rounded: true, clickE: main_search},
        {title: "添加人员", id: "win_but_add", icon: "plus", colourStyle: "primary", rounded: true, clickE: main_add},
        {title: "筛选", id: "win_but_add", icon: "pencil", colourStyle: "white", rounded: true, clickE: main_filter}
    ]
}

var _ToolSalebar = {
    toolBarId: "tools_but",
    items: [
        {title: "查询", id: "win_but_search", icon: "search", colourStyle: "default", rounded: true, clickE: main_search},
        {title: "筛选", id: "win_but_add", icon: "pencil", colourStyle: "white", rounded: true, clickE: main_filter}
    ]
}


var _DataGrid = {
    cols: [
        {title: '代理编号', sortable: true, name: 'salesCode', width: 80, align: 'center'},
        {title: '账号', sortable: true, name: 'salesAccount', width: 80, align: 'center'},
        {title: '姓名', sortable: true, name: 'salesName', width: 80, align: 'center'},
        {title: '联系方式', sortable: true, name: 'salesPhone', width: 80, align: 'center'},
        {title: '类型', sortable: true, name: 'salesType', width: 60, align: 'center', impCode: 'PAR200330102555875'},
        {title: '上级人员', sortable: true, name: 'leaderName', width: 80, align: 'center'},
        {title: '所在区域', sortable: true, name: 'salesArea', width: 80, align: 'center'},
        {title: '微信', sortable: true, name: 'salesWechat', width: 80, align: 'center'},
        {title: '创建人/创建时间', sortable: true, name: 'createRen', width: 180, align: 'center', renderer: createDateRenInp},
        {title: '状态', sortable: true, name: 'state', width: 40, align: 'center', renderer: formatState},
        {title: '发展机构', name: 'control', width: 80, align: 'center', renderer: controlLink},
        {title: '操作', name: 'control', width: 180, align: 'center', renderer: control}
    ]
    , remoteSort: false
    , name: 'salesManListGrid'
    , height: _all_datagrid_height
    , url: getMMGridUrl()
    , dblClickFunc: _dblClick
    , mmPaginatorOpt: _all_win_datagrid_pg
}

var _searchForm1 = {
    lineNum: 1,
    columnNum: 2,
    control: [],
    groupTag: [],
    items: [
        {title: "名称关键字", name: "salesName", type: "text", placeholder: "名称关键字"},
        {title: "状态", name: "state", type: "select", defaultData: '全部', placeholder: "状态", impCode: 'PAR170926033732594'}
    ]
}
let salesmanId = null;
var _Toolbar1 = {
    toolBarId: "tools_but",
    items: [
        {title: "查询", id: "win_but_search1", icon: "search", colourStyle: "default", rounded: true, clickE: main_search1},
    ]
}


var _DataGrid1 = {
    cols: [
        {title: '业务员', sortable: true, name: 'salesName', width: 80, align: 'center', renderer: formatStr},
        {title: '代理商', sortable: true, name: 'agentName', width: 80, align: 'center', renderer: formatAgent},
        {title: '机构名称', sortable: true, name: 'otherName', width: 80, align: 'center', renderer: formatOther},
        {title: '状态', sortable: true, name: 'state', width: 40, align: 'center', renderer: formatState},
        {title: '发展日期', sortable: true, name: 'create_date', width: 40, align: 'center'},
    ]
    , remoteSort: false
    , name: 'salesManListGrid1'
    , height: _all_datagrid_height
    , url: getMMGridUrl1()
    , mmPaginatorOpt: _all_win_datagrid_pg1
}

function formatState(val, item, rowIndex) {
    let str = '';
    str += '<span>';
    if (item.state == 1) {
        str += '正常';
    } else {
        str += '禁用';
    }
    str += '</span>';
    return str;
}
function formatTime(val, item, rowIndex){
    let str = '';
    str += '<span>';
    if (item.create_date != undefined && item.create_date != null){
        Date.format(new Date(item.create_date.toString().replace(/-/g,"/")), "yyyy-MM-dd HH:mm:ss");
    }
    str += '</span>';
    return str;
}
function formatStr(val, item, rowIndex) {
    let str = '';
    str += '<span>' + (_user_name == undefined ? '-' : _user_name) + '</span>';
    return str;
}

function formatAgent(val, item, rowIndex) {
    let str = '';
    str += '<span>' + (item.agent_company == undefined ? '-' : item.agent_company) + '</span>';
    return str;
}

function formatOther(val, item, rowIndex) {
    let str = '';
    let rbaName = item.rba_name == undefined ? '' : item.rba_name;
    let rbsName = item.rbs_name == undefined ? '' : item.rbs_name;
    let rbbName = item.rbb_name == undefined ? '' : item.rbb_name;
    if (rbaName == '' && rbsName == '' && rbbName == '') {
        str = '<span>-</span>';
        return str;
    }
    str += '<span>';
    if (rbaName != ''){
        str += rbaName + '-';
    }
    if (rbsName != '') {
        str += rbsName + '-';
    }
    if (rbbName != '') {
        str += rbbName + '-';
    }
    str = str.substring(0, str.length - 1);
    str += '</span>';

    return str;
}


/**
 * 页面初始化函数
 */
$(function () {
    if (_user_type == 6){
        $('#show2').show();
        $('#show1').hide();
        $('#export1').hide();
        $('#export2').show();
        init2();
    }else {
        $('#show1').show();
        $('#show2').hide();
        $('#export1').show();
        $('#export2').hide();
        init1();
    }
});

function init2() {
    _all_win_searchForm.html('');
    _all_div_hidden.html('');
    _all_win_tools_but.html('');
    // _all_win_datagrid_main1.html('');
    // _all_win_datagrid = null;
    $('#otherShow').hide();
    $('#myShow').show();

    _all_win_searchForm.xform('createForm', _searchForm1);

    _all_div_hidden.xform('createhidden', _searchForm1.hiddenitems);

    _all_win_tools_but.xtoolbar('create', _Toolbar1);

    _all_datagrid_height = $(window).height() - _all_win_searchForm.height() - 64 - 95;

    _DataGrid1.height = _all_datagrid_height;

    _all_win_datagrid1 = _all_win_datagrid_main1.xdatagrid('create', _DataGrid1);

    main_search1();

    /**
     * 改变查询,添加按钮样式
     */
    $("#win_but_search1").css("width", "95px");
    $("#win_but_search1").css("vertical-align", "middle");
}

function init1() {
    _all_win_searchForm.html('');
    _all_div_hidden.html('');
    _all_win_tools_but.html('');
    // _all_win_datagrid_main.html('');
    // _all_win_datagrid = null;
    $('#otherShow').show();
    $('#myShow').hide();

    _all_win_searchForm.xform('createForm', _searchForm);
    _all_div_hidden.xform('createhidden', _searchForm.hiddenitems);

    // if (_user_type == 6) {
    //     _all_win_tools_but.xtoolbar('create', _ToolSalebar);
    // } else {
        _all_win_tools_but.xtoolbar('create', _Toolbar);
    // }


    _all_datagrid_height = $(window).height() - _all_win_searchForm.height() - 64 - 95;

    _DataGrid.height = _all_datagrid_height;
    _all_win_datagrid = _all_win_datagrid_main.xdatagrid('create', _DataGrid);

    main_search();

    /**
     * 改变查询,添加按钮样式
     */
    $("#win_but_search").css("width", "95px");
    $("#win_but_search").css("vertical-align", "middle");

    $("#win_but_add").css("width", "95px");
    $("#win_but_add").css("vertical-align", "middle");
}

function createDateRenInp(val, item, rowIndex) {
    var str = '<span>' + item.createRen + '</span><span style="margin-left: 10px">' + item.createDate + '</span>';
    return str;
}
function controlLink(val, item, rowIndex) {
    var str = "";
    str += "<a onclick=\"view_link(" + item.salesmanId + ",'" + item.salesName + "','" + item.agentCompany + "')\"  class='' style='color: blue;text-decoration: underline'>查看关联</a>&nbsp;&nbsp;";
    return str;
}
var salesmanIds=null;
function control(val, item, rowIndex) {
    salesmanIds!=item.salesmanId;
    var str = "";
    str += "<a onclick=\"main_edit(" + item.salesmanId + ")\"  class='btn btn-warning  btn-xs'>编辑</a>&nbsp;&nbsp;";
    if(item.state != 1) {
        str += "<a onclick=\"main_State(" + item.salesmanId + ", 1)\"  class='btn btn-info  btn-xs'>启用</a>&nbsp;&nbsp; ";
    } else {
        str += "<a onclick=\"main_State(" + item.salesmanId + ", 2)\"  class='btn btn-info  btn-xs'>禁用</a>&nbsp;&nbsp; ";
    }
    if (item.verifyState != undefined && item.verifyState != null && item.verifyState != '') {
        if (item.verifyState == "1") {
            if (_user_type != undefined && _user_type != null && _user_type == '6') {
                str += "<span>审核中</span>";
            } else {
                str += "<a class='btn btn-warning  btn-xs' onclick='verifyObj(\"" + item.salesmanId + "\", \"" + item.salesCode + "\", \"" + item.salesmanName + "\")'>审核</a>&nbsp;&nbsp;";
            }
        } else if (item.verifyState == "2") {
            if (_user_type != undefined && _user_type != null && _user_type == '6') {
                str += "<span style='color: red' onmouseover='showSpanMsg(this, \"" + item.verifyRemark + "\")' onmouseout='hideSpanMsg()'>驳回</span>";
                str += '<a style="padding-left: 10px" onclick="verifyAgain(\'' + item.salesmanId + '\')">再次提交</a>';
            }
        }
    }
    return str;
}

function verifyAgain(id) {
    let vdata = 'id=' + id;
    $.supper("confirm", {
        title: "再次提交", msg: "确认再次提交？", yesE: function () {
            vdata += '&verifyState=1';
            $.supper("doservice", {
                "service": _verify_pass, "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                    }
                    // layer.close(index);
                }
            });
        }
    });
}

let tips = null;
function showSpanMsg(e, message) {
    if (message === undefined || message === 'undefined' || message === null || message === '') {
        message = '无信息';
        return;
    }
    tips = layer.tips(message, $(e), {tips: 3, time: 0});
}

function hideSpanMsg() {
    if (tips === null) {
        return;
    }
    layer.close(tips);
    tips = null;
}

function verifyObj(id, code, name) {
    let html = '<div style="line-height: 98px;text-align: center">是否通过创建的业务员或代理商：<span>' + code + '</span></div>';
    let vdata = 'id=' + id;

    //示范一个公告层
    layer.open({
        type: 1
        , title: "审核操作" //不显示标题栏
        , closeBtn: 1
        , area: ['400px', '200px']
        , shade: 0.8
        , id: 'LAY_layuipro' //设定一个id，防止重复弹出
        , resize: false
        , btn: ['通过', '驳回']
        , btnAlign: 'c'
        , moveType: 1 //拖拽模式，0或者1
        , content: html
        , btn1: function (index, layero) {
            vdata += '&verifyState=3';
            $.supper("doservice", {
                "service": _verify_pass, "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                    }
                    layer.close(index);
                }
            });
        }
        , btn2: function (index, layero) {
            layer.prompt({title: '输入驳回理由', formType: 2}, function(text, index1){

                vdata += '&verifyState=2&verifyRemark=' + text;
                $.supper("doservice", {
                    "service": _verify_pass, "data": vdata, "BackE": function (jsondata) {
                        if (jsondata.code == "1") {
                            $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                        }
                        layer.close(index1);
                    }
                });
            });
            layer.close(index);
        }
    });
}

function main_State(salesmanId, state) {
    $.supper("confirm", {
        title: "删除操作", msg: "确认操作状态？", yesE: function () {
            $.supper("doservice", {
                "service": 'SysSalesmanService.updateSysSalesmanStateBySalesId', "data": 'state=' + state + '&salesmanId=' + salesmanId, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                    } else
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                }
            });
        }
    });
}


/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl() {
    var data = _all_win_searchForm.serialize();
    data += '&' + $('#filterForm').serialize();
    var att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
    return att_url;
}

function getMMGridUrl1() {
    var data = _all_win_searchForm.serialize();
    data += '&salesmanId=-1';
    var att_url = $.supper("getServicePath", {"service": _all_queryAction1, "data": data});
    return att_url;
}

function intiCount() {
    let data = '';
    if (salesmanId != null) {
        data = 'salesmanId=' + salesmanId;
    }

    $.supper("doservice", {
        "service": _all_count, "data": data, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $('#agentCount').text(jsondata.obj.agentCount);
                $('#otherCount').text(jsondata.obj.otherCount);
            }
        }
    });
}

/**
 * 对主表进行查询（刷新）操作
 */
function main_search() {
    $('#divFilter').hide('fast');
    $('.u-mask').hide();
    _all_win_datagrid.opts.url = getMMGridUrl();
    _all_win_datagrid.load();
}

function main_search1() {
    $('#divFilter').hide('fast');
    $('.u-mask').hide();
    _all_win_datagrid1.opts.url = getMMGridUrl1();
    _all_win_datagrid1.load();
    intiCount();
}

/**
 * 添加业务员
 */
function main_add() {
    var att_url = $.supper("getServicePath", {url: _all_win_url_edit});
    $.supper("setProductArray", {"name":"menuItemSalesmanUrl", "value":{url: att_url, isEdit: false}});
    //	var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
    $.supper("showTtemWin", {"url": att_url, "title": _all_edit_title});
}

function main_filter() {
    $('#province').change(function () {
        initShi();
    });

    $('#city').change(function () {
        initArea();
    });
    initShen(null);
    initShi(null);
    initArea(null);
    $('#divFilter').show('fast');
    $('.u-mask').show();

}

function cancelFilter() {
    $('#divFilter').hide('fast');
    $('.u-mask').hide();
}

/**
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function main_edit(id) {
    var att_title = "业务员维护";
    var service = 'SysSalesmanService.getObject';
    var att_url = $.supper("getServicePath", {"data": 'salesmanId=' + id, "service": service, url: _all_win_url_edit});
    $.supper("setProductArray", {"name":"menuItemSalesmanUrl", "value":{url: att_url, isEdit: true}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}

/**
 * 查看业务员或者代理商关联
 * @param id
 */
function view_link(id, name, agentCompany) {
    var att_title = "发展机构";
    var att_url = $.supper("getServicePath", {url: _all_win_view_link});
    $.supper("setProductArray", {"name":"salesmanId", "value":{id: id, name: name, agentCompany: agentCompany}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}

/**
 * 删除选中行记录
 * @param id 选中行记录主键值
 */
function main_delete(id) {
    eval("var vdata= '" + _all_table_Id + "=" + id + "'");
    $.supper("confirm", {
        title: "删除操作", msg: "确认删除记录操作？", yesE: function () {
            $.supper("doservice", {
                "service": _all_deleteAction, "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                    } else
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                }
            });
        }
    });
}

/**
 * 删除多条选中行记录
 */
function main_allDelete() {
    var rows = _all_win_datagrid.selectedRows();
    if (rows == null || rows.length == 0) {
        $.supper("alert", {title: "操作提示", msg: "请先选择要操作的数据记录！"});
        return;
    }
    var rbsIds = "";
    for (var i = 0; i < rows.length; i++) {
        eval('rbsIds += rows[i].' + _all_table_Id + '+","; ');
    }
    rbsIds = rbsIds.substring(0, rbsIds.length - 1);
    $.supper("confirm", {
        title: "批量删除操作", msg: "确认删除记录操作？", yesE: function () {
            eval("var vdata= '" + _all_table_Id + "s=" + rbsIds + "'");
            $.supper("doservice", {
                "service": _all_deleteAllAction, "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: main_search});
                    } else
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                }
            });
        }
    });
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

function changeSalesShow(typeIndex) {
    if (typeIndex == 1){
        $('#show1').show();
        $('#show2').hide();
        $('#export1').show();
        $('#export2').hide();
        init1();
    }else {
        $('#show2').show();
        $('#show1').hide();
        $('#export1').hide();
        $('#export2').show();
        init2();
    }
}
function main_export() {
    var vdata="&salesmanIds=''";
    var newTab=window.open('about:blank');//ExportExcelService   //SysSalesmanService
    $.supper("doservice", {"service":"ExportExcelService.exportSalesManAll","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}
function main_exportAllDaiLi() {
    var vdata="&salesmanIds=''";
    // var vdata="&salesmanIds=salesmanIds";
    var newTab=window.open('about:blank');//SysSalesmanService
    $.supper("doservice", {"service":"SysSalesmanService.exportSalesManAgent","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}
function main_exportS1() {//业务员不传ID 直接用管理员导出全部的方法
    // var vdata="states1="+1;
    var vdata="&salesmanIds=''";
    var newTab=window.open('about:blank');//SysSalesmanService
    $.supper("doservice", {"service":"ExportExcelService.exportSalesMan","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}