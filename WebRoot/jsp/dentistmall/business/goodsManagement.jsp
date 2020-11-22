<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>商家商品管理</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/css/fileManagement/fileManagement.css?v=10' />">
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <script type="text/javascript">
        //需要先配置layui，并使用use方法，这是单独将formSelects模块加入
        layui.config({
            base: '../../../js/' //此处路径请自行处理
        }).extend({
            formSelects: 'formSelects-v4'
        });
    </script>
</head>
<body class="gray-bg">
<%-- 头部 s --%>
<div class="toptit d_flex_between">
    <div class="tit"><i class="layui-icon layui-icon-template-1"></i> 商家商品管理</div>
    <div class="white_btn" onclick="main_search()"><i class="layui-icon layui-icon-refresh"></i>&nbsp;刷新</div>
</div>
<div class="countbox">
    <div id="countt1" class="countboxitemon" onclick="tabSearch(1)">全部商品(<span id="allCountt"></span>)</div>
    <div id="countt2" class="countboxitem" onclick="tabSearch(2)">已上架(<span id="shelvesCountt"></span>)</div>
    <div id="countt3" class="countboxitem" onclick="tabSearch(3)">未上架(<span id="offShelfCountt"></span>)</div>
</div>
<%-- 头部 e --%>
<!-- 查询 s -->
<div class="public_box">
    <div class="ftbox d_flex_between">
        <div class="form-inline">
            <label>输入搜索：</label>
            <input type="text" id="searchName" class="form-control" placeholder="商品名称/商品货号"
                   name="searchName" value="" onkeyup="entersearch()"/>
            <label style="margin-left: 20px">商品状态：</label>
            <select id="searchState" class="form-control" name="searchState" onchange="selectState(this)">
                <option value="">全部</option>
                <option value="1">上架</option>
                <option value="2">下架</option>
                <option value="3">违规下架</option>
            </select>
            <label style="margin-left: 20px">商品品牌：</label>
            <div class="ssbrand" onclick="open_Brand_Box()"><span id="brand"></span><img
                    src="/dentistmall/img/xiaicon.jpg"></div>
            <label style="margin-left: 20px">商家名称：</label>
            <div class="ssbrand" onclick="open_ApplicantName_Box()"><span id="applicantName"></span><img
                    src="/dentistmall/img/xiaicon.jpg"></div>
        </div>
        <div class="green_btn" onkeyup="entersearch()" onclick="main_search()"><i
                class="layui-icon layui-icon-search"></i>&nbsp;查询结果
        </div>

    </div>
</div>
<script>
    function entersearch() {
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13) {
            main_search();
        }
    }
</script>
<!-- 查询 e -->

<div class="public_box2">
    <!-- 主信息区域begin -->
    <div class="lwrapper wrapper-content "
         style="display: flex;justify-content: space-between;align-content: flex-start">
        <!-- 左边区域begin -->
        <div style="width: 17%;">
            <div class="ibox float-e-margins">
                <!-- 标题栏区域 begin-->
                <div class="ibox-title">
                    <!-- 标题文字区域begin -->
                    <h5>
                        <i class="fa fa-th"></i>&nbsp&nbsp商品分类
                    </h5>
                    <!-- 标题文字区域end -->
                </div>
                <!-- 标题栏区域 end-->
                <!-- 操作区域 begin-->
                <div class="ibox-content">
                    <div class="scrollbarlist">
                        <div class="panel-body">
                            <ul id="tree" class="ztree" style="overflow:auto;"></ul>
                        </div>
                    </div>
                </div>
                <!-- 操作区域 end-->
            </div>
        </div>
        <!-- 左边区域end -->
        <!-- 右边区域begin  -->
        <div style="width: 82%;">
            <div class="row">
                <div class="col-sm-12">
                    <!-- 标题栏区域 begin-->
                    <div class="ibox-title">
                        <!-- 标题文字区域begin -->
                        <h5>
                            <i class="fa fa-flask"></i>&nbsp&nbsp<span>商品管理</span>
                        </h5>
                        <!-- 标题文字区域end -->
                        <div class="ibox-tools" id="win_tools_but">
                        </div>
                    </div>
                    <!-- 标题栏区域 end-->
                    <!-- 表格区域begin -->
                    <div class="ibox-content" style="padding: 5px 10px 10px;">
                        <table id="win_datagrid_main" class="mmg"></table>
                        <div id="_all_win_datagrid_pg" style="text-align: right;"></div>
                        <div class="spglftbox">
                            <div class="btna"><input  id="allSelectFile2" class="selectItem" onclick="selecAll()" type="checkbox"><span onclick="selecAll()">全选</span></div>
                            <div class="btnb" onclick="open_Off_Shelf_Goods_Box2()">批量下架</div>
                            <div class="btnb" onclick="export_all()">导出EXCEL</div>
                        </div>
                    </div>
                    <!-- 表格区域end -->
                </div>
            </div>

        </div>
        <!-- 右边区域end -->
    </div>
    <!-- 主信息区域end -->
