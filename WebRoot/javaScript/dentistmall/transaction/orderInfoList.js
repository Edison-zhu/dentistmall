var _all_win_searchForm = $("#win_form_search");
var _all_win_searchForm_buyer = $("#win_form_search_buyer");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_main = $("#win_datagrid_main");
var _all_win_datagrid_pg = $("#_all_win_datagrid_pg");
var _all_div_hidden=$("#win_form_hidden");
// var _dd_search_action = 'MdOrderInfoService.findOrderBySearch';
var _all_queryAction = "MdOrderInfoService.getPagerModelObject";
var _all_queryAction2 = 'MdOrderInfoService.getPagerModelObject2';
var _all_deleteAction = "MdOrderInfoService.deleteObject";
var _all_deleteAllAction = "MdOrderInfoService.deleteAllObject";

var _all_win_datagrid;
var _all_win_url_edit ="/jsp/dentistmall/transaction/updateOrderInfo.jsp";
	//"/jsp/dentistmall/transaction/orderInfo.jsp";
var _all_table_Id = "moiId";
var _all_edit_icon = "gears";
var _all_edit_title = "订单信息维护";
var _all_edit_width = 800;
var _all_edit_height = 700;
var _all_datagrid_height ;
var _supplierdblClick = function (data, row, col) {
    if(data.processStatus=='1')
        editSupplierOrder(data.moiId);
    else
        viewSupplierOrder(data.moiId);
}
var _cgydblClick = function (data, row, col) {
    viewCgyOrder(data.moiId);
}
var _admindblClick = function (data, row, col) {
    viewCgyOrder(data.moiId);
}
var _applicantNameService = 'MdOrderInfoService.getAllPagerModelObjectDistinct';
var _purchaseUnitService = 'MdOrderInfoService.getAllPagerModelObjectDistinct';
var _applicantNameSelectAction = {serviceName: _applicantNameService};
var _purchaseUnitSelectAction = {serviceName: _purchaseUnitService};
//供应商查询条件
var _searchSupplierForm={ 
		lineNum:2,
		columnNum:3,
		control:[],
		groupTag:[], 
		items:[ 
				/**
				 * yanglei
				 * 1.订单关键字：商品名称或订单关键字搜索对应的内容
				 */
			   {title:"商品名或订单号",name:"orderCode",type:"text",placeholder:"商品名或订单号"},
			   {title:"订单状态",name:"processStatus",type:"multipleselect",impCode:"PAR171023031218563", placeholder:"订单状态"},
			   // {title:"支付方式",name:"pay_type_str",type:"select",impCode:"", placeholder:"支付方式", data:[{id: '', name: '全部'}, {id: 3, name: '月结'}]},
			{title:"下单时间范围",name:"placeOrderTime_start",type:"text", type:'userdefined',placeholder:"下单开始时间",readOnly:true, renderer: orderTimeInput},
			// {title:"下单结束时间",name:"placeOrderTime_end",type:"text", placeholder:"下单结束时间",readOnly:true}
		]
	}

var _searchCgyForm={ 
		lineNum:2,
		columnNum:3,
		control:[],
		groupTag:[], 
		items:[ 
			   {title:"订单编号",name:"orderCode",type:"text",placeholder:"输入订单编号,回车查询"}, 
			   {title:"供应商名称",name:"applicantName",type:"text", placeholder:"输入供应商名称,回车查询"},
			   {title:"订单状态",name:"processStatus",type:"multipleselect",impCode:"PAR171023031218563", placeholder:"订单状态"},
			   // {title:"下单时间范围",name:"placeOrderTime_start",type:"text", type:'userdefined',placeholder:"下单开始时间",readOnly:true, renderer: orderTimeInput},
			   // {title:"下单结束时间",name:"placeOrderTime_end",type:"text", placeholder:"下单结束时间",readOnly:true}
		]	
	}

var _searchAdminForm={ 
		lineNum:2,
		columnNum:3,
		control:[],
		groupTag:[], 
		items:[ 
			   {title:"订单编号",name:"orderCode",type:"text",placeholder:"订单编号"}, 
			   {title:"供应商名称",name:"applicantName",type:"text", placeholder:"供应商名称"},
			   {title:"采购商名称",name:"purchaseUnit",type:"text", placeholder:"采购商名称"},
			   {title:"订单状态",name:"processStatus",type:"multipleselect",impCode:"PAR171023031218563", placeholder:"订单状态"},
			   // {title:"下单时间范围",name:"placeOrderTime_start",type:"text", type:'userdefined',placeholder:"下单开始时间",readOnly:true, renderer: orderTimeInput},
			   // {title:"下单结束时间",name:"placeOrderTime_end",type:"text", placeholder:"下单结束时间",readOnly:true}
		]	
}

var _searchBuyerForm = {
	lineNum: 3,
	columnNum: 3,
	control:[],
	groupTag:[],
	items:[
		{title:"收货人姓名",name:"addressee",type:"text",placeholder:"收货人姓名"},
		// {title:"支付方式",name:"pay_type_str",type:"text", placeholder:"支付方式", readOnly: true},
		{title:"采购企业",name:"purchaseUnit",type:"text", placeholder:"采购企业名称"},
		{title:"是否开票",name:"needBill",type:"select",impCode:"", placeholder:"是否开票",data:[{id:1, name: '是'}, {id: 2, name: '否'}]},
		{title:"收货人手机号码",name:"addresseeTelephone",type:"text", placeholder:"收货人手机号码"},
		// {title:"下单时间范围",name:"placeOrderTime_start",type:"text", type:'userdefined',placeholder:"下单开始时间",readOnly:true, renderer: orderTimeInput},
		// {title:"下单结束时间",name:"placeOrderTime_end",type:"text", placeholder:"下单结束时间",readOnly:true}
		{title:"支付方式",name:"pay_type_str",type:"select",impCode:"", placeholder:"支付方式", data:[{id: '', name: '全部'}, {id: 3, name: '月结'}]},
	]
}
	 
var _Toolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search},
			// {title:"刷新",id:"win_but_search",icon:"refresh", colourStyle:"default",rounded:true,clickE:main_refresh},
		        {title:"全部导出",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all}
		       ] 
	}
