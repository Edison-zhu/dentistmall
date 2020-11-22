// var _all_accountForm = $("#accountForm");
var _wmsMiIds = [];
var _mmfIds = [];
var _enterData = {};
var enterType = 0;
var url;
var isNew;
//2020年07月08日16:41:58朱燕冰修改
let _mdpId;
let _mdpsId;
var mmg;
var lastExpandNode;
var queryAction = "mdMaterielInfoService.getMyPagerModelObjectBySomeCase";
var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
var _currentList = [];
var saveWmsMiIds=[];
var saveMmfIds=[];
var _dblClick = function (data, row, col) {
    addMaterielDetail(data.wmsMiId);
}

var _searchName;
var _wmsMiId;
var _mmfId;
var _type; //是那种类型跳转到此页面，1入库

var isLoading = false; // 物料入库是否正在查询中，false为未加载
var lastSearchName = ''; // 当isLoading为true时，记录最近一次输入的值进行查询
var canHideTable = true;

var initDataGrid = function () {
    var cols = [
        // {titleHtml: '<span style="font-size: 12px">物料分类</span>',sortable: true, name: 'mdpId', width: 180, align: 'center',
        //     renderer: function (val, item, rowIndex) {
        //         if(val == undefined){
        //             return '<span style="font-size: 14px;"></span>';
        //         }else {
        //             return '<span style="font-size: 14px;">' + val + '</span>';
        //         }
        //     }},
        {titleHtml: '<span style="font-size: 12px">物料编号</span>',sortable: true, name: 'matCode', width: 200, align: 'center',
            renderer: function (val, item, rowIndex) {
                if(val == undefined){
                    return '<span style="font-size: 14px;"></span>';
                }else {
                    return '<span style="font-size: 14px;">' + val + '</span>';
                }
            }},
        {titleHtml: '<span style="font-size: 12px">物料名称</span>',sortable: true, name: 'matName', width: 200, align: 'center',
            renderer: function (val, item, rowIndex) {

                if(val == undefined){
                    return '<span style="font-size: 14px;"></span>';
                }else {
                    return '<span style="font-size: 14px;">' + val + '</span>';
                }
            }},
        // {titleHtml: '<span style="font-size: 12px">图标</span>', name: 'logo', width: 50, align: 'center', renderer: renderLogo},
        {titleHtml: '<span style="font-size: 12px">规格型号</span>', sortable: true, name: 'norm', width: 130, align: 'center',
            renderer: function (val, item, rowIndex) {
                if(val == undefined){
                    return '<span style="font-size: 14px;"></span>';
                }else {
                    return '<span style="font-size: 14px;">' + val + '</span>';
                }
            }},
        {titleHtml: '<span style="font-size: 12px">当前库存</span>', name: 'baseNumber', width: 130, align: 'center',
            renderer: function (val, item, rowIndex) {
                if(val == undefined){
                    return '<span style="font-size: 14px;"></span>';
                }else {
                    return '<span style="font-size: 14px;">' + val + '</span>';
                }
            }},
        {titleHtml: '<span style="font-size: 12px">单位</span>', name: 'basicUnit', width: 130, align: 'center', renderer: renderUnit},
        // {titleHtml: '<span style="font-size: 12px">品牌</span>', sortable: true, name: 'brand', width: 100, align: 'center',
        //     renderer: function (val, item, rowIndex) {
        //         if(val == undefined){
        //             return '<span style="font-size: 14px;"></span>';
        //         }else {
        //             return '<span style="font-size: 14px;">' + val + '</span>';
        //         }
        //     }},
        // {titleHtml: '<span style="font-size: 12px">生产厂家</span>', sortable: true, name: 'productFactory', width: 80, align: 'center',
        //     renderer: function (val, item, rowIndex) {
        //         if(val == undefined){
        //             return '<span style="font-size: 14px;"></span>';
        //         }else {
        //             return '<span style="font-size: 14px;">' + val + '</span>';
        //         }
        //     }},
        // {titleHtml: '<span style="font-size: 12px">采购均价/零售价</span>', sortable: true, name: 'avgPrice', width: 120, align: 'center', renderer: renderPrice},
        // {titleHtml: '<span style="font-size: 12px">操作</span>', name: 'con', width: 80, align: 'center', renderer: control}
    ];

    var att_mmgurl = getUrl();
    mmg = $('#datagrid2').mmGrid({
        height: 'auto',
        width:'auto'
        , cols: cols
        , nowrap: true
        , method: 'get'
        // , remoteSort: true
        , url: att_mmgurl
        , sortName: 'SECUCODE'
        , sortStatus: 'asc'
        , multiSelect: true
        // , checkCol: true
        , fullWidthRows: false
        , showBackboard: false,
        cellSelected:cellSelecteds,
        dblClickFunc: _dblClick
        , autoLoad: false
        , plugins: [
            $('#pg').mmPaginator({})
        ]
    });
    mmg.load();
    mmg.on('loadSuccess', nextSearch);
    mmg.on('loadSuccess', function (e, data) {
        _currentList = data.items;

    });
    mmg.on('cellSelected',cellSelecteds);
    mmg.on('cellSelected', function(e, item, rowIndex, colIndex){

    })
}
var initDataGrid2 = function () {
    var cols = [
        {titleHtml: '<span style="font-size: 12px">物料编号</span>',sortable: true, name: 'matCode', width: 200, align: 'center',
            renderer: function (val, item, rowIndex) {
                if(val == undefined){
                    return '<span style="font-size: 14px;"></span>';
                }else {
                    return '<span style="font-size: 14px;">' + val + '</span>';
                }
            }},
        {titleHtml: '<span style="font-size: 12px">物料名称</span>',sortable: true, name: 'matName', width: 200, align: 'center',
            renderer: function (val, item, rowIndex) {

                if(val == undefined){
                    return '<span style="font-size: 14px;"></span>';
                }else {
                    return '<span style="font-size: 14px;">' + val + '</span>';
                }
            }},
        // {titleHtml: '<span style="font-size: 12px">图标</span>', name: 'logo', width: 50, align: 'center', renderer: renderLogo},
        {titleHtml: '<span style="font-size: 12px">规格型号</span>', sortable: true, name: 'norm', width: 130, align: 'center',
            renderer: function (val, item, rowIndex) {
                if(val == undefined){
                    return '<span style="font-size: 14px;"></span>';
                }else {
                    return '<span style="font-size: 14px;">' + val + '</span>';
                }
            }},
        {titleHtml: '<span style="font-size: 12px">当前库存</span>', name: 'baseNumber', width: 130, align: 'center',
            renderer: function (val, item, rowIndex) {
                if(val == undefined){
                    return '<span style="font-size: 14px;"></span>';
                }else {
                    return '<span style="font-size: 14px;">' + val + '</span>';
                }
            }},
        {titleHtml: '<span style="font-size: 12px">单位</span>', name: 'basicUnit', width: 130, align: 'center', renderer: renderUnit},
        // {titleHtml: '<span style="font-size: 12px">品牌</span>', sortable: true, name: 'brand', width: 100, align: 'center',
        //     renderer: function (val, item, rowIndex) {
        //         if(val == undefined){
        //             return '<span style="font-size: 14px;"></span>';
        //         }else {
        //             return '<span style="font-size: 14px;">' + val + '</span>';
        //         }
        //     }},
        // {titleHtml: '<span style="font-size: 12px">生产厂家</span>', sortable: true, name: 'productFactory', width: 80, align: 'center',
        //     renderer: function (val, item, rowIndex) {
        //         if(val == undefined){
        //             return '<span style="font-size: 14px;"></span>';
        //         }else {
        //             return '<span style="font-size: 14px;">' + val + '</span>';
        //         }
        //     }},
        // {titleHtml: '<span style="font-size: 12px">采购均价/零售价</span>', sortable: true, name: 'avgPrice', width: 120, align: 'center', renderer: renderPrice},
        {titleHtml: '<span style="font-size: 12px">操作</span>', name: 'con', width: 80, align: 'center', renderer: control}
    ];

    var att_mmgurl = getUrl();
    mmgs = $('#datagrid3').mmGrid({
        height: 'auto',
        width:'auto'
        , cols: cols
        , nowrap: true
        , method: 'get'
        // , remoteSort: true
        , url: att_mmgurl
        , sortName: 'SECUCODE'
        , sortStatus: 'asc'
        , multiSelect: true
        // , checkCol: true
        , fullWidthRows: false
        , showBackboard: false,
        cellSelected:cellSelecteds,
        dblClickFunc: _dblClick
        , autoLoad: false
        , plugins: [
            $('#pg').mmPaginator({})
        ]
    });
    mmgs.load();
    mmgs.on('loadSuccess', nextSearch);
    mmgs.on('loadSuccess', function (e, data) {
        _currentList = data.items;

    });
    mmgs.on('cellSelected',cellSelecteds);
    mmgs.on('cellSelected', function(e, item, rowIndex, colIndex){

    })
    // if (_type != undefined && _type == 1)
    //     mmg.on('loadSuccess', checkRows);
}

var _dblClick = function clockRows(data, row, col){
    selectMatRow(data.wmsMiId,data.mmfId)
    console.log("999")
}

