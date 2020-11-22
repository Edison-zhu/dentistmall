
$(function () {
    writeCurrentDate();
    //日期格式
    laydate({
        elem: '#dateInput1',
        format: 'YYYY-MM-DD'//日期格式
    });
    laydate({
        elem: '#dateInput2',
        format: 'YYYY-MM-DD' //日期格式
    });
     searchAll();
    initCountFeedBack();
});

//获取当前时间
var time1="";
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
    time1=year+"-"+month+"-"+date
    //设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。
    var timer = setTimeout("writeCurrentDate()", 1000);
}
function searchAll() {
        var input1=$("#input1").val();
        var  selectType=$("#selectType").val();
        var  questionType=$("#questionType").val();
        var  state=$("#state").val();
        var  dateInput1=$("#dateInput1").val();
        var  dateInput2=$("#dateInput2").val();
        var vdata="&input1="+input1+"&selectType="+selectType+"&questionType="+questionType+"&state="+state+"&dateInput1="+dateInput1+"&dateInput2="+dateInput2;
        $.supper('initPagination', {
        id: "Pagination1",
        service: "SysFeedBackService.getSysFeedBackList",
        data: vdata,
        limit:10,
        isAjax: "1",
        "BackE":initList
    });

}
function initList(jsondata) {
    var mxList = jsondata;
    var str="";
    var start1="";
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            start1=mxList[i].state;
            str += "<tr>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].fbId+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].createDate1+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].questiontype+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].type+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].state+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].fbValue+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].processingLog+ "</td>";
           if (start1!=null&&start1!=undefined) {
               if (start1=="已处理"){
                   str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF; \" onclick='feedbackMx(" + mxList[i].fbId + ")'>详情</a></td>";
               }else {
                   str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF; \" onclick='feedback1("+mxList[i].fbId+")'>处理</a>&nbsp;&nbsp;<a style=\"color: #46A3FF; \" onclick='feedbackMx("+mxList[i].fbId+")'>详情</a></td>";
               }
           }else {
               str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF; \" onclick='feedback1("+mxList[i].fbId+")'>处理</a>&nbsp;&nbsp;<a style=\"color: #46A3FF; \" onclick='feedbackMx("+mxList[i].fbId+")'>详情</a></td>";
           }

            str += "</tr>";
        }
    }
    $("#mxList").html(str);
}
// function add() {
//     var vdata='';
//     $.supper("doservice", {"service":"BackSqlService.addSysBackDatabase","data":vdata, "BackE":function (jsondata) {
//             if (jsondata.code == "1") {
//                 $.supper("alert",{ title:"操作提示", msg:"备份成功！"});
//             }else
//                 $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
//         }});
// }

//商品总览
function initCountFeedBack(){
    $.supper("doservice", {"service" : "SysFeedBackService.countFeedBack","BackE" : function(jsondata) {
            if (jsondata.code == "1") {
                $("#countFeedBack").html(jsondata.obj.countFeedBack);
            }
        }
    });
}
//编号明细
var eqwww;
function feedbackMx(fbId){
        $("#hideFeedBack").show();
        $("#fildSpan").html(fbId);
        var vdata='fbId='+fbId;
    $.supper("doservice", {"service":"SysFeedBackService.getSysFeedBackListMx","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1"){
                if (jsondata.obj.feedState!="已处理"){
                    $("#dv7").show();
                }
                eqwww=jsondata.obj.feedId1;
                $("#feedId1").html(jsondata.obj.feedId1);
                $("#feedDate").html(jsondata.obj.feedDate);
                $("#feedType").html(jsondata.obj.feedType);
                $("#feedUserName").html(jsondata.obj.feedUserName);
                $("#feedNodeName").html(jsondata.obj.feedNodeName);
                $("#feedPhone").html(jsondata.obj.feedPhone);
                $("#questionType1").html(jsondata.obj.questionType1);
                $("#feedState").html(jsondata.obj.feedState);
                $("#fbValue").html(jsondata.obj.fbValue);
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});



}
function feedback1(fbId) {
    if (fbId==null||fbId==""){
        fbId=eqwww;
    }
    var vdata='fbId='+fbId;
    $.supper("doservice", {"service":"SysFeedBackService.saveUpdateFeedBack","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1"){
                searchAll();
                $("#dv7").hide();
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}
//关闭隐藏页面
function showFeedBackTop() {
    $("#hideFeedBack").hide();
}