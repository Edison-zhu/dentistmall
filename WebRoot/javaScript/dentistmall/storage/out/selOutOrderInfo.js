var _all_win_searchForm = $("#accountForm");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_wait_main = $('#win_datagrid_wait_main');
var _all_win_datagrid_wait_pg = $("#_all_win_datagrid_wait_pg");
var _all_div_hidden=$("#win_form_hidden");

var _wait_queryAction = "MdOutOrderService.getPagerMdOutOrderFlowState";

var _all_win_datagrid_wait;
var _all_table_Id = "wowId";
var _all_table_wait_id = 'mooCode';
var _all_edit_icon = "calendar";
var _all_edit_title = "申领详情";
var _all_edit_width = 1000;
var _all_edit_height = 600;
var _all_datagrid_height ;

var _NameService = 'MdOutWarehouseService.getPagerModelObjectDistinct';
var _NameSelectAction = {serviceName: _NameService};
var _customerNameSelectAction = {serviceName: _NameService};
var _waitNameService = 'MdOutOrderService.getPagerMdOutOrderDistinct';
var _waitNameSelectAction = {serviceName: _waitNameService};
var _waitUserNameSelectAction = {serviceName: _waitNameService};
var _all_cahnge_type = '1';
// 单号关键字、申领部门（需要从系统中读取部门信息）、申领人（关键字或拼音首字母）、申请日期（开始及结束）、出库日期（开始及结束），状态（所有、申领中、已完成、撤销）默认搜索状态给出所有。
var _searchForm={
    lineNum:2,
    columnNum:3,
    control:[],
    groupTag:[],
    hiddenitems:[
        {title:"出库单类型",name:"companyType",type:"hidden",value:'1',placeholder:"出库单类型"}
    ],
    items:[
        // {title:"出库单号",name:"wowCode",type:"text",placeholder:"出库单号"},
        {title:'申领单号', id: 'mooCodeSearchInput', name:'mooCode', width:80, placeholder:"输入申领单号,回车查询"},
        // {title:"申领单号", id: 'relatedBill1SearchInput', name:"relatedBill1", placeholder:"申领单号"},
        {title:"申领部门", id:'customer', name:"customer",type:"text",placeholder:"输入申领部门,回车查询"},
        {title:"申领人", id: 'customerName', name:"customerName",type:"text",placeholder:"输入申领人,回车查询"},
        {title:"申领日期范围", name:"orderTime_str", type:'userdefined', readOnly:true, renderer: orderTimeInput},
        {title:"出库日期范围", name:"outTime_str", type:'userdefined', readOnly:true, renderer: outTimeInput},
        {title:'申领状态', name:'flowState', width:80, type: 'select',impCode:"PAR171113111313225", placeholder:"订单状态"},
    ]
}

function waitCodeInput(){
    var tt = '<input type="text" id="mooCodeSearchInput"/>';
    return tt;
}
function readyCodeInput(){
    var tt = '<input type="text" id="relatedBill1SearchInput" />';
    return tt;
}
function orderTimeInput(){
    var startTime = '<input type="text" placeholder="选择开始日期" id="orderTimeStart" name="orderTimeStart" readonly class="form-control" style="width: 48%;display: inline"/> - ';
    var endTime = '<input type="text" placeholder="选择结束日期" id="orderTimeEnd" name="orderTimeEnd" readonly class="form-control" style="width: 48%;display: inline"/>';
    return startTime + endTime;
}
function outTimeInput() {
    var startTime = '<input type="text" placeholder="选择开始日期" id="outTimeStart" name="outTimeStart" readonly class="form-control" style="width: 48%;display: inline"/> - ';
    var endTime = '<input type="text" placeholder="选择结束日期" id="outTimeEnd" name="outTimeEnd" readonly class="form-control" style="width: 48%;display: inline"/>';
    return startTime + endTime;
}

var _Wait_DataGrid = {
    cols: [
        {title:'申领单号', sortable: true, name:'mooCode', width:80, align: 'center'},
        {title:'申领时间', sortable: true, name:'orderTime', width:80, align: 'center'},
        {title:'申领部门', sortable: true, name:'groupName',width:80, align: 'center'},
        {title:'申领人', sortable: true, name:'userName',width:80, align: 'center'},
        {title:'申领总数', sortable: true, name:'number1', width:40, align: 'center',renderer:Application},
        {title:'实际出库数', sortable: true, name:'number2', width:40, align: 'center',renderer:actual},
        {title:'缺少数量', sortable: true, name:'missingNumber', width:40, align: 'center', renderer: formateOrderOutMissNumberInp},
        {title:'申领状态', sortable: true, name:'flowState', width:80, align: 'center',impCode:"PAR171113111313225"},
        {title:'备注', sortable: true, name:'remarks', width:40, align: 'center'},
        {title:'操作', name:'remarks', width:40, align: 'center', renderer: selectOutOrder}
    ]
    , remoteSort: false
    , name: 'mdOutWarehouserListGrid'
    , height: _all_datagrid_height
    , url: getMMGridWaitUrl()
    , nowrap: true
    , multiSelect: false
    , mmPaginatorOpt: _all_win_datagrid_wait_pg
}
function actual(val, item, rowInde) {
    if (item.number7 == undefined){
        item.number7=0;
    }
    var tt = '-';
    tt = '<span>' + item.number2 + '</span>'+'/'+'<span>' + item.number7 + '</span>';
    return tt;
}
function Application(val, item, rowInde) {
    var tt = '-';
        tt = '<span>' + item.number1 + '</span>'+'/'+'<span>' + (item.number6 == undefined ? 0 : item.number6) + '</span>';
    return tt;
}
function formateOrderOutMissNumberInp(val, item, rowInde){
    var tt = '-';
    var leftNumber = item.number1 - item.number2;
    if (item.number7 == undefined){
        item.number7=0;
    }
    var leftNumbers = (item.number6 == undefined ? 0 : item.number6) - item.number7;
    // if(leftNumber > 0){
    // }
    tt = '<span>' + leftNumber + '</span>'+'/'+'<span>' + leftNumbers + '</span>';
    return tt;
}
// function formateOutWareMissNumberInp(val, item, rowInde){
//     var tt = '-';
//     var leftNumber = item.allNumber - item.alreadyNumber;
//     if(leftNumber > 0){
//         tt = '<span style="color: red;">' + leftNumber + '</span>';
//     }
//     return tt;
// }
var allClaomant;
/**
 * 页面初始化函数
 */
