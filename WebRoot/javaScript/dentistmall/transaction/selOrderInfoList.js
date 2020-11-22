var queryAction ="";
$(function(){   

	var selType=$.supper("getParam", "selType");
	if(selType=='1')//入库
		queryAction = "MdOrderInfoService.getPagerModelEnterOrder";
	else if(selType=='2')//出库
		queryAction = "MdOrderInfoService.getPagerModelOutOrderEnter";
		// queryAction = "MdOrderInfoService.getPagerModelOutOrder"; //用于测试的
	initDataGrid();
	// vsearch();
});
var mmg ;
var initDataGrid =  function(){
	    var cols = [
	                // {title:'订单编号', name:'orderCode', width:100, align: 'center'},
			       	// {title:'下单时间', name:'placeOrderTime', width:120, align: 'center'},
			    	// {title:'下单金额', name:'placeOrderMoney',width:80, align: 'center'},
			    	// {title:'运单号', name:'expressCode',width:80, align: 'center'},
			    	// {title:'供应商名称', name:'applicantName', width:80, align: 'center'},
			    	// {title:'采购人人', name:'purchaseAccount', width:60, align: 'center'}
			{title:'入库单号', sortable: true, name:'billCode', width:80, align: 'center'},
			{title:'入库时间', sortable: true, name:'createDate', width:80, align: 'center'},
			{title:'入库类型', sortable: true, name:'billType',width:80, align: 'center', renderer: renderType},
			{title:'采购金额', sortable: true, name:'price',width:80, align: 'center'},
			{title:'零售金额', sortable: true, name:'retailMoney', width:40, align: 'center'},
			{title:'采购数量', sortable: true, name:'matNumber', width:40, align: 'center', renderer: renderMatNumber},
			{title:'订单数/发货数/入库数', sortable: true, name:'missingNumber', width:40, align: 'center', renderer: formateOrderOutMissNumberInp},
			{title:'供应商', sortable: true, name:'applicantName', width:80, align: 'center',impCode:"PAR171113111313225"},
			{title:'采购人', sortable: true, name:'consignor', width:40, align: 'center'},
			{title:'制作人', sortable: true, name:'createRen', width:40, align: 'center'},
	            ];  
	    var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	          mmg = $('#datagrid1').mmGrid({
	              height:'auto'
	        	  ,cols: cols
	              , method: 'post'
	              , remoteSort:true
	              ,url : att_mmgurl
	              , sortName: 'SECUCODE'
	              , sortStatus: 'asc'
	              , multiSelect: false
	              ,showBackboard:false
	              , checkCol: true
	              , fullWidthRows: true
	              , autoLoad: false
	              , plugins: [
	                  $('#pg').mmPaginator({})
	              ]
	          });  
	          mmg.load(); 
}

function renderType(val, item, rowIndex){
	let str = '';
	if (item.billType == 1) {
		str = '物料入库';
	} else if (item.billType == 2) {
		str = '订单入库';
	}
	return str;
}

function renderMatNumber(val, item, rowIndex) {
	let str = '';
	if (item.matNumber != undefined)
		str = item.matNumber;
	else
		str = item.expectNumber;
	return str;
}

function formateOrderOutMissNumberInp(val, item, rowIndex){
	var str = '';
	if (item.expectNumber != undefined){
		str += item.expectNumber + '/';
		str += item.expectNumber + '/';
		str += item.expectNumber;
	} else {
		str += item.matNumber + '/';
		str += item.number2 + '/';
		str += item.enterNumber;
	}
	return str;
}

function vsearch(){
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
}

function  closeWin(){
	$.supper("closeWin");
}

function save(){
	var rows=mmg.selectedRows();
	if(rows==null || rows.length != 1){
		$.supper("alert",{ title:"操作提示", msg:"请选择数据！"});
		return;
	}
	$.supper("setProductArray", {"name":"selCodeInfo", "value":rows[0]});
	closeWin();
}


