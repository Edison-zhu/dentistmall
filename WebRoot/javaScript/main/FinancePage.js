//仓管首页

$(function () {
    writeCurrentDate();
    // initData();
    // initCount();
   // searchAll();
    totalMoney();
    initDataAnalysis(0);
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
        elem: '#dateInput3',
        format: 'YYYY-MM-DD'//日期格式
    });
    laydate({
        elem: '#dateInput4',
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

    $("#laydate_ok").click(
        initDataAnalysis1()
    );
    allBtn();
    initDataAnalysis1();
    $("#dateInput4").val(time1);
    $("#dateInput3").val(time1);


    // laydate_ok
    // ("#laydate_ok").click


    // $("#dateInput4").blur(function(){
    // });
    //
    // var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    // if(selOutOrderType != null && selOutOrderType.allPages != null){
    //     var allPage;
    //     allPage=selOutOrderType.allPages;
    //     $.supper("setProductArray", {"name":"selOutOrderType", "value":null});
    //     searchAll(allPage);
    // }
    // searchAll(null);

    searchAll();
    // startInterval();
});
// function a5(){
//     initDataAnalysis1();
// }
//
// function a6() {
//     alert(1234);
// }
//

//获取当前时间
var time1="";
var time2="";
var time3="";
var time4="";

function writeCurrentDate() {
    var now = new Date();
    var year = now.getFullYear(); //得到年份
    var month = now.getMonth();//得到月份
    var day = now.getDay();//得到周几
    var date ;
    if (now.getDate()>9){
        date = now.getDate();//得到日期
    }else{
        date ="0"+now.getDate();//得到日期
    }
    var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    month = month + 1;
    if (month>9){
        month = month;
    }else {
        month ="0"+month;
    }
    week = arr_week[day];
    var time = "";
    time = year + "年" + month + "月" + date + "日" + " " + week;
    $("#currentDate").html(time);
    time1=year+"-"+month+"-"+date
    time2=year+"-"+month+"-"+(date-1);
    time3=year+"-"+month+"-"+(date-7);
    time4=year+"-"+(month-1)+"-"+date
    //设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。
    var timer = setTimeout("writeCurrentDate()", 1000);
}


//3个模块的统计
var date1;
var value1="";
function initDataAnalysis(value) {
    var vdata = "&value="+value;
    $.supper("doservice", {
        "service": "FinancialWorkbenchService.getFinanceLeftRight",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            if (value==0) {
                //点击按钮变成红色
                $("#btn11").addClass("a1");//.removeAttr("style");
                $("#btn21").removeClass("a1");
                $("#btn31").removeClass("a1");
                $("#btn41").removeClass("a1");
                $("#dateInput1").val(time1);
                $("#dateInput2").val(time1);

                $("#dateInput3").val(time1);
                $("#dateInput4").val(time1);

                $("#FinanceLeft").html(jsondata.obj.placeOrderMoney1);
                $("#FinanceRight").html(jsondata.obj.placeOrderMoney2);
                value1=0;
            }
            if (value==1) {
                $("#btn21").addClass("a1");//.removeAttr("style");
                $("#btn11").removeClass("a1");
                $("#btn31").removeClass("a1");
                $("#btn41").removeClass("a1");

                $("#dateInput1").val(time2);
                $("#dateInput2").val(time1);

                $("#dateInput3").val(time2);
                $("#dateInput4").val(time1);

                $("#FinanceLeft").html(jsondata.obj.placeOrderMoney1);
                $("#FinanceRight").html(jsondata.obj.placeOrderMoney2);
                value1=1;
            }
            if (value==2) {
                $("#btn31").addClass("a1");//.removeAttr("style");
                $("#btn21").removeClass("a1");
                $("#btn11").removeClass("a1");
                $("#btn41").removeClass("a1");

                $("#dateInput1").val(time3);
                $("#dateInput2").val(time1);

                $("#dateInput3").val(time3);
                $("#dateInput4").val(time1);

                $("#FinanceLeft").html(jsondata.obj.placeOrderMoney1);
                $("#FinanceRight").html(jsondata.obj.placeOrderMoney2);
                value1=2;
            }
            if (value==3) {
                $("#btn41").addClass("a1");//.removeAttr("style");
                $("#btn21").removeClass("a1");
                $("#btn31").removeClass("a1");
                $("#btn11").removeClass("a1");

                $("#dateInput1").val(time4);
                $("#dateInput2").val(time1);

                $("#dateInput3").val(time4);
                $("#dateInput4").val(time1);

                $("#FinanceLeft").html(jsondata.obj.placeOrderMoney1);
                $("#FinanceRight").html(jsondata.obj.placeOrderMoney2);
                value1=3;
            }
        }
    });
}
// //未写完  写完财务接着写
// function searchAll() {
//     //交易时间
//     var dateInput1=$("#dateInput1").val();
//     var dateInput2=$("#dateInput2").val();
//     //关键词
//     var selectGuanjian=$("#selectGuanjian").val();
//     var inputGuanjian=$("#inputGuanjian").val();
//     //支付
//     var zhiFu=$("#zhiFu").val();
//     //状态
//     var state=$("#state").val();
//     //金额范围
//     var jinE1=$("#jinE1").val();
//     var jinE2=$("#jinE2").val();
//     //资金状态
//     var ziJin=$("#ziJin").val();
//     var vdata="&dateInput1="+dateInput1+"&dateInput2"+dateInput2+"&selectGuanjian"+selectGuanjian+"&inputGuanjian"+inputGuanjian+"&zhiFu"+zhiFu+"&state"+state+"&jinE1"+jinE1+"&jinE2"+jinE2+"&ziJin"+ziJin;
//     $.supper('initPagination', {
//         id: "Pagination2",
//         service: "MdOutOrderService.sevenOutMx",
//         data: vdata,
//         limit:5,
//         isAjax: "1",
//         "BackE": ckxq1
//     });
//
// }

