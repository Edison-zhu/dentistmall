var _selType="1";
$(function(){   
	$('.myscrollinfo').slimScroll({
        height: 370,
         wheelStep:'5px'
    });
	initDataGrid();
	//search();
});
var mmg ;
var queryAction = "MdInventoryService.getPagerViewObject";
var initDataGrid =  function(){
	    var cols = [
			       	{title:'物料名称', name:'matName', width:120, align: 'center'},  
			       	{title:'规格', name:'mmfName', width:100, align: 'center'},  
			    	{title:'库存数量', name:'baseNumber', width:80, align: 'center'},  
			    	{title:'基本单位', name:'basicUnit', width:80, align: 'center'}
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
	              , checkCol: true
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
	var att_mmgurl ="";
	att_mmgurl = rpc.getUrlByForm(queryAction,"queryForm");
	mmg.opts.url = att_mmgurl;
    mmg.load(); 
}

function  closeWin(){
	$.supper("closeWin");
}

function save(){
	var rows=mmg.selectedRows();
	if(rows==null || rows.length <=0){
		$.supper("alert",{ title:"操作提示", msg:"请选择数据！"});
		return;
	}
	$.supper("setProductArray", {"name":"selInvInfoArray", "value":rows});
	closeWin();
}


