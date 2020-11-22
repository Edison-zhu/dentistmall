var _all_accountForm = $("#accountForm");
var _wmsMiIds = [];
var _mmfIds = [];
var _enterData = {};
var _editData = {};
var enterType = 0;

var wewId;
var edit;
var url;

var findAction = 'MdEnterWarehouseService.findFormObject';

$(function () {
    var checkInventory = $.supper("getProductArray", "enterInfo");
    if (checkInventory != null) {
        wewId = checkInventory.wewId;
        edit = checkInventory.edit;
        url = checkInventory.url;
    }

    if (wewId != undefined) {
        $.supper("doservice", {
            "service": findAction, "data": 'wewId=' + wewId, "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    let obj = jsondata.obj;
                    $('#billCode').val(obj.billCode);
                    if (obj.billType == 1) {
                        $('#orderWarehous2').attr('checked', true)
                        $('#mx1').hide();
                        $('#mx2').show();
                        searchMatMx();
                        enterType = 2;
                    } else {
                        $('#orderWarehous').attr('checked', true);
                        $('#mx1').show();
                        $('#mx2').hide();
                        searchAllMX();
                        enterType = 1;
                    }
                    $('#billCode').val(obj.billcode);
                    $('#warehousingRemarks').val(obj.warehousingRemarks);
                    $('#invoiceCode').val(obj.invoiceCode);
                    $('#createRen').text(obj.createRen);
                    $('#createDate').text(obj.createDate);
                }
            }
        });
        if (edit == undefined) {
            $('input').attr('readonly', 'readonly');
            document.getElementById("savebtnhaha").style.display = "none";
        }
    }

    laydate({
        elem: '#cgDate',
        format: 'YYYY-MM-DD' //日期格式
    });
    // for (var i = 0; i <mycars.length ; i++) {
    //     var momIds=mycars[i];
    //     laydate({
    //         elem: '#xzwl'+momIds,
    //         format: 'YYYY-MM-DD' //日期格式
    //     });
    // }
    // getNewCode();

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

// function searchAll1(i) {
//     if (i==0) {
//         var limit="";
//         var page="";
//         var vdata="&limit="+limit+"&page="+page;
//     }
//     else if (i==1){
//         var warehousCode=$("#warehousCode").val();
//         var select1=$("#select1").val();
//         var  remarks=$("#remarks").val();
//         //var  billCode=$("#billCode").val();
//         var operationDate=$("#operationDate").val();
//         var limit="";
//         var page="";
//         var vdata="&warehousCode="+warehousCode+"&select1="+select1+"&remarks="+remarks+"&billCode="+"&operationDate="+operationDate+"&limit="+limit+"&page="+page;
//     }
//     $.supper('doservice', {
//         service: "ModifypriceService.getWarehousingList",
//         data: vdata,
//         isAjax: "1",
//         "BackE":initList1
//     });
// }
//
// function initList1(jsondata) {
//     var mxList = jsondata.rows;
//     var str="";
//     var sumPurchaseMoney=0.0;
//     var sumretailMoney=0.0;
//     var countSize=0;
//     if (mxList != null && mxList.length > 0) {
//         for (var i = 0; i < mxList.length; i++) {
//             sumPurchaseMoney=mxList[i].purchaseMoney+sumPurchaseMoney;
//             sumretailMoney=mxList[i].retailMoney+sumretailMoney;
//             countSize++;
//         }
//     }
//     $("#c1").html(countSize);
//     $("#c2").html(sumPurchaseMoney);
//     $("#c3").html(sumretailMoney);
// }
// function deleteWare(wewId){
//     layer.alert('确认删除后则此入库单下所有商品数量恢复未入库，并减少对应的库存数量!',{
//         icon: 1,
//         skin: 'layer-ext-moon'
//     },
//     function(index){
//         layer.close(index);
//             var vdata='wewId='+wewId;
//             $.supper("doservice", {"service":"ModifypriceService.deleteWare","data":vdata, "BackE":function (jsondata) {
//                     if (jsondata.code == "1") {
//                         $.supper("alert",{ title:"操作提示", msg:"操作成功！"});
//                         searchAll(0);
//                     }else {
//                         $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
//                     }
//                 }
//             });
//         }
//     )
// }

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

// function radio1() {
//     enterType = 1;
//     _enterData = {};
//     $("#hideDiv").show();
//     $("#mx1").hide();
//     $('#mx2').hide();
//     main_add(1);
//     $('table').on('click', 'tr', function (e) {
//         // 获取table中所有的行
//         var trs = $(this).parent('tbody').find('tr');
//         // 取消所有行的选中
//         $.each(trs, function (index, item) {
//             // 如果在此使用的是attr()设置选中状态,取消后将不能设置为选中
//             $(item).find('td:eq(0) input').prop('checked', false);
//         });
//         // 选中当前点击的行
//         $(this).find('td:eq(0) input').prop('checked', true)
//     })
//     $("#orderWarehous2").attr("checked", false);
// }
//
// function radio2() {
//     enterType = 2;
//     _enterData = {};
//     $("#hideDiv").hide();
//     $("#orderWarehous").attr("checked", false);
//     $('#mx1').hide();
//     $('#mx2').show();
// }

function quXiao() {
    $("#hideDiv").hide();
    $("#orderWarehous").attr("checked", false);
    $("#orderWarehous2").attr("checked", false);
}

function main_add(i) {
    if (wewId != undefined) {
        $.supper('initPagination', {
            id: "Pagination2",
            service: "ModifypriceService.getPagerModelEnterOrder",
            data: 'wewId=' + wewId,
            limit: 5,
            isAjax: "1",
            "BackE": initListAdd
        });
    }
}

var moiId1 = "";

function choice(moiId) {
    moiId1 = moiId;
}

function queD() {
    $("#hideDiv").hide();
    $("#mx1").show();
    if (moiId1 != "") {
        searchAllMX(1, moiId1);
    } else {
        searchAllMX(1, 0);
    }

}

