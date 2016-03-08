<%@ page
    contentType="text/html ; charset=UTF-8"
    pageEncoding="UTF-8"
    %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml">
        <head>
            <meta charset="utf-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1.0" />
            <title>ManagePage</title>
            <!-- BOOTSTRAP STYLES-->
            <link href="/TeraNaviAdmin/css/assets/css/bootstrap.css" rel="stylesheet" />
            <!-- FONTAWESOME STYLES-->
            <link href="/TeraNaviAdmin/css/assets/css/font-awesome.css" rel="stylesheet" />
            <!-- MORRIS CHART STYLES-->
            <link href="/TeraNaviAdmin/css/assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
            <!-- CUSTOM STYLES-->
            <link href="/TeraNaviAdmin/css/assets/css/custom.css" rel="stylesheet" />
            <!-- GOOGLE FONTS-->
            <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        </head>
        <body>
            <div id="wrapper">
                <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="index.html">管理者</a>
                    </div>
                    <div style="color: white;
                        padding: 15px 50px 5px 50px;
                        float: right;
                        font-size: 16px;">Last access : <span id="lastTime">${sessionScope.loginUser.adminLastLoginDate}</span> &nbsp;
                        <a href="/TeraNavi/front/logout" class="btn btn-danger square-btn-adjust">ログアウト</a>
                    </div>
                </nav>
                <!-- /. NAV TOP -->
                <nav class="navbar-default navbar-side" role="navigation">
                    <div class="sidebar-collapse">
                        <ul class="nav" id="main-menu">
                            <li class="text-center">
                                <img src="${sessionScope.loginUser.iconPath}" class="user-image img-responsive">
                                </li>
                                <li>
                                    <a href="/TeraNaviAdmin/dashboard" class="active-menu"><i class="fa fa-dashboard fa-3x"></i> ダッシュボード</a>
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
                                            <a href="/TeraNaviAdmin/adminau">権限</a>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="/TeraNaviAdmin/KeyCreate"><i class="fa fa-key fa-3x"></i> 登録キー</a>
                                </li>
                                <li>
                                    <a href="/TeraNaviAdmin/front/contsupplist"><i class="fa fa-envelope fa-3x"></i> お問い合わせ</a>
                                </li>
                                <li>
                                    <a href="/TeraNaviAdmin/notice"><i class="fa fa-edit fa-3x"></i> お知らせ </a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <!-- /. NAV SIDE  -->
                    <div id="page-wrapper" >
                        <div id="page-inner">

                            <!-- /. ROW  -->
                            <hr />
                            <div class="row">
                                <div class="col-md-3 col-sm-6 col-xs-6">
                                    <div class="panel panel-back noti-box">
                                        <span class="icon-box bg-color-red set-icon">
                                            <i class="fa fa-envelope-o"></i>
                                        </span>
                                        <div class="text-box" >
                                            <p class="main-text" id="new_contact">0 件</p>
                                            <p class="text-muted">新着問い合わせ</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3 col-sm-6 col-xs-6">
                                    <div class="panel panel-back noti-box">
                                        <span class="icon-box bg-color-green set-icon">
                                            <i class="fa fa-bars"></i>
                                        </span>
                                        <div class="text-box" >
                                            <p class="main-text" id="new_report">0 通</p>
                                            <p class="text-muted">新着通報数</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-2 col-sm-4 col-xs-4 pull-right">
                                    <div class="panel panel-back noti-box">
                                        <span class="icon-box set-icon">
                                            <i class="fa "></i>
                                        </span>
                                        <div class="text-box" >
                                            <div class="col-xs-3"> <i class="fa fa-cloud fa-3x"></i> Newyork City </div>
                                            <div class="col-xs-3">
                                                <div class="text-temp"> 10° </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- /. ROW  -->
                                    <hr />
                                </div>
                            </div>
                            <!-- /. ROW  -->
                            <div class="row">
                                <div class="col-md-5 col-sm-12 col-xs-12">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            Bar Chart Example
                                        </div>
                                        <div class="panel-body">
                                            <div id="morris-bar-chart"></div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6 col-sm-12 col-xs-12">
                                    <div class="panel panel-default">
                                        <div class="panel-heading">
                                            Area Chart Example
                                        </div>
                                        <div class="panel-body">
                                            <div id="morris-area-chart"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /. ROW  -->

                            <div class="col-md-9 col-sm-12 col-xs-12">

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Responsive Table Example
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-bordered table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>#</th>
                                                        <th>First Name</th>
                                                        <th>Last Name</th>
                                                        <th>Username</th>
                                                        <th>User No.</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <td>1</td>
                                                        <td>Mark</td>
                                                        <td>Otto</td>
                                                        <td>@mdo</td>
                                                        <td>100090</td>
                                                    </tr>
                                                    <tr>
                                                        <td>2</td>
                                                        <td>Jacob</td>
                                                        <td>Thornton</td>
                                                        <td>@fat</td>
                                                        <td>100090</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3</td>
                                                        <td>Larry</td>
                                                        <td>the Bird</td>
                                                        <td>@twitter</td>
                                                        <td>100090</td>
                                                    </tr>
                                                    <tr>
                                                        <td>1</td>
                                                        <td>Mark</td>
                                                        <td>Otto</td>
                                                        <td>@mdo</td>
                                                        <td>100090</td>
                                                    </tr>
                                                    <tr>
                                                        <td>2</td>
                                                        <td>Jacob</td>
                                                        <td>Thornton</td>
                                                        <td>@fat</td>
                                                        <td>100090</td>
                                                    </tr>
                                                    <tr>
                                                        <td>3</td>
                                                        <td>Larry</td>
                                                        <td>the Bird</td>
                                                        <td>@twitter</td>
                                                        <td>100090</td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- /. ROW  -->
                    </div>
                    <!-- /. PAGE INNER  -->
                </div>
                <!-- /. PAGE WRAPPER  -->
            </div>
            <!-- /. WRAPPER  -->
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
            <script src="/TeraNaviAdmin/js/new-contact.js"></script>

        </body>
    </html>
