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
    <link rel="stylesheet" href="${request.contextPath}/static/project/css/websocket/chat.css">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/static/css/materialize.min.css"
          media="screen,projection"/>
<#--<link type="text/css" rel="stylesheet" href="${request.contextPath}/static/css/ghpages-materialize.css"/>-->
</head>

<body>
<header>
    <nav>
        <div class="nav-wrapper">
            <a href="#!" class="brand-logo"><i class="material-icons">cloud</i>Logo</a>
            <ul class="right hide-on-med-and-down">
                <li><a href="#!"><i class="material-icons">search</i></a></li>
                <li><a href="#!"><i class="material-icons">view_module</i></a></li>
                <li><a href="#!"><i class="material-icons">refresh</i></a></li>
                <li><a href="#!"><i class="material-icons">more_vert</i></a></li>
            </ul>
        </div>
    </nav>
</header>
<main>
    <div class="row">
    <h1>这里是${cid}号聊天室：</h1>
    <input type="hidden" id="cid" value="${cid}"/>
    <div id="chatView"></div>
</div>
    <div class="row">
        <form class="col s12">
            <div class="row">
            <#--<div class="input-field col s6">-->
            <#--<input placeholder="请输入信息" id="sendText" type="text">-->
            <#--<label for="first_name">发送内容</label>-->
            <#--</div>-->
                <div class="input-field inline">
                    <input id="sendText" type="message">
                    <label for="message" data-error="wrong" data-success="right">发送内容</label>
                    <a class="waves-effect waves-light btn" id="send">发送</a>
                </div>
            <#--<div class="input-field col s6">-->
            <#--<a class="waves-effect waves-light btn" id="send">发送</a>-->
            <#--</div>-->
            </div>
        </form>
    </div>
</main>
<!--JavaScript at end of body for optimized loading-->
<footer class="page-footer">
    <div class="container">
        <div class="row">
            <div class="col l4 s12">
                <h5 class="white-text">相关网站</h5>
                <ul>
                    <li><a class="white-text" target="_blank" href="http://www.okgoes.com">完美起航</a></li>
                    <li><a class="white-text" target="_blank" href="http://note.okgoes.com/">起航笔记</a></li>
                    <li><a class="white-text" target="_blank" href="http://vuematerial.materializecss.cn/">Vue
                        Material中文</a></li>
                    <li><a class="white-text" target="_blank" href="http://blog.okgoes.com/">起航博客</a></li>
                    <li><a class="white-text" target="_blank" href="http://forum.okgoes.com/forum.php">起航论坛</a></li>
                </ul>
            </div>
            <div class="col l4 s12">
                <h5 class="white-text">学习交流</h5>
                <p class="grey-text text-lighten-4">我们为此开发了一个关于Materialize UI框架学习的论坛，你可以在这个论坛分享你的学习经验，同时得到我们的技术帮助。</p>
                <a target="_blank"
                   href="//shang.qq.com/wpa/qunwpa?idkey=a483a26b5f09e710a058154e816b89366870562955be319050b7d096ffe0f9c2"><img
                        border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="materialize学习交流"
                        title="materialize学习交流"></a>
                <br>
                <a class="btn waves-effect waves-light red lighten-3" target="_blank"
                   href="http://c.materializecss.cn/">Materialize交流社区</a>
            </div>
            <div class="col l4 s12" style="overflow: hidden;">
                <h5 class="white-text">联系我们</h5>
                <!--
                <iframe src="http://ghbtns.com/github-btn.html?user=dogfalo&repo=materialize&type=watch&count=true&size=large" allowtransparency="true" frameborder="0" scrolling="0" width="170" height="30"></iframe>
                 -->
                <a href="http://wpa.qq.com/msgrd?V=3&amp;uin=473166512&amp;Site=完美起航&amp;Menu=yes&amp;from=okgoes"
                   target="_blank" title="QQ"><img src="${request.contextPath}/static/project/images/site_qq.jpg" alt="QQ"
                                                   style="vertical-align: middle;"></a>
                <br>
                <a href="mailto:473166512@qq.com" class="twitter-follow-button" data-show-count="true" data-size="large"
                   data-dnt="true">473166512@qq.com</a>
                <br>
                <div><h5 class="white-text">网站信息</h5></div>
                <div><a class="white-text" href="http://www.miitbeian.gov.cn/">赣ICP备15002760号-4</a></div>
                <div class="g-follow" data-annotation="bubble" data-height="24"
                     data-href="https://plus.google.com/108619793845925798422" data-rel="publisher"></div>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            © 2014-2016 Materialize, All rights reserved.
            <a class="grey-text text-lighten-4 right" href="https://github.com/Dogfalo/materialize/blob/master/LICENSE">MIT
                License</a>
        </div>
    </div>
</footer>
</body>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/materialize.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/project/js/websocket/chat.js"></script>

</html>