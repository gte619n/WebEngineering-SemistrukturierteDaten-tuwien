<%-- 
    Document   : SaveName
    Created on : 11.03.2012, 22:04:57
    Author     : Mayerhofer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="user" class="myPackage.UserData" scope="session"/> 
<jsp:setProperty name="user" property="*"/>  
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Example Java Beans - SaveName.jsp</title>
    </head>
    <body>
        <a href="NextPage.jsp">Continue</a> 
    </body>
</html>