function searchAllMX(i, moiId) {
    let queryAction = "MdOrderInfoService.getPagerModelEnterOrder";
    //"ModifypriceService.getAddgetWarehousingMx",
    // searchAll1(i);
    if (wewId != undefined) {
        $.supper('initPagination', {
            id: "Pagination1",
            service: "mdEnterWarehouseMxService.getMdEnterWarehouseMxListByMoiId",
            data: 'wewId=' + wewId,
            limit: 10,
            isAjax: "1",
            "BackE": initListMx
        });
    }
}

function searchMatMx(wmsMiId, mmfId) {
    // let data = '';
    // if (_wmsMiIds.length > 0) {
    //     data += 'wmsMiIds=' + _wmsMiIds.join(',');
    // }
    // if (_mmfIds.length > 0) {
    //     data += '&mmfIds=' + _mmfIds.join(',');
    // }
    if (wewId != undefined) {
        $.supper('initPagination', {
            id: "Pagination1",
            service: "mdEnterWarehouseMxService.getMdEnterWarehouseMxListByMoiId",
            data: 'wewId=' + wewId,
            limit: 10,
            isAjax: "1",
            "BackE": initMatListMx
        });
    }
}

var mycars = new Array();

function initListMx(jsondata) {
    // console.log(JSON.stringify(jsondata))
    var mxList = jsondata;
    var momId1;
    let mmfId;
    let wewMxId;
    var str = "";
    var productNameInt;
    let ratio;
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            momId1 = mxList[i].momId;
            mmfId = mxList[i].mmfId;
            wewMxId = mxList[i].wewMxId;
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
            if (_enterData[wewMxId] == undefined) {
                _enterData[wewMxId] = {};
                _enterData[wewMxId].mmfId = mxList[i].mmfId;
                _enterData[wewMxId].wewMxId = mxList[i].wewMxId == undefined ? '' : mxList[i].wewMxId;
                _enterData[wewMxId].momId = mxList[i].momId == undefined ? '' : mxList[i].momId;
                _enterData[wewMxId].enterNumber = mxList[i].enterNumber == undefined ? 0 : mxList[i].enterNumber;
                _enterData[wewMxId].matNumber = mxList[i].matNumber == undefined ? 0 : mxList[i].matNumber;
                _enterData[wewMxId].unitMoney = mxList[i].price == undefined ? 0 : mxList[i].price;
                _enterData[wewMxId].baseNumber = mxList[i].matNumber == undefined ? '' : mxList[i].matNumber;
                _enterData[wewMxId].ratio = mxList[i].basicCoefficent == undefined ? 1 : mxList[i].basicCoefficent;
                _enterData[wewMxId].splitNumber = mxList[i].splitQuantity == undefined ? '' : mxList[i].splitQuantity;
                _enterData[wewMxId].unit = mxList[i].unit == undefined ? '' : mxList[i].unit;
                _enterData[wewMxId].retailPrice = mxList[i].retailMoney == undefined ? 0 : mxList[i].retailMoney;
                _enterData[wewMxId].batchNode = mxList[i].batchCertNo == undefined ? '' : mxList[i].batchCertNo;
                _enterData[wewMxId].valiedDate = mxList[i].productValitime == undefined ? '' : mxList[i].productValitime;
                _enterData[wewMxId].backPrinting = mxList[i].backPrinting == undefined ? '' : mxList[i].backPrinting;
                _enterData[wewMxId].number1 = mxList[i].number1 == undefined ? 0 : mxList[i].number1;
                _enterData[wewMxId].enterNumber = mxList[i].matNumber == undefined ? 0 : mxList[i].matNumber;
                _enterData[wewMxId].sendNumber = mxList[i].sendNumber == undefined ? 0 : mxList[i].sendNumber;
                _enterData[wewMxId].linkWmsMiId = mxList[i].linkWmsMiId == undefined ? 0 : mxList[i].linkWmsMiId;
                _enterData[wewMxId].linkMmfId = mxList[i].linkMmfId == undefined ? 0 : mxList[i].linkMmfId;
                _enterData[wewMxId].ratio1 = mxList[i].ratio1 == undefined ? ratio : mxList[i].ratio1;
            }

            mycars[i] = momId1;
            let basicUnit = mxList[i].basicUnit == undefined ? '' : mxList[i].basicUnit;
            str += "<tr>";
            str += "<td id='matCode" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].matCode + "<span style='display: none' id='wmsMiId" + wewMxId + "'>" + mxList[i].wmsMiId + "</span><span style='display: none' id='wewMxId" + wewMxId + "'>" + mxList[i].wewMxId + "</span><span style='display: none' id='mmtId" + wewMxId + "'>" + mxList[i].mmtId + "</span></td>";
            str += "<td id='matName" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].matName + "</td>";
            str += "<td id='brand" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].brand == undefined ? '' : mxList[i].brand) + "</td>";
            str += "<td id='mmfNameCode" + wewMxId + "' style=\"text-align:left;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='mmfName" + wewMxId + "'>" + mxList[i].mmfName + "</span><br/><span id='mmtCode" + wewMxId + "'>" + mxList[i].mmfCode + "</span></td>";
            str += "<td id='basicUnit" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].basicUnit + "</td>";
            str += "<td id='matNumber2" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='matNumber" + wewMxId + "'>" + (mxList[i].orderNumber == undefined ? '' : mxList[i].orderNumber) + "</span>/<span id='number2" + wewMxId + "'>" + (mxList[i].sendNumber == undefined ? '' : mxList[i].sendNumber) + "</span></td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" +
                "<input onblur='changeBaseNumber(" + wewMxId + ", 2)' type='text' id='rksl" + wewMxId + "' value='" + (mxList[i].matNumber == undefined ? '' : mxList[i].matNumber) + "' style='width: 50px;height: 25px' class=\"iptb2\">&nbsp;<span id='eb" + wewMxId + "'>" + basicUnit + "</span>" +
                // 系数数量
                ",每<span id='rbUnit" + wewMxId + "'>" + basicUnit + "</span><input readonly type='text' id='rrksl" + wewMxId + "' placeholder='" + _enterData[wewMxId].ratio1 + "' value='" + ratio + "' style='width: 50px;height: 25px'>&nbsp;<span id='rsUnit" + wewMxId + "'>" + (mxList[i].unit == undefined ? basicUnit : mxList[i].unit) + "</span>" +
                "</td>"; // onfocus='changeNumberFocus(" + wewMxId + ")' onblur='changeRatioNumber(" + wewMxId + ", " + ratio + ", 2)'  // don't delete this line;
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input  onblur='changeSplitNumber(" + wewMxId + ", 2)' type='text' id='cfdw1" + wewMxId + "' value='" + (mxList[i].splitQuantity == undefined ? '' : mxList[i].splitQuantity) + "' style='width: 50px;height: 25px;'>&nbsp;<input readonly onblur='changeUnit(" + wewMxId + ", 2)' type='text' id='cfdw2" + wewMxId + "' value='" + (mxList[i].unit == undefined ? '' : mxList[i].unit) + "' style='width: 25px;height: 25px;'></td>";
            str += "<td id='productName" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].productName + "</td>";
            str += "<td id='unitMoney" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].price + "</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeRetailPrice(" + wewMxId + ", 2)' type='text' id='lsj" + wewMxId + "' value='" + (mxList[i].retailMoney == undefined ? '' : mxList[i].retailMoney) + "' style='width: 40px;height: 25px' class=\"iptb2\">&nbsp;</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeBatchCode(" + wewMxId + ", 2)' type='text' id='ph" + wewMxId + "' value='" + (mxList[i].batchCertNo == undefined ? '' : mxList[i].batchCertNo) + "' style='width: 40px;height: 25px' class=\"iptb2\">&nbsp;</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeValiedDate(" + wewMxId + ", 2)' type='text' id='yxq" + wewMxId + "' value='" + (mxList[i].productValitime == undefined ? '' : mxList[i].productValitime) + "' style='width: 80px;height: 25px' class=\"iptb2\">&nbsp;</td>";
            str += "<td id='backPrinting" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;padding-top: 15px;\"><input onblur='changeBP(" + wewMxId + ", 2)' id='bp" + wewMxId + "' value='" + _enterData[wewMxId].backPrinting + "' style='width: 80px;height: 25px' class=\"iptb2\"/></td>";

            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input type='text' value='" + (mxList[i].matName1 == undefined ? '' : mxList[i].matName1) + "'  id='xzwl" + wewMxId + "' style='width: 80px;height: 25px' onclick='selectMat(" + mxList[i].momId + ", " + mxList[i].wmsMiId + "," + wewMxId + ")' class=\"iptb2\">&nbsp;</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" +
                "<input type='hidden' id='mdp" + wewMxId + "'/><input type='hidden' id='mdps" + wewMxId + "'/>" +
                "<a id=\"btn1\"onclick='gaibian(" + mxList[i].wewMxId + ")'><img style=\"\" width=\"15px\" height=\"15px\" src=\"../../../../dentistmall/css/shopping/images/delete.png\"></a>&nbsp;</td>";
            str += "</tr>";
        }
    }
    $("#mxListMx").html(str);

    if (edit == undefined) {
        $('input').attr('readonly', 'readonly');
        $('img').css('display', 'none');
    } else {
        for (let idx in _enterData) {
            laydate({
                elem: "#yxq" + idx, //有效期
                format: 'YYYY-MM-DD',
                choose: function (dates) { //选择好日期的回调
                    changeValiedDate(idx, 1, dates);
                }
            })
        }
    }
}

