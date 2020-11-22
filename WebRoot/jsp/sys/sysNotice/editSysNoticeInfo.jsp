<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>基础平台系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
</head>
<style>
    #win_edit_toolbar {
        float: right;
    }

    }
</style>
<body style="overflow:hidden; ">
<!-- 表单部分begin -->
<form id="accountForm" class="form-horizontal form-bordered"
      role="form">
    <div class="panel panel-default">
        <!-- 隐藏域begin -->
        <div id="win_form_edithidden"></div>
        <!--隐藏区域end -->
        <!-- 正文部分begin -->
        <div class="panel-body scrollbarinfo">
            <div id="win_form_body">
            </div>
            <div id="win_form_img">
                <div class="form-group" style="margin-bottom:0px;"><label class="col-sm-2 control-label">活动封面：</label>
                    <div class="col-sm-8">
                        <div id="asImg" class="asImg">
                            <div class="ibox float-e-margins" style="overflow:hidden;margin-top:5px">
                                <div class="ibox-content">
                                    <div class="form-group" styel="">
                                        <div class="awards-imgs">
                                            <ul class="imgul f-cb" id="imglist">
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="ibox-title" style="border:0px"><h5><span
                                        style="color: lightgrey;margin-left: 5px">格式：png,jpg,gif;大小:不超过3M</span></h5>
                                </div>
                                <input type="hidden" id="lessenFilecode" name="lessenFilecode"><input type="hidden"
                                                                                                      id="listFilecode"
                                                                                                      name="noticeImage"
                                                                                                      value="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="hr-line-dashed" style="margin:5px 0px;"></div>
            <div class="form-group" style="margin-bottom:0px;">
                <label class="col-sm-2 control-label">公告内容：</label>
                <div class="col-sm-9">
                    <textarea class="form-control KindEditor" placeholder="公告内容" name="content" id="content"
                              style="height:400px"></textarea>
                </div>
            </div>
        </div>
        <!-- 正文部分end -->
        <!-- 工具栏部分begin -->
        <div class="panel-footer">
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-4" id="win_edit_toolbar">
                </div>
            </div>
        </div>
        <!-- 工具栏部分end -->
    </div>
</form>
<!-- 表单部分end-->
</body>

</html>
<script src="<c:url value='/javaScript/sys/sysNotice/editSysNoticeInfo.js?v=4'/>"></script>