$(function(){

    // _all_win_searchForm.xform('createForm',_searchForm);
    //
    // _all_div_hidden.xform('createhidden',_searchForm.hiddenitems);
    //
    // _all_win_tools_but.xtoolbar('create',_Toolbar);


    _Wait_DataGrid.height=_all_datagrid_height;
    _all_win_datagrid_wait = _all_win_datagrid_wait_main.xdatagrid('create',_Wait_DataGrid);
    laydate({
        elem: '#orderTime',
        format: 'YYYY-MM-DD'
    });
    // showOrHideReady(false);
    // showOrHideWait(true);

    // _all_win_datagrid_wait.on('loadSuccess', view_wait);
    // _all_win_datagrid.on('loadSuccess', view_ready);

    //设置按钮宽度与图标随文字居中
    $("#win_but_search").css("width","95px");
    $("#win_but_search").css("vertical-align","middle");
    /*  $("#win_but_add").css("width","95px");
      $("#win_but_add").css("vertical-align","middle");*/

    //设置搜索框字体大小
    if($("#mooCodeSearchInput").val() !=null){
        $("#mooCodeSearchInput").css("font-size","13px");
    }
    //设置搜索框字体大小
    if($("#customer").val() !=null){
        $("#customer").css("font-size","13px");
    }
    if($("#customerName").val() !=null){
        $("#customerName").css("font-size","13px");
    }
    if($("#orderTime_str").val() !=null){
        $("#orderTime_str").css("font-size","13px");
    }
    if($("#outTime_str").val() !=null){
        $("#outTime_str").css("font-size","13px");
    }
    if($("#flowState").val() !=null){
        $("#flowState").css("font-size","13px");
    }
    //查询连接回车
    $("#mooCodeSearchInput").on('keydown', function(){
        if (event.keyCode==13) {
            $("#win_but_search").trigger("click");
        }
    });
    //   $("#customer").on('keydown', function(){
    // 	  if (event.keyCode==13) {
    // 		  $("#win_but_search").trigger("click");
    // 	  }
    // });
    //   $("#customerName").on('keydown', function(){
    // 	  if (event.keyCode==13) {
    // 		  $("#win_but_search").trigger("click");
    // 	  }
    // });
    // initFormInputSelect()

    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if(selOutOrderType != null && selOutOrderType.flowState != null){
        allClaomant=selOutOrderType.flowState;
        if (selOutOrderType.flowState1!=null) {
            allClaomant=selOutOrderType.flowState+","+selOutOrderType.flowState1;
        }
        $.supper("setProductArray", {"name":"selOutOrderType", "value":null});
    }
    $("#flowState").val(allClaomant);
    //main_search();
    main_wait_search();

});


//20191125 yangfeng 初始化表格数据
var viewArray = {};

//选择未申领订单操作
function selectOutOrder(val, item, rowIndex){
    var tt = '<a onclick="selOutOrder(' + rowIndex + ')" class="btn btn-primary btn-xs">选择</a>';
    return tt;
}

function selOutOrder(rowIndex){
    var item = _all_win_datagrid_wait.row(rowIndex);
    if(item==null){
        $.supper("alert",{ title:"操作提示", msg:"请选择数据！"});
        return;
    }
    $.supper("setProductArray", {"name":"selCodeInfo", "value":item.mooId});
    closeWin();
}

function  closeWin(){
    $.supper("closeWin");
}
/**
 * 获取数据查询地址
 * @returns
 */

function getMMGridWaitUrl(){
    var data = _all_win_searchForm.serialize();
    data += '&flowState_str=2,3'
    var att_url = $.supper("getServicePath", {"service": _wait_queryAction, "data":data});
    return att_url;
}
/**
 * 对主表进行查询（刷新）操作
 */

function main_wait_search(){
    _all_win_datagrid_wait.opts.url = getMMGridWaitUrl();
    _all_win_datagrid_wait.load();
}
function formEnter() {
    if (event.keyCode == 13)
        main_wait_search();
}

function searchByType(){
    main_wait_search();
}
