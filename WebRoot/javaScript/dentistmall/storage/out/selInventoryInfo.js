var _all_win_searchForm = $("#win_form_search");
var _all_win_tools_but = $("#win_tools_but");
var _all_win_datagrid_wait_main = $('#win_datagrid_wait_main');
var _all_win_datagrid_wait_pg = $("#_all_win_datagrid_wait_pg");
var _all_div_hidden=$("#win_form_hidden");

var _wait_queryAction = "MdOutOrderService.getPagerMdOutOrder";

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
var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
var _mdpId;
var _mdpsId;
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
        // {title:'入库单号', sortable: true, name:'mooCode', width:80, align: 'center'},
        // {title:'入库时间', sortable: true, name:'orderTime', width:80, align: 'center'},
        // {title:'入库类型', sortable: true, name:'groupName',width:80, align: 'center'},
        // {title:'采购金额', sortable: true, name:'userName',width:80, align: 'center'},
        // {title:'零售金额', sortable: true, name:'number1', width:40, align: 'center'},
        // {title:'采购数量', sortable: true, name:'number2', width:40, align: 'center'},
        // {title:'订单数/发货数/入库数', sortable: true, name:'missingNumber', width:40, align: 'center', renderer: formateOrderOutMissNumberInp},
        // {title:'供应商', sortable: true, name:'flowState', width:80, align: 'center',impCode:"PAR171113111313225"},
        // {title:'采购人', sortable: true, name:'remarks', width:40, align: 'center'},
        // {title:'制作人', sortable: true, name:'remarks', width:40, align: 'center'},
        {title: '分类编号', name: 'mieId', width: 100, align: 'center',renderer: selectKind},
        {title: '物料分类', name: 'mdpName', width: 100, align: 'center'},
        {title: '物料编号', name: 'mmfCode', width: 100, align: 'center',sortable: true},
        {title: '物料名称2', name: 'matName', width: 100, align: 'center'},
        // {title: '图片', name: 'logo', width: 60, align: 'center', renderer: renderLogo},
        {title: '规格型号', name: 'mmfName', width: 50, align: 'center'},
        {title: '库存数量', name: 'baseNumber', width: 20, align: 'center'},
        // {title: '单位/最小单位', name: 'basicUnit', width: 40, align: 'center', renderer: renderUnit},
        // {title: '品牌', name: 'brand', width: 20, align: 'center'},
        // {title: '生产厂家', name: 'productFactory', width: 40, align: 'center'},
        // {title: '采购均价/零售价', name: 'avgPrice', width: 70, align: 'center', renderer: renderPrice},
        {title:'操作', name:'remarks', width:40, align: 'center', renderer: selectOutOrder}
    ]
    , remoteSort: false
    , name: 'mdOutWarehouserListGrid'
    , height: _all_datagrid_height
    , url: getMMGridWaitUrl()
    , nowrap: true
    , multiSelect: true
    , mmPaginatorOpt: _all_win_datagrid_wait_pg,
    dblClickFunc: _dblClick
}
function selectKind(val, item, rowIdex){
    let str = '';
    var mieId = item.mieId == undefined ? '' : item.mieId
    var mdpName = item.mdpName == undefined ? '' :item.mdpName
    str += '<span>' + mieId+ '</span>/<span>' + (item.mdpName == undefined ? mdpName : item.mdpName) + '</span>';
    return str;
}
function _dblClick(data, row, col){
    console.log(col)
    var item = _all_win_datagrid_wait.row(col);
    $.supper("setProductArray", {"name":"selCodeInfo", "value":item});
    closeWin();
    console.log("111")
}
function renderLogo(val, item, rowIdex) {
    let str = '';
    if (item.logoPath != undefined && item.logoPath != '') {
        str += '<img src="' + item.logoPath + '" style="width: 40px; height: 40px;" />';
    }
    return str;
}

function renderUnit(val, item, rowIdex) {
    let str = '';
    var basicUnit = item.basicUnit == undefined ? '' : item.basicUnit
    str += '<span>' + basicUnit+ '</span>/<span>' + (item.splitUnit == undefined ? basicUnit : item.splitUnit) + '</span>';
    return str;
}

function renderPrice(val, item, rowIndx) {
    let str = '';
    str += '<span>' + (item.avgPrice == undefined ? '' : item.avgPrice)+ '</span>/<span>' + (item.retailPrice == undefined ? '' : item.retailPrice) + '</span>';
    return str;
}