var _supplierToolbar={
		toolBarId:"tools_but",
		items:[
		        {title:"查询",id:"win_but_search",icon:"search", colourStyle:"default",rounded:true,clickE:main_search},
			// {title:"刷新",id:"win_but_search",icon:"refresh", colourStyle:"default",rounded:true,clickE:main_refresh},
			/* {title:"导出订单",id:"win_but_export",icon:"download", colourStyle:"success",rounded:true,clickE:export_all2},
             {title:"导出对账单",id:"win_but_export2",icon:"download", colourStyle:"info",rounded:true,clickE:export_orderList},*/
		       ] 
	} 
 

//供应商grid
var _SupplierDataGrid = {
    cols: [
        {title: '订单编号', sortable: true, name: 'orderCode', width: 80, align: 'center'},
        {title: '下单时间', sortable: true, name: 'placeOrderTime', width: 80, align: 'center'},
        {title: '采购商名称', sortable: true, name: 'purchaseUnit', width: 150, align: 'center'},
        {title: '收件人', sortable: true, name: 'addressee', width: 50, align: 'center'},
        {title: '联系电话', sortable: true, name: 'addresseeTelephone', width: 80, align: 'center'},
        {title: '下单数量', sortable: true, name: 'commodityNumber', width: 40, align: 'center'},
        {title: '下单金额', sortable: true, name: 'placeOrderMoney', width: 40, align: 'center', renderer: formatMoney},
        {title: '确认数量', sortable: true, name: 'number1', width: 30, align: 'center'},
        {title: '确认金额', sortable: true, name: 'actualMoney', width: 30, align: 'center', renderer: formatMoney},
        {
            title: '流程状态',
            sortable: true,
            name: 'processStatus',
            width: 50,
            align: 'center',
            impCode: "PAR171023031218563"
        },
        {title: '操作', name: 'control', width: 160, align: 'center', renderer: supplierControl}
    ]
    , remoteSort: false
    , name: 'orderInfoListGrid'
    , height: _all_datagrid_height
    , gridtype: '2'
    , nowrap: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
    , dblClickFunc: _supplierdblClick
}

var _CgyDataGrid={
    cols: [
        {title: '订单编号', sortable: true, name: 'orderCode', width: 80, align: 'center'},
        {title: '下单时间', sortable: true, name: 'placeOrderTime', width: 80, align: 'center'},
        {title: '供应商名称', sortable: true, name: 'applicantName', width: 100, align: 'center'},
        {title: '收件人', sortable: true, name: 'addressee', width: 50, align: 'center'},
        {title: '下单数量', sortable: true, name: 'commodityNumber', width: 50, align: 'center'},
        {title: '下单金额', sortable: true, name: 'placeOrderMoney', width: 50, align: 'center', renderer: formatMoney},
        {title: '确认数量', sortable: true, name: 'number1', width: 50, align: 'center'},
        {title: '确认金额', sortable: true, name: 'actualMoney', width: 50, align: 'center', renderer: formatMoney},
        {
            title: '流程状态',
            sortable: true,
            name: 'processStatus',
            width: 50,
            align: 'center',
            impCode: "PAR171023031218563"
        },
        {title: '操作', name: 'control', width: 150, align: 'center', renderer: cgyControl}
    ]
    , remoteSort: false
    , name: 'orderInfoListGrid'
    , height: _all_datagrid_height
    , url: getMMGridUrl()
    , gridtype: '2'
    , nowrap: true
    , mmPaginatorOpt: _all_win_datagrid_pg
    , dblClickFunc: _cgydblClick
}

var _mCgyDataGrid = {
    cols: [
        {title: '订单编号', sortable: true, name: 'orderCode', width: 80, align: 'center'},
        {title: '下单时间', sortable: true, name: 'placeOrderTime', width: 80, align: 'center'},
        {title: '供应商名称', sortable: true, name: 'applicantName', width: 100, align: 'center'},
        {title: '采购人', sortable: true, name: 'purchaseAccount', width: 50, align: 'center'},
        {title: '收件人', sortable: true, name: 'addressee', width: 50, align: 'center'},
        {title: '下单数量', sortable: true, name: 'commodityNumber', width: 50, align: 'center'},
        {title: '下单金额', sortable: true, name: 'placeOrderMoney', width: 50, align: 'center', renderer: formatMoney},
        {title: '确认数量', sortable: true, name: 'number1', width: 50, align: 'center'},
        {title: '确认金额', sortable: true, name: 'actualMoney', width: 50, align: 'center', renderer: formatMoney},
        {
            title: '流程状态',
            sortable: true,
            name: 'processStatus',
            width: 50,
            align: 'center',
            impCode: "PAR171023031218563"
        },
        {title: '操作', name: 'control', width: 150, align: 'center', renderer: cgyControl}
    ]
    , remoteSort: false
    , name: 'orderInfoListGrid'
    , height: _all_datagrid_height
    , url: getMMGridUrl()
    , gridtype: '2'
    , nowrap: true
    , mmPaginatorOpt: _all_win_datagrid_pg
    , dblClickFunc: _cgydblClick
}

var _adminDataGrid = {
    cols: [
        {title: '订单编号', sortable: true, name: 'orderCode', width: 80, align: 'center'},
        {title: '下单时间', sortable: true, name: 'placeOrderTime', width: 80, align: 'center'},
        {title: '供应商名称', sortable: true, name: 'applicantName', width: 100, align: 'center'},
        {title: '采购商名称', sortable: true, name: 'purchaseUnit', width: 150, align: 'center'},
        {title: '收件人', sortable: true, name: 'addressee', width: 50, align: 'center'},
        {title: '联系电话', sortable: true, name: 'addresseeTelephone', width: 80, align: 'center'},
        {title: '下单数量', sortable: true, name: 'commodityNumber', width: 50, align: 'center'},
        {title: '下单金额', sortable: true, name: 'placeOrderMoney', width: 50, align: 'center', renderer: formatMoney},
        {title: '确认数量', sortable: true, name: 'number1', width: 50, align: 'center'},
        {title: '确认金额', sortable: true, name: 'actualMoney', width: 50, align: 'center', renderer: formatMoney},
        {
            title: '流程状态',
            sortable: true,
            name: 'processStatus',
            width: 50,
            align: 'center',
            impCode: "PAR171023031218563"
        },
        {title: '操作', name: 'control', width: 100, align: 'center', renderer: adminControl}
    ]
    , remoteSort: false
    , name: 'orderInfoListGrid'
    , height: _all_datagrid_height
    , gridtype: '2'
    , nowrap: true
    , url: getMMGridUrl()
    , mmPaginatorOpt: _all_win_datagrid_pg
    , dblClickFunc: _admindblClick
}

