var queryAction ="MdOutOrderService.getPagerMdOutOrder";
$(function(){   
	initDataGrid();
	vsearch();
});
var mmg ;
var initDataGrid =  function(){
	    var cols = [
	                {title:'申领单号', name:'mooCode', width:80, align: 'center'},  
	                {title:'申领人', name:'userName',width:80, align: 'center'},  
	                {title:'申领部门', name:'groupName',width:80, align: 'center'},
		 		    {title:'申领数量', name:'number1', width:40, align: 'center'}, 
		 		    {title:'出库数量', name:'number2', width:40, align: 'center'}
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
	              , checkCol: true
	              ,showBackboard:false
	              , fullWidthRows: true
	              , autoLoad: false
	              , plugins: [
	                  $('#pg').mmPaginator({})
	              ]
	          });  
	          mmg.load(); 
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


