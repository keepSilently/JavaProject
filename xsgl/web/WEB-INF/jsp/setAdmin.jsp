<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-06-09/009
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">

    <link href="css/font/css/font.css" rel="stylesheet" />
    <title>设置超级管理员</title>

    <style type="text/css">
        body {
            background-color: #212530;
        }

        #big {
            margin-bottom: 10px;
            margin-top: 13%;
            font-family: '微软雅黑';
        }

        #big h1 {
            text-align: center;
        }

        #title {
            font-size: 18px;
            color: #fff;
        }

        .center {
            background-color: #fff;
            padding: 2.5%;
            border-radius: 6px;
            box-shadow: 2px 2px 9px #333333;
            height: 250px;
        }

        .user {
            width: 100%;
            height: 60px;
        }

        .user span {
            text-align: center;
        }

        .tubiao {
            margin-right: 42%;
            margin-top: -25px;
            display: block;
            float: right;
        }

        input {
            padding-left: 5px;
            padding-right: 20px;
            border: 1px solid rgba(82, 168, 236, 0.8);
            border-radius: 6px;
            width: 60%;
            height: 34px;
        }

        input:focus {
            border: 1px solid #3c763d;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 6px #3c763d;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 6px #3c763d
        }

        .suc_c {
            border: 1px solid #3c763d;
        }

        .error_c {
            border: 1px solid #a94442;
        }

        .suc {
            color: #3c763d;
        }

        .glyphicon-ok {
            color: #5cb85c;
        }

        .error {
            color: #a94442;
        }

        .suc_c:focus {
            border: 1px solid #3c763d;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 6px #3c763d;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 6px #3c763d
        }

        .error_c:focus {
            border: 1px solid #a94442;
            -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 6px #a94442;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 6px #a94442
        }

        .input_submit {
            width: 60%;
            background: #5cb85c;;
            color: #fff;
            cursor: pointer;

        }

        .user span:nth-child(3) {
            color: red;
        }
        .big{
            width:450px;
            margin: 0 auto;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-left: -225px;
            margin-top: -225px;
        }
        .form-group{

        }

    </style>

</head>

<body ng-app="app">

<div class="big">

    <div class="row" id="big">

        <h1 id="title">
            欢迎首次使用本系统，请设置超级管理员
        </h1>

    </div>
    <div class="loginform" >
        <div class="center" >
            <div class="logincontainer col-sm-12">

                <div class="login-content">

                    <form method="post" action="user_setSuperAdmin.action">

                        <div class="form-group">
                            <label>用户名：</label>

                            <div class="user">
                                <input type="text" ng-focus="namefocus=1+namefocus" name="user.name" ng-class="name_c"
                                       placeholder="用户名" ng-model="name"> <span class="tubiao" ng-class="nameIcon">
									</span> <span ng-bind="nameMeg"></span>
                            </div>

                        </div>

                        <div class="form-group">
                            <label>

                                密码：
                            </label>
                            <div class="user">
                                <input name="user.password" ng-focus="pwdfocus=1+pwdfocus" type="text"
                                        placeholder="请输入密码"/>

                                <span class="tubiao"></span> <span ng-bind="pwdMeg"></span>
                            </div>

                        </div>

                        <div class="form-group">
                            <input class="input_submit" type="submit" value="确定设置"/>
                        </div>

                    </form>

                </div>
                <div class="nav"></div>

            </div>
        </div>
    </div>
</div>
</body>



</html>