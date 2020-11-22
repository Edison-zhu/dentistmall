$(function(){   
	initDataGrid();
	//search();
});
var mmg ;
var queryAction = "MdSupplierService.getPagerModelObject";
var initDataGrid =  function(){
	    var cols = [
	                {title:'供应商编号', name:'legalCertificateNo',width:80, align: 'center'},  
			       	{title:'供应商名称', name:'applicantName', width:80, align: 'center'},  
			    	{title:'联系人', name:'legalPerson',width:80, align: 'center'},  
			    	{title:'联系电话', name:'phoneNumber',width:80, align: 'center'},  
			    	{title:'邮箱', name:'mailbox',width:80, align: 'center'}
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
	              ,showBackboard:false
	              , multiSelect: true
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
	$.supper("setProductArray", {"name":"selSupplierInfo", "value":rows[0]});
	closeWin();
}


