/**
 * 20191225 yangfeng
 * 修改调用结构，增加懒加载功能
 * @type {null}
 */
var tips = null;
var limit = 5;
var page = 2; //懒加载是从第二页开始
var moiIdData = {}; //记录当前页面订单的lilmit，page
var option = {}; //server,用于分页查询
var width = 30;
var allMall = true; //商城or后台：true为商城
function initOderListUtil(opt) {
    option = $.extend(true, option, opt);
}

function initOderList(selector, dataList, mall) {
    if (mall === undefined || mall === null) {
        mall = true;
    }
    allMall = mall;
    if (allMall === false) {
        width = 25;
    }
    var str = "";
    if (dataList != null && dataList.length > 0) {
        for (var i = 0; i < dataList.length; i++) {
            var data = dataList[i];
            var mxList = data.orderMxList;
            var totalCount = mxList[mxList.length - 1].total_count;
            if (moiIdData[data.moiId] === undefined || moiIdData[data.moiId] === null) {
                moiIdData[data.moiId] = {};
            }
            moiIdData[data.moiId].data = data;
            moiIdData[data.moiId].limit = limit;
            moiIdData[data.moiId].page = page;
            //增加单选复选框
            if (allMall === true) {
            	str += "<div class=\"order1\"><div class=\"order-header\">"
            		 +"<input type=\"checkbox\" name=\"items\"  onclick=\"exportItem(this,"+data.moiId+")\" value="+data.moiId+">"
            		 + "<a class=\"blue font-weight-trigger\" href=\"" + $.supper("getbasepath") + "ddxq.htm?moiId=" + data.moiId + "\" target='_blank'><span class=\"l32\">订单号：<span id='highLightSpanDD'>" + data.orderCode + "</span></span></a>";	
			}else{
            	str += "<div class=\"order1\"><div class=\"order-header\">"
            		+"<input type=\"checkbox\" name=\"items\"  onclick=\"exportItem(this,"+data.moiId+")\" id='Check" + data.moiId+ "' value="+data.moiId+">"
                    + "<a class=\"blue font-weight-trigger\" onclick='viewSupplierOrder(" + data.moiId + ")' target='_blank'><span class=\"l32\">订单号：<span id='highLightSpanDD'>" + data.orderCode + "</span></span></a>";
            	}
            if (allMall === true) {
                str += "<span>供应商：<a class=\"blue font-weight-trigger\" href=\"" + $.supper("getbasepath") + "pcjxiangxi.htm?wzId=" + data.wzId + "\" target=\"_blank\">";

                if (data.applicantName.length > 10)
                    str += data.applicantName.substring(0, 9) + "...";
                else
                    str += data.applicantName;
                str += "</a></span>";
            }
            str += "<span>下单时间：<strong>" + data.placeOrderTime.substring(0, 16) + "</strong></span>";
            str += "<span style='margin-left: 28px'>集团医院门诊名称：<strong>" + data.purchaseUnit + "</strong></span>";
            // if(data.processStatus=='6' || data.processStatus=='5' || data.processStatus == "7")
            //     str += "<span>完结时间：<strong>"+data.endTime.substring(0,16)+"</strong></span>";
            str += '<div style=\'float: right;position: relative;right: 20px\'>';
            // str += "<a class=\"trigger\" style='background-color: #1f90d8; color: white' target='_blank' href=\"ddxq.htm?moiId="+data.moiId+"\">详情</a></div></div>";
            if (allMall === true) {
                /*str += "<a class=\"trigger\" href=\"javascript:main_export('" + data.moiId + "')\">导出采购单</a>"*/
                str += "<a class=\"trigger\" href=\"javascript:main_export_mx('" + data.moiId + "')\">导出详情</a>"
                str += "<a class=\"trigger\" href=\"javascript:buyAgain('" + data.moiId + "')\">再次购买</a>";
            } else {
            	 str += "<a class=\"trigger\" href=\"javascript:warehouse_export('" + data.moiId + "')\">导出拣货单</a>";
                if (data.processStatus != '2' && data.processStatus != '6') {
                    //str += "<a class=\"trigger\" href=\"javascript:main_exportC("+ data.processStatus + ",'" + data.moiId + "')\">导出配送单</a>";
                	  if (data.scopeBusiness == null && data.scopeBusiness == undefined) {
                          str += "<a class=\"trigger\" href=\"javascript:main_exportC("+ data.processStatus + ",'" + data.moiId + "')\"><img src=\"/dentistmall/img/blue.png\" width=\"30px\" height=\"20px\"/>导出出库单</a>"

                      } else {
                          str += "<a class=\"trigger\" href=\"javascript:main_exportC("+ data.processStatus + ",'" + data.moiId + "')\"><img src=\"/dentistmall/img/red.png\" width=\"30px\" height=\"20px\"/>导出出库单</a>"
                      }
                }
              
            }
            str += '</div></div>';
            str += "<div class=\"order-body\">";
            if (mxList != null && mxList.length > 0) {
                str += '<table id="table-mx' + data.moiId + '" style="width: 100%">'
                str += initTable(data, mxList);
                str += '</table>';
                if (totalCount > limit) {
                    str += '<div id="load-more' + data.moiId + '" style="text-align: center">' +
                        '<a id="clickLoadMore" class="trigger" onclick="onClickloadData(' + totalCount + ',\'' + data.moiId + '\')" style="display: block;background-color: #f9f9f9;font-size: 15px;padding: 4px">' +
                        '↓总共' + totalCount + '条，还剩下<span id="left-count' + data.moiId + '">' + (totalCount - limit) + '</span>条，点击加载更多' +
                        '</a></div>';
                }
            }
            str += "</div></div>";
        }
        $("#" + selector).html(str);
        /*  highLightSpanDD();*/
    }
    if (str == '') {
        str = '<div style="float: left;margin: 1px 44%;font-size: 15px;width: 500px;"><b>抱歉，搜索不到相关商品</b></div>';
        $("#" + selector).html(str);
    }
    if (selectOrderAll != undefined && selectOrderAll != null) {
        var checked = $("#all").prop("checked");
        var excludeCheck = excludeMoiIds;
        var moiCheck = moiIds
        if (checked) {
            $("[name=items]:checkbox").each(function () {
                // var flag = true;
                // $("this").each(function () {
                //     if (!this.checked) {
                //         flag = false;
                //     }
                // });
                if (excludeCheck.indexOf($(this).val() + ',') < 0) {
                    $(this).prop("checked", checked);
                }
            });
        } else {
            $("[name=items]:checkbox").each(function () {
                // var flag = true;
                // $("this").each(function () {
                //     if (!this.checked) {
                //         flag = false;
                //     }
                // });
                if (moiCheck.indexOf($(this).val() + ',') >= 0) {
                    $(this).prop("checked", true);
                }
            });
        }
    }
}