//2020年07月15日10:46:02 朱燕冰修改
function initMatListMx(jsondata) {
    var mxList = jsondata;
    var momId1;
    var str = "";
    var productNameInt;
    let mmfId1;
    let wewMxId;
    let ratio;
    // for (var i = 0; i < mxList.length; i++){
    //     retailMoney = mxList[i].retailMoney;
    //     retailMoneys=eval(retailMoney+0.05)
    // }
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            mmfId1 = mxList[i].mmfId;
            wewMxId = mxList[i].wewMxId;
            ratio = mxList[i].basicCoefficent == undefined ? 1 : mxList[i].basicCoefficent;
            if (_enterData[wewMxId] == undefined) {
                _enterData[wewMxId] = {};
                _enterData[wewMxId].mmfId = mxList[i].mmfId;
                _enterData[wewMxId].wewMxId = mxList[i].wewMxId == undefined ? '' : mxList[i].wewMxId;
                _enterData[wewMxId].productName = mxList[i].productName == undefined ? '' : mxList[i].productName;
                _enterData[wewMxId].unitMoney = mxList[i].price == undefined ? 0 : mxList[i].price;
                _enterData[wewMxId].baseNumber = mxList[i].matNumber == undefined ? '' : mxList[i].matNumber;
                _enterData[wewMxId].ratio = mxList[i].basicCoefficent == undefined ? '' : mxList[i].basicCoefficent;
                _enterData[wewMxId].splitNumber = mxList[i].splitQuantity == undefined ? '' : mxList[i].splitQuantity;
                _enterData[wewMxId].unit = mxList[i].unit == undefined ? '' : mxList[i].unit;
                _enterData[wewMxId].retailMoney = mxList[i].retailMoney == undefined ? 0 : mxList[i].retailMoney;
                _enterData[wewMxId].retailPrice = mxList[i].retailMoney == undefined ? 0 : mxList[i].retailMoney;
                // _enterData[wewMxId].retailMoney = eval(mxList[i].retailMoney+0.05)
                _enterData[wewMxId].batchNode = mxList[i].batchCertNo == undefined ? '' : mxList[i].batchCertNo;
                _enterData[wewMxId].valiedDate = mxList[i].productValitime == undefined ? '' : mxList[i].productValitime;
                _enterData[wewMxId].backPrinting = mxList[i].backPrinting == undefined ? '' : mxList[i].backPrinting;
                _enterData[wewMxId].ratio1 = mxList[i].ratio1 == undefined ? ratio : mxList[i].ratio1;
            }
            let basicUnit = mxList[i].basicUnit == undefined ? '' : mxList[i].basicUnit;
            str += "<tr>";
            str += "<td id='matCode" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].mmfCode + "<span style='display: none' id='wewMxId" + wewMxId + "'>" + mxList[i].wewMxId + "</span></td>";
            str += "<td id='matName" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].matName + "</td>";
            str += "<td id='brand" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].brand == undefined ? '' : mxList[i].brand) + "</td>";
            str += "<td id='mmfNameCode" + wewMxId + "' style=\"text-align:left;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='mmfName" + wewMxId + "'>" + mxList[i].mmfName + "</span><br/><span id='mmtCode" + wewMxId + "'>" + mxList[i].mmfCode + "</span></td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" +
                "<input onblur='changeBaseNumber(" + wewMxId + ", 1)' type='text' id='rksl1" + wewMxId + "' placeholder='" + "" + "' value='" + (mxList[i].matNumber == undefined ? '' : mxList[i].matNumber) + "' style='width: 50px;height: 25px'>&nbsp;" + basicUnit +
                // 系数数量
                ",每" + basicUnit +"<input readonly  type='text' id='rrksl1" + wewMxId + "' placeholder='' value='" + _enterData[wewMxId].ratio1 + "' style='width: 50px;height: 25px'>&nbsp;" + (mxList[i].unit == undefined ? basicUnit : mxList[i].unit) +
                "</td>"; // onblur='changeRatioNumber(" + wewMxId + ", " + ratio + ", 1)' // don't delete this codes
            str += "<td id='matNumber2" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><span id='xs" + wewMxId + "'>" + (mxList[i].basicCoefficent == undefined ? 1 : mxList[i].basicCoefficent) + "</span></td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeSplitNumber(" + wewMxId + ", 1)' type='text' id='cfdw11" + wewMxId + "' value='" + (mxList[i].splitQuantity == undefined ? '' : mxList[i].splitQuantity) + "' style='width: 50px;height: 25px;'>&nbsp;<input readonly onblur='changeUnit(" + wewMxId + ", 1)' type='text' id='cfdw21" + wewMxId + "' value='" + (mxList[i].unit == undefined ? mxList[i].BasicUnit : mxList[i].unit) + "' style='width: 25px;height: 25px;'></td>";
            str += "<td id='productName" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].productName == undefined ? '' : mxList[i].productName) + "</td>";
            str += "<td id='unitMoney" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + mxList[i].price + "</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeRetailPrice(" + wewMxId + ", 1)' type='text' id='lsj1" + wewMxId + "' value='" + (mxList[i].retailMoney == undefined ? '' : mxList[i].retailMoney) + "' style='width: 40px;height: 25px'>&nbsp;</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].batchCertNo == undefined ? '' : mxList[i].batchCertNo) + "</td>";//<input type='text' id='ph"+wewMxId+"' style='width: 40px;height: 25px'>&nbsp;</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\"><input onblur='changeValiedDate(" + wewMxId + ", 1)' type='text' id='yxq1" + wewMxId + "' value='" + (mxList[i].productValitime == undefined ? '' : mxList[i].productValitime) + "' style='width: 80px;height: 25px'>&nbsp;</td>";
            str += "<td id='backPrinting" + wewMxId + "' style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" + (mxList[i].backPrinting == undefined ? '' : mxList[i].backPrinting) + "</td>";
            str += "<td style=\"text-align:center;line-height:3;font-size: 13px;border: 1.5px solid #F0F0F0;\">" +
                "<input type='hidden' id='mdp" + wewMxId + "'/><input type='hidden' id='mdps" + wewMxId + "'/>" +
                "<a id=\"btn1\"onclick='gaibian1(" + mxList[i].wewMxId + ")'><img style=\"\" width=\"15px\" height=\"15px\" src=\"../../../../dentistmall/css/shopping/images/delete.png\"></a>&nbsp;</td>";
            str += "</tr>";
        }
    }
    $("#mxMatListMx").html(str);


    $('#selectMat').hide();
    if (edit == undefined) {
        $('input').attr('readonly', 'readonly');
        $('img').css('display', 'none');
    } else {
        for (let idx in _enterData) {
            laydate({
                elem: "#yxq1" + idx, //有效期
                format: 'YYYY-MM-DD',
                choose: function (dates) { //选择好日期的回调
                    changeValiedDate(idx, 1, dates);
                }
            })
        }
    }

}

