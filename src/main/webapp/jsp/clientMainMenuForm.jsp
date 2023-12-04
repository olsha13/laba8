<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 13.10.2021
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ClientMainMenuForm</title>
    <link href="util/SS.css" rel="stylesheet">
</head>
<body>
<% if(session.getAttribute("name")==null || session.getAttribute("password")==null){
    session.setAttribute("error","Log in or register!!!");
    response.sendRedirect("/jsp/loginMenu.jsp");}%>
<div id = "header">Меню клиента</div>
<div id = "loginForm">
    <form class="form" action="/addClRoom" method="post">
        <fieldset>
            <legend style="text-align: center">Забронировать номер</legend>
            <label for="roomNumber">Номер: </label>
            <input type="number" id="roomNumber" name="clRoomNumberToAdd" step="1" maxlength="4" min="1" max="400" required="required">
            <br/>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>
    <form class="form" action="/delClRoom" method="post">
        <fieldset>
            <legend style="text-align: center">Завершить бронь своего номера</legend>
            <label for="roomNumberForDel">Номера: </label>
            <input type="number" id="roomNumberForDel" name="clRoomNumberToDel" step="1" maxlength="4" min="1" max="400" required="required">
            <br/>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>
</div>
<form action="/showClData" method="post">
    <button type="submit" class="addButton" style="text-align: center; margin-top: 20px;margin-left: 600px;" >Вывести забронированные комнаты</button>
</form>
<div class="errArea">
    <p><%if(session.getAttribute("error")!=null){
        out.println(session.getAttribute("error"));
    }session.setAttribute("error",null);%></p>
</div>
</body>
</html>
