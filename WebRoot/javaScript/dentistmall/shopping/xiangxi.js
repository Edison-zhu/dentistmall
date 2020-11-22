var mmfCodes="";
var _xiangxi_add_carts_action = 'ShoppingService.addCartsPlus';
$(document).ready(function() {
	mmfCodes=$("#mmfCodes").val();
	$(".jqzoom").jqueryzoom({
		xzoom:400,
		yzoom:300,
		offset:10,
		position:"right",
		preload:1,
		scalex:5,
		lens:true
	});
	
	$("#floatShow").click(function() {
		$("#onlineService").toggle();
		$("#floatShow").hide();
		$("#floatHide").show();

	});

	$("#floatHide").click(function() {
		$("#onlineService").toggle();
		$("#floatHide").hide();
		$("#floatShow").show();
	});
	var isLogin=$("#isLogin").val();
	if(isLogin=="1") {
		initPriceLine();
		checkXiangxiFavorites();
	}
	setEvaluateList();
});

function checkXiangxiFavorites(){
	var _wmsMiId = $.supper("getParam", "wmsMiId");
	checkViewFav(_wmsMiId, 'xiangxi-product-operation-fav');
	checkXiangxiFav(_wmsMiId);
}

function  addEvaluate(id){
	var isLogin=$("#isLogin").val();
	if(isLogin!="1"){
		fLogin.openLogin();
		return;
	}
	var _wmsMiId = $.supper("getParam", "wmsMiId");
	var _all_win_url_edit = "/jsp/dentistmall/evaluate/updateEvaluate.jsp";
	var _all_table_Id = "rbbId";
	var _all_edit_icon = "comment";
	var _all_edit_title = "价格点评";
	var _all_edit_width = 600;
	var _all_edit_height = 500;
	var att_url= $.supper("getServicePath", {url:_all_win_url_edit,data:"wmsMiId="+_wmsMiId});
	 var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:setEvaluateList}); 
		 
}



function setEvaluateList(){  
	var att_wmsMiId= $.supper("getParam", "wmsMiId");
	var data ="wmsMiId="+att_wmsMiId;
	$.supper('initPagination',{id:"Pagination_evt",service:"MdEvaluateService.getPagerModelObject",data:data,limit:50,isAjax:"1","BackE":initLists});
}
function initLists(dataList){
	var str = "";
	var suiId=$("#suiId").val();
	if(dataList != null && dataList.length > 0){ 
		$("#evaluateNullList").hide();
		$("#evaluateList").show();
		var isLogin=$("#isLogin").val();
		for(var i =0;i < dataList.length;i++){
			var data = dataList[i]; 
			var att_div01="<span style=\"margin-left:5px;margin-right:5px;\">"+(data.evaluateRen?data.evaluateRen:"")+"</span>|<span style=\"margin-left:5px;\">"+(data.evaluateTime?data.evaluateTime:"")+"<span>";
			var att_div02= "<span style=\"margin-left:5px;margin-right:5px\"> 商品："+(data.matName?data.matName:"")
							+"</span>|<span style=\"margin-left:5px;margin-right:5px\">型号："+(data.mmfName?data.mmfName:"")+"</span>|";
							
			if(isLogin=='1')
				att_div02 += "<span style=\"margin-left:5px;margin-right:5px\">单价：￥"+(data.price?data.price:"")+"</span>|";
			att_div02 +="<span style=\"margin-left:5px;margin-right:5px\">";
			if (data.priceReview=="1"){
				att_div02+="<img src=\"img/xiao1.png\"/>&nbsp;价格有优势";
			}
			if (data.priceReview=="2"){
				att_div02+="<img src=\"img/xiao2.png\"/>&nbsp;价格一般";
			}
			
			if (data.priceReview=="3"){
				att_div02+="<img src=\"img/xiao3.png\"/>&nbsp;价格无优势";
			}
			
			if (data.priceComparison=="1"){
				att_div02+="</span>|<span style=\"margin-left:5px;margin-right:5px\"><img src=\"img/jiantou.png\"/>&nbsp;比较其他渠道较低"+(data.difference?"￥"+data.difference:"");
			}
			if (data.priceComparison=="2"){
				att_div02+="</span>|<span style=\"margin-left:5px;margin-right:5px\"><img src=\"img/jiantou1.png\"/>&nbsp;比较其他渠道较高"+(data.difference?"￥"+data.difference:"");
			}
			att_div02+="</span>|<span style=\"margin-left:5px;\">预购数量："+(data.preOrderNumber?data.preOrderNumber:"")+"</span>";
			if(suiId!=null && data.suiId==suiId)
				att_div02+="<a style=\"float:right;\" href=\"javascript:delMdEvaluate('"+data.mevaId+"')\"><img src=\"img/del.png\;\"/></a>";
			var att_div03=data.reviewContent?data.reviewContent:"";
			var touPath="img/tou.png";
			if(data.barRootPath!=null && data.barRootPath!="")
				touPath=data.barRootPath;
			str +='<table width="1020" border="1" style="color:#808C8A">'+
			 ' <tr >'+
			 '   <td width="65" height="65" rowspan="3" valign="top"><img src="'+touPath+'" width="65" height="65" /></td>'+
			  '  <td width="939" height="25">'+att_div01+'</td>'+
			 ' </tr>'+
			 ' <tr>'+
			 '   <td height="28">'+att_div02+'</td>'+
			 ' </tr>'+
			 ' <tr>'+
			 '   <td style="background-color:#F8F8F8;padding:10px 5px;height:50px">'+att_div03+'</td>'+
			 ' </tr>'+
			'</table> <hr style="margin-top:5px;margin-bottom:5px;"/>';
		}
		$("#contentList").html(str);  
		$("#evaluateTitle").html("("+dataList.length+")");
	}else { 
		$("#evaluateList").hide();
		$("#evaluateNullList").show();
	}
	
}

