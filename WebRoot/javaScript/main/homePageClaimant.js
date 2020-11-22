//仓管首页
$(function () {
    writeCurrentDate();
    initData();
    initCount();
    allBtn();
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
function initData() {
    var vdata = "";//&limit=" + 5 + "&page=" + 1
    $.supper('initPagination', {
        id: "Pagination1",
        service: "MdOutOrderService.sevenClaimant",
        data: vdata,
        limit:10,
        isAjax: "1",
        "BackE": initList
    });

}

function initList(jsondata) {
    var mxList = jsondata;
    var str = "";
    if (mxList != null && mxList.length > 0) {
        var mooId = "";
        var code = "";
        for (var i = 0; i < mxList.length; i++) {
            mooId = mxList[i].mooId;
            str += "<tr ondblclick='ClaimantLeft4("+mooId+",\"" +mxList[i].mooCode+ "\")' '><td style=\"text-align:center;font-size: 13px\"><input class='checkbox1' type=\"checkbox\" id=\"c1\" onclick='checkd(" + mooId + ")' value='" + mooId + "'/></td>";
            str += "<td style=\"text-align:center;font-size: 13px\">" + mxList[i].orderTime + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px\">" + mxList[i].mooCode + "</td>";
            if (mxList[i].flowState == "2") {
                str += "<td style=\"text-align:center;font-size: 13px\">" + "申领中" + "</td>";
            } else if (mxList[i].flowState == "3") {
                str += "<td style=\"text-align:center;font-size: 13px\">" + "部分出库" + "</td>";
            } else if (mxList[i].flowState == "4") {
                str += "<td style=\"text-align:center;font-size: 13px\">" + "已完成" + "</td>";
            } else if (mxList[i].flowState == "5") {
                str += "<td style=\"text-align:center;font-size: 13px\">" + "撤销" + "</td>";
            }
            str += "<td style=\"text-align:center;font-size: 13px\">" + mxList[i].number1 + "/" + mxList[i].number2 + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px\">" + (mxList[i].number1 - mxList[i].number2) + "</td>";
            if ((mxList[i].number1 > mxList[i].number2) && mxList[i].flowState != "4") {
                mooId = mxList[i].mooId;
                code = mxList[i].mooCode;
                flowState1 = mxList[i].flowState;
                str += "<td style=\"text-align:center;font-size: 13px\"><a onclick='initData1(" + mooId + ",\"" + code + "\",\"" + flowState1 + "\")' style='color: #46A3FF'>查看详情</a></td>";
            } else {
                str += "<td style=\"text-align:center;font-size: 13px\">" + " " + "</td>";
            }
            if (mxList[i].remarks!=null&&mxList[i].remarks!=undefined) {
                str += "<td style=\"text-align:center;font-size: 13px\">" + mxList[i].remarks + "</td>";
            }
            str += "</tr>";
        }

    }
    $("#mxList").html(str);
    /* }*/
};

var _all_win_url_edit = "/jsp/dentistmall/storage/editMdOutOrder.jsp";
var _all_win_url_view = "/jsp/dentistmall/storage/viewMdOutOrder.jsp";
var _all_table_Id = "mooId";
var _all_table_moo_code = "mooCode";
var _all_edit_icon = "th";
var _all_edit_title = "申领信息";
var _all_edit_width = 800;
var _all_edit_height = 500;

function ClaimantLeft1() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/picking.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{flowState: 2}});
    $.supper("showTtemWin",{ "url":att_url,"title":"待申领出库订单"});
}
function ClaimantTop2() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPicking.jsp"});
    $.supper("showTtemWin",{ "url":att_url,"title":"添加申领"});
}
function ClaimantTop3() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/picking.jsp"});
    $.supper("showTtemWin",{ "url":att_url,"title":"申领管理"});
}
function ClaimantLeft2() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/picking.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{flowState: 3}});
    $.supper("showTtemWin",{ "url":att_url,"title":"部分出库订单"});
}
function ClaimantLeft3() {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/picking.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{flowState: 4}});
    // $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{flowState: 4,flowState1: 5}});
    $.supper("showTtemWin",{ "url":att_url,"title":"已完成订单"});
}
function ClaimantLeft4(id,mooCode) {
        eval("var data= '"+_all_table_Id+"=" + id + "&mainview=1'");
        if(mooCode !== undefined && mooCode !== null){
            eval('var data = "' + _all_table_Id + '=' + id + '&' + _all_table_moo_code + '=' + mooCode +'&mainview=1"');
        }
        var att_url= $.supper("getServicePath", {"data":data,url:_all_win_url_edit});
        $.supper("setProductArray", {"name":"menuItemUrl", "value":att_url});
        var tt_win=$.supper("showTtemWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:function () {

            }});
    // }
    // var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/storage/editMdOutOrder.jsp"});
    // $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{flowState: 4}});
    // $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{flowState: 4,flowState1: 5}});
    // $.supper("showTtemWin",{ "url":att_url,"title":"已完成订单"});
}

