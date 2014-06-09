<%@page import="br.com.senac.mol.models.MensagensSessao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>

<%
	// variaveis:
	HttpSession sessao = request.getSession();
	MensagensSessao mensagens = (MensagensSessao) sessao.getAttribute("mensagens");
	String status = "&nbsp;";
	String nome = "";
	String email = "";

	// mensagens:
	if (mensagens != null) {
		// algum erro no login?
		if (mensagens.get("erro") != null) {
			status = mensagens.pop("erro");
			nome = mensagens.pop("nome");
			email = mensagens.pop("email");
		}
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
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/cadastro.js"></script>
</head>

<body>

<form id="formCadastroUsuario" name="formCadastroUsuario" action="CadastroUsuarioServlet" method="post">
	<h1><strong>M</strong>OL</h1>
	<br />
	<h2>Cadastre-se</h2>
	<p>
		<input id="txtNome" name="txtNome" type="text" value="<%=nome%>" placeholder="Nome" />
		<input id="txtEmail" name="txtEmail" type="text" value="<%=email%>" placeholder="Email">
		<input id="txtSenha" name="txtSenha" type="password" value="" placeholder="Senha" />
		<input id="txtSenhaConfirma" name="txtSenhaConfirma" type="password" value="" placeholder="Confirme a senha" />
	</p>
	<div id="status" style="text-align: center;"><%=status%></div>
	<p>
		<!-- <input id="submit" name="submit" type="submit" class="btn btn-primary btn_tela_inicial" value="Enviar"><br /> -->
		<a id="btnCadastrar" class="btn btn-primary btn_tela_inicial">Enviar</a><br />
		<!-- <a id="login" href="#self" class="btn btn-primary btn_tela_inicial">Login</a><br /> -->
		<br />
		<a href="<%=request.getContextPath()%>/login.jsp" class="btn btn_tela_inicial">Cancelar</a>
	</p>
</form>

</body>
</html>