function changeNumberFocus(wewMxId) {
    if (_editData[wewMxId].linkWmsMiId == undefined) {
        $.supper("alert", {title: '操作提示', msg: '请选择物料'});
    }
}

// 计算现在的数量
function changeRatioNumber(wewMxId, ratio, idx) {
    let value = '';
    let quantity = 0;
    let ratioNumber = 0;
    let baseNumber = 0;
    if (idx == 1) { //物料入库
        quantity = $('#rksl1' + wewMxId).val();
        ratioNumber = $('#rrksl1' + wewMxId).val();
    } else if (idx == 2) { //订单入库
        quantity = $('#rksl' + wewMxId).val();
        ratioNumber = $('#rrksl' + wewMxId).val();
    }
    quantity = quantity == '' ? 0 : quantity;
    ratioNumber = ratioNumber == '' ? 0 : ratioNumber;

    baseNumber = quantity * ratioNumber;
    if (idx == 1) { //物料入库
        $('#cfdw11' + wewMxId).val(baseNumber);
    } else if (idx == 2) { //订单入库
        $('#cfdw1' + wewMxId).val(baseNumber);
    }
    if (_editData[wewMxId] == undefined) {
        _editData[wewMxId] = {};
    }
    _editData[wewMxId].splitNumber = baseNumber;
    _editData[wewMxId].ratio1 = ratioNumber;
}

function changeBaseNumber(wewMxId, idx) {
    let value = '';
    let ratioNumber = 0;
    let baseNumber = 0;
    if (idx == 1) { //物料入库
        value = $('#rksl1' + wewMxId).val();
        ratioNumber = $('#rrksl1' + wewMxId).val();
    } else if (idx == 2) { //订单入库
        // value = $('#rksl' + wewMxId).val();
        if($('#rksl' + wewMxId).val() == ''){
            value = '0';
        }else {
            value = $('#rksl' + wewMxId).val();
        }
        ratioNumber = $('#rrksl' + wewMxId).val();
    }
    ratioNumber = ratioNumber == '' ? 0 : ratioNumber;
    baseNumber = (value == '' ? 0 : value) * ratioNumber;
    if (idx == 1) { //物料入库
        $('#cfdw11' + wewMxId).val(baseNumber);
    } else if (idx == 2) { //订单入库
        $('#cfdw1' + wewMxId).val(baseNumber);
    }
    if (_editData[wewMxId] == undefined) {
        _editData[wewMxId] = {};
    }
    _editData[wewMxId].splitNumber = baseNumber;
    _editData[wewMxId].baseNumber = value;

}

