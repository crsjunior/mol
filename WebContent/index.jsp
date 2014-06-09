<%@page import="br.com.senac.mol.persistencia.DAO"%>
<%@page import="br.com.senac.mol.entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!doctype html>

<%
	// forca a criacao da base de dados caso ela ainda nao exista:
	DAO dao = new DAO();
	dao.getEntityManager();

	// variaveis:
	HttpSession sessao = request.getSession();
	String caminho = request.getContextPath();
	boolean logoff = request.getParameter("logoff") != null;

	// mensagens:
	if (logoff) {
		sessao.invalidate();
		response.sendRedirect(caminho + "/login.jsp");
		return;
	}

	Usuario usuario = (Usuario) sessao.getAttribute("usuario");
	if (usuario == null) {
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return;
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

<form action="login" method="post">
	<h1><strong>M</strong>OL</h1>
	<br />
	<h2>O que deseja fazer?</h2>
	<p>
		<a id="nova_lista" href="cadastro_lista.html" class="btn btn-primary btn_tela_inicial">Nova Lista</a>
		<a id="exibir_lista" href="#self" class="btn btn-primary btn_tela_inicial">Exibir Listas</a>
		<a id="novo_produto" href="<%=caminho%>/cadastro_produto.jsp" class="btn btn-primary btn_tela_inicial">Novo Produto</a>
		<a id="exibir_produtos" href="#self" class="btn btn-primary btn_tela_inicial">Exibir Produtos</a>
		<a id="novo_produto" href="cadastro_estabelecimento.html" class="btn btn-primary btn_tela_inicial">Novo Estabelecimento</a>
		<a id="exibir_produtos" href="#self" class="btn btn-primary btn_tela_inicial">Exibir Estabelecimentos</a>				
		<a href="<%=caminho%>/index.jsp?logoff" class="btn btn_tela_inicial">Sair</a>
	</p>
</form>

</body>
</html>
