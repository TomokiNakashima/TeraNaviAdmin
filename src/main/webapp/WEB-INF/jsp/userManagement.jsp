<%@ page
contentType="text/html ; charset=UTF-8"
pageEncoding="UTF-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
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
    <!-- GOOGLE FONTS-->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="/TeraNaviAdmin/css/default.css">
    <link href="/TeraNaviAdmin/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
    <link href="/TeraNaviAdmin/css/custom.css" media="all" rel="stylesheet" type="text/css" />
</head><body style="overflow:hidden">
    <div id="wrapper">
            <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index.html">${sessionScope.loginUser.userName}</a>
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
                        <img src="${sessionScope.loginUser.iconPath}" class="user-image img-responsive">
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/dashboard"><i class="fa fa-dashboard fa-3x"></i> ダッシュボード</a>
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/userManagement" class="active-menu"><i class="fa fa-user fa-3x"></i> ユーザー管理</a>
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

    <!-- /. NAV SIDE -->
    <div id="page-wrapper" >
        <div id="page-inner">
            <div class="section">
        <div class="container">
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <ul class="lead nav nav-justified nav-tabs" style="position:absolute;z-index:1">
                        <li class="active">
                            <a href="#report" data-toggle="tab" class="text-warning" style="font-size: 130%;">通報</a>
                        </li>
                        <li>
                            <a href="#insert" data-toggle="tab" class="text-warning" style="font-size:130%;">一括登録</a>
                        </li>
                        <li>
                            <a href="#rocking" data-toggle="tab" class="text-warning" style="font-size:130%;">ロック中ユーザ</a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="tab-content">
        <div class="tab-pane active" id="report">
            <button class="btn btn-warning pull-right" onclick="reload()" id="refresh">更新</button>
            <div class="col-md-10" id="showUserArea_report">
            </div>
        </div>
        <div class="tab-pane" id="insert">
            <div class="col-md-10">
                <form id="csv_from">
                    <div class="form-group" style="margin-top:15%;margin-left:32%">
                        <label style="font-size:400%;font-family:HG行書体;" class="">CSVファイルアップロード</label><br/>
                        <div class="text-center">
                            <input id="file-3" type="file" accept=".csv">
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <div class="tab-pane" id="rocking">
            <button class="btn btn-warning pull-right" onclick="reload()" id="refresh">更新</button>
            <div class="col-md-10" id="showUserArea">
            </div>
        </div>
    </div>
    <div id="caution-modal" class="modal fade" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h1 class="text-center">通報内容</h1>
                </div>
                <div id="contactM" class="modal-body">
                    <form>
                        <%-- <div class="form-group">
                            <label class="control-label" for="adminName">管理者名</label>
                            <input class="form-control" id="contactReplayName" name="adminName" type="text">
                        </div> --%>
                        <div class="form-group">
                            <label class="control-label" for="Title">タイトル</label>
                            <input class="form-control" id="cautionTitle" name="title" type="text" readonly>
                        </div>
                        <div class="form-group">
                            <label class="control-label">被通報者</label>
                            <input class="form-control" id="cautionUser" type="email" name="address" readonly>
                        </div>
                        <div class="form-group">
                            <label class="control-label">通報者</label>
                            <input class="form-control" id="user" type="email" name="address" readonly>
                        </div>
                        <div class="form-group">
                            <label class="control-label">通報文</label>
                            <textarea class="form-control" id="cautionBody" name="body" readonly></textarea>
                        </div>
                        <button type="button" id="cautionBtn" class="btn btn-default pull-right">警告する</button>
                    </form>

                </div>
                <div class="modal-footer"></div>
            </div><!--end moal-content-->
        </div><!--end modal-dialog-->
    </div>
</div>
         <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER -->
    </div>
    <!-- /. WRAPPER -->
    <!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
    <!-- JQUERY SCRIPTS -->
    <script src="http://libs.useso.com/js/jquery/2.1.1/jquery.min.js"></script>
    <%-- <script src="/TeraNaviAdmin/js/assets/js/jquery-1.10.2.js"></script> --%>
    <!-- BOOTSTRAP SCRIPTS -->
    <script src="/TeraNaviAdmin/js/assets/js/bootstrap.min.js"></script>
    <!-- METISMENU SCRIPTS -->
    <script src="/TeraNaviAdmin/js/assets/js/jquery.metisMenu.js"></script>
    <!-- MORRIS CHART SCRIPTS -->
    <script src="/TeraNaviAdmin/js/assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="/TeraNaviAdmin/js/assets/js/morris/morris.js"></script>
    <!-- CUSTOM SCRIPTS -->
    <script src="/TeraNaviAdmin/js/assets/js/custom.js"></script>
    <script src="/TeraNaviAdmin/js/fileinput.js" type="text/javascript"></script>
    <script src="/TeraNaviAdmin/js/fileinput_locale_jp.js" type="text/javascript"></script>
    <script src="/TeraNaviAdmin/js/show-caution.js"></script>
    <script src="/TeraNaviAdmin/js/lock-account.js"></script>
    <script src="/TeraNaviAdmin/js/locking-user.js"></script>

    <script>
    $("#file-3").fileinput({
        uploadUrl: "/TeraNaviAdmin/UserCsv", // server upload action
        uploadAsync: true,
        maxFileCount: 1
    });

    function reload(){
    	loadUserList();
        showCaution();
    }
    </script>

</body></html>