function cellSelecteds(e, item, rowIndex, colIndex){
    var aaa = mmg.selectedRows();
    console.log("123")
    for (let i=0;i<=aaa.length;i++){
        // saveWmsMiIds = []
        // saveMmfIds = []
        saveWmsMiIds.push(aaa[i].wmsMiId)
        saveMmfIds.push(aaa[i].mmfId)
    }
}
function houseCancel(){
    $("#tables").hide();
}
function houseCancels(){
    $("#tables2").hide();
}
function queding(){
    console.log("确定按钮")
    let a = saveWmsMiIds.join(',')
    let b = saveMmfIds.join(',')
    addMated(a,b)
}
function selectMatRow(wmsMiId, mmfId) {
    addMated(wmsMiId, mmfId)
    $("#tables").hide();
   
}
function selectRow(wmsMiId) {

}
function enterSearch(selector, exclude) {
    $('#selectMat').val($(selector).val());
    if (event.keyCode == 13)
        searchs(exclude);
}
function renderLogo(val, item, rowIdex) {
    let str = '';
    if (item.lessenFilePath != undefined && item.lessenFilePath != '') {
        str += '<img src="' + item.lessenFilePath + '" style="width: 40px; height: 40px;" />';
    }
    return str;
}
function renderUnit(val, item, rowIdex) {
    let str = '';
    str += '<span style="font-size: 14px">' + item.basicUnit+ '</span>/<span style="font-size: 14px">' + (item.splitUnit == undefined ? '' : item.splitUnit) + '</span>';
    return str;
}
var _items = {};
function control(val, item, rowIndex) {
    let str = '';
    if (_wmsMiId != undefined) {
        if (_items[item.mmfId] == undefined) {
            _items[item.mmfId] = {};
        }
        _items[item.mmfId] = item;
        return "<a onclick=\"selectRow(" + item.mmfId + ")\" class='btn btn-info btn-sm'>确定</a>";
    } else {
        return "<a onclick=\"selectMatRow(" + item.wmsMiId + ", " + item.mmfId + ")\" class='btn btn-info btn-sm' id='selectMatRows'>确定</a>";
    }
}
function renderPrice(val, item, rowIndx) {
    let str = '';
    str += '<span style="font-size: 14px">' + (item.price == undefined ? '' : item.price)+ '</span>/<span style="font-size: 14px">' + (item.retailPrice == undefined ? '' : item.retailPrice) + '</span>';
    return str;
}
function getUrl(exclude) {
    var data = 'searchName=' + (lastSearchName == '' ? $("#selectMats").val() : lastSearchName);
    if (exclude != undefined)
        data = 'searchName=' + $('#selectMats').val();
    // let att_mmgurl = rpc.getUrlByForm(queryAction, "selectMat");
    let att_mmgurl = $.supper("getServicePath", {"service":queryAction, "data":data});
        if (_mdpId != undefined) {
            att_mmgurl += '&mdpId=' + _mdpId;
        }
        if (_mdpsId != undefined) {
            att_mmgurl += '&mdpsId=' + _mdpsId;
        }
    return att_mmgurl;
}
function getUrls(exclude) {
    var data = 'searchName=' + (lastSearchName == '' ? $("#searchNames").val() : lastSearchName);
    if (exclude != undefined)
        data = 'searchName=' + $('#searchNames').val();
    // let att_mmgurl = rpc.getUrlByForm(queryAction, "selectMat");
    let att_mmgurls = $.supper("getServicePath", {"service":queryAction, "data":data});
    if (_mdpId != undefined) {
        att_mmgurls += '&mdpId=' + _mdpId;
    }
    if (_mdpsId != undefined) {
        att_mmgurls += '&mdpsId=' + _mdpsId;
    }
    return att_mmgurls;
}

function search(exclude) {
    var att_mmgurl = getUrl(exclude);
    mmg.opts.url = att_mmgurl;
    mmg.load();

}
function searchs(exclude) {
    var att_mmgurls = getUrls(exclude);
    mmgs.opts.url = att_mmgurls;
    mmgs.load();

}
// 存在新的搜索词，加载完上次的数据后，搜索最近一次的输入值
function nextSearch() {
    if (lastSearchName == '') {
        isLoading = false;
    }
    if (isLoading == false)
        return;
    lastSearchName = '';
    search();
}

$(function () {
    initDataGrid();
    initDataGrid2();
    var checkInventory = $.supper("getProductArray", "addEnter");
    if (checkInventory != null) {
        url = checkInventory.url;
        isNew = checkInventory.isNew;
    }

    laydate({
        elem: '#cgDate',
        format: 'YYYY-MM-DD' //日期格式
    });
    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if (selOutOrderType != null) {
        $('#orderWarehous').click();
        $.supper("setProductArray", {"name": "selOutOrderType", "value": null});
    }
    loadItemZtree();
    getNewCode();
    $("#selectMat").bind("input propertychange",function(event){
        if (innerInput == true)
            return;
        $('#searchName').val($(this).val());
        // 如果正在加载数据中，保存现有的搜索词
        if (isLoading == true) {
            lastSearchName = $(this).val();
            return;
        }
        if (timeOut != undefined && timeOut != null)
            clearTimeout(timeOut);
        timeOut = setTimeout(function () {
            isLoading = true;
            search();
        }, 500)
    });
    $('#selectMat').on('focus', function () {
        canHideTable = true;
        if ($(this).val() != '')
            $("#tables").show();
    })
    $('#tables').on('mouseover', function () {
        canHideTable = false;
    })
    $('#tables').on('mouseleave', function () {
        canHideTable = true;
    })

    $(document).on('click', function (event) {
        if ($('#selectMat').is(':focus') == false) {
            if (canHideTable == true) {
                canHideTable = false;
                $("#tables").hide();
            }
        }
    })
    // window.eventGetTimes = function (that){
    //     var routeTime = {
    //         max: '2099-06-16 23:59:59',
    //         format: 'yyyy-MM-dd HH:mm:ss',
    //         type: 'datetime',
    //         value: new Date()
    //     };
    //     routeTime.elem = that;
    //     laydate.render(elem);
    // }
});
function getNewCode() {
    $.supper("doservice", {
        "service": "SysParameterService.getNewCode",
        "options": {
            "type": "post",
            "data": "prefix=RKS",
            "async": false
        }, "ifloading": 1, "BackE": function (jsondata) {
            if (jsondata.code == "1" && jsondata.obj) {
                $("#warehousCode").val(jsondata.obj);
            }
        }
    });
}

//处理单选radio
function radio() {
    $("#orderWarehous").attr("checked", false);
    $("#orderWarehous2").attr("checked", false);
}

function radio1() {
    if (JSON.stringify(_enterData) !== '{}') {
        $.supper("confirm", {
            title: "提示", msg: "您还未保存订单入库，是否继续？", yesE: function () {
                $("#mxMatListMx").html('');
                showRaido1();
            },
            noE: function () {
                $("#orderWarehous").attr("checked", false);
            }
        });
    } else {

        showRaido1();
    }
}

function showRaido1() {
    enterType = 1;
    _enterData = {};
    $("#hideDiv").show();
    $("#mx1").hide();
    $('#mx2').hide();
    main_add(1);
    $('table').on('click', 'tr', function (e) {
        // 获取table中所有的行
        var trs = $(this).parent('tbody').find('tr');
        // 取消所有行的选中
        $.each(trs, function (index, item) {
            // 如果在此使用的是attr()设置选中状态,取消后将不能设置为选中
            $(item).find('td:eq(0) input').prop('checked', false);
        });
        // 选中当前点击的行
        $(this).find('td:eq(0) input').prop('checked', true)
    })
    $("#orderWarehous2").attr("checked", false);
}

function radio2() {
    if (moiId1 != undefined && moiId1 != null && moiId1 != '') {
        $.supper("confirm", {
            title: "操作提醒", msg: "您还未保存订单入库，是否继续？", yesE: function () {
                $("#mxListMx").html('');
                showRaido2();
            },
            noE: function () {
                $("#orderWarehous2").attr("checked", false);
            }
        });
    } else {
        showRaido2();
    }
}
function showRaido2() {
    moiId1 = null;
    enterType = 2;
    _enterData = {};
    $("#hideDiv").hide();
    $("#orderWarehous").attr("checked", false);
    $('#mx1').hide();
    $('#mx2').show();
    $('#selectMat').focus();
    $('#selectMat').addClass('focus');
}

var innerInput = false;
function removeClass() {
    innerInput = false;
    // $('#selectMat').removeClass('focus');
}

function addClass() {
    innerInput = true;
    // $('#selectMat').addClass('focus');
}

function quXiao() {
    $("#hideDiv").hide();
    $("#orderWarehous").attr("checked", false);
    $("#orderWarehous2").attr("checked", false);
}

function main_add(i) {
    if (i == 1) {
        var gjz = $("#gjz").val('');
        var cgDate = $("#cgDate").val('');
        var cgRen = $("#cgRen").val('');
        var vdata = '';
    } else if (i == 0) {
        var gjz = $("#gjz").val();
        var cgDate = $("#cgDate").val();
        var cgRen = $("#cgRen").val();
        var vdata = "&gjz=" + gjz + "&cgDate=" + cgDate + "&cgRen=" + cgRen;
    }
    $.supper('initPagination', {
        id: "Pagination2",
        service: "ModifypriceService.getPagerModelEnterOrder",
        data: vdata,
        limit: 5,
        isAjax: "1",
        "BackE": initListAdd
    });
}

function initListAdd(jsondata) {
    var mxList = jsondata;
    var str = "";
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            str += "<tr>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type=\"checkbox\" onclick='choice(" + mxList[i].moiId + ")'></td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].orderCode + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].PlaceOrderTime + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].placeOrderMoney + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].commodityNumber + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].enterNumber2 + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].applicantName + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].purchaseAccount + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].ProcessStatus + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><a id=\"btn1\"style=\"color: #46A3FF;text-decoration: underline;\" onclick='choice(" + mxList[i].moiId + ")'>选择</a>&nbsp;&nbsp;</td>";
            str += "</tr>";
        }
    }
    $("#mxList1").html(str);
}

var moiId1 = "";

function choice(moiId) {
    moiId1 = moiId;
     const BTN1 = document.getElementById('btn1');
     const BTN2 = document.getElementById('btn2');
     // BTN1.addEventListener('click', () => {

     //     BTN2.onclick();
     // });
        BTN2.onclick();


}

function queD() {
    $("#hideDiv").hide();
    $("#mx1").show();
    if (moiId1 != undefined && moiId1 != null && moiId1 != "") {
        searchAllMX(1, moiId1);
    } else {
        searchAllMX(1, 0);
    }

}

