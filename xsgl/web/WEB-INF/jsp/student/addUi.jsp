<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-05-30/030
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>

<%
    String basePath = request.getScheme() + "://" + request.getServerName()
            + ":" + request.getServerPort() + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="add">
    <h1>{{title}}

        <span class="glyphicon glyphicon-remove" ng-click="closeWindow()"></span>
    </h1>
    <div class="content">

        <table>
            <tr>
                <td>头像：</td>
                <td>
                    <img ng-src="{{student.img}}" ng-click="showFile()" id="txImg">
                    <input type="file" accept="image/*" id="img" nv-file-select="" uploader="uploader"
                           onchange='angular.element(this).scope().fileNameChanged(this)' style="display:none"/>
                    <p ng-click="showFile()">点击更换头像</p>
                </td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td>
                    <input ng-model="student.name" type="text">

                </td>
            </tr>
            <tr>
                <td>学号：</td>
                <td>
                    <input ng-model="student.schoolId" type="text"><span style="margin-left: 10px;color: red" ng-bind="error.schoolId"></span>

                </td>
            </tr>
            <tr>
                <td>性别：</td>
                <td class="gender">






                    <P ng-click="genderY()" style="cursor: pointer;  float: left;width: 80px;height: 42px;text-align: center;line-height: 42px;"><span
                            ng-class="{false:'glyphicon glyphicon-unchecked',true:'glyphicon glyphicon-check',undefined:'glyphicon glyphicon-unchecked',null:'glyphicon glyphicon-unchecked'}[student.gender]"></span>
                        男
                    </P>
                    <P ng-click="genderX()" style="cursor: pointer;cursor: pointer;  float: left;width: 80px;height: 42px;text-align: center;line-height: 42px;">
                        <span ng-class="{true:'glyphicon glyphicon-unchecked',false:'glyphicon glyphicon-check',undefined:'glyphicon glyphicon-unchecked',null:'glyphicon glyphicon-unchecked'}[student.gender]"></span>
                        女</P>


                </td>
            </tr>
            <tr>
                <td>生日：</td>
                <td>
                    <input ng-model="student.birthday" class="myDate" id="birthday" type="text">
                </td>
            </tr>
            <tr>
                <td>联系电话：</td>
                <td>
                    <input ng-model="student.mobile" type="text">
                </td>
            </tr>


            <tr>
                <td>班级：</td>
                <td>
                    <select ng-model="selected1" ng-options="item.professionName for item in  professionList">
                        <option ng-if="professionList.length==0">请先添加院系</option>
                    </select>
                    <select  ng-model="selected2" ng-options="i.name for i in  departmentList">
                        <option ng-if="departmentList.length==0">请先添加专业</option>
                    </select>
                    <select ng-model="selected3" ng-options="item.classesName for item in  classList">

                    </select>

                </td>
            </tr>
            <tr>
                <td>宿舍楼：</td>
                <td>
                    <input ng-model="student.dormitory" type="text">

                </td>
            </tr>
            <tr>
                <td>宿舍号：</td>
                <td>
                    <input ng-model="student.dormitoryNum" type="text">

                </td>
            </tr>


        </table>


    </div>
    <div>
        <p class="close" ng-click="selectClose()" style="line-height: 45px;height: 45px;cursor: pointer;margin-top: 0">

            <span ng-class="{false:'glyphicon glyphicon-unchecked',true:'glyphicon glyphicon-check',undefined:'glyphicon glyphicon-unchecked'}[windown.isClose]"></span>
            <span>确认并关闭窗口</span>

        </p>
        <p>
            <%--error.isError--%>
            <button type="button" ng-click="addStudent()">确定</button>
            <button type="button" ng-click="closeWindow()">关闭</button>

        </p>

    </div>
</div>
<div class="big"></div>