function searchAll(i) {
        var orderCodeGJ=$("#orderCodeGJ").val();
        var dateInput1=$("#dateInput1").val();
        var dateInput2=$("#dateInput2").val();
        var selectValue=$("#selectValue").val();
        if (i!=null&&i==1) {
            dateInput1='';
            dateInput2='';
        }
        var vdata="&orderCodeGJ="+orderCodeGJ+"&dateInput1="+dateInput1+"&dateInput2="+dateInput2+"&selectValue="+selectValue;
        $.supper('initPagination', {
        id: "Pagination1",
        service: "FinancialWorkbenchService.getFinanciaBranchMxList",
        data: vdata,
        limit:10,
        isAjax: "1",
        "BackE":initList
    });

}
var orderCodeGJ1="";
var dateInput5="";
var dateInput6="";
function searchAlls(){
    $("#divHide1").show();
    $("#orderCodeGJ1").val($("#orderCodeGJ").val())
    $("#dateInput5").val($("#dateInput1").val());
    dateInput6=$("#dateInput6").val($("#dateInput2").val());


    orderCodeGJ1=$("#orderCodeGJ1").val();
    dateInput5=$("#dateInput5").val();
    dateInput6=$("#dateInput6").val();
}
function searchAll1(){
    var selectValue1=$("#selectValue1").val();
    var moneyFw1=$("#moneyFw1").val();
    var moneyFw2=$("#moneyFw2").val();
    var gongYingS=$("#gongYingS").val();
    var caiGou=$("#caiGou").val();
    var vdata="&orderCodeGJ="+orderCodeGJ1+"&dateInput1="+dateInput5+"&dateInput2="+dateInput6+"&selectValue="+selectValue1+"&moneyFw1="+moneyFw1+"&moneyFw2="+moneyFw2+"&gongYingS="+gongYingS+"&caiGou="+caiGou;
    $.supper('initPagination', {
        id: "Pagination1",
        service: "FinancialWorkbenchService.getFinanciaBranchMxList",
        data: vdata,
        limit:10,
        isAjax: "1",
        "BackE":initList
    });
}
function initList(jsondata) {
    var mxList = jsondata;
    var str = "";
    var mooId = "";
    var pages="";
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            // alert(mxList.length);
            mooId = mxList[i].moiId;
            pages=mxList[i].pageParameter;
            str += "<tr style='border-bottom:1.5px solid #F0F0F0;'><td style=\"text-align:center;font-size: 13px;border-left: 1.5px solid #F0F0F0;\"><input class='checkbox1' type=\"checkbox\" id=\"c1\" onclick='checkd(" + mooId + ")' value='" + mooId + "'/></td>";
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].order_code+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].PlaceOrderTime+ "</td>";
            if (mxList[i].payType!=null&&mxList[i].payType!=undefined){
                if (mxList[i].payType==1) {
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">支付宝</td>";
                 }else if (mxList[i].payType==2){
                     str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">微信</td>";
                 }else if (mxList[i].payType==3){
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">月结</td>";
                 }
                    }
                else {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\"></td>";
             }
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">"+mxList[i].paydate+"</td>";
                if (mxList[i].pay_code!=null&&mxList[i].pay_code!=undefined) {
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].pay_code+ "</td>";
                }else{
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\"></td>";
                }
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].place_order_money+ "</td>";
                if (mxList[i].Actual_money!=null&&mxList[i].Actual_money!=undefined) {
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].Actual_money+ "</td>";
                }
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].Purchase_unit+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">" + mxList[i].applicant_Name+ "</td>";
            if (mxList[i].settlement!=null&&mxList[i].settlement!=undefined) {
                if (mxList[i].settlement==1) {
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">已结算</td>";
                }else if (mxList[i].settlement==0){
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">未结算</td>";
                } else if(mxList[i].settlement==2){
                    str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">部分结算</td>";
                }
            }else {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;\">未结算</td>";
            }if (mxList[i].settlement==1){
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;border-right:1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF;text-decoration:underline; \" onclick='ClaimantLeft2(" + mooId +","+pages+ ")' >查看订单</a></td>";
            }else {
                str += "<td style=\"text-align:center;font-size: 13px;border-left:1.5px solid #F0F0F0;border-right:1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF;text-decoration:underline; \" onclick='ClaimantLeft2(" + mooId +","+pages+ ")'>查看订单</a>&nbsp;&nbsp;<a style=\"color: #46A3FF;text-decoration:underline;\" onclick='checkd1(" + mooId + ")'>结算</a></td>";
            }
            str += "</tr>";
        }

    }
    $("#mxList").html(str);
};