function searchAllMX(i, moiId) {
    if (i == 0) {
        var vdata = '';
    } else if (i == 1) {
        var state1 = 1;
        var vdata = "&moiId=" + moiId + "&state1=" + state1;
    } else if (i = 2) {
        var vdata = "&moiId=" + moiId;
    }
    let queryAction = "MdOrderInfoService.getPagerModelEnterOrder";
    //"ModifypriceService.getAddgetWarehousingMx",
    // searchAll1(i);
    showLoading()
    $.supper('initPagination', {
        id: "Pagination1",
        service: "ModifypriceService.getAddgetWarehousingMx",
        data: vdata,
        limit: 10,
        isAjax: "1",
        "BackE": initListMx
    });
}

function searchMatMx(wmsMiId, mmfId) {
    let data = '';
    if (_wmsMiIds.length > 0) {
        data += 'wmsMiIds=' + _wmsMiIds.join(',');
    }
    if (_mmfIds.length > 0) {
        data += '&mmfIds=' + _mmfIds.join(',');
    }
    showLoading();
    $.supper('initPagination', {
        id: "Pagination1",
        service: "mdMaterielInfoService.getMyPagerModelObject",
        data: data,
        limit: 10,
        isAjax: "1",
        "BackE": initMatListMx
    });
}

function showLoading() {

    var inntHtml = '';
    inntHtml += '<div id="loading-mask"  class="u-shade" style="z-index:9098910159999;display:block ">';
    inntHtml += '<img src="/dentistmall/img/loading.gif" alt=""></div>';

    // if (top == self) {
        $(this.document.body).append(inntHtml);
        $('#loading-mask').fadeIn('fast');
        // 是顶级窗口
    // } else {
    //     // 不是顶级窗口
    //     return top.showLoading();
    // }
}

function closeLoading(){
    // if (top == self) {
        $('#loading-mask').fadeOut('fast');
        $('#loading-mask').remove();
        // 是顶级窗口
    // } else {
    //     // 不是顶级窗口
    //     return top.closeLoading();
    // }

}

var mycars = new Array();

//订单入库表格
function initListMx(jsondata) {
    closeLoading();
    var mxList = jsondata;
    var momId1;
    let mmfId;
    var str = "";
    var productNameInt;
    let ratio;
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            momId1 = mxList[i].momId;
            mmfId = mxList[i].mmfId;
            ratio = mxList[i].basicCoefficent == undefined ? 1 : mxList[i].basicCoefficent;
            // var reg=/^[\u4E00-\u9FA5]+$/;
            // if(!reg.test(mxList[i].productName)){
            //     productNameInt=parseInt(mxList[i].productName);
            // }elsel{
            //
            // }
            // if (mxList[i].productName) {
            //
            // }
            if (_enterData[mmfId] == undefined) {
                _enterData[mmfId] = {};
                _enterData[mmfId].matCode = mxList[i].matCode == undefined ? '' : mxList[i].matCode;
                _enterData[mmfId].momId = mxList[i].momId == undefined ? '' : mxList[i].momId;
                _enterData[mmfId].enterNumber = mxList[i].enterNumber == undefined ? 0 : mxList[i].enterNumber;
                _enterData[mmfId].matNumber = mxList[i].matNumber == undefined ? 0 : mxList[i].matNumber;
                _enterData[mmfId].number2 = mxList[i].number2 == undefined ? 0 : Number(mxList[i].number2);
                _enterData[mmfId].unitMoney = 0;//mxList[i].unitMoney == undefined ? 0 : mxList[i].unitMoney;
                // _enterData[mmfId].baseNumber = mxList[i].leftNumber == undefined ? '' : mxList[i].leftNumber;
                _enterData[mmfId].ratio = mxList[i].basicCoefficent == undefined ? 1 : mxList[i].basicCoefficent;
                // _enterData[mmfId].splitNumber = 0; //_enterData[mmfId].baseNumber == '' ? 0 : (_enterData[mmfId].baseNumber * _enterData[mmfId].ratio);
                // _enterData[mmfId].splitNumber = mxList[i].splitQuantity == undefined ? '' : mxList[i].splitQuantity;
                _enterData[mmfId].unit = mxList[i].unit == undefined ? '' : mxList[i].unit;
                _enterData[mmfId].retailPrice = mxList[i].retailPrice == undefined ? '' : mxList[i].retailPrice;
                _enterData[mmfId].batchNode = '';//mxList[i].batchCode == undefined ? '' : mxList[i].batchCode;
                _enterData[mmfId].valiedDate = '';//mxList[i].valiedDate == undefined ? '' : mxList[i].valiedDate;
                _enterData[mmfId].backPrinting = '';//mxList[i].backPrinting == undefined ? '' : mxList[i].backPrinting;
                _enterData[mmfId].mdpId = mxList[i].mdp == undefined ? '' : mxList[i].mdpId;
                _enterData[mmfId].mdpsId = mxList[i].mdp == undefined ? '' : mxList[i].mdpsId;
                // _enterData[mmfId].ratio1 = mxList[i].ratio1 == undefined ? ratio : mxList[i].ratio1;

                // _enterData[mmfId].basicUnit = mxList[i].BasicUnit == undefined ? '' : mxList[i].BasicUnit;
                _enterData[mmfId].mmfCode = '';
                _enterData[mmfId].matName1 = '';
                _enterData[mmfId].mmfName = '';
                _enterData[mmfId].basicUnit = '';
                _enterData[mmfId].baseNumber = '';
                _enterData[mmfId].quantity = 0;
                _enterData[mmfId].matCode1 = '';
                _enterData[mmfId].remark = '';
            }
            // let
            if (_enterData[mmfId].matName1 != undefined && _enterData[mmfId].matName1 != '') {

            }
            mycars[i] = momId1;
            let basicUnit = _enterData[mmfId].basicUnit;
            str += "<tr>";
            // str += "<td id='matCode" + mmfId + "' style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].matCode + "<span style='display: none' id='wmsMiId" + mmfId + "'>" + mxList[i].wmsMiId + "</span><span style='display: none' id='mmfId" + mmfId + "'>" + mxList[i].mmfId + "</span><span style='display: none' id='mmtId" + mmfId + "'>" + mxList[i].mmtId + "</span></td>";
            str += "<td id='matName" + mmfId + "' style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].matName + "</td>";
            str += "<td id='brand" + mmfId + "' style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].brand + "</td>";
            // str += "<td id='mmfNameCode" + mmfId + "' style=\"text-align:left;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='mmfName" + mmfId + "'>" + mxList[i].mmfName + "</span><br/><span id='mmtCode" + mmfId + "'>" + mxList[i].mmtCode + "</span></td>";
            str += "<td id='mmfNameCode" + mmfId + "' style=\"text-align:left;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='mmfName" + mmfId + "'>" + mxList[i].mmfName + "</span></td>";
            str += "<td id='productName" + mmfId + "' style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].productName + "</td>";
            str += "<td id='basicUnit" + mmfId + "' style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + _enterData[mmfId].basicUnit + "</td>";
            str += "<td id='matNumber2" + mmfId + "' style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='matNumber" + mmfId + "'>" + mxList[i].matNumber + "</span></td>";
            str += "<td id='matNumber2" + mmfId + "' style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='number2" + mmfId + "'>" + mxList[i].number2 + "</span></td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type='text' value='" + _enterData[mmfId].matCode1 + "' onkeydown='selectMatBefore(this.value," + mxList[i].mmfId + ", " + mxList[i].wmsMiId + ")'  id='xzwl" + mmfId + "' style='width: 80px;height: 25px' onclick=''><button onclick='selectMatBeforeBtn(" + mxList[i].mmfId + ", " + mxList[i].wmsMiId + ")' class='selectbtn1'>选择物料</button>&nbsp;</td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type='text'   id='xzwlname" + mmfId + "' value='" + _enterData[mmfId].matName1 + "' style='width: 40px;height: 25px'></td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\" id='xzwlmmfname" + mmfId + "'>" +  _enterData[mmfId].mmfName + "</td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\" id='xzwlunit" + mmfId + "'>" +  _enterData[mmfId].basicUnit + "</td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\" id='xzwlnum" + mmfId + "'>" + _enterData[mmfId].quantity + "</td>";
            str += "<td style=\"text-align:center;width: 68px;font-size: 13px;line-height:30px;border: 1.5px solid #F0F0F0;\">" + "<input onfocus='changeNumberFocus(" + mmfId + ")' onblur='changeBaseNumber(" + mmfId + ", 2)' type='text' id='rksl" + mmfId + "' placeholder='' value='" + (_enterData[mmfId].baseNumber == undefined ? '' : _enterData[mmfId].baseNumber) + "' style='width: 50px;height: 25px'></td>";
            // str += "<td style=\"text-align:center;width: 68px;font-size: 13px;line-height:30px;border: 1.5px solid #F0F0F0;\">" +
            //     "<input onfocus='changeNumberFocus(" + mmfId + ")' onblur='changeBaseNumber(" + mmfId + ", 2)' type='text' id='rksl" + mmfId + "' placeholder='' value='" + (_enterData[mmfId].baseNumber == undefined ? '' : _enterData[mmfId].baseNumber) + "' style='width: 50px;height: 25px'>&nbsp;<span id='eb" + mmfId + "'>" + _enterData[mmfId].basicUnit + "</span>" +
            //     // 系数数量
            //     ",<div class='minibr'></div>每<span id='rbUnit" + mmfId + "'>" + _enterData[mmfId].basicUnit + "</span><input readonly  type='text' id='rrksl" + mmfId + "' placeholder='' value='" + (_enterData[mmfId].ratio1 == undefined ? '' : _enterData[mmfId].ratio1) + "' style='width: 50px;height: 25px'>&nbsp;<span id='rsUnit" + mmfId + "'>" + (_enterData[mmfId].unit == undefined ? _enterData[mmfId].basicUnit : _enterData[mmfId].unit) + "</span>" +
            //     "</td>";
            //onfocus='changeNumberFocus(" + mmfId + ")' onblur='changeRatioNumber(" + mmfId + ", " + ratio + ", 2)' //保留不要删，后期要修改
            // str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type='text' onblur='changeSplitNumber(" + mmfId + ", 2)' id='cfdw1" + mmfId + "' placeholder='" + _enterData[mmfId].splitNumber + "' value='" + _enterData[mmfId].splitNumber + "' style='width: 50px;height: 25px;'>&nbsp;" + (mxList[i].BasicUnit == undefined ? '' : mxList[i].BasicUnit) + "</td>";
            // str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type='text' onfocus='changeNumberFocus(" + mmfId + ")' onblur='changeSplitNumber(" + mmfId + ", 2)' id='cfdw1" + mmfId + "' placeholder='' value='" + (_enterData[mmfId].splitNumber == undefined ? '' : _enterData[mmfId].splitNumber) + "' style='width: 50px;height: 25px;'>&nbsp;<span id='cfUnit" + mmfId + "'>" + (_enterData[mmfId].unit == undefined ? _enterData[mmfId].basicUnit : _enterData[mmfId].unit) + "</span></td>";
            // str += "<td id='unitMoney" + mmfId + "' style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].unitMoney + "</td>";
            //采购价（元）
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeDdprice(" + mmfId + ", " + mxList[i].unitMoney + ")' type='text' id='ddprice" + mmfId + "' value='" + _enterData[mmfId].unitMoney + "' style='width: 40px;height: 25px'>&nbsp;</td>";
            //采购金额（元）
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeDdpriceAll(" + mmfId + ", " + mxList[i].pricesAll + ")' type='text' id='ddpriceall" + mmfId + "' value='" + (_enterData[mmfId].unitMoney * (_enterData[mmfId].baseNumber == undefined ? '' : _enterData[mmfId].baseNumber)) + "' style='width: 40px;height: 25px'>&nbsp;</td>";
            // str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeRetailPrice(" + mmfId + ", 2)' type='text' id='lsj" + mmfId + "' value='" + _enterData[mmfId].retailPrice + "' style='width: 40px;height: 25px'>&nbsp;</td>";


            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeBatchCode(" + mmfId + ", 2)' type='text' id='ph" + mmfId + "' value='" + _enterData[mmfId].batchNode + "' style='width: 40px;height: 25px'>&nbsp;</td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='' type='text' id='yxq" + mmfId + "' value='" + _enterData[mmfId].valiedDate + "' style='width: 80px;height: 25px'>&nbsp;</td>";
            str += "<td id='backPrinting" + mmfId + "' style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeBP(" + mmfId + ", 2)' id='bp" + mmfId + "' value='" + (_enterData[mmfId].backPrinting == undefined ? '' : _enterData[mmfId].backPrinting) + "' style='width: 80px;height: 25px'/></td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeRemark(" + mmfId + ")' type='text' id='remark" + mmfId + "' value='" + _enterData[mmfId].remark + "' style='width: 40px;height: 25px'>&nbsp;</td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\">" +
                "<input type='hidden' id='mdp" + mmfId + "' value='" + _enterData[mmfId].mdpId + "'/><input type='hidden' id='mdps" + mmfId + "' value='" + _enterData[mmfId].mdpsId + "'/>" +
                "<a onclick='gaibian(" + mxList[i].momId + ", " + mmfId + ")'><img style=\"\" width=\"15px\" height=\"15px\" src=\"../../../../dentistmall/css/shopping/images/delete.png\"></a>&nbsp;</td>";
            str += "</tr>";
        }
    }
    $("#mxListMx").html(str);
    setTimeout(function () {
        for (let idx in _enterData) {
            laydate({
                elem: "#yxq" + idx, //有效期
                format: 'YYYY-MM-DD',
                choose: function(dates){ //选择好日期的回调
                    changeValiedDate(idx, 2, dates);
                }
            })
        }
    }, 200)
}

