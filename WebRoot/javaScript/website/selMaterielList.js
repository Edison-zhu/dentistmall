$(function(){   
	initDataGrid();
	//search();
});
var mmg ;
var queryAction = "MdMaterielInfoService.getPagerModelObject";
var initDataGrid =  function(){
	    var cols = [
					{title:'商品编码', name:'matCode', width:80, align: 'center'}, 
					{title:'商品名称', name:'matName', width:80, align: 'center'}, 
					{title:'供应商', name:'applicantName', width:80, align: 'center'}, 
					{title:'生产厂家', name:'productName', width:80, align: 'center'}, 
					{title:'品牌', name:'brand', width:80, align: 'center'}, 
					{title:'单价', name:'money1', width:80, align: 'center'}, 
					{title:'基本单位', name:'basicUnit', width:80, align: 'center'}
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
	              , multiSelect: true
	              , checkCol: true
	              ,showBackboard:false
	              , fullWidthRows: true
	              , autoLoad: false
	              , nowrap:true
	              , plugins: [
	                  $('#pg').mmPaginator({})
	              ]
	          });  
	          mmg.load(); 
}

function search2(){
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
		$.supper("alert",{ title:"操作提示", msg:"请选择一条数据！"});
		return;
	}
	$.supper("setProductArray", {"name":"selMaterielInfo", "value":rows[0]});
	closeWin();
}