function orderTimeInput(){
	var startTime = '<input type="text" placeholder="选择开始日期" id="placeOrderTime_start" name="placeOrderTime_start" readonly class="form-control" style="width: 48%;display: inline"/> - ';
	var endTime = '<input type="text" placeholder="选择结束日期" id="placeOrderTime_end" name="placeOrderTime_end" readonly class="form-control" style="width: 48%;display: inline"/>';
	return startTime + endTime;
}

//获取当前时间
var time11="";
var time21="";
var time31="";

function writeCurrentDate1() {
	var now = new Date();
	var year = now.getFullYear(); //得到年份
	var month = now.getMonth();//得到月份
	var day = now.getDay();//得到周几
	var date ;
	if (now.getDate()>9){
		date = now.getDate();//得到日期
	}else{
		date ="0"+now.getDate();//得到日期
	}
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	var month1 =month;
	month = month + 1;
	if (month>9){
		month = month;
	}else {
		month ="0"+month;
		month1 ="0"+month1;
	}
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + week;
	$("#currentDate").html(time);
	time11=year+"-"+month+"-"+date;
	time21=year+"-"+month+"-"+(date-1);
	time31=year+"-"+month1+"-"+date;
	//设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。
	var timer = setTimeout("writeCurrentDate()", 1000);
}

/**
 * 页面初始化函数
 */
var organizaType;
var userRole;
var clearSelects;

