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
	<link rel="stylesheet" href="<%=caminho%>/css/bootstrap.css">
	<link rel="stylesheet" href="<%=caminho%>/css/bootstrap-responsive.css">
	<link rel="stylesheet" href="<%=caminho%>/css/style.css">
	<link rel="stylesheet" href="<%=caminho%>/css/validaForm.css">
	<script type="text/javascript" src="<%=caminho%>/js/jquery.js"></script>
	<script type="text/javascript" src="<%=caminho%>/js/jMask.js"></script>
	<script type="text/javascript" src="<%=caminho%>/js/utils.js"></script>
	<!-- <script type="text/javascript" src="<%=caminho%>/js/cadastro_produto.js"></script> -->
	<script type="text/javascript">
		var formCampos = {
				'txtDescricao': false, 'txtResenha': false, 'txtPreco': false
		};
		var formNome = 'formCadastroProduto';
		var formSubmitOnEnter = false;
	</script>
	<script type="text/javascript" src="<%=caminho%>/js/validaForm.js"></script>
</head>

<body>

<form id="formCadastroProduto" name="formCadastroProduto" action="CadastroProdutoServlet" method="post">
	<h1><strong>M</strong>OL</h1>
	<br />
	<h2>Cadastro de Produto</h2>
	<p>
		<input id="txtDescricao" name="txtDescricao" type="text" value="<%=descricao%>" placeholder="Descrição" data-obrigatorio="true" data-tammin="3" />
		<textarea id="txtResenha" name="txtResenha" rows="4" placeholder="Resenha" data-obrigatorio="true"><%=resenha%></textarea>
		<input id="txtPreco" name="txtPreco" type="text" value="<%=preco%>" placeholder="Preço" data-obrigatorio="true" data-tipo="dinheiro" />
		<a id="btn-add-image" class="btn btn-primary btn_tela_inicial">Adicionar Imagem</a>
	</p>
	<div id="status" class="<%=statusClass%>"><%=status%></div>
	<p>
		<a id="btnSubmit" class="btn btn-primary btn_tela_inicial">Salvar</a>
		<a href="<%=caminho%>/index.jsp" class="btn btn_tela_inicial">Cancelar</a>
	</p>
	<div class="div-arq">
		<input id="arqImagem" name="arqImagem" type="file" accept="image/*" />
	</div>
</form>

</body>
</html>