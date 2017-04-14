<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="com.netcracker.veromeev.archinc.dbmanager.DBManager" %>
<%@ page
        import="com.netcracker.veromeev.archinc.dbmanager.exception.DBManagerException" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.netcracker.veromeev.archinc.dao.AbstractDAO" %>
<%@ page import="com.netcracker.veromeev.archinc.dao.UserDAO" %>
<%@ page import="com.netcracker.veromeev.archinc.dao.exception.DAOException" %>
<html>
<body>
<h2>Hello World!</h2>
<%
    String s;
    try (
            Connection connection = DBManager.getInstance().getConnection();
    ){
        UserDAO dao = new UserDAO(connection);
        dao.delete(2);
        s = "sukes";
    } catch (DBManagerException | DAOException e) {
        s = e.getMessage() + "</p><p>caused by: " + e.getCause();
    }
%>
<p><%= s %></p>
</body>
</html>
