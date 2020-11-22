var mooId3;
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
                jump();
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
var  allClaomant;
$(function () {
    $("#allColor").addClass("active");
    $("#a6").addClass("active")
    $("#countAll").addClass("active")
    $("#b6").addClass("active")
    loadItemZtree();
    // laydate({
    //     elem: '#searchDdStartTime',
    //     format: 'YYYY-MM-DD' //日期格式
    // });

    layui.use('laydate', function () {
        var laydate = layui.laydate;
        laydate.render({
            elem: '#searchDdStartTime'
            , range: "~"
        });
    });

//增加回车搜索功能
    $("#searchDdName").on('keydown', function(){
        if (event.keyCode==13) {
            $("#searchDdBtn").trigger("click");
        }
    });

    //点击上一页下一页
    $('#prePageDD').on('click', function () {
        prePageDD();
    })
    $('#nextPageDD').on('click', function () {
        nextPageDD();
    })
    //2020年07月02日16:09:30 朱燕冰修改
    layui.use(['jquery', 'formSelects'], function () {
        var formSelects = layui.formSelects;
        formSelects.data('selectState1', 'local', {
            arr: [
                {"name": "全部", "value": 1},
                {"name": "申请中", "value": 2},
                {"name": "部分出库", "value": 3},
                {"name": "已完成", "value": 4},
                {"name": "撤销", "value": 5}
            ]
        });
        clearSelects = function () {
            formSelects.value('selectState1', [])
        }
        formSelects.btns('selectState1', []);
    });
    var selOutOrderType = $.supper("getProductArray", "selOutOrderInfo");
    if(selOutOrderType != null && selOutOrderType.flowState != null){
        allClaomant=selOutOrderType.flowState;
        $.supper("setProductArray", {"name":"selOutOrderInfo", "value":null});
        // if (allClaomant)
        // if (allClaomant!=null&&allClaomant!=undefined) {
        //     initData();
        // }else {
        //     initData();
        // }
    }

    if (allClaomant!=null&&allClaomant!=undefined){
        if (allClaomant == 1){
            $("#allI5 a").removeClass("active")
            $("#allI4 a").removeClass("active")
            $("#allIn3 a").removeClass("active")
            $("#allIn2 a").removeClass("active")
            $("#allI1 a").addClass("active")
        }
        if (allClaomant == 2){
            $("#allI5 a").removeClass("active")
            $("#allI4 a").removeClass("active")
            $("#allIn3 a").removeClass("active")
            $("#allIn2 a").addClass("active")
            $("#allI1 a").removeClass("active")
        }
        if (allClaomant == 3){
            $("#allI5 a").removeClass("active")
            $("#allI4 a").removeClass("active")
            $("#allIn3 a").addClass("active")
            $("#allIn2 a").removeClass("active")
            $("#allI1 a").removeClass("active")
        }
        if (allClaomant == 4){
            $("#allI5 a").removeClass("active")
            $("#allI4 a").addClass("active")
            $("#allIn3 a").removeClass("active")
            $("#allIn2 a").removeClass("active")
            $("#allI1 a").removeClass("active")
        }
        if(allClaomant == 5){
            $("#allI5 a").addClass("active")
            $("#allI4 a").removeClass("active")
            $("#allIn3 a").removeClass("active")
            $("#allIn2 a").removeClass("active")
            $("#allI1 a").removeClass("active")
        }
        clickAll(allClaomant);
    } else {
        initData();
    }
});

function closeSelect() {
    $('#selectDateDiv').hide();
}
function selectRangeDate() {    
    $('#selectDateDiv').show();
}

/*}*
 *
 */
function initData() {
    if ($('#all').prop('checked') == true){
    $('#all').prop('checked',false);
}
    var vdata=$("#formAll").serialize();
    var vdate1=vdata;
    stateCount(vdate1);
    $.supper('initPagination', {
        id: "Pagination1",
        service: "MdOutOrderService.getPicking",
        data: vdata,
        limit:10,
        isAjax: "1",
        "BackE": initPickingList
    });
    change(1)
}
$('#formAll').bind('keyup', function(event) {
    if (event.keyCode == "13") {
        //回车执行查询
        $('#searchDdBtn').click();
        initData();
    }
});
function clickAll(i) {
    var vdata='&selectState1='+i;
    var vdate1=vdata;
    stateCount(vdate1);
    $.supper('initPagination', {
        id: "Pagination1",
        service: "MdOutOrderService.getPicking",
        data: vdata,
        limit:10,
        isAjax: "1",
        "BackE": initPickingList
    });
}
//根据条件查询全部申请 部分出库等状态的数量
function stateCount(vdate1) {
        var vdata=vdate1;
    $.supper("doservice", {
        "service": "MdOutOrderService.getPickingStateCount",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            $("#countAll").html(jsondata.obj.countAll);
            $("#countApplying").html(jsondata.obj.countApplying);
            $("#countPartial").html(jsondata.obj.countPartial);
            $("#countComplete").html(jsondata.obj.countComplete);
            $("#countrevoke").html(jsondata.obj.countrevoke);
        }
    });
}