//仅仅生成table标签下的内容
function initTable(data, mxList, lazyLoad) {
    var str = '';
    var number3 = data.number3 ? data.number3 : 0;
    var processStatus = data.processStatus;
    for (var j = 0; j < mxList.length - 1; j++) {
        var mx = mxList[j];
        str += '<tr>';
        str += "<td style=\"width: 40%\"><div class=\"order-info\"><div class=\"left\" style='display: inline-block;'><a href=\"" + $.supper("getbasepath") + "xiangxi.htm?wmsMiId=" + mx.wms_mi_id + "\" target=\"_blank\"><img src=\"" + mx.less_file_path + "\" width=\"68\" height=\"68\"></a>";
        str += "<ul><li class=\"name\"><a href=\"" + $.supper("getbasepath") + "xiangxi.htm?wmsMiId=" + mx.wms_mi_id + "\" target=\"_blank\"><span id='highLightSpanDD'>" + mx.mat_name + "</span></a></li>";
        str += "<li class=\"type\">型号：<strong>" + mx.NORM + "</strong></li>";
        str += '<li class="type">编号：' + mx.mmf_code + '</li>';
        str += "</ul></div></div></td>";
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span class="money">￥' + toDecimal2(mx.Unit_money) + '</span></div></td>';
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span class="number">' + mx.mat_number + '</span></div></td>';
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span style="display: inline;line-height: 98px;width: 100%">';
        // if (data.processStatus == '1' || data.processStatus == '3' || data.processStatus == '4') {
            var number2 = mx.number2 == undefined ? 0 : mx.number2;
            if(processStatus == '5'){
                str += "<span>已完成</span><br/>";
            } else if(processStatus == '6'){
                if(mx.after_sale_state == '1'){
                    str += "<span>已退货</span><br/>";
                }else if(mx.after_sale_state == '2'){
                    str += "<span>已退款</span><br/>";
                }else {
                    str += "<span>已取消</span><br/>";
                }
            }else if (number2 <= 0) {
                str += "<span>待发货</span><br/>";
            } else if (number2 < mx.mat_number) {
                str += "<span>部分发货</span><br/>";
            } else if (number2 >= mx.mat_number) {
                str += "<span>已发货</span><br/>";
            }
        // }
         str += '</span></div></td>';
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span style="display: inline;line-height: 98px;width: 100%">';

        if (mx.after_sale_name != undefined) {
            if (allMall === true) {
                str += "<a href=\"" + $.supper("getbasepath") + "asmx.htm?masId=" + mx.mas_id + "\" class='trigger' style=\"color: blue\" target='_blank'><span class=\"money a\" style='text-decoration: underline;'>" + mx.after_sale_name + "</span></a><br />";
            } else {
                str += '<a onclick="viewSupplierOrderAsMx(' + mx.mas_id + ')" class="a" style="color: blue;text-decoration: underline;">' + mx.after_sale_name + '</a>';
            }
        }

        str += '</span></div></td>';
        // str += "<div class=\"unit\">"+mx.Basic_unit+"</div>";
        if (j == 0 && lazyLoad === undefined) {
            str += '<td id="row-chang' + data.moiId + '" rowspan="' + (mxList.length - 1) + '" class="rigth-td">';
            str += '<div class="right">';
            //鼠标悬停事件
            if (allMall === false) {
                 if (data.scopeBusiness == undefined || data.scopeBusiness == null) {
                     str += "<div style=\"text-align: center;display:inline-block;width: 20%\"><span class=\"money\">" + data.purchaseAccount + "</span><br/></div>";
                 } else {
                    str += "<div style=\"text-align: center;display:inline-block;width: 20%;min-height: 98px;vertical-align: top;\"><span class=\"money\">" + data.purchaseAccount + "</span><br/><span class=\"scopeBusinessImg\"><img id=\"scopeBusinessImg" + data.moiId + "\" src=\"/dentistmall/img/scopeBusiness.png\" width=\"20px\" height=\"20px\" onmouseover='showSpanMsg(this, \"" + data.scopeBusiness + "\")' onmouseout='hideSpanMsg()' style=\"margin-top:8px\"/></span></div>";
                 }
            }
            if(data.money2 != undefined && data.money2 != null){
                data.expressMoney = data.money2;
            }else if((data.placeOrderMoney-data.expressMoney)>=data.experssSupper){
            	data.expressMoney= 0;
            }
            if (data.payType != undefined && data.payType != 3) {
                str += "<div style=\"text-align: center;display:inline-block;width: 30%;min-height: 98px;vertical-align: top;\">" +
                    "<span class=\"money red\">￥<span id='pMoney" + data.moiId + "'>" + toDecimal2(data.placeOrderMoney) + "</span></span><br />" +
                    "<span class=\"express\" style=\"width:155px;margin-left: -15px\">(含快递12费:<span id='eMoney" + data.moiId + "'>" + toDecimal2(data.expressMoney) + "</span>)</span>";
                if (allMall === true) {
                    // if(data.scopeBusiness !== undefined && data.scopeBusiness !== 'undefined' && data.scopeBusiness !== null) {
/*                        str += "<br /><span class=\"scopeBusinessImg\"><img id=\"scopeBusinessImg" + data.moiId + "\" src=\"/dentistmall/img/scopeBusiness.png\" width=\"20px\" height=\"20px\" onmouseover='showSpanMsg(this, \"" + data.scopeBusiness + "\")' onmouseout='hideSpanMsg()'/></span>";
*/                    // }
                }
                str += "</div>";
                //月结
            } else if (data.payType == 3) {
                str += "<div style=\"text-align: center;display:inline-block;width: 30%;min-height: 98px;vertical-align: top;\">" +
                    "<img src=\"/dentistmall/img/money.png\" width=\"15px\" height=\"15px\"/><span class=\"money red\">￥<span id='pMoney" + data.moiId + "'>" + toDecimal2(data.placeOrderMoney) + "</span></span><br />" +
                    "<span class=\"express\" style=\"width:155px;margin-left: -15px\">(含快递费:<span id='eMoney" + data.moiId + "'>" + toDecimal2(data.expressMoney) + "</span>)</span>";
                if (allMall === true) {
                    // if(data.scopeBusiness !== undefined && data.scopeBusiness !== 'undefined' && data.scopeBusiness !== null) {
                        str += "<br /><span class=\"scopeBusinessImg\"><img id=\"scopeBusinessImg" + data.moiId + "\" src=\"/dentistmall/img/scopeBusiness.png\" width=\"20px\" height=\"20px\"  onmouseover='showSpanMsg(this, \"" + data.scopeBusiness + "\")' onmouseout='hideSpanMsg()' style=\"margin-top:8px\"/></span>";
                    // }
                }
                str += "</div>";
            } else {
                str += "<div style=\"text-align: center;display:inline-block;width: 30%;min-height: 98px;vertical-align: top;\">" +
                    "<span class=\"money red\">￥<span id='pMoney" + data.moiId + "'>" + toDecimal2(data.placeOrderMoney) + "</span></span><br />" +
                    "<span class=\"express\" style=\"width:155px;margin-left: -15px\">(含快递费:<span id='eMoney" + data.moiId + "'>" + toDecimal2(data.expressMoney) + "</span>)</span>";

                // str += "<br /><span class=\"scopeBusinessImg\"><img id=\"scopeBusinessImg" + data.moiId + "\" src=\"/dentistmall/img/scopeBusiness.png\" width=\"20px\" height=\"20px\" onmouseover='showSpanMsg(this, \"" + data.scopeBusiness + "\")' onmouseout='hideSpanMsg()' /></span>";
                str += "</div>";
            }
            if(data.processStatusName == '交易失败'){
                data.processStatusName = '交易关闭';
            }
            str += "<div style=\"text-align: center;display:inline-block;width: " + width + "%;min-height: 98px;vertical-align: top;\">" +
                "<span class=\"money\">" + data.processStatusName + "</span><br />";

            if (allMall === true) {

                str += "<a href=\"" + $.supper("getbasepath") + "ddxq.htm?moiId=" + data.moiId + "\" class='trigger' target='_blank'><span class=\"money a\">订单详情</span></a><br />";
            } else {

                str += "<a onclick='viewSupplierOrder(" + data.moiId + ")' class='trigger' target='_blank'><span class=\"money a\">订单详情</span></a><br />";
            }
            if (data.processStatus != '5') {
                str += "<a href=\"" + $.supper("getbasepath") + "ddxq.htm?moiId=" + data.moiId + "\" class='trigger' target='_blank'><span class=\"money a\">查看物流</span></a>";
            }
            if (allMall === true){
                if(mxList[mxList.length - 1].as_count !== undefined && mxList[mxList.length - 1].as_count > 0){
                    str += "<a href=\"" + $.supper("getbasepath") + "orderas.htm?moiId=" + data.moiId + "\" class='trigger' target='_blank'><span class=\"money a\" style=\"\">售后信息</span></a><br />";
                }
            }
            str += "</div>";
            str += "<div style=\"text-align: center;display:inline-block;width: " + width + "%;vertical-align: top;\"><div style='margin-top: 10px'></div>";
            if (data.needBill == '1') {
                if(data.billType == '2') {
                    str += "<span class=\"money \" onmouseover='showSpanMsg(this, \"公司发票抬头：" + (data.billHeard == undefined ? '' : data.billHeard) + "<br/>纳税人识别号：" + (data.billCode == undefined ? '' : data.billCode) + "\")' onmouseout='hideSpanMsg()'><img src='/dentistmall/img/bill.png' style='width: 20px;position: relative;top: -10px' /></span><br />";
                }else {
                    str += "<span class=\"money \" onmouseover='showSpanMsg(this, \"个人发票抬头：" + (data.billHeard == undefined ? '' : data.billHeard) + "\")' onmouseout='hideSpanMsg()'><img src='/dentistmall/img/bill.png' style='width: 20px;position: relative;top: -10px' /></span><br />";
                }
            }
            if(allMall === true) {
                // if(mxList[mxList.length - 1].as_count !== undefined && mxList[mxList.length - 1].as_count > 0){
                //     str += "<a href=\"" + $.supper("getbasepath") + "orderas.htm?moiId=" + data.moiId + "\" class='trigger' target='_blank'><span class=\"money a\" style=\"\">售后信息</span></a><br />";
                // }
                if ( _status != 7 && data.processStatus == '2') {
                    str += "<a class=\"btn btn-white\" style='margin-top: -12px' href=\"javascript:cancelMat('" + data.moiId + "')\">取消</a><br/>";
                    str += "<a class=\" btn btn-primary btn-sm\" style='height: 26px;line-height: 1.25;' href=\"" + $.supper("getbasepath") + "ddpay.htm?moiId=" + data.moiId + "\" >立即支付</a>";
                } else if ( _status != 7 && (data.processStatus == '3' || data.processStatus == '4'))
                    str += "<a class=\" btn btn-primary btn-sm\" href=\"" + $.supper("getbasepath") + "ddsh.htm?moiId=" + data.moiId + "\" style=\"margin-top: 0px;height: 26px;margin-top:-8px;line-height: 1.25;\">确认收货</a>";
                else if ( _status != 7 && (data.processStatus == "1" && (data.number1 == null || data.number1 == ''  || data.number1 == '0') && number3 == 0))
                    str += "<a class=\" btn btn-primary btn-sm\" style='width: 70px; height: 26px;margin-top: -8px;line-height: 1.25;' href=\"javascript:cancelMat('" + data.moiId + "')\" >取&nbsp;&nbsp;消</a>";
                if (_status != 7 && (data.processStatus == "3" || data.processStatus == "4" )){
                    str += "<a class=\" btn btn-primary btn-sm\" style='margin-top: 5px;height: 26px;line-height: 1.25;' href=\"" + $.supper("getbasepath") + "asapply.htm?moiId=" + data.moiId + "\" target='_blank' style=\"margin-top: 20px\">申请售后</a>";
                // } else if (mxList[mxList.length - 1].as_count !== undefined && mxList[mxList.length - 1].as_count < mxList[mxList.length - 1].total_count){
                //     str += "<a class=\" btn btn-primary btn-sm\" href=\"" + $.supper("getbasepath") + "asapply.htm?moiId=" + data.moiId + "\" target='_blank'>申请售后</a>";
                }else if (status != 7 &&data.processStatus == "5"){
                    str += "<a class=\" btn btn-primary btn-sm\" style='margin-top: -8px;height: 26px;line-height: 1.25;' href=\"" + $.supper("getbasepath") + "asapply.htm?moiId=" + data.moiId + "\" target='_blank' style=\"margin-top: 20px\">申请售后</a>";
                } /*else if ((data.number1 == null || data.number1 == '' || data.number1 == '0') && data.processStatus != '6' && data.processStatus != '7' && number3 == 0)
                    str += "<a class=\" btn btn-primary btn-sm\" href=\"javascript:backMat('" + data.moiId + "')\">退货</a>";*/

            } else {
                if(mxList[mxList.length - 1].as_count !== undefined && mxList[mxList.length - 1].as_count > 0){
                    str += "<a onclick='viewSupplierOrderAs(" + data.moiId + ")' class='trigger' target='_blank'><span class=\"money a\" style=\"margin-top: 20px\">售后信息</span></a><br />";
                }
                if (userRole != undefined && userRole != '' && (data.processStatus == '1' )){ //待发货|| data.processStatus == '4'
                    str += "<a class=\" btn btn-primary btn-sm\" onclick=\"sendMat('"+  data.moiId+"')\" style=\"margin-bottom:-50px\">立即发货</a>";
                }if (userRole != undefined && userRole != '' && (data.processStatus == '4')){
                    str += "<a class=\" btn btn-primary btn-sm\" onclick=\"sendMat('"+  data.moiId+"')\" style=\"margin-bottom:0px\">立即发货</a>";
                } else if (data.processStatus == '2') { //待付款，后台可以修改下单金额和运费
                    str += "<a class=\" btn btn-primary btn-sm\" onclick='changeMoney(" + data.moiId + ")' style=\"margin-top: 20px\">修改金额</a>";
                }
                //
                // else if (data.processStatus == '1' || data.processStatus == '3' || data.processStatus == '4')
                //     str += "<a class=\" btn btn-primary btn-sm\" href=\"" + $.supper("getbasepath") + "ddsh.htm?moiId=" + data.moiId + "\">确认收货</a>";
                // else if ((data.processStatus == "1" && (data.number1 == null || data.number1 == '' || data.number1 == '0') && number3 == 0))
                //     str += "<a class=\" btn btn-primary btn-sm\" href=\"javascript:cancelMat('" + data.moiId + "')\">取消</a>";
                // else if ((data.snumber1 == null || data.number1 == '' || data.number1 == '0') && data.processStatus != '6' && data.processStatus != '7' && number3 == 0)
                //     str += "<a class=\" btn btn-primary btn-sm\" href=\"javascript:backMat('" + data.moiId + "')\">退货</a>";
            }
            // str += "<span class=\"money\">确认收货</span>";
            str += "</div>";

            str += '</div>';
            str += '</td>';
        }
        str += '</tr>'

        // 如果超过显示限制，则显示新的一行数据，用于点击事件
    }

    return str;
}

