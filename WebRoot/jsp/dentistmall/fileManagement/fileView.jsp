<%-- Author: songwenwen  Date: 2020/7/30 --%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<head>
    <title>商品模型相册管理</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <link rel="stylesheet" href="<c:url value='/css/fileManagement/fileManagement.css?v=7' />">
    <link rel="stylesheet" href="<c:url value='/js/plugins/layui/css/layui.css?v=1'/>" media="all">
    <script src="<c:url value='/js/plugins/layui/layui.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/js/plugins/pagination/pagination.css?13' />">
    <script src="<c:url value='/js/plugins/pagination/jquery.pagination.js?v=64' />"></script>
    <script type="text/javascript">
        //需要先配置layui，并使用use方法，这是单独将formSelects模块加入
        layui.config({
            base: '../../../js/' //此处路径请自行处理
        }).extend({
            formSelects: 'formSelects-v4'
        });
    </script>
</head>
<body>
<%-- 头部 s --%>
<div class="toptit d_flex_between">
    <div class="tit"><i class="layui-icon layui-icon-template-1"></i>查看相册</div>
    <div class="d_flex_right">
        <div class="white_btn" onclick="backTo()"><i class="layui-icon layui-icon-left"></i>&nbsp;返回</div>
        <div class="white_btn" onclick="getFileListUpdate()"><i class="layui-icon layui-icon-refresh"></i>&nbsp;刷新</div>
    </div>
</div>
<%-- 头部 e --%>

<!-- 表格 s -->
<div class="public_box2">
    <div class="tpbox d_flex_between">
        <div><span id="thisFileName"></span> (<span id="countPics"></span>张)</div>
        <div class="white_btn" onclick="open_Upload_File_Box()">上传图片</div>
    </div>
    <div class="file_list_box">
        <!-- ----------------- -->
        <div class="file_list_box_tpln">
            <div><input type="checkbox" id="allSelectFile1" class="selectItem" onclick="checkAllFile1(this,-1)" name="allSelectFile1" class="loginName">全选</div>
            <div onclick="batch_Deletion_Files()" >批量删除</div>
            <div onclick="open_transfer_File_Box_all()" >批量转移相册</div>
        </div>
        <!-- ----------------- -->
        <ul id="filesList" class="file_list_ul"></ul>
        <div class="clear"></div>
        <div class="file_list_box_ftln">
            <div class="lfbox">
                <div><input type="checkbox" id="allSelectFile2" class="selectItem" onclick="checkAllFile2(this,-1)" name="allSelectFile2" class="loginName">全选</div>
                <div onclick="batch_Deletion_Files()" >批量删除</div>
                <div onclick="open_transfer_File_Box_all()" >批量转移相册</div>
            </div>
            <div class="pagination m-pagenum pagination rtbox" id="Pagination"></div>
        </div>
        <!-- ----------------- -->
    </div>
</div>
<!-- 表格 e -->
<!-- 上传图片 s -->
<div id="upload_File_box" class="file_upload_box">
    <form id="file_upload_form" method="get" action="">
        <div class="file_upload_lnbox">
            <div class="file_upload_lf ">选择相册：</div>
            <div class="file_upload_rt1">
                <select onchange="select_Upload_File_Name()" id="upload_file_id"></select>
            </div>
        </div>
        <div class="file_upload_lnbox">
            <div class="file_upload_lf">选择图片：</div>
            <div class="awards-imgsss file_upload_rt2">
                <ul class="imgul f-cb" id="imglist">
                </ul>
            </div>
            <div style="padding-left: 10px"  id="selectPicNum"></div>
            <div class="file_upload_tps">按住ctrl可同时批量选择多张图片上传</div>
        </div>
        <div class="clear"></div>
        <div class="file_upload_btnbox">
            <div class="file_upload_btn_cancel" onclick="close_Upload_File_Box()">取消</div>
            <div class="file_upload_btn_submit" onclick="do_Upload_File()">确定</div>
        </div>
    </form>
</div>
<!-- 上传图片 e -->


<!-- 转移相册 s -->
<div id="transfer_File_box" class="file_upload_box">
    <form id="file_transfer_form" method="get" action="">
        <div class="file_upload_lnbox">
            <div class="file_upload_lf ">选择相册：</div>
            <div class="file_upload_rt1">
                <select onchange="select_Transfer_File_Name()" id="transfer_file_id"></select>
            </div>
        </div>
        <div class="clear"></div>
        <div class="file_upload_btnbox">
            <div class="file_upload_btn_cancel" onclick="close_Upload_File_Box()">取消</div>
            <div class="file_upload_btn_submit" onclick="do_transfer_File()">确定</div>
        </div>
    </form>
</div>
<!-- 转移相册 e -->

</body>
</html>
<script src="<c:url value='/javaScript/dentistmall/fileManagement/fileView.js?v=82'/>"></script>
<%--<script src="<c:url value='/javaScript/dentistmall/fileManagement/imgFileupload.js?v=11'/>"></script>--%>