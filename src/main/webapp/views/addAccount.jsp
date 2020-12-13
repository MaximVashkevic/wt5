<%--
  Created by IntelliJ IDEA.
  User: mvashkewi4
  Date: 13.12.2020
  Time: 11:42
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add account</title>
</head>
<body>
<form action="controller?command=addAccount" method="post">
    <p>
        <label for="account_number">Login</label>
        <input id="account_number" name="account_number" type="text">
    </p>
    <button>Add</button>
</form>
</body>
</html>
