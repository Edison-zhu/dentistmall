
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_tools_but = $("#win_tools_but");
var _all_div_hidden=$("#win_form_hidden");
var _all_win_searchForm=$("#tableSH");
var _all_win_searchForm_hj=$("#tableSH_hj");

var _all_queryAction = "MdOrderMxService.getPagerModelObject";
var _all_deleteAction="MdOrderMxService.deleteObject"
var _all_moiId;
var _all_win_datagrid;
 
var _all_table_Id = "moiId";
 
var _all_datagrid_height ;
  
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		       	{title:"批量删除",id:"win_but_alldel",icon:"close", colourStyle:"success",rounded:true,clickE:main_allDelete} 
		       	,{title:"整单确认",id:"win_but_allUp",icon:"save", colourStyle:"success",rounded:true,clickE:main_allUp} 
		       ] 
	}

var _DataGrid={
			cols: [  
	                {title:'商品编码', sortable: true, name:'matCode', width:80, align: 'center'},
	                {title:'商品名称', sortable: true, name:'matName', width:80, align: 'center'},
	               {title:'商品数量', sortable: true, name:'matNumber', width:80, align: 'center'},
			    	{title:'单价', sortable: true, name:'unitMoney',width:80, align: 'center'},
			    	{title:'总价', sortable: true, name:'totalMoney', width:80, align: 'center'},
			    	{title:'基本单位', sortable: true, name:'basicUnit', width:80, align: 'center'},
			    	{title:'规格', sortable: true, name:'norm', width:80, align: 'center'} ,
			    	 {title:'操作' ,name:'control',width:100,  align:'center',renderer:control }    
	                
	           ]
			, remoteSort: false
			,name:'orderMxInfoListGrid'
			,height:_all_datagrid_height
			, url:getMMGridUrl() 
	       
	}

/**
 * 页面初始化函数
 */
$(function(){ 
	
	_all_moiId = $.supper("getParam", _all_table_Id); 
	
   _all_win_tools_but.xtoolbar('create',_Toolbar); 
	  
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-_all_win_searchForm_hj.height()-64-95; 
  
  _DataGrid.height=_all_datagrid_height;
  
  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid); 
  
  main_search();
  
  initLaber();
  
});  

function control(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"main_delete('"+  idvalue+"')\"  class='btn btn-danger  btn-xs'>删除</a> "; 
	str += "<a onclick=\"main_sh('"+  idvalue+"')\"  class='btn btn-info  btn-xs'>收货确认</a> ";
	str += "<a onclick=\"main_th('"+  idvalue+"')\"  class='btn btn-info  btn-xs'>退货确认</a> ";
   return str;
}


function  main_sh(id){
	
}

function  main_th(id){
	
} 

function main_allUp(){
	
}

/**
 * 修改合计区域信息
 */
function changhjdiv(){
	var rows_data=_all_win_datagrid.rows();
	var rowData={};	
	// 商品总数：
	  rowData.commodityNumber=0;
	// 确认总数：
	  rowData.allNumber=0;
	// 未确认数量：
	   rowData.notNumber=0;
	// 退货数量
	  rowData.backNumber=0;
	// 总价
	   rowData.placeOrderMoney=0;	
	for(var i=0;i<rows_data.lenght;i++){
		rowData.commodityNumber=rowData.commodityNumber+(rows_data[i].matNumber?rows_data[i].matNumber:0);
		rowData.allNumber=rowData.allNumber+(rows_data[i].number1?rows_data[i].number1:0);
		rowData.notNumber=rowData.notNumber+(rows_data[i].number2?rows_data[i].number2:0);
		rowData.backNumber=rowData.backNumber+(rows_data[i].number3?rows_data[i].number3:0); 
		rowData.placeOrderMoney=rowData.placeOrderMoney+(rows_data[i].totalMoney?rows_data[i].totalMoney:0);
	} 
	// 商品总数：
	  rowData.commodityNumber=rowData.commodityNumber==0?"0":rowData.commodityNumber+"";
	// 确认总数：
	  rowData.allNumber=rowData.allNumber==0?"0":rowData.allNumber+"";
	// 未确认数量：
	   rowData.notNumber=rowData.notNumber==0?"0":rowData.notNumber+"";
	// 退货数量
	  rowData.backNumber=rowData.backNumber==0?"0":rowData.backNumber+"";
	// 总价
	   rowData.placeOrderMoney=rowData.placeOrderMoney==0?"0":rowData.placeOrderMoney+"";
	
	changeLaber(rowData,false); 
}



/**
 * 初始化Laber
 */
function initLaber(){
	changeLaber({},true);
	var vdata="moiId="+_all_moiId;
	var _all_initLaberAction="MdOrderInfoService.findFormObject";
	$.supper("doservice", {"service":_all_initLaberAction,"data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1"&&jsondata.obj) {
			changeLaber(jsondata.obj,false);
			changhjdiv();
		}
 	}});
}

/**
 * 修改地址
 */
function edit_address(){
	var att_url="/jsp/dentistmall/mall/deliveryAddressSelect.jsp";
	 var vdata= "";
	var att_url= $.supper("getServicePath", {"data":vdata,url:att_url});
    var tt_win=$.supper("showWin",{url:att_url,title:"选中收货地址",icon:"fa-car",width:900,height:600, BackE:function(){
    	var rowData= $.supper("getProductArray", "win_address"); 
    	var Data={"addressee":rowData.addressee,"delivery_address":rowData.deliveryAddress,"addressee_Mobile":rowData.addresseeMobile};
    	changeLaber(Data,false);
    	$.supper("setProductArray", {"name":"win_address", "value":{}}); 
    }}); 
}