function changeSplitNumber(wewMxId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        value = $('#cfdw11' + wewMxId).val();
    } else if (idx == 2) { //订单入库
        // value = $('#cfdw1' + wewMxId).val();
        if ($('#cfdw1' + wewMxId).val() == ''){
            value = '0';
        }else {
            value = $('#cfdw1' + wewMxId).val();
        }
    }
    if (_editData[wewMxId] == undefined) {
        _editData[wewMxId] = {};
    }
    _editData[wewMxId].splitNumber = value;
}

function changeUnit(wewMxId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        value = $('#cfdw21' + wewMxId).val();
    } else if (idx == 2) { //订单入库
        value = $('#cfdw2' + wewMxId).val();
    }
    if (_editData[wewMxId] == undefined) {
        _editData[wewMxId] = {};
    }
    _editData[wewMxId].unit = value;
}

function changeRetailPrice(wewMxId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        value = $('#lsj1' + wewMxId).val();
    } else if (idx == 2) { //订单入库
        value = $('#lsj' + wewMxId).val();
    }
    if (_editData[wewMxId] == undefined) {
        _editData[wewMxId] = {};
    }
    _editData[wewMxId].retailPrice = value;
}

function changeBatchCode(wewMxId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        value = $('#ph1' + wewMxId).val();
    } else if (idx == 2) { //订单入库
        value = $('#ph' + wewMxId).val();
    }
    if (_editData[wewMxId] == undefined) {
        _editData[wewMxId] = {};
    }
    _editData[wewMxId].batchNode = value;
}

function changeValiedDate(wewMxId, idx, date) {
    // let value = '';
    // if (idx == 1) { //物料入库
    //     value = $('#yxq1' + wewMxId).val();
    // } else if (idx == 2) { //订单入库
    //     value = $('#yxq' + wewMxId).val();
    // }
    if (_editData[wewMxId] == undefined) {
        _editData[wewMxId] = {};
    }
    _editData[wewMxId].valiedDate = date;
}

function changeBP(wewMxId, idx) {
    let value = '';
    if (idx == 1) { //物料入库
        value = $('#bp1' + wewMxId).val();
    } else if (idx == 2) { //订单入库
        value = $('#bp' + wewMxId).val();
    }
    if (_editData[wewMxId] == undefined) {
        _editData[wewMxId] = {};
    }
    _editData[wewMxId].backPrinting = value;
}

function selectMat(momId, wmsMiId, wewMxId) {
    if (edit == undefined) {
        return;
    }
    if (wmsMiId == undefined) {
        $.supper("setProductArray", {"name": "searchName", "value": {searchName: $('#searchName1').val()}});
    } else {
        $.supper("setProductArray", {
            "name": "searchName",
            "value": {searchName: $('#searchName1').val(), wmsMiId: wmsMiId}
        });
    }
    var att_url = $.supper("getServicePath", {"data": '', url: "/jsp/dentistmall/modifyprice/selMateriel"});
    var tt_win = $.supper("showWin", {
        url: att_url,
        title: "物料信息",
        icon: "fa-bars",
        width: "1250",
        height: '700',
        BackE: function () {
            addMat(momId, wmsMiId, wewMxId);
        }
    });
}

