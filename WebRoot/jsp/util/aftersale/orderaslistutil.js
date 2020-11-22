/**
 * 20191231 yangfeng
 * 增加售后列表
 * @type {null}
 */
var tips = null;
var limit = 5;
var page = 2; //懒加载是从第二页开始
var masIdData = {}; //记录当前页面订单的lilmit，page
var option = {}; //server,用于分页查询
var width = 30;
var allMall = true; //商城or后台：true为商城
function initOderListUtil(opt) {
    option = $.extend(true, option, opt);
}

function initOderAsList(selector, dataList, mall) {
    if (mall === undefined || mall === null) {
        mall = true;
    }
    allMall = mall;
    if (allMall === false) {
        width = 25;    }
    var str = "";
    if (dataList != null && dataList.length > 0) {
        for (var i = 0; i < dataList.length; i++) {
            var data = dataList[i];
            var mxList = data.asMxList;
            var totalCount = mxList[mxList.length - 1].total_count;
            if (masIdData[data.masId] === undefined || masIdData[data.masId] === null) {
                masIdData[data.masId] = {};
            }
            masIdData[data.masId].data = data;
            masIdData[data.masId].limit = limit;
            masIdData[data.masId].page = page;
            str += "<div class=\"order1\"><div class=\"order-header\">";
            if(allMall == true) {
                str += "<a class=\"blue font-weight-trigger\" href=\"" + $.supper("getbasepath") + "asmx.htm?masId=" + data.masId + "\" target='_blank'><span class=\"l32\">售后单号：<span id='highLightSpanDD'>" + data.masCode + "</span></span></a>";
            } else {
                str += "<a class=\"blue font-weight-trigger\"  onclick='viewSupplierOrderAsMx(" + data.masId + ")' target='_blank'><span class=\"l32\">售后单号：<span id='highLightSpanDD'>" + data.masCode + "</span></span></a>";
            }
            if (allMall === true) {
                // str += "<span>供应商：<a class=\"blue font-weight-trigger\" href=\"" + $.supper("getbasepath") + "pcjxiangxi.htm?wzId=" + data.wzId + "\" target=\"_blank\">";
                //
                // if (data.applicantName.length > 10)
                //     str += data.applicantName.substring(0, 9) + "...";
                // else
                //     str += data.applicantName;
                // str += "</a></span>";
            }
            str += "<span>创建时间：<strong>" + data.createDate.substring(0, 16) + "</strong></span>";
            str += '<div style=\'float: right;position: relative;right: 20px\'>';
            if (allMall === true) {
                // str += "<a class=\"trigger\" href=\"javascript:main_export('" + data.masId + "')\">导出</a>"
                // str += "<a class=\"trigger\" href=\"javascript:main_export_mx('" + data.masId + "')\">导出详情</a>"
                // str += "<a class=\"trigger\" href=\"javascript:buyAgain('" + data.masId + "')\">再次购买</a>";
            } else {
                // if (data.scopeBusiness == null && data.scopeBusiness == undefined) {
                //     str += "<a class=\"trigger\" href=\"javascript:main_export('" + data.masId + "')\"><img src=\"/dentistmall/img/blue.png\" width=\"30px\" height=\"20px\"/>导出</ a>"
                //
                // } else {
                //     str += "<a class=\"trigger\" href= \"javascript:main_export('" + data.masId + "')\"><img src=\"/dentistmall/img/red.png\" width=\"30px\" height=\"20px\"/>导出</ a>"
                // }
            }
            str += '</div></div>';
            str += "<div class=\"order-body\">";
            if (mxList != null && mxList.length > 0) {
                str += '<table id="table-mx' + data.masId + '" style="width: 100%">'
                str += initTable(data, mxList);
                str += '</table>';
                if (totalCount > limit) {
                    str += '<div id="load-more' + data.masId + '" style="text-align: center">' +
                        '<a id="clickLoadMore" class="trigger" onclick="onClickloadData(' + totalCount + ',\'' + data.masId + '\')" style="display: block;background-color: #f9f9f9;font-size: 15px;padding: 4px">' +
                        '↓总共' + totalCount + '条，还剩下<span id="left-count' + data.masId + '">' + (totalCount - limit) + '</span>条，点击加载更多' +
                        '</a></div>';
                }
            }
            str += "</div></div>";
        }
        $("#" + selector).html(str);
    }
    if (str == '') {
        str = '<div style="float: left;margin: 1px 44%;font-size: 15px;width: 500px;"><b>抱歉，搜索不到相关商品</b></div>';
        $("#" + selector).html(str);
    }
}

