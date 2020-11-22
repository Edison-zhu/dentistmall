
$(function(){
	initCount();
	initCountTransaction();
	initData();
	initDataTen();
	initCountMateriel();
	initTypeMateriel();
	initDataOleNew(0);
	initDataConversion(0);
	initDataAnalysis(0);
	writeCurrentDate()

});
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
	    time = year + "年" + month + "月" + date + "日" + " "+ week;
	    $("#currentDate").html(time);
	    //设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。
	    var timer = setTimeout("writeCurrentDate()", 1000);
}
function initCount(){
	$.supper("doservice", {"service" : "MdOrderInfoService.countOrderMoney1","BackE" : function(jsondata) {
			if (jsondata.code == "1") {
				$("#countOrers").html(jsondata.obj.countOrers);
				$("#placeOrderMoneys").html(jsondata.obj.placeOrderMoneys);
				$("#RetreatPlaceOrderMoney").html(jsondata.obj.RetreatPlaceOrderMoney);
				$("#RetreatPlaceOrderMoney2").html(jsondata.obj.RetreatPlaceOrderMoney2);
				$("#RetreatPlaceOrderMoney3").html(jsondata.obj.RetreatPlaceOrderMoney3);
				$("#TransactionCount").html(jsondata.obj.TransactionCount);
				$("#TransactionMoneyCount").html(jsondata.obj.TransactionMoneyCount);
				if (jsondata.obj.TransactionCount!=0) {
					var num=jsondata.obj.PercentageCount/jsondata.obj.TransactionCount;
					$("#PercentageCount").html(toDecimal2(num*100)+"%");
				}else{
					$("#PercentageCount").html("0%");
				}
			}
		}
	});
}
function initCountTransaction(){
	$.supper("doservice", {"service" : "MdOrderInfoService.countOrderSuppiler","BackE" : function(jsondata) {
			if (jsondata.code == "1") {
				$("#dfhCount").html(jsondata.obj.dfh);
				$("#dfkCount").html(jsondata.obj.dfk);
				$("#bffhCount").html(jsondata.obj.bffh);
				$("#dshCount").html(jsondata.obj.dsh);
				$("#jycgCount").html(jsondata.obj.jycg);
				$("#jysbCount").html(jsondata.obj.jysb);
				$("#shtkCount").html(jsondata.obj.shtkCount);
				$("#shthCount").html(jsondata.obj.shthCount);
				$("#shwcCount").html(jsondata.obj.shwcCount);
			}
		}
	});
}
/**
 * 交易实时战报
 */