// if ($('#all').prop('checked') == true){
//     $('#all').prop('checked',false);
// }
var mooIds1="";
function initPickingList(jsondata) {
    mooIds1='';
    var mxList = jsondata;
    var str = "";
    let checked = '';
    if ($('#all').prop('checked') == true)
        checked = 'checked';
    if (mxList != null && mxList.length > 0) {
        var mooId = "";
        for (var i = 0; i < mxList.length; i++) {

            mooId = mxList[i].mooId;//bgcolor='#F0F0F0'
            mooIds1+=mooId+",";
            str += "<a onclick='show(" + mooId + ")'><div style='background-color: white;height: 10px'; id='div1'+'+mooId+'><tr>"
            str += "</tr></div>";
            str += "<div style='width: 100%;height: 25px;background-color:#F0F0F0;margin-top: 5px;line-height: 25px'><tr><td style=\"font-size: 13px\"><div style='float: left;width:3%;vertical-align:middle;display: flex;justify-content: center'><input class='checkbox' " + checked + " name='items' type=\"checkbox\" onclick='checkd(this," + mooId + ")'/></div></td>";
            str+= " <td style=\"font-size: 13px;line-height: 3;\"><div style='float: left;width:50%;text-align: left;'>申领单号："+mxList[i].mooCode+"</div></td>";
            str+= " <td style=\"font-size: 13px;line-height: 3;\"><div style='float: left;width:25%;text-align: center;'>申领时间："+mxList[i].orderTime+"</div></td>";
            if (mxList[i].flowState!=null&&mxList[i].flowState!=undefined) {
                if (mxList[i].flowState==2) {
                    str+= " <td style=\"text-align:center;line-height: 3;font-size: 13px\"><div style='width:10%;float: left;text-align: center;'>申请中</div></td>";
                }
                if (mxList[i].flowState==3) {
                    str+= " <td style=\"text-align:center;line-height: 3;font-size: 13px\"><div style='width: 10%;float: left;text-align: center;'>部分发货</div></td>";
                }
                if (mxList[i].flowState==4) {
                    str+= " <td style=\"text-align:center;line-height: 3;font-size: 13px\"><div style='width: 10%;float: left;text-align: center;'>已完成</div></td>";
                }
                if (mxList[i].flowState==5) {
                    str+= " <td style=\"text-align:center;line-height: 3;font-size: 13px\"><div style='width: 10%;float: left;text-align: center;'>撤销</div></td>";
                }
                if (mxList[i].flowState==7) {
                    str+= " <td style=\"text-align:center;line-height: 3;font-size: 13px\"><div style='width: 10%;float: left;text-align: center;'>售后</div></td>";
                }
            }else {
                str+= " <td style=\"text-align:center;line-height: 3;font-size: 13px\"><div style='width: 10%;float: left;text-align: center;'>&nbsp;</div></td>";
            }
            str+= " <td style=\"font-size: 13px;\"><div style='width: 6%;text-align: right;float: left;'><a style='color:#1E90FF;' onclick='exportPickId("+mooId+")'>导出excel&nbsp;&nbsp;</a></div></td>";
            if (i==0){
                str+= " <td style=\"font-size: 13px;\"><div style='width: 5%;text-align: left;float: left;'><a id='open"+mooId+"' onclick='show(" + mooId + ")' style='display: none;color:#1E90FF;'>&nbsp;&nbsp;展开</a><a id='away"+mooId+"' onclick='hideaway(" + mooId + ")' style='color:#1E90FF;'>&nbsp;&nbsp;收起</a></div></td>";
            }else {
                str+= " <td style=\"font-size: 13px;\"><div style='width: 5%;text-align: left;float: left;'><a id='open"+mooId+"' onclick='show(" + mooId + ")' style='color:#1E90FF;'>&nbsp;&nbsp;展开</a><a id='away"+mooId+"' onclick='hideaway(" + mooId + ")' style='color:#1E90FF;display: none;'>&nbsp;&nbsp;收起</a></div></td>";
            }
            str += "</tr></div>";
            str += "<div style='background-color: white;height: auto'><tr><div style='position:relative'><div id='hidden1"+mooId+"' style='position: fixed;top: 300px;left: 50%;margin-left:-250px;width: 500px;height: 270px;background-color:white;border:1px solid #1ab394;display:none;z-index: 999'><div style='background-color: #1ab394;width: 100%;height: 40px'><div style='line-height:40px'><span style='margin-left: 30px;color: #fff;'>申领单日志</span><a onclick='hideLog("+mooId+")'><span style='margin-left: 330px;color: #fff;'>关闭</span></a></div><div id='hidden2"+mooId+"'><table class=\"table invoice-table1\" style=\"border: 0.5px solid #F5F5F5;background-color: white\">\n" +
                "                <thead>" +
                "                <tr style=\"border: 0.5px solid #F5F5F5\">\n" +
                "                    <th style=\"text-align:center;font-size: 13px;border: 0.5px solid #DCDCDC;width: 30%;height: 20px;\">时间</th>\n" +
                "                    <th style=\"text-align:center;font-size: 13px;border: 0.5px solid #DCDCDC;width: 15%;height: 20px;\">操作人员</th>\n" +
                "                    <th style=\"text-align:center;font-size: 13px;border: 0.5px solid #DCDCDC;width: 18%;height: 20px;\">操作</th>\n" +
                "                    <th style=\"text-align:center;font-size: 13px;border: 0.5px solid #DCDCDC;width: 37%;height: 20px;\">操作详情</th>\n" +
                "                </tr>\n" +
                "                </thead>\n" +
                "                <tbody style=\"border: 0.5px solid #DCDCDC;\" id='hidden12"+mooId+"'>" +
                "                </tbody>" +
                "            </table>" +
                "</div><div class=\"pagination\" id=\"Pagination5\" style=\"text-align: center;\"></div></div>" +
                "</div></div><div id='hidden"+mooId+"'></div>"
            str += "</tr></div></a>";
            if (i==0 && mooId != undefined){
                show(mooId);
            }
        }
    }
    $("#mxList").html(str);
};
var mooId1;
// var mooIdS="";
function tdClick(mooId) {
    $("#hidden"+mooId).show();
    $("#open"+mooId).hide();
    $("#away"+mooId).show();
    if (mooId1!=null&&mooId1!=undefined){
        $("#hidden"+mooId1).hide();
        // $("#away"+mooId1).show();
        // // $("#open"+mooId).show();
        // // $("#away"+mooId).show();
    }
    mooId1=mooId;
    initDataMx(mooId);
}
function show(mooId) {
    // $("#hidden"+mooId).show();
    // $("#open"+mooId).hide();
    // $("#away"+mooId).show();
    if (mooId1!=null&&mooId1!=undefined){
        // console.log(mooId+"===="+mooId1)
        if (mooId==mooId1) {
                // $("#hidden"+mooId).show();
                // $("#open"+mooId).hide();
                // $("#away"+mooId).show();
            if($("#hidden"+mooId).is(":hidden")){
                $("#hidden"+mooId).show();//如果元素为隐藏,则将它显现
                $("#open"+mooId).hide();
                $("#away"+mooId).show();
            }else{
                $("#hidden"+mooId).hide();     //如果元素为显现,则将其隐藏
                $("#open"+mooId1).show();
                $("#away"+mooId1).hide();
            }
        }else {
            $("#hidden"+mooId1).hide();
            $("#open"+mooId1).show();
            $("#away"+mooId1).hide();
            $("#hidden"+mooId).show();
            $("#open"+mooId).hide();
            $("#away"+mooId).show();
        }
    }
    // if($("#hidden"+mooId).is(":hidden")){
    //     $("#hidden"+mooId).show();//如果元素为隐藏,则将它显现
    //     $("#open"+mooId).hide();
    //     $("#away"+mooId).show();
    // }else{
    //     $("#hidden"+mooId).hide();     //如果元素为显现,则将其隐藏
    //     $("#open"+mooId1).show();
    //     $("#away"+mooId1).hide();
    // }
    mooId1=mooId;
    initDataMx(mooId);
}
function hideaway(mooId) {
    if (mooId1!=null&&mooId1!=undefined){
        $("#away"+mooId).show();
        $("#open"+mooId).hide();
    }
    $("#hidden"+mooId).hide();
    $("#open"+mooId).show();
    $("#away"+mooId).hide();
    $("#hidden1"+mooIdLog1).hide();
    mooId1=mooId;
}
function initDataMx(mooId) {
    var vdata ='mooId='+mooId;
    $.supper('initPagination', {
        id: "Pagination2",
        service: "MdOutOrderService.getPickingMx",
        data: vdata,
        // limit:10,
        isAjax: "1",
        "BackE": initListMx
    });
}
var  firstCount=0;
function initListMx(jsondata) {
    var mxList = jsondata;
    var str = "";
    var mooId1;
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            if (i>0&&i<10){
                firstCount=1;
            }
            // if (i<mxList.length-1&&i>mxList.length-((mxList.length%10)+1)) {
            // }
            // alert((mxList.length%10)+1);
            // alert(mxList.length%10);
            var data = mxList[i];
            mooId1=data.mooId;

            // alert(data.rootPath);
            str += "<div id=''><div style='background-color: rgba(192, 192, 192, 0);border: 1px solid #F5F5F5;' class=\"order-info newlistH\"><div class=\"left newlstW\" style='padding-left: 10px;float: left'><div style='margin-top: 33px;float: left;'><a onclick='getNormMx("+data.wmsMiId+")'><img src=\""+data.rootPath+"\" width=\"68\" height=\"68\"></a></div>";
            str += "<ul style='float: left;margin-left: 14px;'><li class=\"name\" style='text-align: left;margin-top: 13px'><a onclick='getNormMx("+data.wmsMiId+")' style='color:#1E90FF '>"+data.matName+"</a>";
            str += "<li class=\"type\" style='text-align: left;margin-top: 10px'>品牌：<strong><a target=\"_blank\">"+(data.brand !== undefined ? data.brand : '')+"</a></strong></li>";
            str += "<li class=\"type\" style='text-align: left;margin-top: 10px'>规格型号：<strong><a target=\"_blank\">"+data.norm+"</a></strong></li>";
            str +="<li class=\"type\" style='text-align: left;margin-top: 10px'>物料编号："+(data.matCode !== undefined ? data.matCode : '')+"</li>";
            str += '<li class=\"name\" style=\'text-align: left;margin-top: 10px\'>包装方式：'+(data.productName !== undefined ? data.productName : '')+'</li>';
            str += "</ul></div>";
            //根据最小单位判断是否显示最小单位
            if (data.splitQuantity!=undefined&&data.splitQuantity!=null) {
                str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='' class=\"money\">"+data.baseNumber+(data.basicUnit !== undefined ? data.basicUnit : '')+"<span class=\"money\" >"+(data.splitQuantity!== undefined ?data.splitQuantity:'0')+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></div></div>";
            }else {
                str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='' class=\"money\">"+data.baseNumber+(data.basicUnit !== undefined ? data.basicUnit : '')+"</div></div>";
            }
            //根据最小单位判断是否显示最小单位
            if (data.splitQuantity!=undefined&&data.splitQuantity!=null) {
                str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='' class=\"money\">"+(data.number1 !== undefined ? data.number1 : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+"<span class=\"money\" >"+(data.splitNumber1!== undefined ?data.splitNumber1:'0')+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></div></div>";//splitNumber1
            }else {
                str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='' class=\"money\">"+(data.number1 !== undefined ? data.number1 : '0')+(data.basicUnit !== undefined ? data.basicUnit : '')+"</div></div>";//splitNumber1
            }
            if ((data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))>0 || (data.splitQuantity-(data.splitNumber1 !== undefined ? data.splitNumber1 : '0'))>0) {
                //根据最小单位判断是否显示最小单位
                if (data.splitQuantity!=undefined&&data.splitQuantity!=null) {
                    str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='color: red' class=\"money\">"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+(data.basicUnit !== undefined ? data.basicUnit : '')+"<span class=\"money\" >"+(((data.splitQuantity !== undefined ? data.splitQuantity : 0)-(data.splitNumber1 !== undefined ? data.splitNumber1 : 0)))+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></div></div>";
                }else {
                    str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='color: red' class=\"money\">"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+(data.basicUnit !== undefined ? data.basicUnit : '')+"</div></div>";
                }
                //备用 上次改需求    str += "<div class=\"right\" style='float: left;margin-top: 55px;margin-left:85px'><div style='text-align: center;width:90px;height: 30px;color: red' class=\"money\">"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+(data.basicUnit !== undefined ? data.basicUnit : '')+"<span class=\"money\" >"+(((data.splitQuantity !== undefined ? data.splitQuantity : '0')-(data.splitNumber1 !== undefined ? data.splitNumber1 : '0')))+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></div></div>";
            }else {
                //根据最小单位判断是否显示最小单位
                if (data.splitQuantity!=undefined&&data.splitQuantity!=null) {
                    str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='text-align: center;' class=\"money\">"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+(data.basicUnit !== undefined ? data.basicUnit : '')+"<span class=\"money\" >"+(((data.splitQuantity !== undefined ? data.splitQuantity : 0)-(data.splitNumber1 !== undefined ? data.splitNumber1 : 0)))+(data.splitUnit !== undefined ? data.splitUnit : ''+data.basicUnit+'')+"</span></div></div>";
                }else {
                    str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='text-align: center;' class=\"money\">"+(data.baseNumber-(data.number1 !== undefined ? data.number1 : '0'))+(data.basicUnit !== undefined ? data.basicUnit : '')+"</div></div>";
                }
            }
            // str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='text-align: center;width:200px;height: 30px;' class=\"money\">"+(data.orderTime!==undefined ? data.orderTime:'')+"</div></div>";
            str += "<div class=\"right newlstW3\" style='float: left;height:100%;display: flex;justify-content: center;align-items: center;'><div style='text-align: center;width:200px;height: 30px;' class=\"money\"></div></div>";
            // str += "<div class=\"right\" style='float: left;border-right: 1px solid #F5F5F5;'><div style='text-align: center;width:20px;height: 30px;' class=\"money\">"+(data.orderTime!==undefined ? data.orderTime:'')+"</div>";
            // alert(Math.ceil(mxList.length/3));
            if (i==0){

                if (data.flowState=="2"||data.flowState=="3") {
                    str += "<a onclick='editPicking("+mooId1+")'><div class=\"right\" style='float: left;margin-top: 50px;margin-left: -30px;'><div style='text-align: center;width:25px;height: 30px;' class=\"money\"><img style=\" width: 16px;height: 15px\" src=\"../../../../dentistmall/css/shopping/images/reapply.png\"/><div style='font-size: 12px;margin-top: 3px;'>修改</div></div></div></a>";
                }
                str += "<a onclick='viewDetails("+mooId1+")'><div class=\"right\" style='float: left;margin-top: 50px;'><div style='text-align: center;width:60px;height: 30px;' class=\"money\"><img style='width: 16px;height: 15px'  src=\"../../../../dentistmall/css/shopping/images/viewDetails.png\"/><div style='font-size: 12px;margin-top: 3px;'>查看详情</div><div class=\"tipbox1\"></div></div></div></a>";
                if (data.flowState=="2"||data.flowState=="3"){
                    str += "<a onclick='cancelAll("+mooId1+")'><div class=\"right\" style='float: left;margin-top: 50px;'><div style='text-align: center;width:60px;height: 30px;' class=\"money\"><img style=\" width: 16px;height: 15px\" src=\"../../../../dentistmall/css/shopping/images/cancelAll.png\"/><div style='font-size: 12px;margin-top: 3px;'>全部撤销</div></div></div></a>";
                }
                str += "<a onclick='reapply("+mooId1+")'><div class=\"right\" style='float: left;margin-top: 50px;'><div style='text-align: center;width:60px;height: 30px;' class=\"money\"><img style=\" width: 16px;height: 15px\" src=\"../../../../dentistmall/css/shopping/images/viewDetails.png\"/><div style='font-size: 12px;margin-top: 3px;'>再次申请</div><div class=\"tipbox1\"></div></div></div></a>";
                str += "<a onclick='cklog("+mooId1+")'><div class=\"right\" style='float: left;margin-top: 50px;'><div style='text-align: center;width:60px;height: 30px;' class=\"money\"><img style=\" width: 16px;height: 15px\" src=\"../../../../dentistmall/css/shopping/images/cancelAll.png\"/><div style='font-size: 12px;margin-top: 3px;'>查看日志</div></div></div></a>";
                if (data.remarks!==undefined&&data.remarks!=null&&data.remarks!=""){
                    str += "<a><div class=\"right\" style='float: left;margin-top: 50px;'><div style='text-align: center;width:60px;height: 30px;' class=\"money\"><img onmouseover='onmouseMooId("+mooId1+")' onmouseout='onmouseoutMooId("+mooId1+")' style='width: 16px;height: 15px'  src=\"../../../../dentistmall/css/shopping/images/viewDetails.png\"/><div style='font-size: 12px;margin-top: 3px;' onmouseover='onmouseMooId("+mooId1+")' onmouseout='onmouseoutMooId("+mooId1+")' >申领说明</div><div class=\"tipbox1\"><div id='xqHide"+mooId1+"' class=\"tipbox\">"+data.remarks+"</div></div></div></div></a>";
                }else {
                    str += "<a><div class=\"right\" style='float: left;margin-top: 50px;'><div style='text-align: center;width:60px;height: 30px;' class=\"money\"><img onmouseover='onmouseMooId("+mooId1+")' onmouseout='onmouseoutMooId("+mooId1+")' style='width: 16px;height: 15px'  src=\"../../../../dentistmall/css/shopping/images/viewDetails.png\"/><div style='font-size: 12px;margin-top: 3px;' onmouseover='onmouseMooId("+mooId1+")' onmouseout='onmouseoutMooId("+mooId1+")' >申领说明</div><div class=\"tipbox1\"><div id='xqHide"+mooId1+"' class=\"tipbox\">暂无说明</div></div></div></div></a>";
                }
                if (data.wowRemarks!=undefined&&data.wowRemarks!=null&&data.wowRemarks!=""){
                    str += "<a><div class=\"right\" style='float: left;margin-top: 50px;'><div style='text-align: center;width:60px;height: 30px;' class=\"money\"><img onmouseover='onmouseMooId1("+mooId1+")' onmouseout='onmouseoutMooId1("+mooId1+")' style=\" width: 16px;height: 15px\" src=\"../../../../dentistmall/css/shopping/images/viewDetails.png\"/><div style='font-size: 12px;margin-top: 3px;' onmouseover='onmouseMooId1("+mooId1+")' onmouseout='onmouseoutMooId1("+mooId1+")'>出库摘要</div><div class=\"tipbox1\"><div id='xqHide1"+mooId1+"' class=\"tipbox\">出库摘要："+(data.wowRemarks !== undefined ? data.wowRemarks : '')+"</div></div></div></div></a>";
                }else {
                    str += "<a><div class=\"right\" style='float: left;margin-top: 50px;'><div style='text-align: center;width:60px;height: 30px;' class=\"money\"><img onmouseover='onmouseMooId1("+mooId1+")' onmouseout='onmouseoutMooId1("+mooId1+")' style=\" width: 16px;height: 15px\" src=\"../../../../dentistmall/css/shopping/images/viewDetails.png\"/><div style='font-size: 12px;margin-top: 3px;' onmouseover='onmouseMooId1("+mooId1+")' onmouseout='onmouseoutMooId1("+mooId1+")'>出库摘要</div><div class=\"tipbox1\"><div id='xqHide1"+mooId1+"' class=\"tipbox\">暂无出库摘要&nbsp;&nbsp;"+(data.wowRemarks !== undefined ? data.wowRemarks : '')+"</div></div></div></div></a>";
                }


            }else{
                str += "<div class=\"right\" style='float: left;'><div style='text-align: center;width:20px;height: 30px;' class=\"money\">&nbsp;</div></div>";
            }
            str +="</div></div>";
        }
    }
    $("#hidden"+mooId1).html(str);
};
function viewDetails(mooId1) {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/pickingMx.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{mooId1:mooId1,url: att_url}});
    $.supper("showTtemWin",{ "url":att_url,"title":"领料申请查看"});
}
function editPicking(mooId1) {
    var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/pickingEdit.jsp"});
    $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{mooId1:mooId1, url: att_url }});
    $.supper("showTtemWin",{ "url":att_url,"title":"修改领料申请"});
}