var allClaomant;
$(function(){

  organizaType=$("#organizaType").val();
  userRole = $("#userRole").val();
  if(organizaType =='100'){
	  _all_win_searchForm.xform('createForm',_searchSupplierForm); 
	  _all_div_hidden.xform('createhidden',_searchSupplierForm.hiddenitems);

  }else if(organizaType =='20001' || organizaType =='20002' || organizaType =='20003'){
	  _all_win_searchForm.xform('createForm',_searchCgyForm); 
	  _all_div_hidden.xform('createhidden',_searchCgyForm.hiddenitems);
	  applicantNameInputSelect();
  }else{
	  _all_win_searchForm.xform('createForm',_searchAdminForm); 
	  _all_div_hidden.xform('createhidden',_searchAdminForm.hiddenitems);
	  applicantNameInputSelect();
	  // purchaseUnitInputSelect();
  }
	_all_win_searchForm_buyer.xform('createForm',_searchBuyerForm);
	purchaseUnitInputSelect();
	// $('#pay_type_str').val('月结');
	$('#pay_type_str').val('3');

	_all_win_searchForm = $('#win_form_search')
  _all_datagrid_height=   $(window).height()-_all_win_searchForm.height()-64-95;
	initOderListUtil({server: 'ShoppingService.getOrderMxModelByMoiId'});
  if(organizaType =='100'){
	  initOderListUtil({server: 'ShoppingService.getOrderMxModelByMoiId'});
	  _all_win_tools_but.xtoolbar('create',_supplierToolbar);
	  //初始化增加状态count
	  initCount();
	  // _SupplierDataGrid.height=_all_datagrid_height;
	  // _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_SupplierDataGrid);
  }else if(organizaType =='20001' || organizaType =='20002' || organizaType =='20003'){
	  _all_win_tools_but.xtoolbar('create',_Toolbar);
	  if(userRole.indexOf("1") >= 0){
		  _mCgyDataGrid.height=_all_datagrid_height;
		  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_mCgyDataGrid);
	  }else if(userRole.indexOf("2") >= 0){
		  _CgyDataGrid.height=_all_datagrid_height;
		  _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_CgyDataGrid);
	  }
	  initCount();
  }else{
	  _all_win_tools_but.xtoolbar('create',_Toolbar);
	  initCount();
	  // _adminDataGrid.height=_all_datagrid_height;
	  // _all_win_datagrid= _all_win_datagrid_main.xdatagrid('create',_adminDataGrid);
  }

	layui.use(['jquery', 'formSelects'], function () {
		var formSelects = layui.formSelects;
		formSelects.config('processStatus', {
			keyName: 'name',
			keyVal: 'id'
		}).data('processStatus', 'server', {
			beforeSuccess: function (id, url, searchVal, result) {        //success之前的回调, 干嘛呢? 处理数据的, 如果后台不想修改数据, 你也不想修改源码, 那就用这种方式处理下数据结构吧
				return result.obj;  //必须return一个结果, 这个结果要符合对应的数据结构
			},
			url: $.supper("getServicePath", {
				"service": 'sysParameterService.getTreeNodeListByParentCode',
				"data": 'parentCode=PAR171023031218563'
			})
		});
		clearSelects = function () {
			formSelects.value('processStatus', [])
		}
	});

	writeCurrentDate();
	$("#placeOrderTime_start").val(time4);
	$("#placeOrderTime_end").val(time1);

	if (organizaType=='100') {
		laydate({
			elem: '#placeOrderTime_start',
			format: 'YYYY-MM-DD'//日期格式
		});
		laydate({
			elem: '#placeOrderTime_end',
			format: 'YYYY-MM-DD' //日期格式
		});
	}


  //20191123 yangfeng 汇总显示
  //   _all_win_datagrid.on('loadSuccess', function (e, data) {
  //       var processStatus = '1';
  //       var items = data.items;
  //
  //       var totalCount = data.totalCount;
  //       var waitCount = filterOrderCount("1", items);
  //       var waitFinCount = filterOrderCount("3", items);
  //       var partCount = filterOrderCount("2", items);
  //       var finishCount = filterOrderCount("5", items);
  //       var backCount = filterOrderCount("6", items);
  //       var cancelCount = filterOrderCount("7", items);
  //
  //       $('#allCount').text(totalCount);
  //       $('#waitCount').text(waitCount);
  //       $('#waitFinCount').text(waitFinCount);
  //       $('#partCount').text(partCount);
  //       $('#finishCount').text(finishCount);
  //       $('#backCount').text(backCount);
  //       $('#cancelCount').text(cancelCount);
  //   })
	writeCurrentDate1();
	var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
	if(selOutOrderType != null && selOutOrderType.stateTime != null){
		allClaomant=selOutOrderType.stateTime;
		$.supper("setProductArray", {"name":"selOutOrderType", "value":null});
	}
	if (allClaomant==undefined) {
		$("#placeOrderTime_start").val();
		$("#placeOrderTime_end").val();
	}else if (allClaomant==1) {
		$("#placeOrderTime_start").val(time11);
		$("#placeOrderTime_end").val(time11);
		// $("#win_but_search").trigger("click");
	}else if (allClaomant==2) {
		$("#placeOrderTime_start").val(time21);
		$("#placeOrderTime_end").val(time21);
	}else if(allClaomant==3){
		$("#placeOrderTime_start").val(time31);
		$("#placeOrderTime_end").val(time11);
	}
	if(organizaType =='20001' || organizaType =='20002' || organizaType =='20003') {
		main_search2();
	}else {
		main_search();
		initCount();
	}
    
  //设置查询按钮大小
    $("#win_but_search").css("width","95px");
    $("#win_but_search").css("vertical-align","middle");
    
    //设置搜索框字体大小
    if($("#orderCode").val() !=null){
  	  $("#orderCode").css("font-size","13px");
    }
    if($("#purchaseUnit").val() !=null){
  	  $("#purchaseUnit").css("font-size","13px");
    }
    if($("#applicantName").val() !=null){
    	  $("#applicantName").css("font-size","13px");
      }
    if($("#processStatus").val() !=null){
    	  $("#processStatus").css("font-size","13px");
      }
    //设置下拉框字体大小  123
    if($("#placeOrderTime_start").val() !=null){
    	  $("#placeOrderTime_start").css("font-size","13px");
      }
    if($("#placeOrderTime_end").val() !=null){
    	  $("#placeOrderTime_end").css("font-size","13px");
      }
    
  //新增回车查询 2019-12-4 yanglei
  	 $("#orderCode").on('keydown', function(){
  		  if (event.keyCode==13) {
  			  $("#win_but_search").trigger("click");
  		  }
  	});
  	/**
	  * yanglei
	  * 关键字查询（支持多个人姓名查询并用/隔开）
	  * 2019-12-18
	  */
  	 $("#addressee").on('keydown', function(){
 		  if (event.keyCode==13) {
 			  $("#win_but_search").trigger("click");
 		  }
 	});
  	 
  	/**
	  * yanglei
	  * 支付方式：全部、现结（支付宝/微信）、月结（按月结算）
	  * 2019-12-18
	  */
  	 $("#pay_type_str").on('keydown', function(){
 		  if (event.keyCode==13) {
 			  $("#win_but_search").trigger("click");
 		  }
 	});
  	/**
	  * yanglei
	  * 采购企业：根据企业的关键字查询自动补全企业名称（若是没有则显示无） 回车查询 
	  * 2019-12-18
	  */
  	 $("#purchaseUnit").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
  	/**
	  * yanglei
	  * 收件人手机号码：手机号码关键字（支持多个手机号码查询并用/隔开） 回车查询 
	  * 2019-12-18
	  */
  	 $("#addresseeTelephone").on('keydown', function(){
		  if (event.keyCode==13) {
			  $("#win_but_search").trigger("click");
		  }
	});
  	 
  	 /**
  	  * 增加展开，隐藏搜索条件
  	  */
  	$('#open').click(function(){//给open绑定一个点击事件;
  		 if($('#win_form_search_buyer').is(':hidden')&&$('#divSearch').is(':hidden')){
  			$('#win_form_search_buyer').slideDown('slow');
  			$('#divSearch').slideDown('slow');
  			$(this).text('收起');
  		 }else{
  			$('#win_form_search_buyer').slideUp('slow');
  			$('#divSearch').slideUp('slow');
  			$(this).text('展开'); 
  		 }
  	});
  /*
   * 增加重置按钮  一键清空文本框的搜索内容
   */
  	$('#divSearch2').click(function(){
  		 $("#addressee").val("");
  		 $("#applicantName").val("");
  		 $("#purchaseUnit").val("");
  		 $("#addresseeTelephone").val("");
  		 $("#orderCode").val("");
  	});

	/**
	 * 增加复选框全选/取消
	 */
	selectOrderAll()
	/**
	 * 获取复选框的值
	 */
	/*$(".items").click(function(){
		var arr = new Array();
		$("#fruit input:checkbox[name='message']:checked").each(function(i){
		arr[i] = $(this).val();
		});
		var vals = arr.join(",");
		console.log(vals,222);
		});
*/

	$('.clickA').on('click', function () {
		var thisId = $(this).attr('id');
		$('a[id^="_item"]').each(function () {
			var id = $(this).attr('id');
			$(this).removeClass('taps-item-active');
			$(this).removeClass('taps-item');
			if (thisId == id) {
				$(this).addClass('taps-item-active');
			} else {
				$(this).addClass('taps-item');
			}
		})
	})

	initChangeMoney();
	$.supper("setProductArray", {"name":"selOutOrderInfo", "value":null});
});
//获取当前时间
var time1="";
var time2="";
var time3="";
var time4="";
function writeCurrentDate() {
	var now = new Date();
	var year = now.getFullYear(); //得到年份
	var month = now.getMonth();//得到月份
	var day = now.getDay();//得到周几
	var date ;
	var dateDay ;
	if (now.getDate()>9){
		dateDay=now.getDate()+1;
		date = now.getDate();//得到日期
	}else{
		dateDay="0"+(now.getDate()+1);
		date ="0"+now.getDate();//得到日期
	}
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	var month1 =month;
	month = month + 1;
	if (month>9){
		month = month;
	}else {
		month ="0"+month;
		month1 ="0"+month1;
	}
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + week;
	$("#currentDate").html(time);
	time1=year+"-"+month+"-"+date;
	time4=(year-1)+"-"+month+"-"+(dateDay);
	//设置得到当前日期的函数的执行间隔时间，每1000毫秒刷新一次。
	var timer = setTimeout("writeCurrentDate()", 1000);
}
function selectOrderAll() {
	$("#all").click(function() {
		// switch (allChecked) {
		// 	case 0:
		// 		$("[name=items]:checkbox").prop("checked", true);
		// 		break;
		// 	case 1:
		// 		$("[name=items]:checkbox").prop("checked", false);
		// 		break;
		// 	case 2:
		// 		$("[name=items]:checkbox").prop("checked", true);
		// 		break;
		// }
		// $("[name=items]:checkbox").prop("checked", this.checked);

	});
	$("[name=items]:checkbox").click(function() {
		var flag = true;
		$("this").each(function() {
			if (!this.checked) {
				flag = false;
			}
		});
		$("#all").prop("checked", flag);
	});
}

