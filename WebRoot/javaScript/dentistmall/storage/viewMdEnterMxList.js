$(function(){   
	var wiId=$.supper("getParam", "wiId");
	$("#wiId").val(wiId);
	initDataGrid();
});
var mmg ;
var queryAction = "MdEnterWarehouseMxService.getMdEnterWarehouseMxListByWiId";
var initDataGrid =  function(){
	    var cols = [
					{title:'物料名称', name:'mat_name', width:80, align: 'center'},  
					{title:'规格', name:'norm', width:80, align: 'center'},  
					{title:'供应商', name:'supplier_name', width:80, align: 'center'},
	                {title:'采购人', name:'consignee', width:80, align: 'center'},  
			       	{title:'采购时间', name:'order_datetime', width:80, align: 'center'},  
			       	{title:'入库人', name:'consignor', width:80, align: 'center'},  
			       	{title:'入库时间', name:'receipt_datetime', width:80, align: 'center'},  
			    	{title:'入库数量', name:'mat_number', width:80, align: 'center'},  
			    	{title:'单价', name:'price', width:80, align: 'center',renderer:controlMoney},
			    	{title:'总价', name:'price', width:80, align: 'center',renderer:countMoney}
	            ];  
	    var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	          mmg = $('#datagrid1').mmGrid({
	              height:'280px'
	        	  ,cols: cols
	              , method: 'post'
	              , remoteSort:true
	              ,url : att_mmgurl
	              , sortName: 'SECUCODE'
	              , sortStatus: 'asc'
	              , multiSelect: true
	              ,showBackboard:false
	              , checkCol: false
	              , fullWidthRows: true
	              , autoLoad: false
	              , nowrap:true
	              , plugins: [
	                  $('#pg').mmPaginator({})
	              ]
	          });  
	          mmg.load(); 
}

function controlMode(val,item,rowIndex){
	if(val=="2")
		return "订单入库";
	else (val=="1")
		return "物料入库";
	return "-";
}

function controlMoney(val,item,rowIndex){
	if(val!= null && val!= "")
		return toDecimal2(val);
	else
		return "-";
}

function countMoney(val,item,rowIndex){
	if(val!= null && val!= "" && item.mat_number != null && item.mat_number!=""){
		return toDecimal2(val*item.mat_number);
	}
	else
		return "-";
}

function search2(){
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
}

function  closeWin(){
	$.supper("closeWin");
}