function cancelAll(mooId1) {
    var vdata="mooId1="+mooId1;
    $.supper("confirm",{ title:"全部撤销", msg:"确定要全部撤销吗？" ,yesE:function(){
            $.supper("doservice", {
                "service": "MdOutOrderService.saveCancelAll",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
                    if (jsondata.code == "1") {
                        initData();
                        $.supper("alert",{ title:"操作提示", msg:"撤销成功!"});
                    } else{
                        $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
                    }
                }
            });
        }});
}


//
// function initDataMx1(mooId) {
//     var vdata ='mooId='+mooId;
//     $.supper('initPagination', {
//         id: "Pagination3",
//         service: "MdOutOrderService.getPickingMx",
//         data: vdata,
//         // limit:10,
//         isAjax: "1",
//         "BackE": initListMx1
//     });
// }
// function initListMx1(jsondata) {
//     var mxList = jsondata;
//     var str = "";
//     var mooId1;
//     var shu="";
//     var wmsId1="";
//     var mmfId1="";
//     if (mxList != null && mxList.length > 0) {
//         for (var i = 0; i < mxList.length; i++) {
//             var data = mxList[i];
//             mooId1=data.mooId;
//             wmsId1+=data.wmsMiId+",";
//             shu+=data.baseNumber+",";
//             mmfId1+=data.mmfId+",";
//         }
//     }
//     shus=shu;
//     alert(shus);
// };
// function shus() {
//     var shus="";
//     var wmsId1s="";
//     var mmfId1s="";
// }
function reapply(mooId1) {
    var vdata="mooId1="+mooId1;
    $.supper("doservice", {
        "service": "MdOutOrderService.saveReapply",data:vdata, isAjax:"1",  "BackE": function (jsondata) {
            if (jsondata.code == "1") {
                countCollect1();
                $.supper("alert",{ title:"操作提示", msg:"已加入购物车,请进入购物车查看!"});

            } else{
                $.supper("alert",{ title:"操作提示", msg:"操作失败!"});
            }
        }
    });
}
var mooIdLog1;
var mooIdLog2;
function cklog(mooId1) {
    if (mooId1!=mooIdLog1) {
        mooIdLoglist(mooId1);
        mooIdLog2=mooId1;
        $("#hidden1"+mooId1).show();
        $("#hidden1"+mooIdLog1).hide();
    }else{
        mooIdLoglist(mooId1);
        $("#hidden1"+mooId1).show();
        $("#hidden1"+mooIdLog1).show();
    }
    mooIdLog1=mooId1;
}
// hideLog
function hideLog(mooId1) {
    $("#hidden1"+mooId1).hide();
}
function mooIdLoglist(mooId1) {
    var vdata="mooId1="+mooId1;
    $.supper('initPagination', {
        id: "Pagination5",
        service: "MdOutOrderService.getMdoutLog",
        data: vdata,
        limit:4,
        isAjax: "1",
        "BackE": mooIdLogInitList
    });
}

