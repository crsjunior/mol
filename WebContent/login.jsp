<%@page import="br.com.senac.mol.models.MensagensSessao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>

<%
	HttpSession sessao = request.getSession();
	MensagensSessao mensagens = (MensagensSessao) sessao.getAttribute("m");
	String erro = "&nbsp;";
	if (mensagens != null) {
		if (mensagens.get("erro") != null)
			erro = mensagens.pop("erro");
	}
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

<form id="formLogin" name="formLogin" action="LoginServlet" method="post">
	<h1><strong>M</strong>OL</h1>
	<br />
	<h2>Login</h2>
	<p>
		<input id="txtEmail" name="txtEmail" type="text" value="" placeholder="Email" />
		<input id="txtSenha" name="txtSenha" type="password" value="" placeholder="Senha" />
	</p>
	<div style="text-align: center;"><%=erro%></div>
	<p>
		<a id="btnLogin" class="btn btn-primary btn_tela_inicial">Login</a><br />
		<br />
		<a href="<%=request.getContextPath()%>/esqueci_senha.jsp" class="btn btn_tela_inicial">Esqueci minha senha</a><br />
		<a href="<%=request.getContextPath()%>/cadastro_usuario.jsp" class="btn btn_tela_inicial">Junte-se a Rede MOL</a>
	</p>
</form>

</body>
</html>