<%--
  Created by IntelliJ IDEA.
  User: silent
  Date: 2017-05-29/029
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    response.sendRedirect(basePath + "login.action");
%>