function mooIdLogInitList(jsondata) {
    var mxList = jsondata;
    var LogmooId;
    var str = "";
    if (mxList != null && mxList.length > 0) {
        for (var i = 0; i < mxList.length; i++) {
            var data = mxList[i];
            LogmooId=data.mooId;
            str +="<tr>"
            str += "<td style=\"text-align:center;font-size: 13px;border: 0.5px solid #F5F5F5;width: 15%;height: 20px;\">" + data.createDate + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 0.5px solid #F5F5F5;width: 15%;height: 20px;\">" + data.createRen + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 0.5px solid #F5F5F5;width: 15%;height: 20px;\">" + data.operationState + "</td>";
            str += "<td style=\"text-align:center;font-size: 13px;border: 0.5px solid #F5F5F5;width: 15%;height: 20px;\">" + data.createLog + "</td>";
            str += "</tr>";
        }
    }
    $("#hidden12"+LogmooId).html(str);
    // $("#hidden"+mooId1).show();
    // alert($("#hidden"+mooId1).val(str));
};
//搜索边上的上一页下一页
// function prePageDD() {
//     var pre = document.querySelector('#prev');
//     var next = document.querySelector('#next');
//     var aitem1 = document.getElementById("prePageDD");
//     // alert($("#prev").val());
//     // var a1=$("#prev").val();
//     // if (a1==undefined){
//     //     alert(1);
//     // }
//     // if (a1=="") {
//     //     alert(2);
//     // }
//
//     var aitem2 = document.getElementById("nextPageDD");
//     // console.log('pre-----',pre)
//     // console.log('next-----',next)
//     pre.click();
//         aitem1.style.background = "#1ab394";
//         aitem1.style.border = "1px solid #1ab394";
//         aitem2.style.background = "#DCDCDC";
//         aitem2.style.border = "1px solid #DCDCDC";
//     // if (pre !== undefined) {
//     //     pre.click();
//     //     aitem1.style.background = "#1ab394";
//     //     aitem1.style.border = "1px solid #1ab394";
//     // }else{
//     //     aitem1.style.background = "#DCDCDC";
//     //     aitem1.style.border = "1px solid #DCDCDC";
//     // }
//     // if (next !== undefined) {
//     //     aitem2.style.background = "#1ab394";
//     //     aitem2.style.border = "1px solid #1ab394";
//     // }else{
//     //     aitem2.style.background = "#DCDCDC";
//     //     aitem2.style.border = "1px solid #DCDCDC";
//     // }
// }
//搜索边上的上一页下一页
function prePageDD() {
    var pre = document.querySelector('#prev');
    var aitem1 = document.getElementById("prePageDD");
    // var a1=$("#prev").val();
    // if (a1==undefined) {
    //     var aitem1 = document.getElementById("prePageDD");
    //     // aitem1.style.background = "#1ab394";
    //     // aitem1.style.border = "1px solid #1ab394";
    //     aitem1.style.background = "#DCDCDC";
    //     aitem1.style.border = "1px solid #DCDCDC";
    // }else {
    //     aitem1.style.background = "#1ab394";
    //     aitem1.style.border = "1px solid #1ab394";
    // }
    if (pre !== null) {
        pre.click();
    }
}

