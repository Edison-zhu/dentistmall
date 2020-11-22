//仓管首页
var _applicantNameService = 'MdOrderInfoService.getAllPagerModelObjectDistinct';
var _purchaseUnitService = 'MdOrderInfoService.getAllPagerModelObjectDistinct';
var _applicantNameSelectAction = {serviceName: _applicantNameService};
var _purchaseUnitSelectAction = {serviceName: _purchaseUnitService};

$(function () {
    totalMoney();
    //日期格式
    laydate({
        elem: '#dateInput1',
        format: 'YYYY-MM-DD'//日期格式
    });
    laydate({
        elem: '#dateInput2',
        format: 'YYYY-MM-DD' //日期格式
    });
    laydate({
        elem: '#dateInput5',
        format: 'YYYY-MM-DD'//日期格式
    });
    laydate({
        elem: '#dateInput6',
        format: 'YYYY-MM-DD' //日期格式
    });
    laydate({
        elem: '#dateInput7',
        format: 'YYYY-MM-DD' //日期格式
    });
    laydate({
        elem: '#dateInput8',
        format: 'YYYY-MM-DD' //日期格式
    });

    allBtn();

    searchAll();

    applicantNameInputSelect();
    purchaseUnitInputSelect();
});

function applicantNameInputSelect() {
    $('#gongYingS').editableSelect();
    _applicantNameSelectAction.data = '&distinctName=applicantName';
    $('#gongYingS').on('focus', function () {
        $('#gongYingS').editableSelect('clear');
        var shqJson=$.supper("doselectService", _applicantNameSelectAction);
        var items = shqJson.items;
        for(var  i = 0 ;i < items.length ;i++){
            $('#gongYingS').editableSelect('add', items[i])
        }
        $('#gongYingS').editableSelect('show');
    })
}
function purchaseUnitInputSelect() {
    $('#jiGouMenZhen').editableSelect();
    _purchaseUnitSelectAction.data = '&distinctName=purchaseUnit';
    $('#jiGouMenZhen').on('focus', function () {
        $('#jiGouMenZhen').editableSelect('clear');
        var shqJson=$.supper("doselectService", _purchaseUnitSelectAction);
        var items = shqJson.items;
        for(var  i = 0 ;i < items.length ;i++){
            $('#jiGouMenZhen').editableSelect('add', items[i])
        }
        $('#jiGouMenZhen').editableSelect('show');
    })
}

function selectSalesMan() {
    var att_url= $.supper("getServicePath", {"data":'',url:"/jsp/dentistmall/salesman/choosesalesman"});
    var tt_win=$.supper("showWin",{url:att_url,title:"业务员信息",icon:"fa-bars",width:"1080",height: '570', BackE:addSalesManToInput});
}
function addSalesManToInput() {
    var selSalesManList = $.supper("getProductArray", "selSalesMan");
    if(selSalesManList != null && selSalesManList.length > 0 && selSalesManList.indexOf(',') >= 0){
        var salesmanDataArray = selSalesManList.split(',');
        var salesmanName = salesmanDataArray[1];
        var salesmanId = salesmanDataArray[0];
        $('#yeWuDaiLiS1').val(salesmanName);
        $('#yeWuDaiLiS').val(salesmanId);
        $.supper("setProductArray", {"name":"selSalesMan", "value":null});
    }
}

function clearSales() {
    $('#yeWuDaiLiS1').val('');
    $('#yeWuDaiLiS').val('');
}

function searchAll() {
    var orderCodeGJ = $("#orderCodeGJ").val();
    var dateInput1 = $("#dateInput1").val();
    var dateInput2 = $("#dateInput2").val();
    var selectValue = $("#selectValue").val();
    var vdata = "&orderCodeGJ=" + orderCodeGJ + "&dateInput1=" + dateInput1 + "&dateInput2=" + dateInput2 + "&selectValue=" + selectValue;
    $.supper('initPagination', {
        id: "Pagination1",
        service: "SysSalesmanService.getSalesManLoanMxList",
        data: vdata,
        limit: 10,
        isAjax: "1",
        "BackE": initList
    });

}

var orderCodeGJ1 = "";
var dateInput5 = "";
var dateInput6 = "";

function searchAlls() {
    $("#divHide1").show();
    $("#orderCodeGJ1").val($("#orderCodeGJ").val())
    $("#dateInput5").val($("#dateInput1").val());
    dateInput6 = $("#dateInput6").val($("#dateInput2").val());


    orderCodeGJ1 = $("#orderCodeGJ1").val();
    dateInput5 = $("#dateInput5").val();
    dateInput6 = $("#dateInput6").val();
}