function addMat(momId, wmsMiId, wewMxId) {
    let selPartAndNorm = $.supper("getProductArray", "selPartAndNorm");
    let data;
    if (selPartAndNorm != undefined && selPartAndNorm != null) {
        if (selPartAndNorm.data == '') {

        } else {
            data = selPartAndNorm.data;
            if (momId != undefined && wmsMiId != undefined) {
                // let dataList = data.split(',');
                // $('#ph' + momId).val(dataList[3]);
                // $('#lsj' + momId).val(dataList[4]);
                // $('#cfdw1' + momId).val(dataList[6]);
                // $('#mdp' + momId).val(dataList[0]);
                // $('#mdps' + momId).val(dataList[1]);
                autoInput(data, momId, wewMxId);
            } else {
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
    $.supper("setProductArray", {"name": "selPartAndNorm", "value": null});
}

function autoInput(data, mmfId, wewMxId) {
    $('#ph' + wewMxId).val(data.backPrinting);
    $('#lsj' + wewMxId).val(data.retailPrice);
    $('#cfdw2' + wewMxId).val(data.splitUnit);
    $('#mdp' + wewMxId).val(data.mdpId);
    $('#mdps' + wewMxId).val(data.mdpsId);
    $('#xzwl' + wewMxId).val(data.matName);

    if (_editData[wewMxId] == undefined)
        _editData[wewMxId] = {};
    if (data.mmfId != undefined && data.mmfId != '')
        _editData[wewMxId].linkMmfId = data.mmfId;
    if (data.wmsMiId != undefined && data.wmsMiId != '')
        _editData[wewMxId].linkWmsMiId = data.wmsMiId;
    _enterData[wewMxId].basicUnit = data.basicUnit == undefined ? '' : data.basicUnit;
    _enterData[wewMxId].unit = data.splitUnit == undefined ? _enterData[wewMxId].basicUnit : data.splitUnit;
    _enterData[wewMxId].retailPrice = data.retailPrice == undefined ? '' : data.retailPrice;
    _enterData[wewMxId].batchNode = data.batchNode == undefined ? '' : data.batchNode;
    _enterData[wewMxId].valiedDate = data.valiedDate == undefined ? '' : data.valiedDate;
    _enterData[wewMxId].backPrinting = data.backPrinting == undefined ? '' : data.backPrinting;
    _enterData[wewMxId].mdpId = data.mdpId == undefined ? '' : data.mdpId;
    _enterData[wewMxId].mdpsId = data.mdpsId == undefined ? '' : data.mdpsId;
    _enterData[wewMxId].mmfCode = data.mmfCode == undefined ? '' : data.mmfCode;
    _enterData[wewMxId].ratio = data.basicCoefficent == undefined ? 1 : data.basicCoefficent;
    _editData[wewMxId].ratio1 = data.basicCoefficent == undefined ? 1 : data.basicCoefficent;
    _editData[wewMxId].matName = data.matName == undefined ? '' : data.matNumber;
    $('#rrksl' + wewMxId).val(_enterData[wewMxId].ratio);
    $('#basicUnit' + wewMxId).text(_enterData[wewMxId].basicUnit);
    $('#rbUnit' + wewMxId).text(_enterData[wewMxId].basicUnit);
    $('#eb' + wewMxId).text(_enterData[mmfId].basicUnit);
    $('#rsUnit' + wewMxId).text(_enterData[wewMxId].unit);
    $('#cfdw2' + wewMxId).text(_enterData[wewMxId].unit);
    if (CheckUtil.isDigit(_enterData[wewMxId].baseNumber)) {
        let splitnumber = _enterData[wewMxId].baseNumber * _enterData[wewMxId].ratio;
        $('#cfdw1' + wewMxId).val(splitnumber);
    }
}


function save() {
    if (enterType == 1) {
        // console.log('入库管理-入库详情-编辑-保存')
        if (moiId1 != null) {
            // var number2s = 0;
            // var lsjg = 0;
            // for (var i = 0; i < mycars.length; i++) {
            //     var momIds = mycars[i];
            //     var number2 = parseFloat(document.getElementById('number2' + momIds).innerText);
            //     var rksl = parseFloat($("#rksl" + momIds).val());
            //     if (number2 < rksl) {
            //         $.supper("alert", {title: "操作提示", msg: "入库数量不能大于发货数量！"});
            //         return;
            //     }
            //     number2s += number2;
            //     lsjg += parseFloat($("#lsj" + momIds).val());
            // }
            // var warehousCode = $("#warehousCode").val();
            // var orderWarehous = $("#orderWarehous").val();
            // var remarks = $("#remarks").val();
            // var billCode = $("#billCode").val();
            // var vdata = '&moiIds=' + moiId1 + '&warehousCode=' + warehousCode + '&orderWarehous=' + orderWarehous + '&remarks=' + remarks + '&billCode=' + billCode + '&number2s=' + number2s + '&lsjg=' + lsjg;
            // $.supper("doservice", {
            //     "service": "ModifypriceService.saveWarehousing", "data": vdata, "BackE": function (jsondata) {
            //         if (jsondata.code == "1") {
            //             var wewId2 = jsondata.obj;
            //searchAllMX(1,moiId1);
            saveMx();
            //         } else {
            //             $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            //         }
            //     }
            // });
        }
    } else if (enterType == 2) {
        // var number2s = 0;
        // var lsjg = 0;
        // for (var i = 0; i < mycars.length; i++) {
        //     var momIds = mycars[i];
        //     var number2 = parseFloat(document.getElementById('number2' + momIds).innerText);
        //     var rksl = parseFloat($("#rksl" + momIds).val());
        //     if (number2 < rksl) {
        //         $.supper("alert", {title: "操作提示", msg: "入库数量不能大于发货数量！"});
        //         return;
        //     }
        //     number2s += number2;
        //     lsjg += parseFloat($("#lsj" + momIds).val());
        // }
        // var warehousCode = $("#warehousCode").val();
        // var orderWarehous = $("#orderWarehous").val();
        // var remarks = $("#remarks").val();
        // var billCode = $("#billCode").val();
        // var vdata = '&moiIds=' + moiId1 + '&warehousCode=' + warehousCode + '&orderWarehous=' + orderWarehous + '&remarks=' + remarks + '&billCode=' + billCode + '&number2s=' + number2s + '&lsjg=' + lsjg;
        // $.supper("doservice", {
        //     "service": "ModifypriceService.saveWarehousing", "data": vdata, "BackE": function (jsondata) {
        //         if (jsondata.code == "1") {
        //             var wewId2 = jsondata.obj;
        //searchAllMX(1,moiId1);
        saveMatEn();
        //         } else {
        //             $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        //         }
        //     }
        // });
    }
}

function saveMatEn() {
    let data = $('#saveForm').serialize(); //'wewId=' + wewId2;
    data += '&wewId=' + wewId;
    var wewMxIds = '';
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
    let ratio1s = '';
    for (let wewMxId in _editData) {
        var shu = _editData[wewMxId].baseNumber == undefined ? _enterData[wewMxId].baseNumber : _editData[wewMxId].baseNumber;
        if (shu != null && shu != "" && shu > 0) {
            if (_enterData[wewMxId] == undefined)
                continue;
            mmfIds += _enterData[wewMxId].mmfId;
            shus += shu + ",";
            wewMxIds += wewMxId + ",";
            prices += (_editData[wewMxId].unitMoney == undefined ? _enterData[wewMxId].unitMoney : _editData[wewMxId].unitMoney) + ",";
            packasgs += (_enterData[wewMxId].productName == undefined ? _enterData[wewMxId].productName : _enterData[wewMxId].productName) + ',';
            splitNumbers += (_editData[wewMxId].splitNumber == undefined ? _enterData[wewMxId].splitNumber : _editData[wewMxId].splitNumber) + ",";
            units += (_editData[wewMxId].unit == undefined ? _enterData[wewMxId].unit : _editData[wewMxId].unit) + ",";
            retailPrices += (_editData[wewMxId].retailPrice == undefined ? _enterData[wewMxId].retailPrice : _editData[wewMxId].retailPrice) + ",";
            batchNodes += (_editData[wewMxId].batchNode == undefined ? _enterData[wewMxId].batchNode : _editData[wewMxId].batchNode) + ",";
            valiedDates += (_editData[wewMxId].valiedDate == undefined ? _enterData[wewMxId].valiedDate : _editData[wewMxId].valiedDate) + ",";
            backPrintings += (_editData[wewMxId].backPrinting == undefined ? _enterData[wewMxId].backPrinting : _editData[wewMxId].backPrinting) + ",";
            ratio1s += (_editData[wewMxId].ratio1 == undefined ? _enterData[wewMxId].ratio1 : _editData[wewMxId].ratio1) + ',';
        }
    }

    if (wewMxIds != '')
        wewMxIds = wewMxIds.substring(0, wewMxIds.length - 1);
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
    if (retailPrices != ''){
        retailPrices = retailPrices.substring(0, retailPrices.length - 1);
        // retailPrices = eval(retailPrices-0.05)
    }
    // if (retailPrices != '')
    //     retailPrices = retailPrices.substring(0, retailPrices.length - 1);
    if (batchNodes != '')
        batchNodes = batchNodes.substring(0, batchNodes.length - 1);
    if (valiedDates != '')
        valiedDates = valiedDates.substring(0, valiedDates.length - 1);
    if (backPrintings != '')
        backPrintings = backPrintings.substring(0, backPrintings.length - 1);
    if (ratio1s != '')
        ratio1s = ratio1s.substring(0, ratio1s.length - 1);
    data += "&wewMxIds=" + wewMxIds + "&shus=" + shus + "&mmfIds=" + mmfIds + "&billType=1" + "&prices=" + prices + "&packasgs=" + packasgs +
        "&productPTimes=" + productPTimes + "&productValiTimes=" + valiedDates + "&factories=" + factories + "&certnos=" + batchNodes;
    data += '&splitNumbers=' + splitNumbers + '&units=' + units + '&retailPrices=' + retailPrices + '&backPrintings=' + backPrintings;
    data += '&ratio1s=' + ratio1s;
    $.supper("doservice", {
        "service": _all_saveAction,
        "ifloading": 1,
        "options": {"type": "post", "data": data},
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作成功！",
                    // BackE: closeWin
                });
            } else
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作失败！"
                });
        }
    });
}