function offTall(){
    $("#divHide1").hide();
}

var checkStr="";
var check1;
function allBtn() {
    $("#all").click(function(){
        if(this.checked){
            $("table tbody :checkbox").each(function () {
                $(this).prop("checked", true);
                checkStr+=$(this).val()+",";
            })
        }else{
            $("table tbody :checkbox").prop("checked", false);
            checkStr="";
        }
    });
}
var count =  1;
function checkd(mooId) {
    if (checkStr.indexOf(mooId) >= 0) {
        checkStr = checkStr.replace(mooId + ',', '');
    } else {
        checkStr += mooId + ',';
    }
}

function checkd1(mooId) {
    checkStr=mooId;
    plJieSuan();
}
function plJieSuan(){
    var settlementMoney="";
    if (checkStr!=null&&checkStr!=undefined&&checkStr!=""){
        var vdata="&checkStr="+checkStr+"&settlementMoney="+settlementMoney;
        $.supper("doservice", {"service":"FinancialWorkbenchService.savePlJieSuan","data":vdata, "BackE":function (jsondata) {
                if (jsondata.code == "1") {
                    searchAll(1);
                    initDataAnalysis(value1)
                }else
                    $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
            }});
    }else{
        $.supper("alert",{ title:"操作提示", msg:"请选择操作数据！"});
    }
}

// function initDataAnalysis2() {
//     var dateInput3=$("#dateInput3").val();
//     var dateInput4=$("#dateInput4").val();
//     var vdata = "&dateInput3=" + dateInput3+"&dateInput4="+dateInput4;
//     $.supper("doservice", {
//         "service": "FinancialWorkbenchService.getDateMxlistString2",
//         data: vdata,
//         isAjax: "1",
//         "BackE": function (jsondata) {
//             var mxList = jsondata.rows;
//             var str1=[];
//             var str3=[];
//             if (mxList != null && mxList.length > 0) {
//                 for (var i = 0; i < mxList.length; i++) {
//                     str1.push(mxList[i].applicantName);
//                     str3.push(toDecimal2(mxList[i].placeOrderMoney/10000));
//                 }
//             }
//
//         }
//     });
// }

function initDataAnalysis1() {
    var dateInput3=$("#dateInput3").val();
    var dateInput4=$("#dateInput4").val();
    var vdata = "&dateInput3=" + dateInput3+"&dateInput4="+dateInput4;
    $.supper("doservice", {
        "service": "FinancialWorkbenchService.getDateMxlistString",
        data: vdata,
        isAjax: "1",
        "BackE": function (jsondata) {
            var mxList = jsondata.rows;
            var str = [];
            var st2=[];
            var st3=[];
            if (mxList != null && mxList.length > 0) {
                for (var i = 0; i < mxList.length; i++) {
                    str.push(mxList[i].applicantName);
                    st2.push(toDecimal2(mxList[i].placeOrderMoney/10000));
                    st3.push(toDecimal2(mxList[i].placeOrderMoney1/10000));
                }
            }
            myChart.setOption({
                xAxis: {
                    data: str
                },
                series: [
                    {
                        //根据名字对应到相应的系列
                       // name: str,
                        data: st2
                    },
                    {
                       // name:str1,
                        data:st3
                    }
                ]
            })
        }
    });
}