function nextPageDD() {
    var next = document.querySelector('#next');
    if (next !== null) {
        next.click();
    }
}

// function nextPageDD() {
//     console.log('点击下一页')
//     var pre = document.querySelector('#prev');
//     var next = document.querySelector('#next');
//     var aitem1 = document.getElementById("prePageDD");
//     var aitem2 = document.getElementById("nextPageDD");
//     console.log('pre-----',pre)
//     console.log('next-----',next)
//     next.click();
//     // var a1=$("#next").val();
//     // if (a1==undefined){
//     //     alert(1);
//     // }
//     // if (a1=="") {
//     //     alert(2);
//     // }
//     // if (next !== undefined) {
//     //
//     //     aitem2.style.background = "#1ab394";
//     //     aitem2.style.border = "1px solid #1ab394";
//     // }else{
//     //     aitem2.style.background = "#DCDCDC";
//     //     aitem2.style.border = "1px solid #DCDCDC";
//     // }
//     // if (pre !== undefined) {
//     //     aitem1.style.background = "#1ab394";
//     //     aitem1.style.border = "1px solid #1ab394";
//     // }else{
//     //     aitem1.style.background = "#DCDCDC";
//     //     aitem1.style.border = "1px solid #DCDCDC";
//     // }
//
//     // aitem2.style.background = "#1ab394";
//     // aitem2.style.border = "1px solid #1ab394";
//     //
//     // aitem1.style.background = "#DCDCDC";
//     // aitem1.style.border = "1px solid #DCDCDC";
// }

