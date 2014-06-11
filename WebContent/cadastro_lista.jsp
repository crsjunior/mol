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
	String descricao = "";
	String resenha = "";
	String preco = "";

	// mensagens:
	if (mensagens != null) {
		// algum erro?
		if (mensagens.get("erro") != null) {
			status = mensagens.pop("erro");
			statusClass = "vermelho";
			descricao = mensagens.pop("descricao");
			resenha = mensagens.pop("resenha");
			preco = mensagens.pop("preco");
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
	<script type="text/javascript">
		$(function()
		{
			$('#txtDescricao').focus();
			$('#btnCadastrar').click(function()
			{
				$('#formCadastroLista').submit();
			});
		});
	</script>
</head>

<body>

<form id="formCadastroLista" name="formCadastroLista" action="CadastroListaServlet" method="post">
	<h1><strong>M</strong>OL</h1>
	<br />
	<h2>Cadastro de Lista</h2>
	<p>
		<input id="txtDescricao" name="txtDescricao" type="text" value="<%=descricao%>" placeholder="Descrição" />
	</p>
	<div id="div-produtos">
		
	</div>
	<div id="status" class="<%=statusClass%>"><%=status%></div>
	<p>
		<a id="btnAdicionarProdutos" class="btn btn-primary btn_tela_inicial">Adicionar produtos</a>
		<a id="btnCadastrar" class="btn btn-primary btn_tela_inicial">Cadastrar Lista</a>
		<a href="<%=caminho%>/index.jsp" class="btn btn_tela_inicial">Cancelar</a>
	</p>
</form>

</body>
</html>