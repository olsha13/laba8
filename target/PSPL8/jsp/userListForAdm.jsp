<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 13.10.2021
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserList</title>
    <link href="util/SS.css" rel="stylesheet">
</head>
<body>
<% if(session.getAttribute("name")==null || session.getAttribute("password")==null){
    session.setAttribute("error","Log in or register!!!");
    response.sendRedirect("/jsp/loginMenu.jsp");}%>
<div id = "header">Данные о пользователях</div>
<form class="form" action="/jsp/admMainMenuForm.jsp" style="text-align: center">
    <%List<String> list = (List) session.getAttribute("ResultList");
        out.print("ID           Name           Password          IsAdmin");
        out.print("</br>");
        for (String o :list){
            out.print(o);
            out.print("</br>");
        }
        session.setAttribute("ResultSet",null);%>
    <br/>
    <button type="submit" class="addButton">Подтвердить</button>
</form>
</body>
</html>