function applicantNameInputSelect() {
	$('#applicantName').editableSelect({
		keyEnter: function () {
			$("#win_but_search").trigger("click");
		}
	});
	var data = _all_win_searchForm.serialize({
		keyEnter: function () {
			$("#win_but_search").trigger("click");
		}
	});
	_applicantNameSelectAction.data = data + '&distinctName=applicantName';
	$('#applicantName').on('focus', function () {
		$('#applicantName').editableSelect('clear');
		var shqJson=$.supper("doselectService", _applicantNameSelectAction);
		var items = shqJson.items;
		for(var  i = 0 ;i < items.length ;i++){
			$('#applicantName').editableSelect('add', items[i])
		}
		$('#applicantName').editableSelect('show');
	})
}
function purchaseUnitInputSelect() {
	$('#purchaseUnit').editableSelect();
	var data = _all_win_searchForm.serialize();
	_purchaseUnitSelectAction.data = data + '&distinctName=purchaseUnit';
	$('#purchaseUnit').on('focus', function () {
		$('#purchaseUnit').editableSelect('clear');
		var shqJson=$.supper("doselectService", _purchaseUnitSelectAction);
		var items = shqJson.items;
		for(var  i = 0 ;i < items.length ;i++){
			$('#purchaseUnit').editableSelect('add', items[i])
		}
		$('#purchaseUnit').editableSelect('show');
	})
}
function formatMoney(val,item,rowIndex){
	if(val != null && val!="")
		return toDecimal2(val);
	else
		return "";
}
//供应商操作
function supplierControl(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	if(item.processStatus=='1')
		str += "<a onclick=\"editSupplierOrder('"+  idvalue+"')\"  class='btn btn-info  btn-xs'>修改</a>&nbsp;&nbsp;";
	else
		str += "<a onclick=\"viewSupplierOrder('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>查看</a>&nbsp;&nbsp;";
	var number2 = item.number2?item.number2:0;
	if( number2 < item.commodityNumber && item.processStatus!='6' && item.processStatus!='5'&& item.processStatus!='7')
		str += "<a onclick=\"sendMat('"+  idvalue+"')\"  class='btn btn-success  btn-xs'>发货</a>&nbsp;&nbsp;";
	str += "<a onclick=\"main_supplier_export('"+  idvalue+"')\"  class='btn btn-success  btn-xs'>导出详情</a>&nbsp;&nbsp;";
   return str;
}
//采购员操作
function cgyControl(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	var number3 = item.number3?item.number3:0;
	str += "<a onclick=\"viewCgyOrder('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>查看</a>&nbsp;&nbsp;";
	if(item.processStatus=='2' || item.processStatus=='3' || item.processStatus=='4')
		str += "<a onclick=\"receiveMat('"+  idvalue+"')\"  class='btn btn-info  btn-xs'>收货</a>&nbsp;&nbsp;";
	if((item.number1==null || item.number1=='' || item.number1=='0') && item.processStatus!='6' && number3 ==0)
		str += "<a onclick=\"backMat('"+  idvalue+"')\"  class='btn btn-danger  btn-xs'>退货</a>&nbsp;&nbsp;";
	str += "<a onclick=\"main_export('"+  idvalue+"')\"  class='btn btn-success  btn-xs'>导出</a>&nbsp;&nbsp;";
   return str;
}

function adminControl(val,item,rowIndex){
	var str = "";
	eval( 'var idvalue=  item.'+_all_table_Id);
	str += "<a onclick=\"viewCgyOrder('"+  idvalue+"')\"  class='btn btn-warning  btn-xs'>查看</a>&nbsp;&nbsp;";
   return str;
}
/**
 * 获取数据查询地址
 * @returns
 */
function getMMGridUrl(){
	var data = _all_win_searchForm.serialize();
	var data1 = _all_win_searchForm_buyer.serialize();
	data = data + '&' + data1;
	if(organizaType =='20001' || organizaType =='20002' || organizaType =='20003') {
		var att_url = $.supper("getServicePath", {"service": _all_queryAction2, "data": data});
	} else {
		var att_url = $.supper("getServicePath", {"service": _all_queryAction, "data": data});
	}
	return att_url;
}

//20191123 yangfeng 筛选数量
function filterOrderCount(processStatus, items){
    if(items === null || items === undefined){
        return 0;
    }

    var count = 0;
    $.each(items, function(index, item){
        if(item.processStatus == processStatus){
            count++;
        }
    })
    return count;
}

/**
 * 对主表进行查询（刷新）操作
 */
var _searchName = '';
//重置
function resetSearch() {
	_all_win_searchForm.xform('cleanForm',_searchSupplierForm);
	_all_win_searchForm_buyer.xform('cleanForm',_searchSupplierForm);
	_searchName = '';
	$('#pay_type_str').val('3');
	if(clearSelects !== null && clearSelects !== undefined) {
		clearSelects();
	}
	main_search();
}

