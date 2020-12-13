<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mvashkewi4
  Date: 13.12.2020
  Time: 11:36
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Accounts</title>
</head>
<c:set var="accounts" value='${requestScope["accounts"]}'/>
<body>
<table>
    <thead>
    <tr>
        <th>#</th>
        <th>Account number</th>
        <th>Amount</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="account" items="${accounts}" varStatus="loop">
        <tr>
            <td>${loop.index + 1} </td>
            <td>${account.getAccountNumber()}</td>
            <td>${account.getAmount()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="controller?command=go_addAccount">Add account</a>
<a href="controller?command=go_index">Back to main</a>
</body>
</html>
