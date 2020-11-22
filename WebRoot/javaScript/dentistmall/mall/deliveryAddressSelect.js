var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");

var _all_queryAction = "MdDeliveryAddressService.getPagerModelObject";
var _all_deleteAction = "MdDeliveryAddressService.deleteObject";
var _all_deleteAllAction = "MdDeliveryAddressService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit = "/jsp/dentistmall/mall/updateDeliveryAddress.jsp";
var _all_table_Id = "mdaId";
var _all_edit_icon = "gears";
var _all_edit_title = "收货地址维护";
var _all_edit_width = 800;
var _all_edit_height =400;
var _all_datagrid_height ;

var _searchForm={ 
		lineNum:1,
		columnNum:2,
		control:[],
		groupTag:[], 
		items:[ 
			   {title:"所在区域",name:"location",type:"text",placeholder:"所在区域"}, 
			   {title:"收件人",name:"addressee",type:"text", placeholder:"收件人"}
		]	
	};
	 
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search}, 
		       	{title:"确定选中",id:"win_but_add",icon:"plus", colourStyle:"primary",rounded:true,clickE:main_select} 
		       ] 
	} ;
var _DataGrid={
			cols: [
			    	{title:'所在区域', sortable: true, name:'location', width:80, align: 'center'},
			    	{title:'配送地址', sortable: true, name:'deliveryAddress', width:80, align: 'center'},
			    	{title:'邮编', sortable: true, name:'zipCode', width:80, align: 'center'},
			    	{title:'收件人', sortable: true, name:'addressee', width:80, align: 'center'},
			    	{title:'收件联系电话', sortable: true, name:'addresseeTelephone', width:80, align: 'center'},
			    	{title:'收件人手机', sortable: true, name:'addresseeMobile', width:80, align: 'center'},
			    	{title:'是否默认', sortable: true, name:'ifDefault', width:80, align: 'center'}
			    	//,			    	 {title:'操作' ,name:'control',width:100,  align:'center',renderer:control }
		               
	           ]
				, remoteSort: false
			,nowrap:true
			,multiSelect:false 
			,name:'deliveryAddressListGrid'
			,height:_all_datagrid_height
			, url:getMMGridUrl() 
	        ,mmPaginatorOpt:_all_win_datagrid_pg
	};


/**
 * 页面初始化函数
 */
$(function(){ 
	
  _all_win_searchForm.xform('createForm',_searchForm); 
  
  _all_div_hidden.xform('createhidden',_searchForm.hiddenitems);
  
  _all_win_tools_but.xtoolbar('create',_Toolbar); 
  
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-95; 
  
  _DataGrid.height=_all_datagrid_height;
  
  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_DataGrid); 
  
  main_search();
  
});   

/**
 * 选中记录操作事件
 *  
 */
function main_select(){ 
	
	var rows=_all_win_datagrid.selectedRows();
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}  
	$.supper("setProductArray", {"name":"win_address", "value":rows[0]}); 
	$.supper("closeWin");  
} 

/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl(){	
	var data = _all_win_searchForm.serialize();
	var att_url= $.supper("getServicePath", {"service":_all_queryAction, "data":data}); 
	return att_url;
}
/**
 * 对主表进行查询（刷新）操作
 */
function main_search(){
	_all_win_datagrid.opts.url = getMMGridUrl();
	_all_win_datagrid.load();
}
 

 