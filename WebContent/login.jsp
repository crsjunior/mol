<%@page import="br.com.senac.mol.models.MensagensSessao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>

<%
	// variaveis:
	HttpSession sessao = request.getSession();
	MensagensSessao mensagens = (MensagensSessao) sessao.getAttribute("mensagens");
	String caminho = request.getContextPath();
	String status = "&nbsp;";
	String statusClass = "";
	String email = "";

	// mensagens:
	if (mensagens != null) {
		// algum erro no login?
		if (mensagens.get("erro") != null) {
			status = mensagens.pop("erro");
			statusClass = "vermelho";
			email = mensagens.pop("email");
		}
	}
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>MOL</title>
	<link rel="stylesheet" href="<%=caminho%>/css/bootstrap.css">
	<link rel="stylesheet" href="<%=caminho%>/css/bootstrap-responsive.css">
	<link rel="stylesheet" href="<%=caminho%>/css/style.css">
	<link rel="stylesheet" href="<%=caminho%>/css/validaForm.css">
	<script type="text/javascript" src="<%=caminho%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=caminho%>/js/utils.js"></script>
	<script type="text/javascript">
		var formCampos = {
				'txtEmail': false, 'txtSenha': false
		};
		var formNome = 'formLogin';
		var formSubmitOnEnter = true;
	</script>
	<script type="text/javascript" src="<%=caminho%>/js/validaForm.js"></script>
</head>

<body>

<form id="formLogin" name="formLogin" action="LoginServlet" method="post">
	<h1><strong>M</strong>OL</h1>
	<br />
	<h2>Login</h2>
	<p>
		<input id="txtEmail" name="txtEmail" type="text" value="<%=email%>" placeholder="Email" data-obrigatorio="true" data-tipo="email" />
		<input id="txtSenha" name="txtSenha" type="password" value="" placeholder="Senha" data-obrigatorio="true" data-tammin="6" />
	</p>
	<div id="status" class="<%=statusClass%>"><%=status%></div>
	<p>
		<a id="btnSubmit" class="btn btn-primary btn_tela_inicial">Login</a><br />
		<br />
		<a href="<%=caminho%>/esqueci_senha.jsp" class="btn btn_tela_inicial">Esqueci minha senha</a><br />
		<a href="<%=caminho%>/cadastro_usuario.jsp" class="btn btn_tela_inicial">Junte-se a Rede MOL</a>
	</p>
</form>

</body>
</html>