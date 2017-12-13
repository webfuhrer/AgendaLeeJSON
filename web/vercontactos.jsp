<%-- 
    Document   : vercontactos
    Created on : 13-dic-2017, 12:05:52
    Author     : luis
--%>

<%@page import="leejson.CrearHTML"%>
<%@page import="java.util.ArrayList"%>
<%@page import="leejson.Contacto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ArrayList<Contacto> lista=(ArrayList<Contacto>)request.getAttribute("lista");
    String html_tabla=CrearHTML.crearTabla(lista);
    
    %>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Contactos:!</h1>
        <%=html_tabla%>
    </body>
</html>
