<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-05-30/030
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + "/";
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>list</title>
    <link href="css/studentList.css" rel="stylesheet"/>
    <link href="css/userList.css" rel="stylesheet"/>
    <link href="css/font/css/font.css" rel="stylesheet"/>
    <link href="css/header.css" rel="stylesheet"/>
    <link href="css/addStudent.css" rel="stylesheet"/>
    <link href="css/addUser.css" rel="stylesheet"/>
    <link href="css/page.css" rel="stylesheet"/>
    <link href="css/upload.css" rel="stylesheet"/>
    <script src="js/angular/angular.min.js"></script>
    <script src="js/angular/angular-ui-router.min.js"></script>
    <script src="js/angular/angular-animate.min.js"></script>
    <script src="js/angular/angular-file-upload.js"></script>
    <script src="js/app.js"></script>
    <script src="js/student-controller.js"></script>
    <script src="js/user-controller.js"></script>
    <script src="js/page.js"></script>
    <script src="js/upload-controller.js"></script>
    <script src="js/datepicker/jquery-1.7.1.min.js"></script>
    <script src="js/datepicker/bootstrap-datepicker.js"></script>

    <%--<link href="css/animate/animate.min.css" rel="stylesheet">--%>


</head>
<body ng-app="app" ng-controller="main">

<%@include file="/common/header.jsp" %>
<div class="studentList" ui-view="list"></div>

</body>
</html>