function changeRemark(mmfId) {
    _enterData[mmfId].remark = $('#remark' + mmfId).val();
}
//修改采购价（元）
function changeDdprice(mmfId, money) {
    let rksl = $('#rksl' + mmfId).val();
    let ddprice = $('#ddprice' + mmfId).val();
    if (CheckUtil.isDigit(ddprice) == false) {
        ddprice = 0;
        $('#ddprice' + mmfId).val(ddprice);
    }
    if (CheckUtil.isInteger(rksl) == false) {
        rksl = 0;
        $('#rksl' + mmfId).val(rksl);
    }
    const ddAllPrice = rksl * ddprice;
    $('#ddpriceall' + mmfId).val(ddAllPrice);
    // let value  = $('#ddprice' + mmfId).val();
    _enterData[mmfId].unitMoney = ddprice;
}
function changeDdpriceAll(mmfId, money) {
    let rksl = $('#rksl' + mmfId).val();
    let ddpriceall = $('#ddpriceall' + mmfId).val();
    if (CheckUtil.isDigit(ddpriceall) == false) {
        ddpriceall = 0;
        $('#ddpriceall' + mmfId).val(ddpriceall);
    }
    if (CheckUtil.isInteger(rksl) == false) {
        rksl = 1;
        $('#rksl' + mmfId).val(0);
    }
    const ddprice = ddpriceall / rksl;
    $('#ddprice' + mmfId).val(ddprice);
    // let value  = $('#ddprice' + mmfId).val();
    _enterData[mmfId].unitMoney = ddprice;
    // let value  = $('#ddpriceall' + mmfId).val();
    // _enterData2[mmfId].unitMoney = value;
}


