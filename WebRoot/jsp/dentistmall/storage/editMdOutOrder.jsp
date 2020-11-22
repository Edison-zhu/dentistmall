<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>基础平台系统</title>
    <%@ include file="/jsp/lib.jsp" %>
    <%@ include file="/jsp/link.jsp" %>
    <style>
        .collectText {
            float: left;
            position: relative;
            left: 1%;
            font-size: 14px;
        }

        .collectText span {
            color: black;
            font-weight: bold;
        }

        .tdclass {
            color: gray;
        }
        .searchDiv {
            width: 50%;
            display: inline;
            margin-left: 30px;
        }
        .searchDiv div{
            margin-top: -13px;
        }
        .searchDiv input{
            width: 80px;
            height: 30px;
            font-size: 14px;
            vertical-align: middle;
            margin-top: -10px;
        }
        .searchBtn {
            width: 80px;
            height: 23px;
            font-size: 12px;
            vertical-align: middle;
            margin-top: -8px;
        }
        .input-text{
            height: 22px;
        }
        .input-button{
            height: 22px;
            padding: 4px 9px;
            line-height: 1px;
        }
        #exportBtn{
       		width:95px;
  			height:21px;
        }
        #button1{
        	width:95px;
  			height:21px;
        }
        #win_edit_toolbar{
  			float: right;
        }
    </style>
</head>

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
            <div id="d1">
                <table style="margin: 0px auto;margin-bottom: 10px">
                    <tr>
                        <td class="tdclass">温馨提示：</td>
                        <td class="tdclass">您可通过继续添加，来增加物料信息。也可以通过勾选不需要的物料移除信息</td>
                    </tr>
                    <tr>
                        <td></td>
                        <td class="tdclass">填写申请数量后，点击提交申请后即可在申请管理中查看申请记录</td>
                    </tr>
                </table>
            </div>
            <!--
                2019-12-08
                yanglei
                修改申领人角色登录的添加申领中的，去掉申领单号、申领人、申领部门这些,之后用到删除注释就加上。
                2019-12-09 yangfeng 将form表单隐藏，form表单中有需要的参数
             -->
            <div id="win_form_body">
            </div>


            <div class="ibox float-e-margins" style="border:1px solid #e7eaec;margin-top:5px">
                <!--标题栏区域 begin-->
                <div class="ibox-title"
                     style="border-bottom:1px solid #e7eaec;border-top:0px;min-height:45px;margin-bottom:10px">
                    <!-- 标题文字区域begin -->
                    <h5>&nbsp&nbsp申领明细列表</h5>
                    <div class="searchDiv">
                        <div id="searchForm" class="form-inline" style="display: inline">
                            <label>商品名称：</label>
                            <input type="text" class="form-control" placeholder="请输入商品名称,回车查询" id="searchMatName"/>
                            <label>规格：</label>
                            <input type="text" class="form-control" placeholder="请输入规格,回车查询" id="searchNorm"/>
                            <button type="button" onclick="searchmoo();" class="btn btn-primary btn-xs searchBtn" id="button1">搜索
                            </button>
                        </div>
                    </div>
<%--					商品名称:--%>
<%--					<input type="text" id="spmc" value=""></input> 规格:<input type="text" id="gg" value=""></input>--%>
<%--					<a onclick="win_form_search()"  class='btn btn-success  btn-xs' style="color:#fff">搜索</a>--%>
					<!-- 标题文字区域end -->
                    <div class="ibox-tools" style="display: inline">
						<div style="display: inline"> <!-- 导出功能，修改对此div修改，由于导出会一直存在，不要放进id=ibox-tools-moo的div里 -->
							<button type="button" class="btn btn-success btn-xs" id="exportBtn"
									onclick="exportFile();"><i class="fa fa-download"></i>导出
							</button>
						</div>
                        <div id="ibox-tools-moo" style="display: none">
                            <i id="win_edit_toolbarRR"></i>
                            <button type="button" class="btn btn-danger btn-xs" id="removeBatchBtn"
                                    onclick="removeBatch();"><i class="fa fa-close"></i>移除商品
                            </button>
<%--                            <button type="button" class="btn btn-success btn-xs" id="selInvBut" onclick="selInv();"><i--%>
<%--                                    class="fa fa-edit"></i>选择商品--%>
<%--                            </button>--%>
                        </div>
                    </div>
                </div>
                <div id="gridDiv">
                    <table id="datagrid1" class="mmg"></table>
                    <!-- 汇总 -->
                    <div class="collectText">
                        汇总：共计申请<span id="collectCount">0</span>条商品，申请数量合计<span id="collectAllCount">0</span>
                    </div>
                    <div id="pgOutOrder" style="text-align: right;"></div>
                </div>
                <!-- !--表格区域begin -->

                <!--!--表格区域end-->
                <div class="form-inline" style="margin-left: 10px">
                    <label>备注：</label>
                    <input type="text" id="remarks" class="form-control" style="width: 600px;"
                           placeholder="选填字段，字数不超过100"
                           name="remarks"/>
                </div>
            </div>
            <!--    <div id="">
                   <center><div id="win_edit_toolbarRR"></div></center>
               </div> -->
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
<input type="hidden" id="uName" value="${sessionScope.sessionUser.userName}">
</body>

</html>
<script src="<c:url value='/javaScript/dentistmall/storage/editMdOutOrder.js?v=42'/>"></script>