function searchAll1() {
    var selectValue1 = $("#selectValue1").val();
    var moneyFw1 = $("#moneyFw1").val();
    var moneyFw2 = $("#moneyFw2").val();
    var gongYingS = $("#gongYingS").val();
    var caiGou = $("#yeWuDaiLiS").val();
    var jiGouMenZhen = $('#jiGouMenZhen').val();
    var dateInput7 = $('#dateInput7').val();
    var dateInput8 = $('#dateInput8').val();
    var selectValue2 = $('#selectValue2').val();
    orderCodeGJ1 = $("#orderCodeGJ1").val();
    dateInput5 = $("#dateInput5").val();
    dateInput6 = $("#dateInput6").val();
    var vdata = "&orderCodeGJ=" + orderCodeGJ1 + "&dateInput1=" + dateInput5 + "&dateInput2=" + dateInput6 +
        "&selectValue=" + selectValue1 + "&moneyFw1=" + moneyFw1 + "&moneyFw2=" + moneyFw2 + "&gongYingS=" + gongYingS +
        "&caiGou=" + caiGou + "&jiGouMenZhen=" + jiGouMenZhen + "&dateInput7=" + dateInput7 + "&dateInput8=" + dateInput8 + "&selectValue1=" + selectValue2;
    $.supper('initPagination', {
        id: "Pagination1",
        service: "SysSalesmanService.getSalesManLoanMxList",
        data: vdata,
        limit: 10,
        isAjax: "1",
        "BackE": initList
    });
}

function initList(jsondata) {
    var mxList = jsondata;
    var str = "";
    var moiId = "";
    var pages = "";
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            moiId = mxList[i].moi_id;
            pages = mxList[i].pageParameter;
            str += "<tr style='border-bottom:1.5px solid #F0F0F0;'><td style=\"text-align:center;font-size: 13px;border-left: 1.5px solid #F0F0F0;\"><input class='checkbox1' type=\"checkbox\" id=\"c1\" onclick='checkd(" + moiId + ")' value='" + moiId + "'/></td>";
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].sales_name + "</td>";
            if (mxList[i].sales_type == 1) {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">机构门诊业务</td>";
            } else if (mxList[i].sales_type == 2) {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">代理商</td>";
            } else {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">供应商业务</td>";
            }
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].order_code + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].place_order_time + "</td>";
            if (mxList[i].pay_type != null && mxList[i].pay_type != undefined) {
                if (mxList[i].pay_type == 1) {
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">支付宝</td>";
                } else if (mxList[i].pay_type == 2) {
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">微信</td>";
                } else if (mxList[i].pay_type == 3) {
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">月结</td>";
                }
            } else {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\"></td>";
            }
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].pay_date + "</td>";
            if (mxList[i].pay_code != null && mxList[i].pay_code != undefined) {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].pay_code + "</td>";
            } else {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\"></td>";
            }
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].place_order_money + "</td>";
            if (mxList[i].actual_money != null && mxList[i].actual_money != undefined) {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].actual_money + "</td>";
            } else {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\"></td>";
            }
            // str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].purchase_unit + "</td>";
            let processStatusName = '';
            switch (mxList[i].process_status) {
                case '1':
                    processStatusName = "待发货";
                    break;
                case '2':
                    processStatusName = "待付款";
                    break;
                case '3':
                    processStatusName = "待收货";
                    break;
                case '4':
                    processStatusName = "部分发货";
                    break;
                case '5':
                    processStatusName = "交易成功";
                    break;
                case '6':
                    processStatusName = "交易失败";
                    break;
            }
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + processStatusName + "</td>";
            if (mxList[i].loan_state == 1) {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">已放款</td>";
            } else if (mxList[i].loan_state == 2) {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">待放款</td>";
            } else {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">未放款</td>";
            }
            // str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].process_status + "</td>";
            if (mxList[i].loan_state == 1 || mxList[i].settlement != 1) {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;border-right:1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF;text-decoration:underline; \" onclick='ClaimantLeft2(" + moiId + "," + pages + ")' >查看订单</a></td>";
            } else if (mxList[i].settlement == 1) {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;border-right:1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF;text-decoration:underline; \" onclick='ClaimantLeft2(" + moiId + "," + pages + ")'>查看订单</a>&nbsp;&nbsp;<a style=\"color: #46A3FF;text-decoration:underline;\" onclick='checkd1(" + moiId + ")'>放款</a></td>";
            }
            if (checkStrSettlement[moiId] == undefined) {
                checkStrSettlement[moiId] = {};
            }
            checkStrSettlement[moiId].settlement = mxList[i].settlement == undefined ? '' : mxList[i].settlement;
            checkStrSettlement[moiId].loanState = mxList[i].loanState == undefined ? '' : mxList[i].loanState;
            str += "</tr>";
        }

    }
    $("#mxList").html(str);
};

