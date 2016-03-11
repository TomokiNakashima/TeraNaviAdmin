<%@ page
   contentType="text/html ; charset=UTF-8"
   pageEncoding="UTF-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html><head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ManagePage</title>
    <!-- BOOTSTRAP STYLES-->
    <link href="/TeraNaviAdmin/css/assets/css/bootstrap.css" rel="stylesheet">
    <!-- FONTAWESOME STYLES-->
    <link href="/TeraNaviAdmin/css/assets/css/font-awesome.css" rel="stylesheet">
    <!-- MORRIS CHART STYLES-->
    <link href="/TeraNaviAdmin/css/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet">
    <!-- CUSTOM STYLES-->
    <link href="/TeraNaviAdmin/css/assets/css/custom.css" rel="stylesheet">
    <link href="/TeraNaviAdmin/css/adminau.css" rel="stylesheet">
    <!-- GOOGLE FONTS-->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
</head><body style="overflow:hidden">
				<%
				if(session.getAttribute("loginUser")==null){
					response.sendRedirect("/TeraNaviAdmin/index"); 
				}
				%>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand">${sessionScope.loginUser.userName}</a>
            </div>
            <div style="color: white;

            padding: 15px 50px 5px 50px;

            float: right;

            font-size: 16px;">Last access : ${sessionScope.loginUser.adminLastLoginDate} &nbsp;
            <a href="/TeraNaviAdmin/front/logout" class="btn btn-danger square-btn-adjust">ログアウト</a>
        </div>
    </nav>
    <!-- /. NAV TOP -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
                <li class="text-center">
                    <img src="${sessionScope.loginUser.iconPath}" style="height:200px;width:200px;" class="user-image img-responsive">
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/dashboard"><i class="fa fa-dashboard fa-3x"></i> ダッシュボード</a>
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/userManagement"><i class="fa fa-user fa-3x"></i> ユーザー管理</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-tasks fa-3x"></i> 利用変更<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="/TeraNaviAdmin/policyedit">利用規約</a>
                            </li>
                            <li>
                                <a href="/TeraNaviAdmin/adminau" class="active-menu">管理者権限</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/KeyCreate"><i class="fa fa-key fa-3x"></i> 登録キー</a>
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/front/contsupplist"><i class="fa fa-envelope fa-3x"></i> お問い合わせ</a>
                    </li>
                    <li style="display: none">
                        <a href="/TeraNaviAdmin/notice"><i class="fa fa-edit fa-3x"></i> お知らせ </a>
                    </li>
                </ul>
        </div>
    </nav>
    <!-- /. NAV SIDE -->
    <div id="page-wrapper">
            <div id="page-inner">
        <div class="col-md-3 col-md-offset-4" style="margin-top:20px;">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="ユーザー名" id="inputUserName">
                <span class="input-group-btn">
                    <button class="btn btn-default fa fa-search" type="button" style="height:34px" onclick="search()" id="search_button"></button>
                </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->

        <!-- print area -->
        <div class="col-md-8" style="padding:50px;margin-left:15%">
            <div class="row" id="printArea">
                <img src="http://pic.58pic.com/58pic/11/44/45/35Y58PICeC9.jpg" style="margin-left:15%;">
            </div>
        </div>
    </div>
        <!-- /print area -->
        <!-- /. PAGE WRAPPER -->
    </div>
    <!-- /. WRAPPER -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="/TeraNaviAdmin/js/assets/js/jquery-1.10.2.js"></script>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="/TeraNaviAdmin/js/assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="/TeraNaviAdmin/js/assets/js/jquery.metisMenu.js"></script>
    <!-- MORRIS CHART SCRIPTS -->
    <script src="/TeraNaviAdmin/js/assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="/TeraNaviAdmin/js/assets/js/morris/morris.js"></script>
    <!-- CUSTOM SCRIPTS -->
    <script src="/TeraNaviAdmin/js/assets/js/custom.js"></script>
    <script src="/TeraNaviAdmin/js/search.js"></script>

</body></html>
