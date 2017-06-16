<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-05-30/030
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + "/";
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生管理系统</title>
    <link href="css/studentList.css" rel="stylesheet"/>
    <link href="css/userList.css" rel="stylesheet"/>
    <link href="css/font/css/font.css" rel="stylesheet"/>
    <link href="css/header.css" rel="stylesheet"/>
    <link href="css/addStudent.css" rel="stylesheet"/>
    <link href="css/addUser.css" rel="stylesheet"/>
    <link href="css/page.css" rel="stylesheet"/>
    <link href="css/upload.css" rel="stylesheet"/>
    <link href="css/classList.css" rel="stylesheet"/>
    <link href="css/tab.css" rel="stylesheet"/>
    <link href="css/upload.css" rel="stylesheet"/>
    <script src="js/angular/angular.min.js"></script>
    <script src="js/angular/angular-ui-router.min.js"></script>
    <script src="js/angular/angular-animate.min.js"></script>
    <script src="js/angular/angular-file-upload.js"></script>
    <script src="js/app.js"></script>
    <script src="js/class-controller.js"></script>
    <script src="js/student-controller.js"></script>
    <script src="js/user-controller.js"></script>
    <script src="js/page.js"></script>
    <script src="js/upload-controller.js"></script>
    <script src="js/datepicker/jquery-1.7.1.min.js"></script>
    <script src="js/datepicker/bootstrap-datepicker.js"></script>
    <script src="js/echarts/echarts.min.js"></script>

    <%--<link href="css/animate/animate.min.css" rel="stylesheet">--%>
</head>





<body ng-app="app" ng-controller="main">
<header>
    <h1>
        学生管理系统
    </h1>

    <ul>
        <li ui-sref-active="active"  ui-sref="index" ng-class='{activeTab:activeTab==1}'>

            <span class="glyphicon glyphicon-th-large
glyphicon "></span>
            <span>首页</span>

        </li>

        <s:if test="#session.SYS_USER.permission=='superAdmin'">
        <li ng-class='{activeTab:activeTab==2}' ui-sref-active="active"  ui-sref="user.page({'pageNo':1})">
            <span class="glyphicon glyphicon-user"></span>
            <span>用户管理</span>
        </li>
        </s:if>
        <li ng-class='{activeTab:activeTab==3}' ui-sref-active="active"  ui-sref="stu.page({'pageNo':1})">
            <span class="glyphicon glyphicon-cog"></span>
            <span>学生管理</span>
        </li>
        <s:if test="#session.SYS_USER.permission=='superAdmin'">
        <li ng-class='{activeTab:activeTab==4}' ui-sref-active="active"  ui-sref="class">
            <span class="glyphicon glyphicon-wrench"></span>
            <span>院系信息</span>
        </li>
        </s:if>

        <li ng-class='{activeTab:activeTab==5}' ui-sref-active="active"  ui-sref="userInfo">
            <span class="glyphicon glyphicon-exclamation-sign"></span>
            <span>个人信息</span>
        </li>
        <li  ng-class='{activeTab:activeTab==6}' ng-click="logout()">
            <span class="glyphicon glyphicon-log-out"></span>
            <span>退出</span>
        </li>
    </ul>
    <p style="margin-top: -65px">

        <img  src="<s:property value='#session.SYS_USER.img' />"/>
        <span >
        <s:property value='#session.SYS_USER.name' />
        </span>
    </p>
</header>
<nav class="header" style="padding-left:5px ">
    <span  style="line-height: 32px">欢迎，</span>
    <span ng-bind="{user:'普通用户',admin:'管理员',superAdmin:'超级管理员'}[user.permission]" style="line-height: 32px;color: #1a873a"></span>

    <span ng-bind="user.name" style="line-height: 32px"></span>
</nav>
<div class="studentList" ui-view="list"></div>
<a href="login_logout.action" id="logout" style="display: none"></a>




<script>

</script>


</body>
</html>