var mooId;
var url;
$(function () {
    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if(selOutOrderType != null && selOutOrderType.mooId1 != null) {
        mooId = selOutOrderType.mooId1;
        url = selOutOrderType.url;
    }
    initDataMx();
    seachAll(mooId);
    // myFunc1();

});
function initDataMx() {
    var gjz=$("#gjz").val();
    var vdata ='&mooId='+mooId+'&gjz='+gjz;
    $.supper('initPagination', {
        id: "Pagination1",
        service: "MdOutOrderService.getPickingXq",
        data: vdata,
        // limit:10,
        isAjax: "1",
        "BackE": initListMx
    });
}
var momId="";
var mxListLenth=0;
var momIdAllInt=0;
function initListMx(jsondata) {
    var mxList = jsondata;
    var str = "";
    var mmfCounts=0;
    var mooId1;
    var momId1=""
    var baseNumbers=0;
    var numberCount2=0;
    // var numberCount2Int=0;
    if (mxList != null && mxList.length > 0) {
        mmfCounts=mxList.length;
        // str+="<div style='width: 1200px'>"
        for (var i = 0; i < mxList.length; i++) {
            var data = mxList[i];
            mxListLenth=mxList.length;
            momId1=data.momId;
            momId+=data.momId+",";
            momIdAllInt+=1;
            str += "<div class=\"listbox\"><div class=\"items\" style='height: 120px'><div style='width: 5%;text-align: center'><input class='checkbox' name='items' type=\"checkbox\" onclick='checkd(this," + momId1 + ")' style='margin: 0'/></div><div class=\"imgbox\" style='width: 40%;'><div class=\"img\"><img onclick='pathTo("+data.wmsMiId+")' src=\""+data.rootPath+"\" width=\"68\" height=\"68\" style='cursor: pointer'>";
            str +="</div>";
            str += "<div><ul style='float: left;margin-bottom: 0px;'><li class=\"name\" style='text-align: left;'><a onclick='pathTo("+data.wmsMiId+")'>"+data.matName+"</a></li>";
            str += "<li class=\"type\" style='text-align: left;margin-top: 5px'>品牌：<strong><a target=\"_blank\">"+data.brand+"</a></strong></li>";
            str += "<li class=\"type\" style='text-align: left;'>规格型号：<strong><a target=\"_blank\">"+data.norm+"</a></strong></li>";
            str +="<li class=\"type\" style='text-align: left;'>物料编号："+(data.matCode !== undefined ? data.matCode : '')+"</li>";
            str += '<li class="\type\" style=\'text-align: left;\'>包装方式：'+(data.productName !== undefined ? data.productName : '')+'</li>';
            str += "</ul></div></div>";
            // if (data.splitQuantity!=null&&data.splitQuantity!=undefined&&data.splitUnit!="") {
            str += "<span style='width: 10%;text-align: center;'><span class=\"\">"+(data.quantity !== undefined ? data.quantity : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+"</span><span class=\"\" style='margin-right: 0px'>"+(data.split_quantity1 !== undefined ? data.split_quantity1 : '0')+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></span>";
            // }else {
            //     str += "<span style='width: 150px;text-align: center'><span class=\"\">"+(data.quantity !== undefined ? data.quantity : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+"</span></span>";
            // }
            if (data.number1 == undefined ) {
                // str += "<span style='width: 15%;text-align: center;margin-left: 25px'><input style=\"margin-right:5px;width: 15px\" name=\"\" type=\"button\" value=\"-\" onclick=\"minusShu("+data.momId+")\"></input><span class=\"\"><input oninput='myFunc("+data.momId+")'  id=\"baseNumber"+data.momId+"\" style='width: 30px;height: 25px;' value='"+(data.baseNumber !== undefined ? data.baseNumber : '0')+"'>"+(data.basicUnit !== undefined ? data.basicUnit : '')+"<input style=\"margin-left:5px;height: 27px;width: 14px\" name=\"\" type=\"button\" value=\"+\" onclick=\"addShu("+data.momId+")\"></input>"+"</span>";
                str += "<span style='width: 15%;text-align: center;margin-left: 25px'><span class=\"\"><input oninput='myFunc("+data.momId+")'  id=\"baseNumber"+data.momId+"\" style='width: 30px;height: 25px;' value='"+(data.baseNumber !== undefined ? data.baseNumber : '0')+"'>"+(data.basicUnit !== undefined ? data.basicUnit : '')+""+"</span>";

                // if (data.splitQuantity!=null&&data.splitQuantity!=undefined&&data.splitQuantity!="") {

                    str +="<span style='margin-left: 20px;'><input oninput='myFunc2("+'data.splitUnit'+")'  id=\"splitQuantity"+data.momId+"\" value='"+(data.splitQuantity !== undefined ? data.splitQuantity : '0')+"' type='text' style='width: 29px;height: 25px'>"+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></span>"
                // }else {
                //     //当最小单位为空时 去掉最小单位的显示  <input oninput='myFunc2("+data.momId+")'  id=\"splitQuantity"+data.momId+"\" value='"+(data.splitQuantity !== undefined ? data.splitQuantity : '0')+"' type='text' style='width: 29px;height: 25px'>"+data.basicUnit+"</span>
                //     str +="<span style='margin-left: 20px'></span></span>"
                // }
            }else {
                str += "<span style='width: 150px;'><span class=\"rtbox\">"+(data.baseNumber !== undefined ? data.baseNumber : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+"</span></span>";
            }
            baseNumbers+=parseInt(data.baseNumber);
            numberCount2+=parseInt(data.splitQuantity !== undefined ? data.splitQuantity : '0');
            // if (data.splitQuantity!=null&&data.splitQuantity!=undefined&&data.splitQuantity!="") {
                str += "<span style='width: 150px;text-align: center' ><span class=\"\">"+(data.number1 !== undefined ? data.number1 : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+"</span><span class=\"\" style='margin-right: 20px'>"+(data.splitNumber1 !== undefined ? data.splitNumber1 : '0')+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></span>";
            // }else {
            //     str += "<span style='width: 150px;text-align: center' ><span class=\"\">"+(data.number1 !== undefined ? data.number1 : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+"</span></span>";
            // }
            if ((data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))>0) {
                // if (data.splitQuantity!=null&&data.splitQuantity!=undefined&&data.splitQuantity!=""){
                    str += "<span style='width: 150px;text-align: center;margin-left: 5px'><span style='color: red' class=\"\">"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+"</span>"+(data.basicUnit !== undefined ? data.basicUnit : '')+"<span class=\"\" >"+(((data.splitQuantity !== undefined ? data.splitQuantity : '0')-(data.splitNumber1 !== undefined ? data.splitNumber1 : '0')))+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></span>";
                // }else {
                //     str += "<span style='width: 150px;text-align: center;margin-left: 15px'><span style='color: red' class=\"\">"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+"</span>"+(data.basicUnit !== undefined ? data.basicUnit : '')+"</span>";
                // }
            }else {
                // if (data.splitQuantity!=null&&data.splitQuantity!=undefined&&data.splitQuantity!=""){
                    str += "<span style='width: 150px;text-align: center;margin-left: 5px''><span style='color: red' class=\"\">"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+"</span>"+(data.basicUnit !== undefined ? data.basicUnit : '')+"<span class=\"\" >"+(((data.splitQuantity !== undefined ? data.splitQuantity : '0')-(data.splitNumber1 !== undefined ? data.splitNumber1 : '0')))+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></span>";
                // }else {
                //     str += "<span style='width: 150px;text-align: center;margin-left: 15px''><span style='color: red' class=\"\">"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+"</span>"+(data.basicUnit !== undefined ? data.basicUnit : '')+"</span>";
                // }
            }
            str+="<div style='margin-right: 10px;width: 50px;margin-left:70px; '><a onclick='delId("+data.momId+")'>移除</a></div>"
            // str += "<div class=\"right\" style='float: left;margin-top: 55px;'><div style='text-align: center;width:200px;height: 30px;' class=\"money\">"+(data.orderTime!==undefined ? data.orderTime:'')+"</div></div>";
            str +="</div>";
        }
    }
    $("#mxList").html(str);
    $("#numberCount1").html(baseNumbers);
    // $("#numberCount2").html("最小单位数量"+numberCount2);
    $("#mmfCount").html(mmfCounts);
    // $("#hidden"+mooId1).show();
};
//减少符号
function minusShu(momId){
    console.log("111")
    var shu =$("#baseNumber"+momId).val();
    if(shu >1)
        shu--;
    $("#baseNumber"+momId).val(shu);
    // $("#count_"+mmfCode).html("￥"+toDecimal2(count));
}
//增加符号
function addShu(momId){
    var shu =$("#baseNumber"+momId).val();
    shu++;
    $("#baseNumber"+momId).val(shu);

}
function pathTo(wmsMiId) {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPickingMxFindId.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{wmsMiId: wmsMiId}});
    $.supper("showTtemWin",{ "url":att_url,"title":"新增领料申请"});
}
var momId23;
function myFunc() {
    momId23=momId.substr(0, momId.length- 1);
    momId23= momId23.split(",");
    var baseNumbers=0;
    for (i=0;i<momId23.length;i++ ){
            baseNumbers+=parseInt($("#baseNumber"+momId23[i]).val());
    }
    $("#numberCount1").html(baseNumbers);
}
var momId24;
function myFunc2(splitUnit) {
    momId24=momId.substr(0, momId.length- 1);
    momId24= momId24.split(",");
    var baseNumbers=0;
    for (i=0;i<momId24.length;i++ ){
        baseNumbers+=parseInt($("#splitQuantity"+momId24[i]).val());
    }
    $("#numberCount2").html(baseNumbers);
}

