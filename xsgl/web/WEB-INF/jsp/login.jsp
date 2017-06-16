<%@ taglib prefix="S" uri="/struts-tags" %>
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
    <script src="js/angular/angular.min.js"></script>
    <link href="css/font/css/font.css" rel="stylesheet" />
    <title>登录</title>

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
            font-size: 30px;
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
            width: 600px;
            margin: 0 auto;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-left: -300px;
            margin-top: -300px;
        }
        .form-group{

        }

    </style>

</head>

<body ng-app="app">

<div class="big">

    <div class="row" id="big">

        <h1 id="title">
            用户登录
        </h1>

    </div>
    <div class="loginform" ng-controller="con">
        <div class="center" >
            <div class="logincontainer col-sm-12">

                <div class="login-content">

                    <form method="post" action="login.action">

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
                                <input name="user.password" ng-focus="pwdfocus=1+pwdfocus" type="password" ng-model="pwd"
                                       ng-class="pwd_c" placeholder="请输入密码"/>
                                <span class="tubiao" ng-class="pwdIcon"></span> <span ng-bind="pwdMeg"></span>
                                <h4 style="color: red"><S:property value="loginMessage"></S:property></h4>
                            </div>

                        </div>

                        <div class="form-group">
                            <input class="input_submit" type="submit" value="登录"
                                   ng-disabled="login"/>
                        </div>

                    </form>

                </div>
                <div class="nav"></div>

            </div>
        </div>
    </div>
</div>
</body>

<script>
    var m = angular.module("app", []);

    m.controller("con", function ($scope, $http) {
        /**
         glyphicon glyphicon-remove    错误图标
         glyphicon glyphicon-ok         ok图标

         * */
        $scope.login = true;
        $scope.nameIcon;
        $scope.pwdIcon;
        $scope.pwd_s = false;
        $scope.name_s = false;
        $scope.nameMeg = "";
        $scope.pwdMeg = "";
        $scope.$watch("name", function (name) {
            $scope.nameIcon = "";
            $scope.nameMeg = "";
            $scope.name_c = "";

            if (undefined == name) {
                name = "";
            }
            if ($scope.namefocus != undefined) {


                $http({method:'POST',url:'user_isUser.action',data:{user:{"name":name}}}).then(function (res) {
                    if (res.data.status == 1) {
                        $scope.nameIcon = "glyphicon glyphicon-ok suc";
                        $scope.nameMeg = "";
                       $scope.name_c = "suc_c";
                        $scope.name_s = false;
                        if (!$scope.pwd_s && !$scope.name_s) {
                            $scope.login = false;
                        }


                    }else if(res.data.status==-1) {
                        $scope.nameMeg="用户名不存在";
                        $scope.name_s = true;
                        $scope.login = true;
                        $scope.nameIcon = "glyphicon glyphicon-remove error";
                        $scope.name_c = "error_c";
                    }
                }, function () {
                    alert("查询失败")

                });


            }
        })

        $scope.$watch("pwd", function (pwd) {

            $scope.pwdIcon = "";
            $scope.pwdMeg = "";
            $scope.pwd_c = "";
            if (undefined == pwd) {
                pwd = "";
            }
            if ($scope.pwdfocus != undefined) {


                    if (pwd.length>=4) {
                        $scope.pwdIcon = "glyphicon glyphicon-ok suc";
                        $scope.pwdMeg = "";
                        $scope.pwd_c = "suc_c";
                        $scope.pwd_s = false;
                        if (!$scope.pwd_s && !$scope.name_s) {
                            $scope.login = false;
                        }
                    } else {
                        $scope.login = true;
                        $scope.pwd_s = true;
                        $scope.pwdIcon = "glyphicon glyphicon-remove error";
                        $scope.pwdMeg = "密码要大于4位";
                        $scope.pwd_c = "error_c";
                    }

            }
        })
    });
</script>


</html>