function initData(){
	var vdata = "&limit="+10+"&page="+1;
	$.supper("doservice", {"service":"MdOrderInfoService.getOrderMxListByTransaction1",data:vdata, isAjax:"1", "BackE": function (jsondata) {
			//initXQList('matList',dataList, false);
		/* if (jsondata.code == "1") {*/
		 	var mxList = jsondata.rows;
		 	var str = "";
		 	if(mxList != null && mxList.length > 0){
		 		for(var i =0;i < mxList.length;i++){
		 			str +="<tr><td style=\"text-align:center\">"+mxList[i].PlaceOrderTime+"</td>";
		 			str += "<td style=\"text-align:center\">"+mxList[i].Purchase_unit+"</td>";
		 			str += "<td style=\"text-align:center\">"+mxList[i].commodityNumber+"</td>";
		 			str += "<td style=\"text-align:center\">"+toDecimal2(mxList[i].placeOrderMoney)+"</td>";
		 			str += "</tr>";
				}
		 		$("#mxList").html(str);
		 	}
		/* }*/
 	}});	
}
//商品总览
function initCountMateriel(){
	$.supper("doservice", {"service" : "MdOrderInfoService.materielState1","BackE" : function(jsondata) {
		if (jsondata.code == "1") {
				$("#allmaterielCount").html(jsondata.obj.allmaterielCount);
				$("#ysjCount").html(jsondata.obj.ysjCount);
				$("#yxjCount").html(jsondata.obj.yxjCount);

				$("#allYg").html(jsondata.obj.allYg);
				$("#qy").html(jsondata.obj.qy1);
				$("#jy").html(jsondata.obj.jy);
				}
		}
	});
}
//商品种类统计
function initTypeMateriel(){
	$.supper("doservice", {"service" : "MdOrderInfoService.materielType","BackE" : function(jsondata) {
		if (jsondata.code == "1") {
			var str = "";
			var matType=jsondata.obj.matType;
			if (matType!=null&&matType!=undefined) {
				var matType1=jsondata.obj.matType1/matType;
				var matType2=jsondata.obj.matType2/matType;
				var matType3=jsondata.obj.matType3/matType;
				var matType4=jsondata.obj.matType4/matType;
				var matType5=jsondata.obj.matType5/matType;
				var matType6=jsondata.obj.matType6/matType;
				var matType7=jsondata.obj.matType7/matType;
				var matType8=jsondata.obj.matType8/matType;
			}
			str +="<tr><td style=\"text-align:center\">"+toDecimal2(matType1*100)+"%"+"</td>";
			str +="<td style=\"text-align:center\">"+toDecimal2(matType2*100)+"%"+"</td>";
			str +="<td style=\"text-align:center\">"+toDecimal2(matType3*100)+"%"+"</td>";
			str +="<td style=\"text-align:center\">"+toDecimal2(matType4*100)+"%"+"</td>";
			str +="<td style=\"text-align:center\">"+toDecimal2(matType5*100)+"%"+"</td>";
			str +="<td style=\"text-align:center\">"+toDecimal2(matType6*100)+"%"+"</td>";
			str +="<td style=\"text-align:center\">"+toDecimal2(matType7*100)+"%"+"</td>";
			str +="<td style=\"text-align:center\">"+toDecimal2(matType8*100)+"%"+"</td>";
			str += "</tr>";
			$("#mxmaterielList").html(str);
				}
		}
	});
}
//交易分析
function initDataAnalysis(value){
	var vdata = "&value="+value;
	$.supper("doservice", {"service":"MdOrderInfoService.materielAnalysis1",data:vdata, isAjax:"1", "BackE": function (jsondata) {
		if (value==0) {
			//点击按钮变成红色
			$("#btn11").addClass("a1");//.removeAttr("style");
			$("#btn21").removeClass("a1");
			$("#btn31").removeClass("a1");
			$("#btn41").removeClass("a1");

			$("#AnalysisCount").html(jsondata.obj.AnalysisCount0+"/");
			$("#AnalysisCounts").html(jsondata.obj.AnalysisCounts0);
			$("#ActualMoney").html(toDecimal2(jsondata.obj.ActualMoney0)+"/");
			$("#ActualMoneys").html(toDecimal2(jsondata.obj.ActualMoneys0));
			if (jsondata.obj.Pepole0!=0) {
				$("#MoneyAvgs").html(toDecimal2(jsondata.obj.MoneyAvgs0/jsondata.obj.Pepole0));
			}else{
				$("#MoneyAvgs").html(toDecimal2(0));
			}
		}
		if (value==1) {
			$("#btn21").addClass("a1");//.removeAttr("style");
			$("#btn11").removeClass("a1");
			$("#btn31").removeClass("a1");
			$("#btn41").removeClass("a1");

			$("#AnalysisCount").html(jsondata.obj.AnalysisCount1+"/");
			$("#AnalysisCounts").html(jsondata.obj.AnalysisCounts1);
			$("#ActualMoney").html(toDecimal2(jsondata.obj.ActualMoney1)+"/");
			$("#ActualMoneys").html(toDecimal2(jsondata.obj.ActualMoneys1));
			if (jsondata.obj.Pepole1!=0) {
				$("#MoneyAvgs").html(toDecimal2(jsondata.obj.MoneyAvgs1/jsondata.obj.Pepole1));
			}else{
				$("#MoneyAvgs").html(toDecimal2(0));
			}
		}
		if (value==2) {
			$("#btn31").addClass("a1");//.removeAttr("style");
			$("#btn21").removeClass("a1");
			$("#btn11").removeClass("a1");
			$("#btn41").removeClass("a1");

			$("#AnalysisCount").html(jsondata.obj.AnalysisCount2+"/");
			$("#AnalysisCounts").html(jsondata.obj.AnalysisCounts2);
			$("#ActualMoney").html(toDecimal2(jsondata.obj.ActualMoney2)+"/");
			$("#ActualMoneys").html(toDecimal2(jsondata.obj.ActualMoneys2));
			if (jsondata.obj.Pepole2!=0) {
				$("#MoneyAvgs").html(toDecimal2(jsondata.obj.MoneyAvgs2/jsondata.obj.Pepole2));
			}else{
				$("#MoneyAvgs").html(toDecimal2(0));
			}
		}
		if (value==3) {
			$("#btn41").addClass("a1");//.removeAttr("style");
			$("#btn21").removeClass("a1");
			$("#btn31").removeClass("a1");
			$("#btn11").removeClass("a1");

			$("#AnalysisCount").html(jsondata.obj.AnalysisCount3+"/");
			$("#AnalysisCounts").html(jsondata.obj.AnalysisCounts3);
			$("#ActualMoney").html(toDecimal2(jsondata.obj.ActualMoney3)+"/");
			$("#ActualMoneys").html(toDecimal2(jsondata.obj.ActualMoneys3));
			if (jsondata.obj.Pepole3!=0) {
				$("#MoneyAvgs").html(toDecimal2(jsondata.obj.MoneyAvgs3/jsondata.obj.Pepole3));
			}else{
				$("#MoneyAvgs").html(toDecimal2(0));
			}
		}
 	}});	
}
//商品排行榜
function initDataTen(){
	var vdata = "&limit="+10+"&page="+1;
	$.supper("doservice", {"service":"MdOrderInfoService.getOrderMxListmaterielTop1",data:vdata, isAjax:"1", "BackE": function (jsondata) {
		 	var mxList = jsondata.rows;
		 	var str = "";
		 	if(mxList != null && mxList.length > 0){
		 		for(var i =0;i < mxList.length;i++){
		 			str +="<tr><td style=\"text-align:center\">"+mxList[i].matName+"</td>";
		 			str += "<td style=\"text-align:center\">"+mxList[i].matNumber+"</td>";
		 			str += "<td style=\"text-align:center\">"+mxList[i].wmsMiId+"</td>";
		 			str += "<td style=\"text-align:center\">"+toDecimal2(mxList[i].TotalMoney)+"</td>";
		 			str += "</tr>";
				}
		 		$("#mxListTen").html(str);
		 	}
		/* }*/
 	}});	
}
//交易转化率
function initDataConversion(value){
	var vdata = "&value="+value;
	$.supper("doservice", {"service":"MdOrderInfoService.materielConversion1",data:vdata, isAjax:"1", "BackE": function (jsondata) {
		if (value==0) {
			//点击按钮变成红色
			$("#btn1").addClass("a1");//.removeAttr("style");
			$("#btn2").removeClass("a1");
			$("#btn3").removeClass("a1");
			$("#btn4").removeClass("a1");
			if (jsondata.obj.browse0!=0) {
				$("#OrderBrowse").html(toDecimal2(jsondata.obj.conversionOrder0/jsondata.obj.browse0*100)+"%");
				$("#PaymentBrowse").html(toDecimal2(jsondata.obj.conversionPayment0/jsondata.obj.browse0*100)+"%");
			}else{
				$("#OrderBrowse").html(toDecimal2(0)+"%");
				$("#PaymentBrowse").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.conversionOrder0!=0) {
				$("#OrderPayment").html(toDecimal2(jsondata.obj.conversionPayment0/jsondata.obj.conversionOrder0*100)+"%");
			}else{
				$("#OrderPayment").html(toDecimal2(0)+"%");
			}
		}
		if (value==1) {
			//点击按钮变成红色
			$("#btn2").addClass("a1");//.removeAttr("style");
			 $("#btn1").removeClass("a1");
			$("#btn3").removeClass("a1");
			$("#btn4").removeClass("a1");
			if (jsondata.obj.browse1!=0) {
				$("#OrderBrowse").html(toDecimal2(jsondata.obj.conversionOrder1/jsondata.obj.browse1*100)+"%");
				$("#PaymentBrowse").html(toDecimal2(jsondata.obj.conversionPayment1/jsondata.obj.browse1*100)+"%");
			}else{
				$("#OrderBrowse").html(toDecimal2(0)+"%");
				$("#PaymentBrowse").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.conversionOrder1!=0) {
				$("#OrderPayment").html(toDecimal2(jsondata.obj.conversionPayment1/jsondata.obj.conversionOrder1*100)+"%");
			}else{
				$("#OrderPayment").html(toDecimal2(0)+"%");
			}
		}
		if (value==2) {
			//点击按钮变成红色
			$("#btn3").addClass("a1");//.removeAttr("style");
			$("#btn1").removeClass("a1");
			$("#btn2").removeClass("a1");
			$("#btn4").removeClass("a1");
			if (jsondata.obj.browse2!=0) {
				$("#OrderBrowse").html(toDecimal2(jsondata.obj.conversionOrder2/jsondata.obj.browse2*100)+"%");
				$("#PaymentBrowse").html(toDecimal2(jsondata.obj.conversionPayment2/jsondata.obj.browse2*100)+"%");
			}else{
				$("#OrderBrowse").html(toDecimal2(0)+"%");
				$("#PaymentBrowse").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.conversionOrder2!=0) {
				$("#OrderPayment").html(toDecimal2(jsondata.obj.conversionPayment2/jsondata.obj.conversionOrder2*100)+"%");
			}else{
				$("#OrderPayment").html(toDecimal2(0)+"%");
			}
		}
		if (value==3) {
			//点击按钮变成红色
			$("#btn4").addClass("a1");//.removeAttr("style");
			$("#btn1").removeClass("a1");
			$("#btn2").removeClass("a1");
			$("#btn3").removeClass("a1");
			if (jsondata.obj.browse3!=0) {
				$("#OrderBrowse").html(toDecimal2(jsondata.obj.conversionOrder3/jsondata.obj.browse3*100)+"%");
				$("#PaymentBrowse").html(toDecimal2(jsondata.obj.conversionPayment3/jsondata.obj.browse3*100)+"%");
			}else{
				$("#OrderBrowse").html(toDecimal2(0)+"%");
				$("#PaymentBrowse").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.conversionOrder3!=0) {
				$("#OrderPayment").html(toDecimal2(jsondata.obj.conversionPayment3/jsondata.obj.conversionOrder3*100)+"%");
			}else{
				$("#OrderPayment").html(toDecimal2(0)+"%");
			}
		}
 	}});	
}
function initDataOleNew(value){
	var vdata = "&value="+value;
	$.supper("doservice", {"service":"MdOrderInfoService.NewOldMoneyCount",data:vdata, isAjax:"1", "BackE": function (jsondata) {
		if (value==0) {
			$("#NewSumMoney").html(toDecimal2(jsondata.obj.NewSumMoney));
			$("#NewSumCount").html(jsondata.obj.NewSumCount);
			$("#OldSumMoney").html(toDecimal2(jsondata.obj.OldSumMoney));
			$("#OldSumCount").html(jsondata.obj.OldSumCount);
			if (jsondata.obj.TopNewSumMoney!="0.0") {
				compare=toDecimal2(jsondata.obj.NewSumMoney/jsondata.obj.TopNewSumMoney);
				if (compare>0) {
					$("#compare1").html("↑"+compare+"%");
				}
				if (compare<0) {
					$("#compare1").html("↓"+compare+"%");
				}
				if (compare==0) {
					$("#compare1").html(toDecimal2(0)+"%");
				}
			}else{
				$("#compare1").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.TopNewSumCount!="0") {
				 compare=toDecimal2(jsondata.obj.NewSumCount/jsondata.obj.TopNewSumCount*100);
				if (compare>0) {
					$("#compare2").html("↑"+compare+"%");
				}
				if (compare<0) {
					$("#compare2").html("↓"+compare+"%");
				}
				if (compare==0.00) {
					$("#compare2").html(toDecimal2(0)+"%");
				}
			}else{
				$("#compare2").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.TopOldSumMoney!="0") {
				var compare=toDecimal2((jsondata.obj.OldSumMoney/jsondata.obj.TopOldSumMoney)*100);
				if (compare>0) {
					$("#compare3").html("↑"+compare+"%");
				}
				if (compare<0) {
					$("#compare3").html(toDecimal2("↓"+compare*100)+"%");
				}
				if (compare==0.00) {
					$("#compare3").html(toDecimal2(0)+"%");
				}
			}else{
				$("#compare3").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.TopOldSumCount!="0") {
				 compare=toDecimal2(jsondata.obj.OldSumCount/jsondata.obj.TopOldSumCount*100);
				if (compare>0) {
					$("#compare4").html("↑"+compare*100+"%");
				}
				if (compare<0) {
					$("#compare4").html("↓"+compare*100+"%");
				}
				if (compare==0.00) {
					$("#compare4").html(toDecimal2(0)+"%");
				}
			}else{
				$("#compare4").html(toDecimal2(0)+"%");
			}
		}
		if (value==1) {
			$("#NewSumMoney").html(toDecimal2(jsondata.obj.NewSumMoney));
			$("#NewSumCount").html(jsondata.obj.NewSumCount);
			$("#OldSumMoney").html(toDecimal2(jsondata.obj.OldSumMoney));
			$("#OldSumCount").html(jsondata.obj.OldSumCount);
			if (jsondata.obj.TopNewSumMoney!="0.0") {
				compare=toDecimal2(jsondata.obj.NewSumMoney/jsondata.obj.TopNewSumMoney*100);
				if (compare>0) {
					$("#compare1").html("↑"+compare+"%");
				}
				if (compare<0) {
					$("#compare1").html("↓"+compare+"%");
				}
				if (compare==0.00) {
					$("#compare1").html(toDecimal2(0)+"%");
				}
				console.log(compare1);
			}else{
				$("#compare1").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.TopNewSumCount!="0") {
				 compare=toDecimal2(jsondata.obj.NewSumCount/jsondata.obj.TopNewSumCount*100);
				if (compare>0) {
					$("#compare2").html("↑"+compare+"%");
				}
				if (compare<0) {
					$("#compare2").html("↓"+compare+"%");
				}
				if (compare==0.00) {
					$("#compare2").html(toDecimal2(0)+"%");
				}
			}else{
				$("#compare2").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.TopOldSumMoney!="0") {
				 compare=toDecimal2(jsondata.obj.OldSumMoney/jsondata.obj.TopOldSumMoney*100);
				if (compare>0) {
					$("#compare3").html("↑"+compare+"%");
				}
				if (compare<0) {
					$("#compare3").html("↓"+compare+"%");
				}
				if (compare==0.00) {
					$("#compare3").html(toDecimal2(0)+"%");
				}
			}else{
				$("#compare3").html(toDecimal2(0)+"%");
			}
			if (jsondata.obj.TopOldSumCount!="0") {
				 compare=toDecimal2(jsondata.obj.OldSumCount/jsondata.obj.TopOldSumCount*100);
				if (compare>0) {
					$("#compare4").html("↑"+compare+"%");
				}
				if (compare<0) {
					$("#compare4").html("↓"+compare+"%");
				}
				if (compare==0.00) {
					$("#compare4").html(toDecimal2(0)+"%");
				}
			}else{
				$("#compare4").html(toDecimal2(0)+"%");
			}
		}
 	}});	
}
//导出7天数据分析
function exportSevenDate(){
	var vdata="";
	var newTab=window.open('about:blank');
//重新提交
	$.supper("doservice", {"service":"MdOrderInfoService.exportSevenDayTwo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
//快捷方式的超链接部分 **********start*************
function allOrder() {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/transaction/allOrderInfoList"});
	$.supper("showTtemWin",{ "url":att_url,"title":"全部订单"});
}
function commodity1() {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/mall/materielList.jsp"});
	$.supper("showTtemWin",{ "url":att_url,"title":"商品管理"});
}
function supplier1() {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/jsp/website/sysWebsiteInfoList.jsp"});
	$.supper("showTtemWin",{ "url":att_url,"title":"供应商商铺管理"});
}
function group1() {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/business/companyGroupList.jsp"});
	$.supper("showTtemWin",{ "url":att_url,"title":"集团管理"});
}
function hospital1() {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/business/dentistHospitalList.jsp"});
	$.supper("showTtemWin",{ "url":att_url,"title":"医院管理"});
}
function container3Bottme1() {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/business/dentalClinicList.jsp"});
	$.supper("showTtemWin",{ "url":att_url,"title":"门诊管理"});
}


//四个工作面板的链接跳转

// function leftA1() {
// 	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/transaction/allOrderInfoList.jsp"});
// 	$.supper("setProductArray", {"name":"selOutOrderInfo", "value":{stateTime: 1}});
// 	$.supper("showTtemWin",{ "url":att_url,"title":"全部订单"});
// }
// function leftA2() {
// 	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/transaction/allOrderInfoList.jsp"});
// 	$.supper("setProductArray", {"name":"selOutOrderInfo", "value":{stateTime: 2}});
// 	$.supper("showTtemWin",{ "url":att_url,"title":"全部订单"});
// }
// function leftA3() {
// 	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/transaction/allOrderInfoList.jsp"});
// 	$.supper("setProductArray", {"name":"selOutOrderInfo", "value":{stateTime: 3}});
// 	$.supper("showTtemWin",{ "url":att_url,"title":"全部订单"});
// }
function leftA1() {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/transaction/orderInfoList.jsp"});
	$.supper("setProductArray", {"name":"selOutOrderInfo", "value":{stateTime: 1}});
	$.supper("showTtemWin",{ "url":att_url,"title":"订单维护"});
}
function leftA2() {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/transaction/orderInfoList.jsp"});
	$.supper("setProductArray", {"name":"selOutOrderInfo", "value":{stateTime: 2}});
	$.supper("showTtemWin",{ "url":att_url,"title":"订单维护"});
}
function leftA3() {
	var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/transaction/orderInfoList.jsp"});
	$.supper("setProductArray", {"name":"selOutOrderInfo", "value":{stateTime: 3}});
	$.supper("showTtemWin",{ "url":att_url,"title":"订单维护"});
}



//快捷方式的超链接部分 **********end*************