function initMatListMx(jsondata) {
    closeLoading();
    var mxList = jsondata;
    var momId1;
    var str = "";
    var productNameInt;
    let mmfId1;
    let ratio;
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            mmfId1 = mxList[i].mmfId;
            ratio = mxList[i].basicCoefficent == undefined ? 1 : mxList[i].basicCoefficent;
            if (_enterData[mmfId1] == undefined) {
                _enterData[mmfId1] = {};
                _enterData[mmfId1].productName = mxList[i].productName == undefined ? '' : mxList[i].productName;
                _enterData[mmfId1].unitMoney = mxList[i].price == undefined ? 0 : mxList[i].price;
                _enterData[mmfId1].baseNumber = 0;//mxList[i].number2 == undefined ? '' : mxList[i].number2;
                _enterData[mmfId1].ratio = mxList[i].basicCoefficent == undefined ? 1 : mxList[i].basicCoefficent;
                // _enterData[mmfId1].splitNumber = mxList[i].splitQuantity == undefined ? '' : mxList[i].splitQuantity;
                _enterData[mmfId1].splitNumber = 0; // _enterData[mmfId1].baseNumber == '' ? '' : (_enterData[mmfId1].baseNumber * _enterData[mmfId1].ratio);
                _enterData[mmfId1].unit = mxList[i].unit == undefined ? '' : mxList[i].unit;
                _enterData[mmfId1].retailPrice = mxList[i].retailPrice == undefined ? '' : mxList[i].retailPrice;
                _enterData[mmfId1].batchNode = '';// mxList[i].batchCode == undefined ? '' : mxList[i].batchCode;
                _enterData[mmfId1].valiedDate = '';// mxList[i].valiedDate == undefined ? '' : mxList[i].valiedDate;
                _enterData[mmfId1].backPrinting = '';// mxList[i].backPrinting == undefined ? '' : mxList[i].backPrinting;
                _enterData[mmfId1].mdpId = mxList[i].mdp == undefined ? '' : mxList[i].mdpId;
                _enterData[mmfId1].mdpsId = mxList[i].mdp == undefined ? '' : mxList[i].mdpsId;
                _enterData[mmfId1].ratio1 = mxList[i].ratio1 == undefined ? ratio : mxList[i].ratio1;

                _enterData[mmfId1].remark = '';
                _enterData[mmfId1].quantity1 = mxList[i].quantity1 == undefined ? 0 : mxList[i].quantity1;
            }

            let basicUnit = mxList[i].basicUnit == undefined ? '' : mxList[i].basicUnit;
            str += "<tr id='matTr" + mmfId1 + "'>";
            //物料编号
            // str += "<td id='matCode" + mmfId1 + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].mmfCode == undefined ? '' : mxList[i].mmfCode) + "<span style='display: none' id='mmfId" + mmfId1 + "'>" + mxList[i].mmfId + "</span></td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type='text' value='" + (mxList[i].matCode == undefined ? '' : mxList[i].matCode) + "'onblur='hideClass()' id='selectMat' style='width: 80px;height: 25px' onclick=''><button onclick='selectMatBefores(1)' class='selectbtn1' id='selectbtn1'>查询</button></td>";
            //物料名称
            // str += "<td id='matName" + mmfId1 + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].matName == undefined ? '' : mxList[i].matName) + "</td>";
            str += "<td style=\"text-align:center;line-height:30px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type='text'   id='matName" + mmfId1 + "' value='" + (mxList[i].matName == undefined ? '' : mxList[i].matName) + "' style='width: 40px;height: 25px'></td>";
            // str += "<td id='brand" + mmfId1 + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].brand ==  undefined ? '' : mxList[i].brand) + "</td>";
            // str += "<td id='mmfNameCode" + mmfId1 + "' style=\"text-align:left;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='mmfName" + mmfId1 + "'>" + (mxList[i].mmfName == undefined ? '' : mxList[i].mmfName) + "</span><br/><span id='mmtCode" + mmfId1 + "'>" + (mxList[i].mmfCode == undefined ? '' : mxList[i].mmfCode) + "</span></td>";
            //规格型号
            str += "<td id='mmfNameCode" + mmfId1 + "' style=\"text-align:left;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='mmfName" + mmfId1 + "'>" + (mxList[i].mmfName == undefined ? '' : mxList[i].mmfName) + "</span></td>";
            //单位
            str += "<td id='basicUnit" + mmfId1 + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].basicUnit ==  undefined ? '' : mxList[i].basicUnit) + "</td>";
            // str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" +
            //     "<input onblur='changeBaseNumber(" + mmfId1 + ", 1)' type='text' id='rksl1" + mmfId1 + "' placeholder='" + "" + "' value='" + _enterData[mmfId1].baseNumber + "' style='width: 50px;height: 25px'>&nbsp;" + (mxList[i].basicUnit == undefined ? '' : mxList[i].basicUnit) +
            //     // 系数数量
            //     ",每" + basicUnit +"<input readonly type='text' id='rrksl1" + mmfId1 + "' placeholder='' value='" + ratio + "' style='width: 50px;height: 25px'>&nbsp;" + (mxList[i].splitUnit == undefined ? basicUnit : mxList[i].splitUnit) +
            //     "</td>"; //  onblur='changeRatioNumber(" + mmfId1 + ", " + ratio + ", 1)' // 不要删
            //当前库存
            str += "<td id='quantity" + mmfId1 + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (_enterData[mmfId1].quantity1 ==  undefined ? 0 : _enterData[mmfId1].quantity1) + "</td>";
            //入库数量
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeBaseNumber(" + mmfId1 + ", 1)' type='text' id='rksl1" + mmfId1 + "' placeholder='" + "" + "' value='" + (_enterData[mmfId1].baseNumber ==  undefined ? 0 : _enterData[mmfId1].baseNumber) + "' style='width: 50px;height: 25px'/></td>";
           //采购价
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeRetailPrice(" + mmfId1 + ", 1)' type='text' id='lsj1" + mmfId1 + "' value='" + _enterData[mmfId1].retailPrice + "' style='width: 40px;height: 25px'>&nbsp;</td>";
            //采购金额（元）
            let rksl = $('#rksl1').val();
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeRetailAllPrice(" + mmfId1 + ", 1)' type='text' id='alllsj1" + mmfId1 + "' value='" + (_enterData[mmfId1].retailPrice * (_enterData[mmfId1].baseNumber ==  undefined ? 0 : _enterData[mmfId1].baseNumber)) + "' style='width: 40px;height: 25px'>&nbsp;</td>";
            // str += "<td id='matNumber2" + mmfId1 + "' style=\"line-height:3;text-align:center;width: 68px;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='xs" + mmfId1 + "'>" + _enterData[mmfId1].ratio + "</span></td>";
            // str += "<td style=\"line-height:3;text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeSplitNumber(" + mmfId1 + ", 1)' type='text' id='cfdw11" + mmfId1 + "' value='' style='width: 50px;height: 25px;'>"+ (mxList[i].splitUnit == undefined ? basicUnit : mxList[i].splitUnit) + "</td>";
            // str += "<td id='productName" + mmfId1 + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].productName == undefined ? '' : mxList[i].productName) + "</td>";
            // str += "<td id='unitMoney" + mmfId1 + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].price == undefined ? '' : mxList[i].price) + "</td>";
            // str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeRetailPrice(" + mmfId1 + ", 1)' type='text' id='lsj1" + mmfId1 + "' value='" + _enterData[mmfId1].retailPrice + "' style='width: 40px;height: 25px'>&nbsp;</td>";
            //批号
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeBatchCode(" + mmfId1 + ", 1)' type='text' id='ph1" + mmfId1 + "' value='" + _enterData[mmfId1].batchNode + "' style='width: 40px;height: 25px'>&nbsp;</td>";//<input type='text' id='ph"+mmfId1+"' style='width: 40px;height: 25px'>&nbsp;</td>";
            //有效期
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='' type='text' id='yxq1" + mmfId1 + "' value='" + _enterData[mmfId1].valiedDate + "' style='width: 80px;height: 25px'>&nbsp;</td>";
            //注册证号/备案号
            str += "<td id='backPrinting" + mmfId1 + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeBP(" + mmfId1 + ", 1)' id='bp1" + mmfId1 + "' value='" + (_enterData[mmfId1].backPrinting == undefined ? '' : _enterData[mmfId1].backPrinting) + "' style='width: 80px;height: 25px'/></td>";
            //备注
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeRemark(" + mmfId1 + ")' type='text' id='remark" + mmfId1 + "' value='" + _enterData[mmfId1].remark + "' style='width: 40px;height: 25px'>&nbsp;</td>";
            //操作
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" +
                "<input type='hidden' id='mdp" + mmfId1 + "'/><input type='hidden' id='mdps" + mmfId1 + "'/>" +
                "<a onclick='gaibian1(" + mxList[i].wmsMiId + ", " + mmfId1 + ")'><img style=\"\" width=\"15px\" height=\"15px\" src=\"../../../../dentistmall/css/shopping/images/delete.png\"></a>&nbsp;</td>";
            str += "</tr>";
        }
    }
    $("#mxMatListMx").html(str);
    setTimeout(function () {
        for (let idx in _enterData) {
            laydate({
                elem: "#yxq1" + idx, //有效期
                format: 'YYYY-MM-DD',
                choose: function(dates){ //选择好日期的回调
                    changeValiedDate(idx, 1, dates);
                }
            })
        }
    }, 200)
}

function changeNumberFocus(mmfId) {
    if (_enterData[mmfId].wmsMiId == undefined) {
        $.supper("alert", {title: '操作提示', msg: '请选择物料'});
    }
}

// 计算现在的数量
function changeRatioNumber(mmfId, ratio, idx) {
    let value = '';
    let quantity = 0;
    let ratioNumber = 0;
    let baseNumber = 0;
    if (idx == 1) { //物料入库
        quantity = $('#rksl1' + mmfId).val();
        ratioNumber = $('#rrksl1' + mmfId).val();
    } else if (idx == 2) { //订单入库
        quantity = $('#rksl' + mmfId).val();
        ratioNumber = $('#rrksl' + mmfId).val();
    }
    quantity = quantity == '' ? 0 : quantity;
    ratioNumber = ratioNumber == '' ? 0 : ratioNumber;

    baseNumber = quantity * ratioNumber;
    if (idx == 1) { //物料入库
        $('#cfdw11' + mmfId).val(baseNumber);
    } else if (idx == 2) { //订单入库
        $('#cfdw1' + mmfId).val(baseNumber);
    }
    _enterData[mmfId].splitNumber = baseNumber;
    _enterData[mmfId].ratio1 = ratioNumber;
}

function changeBaseNumber(mmfId, idx) {
    let value = '';
    let ratioNumber = 0;
    let baseNumber = 0;
    if (idx == 1) { //物料入库
        value = $('#rksl1' + mmfId).val();
        ratioNumber = $('#rrksl1' + mmfId).val();
        changeRetailPrice(mmfId, 1);
    } else if (idx == 2) { //订单入库
        value = $('#rksl' + mmfId).val();
        ratioNumber = $('#rrksl' + mmfId).val();
        changeDdprice(mmfId, 0);
    }

    if (CheckUtil.isDigit(value) == false){
        value = '';
        $('#rksl' + mmfId).val('');
        $('#rksl1' + mmfId).val('');
    }
    ratioNumber = 1;//ratioNumber == '' ? 0 : ratioNumber;
    baseNumber = (value == '' ? 0 : value) * ratioNumber;
    if (idx == 1) { //物料入库
        $('#cfdw11' + mmfId).val(baseNumber);
    } else if (idx == 2) { //订单入库
        $('#cfdw1' + mmfId).val(baseNumber);
    }
    _enterData[mmfId].splitNumber = value; //baseNumber;
    _enterData[mmfId].baseNumber = value;
    // let splitnumber = '';
    // if (CheckUtil.isDigit(value)) {
    //     splitnumber = _enterData[mmfId].baseNumber * (_enterData[mmfId].ratio == undefined ? 1 : _enterData[mmfId].ratio);
    // }
    // if (idx == 1) { //物料入库
    //     value = $('#cfdw11' + mmfId).val(splitnumber);
    // } else if (idx == 2) { //订单入库
    //     value = $('#cfdw1' + mmfId).val(splitnumber);
    // }
}
function changeSplitNumber(mmfId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        value = $('#cfdw11' + mmfId).val();
    } else if (idx == 2) { //订单入库
        value = $('#cfdw1' + mmfId).val();
    }
    _enterData[mmfId].splitNumber = value;
}
function changeUnit(mmfId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        value = $('#cfdw21' + mmfId).val();
    } else if (idx == 2) { //订单入库
        value = $('#cfdw2' + mmfId).val();
    }
    _enterData[mmfId].unit = value;
}
function changeRetailPrice(mmfId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        let rksl = $('#rksl1' + mmfId).val();
        if (CheckUtil.isInteger(rksl) == false) {
            rksl = 0;
            $('#rksl1' + mmfId).val(rksl);
        }
        value = $('#lsj1' + mmfId).val();
        if (CheckUtil.isDigit(value) == false) {
            value = 0;
            $('#lsj1' + mmfId).val(value);
        }
        let allvalue = value * rksl;
        $('#alllsj1' + mmfId).val(allvalue);
    } else if (idx == 2) { //订单入库
        value = $('#lsj' + mmfId).val();
    }
    _enterData[mmfId].retailPrice = value;
}

function changeRetailAllPrice(mmfId) {
    let value = '';
    let rksl = $('#rksl1' + mmfId).val();
    if (CheckUtil.isInteger(rksl) == false) {
        rksl = 1;
        $('#rksl1' + mmfId).val(0);
    }
    allvalue = $('#alllsj1' + mmfId).val();
    if (CheckUtil.isDigit(allvalue) == false) {
        allvalue = 0;
        $('#alllsj1' + mmfId).val(allvalue);
    }
    value = allvalue / rksl;
    $('#lsj1' + mmfId).val(value);
    _enterData[mmfId].retailPrice = value;
}
function changeBatchCode(mmfId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        value = $('#ph1' + mmfId).val();
    } else if (idx == 2) { //订单入库
        value = $('#ph' + mmfId).val();
    }
    _enterData[mmfId].batchNode = value;
}
function changeValiedDate(mmfId, idx, date) {
    // let value = '';
    // if (idx == 1) { //物料入库
    //     value = $('#yxq1' + mmfId).val();
    // } else if (idx == 2) { //订单入库
    //     value = $('#yxq' + mmfId).val();
    // }
    _enterData[mmfId].valiedDate = date;
}

