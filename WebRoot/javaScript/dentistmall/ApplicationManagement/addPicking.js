var _all_searchLBName = 'this is an empty search name'; //查询结果是否存在高亮显示
var needHighlight = false;
var  allClaomant="";
var  mmtId1="";
var _mdpId;
var _mdpsId;
var _all_SiteFirst = 'MdMaterielPartService.getFirstObject';
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
                searchName2(1);
            }
        }

    }
    //设置树的初始数据
    var zNodes = [
        // {id: 0, pId: "", name: "类别列表", isParent: true}
    ];
    $.fn.zTree.init($("#tree"), setting, zNodes);
    //自动展现第一层树
    var zTree = $.fn.zTree.getZTreeObj("tree");
    var node = zTree.getNodesByParam("id","0");
    lastExpandNode=node;
    zTree.expandNode(node[0],  true, false, false);

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
    searchName2();
}

//当修改树的数据后刷新当前节点的父节点
function loadUpdateTree() {
    var zTree = $.fn.zTree.getZTreeObj("tree");
    if (lastExpandNode != null) {
        var zTree = $.fn.zTree.getZTreeObj("tree");
        if (lastExpandNode.getParentNode() != null)//刷新当前节点的父节点
            zTree.reAsyncChildNodes(lastExpandNode.getParentNode(), "refresh");
    }
    searchName2();
}

$(function () {
    var treejump = $.supper("getProductArray", "treeJumps");
    // console.log("tree"+treejump)
    if (treejump!= null && treejump._mdpId != null){
        $.supper("setProductArray", {"name": "selOutOrderType", "value": null});
        // console.log("=====>>"+treejump._mdpId)
        _mdpId = treejump._mdpId;
        _mdpsId = treejump._mdpsId;
         // searchAll(_mdpId,_mdpsId)
    }

    $('.search-sort .icon-finance').click(function () {
        $(this).toggleClass("sort-up sort-down");
    });
    // console.log($("#searchLBName").val(), 'ss');
    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if (selOutOrderType != null && selOutOrderType.searchName1 != null) {
        allClaomant = selOutOrderType.searchName1;
         $("#searchName").val(allClaomant);
        // searchAll();*/
        $.supper("setProductArray", {"name": "selOutOrderType", "value": null});
    }

    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if (selOutOrderType != null && selOutOrderType.mmtId != null) {
        mmtId1 = selOutOrderType.mmtId;
        $.supper("setProductArray", {"name": "selOutOrderType", "value": null});
    }
    var tips;
    var message = '移除收藏夹';
    $('.product-operation a:nth-child(1)').on({
        mouseenter:function(){
            var that = this;
            tips =layer.tips(message,that,{tips:[2,'#fff'],time:0,area: 'auto',maxWidth:500});
        },
        mouseleave:function(){
            layer.close(tips);
        }
    });
    $('#searchName').bind('keyup', function(event) {
        if (event.keyCode == "13") {
            //回车执行查询
            $('#search_button').click();
            searchName2()
        }
    });
    // console.log($("#searchLBName").val(), 'ssaaaaas');
    // alert(mmtId1);
    $("#searchLBName").val(allClaomant);
    searchAll();
    countCollect1();
    loadItemZtree();
    _mdpId = undefined;
    _mdpsId = undefined;
});

