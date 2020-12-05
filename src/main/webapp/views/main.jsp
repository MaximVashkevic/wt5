<%--
  Created by IntelliJ IDEA.
  User: mvashkewi4
  Date: 02.11.2020
  Time: 14:44
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
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
        <p>The best payments' system in the world!</p>
        <c:set var="user" value='${requestScope["user"]}'/>
        <p>Welcome, ${user.getLogin()}!</p>
    </main>
</div>
</body>
</html>
