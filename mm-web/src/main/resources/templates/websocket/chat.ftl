<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>hello world</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/static/css/materialize.min.css" media="screen,projection"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body>
<h1>这里是聊天室：${cid}</h1>
<input type="hidden" id="cid" value="${cid}"/>
<textarea id="chatView" style="height: 900px;width: 500px;"></textarea>
<!--JavaScript at end of body for optimized loading-->

</body>
<script type="text/javascript" src="${request.contextPath}/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/js/materialize.min.js"></script>
<script type="text/javascript" src="${request.contextPath}/static/project/js/websocket/chat.js"></script>

</html>