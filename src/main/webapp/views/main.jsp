<%--
  Created by IntelliJ IDEA.
  User: mvashkewi4
  Date: 02.11.2020
  Time: 14:44
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Payments</title>
</head>
<body>
<div>
    <header>
        <h1>Payments</h1>
    </header>
    <menu>
        <a href="controller?command=logout">Log out</a>
    </menu>
    <main>
        <c:set var="error" value='${requestScope["error"]}'/>
        <p style="color:red;">${error}</p>
        <p>The best payments' system in the world!</p>
        <c:set var="user" value='${applicationScope["user"]}'/>
        <p>Welcome, ${user.getLogin()}!</p>
        <a href="controller?command=go_accounts">Accounts</a>
    </main>
</div>
</body>
</html>
