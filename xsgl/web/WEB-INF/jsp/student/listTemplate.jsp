<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-06-03/003
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div  class="list">
    <h2>学生信息</h2>
    <div class="listHeader">
        <div>
            <input type="text" ng-model="keyword" placeholder="请输入查询的关键字">
            <button type="button" ng-Click="find()"><span class="glyphicon glyphicon-search"></span>查询</button>
        </div>
        <div>
            <a ui-sref-active="active"  ui-sref="stu.upload"><span class="glyphicon glyphicon-upload"></span>从excel导入</a>
            <a ng-click="refresh()"><span class="glyphicon glyphicon-refresh"></span>刷新</a>
            <a ng-if="permission!='user'" ui-sref-active="active" ui-sref=".add" ng-click="add()"><span class="glyphicon glyphicon-plus"></span>新增</a>
            <a  ng-if="permission!='user'" ng-click="edit() " ><span class="glyphicon glyphicon-edit"></span>编辑</a>
            <a ng-if="permission!='user'" ng-click="deleteStudent()"><span class="glyphicon glyphicon-trash" ></span>删除</a>
            <a ng-click="exportExcel()"><span class="glyphicon glyphicon-download-alt" ></span>导出excel</a>
        </div>
    </div>


    <ul class="baseUL">
        <li ng-click="allCheck()">

            <span ng-class="{false:'glyphicon glyphicon-unchecked',true:'glyphicon glyphicon-check '}[check]"  ></span>


        </li>
        <li>姓名</li>
        <li>学号</li>
        <li>班级</li>
        <li>专业</li>
        <li>性别</li>
        <li>宿舍楼</li>
        <li>宿舍号</li>
        <li>联系电话</li>
    </ul>

    <div>
        <ul ng-repeat="student in studentList" ng-click="selectStu(student)">
            <li>
                <span ng-class="{false:'glyphicon glyphicon-unchecked',true:'glyphicon glyphicon-check',undefined:'glyphicon glyphicon-unchecked'}[student.checked]"  ></span>

            </li>
            <li class="tx" >
                <img  ng-src="{{student.img}}">
                <span ng-bind="student.name"></span></li>
            <li ng-bind="student.schoolId"></li>
            <li ng-bind="student.classes.classesName"></li>
            <li ng-bind="student.classes.department.name"></li>
            <li ng-bind="{true:'男',false:'女'}[student.gender]"></li>
            <li ng-bind="student.dormitory"></li>
            <li ng-bind="student.dormitoryNum"></li>
            <li ng-bind="student.mobile"></li>
        </ul>
    </div>
</div>
<div  ui-view="page"></div>
<div class="bounceInDown" ui-view="stuAdd"></div>
<div  ui-view="stuUpload"></div>