function removeTr(mmfCode) {
	var clas = $("#li_"+mmfCode).attr("class");
	if(clas=="selected"){
		if((mmfCodes.split(',').length -1)>1){
			$("#li_"+mmfCode).removeClass();
			$("#tr_"+mmfCode).remove();
			mmfCodes=mmfCodes.replace(mmfCode+",","");
			$("#gwcListTable").remove("#tr_" + mmfCode);
		}
	}
	if((mmfCodes.split(',').length -1) == 1){
		showOrHideRemoveBtn(false);
	}
}
function showOrHideRemoveBtn(showOrHide){
	if(showOrHide === true) {
		$('input[id^="remove_"]').each(function () {
			$(this).show();
		})
	} else {
		$('input[id^="remove_"]').each(function () {
			$(this).hide();
		})
	}

}

function selFormate(mmfCode,mmfName,price,mmfId){
	var isLogin=$("#isLogin").val();
	var clas = $("#li_"+mmfId).attr("class");
	if(clas=="selected"){
		if((mmfCodes.split(',').length -1)>1){
			$("#li_"+mmfId).removeClass();
			$("#tr_"+mmfId).remove();
			mmfCodes=mmfCodes.replace(mmfId+",","");
			$("#gwcListTable").remove("#tr_" + mmfId);
		}
	}else{
		$("#li_"+mmfId).removeClass();
		$("#li_"+mmfId).addClass("selected");
		var str = "<tr id=\"tr_"+mmfId+"\">";
		str += "<td><div class=\"product-info-choose-count m-top20\">";//<span>购买数量：</span>";
		str += '<div style="display: inline"><a class="nameButton" disabled><span class="product-info-inventory ">' + mmfName + '</span></a>';
		str += "</div>";
		str += '</div></td>';
		str += '<td style="width: 100px;text-align: left" >';
		str += '<div class="m-top20"><span style="margin-left: 6px" class="product-info-inventory">No.'+mmfCode+'</span></div>';
		str += '</td>'
		if(isLogin=='1'){
			str += "<td style=\"text-align: center\"><div class=\"product-info-choose-count m-top20\"><span class=\"product-info-inventory \"><font style=\"color:#e64419\">￥"+toDecimal2(price)+"</font></span></div></td>";
			// str += "<span class=\"product-info-inventory \" style=\"margin-left:5px\">总价:<font style=\"color:#e64419\" id=\"count_"+mmfCode+"\">"
			// 	+"￥"+toDecimal2(price)+"</font></span>";
		}
		str += '<td style="width: 140px;"><div class="product-info-choose-count m-top20" style="padding-left: 14px">';
		str += "<input id=\"min\" name=\"\" style=\"margin-left:5px\" type=\"button\" value=\"-\" onclick=\"minusShu('"+mmfId+"')\">";
		str += "<input type=\"hidden\" id=\"id_"+mmfId+"\" value=\""+mmfId+"\"> ";
		str += "<input type=\"hidden\" id=\"price_"+mmfId+"\" value=\""+price+"\">";
		str += "<input id=\"inp_"+mmfId+"\" name=\"\" style=\"padding: 4px 8px;\" type=\"text\" value=\"1\" class=\"product-info-count\" onkeyup=\"this.value=this.value.replace(/[^0-9]/g,'');countMoney('"+mmfId+"')\"/>";
		str += "<input id=\"add\" style=\"margin-left:5px\" name=\"\" type=\"button\" value=\"+\" onclick=\"addShu('"+mmfId+"')\">";
		str += "</div>";
		str += '<td>';
		str += '<div class="product-info-choose-count m-top20"><input id="remove_${format.mmfId}" type="button" value="移除" onclick="removeTr(' + mmfId + ')"></div>';
		str += '</td>';
		str +="</td></tr>";
		$("#gwcListTable").append(str);
		mmfCodes += mmfId+",";
		showOrHideRemoveBtn(true);
	}
	if((mmfCodes.split(',').length -1) == 1){
		showOrHideRemoveBtn(false);
	}
	if(isLogin=='1'){
		initPrice();
		initPriceLine();
	}
}

