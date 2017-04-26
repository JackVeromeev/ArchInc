<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Admin</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">

    <link rel="StyleSheet" href="css/style.css" type="text/css">

</head>
<body>

<div class="row">
    <div class="col-md-3">
        <form action="./" method="post">
            <input type="hidden" name="command" value="admin">
            <button type="submit" name="action" value="createuser" class="fda button-add">Add new User</button>
        </form>
    </div>
    <div class="col-md-2">
        <form action="./" method="post">
            <input type="hidden" name="command" value="login">
            <button type="submit" name="type" value="logout" class="fda button-add">Log out</button>
        </form>
    </div>
</div>


<div class="col-md-12">
    <table border="1" width="100%" cellpadding="5">
        <tr>
            <th>Login</th>
            <th>Type</th>
            <th>Action</th>
        </tr>

        <c:forEach var="user" items="${requestScope.users}">
            <tr>
                <td>${user.login}</td>
                <td>${user.userType}</td>
                <td>
                    <form action="./" method="post">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="command" value="admin">
                        <button class="button-edit" type="submit" name="action" value="edituser">Edit</button>
                        <button class="button-delete" type="submit" name="action" value="deleteuser">Delete</button>
                        </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
