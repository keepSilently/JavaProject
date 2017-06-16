<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-06-05/005
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="userList">
    <h2>用户列表</h2>
    <div class="listHeader">
        <div>
            <input type="text" ng-model="keyword" placeholder="请输入查询的关键字">
            <button type="button" ng-Click="findUser()"><span class="glyphicon glyphicon-search"></span>查询</button>
        </div>
        <div>
            <a ui-sref-active="active"  ui-sref="user.upload"><span class="glyphicon glyphicon-upload"></span>从excel导入</a>
            <a ng-click="refresh()"><span class="glyphicon glyphicon-refresh"></span>刷新</a>
            <a  ui-sref-active="active" ui-sref="user.add" ng-click="add()"><span class="glyphicon glyphicon-plus"></span>新增</a>
            <a  ng-click="edit() " ><span class="glyphicon glyphicon-edit"></span>编辑</a>
            <a  ng-click="deleteUser()"><span class="glyphicon glyphicon-trash" ></span>删除</a>
            <a ng-click="exportExcel()"><span class="glyphicon glyphicon-download-alt" ></span>导出excel</a>
        </div>
    </div>


    <ul class="baseUL">
        <li ng-click="allCheck()">
            <span ng-class="{false:'glyphicon glyphicon-unchecked',true:'glyphicon glyphicon-check '}[check]"></span>
        </li>
        <li>用户名</li>
        <li>密码</li>
        <li>设置管理员</li>
        <li>权限</li>

    </ul>
    <div ng-repeat="user in userList">

        <ul ng-click="selectUser(user)">
            <li>
                <span ng-class="{false:'glyphicon glyphicon-unchecked',true:'glyphicon glyphicon-check',undefined:'glyphicon glyphicon-unchecked'}[user.checked]"></span>

            </li>
            <li class="tx">
                <img ng-src="{{user.img}}">

                <span ng-bind="user.name">姓名名1111111111</span></li>
            <li ng-bind="user.password">201502100304</li>
            <li>
                <label class="toggle">
                    <input type="checkbox" ng-checked="{user:false,admin:true}[user.permission]"  ng-click="changePermission($event,user)" >
                    <span class="handle" style="left: 100px;top: -35px;"></span>
                </label>

            </li>
            <li ng-bind="{user:'普通用户',admin:'管理员'}[user.permission]">软件急速呵呵</li>
        </ul>

    </div>

</div>

<div ui-view="page"></div>
<div class="bounceInDown" ui-view="addUser"></div>
<div  ui-view="userUpload"></div>