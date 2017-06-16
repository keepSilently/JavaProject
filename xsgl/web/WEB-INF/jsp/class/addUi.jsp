<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-06-10/010
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="tab">
    <h1>添加

        <span class="glyphicon glyphicon-remove" ng-click="closeWindow()"></span>
    </h1>
    <div class="content TabNav">



        <ul ng-init='activeTab=1'>
            <li ng-class='{active:activeTab==1}' ng-click='activeTab=1'>添加院系</li>
            <li ng-class='{active:activeTab==2}' ng-click='activeTab=2'>添加专业</li>
            <li ng-class='{active:activeTab==3}' ng-click='activeTab=3'>添加班级</li>

        </ul>
        <div class="TabCon">
            <div ng-show='activeTab==1'>
                <input ng-model="addProfessionName"  type="text" placeholder="请输入院系">
                <button type="button" ng-click="addProfession()">确定添加</button>

            </div>
            <div ng-show='activeTab==2' >
                <select ng-model="selected1" ng-options="item.professionName for item in  professionList">
                    <option ng-if="professionList.length==0">请先添加院系</option>
                </select>
                <input type="text" ng-model="departmentName" placeholder="请输入专业">
                <button ng-click="addDepartment()" type="button">确定添加</button>

            </div>
            <div ng-show='activeTab==3'>
                <select ng-model="selected2" ng-options="item.professionName for item in  professionList">
                    <option ng-if="professionList.length==0">请先添加院系</option>
                </select>


                <select  ng-model="selected3" ng-options="i.name for i in  departmentList">
                        <option ng-if="departmentList.length==0">请先添加专业</option>
                </select>
                <input type="text" ng-model="classesName" placeholder="请输入班级">
                <button ng-click="addClass()" type="button">确定添加</button>

            </div>
        </div>


    </div>
    <div>
        <p>
            <button type="button" ng-click="closeWindow()">关闭</button>

        </p>

    </div>
</div>
<div class="big"></div>