function showSpanMsg(e, message) {
    if (message === undefined || message === 'undefined' || message === null) {
        message = '无信息';
    }
    tips = layer.tips(message, $(e), {tips: 3, time: 0});
}

function hideSpanMsg() {
    if (tips === null) {
        return;
    }
    layer.close(tips);
    tips = null;
}

function addPage() {
    page += 1;
}

function minPage() {
    page -= 1;
    if (page <= 0) {
        page = 1;
    }
}

//懒加载方法
function onClickloadData(totalCount, moiId) {
    if (moiId === undefined || moiId === 'undefined' || moiId === null) {
        return;
    }
    // $('#load-more' + moiId).remove();
    // $('#table-mx' + moiId).remove('#load-more' + moiId);
    var pageAndLimit = 'page=' + moiIdData[moiId].page + '&limit=' + moiIdData[moiId].limit;
    var data = 'moiId=' + moiId + '&' + pageAndLimit;
    if(_searchName !== '') {
        data += '&searchName=' + _searchName;
    }
    $.supper("doservice", {
        "service": option.server, "data": data, "BackE": function (jsondata) {
            var mxList = jsondata.items;
            var showCount = (moiIdData[moiId].page - 1) * limit;
            // mxList.splice(mxList.length - 1, 1);
            var mdata = moiIdData[moiId].data;
            showCount += mxList.length - 1;
            var str = initTable(mdata, mxList, true);
            $('#table-mx' + moiId).append(str);
            var rowSpan = $('#row-chang' + moiId).attr('rowspan');
            $('#row-chang' + moiId).attr('rowspan', rowSpan + mxList.length - 1);
            moiIdData[moiId].page += 1;
            $('#left-count' + moiId).text(totalCount - showCount);
            if (showCount >= totalCount) {
                $('#load-more' + moiId).remove();
            }
        }
    });
}
//导出配送单
function main_exportC(state, moiId){
    if(state == '1'){
        $.supper("alert", {title: '操作提示', msg: '请发货'});
        return;
    }
    var vdata="moiId=" + moiId;
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"MdOrderInfoService.exportSupplierInfoSheetC","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}
