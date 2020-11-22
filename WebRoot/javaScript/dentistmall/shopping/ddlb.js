var _all_limit_number = 5;
var _dd_search_action = 'ShoppingService.findOrderBySearch';
var selectOrderAll;
// 检查是否需要安全码
var getOpenSecurityService = 'sysUserService.getOpenSecurity';
var checkSecurityCode = 'sysUserService.updateOpenSecurityState';
$(function () {
    var $gaddress = $('#addressInfo');
    var $addressMask = $('#addressId');
    fLogin = {
        /* 打开弹窗 */
        'openLogin': function () {
            $gaddress.css({'top': '200px', 'left': ($(window).width() - $gaddress.width()) / 2 + 'px'}).fadeIn();
            $addressMask.fadeIn();
        },
        /* 关闭弹窗 */
        'closeLogin': function () {
            $gaddress.fadeOut();
            $addressMask.fadeOut();
        }
    };
    $('#addressClose').click(fLogin.closeLogin);
    initCount();
    initQhCount();
    $.supper('initPagination', {
        id: "Pagination",
        service: "ShoppingService.getOrderPager",
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initList
    });
    var timer1 = window.setTimeout(initQhCount, 5 * 60 * 1000);

    $('#prePageDD').on('click', function () {
        prePageDD();
    })
    $('#nextPageDD').on('click', function () {
        nextPageDD();
    })
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#searchDdStartTime'
            // ,type: 'year'
            , range: "~" //或 range: '~' 来自定义分割字符
        });
    });
    // laydate.render({
    // 	elem: '#searchDdStartTime'
    // 	,type: 'datetime'
    // 	,range: true //或 range: '~' 来自定义分割字符
    // });

    $('.clickA').on('click', function () {
		var thisId = $(this).attr('id');
		$('a[id^="_item"]').each(function () {
			var id = $(this).attr('id');
            $(this).removeClass('taps-item-active');
            $(this).removeClass('taps-item');
            if (thisId == id) {
                $(this).addClass('taps-item-active');
            } else {
                $(this).addClass('taps-item');
            }
        })
    })
    initOderListUtil({server: 'ShoppingService.getOrderMxModelByMoiId'});

    $('#searchDdName').on('keyup', function () {
        if (event.keyCode == 13) {
            searchDd();
        }
    })
});
var _status = '';

function changeType(status, vid) {
    initCount();
    $('#searchDdName').val('');
    $('#searchDDState').val('');
    $("#_item").removeClass();
    $("#_item1").removeClass();
    $("#_item2").removeClass();
    if (status != "") {
        $('#searchDDState').hide();
    } else {
        $('#searchDDState').show();
    }

    _status = status;
    _searchName = '';
    $('#searchDdName').val('');
    $(".box_tittle").children().removeClass();
    $("#" + vid).addClass("qhbeij");
    var vdata = "1=1";
    if (status != null && status != "")
        var vdata = "processStatus=" + status;
    $.supper('initPagination', {
        id: "Pagination",
        service: "ShoppingService.getOrderPager",
        data: vdata,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initList
    });
}

function initList(dataList) {
    initOderList('divList', dataList, true);
}