function seachAll(mooId) {
    var vdata="mooId="+mooId;
    $.supper("doservice", {
        "service": "MdOutOrderService.getPickingOrderInfo",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            $("#mooCode").val(jsondata.obj.mooCode);
            $("#remarks").val(jsondata.obj.remarks);
            $("#orderTime").val(jsondata.obj.orderTime);
            $("#flowState").html(jsondata.obj.flowState);
            if(jsondata.obj.flowState!="申请中"&&jsondata.obj.flowState!="部分出库"){
                $("#cancelAllbtn").attr("disabled", true);
                $("#cancelAllbtn").css("background",'#DCDCDC');
                $("#cancelAllbtn").css("border",'1px solid #DCDCDC');
            }
        }
    });
}
//全部撤销按钮2020年07月02日21:25:57朱燕冰修改
function submits() {
    var vdata="mooId1="+mooId;
    $.supper("confirm",{ title:"全部撤销", msg:"确定要全部撤销吗？" ,yesE:function(){
        $.supper("doservice", {
            "service": "MdOutOrderService.saveCancelAll",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
                if (jsondata.code == "1") {
                    $.supper("alert",{ title:"操作提示", msg:"撤销成功!"});
                    seachAll(mooId);
                    backTo();
                } else{
                    $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
                }
            }
        });
    }});
}
//提交修改申请数量
function submit(){
    var remarks=$("#remarks").val();
    momId=momId.substr(0, momId.length - 1);
    momIds= momId.split(",");
    var baseNumbers="";
    for (i=0;i<momIds.length ;i++ )
    {
        baseNumber=$("#baseNumber"+momIds[i]).val();
        baseNumbers+=(baseNumber == undefined ? 0 : baseNumber)+",";
    }
    var splitQuantitys="";
    for (i=0;i<momIds.length ;i++ )
    {
        if ($("#splitQuantity"+momIds[i]).val()!=null&&$("#splitQuantity"+momIds[i]).val()!=undefined) {
            splitQuantity=$("#splitQuantity"+momIds[i]).val();
        }else {
            splitQuantity=0;
        }

        splitQuantitys+=splitQuantity+",";
    }
    var vdata="&momIds="+momId+"&baseNumbers="+baseNumbers+"&remarks="+remarks+"&splitQuantitys="+splitQuantitys;
    $.supper("doservice", {
        "service": "MdOutOrderService.saveBaseNumber",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                initDataMx();
                momId="";
                $.supper("alert",{ title:"操作提示", msg:"操作成功!"});
                backTo();
            } else{
                $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
            }
        }

    });

}
//关闭当前页面
function backTo() {

    $.supper("setProductArray", {"name": "selOutOrderInfo", "value": null });
    setTimeout(function () {
        if (url != undefined) {
            $.supper("closeTtemWin", {url: url});
        }
    }, 200);
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
// $("#all").click(function() {
//     $("[name=items]:checkbox").prop("checked", this.checked);
//     $("#all1").prop("checked", this.checked);
// });
// $("[name=items]:checkbox").click(function() {
//     var flag = true;
//     $("this").each(function() {
//         if (!this.checked) {
//             flag = false;
//         }
//     });
//     $("#all").prop("checked", flag);
//     $("#all1").prop("checked", flag);
// });
//
// $("#all1").click(function() {
//     $("[name=items]:checkbox").prop("checked", this.checked);
//     $("#all").prop("checked", this.checked);
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

$("#all").click(function() {
    $("[name=items]:checkbox").prop("checked", this.checked);
});

$("[name=items]:checkbox").click(function() {
    var flag = true;
    $("this").each(function() {
        if (!this.checked) {
            flag = false;
        }
    });
    $("#all").prop("checked", flag);
});
$("#all").click(function() {
    $("[name=items]:checkbox").prop("checked", this.checked);
    $("#all1").prop("checked", this.checked);
});
$("[name=items]:checkbox").click(function() {
    var flag = true;
    $("this").each(function() {
        if (!this.checked) {
            flag = false;
        }
    });
    $("#all").prop("checked", flag);
    $("#all1").prop("checked", flag);
});


$("#all1").click(function() {
    $("[name=items]:checkbox").prop("checked", this.checked);
    $("#all").prop("checked", this.checked);
});
$("[name=items]:checkbox").click(function() {
    var flag = true;
    $("this").each(function() {
        if (!this.checked) {
            flag = false;
        }
    });
    $("#all").prop("checked", flag);
    // $("#all1").prop("checked", flag);
});


var momId1s ='';
var momId1sAllInt;
var checkdDan=0;
//check剥削从复选框选择所有
function exportItem(selector,momId1){
    if(selector.checked === true){
        momId1s = momId;
        checkdDan=1;
        momId1sAllInt=momIdAllInt;
    }else {
        momId1sAllInt=0;
        // moiIds = moiIds.replace(moiId+",", '');
        momId1s='';
        checkdDan=0;
    }
}
function checkd(selector1,momId1){
    if (momId1s.indexOf(momId1) >= 0) {
        momId1s = momId1s.replace(momId1 + ',', '');
        if (momId1s!=undefined&&momId1s!='') {
            checkdDan=3;
        }else {
            checkdDan=0;
        }
    } else {
        momId1s += momId1 + ',';
        if (momId1s!=undefined&&momId1s!=null) {
             checkdDan=3;
        }else {
             checkdDan=0;
        }
    }
}
//移除所有全选
function delAll() {
    // alert(checkdDan);
    if (momId1sAllInt = mxListLenth){
        if(checkdDan==0){
            $.supper("alert",{ title:"操作提示", msg:"请选择商品"});
            return;
        }else if (checkdDan==1) {
                $.supper("alert",{ title:"操作提示", msg:"申领单至少有一条商品"});
            return;
        }
    }
    momId1sAllInt = mxListLenth
    if (mxListLenth<=1){
        $.supper("alert",{ title:"操作提示", msg:"申领单至少有一条商品!"});
        return;
    }
    var vdata="momId1s="+momId1s;
    $.supper("doservice", {
        "service": "MdOutOrderService.saveDeletemomId",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                // initDataMx();
                momId="";
                initDataMx();
                $.supper("alert",{ title:"操作提示", msg:"移除成功!"});
            } else{
                $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
            }
        }
    });
}
function delId(momId32) {
    if (mxListLenth<=1){
        $.supper("alert",{ title:"操作提示", msg:"申领单至少有一条商品"});
        return;
    }
    var a1s=momId32+",";
    var vdata="momId1s="+a1s;
    $.supper("doservice", {
        "service": "MdOutOrderService.saveDeletemomId",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                momId="";
                initDataMx();
                $.supper("alert",{ title:"操作提示", msg:"移除成功!"});
            } else{
                $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
            }
        }
    });
}
