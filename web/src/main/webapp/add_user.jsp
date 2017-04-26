<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add new user</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="StyleSheet" href="css/style.css" type="text/css">
</head>

<body>
<div class="col-md-12">

    <h1>Add new user</h1>

    <form id="application" action=" ./" method="POST">

        <input type="hidden" name="command" value="admin">
        <input name="login" type="text" id="applicationName" maxlength="20" placeholder="Login" required />
        <input name="password" type="password" id="applicationPassword" maxlength="20" placeholder="Password" required />

        <p id="applicationType">
            <select name="usertype" size="1" >
                <option value="HUMAN_RESOURCE">H-R</option>
                <option value="ADMIN">Admin</option>
                <option value="MANAGER">Manager</option>
            </select>
        </p>

        <button class="applicationButton" name="action" value="submitcreate" type="submit">Save</button>
        <button class="applicationButton" name="action" value="cancel" type="submit">Close</button>

    </form>

</div>

</body>

</html>