/**
 * 修改Laber事件
 * 
 */ 
 function changeLaber(rowData,type){
	 //收货人:
     var att_addressee=rowData.addressee;
     if(att_addressee){ $("#addressee").text(att_addressee); }else if(type){$("#addressee").text("");} 
	     // 收货地址:
	var att_delivery_address = rowData.delivery_address;
	// 收货人电话：
	var att_addressee_Mobile = rowData.addressee_Mobile;
	// 单据编号:
	var att_orderCode = rowData.orderCode;
	// 供应商:
	var att_purchaseUnit = rowData.purchaseUnit;
	// 公司地址:
	var att_corporateDomicile = rowData.corporateDomicile;
	// 公司电话：
	var att_phoneNumber = rowData.phoneNumber;
	// 下单日期：
	var att_placeOrderTime = rowData.placeOrderTime;
	
	// 商品总数：
	var att_commodityNumber = rowData.commodityNumber;
	// 确认总数：
	var att_allNumber = rowData.allNumber;
	// 未确认数量：
	var att_notNumber = rowData.notNumber;
	// 退货数量
	var att_backNumber = rowData.backNumber;
	// 总价
	var att_placeOrderMoney = rowData.placeOrderMoney;

	if (att_delivery_address) {
		$("#delivery_address").text(att_delivery_address);
	} else if (type) {
		$("#delivery_address").text("");
	}
	if (att_addressee_Mobile) {
		$("#addressee_Mobile").text(att_addressee_Mobile);
	} else if (type) {
		$("#addressee_Mobile").text("");
	}
	if (att_orderCode) {
		$("#orderCode").text(att_orderCode);
	} else if (type) {
		$("#orderCode").text("");
	}
	if (att_purchaseUnit) {
		$("#purchaseUnit").text(att_purchaseUnit);
	} else if (type) {
		$("#purchaseUnit").text("");
	}
	if (att_corporateDomicile) {
		$("#corporateDomicile").text(att_corporateDomicile);
	} else if (type) {
		$("#corporateDomicile").text("");
	}
	if (att_phoneNumber) {
		$("#phoneNumber").text(att_phoneNumber);
	} else if (type) {
		$("#phoneNumber").text("");
	}
	if (att_placeOrderTime) {
		$("#placeOrderTime").text(att_placeOrderTime);
	} else if (type) {
		$("#placeOrderTime").text("");
	}
	if (att_commodityNumber) {
		$("#commodityNumber").text(att_commodityNumber);
	} else if (type) {
		$("#commodityNumber").text("");
	}
	if (att_allNumber) {
		$("#allNumber").text(att_allNumber);
	} else if (type) {
		$("#allNumber").text("");
	}
	if (att_notNumber) {
		$("#notNumber").text(att_notNumber);
	} else if (type) {
		$("#notNumber").text("");
	}
	if (att_backNumber) {
		$("#backNumber").text(att_backNumber);
	} else if (type) {
		$("#backNumber").text("");
	}
	if (att_placeOrderMoney) {
		$("#placeOrderMoney").text(att_placeOrderMoney);
	} else if (type) {
		$("#placeOrderMoney").text("");
	}  
 }


/**
 * 获取数据查询地址
 * 
 * @returns
 */
function getMMGridUrl(){	
	if(_all_moiId){
		var data = "moiId"+_all_moiId;
		var att_url= $.supper("getServicePath", {"service":_all_queryAction, "data":data}); 
	}
	return att_url;
}
/**
 * 对主表进行查询（刷新）操作
 */
function main_search(){
	if(_all_moiId){
		_all_win_datagrid.opts.url = getMMGridUrl();
		_all_win_datagrid.load();
	}
}

 
/**
 * 修改记录操作事件
 * @param id  选中行记录主键值
 */
function main_edit(id){
	var att_url="/jsp/dentistmall/transaction/invoice.jsp";
	eval("var data= '"+_all_table_Id+"="+id+"'");
	var att_url= $.supper("getServicePath", {"data":data,url:att_url});
	//var tt_win=$.supper("showWin",{url:att_url,title:_all_edit_title,icon:"fa-"+_all_edit_icon,width:_all_edit_width,height:_all_edit_height, BackE:main_search});
	$.supper("showTtemWin",{ "url":att_url,"title":_all_edit_title});
}  
/**
 * 删除选中行记录
 * @param id 选中行记录主键值
 */
function main_delete(id){ 
	eval("var vdata= '"+_all_table_Id+"="+id+"'"); 
	$.supper("confirm",{ title:"删除操作", msg:"确认删除记录操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":_all_deleteAction,"data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
} 

/**
 * 删除多条选中行记录
 */
function main_allDelete(){ 
	var rows=_all_win_datagrid.selectedRows();
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}
	var rbsIds="";
	for(var i =0 ;i < rows.length;i++){
		eval('rbsIds += rows[i].'+_all_table_Id+'+","; ' ); 
	}
	rbsIds = rbsIds.substring(0,rbsIds.length-1);	
	$.supper("confirm",{ title:"批量删除操作", msg:"确认删除记录操作？", yesE:function(){		 
		eval("var vdata= '"+_all_table_Id+"s="+rbsIds+"'"); 		
		$.supper("doservice", {"service":_all_deleteAllAction,"data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}


 