<%@ page
contentType="text/html ; charset=UTF-8"
pageEncoding="UTF-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
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
</head><body>
    <div id="wrapper">
        <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">管理者</a>
            </div>
            <div style="color: white;

            padding: 15px 50px 5px 50px;

            float: right;

            font-size: 16px;">Last access : ${sessionScope.loginUser.adminLastLoginDate} &nbsp;
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
                        <a href="/TeraNaviAdmin/dashboard.jsp"><i class="fa fa-dashboard fa-3x"></i> ダッシュボード</a>
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/userManager.jsp"><i class="fa fa-user fa-3x"></i> ユーザー管理</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-tasks fa-3x"></i> 利用変更<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="/TeraNaviAdmin/policyedit">利用規約</a>
                            </li>
                            <li>
                                <a href="/TeraNaviAdmin/authorrity.jsp">権限</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/KeyCreate"><i class="fa fa-key fa-3x"></i> 登録キー</a>
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/contact" class="active-menu"><i class="fa fa-envelope fa-3x"></i> お問い合わせ</a>
                    </li>
                    <li>
                        <a href="/TeraNaviAdmin/notice"><i class="fa fa-edit fa-3x"></i> お知らせ </a>
                    </li>
                </ul>
            </div>
        </nav>
    <!-- /. NAV SIDE -->
    <div id="page-wrapper">
        <table class="table table-hover">
            <caption><h3>お問い合わせ</h3></caption>
            <tbody>
                <tr>
                    <th>No</th>
                    <th>名前</th>
                    <th>カテゴリ</th>
                    <th>タイトル</th>
                    <th>時間</th>
                    <th>詳細</th>
                </tr>
                <c:forEach var="contact" items="${result}">
                    <tr id="contactId_${contact.id}">
                        <td>${contact.id}</td>
                        <td>${contact.userName}</td>
                        <td>${contact.category}</td>
                        <td>${contact.title}</td>
                        <td>${contact.date}</td>
                        <td hidden>${contact.contactBody}</td>
                        <td hidden>${contact.address}</td>
                        <td class="col-md-1 pull-right">
                            <button type="button" class="btn btn-primary" onclick="read(${contact.id})">詳細へ</button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="contact-modal" class="modal fade" aria-hidden="true" style="display: none;">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h1 class="text-center">返信</h1>
                    </div>
                    <div id="contactM" class="modal-body">
                        <form action="/TeraNavi/front/" method="post">
                            <div class="form-group">
                                <label class="control-label" for="exampleInputName">お名前</label>
                                <input class="form-control" id="contactReplayName" name="name" type="text">
                            </div>
                            <div class="form-group">
                                <label class="control-label" for="exampleInputTitle">件名</label>
                                <input class="form-control" id="contactReplayTitle" name="title" type="text">
                            </div>
                            <div class="form-group">
                                <label class="control-label">メールアドレス</label>
                                <input class="form-control" id="contactReplayAddress" type="email" name="address">
                            </div>
                            <div class="form-group">
                                <label class="control-label">本文</label>
                                <textarea class="form-control" id="contactReplayBody" name="body" rows="6"></textarea>
                            </div>
                            <button type="submit" id="contactBtn" class="btn btn-default pull-right">送信する</button>
                        </form>

                    </div>
                    <div class="modal-footer"></div>
                </div><!--end moal-content-->
            </div><!--end modal-dialog-->
        </div>

        <div id="mail-modal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h1 class="text-center">お問い合わせ詳細</h1>
                    </div>
                    <div id="contactM" class="modal-body">

                        <div class="form-group">
                            <label class="control-label" for="exampleInputName">お名前</label>
                            <input class="form-control" id="contactName" name="name" type="text" readonly>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="exampleInputTitle">件名</label>
                            <input class="form-control" id="contactTitle" name="title" type="text" readonly>
                        </div>
                        <div class="form-group">
                            <label class="control-label">メールアドレス</label>
                            <input class="form-control" id="contactAddress" type="email" name="address" readonly>
                        </div>
                        <div class="form-group">
                            <label class="control-label">カテゴリ</label>
                            <input class="form-control" id="contactCategory" name="category" readonly>
                        </div>
                        <div class="form-group">
                            <label class="control-label">本文</label>
                            <textarea class="form-control" id="contactBody" name="body" readonly rows="6"></textarea>
                        </div>
                            <button type="button" id="contactBtn" class="btn btn-default pull-right" onclick="formWrite(false)">返信する</button>
                                </div>
                    <div class="modal-footer"></div>
                </div><!--end moal-content-->
            </div><!--end modal-dialog-->
        </div><!--end modal-->

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
    <script src="/TeraNaviAdmin/js/contact-modal.js"></script>
</body>
</html>
