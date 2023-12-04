<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 12.10.2021
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdmMainMenuForm</title>
    <link href="util/SS.css" rel="stylesheet">
</head>
<body>
<% if(session.getAttribute("name")==null || session.getAttribute("password")==null){
    session.setAttribute("error","Log in or register!!!");
    response.sendRedirect("/jsp/loginMenu.jsp");}%>
<div id = "header">Меню администратора</div>
<div id = "loginForm">
    <form class="form" action="/addRoom" method="post">
        <fieldset>
            <legend style="text-align: center">Забронировать номер</legend>
            <label for="roomNumber">Номер: </label>
            <input type="number" id="roomNumber" name="roomNumberToAdd" step="1" maxlength="4" min="1" max="400" required="required">
            <br/>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>
    <form class="form" action="/delRoom" method="post">
        <fieldset>
            <legend style="text-align: center">Завершить бронь номера</legend>
            <label for="roomNumberForDel">Номера: </label>
            <input type="number" id="roomNumberForDel" name="roomNumberToDel" step="1" maxlength="4" min="1" max="400" required="required">
            <br/>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>
    <form class="form" action="/regAdm" method="post">
        <fieldset>
            <legend style="text-align: center">Зарегистрировать администратора</legend>
            <label for="admName">Введите имя администратора: </label>
            <input type="text" id="admName" name="admName" required="required">
            <br/>
            <label for="admPassword">Введите пароль: </label>
            <input type="password" id="admPassword" name="admPassword" required="required">
            <br/>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>
    <form class="form" action="/delUsAdm" method="post">
        <fieldset>
            <legend style="text-align: center">Удалить пользователя/администратора</legend>
            <label for="usernameToDel">Введите имя пользователя/администратора: </label>
            <input type="text" id="usernameToDel" name="usernameToDel" required="required">
            <br/>
            <label for="admPassword">Введите пароль пользователя/администратора: </label>
            <input type="password" id="delPassword" name="delPassword" required="required">
            <br/>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>
</div>
<form action="/showData" method="post">
    <button type="submit" class="addButton" style="text-align: center; margin-top: 5px;margin-left: 600px;" >Вывести все забронированные комнаты</button>
</form>
<form action="/showUsers" method="post">
    <button type="submit" class="addButton" style="text-align: center; margin-top: 5px;margin-left: 600px;" >Вывести всех пользователей</button>
</form>
<div class="errArea">
    <p><%if(session.getAttribute("error")!=null){
        out.println(session.getAttribute("error"));
    }session.setAttribute("error",null);%></p>
</div>
</body>
</html>