var searchNames;
function searchName2(exclude) {
    searchNames=$("#searchName").val();
    searchAll(exclude);
}
function searchName3(search_name) {
    var searchName = $.trim($("#searchName").val());
    if (search_name !== undefined && search_name !== null) {
        searchName = $.trim(search_name);
    }
    saveSearchCookie(searchName);
    if (_searchType == 'supplier') {
        if (searchName != null && searchName != "") {
            window.location.href = "hzcj.htm?searchName=" + searchName;
        }
    } else if (_searchType == 'mat') {
        if (searchName != null && searchName != "") {
            window.location.href = "liebiao.htm?searchName=" + searchName;
        }
    }
}
var _parrall=[];
function brandSearch1(id, i) {
    if(i==0){
        _parrall=[]
        $('#brand0').css("color","#1ab394");
        $('#brand0').css("font-weight","bold");
        $('#brand1').css("color","#333");
        $('#brand1').css("font-weight","normal");
        $('#brand2').css("color","#333");
        $('#brand2').css("font-weight","normal");
        $('#brand3').css("color","#333");
        $('#brand3').css("font-weight","normal");
    }else {
        let idx = _parrall.indexOf($('#' + id).text());
        if (idx >= 0) {
            _parrall.splice(idx,1)
        } else {
            _parrall.push($('#' + id).text());
        }
    }
    if (_parrall.length > 0){
        $('#brand0').css("color","#333");
        $('#brand0').css("font-weight","normal");
        for (var k = 0, l = _parrall.length; k < _parrall.length; k++){
            if(_parrall[k] == $('#' + id).text()){
                $('#' + id).css("color","#1ab394");
                $('#' + id).css("font-weight","bold");
            }else {
                $('#' + id).css("color","#333");
                $('#' + id).css("font-weight","normal");
            }
        }
    }else {
        $('#brand1').css("color","#333");
        $('#brand1').css("font-weight","normal");
        $('#brand2').css("color","#333");
        $('#brand2').css("font-weight","normal");
        $('#brand3').css("color","#333");
        $('#brand3').css("font-weight","normal");
        _parrall = []
    }
    console.log('_parrall------->',_parrall)
    $('#brand').val(_parrall.join(','));
    searchAll();
}


function searchAll() {
    if (searchNames!=null&&searchNames!=undefined) {
        $('#searchLBName').val(searchNames);
    }
    if (mmtId1!=null&&mmtId1!=undefined) {
        $('#mmtId').val(mmtId1);
    }
    var data = $("#searchForm").serialize();
    if (_mdpId != undefined)
        data += '&mdpId=' + _mdpId;
    if (_mdpsId != undefined)
        data += '&mdpsId=' + _mdpsId;

    showLoading();
    $.supper('initPagination',{id:"Pagination",service:"MdOutOrderService.getPagerModelBySearchName",data:data,limit:24,isAjax:"1","BackE":initAddPickingList}
    );

}

function showLoading() {

    var inntHtml = '';
    inntHtml += '<div id="loading-mask"  class="u-shade" style="z-index:9098910159999;display:block ">';
    inntHtml += '<img src="/dentistmall/img/loading.gif" alt=""></div>';

    // if (top == self) {
    $(this.document.body).append(inntHtml);
    $('#loading-mask').fadeIn('fast');
    // 是顶级窗口
    // } else {
    //     // 不是顶级窗口
    //     return top.showLoading();
    // }
}

function closeLoading(){
    // if (top == self) {
    $('#loading-mask').fadeOut('fast');
    $('#loading-mask').remove();
    // 是顶级窗口
    // } else {
    //     // 不是顶级窗口
    //     return top.closeLoading();
    // }

}
function weightShowMore(){
    $("#weightOpen").hide();
    $("#weightClose").show();
    $(".font-weight-trigger").each(function(){
        $(this).show();
    });
}
function weightCloseMore(){
    $("#weightOpen").show();
    $("#weightClose").hide();
    var index=0;
    $(".font-weight-trigger").each(function(){
        index++;
        if(index > 9)
            $(this).hide();
    });
}

function categoryShowMore(){
    $("#categoryOpen").hide();
    $("#categoryClose").show();
    $(".category-trigger").each(function(){
        $(this).show();
    });
}
function categoryCloseMore(){
    $("#categoryOpen").show();
    $("#categoryClose").hide();
    var index=0;
    $(".category-trigger").each(function(){
        index++;
        if(index > 9)
            $(this).hide();
    });
}