//仅仅生成table标签下的内容
function initTable(data, mxList, lazyLoad) {
    var str = '';
    var number3 = data.number3 ? data.number3 : 0;
    for (var j = 0; j < mxList.length - 1; j++) {
        var mx = mxList[j];
        str += '<tr>';
        str += "<td style=\"width: 44%\"><div class=\"order-info\"><div class=\"left\" style='display: inline-block;'><a href=\"" + $.supper("getbasepath") + "xiangxi.htm?wmsMiId=" + mx.wms_mi_id + "\" target=\"_blank\"><img src=\"" + mx.less_file_path + "\" width=\"68\" height=\"68\"></a>";
        str += "<ul><li class=\"name\"><a href=\"" + $.supper("getbasepath") + "xiangxi.htm?wmsMiId=" + mx.wms_mi_id + "\" target=\"_blank\"><span id='highLightSpanDD'>" + mx.mat_name + "</span></a></li>";
        str += "<li class=\"type\">型号：<strong>" + mx.NORM + "</strong></li>";
        str += '<li class="type">编号：' + mx.mmf_code + '</li>';
        str += "</ul></div></div></td>";
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span class="money">￥' + toDecimal2(mx.Unit_money) + '</span></div></td>';
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span class="number">' + mx.mat_number + '</span></div></td>';
        str += '<td class="td-text-align" style="width: 7%"><div class=\"order-info\"><span style="display: inline;line-height: 98px;width: 100%"></span></div></td>';
        str += '<td class="rigth-td" style="">';
        str += "<div style=\"text-align: center;display:inline-block;width: 39%;min-height: 98px;vertical-align: top;line-height: 98px\">" +
            "￥<span class=\"money\">" + toDecimal2(mx.Total_money) + "</span></div>";
        str += '<div class="right" style="display:inline-block;width: ' + width + '%">';
        str += "<div style=\"text-align: center;min-height: 98px;vertical-align: top;\">" +
            "<span class=\"money\">" + (mx.after_sale_name != undefined ? mx.after_sale_name : '已取消') + "</span><br />";
        if (allMall === true) {
            str += "<a href=\"" + $.supper("getbasepath") + "asmx.htm?masId=" + data.masId + "\" class='trigger' target='_blank'><span class=\"money a\">售后详情</span></a><br />";
        } else {
            str += "<a onclick='viewSupplierOrderAsMx(" + data.masId + ")' class='trigger' target='_blank'><span class=\"money a\">售后详情</span></a><br />";
        }
        // if(data.remarks !== undefined && data.remarks !== '') {
        str += "<span class=\"scopeBusinessImg\"><img id=\"scopeBusinessImg\" src=\"/dentistmall/img/scopeBusiness.png\" width=\"20px\" height=\"20px\" onmouseover='showSpanMsg(this, \"" + data.remarks + "\")' onmouseout='hideSpanMsg()'/></span>";
        // }
        str += "</div>";
        str += '</div>';
        str += '</td>';
        str += '</tr>'
    }
    return str;
}

function showSpanMsg(e, message) {
    if (message === undefined || message === 'undefined' || message === null || message === '') {
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

//懒加载方法
function onClickloadData(totalCount, masId) {
    if (masId === undefined || masId === 'undefined' || masId === null) {
        return;
    }
    var pageAndLimit = 'page=' + masIdData[masId].page + '&limit=' + masIdData[masId].limit;
    var data = 'masId=' + masId + '&' + pageAndLimit;
    if (_searchName !== ''){
        data += '&searchName=' + _searchName;
    }
    $.supper("doservice", {
        "service": option.server, "data": data, "BackE": function (jsondata) {
            var mxList = jsondata.items;
            var showCount = (masIdData[masId].page - 1) * limit;
            var mdata = masIdData[masId].data;
            showCount += mxList.length - 1;
            var str = initTable(mdata, mxList, true);
            $('#table-mx' + masId).append(str);
            masIdData[masId].page += 1;
            $('#left-count' + masId).text(totalCount - showCount);
            if (showCount >= totalCount) {
                $('#load-more' + masId).remove();
            }
        }
    });
}