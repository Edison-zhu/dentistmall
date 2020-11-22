$(function(){   
	var wiId=$.supper("getParam", "wiId");
	$("#wiId").val(wiId);
	initDataGrid();
});
var mmg ;
var queryAction = "MdOutWarehouseMxService.getMdOutWarehouseMxListByWiId";
var initDataGrid =  function(){
	    var cols = [
					{title:'物料名称', name:'mat_name', width:80, align: 'center'},  
					{title:'规格', name:'norm', width:80, align: 'center'},  
					{title:'供应商', name:'supplier_name', width:80, align: 'center'},
	                {title:'出库类型', name:'company_type', width:80, align: 'center',renderer:controlMode}, 
	                {title:'申购人', name:'customer_name', width:80, align: 'center'}, 
			       	{title:'出库人', name:'user_name', width:80, align: 'center'},  
			       	{title:'出库时间', name:'finsh_date', width:80, align: 'center'},  
			    	{title:'出库数量', name:'base_number', width:80, align: 'center'}
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
	if(val=="1")
		return "申领出库";
	else (val=="2")
		return "退货出库";
}

function search2(){
	var att_mmgurl =  rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
}

function  closeWin(){
	$.supper("closeWin");
}