function brandSearch(brand,bid){
    $(".font-weight-trigger").removeClass("active");
    $("#brand_"+bid).addClass("active");
    $("#brand").val(brand);
    var data = $("#searchForm").serialize();
    $.supper('initPagination',{id:"Pagination",service:"MdOutOrderService.getPagerModelBySearchName",data:data,limit:20,isAjax:"1","BackE":initAddPickingList});
}

function categorySearch(category,bid){
    $(".category-trigger").removeClass("active");
    $("#category_"+bid).addClass("active");
    $("#mmtId").val(category);
    var data = $("#searchForm").serialize();
    $.supper('initPagination',{id:"Pagination",service:"MdOutOrderService.getPagerModelBySearchName",data:data,limit:20,isAjax:"1","BackE":initAddPickingList});
}

function setDefault(){
    $(".search-sort label").removeClass("active");
    $("#default").addClass("active");
    $("#isDefault").val("1");
    $("#numOrder").val("0");
    $("#priceOrder").val("0");
    var data = $("#searchForm").serialize();
    $.supper('initPagination',{id:"Pagination",service:"MdOutOrderService.getPagerModelBySearchName",data:data,limit:20,isAjax:"1","BackE":initAddPickingList});
}
function setPrice(){
    var orType=$("#price").attr("class");
    $("#isDefault").val("0");
    $("#numOrder").val("0");
    if(orType.indexOf('sort-up')>-1){//由高到底
        $("#priceOrder").val("2");
    }else{//由低到高
        $("#priceOrder").val("1");
    }
    $(".search-sort label").removeClass("active");
    $("#price").addClass("active");
    var data = $("#searchForm").serialize();
    $.supper('initPagination',{id:"Pagination",service:"MdOutOrderService.getPagerModelBySearchName",data:data,limit:20,isAjax:"1","BackE":initAddPickingList});
}
function setNumber(){
    // $(".search-sort label").removeClass("active");
    // $("#number").addClass("active");
    // $("#isDefault").val("0");
    // $("#priceOrder").val("0");
    // $("#numOrder").val("2");
    var data = $("#searchForm").serialize();
    $.supper('initPagination',{id:"Pagination",service:"MdOutOrderService.getPagerModelBySearchName",data:data,limit:20,isAjax:"1","BackE":initAddPickingList});
}
var wmms = {};