// var _all_saveAction = "MdEnterWarehouseService.saveMdEnterWarehouse";
var _all_saveAction = "MdEnterWarehouseMxService.saveOrUpdateObject";

function saveMx() {
    let data = 'moiIds=' + moiId1;// 'wewId=' + wewId2;
    data += '&' + $('#saveForm').serialize();
    data += '&wewId=' + wewId;
    var wewMxIds = '';
    var momIds = "";
    var shus = "";
    var mmfIds = "";
    var linkmmfIds = "";
    var linkwmsIds = "";
    var number1s = "";
    var prices = "";
    var masIds = '';
    var splitNumbers = '';
    var units = '';
    var retailPrices = '';
    var batchNodes = '';
    var valiedDates = '';
    var backPrintings = '';
    var flag = false;
    let ratio1s = '';

    for (let wewMxId in _editData) {
        if (_enterData[wewMxId] == undefined || (_enterData[wewMxId].linkWmsMiId == undefined && _editData[wewMxId].linkWmsMiId == undefined))
            continue;
        var tt = _enterData[wewMxId].sendNumber;
        var shu = _editData[wewMxId].baseNumber == undefined ? _enterData[wewMxId].baseNumber : _editData[wewMxId].baseNumber;
        if (shu != null && shu != "" && shu > tt) {
            $.supper("alert", {title: "操作提示", msg: "入库数量不能大于发货数量！"});
            return;
        // } else if (shu != null && shu != "" && shu > 0) {
        } else if (shu != null && shu != "" ) {
            flag = true;
            momIds += _enterData[wewMxId].momId + ",";
            wewMxIds += wewMxId + ',';
            shus += shu + ",";
            mmfIds += _enterData[wewMxId].mmfId + ",";
            number1s += (_editData[wewMxId].matNumber == undefined ? _enterData[wewMxId].matNumber : _editData[wewMxId].matNumber) + ",";
            prices += (_enterData[wewMxId].unitMoney == undefined ? _enterData[wewMxId].unitMoney : _enterData[wewMxId].unitMoney) + ",";
            splitNumbers += (_editData[wewMxId].splitNumber == undefined ? _enterData[wewMxId].splitNumber : _editData[wewMxId].splitNumber) + ",";
            units += (_editData[wewMxId].unit == undefined ? _enterData[wewMxId].unit : _editData[wewMxId].unit) + ",";
            retailPrices += (_editData[wewMxId].retailPrice == undefined ? _enterData[wewMxId].retailPrice : _editData[wewMxId].retailPrice) + ",";
            batchNodes += (_editData[wewMxId].batchNode == undefined ? _enterData[wewMxId].batchNode : _editData[wewMxId].batchNode) + ",";
            valiedDates += (_editData[wewMxId].valiedDate == undefined ? _enterData[wewMxId].valiedDate : _editData[wewMxId].valiedDate) + ",";
            backPrintings += (_editData[wewMxId].backPrinting == undefined ? _enterData[wewMxId].backPrinting : _editData[wewMxId].backPrinting) + ",";
            ratio1s += (_editData[wewMxId].ratio1 == undefined ? _enterData[wewMxId].ratio1 : _editData[wewMxId].ratio1) + ',';
            // if (_editData[wewMxId].linkMmfId != undefined)
            linkmmfIds += (_editData[wewMxId].linkMmfId == undefined ? _enterData[wewMxId].linkMmfId : _editData[wewMxId].linkMmfId) + ",";
            // if (_editData[wewMxId].linkWmsMiId != undefined)
            linkwmsIds += (_editData[wewMxId].linkWmsMiId == undefined ? _enterData[wewMxId].linkWmsMiId : _editData[wewMxId].linkWmsMiId) + ",";
            // if (masIds.indexOf(_enterData[wewMxId].masId) < 0) {
            //     masIds += _enterData[wewMxId].masId + ',';
            // }
            // alert($("#xzwl"+wewMxId).val());
            //   xzwls+=($("#xzwl"+wewMxId).val()==undefined ? $("#xzwl"+wewMxId).val() :)
            //   if ($("#xzwl"+wewMxId).val()!=undefined){
            //       xzwls+=$("#xzwl"+wewMxId).val()+",";
            //   }else {
            //       xzwls+='null';
            //   }
        }
    }
    var message = '';
    if (wewMxIds != '')
        wewMxIds = wewMxIds.substring(0, wewMxIds.length - 1);
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
    if (retailPrices != ''){
        retailPrices = retailPrices.substring(0, retailPrices.length - 1);
    }

    if (batchNodes != '')
        batchNodes = batchNodes.substring(0, batchNodes.length - 1);
    if (valiedDates != '')
        valiedDates = valiedDates.substring(0, valiedDates.length - 1);
    if (backPrintings != '')
        backPrintings = backPrintings.substring(0, backPrintings.length - 1);
    if (linkmmfIds != '') {
        linkmmfIds = linkmmfIds.substring(0, linkmmfIds.length - 1);
    }
    if (linkwmsIds != '') {
        linkwmsIds = linkwmsIds.substring(0, linkwmsIds.length - 1);
    }
    if (ratio1s != '')
        ratio1s = ratio1s.substring(0, ratio1s.length - 1);
    data += "&wewMxIds=" + wewMxIds + "&shus=" + shus + "&momIds=" + momIds + "&mmfIds=" + mmfIds + "&billType=2&number1s=" + number1s + "&prices=" + prices;
    data += '&splitNumbers=' + splitNumbers + '&units=' + units + '&retailPrices=' + retailPrices + '&certnos=' + batchNodes + '&productValiTimes=' + valiedDates + '&backPrintings=' + backPrintings + '&linkmmfIds=' + linkmmfIds + '&linkwmsIds=' + linkwmsIds;
    data += '&ratio1s=' + ratio1s;
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
            }  else if (jsondata.code == "6") {
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

function gaibian(wewMxId) {

    var vdata = 'wewMxId=' + wewMxId;
    const BTN1 = document.getElementById('btn1');
    const BTN2 = document.getElementById('btn2');
    //给按钮2添加点击事件
    BTN1.addEventListener('click', () => {
        BTN2.onclick();
    });
    //mycars.length=mycars.length-1;
    $.supper("doservice", {
        "service": "ModifypriceService.deleteWareMx", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert",{ title:"操作提示", msg:"操作成功！"});
                searchAllMX(2, moiId1);
                _enterData[wewMxId] = undefined;
                mycars = new Array();
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        }
    });
}