function main_search(){
	// _all_win_datagrid.opts.url = getMMGridUrl();
	// _all_win_datagrid.load();
	if(organizaType =='20001' || organizaType =='20002' || organizaType =='20003') {
		main_search2();
		return;
	}
	var placeOrderTime_start= Date.parse($("#placeOrderTime_start").val());
	var placeOrderTime_end= Date.parse($("#placeOrderTime_end").val());
	var day = parseInt((placeOrderTime_end-placeOrderTime_start)/ (1000 * 60 * 60 * 24));//核心：时间戳相减，然后除以天数
	if (day>365) {
		$.supper("alert",{ title:"操作提示", msg:"开始时间和结束时间不能超过一个月！"});
		return;
	}
	initCount();
	// if ((placeOrderTime_end-placeOrderTime_start）)
    var data = _all_win_searchForm.serialize();
    var data1 = _all_win_searchForm_buyer.serialize();
    data = data + '&' + data1;
	// alert();
    _searchName = $('#orderCode').val();
	if (_state == 0) {
		data += '&processStatus=';
	} /*else if (state == 4 || state == 3) {//当传的值为待收货时,往后台传值为3,数据库定义待收货为3,部分收货4也属于待收货
		data ='processStatus_str=' + '3,4';
	} */else {
		data += '&processStatus_str=' + _state;
	}
    try {
        $.supper('initPagination',{id:"Pagination",service:_all_queryAction,data:data,limit:5,isAjax:"1","BackE":initList});
		initCount();
    }catch (e) {
        console.log(e)
    }
}
function main_refresh() {
	if(organizaType =='20001' || organizaType =='20002' || organizaType =='20003') {
		main_search2();
		return;
	}
	_searchName = '';
	$('#orderCode').val('');
	var data = '';
	var data = _all_win_searchForm.serialize();
	var data1 = _all_win_searchForm_buyer.serialize();
	data = data + '&' + data1;

	// var data = _all_win_searchForm.serialize();
	// var data1 = _all_win_searchForm_buyer.serialize();
	// data = data + '&' + data1;
	if (_state == 0) {
		data += '&processStatus=';
	} /*else if (state == 4 || state == 3) {//当传的值为待收货时,往后台传值为3,数据库定义待收货为3,部分收货4也属于待收货
		data ='processStatus_str=' + '3,4';
	} */else {
		data += '&processStatus_str=' + _state;
	}
	try {
		$.supper('initPagination',{id:"Pagination",service:_all_queryAction,data:data,limit:5,isAjax:"1","BackE":initList});
	}catch (e) {
		console.log(e)
	}
	// $('#_item1').click();
	initCount();
}
function main_search2(){
	_all_win_datagrid.opts.url = getMMGridUrl();
	_all_win_datagrid.load();
}
function initList(dataList) {
    initOderList('divList', dataList, false);
  /* $("#scopeBusinessImg").html);*/
}

/**
 * 交易状态
 * @param state
 */
var _state = 0;
function main_search_bystate(state) {
	var data = 'processStatus='+state;

	if (state == 0) {
		data = 'processStatus=';
	} /*else if (state == 4 || state == 3) {//当传的值为待收货时,往后台传值为3,数据库定义待收货为3,部分收货4也属于待收货
		data ='processStatus_str=' + '3,4';
	} */else {
		data ='processStatus_str=' + state;
	}
	_searchName = '';
	$('#orderCode').val('');
	var data1 = _all_win_searchForm.serialize();
	var data2 = _all_win_searchForm_buyer.serialize();
	data = data + '&' + data1 + '&' + data2;

	_state = state;
	$.supper('initPagination',{id:"Pagination",service:_all_queryAction,data:data,limit:5,isAjax:"1","BackE":initList});
	initCount();
}
/**
 * 增加状态count
 * 2019-12-19、
 * yanglei
 */
function initCount(){
	var data = _all_win_searchForm.serialize();
	var data1 = _all_win_searchForm_buyer.serialize();
	data = data + '&' + data1;
	_searchName = $('#orderCode').val();
	// if (_state == 0) {
	// 	data += '&processStatus=';
	// } /*else if (state == 4 || state == 3) {//当传的值为待收货时,往后台传值为3,数据库定义待收货为3,部分收货4也属于待收货
	// 	data ='processStatus_str=' + '3,4';
	// } */else {
	// 	data += '&processStatus_str=' + _state;
	// }
	$.supper("doservice", {"service" : "MdOrderInfoService.countOrder", "data": data,"BackE" : function(jsondata) {
			if (jsondata.code == "1") {
				$("#allCount").html(jsondata.obj.all);
				$('#dfkCount').html(jsondata.obj.dfk);
				$("#dfhCount").html(jsondata.obj.dfh);
				$("#bffhCount").html(jsondata.obj.bffh);
				$("#dshCount").html(jsondata.obj.dsh);
				$("#jycgCount").html(jsondata.obj.jycg);
				$("#jysbCount").html(jsondata.obj.jysb);
				$("#sh").html(jsondata.obj.sh);
			}
		}
	});
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

function editSupplierOrder(moiId){
	var vdata="moiId="+moiId;
	var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/updateSupplierOrderInfo"});
	$.supper("showTtemWin",{ "url":att_url,"title":"修改订单"});
}

function viewSupplierOrder(moiId){
	var vdata="moiId="+moiId;
	var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/viewSupplierOrderInfo"});
	$.supper("showTtemWin",{ "url":att_url,"title":"查看订单"});
}

function viewCgyOrder(moiId){
	var vdata="moiId="+moiId;
	var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/viewCgyOrderInfo"});
	$.supper("showTtemWin",{ "url":att_url,"title":"查看订单"});
}

function sendMat(moiId){
	var vdata="moiId="+moiId;
	// var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/sendOrderInfo"});
	var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/sendOrderInfoNew"});
	$.supper("showTtemWin",{ "url":att_url,"title":"订单发货"});
}

function receiveMat(moiId){
	var vdata="moiId="+moiId;
	var att_url= $.supper("getServicePath", {"service":"MdOrderInfoService.doFindObject","data":vdata,url:"/jsp/dentistmall/transaction/receiveOrderInfo"});
	$.supper("showTtemWin",{ "url":att_url,"title":"订单确认"});
}

function viewSupplierOrderAs(moiId) {
	var vdata = 'moiId=' + moiId;
	var att_url= $.supper("getServicePath", {"service":"","data":vdata,url:"/jsp/dentistmall/transaction/orderaftersale/supplierorderas"});
	$.supper("showTtemWin",{ "url":att_url,"title":"售后信息"});
}

function backMat(moiId){
	var vdata="moiId="+moiId;
	$.supper("confirm",{ title:"退货操作", msg:"确认退货操作？" ,yesE:function(){ 
		$.supper("doservice", {"service":"MdOrderInfoService.saveBackOrderInfo","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				$.supper("alert",{ title:"操作提示", msg:"操作成功！", BackE:main_search});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
	 	}});
	}});
}

