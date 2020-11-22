//仓管首页
var allClaomant;
var allPages;
var thisWin;
$(function () {
    var selOutOrderType = $.supper("getProductArray", "salesManLoan");
    if (selOutOrderType != null && selOutOrderType.moiId != null) {
        allClaomant = selOutOrderType.moiId;
        allPages = selOutOrderType.pages;
        thisWin = selOutOrderType.url;
        // $.supper("setProductArray", {"name": "salesManLoan", "value": null});
    }
    initDataAnalysis();
    initDataAnalysis2();
});


// var settlementState;
// var settlementMoney;
function initDataAnalysis() {
    if (allClaomant == undefined || allClaomant == null) {
        return;
    }
    var vdata = "&moiId=" + allClaomant + '&page=1';
    $.supper("doservice", {
        "service": "SysSalesmanService.getSalesManLoanMxList",
        data: vdata,
        isAjax: "1",
        "BackE": function (jsondata) {
            var mxList = jsondata.rows;
            var str = "";
            if (mxList != null && mxList.length > 0) {
                for (var i = 0; i < mxList.length; i++) {
                    // settlementState=mxList[i].settlement;
                    // settlementMoneys=mxList[i].settlement_money;
                    //mooId = mxList[i].moiId;
                    str += "<tr style='display: inline-block;'><td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].order_code + "" +
                        "<a onclick=\"viewSupplierOrder(" + mxList[i].moi_id + ")\" class='trigger' target='_blank'>订单详情</a></td>";
                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].place_order_time + "</td>";
                    if (mxList[i].pay_type != null && mxList[i].pay_type != undefined) {
                        if (mxList[i].pay_type == 1) {
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">支付宝</td>";
                        } else if (mxList[i].pay_type == 2) {
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">微信</td>";
                        } else if (mxList[i].pay_type == 3) {
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">月结</td>";
                        }
                    } else {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\"></td>";
                    }
                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].pay_date + "</td>";
                    if (mxList[i].pay_code != null && mxList[i].pay_code != undefined) {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].pay_code + "</td>";
                    } else {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\"></td>";
                    }
                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].place_order_money + "</td>";
                    if (mxList[i].actual_money != null && mxList[i].actual_money != undefined) {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].actual_money + "</td>";
                    } else {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\"></td>";
                    }

                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].purchase_unit + "</td>";
                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].applicant_name + "</td>";
                    if (mxList[i].process_status != null && mxList[i].process_status != undefined) {
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
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + processStatusName + "</td>";
                        if (mxList[i].settlement != null && mxList[i].settlement != undefined) {
                            if (mxList[i].settlement == 1) {
                                str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">已结算</td>";
                            } else if (mxList[i].settlement == 0) {
                                str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">未结算</td>";
                            } else if (mxList[i].settlement == 2) {
                                str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">部分结算</td>";
                            }
                        } else {
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">未结算</td>";
                        }
                        if (mxList[i].sales_type == 1) {
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">机构门诊业务/" + mxList[i].sales_name + "</td>";
                        } else if (mxList[i].sales_type == 2) {
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">代理商/" + mxList[i].sales_name + "</td>";
                        } else {
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">供应商业务/" + mxList[i].sales_name + "</td>";
                        }
                    }
                    str += "</tr>";
                }
                $("#mxList").html(str);
            }
        }
    });
}

function viewSupplierOrder(moiId){
    var vdata="moiId="+moiId;
    var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/viewSupplierOrderInfo"});
    $.supper("setProductArray", {"name": "selOutOrderInfo", "value": {preUrl: thisWin, orderUrl: att_url}});
    $.supper("showTtemWin",{ "url":att_url,"title":"查看订单"});
}

var lackMoney;
var orderCode;

function initDataAnalysis2() {
    if (allClaomant == undefined || allClaomant == null) {
        return;
    }
    var vdata = "&moiId=" + allClaomant + '&page=1';
    $.supper("doservice", {
        "service": "SysSalesmanService.getSalesManLoanMxList",
        data: vdata,
        isAjax: "1",
        "BackE": function (jsondata) {
            var mxList = jsondata.rows;
            var str = "";

            // var lackMoney=mxList[i].Actual_money-mxList[i].settlement_money;
            if (mxList != null && mxList.length > 0) {
                for (var i = 0; i < mxList.length; i++) {
                    moiId = mxList[i].moiId;
                    orderCode = mxList[i].order_code;
                    str += "<tr style='display: inline-block;'>";
                    if (mxList[i].loan_state == 1) {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">已放款</td>";
                    } else if (mxList[i].loan_state == 2) {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">待放款</td>";
                    } else {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">未放款</td>";
                    }

                    if (mxList[i].loan_log != undefined && mxList[i].loan_log != null) {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].loan_log + "</td>";
                    } else {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\"></td>";
                    }
                    str += "</tr>";
                }
                $("#mxList1").html(str);
            }
        }
    });
}

function jieSuan() {
    // if (input1)

    var checkStr = allClaomant;
    var settlementMoney = $("#input1").val();
    if (settlementMoney > lackMoney) {
        $.supper("alert", {title: "操作提示", msg: "结算金额不能超过" + lackMoney});
        return;
    } else {
        if (checkStr != null && checkStr != undefined && checkStr != "") {
            var vdata = "&checkStr=" + checkStr + "&settlementMoney=" + settlementMoney;
            $.supper("doservice", {
                "service": "SysSalesmanService.saveLoan", "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        initDataAnalysis();
                        initDataAnalysis2();
                    } else
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                }
            });
        } else {
            alert("请选择操作数据");
        }
    }
}

//跳转到财务工作台页面
function requestFinance() {
    setTimeout(function () {
        $.supper("setProductArray", {"name": "salesManLoan", "value": null});
        $.supper("closeTtemWin", {url: thisWin});
    }, 200);
    var att_url = $.supper("getServicePath", {"service": "", url: "/jsp/dentistmall/salesman/SalesManLoan.jsp"});
    $.supper("setProductArray", {"name": "selOutOrderInfo", "value": {allPages: allPages}});
    $.supper("showTtemWin", {"url": att_url, "title": "业务放款"});
}