function formateOrderOutMissNumberInp(val, item, rowInde){
    var tt = '-';
    var leftNumber = item.number1 - item.number2;
    if(leftNumber > 0){
        tt = '<span style="color: red;">' + leftNumber + '</span>';
    }
    return tt;
}
function formateOutWareMissNumberInp(val, item, rowInde){
    var tt = '-';
    var leftNumber = item.allNumber - item.alreadyNumber;
    if(leftNumber > 0){
        tt = '<span style="color: red;">' + leftNumber + '</span>';
    }
    return tt;
}
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


    // laydate({
    // 	elem: '#finshDate_start',
    // 	format: 'YYYY-MM-DD' //日期格式
    // });
    // laydate({
    // 	elem: '#finshDate_end',
    // 	format: 'YYYY-MM-DD' //日期格式
    // });
    // laydate({
    //     elem: '#orderTimeStart',
    //     format: 'YYYY-MM-DD'
    // });
    // laydate({
    //     elem: '#orderTimeEnd',
    //     format: 'YYYY-MM-DD'
    // });
    // laydate({
    //     elem: '#outTimeStart',
    //     format: 'YYYY-MM-DD'
    // });
    // laydate({
    //     elem: '#outTimeEnd',
    //     format: 'YYYY-MM-DD'
    // });
    //
    // showOrHideReady(false);
    // showOrHideWait(true);
    //
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
    loadItemZtree();


});


//20191125 yangfeng 初始化表格数据

var viewArray = {};

//选择未申领订单操作
function selectOutOrder(val, item, rowIndex){
    var tt = '<a onclick="selOutOrder(' + rowIndex + ')" class="btn btn-primary btn-xs">确定</a>';
    return tt;
}
function saveBaosun(){
    var rows=_all_win_datagrid_wait.selectedRows();
    console.log(rows)
    $.supper("setProductArray", {"name":"selCodeInfo", "value":rows});
    closeWin();
    console.log(rows)
}

function selOutOrder(rowIndex){
    console.log(rowIndex)
    var item = _all_win_datagrid_wait.row(rowIndex);
    if(item==null){
        $.supper("alert",{ title:"操作提示", msg:"请选择数据！"});
        return;
    }
    $.supper("setProductArray", {"name":"selCodeInfo", "value":item});
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
    var data = '';//_all_win_searchForm.serialize();
    data += 'searchName=' + $('#searchName').val();
    if (_mdpId != undefined) {
        data += '&mdpId=' + _mdpId;
    }
    // else if ($('#mdpId').val() != '') {
    //     att_mmgurl += '&mdpId=' + $('#mdpId').val();
    // }
    if (_mdpsId != undefined) {
        data += '&mdpsId=' + _mdpsId;
    }
    var att_url = $.supper("getServicePath", {"service": "MdInventoryService.getPagerViewObject", "data":data});
    return att_url;
}
/**
 * 对主表进行查询（刷新）操作
 */

function main_wait_search(){
    _all_win_datagrid_wait.opts.url = getMMGridWaitUrl();
    _all_win_datagrid_wait.load();

}

function searchByType(){
    main_wait_search();
}


var treeClickLevel = 0;
var loadItemZtree = function () {
    let data = '';
    if (_mdpId != undefined) {
        data += 'mdpId=' + _mdpId;
    }
    if (_mdpsId != undefined) {
        data += '&mdpsId=' + _mdpsId;
    }
    //设置树形数据开始
    var setting = {
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                tags: "tags",
                isParent: "isParent"
            }
        },
        async: {
            enable: true,
            url: $.supper("getServicePath", {
                "service": _all_SiteFirst,
                "data": data,
                autoParam: ["id"],
            }),  //获取异步数据的地址
            autoParam: ["id"],
            dataFilter: filter //设置数据的展现形式
        },
        callback: {//增加点击事件
            beforeClick: function (treeId, treeNode) {
                lastExpandNode = treeNode;//记录当前点击的节点
                treeClickLevel = treeNode.level;
                _mdpId = undefined;
                _mdpsId = undefined;
                if (treeNode.level == 0) {
                    _mdpId = treeNode.id;
                } else {
                    _mdpsId = treeNode.id;
                }
                main_wait_search();
            }
        }
    }
    //设置树的初始数据
    var zNodes = [
        // {id: 0, pId: "", name: "栏目列表", isParent: true}
    ];
    $.fn.zTree.init($("#tree"), setting, zNodes);
    //设置树形数据结束
}

function initTree() {
    let data = '';
    if (_mdpId != undefined) {
        data += 'mdpId=' + _mdpId;
    }
    if (_mdpsId != undefined) {
        data += '&mdpsId=' + _mdpsId;
    }
    //设置树的初始数据
    $.supper("doservice", {
        "service": _all_SiteFirst, 'data': data, "BackE": function (jsondata) {
            $.fn.zTree.init($("#roleTree"), rolSetting, jsondata);
            //设置树形数据结束
            $("#roleTree").css("height", $(window).height() - 120);
        }
    });
}

//设置数据的展现形式
function filter(treeId, parentNode, childNodes) {
    if (!childNodes)
        return null;
    for (var i = 0, l = childNodes.length; i < l; i++) {
        if (childNodes[i].name !== undefined)
            childNodes[i].name = childNodes[i].name.replace('', '');
    }
    return childNodes;
}


//--------------------------------tree---------------------------

//当增加树的数据后刷新当前节点
function loadAddTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null)//刷新当前节点
        zTree.reAsyncChildNodes(lastExpandNode, "refresh");
    main_wait_search();
}

//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null) {
        var zTree = $.fn.zTree.getZTreeObj("tree");
        if (lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
            zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");
    }
    main_wait_search();
}