function main_export(id){
	var vdata="moiId="+id;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
/**
 * 增加导出出库单
 * yanglei
 * 2020-01-05
 * @param id
 */
function warehouse_export(id){
	var vdata="moiId="+id;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportOutWarehouse","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
function export_all(){
	var vdata = _all_win_searchForm.serialize();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportCgOrderList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
function export_all2(){
	var vdata = _all_win_searchForm.serialize();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportGyOrderList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
function export_orderList(){
	var vdata = _all_win_searchForm.serialize();
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportDzOrderList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
function main_supplier_export(id){
	var vdata="moiId="+id;
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportSupplierInfo","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
function viewSupplierOrderAsMx(masId) {
	var vdata="masId="+masId;
	var att_url= $.supper("getServicePath", {"service":"MdOrderAfterSaleService.getMdIrderSaleAfterObjectByMasId","data":vdata,url:"/jsp/dentistmall/transaction/orderaftersale/supplierasmx"});
	$.supper("showTtemWin",{ "url":att_url,"title":"售后明细"});
}
/**
 * 批量导出全部订单方法
 * 
 */
var moiIds = '';
var excludeMoiIds = '';
var allChecked = 0; // 0不选，1全选，2半选

function exportItem1(selector, moiId) {
	switch (allChecked) {
		case 0:
			allChecked = 1;
			moiIds = moiId + ',';
			$("[name=items]:checkbox").prop("checked", true);
			break;
		case 1:
			allChecked = 0;
			moiIds = '';
			excludeMoiIds = '';
			$("[name=items]:checkbox").prop("checked", false);
			break;
		case 2:
			allChecked = 1;
			excludeMoiIds = '';
			moiId = moiId + ',';
			$("[name=items]:checkbox").prop("checked", true);
			$("#all").prop("checked", true);
			break;
	}
}
function exportItem(selector, moiId) {
	switch (allChecked) {
		case 0:
			if (selector.checked === true) {
				moiIds += moiId + ",";
			} else {
				moiIds = moiIds.replace(moiId + ",", '');
			}
			break;
		case 1:
			allChecked = 2;
			if (selector.checked === true) {
				excludeMoiIds = excludeMoiIds.replace(moiId + ",", '');
				console.log(excludeMoiIds)
				if (excludeMoiIds == '') {
					$("#all").prop("checked", true);
				}
			} else {
				excludeMoiIds += moiId + ",";
				$("#all").prop("indeterminate", true);
			}
			break;
		case 2:
			allChecked = 2;
			if (selector.checked === true) {
				excludeMoiIds = excludeMoiIds.replace(moiId + ",", '');
				if (excludeMoiIds == '') {
					$("#all").prop("checked", true);
				}
			} else {
				excludeMoiIds += moiId + ",";
				$("#all").prop("indeterminate", true);
			}
			break;
	}
	// if (allChecked === true) {
	// 	moiIds = moiIds.replace(moiId + ",", '');
	// 	excludeMoiIds += moiId + ',';
	// }else if (selector.checked === true) {
	// 	moiIds += moiId + ",";
	// 	allChecked = true;
	// } else {
	// 	moiIds = moiIds.replace(moiId + ",", '');
	// }
}
function export_batch_order(){
	var rows=moiIds;
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}
	rows=rows.substring(0,rows.length-1);
	var vdata="";
	if (rows==-1) {
		/*vdata +="&"+_all_win_searchForm.serialize();*/
		var data = _all_win_searchForm.serialize();
	    var data1 = _all_win_searchForm_buyer.serialize();
	    vdata = data + '&' + data1;
	    var excludeIds = '';
	    if (excludeMoiIds != '') {
			excludeIds = excludeMoiIds.substring(0, excludeMoiIds.length - 1);
		}
	    vdata += '&excludeMoiIds=' + excludeIds;
	}else{
		vdata="moiIds="+rows;
	}
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"ExportExcelService.exportCgOrderList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}
//批量导出账单信息
function export_batch_order1(){
	var rows=moiIds;
	if(rows ==null || rows.length ==0){
		$.supper("alert",{ title:"操作提示", msg:"请先选择要操作的数据记录！"});
		return;
	}
	rows=rows.substring(0,rows.length-1);
	var vdata="";
	if (rows==-1) {
		/*vdata +="&"+_all_win_searchForm.serialize();*/
		var data = _all_win_searchForm.serialize();
	    var data1 = _all_win_searchForm_buyer.serialize();
	    vdata = data + '&' + data1;
		var excludeIds = '';
		if (excludeMoiIds != '') {
			excludeIds = excludeMoiIds.substring(0, excludeMoiIds.length - 1);
		}
		vdata += '&excludeMoiIds=' + excludeIds;
	}else{
		vdata="moiIds="+rows;
	}
	var newTab=window.open('about:blank');
	$.supper("doservice", {"service":"MdOrderInfoService.exportDzOrderList","data":vdata, "BackE":function (jsondata) {
		if (jsondata.code == "1") {
			newTab.location.href=jsondata.obj.path;
		}else
			$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
 	}});
}

//修改金额
var changeMoiId;
function initChangeMoney() {
	$('#placeOrderMoney').on('blur', function () {
		var value = $(this).val();
		var em = $('#exoressMoney').val();
		var sm = $('#saleMoney').val();
		var money = parseFloat(value == '' ? 0 : value) + parseFloat(em == '' ? 0 : em) - parseFloat(sm == '' ? 0 : sm);
		if(money <= 0){
			money = 0;
		}
		var msg = '';
		if(CheckUtil.isPlusFloat(money) == false && CheckUtil.isPlusInteger(money) == false){
			msg = "请输入正确的金额！";
		}
		if(msg != '') {
			$('#allMoney').text(msg);
		}else {
			var str = '￥' + toDecimal2(money) + ' = ' + parseFloat(value == '' ? 0 : value) + ' + ' + parseFloat(em == '' ? 0 : em) + ' - ' + parseFloat(sm == '' ? 0 : sm);
			$('#allMoney').text(str);
		}
	})
	$('#exoressMoney').on('blur', function () {
		var pm = $('#placeOrderMoney').val();
		var value = $(this).val();
		var sm = $('#saleMoney').val();
		var money = parseFloat(value == '' ? 0 : value) + parseFloat(pm == '' ? 0 : pm) - parseFloat(sm == '' ? 0 : sm);
		if(money <= 0){
			money = 0;
		}
		var msg = '';
		if(CheckUtil.isPlusFloat(money) == false && CheckUtil.isPlusInteger(money) == false){
			msg = "请输入正确的金额！";
		}
		if(msg != '') {
			$('#allMoney').text(msg);
		}else {
			var str = '￥' + toDecimal2(money) + ' = ' + parseFloat(pm == '' ? 0 : pm) + ' + ' + parseFloat(value == '' ? 0 : value) + ' - ' + parseFloat(sm == '' ? 0 : sm);
			$('#allMoney').text(str);
		}
	})
	$('#saleMoney').on('blur', function () {
		var pm = $('#placeOrderMoney').val();
		var em = $('#exoressMoney').val();
		var value = $(this).val();
		var money = parseFloat(pm == '' ? 0 : pm) + parseFloat(em == '' ? 0 : em) - parseFloat(value == '' ? 0 : value);
		if(money <= 0){
			money = 0;
		}
		var msg = '';
		if(CheckUtil.isPlusFloat(money) == false && CheckUtil.isPlusInteger(money) == false){
			msg = "请输入正确的金额！";
		}
		if(msg != '') {
			$('#allMoney').text(msg);
		}else {
			var str = '￥' + toDecimal2(money) + ' = ' + parseFloat(pm == '' ? 0 : pm) + ' + ' + parseFloat(em == '' ? 0 : em) + ' - ' + parseFloat(value == '' ? 0 : value);
			$('#allMoney').text(str);
		}
	})
}
function checkChangeMoney(placeMoney, expressMoney, saleMoney) {
	var allMoney = parseFloat(placeMoney == '' ? 0 : placeMoney) + parseFloat(expressMoney == '' ? 0 : expressMoney) + parseFloat(saleMoney == '' ? 0 : saleMoney);
	if(CheckUtil.isPlusFloat(allMoney) == false && CheckUtil.isPlusInteger(allMoney) == false){
		$.supper("alert",{ title:"操作提示", msg:"请输入正确的金额！"});
		return false;
	}
	return true;
}
function changeAllMoey(moiId) {
	if(changeMoiId === undefined || changeMoiId === null || changeMoiId === ''){
		$.supper("alert",{ title:"操作提示", msg:"操作出错，请重新选择！"});
		return;
	}

	var placeMoney = $('#placeOrderMoney').val();
	var expressMoney = $('#exoressMoney').val();
	var sm = $('#saleMoney').val();
	if(placeMoney == '' && expressMoney == '' && sm == ''){
		$.supper("alert",{ title:"操作提示", msg:"请输入修改后的金额或者运费或者优惠金额！"});
		return false;
	}
	if(checkChangeMoney(placeMoney, expressMoney, sm) === false){
		return;
	}
	var vdata = $('#filterForm').serialize();
	vdata += '&moiId=' + changeMoiId;
	$.supper("doservice", {"service":"MdOrderInfoService.updateOrderMoney","data":vdata, "BackE":function (jsondata) {
			if (jsondata.code == "1") {
				var obj = jsondata.obj;
				if(obj != null){
					var thisMoiId = obj.moiId;
					var placeOrderMoney = obj.placeOrderMoney;
					var orderExpressMoney = obj.orderExpressMoney;
					$('#pMoney' + thisMoiId).text(toDecimal2(placeOrderMoney));
					$('#eMoney' + thisMoiId).text(toDecimal2(orderExpressMoney));
				}
				cancelChange();
			}else if (jsondata.code == "2") {
				$.supper("alert",{ title:"操作提示", msg: jsondata.meg});
			}else if (jsondata.code == "3") {
				$.supper("alert",{ title:"操作提示", msg: jsondata.meg});
			}else
				$.supper("alert",{ title:"操作提示", msg:"操作失败！"});
		}});
}
function changeMoney(moiId) {
	changeMoiId = moiId;
	$('#divFilter').show('fast');
	$('.u-mask').show();
	var placeOrderMoney = $('#pMoney' + moiId).text();
	var expressMoney = $('#eMoney' + moiId).text();
	if(expressMoney !== undefined && expressMoney !== null && expressMoney != '--'){
		placeOrderMoney -= parseFloat(expressMoney);
		placeOrderMoney = toDecimal2(placeOrderMoney);
	}
	$('#placeOrderMoney').val(placeOrderMoney == undefined ? 0 : placeOrderMoney);
	$('#exoressMoney').val(expressMoney == undefined ? 0 : expressMoney);

	var pm = $('#placeOrderMoney').val();
	var em = $('#exoressMoney').val();
	var sm = $('#saleMoney').val();
	var money = parseFloat(pm == '' ? 0 : pm) + parseFloat(em == '' ? 0 : em) - parseFloat(sm == '' ? 0 : sm);
	if(money <= 0){
		money = 0;
	}
	var msg = '';
	if(CheckUtil.isPlusFloat(money) == false && CheckUtil.isPlusInteger(money) == false){
		msg = "请输入正确的金额！";
	}
	if(msg != '') {
		$('#allMoney').text(msg);
	}else {
		var str = '￥' + toDecimal2(money) + ' = ' + parseFloat(pm == '' ? 0 : pm) + ' + ' + parseFloat(em == '' ? 0 : em) + ' - ' + parseFloat(sm == '' ? 0 : sm);
		$('#allMoney').text(str);
	}
	var top = ($(window).height() - $('#divFilter').height())/4;
	var left = ($(window).width() - $('#divFilter').width())/4;
	var scrollTop = $(document).scrollTop();
	var scrollLeft = $(document).scrollLeft();
	$('#divFilter').css( { position : 'absolute', 'top' : top + scrollTop, left : left + scrollLeft } );
}
function cancelChange() {
	$('#divFilter').hide('fast');
	$('.u-mask').hide();
}