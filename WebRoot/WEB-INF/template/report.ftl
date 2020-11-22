<!DOCTYPE html> 
<html>
  <head> 
   <title>查看心电报告</title> 
   <meta http-equiv="X-UA-Compatible" content="edge"/>
		<meta name="renderer" content="webkit">
		<meta http-equiv="Content-Script-Type" content="text/javascript"/>
		<meta name="format-detection" content="telephone=no"/>
		<meta name="viewport" content="width=device-width,user-scalable=no">
	<#include "link.html"/> 
  </head> 
<body >
<body style="overflow:hidden;">
	<div class="panel-body" style="">
			<div id="container" style="min-width:350px;height:250px;"></div>
		<div class="form-group" styel="" >
			<div class="controls col-sm-4" style="float:left">
				<div style="font-size:10px">
					最高心率
				</div>
				<div>
					<span style="font-size:25px;font-weight:500">${zgxl}</span><span style="font-size:20px;">bmp</span>
				</div>
            </div>
			<div class="controls col-sm-4" style="float:left;border-left:1px solid #000;border-right:1px solid #000">
				<div style="font-size:10px">
					最低心率
				</div>
				<div>
					<span style="font-size:25px;font-weight:500">${zdxl}</span><span style="font-size:20px;">bmp</span>
				</div>
            </div>
			<div class="controls col-sm-4" style="float:left">
				<div style="font-size:10px">
					平均心率
				</div>
				<div>
					<span style="font-size:25px;font-weight:500">${pjxl}</span><span style="font-size:20px;">bmp</span>
				</div>
            </div>
		</div>
	</div>
	<div class="form-group" style="background-color:#999999;padding:5px;font-size:10px" >
		基本信息
    </div>
	<div class="form-group" style="font-size:13px;padding-left:5px" >
		<div>
			<span style="margin-right:2px">记录时间:</span><span >${time}</span>
		</div>
		<div>
			<span style="margin-right:2px">文件大小(kb):</span><span >${size}</span>
		</div>
    </div>
	<div class="form-group" style="background-color:#999999;padding:5px;font-size:10px" >
		分析结果
    </div>
	<div class="form-group" style="font-size:13px;padding-left:5px" >
		<div>
			<span style="margin-right:2px;">总测量时间(S):</span><span style="width:50px">${countTime}</span>
			<span style="margin-right:2px;margin-left:80px">心率(bpm):</span><span >${pjxl}</span>
		</div>
		<div>
			<span style="margin-right:2px">最大心率(bpm):</span><span >${zgxl}</span>
			<span style="margin-right:2px;margin-left:77px">最小心率(bpm):</span><span >${zdxl}</span>
		</div>
		<div>
			<span style="margin-right:2px">总心搏数:</span><span >72</span>
			<span style="margin-right:2px;margin-left:110px">最大RR间期(ms):</span><span >67</span>
		</div>
		<div>
			<span style="margin-right:2px">最下RR间期(ms):</span><span >72</span>
			<span style="margin-right:2px;margin-left:66px">最小心率(bpm):</span><span >67</span>
		</div>
		<div>
			<span style="margin-right:2px">QRS宽度(ms):</span><span >72</span>
		</div>
	</div>
	
	<div class="form-group" style="background-color:#999999;padding:5px;font-size:10px" >
		心电图分析结果
    </div>
	<div class="form-group" style="font-size:13px;padding-left:5px" >
		800正常窦性心率<br/>604 ST-T改变(心肌缺血可能)<br/>**可能是异常心电图***
    </div>
    <input type="hidden" id="userName" value="${userName}">
    <input type="hidden" id="mdId" value="${mdId}">
</body>
</html>
<script>
$(function(){  
	showMap($("#mdId").val(),$("#userName").val());
});
function showMap(mdId,userName){
	$.supper("doservice", {"service":"MonitoringService.getMonitoringInfoData","data":"mdId="+mdId,"BackE":function (jsondata) {
		if (jsondata.code == "1") {
			var data = getArray(jsondata.obj);
			
			initMap("container",jsondata.obj,250,330,"#FFFFFF",userName+"心电图","#050505");
			//var chart = $('#container').highcharts();
			//chart.xAxis[0].setExtremes(Date.UTC(2004, 3, 1),Date.UTC(2004, 3, 1)+num*1000,true);
			
		}
 	}});
}

function getArray(array){
	var x2 = [];
	for(var i = 0 ; i < array.length;i++){
		x2[i] = [parseFloat(array[i])];
	}

	return x2;
}
var num = 4;
function initMap(id,vdata,vheight,vwidth,bgcolor,lineName,lineColor){
	$('#'+id).highcharts('StockChart', {
        chart: {
            height:vheight,
			width:vwidth,
			backgroundColor:bgcolor
			
        },
		credits: {
			enabled: false
		},
		
        xAxis:{
			labels:{
				enabled:false
			},
			
			tickInterval:0.0054 * 1000*num,
			gridLineColor: '#cccccc',
			gridLineWidth: 1

		},
			
		
		yAxis:{
			gridLineColor: '#cccccc',
			gridLineWidth: 1,
			max:1.0,
			min:-1.0,
			tickInterval:0.1,
			labels:{
				enabled:false
			},
			title: {
					text: ''
				}

				
		},
		rangeSelector: {
			buttons: [{
				count: num,
				type: 'second',
				text: '1'
			}],
			inputEnabled: false,
			selected: 0,
			allButtonsEnabled:true
		},
		tooltip:{
			enabled:false,
			hideDelay:1
			
		},

		navigator:{
			 enabled:false
			
		},
        title: {
            text: lineName
        },
        series: [{
            name: lineName,
			color:'red',
            data: vdata,
            pointStart: Date.UTC(2004, 3, 1),
            pointInterval: 0.0027 * 1000
           
        }]

    });
}
</script>
 