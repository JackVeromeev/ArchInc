<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="com.netcracker.veromeev.archinc.dbmanager.DBManager" %>
<%@ page
        import="com.netcracker.veromeev.archinc.dbmanager.exception.DBManagerException" %>
<%@ page import="java.sql.SQLException" %>
<html>
<body>
<h2>Hello World!</h2>
<%
    String s = "";
    try (
            Connection connection = DBManager.getInstance().getConnection();
            PreparedStatement preparedStatement =
            connection.prepareStatement(
                "INSERT INTO Usertype (id_Usertype, Type) VALUES" +
                "(?, ?)")
    ){
        connection.setAutoCommit(false);
        s += "<p>";
        preparedStatement.setInt(1, 1);
        preparedStatement.setString(2, "HR");
        preparedStatement.execute();
        s += "HR kekekek</p>";
        connection.commit();
//        s += "<p>";
//        preparedStatement.setString(1, "CUSTOMER");
//        preparedStatement.execute();
//        s += "</p>";
//        s += "<p>";
//        preparedStatement.setString(1, "MANAGER");
//        preparedStatement.execute();
//        s += "</p>";
//        s += "<p>";
//        preparedStatement.setString(1, "ADMIN");
//        preparedStatement.execute();
//        s += "</p>";
    } catch (DBManagerException | SQLException e) {
        s += "<p>" + e.toString() + "</p>";
    }
%>
<p><%= s %></p>
</body>
</html>