function initAddPickingList(dataList){
    closeLoading();
    mmtId1="";
    var str = "";
    var state;
    var wmsMiId2;
    let wmsMiIds = [];
    var isLogin=$("#isLogin").val();
    let aliasName = '';
    if(dataList != null && dataList.length > 0){
        for(var i =0;i < dataList.length;i++){
            var data = dataList[i];
            // str = '';
            if(i%6==0)
                str +="<li id='li"+data.wmsMiId+"'>";
            else
                str +="<li style=\"margin-left:10px\">";
            wmsMiId2=data.wmsMiId;
            wmsMiIds.push(wmsMiId2);

            if (wmms[wmsMiId2] == undefined)
                wmms[wmsMiId2] = {};
            wmms[wmsMiId2].item = data;
            str += "<a onclick='Editalias("+wmsMiId2+")'><div style='float: right;'><img style=\" width: 20px;height: 21px\"\n" +
                "                                 src=\"../../../../dentistmall/css/shopping/images/edit1.png\"></div></a><div class=\"product-img-container\"><a onclick='getNormMx("+data.wmsMiId+")' style='width: 100%; height: 100%'><img src=\""+data.lessenFilePath+"\" alt=\"\"></a></div>";
            str += "<p class=\"product-name ellipsis\"><span id='searchNameMatch" + data.matName + "' onclick='getNormMx("+data.wmsMiId+")' style='cursor: pointer' >"+data.matName+"</span></p></a>";

            // if (data.linkWmsMiId != undefined)
            //     aliasName = data.aliasName2 == undefined ? '' : data.aliasName2;
            // else if (data.aliasName3 != undefined)
            //     aliasName = data.aliasName3 == undefined ? '' : data.aliasName3;
            // else
                aliasName = data.aliasName == undefined ? '' : data.aliasName;
            if (aliasName != '')
                str +="<p class=\"product-desc m-top10 ellipsis\" style='color: red'>别名："
                    +"<a style=\"color:#888;cursor:pointer\" onclick='Editalias("+wmsMiId2+")'  target=\"_blank\"><span id='searchNameMatch" + aliasName + "'>"+aliasName+"</span></a> </p>";

             str += "<p class=\"product-desc ellipsis\" style='height: 24px'> <span>";
            if(data.norm!=undefined && data.norm!=null) {
                var normQ=data.norm;
                var reg = RegExp(/、/)
                if (normQ.match(reg)) {
                    state=1;
                    str += "<a id='getNormMx' onclick='getNormMx("+data.wmsMiId+")'class=\"btn btn-default btn-xs\" style='width: 95px;height: 22px;'>选择规格</a></span>";
                }else {
                    state=2;
                    str += "<span>"+data.norm+"</span></span>";
                }
            }else
                str += "&nbsp;&nbsp;";
            str += "</span></p><p class=\"product-price-container flex m-top10\">";
            if(isLogin=='1')
                str += "<span class=\"red\">￥"+toDecimal2(data.money1)+"</span>";
            str +="<span class=\"product-sale-count\">库存总数量：<b>"+(data.base_numbers!=null?data.base_numbers:0)+"</b>"+data.basicUnit+(data.splitBaseNumber!=null?data.splitBaseNumber:0)+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></p>";
            str += "<div class=\"product-operation\">";//title="加入收藏夹"
            if (state==2){
                str += "<a id='hide"+data.wmsMiId+"' onmouseleave=\"hides()\" onmouseenter=\"seachCollect('"+data.wmsMiId+"')\" onclick=\"addCollect('"+data.wmsMiId+"','"+state+"')\" href=\"javascript:setFavorites('"+data.wmsMiId+"','liebiao-product-operation-fav');\"  style=\"margin-left:5px;\" class=\"\"><span id=\"liebiao-product-operation-fav-"+data.wmsMiId+"\" name=\""+data.wmsMiId+"\"  class=\"icon-favorite\"></span></a>";
                str += "<a onmouseover=\"seachCar()\" onclick=\"addCar('"+data.wmsMiId+"','"+state+"')\" href=\"javascript:setCar('"+data.wmsMiId+"','liebiao-product-operation-cart');\" title=\"加入购物车\" style=\"margin-left:5px;\"><span id=\"liebiao-product-operation-cart-"+data.wmsMiId+"\" name=\""+data.wmsMiId+"\" class=\"icon-shopping-cart add-product-cart\"></span></a>";
            }else {
                str += "<a id='hide"+data.wmsMiId+"' onmouseleave=\"hides()\" onmouseenter=\"seachCollect('"+data.wmsMiId+"')\" onclick=\"getNormMx('"+data.wmsMiId+"')\" href=\"javascript:setFavorites('"+data.wmsMiId+"','liebiao-product-operation-fav');\" style=\"margin-left:5px;\" class=\"\"><span id=\"liebiao-product-operation-fav-"+data.wmsMiId+"\" name=\""+data.wmsMiId+"\" class=\"icon-favorite\"></span></a>";//class="icon-favorite" //icon-favorite-solid
                str += "<a onmouseover=\"seachCar()\" onclick=\"getNormMx('"+data.wmsMiId+"')\" href=\"javascript:setCar('"+data.wmsMiId+"','liebiao-product-operation-cart');\" title=\"加入购物车\" style=\"margin-left:5px;\"><span id=\"liebiao-product-operation-cart-"+data.wmsMiId+"\" name=\""+data.wmsMiId+"\" class=\"icon-shopping-cart add-product-cart\"></span></a>";
            }
            // seachIcon(wmsMiId2);
            str +"<a href=\"javascript:;\"><span class=\"icon-search\">11</span></a>";
            str +"<a href=\"javascript:;\"><span class=\"icon-scanning\"></span></a>";
            str +="</div></li>";

            // $(str).fadeIn(200).appendTo($("#contentList"));
            // $("#contentList").append(str);
        }
    }
    if(str === ''){
        if(_all_searchLBName != 'this is an empty search name') {
            str = '<div style="float: left;margin: 5% auto;font-size: 14px"><div>抱歉！没有找到与<b style="margin: 0 5px 0 5px; font-size: 15px">' + _all_searchLBName + '</b>相关的宝贝</div>';
            _all_searchLBName = _all_searchLBName.replace(/\s/g, "");
            if (_all_searchLBName.length >= 2) {
                var lbName = _all_searchLBName.split('');
                str += '<div>别担心，我们根据部分搜索词帮您找到了一些结果：';

                let firstT = '';
                lbName.forEach(function (value) {
                    firstT += value + ' ';
                });
                if (firstT != '') {
                    firstT = firstT.substring(0, firstT.length - 1);
                    str += '<a class="search-button ahover" onClick="searchName2(\'' + firstT + '\')"><b>' + firstT + '</b></a>、';
                }
                lbName.forEach(function (value) {
                    str += '<a class="search-button ahover" onClick="searchName2(\'' + value + '\')"><b>' + value + '</b></a>、';
                })

                str = str.substring(0, str.length - 1);
                str += '</div></div>';
            }
        } else {
            str = '<div style="float: left;margin: 5% auto;font-size: 14px"><div>抱歉！没有找到相关的宝贝</div></div>';
        }
    }
    $("#contentList").html(str);
    if(needHighlight) {
        highLightSpan();
    }

    setTimeout(function () {
        for (let id of wmsMiIds)
            seachIcon(id);
    }, 200);
    // _intCart();

}

