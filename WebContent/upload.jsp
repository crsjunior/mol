<%@page import="org.apache.tomcat.util.http.fileupload.FileUpload"%>
<%@page import="br.com.senac.mol.persistencia.DAO"%>
<%@page import="br.com.senac.mol.entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>

<%

    // variaveis:
    HttpSession sessao = request.getSession();
    String caminho = request.getContextPath();

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>MOL</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-responsive.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
    </head>

    <body>

        <form method="post" action="upload" enctype="multipart/form-data">
            Select file to upload: <input type="file" name="uploadFile" />
            <br/><br/>
            <input type="submit" value="Upload" />
        </form>

    </body>
</html>
