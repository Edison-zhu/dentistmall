//仓管首页
$(function () {
    initCount();
    writeCurrentDate();
    initDataAnalysis(0);
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

function initDataAnalysis(value) {
    if (value==0) {
        $("#btn1").addClass("a1");//.removeAttr("style");
        $("#btn2").removeClass("a1");
        $("#btn3").removeClass("a1");
        $("#btn4").removeClass("a1");
    }
    if (value==1) {
        $("#btn2").addClass("a1");//.removeAttr("style");
        $("#btn1").removeClass("a1");
        $("#btn3").removeClass("a1");
        $("#btn4").removeClass("a1");
    }
    if (value==2) {
        $("#btn3").addClass("a1");//.removeAttr("style");
        $("#btn2").removeClass("a1");
        $("#btn1").removeClass("a1");
        $("#btn4").removeClass("a1");
    }
    if (value==3) {
        $("#btn4").addClass("a1");//.removeAttr("style");
        $("#btn2").removeClass("a1");
        $("#btn3").removeClass("a1");
        $("#btn1").removeClass("a1");
    }
    var vdata = "&value=" + value;
    $.supper("doservice", {
        "service": "MdOutOrderService.departmentStatisticalReport",
        data: vdata,
        isAjax: "1",
        "BackE": function (jsondata) {
            var mxList = jsondata.rows;
            var str = [];
            var st2=[];
            if (mxList != null && mxList.length > 0) {
                for (var i = 0; i < mxList.length; i++) {
                    str.push(mxList[i].userName);
                     st2.push(mxList[i].count);
                }
            }
            myChart.setOption({
                xAxis: {
                    data: str
                },
                series: [
                    {
                        //根据名字对应到相应的系列
                        //name: str,
                        data: st2
                    }
                ]
            })
        }
    });
}

var jumpMooIds = '';
//5个模块的统计
function initCount() {
    $.supper("doservice", {
        "service": "MdOutOrderService.countDepartmentReport", "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#OutStock").html(jsondata.obj.outStock);
                $("#WarehousedOrder").html(jsondata.obj.warehouse);
                $("#ReturnStock").html(jsondata.obj.returnStock);
                $("#RetreatPlaceOrderMoney").html(jsondata.obj.stockAlarm);
                if (jsondata.obj.safetyStockAlarm != undefined) {
                    let alarm = jsondata.obj.safetyStockAlarm;
                    $("#CumulativeWarning").html(alarm.LackNumber == undefined ? 0 : alarm.LackNumber);
                    jumpMooIds = alarm.mooIds == undefined ? '' : alarm.mooIds;
                } else {
                    $("#CumulativeWarning").html(0);
                }

            }
        }
    });
}
//导出申领出库预警
function exportCumulativeWarning(){
    var vdata="";
    var newTab=window.open('about:blank');
//重新提交
    $.supper("doservice", {"service":"MdOutOrderService.exportCumulativeWarning","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}
//快捷方式的超链接部分 **********start*************
function leftA1() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/storage/applyMdOutWarehouserList.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{flowState: 2}});
    $.supper("showTtemWin",{ "url":att_url,"title":"申领出库"});
}
function leftA2() {
    // var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/transaction/ckOrderInfoList.jsp"});
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/modifyprice/addWarehousing.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{radio: 1}});
    $.supper("showTtemWin",{ "url":att_url,"title":"订单待入库"});
}
function leftA3() {
    // var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/storage/mdOutWarehouserList.jsp"});
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/storage/applyMdOutWarehouserList.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{wowType: 2}});
    $.supper("showTtemWin",{ "url":att_url,"title":"退货出库"});
}
function leftA4() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/storage/applyMdOutWarehouserList.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{warningMoo: true, mooIds: jumpMooIds}});
    $.supper("showTtemWin",{ "url":att_url,"title":"申领库存告警"});
}
function leftA5() {
    // var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/storage/inventoryList.jsp"});
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/storage/viewInventory.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{warning: true}});
    $.supper("showTtemWin",{ "url":att_url,"title":"安全库存警报"});
}