//重置
function resetSearch() {
    $('#searchDdName').val('');
    $('#searchDdStartTime').val('');
    //$('#selectState1').val('')
    layui.formSelects.value('selectState1', []);
    // $('#searchDDPayType').val('');
    // _searchName = '';
    // $('#searchDdName').val('');
    initData();
}
// var moiIds = '';
// function exportItem(selector,moiId){
//     if(selector.checked === true){
//         moiIds += moiId+",";
//     }else {
//         moiIds = moiIds.replace(moiId+",", '');
//     }
// }
//
$("#all").click(function() {
    $("[name=items]:checkbox").prop("checked", this.checked);
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

var moiIds ='';
// var moiId1=mooIds1;
function exportItem(selector,moiId){
    if(selector.checked === true){
        moiIds = mooIds1;
        // moiId1 = mooIds1;
    }else {
        // moiIds = moiIds.replace(moiId+",", '');
        moiIds='';
        // moiId1=mooIds1;
    }
}

function checkd(selector1,moiId){
    if (moiIds.indexOf(moiId) >= 0) {
        moiIds = moiIds.replace(moiId + ',', '');
    } else {
        moiIds += moiId + ',';
    }
}
//批量导出物料清单
function exportPick(){
    // alert(moiIds);
    // return;
    var vdata='';
    if (moiIds!=null&&moiIds!=undefined&&moiIds!="") {
        vdata="moiIds="+moiIds;
    }else {
        vdata="moiIds="+mooIds1;
        $.supper("confirm", {
            title: "导出操作", msg: "您暂时未选择要导出的数据，系统将所有数据导出,是否确定导出？", yesE: function () {
                var newTab=window.open('about:blank');
                $.supper("doservice", {"service":"ExportExcelService.exportPick","data":vdata, "BackE":function (jsondata) {
                        if (jsondata.code == "1") {
                            newTab.location.href=jsondata.obj.path;
                        }else
                            $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
                    }});
            }});
        return;

    }
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"ExportExcelService.exportPick","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}
//单个导出excel
function exportPickId(mooId){
    mooId2s=mooId+",";
    var  vdata="moiIds="+mooId2s;
    var newTab=window.open('about:blank');
    $.supper("doservice", {"service":"ExportExcelService.exportPick","data":vdata, "BackE":function (jsondata) {
            if (jsondata.code == "1") {
                newTab.location.href=jsondata.obj.path;
            }else
                $.supper("alert",{ title:"操作提示", msg:"操作失败！"});
        }});
}


function getNormMx(wmsMiId) {
    if (wmsMiId!=null&&wmsMiId!=undefined){
        var att_url= $.supper("getServicePath", {"service":"",url:"/jsp/dentistmall/ApplicationManagement/addPickingMxFindId.jsp"});
        $.supper("setProductArray", {"name":"selOutOrderInfo", "value":{wmsMiId: wmsMiId,url: att_url}});
        $.supper("showTtemWin",{ "url":att_url,"title":"新增领料申请详情"});
    }else {
        $.supper("alert",{ title:"操作提示", msg:"物料编号不存在,请重新申请"});
        return;
    }

}

/**
 * 增加复选框全选/取消
 */
// $("#all").click(function() {
//     $("[name=items]:checkbox").prop("checked", this.checked);
// });
//
// $("[name=items]:checkbox").click(function() {
//     var flag = true;
//     $("this").each(function() {
//         if (!this.checked) {
//             flag = false;
//         }
//     });
//     $("#all").prop("checked", flag);
// });

function  onmouseMooId(mooId1){
    $("#xqHide"+mooId1).show();
}

function onmouseoutMooId(mooId1) {
    $("#xqHide"+mooId1).hide();
}

function  onmouseMooId1(mooId1){
    $("#xqHide1"+mooId1).show();
}

function onmouseoutMooId1(mooId1) {
    $("#xqHide1"+mooId1).hide();
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
function setBodyWidth(){
    var barWidthHelper=document.createElement('div');
    barWidthHelper.style.cssText="overflow:scroll; width:100px; height:100px;";
    document.body.appendChild(barWidthHelper);
    var barWidth=barWidthHelper.offsetWidth-barWidthHelper.clientWidth;
    document.body.removeChild(barWidthHelper);
    var bodyWidth=window.screen.availWidth-barWidth;
    return bodyWidth;
}

$(document).ready(
    function(){
        var bodyWidth=setBodyWidth()+"px";
        //document.body.style.width=bodyWidth;
        $("body").css("width",bodyWidth);
    }
);
