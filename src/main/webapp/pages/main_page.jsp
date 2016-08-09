<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <link rel="shortcut icon" href="/resources/images/favicon.ico" type="image\x-icon" />
    <link rel="icon" href="/resources/images/favicon.ico" type="image\x-icon" />
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/resources/js/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="/resources/js/todoScripts.js"></script>
    <sec:csrfMetaTags />
    <meta http-equiv="refresh" content="${pageContext.session.maxInactiveInterval}">
    <title>WunderWaffel</title>
</head>
<body style="background-image: url('/resources/images/background2.jpg'); background-attachment: fixed">

<nav class="navbar navbar-inverse navbar-fixed-top" style="color: #3e8f3e">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">

            </button>
            <a class="navbar-brand" href="#" style="font-size: 160%; padding-left: 30px">WunderWaffel</a>
        </div>

        <p id="navbarText" class="navbar-text" style="font-size: 115%; padding-left: 145px; width: 60%; white-space: nowrap; overflow: hidden; text-overflow: ellipsis"></p>
        <form class="navbar-form navbar-right" style="padding-right: 30px" action="/auth/logout">
            <button id="profileButton" onclick="showProfile(event)" type="submit" class="btn btn-default">Profile</button>
            <button type="submit" class="btn btn-default">Sign Out</button>
        </form>
    </div>
    </div>
</nav>




</body>
</html>
