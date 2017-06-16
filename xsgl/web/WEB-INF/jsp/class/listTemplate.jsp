<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-06-10/010
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="classList" >
    <h2>院系，专业，年级，管理</h2>
    <div class="listHeader">
        <div>
            <a ng-click="refresh()"><span class="glyphicon glyphicon-refresh" ></span>刷新</a>
            <a ng-if="permission=='superAdmin'" ui-sref-active="active" ui-sref="class.add" ><span class="glyphicon glyphicon-plus"></span>新增</a>
        </div>

    </div>
    <div class="panel-body" style="height:600px;width: 1200px;border-bottom: 1px solid #ccc" echarts="searchCostOption" id="searchCost"></div>


</div>

<div ui-view="classAdd"></div>