function ClaimantLeft2(moiId,pages) {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/main/FinancePageMx.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{moiId: moiId,pages:pages}});
    $.supper("showTtemWin",{ "url":att_url,"title":"结算对账明细"});
}
function exportFinanciaBranchMxList() {
    var orderCodeGJ=$("#orderCodeGJ").val();
    //交易时间
    var dateInput1=$("#dateInput1").val();
    var dateInput2=$("#dateInput2").val();

    var selectValue=$("#selectValue").val();
    var vdata="&orderCodeGJ="+orderCodeGJ+"&dateInput1="+dateInput1+"&dateInput2="+dateInput2+"&selectValue="+selectValue;
//导出财务对账
var newTab=window.open('about:blank');
//重新提交
$.supper("doservice", {"service":"FinancialWorkbenchService.exportFinanciaBranchMxList","data":vdata, "BackE":function (jsondata) {
        if (jsondata.code == "1") {
            newTab.location.href=jsondata.obj.path;
        }else
            $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
    }});
}
//导出供应商交易金额分析
function exportapplicName() {
    var dateInput3=$("#dateInput3").val();
    var dateInput4=$("#dateInput4").val();
    var vdata="&dateInput3="+dateInput3+"&dateInput4="+dateInput4;
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"FinancialWorkbenchService.exportapplicName","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}
//统计汇总
function totalMoney() {
    var selectValue1=$("#selectValue1").val();
    var moneyFw1=$("#moneyFw1").val();
    var moneyFw2=$("#moneyFw2").val();
    var gongYingS=$("#gongYingS").val();
    var caiGou=$("#caiGou").val();
    var vdata="&orderCodeGJ="+orderCodeGJ1+"&dateInput1="+dateInput5+"&dateInput2="+dateInput6+"&selectValue="+selectValue1+"&moneyFw1="+moneyFw1+"&moneyFw2="+moneyFw2+"&gongYingS="+gongYingS+"&caiGou="+caiGou;
    $.supper("doservice", {
        "service": "FinancialWorkbenchService.getTotalMoney",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            $("#total1").html(jsondata.obj.total);
            $("#money1").html(jsondata.obj.money2);
            $("#money2").html(jsondata.obj.money1);
        }
    });
}

//##################################

