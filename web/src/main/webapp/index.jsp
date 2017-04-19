<%@ page import="java.sql.Connection" %>
<%@ page import="com.netcracker.veromeev.archinc.dbmanager.DBManager" %>
<%@ page
        import="com.netcracker.veromeev.archinc.dbmanager.exception.DBManagerException" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="com.netcracker.veromeev.archinc.dao.exception.DAOException" %>
<%@ page import="com.netcracker.veromeev.archinc.entity.OrderedMan" %>
<%@ page import="com.netcracker.veromeev.archinc.dao.OrderedManDAO" %>
<%@ page
        import="com.netcracker.veromeev.archinc.enumeration.EnumerationInitializer" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.netcracker.veromeev.archinc.enumeration.Education" %>
<html>
<body>
    <%
        Connection connection = null;
        try {
            connection = DBManager.getInstance().getConnection();
        } catch (DBManagerException e) {
            e.printStackTrace();
        }
        try {
            EnumerationInitializer.initialize(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>

    <p><%=Education.BASIC.toString()%></p>

    <p><%=Education.AVERAGE.toString()%></p>

    <p><%=Education.SPECIAL_AVERAGE.toString()%></p>

    <p><%=Education.PROFESSIONAL_TECH.toString()%></p>

    <p><%=Education.HIGH.toString()%></p>
</body>
</html>