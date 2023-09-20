<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="webApp.Usuario" %>

<!DOCTYPE html>
<html>
<head>
</head>
<body bgcolor="#fffff">

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");

    Usuario validUser = new Usuario("admin", "admi123");

    if (username != null && password != null && username.equals(validUser.getUsername()) && password.equals(validUser.getPassword())) {
        
        response.sendRedirect("bienvenido.jsp?username=" + username);
    } else {
       
%>
    <p>Usuario o contraseña incorrectos.</p>
<%
    }
%>

<form method="post" action="login.jsp">
    <table width=500px cellpadding="2" cellspacing="2" align="center"
            border="1" bordercolor="#FF69B4" bgcolor="#E6E6FA">
        <tr>
            <td align="center" colspan="2"><font color="#0000F"><h2>INICIA SESION</h2></font></td>
        </tr>
        <tr>
            <td align="center">USERNAME</td>
            <td><input type="text" size="20" name="username"></td>
        </tr>
        <tr>
            <td align="center">PASSWORD</td>
            <td><input type="password" size="20" maxlength="30" name="password"></td>
        </tr>
        <tr>
            <td align="center">&nbsp;</td>
            <td><input type="submit" value="Iniciar Sesión" /></td>
        </tr>
    </table>
</form>

</body>
</html>