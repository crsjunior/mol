<%@page import="br.com.senac.mol.models.MensagensSessao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>

<%
	// variaveis:
	HttpSession sessao = request.getSession();
	MensagensSessao mensagens = (MensagensSessao) sessao.getAttribute("mensagens");
	String caminho = request.getContextPath();
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
	<link rel="stylesheet" href="<%=caminho%>/css/bootstrap.css">
	<link rel="stylesheet" href="<%=caminho%>/css/bootstrap-responsive.css">
	<link rel="stylesheet" href="<%=caminho%>/css/style.css">
	<script type="text/javascript" src="<%=caminho%>/js/jquery.js"></script>
	<!-- <script type="text/javascript" src="<%=caminho%>/js/cadastro.js"></script> -->
	<script type="text/javascript">
		var formCampos = {
				'txtNome': false, 'txtEmail': false, 'txtSenha': false, 'txtSenhaConfirma': false
		};
		var formNome = 'formCadastroUsuario';
	</script>
	<script type="text/javascript" src="<%=caminho%>/js/formUtil.js"></script>
</head>

<body>

    <form autocomplete="off" id="formCadastroUsuario" name="formCadastroUsuario" action="CadastroUsuarioServlet" method="post">
	<h1><strong>M</strong>OL</h1>
	<br />
	<h2>Cadastre-se</h2>
	<p>
		<input autocomplete="off" id="txtNomeCad" name="txtNomeCad" type="text" value="<%=nome%>" placeholder="Nome" />
		<input autocomplete="off" id="txtEmailCad" name="txtEmailCad" type="text" value="<%=email%>" placeholder="Email">
		<input autocomplete="off" id="txtSenhaCad" name="txtSenhaCad" type="password" value="" placeholder="Senha" />
		<input autocomplete="off" id="txtSenhaConfirmaCad" name="txtSenhaConfirmaCad" type="password" value="" placeholder="Confirme a senha" />
	</p>
	<div id="status" style="text-align: center;"><%=status%></div>
	<p>
		<a id="btnSubmit" class="btn btn-primary btn_tela_inicial">Enviar</a><br />
		<br />
		<a href="<%=caminho%>/login.jsp" class="btn btn_tela_inicial">Cancelar</a>
	</p>
</form>

</body>
</html>