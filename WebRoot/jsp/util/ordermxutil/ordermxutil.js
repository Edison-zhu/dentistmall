
function initXQList(select, dataList, mall) {
    if (mall === undefined || mall === null) {
        mall = true;
    }
    var peiSo=false;
    var str ='';
    if (dataList != null && dataList.length > 0) {
        if (dataList != null && dataList.length > 1) {
            // var totalMoney = 0;
            for (var i = 0; i < dataList.length - 1; i++) {
                var mat = dataList[i];
                str += "<div class=\"order-info\"><div class=\"left\" style=\"margin-left:10px\"><img src=\"" + mat.less_file_path + "\" width=\"68\" height=\"68\"><ul>";
                str += "<li class=\"name\"><a href=\"" + $.supper("getbasepath") + "xiangxi.htm?wmsMiId=" + mat.wms_mi_id + "\" target=\"_blank\">" + mat.mat_name + "</a></li>";
                str += "<li class=\"type\">型号：<strong>" + mat.NORM + "</strong></li>";
                str += "<li class=\"type\">编号：<strong>" + mat.mmf_code + "</strong></li>";
                str += "</ul></div>";
                str += "<div class=\"right\"><div class=\"money\">￥" + toDecimal2(mat.Unit_money) + "</div>"
                    + "<div class=\"number\">" + mat.mat_number + "</div>"
                    + "<div class=\"number\">" + (mat.number2 != null ? mat.number2 : "--") + "</div>"
                    + "<div class=\"number\">" + (mat.shure_number != null ? mat.shure_number : "0") + "</div>"
                    + "<div class=\"money red\">￥" + toDecimal2(mat.Total_money) + "</div>"
                    + "<div class=\"money red\">￥" + toDecimal2((mat.money1 != null ? mat.money1 : "0")) + "</div>";
                str += "</div></div>";
                if(peiSo!=true&&mat.mat_number!=mat.number2){
                    peiSo=true;
                }
                // totalMoney += Number(mat.Total_money);
            }if(peiSo!=true) {
                $("#test").attr({"readonly": "readonly"});
            }
            // if(mall === true) {
                $('#totalmoney').text(toDecimal2(dataList[dataList.length - 1].mx_total_money));
            // }
        }
        $("#" + select).html(str);
    }
    if (str == '') {
        str = '<div style="float: left;margin: 1px 44%;font-size: 15px;width: 500px;"><b>抱歉，搜索不到相关商品</b></div>';
        $("#matList").html(str);
    }
}
//发货
function sendMat(moiId){
    var vdata="moiId="+moiId;
    var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/sendOrderInfoNew"});
    $.supper("showTtemWin",{ "url":att_url,"title":"订单发货"});
}
//后台查看售后
function viewSupplierOrderAs(moiId) {
    var vdata = 'moiId=' + moiId;
    var att_url= $.supper("getServicePath", {"service":"","data":vdata,url:"/jsp/dentistmall/transaction/orderaftersale/supplierorderas"});
    $.supper("showTtemWin",{ "url":att_url,"title":"售后信息"});
}