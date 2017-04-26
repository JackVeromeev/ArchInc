<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>Login</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="StyleSheet" href="css/style.css" type="text/css">

    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>


<body>

    <header>

        <div class="row">

            <div class="col-md-7">

                <h1 id="title">Arch Inc</h1>

                <h1>Please, login</h1>

                <form id="application" action="./" method="POST">
                    <input type="hidden" name="command" value="login" />
                    <input name="login" type="text" id="applicationName" maxlength="20" placeholder="Login" required />
                    <input name="password" type="password" id="applicationPassword" maxlength="20" placeholder="Password" required />

                    <%-- if wrong login or password --%>
                    <c:set value="${requestScope.message}" scope="page" var="msg"/>
                    <c:if test="${not empty msg}">
                        <p id="error-msg">${msg}</p>
                    </c:if>

                    <button class="applicationButton" name="type" value="signin" type="submit">Sign in</button>
                    <button class="applicationButton" name="type" value="signup" type="submit">Sign up</button>
                </form>



            </div>

            <div class="col-md-5">
                <p><img src="img/builder.png" width="450" height="450" align="center"/></p>
            </div>

        </div>





        <h1 id="slogan">Let's build your future</h1>

    </header>

</body>

</html>
