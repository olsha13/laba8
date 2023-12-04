<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 12.10.2021
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="util/SS.css" rel="stylesheet">
</head>
<body>
<div id = "header">Авторизация</div>
<div id = "loginForm">
<form class="form" action="/login" method="post">
    <fieldset>
        <legend style="text-align: center">Форма авторизации/регистрации</legend>
        <label for="username">Введите имя пользователя/администратора: </label>
        <input type="text" id="username" name="username" required="required">
        <br/>
        <label for="password">Введите пароль: </label>
        <input type="password" id="password" name="password" required="required">
        <br/>
        <label for="enterAsAdmin">Войти в систему в качестве администратора: </label>
        <input type="checkbox" id="enterAsAdmin" name="enterAsAdmin">
        <br/>
        <label for="register">Новый пользователь : </label>
        <input type="checkbox" id="register" name="register">
        <br/>
        <button type="submit" class="addButton">Подтвердить</button>
        <button type="reset" class="addButton">Очистить</button>
    </fieldset>
</form>
</div>
<div class="errArea">
    <p><%if(session.getAttribute("error")!=null){
        out.println(session.getAttribute("error"));
    }session.setAttribute("error",null);%></p>
</div>
</body>
</html>
