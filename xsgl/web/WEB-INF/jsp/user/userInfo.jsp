<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-06-05/005
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="addUser">
    <h1>用户信息

        <span class="glyphicon glyphicon-remove" ng-click="closeWindow()"></span>
    </h1>
    <div class="content">

        <table>
            <tr>
                <td>头像：</td>
                <td>
                    <img ng-src="{{user.img}}" >

                </td>
            </tr>
            <tr>


                <td>用户名：</td>
                <td ng-bind="user.name">


                </td>
            </tr>
            <tr>
                <td >密码：</td>
                <td ng-bind="user.password">


                </td>
            </tr>


            <tr>
                <td>用户权限：</td>
                <td ng-bind="{user:'普通用户',admin:'管理员',superAdmin:'超级管理员'}[user.permission]">
                </td>
            </tr>


        </table>


    </div>
    <div>
        <p>
            <button type="button" ng-click="addUser()">编辑</button>
            <button type="button" ng-click="closeWindow()">关闭</button>

        </p>

    </div>
</div>
<div class="big"></div>


