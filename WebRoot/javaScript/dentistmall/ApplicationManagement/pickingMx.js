var mooId;
var url;
$(function () {
    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if(selOutOrderType != null && selOutOrderType.mooId1 != null) {
        mooId = selOutOrderType.mooId1;
        url=selOutOrderType.url;
    }
    initDataMx();
    seachAll(mooId);
});
//gjz
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
function initListMx(jsondata) {
    var mxList = jsondata;
    var str = "";
    var mooId1;
    var mmfCounts=0;
    var baseNumbers=0;
    let ratio;
    let baseNumber = 0;
    if (mxList != null && mxList.length > 0) {
        mmfCounts=mxList.length;
        // str+="<div style='width: 1200px'>"
        for (var i = 0; i < mxList.length; i++) {
            var data = mxList[i];
            ratio = data.ratio == undefined ? 1 : data.ratio;
            // mooId1=data.mooId;
            str += "<div class=\"listbox\"><div class=\"items\" style='height: 120px'><div class=\"imgbox\"><div class=\"img\"><img src=\""+data.rootPath+"\" width=\"68\" height=\"68\"  onclick='getNormMx("+data.wmsMiId+")' style='cursor: pointer'>";
            str +="</div>";
            str += "<div><ul style='float: left;margin-bottom: 0px;'><li class=\"name\" style='text-align: left;' onclick='getNormMx("+data.wmsMiId+")'><a>"+data.matName+"</a></li>";
            str += "<li class=\"type\" style='text-align: left;'>品牌：<strong><a target=\"_blank\">"+data.brand+"</a></strong></li>";
            str += "<li class=\"type\" style='text-align: left;'>规格型号：<strong><a target=\"_blank\">"+data.norm+"</a></strong></li>";
            str +="<li class=\"type\" style='text-align: left;'>物料编号："+(data.matCode !== undefined ? data.matCode : '')+"</li>";
            str += '<li class="\type\" style=\'text-align: left;\'>包装方式：'+(data.productName !== undefined ? data.productName : '')+'</li>';
            str += "</ul></div></div>";
            // str += "<div id=''><div class=\"\"  style='height: 135px;background-color: rgba(192, 192, 192, 0);border: 1px solid #F5F5F5;' class=\"order-info\"><div class=\"left\" style='margin-left: 20px;'><div style='margin-top: 33px;float: left;'><img src=\""+data.rootPath+"\" width=\"68\" height=\"68\"></div>";
            // str += "<ul style='float: left;margin-left: 20px;'><li class=\"name\" style='text-align: left;margin-top: 23px'><a>"+data.matName+"</a>";
            // str += "<li class=\"type\" style='text-align: left;margin-top: 10px'>规格型号：<strong><a target=\"_blank\">"+data.norm+"</a></strong></li>";
            // str +="<li class=\"type\" style='text-align: left;margin-top: 10px'>物料编号："+(data.matCode !== undefined ? data.matCode : '')+"</li>";
            // str += '<li class="\type\" style=\'text-align: left;margin-top: 10px\'>包装方式：'+(data.productName !== undefined ? data.productName : '')+'</li>';
            // str += "</ul></div>";
            // str += "<div class=\"right\" style='float: left;margin-top: 55px;margin-left:-30px'><div style='text-align: center;width:40px;height: 30px;' class=\"money\">"+data.baseNumber+(data.product_name !== undefined ? data.product_name : '')+"</div></div>";
            baseNumber = (data.split_quantity1 == undefined ? 0 : data.split_quantity1) - data.quantity * ratio;
            str += "<div class=\"rtbox\">"+(data.quantity !== undefined ? data.quantity : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+""+(baseNumber <= 0 ? 0 : baseNumber)+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</div>";
            str += "<div class=\"rtbox\">"+(data.baseNumber !== undefined ? data.baseNumber : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+""+(data.splitQuantity !== undefined ? data.splitQuantity : '0')+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</div>";
            str += "<div class=\"rtbox\">"+(data.number1 !== undefined ? data.number1 : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+""+(data.splitNumber1 !== undefined ? data.splitNumber1 : '0')+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</div>";
            if ((data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))>0) {
                str += "<div class=\"rtbox\" style='color: red'>"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+(data.basicUnit !== undefined ? data.basicUnit : '')+""+(data.splitQuantity-(data.splitNumber1 !== undefined ? data.splitNumber1 : '0'))+(data.splitUnit !== undefined ? data.splitUnit : '')+data.basicUnit+''+"</div>";
            }else {
                str += "<div class=\"rtbox\" style='color: red'>"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+(data.basicUnit !== undefined ? data.basicUnit : '')+""+(data.splitQuantity-(data.splitNumber1 !== undefined ? data.splitNumber1 : '0'))+(data.splitUnit !== undefined ? data.splitUnit : '')+data.basicUnit+''+"</div>";
            }
            baseNumbers+=parseInt(data.baseNumber);
            // str += "<div class=\"right\" style='float: left;margin-top: 55px;'><div style='text-align: center;width:200px;height: 30px;' class=\"money\">"+(data.orderTime!==undefined ? data.orderTime:'')+"</div></div>";
            str +="</div>";
        }
    }
    $("#mxList").html(str);
    $("#numberCount1").html(baseNumbers);
    $("#mmfCount").html(mmfCounts);
    // $("#hidden"+mooId1).show();
    // alert($("#hidden"+mooId1).val(str));
};

function getNormMx(wmsMiId) {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPickingMxFindId.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{wmsMiId: wmsMiId}});
    $.supper("showTtemWin",{ "url":att_url,"title":"新增领料申请详情"});
}

function seachAll(mooId) {
    var vdata="mooId="+mooId;
    $.supper("doservice", {
        "service": "MdOutOrderService.getPickingOrderInfo",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            $("#mooCode").val(jsondata.obj.mooCode);
            $("#remarks").html(jsondata.obj.remarks);
            $("#orderTime").val(jsondata.obj.orderTime);
            $("#flowState").html(jsondata.obj.flowState);
            // alert(jsondata.obj.flowState);
            if(jsondata.obj.flowState!="申请中"&&jsondata.obj.flowState!="部分出库"){
                // alert(1234);
                    $("#cancelAllbtn").attr("disabled", true);
                    $("#cancelAllbtn").css("background",'#DCDCDC');
                $("#cancelAllbtn").css("border",'1px solid #DCDCDC');
            }
        }
    });
}

//单个导出excel
function exportPickId(){
    mooId2s=mooId+",";
    var  vdata="moiIds="+mooId2s;
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"ExportExcelService.exportPick","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}

function backTo() {
    $.supper("setProductArray", {"name": "selOutOrderInfo", "value": null });
    setTimeout(function () {
        if (url != undefined) {
            $.supper("closeTtemWin", {url: url});
        }
    }, 200);
}
function cancelAll() {
    layer.alert('确定要撤销申请吗？',{
        icon: 1,
        skin: 'layer-ext-moon',
        //closeBtn: 2,
        btn: ['确定','取消'] //按钮
    },
        function (index) {
        console.log("111")
            layer.close(index);
            var vdata="mooId1="+mooId;
            $.supper("doservice", {
                "service": "MdOutOrderService.saveCancelAll",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        returnPicking()
                        // initData();
                        seachAll(mooId);
                        $.supper("alert",{ title:"操作提示", msg:"撤销成功!"});

                    } else{
                        $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
                    }
                }
            });
        }
    )
}
function returnPicking() {
        if (url != undefined) {
            $.supper("closeTtemWin", {url: url});
        }
}