<%--
  Created by IntelliJ IDEA.
  User: mvashkewi4
  Date: 02.11.2020
  Time: 14:47
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div>
    <header>
        <h1>Login</h1>
    </header>
    <main>
        <c:set var="error" value='${requestScope["error"]}'/>
        <p style="color:red;">${error}</p>
        <form action="controller?command=login" method="post">
            <c:set var="login" value='${requestScope["login"]}'/>
            <label for="login">Login</label>
            <input id="login" name="login" type="text" value="${login}">
            <label for="password">Password</label>
            <input id="password" name="password" type="password">
            <button>Log in</button>
        </form>
        <a href="controller?command=go_signUp">Sign up</a>
    </main>
</div>
</body>
</html>