function gaibian1(wewMxId) {
    var vdata = 'wewMxId=' + wewMxId;
    $.supper("doservice", {
        "service": "ModifypriceService.deleteWareMx", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // $.supper("alert",{ title:"操作提示", msg:"操作成功！"});
                searchMatMx();
                _enterData[wewMxId] = undefined;
            } else {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
            }
        }
    });
}

function backTo() {
    var view_url = 'jsp/dentistmall/modifyprice/warehousingManagement.jsp';
    $.supper("showTtemWin", {"url": view_url, "title": '入库管理'});
    $.supper("setProductArray", {"name": "enterInfo", "value": null});
    setTimeout(function () {
        if (url != undefined) {
            $.supper("closeTtemWin", {url: url});
        }
    }, 200);
}

//导出入库管理excel
function exportExcel() {
    var vdata = "wewId=" + wewId;
    var newTab = window.open('about:blank');
//重新提交
    $.supper("doservice", {
        "service": "EnterWarehouseExportService.exportWarehousingInfoList",
        "data": vdata,
        "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

// var _all_questAction = "MdEnterWarehouseService.findFormObject";
// function initForm(){
//     //_rbbId = $.supper("getParam", _all_table_Id);
//     var wewId=154;
//     var att_data="wewId="+wewId;
//     //_all_div_body.xform('createForm',_saveForm);
//     if(wewId != null){
//         _all_accountForm.xform('loadAjaxForm',{'ActionUrl':_all_questAction,"data":att_data});
//     }
//     initDataGrid();
// }

// var mmg;
// var initDataGrid = function(){
//     var cols = [
//         {title:'商品名称', name:'matName' ,width:100,  align:'center',renderer:controlInfo  },
//         {title:'规格', name:'norm' ,width:30,  align:'center',renderer:controlMmfName} ,
//         {title:'单位', name:'basicUnit' ,width:30,  align:'center'} ,
//         {title:'订单数量', name:'supplierName', width: 80, align: 'center'},
//         {title:'发货数量', name:'number2', width: 80, align: 'center'},
//         {title:'已入库数量', name:'enterNumber', width: 80, align: 'center'},
//         {title:'入库数量', name:'number2', width: 80, align: 'center',renderer:formateInp}
//     ];
//     mmg = $('#datagrid1').mmGrid({
//         height:'auto'
//         , cols: cols
//         , method: 'get'
//         , remoteSort:false
//         , sortName: 'serialNumber'
//         , sortStatus: 'asc'
//         , multiSelect: true
//         , checkCol: false
//         ,showBackboard:false
//         , fullWidthRows: true
//         , autoLoad: false
//         , nowrap:true
//     });
//     mmg.load([]);
// }
// function controlInfo(val,item,rowIndex){
//     var str = "";
//     str += "<a href=\""+$.supper("getbasepath")+"xiangxi.htm?wmsMiId="+item.wmsMiId+"\" id=\""+item.mmfId+"MatName\" target=\"_blank\">"+item.matName+"</a> ";
//     return str;
// }
// function controlMmfName(val,item,rowIndex){
//     var str = "";
//     str += "<lable id=\""+item.mmfId+"MmfName\">"+item.norm+"</lable> ";
//     return str;
// }
// function delRow(mmfId){
//     var rows = mmg.rows();
//     for(var i =0;i < rows.length;i++){
//         if(rows[i] != null && rows[i].mmfId==mmfId){
//             mmg.removeRow(i);
//         }
//     }
// }
// function formateInp(val,item,rowIndex){
//     var enterNumber = (item.enterNumber!=null?item.enterNumber:0);
//     var number2 = (item.number2!=null?item.number2:0);
//     var cha=parseInt(number2)-parseInt(enterNumber);
//     if(enterNumber < number2)
//         var tt = "<input type=\"text\" id=\""+item.mmfId+"Inp\" value=\""+cha+"\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'')\" style=\"width:80px\"/>";
//     else
//         tt="-";
//     return tt;
// }
// function loadGrid(moiId){
//     moiId=600;
//     var att_url= $.supper("getServicePath", {service:"MdOrderMxService.getMdOrderMxListByMoiIdForEnter",data:"moiId="+moiId});
//     mmg.opts.url = att_url;
//     mmg.load();
// }
