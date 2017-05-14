<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add new Employee</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        th{
            padding: 6px;
        }
    </style>
</head>
<body style="background-color: #ADD8E6; padding-left: 20px">

    <h1>Please, fill the blank</h1>
    <form method="post" action="./">
        <input type="hidden" name="command" value="hr" />
        <table width="500px" border="0" >
            <tr>
                <th>First Name</th>
                <td><input type="text" name="first_name" required></td>

            </tr>
            <tr>
                <th>Last Name</th>
                <td><input type="text" name="last_name" required></td>
            </tr>
            <tr>
                <th>Second Name</th>
                <td><input type="text" name="second_name" required></td>
            </tr>
            <tr>
                <th>Age</th>
                <td><input type="number" name="age" required></td>
            </tr>
            <tr>
                <th>Sex</th>
                <td>
                    <select name="sex" size="1">
                        <option>Male</option>
                        <option>Female</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Qualification</th>
                <td><input type="text" name="qualification" required></td>
            </tr>
            <tr>
                <th>Experience (years)</th>
                <td><input type="number" name="experience" required ></td>
            </tr>
            <tr>
                <th>Education</th>
                <td><select size="1" name="education">
                    <option>Basic</option>
                    <option>Average</option>
                    <option >Special-average</option>
                    <option>Professional-technical</option>
                    <option>High</option>
                </select></td>
            </tr>
            <tr>
                <th>Phone Number</th>
                <td><input type="tel" name="phone_number" required></td>
            </tr>
            <tr>
                <th>Salary</th>
                <td><input type="number" name="salary" required></td>
            </tr>
            <th>Address:</th>
            <tr>

                <th>Country</th>
                <td><input type="text" name="country" required></td>
            </tr>
            <tr>

                <th>Region</th>
                <td><input type="text" name="region" required></td>
            </tr>
            <tr>

                <th>Town</th>
                <td><input type="text" name="town" required></td>
            </tr>
            <tr>

                <th>Street</th>
                <td><input type="text" name="street"required></td>
            </tr>
            <tr>

                <th>House</th>
                <td><input type="number" name="house" required></td>
            </tr>
            <tr><td><p></p></td></tr>
            <tr>
                <td>
                    <button type="submit" name="action" value="submit_add">Submit</button>
                    <button type="submit" name="action" value="cancel">Cancel</button>
                </td>
            </tr>

        </table>

    </form>
</body>
</html>