function highLightSpan() {
    var strArray1 = new Array();
    var strArray2 = new Array();
    strArray1 = _all_searchLBName.split(' ');
    strArray2 = _all_searchLBName.split('');
    strArray1.forEach(function (value) {
        strArray2.forEach(function (value1) {
            if (value1.trim() != value1.trim()) {
                strArray1.push(value1);
            }
        })
    })
    strArray1.reverse();
    $("#contentList").find('span[id^=searchNameMatch]').each(function () {
        var reStr = $(this).text();
        var that = this;
        strArray1.forEach(function (value) {
            if (value != '') {
                var reg = new RegExp(value, 'gi');
                reStr = reStr.replace(reg, '<span class="highLight">' + value + '</span>');
                $(that).html(reStr);
            }
        })
    });
}
function addCollect(wmsMiId,state) {
    if (state==1){
        $.supper("alert",{ title:"操作提示", msg:"请选择要收藏的规格"});
        return;
    }
    if ($("#liebiao-product-operation-fav-"+wmsMiId).attr('class')=='icon-favorite'){
        layer.msg('成功加入收藏夹！');
        $("#liebiao-product-operation-fav-"+wmsMiId).removeClass().addClass('icon-favorite-solid');
    }else {
        $("#liebiao-product-operation-fav-"+wmsMiId).removeClass().addClass('icon-favorite');
        layer.msg('成功移除收藏夹！');
    }

    var vdata="&wmsMiId="+wmsMiId;
    $.supper("doservice", {"service":"MdOutOrderService.saveCollect","data":vdata, ifloading: 1, "BackE":function (jsondata) {
            if (jsondata.obj.state == "1") {

                //searchAll();
            }else if (jsondata.obj.state=="0"){
                // $.supper("alert",{ title:"操作提示", msg:"成功移除收藏夹！"});
                //  layer.msg('成功移除收藏夹！');
                //searchAll();
            } else
                $.supper("alert",{ title:"操作提示", msg:jsondata.meg});
        }});
}