</div>

<!-- 下架商品 s -->
<div id="off_Shelf_Goods" class="addfileform">
    <div class="descline2">
        <label><span style="color: red">*</span>违规原因：</label><textarea name="off_reason" id="off_reason" placeholder="不能为空，2-30个字内"></textarea>
    </div>
    <div class="btnline2">
        <div class="btn" onclick="Off_Goods()">提交</div>
    </div>
</div>
<!-- 下架商品 e -->

<!-- 商品品牌弹窗 s -->
<div id="xia_Brand_Box" class="brandBox">
    <div id="brandLetter" class="letterbox"></div>
    <div class="inputbox">
        <input type="text" id="brandSearchName" class="form-control" placeholder="按品牌名称或关键词搜索"
               name="brandSearchName" value="" onkeyup="entersearch_brand()"/>
        <div class="ssicon" onclick="searchBrand()"><i class="layui-icon layui-icon-search"></i></div>
        <div class="text1">当前已选择品牌：</div>
        <div class="text2" id="selectBrandNames">暂无</div>
        <div class="text3" id="cancelBrandNamesBtn" onclick="cancel_searchBrand()"></div>
    </div>
    <div class="clear"></div>
    <div id="brandLlistitem" class="brandLlistitem"></div>
    <div class="clear"></div>
    <div class="file_upload_btnbox">
        <div class="file_upload_btn_cancel" onclick="close_All_Box()">取消</div>
        <div class="file_upload_btn_submit" onclick="do_Select_Brand()">确定</div>
    </div>
</div>
<script>
    function entersearch_brand() {
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13) {
            searchBrand();
        }
    }
</script>
<!-- 商品品牌弹窗 e -->

<!-- 商家名称弹窗 s -->
<div id="xia_ApplicantName_Box" class="brandBox">
    <div id="applicantNameLetter" class="letterbox"></div>
    <div class="inputbox">
        <input type="text" id="applicantnameSearchName" class="form-control" placeholder="按商家名称或关键词搜索"
               name="applicantnameSearchName" value="" onkeyup="entersearch_applicantName()"/>
        <div class="ssicon" onclick="searchApplicantName()"><i class="layui-icon layui-icon-search"></i></div>
        <div class="text1">当前已选择商家：</div>
        <div class="text2" id="selectApplicantNames">暂无</div>
        <div class="text3" id="cancelApplicantNameBtn" onclick="cancel_searchApplicantName()"></div>
    </div>
    <div class="clear"></div>
    <div id="applicantnameLlistitem" class="brandLlistitem"></div>
    <div class="clear"></div>
    <div class="file_upload_btnbox">
        <div class="file_upload_btn_cancel" onclick="close_All_Box()">取消</div>
        <div class="file_upload_btn_submit" onclick="do_Select_ApplicantName()">确定</div>
    </div>
</div>
<script>
    function entersearch_applicantName() {
        var event = window.event || arguments.callee.caller.arguments[0];
        if (event.keyCode == 13) {
            searchApplicantName();
        }
    }
</script>
<!-- 商家名称弹窗 e -->

</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/business/goodsManagement.js?v=74'/>"></script>