function delMdEvaluate(mevaId){
	var vdata="mevaId="+mevaId;
	$.supper("confirm",{ title:"删除操作", msg:"确认删除记录操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdEvaluateService.deleteObject","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:setEvaluateList});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

function initPrice(){
	if(mmfCodes != null && mmfCodes != ""){
		var str = mmfCodes.substring(0,mmfCodes.length-1);
		var arry = str.split(",");
		var minMoney=0;
		var maxMoney=0;
		for(var i = 0 ; i < arry.length ; i++){
			var price = $("#price_"+arry[i]).val();
			if(i==0){
				minMoney=price;
				maxMoney=price;
			}
			if(price  < minMoney)
				minMoney=price;
			if(price > maxMoney)
				maxMoney=price;
		}
		
		if(maxMoney>minMoney)
			$("#matPrice").html("￥"+toDecimal2(minMoney)+" — ￥"+toDecimal2(maxMoney));
		else
			$("#matPrice").html("￥"+toDecimal2(minMoney));
	}
}

function minusShu(mmfCode){
	var shu =$("#inp_"+mmfCode).val();
	var price =$("#price_"+mmfCode).val();
	if(shu >1)
		shu--;
	$("#inp_"+mmfCode).val(shu);
	var count =price*shu;
	// $("#count_"+mmfCode).html("￥"+toDecimal2(count));
}
function addShu(mmfCode){
	var shu =$("#inp_"+mmfCode).val();
	var price =$("#price_"+mmfCode).val();
	shu++;
	$("#inp_"+mmfCode).val(shu);
	var count =price*shu;
	// $("#count_"+mmfCode).html("￥"+toDecimal2(count));
}

function countMoney(mmfCode){
	var shu =$("#inp_"+mmfCode).val();
	var price =$("#price_"+mmfCode).val();
	var count =0;
	if(shu!= null && shu != "")
		count =price*shu
	// $("#count_"+mmfCode).html("￥"+toDecimal2(count));
}

function addOrder(){
	if (isLoging() == false) {
		//$.supper("alert",{ title:"操作提示", msg:"请先登录商城！"});
		fLogin.openLogin();
		return;
	}
	if(mmfCodes != null && mmfCodes != ""){
		var str = mmfCodes.substring(0, mmfCodes.length - 1);
		var arry = str.split(",");
		for (var i = 0; i < arry.length; i++) {
			var shu = $("#inp_" + arry[i]).val();
			if (shu == null || shu == "" || shu <= 0) {
				$.supper("alert", {title: "操作提示", msg: "购买数量不能为空和0！"});
				return;
			}
		}
		var success = false;
		for (var i = 0; i < arry.length; i++) {
			var mmfId = $("#id_" + arry[i]).val();
			var shus = $("#inp_" + arry[i]).val();
			var prices = $("#price_" + arry[i]).val();
			var data = 'mmfId=' + mmfId + '&shu=' + shus + '&price=' + prices;
			$.supper("doservice", {
				"service": _xiangxi_add_carts_action, 'data': data, "BackE": function (jsondata) {
					if (jsondata.code == "1") {
						success = true;
						countCar()
					} else {
						success = false;
					}
				}
			});
		}
		layer.msg('成功加入购物车！');

	}
}

function preview(event){
	  var _src = $(event).attr("src");
	  $(event).css("border-color","#e64419");
	  $(event).parent().siblings().children("img").css("border-color","#CCC");
	  $("#previewImg").attr({
			src:_src.replace("\/n5\/","\/n1\/"),
			jqimg:_src.replace("\/n5\/","\/n0\/")
		});
	}

function initPriceLine(){
	var mmfIds =mmfCodes.substring(0,mmfCodes.length-1);
	var vdata="mmfIds="+mmfIds;
	$.supper("doservice", {"service":"MdMaterielFormatService.getFormatPriceList","data":vdata,"BackE": function(jsondata) {
		if (jsondata.code=="1") {
			var lineList = jsondata.obj;
			if(lineList != null && lineList.length > 0){
				var str = "";
				var seriesOptions=[];
				for(var i=0;i < lineList.length;i ++){
					var dataInfo = lineList[i];
					var ss={
							name: dataInfo.name,
							data: getArray(dataInfo.dataList)
					};
					seriesOptions.push(ss);
				}
				createArea("chars","价格趋势",seriesOptions);
			}
			
		}
}});
}

function createArea(vid,tilte,seriesOptions){
	$('#'+vid).highcharts('StockChart',{
        chart: {
        	
        },
        title: {
            text: tilte
        },
		exporting:{
			enabled:false
		},
		credits:{
			enabled:false
		},
		rangeSelector:{
			enabled:false
		},
		legend:{
			enabled:true,
			verticalAlign:'bottom'
		},
        xAxis: {
            allowDecimals: false,
            labels: {
                formatter: function () {
                   return Highcharts.dateFormat('%m/%d', this.value);  // clean, unformatted number for year
                }
            }
        },
        yAxis: {
            title: {
                text: tilte
            },
            labels: {
                formatter: function () {
                    return this.value;
                }
            }
        },
        navigator:{
        	xAxis:{
                labels: {
                    formatter: function () {
                       return Highcharts.dateFormat('%Y-%m-%d', this.value);  // clean, unformatted number for year
                    }
                }
            }
        },
        tooltip: {
        	valueDecimals:2,
        	xDateFormat:'%Y-%m-%d'
        },
        plotOptions: {
            area: {
               
                marker: {
                    enabled: false,
                    symbol: 'circle',
                    radius: 4,
                    states: {
                        hover: {
                            enabled: true
                        }
                    }
                }
            }
        },
        series:seriesOptions 
    });
	
}

function getArray(array){
	var x2 = [];
	for(var i = 0 ; i < array.length;i++){
		x2[i] = [parseInt(array[i].time),parseFloat(array[i].val)];
	}

	return x2;
}
//立即购买
function buyNow(){
	if (isLoging() == false) {
		//$.supper("alert",{ title:"操作提示", msg:"请先登录商城！"});
		fLogin.openLogin();
		return;
	}
	if(mmfCodes != null && mmfCodes != "") {
		var mmfIds="";
		var shus = "";
		var str = mmfCodes.substring(0, mmfCodes.length - 1);
		var arry = str.split(",");
		for (var i = 0; i < arry.length; i++) {
			var shu = $("#inp_" + arry[i]).val();
			if (shu == null || shu == "" || shu <= 0) {
				$.supper("alert", {title: "操作提示", msg: "购买数量不能为空和0！"});
				return;
			}
		}
		for (var i = 0; i < arry.length; i++) {
			var mmfId = $("#id_" + arry[i]).val();
			var shu = $("#inp_" + arry[i]).val();
			mmfIds += mmfId + ',';
			shus += shu + ',';
		}
		mmfIds = mmfIds.substring(0,mmfIds.length-1);
		shus = shus.substring(0,shus.length-1);
		window.location.href="ddqr.htm?mmfIds="+mmfIds+"&shus="+shus;
	} else{
		$.supper("alert",{ title:"操作提示", msg:"请选择购买的商品!"});
	}
}