//3个模块的统计
function initCount() {
    $.supper("doservice", {
        "service": "MdOutOrderService.ClaimPartialOut", "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#Claim").html(jsondata.obj.claim);
                $("#PartialOutgoing").html(jsondata.obj.partial);
                $("#CompleteOut").html(jsondata.obj.completeOut);
            }
        }
    });
}
function initData1(mooId,code,flowState1) {
    $("#outCode").html(code);
    if (flowState1=="2"){
        $("#flowState1").html("申领中");
    }else if(flowState1=="3"){
        $("#flowState1").html("部分出库");
    }else if(flowState1=="5"){
        $("#flowState1").html("撤销");
    }
    $("#ck").show();
    var vdata="&mooId="+mooId;//+"&limit=" + 5 + "&page=" + 1
    $.supper('initPagination', {
        id: "Pagination2",
        service: "MdOutOrderService.sevenOutMx",
        data: vdata,
        limit:5,
        isAjax: "1",
        "BackE": ckxq1
    });

}
function ckxq1(jsondata) {
     var mxList = jsondata;
            var str = "";
            if(mxList != null && mxList.length > 0){
                for(var i =0;i < mxList.length;i++) {
                      // if (mxList[i].lackNumber>mxList[i].baseNumber||mxList[i].lackNumber==mxList[i].baseNumber) {
                        str +="<tr>"
                        str += "<td style=\"text-align:center;font-size: 13px\">" + mxList[i].matName + "</td>";
                        str += "<td style=\"text-align:center;font-size: 13px\">" + mxList[i].norm + "</td>";
                        str += "<td style=\"text-align:center;font-size: 13px\">" + mxList[i].baseNumber + "/" + mxList[i].lackNumber + "</td>";
                        str += "</tr>";
                      // }
                }
            }
        $("#mxList1").html(str);
    }
//         // });
// }
// function ckxq1(mooId,code,flowState1,) {
//     $("#outCode").html(code);
//     if (flowState1=="2"){
//         $("#flowState1").html("申领中");
//     }else if(flowState1=="3"){
//         $("#flowState1").html("部分出库");
//     }else if(flowState1=="5"){
//         $("#flowState1").html("撤销");
//     }
//     $("#ck").show();
//     var vdata="&mooId="+mooId;
//     $.supper("doservice", {"service":"MdOutOrderService.sevenOutMx",data:vdata, isAjax:"1", "BackE": function (jsondata) {
//             var mxList = jsondata.rows;
//             var str = "";
//             if(mxList != null && mxList.length > 0){
//                 for(var i =0;i < mxList.length;i++) {
//                     if (mxList[i].lackNumber>mxList[i].baseNumber||mxList[i].lackNumber==mxList[i].baseNumber) {
//                         str +="<tr>"
//                         str += "<td style=\"text-align:center\">" + mxList[i].matName + "</td>";
//                         str += "<td style=\"text-align:center\">" + mxList[i].norm + "</td>";
//                         str += "<td style=\"text-align:center\">" + mxList[i].baseNumber + "/" + mxList[i].lackNumber + "</td>";
//                         str += "</tr>";
//                     }
//                 }
//                 $("#mxList1").html(str);
//             }
//
//         }
//     });
// }
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
function close1() {
    $("#ck").hide();
}
var count =  1;
function checkd(mooId) {
    if (checkStr.indexOf(mooId) >= 0) {
        checkStr = checkStr.replace(mooId + ',', '');
    } else {
        checkStr += mooId + ',';
    }
    // if(count%2!=0){
    //     checkStr+=mooId+",";
    //     count++;
    // }else if (count%2==0){
    //     checkStr="";
    //     count++;
    // }
    // if(count%2!=0){
    //     checkStr+=mooId+",";
    //     count++;
    // }
    // if(count%2==0){
    //     checkStr="";
    //     count++;
    // }
    // alert(偶数次+checkStr);
}
function yiRead(){
    if (checkStr!=null&&checkStr!=undefined&&checkStr!=""){
        var vdata="checkStr="+checkStr;
    //$('#loading1').show();
    $.supper("doservice", {"service":"MdOutOrderService.saveRead","data":vdata, "BackE":function (jsondata) {
        if (jsondata.code == "1") {
                initData();
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
    }else{
        alert("请选择操作数据");
    }
}