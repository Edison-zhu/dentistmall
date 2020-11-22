//仓管首页
var allClaomant;
var allPages;
$(function () {
    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if(selOutOrderType != null && selOutOrderType.moiId != null){
        allClaomant=selOutOrderType.moiId;
        allPages=selOutOrderType.pages;
        $.supper("setProductArray", {"name":"selOutOrderType", "value":null});
    }
    initDataAnalysis();
    initDataAnalysis2();
});



// var settlementState;
// var settlementMoney;
function initDataAnalysis() {
    if (allClaomant!=null&&allClaomant!=undefined) {
        var vdata = "&moiId=" +allClaomant;
    }else {
        var vdata = "&moiId="+209332;
    }
    $.supper("doservice", {
        "service": "FinancialWorkbenchService.getFinanciaBranchMxList",
        data: vdata,
        isAjax: "1",
        "BackE": function (jsondata) {
            var mxList = jsondata.rows;
            var str="";
            if (mxList != null && mxList.length > 0) {
                for (var i = 0; i < mxList.length; i++) {
                    // settlementState=mxList[i].settlement;
                    // settlementMoneys=mxList[i].settlement_money;
                    //mooId = mxList[i].moiId;
                    str += "<tr style='display: inline-block;'><td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].order_code+ "</td>";
                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].PlaceOrderTime+ "</td>";
                    if (mxList[i].payType!=null&&mxList[i].payType!=undefined){
                        if (mxList[i].payType==1) {
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">支付宝</td>";
                        }else if (mxList[i].payType==2){
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">微信</td>";
                        }else if (mxList[i].payType==3){
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">月结</td>";
                        }
                    }
                    else {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\"></td>";
                    }
                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">"+mxList[i].paydate+"</td>";
                    if (mxList[i].pay_code!=null&&mxList[i].pay_code!=undefined) {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].pay_code+ "</td>";
                    }else{
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\"></td>";
                    }
                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].place_order_money+ "</td>";
                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].Actual_money+ "</td>";

                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].Purchase_unit+ "</td>";
                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">" + mxList[i].applicant_Name+ "</td>";
                    if (mxList[i].pStart!=null&&mxList[i].pStart!=undefined) {
                     if (mxList[i].pStart==5) {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">已完成</td>";
                    }else if (mxList[i].pStart==6) {
                        str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">交易失败</td>";
                    }
                    }
                    str += "</tr>";
                }
                $("#mxList").html(str);
            }
    }
    });
}
var lackMoney;
var orderCode;
function initDataAnalysis2() {
    if (allClaomant!=null&&allClaomant!=undefined) {
        var vdata = "&moiId=" +allClaomant;
    }else {
        var vdata = "&moiId="+209332;
    }
    $.supper("doservice", {
        "service": "FinancialWorkbenchService.getFinanciaBranchMxList",
        data: vdata,
        isAjax: "1",
        "BackE": function (jsondata) {
            var mxList = jsondata.rows;
            var str="";

            // var lackMoney=mxList[i].Actual_money-mxList[i].settlement_money;
            if (mxList != null && mxList.length > 0) {
                for (var i = 0; i < mxList.length; i++) {
                    if (mxList[i].settlement_money!=null&&mxList[i].settlement_money!=undefined){
                        lackMoney=mxList[i].Actual_money-mxList[i].settlement_money;
                    }else {
                        lackMoney=mxList[i].Actual_money-0.0;
                    }
                    moiId=mxList[i].moiId;
                    orderCode=mxList[i].order_code;
                    str += "<tr style='display: inline-block;'>";
                        if (mxList[i].settlement==null&&mxList[i].settlement==undefined) {
                            mxList[i].settlement=0;
                        }
                        if (mxList[i].settlement==1) {
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">已结算</td>";
                        }else if (mxList[i].settlement==0){
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">未结算</td>";
                        } else if(mxList[i].settlement==2){
                            str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">部分结算</td>";
                        }
                    if (mxList[i].Actual_money!=null&&mxList[i].Actual_money!=undefined){
                        if (mxList[i].settlement_money!=null&&mxList[i].settlement_money!=undefined) {
                            if (mxList[i].settlement!=1) {
                                if (mxList[i].settlement_money!=mxList[i].Actual_money) {
                                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\"><input type='text' id=\"input1\" value='' placeholder='结算金额不能超过"+lackMoney+"'  style=\"position: relative;top:-1px\"><button type=\"button\" onclick=\"jieSuan();\" class=\"btn btn-primary btn-xs\" id=\"button1\" style=\"width: 95px;height: 22px;margin-top: -4px;margin-left: 10px;margin-right: 10px\">结算</button>";
                                }else{
                                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">";
                                }
                                str += "应结算￥" + mxList[i].Actual_money + "已结算￥" + mxList[i].settlement_money + "</td>";
                            }else {
                                str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">";
                                str += "应结算￥" + mxList[i].Actual_money + "已结算￥" + mxList[i].settlement_money + "</td>";
                            }
                            str += "应结算￥" + mxList[i].Actual_money + "已结算￥" + mxList[i].settlement_money + "</td>";
                        }else {
                            if (mxList[i].settlement!=1) {
                                if (mxList[i].settlement_money!=mxList[i].Actual_money) {
                                    str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\"><input type='text' id=\"input1\" value='' placeholder='结算金额不能超过"+lackMoney+"' style=\"position: relative;top:-1px\"><button type=\"button\" onclick=\"jieSuan();\" class=\"btn btn-primary btn-xs\" id=\"button1\" style=\"width: 95px;height: 22px;margin-top: -4px;margin-left: 10px;margin-right: 10px\">结算</button>";
                                }else {
                                    str += "应结算￥" + mxList[i].Actual_money + "已结算￥0</td>";
                                }
                            }
                            str += "应结算￥" + mxList[i].Actual_money + "已结算￥0</td>";
                        }
                        }
                     if (mxList[i].settlement_log!=null&&mxList[i].settlement_log!=undefined){
                         str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\">"+ mxList[i].settlement_log +"</td>";
                     }else {
                         str += "<td style=\"text-align:left;width:1180px;font-size: 13px;height: 29px;display: block;\"></td>";
                     }
                        str += "</tr>";
                }
                $("#mxList1").html(str);
            }
        }
    });
}

function jieSuan(){
    // if (input1)

    var checkStr=allClaomant;
    var settlementMoney=$("#input1").val();
    if (settlementMoney>lackMoney) {
        $.supper("alert",{ title:"操作提示", msg:"结算金额不能超过"+lackMoney});
        return;
    }else {
        if (checkStr!=null&&checkStr!=undefined&&checkStr!=""){
            var vdata="&checkStr="+checkStr+"&settlementMoney="+settlementMoney;
            $.supper("doservice", {"service":"FinancialWorkbenchService.savePlJieSuan","data":vdata, "BackE":function (jsondata) {
                    if (jsondata.code == "1") {
                        initDataAnalysis();
                        initDataAnalysis2();
                    }else
                        $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
                }});
        }else{
            alert("请选择操作数据");
        }
    }
}
//跳转到财务工作台页面
function requestFinance() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/main/FinancePage.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{allPages: allPages}});
    $.supper("showTtemWin",{ "url":att_url,"title":"财务工作台"});
}