// function initList(dataList){
// 	var str = "";
// 	if(dataList != null && dataList.length > 0){
// 		for(var i =0;i < dataList.length;i++){
// 			var data = dataList[i];
// 			var mxList = data.orderMxList;
// 			var number3 = data.number3?data.number3:0;
// 			str +="<div class=\"order\"><div class=\"order-header\">"
// 				+"<a class=\"blue font-weight-trigger\" href=\"ddxq.htm?moiId="+data.moiId+"\" target='_blank'><span class=\"l32\">订单号：<span id='highLightSpanDD'>"+data.orderCode+"</span></span></a>";
// 			str +="<span>供应商：<a class=\"blue font-weight-trigger\" href=\"pcjxiangxi.htm?wzId="+data.wzId+"\" target=\"_blank\">";
// 			if(data.applicantName.length >10)
// 				str += data.applicantName.substring(0,9)+"...";
// 			else
// 				str += data.applicantName;
// 			str +="</a></span>";
// 			str +="<span>订单状态：<strong>"+data.processStatusName+"</strong></span>";
// 			str +="<span>下单时间：<strong>"+data.placeOrderTime.substring(0,16)+"</strong></span>";
// 			if(data.processStatus=='6' || data.processStatus=='5' || data.processStatus == "7")
// 				str += "<span>完结时间：<strong>"+data.endTime.substring(0,16)+"</strong></span>";
// 			if(data.processStatus=='2'||data.processStatus=='3'||data.processStatus=='4')
// 				str +="<a class=\"trigger\" href=\"ddsh.htm?moiId="+data.moiId+"\">收货</a>";
// 			if((data.processStatus=="1" && (data.number1==null || data.number1=='' || data.number1=='0') && number3 == 0))
// 				str +="<a class=\"trigger\" href=\"javascript:cancelMat('"+  data.moiId+"')\">取消</a>";
// 			else if((data.number1==null || data.number1=='' || data.number1=='0') && data.processStatus!='6' && data.processStatus!='7' && number3==0)
// 				str +="<a class=\"trigger\" href=\"javascript:backMat('"+  data.moiId+"')\">退货</a>";
// 			str += "<a class=\"trigger\" href=\"javascript:main_export('"+  data.moiId+"')\">导出</a>"
// 			str += "<a class=\"trigger\" style='background-color: #1f90d8; color: white' target='_blank' href=\"ddxq.htm?moiId="+data.moiId+"\">详情</a></div>";
// 			str +="<div class=\"order-body\">";
// 			if(mxList != null && mxList.length > 0){
// 				for(var j =0;j < mxList.length;j++){
// 					var mx= mxList[j];
// 					str += "<div class=\"order-info\"><div class=\"left\"><a href=\"xiangxi.htm?wmsMiId="+mx.wms_mi_id+"\" target=\"_blank\"><img src=\""+mx.less_file_path+"\" width=\"68\" height=\"68\"></a>";
// 					str += "<ul><li class=\"name\"><a href=\"xiangxi.htm?wmsMiId="+mx.wms_mi_id+"\" target=\"_blank\"><span id='highLightSpanDD'>"+mx.mat_name+"</span></a></li>";
// 					str += "<li class=\"type\">型号：<strong>"+mx.NORM+"</strong></li>";
// 					str += '<li class="type">编号：'+mx.mmf_code+'</li>';
// 					str += "</ul></div>";
//
// 					str += "<div class=\"right\"><div class=\"money\">￥"+toDecimal2(mx.Unit_money)
// 						+"</div><div class=\"number\">"+mx.mat_number+"</div><div class=\"unit\">"+mx.Basic_unit+"</div>";
// 					str += "<div class=\"money red\">￥"+toDecimal2(mx.Total_money)+"</div></div>";
// 					str +="</div>";
// 				}
// 			}
// 			str +="</div></div>";
// 		}
// 		$("#divList").html(str);
// 		highLightSpanDD();
// 	}
// 	if(str == ''){
// 		str = '<div style="float: left;margin: 1px 44%;font-size: 15px;width: 500px;"><b>抱歉，搜索不到相关商品</b></div>';
// 		$("#divList").html(str);
// 	}
// }
function highLightSpanDD() {
    var serachDdName = $('#searchDdName').val();
    if (serachDdName == '') {
        return;
    }
    $(".order").find('span[id^=highLightSpanDD]').each(function () {
        var reStr = $(this).text();
        var that = this;
        var reg = new RegExp(serachDdName, 'gi');
        reStr = reStr.replace(reg, '<span class="highLight" style="margin-right:0px">' + serachDdName + '</span>');
        $(that).html(reStr);
    });
}

