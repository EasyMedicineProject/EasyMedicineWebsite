<%--
  Created by IntelliJ IDEA.
  User: ivan
  Date: 1/6/2018
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/RegisterStyle.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600' rel='stylesheet' type='text/css'>
</head>
<body>
<%
    System.out.println(session.getAttribute("isLogged"));
    if (session.getAttribute("isLogged") != null){
    if ((Boolean) session.getAttribute("isLogged")) {
        System.out.println("You are logged in.");
        response.setHeader("Cache-Control",
                "no-cache, no-store, must-revalidate");// HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
%>
<form method="POST" action="index">
    <input type="submit"  class="btn btn-warning" value="излезни">
</form>

<form action="manage_account.jsp">
    <input type="submit" class="btn btn-info" value="Настройки акаунт">
</form>
<%
    } else {
        System.out.println("v mrysniq if sym");%>
<form action="login.jsp">
    <input type="submit" class="btn btn-success" value="Влез">
</form>

<form action="registration.jsp">
    <input type="submit" class="btn btn-danger" value="Регистрирай се">
</form>

       <% }}
        else if(session.getAttribute("isLogged") == null){
           System.out.println("na pravilnoto mqsto sym");
           response.setHeader("Cache-Control",
                   "no-cache, no-store, must-revalidate");// HTTP 1.1
           response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
           response.setDateHeader("Expires", 0); // Proxies.
%>
<form action="login.jsp">
    <input type="submit" class="btn btn-success" value="Влез">
</form>

<form action="registration.jsp">
    <input type="submit" class="btn btn-danger" value="Регистрирай се">
</form>
<%
    }
%>



</body>
</form>
</body>
</html>