function changeBP(mmfId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        value = $('#bp1' + mmfId).val();
    } else if (idx == 2) { //订单入库
        value = $('#bp' + mmfId).val();
    }
    _enterData[mmfId].backPrinting = value;
}
//2020年07月10日08:43:02朱燕冰修改
//input获取焦点事件
function selectMatBeforesToo(value, mmfId, wmsMiId){
    let para = $("#selectMat").val()
    
    $("#selectMat").bind("input propertychange",function(event){
        if (para.length <= 0){
            $("#tables").hide();
        }else {
            $("#tables").show();
            search();
        }
    });
}
function hideClass(){
    setTimeout(function () {
        if (canHideTable == true || $(this).val() == '') {
            canHideTable = false;
            $('tables').hide();
        }
    }, 200);
}
var timeOut;
function selectMatBefores(value, mmfId, wmsMiId) {
    if (value == '') {
        $("#tables2").hide();
    }else {
        // canHideTable = false;
        $("#tables2").show();
    }
    search();
}
function selectMatBeforestodo(value, mmfId, wmsMiId) {
    if (value == '') {
        $("#tables").hide();
    }else {
        // canHideTable = false;
        $("#tables").show();
    }
    search();
}
function selectMatBefore(value, mmfId, wmsMiId) {
    // $("#selectMat").bind("input propertychange",function(event){
    //
    //     search();
    // });
    // if (value == '') {
    //     $("#tables").hide();
    //     $("#selectMat").bind("input propertychange",function(event){
    //         search();
    //     });
    // }else {
    //     $("#tables").show();
    // }
    if (event.keyCode == 13) {
        selectMat(value, mmfId, wmsMiId);
        // search();
    }
}
function selectMatBeforeBtn(mmfId, wmsMiId) {
    let value = '';
    if (mmfId != undefined) {
        value = $('#xzwl' + mmfId).val();
    } else
        value = $('#selectMat').val();
    selectMat(value, mmfId, wmsMiId);
}

function selectMat(value, mmfId, wmsMiId) {
    if (wmsMiId == undefined) {
        $.supper("setProductArray", {"name": "searchName", "value": {searchName: value, type: 1}});
    } else {
        $.supper("setProductArray", {
            "name": "searchName",
            "value": {searchName: value, wmsMiId: wmsMiId, mmfId: mmfId}
        });
    }
    var att_url = $.supper("getServicePath", {"data": '', url: "/jsp/dentistmall/modifyprice/selMateriel"});
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: "物料信息",
        icon: "fa-bars",
        width: "1400",
        height: '800',
        BackE: function () {
            addMat(mmfId, wmsMiId);
        }
    });
}
//修改后的方法
function addMated(wmsMiId, mmfId) {
    if (wmsMiId != undefined) {
        if (_wmsMiIds.indexOf(wmsMiId) < 0) {
            _wmsMiIds.push(wmsMiId);
        }
        if (_mmfIds.indexOf(mmfId) < 0) {
            _mmfIds.push(mmfId);
        }
        searchMatMx(wmsMiId,mmfId);
    }
}
//未修改的方法
function addMat(mmfId, wmsMiId) {
    let selPartAndNorm = $.supper("getProductArray", "selPartAndNorm");
    let data;
    if (selPartAndNorm != undefined && selPartAndNorm != null) {
        if (selPartAndNorm.data == '') {
            $('#xzwl' + mmfId).val(_enterData[mmfId].matName);

        } else {
            data = selPartAndNorm.data;
            console.log('data-------------->',data)
            if (mmfId != undefined && wmsMiId != undefined) {
                if (data == undefined) {
                    $('#xzwl' + mmfId).val(_enterData[mmfId].matCode1);
                    $('#xzwlname' + mmfId).val(_enterData[mmfId].matName1);
                    $('#xzwlmmfname' + mmfId).html(_enterData[mmfId].mmfName);
                    $('#xzwlunit' + mmfId).html(_enterData[mmfId].basicUnit);
                    $('#xzwlnum' + mmfId).html(_enterData[mmfId].quantity);
                }else {
                    // let dataList = data.split(',');
                    autoInput1(data, mmfId);
                }
            } else {
                if (data != undefined) {
                    if (_wmsMiIds.indexOf(data.wmsMiId) < 0) {
                        _wmsMiIds.push(data.wmsMiId);
                    }
                    if (_mmfIds.indexOf(data.mmfId) < 0) {
                        _mmfIds.push(data.mmfId);
                    }
                    searchMatMx(data.wmsMiId, data.mmfId);
                }
            }
        }
    }
    if (enterType == 2) {
        $('#selectMat').val('');
        $('#selectMat').focus();
    }
    $.supper("setProductArray", {"name": "selPartAndNorm", "value": null});
}

function autoInput1(data, mmfId) {
    $('#xzwl' + mmfId).val(data.matCode == undefined ? '' : data.matCode);
    $('#xzwlname' + mmfId).val(data.matName == undefined ? '' : data.matName);
    $('#xzwlmmfname' + mmfId).html(data.norm == undefined ? '' : data.norm);
    $('#xzwlunit' + mmfId).html(data.basicUnit == undefined ? '' : data.basicUnit);
    $('#xzwlnum' + mmfId).html(data.quantity == undefined ? 0 : data.quantity);
    if (data.mmfId != undefined && data.mmfId != '')
        _enterData[mmfId].mmfId = data.mmfId;
    if (data.wmsMiId != undefined && data.wmsMiId != '')
        _enterData[mmfId].wmsMiId = data.wmsMiId;
    _enterData[mmfId].basicUnit = data.basicUnit == undefined ? '' : data.basicUnit;
    _enterData[mmfId].unit = data.splitUnit == undefined ? _enterData[mmfId].basicUnit : data.splitUnit;
    _enterData[mmfId].retailPrice = data.retailPrice == undefined ? '' : data.retailPrice;
    _enterData[mmfId].batchNode = data.batchNode == undefined ? '' : data.batchNode;
    _enterData[mmfId].valiedDate = data.valiedDate == undefined ? '' : data.valiedDate;
    _enterData[mmfId].backPrinting = data.backPrinting == undefined ? '' : data.backPrinting;
    _enterData[mmfId].mdpId = data.mdpId == undefined ? '' : data.mdpId;
    _enterData[mmfId].mdpsId = data.mdpsId == undefined ? '' : data.mdpsId;
    _enterData[mmfId].mmfCode = data.mmfCode == undefined ? '' : data.mmfCode;
    _enterData[mmfId].ratio1 = data.basicCoefficent == undefined ? 1 : data.basicCoefficent;
    _enterData[mmfId].matName1 = data.matName == undefined ? '' : data.matName;
    _enterData[mmfId].matCode1 = data.matCode == undefined ? '' : data.matCode;
    _enterData[mmfId].mmfName = data.norm == undefined ? '' : data.norm;
    _enterData[mmfId].quantity = data.quantity == undefined ? 0 : data.quantity;
}

function autoInput(data, mmfId) {
    $('#ph' + mmfId).val(data.backPrinting);
    $('#lsj' + mmfId).val(data.retailPrice);
    // $('#cfdw1' + mmfId).val(data.splitUnit);
    $('#mdp' + mmfId).val(data.mdpId);
    $('#mdps' + mmfId).val(data.mdpsId);
    $('#xzwl' + mmfId).val(data.matName);

    if (data.mmfId != undefined && data.mmfId != '')
        _enterData[mmfId].mmfId = data.mmfId;
    if (data.wmsMiId != undefined && data.wmsMiId != '')
        _enterData[mmfId].wmsMiId = data.wmsMiId;
    _enterData[mmfId].basicUnit = data.basicUnit == undefined ? '' : data.basicUnit;
    _enterData[mmfId].unit = data.splitUnit == undefined ? _enterData[mmfId].basicUnit : data.splitUnit;
    _enterData[mmfId].retailPrice = data.retailPrice == undefined ? '' : data.retailPrice;
    _enterData[mmfId].batchNode = data.batchNode == undefined ? '' : data.batchNode;
    _enterData[mmfId].valiedDate = data.valiedDate == undefined ? '' : data.valiedDate;
    _enterData[mmfId].backPrinting = data.backPrinting == undefined ? '' : data.backPrinting;
    _enterData[mmfId].mdpId = data.mdpId == undefined ? '' : data.mdpId;
    _enterData[mmfId].mdpsId = data.mdpsId == undefined ? '' : data.mdpsId;
    _enterData[mmfId].mmfCode = data.mmfCode == undefined ? '' : data.mmfCode;
    _enterData[mmfId].ratio1 = data.basicCoefficent == undefined ? 1 : data.basicCoefficent;
    _enterData[mmfId].matName1 = data.matName == undefined ? '' : data.matName;
    $('#rrksl' + mmfId).val(_enterData[mmfId].ratio);
    $('#eb' + mmfId).text(_enterData[mmfId].basicUnit);
    $('#basicUnit' + mmfId).text(_enterData[mmfId].basicUnit);
    $('#rbUnit' + mmfId).text(_enterData[mmfId].basicUnit);
    $('#rsUnit' + mmfId).text(_enterData[mmfId].unit);
    $('#cfUnit' + mmfId).text(_enterData[mmfId].unit);
    if (CheckUtil.isDigit(_enterData[mmfId].baseNumber)) {
        let splitnumber = _enterData[mmfId].baseNumber * _enterData[mmfId].ratio;
        $('#cfdw1' + mmfId).val(splitnumber);
    }
}