var cal;
var isFocus = false; // 是否为焦点
function SetDate(obj, strFormat)
{

    var date = new Date();
    var by = date.getFullYear() - 50; // 最小值 → 50年前
    var ey = date.getFullYear() + 50; // 最大值 → 20年后
// 初始化为中文版，1为英文版
    cal = (cal == null) ? new Calendar(by, ey, 0, strFormat)
        : (cal.dateFormatStyle == strFormat ? cal : new Calendar(by, ey, 0,
            strFormat));
    cal.show(obj);
}
/**//* 返回日期 */
String.prototype.toDate = function(style) {
    var y = this.substring(style.indexOf('y'), style.lastIndexOf('y') + 1);// 年
    var m = this.substring(style.indexOf('M'), style.lastIndexOf('M') + 1);// 月
    var d = this.substring(style.indexOf('d'), style.lastIndexOf('d') + 1);// 日
    var h = this.substring(style.indexOf('h'), style.lastIndexOf('h') + 1);// 时
    var i = this.substring(style.indexOf('m'), style.lastIndexOf('m') + 1);// 分
    var s = this.substring(style.indexOf('s'), style.lastIndexOf('s') + 1);// 秒
    if (isNaN(y))
        y = new Date().getFullYear();
    if (isNaN(m))
        m = new Date().getMonth();
    if (isNaN(d))
        d = new Date().getDate();
    if (isNaN(h))
        h = new Date().getHours();
    if (isNaN(i))
        i = new Date().getMinutes();
    if (isNaN(s))
        s = new Date().getSeconds();
    var dt;
    eval("dt = new Date('" + y + "', '" + (m - 1) + "','" + d + "','" + h
        + "','" + i + "','" + s + "')");
    return dt;
}
/**//* 格式化日期 */
Date.prototype.format = function(style) {
    var o = {
        "M+" : this.getMonth() + 1, // month
        "d+" : this.getDate(), // day
        "h+" : this.getHours(), // hour
        "m+" : this.getMinutes(), // minute
        "s+" : this.getSeconds(), // second
        "w+" : "天一二三四五六".charAt(this.getDay()), // week
        "q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
        "S" : this.getMilliseconds()
// millisecond
    }
    if (/(y+)/.test(style)) {
        style = style.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(style)) {
            style = style.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return style;
};

/**//*
* 日历类 @param beginYear 1990 @param endYear 2010 @param lang 0(中文)|1(英语)
* 可自由扩充 @param dateFormatStyle "yyyy-MM-dd";
*/
function Calendar(beginYear, endYear, lang, dateFormatStyle) {
    this.beginYear = 1990;
    this.endYear = 2010;
    this.lang = 0; // 0(中文) | 1(英文)
    this.dateFormatStyle = "yyyy-MM-dd";

    if (beginYear != null && endYear != null) {
        this.beginYear = beginYear;
        this.endYear = endYear;
    }
    if (lang != null) {
        this.lang = lang
    }

    if (dateFormatStyle != null) {
        this.dateFormatStyle = dateFormatStyle
    }

    this.dateControl = null;
    this.panel = this.getElementById("calendarPanel");
    this.container = this.getElementById("ContainerPanel");
    this.form = null;

    this.date = new Date();
    this.year = this.date.getFullYear();
    this.month = this.date.getMonth();

    this.colors = {
        "cur_word" : "#FFFFFF", // 当日日期文字颜色
        "cur_bg" : "#83A6F4", // 当日日期单元格背影色
        "sel_bg" : "#FFCCCC", // 已被选择的日期单元格背影色
        "sun_word" : "#FF0000", // 星期天文字颜色
        "sat_word" : "#0000FF", // 星期六文字颜色
        "td_word_light" : "#333333", // 单元格文字颜色
        "td_word_dark" : "#CCCCCC", // 单元格文字暗色
        "td_bg_out" : "#EFEFEF", // 单元格背影色
        "td_bg_over" : "#FFCC00", // 单元格背影色
        "tr_word" : "#FFFFFF", // 日历头文字颜色
        "tr_bg" : "#666666", // 日历头背影色
        "input_border" : "#CCCCCC", // input控件的边框颜色
        "input_bg" : "#EFEFEF" // input控件的背影色
    }

    this.draw();
    this.bindYear();
    this.bindMonth();
    this.changeSelect();
    this.bindData();
}

/**//*
* 日历类属性（语言包，可自由扩展）
*/
Calendar.language = {
    "year" : [ [ "" ], [ "" ] ],
    "months" : [
        [ "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月",
            "十一月", "十二月" ],
        [ "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP",
            "OCT", "NOV", "DEC" ] ],
    "weeks" : [ [ "日", "一", "二", "三", "四", "五", "六" ],
        [ "SUN", "MON", "TUR", "WED", "THU", "FRI", "SAT" ] ],
    "abort" : [ [ "时间" ], [ "TIME" ] ],
    "clear" : [ [ "清空" ], [ "CLS" ] ],
    "today" : [ [ "今天" ], [ "TODAY" ] ],
    "close" : [ [ "关闭" ], [ "CLOSE" ] ]
}

Calendar.prototype.draw = function() {
    calendar = this;

    var mvAry = [];
    mvAry[mvAry.length] = ' <div name="calendarForm" style="margin: 0px;">';
    mvAry[mvAry.length] = ' <table width="100%" border="0" cellpadding="0" cellspacing="1">';
    mvAry[mvAry.length] = ' <tr>';
    mvAry[mvAry.length] = ' <th align="left" width="1%"><input style="border: 1px solid '
        + calendar.colors["input_border"]
        + ';background-color:'
        + calendar.colors["input_bg"]
        + ';width:16px;height:20px;" name="prevMonth" type="button" id="prevMonth" value="<" /></th>';
    mvAry[mvAry.length] = ' <th align="center" width="98%" nowrap="nowrap"><select name="calendarYear" id="calendarYear" style="font-size:12px;"></select><select name="calendarMonth" id="calendarMonth" style="font-size:12px;"></select></th>';
    mvAry[mvAry.length] = ' <th align="right" width="1%"><input style="border: 1px solid '
        + calendar.colors["input_border"]
        + ';background-color:'
        + calendar.colors["input_bg"]
        + ';width:16px;height:20px;" name="nextMonth" type="button" id="nextMonth" value=">" /></th>';
    mvAry[mvAry.length] = ' </tr>';
    mvAry[mvAry.length] = ' </table>';
    mvAry[mvAry.length] = ' <table id="calendarTable" width="100%" style="border:0px solid #CCCCCC;background-color:#FFFFFF" border="0" cellpadding="3" cellspacing="1">';
    mvAry[mvAry.length] = ' <tr>';
    for ( var i = 0; i < 7; i++) {
        mvAry[mvAry.length] = ' <th style="font-weight:normal;background-color:'
            + calendar.colors["tr_bg"]
            + ';color:'
            + calendar.colors["tr_word"]
            + ';">'
            + Calendar.language["weeks"][this.lang][i] + '</th>';
    }
    mvAry[mvAry.length] = ' </tr>';
    for ( var i = 0; i < 6; i++) {
        mvAry[mvAry.length] = ' <tr align="center">';
        for ( var j = 0; j < 7; j++) {
            if (j == 0) {
                mvAry[mvAry.length] = ' <td style="cursor:default;color:' + calendar.colors["sun_word"] + ';"></td>';
            } else if (j == 6) {
                mvAry[mvAry.length] = ' <td style="cursor:default;color:' + calendar.colors["sat_word"] + ';"></td>';
            } else {
                mvAry[mvAry.length] = ' <td style="cursor:default;"></td>';
            }
        }
        mvAry[mvAry.length] = ' </tr>';
    }

    mvAry[mvAry.length] = ' <tr align="center" style="font-size:12px;">';
    mvAry[mvAry.length] = ' <td name="abort" id="abort" colspan="1" style="cursor:default;">' + Calendar.language["abort"][this.lang] + '</td>';
    mvAry[mvAry.length] = ' <td colspan="6"><select name="calendarHour" id="calendarHour"></select>';
    mvAry[mvAry.length] = ':<select name="calendarMinute" id="calendarMinute"></select>';
    mvAry[mvAry.length] = ':<select name="calendarSecond" id="calendarSecond"></select>';
    mvAry[mvAry.length] = ' </td></tr>';

    mvAry[mvAry.length] = ' <tr style="background-color:' + calendar.colors["input_bg"] + ';">';
    mvAry[mvAry.length] = ' <th colspan="2"><input name="calendarClear" type="button" id="calendarClear" value="'
        + Calendar.language["clear"][this.lang]
        + '" style="border: 1px solid '
        + calendar.colors["input_border"]
        + ';background-color:'
        + calendar.colors["input_bg"]
        + ';width:100%;height:20px;font-size:12px;"/></th>';
    mvAry[mvAry.length] = ' <th colspan="3"><input name="calendarToday" type="button" id="calendarToday" value="'
        + Calendar.language["today"][this.lang]
        + '" style="border: 1px solid '
        + calendar.colors["input_border"]
        + ';background-color:'
        + calendar.colors["input_bg"]
        + ';width:100%;height:20px;font-size:12px;"/></th>';
    mvAry[mvAry.length] = ' <th colspan="2"><input name="calendarClose" type="button" id="calendarClose" value="'
        + Calendar.language["close"][this.lang]
        + '" style="border: 1px solid '
        + calendar.colors["input_border"]
        + ';background-color:'
        + calendar.colors["input_bg"]
        + ';width:100%;height:20px;font-size:12px;"/></th>';
    mvAry[mvAry.length] = ' </tr>';
    mvAry[mvAry.length] = ' </table>';
    mvAry[mvAry.length] = ' </div>';
    this.panel.innerHTML = mvAry.join("");

    var obj = this.getElementById("prevMonth");
    obj.onclick = function() {
        calendar.goPrevMonth(calendar);
    }
    obj.onblur = function() {
        calendar.onblur();
    }
    this.prevMonth = obj;

    obj = this.getElementById("nextMonth");
    obj.onclick = function() {
        calendar.goNextMonth(calendar);
    }
    obj.onblur = function() {
        calendar.onblur();
    }
    this.nextMonth = obj;

    obj = this.getElementById("calendarClear");
    obj.onclick = function() {
        calendar.dateControl.value = "";
        calendar.hide();
    }
    this.calendarClear = obj;

    obj = this.getElementById("calendarClose");
    obj.onclick = function() {
        calendar.hide();
    }
    this.calendarClose = obj;

    obj = this.getElementById("calendarYear");
    obj.onchange = function() {
        calendar.update(calendar);
    }
    obj.onblur = function() {
        calendar.onblur();
    }
    this.calendarYear = obj;

    obj = this.getElementById("calendarMonth");
    with (obj) {
        onchange = function() {
            calendar.update(calendar);
        }
        onblur = function() {
            calendar.onblur();
        }
    }
    this.calendarMonth = obj;

    obj = this.getElementById("calendarHour");
    with (obj) {
        length = 0;
        for ( var i = 0; i < 24; i++) {
            if (i < 10) {
                options[length] = new Option("0" + i, "0" + i);
            } else {
                options[length] = new Option(i, i);
            }
        }
    }
    this.calendarHour = obj;

    obj = this.getElementById("calendarMinute");
    with (obj) {
        length = 0;
        for ( var i = 0; i < 60; i++) {
            if (i < 10) {
                options[length] = new Option("0" + i, "0" + i);
            } else {
                options[length] = new Option(i, i);
            }
        }
    }
    this.calendarMinute = obj;

    obj = this.getElementById("calendarSecond");
    with (obj) {
        length = 0;
        for ( var i = 0; i < 60; i++) {
            if (i < 10) {
                options[length] = new Option("0" + i, "0" + i);
            } else {
                options[length] = new Option(i, i);
            }
        }
    }
    this.calendarSecond = obj;

    obj = this.getElementById("calendarToday");
    obj.onclick = function() {
        var today = new Date();
        calendar.date = today;
        calendar.year = today.getFullYear();
        calendar.month = today.getMonth();
        calendar.changeSelect();
        calendar.bindData();
        calendar.dateControl.value = today.format(calendar.dateFormatStyle);
        calendar.hide();
    }
    this.calendarToday = obj;
}

// 年份下拉框绑定数据
Calendar.prototype.bindYear = function() {
    var cy = this.calendarYear;
    cy.length = 0;
    for ( var i = this.beginYear; i <= this.endYear; i++) {
        cy.options[cy.length] = new Option(i
            + Calendar.language["year"][this.lang], i);
    }
}

// 月份下拉框绑定数据
Calendar.prototype.bindMonth = function() {
    var cm = this.calendarMonth;
    cm.length = 0;
    for ( var i = 0; i < 12; i++) {
        cm.options[cm.length] = new Option(
            Calendar.language["months"][this.lang][i], i);
    }
}

// 获取小时的数据
Calendar.prototype.getHour = function() {
    return this.calendarHour.options[this.calendarHour.selectedIndex].value;
}

// 获取分钟的数据
Calendar.prototype.getMinute = function() {
    return this.calendarMinute.options[this.calendarMinute.selectedIndex].value;
}

// 获取秒的数据
Calendar.prototype.getSecond = function() {
    return this.calendarSecond.options[this.calendarSecond.selectedIndex].value;
}

// 向前一月
Calendar.prototype.goPrevMonth = function(e) {
    if (this.year == this.beginYear && this.month == 0) {
        return;
    }
    this.month--;
    if (this.month == -1) {
        this.year--;
        this.month = 11;
    }
    this.date = new Date(this.year, this.month, 1, this.getHour(), this
        .getMinute(), this.getSecond());
    this.changeSelect();
    this.bindData();
}

// 向后一月
Calendar.prototype.goNextMonth = function(e) {
    if (this.year == this.endYear && this.month == 11) {
        return;
    }
    this.month++;
    if (this.month == 12) {
        this.year++;
        this.month = 0;
    }
    this.date = new Date(this.year, this.month, 1, this.getHour(), this
        .getMinute(), this.getSecond());
    this.changeSelect();
    this.bindData();
}

// 改变SELECT选中状态
Calendar.prototype.changeSelect = function() {
    var cy = this.calendarYear;
    var cm = this.calendarMonth;
    var ch = this.calendarHour;
    var ci = this.calendarMinute;
    var cs = this.calendarSecond;
    for ( var i = 0; i < cy.length; i++) {
        if (cy.options[i].value == this.date.getFullYear()) {
            cy[i].selected = true;
            break;
        }
    }
    for ( var i = 0; i < cm.length; i++) {
        if (cm.options[i].value == this.date.getMonth()) {
            cm[i].selected = true;
            break;
        }
    }
    for ( var i = 0; i < ch.length; i++) {
        if (ch.options[i].value == this.date.getHours()) {
            ch[i].selected = true;
            break;
        }
    }
    for ( var i = 0; i < ci.length; i++) {
        if (ci.options[i].value == this.date.getMinutes()) {
            ci[i].selected = true;
            break;
        }
    }
    for ( var i = 0; i < cs.length; i++) {
        if (cs.options[i].value == this.date.getSeconds()) {
            cs[i].selected = true;
            break;
        }
    }
}

// 更新年、月
Calendar.prototype.update = function(e) {
    this.year = e.calendarYear.options[e.calendarYear.selectedIndex].value;
    this.month = e.calendarMonth.options[e.calendarMonth.selectedIndex].value;
    this.date = new Date(this.year, this.month, 1, this.getHour(), this
        .getMinute(), this.getSecond());
    this.changeSelect();
    this.bindData();
}

// 绑定数据到月视图
Calendar.prototype.bindData = function() {
    var calendar = this;
    var dateArray = this.getMonthViewArray(this.date.getFullYear(), this.date
        .getMonth());
    var tds = this.getElementById("calendarTable").getElementsByTagName("td");
    for ( var i = 0; i < tds.length; i++) {
        tds[i].style.backgroundColor = calendar.colors["td_bg_out"];
        tds[i].onclick = function() {
            return;
        }
        tds[i].onmouseover = function() {
            return;
        }
        tds[i].onmouseout = function() {
            return;
        }
        if (i > dateArray.length - 1)
            break;
        tds[i].innerHTML = dateArray[i];
        if (dateArray[i] != " ") {
            tds[i].onclick = function() {
                if (calendar.dateControl != null) {
                    calendar.dateControl.value = new Date(calendar.date
                            .getFullYear(), calendar.date.getMonth(),
                        this.innerHTML, calendar.getHour(), calendar
                            .getMinute(), calendar.getSecond())
                        .format(calendar.dateFormatStyle);
                }
                calendar.hide();
            }
            tds[i].onmouseover = function() {
                this.style.backgroundColor = calendar.colors["td_bg_over"];
            }
            tds[i].onmouseout = function() {
                this.style.backgroundColor = calendar.colors["td_bg_out"];
            }
            if (new Date().format("yyyy-MM-dd") == new Date(calendar.date
                .getFullYear(), calendar.date.getMonth(), dateArray[i])
                .format("yyyy-MM-dd")) {
                tds[i].style.backgroundColor = calendar.colors["cur_bg"];
                tds[i].onmouseover = function() {
                    this.style.backgroundColor = calendar.colors["td_bg_over"];
                }
                tds[i].onmouseout = function() {
                    this.style.backgroundColor = calendar.colors["cur_bg"];
                }
            }// end if

// 设置已被选择的日期单元格背影色
            if (calendar.dateControl != null
                && calendar.dateControl.value == new Date(calendar.date
                        .getFullYear(), calendar.date.getMonth(),
                    dateArray[i], calendar.getHour(), calendar
                        .getMinute(), calendar.getSecond())
                    .format(calendar.dateFormatStyle)) {
                tds[i].style.backgroundColor = calendar.colors["sel_bg"];
                tds[i].onmouseover = function() {
                    this.style.backgroundColor = calendar.colors["td_bg_over"];
                }
                tds[i].onmouseout = function() {
                    this.style.backgroundColor = calendar.colors["sel_bg"];
                }
            }
        }
    }
}

// 根据年、月得到月视图数据(数组形式)
Calendar.prototype.getMonthViewArray = function(y, m) {
    var mvArray = [];
    var dayOfFirstDay = new Date(y, m, 1).getDay();
    var daysOfMonth = new Date(y, m + 1, 0).getDate();
    for ( var i = 0; i < 42; i++) {
        mvArray[i] = " ";
    }
    for ( var i = 0; i < daysOfMonth; i++) {
        mvArray[i + dayOfFirstDay] = i + 1;
    }
    return mvArray;
}

// 扩展 document.getElementById(id) 多浏览器兼容性 from meizz tree source
Calendar.prototype.getElementById = function(id) {
    if (typeof (id) != "string" || id == "")
        return null;
    if (document.getElementById)
        return document.getElementById(id);
    if (document.all)
        return document.all(id);
    try {
        return eval(id);
    } catch (e) {
        return null;
    }
}

// 扩展 object.getElementsByTagName(tagName)
Calendar.prototype.getElementsByTagName = function(object, tagName) {
    if (document.getElementsByTagName)
        return document.getElementsByTagName(tagName);
    if (document.all)
        return document.all.tags(tagName);
}

// 取得HTML控件绝对位置
Calendar.prototype.getAbsPoint = function(e) {
    var x = e.offsetLeft;
    var y = e.offsetTop;
    while (e = e.offsetParent) {
        x += e.offsetLeft;
        y += e.offsetTop;
    }
    return {
        "x" : x,
        "y" : y
    };
}

// 显示日历
Calendar.prototype.show = function(dateObj, popControl) {
    if (dateObj == null) {
        throw new Error("arguments[0] is necessary")
    }
    this.dateControl = dateObj;

    this.date = (dateObj.value.length > 0) ? new Date(dateObj.value
        .toDate(this.dateFormatStyle)) : new Date();// 若为空则显示当前月份
    this.year = this.date.getFullYear();
    this.month = this.date.getMonth();
    this.changeSelect();
    this.bindData();
    if (popControl == null) {
        popControl = dateObj;
    }
    var xy = this.getAbsPoint(popControl);
    this.panel.style.left = xy.x - 25 + "px";
    this.panel.style.top = (xy.y + dateObj.offsetHeight) + "px";

    this.panel.style.display = "";
    this.container.style.display = "";

    dateObj.onblur = function() {
        calendar.onblur();
    }
    this.container.onmouseover = function() {
        isFocus = true;
    }
    this.container.onmouseout = function() {
        isFocus = false;
    }
}

// 隐藏日历
Calendar.prototype.hide = function() {
    this.panel.style.display = "none";
    this.container.style.display = "none";
    isFocus = false;
}

// 焦点转移时隐藏日历
Calendar.prototype.onblur = function() {
    if (!isFocus) {
        this.hide();
    }
}
document
    .write('<div id="ContainerPanel" style="display:none;"><div id="calendarPanel" style="position: absolute;display: none;z-index: 9999;');
document
    .write('background-color: #FFFFFF;border: 1px solid #CCCCCC;width:175px;font-size:12px;margin-left:25px;"></div>');
/**if (document.all) {
document
.write('<iframe style="position:absolute;z-index:2000;width:expression(this.previousSibling.offsetWidth);');
document.write('height:expression(this.previousSibling.offsetHeight);');
document
.write('left:expression(this.previousSibling.offsetLeft);top:expression(this.previousSibling.offsetTop);');
document
.write('display:expression(this.previousSibling.style.display);" scrolling="no" frameborder="no"></iframe>');
}*/
document.write('</div>');