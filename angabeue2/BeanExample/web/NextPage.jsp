<%-- 
    Document   : NextPage
    Created on : 11.03.2012, 22:07:59
    Author     : Mayerhofer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="myPackage.UserData" scope="session"/> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Example Java Beans - NextPage.jsp</title>
    </head>
    <body>
        <h1>You entered</h1>
        Name: <%= user.getUsername() %><br/>
        Email: <%= user.getEmail() %><br/> 
        Age: <%= user.getAge() %><br/> 
    </body>
</html>