function oneAdd() {

}

function newAdd() {

}

function save() {
    console.log("111")
    if (enterType == 1) {
        if (moiId1 != undefined && moiId1 != null && moiId1 != '' && $("#mxListMx tr").length > 0) {
                saveMx();
        }else {
            $.supper("alert", {title : "操作提示",msg : "还未选择数据！"});
        }
    } else if (enterType == 2) {
        if ($("#mxMatListMx tr").length > 0)
            saveMatEn();
        else {
            $.supper("alert", {title : "操作提示",msg : "还未选择数据！"});
        }
    }
}

function saveMatEn() {
    let data = $('#saveForm').serialize(); //'wewId=' + wewId2;
    var shus = "";
    var mmfIds = "";
    var prices = "";
    var productPTimes = "";
    var productValiTimes = "";
    var packasgs = "";
    var factories = "";
    var certnos = "";
    var splitNumbers = '';
    var units = '';
    var retailPrices = '';
    var batchNodes = '';
    var valiedDates = '';
    var backPrintings = '';
    var ratio1s = '';
    var remarks = '';
    // if(rows != null && rows.length > 0 && rows[0] != null){
    //     var flag=false;
    //     for(var i=0;i < rows.length;i ++){
    //         var mmfId=rows[i].mmfId;
    //         var tt = rows[i].matNumber-rows[i].enterNumber - rows[i].backNumber;
    //         var shu = $("#"+mmfId+"Inp").val();
    //         var price=$("#"+mmfId+"price").val();
    //         if(shu != null && shu != "" && shu >tt){
    //             $.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，入库数量不能大于订单数量！"});
    //             return;
    //         }if(price == null ||price==""){
    //             $.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，价格不能为空！"});
    //             return;
    //         }else if(shu != null && shu != "" && shu >0){
    //             flag=true;
    //             shus += shu+",";
    //             mmfIds += mmfId+",";
    //             prices += price+",";
    //         } else if (shu == null || shu == "" || shu <= 0){
    //             flag = false;
    //         }
    //
    //         // 20191118 yangfeng 增加五个新字段
    //         var pack = $("#"+mmfId+"Pack").val();
    //         if(pack.length > 10){
    //             $.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，包装方式字数不能大于10个！"});
    //             return;
    //         }
    //         packasgs += pack + ",";
    //
    //         var ptime = $("#"+mmfId+"PTime").val();
    //         var pvtime = $("#"+mmfId+"PVTime").val()
    //         if((ptime !="" && pvtime != "")){
    //             var start_time = new Date(ptime.replace("-", "/").replace("-", "/"));
    //             var end_time = new Date(pvtime.replace("-", "/").replace("-", "/"));
    //             if(start_time > end_time) {
    //                 $.supper("alert", {title: "操作提示", msg: "第" + (i + 1) + "行，生产日期不能超过有效期！"});
    //                 return;
    //             }
    //         }
    //         productPTimes += ptime + ",";
    //         productValiTimes += pvtime + ",";
    //
    //         var fac = $("#"+mmfId+"Factory").val();
    //         if(fac.length > 4){
    //             $.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，生产厂家字数不能大于4个！"});
    //             return;
    //         }
    //         factories += fac + ",";
    //
    //         var certno = $("#"+mmfId+"CertNo").val();
    //         if(certno.length > 16){
    //             $.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，批次证号字数不能大于16个！"});
    //             return;
    //         }
    //         certnos += certno + ",";
    //     }
    //     if(!flag){
    //         $.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，请输入库数量！"});
    //         return;
    //     }
    //     shus=shus.substring(0,shus.length-1);
    //     mmfIds=mmfIds.substring(0,mmfIds.length-1);
    //     prices = prices.substring(0,prices.length-1);
    //     packasgs=packasgs.substring(0,packasgs.length-1);
    //     productPTimes=productPTimes.substring(0,productPTimes.length-1);
    //     productValiTimes = productValiTimes.substring(0,productValiTimes.length-1);
    //     factories=factories.substring(0,factories.length-1);
    //     certnos=certnos.substring(0,certnos.length-1);
    // }else{
    //     $.supper("alert", {title : "操作提示",msg : "第" + (i + 1) + "行，没有入库明细，不允许入库！"});
    //     return;
    // }

    for (let mmfId in _enterData) {
        console.log("表格的数据"+_enterData)
        if (_enterData[mmfId] == undefined)
            continue;
        var shu = _enterData[mmfId].baseNumber;
        var splitNumber = _enterData[mmfId].splitNumber;
        if ((shu == null || shu == '' || shu <= 0) && (splitNumber == undefined || splitNumber == '' || splitNumber <= 0)){
            $.supper("alert", {title : "操作提示",msg : "入库数量不能为空！"});
            return;
        }
        if ((shu != null && shu != "" && shu > 0) || (splitNumber != undefined && splitNumber != "" && splitNumber > 0)) {
            shus += (shu == '' ? 0 : shu) + ",";
            mmfIds += mmfId + ",";
            prices += _enterData[mmfId].unitMoney + ",";
            packasgs += _enterData[mmfId].productName + ',';
            splitNumbers += (splitNumber == '' ? 0 : splitNumber) + ",";
            units += _enterData[mmfId].unit + ",";
            retailPrices += _enterData[mmfId].retailPrice + ",";
            batchNodes += _enterData[mmfId].batchNode + ",";
            valiedDates += _enterData[mmfId].valiedDate + ",";
            backPrintings += _enterData[mmfId].backPrinting + ",";
            ratio1s += _enterData[mmfId].ratio1 + ',';
            remarks += _enterData[mmfId].remark + '##';
        }
    }
    if (shus == '' && splitNumbers == ''){
        $.supper("alert", {title : "操作提示",msg : "入库数量不能为空！"});
        return;
    }

    if (shus != '')
        shus = shus.substring(0, shus.length - 1);
    if (mmfIds != '')
        mmfIds = mmfIds.substring(0, mmfIds.length - 1);
    if (prices != '')
        prices = prices.substring(0, prices.length - 1);
    if (packasgs != '')
        packasgs = packasgs.substring(0, packasgs.length - 1);
    if (splitNumbers != '')
        splitNumbers = splitNumbers.substring(0, splitNumbers.length - 1);
    if (units != '')
        units = units.substring(0, units.length - 1);
    if (retailPrices != '')
        retailPrices = retailPrices.substring(0, retailPrices.length - 1);
    if (batchNodes != '')
        batchNodes = batchNodes.substring(0, batchNodes.length - 1);
    if (valiedDates != '')
        valiedDates = valiedDates.substring(0, valiedDates.length - 1);
    if (backPrintings != '')
        backPrintings = backPrintings.substring(0, backPrintings.length - 1);
    if (ratio1s != '')
        ratio1s = ratio1s.substring(0, ratio1s.length - 1);
    if (remarks != '')
        remarks = remarks.substring(0, remarks.length - 2);
    data += "&shus=" + shus + "&mmfIds=" + mmfIds + "&billType=1" + "&prices=" + prices + "&packasgs=" + packasgs +
        "&productPTimes=" + productPTimes + "&productValiTimes=" + valiedDates + "&factories=" + factories + "&certnos=" + batchNodes;
    data += '&splitNumbers=' + splitNumbers + '&units=' + units + '&retailPrices=' + retailPrices + '&backPrintings=' + backPrintings;
    data += '&ratio1s=' + ratio1s + '&remarks=' + remarks;
    $.supper("doservice", {
        "service": _all_saveAction,
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作成功！",
                    BackE: closeWin
                });
            } else if (jsondata.code == "-2") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "入库单号已存在，请刷新页面重新入库！"
                });
            } else
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作失败！"
                });
        }
    });
}

var _all_saveAction = "MdEnterWarehouseService.saveMdEnterWarehouse";

