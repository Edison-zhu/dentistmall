$(function () {
    // laydate({
    //     elem: '#operationDate',
    //     format: 'YYYY-MM-DD',//日期格式
    //     type:'datetime'
    // });

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#rangeDate'
            // ,type: 'year'
            , type: 'datetime'
            , range: "~" //或 range: '~' 来自定义分割字符
            , position: 'static'
            , done: function (value, date) {
                $('#operationDate').val(value);
                $('#rangeDateDiv').hide();
            }
        });
    });

    searchAll(0);
    searchAll1(0);
});
var desc="";
 var count= {};
 var aCount = 2;

function a(a) {
    if (aCount == 1) {
        desc = a;
        aCount = 2;
    } else if (aCount == 2) {
        desc = a + 10;
        aCount = 1;
    }

    searchAll(1);
}
//2020年07月10日13:40:01修改时分秒
function closeSelect() {
    $('#rangeDateDiv').hide();
}
function selectDate(dateIndex) {
    let date = new Date();
    let renderDate = formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    let range = false;
    switch (dateIndex) {
        case 1:
            date.setDate(date.getDate() - 1);
            range = true;
            break;
        case 2:
            date.setDate(date.getDate() - 2);
            range = true;
            break;
        case 3:
            date.setDate(date.getDate() - 6);
            range = true;
            break;
        case 4:
            date.setDate(date.getDate() - 13);
            range = true;
            break;
        case 5:
            date.setDate(date.getDate() - 20);
            range = true;
            break;
        case 6:
            date.setDate(date.getDate() - 29);
            range = true;
            break;
        case 7:
            date.setDate(date.getDate() - 59);
            range = true;
            break;
    }
    if (range == true) {
        renderDate = formatDate(date,'yyyy-MM-dd hh:mm:ss') + '~' + renderDate;
    }
    $('#operationDate').val(renderDate);
    $('#rangeDateDiv').hide();
}
function selectRangeDate() {
     $('#rangeDateDiv').show();
}
function formatDate(date, fmt) {
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
//================================修改结束
function seachEnter() {
    if (event.keyCode == 13) {
        searchAll(1);
    }
}

function searchAll(i) {
        if (i==0) {
            var vdata='';
        }else if (i==1){
            var warehousCode=$("#warehousCode").val();
            var select1=$("#select1").val();
            var  remarks=$("#remarks").val();
            //var  billCode=$("#billCode").val();
            var operationDate=$("#operationDate").val();
            var vdata="&warehousCode="+warehousCode+"&select1="+select1+"&remarks="+remarks+"&billCode="+"&operationDate="+operationDate+"&desc="+desc;
        }
        searchAll1(i);
        $.supper('initPagination', {
        id: "Pagination1",
        service: "ModifypriceService.getWarehousingList",
        data: vdata,
        limit:10,
        isAjax: "1",
        "BackE":initList
    });
}
var wewIds='';
function initList(jsondata) {
    var mxList = jsondata;
    var str="";
    wewIds='';
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            wewIds+=mxList[i].wewId+',';
            str += "<tr>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input class='checkbox' name='items' type=\"checkbox\" onclick='checkd(this," + mxList[i].wewId + ")'></td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].Billcode+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].billType+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].warehousingRemarks+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].purchaseMoney+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].retailMoney+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].createRen+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].createDate1+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].invoiceCode+ "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 1.5px solid #F0F0F0;\"><a style=\"color: #46A3FF;text-decoration: underline;\" onclick='viewInfo(" + mxList[i].wewId + ")'>详情</a>&nbsp;&nbsp;<a style=\"color: #46A3FF;text-decoration: underline;\" onclick='editInfo(" + mxList[i].wewId + ")'>编辑</a>&nbsp;&nbsp;<a style=\"color: #46A3FF;text-decoration: underline;\" onclick='deleteWare(" + mxList[i].wewId + ")'>删除</a></td>";
            str += "</tr>";
        }
    }
    $("#mxList").html(str);
}
function searchAll1(i) {
    if (i==0) {
        var limit="";
        var page="";
        var vdata="&limit="+limit+"&page="+page;
    }
    else if (i==1){
        var warehousCode=$("#warehousCode").val();
        var select1=$("#select1").val();
        var  remarks=$("#remarks").val();
        //var  billCode=$("#billCode").val();
        var operationDate=$("#operationDate").val();
        var limit="";
        var page="";
        var vdata="&warehousCode="+warehousCode+"&select1="+select1+"&remarks="+remarks+"&billCode="+"&operationDate="+operationDate+"&limit="+limit+"&page="+page;
    }
    $.supper('doservice', {
        service: "ModifypriceService.getWarehousingList",
        data: vdata,
        isAjax: "1",
        "BackE":initList1
    });
}