var carState=0;
//加入收藏夹点击选择规格
function addCars(wmsMiId,state) {
    if (state == 1) {
        //$('#getNormMx').click(wmsMiId);
        const BTN2 = document.getElementById('getNormMx');
        BTN2.onclick(wmsMiId);
        // $("#getNormMx").trigger("click",wmsMiId);
        //$.supper("alert",{ title:"操作提示", msg:"请选择要加入购物车的规格"});
        return;
    }
}
//加入物料清单
function addCar(wmsMiId,state) {
    if (state==1){
        //$('#getNormMx').click(wmsMiId);
        const BTN2 = document.getElementById('getNormMx');
        BTN2.onclick(wmsMiId);
        // $("#getNormMx").trigger("click",wmsMiId);
        //$.supper("alert",{ title:"操作提示", msg:"请选择要加入购物车的规格"});
        return;
    }
    var vdata="&wmsMiId="+wmsMiId+"&shus="+"1";
    $.supper("doservice", {"service":"MdOutOrderService.addCarts","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                countCollect1();
                $.supper("alert",{ title:"操作提示", msg:"添加到购物车成功！"});
            }else
                $.supper("alert",{ title:"操作提示", msg:jsondata.meg});
        }});
}



//选择规格
function getNormMx(wmsMiId) {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPickingMxFindId.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{wmsMiId: wmsMiId,url: att_url}});
    $.supper("showTtemWin",{ "url":att_url,"title":"新增领料申请详情"});
}
var count;
function hides() {
    layer.close();
}
//加入收藏夹
function seachIcon(wmsMiId2) {
    var vdata="&wmsMiId="+wmsMiId2;
    $.supper("doservice", {"service":"MdOutOrderService.seachCollect","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                count=jsondata.obj;
                if (count>0) {
                     $("#liebiao-product-operation-fav-"+wmsMiId2).removeClass().addClass('icon-favorite-solid');
                } else {
                     $("#liebiao-product-operation-fav-"+wmsMiId2).removeClass().addClass('icon-favorite');
               }
            }else
                $.supper("alert",{ title:"操作提示", msg:jsondata.meg});
        }});

}
function seachCollect(wmsMiId) {
    var vdata="&wmsMiId="+wmsMiId;
    $.supper("doservice", {"service":"MdOutOrderService.seachCollect","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                count=jsondata.obj;
                var tips;
                $(".product-operation a:nth-child(1)").bind("mouseover", function () {
                    // var message = '加入收藏夹';
                    var message='';
                    if (count<=0) {
                        message = '加入收藏夹';
                        $("#hide"+wmsMiId).attr("title","加入收藏夹");
                    }else if (count>0){
                         message = '移除收藏夹';
                        $("#hide"+wmsMiId).attr("title","移除收藏夹");
                    }
                });
            }else
                $.supper("alert",{ title:"操作提示", msg:jsondata.meg});
    }});

}
function seachCar() {
    var tips;
    $(".product-operation a:nth-child(2)").bind("mouseover", function () {
        var message = '加入物料清单';
        if (1==1) {
            message = '加入物料清单';
        }
    });
}

function countCollect1() {
    var vdata='';
    $.supper("doservice", {"service":"MdOutOrderService.countCollect1","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                $("#countCollects").html(jsondata.obj)
            }else{

            }
        }});
}

