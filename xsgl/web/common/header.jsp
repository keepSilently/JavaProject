<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-05-30/030
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<header>
    <h1>
        logo
    </h1>

    <ul>
        <li>
            <span>上</span>
            <span>首页</span>

        </li>
        <li ui-sref-active="active"  ui-sref="user.page({'pageNo':1})">
            <span>上</span>
            <span>用户管理</span>

        </li>
        <li ui-sref-active="active"  ui-sref="stu.page({'pageNo':1})">
            <span>上</span>
            <span>学生管理</span>
        </li>
        <li>
            <span>上</span>
            <span>个人信息</span>
        </li>
        <li>
            <span>上</span>
            <span>下下下下</span>
        </li>
    </ul>
    <p style="margin-top: -65px">
        <img  src="upload/user/${sessionScope.user.img}"/>
        <span >${sessionScope.user.name}</span>
    </p>
</header>
<nav class="header"></nav>