function initList1(jsondata) {
    var mxList = jsondata.rows;
    var str="";
    var sumPurchaseMoney=0.0;
    var sumretailMoney = 0.0;
    var countSize = 0;
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            sumPurchaseMoney = (CheckUtil.isDigit(mxList[i].purchaseMoney) == false ? 0 : Number(mxList[i].purchaseMoney)) + sumPurchaseMoney;
            sumretailMoney = (CheckUtil.isDigit(mxList[i].retailMoney) == false ? 0 : Number(mxList[i].retailMoney)) + sumretailMoney;
            countSize++;
        }
    }
    // num.toFixed(2);2020年07月08日08:43:24朱燕冰修改
    $("#c1").html(countSize);
    $("#c2").html(sumPurchaseMoney.toFixed(2));
    $("#c3").html(sumretailMoney.toFixed(2));
}
var view_url = '/jsp/dentistmall/modifyprice/warehousingmx.jsp';
function viewInfo(wewId) {
    let att_title = "入库详情";
    let att_userType = "1";
    var att_url = $.supper("getServicePath", {url: view_url});
    $.supper("setProductArray", {"name": "enterInfo", "value": {wewId: wewId, url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}
function editInfo(wewId) {
    let att_title = "入库编辑";
    let att_userType = "1";
    let att_url = $.supper("getServicePath", {url: view_url});
    $.supper("setProductArray", {"name": "enterInfo", "value": {wewId: wewId, edit: true, url: att_url}});
    $.supper("showTtemWin", {"url": att_url, "title": att_title});
}
function addNewEnter() {
    let att_title = "新增入库";
    let att_userType = "1";
    let att_url = $.supper("getServicePath", {url: '/dentistmall/jsp/dentistmall/modifyprice/addWarehousing.jsp'});
    $.supper("setProductArray", {"name": "addEnter", "value": {url: '/dentistmall/jsp/dentistmall/modifyprice/addWarehousing.jsp', isNew: true}});
    $.supper("showTtemWin", {"url": '/dentistmall/jsp/dentistmall/modifyprice/addWarehousing.jsp', "title": att_title});
}
function deleteWare(wewId){
    layer.alert('确认删除后，此入库单下所有商品数量恢复未入库，并减少对应的库存数量！',{
        icon: 1,
        skin: 'layer-ext-moon',
        //closeBtn: 2,
        btn: ['确定','取消'] //按钮
    },
    function(index){
        layer.close(index);
            var vdata='wewId='+wewId;
            $.supper("doservice", {"service":"ModifypriceService.deleteWare","data":vdata, "BackE":function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert",{ title:"操作提示", msg:"操作成功！"});
                        searchAll(0);
                    }else {
                        $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
                    }
                }
            });
        }
    )

}

// $("#all").click(function() {
//     $("[name=items]:checkbox").prop("checked", this.checked);
// });
// $("[name=items]:checkbox").click(function() {
//     var flag = true;
//     $("this").each(function() {
//         if (!this.checked) {
//             flag = false;
//         }
//     });
//     $("#all").prop("checked", flag);
// });
var wewId1='';
function exportItem(selector,id){
    if(selector.checked === true){
        $("[name=items]:checkbox").prop("checked", true);
        wewId1=wewIds;
    }else {
        $("[name=items]:checkbox").prop("checked", false);
        wewId1='';
    }
}
function checkd(selector1,wewId){
    if (wewId1.indexOf(wewId) >= 0) {
        wewId1 = wewId1.replace(wewId + ',', '');
    } else {
        wewId1 += wewId + ',';
    }
}
//导出入库管理excel 
function export1() {
    // alert(wewId1);
    if ($('#mxList').find('tr').length <= 0) {
        $.supper("alert",{ title:"操作提示", msg:"无数据，无法下载表格！"});
        return;
    }
    var warehousCode=$("#warehousCode").val();
    var select1=$("#select1").val();
    var  remarks=$("#remarks").val();
    //var  billCode=$("#billCode").val();
    var operationDate=$("#operationDate").val();
    if (wewId1==''){
        $.supper("confirm",{ title:"操作提示", msg:"您未选择要导出的数据，系统将所有数据导出是否确定导出？" ,yesE:function(){
                var vdata="&warehousCode="+warehousCode+"&select1="+select1+"&remarks="+remarks+"&billCode="+"&operationDate="+operationDate+"&wewId1="+wewId1;
                //先判断是否有数据
                $.supper("doservice", {"service":"ModifypriceService.getWarehousingList","data":vdata + '&limit=1&page=1', "BackE":function (jsondata) {
                        if (jsondata.items.length > 0) {
                            var newTab=window.open('about:blank');
                            //重新提交
                            $.supper("doservice", {"service":"EnterWarehouseExportService.exportWarehousingInfoList","data":vdata, "BackE":function (jsondata) {
                                    if (jsondata.code == "1") {
                                        newTab.location.href=jsondata.obj.path;
                                    }else if (jsondata.code == '2') {
                                        $.supper("alert",{ title:"操作提示", msg:"无数据，无法下载表格！"});
                                    }else
                                        $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
                                }});
                        }else {
                            $.supper("alert",{ title:"操作提示", msg:"无数据，无法下载表格！"});
                        }
                }});
            }
        })
        return;
    }
    // if (wewId1!=null&&wewId1!=''&&wewId1!=undefined) {
    //     wewId1=wewId1.substring(0,wewId1.length-1);
    // }
    var vdata="&warehousCode="+warehousCode+"&select1="+select1+"&remarks="+remarks+"&billCode="+"&operationDate="+operationDate+"&wewId1="+wewId1;
//先判断是否有数据
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"EnterWarehouseExportService.exportWarehousingInfoList","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else if (jsondata.code == '2') {
                $.supper("alert",{ title:"操作提示", msg:"无数据，无法下载表格！"});
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
            newTab.location.href=jsondata.obj.path;
    }
    });
    //         $.supper("doservice", {"service":"ModifypriceService.getWarehousingList","data":vdata + '&limit=1&page=1', "BackE":function (jsondata) {
    //         if (jsondata.items.length > 0) {
    //             var newTab=window.open('about:blank');
    //             //重新提交
    //             $.supper("doservice", {"service":"EnterWarehouseExportService.exportWarehousingInfoList","data":vdata, "BackE":function (jsondata) {
    //                     newTab.location.href=jsondata.obj.path;
    //                     alert(jsondata.code);
    //                 // if (jsondata.code == "1") {
    //                 //         newTab.location.href=jsondata.obj.path;
    //                 //     }else if (jsondata.code == '2') {
    //                 //         $.supper("alert",{ title:"操作提示", msg:"无数据，无法下载表格！"});
    //                 //     }else
    //                 //         $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
    //                 }});
    //         }else {
    //             $.supper("alert",{ title:"操作提示", msg:"无数据，无法下载表格！"});
    //         }
    //     }});
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
        $("body").css("width",bodyWidth);
    }
);