function ClaimantLeft2(moiId, pages) {
    var att_url = $.supper("getServicePath", {"service": "", url: "/jsp/dentistmall/salesman/SalesManLoanMx.jsp"});
    $.supper("setProductArray", {"name": "salesManLoan", "value": {moiId: moiId, pages: pages, url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": "订单放款明细"});
}

function offTall() {
    $("#divHide1").hide();
}

var checkStr = "";
var check1;

function allBtn() {
    $("#all").click(function () {
        if (this.checked) {
            $("table tbody :checkbox").each(function () {
                $(this).prop("checked", true);
                checkStr += $(this).val() + ",";
            })
        } else {
            $("table tbody :checkbox").prop("checked", false);
            checkStr = "";
        }
    });
}

var count = 1;
var checkStrSettlement = {};

function checkd(moiId) {
    if (checkStr.indexOf(moiId) >= 0) {
        checkStr = checkStr.replace(moiId + ',', '');
    } else {
        checkStr += moiId + ',';
    }
}

function checkd1(moiId) {
    if (moiId == undefined || moiId == null || moiId == '') {
        $.supper("alert", {title: "操作提示", msg: "请选择操作数据！"});
        return;
    }
    $.supper("confirm", {
        title: "放款操作", msg: "您正在操作放款，请核对订单是否已完成放款，放款成功后无法进行撤销.是否确定放款？", yesE: function () {
            checkStr = moiId;
            plJieSuan();
        }
    });
}

function checkSettlementAndLoanState() {
    console.log(checkStr)
    if ((checkStr + '').indexOf(",") < 0) {
        if (checkStrSettlement[checkStr].settlement != 1) {
            $.supper("alert", {title: "操作提示", msg: "未结算，不允许放款！"});
            return false;
        }
        if (checkStrSettlement[checkStr].loanState == 1) {
            $.supper("alert", {title: "操作提示", msg: "已结算，不允许重复放款！"});
            return false;
        }
        return true;
    }
    let moiIdList = checkStr.split(',');
    let moiId = '';
    let settlementAndLoan;
    for (let idx in moiIdList) {
        moiId = moiIdList[idx];
        settlementAndLoan = checkStrSettlement[moiId];
        if (settlementAndLoan == undefined || settlementAndLoan == null) {
            continue;
        }
        if (settlementAndLoan.settlement != 1) {
            $.supper("alert", {title: "操作提示", msg: "未结算，不允许放款！"});
            return false;
        }
        if (settlementAndLoan.loanState == 1) {
            $.supper("alert", {title: "操作提示", msg: "已结算，不允许重复放款！"});
            return false;
        }
    }
    return true;
}

function plJieSuan() {
    var settlementMoney = "";
    if (checkStr == undefined && checkStr == null && checkStr == "") {
        $.supper("alert", {title: "操作提示", msg: "请选择数据！"});
        return;
    }
    if (checkSettlementAndLoanState() == false) {
        return;
    }

    var vdata = "&checkStr=" + checkStr + "&loanMoney=" + settlementMoney;
    $.supper("doservice", {
        "service": "SysSalesmanService.saveLoan", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                searchAll();
            } else if (jsondata.code == '2') {
                $.supper("alert", {title: "操作提示", msg: "存在未结算数据，不允许放款！"});
            } else if (jsondata.code == '3') {
                $.supper("alert", {title: "操作提示", msg: "存在已结算数据，不允许放款！"});
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });

}

function exportFinanciaBranchMxList() {
    var selectValue1 = $("#selectValue1").val();
    var moneyFw1 = $("#moneyFw1").val();
    var moneyFw2 = $("#moneyFw2").val();
    var gongYingS = $("#gongYingS").val();
    var caiGou = $("#yeWuDaiLiS").val();
    var jiGouMenZhen = $('#jiGouMenZhen').val();
    var dateInput7 = $('#dateInput7').val();
    var dateInput8 = $('#dateInput8').val();
    var selectValue2 = $('#selectValue2').val();
    orderCodeGJ1 = $("#orderCodeGJ1").val();
    dateInput5 = $("#dateInput5").val();
    dateInput6 = $("#dateInput6").val();
    var vdata = "&orderCodeGJ=" + orderCodeGJ1 + "&dateInput1=" + dateInput5 + "&dateInput2=" + dateInput6 +
        "&selectValue=" + selectValue1 + "&moneyFw1=" + moneyFw1 + "&moneyFw2=" + moneyFw2 + "&gongYingS=" + gongYingS +
        "&caiGou=" + caiGou + "&jiGouMenZhen=" + jiGouMenZhen + "&dateInput7=" + dateInput7 + "&dateInput8=" + dateInput8 + "&selectValue1=" + selectValue2;
    //导出财务对账
    var newTab = window.open('about:blank');
//重新提交
    $.supper("doservice", {
        "service": "ExportExcelService.exportLoanMxList", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

//导出供应商交易金额分析
function exportapplicName() {
    var dateInput3 = $("#dateInput3").val();
    var dateInput4 = $("#dateInput4").val();
    var vdata = "&dateInput3=" + dateInput3 + "&dateInput4=" + dateInput4;
    var newTab = window.open('about:blank');
    $.supper("doservice", {
        "service": "SysSalesmanService.exportapplicName", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

//统计汇总
function totalMoney() {
    vdata = '';
    $.supper("doservice", {
        "service": "SysSalesmanService.getTotalMoney", data: vdata, isAjax: "1", "BackE": function (jsondata) {
            $("#total1").html(jsondata.obj.total);
            $("#money1").html(jsondata.obj.money1);
            $("#money2").html(jsondata.obj.money2);
        }
    });
}