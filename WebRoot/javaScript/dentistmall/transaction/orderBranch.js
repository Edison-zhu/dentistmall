//仓管首页
$(function () {
    writeCurrentDate();
    // initData();
    // initCount();
    //searchAll();
    searchAll();
    initCount1();

    initCount();
    //日期格式
    laydate({
        elem: '#dateInput1',
        format: 'YYYY-MM-DD'//日期格式
    });
    laydate({
        elem: '#dateInput2',
        format: 'YYYY-MM-DD' //日期格式
    });

});

//获取当前时间
function writeCurrentDate() {
    var now = new Date();
    var year = now.getFullYear(); //得到年份
    var month = now.getMonth();//得到月份
    var date = now.getDate();//得到日期
    var day = now.getDay();//得到周几
    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    month = month + 1;
    week = arr_week[day];
    var time = "";
    time = year + "年" + month + "月" + date + "日" + " " + week;
    $("#currentDate").html(time);
    //设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。
    var timer = setTimeout("writeCurrentDate()", 1000);
}
/**
 *
 */
// function initData() {
//     var vdata = "";//&limit=" + 5 + "&page=" + 1
//     $.supper('initPagination', {
//         id: "Pagination1",
//         service: "MdOutOrderService.sevenClaimant",
//         data: vdata,
//         limit:10,
//         isAjax: "1",
//         "BackE": initList
//     });
//
// }
//

//3个模块的统计
function initCount() {
    $.supper("doservice", {
        "service": "MdOrderInfoService.branchleft1", "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#branchleft1").html(jsondata.obj.branchleft1);
                $("#branchleft2").html(jsondata.obj.branchleft2);
            }
        }
    });
}
function initCount1() {
    $.supper("doservice", {
        "service": "MdOrderInfoService.getBranchCount", "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#yiJieSuanMoney").html(jsondata.obj.yiJieSuanMoney);
                $("#yiJieSuanCount").html(jsondata.obj.yiJieSuanCount);

                $("#weiJieSuanMoney").html(jsondata.obj.weiJieSuanMoney);
                $("#weiJieSuanCount").html(jsondata.obj.weiJieSuanCount);

                $("#yiTuiKuanMoney").html(jsondata.obj.yiTuiKuanMoney);
                $("#yiTuiKuanCount").html(jsondata.obj.yiTuiKuanCount);

                $("#weiTuiKuanMoney").html(jsondata.obj.weiTuiKuanMoney);
                $("#weiTuiKuanCount").html(jsondata.obj.weiTuiKuanCount);

            }
        }
    });
}
//
function searchAll() {
    //交易时间
    var dateInput1=$("#dateInput1").val();
    var dateInput2=$("#dateInput2").val();
    //关键词
    var selectGuanjian=$("#selectGuanjian").val();
    var inputGuanjian=$("#inputGuanjian").val();
    //支付
    var zhiFu=$("#zhiFu").val();
    //状态
    var state=$("#state").val();
    //金额范围
    var jinE1=$("#jinE1").val();
    var jinE2=$("#jinE2").val();
    //资金状态
    var ziJin=$("#ziJin").val();
    var vdata="&dateInput1="+dateInput1+"&dateInput2="+dateInput2+"&selectGuanjian="+selectGuanjian+"&inputGuanjian="+inputGuanjian+"&zhiFu="+zhiFu+"&state="+state+"&jinE1="+jinE1+"&jinE2="+jinE2+"&ziJin="+ziJin;
    $.supper('initPagination', {
        id: "Pagination1",
        service: "MdOrderInfoService.getBranchMxList",
        data: vdata,
        limit:5,
        isAjax: "1",
        "BackE":initList
    });

}