function initCount() {
    $.supper("doservice", {
        "service": "ShoppingService.countOrder", "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                $("#allCount").html(jsondata.obj.all);
                $("#dfhCount").html(jsondata.obj.dfh);
                $("#dfkCount").html(jsondata.obj.dfk);
                $("#dshCount").html(jsondata.obj.dsh);
                $("#bfhCount").html(jsondata.obj.bfh);
                $("#succCount").html(jsondata.obj.succ);
                $("#failCount").html(jsondata.obj.fail);
                $("#shCount").html(jsondata.obj.sh);
            }
        }
    });
}

function backMat(moiId) {
    var title = "退货操作";
    var msg = "确认退货操作？";
    var vdata = "moiId=" + moiId;
    $.supper("confirm", {
        title: title, msg: msg, yesE: function () {
            $.supper("doservice", {
                "service": "MdOrderInfoService.saveBackOrderInfo", "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: reSearch});
                    } else
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                }
            });
        }
    });
}
// 验证安全码，通过执行相关方法
function checkSecurityCodeFunc() {
    let securityCode = $('#securityCode').val();
    if (securityCode == '') {
        $.supper("alert", {
            title: "操作提示",
            msg: "请输入验证码！"
        });
        return;
    }
    $.supper("doservice", {
        "service": checkSecurityCode, "ifloading": 1, 'data': 'securityCode=' + securityCode, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                fLogin.closeLogin();
                cancelMats(moiIds);
            } else
                $.supper("alert", {
                    title: "操作提示",
                    msg: jsondata.meg
                });
        }
    });
}
var moiIds = ''
function cancelMat(moiId){
    moiIds = moiId
    console.log('获取是否需要安全码--------')
    // 获取是否需要安全码
    $.supper("doservice", {
        "service": getOpenSecurityService, "ifloading": 1, "BackE": function (jsondata) {
            console.log('获取是否需要安全码--------',jsondata)
            if (jsondata.code == "1") {
                // 需要安全码则输入安全码
                fLogin.openLogin();
            } else if (jsondata.code == '2') {
                // 不需要, 直接走确认付款
                cancelMats(moiId);
            } else if (jsondata.code == '3') {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "未登录！"
                });
            } else {
                $.supper("alert", {
                    title: "操作提示",
                    msg: "操作失败！"
                });
            }
        }
    });
}
function cancelMats(moiId) {
    var vdata = "moiId=" + moiId;
    $.supper("confirm", {
        title: "取消操作", msg: "确认取消操作？", yesE: function () {

            $.supper("doservice", {
                "service": "MdOrderInfoService.saveCancelBackInfo", "data": vdata, "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        $.supper("alert", {title: "操作提示", msg: "操作成功！", BackE: reSearch});
                    } else if (jsondata.code == "2") {
                        $.supper("alert", {title: "操作提示", msg: "状态已改变，请刷新！"});
                    } else {
                        $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                    }
                }
            })
        }
    });
}

function reSearch() {
    var vdata = "1=1";
    if (_status != null && _status != "")
        vdata = "processStatus=" + _status;
    $.supper('initPagination', {
        id: "Pagination",
        service: "ShoppingService.getOrderPager",
        data: vdata,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initList
    });
    initCount();
}

function initQhCount() {
    $.supper("doservice", {
        "service": "MdNewsInfoService.getInventoryNewList", "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                if (jsondata.obj != null) {
                    if (jsondata.obj.length > 0)
                        $("#newsCount").html(jsondata.obj.length);
                    else
                        $("#newsCount").html("");
                }
            }
        }
    });
}

