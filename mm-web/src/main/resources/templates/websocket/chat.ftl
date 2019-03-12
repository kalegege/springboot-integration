<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="msapplication-tap-highlight" content="no">
    <title>hello world</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/static/css/materialize.min.css"
          media="screen,projection"/>
    <link rel="stylesheet" href="${request.contextPath}/static/project/css/websocket/chat.css">
<#--<link type="text/css" rel="stylesheet" href="${request.contextPath}/static/css/ghpages-materialize.css"/>-->
</head>

<body>
<header>
    <nav>
        <div class="nav-wrapper row">
        <#--<a href="#!" class="brand-logo"><i class="material-icons">cloud</i>Logo</a>-->
            <form class="col s6">
                <div class="input-field">
                    <input id="search" type="search" required>
                    <label class="label-icon" for="search"><i class="material-icons">search</i></label>
                    <i class="material-icons">close</i>
                </div>
            </form>
            <ul class="navbar-list right col s3 offset-s3">
                <li><a href="#!"><i class="material-icons">search</i></a></li>
                <li><a href="#!"><i class="material-icons">view_module</i></a></li>
                <li><a href="#!"><i class="material-icons">refresh</i></a></li>
                <li><a href="#!"><i class="material-icons">more_vert</i></a></li>
            </ul>
        </div>
    </nav>
</header>
<ul id="slide-out" class="sidenav">
    <li>
        <div class="user-view">
            <div class="background">

            </div>
            <a href="#user"><span class="white-text name">12312</span></a>
            <a href="#name"><span class="white-text name">John Doe</span></a>
            <a href="#email"><span class="white-text email">jdandturk@gmail.com</span></a>
        </div>
    </li>
    <li><a href="#!"><i class="material-icons">cloud</i>First Link With Icon</a></li>
    <li><a href="#!">Second Link</a></li>
    <li>
        <div class="divider"></div>
    </li>
    <li><a class="subheader">Subheader</a></li>
    <li><a class="waves-effect" href="#!">Third Link With Waves</a></li>
</ul>
<a href="#" data-target="slide-out" class="sidenav-trigger"><i class="material-icons">menu</i></a>
<div class="row">
    <div class="col s16 animate fadeRight">
        <div class="card">
            <div class="card-content">
                <h4 class="card-title mb-0">用户ID：${cid}</h4>
                <input type="hidden" id="cid" value="${cid}"/>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col s6 offset-s3">
        <div class="card-panel teal">
            <span class="white-text" id="chatView"></span>
        </div>
    </div>
</div>
<div class="row">
    <form class="col s12">
        <div class="row">
        <#--<input id="sendText" type="message">-->
            <div class="input-field col s5 offset-s3">
                <textarea id="sendText" class="materialize-textarea"></textarea>
                <label for="textarea1">Textarea</label>
            </div>
            <div class="col s4">
                <a class="waves-effect waves-light btn" id="send">发送</a>
            </div>
        <#--<label for="message" data-error="wrong" data-success="right">发送内容</label>-->
        </div>
    </form>
</div>

<!--JavaScript at end of body for optimized loading-->
<footer class="page-footer footer footer-static footer-dark gradient-45deg-indigo-purple gradient-shadow navbar-border navbar-shadow">
    <div class="footer-copyright">
        <div class="container"><span>© 2019          <a
                href="http://themeforest.net/user/pixinvent/portfolio?ref=pixinvent" target="_blank">PIXINVENT</a> All rights reserved.</span><span
                class="right hide-on-small-only">Design and Developed by <a href="https://pixinvent.com/">PIXINVENT</a></span>
        </div>
    </div>
</footer>
</body>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/materialize.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/project/js/websocket/chat.js"></script>

</html>