function initList(jsondata) {
    var mxList = jsondata;
    var str = "";
    var mooId = "";
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            mooId = mxList[i].moiId;
           //  if (mxList[i].processState==5) {
           //      str += "<tr style='border-bottom:1.5px solid #F0F0F0;'><td style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].PlaceOrderTime + "</td>";
           //  }else {
           //      if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
           //          str += "<tr style='border-bottom:1.5px solid #F0F0F0;'><td style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].PlaceOrderTime + "</td>";
           //          // str +=" <tr style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].PlaceOrderTime + "</tr>"
           //      }else {
           //          str += "<tr style='border-bottom:1.5px solid #F0F0F0;'><td style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].PlaceOrderTime + "</td>";
           //      }
           //  }
           //  if (mxList[i].processState==5) {
           //      str += "<td style=\"text-align:center;padding:0px;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].orderCode + "<hr/></td>";
           //  }else {
           //      if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
           //          str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\"><div style='height: 50%;border-bottom: solid 1px black;'>" + mxList[i].orderCode + "</div></td>";
           //      }else {
           //          str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].orderCode + "</td>";
           //      }
           //  }
           //  if (mxList[i].processState==5) {
           //      str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].orderCode + "</td>";
           //  }else {
           //      if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
           //          str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\"><div style='height: 50%;border-bottom: solid 1px black;'>" + mxList[i].orderCode + "</div></td>";
           //      }else {
           //          str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].orderCode + "</td>";
           //      }
           //  }
           //       str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">" + mxList[i].orderCode+ "</td>";
           //      str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">" + mxList[i].PurchaseUnit+ "</td>";
           //  if (mxList[i].payType==1) {
           //               str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">支付宝</td>";
           //           }else if (mxList[i].payType==2){
           //               str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">微信</td>";
           //           }else if (mxList[i].payType==3){
           //               str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">月结</td>";
           //           }else {
           //           str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\"></td>";
           //       }
           // str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">" + mxList[i].placeOrderMoney+ "</td>";
           //   if (mxList[i].processState==5) {
           //   str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">交易成功</td>";
           //   }else if (mxList[i].processState==6) {
           //       str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">交易失败</td>";
           //   }else{
           //       str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\"></td>";
           //   }
           //  if (mxList[i].settlement != null && mxList[i].settlement != undefined) {
           //               if (mxList[i].settlement == 0) {
           //                   str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">待结算</td>";
           //               } else if (mxList[i].settlement == 1) {
           //                   str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">已结算</td>";
           //               } else if (mxList[i].settlement == 2) {
           //                   str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">部分结算</td>";
           //               }
           //           } else {
           //               str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">未结算</td>";
           //           }
           //  str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border-right:1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF; \" onclick=\"Claimant1(" + mooId + ")\">详情</a></td>";//<a style=\"color: #46A3FF; \" onclick='ClaimantLeft2(" + mooId + ")'>

            //注释分割线-----------------------------

             if (mxList[i].processState==5){
                str += "<tr style='border-bottom:1.5px solid #F0F0F0;'><td style=\"text-align:center;line-height:3;border-left:1.5px solid #F0F0F0;font-size: 13px;\">" + mxList[i].PlaceOrderTime+ "</td>";
            }else {
                if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
                    str += "<tr style='border-bottom:1.5px solid #F0F0F0;'><td style=\"text-align:center;line-height:6;border-left:1.5px solid #F0F0F0;font-size: 13px;\">" + mxList[i].PlaceOrderTime+ "</td>";
                }
                else {
                    str += "<tr style='border-bottom:1.5px solid #F0F0F0;'><td style=\"text-align:center;line-height:3;border-left:1.5px solid #F0F0F0;font-size: 13px;\">" + mxList[i].PlaceOrderTime+ "</td>";
                }
            }
           if (mxList[i].processState==5){
                str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">" + mxList[i].orderCode+ "</td>";
            }else{
               if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
                   str += "<td style=\"text-align:center;font-size: 13px;line-height:3;padding: 0px;\">"+ mxList[i].orderCode+"<br/><hr/><span style='color: #BEBEBE'>"+mxList[i].masCode+"</span></td>";
               }else {
                   str += "<td style=\"text-align:center;line-height:3;line-height:3;font-size: 13px;\">" + mxList[i].orderCode+"</td>";
               }
           }

           if (mxList[i].processState==5){
               str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">" + mxList[i].PurchaseUnit+ "</td>";
           }else{
               if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
                   str += "<td style=\"text-align:center;line-height:3;font-size: 13px;padding: 0px;\">" + mxList[i].PurchaseUnit+ "<br/><hr/><span style='color: #BEBEBE'>(退款给对方)</span></td>";
               }else {
                   str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">" + mxList[i].PurchaseUnit+ "</td>";
               }
           }




            if (mxList[i].processState==5){
            if (mxList[i].payType!=null&&mxList[i].payType!=undefined) {
                if (mxList[i].payType==1) {
                    str += "<td style=\"text-align:center;line-height:3;padding: 0px;font-size: 13px;\">支付宝</td>";
                }else if (mxList[i].payType==2){
                    str += "<td style=\"text-align:center;line-height:3;padding: 0px;font-size: 13px;\">微信</td>";
                }else if (mxList[i].payType==3){
                    str += "<td style=\"text-align:center;line-height:3;padding: 0px;font-size: 13px;\">月结</td>";
                }
            }else {
                str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">月结</td>";
            }
            }else{
                if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
                    if (mxList[i].payType!=null&&mxList[i].payType!=undefined) {
                        if (mxList[i].payType==1) {
                            str += "<td style=\"text-align:center;line-height:3;padding: 0px;font-size: 13px;\">支付宝<hr/></td>";
                        }else if (mxList[i].payType==2){
                            str += "<td style=\"text-align:center;line-height:3;padding: 0px;font-size: 13px;\">微信<hr/></td>";
                        }else if (mxList[i].payType==3){
                            str += "<td style=\"text-align:center;line-height:3;padding: 0px;font-size: 13px;\">月结<hr/></td>";
                        }
                    }else {
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;padding: 0px;border-left:1.5px solid #F0F0F0;\">月结<hr/></td>";
                    }                }
                else {
                    if (mxList[i].payType!=null&&mxList[i].payType!=undefined) {
                        if (mxList[i].payType==1) {
                            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">支付宝</td>";
                        }else if (mxList[i].payType==2){
                            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">微信</td>";
                        }else if (mxList[i].payType==3){
                            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">月结</td>";
                        }
                    }else {
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">月结</td>";
                    }
                }
            }

            if (mxList[i].processState==5){
                str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">+" + mxList[i].placeOrderMoney+ "</td>";
            }
            else{
                if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
                    str += "<td style=\"text-align:center;line-height:3;font-size: 13px;padding: 0px;\">+" + mxList[i].placeOrderMoney+ "<br/><hr/><span style='color: #BEBEBE'>-"+mxList[i].saleMoney+ "</span></td>";
                }else {
                    str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">+" + mxList[i].placeOrderMoney+ "</td>";
                }
            }
            if (mxList[i].processState==5){
                if (mxList[i].processState==5) {
                    str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">交易成功</td>";
                }else if (mxList[i].processState==6) {
                    str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">交易失败</td>";
                }else{
                    str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\"></td>";
                }
            }
            else{
                if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
                    if (mxList[i].processState==5) {
                        str += "<td style=\"text-align:center;padding: 0px;line-height:3;font-size: 13px;\">交易成功<hr/></td>";
                    }else if (mxList[i].processState==6) {
                        str += "<td style=\"text-align:center;padding: 0px;line-height:3;font-size: 13px;\">交易失败<hr/></td>";
                    }else{
                        str += "<td style=\"text-align:center;padding: 0px;line-height:3;font-size: 13px;\"><hr/></td>";
                    }                }else {
                    if (mxList[i].processState==5) {
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">交易成功</td>";
                    }else if (mxList[i].processState==6) {
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">交易失败</td>";
                    }else{
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\"></td>";
                    }                }
            }


            if (mxList[i].processState==5) {
                if (mxList[i].settlement != null && mxList[i].settlement != undefined) {
                    if (mxList[i].settlement == 0) {
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">待结算</td>";
                    } else if (mxList[i].settlement == 1) {
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">已结算</td>";
                    } else if (mxList[i].settlement == 2) {
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">部分结算</td>";
                    }
                } else {
                    str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">未结算</td>";
                }
            }
            else{
                if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
                    if (mxList[i].settlement != null && mxList[i].settlement != undefined) {
                        if (mxList[i].settlement == 0) {
                            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;padding: 0px;\">待结算<hr/></td>";
                        } else if (mxList[i].settlement == 1) {
                            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;padding: 0px;\">已结算<hr/></td>";
                        } else if (mxList[i].settlement == 2) {
                            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;padding: 0px;\">部分结算<hr/></td>";
                        }
                    } else {
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;padding: 0px;\">未结算<hr/></td>";
                    }
                }
                else {
                    if (mxList[i].settlement != null && mxList[i].settlement != undefined) {
                        if (mxList[i].settlement == 0) {
                            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">待结算</td>";
                        } else if (mxList[i].settlement == 1) {
                            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">已结算</td>";
                        } else if (mxList[i].settlement == 2) {
                            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">部分结算</td>";
                        }
                    } else {
                        str += "<td style=\"text-align:center;line-height:3;font-size: 13px;\">未结算</td>";
                    }                }
            }

            if (mxList[i].processState==5){
                str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border-right:1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF;text-decoration: underline \" onclick=\"Claimant1(" + mooId + ")\">详情</a></td>";//<a style=\"color: #46A3FF; \" onclick='ClaimantLeft2(" + mooId + ")'>
            }
            else{
                if (mxList[i].masCode!=null&&mxList[i].masCode!=undefined) {
                    str += "<td style=\"text-align:center;line-height:3;padding: 0px;font-size: 13px;border-right:1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF;text-decoration: underline \" onclick=\"Claimant1(" + mooId + ")\">详情</a><hr/></td>";//<a style=\"color: #46A3FF; \" onclick='ClaimantLeft2(" + mooId + ")'>
                }else {
                    str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border-right:1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF;text-decoration: underline \" onclick=\"Claimant1(" + mooId + ")\">详情</a></td>";//<a style=\"color: #46A3FF; \" onclick='ClaimantLeft2(" + mooId + ")'>
                }
            }
            // str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border-left:1.5px solid #F0F0F0;border-right:1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF; \" onclick=\"Claimant1(" + mooId + ")\">详情</a></td>";//<a style=\"color: #46A3FF; \" onclick='ClaimantLeft2(" + mooId + ")'>
            str += "</tr>";
        }
        //countSums+="已结算"+yiJieSum+"未结算"+weiJie+"退款"+yiTuiKuan+"笔"+"未退款"+weiTuiKuans+"笔";
        $("#mxList").html(str);
    }
    //$("#countMoney").html(countSums);
    /* }*/
};
// function exportExcel1() {
//
// }
//导出申领出库预警
function exportExcel1(){
    var dateInput1=$("#dateInput1").val();
    var dateInput2=$("#dateInput2").val();
    //关键词
    var selectGuanjian=$("#selectGuanjian").val();
    var inputGuanjian=$("#inputGuanjian").val();
    //支付
    var zhiFu=$("#zhiFu").val();
    //状态
    var state=$("#state").val();
    //金额范围
    var jinE1=$("#jinE1").val();
    var jinE2=$("#jinE2").val();
    //资金状态
    var ziJin=$("#ziJin").val();
    var vdata="&dateInput1="+dateInput1+"&dateInput2="+dateInput2+"&selectGuanjian="+selectGuanjian+"&inputGuanjian="+inputGuanjian+"&zhiFu="+zhiFu+"&state="+state+"&jinE1="+jinE1+"&jinE2="+jinE2+"&ziJin="+ziJin;
    var newTab=window.open('about:blank');
    //重新提交
    $.supper("doservice", {"service":"MdOrderInfoService.exportBranchMxList","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}
function click1() {
    if ($("#countMoney").is(':hidden')){
        $("#countMoney").show();
    }else {
        $("#countMoney").hide();
    }
}
function Claimant1(moiId) {
    var vdata = 'moiId=' + moiId;
    var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/viewSupplierOrderInfo"});
    $.supper("showTtemWin",{ "url":att_url,"title":"订单详情"});
}
