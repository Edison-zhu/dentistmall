<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
      <title>优牙库</title>
      
<%@ include file="/jsp/lib.jsp"%> 
<%@ include file="/jsp/link.jsp"%>
<script src="<c:url value='/javaScript/main/main.js?v=40'/>"></script>
<style>
.g-notice {
  height: 44px;
}
.m-notice h3 {
  width: 50px;
  height: 44px;
  line-height: 44px;
  padding: 0 40px;
  color: #FFF;
  font-size: 12px;
  background-color: #587eab;
}
.m-notice {
  position: relative;
  width: 600px;
  float:left;
  margin-left:20px;
  height: 42px;
  overflow: hidden;
  padding: 0 30px;
  border-left: none;
}
.m-notice ul {
  position: absolute;
  width: 100%;
}
.m-notice .list {
  width: 600px;
  height: 44px;
  line-height: 44px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.m-notice a {
  color: #005bac;
  font-size: 13px;
}
.m-notice a:hover {
  text-decoration: underline;
}
</style>
</head>

<body class="fixed-sidebar full-height-layout gray-bg">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                <!-- 用户信息导向区域begin -->
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                        <!--  头像区域begin -->
                            <span>
                            	<c:if test="${sessionScope.sessionUser.barCodePath != null}">
                            		<img alt="image" id="heardImg" class="img-circle" src="${sessionScope.sessionUser.barCodePath}" style="width:80px;height:80px"/>
                            	</c:if>
                            	<c:if test="${sessionScope.sessionUser.barCodePath == null}">
                            		<img alt="image" id="heardImg" class="img-circle" src="img/tou.png" style="width:80px;height:80px"/>
                            	</c:if>
                            </span>
                        <!-- 头像区域end -->    
                        <!-- 用户信息区域begin -->   
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                  <!-- 用户名称begin -->
                                   <span class="text-muted text-xs block">(${sessionScope.sessionUser.orgName})</span>
	                               <!-- 用户名称end -->
	                               <!-- 用户角色begin -->
	                                <span class="text-muted text-xs block">${sessionScope.sessionUser.userName}<b class="caret"></b></span>
	                                  <!-- 用户角色end -->
                                </span>
                            </a>
                        <!-- 用户信息区域end -->     
                        <!-- 用户信息维护菜单 begin -->     
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a   href="javascript:editUser('${sessionScope.sessionUser.suiId}');">个人资料</a>
                                </li>
                                <li><a   href="javascript:updateHeard();">设置头像</a>
                                </li>
                                <li><a href="javascript:updatePass();">修改密码</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="javascript:ReceptionCancellation()">安全退出</a>
                                </li>
                            </ul>
                         <!-- 用户信息维护菜单 end -->     
                        </div>
                         <!--缩小后的图标begin -->     
                        <div class="logo-element">MAIN
                        </div>
                           <!--缩小后的图标end -->    
                    </li>
                <!-- 用户信息导向区域end -->
                <!-- 系统菜单区域begin -->
                <!-- 系统菜单区域end -->  
                   </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1" >
            <!-- 页头信息部分begin-->
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                 <!-- 菜单控制区域begin -->   
                    <div class="navbar-header">
                     <!-- 菜单的收缩按钮begin -->   
                      <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                      <!-- 菜单的收缩按钮end -->
                     </div>
                     <span class="m-notice">
				      <ul class="noticelist" id="noticelist">
				       </ul>
				   </span>
                <!--  菜单控制区域end -->
                <!-- 页头菜单区域begin -->
                    <ul class="nav navbar-top-links navbar-right">
                      
                        <!--菜单区域begin  -->
                         <li class="dropdown" style="margin-right:50px">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#" style="">
                                <i class="fa fa-bell"></i> <span class="label label-warning" id="warningCount"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-alerts" id="newsList" style="width:380px">
                            </ul>
                        </li>
                         		<!-- 菜单区域end  -->
                        
                    
                    </ul>
                   <!-- 欢迎信息end --> 
                </nav>
            </div>
            <!--页头信息部分 end  -->
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                       <!--  <a href="" class="active J_menuTab" data-id="/dentistmall/jsp/main/homePage.jsp">首页</a> -->
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <button class="roll-nav roll-right dropdown J_tabClose"><span class="dropdown-toggle" data-toggle="dropdown">关闭操作<span class="caret"></span></span>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <!-- <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li> -->
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </button>
                <a href="javascript:ReceptionCancellation()" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="" allowfullscreen="true"  webkitallowfullscreen="true" mozallowfullscreen="true"frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
            <!-- 页尾部分begin -->
            <div class="footer">
                <div class="pull-right">&copy;<a href="index.htm" target="_blank">优牙库商城</a>
                </div>
            </div>
            <!-- 页尾部分end -->
        </div>
        <!--右侧部分结束-->
</body>
</html>


<script>
		var  _win_main_orgGxId=${sessionScope.sessionUser.orgGxId};	
	    var  _win_main_orgName="${sessionScope.sessionUser.orgName}";
		// 获取当前登录角色的角色
	    var _user_type = "${sessionScope.sessionUser.userType}";
	    var _user_role = "${sessionScope.sessionUser.userRole}";
        var _user_org_type = "${sessionScope.sessionUser.organizaType}";
</script>