var AliasNameWmsMiId;
var aliasNamesNUmber=0;
var matName2;
function Editalias(wmsMiId) {
    console.log("111")
    var item = wmms[wmsMiId].item;
    matName2=item.matName;
    $("#Editalias1").show();
    var str = "<div><div style='float: left;'><img style='height: 70px;width: 70px' src=\""+item.lessenFilePath+"\" alt=\"\"></div>" +
        "<div style='float: left;margin-left: 50px;width: 200px;line-height: 30px;'><ul><li>"+item.matName+"</li><li>包装方式："+item.productName+"</li></ul></div>" +
        "<div style='float: left;margin-left: 50px;width: 200px;line-height: 30px;'><ul><li>单位："+item.basicUnit+"</li><li'>库存数量："+(item.quantity!=null?item.quantity:0)+(item.basicUnit!=null?item.basicUnit:'')+(item.splitBaseNumber!=null?item.splitBaseNumber:0)+(item.splitUnit !== undefined ? item.splitUnit : ''+item.basicUnit+'')+"</li></ul></div>" +
        "</div>";
    $("#EditList").html(str);
    AliasNameWmsMiId=item.wmsMiId;
    if (item.linkWmsMiId != undefined){
        aliasNames = item.aliasName2 == undefined ? '' : item.aliasName2
    } else if (item.aliasName3 != undefined){
        aliasNames = item.aliasName3 == undefined ? '' : item.aliasName3
    } else{
        aliasNames = item.aliasName == undefined ? '' : item.aliasName
    }
    // var aliasNames='';
    //     aliasNames = item.aliasName == undefined ? '' : item.aliasName;
    forList(aliasNames);
}
function forList(aliasNames) {
    /**
     * 2020年07月13日13:31:13 朱燕冰
     * 判断是否已有别名
     */
    if (aliasNames!=null&&aliasNames!=undefined&&aliasNames!=""){
        aliasNames= aliasNames.split(",");
        //已有别名
        aliasNamess=aliasNames;
        console.log("已有参考别名"+aliasNamess);
        var str2="";
        for (i=0;i<aliasNames.length ;i++ )
        {
            aliasNamesNUmber=aliasNames.length;
            str2+="<div style=\"margin-left: 0px\" class=\"btn\" ><a style='display:block;width: auto'id='deleteAli"+i+"' class=\"search-button\" onclick='deleteAliasNames(\""+aliasNames[i]+"\","+i+")'>"+aliasNames[i]+"<div style='float: right;margin-top: -7px'><div style='color: #0e9aef;'>删除</div></div></a></div>";
        }
        $("#bms").html(str2);
        $("#bmCount").hide();
    }else {
        //2020年07月03日09:31:25朱燕冰修改
        aliasNamesNUmber=0;
        var str3="<span style=\"margin-left: 43px\">参考别名:</span><span style=\"margin-left: 20px\">"+matName2+"1</span></span><span style=\"position: absolute;left: 137px;margin-top: 38px\">"+matName2+"2</span></span><span style=\"position: absolute;left: 137px;margin-top: 80px\">"+matName2+"3</span>";
        $("#bmCount").hide();
        $("#bms").show()
        $("#bms").html(str3);
    }

}

function hide2() {
    $("#Editalias1").hide();
}
function success() {
    $("#Editalias1").hide();
}

function deleteAliasNames(aliasName,i) {
    var vdata="&wmsMiId="+AliasNameWmsMiId+"&aliasName="+aliasName;
    $.supper("doservice", {
        "service": "MdOutOrderService.saveDeleteInventoryMaterielAliasName",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                var a1=jsondata.obj;
                forList(a1);
                searchAll();
                $.supper("alert",{ title:"操作提示", msg:"删除成功!"});
            }else{
                $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
            }
        }
    });
}

function addAliasName() {
    if (aliasNamesNUmber>=3) {
        $.supper("alert",{ title:"操作提示", msg:"最多可添加三个别名!"});
        return;
    }
   var AliasNameId=$("#AliasNameId").val()
    if($("#AliasNameId").val() == ""){
        $.supper("alert",{ title:"操作提示", msg:"请输入别名!"});
        return;
    }
    var vdata="&wmsMiId="+AliasNameWmsMiId+"&aliasName="+AliasNameId;
    $.supper("doservice", {
        "service": "MdOutOrderService.saveInventoryMaterielAliasName",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                var a1=jsondata.obj;
                forList(a1);
                searchAll();
                $("#AliasNameId").val('');
               console.log('111---------->',$("#AliasNameId").val())
                $.supper("alert",{ title:"操作提示", msg:"添加成功!"});

            }else{
                $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
            }
        }
    });
}