function main_export(id) {
    var vdata = "moiId=" + id;
    var newTab = window.open('about:blank');
    $.supper("doservice", {
        "service": "MdOrderInfoService.exportInfo", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

function main_export_mx(id) {
    var vdata = "moiId=" + id;
    var newTab = window.open('about:blank');
    $.supper("doservice", {
        "service": "MdOrderInfoService.exportInfo", "data": vdata, "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href = jsondata.obj.path;
            } else
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
        }
    });
}

//订单列表查询
var _searchName = '';
function searchDd() {
    var serachDdName = 'searchName=' + $('#searchDdName').val();
    _searchName = $('#searchDdName').val();
    var searchDDState = 'processStatus=' + $('#searchDDState').val();
    var searchDate = 'placeOrderTime=' + $('#searchDdStartTime').val();
    var searchPayType = 'payType=' + $('#searchDDPayType').val();
    var data = serachDdName + '&' + searchDDState + '&' + searchDate + '&' + searchPayType + '&processStatus2=' + _status;
    $.supper('initPagination', {
        id: "Pagination",
        service: _dd_search_action,
        "data": data,
        limit: _all_limit_number,
        isAjax: "1",
        "BackE": initList
    });
}

function resetSearch() {
    $('#searchDdName').val('');
    $('#searchDDState').val('');
    $('#searchDdStartTime').val('');
    $('#searchDDPayType').val('');
    _searchName = '';
    $('#searchDdName').val('');
    searchDd();
}

//搜索边上的上一页下一页
function prePageDD() {
    var pre = document.querySelector('#prev');
    if (pre !== null) {
        pre.click();
    }
}

function nextPageDD() {
    var next = document.querySelector('#next');
    if (next !== null) {
        next.click();
    }
}

function buyAgain(moiId) {
    var data = 'moiId=' + moiId;
    $.supper("doservice", {
        "service": "ShoppingService.buyAgain", "data": data, "BackE": function (jsondata) {
            var items = jsondata.items;
            if (items.length <= 0) {
                $.supper("alert", {title: "操作提示", msg: "操作失败！"});
                return false;
            }
            var mmfIds = '';
            var shus = '';
            $.each(items, function (key, item) {
                mmfIds += item.mmf_id + ',';
                shus += item.mat_number + ',';
            })
            mmfIds = mmfIds.substring(0, mmfIds.length - 1);
            shus = shus.substring(0, shus.length - 1);
            window.location.href = "ddqr.htm?mmfIds=" + mmfIds + "&shus=" + shus;
        }
    });
}
//订单列表批量导出订单信息功能 ***********start**************
/**
 * 批量导出全部订单方法
 * 
 */
var moiIds = '';
function exportItem(selector,moiId){
		if(selector.checked === true){
			moiIds += moiId+",";
	     }else {
	    	 moiIds = moiIds.replace(moiId+",", '');
	     }
	}
/**
 * 增加复选框全选/取消
 */
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
function export_batch_order(){
	var rows=moiIds;
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}
	rows=rows.substring(0,rows.length-1);
	var vdata="";
	if (rows==-1) {
	    var serachDdName = 'searchName=' + $('#searchDdName').val();
	    var searchDDState = 'processStatus=' + $('#searchDDState').val();
	    var searchDate = 'placeOrderTime=' + $('#searchDdStartTime').val();
	    var searchPayType = 'payType=' + $('#searchDDPayType').val();
	    vdata = serachDdName + '&' + searchDDState + '&' + searchDate + '&' + searchPayType;
	}else{
		vdata="moiIds="+rows;
	}
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportCgOrderList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

//批量导出账单信息
function export_batch_order1(){
	var rows=moiIds;
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}
	rows=rows.substring(0,rows.length-1);
	var vdata="";
	if (rows==-1) {
	    var serachDdName = 'searchName=' + $('#searchDdName').val();
	    var searchDDState = 'processStatus=' + $('#searchDDState').val();
	    var searchDate = 'placeOrderTime=' + $('#searchDdStartTime').val();
	    var searchPayType = 'payType=' + $('#searchDDPayType').val();
	    vdata = serachDdName + '&' + searchDDState + '&' + searchDate + '&' + searchPayType;
	}else{
		vdata="moiIds="+rows;
	}
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportDzOrderList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

//订单列表批量导出订单信息功能 ***********end**************
