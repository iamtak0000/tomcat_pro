<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<%
    //1. JNDI 서버 객체 새성
    InitialContext ic= new InitialContext();
 
    //2. lookup()
    DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/h2");
 
    //3. getConnection()
    Connection conn = ds.getConnection();
 
    Statement stmt=conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT * FROM INFORMATION_SCHEMA.TABLES");
 
    while(rs.next()) {
        out.print(rs.getString("id") + ":" + rs.getString(2) + "<br>");
    }
 
    //반납
    rs.close();
    stmt.close();
    conn.close();
%>
