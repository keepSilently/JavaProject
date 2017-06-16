<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-06-05/005
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="addUser">
    <h1>{{title}}

        <span class="glyphicon glyphicon-remove" ng-click="closeWindow()"></span>
    </h1>
    <div class="content">

        <table>
            <tr>
                <td>头像：</td>
                <td>
                    <img ng-src="{{user.img}}" ng-click="showFile()" id="txImg">
                    <input type="file" accept="image/*" id="img" nv-file-select="" uploader="uploader"
                           onchange='angular.element(this).scope().fileNameChanged(this)' style="display:none"/>
                    <p ng-click="showFile()">点击更换头像</p>
                </td>
            </tr>
            <tr>


                <td>用户名：</td>
                <td>
                    <input ng-model="user.name" type="text">

                </td>
            </tr>
            <tr>
                <td>密码：</td>
                <td>
                    <input ng-model="user.password" type="text">

                </td>
            </tr>


            <tr>
                <td>设置管理员：</td>
                <td>

                    <div class="td_toggle">
                        <label class="toggle">
                            <input type="checkbox" ng-checked="{user:false,admin:true}[user.permission]" ng-model="checkbox" >
                            <span class="handle"></span>
                        </label>

                    </div>


                </td>
            </tr>


        </table>


    </div>
    <div>
        <p class="close" ng-click="selectClose()" style="line-height: 45px;padding-left: 8px;cursor: pointer;margin-top: 0">

            <span ng-class="{false:'glyphicon glyphicon-unchecked',true:'glyphicon glyphicon-check',undefined:'glyphicon glyphicon-unchecked'}[windown.isClose]"></span>
            确认并关闭窗口

        </p>
        <p>
            <button type="button" ng-click="addUser()">确定</button>
            <button type="button" ng-click="closeWindow()">关闭</button>

        </p>

    </div>
</div>
<div class="big"></div>