function saveMx() {
    let data = 'moiIds=' + moiId1;// 'wewId=' + wewId2;
    data += '&' + $('#saveForm').serialize();
    var momIds = "";
    var shus = "";
    var mmfIds = "";
    var number1s = "";
    var prices = "";
    var masIds = '';
    var splitNumbers = '';
    var units = '';
    var retailPrices = '';
    var batchNodes = '';
    var valiedDates = '';
    var backPrintings = '';
    var wmsMiIds = '';
    var ratio1s = '';
    var remarks = '';

    var flag = false;

    let mmfIdIndex = 0;
    let enterIndex = 0;
    for (let mmfId in _enterData) {
        enterIndex ++;
        if (_enterData[mmfId] == undefined || _enterData[mmfId].wmsMiId == undefined)
            mmfIdIndex ++;
        // let Printing = _enterData[mmfId].backPrinting; //注册证号
        // let batchNode = _enterData[mmfId].batchNode;// 批号
        // let indexID = _enterData[mmfId].matCode;//规格编号
        // // console.log(JSON.stringify(_enterData[mmfId].matCode))
        // for (let mmfId in _enterData){
        //     if (_enterData[mmfId] == undefined || _enterData[mmfId].wmsMiId == undefined)
        //         continue;
        //     let indexIDs = _enterData[mmfId].matCode;//规格编号
        //     let batchNodes = _enterData[mmfId].batchNode;// 批号
        //     let Printings = _enterData[mmfId].backPrinting; //注册证号
        //     // if (indexIDs != indexID){
        //     //     if ( Printing == Printings){
        //     //         $.supper("alert", {title: "操作提示", msg: "注册证号或批号不能重复！"});
        //     //         return;
        //     //     }
        //     //     if (batchNode == batchNodes){
        //     //         $.supper("alert", {title: "操作提示", msg: "批号不能重复！"});
        //     //     }
        //     // }
        // }
    }
    if(mmfIdIndex >= enterIndex){
        $.supper("alert", {title: "操作提示", msg: "请选择物料！"});
        return;
    }

    for (let mmfId in _enterData) {
        if (_enterData[mmfId] == undefined || _enterData[mmfId].wmsMiId == undefined)
            continue;
        var tt = _enterData[mmfId].number2 - _enterData[mmfId].enterNumber;
        var shu = _enterData[mmfId].baseNumber;

        if (shu != null && shu != "" && shu > tt) {
            $.supper("alert", {title: "操作提示", msg: "入库数量不能大于发货数量！"});
            return;
        } else if (shu != null && shu != "" && shu > 0) {
            flag = true;
            momIds += _enterData[mmfId].momId + ",";
            shus += shu + ",";
            if (_enterData[mmfId].mmfId != undefined && _enterData[mmfId].mmfId != '')
                mmfIds += _enterData[mmfId].mmfId + ',';
            else
                mmfIds += mmfId + ",";
            if (_enterData[mmfId].wmsMiId != undefined && _enterData[mmfId].wmsMiId != '')
                wmsMiIds += _enterData[mmfId].wmsMiId + ',';
            else
                wmsMiIds += ',';
            number1s += _enterData[mmfId].matNumber + ",";
            prices += _enterData[mmfId].unitMoney + ",";
            splitNumbers += _enterData[mmfId].splitNumber + ",";
            units += _enterData[mmfId].unit + ",";
            retailPrices += _enterData[mmfId].retailPrice + ",";
            batchNodes += _enterData[mmfId].batchNode + ",";
            valiedDates += _enterData[mmfId].valiedDate + ",";
            backPrintings += _enterData[mmfId].backPrinting + ",";
            ratio1s += _enterData[mmfId].ratio1 + ',';
            remarks += _enterData[mmfId].remark + '##';
        }
    }


    if (shu == ''){
        $.supper("alert", {title : "操作提示",msg : "请填写入库数量！"});
        return;
    }
    var message = '';
    if (momIds != '')
        momIds = momIds.substring(0, momIds.length - 1);
    if (shus != '')
        shus = shus.substring(0, shus.length - 1);
    if (mmfIds != '')
        mmfIds = mmfIds.substring(0, mmfIds.length - 1);
    if (number1s != '')
        number1s = number1s.substring(0, number1s.length - 1);
    if (prices != '')
        prices = prices.substring(0, prices.length - 1);
    if (splitNumbers != '')
        splitNumbers = splitNumbers.substring(0, splitNumbers.length - 1);
    if (units != '')
        units = units.substring(0, units.length - 1);
    if (retailPrices != '')
        retailPrices = retailPrices.substring(0, retailPrices.length - 1);
    if (batchNodes != '')
        batchNodes = batchNodes.substring(0, batchNodes.length - 1);
    if (valiedDates != '')
        valiedDates = valiedDates.substring(0, valiedDates.length - 1);
    if (backPrintings != '')
        backPrintings = backPrintings.substring(0, backPrintings.length - 1);
    if (wmsMiIds != '')
        wmsMiIds = wmsMiIds.substring(0, wmsMiIds.length - 1);
    if (ratio1s != '')
        ratio1s = ratio1s.substring(0, ratio1s.length - 1);
    if (remarks != '')
        remarks = remarks.substring(0, remarks.length - 2);
    data += '&wmsMiIds=' + wmsMiIds + "&shus=" + shus + "&momIds=" + momIds + "&mmfIds=" + mmfIds + "&billType=2&number1s=" + number1s + "&prices=" + prices;
    data += '&splitNumbers=' + splitNumbers + '&units=' + units + '&retailPrices=' + retailPrices + '&certnos=' + batchNodes + '&productValiTimes=' + valiedDates + '&backPrintings=' + backPrintings;
    data += '&ratio1s=' + ratio1s + '&remarks=' + remarks;
    $.supper("doservice", {
        "service": _all_saveAction,
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作成功！",
                     BackE: closeWin
                });
            } else if (jsondata.code == "4") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作成功！",
                    BackE: closeWin
                });
            } else if (jsondata.code == "5") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作成功！",
                    BackE: closeWin
                });
            } else if (jsondata.code == "6") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "存在未选择物料的数据！",
                    // BackE: closeWin
                });
            } else if (jsondata.code == "7") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "物料数据有误！",
                    // BackE: closeWin
                });
            } else if (jsondata.code == "-2") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "入库单号已存在，请刷新页面重新入库！"
                });
            } else
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作失败！"
                });
        }
    });


}

function closeWin() {
    backTo();
}
function gaibian(StateMomId, mmfId) {
    var vdata = 'StateMomId=' + StateMomId;
    //mycars.length=mycars.length-1;
    $.supper("doservice", {
        "service": "ModifypriceService.saveChangeState", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert",{ title:"操作提示", msg:"操作成功！"});
                searchAllMX(2, moiId1);
                _enterData[mmfId] = undefined;
                mycars = new Array();
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        }
    });
}

function gaibian1(wmsMiId, mmfId) {
    _enterData[mmfId] = undefined;
    if (_wmsMiIds.indexOf(wmsMiId) >= 0)
        _wmsMiIds.splice(_wmsMiIds.indexOf(wmsMiId), 1);
    if (_mmfIds.indexOf(mmfId) >= 0)
        _mmfIds.splice(_mmfIds.indexOf(mmfId), 1);
    $('#matTr' + mmfId).remove();
}
function gaibian2() {
    selectMatBefores(1)
}
var that = this;
function goBack() {
    if (JSON.stringify(_enterData) !== '{}') {
        $.supper("confirm", {
            title: "提示", msg: "有未保存记录，是否返回？", yesE: function () {
                setTimeout(function () {
                    that.ClaimantTop3()
                    that.backTo();
                }, 200)
            }
        });
    } else {
        // ClaimantTop3();
        backTo();
    }
}
function ClaimantTop3() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/modifyprice/addWarehousing.jsp"});
    $.supper("showTtemWin",{ "url":att_url,"title":"新增入库"});
}
function backTo() {
    if (isNew == undefined)
        url = '/dentistmall/jsp/dentistmall/modifyprice/addWarehousing.jsp';
    $.supper("showTtemWin", {"url": '/dentistmall/jsp/dentistmall/modifyprice/warehousingManagement.jsp', "title": '入库管理'});
    $.supper("setProductArray", {"name": "addEnter", "value": null });
    setTimeout(function () {
        if (url != undefined) {
            $.supper("closeTtemWin", {url: url});
        }
    }, 200);
}
function setBodyWidth(){
    var barWidthHelper=document.createElement('div');
    barWidthHelper.style.cssText="overflow:scroll; width:100px; height:100px;";
    document.body.appendChild(barWidthHelper);
    var barWidth=barWidthHelper.offsetWidth-barWidthHelper.clientWidth;
    document.body.removeChild(barWidthHelper);
    var bodyWidth=window.screen.availWidth-barWidth;
    return bodyWidth;
}

$(document).ready(
    function(){
        var bodyWidth=setBodyWidth()+"px";
        //document.body.style.width=bodyWidth;
        // $("body").css("width",bodyWidth);
    }
);
var treeClickLevel = 0;
var loadItemZtree = function () {
    let data = '';
    if (_mdpId != undefined) {
        data += 'mdpId=' + _mdpId;
    }
    if (_mdpsId != undefined) {
        data += '&mdpsId=' + _mdpsId;
    }
    //设置树形数据开始
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                tags: "tags",
                isParent: "isParent"
            }
        },
        async: {
            enable: true,
            url: $.supper("getServicePath", {
                "service": _all_SiteFirst,
                "data": data,
                autoParam: ["id"],
            }),  //获取异步数据的地址
            autoParam: ["id"],
            dataFilter: filter //设置数据的展现形式
        },
        callback: {//增加点击事件
            beforeClick: function (treeId, treeNode) {
                lastExpandNode = treeNode;//记录当前点击的节点
                treeClickLevel = treeNode.level;
                _mdpId = undefined;
                _mdpsId = undefined;

                if (treeNode.level == 1) {
                    
                    _mdpId = treeNode.id;
                    _mdpIdname = treeNode.name;
                    
                } else if(treeNode.level == 2){
                    _mdpsId = treeNode.id;
                    _mdpsIdname = treeNode.name;
                } else {
                    _mdpId = undefined;
                    _mdpsId = undefined;
                    _mdpIdname = '';
                    _mdpsIdname = '';
                }
                // if (treeNode.level == 0) {
                //     _mdpId = treeNode.id;
                // } else {
                //     _mdpsId = treeNode.id;
                // }
                search();
            }
        }
    }
    //设置树的初始数据
    var zNodes = [
         {id: 0, pId: "", name: "全部", isParent: true}
    ];
    $.fn.zTree.init($("#tree"), setting, zNodes);
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var node = zTree.getNodesByParam("id","0");
    lastExpandNode=node;
    zTree.expandNode(node[0],  true, false, false);
    //设置树形数据结束
}
function initTree() {
    let data = '';
    if (_mdpId != undefined) {
        data += 'mdpId=' + _mdpId;
    }
    if (_mdpsId != undefined) {
        data += '&mdpsId=' + _mdpsId;
    }
    //设置树的初始数据
    $.supper("doservice", {
        "service": _all_SiteFirst, 'data': data, "BackE": function (jsondata) {
            $.fn.zTree.init($("#roleTree"), rolSetting, jsondata);
            //设置树形数据结束
            $("#roleTree").css("height", $(window).height() - 120);
        }
    });
}

//设置数据的展现形式
function filter(treeId, parentNode, childNodes) {
    if (!childNodes)
        return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        if (childNodes[i].name !== undefined)
            childNodes[i].name = childNodes[i].name.replace('', '');
    }
    return childNodes;
}


//--------------------------------tree---------------------------

//当增加树的数据后刷新当前节点
function loadAddTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null)//刷新当前节点
        zTree.reAsyncChildNodes(lastExpandNode, "refresh");
    search();
}

//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null) {
        var zTree = $.fn.zTree.getZTreeObj("tree");
        if (lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
            zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");
    }
    search();
}